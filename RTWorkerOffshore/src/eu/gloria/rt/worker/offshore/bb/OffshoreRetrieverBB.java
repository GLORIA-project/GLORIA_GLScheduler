package eu.gloria.rt.worker.offshore.bb;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import eu.gloria.rt.catalogue.CatalogueTools;
import eu.gloria.rt.catalogue.Observer;
import eu.gloria.rt.catalogue.RTSInfo;
import eu.gloria.rt.db.scheduler.ObservingPlan;
import eu.gloria.rt.db.scheduler.ObservingPlanManager;
import eu.gloria.rt.db.scheduler.ObservingPlanState;
import eu.gloria.rt.db.util.DBUtil;
import eu.gloria.rt.entity.db.FileContentType;
import eu.gloria.rt.entity.db.FileFormat;
import eu.gloria.rt.entity.db.FileType;
import eu.gloria.rt.entity.db.ObservingPlanOwner;
import eu.gloria.rt.entity.db.ObservingPlanType;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rt.tools.img.FITS;
import eu.gloria.rt.tools.transfer.FileDownloader;
import eu.gloria.rt.tools.transfer.FileURL;
import eu.gloria.rti.sch.core.OffshorePluginRetriever;
import eu.gloria.rti.sch.core.OffshoreRetriever;
import eu.gloria.rti_db.tools.RTIDBProxyConnection;
import eu.gloria.tools.log.LogUtil;
import eu.gloria.tools.time.DateTools;

public class OffshoreRetrieverBB extends OffshorePluginRetriever implements OffshoreRetriever {
	
	private BBGateway bbGateway;

	@Override
	public void retrieve(long idOp) throws RTSchException {
		
		double obs_altitude = getPropertyValueDouble("Altitude");
		double obs_latitude = getPropertyValueDouble("Latitude");
		double obs_longitude = getPropertyValueDouble("Longitude");
		
		String workDirectory = getPropertyValueString("WorkDirectory");
		
		String proxyHost = getPropertyValueString("RTIDBProxyHost");
		String proxyPort = getPropertyValueString("RTIDBProxyPort");
		String proxyAppName = getPropertyValueString("RTIDBProxyAppName");
		String proxyUser = getPropertyValueString("RTIDBProxyUser");
		String proxyPw = getPropertyValueString("RTIDBProxyPw");
		boolean proxyHttps = Boolean.parseBoolean(getPropertyValueString("RTIDBProxyHttps"));
		String proxyCertRep = getPropertyValueString("RTIDBProxyCertRep");

		
		boolean bbPurge = getPropertyValueBoolean("BBPurge");
		String bbGatewayHost = getPropertyValueString("BBHost");
		String bbGatewayPort = getPropertyValueString("BBPort");
		String bbGatewayAppName = getPropertyValueString("BBAppName");
		boolean secure = getPropertyValueBoolean("BBSecure");
		boolean escape = getPropertyValueBoolean("BBEscape");
		this.bbGateway = new BBGateway(bbGatewayHost, bbGatewayPort, bbGatewayAppName, secure, escape);
		
		int bbMaxImgCount = 0;
		try{
			bbMaxImgCount = getPropertyValueInt("BBMaxImgNumber");
		}catch(Exception ex){
			bbMaxImgCount = 0;
		}
		
		Observer observer = new Observer();
		observer.setAltitude(obs_altitude);
		observer.setLatitude(obs_latitude);
		observer.setLongitude(obs_longitude);
		
		LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). BEGIN");
		LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Configuration. BBMaxImgNumber=" + bbMaxImgCount);
		
		EntityManager em = DBUtil.getEntityManager();
		ObservingPlanManager manager = new ObservingPlanManager();
		
		ObservingPlan dbOp = null;
		
		String finalComment = "";
		ObservingPlanState finalState = ObservingPlanState.DONE;
		List<BBObservingPlanInfo> opsInfo = null;
		
