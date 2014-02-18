package eu.gloria.rt.exception;

import java.math.BigInteger;

/**
 * RT Exception
 * @author jcabello
 *
 */
public class RTException extends Exception {
	
	/**
	 * Error code. 1 by default.
	 */
	private BigInteger errorCode;

	/**
	 * Constructor.
	 */
	public RTException(){
		errorCode = new BigInteger("1");
		
		//this.printStackTrace();
	} 
	
	/**
	 * Constructor.
	 * @param message Error message
	 */
	public RTException(String message){
		super(message); 
		errorCode = new BigInteger("1");
		
		//this.printStackTrace();
	} 
	
	/**
	 * Constructor.
	 * @param cause internal Exception.
	 */
	public RTException(Throwable cause) { 
		super(cause); 
		errorCode = new BigInteger("1");
		
		//this.printStackTrace();
	}
	
	/**
	 * Constructor.
	 * @param message Error message.
	 * @param errCode Error code.
	 */
	public RTException(String message, int errCode){
		super(message); 
		errorCode = new BigInteger(String.valueOf(errCode));
		
		//this.printStackTrace();
	}
	
	/**
	 * Constructor.
	 * @param message Error message.
	 * @param errCode Error code.
	 */
	public RTException(String message, BigInteger errCode){
		super(message); 
		errorCode = errCode;
		
		//this.printStackTrace();
	}
	
	/**
	 * Constructor.
	 * @param cause Internal Exception.
	 * @param errCode Error code.
	 */
	public RTException(Throwable cause, int errCode) { 
		super(cause); 
		errorCode = new BigInteger(String.valueOf(errCode));
		
		//this.printStackTrace();
	}
	
	/**
	 * Constructor.
	 * @param cause Internal Exception.
	 * @param errCode Error code.
	 */
	public RTException(Throwable cause, BigInteger errCode) { 
		super(cause); 
		errorCode = errCode;
		
		//this.printStackTrace();
	}

	/**
	 * Access method
	 * @return Error code.
	 */
	public BigInteger getErrorCode() {
		return errorCode;
	}

	/**
	 * Access method
	 * @param errorCode error code.
	 */
	public void setErrorCode(BigInteger errorCode) {
		this.errorCode = errorCode;
	}
}
