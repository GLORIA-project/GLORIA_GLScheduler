package eu.gloria.rtc.device.validation;


import eu.gloria.rt.entity.device.AlarmState;
import eu.gloria.rt.entity.device.Device;

/**
 * Checks if the device alarm state is valid (NONE) to receive commands.
 * 
 * @author jcabello
 *
 */
public class DevAlarmOperativeValidator extends DevAlarmStateValidator {
	
	/**
	 * Constructor.
	 * @param device Device to check.
	 */
	public DevAlarmOperativeValidator(Device device){
		
		super(device);
		
		rightStates.add(AlarmState.NONE);
		
	}

}
