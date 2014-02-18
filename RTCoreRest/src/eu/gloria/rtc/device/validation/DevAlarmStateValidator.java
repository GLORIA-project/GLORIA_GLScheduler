package eu.gloria.rtc.device.validation;

import java.util.ArrayList;
import java.util.List;

import eu.gloria.rt.entity.device.AlarmState;
import eu.gloria.rt.entity.device.Device;

/**
 * Validates if a device alarm state is included in a list of states.
 * @author jcabello
 *
 */
public abstract class DevAlarmStateValidator {
	
	/**
	 * List of alarm states
	 */
	protected List<AlarmState> rightStates;
	
	/**
	 * Device to check.
	 */
	protected Device device;
	
	/**
	 * Constructor
	 * @param device Device to check.
	 */
	public DevAlarmStateValidator(Device device){
		rightStates = new ArrayList<AlarmState>();
		this.device = device;
	}
	
	/**
	 * Return true if the device is included in a state list.
	 * @return boolean
	 */
	public boolean isInRightState(){
		return rightStates.contains(device.getAlarmState());
	}
	
	/**
	 * Returns the status description.
	 * @return String
	 */
	public String getStatusDescription(){
		return "The device is in the alarm state: " + device.getAlarmState();
	}
	

}
