package eu.gloria.rtc.device.validation;

import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.exception.RTException;

/**
 * Validates if a device is ready to receive a request (Operative) taking into account the activity state.
 * @author jcabello
 *
 */
public abstract class DevActivityStateValidator {

	/**
	 * Device to check.
	 */
	protected Device device;
	
	/**
	 * Number of activity states number.
	 */
	protected int activityStatesNumber;
	
	/**
	 * Constructor.
	 * @param device Device to check.
	 */
	public DevActivityStateValidator(Device device){
		this.device = device;
		this.activityStatesNumber = 1; //By default
	}
	
	/**
	 * Number of existing activity states into the device.
	 * @return Integer.
	 */
	public int getActivitiyStatesNumber(){
		return this.activityStatesNumber;
	}
	
	/**
	 * Returns true if all the activities states (all at the same time) allow the device to receive a new request.
	 * @return Boolean.
	 */
	public abstract boolean isOperative();
	
	/**
	 * Returns true if the activity state allows the device to receive a new request.
	 * @param activityStateNumber ActivityStateNumber.
	 * @return Boolean.
	 * @throws RTException In error case.
	 */
	public abstract boolean isOperative(int activityStateNumber) throws RTException;
	
	/**
	 * Returns the status description.
	 * @return String.
	 */
	public abstract String getStatusDescription();
	
	/**
	 * Returns the status description.
	 * @param activityStateNumber ActivityStateNumber.
	 * @return String .
	 * @throws RTException In error case
	 */
	public abstract String getStatusDescription(int activityStateNumber) throws RTException;
	
}
