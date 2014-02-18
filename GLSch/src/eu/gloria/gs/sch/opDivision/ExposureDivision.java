package eu.gloria.gs.sch.opDivision;


import eu.gloria.gs.sch.core.SchedulerCore;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeOPState;
import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.op.SimpleExposure;
import eu.gloria.gs.sch.exception.GLSchException;

public class ExposureDivision {
	

	public ExposureDivision(GloriaOPlan gloriaOP) throws GLSchException{
				
		SimpleExposure exposure = new SimpleExposure();
		exposure.setExposureInfo(gloriaOP.getInstructions().getExpose().getExposureTimeInfo());
		exposure.setFilter(gloriaOP.getInstructions().getExpose().getFilter());
		exposure.setTarget(gloriaOP.getInstructions().getTarget());
		
		
		//To database
		eu.gloria.gs.sch.db.telescOP.SimpleExposure exposureDB = new eu.gloria.gs.sch.db.telescOP.SimpleExposure();
		exposureDB.setFilter(exposure.getFilter().toString());
		exposureDB.setSimpleExid(gloriaOP.getMetadata().getUuid()+"se1");
		exposureDB.setStateId(TelescopeOPState.IDLE);
		
		if (exposure.getTarget().getObjName() != null)
			exposureDB.setTargetName(exposure.getTarget().getObjName());
		else
			exposureDB.setTargetRADEC(exposure.getTarget().getCoordinates().toString());
		
		
		TelescOPManager telescManager = new TelescOPManager();
		telescManager.createSimpleExposure(exposureDB);
		telescManager.close();
				
		GloriaOPManager opManager = new GloriaOPManager();
		eu.gloria.gs.sch.db.gloriaOP.OPsegmentation segmentation = new eu.gloria.gs.sch.db.gloriaOP.OPsegmentation();
		segmentation.setSimpleExposure(exposureDB);
		segmentation.setGloriaOp(opManager.getGloriaOP(gloriaOP.getMetadata().getUuid()));
		opManager.createOPsegmentation(segmentation);
		
		opManager.close();
		
		SchedulerCore schedulerThread = new SchedulerCore (gloriaOP, exposure, exposureDB.getSimpleExid());
		
		new Thread (schedulerThread).start();
		
	}
}
