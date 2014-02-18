package eu.gloria.gs.sch;

import java.io.File;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Path;

import eu.gloria.gs.rt.db.TelescopeConnection;
import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOP;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPManager;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPState;
import eu.gloria.gs.sch.db.gloriaOP.OPsegmentation;
import eu.gloria.gs.sch.db.telescOP.SimpleExposure;
import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeOPState;
import eu.gloria.gs.sch.db.util.DBPaginationSearch;
import eu.gloria.gs.sch.entity.GLSchInterface;
import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.PlanInfo;
import eu.gloria.gs.sch.entity.PlanSearchFilter;
import eu.gloria.gs.sch.entity.PlanSearchFilterResult;
import eu.gloria.gs.sch.entity.PlanSearchPaginationInfo;
import eu.gloria.gs.sch.entity.PlanState;
import eu.gloria.gs.sch.entity.PlanStateInfo;
import eu.gloria.gs.sch.entity.PlanType;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.opDivision.ExposureDivision;
import eu.gloria.gs.sch.tarVisib.TargetVisibility;

import eu.gloria.rt.entity.scheduler.PlanSearchPagination;
import eu.gloria.rt.entity.scheduler.PlanStateDetail;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rti_scheduler.RTISchRestProxy;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.file.FileUtil;
import eu.gloria.tools.time.DateTools;
import eu.gloria.tools.uuid.sch.op.UUID;

@Path("Scheduler")
public class Scheduler implements GLSchInterface{
	
	private static final Logger LOG = Logger.getLogger(Scheduler.class.getName());
	
	private String xsdFile =     Config.getProperty("DEV_sch", "opXsdFile");
	private String advBasePath =  Config.getProperty("DEV_sch", "opsXmlFolder");
	
	public String planSubmit(String planList) throws GLSchException {
		
		LOG.info("Executing operation planSubmit");
		
		GloriaOPlan op = null;
		

		try {
			
			if (planList != null) {
				
				try{							
					op = new GloriaOPlan(planList);
				}catch(Exception ex){
					throw new GLSchException("Error Parsing the Observing Plan XML");
				}

				op.setUuid((new UUID()).getGLValue());
				

				String fullFileName = advBasePath + op.getMetadata().getUuid() + ".xml";	
				FileUtil.save(planList, new File(fullFileName), "UTF-8", true);
				
				// Insert into DB.
				GloriaOPManager manager = new GloriaOPManager();

				GloriaOP databaseOP = new GloriaOP();
				databaseOP.setGlOPid(op.getMetadata().getUuid());
				databaseOP.setUser(op.getMetadata().getUser());
				databaseOP.setStateId(GloriaOPState.SUBMITTED);

				manager.createGloriaOP(databaseOP);

				new ExposureDivision(op);
					
				manager.close();
	
			}
		}catch (Exception e){
			throw new GLSchException(e.getMessage());
		}
		
		LOG.info("End of operation planSubmit " + op.getMetadata().getUuid());
		
		return op.getMetadata().getUuid();
		
	}



