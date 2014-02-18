package eu.gloria.rtc.device.validation;

import java.util.List;

import eu.gloria.rt.entity.device.ActivityStateMount;
import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.entity.device.DeviceMount;
import eu.gloria.rt.exception.RTException;

/**
 * Validates if a Mount device is ready to receive a request (Operative) taking into account the activity state.
 * @author jcabello
 *
 */
public class DevMountActivityOperativeValidator  extends DevActivityStateValidator {
	
	/**
	 * List of right activity states
	 */
	protected List<ActivityStateMount> rightStates;
	
	/**
	 * Constructor.
	 * @param device Device to check.
	 */
	public DevMountActivityOperativeValidator(Device device){
		
		super(device);
		
		rightStates.add(ActivityStateMount.READY);
		rightStates.add(ActivityStateMount.PARKED);
		rightStates.add(ActivityStateMount.STOP);
	}
	
	/**
	 * Access method
	 * @return Device
	 */
	private DeviceMount getDevice(){
		return (DeviceMount) this.device;
	}

	/**
	 * Returns true if all the activities states (all at the same time) allow the device to receive a new request.
	 * @return Boolean.
	 */
	@Override
	public boolean isOperative() {
		
		return rightStates.contains( getDevice().getActivityState());
	}
	
	/**
	 * Returns true if the activity state allows the device to receive a new request.
	 * @param activityStateNumber ActivityStateNumber.
	 * @return Boolean.
	 * @throws RTException In error case.
	 */
	@Override
	public  boolean isOperative(int activityStateNumber) throws RTException{
		
		if (activityStateNumber < 1 || activityStateNumber > this.getActivitiyStatesNumber()){
			throw new RTException("Invalid activityStateNumber");
		}
		
		return rightStates.contains( getDevice().getActivityState());
	}
	
	/**
	 * Returns the status description.
	 * @return String
	 */
	@Override
	public String getStatusDescription(){
		return "The device is in the activity state: " + getDevice().getActivityState();
	}
	
	/**
	 * Returns the status description.
	 * @param activityStateNumber ActivityStateNumber.
	 * @return String.
	 * @throws RTException In error case.
	 */
	@Override
	public String getStatusDescription(int activityStateNumber) throws RTException{
		
		if (activityStateNumber < 1 || activityStateNumber > this.getActivitiyStatesNumber()){
			throw new RTException("Invalid activityStateNumber");
		}
		
		return "The device is in the activity state: " + getDevice().getActivityState();
		
	}

}
