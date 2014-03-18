package eu.gloria.rt.worker.offshore.bb;

public class BBCommunicationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BBCommunicationException(){ 
	} 
	
	public BBCommunicationException(String message){
		super(message); 
	} 
	
	public BBCommunicationException(Throwable cause) { 
		super(cause); 
	}

}
