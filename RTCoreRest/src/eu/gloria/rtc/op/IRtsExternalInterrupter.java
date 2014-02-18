package eu.gloria.rtc.op;

public interface IRtsExternalInterrupter {
	
	public void interrupt() throws ExtRtsInterruptionException;
	public void resume() throws ExtRtsInterruptionException;
	public ExtRtsInterruptionInfo getInterruptionInfo();
	public ExtEventResume getEventResume() throws ExtRtsInterruptionException;

}
