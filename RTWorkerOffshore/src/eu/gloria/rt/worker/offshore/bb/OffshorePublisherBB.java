package eu.gloria.rt.worker.offshore.bb;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.ws.commons.schema.utils.TargetNamespaceValidator;

import eu.gloria.rt.catalogue.Catalogue;
import eu.gloria.rt.catalogue.ObjCategory;
import eu.gloria.rt.catalogue.ObjInfo;
import eu.gloria.rt.catalogue.Observer;
import eu.gloria.rt.db.scheduler.ObservingPlan;
import eu.gloria.rt.db.scheduler.ObservingPlanManager;
import eu.gloria.rt.db.scheduler.ObservingPlanState;
import eu.gloria.rt.db.util.DBUtil;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rt.unit.Radec;
import eu.gloria.rt.worker.offshore.acp.rtml.RtmlException;
import eu.gloria.rti.sch.core.OffshorePluginPublisher;
import eu.gloria.rti.sch.core.OffshorePublisher;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintMoonAltitude;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintMoonDistance;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintTargetAltitude;
import eu.gloria.rti.sch.core.plan.constraint.Constraints;
import eu.gloria.rti.sch.core.plan.instruction.CameraSettings;
import eu.gloria.rti.sch.core.plan.instruction.Expose;
import eu.gloria.rti.sch.core.plan.instruction.Instruction;
import eu.gloria.rti.sch.core.plan.instruction.Loop;
import eu.gloria.rti.sch.core.plan.instruction.Target;
import eu.gloria.tools.conversion.DegreeFormat;
import eu.gloria.tools.log.LogUtil;
import eu.gloria.tools.time.DateTools;
import eu.gloria.tools.uuid.op.UUID;

public class OffshorePublisherBB  extends OffshorePluginPublisher implements OffshorePublisher {
	
	private String filterDefault;
	private double RTSMinTargetAltitude;
	private double RTSMinMoonDistance;
	private double RTSMaxMoonAltitude;
	private OffshoreOpInfo offshoreOpInfo;
	
	private BBGateway bbGateway;

	@Override
	public void publish(long idOp) throws RTSchException {
		
		String xmlPath= getPropertyValueString("xmlPath");
		String opXSD = getPropertyValueString("opXSD");
		String filterMapping = getPropertyValueString("filterMapping");
		
		double obs_altitude = getPropertyValueDouble("Altitude");
		double obs_latitude = getPropertyValueDouble("Latitude");
		double obs_longitude = getPropertyValueDouble("Longitude");
		
		this.filterDefault = getPropertyValueString("filterLocalDefault");
		this.RTSMinTargetAltitude = getPropertyValueDouble("RTSMinTargetAltitude");
		this.RTSMinMoonDistance = getPropertyValueDouble("RTSMinMoonDistance");
		this.RTSMaxMoonAltitude = getPropertyValueDouble("RTSMaxMoonAltitude");
		
		String bbGatewayHost = getPropertyValueString("BBHost");
		String bbGatewayPort = getPropertyValueString("BBPort");
		String bbGatewayAppName = getPropertyValueString("BBAppName");
		boolean secure = getPropertyValueBoolean("BBSecure");
		boolean escape = getPropertyValueBoolean("BBEscape");
		this.bbGateway = new BBGateway(bbGatewayHost, bbGatewayPort, bbGatewayAppName, secure, escape);
		
	
		this.offshoreOpInfo = new OffshoreOpInfo();
		
		Observer observer = new Observer();
		observer.setAltitude(obs_altitude);
		observer.setLatitude(obs_latitude);
		observer.setLongitude(obs_longitude);
		
		EntityManager em = DBUtil.getEntityManager();
		ObservingPlanManager manager = new ObservingPlanManager();
		
		ObservingPlan dbOp = null;
		
		try{
			
			DBUtil.beginTransaction(em);
			
			dbOp = manager.get(em, idOp);
			
			if (dbOp != null){
				
				try{
					
					
					eu.gloria.rti.sch.core.ObservingPlan op = new eu.gloria.rti.sch.core.ObservingPlan(xmlPath + dbOp.getFile() , opXSD);
					
					BBOpContext context = new BBOpContext();
					
					context.setUuid(dbOp.getUuid());
					context.setOp(op);
					context.loadFilterMapping(filterMapping);
					context.setObserver(observer);
					context.setScheduleDateIni(dbOp.getScheduleDateIni());
					context.setScheduleDateEnd(dbOp.getExecDeadline());
					
					publishOps(context);
					
					dbOp.setOffshoreOpInfo(this.offshoreOpInfo.toJSON());
					dbOp.setEventOffshoreReqDate(new Date());
					dbOp.setState(ObservingPlanState.OFFSHORE);
				
				}catch(Exception ex){
					
					ex.printStackTrace();
					
					dbOp.setState(ObservingPlanState.ERROR);
					dbOp.setComment("ERROR: " + ex.getMessage());
				}
				
			}else{
				
				throw new Exception("OffshorePublisherBB. The observing plan does not exist. ID=" + idOp);
				
			}
			
			DBUtil.commit(em);
			
		} catch (Exception ex) {
			
			DBUtil.rollback(em);
			throw new RTSchException(ex.getMessage());
			
		} finally {
			DBUtil.close(em);
		}
		
	}
	
