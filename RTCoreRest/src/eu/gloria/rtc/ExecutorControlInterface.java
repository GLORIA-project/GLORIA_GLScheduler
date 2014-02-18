package eu.gloria.rtc;

import eu.gloria.rt.exception.RTException;
import eu.gloria.rtc.op.ExtExecInfo;
import eu.gloria.rtc.op.OpInfo;

/**
 * 
 * @author jcabello
 *
 */
public interface ExecutorControlInterface {
	
	public String start(String uuidOp, String user, long seconds) throws RTException;
	
	public void stop() throws RTException;
	
	public ExtExecInfo getExtExecInfo()throws RTException;
	
	public OpInfo getLastOpInfo() throws RTException;
	
	public boolean isRunningOp() throws RTException;
	

}
