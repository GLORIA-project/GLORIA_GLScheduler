package eu.gloria.rtc.device.validation;

import eu.gloria.rt.entity.device.CommunicationState;
import eu.gloria.rt.entity.device.Device;

/**
 * Checks if the device communication state is valid (READY, IDLE) to receive commands.
 * 
 * @author jcabello
 *
 */
public class DevCommunicationOperativeValidator extends
		DevCommunicationStateValidator {
	
	/**
	 * Constructor.
	 * @param device Device to check.
	 */
	public DevCommunicationOperativeValidator(Device device){
		
		super(device);
		
		rightStates.add(CommunicationState.READY);
		rightStates.add(CommunicationState.IDLE);
		
	}

}
