package eu.gloria.rtc.device.validation;

import java.util.ArrayList;
import java.util.List;

import eu.gloria.rt.entity.device.CommunicationState;
import eu.gloria.rt.entity.device.Device;

/**
 * Validates if a device communication state is included in a list of states.
 * @author jcabello
 *
 */
public class DevCommunicationStateValidator {

	/**
	 * List of communication states
	 */
	protected List<CommunicationState> rightStates;
	
	/**
	 * Device to check.
	 */
	protected Device device;
	
	/**
	 * Constructor
	 * @param device Device to check.
	 */
	public DevCommunicationStateValidator(Device device){
		rightStates = new ArrayList<CommunicationState>();
		this.device = device;
	}
	
	/**
	 * Return true if the device is included in a state list.
	 * @return boolean
	 */
	public boolean isInRightState(){
		return rightStates.contains(device.getCommunicationState());
	}
	
	/**
	 * Returns the status description.
	 * @return String
	 */
	public String getStatusDescription(){
		return "The device is in the communication state: " + device.getCommunicationState();
	}
	
}