	private void publishOps(BBOpContext context) throws Exception{
		
		
		List<Instruction> insts = context.getOp().getInstructions();
		Constraints constraints = context.getOp().getConstraints();
		
		//TargetAltitude
		if (constraints != null && constraints.getTargetAltitude() != null ){
			ConstraintTargetAltitude constTargetAltitude = (ConstraintTargetAltitude)constraints.getTargetAltitude();
			double targetAltitude = constTargetAltitude.getAltitude();
			if ( targetAltitude < this.RTSMinTargetAltitude) targetAltitude = this.RTSMinTargetAltitude;
			context.setConstraintTargetAltitude(targetAltitude);
		}else{
			context.setConstraintTargetAltitude(this.RTSMinTargetAltitude);
		}
		
		//MoonDistance
		if (constraints != null && constraints.getMoonDistance() != null ){
			ConstraintMoonDistance constDistance = (ConstraintMoonDistance) constraints.getMoonDistance();
			if (constDistance.getDistance() < this.RTSMinMoonDistance) {
				context.setConstraintMoonDistance(this.RTSMinMoonDistance);
			}else{
				context.setConstraintMoonDistance(constDistance.getDistance());
			}
		}else{
			context.setConstraintMoonDistance(this.RTSMinMoonDistance);
		}
		
		//MoonAltitude
		if (constraints != null && constraints.getMoonAltitude() != null ){
			ConstraintMoonAltitude constMoonAltitude = (ConstraintMoonAltitude) constraints.getMoonAltitude();
			if (constMoonAltitude.getAltitude() > this.RTSMaxMoonAltitude) {
				context.setConstraintMoonAltitude(this.RTSMaxMoonAltitude);
			}else{
				context.setConstraintMoonAltitude(constMoonAltitude.getAltitude());
			}
		}else{
			context.setConstraintMoonAltitude(this.RTSMaxMoonAltitude);
		}
		
		
		publishOps(insts, context);
	}
	
