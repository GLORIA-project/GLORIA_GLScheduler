package eu.gloria.rtc.device.validation;

import java.util.List;

import eu.gloria.rt.entity.device.ActivityStateDome;
import eu.gloria.rt.entity.device.ActivityStateDomeOpening;
import eu.gloria.rt.entity.device.ActivityStateMount;
import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.entity.device.DeviceDome;
import eu.gloria.rt.entity.device.DeviceMount;
import eu.gloria.rt.exception.RTException;

/**
 * Validates if a Dome device is ready to receive a request (Operative) taking into account the activity state.
 * @author jcabello
 *
 */
public class DevDomeActivityOperativeValidator extends DevActivityStateValidator {
	
	private static final int ROTOR_ACTIVITY_NUMBER = 1;
	private static final int OPENING_ACTIVITY_NUMBER = 2;
	
	private static final int ACTIVITY_STATES_NUMBER = 2;

	/**
	 * List of right rotor activity states.
	 */
	protected List<ActivityStateDome> rightRotorStates;
	
	/**
	 * List of right opening activity states.
	 */
	protected List<ActivityStateDomeOpening> rightOpeningStates;
	
	/**
	 * Constructor.
	 * @param device Device to check.
	 */
	public DevDomeActivityOperativeValidator(Device device){
		
		super(device);
		
		
		this.activityStatesNumber = ACTIVITY_STATES_NUMBER;
		
		rightRotorStates.add(ActivityStateDome.READY);
		rightRotorStates.add(ActivityStateDome.STOP);
		
		rightOpeningStates.add(ActivityStateDomeOpening.READY);
		rightOpeningStates.add(ActivityStateDomeOpening.OPEN);
		rightOpeningStates.add(ActivityStateDomeOpening.STOP);
		rightOpeningStates.add(ActivityStateDomeOpening.CLOSE);
	}
	
	/**
	 * Access method
	 * @return Device
	 */
	private DeviceDome getDevice(){
		return (DeviceDome) this.device;
	}

	/**
	 * Returns true if all the activities states (all at the same time) allow the device to receive a new request.
	 * @return Boolean.
	 */
	@Override
	public boolean isOperative() {
		
		boolean result = true;
		result = result && rightRotorStates.contains(getDevice().getActivityState());
		result = result && rightOpeningStates.contains(getDevice().getActivityStateOpening());
		
		return result;
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
		
		switch(activityStateNumber){
			case ROTOR_ACTIVITY_NUMBER:
				return rightRotorStates.contains(getDevice().getActivityState());
			default:
				return rightOpeningStates.contains(getDevice().getActivityStateOpening());
		}
		
	}
	
	/**
	 * Returns the status description.
	 * @return String
	 */
	@Override
	public String getStatusDescription(){
		return "The device is in [activity rotor state: " + getDevice().getActivityState() + ", activity opening state: " + getDevice().getActivityStateOpening() + "]";
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
		
		switch(activityStateNumber){
			case ROTOR_ACTIVITY_NUMBER:
				return "The device is in the activity rotor state: " + getDevice().getActivityState();
			default:
				return "The device is in the activity opening state: " + getDevice().getActivityStateOpening();
			
		}
		
	}
	
}
