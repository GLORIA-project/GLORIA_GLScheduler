package eu.gloria.rt.exception;


/**
 * Raised 
 * @author jcabello
 *
 */
public class UnsupportedOpException extends RTException {

	public UnsupportedOpException(){
	} 
	
	public UnsupportedOpException(String message){
		super(message); 
	} 
	
	public UnsupportedOpException(Throwable cause) { 
		super(cause); 
	}
	
}
