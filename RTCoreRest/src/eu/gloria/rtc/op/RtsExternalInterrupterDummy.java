package eu.gloria.rtc.op;


public class RtsExternalInterrupterDummy implements IRtsExternalInterrupter {
	
	private ExtRtsInterruptionInfo info;
	private Thread thread = null;
	private boolean returnEvent = false;
	
	public RtsExternalInterrupterDummy(){
		
		info = new ExtRtsInterruptionInfo();
		info.setInterruptable(true);
		info.setState(ExtExecInterruptionState.RESUMED);
		returnEvent = false;
	}

	@Override
	public void interrupt() throws ExtRtsInterruptionException {
		
		switch(info.getState()){
		case INTERRUPTED:
			throw new ExtRtsInterruptionException("Already INTERRUPTED.");
		case WAIT_TURN:
			throw new ExtRtsInterruptionException("Waiting for turn");
		}

		//sleep 10 seconds
		try {
			Thread.sleep(1000);
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TO WAIT_TURN...
		info.setInterruptable(false);
		info.setState(ExtExecInterruptionState.WAIT_TURN);
		info.setUnInterruptableReason("Waiting...turn");
		
		ToInterrupted th = new ToInterrupted();
		thread = new Thread(th);
		thread.start();
	}

	@Override
	public void resume() {
		
		if (thread != null){
			thread.interrupt();
			thread = null;
		}
		
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
		
		//sleep 10 seconds
		try {
			Thread.sleep(3000);
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ExtRtsInterruptionInfo getInterruptionInfo(){
		return info;
	}

	@Override
	public ExtEventResume getEventResume() {
		
		if (returnEvent){
			
			ExtEventResume result = new ExtEventResume();
			result.setDescription("GRB");
			returnEvent = false;
			return result;
		}
		
		return null;
	}
	
	class ToInterrupted implements Runnable {

		@Override
		public void run() {

			try {
				
				Thread.sleep(30000);
				
				info.setInterruptable(false);
				info.setState(ExtExecInterruptionState.INTERRUPTED);
				info.setUnInterruptableReason("Already INTERRUPTED.");
				
				Thread.sleep(30000);
				
				returnEvent = true;
				
				thread = null;

			} catch (InterruptedException iex) {
				iex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}

		}
	}

}