	public PlanSearchFilterResult getOPByFilter(String number, String size, PlanSearchFilter filter) throws GLSchException {
		
		LOG.info("Executing operation getOPByFilter");
						
		
		PlanSearchPagination pagination = new PlanSearchPagination();
		pagination.setPageNumber(Integer.valueOf(number));
		pagination.setPageSize(Integer.valueOf(size));
		
		PlanSearchFilterResult result = new PlanSearchFilterResult();
		List<PlanInfo> items = new ArrayList<PlanInfo>();		
		
		try{
					
			
			DBPaginationSearch paginationSearch = new DBPaginationSearch();
			paginationSearch.setPageNumber(pagination.getPageNumber());
			paginationSearch.setPageSize(pagination.getPageSize());
			
//			Date execPredictedDateIni = null;
//			Date execPredictedDateEnd = null;
//			Date execBeginDateIni = null;
//			Date execBeginDateEnd = null;
//			Date execEndDateIni = null;
//			Date execEndDateEnd = null;
			Date observationSessionDate = null;
			
			String user = null;
			
						
			if (filter != null){
				
				user = filter.getUser();
				
//				if (filter.getExecPredictedDateInteval() != null && filter.getExecPredictedDateInteval().getMin() != null){
//					execPredictedDateIni = DateTools.getDate(filter.getExecPredictedDateInteval().getMin());
//				}
//				
//				if (filter.getExecPredictedDateInteval() != null && filter.getExecPredictedDateInteval().getMax() != null){
//					execPredictedDateEnd = DateTools.getDate(filter.getExecPredictedDateInteval().getMax());
//				}
//				
//				if (filter.getExecBeginDateInteval() != null && filter.getExecBeginDateInteval().getMin() != null){
//					execBeginDateIni = DateTools.getDate(filter.getExecBeginDateInteval().getMin());
//				}
//				
//				if (filter.getExecBeginDateInteval() != null && filter.getExecBeginDateInteval().getMax() != null){
//					execBeginDateEnd = DateTools.getDate(filter.getExecBeginDateInteval().getMax());
//				}
//				
//				if (filter.getExecEndDateInteval() != null && filter.getExecEndDateInteval().getMin() != null){
//					execEndDateIni = DateTools.getDate(filter.getExecEndDateInteval().getMin());
//				}
//				
//				if (filter.getExecEndDateInteval() != null && filter.getExecEndDateInteval().getMax() != null){
//					execEndDateEnd = DateTools.getDate(filter.getExecEndDateInteval().getMax());
//				}
//				
				observationSessionDate = DateTools.getDate(filter.getObservationSession());
								
				List<PlanState> planStates = new ArrayList<PlanState>();
				List<PlanType> planTypes = new ArrayList<PlanType>();
							
			}
						
			
			//Resolves the pagination result
			GloriaOPManager manager = new GloriaOPManager();
			long rows = manager.getCountByFilter(paginationSearch,user,	filter.getTypes(), filter.getStates(), observationSessionDate);
			
			
			//LIMITATION!!!! No more than 50 items per page
			if (pagination.getPageSize() > 50){
				pagination.setPageSize(50);
			}
			
			//LIMITATION!!! current page >= 1
			if (pagination.getPageNumber() <= 0){
				pagination.setPageNumber(1);
			}
			
			PlanSearchPaginationInfo pagInfo = new PlanSearchPaginationInfo();
			int pages = 0;
			if (rows > 0){
				pages = (new Long(rows / pagination.getPageSize()).intValue());
				if ((new Long(rows % pagination.getPageSize()).intValue()) > 0) pages++;
			}
			
			pagInfo.setPageCount(pages);
			pagInfo.setPageNumber(pagination.getPageNumber());
			pagInfo.setPageSize(pagination.getPageSize());
			result.setPaginationInfo(pagInfo);
			
			//Resolves the page content
			List<GloriaOP> opList = manager.getByFilter(paginationSearch,user,
					filter.getTypes(), filter.getStates(),observationSessionDate);
			
			
			for (int x = 0; x < opList.size(); x++) {

				GloriaOP op = opList.get(x);
				
				if (op != null) {
					
					PlanInfo pi = new PlanInfo();
					pi.setUuid(op.getGlOPid());
					
					PlanStateInfo piInfo = new PlanStateInfo();
					GloriaOPState state = null;
					
					if (op.getStateId() == GloriaOPState.CONFIRMED){
						state = updateGloriaOPStateFromSE(op.getGlOPid());	
					}else{
						state = op.getStateId();
					}
					
					switch (state){
					case CONFIRMED: piInfo.setState(PlanState.CONFIRMED);break;
					case ABORTED: piInfo.setState(PlanState.ABORTED); break;					
					case DONE: piInfo.setState(PlanState.DONE); break;
					case ERROR: piInfo.setState(PlanState.ERROR); break;
					case REJECTED: piInfo.setState(PlanState.REJECTED); break;
					case SUBMITTED: piInfo.setState(PlanState.SUBMITTED); break;					
					}					
					pi.setStateInfo(piInfo);
					
					
					//User
					pi.setUser(op.getUser());
					
					//Description
					pi.setDescription(op.getDescription());
					
					//Comment
					pi.setComment(op.getComment());
					
					//Type
//					switch(op.getType()){
//					case BIAS:
//						pi.setType(PlanType.BIAS);
//						break;
//					case DARK:
//						pi.setType(PlanType.DARK);
//						break;
//					case FLAT:
//						pi.setType(PlanType.FLAT);
//						break;
//					case OBSERVATION:
//						pi.setType(PlanType.OBSERVATION);
//						break;
//					}
					
					//ExecDateObservationSession
//					if (op.getExecDateObservationSession() != null){
//						pi.setObservationSession(DateTools.getXmlGregorianCalendar(op.getExecDateObservationSession()));
//					}
//					
//					//ExecDeadLine
//					if (op.getExecDeadline() != null){
//						pi.setExecDeadline(DateTools.getXmlGregorianCalendar(op.getExecDeadline()));
//					}
//					
//					//PredAstronomical
//					if (op.getPredAstr() != null){
//						pi.setPredAstr(DateTools.getXmlGregorianCalendar(op.getPredAstr()));
//					}
//					
//					//Error
//					piInfo.setErrorCode(BigInteger.valueOf(0));
//					if (op.getState() == ObservingPlanState.ERROR || op.getState() == ObservingPlanState.ABORTED){
//						piInfo.setErrorDesc(op.getComment());
//					}
					
//					//State
//					switch (op.getState()){
//					case ADVERT_ABORTED:
//					case ADVERT_ERROR:
//					case ADVERT_ACCEPTED:
//					case ADVERT_QUEUED:
//					case ADVERT_RUNNING:
//						piInfo.setState(PlanState.ADVERTISE);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					case ABORTED:
//					case ABORTING:
//						piInfo.setState(PlanState.ABORTED);
//						piInfo.setStateDetail(PlanStateDetail.OUT_OF_TIME);
//						break;
//					case ADVERT_REJECTED:
//						piInfo.setState(PlanState.REJECTED);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					case ERROR:
//						piInfo.setState(PlanState.ERROR);
//						piInfo.setStateDetail(PlanStateDetail.WRONG_BEHAVIOUR);
//						break;
//					case QUEUED:
//						piInfo.setState(PlanState.QUEUED);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					case RUNNING:
//						piInfo.setState(PlanState.RUNNING);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					case DONE:
//						piInfo.setState(PlanState.DONE);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					case OFFSHORE:
//						piInfo.setState(PlanState.QUEUED);
//						piInfo.setStateDetail(PlanStateDetail.NONE);
//						break;
//					}
//					
//					if (op.getScheduleDateIni() != null){
//						pi.setScheduleDateIni(DateTools.getXmlGregorianCalendar(op.getScheduleDateIni()));
//					}
//					
//					if (op.getScheduleDateEnd() != null){
//						pi.setScheduleDateEnd(DateTools.getXmlGregorianCalendar(op.getScheduleDateEnd()));
//					}
//					
//					
//					if (op.getState() == ObservingPlanState.DONE){
//						pi.setExecDateIni(DateTools.getXmlGregorianCalendar(op.getExecDateIni()));
//						pi.setExecDateEnd(DateTools.getXmlGregorianCalendar(op.getExecDateEnd()));
//					}
					
					//pi.setQueuedDate(value)
					
					items.add(pi);
				}

			}
			
			result.getItems().addAll(items);
			
			manager.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new GLSchException(ex.getMessage());
		}
		
		LOG.info("End of operation getOPByFilter");
		return result;
	}



