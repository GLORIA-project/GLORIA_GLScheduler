package eu.gloria.rtc.device.validation;

import eu.gloria.rt.entity.device.Device;

/**
 * Wraps business logic related to the devices states.
 * @author jcabello
 *
 */
public class DeviceStateManager {
	
	/**
	 * Device to manage.
	 */
	private Device device;
	
	/**
	 * Constructor.
	 * @param device  Device to manage.
	 */
	public DeviceStateManager(Device device){
		this.device = device;
	}
	
	/**
	 * Checks if the device is online and can process a request.
	 * @return boolean
	 */
	public boolean isOperative(){
		return false;
	}
	
	/**
	 * Checks if the device is online and can process a request.
	 * @throws RTCDeviceCommunicationException If a communication problems exists.
	 * @throws RTCDeviceActivityException If an activity problem exists.
	 * @throws RTCDeviceInAlarmException If the device is in alarm state
	 */
	public void checkIsOperative() throws RTCDeviceCommunicationException, RTCDeviceActivityException, RTCDeviceInAlarmException{
		
		//Communication
		DevCommunicationOperativeValidator commValidator = new DevCommunicationOperativeValidator(this.device);
		if (!commValidator.isInRightState()){
			throw new RTCDeviceCommunicationException(commValidator.getStatusDescription());
		}
		
		
		//Alarm
		DevAlarmOperativeValidator alarmValidator = new DevAlarmOperativeValidator(this.device);
		if (!alarmValidator.isInRightState()){
			throw new RTCDeviceInAlarmException(alarmValidator.getStatusDescription());
		}
		
		//Activity
		DevActivityStateValidator activityValidator = getActivityValidator();
		if (!activityValidator.isOperative()){
			throw new RTCDeviceActivityException(activityValidator.getStatusDescription());
		}
		
	}
	
	/**
	 * Builds the right Activity state validator depending on the DeviceType.
	 * @return Activity validator.
	 */
	private DevActivityStateValidator getActivityValidator(){
		
		DevActivityStateValidator result;
		
		switch(device.getType()){
			case CCD:
				result = new DevCameraActivityOperativeValidator(this.device);
				break;
			case DOME:
				result = new DevDomeActivityOperativeValidator(this.device);
				break;
			case FW:
				result = new DevFilterActivityOperativeValidator(this.device);
				break;
			case FOCUS:
				result = new DevFocuserActivityOperativeValidator(this.device);
				break;
			case MIRROR:
				result = new DevMirrorActivityOperativeValidator(this.device);
				break;
			default:
				result = new DevActivityOperativeValidator(this.device);
				
		}
		
		return result;
		
	}
	
	

}
