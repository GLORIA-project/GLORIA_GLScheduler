package eu.gloria.rtc;

import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Dome.
 * 
 * @author jcabello
 *
 */
public interface DomeControlInterface extends DeviceManagerInterface {
	
	
	/**
	 * Returns  the number (N) of element of the dome.
	 * 
	 * @param deviceId Device identifier
	 * @return Number of elements
	 * @throws RTException In error case.
	 */
	public int domGetNumberElement(String deviceId) throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome altitude.
	 * 
	 * @param deviceId Device identifier.
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domCanSetAltitude(String deviceId) throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome azimuth.
	 * 
	 * @param deviceId Device identifier
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean domCanSetAzimuth(String deviceId) throws RTException;
	
	/**
	 * Returns true if it is capable of setting dome park position.
	 * 
	 * @param deviceId Device identifier
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domCanSetPark(String deviceId) throws RTException;
	
	/**
	 * Returns true if the dome is in its home position.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domIsAtHome(String deviceId) throws RTException;
	
	/**
	 * Returns true if the dome is in its park position.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean domIsAtPark(String deviceId) throws RTException;
	
	/**
	 * Returns the altitude position of the dome.
	 * @param deviceId Device identifier.
	 * @return Altitude.
	 * @throws RTException In error case.
	 */
	public double domGetAltitude(String deviceId) throws RTException;
	
	
	/**
	 * Returns the azimuth position of the dome.
	 * @param deviceId Device identifier.
	 * @return Azimuth.
	 * @throws RTException In error case.
	 */
	public double domGetAzimuth(String deviceId) throws RTException;
	
	
	/**
	 * This method will open the n_element of the dome. For this, the state must be CLOSE.
	 * 
	 * @param deviceId Device identifier.
	 * @param element The element (order) to open.
	 * @throws RTException In error case.
	 */
	public void domOpen(String deviceId, int element) throws RTException;
	
	/**
	 * This method will close the n_element of the dome. For this, the state must be OPEN.
	 * 
	 * @param deviceId Device identifier.
	 * @param element The element (order) to close.
	 * @throws RTException In error case.
	 */
	public void domClose(String deviceId, int element) throws RTException;
	
	/**
	 * This method will move the dome to the home position. If an error happens (i.e. electrical, motor...), this method will be used to move the dome in a known position.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void domGoHome(String deviceId) throws RTException;
	
	/**
	 * This method will establish the dome park position. .
	 * 
	 * @param deviceId Device identifier.
	 * @param altitude Altitude value.
	 * @param azimuth  Azimuth value.
	 * @throws RTException In error case.
	 */
	public void domSetPark(String deviceId, double altitude, double azimuth) throws RTException;
	
	/**
	 * Moves dome to park position.
	 * 
	 * @param deviceId Device identifier
	 * @throws RTException In error case.
	 */
	public void domPark(String deviceId) throws RTException;
	
	/**
	 * This method will move the dome to the indicated azimuth.
	 * 
	 * @param deviceId Device identifier
	 * @param azimuth Azimuth value.
	 * @throws RTException In error case.
	 */
	public void domMoveAzimuth(String deviceId, double azimuth) throws RTException;
	
	/**
	 * This method will move the dome to the indicated altitude.
	 * 
	 * @param deviceId Device identifier
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void domMoveAltitude(String deviceId, double altitude) throws RTException;
	
	
	/**
	 * Access method to the state (on/off) of the mount tracking drive.
	 * 
	 * @param deviceId Device identifier
	 * @param value True to activate the tracking
	 * @throws RTException In error case.
	 */
	public void domSetTracking (String deviceId, boolean value) throws RTException;
	
	/**
	 * Access method to the state (on/off) of the mount tracking drive.
	 * 
	 * @param deviceId Device identifier
	 * @return value If true the tracking is active
	 * @throws RTException In error case.
	 */
	public boolean domGetTracking (String deviceId) throws RTException;

	
	/**
	 * Slew to object 
	 * 
	 * @param deviceId Device identifier
	 * @param object Object to slew to
	 * @throws RTException In error case.
	 */
	public void domSlewObject (String deviceId, String object) throws RTException;
}