	public PlanInfo getOPByUuid(String planUuid) throws GLSchException {
		
		LOG.info("Executing operation getOPByUuid");
		
		PlanInfo info = null;
		GloriaOPState state = null;
		
		GloriaOPManager glManager = new GloriaOPManager();		
		
		GloriaOP op = glManager.getGloriaOP(planUuid);
		if (op.getStateId() == GloriaOPState.CONFIRMED){		
			state = updateGloriaOPStateFromSE(op.getGlOPid());
		}else{
			state = op.getStateId();
		}
					

		info = new PlanInfo();
		info.setComment(op.getComment());
		info.setDescription(op.getDescription());
		info.setType(PlanType.OBSERVATION);
		info.setUser(op.getUser());
		info.setUuid(op.getGlOPid());
		PlanStateInfo stateInfo = new PlanStateInfo();

		switch (state){
		case CONFIRMED: stateInfo.setState(PlanState.CONFIRMED); break;		
		case SUBMITTED:stateInfo.setState(PlanState.SUBMITTED); break;
		case REJECTED:stateInfo.setState(PlanState.REJECTED); break;
		case DONE:stateInfo.setState(PlanState.DONE); break;
		case ABORTED:stateInfo.setState(PlanState.ABORTED); break;
		case ERROR:stateInfo.setState(PlanState.ERROR); break;
		}			
		info.setStateInfo(stateInfo);	
		
		glManager.close();		
		
		LOG.info("End of operation getOPByUuid");
		
		return info;
	}


