package eu.gloria.rt.exception;


public class UnknownDataException extends RTException {

	public UnknownDataException(){
	} 
	
	public UnknownDataException(String message){
		super(message); 
	} 
	
	public UnknownDataException(Throwable cause) { 
		super(cause); 
	}
	
}
