package eu.gloria.rtc;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Photometer device.
 * 
 * @author jcabello
 *
 */
public interface PhotometerControlInterface  extends DeviceManagerInterface {

	
	/**
	 * Return the measure unit of the sensor. A/V if a photodiode is used; Ohms is a photoresistor is used...
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public MeasureUnit fhtGetMeasureUnit(String deviceId) throws RTException;
	
	/**
	 * Returns the measure of the sensor. 
	 * 
	 * @param deviceId Device identifier.
	 * @return MeasureUnit
	 * @throws RTException In error case.
	 */
	public double fhtGetMeasure(String deviceId) throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void fhtSetMeasureStates(String deviceId, SensorStateIntervalDouble[] states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param deviceId Device identifier.
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public SensorStateIntervalDouble[] fhtGetMeasureStates(String deviceId) throws RTException;
	
}
