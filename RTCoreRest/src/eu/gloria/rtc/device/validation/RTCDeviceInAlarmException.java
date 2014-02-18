package eu.gloria.rtc.device.validation;

import eu.gloria.rt.exception.RTException;

public class RTCDeviceInAlarmException extends RTException {
	
	public RTCDeviceInAlarmException(){
	} 
	
	public RTCDeviceInAlarmException(String message){
		super(message); 
	} 
	
	public RTCDeviceInAlarmException(Throwable cause) { 
		super(cause); 
	}
}
