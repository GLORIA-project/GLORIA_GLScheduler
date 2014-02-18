package eu.gloria.rtd;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Photometer device.
 * 
 * @author jcabello
 *
 */
public interface RTDPhotometerInterface  extends RTDDeviceInterface {

	
	/**
	 * Return the measure unit of the sensor. A/V if a photodiode is used; Ohms is a photoresistor is used...
	 * 
	 * @return MeasureUnit {@link MeasureUnit}
	 * @throws RTException In error case.
	 */
	public MeasureUnit fhtGetMeasureUnit() throws RTException;
	
	/**
	 * Returns the measure of the sensor. 
	 * 
	 * @return Measure
	 * @throws RTException In error case.
	 */
	public double fhtGetMeasure() throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void fhtSetMeasureStates(SensorStateIntervalDouble[] states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public SensorStateIntervalDouble[] fhtGetMeasureStates() throws RTException;
	
}
