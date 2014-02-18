package eu.gloria.rtd;

import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Dome.
 * 
 * @author jcabello
 *
 */
public interface RTDDomeInterface extends RTDDeviceInterface {
	
	
	/**
	 * Returns  the number (N) of element of the dome.
	 * 
	 * @return Number of elements
	 * @throws RTException In error case.
	 */
	public int domGetNumberElement() throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome altitude.
	 * 
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domCanSetAltitude() throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome azimuth.
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean domCanSetAzimuth() throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome park position.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domCanSetPark() throws RTException;
	
	/**
	 * Returns true if the dome is in its home position.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domIsAtHome() throws RTException;
	
	/**
	 * Returns true if the dome is in its park position.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domIsAtPark() throws RTException;
	
	/**
	 * Returns the altitude position of the dome.
	 * @return Altitude.
	 * @throws RTException In error case.
	 */
	public double domGetAltitude() throws RTException;
	
	
	/**
	 * Returns the azimuth position of the dome.
	 * @return Azimuth.
	 * @throws RTException In error case.
	 */
	public double domGetAzimuth() throws RTException;
	
	
	/**
	 * This method will open the n_element of the dome. For this, the state must be CLOSE.
	 * 
	 * @param element The element (order) to open.
	 * @throws RTException In error case.
	 */
	public void domOpen(int element) throws RTException;
	
	/**
	 * This method will close the n_element of the dome. For this, the state must be OPEN.
	 * 
	 * @param element The element (order) to close.
	 * @throws RTException In error case.
	 */
	public void domClose(int element) throws RTException;
	
	/**
	 * This method will move the dome to the home position. If an error happens (i.e. electrical, motor...), this method will be used to move the dome in a known position.
	 * 
	 * @throws RTException In error case.
	 */
	public void domGoHome() throws RTException;
	
	/**
	 * This method will establish the dome park position. .
	 * 
	 * @param altitude Altitude value.
	 * @param azimuth  Azimuth value.
	 * @throws RTException In error case.
	 */
	public void domSetPark(double altitude, double azimuth) throws RTException;
	
	/**
	 * Moves dome to park position.
	 * 
	 * @throws RTException In error case.
	 */
	public void domPark() throws RTException;
	
	/**
	 * This method will move the dome to the indicated azimuth.
	 * 
	 * @param azimuth Azimuth value.
	 * @throws RTException In error case.
	 */
	public void domMoveAzimuth(double azimuth) throws RTException;
	
	/**
	 * This method will move the dome to the indicated altitude.
	 * 
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void domMoveAltitude(double altitude) throws RTException;
	
	
	/**
	 * Access method to the state (on/off) of the mount tracking drive.
	 * 
	 * @param value True to activate the tracking
	 * @throws RTException In error case.
	 */
	public void domSetTracking (boolean value) throws RTException;
	
	/**
	 * Access method to the state (on/off) of the mount tracking drive.
	 * 
	 * @return value If true the tracking is active
	 * @throws RTException In error case.
	 */
	public boolean domGetTracking () throws RTException;
	
	/**
	 * Slew to object 
	 * @param object Object to slew to
	 * @throws RTException In error case.
	 */
	public void domSlewObject (String object) throws RTException;

}
