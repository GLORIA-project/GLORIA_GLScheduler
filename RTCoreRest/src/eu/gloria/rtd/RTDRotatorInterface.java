package eu.gloria.rtd;

import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Rotator.
 * 
 * @author mclopez
 *
 */
public interface RTDRotatorInterface extends RTDDeviceInterface {
	
	/**
	 * Returns the rotator current position
	 * 
	 * @return Current position
	 * @throws RTException In error case.
	 */
	public double rttGetCurrentPosition () throws RTException;
	
	
	/**
	 * Sets the rotator target position
	 * 
	 * @param position
	 * @throws RTException In error case.
	 */
	public void rttSetTargetPosition (double position) throws RTException;

}
