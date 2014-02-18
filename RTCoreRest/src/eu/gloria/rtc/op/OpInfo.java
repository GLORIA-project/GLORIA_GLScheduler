package eu.gloria.rtc.op;

import eu.gloria.tools.time.TimeOut;

public class OpInfo {
	
	private String uuid;
	private String user;
	private long duration;
	private String desc;
	private OpState state;
	private TimeOut timeout;
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public OpState getState() {
		return state;
	}
	public void setState(OpState state) {
		this.state = state;
	}
	public TimeOut getTimeout() {
		return timeout;
	}
	public void setTimeout(TimeOut timeout) {
		this.timeout = timeout;
	}
	
	public String toString(){
		return "uuid=" + uuid + ", user=" + uuid + ", duration=" + duration + ", desc=" + desc + ", state=" + state;
	}
	
	

}
