package eu.gloria.rtd;

import eu.gloria.rt.exception.RTException;


/**
 * 
 *  This is the interface that controls a Focuser device.
 * 
 * @author jcabello
 *
 */
public interface RTDFocuserInterface extends RTDDeviceInterface {
	

	/**
	 * Retrieves the camera identifier where the focuser is connected.
	 * 
	 * @return Camera identifier
	 * @throws RTException In error case.
	 */
	public String focGetCamera() throws RTException;
	
	/**
	 * True if the focuser is capable of absolute position.
	 * 
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean focIsAbsolute() throws RTException;
	
	/**
	 * Step size (microns) for the focuser.
	 * 
	 * @return size
	 * @throws RTException In error case.
	 */
	public double focGetStepSize() throws RTException;
	
	/**
	 * Maximum increment size in one move operation allowed by the focuser.
	 * 
	 * @return Increment
	 * @throws RTException In error case.
	 */
	public long focGetMaxIncrement() throws RTException;
	
	/**
	 * Maximum step position permitted.
	 * 
	 * @return Step
	 * @throws RTException In error case.
	 */
	public long focGetMaxStep() throws RTException;
	
	/**
	 * Current focuser position, in steps.
	 * 
	 * @return position
	 * @throws RTException In error case.
	 */
	public long focGetPosition() throws RTException;
	
	/**
	 * Its value is True if focuser has temperature compensation available.
	 * 
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean focIsTempCompAvailable() throws RTException;
	
	/**
	 * Current ambient temperature as measured by the focuser. Raises an exception if ambient 
	 * temperature is not available. Commonly available on focusers with a built-in temperature 
	 * compensation mode.
	 * 
	 * @return Temperature
	 * @throws RTException In error case.
	 */
	public double focGetTemperature() throws RTException;
	
	/**
	 * If the TempCompAvailable property is True, then setting TempComp to True puts the focuser 
	 * into temperature tracking mode. Set to False to turn off temperature tracking.
	 * While focuser is in temperature tracking mode, Move commands will be rejected. 
	 * An exception will be raised if focIsTempCompAvailable is False and an attempt is made to set 
	 * TempComp to true
	 * 
	 * @param trackingMode tracking mode.
	 * @throws RTException In error case.
	 */
	public void focSetTempComp(boolean trackingMode) throws RTException;
		
	

	/**
	 * It stops any previous focuser motion.
	 * 
	 * @throws RTException In error case.
	 */
	public void focHalt() throws RTException;
	
	/**
	 * Moves the focuser by the specified amount or to the specified position depending on 
	 * the value of the Absolute property. If the Absolute property is True, then this is 
	 * an absolute positioning focuser. The Move command tells the focuser to move to an exact 
	 * step position, and the Position parameter of the Move method is an integer between 0 and 
	 * MaxStep (0 >= position <= MaxStep). If the Absolute property is False, then this is a 
	 * relative positioning focuser. The Move command tells the focuser to move in a relative 
	 * direction, and the Position parameter of the Move method (in this case, step distance) 
	 * is an integer between minus MaxIncrement and plus MaxIncrement 
	 * (-MaxIncrement  >= position <= MaxIncrement)
	 *
 	 * @param position The target position of the movement.
	 * @throws RTException In error case.
	 */
	public void focMove(long position) throws RTException;
	

}
