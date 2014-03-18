package eu.gloria.rt.worker.offshore.bb;

import java.util.Date;
import java.util.HashMap;

import eu.gloria.rt.catalogue.Observer;
import eu.gloria.rti.sch.core.ObservingPlan;
import eu.gloria.rti.sch.core.plan.instruction.CameraSettings;
import eu.gloria.rti.sch.core.plan.instruction.Expose;
import eu.gloria.rti.sch.core.plan.instruction.Target;

public class BBOpContext {
	
	private String uuid;
	private ObservingPlan op;
	private HashMap<String, String> filterMappingGloria2BB;
	private Observer observer;
	private Date scheduleDateIni;
	private Date scheduleDateEnd;
	
	private double constraintTargetAltitude;
	private double constraintMoonAltitude;
	private double constraintMoonDistance;
	
	private CameraSettings cameraSettings;
	private Expose expose;
	private Target target;

	
	public BBOpContext() {
		filterMappingGloria2BB = new HashMap<String, String>();
	}
	
	/**
	 * FilterMapping:
	 * @param filterMapping GLORIA_FILTER1=TALON_FILTER1;GLORIA_FILTER2=TALON_FILTER2....
	 */
	public void loadFilterMapping(String filterMapping){
		
		filterMappingGloria2BB.clear();
		
		if (filterMapping != null){
			
			String[] pairs = filterMapping.split(";");
			for (int pair = 0 ; pair < pairs.length; pair++){
				
				String[] values = pairs[pair].split("=");
				filterMappingGloria2BB.put(values[0], values[1]);
			}
			
		}
	}

	public ObservingPlan getOp() {
		return op;
	}

	public void setOp(ObservingPlan op) {
		this.op = op;
	}

	public HashMap<String, String> getFilterMappingGloria2BB() {
		return filterMappingGloria2BB;
	}

	public void setFilterMappingGloria2BB(
			HashMap<String, String> filterMappingGloria2BB) {
		this.filterMappingGloria2BB = filterMappingGloria2BB;
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public Date getScheduleDateIni() {
		return scheduleDateIni;
	}

	public void setScheduleDateIni(Date scheduleDateIni) {
		this.scheduleDateIni = scheduleDateIni;
	}

	public Date getScheduleDateEnd() {
		return scheduleDateEnd;
	}

	public void setScheduleDateEnd(Date scheduleDateEnd) {
		this.scheduleDateEnd = scheduleDateEnd;
	}

	public CameraSettings getCameraSettings() {
		return cameraSettings;
	}

	public void setCameraSettings(CameraSettings cameraSettings) {
		this.cameraSettings = cameraSettings;
	}

	public Expose getExpose() {
		return expose;
	}

	public void setExpose(Expose expose) {
		this.expose = expose;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public double getConstraintTargetAltitude() {
		return constraintTargetAltitude;
	}

	public void setConstraintTargetAltitude(double constraintTargetAltitude) {
		this.constraintTargetAltitude = constraintTargetAltitude;
	}

	public double getConstraintMoonAltitude() {
		return constraintMoonAltitude;
	}

	public void setConstraintMoonAltitude(double constraintMoonAltitude) {
		this.constraintMoonAltitude = constraintMoonAltitude;
	}

	public double getConstraintMoonDistance() {
		return constraintMoonDistance;
	}

	public void setConstraintMoonDistance(double constraintMoonDistance) {
		this.constraintMoonDistance = constraintMoonDistance;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
