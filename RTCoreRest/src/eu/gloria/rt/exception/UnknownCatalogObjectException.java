package eu.gloria.rt.exception;


/**
 * Exception used when a global object identifier cannot be translate to a local object identifier (mount).
 * 
 * @author jcabello
 *
 */
public class UnknownCatalogObjectException extends RTException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public UnknownCatalogObjectException(){
	} 
	
	/**
	 * Constructor.
	 * @param message Error message.
	 */
	public UnknownCatalogObjectException(String message){
		super(message); 
	} 
	
	/**
	 * Constructor.
	 * @param cause Exception
	 */
	public UnknownCatalogObjectException(Throwable cause) { 
		super(cause); 
	}

}
