package eu.gloria.rt.worker.offshore.bb;

import java.util.ArrayList;
import java.util.List;

public class BBObservingPlanInfo {
	
	private BBObservingPlanState state;
	
	private String minMoonDistance;
	private String targetId;
	private String minAltitude;
	private String id;
	private String exposure;
	private String name;
	private String uuid;
	private String filter;
	private String ra;
	private String maxMoonAltitude;
	private String dec;
	
	private List<BBImageInfo> imgs;
	
	public BBObservingPlanInfo(){
		imgs = new ArrayList<BBImageInfo>();
	}
	
	public BBObservingPlanState getState() {
		return state;
	}
	public void setState(BBObservingPlanState state) {
		this.state = state;
	}
	public String getMinMoonDistance() {
		return minMoonDistance;
	}
	public void setMinMoonDistance(String minMoonDistance) {
		this.minMoonDistance = minMoonDistance;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getMinAltitude() {
		return minAltitude;
	}
	public void setMinAltitude(String minAltitude) {
		this.minAltitude = minAltitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExposure() {
		return exposure;
	}
	public void setExposure(String exposure) {
		this.exposure = exposure;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getMaxMoonAltitude() {
		return maxMoonAltitude;
	}
	public void setMaxMoonAltitude(String maxMoonAltitude) {
		this.maxMoonAltitude = maxMoonAltitude;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}

	public List<BBImageInfo> getImgs() {
		return imgs;
	}

	public void setImgs(List<BBImageInfo> imgs) {
		this.imgs = imgs;
	}

}
