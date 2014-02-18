package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;


/**
 * This interface defines the methods that control a Rain detector device.
 * 
 * @author jcabello
 *
 */
public interface RainDetectorControlInterface extends DeviceManagerInterface {

	/**
	 * Returns the measure units of the sensor. Cycles if a capacitor is used; Inches is a hygroscopic disc is used.
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public MeasureUnit rndGetMeasureUnit(String deviceId) throws RTException;
	
	/**
	 * Returns the measure of the sensor. Cycles if a capacitor is used; Inches is a hygroscopic disc is used.
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public double rndGetMeasure(String deviceId) throws RTException;
	
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void rndSetMeasureStates(String deviceId, List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> rndGetMeasureStates(String deviceId) throws RTException;
	
	/**
	 * Return whether it is raining or not.
	 * 
	 * @param deviceId Device identifier.
	 * @return True if it is raining
	 * @throws RTException In error case.
	 */
	public boolean rndIsRaining(String deviceId) throws RTException;
	
}
