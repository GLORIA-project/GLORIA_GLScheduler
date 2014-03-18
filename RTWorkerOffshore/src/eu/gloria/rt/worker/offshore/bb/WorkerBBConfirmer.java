package eu.gloria.rt.worker.offshore.bb;

import java.nio.channels.ClosedByInterruptException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import eu.gloria.rt.db.scheduler.ObservingPlan;
import eu.gloria.rt.db.scheduler.ObservingPlanManager;
import eu.gloria.rt.db.task.TaskProperty;
import eu.gloria.rt.db.util.DBUtil;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rt.worker.core.Worker;
import eu.gloria.tools.log.LogUtil;
import eu.gloria.tools.time.DateTools;

public class WorkerBBConfirmer extends Worker {
	
	private BBGateway bbGateway;
	
	public WorkerBBConfirmer(){
		
		super();
		
	}
	
	@Override
	public void init(String id, long sleepTime, List<TaskProperty> properties){
		
		super.init(id, sleepTime, properties);
		
	}

	@Override
	protected void doAction() throws InterruptedException,
			ClosedByInterruptException, Exception {
		
		LogUtil.info(this, "WorkerBBConfirmer::BEGIN.");
		
		String bbGatewayHost = getPropertyStringValue("BBHost");
		String bbGatewayPort = getPropertyStringValue("BBPort");
		String bbGatewayAppName = getPropertyStringValue("BBAppName");
		boolean secure = getPropertyBooleanValue("BBSecure");
		boolean escape = getPropertyBooleanValue("BBEscape");
		int secsInc = getPropertyIntValue("advertOffshoreDeadlineSecsIncrement");
		
		this.bbGateway = new BBGateway(bbGatewayHost, bbGatewayPort, bbGatewayAppName, secure, escape);
		
		
		
		EntityManager em = DBUtil.getEntityManager();
		ObservingPlanManager manager = new ObservingPlanManager();
		
		ObservingPlan dbOp = null;
		
		try{
			
			DBUtil.beginTransaction(em);
			
			LogUtil.info(this, "WorkerBBConfirmer::looking for op to confirm....");
			
			dbOp = manager.getToOffshoreConfirmation(em);
			
			if (dbOp != null){
				
				LogUtil.info(this, "WorkerBBConfirmer::Processing opUuid=" + dbOp.getUuid());
				
				OffshoreOpInfo opInfo = OffshoreOpInfo.getInfo(dbOp.getOffshoreOpInfo());
				
				if (areAllOpsApprovedOrConfirmedInOffshore(opInfo)){
					
					//Confirm all Ops.
					try{
						
						confirm(opInfo);
						
					}catch(Exception ex){
						LogUtil.severe(this, "WorkerBBConfirmer. confirming[uuid=" +dbOp.getUuid() + "]. Error:" + ex.getMessage());
						
						//update the advertOffshoreDeadline..to check later...again
						dbOp.setAdvertOffshoreDeadline(DateTools.increment(dbOp.getAdvertOffshoreDeadline(), Calendar.SECOND, secsInc));
					}
					
					dbOp.setEventOffshoreConfirmDate(new Date());
					
				}else if (isThereAtLeastOneOpRejected(opInfo)) {
					
					//Force to get advertdeadline over --> The OP will be rejected.
					dbOp.setAdvertOffshoreDeadline(dbOp.getAdvertDeadline()); 
					
				}else{
					
					//update the advertOffshoreDeadline
					dbOp.setAdvertOffshoreDeadline(DateTools.increment(dbOp.getAdvertOffshoreDeadline(), Calendar.SECOND, secsInc));
					
				}
				
				LogUtil.info(this, "WorkerBBConfirmer::PROCESSED opUuid=" + dbOp.getUuid());
				
			}else{
				LogUtil.info(this, "WorkerBBConfirmer::Processed opUuid= NONE");
			}
			
			DBUtil.commit(em);
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
			DBUtil.rollback(em);
			
			throw new RTSchException(ex.getMessage());
			
		}finally{
			
			DBUtil.close(em);
			
		}
		
		LogUtil.info(this, "WorkerBBConfirmer::END.");
		
	}
	
	public boolean areAllOpsApprovedOrConfirmedInOffshore(OffshoreOpInfo opInfo) throws Exception{
		
		
		if (opInfo != null){
			if (opInfo.getOpIdList() != null){
				for (int x = 0; x < opInfo.getOpIdList().size(); x++){
					
					if (bbGateway.opState(opInfo.getOpIdList().get(x)) != BBObservingPlanState.approved && bbGateway.opState(opInfo.getOpIdList().get(x)) != BBObservingPlanState.confirmed ){
						return false;
					}
					
				}
			}
		}
		
		
		return true;
		
	}
	
	public boolean isThereAtLeastOneOpRejected(OffshoreOpInfo opInfo) throws Exception{
		
		
		if (opInfo != null){
			if (opInfo.getOpIdList() != null){
				for (int x = 0; x < opInfo.getOpIdList().size(); x++){
					
					if (bbGateway.opState(opInfo.getOpIdList().get(x)) == BBObservingPlanState.rejected){
						return true;
					}
					
				}
			}
		}
		
		
		return false;
		
	}
	
	
	public boolean confirm(OffshoreOpInfo opInfo) throws Exception{
		
		
		if (opInfo != null){
			if (opInfo.getOpIdList() != null){
				for (int x = 0; x < opInfo.getOpIdList().size(); x++){
					
					String opId = opInfo.getOpIdList().get(x);
					
					if (bbGateway.opState(opId) == BBObservingPlanState.approved){
						if (!this.bbGateway.opConfirm(opId)){
							throw new Exception("Impossible to confirm [opId=" + opId + "]");
						}else{
							LogUtil.info(this,"WorkerBBConfirmer.confirme(). Confirmed [opID=" +opId +  "]" );
						}
					}
					
				}
			}
		}
		
		
		return true;
		
	}

}
