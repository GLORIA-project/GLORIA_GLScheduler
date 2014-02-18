package eu.gloria.rtd;

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
public interface RTDStormSensorInterface extends RTDDeviceInterface {
	
	/**
	 * Returns true if the the device can provide the storm orientation.
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean strIsAvailableOrientation() throws RTException;
	
	/**
	 * Returns the measure unit of the sensor (distance: MILES, KM).
	 * 
	 * @return MeasureUnit {@link MeasureUnit}
	 * @throws RTException In error case.
	 */
	public MeasureUnit strGetDistanceMeasureUnit() throws RTException;
	
	/**
	 * Returns the measure  of the sensor distance:(MILES, KM).
	 * 
	 * @return Measure
	 * @throws RTException In error case.
	 */
	public long strGetDistanceMeasure() throws RTException;
	
	/**
	 * Returns the relative storm orientation.
	 * 
	 * @return degrees
	 * @throws RTException In error case.
	 */
	public double strGetDegrees() throws RTException;
	
	/**
	 * Returns the absolute storm orientation (Configuration).
	 * 
	 * @return degrees
	 * @throws RTException In error case.
	 */
	public double strGetAbosoluteDegrees() throws RTException;
	
	
	/**
	 * Sets the distance measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void strSetDistanceMeasureStates(List<SensorStateIntervalLong> states) throws RTException;
	
	/**
	 * Returns the list of the distance measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalLong> strGetDistanceMeasureStates() throws RTException;
	
	/**
	 * Sets the orientation measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void strSetOrientationMeasureStates(List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the orientation measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> strGetOrientationMeasureStates() throws RTException;

}
