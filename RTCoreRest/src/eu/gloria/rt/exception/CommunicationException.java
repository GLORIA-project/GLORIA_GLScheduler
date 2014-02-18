package eu.gloria.rt.exception;


/**
 * RTS Communication Exception.
 * 
 * @author jcabello
 *
 */
public class CommunicationException extends RTException {

	/**
	 * Constructor.
	 */
	public CommunicationException(){
	} 
	
	/**
	 * Constructor.
	 * @param message Error message.
	 */
	public CommunicationException(String message){
		super(message); 
	} 
	
	/**
	 * Constructor.
	 * @param cause Exception
	 */
	public CommunicationException(Throwable cause) { 
		super(cause); 
	}
	
}