		try{
			
			DBUtil.beginTransaction(em);
			
			dbOp = manager.get(em, idOp);
			
			if (dbOp != null){
				
				//Creates the db webservice proxy
				RTIDBProxyConnection dbProxy = new RTIDBProxyConnection(proxyHost, proxyPort, proxyAppName, proxyUser, proxyPw, proxyHttps, proxyCertRep);
				
				try{
					
					File workDirectoryPath = new File(workDirectory);
					if (!workDirectoryPath.exists() || !workDirectoryPath.isDirectory() ) throw new Exception("Local work directory does not exist: " + workDirectory);
					
					LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). work directory exists: " + workDirectory);
					
					OffshoreOpInfo dbOffshoreOpInfo = OffshoreOpInfo.getInfo(dbOp.getOffshoreOpInfo());
					if (dbOffshoreOpInfo != null){
						
						opsInfo = getOPInfo(dbOffshoreOpInfo);
						if (opsInfo != null && opsInfo.size() > 0){
							
							if (areAllOpsCompleted(opsInfo)){
								
								//DBRepository->Create the Observing Plan
								eu.gloria.rt.entity.db.ObservingPlan repOP = new eu.gloria.rt.entity.db.ObservingPlan();
								repOP.setOwner(ObservingPlanOwner.USER);
								repOP.setType(ObservingPlanType.OBSERVATION);
								repOP.setUser(dbOp.getUser());
								repOP.setUuid(dbOp.getUuid());
								
								try{
									
									String uuid = dbProxy.getProxy().opCreate(repOP);
									repOP = dbProxy.getProxy().opGet(uuid);
									
									LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). DBRepository OP created. UUID= " + uuid);
									
								}catch(Exception ex){
									LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error registering the Observing Plan into the DBRepository. " + ex.getMessage());
									throw new Exception("Error registering the Observing Plan into the DBRepository.");
								}
								
								FileDownloader downloader = new FileDownloader(workDirectory);
								
								for (int x = 0; x < opsInfo.size(); x++){
									BBObservingPlanInfo bbOpInfo =  opsInfo.get(x);
									
									List<BBImageInfo> imgs = bbOpInfo.getImgs();
									if (imgs != null){
										
										for (int y = 0; y < imgs.size(); y++){
											
											if (bbMaxImgCount > 0 && y >= bbMaxImgCount){
												//No more images for this BB OP.
												LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Stop the image recovering. bbOpInfo.id=" + bbOpInfo.getId() + ", count:" + bbMaxImgCount + "/" + imgs.size() );
												break;
											}
											
											BBImageInfo imgInfo = imgs.get(y);
											
											String fileNameFits = null;
											FileURL fileUrl = null;
											eu.gloria.rt.entity.db.File file = null;
											String fileUUID = null;
											String urlSource = null;
											
											try{
											
												//DOWNLOAD FITS FILE
												fileNameFits = workDirectory + "tmp.fits";
												fileUrl = new FileURL(imgInfo.urlFits);
												urlSource = "file://" + workDirectory + "tmp.fits";
												try{
													downloader.download(fileUrl, fileNameFits);
												}catch(Exception ex){
													urlSource = null;
													LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error downloading  fits file format . fileUrl=" + fileUrl + "Error:" + ex.getMessage());
													throw new Exception("Error rror downloading  fits file format.");
												}
											
												//Resolve the FileContentType
												FileContentType fileContentType = FileContentType.OBSERVATION;
												FITS fits = new FITS(new File(fileNameFits));
												String paramExptimeStr = fits.getHeaderParameter("EXPTIME");
												LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). FITS HEADER EXPTIME=" + paramExptimeStr);
												try{
													double paramExptimeDouble = Double.parseDouble(paramExptimeStr);
													if (paramExptimeDouble == 0){
														fileContentType = FileContentType.BIAS;
													}else if (paramExptimeDouble < 0){
														fileContentType = FileContentType.DARK;
													}else {
														fileContentType = FileContentType.OBSERVATION;
													}
												}catch(Exception ex){
													LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). FITS HEADER EXPTIME is not a double. The file will be stored as OBSERVATION content type.");
												}
											
												//Creates the file into RTIDB.
												try{
													file = new eu.gloria.rt.entity.db.File();
													file.setContentType(fileContentType);
													file.setDate(getDate(new Date()));
													file.setType(FileType.IMAGE);
													fileUUID = dbProxy.getProxy().fileCreate(dbOp.getUuid(), file);
						            			
													LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). CREATED GLORIA file UUID=" + fileUUID);
						            			
												}catch(Exception ex){
													LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + ").Error registering a file into the DBRepository. " + ex.getMessage());
													throw new Exception("Error registering a file into the DBRepository.");
												}
											
												//FITS -> Repository
												if (urlSource != null){
													try{
													
														//Creates the format FITS
														LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). source file url=" + urlSource);
														try{
															dbProxy.getProxy().fileAddFormat(fileUUID, FileFormat.FITS, urlSource);
															LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). UPLOADED file format. url=" + urlSource);
														}catch(Exception ex){
															throw new Exception("Error adding a file format to a file into the DBRepository. urlSourcefile=" + urlSource);
														}
													
													}catch(Exception ex){
														LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error adding a file format to a file into the DBRepository. urlSourcefile=" + urlSource + "Error:" + ex.getMessage());
													}
												}
											
											}finally{
												if (fileNameFits != null){
													File tmpFile = new File(fileNameFits);
													tmpFile.delete();
												}
												
											}
											
											
											//JPG download && JPG-> repository
											String fileNameJpg = workDirectory + "tmp.jpeg";
											fileUrl = new FileURL(imgInfo.urlJpg);
											urlSource = "file://" + workDirectory + "tmp.jpeg";
											try{
												
												downloader.download(fileUrl, fileNameJpg);
												
												//Creates the format FITS
								            	LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). source file url=" + urlSource);
								            	try{
							            			dbProxy.getProxy().fileAddFormat(fileUUID, FileFormat.JPG, urlSource);
							            			LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). UPLOADED file format. url=" + urlSource);
							            		}catch(Exception ex){
													throw new Exception("Error adding a file format to a file into the DBRepository. urlSourcefile=" + urlSource);
												}
												
											}catch(Exception ex){
												LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error adding a file format to a file into the DBRepository. urlSourcefile=" + urlSource + "Error:" + ex.getMessage());
											}finally{
												File tmpFile = new File(fileNameJpg);
												tmpFile.delete();
											}
											
										} //for imgs
									} 
								} //for ops
								
								finalState = ObservingPlanState.DONE;
								finalComment = "";
								
								dbOp.setExecDateIni(dbOp.getScheduleDateIni());
								dbOp.setExecDateEnd(dbOp.getScheduleDateEnd());
								
								//Resolves the observation session.
								Date currentSession = getObservationSessionDate(observer, dbOp.getScheduleDateIni());
								dbOp.setExecDateObservationSession(currentSession);
								
								
							}else{
								
								//Error-> not all ops are completed.
								finalState = ObservingPlanState.ABORTED;
								finalComment = "The observing plan was not executed.";
								LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). All OPs are NOT completed. ");
							}
							
						}else{
							//Error no Ops in offshore
							finalState = ObservingPlanState.ERROR;
							finalComment = "The observing plan was not executed.";
							LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). There is not any OP within the BB. ");
						}
						
					}else{
						//Error-> no right json in db
						finalState = ObservingPlanState.ERROR;
						finalComment = "The observing plan was not executed.";
						LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). There is not JSON content into the DB entry. ");
					}
					
				}catch(Exception ex){
					
					ex.printStackTrace();
					
					finalState = ObservingPlanState.ERROR;
					finalComment = "The observing plan was not executed.";
					
					//TODO: remove from RTIDB OP
				}
				
				dbOp.setState(finalState);
				dbOp.setComment(finalComment);
				
				if (finalState != ObservingPlanState.DONE){
					try{
						dbProxy.getProxy().opDelete(dbOp.getUuid());
					}catch(Exception ex){
						LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error deleting OP from RTIDB:" + ex.getMessage());
					}
					
				}
				
				//Purge BB
				if (bbPurge){
					purgeBB(opsInfo);
				}
				
			}else{
				
				throw new Exception("OffshoreRetrieverBB. The observing plan does not exist. ID=" + idOp);
				
			}
			
			DBUtil.commit(em);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			DBUtil.rollback(em);
			
			LogUtil.severe(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). Error:" + ex.getMessage());
			
			throw new RTSchException(ex.getMessage());
			
		} finally {
			DBUtil.close(em);
		}
		
		LogUtil.info(this, "OffshoreRetrieverBB.retrieve(" + idOp + "). END");
		
	}
	
	private void purgeBB(List<BBObservingPlanInfo> opsInfo){
		
		if (opsInfo != null){
			
			for (int x = 0; x < opsInfo.size(); x++){
				BBObservingPlanInfo bbOpInfo =  opsInfo.get(x);
				
				try{
					this.bbGateway.opCancel(bbOpInfo.getId());
				}catch(Exception ex){
					LogUtil.severe(this, "OffshoreRetrieverBB.purging(OpId=" + bbOpInfo.getId() + "). Error canceling OP:" + ex.getMessage());
				}
				
				try{
					this.bbGateway.targetDelete(bbOpInfo.getTargetId());
				}catch(Exception ex){
					LogUtil.severe(this, "OffshoreRetrieverBB.purging(OpId=" + bbOpInfo.getId() + ", targetId=" + bbOpInfo.getTargetId() + "). Error deleting target:" + ex.getMessage());
				}
			}
			
		}
	}
	
	private List<BBObservingPlanInfo> getOPInfo(OffshoreOpInfo offshoreOpInfo) throws Exception{
		
		ArrayList<BBObservingPlanInfo> result = new ArrayList<BBObservingPlanInfo>();
		
		for (int x = 0; x < offshoreOpInfo.getOpIdList().size(); x++){
			result.add(this.bbGateway.opInfo(offshoreOpInfo.getOpIdList().get(x)));
		}
		
		return result;
	}
	
	private boolean areAllOpsCompleted(List<BBObservingPlanInfo> opsInfo){
		
		for (int x = 0; x < opsInfo.size(); x++){
			if (opsInfo.get(x).getState() != BBObservingPlanState.completed) return false;
		}
		
		return true;
	}
	
	private Date getObservationSessionDate(Observer observer, Date date) throws Exception{
		RTSInfo info = CatalogueTools.getSunRTSInfo(observer, date);
		Date currentSession = DateTools.trunk(date, "yyyyMMdd");
		if (date.compareTo(info.getSet()) >= 0){ //after sun set -> currentSession + 1 day
			currentSession = DateTools.increment(currentSession, Calendar.DATE, 1);
		} else { //currentSession
			//Nothing
		}
		return currentSession;
	}
	
	private XMLGregorianCalendar getDate(Date date) throws Exception{
    	GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return xmlCalendar;
    }

}