	private GloriaOPState updateGloriaOPStateFromSE (String gloriaUUID) throws GLSchException{
		
		GloriaOPState newState = null;
		
		GloriaOPManager glManager = new GloriaOPManager();
		TelescOPManager telManager = new TelescOPManager();
		TelescopesDBManager manager = TelescopesDBManager.getTelescopesDBManager ();
		
		List <String> seUUIDList = glManager.getSimpleExposureByGlOP(gloriaUUID);
		for (String seUUID: seUUIDList){
			SimpleExposure exposure = telManager.getSE(seUUID);
			if (exposure.getStateId() == TelescopeOPState.CONFIRMED){
				try {
					int telescopeId = exposure.getAvailableTelescope().getTelescopeList().getId();
//					TelescopeConnection connection = manager.getTelescopeConnection(telescopeId);
					
					TelescopeConnection connection = new TelescopeConnection();
					connection.setIp("localhost");
					connection.setPort("8080");
					connection.setPass("12345");
					connection.setUser("gloria_user");
					
					RTISchRestProxy rtiSchProxy = new RTISchRestProxy(connection.getIp(), connection.getPort(), "RTISchRest/GloriaRTISch");
					UUID uuid = new UUID(telescopeId, exposure.getSimpleExid());
					eu.gloria.rt.entity.scheduler.PlanInfo infoSimpleExposure = rtiSchProxy.getConnection().planSearchByUuid(uuid.getValue());
															
					
					PlanStateInfo stateInfo = new PlanStateInfo();
					
					switch (infoSimpleExposure.getStateInfo().getState()){
					case ADVERTISE:
					case QUEUED:
					case RUNNING: newState = GloriaOPState.CONFIRMED; break;
					case DONE: {
						newState = GloriaOPState.DONE;
						glManager.updateGloriaOPState(gloriaUUID, newState);
						TargetVisibility.deleteExposureMap(seUUID);
					}break;
					case ABORTED:{ 
						newState = GloriaOPState.ABORTED;
						glManager.updateGloriaOPState(gloriaUUID, newState);
						TargetVisibility.deleteExposureMap(seUUID);
					}break;
					case REJECTED:{ 
						newState = GloriaOPState.REJECTED;
						glManager.updateGloriaOPState(gloriaUUID, newState);
						TargetVisibility.deleteExposureMap(seUUID);
					}break;
					case ERROR:{ 
						newState = GloriaOPState.ERROR;
						glManager.updateGloriaOPState(gloriaUUID, newState);
						TargetVisibility.deleteExposureMap(seUUID);
					}break;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new GLSchException (e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					throw new GLSchException (e.getMessage());
				}
			}

		}
		
		telManager.close();
		glManager.close();
		
		return newState;
	}
	
	
}
