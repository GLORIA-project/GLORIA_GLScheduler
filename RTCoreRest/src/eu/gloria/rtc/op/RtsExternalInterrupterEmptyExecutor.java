package eu.gloria.rtc.op;


/**
 * RTS External Interrupter implementation for RTS without External executor.
 * 
 * @author jcabello
 *
 */
public class RtsExternalInterrupterEmptyExecutor implements
		IRtsExternalInterrupter {
	
	private ExtRtsInterruptionInfo info;
	
	public RtsExternalInterrupterEmptyExecutor(){
		
		info = new ExtRtsInterruptionInfo();
		info.setInterruptable(true);
		info.setState(ExtExecInterruptionState.RESUMED);
	}

	@Override
	public void interrupt() throws ExtRtsInterruptionException {

		switch(info.getState()){
		case INTERRUPTED:
			throw new ExtRtsInterruptionException("Already INTERRUPTED.");
		case WAIT_TURN:
			throw new ExtRtsInterruptionException("Waiting for turn");
		}
		
		info.setInterruptable(false);
		info.setState(ExtExecInterruptionState.INTERRUPTED);
		info.setUnInterruptableReason("Already INTERRUPTED.");
	}

	@Override
	public void resume() throws ExtRtsInterruptionException {

		switch(info.getState()){
		
		case INTERRUPTED:
			
			info.setInterruptable(true);
			info.setState(ExtExecInterruptionState.RESUMED);
			info.setUnInterruptableReason("");
			
			break;
			
		case RESUMED:
			
			info.setInterruptable(true);
			info.setState(ExtExecInterruptionState.RESUMED);
			info.setUnInterruptableReason("");
			
			break;
			
		case WAIT_TURN:
			
			info.setInterruptable(true);
			info.setState(ExtExecInterruptionState.RESUMED);
			info.setUnInterruptableReason("");
			
			break;
		}

	}

	@Override
	public ExtRtsInterruptionInfo getInterruptionInfo() {
		return info;
	}

	@Override
	public ExtEventResume getEventResume() {
		return null;
	}

}
