package eu.gloria.rtc;

import eu.gloria.rt.exception.RTException;


/**
 * This is the interface that controls a rotator device.
 * 
 * @author mclopez
 *
 */
public interface RotatorInterface extends DeviceManagerInterface {

	/**
	 * Returns the rotator current position
	 * 
	 * @return Current position
	 * @throws RTException In error case.
	 */
	public double rttGetCurrentPosition (String deviceId) throws RTException;
	
	
	/**
	 * Sets the rotator target position
	 * 
	 * @param position
	 * @throws RTException In error case.
	 */
	public void rttSetTargetPosition (String deviceId, double position) throws RTException;
	
}
