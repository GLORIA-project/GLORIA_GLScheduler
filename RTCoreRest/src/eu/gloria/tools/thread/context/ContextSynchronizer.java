package eu.gloria.tools.thread.context;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.log.LogUtil;
import eu.gloria.tools.time.TimeOut;

/**
 * Waits for a context.
 * @author jcabello
 *
 */
public abstract class ContextSynchronizer {
	
	private long timeout;
	private TimeOut timeOut;
	
	public ContextSynchronizer(long periodWaitTime, long timeout){
		this.waitTime = periodWaitTime; 
		this.timeout = timeout;
	}
	
	private long waitTime;
	
	public abstract boolean isValidContext() throws RTException;
	
	public void check() throws RTException, InterruptedException {
		
		try{
			
			if (timeout > 0) {
				timeOut = new TimeOut(timeout);
			}
			
			while (true){
				
				if (isValidContext()) return;
				
				if ((timeout > 0) && (timeOut.timeOut())){
					throw new RTException ("Timeout waiting..." + timeout);
				}
				LogUtil.info(this,"waitfor...sleeeping: " + waitTime);
				
				Thread.sleep(waitTime);
			}
		}catch(InterruptedException iex){
			throw iex;
		}catch(Exception ex){
			throw new RTException(ex.getMessage());
		}
		
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

}
