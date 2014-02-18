package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.exception.RTException;


/**
 * This interface defines the methods that control a Wind Speed device.
 * 
 * @author jcabello
 *
 */
public interface WindSpeedControlInterface extends DeviceManagerInterface {

	/**
	 * Returns the measure units of the sensor. Km/h if a anemometer is used. 
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public MeasureUnit wspGetMeasureUnit(String deviceId) throws RTException;
	
	/**
	 * Returns the measure of the sensor. Km/h if a anemometer is used. 
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public double wspGetMeasure(String deviceId) throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void wspSetMeasureStates(String deviceId, List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> wspGetMeasureStates(String deviceId) throws RTException;
	
}
