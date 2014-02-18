package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.exception.RTException;


/**
 * 
 * This is the interface that controls a FilterWheel device.
 * 
 * @author jcabello
 *
 */
public interface FilterWheelInterface extends DeviceManagerInterface {

	/**
	 * Retrieves the camera identifier where the filter wheel is connected.
	 * @param deviceId Device Identifier.
	 * @return Camera identifier.
	 * @throws RTException In error case.
	 */
	public String fwGetCamera (String deviceId) throws RTException;
	
	
	/**
	 * Retrieves the list of filter in the wheel. The list position corresponds with the wheel position.
	 * @param deviceId Device Identifier.
	 * @return filter list.
	 * @throws RTException In error case.
	 */
	public List<String> fwGetFilterList (String deviceId) throws RTException;
	
	/**
	 * Number of filter position in wheel.
	 * @param deviceId Device Identifier.
	 * @return Number of the possible positions.
	 * @throws RTException In error case.
	 */
	public int fwGetPositionNumber(String deviceId) throws RTException;
	
	/**
	 * Speed filter switching (ms).
	 * @param deviceId Device Identifier.
	 * @return Speed switching (ms)
	 * @throws RTException In error case.
	 */
	public int fwGetSpeedSwitching(String deviceId) throws RTException;
	
	
	/**
	 * Retrieves the filter inches (1.25’’, 2’’...)
	 * @param deviceId Device Identifier.
	 * @return inches
	 * @throws RTException In error case.
	 */
	public float fwGetFilterSize(String deviceId) throws RTException;
	
	/**
	 * Retrieves the kind of filter selected
	 * @param deviceId Device Identifier.
	 * @return Kind (G, R, Z, clear...)
	 * @throws RTException In error case.
	 */
	public String fwGetFilterKind(String deviceId) throws RTException;
	
	/**
	 * Returns true if the wheel is stopped in the home position.
	 * @param deviceId Device Identifier.
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean fwIsAtHome(String deviceId) throws RTException;
	
	/**
	 * Focus offset in each wheel position. These 	values are focuser and filter dependent.
	 * @param deviceId Device Identifier.
	 * @param positions A list of wheel position, one per wheel belonging to the device.
	 * @throws RTException In error case.
	 */
	public void fwSetOffset(String deviceId, List<Integer> positions) throws RTException;
	
	/**
	 *  Select the filter, by filter kind, to switch to.
	 * @param deviceId Device Identifier.
	 * @param kind Wheel Kind.
	 * @throws RTException In error case.
	 */
	public void fwSelectFilterKind(String deviceId, String kind) throws RTException;
	
	/**
	 * Select filter, by wheel position, to switch to.
	 * @param deviceId Device Identifier.
	 * @param position Position within the wheel (1..N)
	 * @throws RTException In error case.
	 */
	public void fwSelectFilterPosition(String deviceId, int position) throws RTException;
	
	/**
	 * Switch the wheel to the home position.
	 * @param deviceId Device Identifier.
	 * @throws RTException In error case.
	 */
	public void fwGoHome(String deviceId) throws RTException;
	
	
	
}
