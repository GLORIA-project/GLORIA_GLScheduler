package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;


/**
 * This interface defines the methods that control a Weather Vane device.
 * 
 * @author jcabello
 *
 */
public interface WeatherVaneControlInterface extends DeviceManagerInterface {
	
	/**
	 * Returns the measure units of the sensor (DEGREES). 
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public MeasureUnit wvnGetMeasureUnit(String deviceId) throws RTException;
	
	/**
	 * Returns the measure of the sensor.
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public double wvnGetMeasure(String deviceId) throws RTException;
	
	
	/**
	 * Is the absolute orientation (Device configuration).
	 * @param device Device Identifier.
	 * @return degrees.
	 * @throws RTException In error case.
	 */
	public double wvnGetAbosluteDegrees(String device) throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param device Device Identifier.
	 * @param states State (measure interval and alarm activation).
	 * @throws RTException In error case.
	 */
	public void wvnSetMeasureStates(String device, List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param device Device Identifier.
	 * @return List of states information.
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> wvnGetMeasureStates(String device) throws RTException;

}