	private void publishOps(List<Instruction> insts, BBOpContext context) throws Exception{
		
		if (insts != null){
			
			String opUUID = context.getUuid();
			
			try{
				
				int count = 0;
				for (int x= 0; x < insts.size(); x++){
					Instruction inst = insts.get(x);
					if (inst instanceof Target){
						context.setTarget((Target)inst);
					}else if (inst instanceof CameraSettings){
						context.setCameraSettings((CameraSettings)inst);
					}else if (inst instanceof Expose){
						context.setExpose((Expose) inst);
						context.setUuid(opUUID + "_" + count);
						count++;
						publishOp(context);
					}else if (inst instanceof Loop){
						//IGNORE
					}
					
				}
				
				
			}catch(Exception ex){
				
				//Delete all targets Id and Ops from BB
				
				LogUtil.severe(this, "OffshorePublisherBB.publishOps(). Error:" + ex.getMessage());
				
				for (int x = 0 ; x < this.offshoreOpInfo.getOpIdList().size(); x++){
					try{
						LogUtil.info(this, "OffshorePublisherBB.publishOps(). Deleting... from BB, opId=" + this.offshoreOpInfo.getOpIdList().get(x));
						if (this.bbGateway.opCancel( this.offshoreOpInfo.getOpIdList().get(x))){
							LogUtil.info(this, "OffshorePublisherBB.publishOps(). Deleted from BB, opId=" + this.offshoreOpInfo.getOpIdList().get(x));
						}else{
							LogUtil.info(this, "OffshorePublisherBB.publishOps(). Impossible to delete from BB the opId=" + this.offshoreOpInfo.getOpIdList().get(x));
						}
						
					}catch(Exception e){
					}
				}
				
				for (int x = 0 ; x < this.offshoreOpInfo.getTargetIdList().size(); x++){
					try{
						LogUtil.info(this, "OffshorePublisherBB.publishOps(). Deleting... from BB, targetID=" + this.offshoreOpInfo.getTargetIdList().get(x));
						if (this.bbGateway.targetDelete(this.offshoreOpInfo.getTargetIdList().get(x))){
							LogUtil.info(this, "OffshorePublisherBB.publishOps(). Deleted from BB, targetID=" + this.offshoreOpInfo.getTargetIdList().get(x));
						}else{
							LogUtil.info(this, "OffshorePublisherBB.publishOps(). Impossible to delete from BB the targetID=" + this.offshoreOpInfo.getTargetIdList().get(x) );
						}
						
					}catch(Exception e){
					}
				}
				
				
				
				throw ex;
				
			}
			
		}
		
	}
	
