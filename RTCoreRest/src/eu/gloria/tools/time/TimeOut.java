package eu.gloria.tools.time;

import java.util.Date;

public class TimeOut {
	
	private long initTime;
	private long timeout;
	
	public TimeOut(long timeout){
		
		Date now = new Date();
		this.initTime = now.getTime();
		this.timeout = timeout;
		
	}
	
	public boolean timeOut(){
		Date now = new Date();
		boolean condition = timeout + initTime <= now.getTime();
		
//		if (condition)
//			condition = false;
		
		return (condition);
	}
	
	public void reset(){
		Date now = new Date();
		initTime = now.getTime();
	}

}
