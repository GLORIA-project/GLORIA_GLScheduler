package eu.gloria.rtd;

import java.util.List;

import eu.gloria.rt.exception.RTException;


/**
 * 
 * This is the interface that controls a FilterWheel device.
 * 
 * @author jcabello
 *
 */
public interface RTDFilterWheelInterface extends RTDDeviceInterface {

	
	/**
	 * Retrieves the camera identifier where the filter wheel is connected.
	 * @return Camera identifier.
	 * @throws RTException In error case.
	 */
	public String fwGetCamera () throws RTException;
	
	/**
	 * Retrieves the list of filter in the wheel. The list position corresponds with the wheel position.
	 * @return filter list.
	 * @throws RTException In error case.
	 */
	public List<String> fwGetFilterList () throws RTException;
	
	/**
	 * Number of filter position in wheel.
	 * @return Number of the possible positions.
	 * @throws RTException In error case.
	 */
	public int fwGetPositionNumber() throws RTException;
	
	/**
	 * Speed filter switching (ms).
	 * @return Speed switching (ms)
	 * @throws RTException In error case.
	 */
	public int fwGetSpeedSwitching() throws RTException;
	
	
	/**
	 * Retrieves the filter inches (1.25’’, 2’’...)
	 * @return inches
	 * @throws RTException In error case.
	 */
	public float fwGetFilterSize() throws RTException;
	
	/**
	 * Retrieves the kind of filter selected
	 * @return Kind (G, R, Z, clear...)
	 * @throws RTException In error case.
	 */
	public String fwGetFilterKind() throws RTException;
	
	/**
	 * Returns true if the wheel is stopped in the home position.
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean fwIsAtHome() throws RTException;
	
	/**
	 * Focus offset in each wheel position. These values are focuser and filter dependent.
	 * @param positions A list of wheel position, one per wheel belonging to the device.
	 * @throws RTException In error case.
	 */
	public void fwSetOffset(List<Integer> positions) throws RTException;
	
	/**
	 *  Select the filter, by filter kind, to switch to.
	 * @param kind Wheel Kind.
	 * @throws RTException In error case.
	 */
	public void fwSelectFilterKind(String kind) throws RTException;
	
	/**
	 * Select filter, by wheel position, to switch to.
	 * @param position Position within the wheel (1..N)
	 * @throws RTException In error case.
	 */
	public void fwSelectFilterPosition(int position) throws RTException;
	
	/**
	 * Switch the wheel to the home position.
	 * @throws RTException In error case.
	 */
	public void fwGoHome() throws RTException;
	
	
	
}
