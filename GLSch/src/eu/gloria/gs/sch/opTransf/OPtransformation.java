package eu.gloria.gs.sch.opTransf;


import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.op.SimpleExposure;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.rt.entity.scheduler.plan.Binning;
import eu.gloria.rt.entity.scheduler.plan.CameraSettings;
import eu.gloria.rt.entity.scheduler.plan.Constraints;
import eu.gloria.rt.entity.scheduler.plan.Coordinates;
import eu.gloria.rt.entity.scheduler.plan.Expose;
import eu.gloria.rt.entity.scheduler.plan.FilterType;
import eu.gloria.rt.entity.scheduler.plan.Filters;
import eu.gloria.rt.entity.scheduler.plan.Instructions;
import eu.gloria.rt.entity.scheduler.plan.J2000;
import eu.gloria.rt.entity.scheduler.plan.Metadata;
import eu.gloria.rt.entity.scheduler.plan.Mode;
import eu.gloria.rt.entity.scheduler.plan.Plan;
import eu.gloria.rt.entity.scheduler.plan.PlanType;
import eu.gloria.rt.entity.scheduler.plan.Target;
import eu.gloria.rt.entity.scheduler.plan.Targets;
import eu.gloria.tools.uuid.sch.op.UUID;

public class OPtransformation {
	
	
	private int telescopeID;
	private SimpleExposure exposure = null;
	private GloriaOPlan gloriaOP = null;
	private String uuid = null;
	private TelescopesDBManager manager = null;
	
	public OPtransformation (int telescope, GloriaOPlan gloriaOp, SimpleExposure exposure, String uuid){
		
		this.telescopeID = telescope;
		this.exposure = exposure;
		this.gloriaOP = gloriaOp;
		this.uuid = uuid;
		
		manager = TelescopesDBManager.getTelescopesDBManager();
		
	}
	
	public Plan createRTIPlan () throws GLSchException{
		
		Plan rtiPlan = new Plan();
		Metadata rtiPlanMetadata = new Metadata();
		Constraints rtiPlanConstraints = new Constraints();
		Instructions rtiPlanInstructions = new Instructions();
		
		//METADATA
		rtiPlanMetadata.setDescription(gloriaOP.getMetadata().getDescription());
		rtiPlanMetadata.setPriority("0"); //Complete with Karma value
		rtiPlanMetadata.setType(PlanType.OBSERVATION);
		rtiPlanMetadata.setUser(gloriaOP.getMetadata().getUser());
		
		try {
			rtiPlanMetadata.setUuid((new UUID(telescopeID, uuid)).getValue());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//CONSTRAINTS
		String filter = null;
		String data[] = exposure.getFilter().toString().split("GENERIC_");
		if (data.length>1){	//GENERIC			
			try {			
				
				List <String> telescopeFilters = manager.searchTelescopeFilters(telescopeID);
			
				for (String item : telescopeFilters){
					if (item.contains(data[1])){
						filter = item;
						break;
					}
				}
				
				if (filter == null)
					throw new GLSchException("OPTransformation fails. Filter");
			} catch (SQLException e) {
				throw new GLSchException("OPTransformation fails. Filter. " + e.getMessage());
			}
		}
		
		
		Filters filters = new Filters();
		filters.getFilter().add(FilterType.valueOf(filter));
		rtiPlanConstraints.setFilters(filters);
		
		rtiPlanConstraints.setMode(Mode.BATCH);
		
		Targets targets = new Targets();
		Target target = new Target();
		if (exposure.getTarget().getObjName() != null){
			target.setObjName(exposure.getTarget().getObjName());
		}else{
			Coordinates coord = new Coordinates();
			J2000 j = new J2000();
			j.setDEC(exposure.getTarget().getCoordinates().getJ2000().getDEC());
			j.setRA(exposure.getTarget().getCoordinates().getJ2000().getRA());
			coord.setJ2000(j);
			target.setCoordinates(coord);
		}			
		targets.getTarget().add(target);
		rtiPlanConstraints.setTargets(targets);
		
		rtiPlanConstraints.setMoonAltitude(gloriaOP.getConstraints().getMoonAltitude());
		rtiPlanConstraints.setMoonDistance(gloriaOP.getConstraints().getMoonDistance());
		rtiPlanConstraints.setTargetAltitude(gloriaOP.getConstraints().getTargetAltitudeInfo().getTargetAltitude());
		
		//INSTRUCTIONS
		rtiPlanInstructions.setTarget(target);
		CameraSettings settings = new CameraSettings();
		Binning binning = new Binning();
		binning.setBinX(new BigInteger("1"));
		binning.setBinY(new BigInteger("1"));
		settings.setBinning(binning);
		rtiPlanInstructions.setCameraSettings(settings);
		
		Expose exp = new Expose();
		exp.setExpositionTime(exposure.getExposureInfo().getExpositionTime());
		exp.setFilter(FilterType.valueOf(filter));
		exp.setRepeatCount(new BigInteger("1"));
		rtiPlanInstructions.getExpose().add(exp);
		
		
		//RTIPLAN
		rtiPlan.setMetadata(rtiPlanMetadata);
		rtiPlan.setConstraints(rtiPlanConstraints);
		rtiPlan.setInstructions(rtiPlanInstructions);		
		
		return rtiPlan;
		
	}

}
