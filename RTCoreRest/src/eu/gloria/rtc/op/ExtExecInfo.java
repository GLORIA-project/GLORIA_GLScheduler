package eu.gloria.rtc.op;

public class ExtExecInfo {

	private ExtExecState state;
	private String uuidOp;
	private String description;
	
	public ExtExecState getState() {
		return state;
	}
	public void setState(ExtExecState state) {
		this.state = state;
	}
	public String getUuidOp() {
		return uuidOp;
	}
	public void setUuidOp(String uuidOp) {
		this.uuidOp = uuidOp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
