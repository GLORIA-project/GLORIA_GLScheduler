package eu.gloria.gs.sch.exception;

/**
 * 
 * GLORIA Scheduler exception
 * 
 * @author mclopez
 *
 */
public class GLSchException extends Exception {
	
	public GLSchException(){
		
	}
	
	public GLSchException(String message){
		super(message); 
	}

	public GLSchException(Throwable cause){
		super(cause); 
	}

}
