package eu.gloria.rtc.device.validation;

import eu.gloria.rt.exception.RTException;

public class RTCDeviceActivityException extends RTException {
	
	public RTCDeviceActivityException(){
	} 
	
	public RTCDeviceActivityException(String message){
		super(message); 
	} 
	
	public RTCDeviceActivityException(Throwable cause) { 
		super(cause); 
	}
	

}
