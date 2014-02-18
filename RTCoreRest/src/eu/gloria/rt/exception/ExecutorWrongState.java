package eu.gloria.rt.exception;

public class ExecutorWrongState extends RTException {
	
	public ExecutorWrongState(){
	} 
	
	public ExecutorWrongState(String message){
		super(message); 
	} 
	
	public ExecutorWrongState(Throwable cause) { 
		super(cause); 
	}

}
