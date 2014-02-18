package eu.gloria.rtc.device.validation;

import eu.gloria.rt.exception.RTException;

public class RTCDeviceCommunicationException extends RTException {
	
	public RTCDeviceCommunicationException(){
	} 
	
	public RTCDeviceCommunicationException(String message){
		super(message); 
	} 
	
	public RTCDeviceCommunicationException(Throwable cause) { 
		super(cause); 
	}

}
