package eu.gloria.rtc.op;

public class ExtRtsInterruptionInfo {
	
	private boolean interruptable;
	private String unInterruptableReason;
	private ExtExecInterruptionState state;
	
	public boolean isInterruptable() {
		return interruptable;
	}
	public void setInterruptable(boolean interruptable) {
		this.interruptable = interruptable;
	}
	public String getUnInterruptableReason() {
		return unInterruptableReason;
	}
	public void setUnInterruptableReason(String unInterruptableReason) {
		this.unInterruptableReason = unInterruptableReason;
	}
	public ExtExecInterruptionState getState() {
		return state;
	}
	public void setState(ExtExecInterruptionState state) {
		this.state = state;
		
		System.out.print(state+"\n");
	}

}