	private void publishOp(BBOpContext context) throws Exception{
		
		try{
			
			//Date now = new Date();
			//Date gmtNow = DateTools.getGMT(now);
			
			String targetTn = "TargetByRadec";
			String targetRa = null;
			String targetDec = null;
			
			String opFilter = null;
			String opExposure = null;
			String opCamSettingBinX = null;
			String opCamSettingBinY = null;
			String opConstraintTargetAltitude = String.valueOf(context.getConstraintTargetAltitude());
			String opConstraintMoonDistance   = String.valueOf(context.getConstraintMoonDistance());
			String opConstraintMoonAltitude   = String.valueOf(context.getConstraintMoonAltitude());
			String opUuid = context.getUuid();
			
			if (context.getTarget() != null){
				
				if (context.getTarget().getCoordinates() != null && context.getTarget().getCoordinates().getJ2000()!= null){
					
					targetRa = String.valueOf(context.getTarget().getCoordinates().getJ2000().getRa());
					targetDec = String.valueOf(context.getTarget().getCoordinates().getJ2000().getDec());
					
				} else if (context.getTarget().getObjName() != null){
					
					//Resolve the object data into the catalogue
					Catalogue catalogue = new Catalogue(context.getObserver().getLongitude(), context.getObserver().getLatitude(), context.getObserver().getAltitude());
					ObjInfo objInfo  = catalogue.getObject(context.getTarget().getObjName(), ObjCategory.MajorPlanetAndMoon);
					if (objInfo == null){
						objInfo  = catalogue.getObject(context.getTarget().getObjName(), ObjCategory.OutsideSSystemObj);
						if (objInfo == null){
							objInfo  = catalogue.getObject(context.getTarget().getObjName(), ObjCategory.MinorPlanetOrAsteroid);
							if (objInfo == null) throw new Exception("Unknown Target: " + context.getTarget().getObjName());
						}
					}
					
					if (objInfo.getCategory() == ObjCategory.OutsideSSystemObj){ //By RADEC
						
						targetTn = context.getTarget().getObjName();
						targetRa = String.valueOf(objInfo.getPosition().getRaDecimal());
						targetDec = String.valueOf(objInfo.getPosition().getDecDecimal());
						
					}else{ //By name
						
						if (objInfo.getCategory() == ObjCategory.MajorPlanetAndMoon){
							//By radec position because is only for one night
							ObjInfo objInfo2 = catalogue.getObject(context.getTarget().getObjName(), ObjCategory.MajorPlanetAndMoon, context.getScheduleDateIni());
							
							targetTn = context.getTarget().getObjName();
							targetRa = String.valueOf(objInfo2.getPosition().getRaDecimal());
							targetDec = String.valueOf(objInfo2.getPosition().getDecDecimal());
							
						}else{
							throw new Exception("Unknown target: " + context.getTarget().getObjName());
						}
					}
				}
			}
			
			//Filter
			opFilter = getFilter(context);
			
			//Exposition time
			opExposure = String.valueOf(context.getExpose().getExpositionTime());
			if (context.getCameraSettings() != null && context.getCameraSettings().getBinning() != null){
				opCamSettingBinX = String.valueOf(context.getCameraSettings().getBinning().getBinX());
				opCamSettingBinY = String.valueOf(context.getCameraSettings().getBinning().getBinY());
			}
			
			//repetition count
			int exposeCount = 1;
			if (context.getExpose().getRepeatCount() != null){
				exposeCount = context.getExpose().getRepeatCount().intValue();
				if (exposeCount > 1) throw new Exception("Repetition count for Expose must be 1");
			}
			
			
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opUuid" +", PARAM_VALUE=" + opUuid);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=targetTn, PARAM_VALUE=" + targetTn);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=targetRa, PARAM_VALUE=" + targetRa );
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=targetDec, PARAM_VALUE=" + targetDec );
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opFilter, PARAM_VALUE=" + opFilter);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opExposure" +", PARAM_VALUE=" + opExposure);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opCamSettingBinX" +", PARAM_VALUE=" + opCamSettingBinX);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opCamSettingBinY" +", PARAM_VALUE=" + opCamSettingBinY);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opConstraintTargetAltitude" +", PARAM_VALUE=" + opConstraintTargetAltitude);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opConstraintMoonDistance" +", PARAM_VALUE=" + opConstraintMoonDistance);
			LogUtil.info(this, "OffshorePublisherBB.publishOp(). PARAM_NAME=opConstraintMoonAltitude" +", PARAM_VALUE=" + opConstraintMoonAltitude);

			
			//BB-Schedule
			String targetId = null;
			String opId = null;
			try{
					//1) create the target
					targetId = this.bbGateway.targetCreate(targetTn, targetRa, targetDec);
					
					LogUtil.info(this, "OffshorePublisherBB.publishOp(). Created targetID=" +targetId);
					
					//2) schedule the OP.
					opId = this.bbGateway.opCreate(targetId, opExposure, opConstraintTargetAltitude, opConstraintMoonAltitude, opConstraintMoonDistance, opFilter, opUuid);
					
					LogUtil.info(this, "OffshorePublisherBB.publishOp(). Created opId=" +opId);
					
					//3) add to the offshoreOpInfo
					this.offshoreOpInfo.getTargetIdList().add(targetId);
					this.offshoreOpInfo.getOpIdList().add(opId);
					
			}catch(Exception ex){
				
				LogUtil.severe(this, "OffshorePublisherBB.publishOp(). Error:" + ex.getMessage());
				
				if (opId != null){
					
					try{
						
						LogUtil.info(this, "OffshorePublisherBB.publishOp(). Deleting... from BB, opId=" + opId);
						if (this.bbGateway.opCancel( opId)){
							LogUtil.info(this, "OffshorePublisherBB.publishOp(). Deleted from BB, opId=" + opId);
						}else{
							LogUtil.info(this, "OffshorePublisherBB.publishOp(). Impossible to delete from BB the opId=" + opId);
						}
					}catch(Exception e){
						
					}
					
				}
				
				if (targetId != null){
					
					try{
						LogUtil.info(this, "OffshorePublisherBB.publishOp(). Deleting... from BB, targetID=" + targetId);
						if (this.bbGateway.targetDelete(targetId)){
							LogUtil.info(this, "OffshorePublisherBB.publishOp(). Deleted from BB, targetID=" + targetId);
						}else{
							LogUtil.info(this, "OffshorePublisherBB.publishOp(). Impossible to delete from BB the targetID=" + targetId);
						}
						
					}catch(Exception e){
						
					}
					
				}
				
				throw ex;
				
			}finally{
				
			}
		}catch(Exception ex){
			throw ex;
		}
		
		
	}
	
	private String getFilter(BBOpContext context) throws RtmlException{
		
		String localFilter = null;
		
		if (context.getExpose().getFilter() != null){
			localFilter = context.getFilterMappingGloria2BB().get(context.getExpose().getFilter());
		}else{
			localFilter = this.filterDefault; //By default
		}
		
		return localFilter;
		
		
	}

}
