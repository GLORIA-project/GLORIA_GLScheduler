package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.entity.device.SensorStateIntervalLong;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Storm Sensor device.
 * 
 * @author jcabello
 *
 */
public interface StormSensorControlInterface extends DeviceManagerInterface {
	
	/**
	 * Returns true if the the device can provide the storm orientation.
	 * 
	 * @param deviceId Device Identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean strIsAvailableOrientation(String deviceId) throws RTException;
	
	/**
	 * Returns the measure unit of the sensor (distance: MILES, KM).
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public MeasureUnit strGetDistanceMeasureUnit(String deviceId) throws RTException;
	
	/**
	 * Returns the measure  of the sensor distance:(MILES, KM).
	 * 
	 * @param deviceId Device identifier.
	 * @return Measure
	 * @throws RTException In error case.
	 */
	public long strGetDistanceMeasure(String deviceId) throws RTException;
	
	/**
	 * Returns the relative storm orientation.
	 * 
	 * @param deviceId Device identifier.
	 * @return degrees
	 * @throws RTException In error case.
	 */
	public double strGetDegrees(String deviceId) throws RTException;
	
	/**
	 * Returns the absolute storm orientation (Configuration).
	 * 
	 * @param deviceId Device identifier.
	 * @return degrees
	 * @throws RTException In error case.
	 */
	public double strGetAbosoluteDegrees(String deviceId) throws RTException;
	
	
	/**
	 * Sets the distance measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void strSetDistanceMeasureStates(String deviceId, List<SensorStateIntervalLong> states) throws RTException;
	
	/**
	 * Returns the list of the distance measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalLong> strGetDistanceMeasureStates(String deviceId) throws RTException;
	
	/**
	 * Sets the orientation measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void strSetOrientationMeasureStates(String deviceId, List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the orientation measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> strGetOrientationMeasureStates(String deviceId) throws RTException;

}
