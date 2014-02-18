package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;
import eu.gloria.rtd.RTDDeviceInterface;

/**
 * This interface defines the methods that control a Cloud Detector device.
 * 
 * @author mclopez
 *
 */
public interface CloudDetectorInterface extends DeviceManagerInterface {



	/**
	 * Returns the measure units of the sensor. 
	 * 
	 * @return MeasureUnit {@link MeasureUnit}
	 * @throws RTException In error case.
	 */
	public MeasureUnit cldGetMeasureUnit(String deviceId) throws RTException;

	/**
	 * Returns the measure of the sensor.  
	 * 
	 * @return measurement 
	 * @throws RTException In error case.
	 */
	public double cldGetMeasure(String deviceId) throws RTException;


	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation) {@link SensorStateIntervalDouble}
	 * @throws RTException In error case.
	 */
	public void cldSetMeasureStates(String deviceId, List<SensorStateIntervalDouble> states) throws RTException;

	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information {@link SensorStateIntervalDouble}
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> cldGetMeasureStates(String deviceId) throws RTException;

	


}
