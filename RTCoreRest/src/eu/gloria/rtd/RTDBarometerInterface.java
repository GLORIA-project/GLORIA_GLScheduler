package eu.gloria.rtd;

import java.util.List;

import eu.gloria.rt.entity.device.MeasureUnit;
import eu.gloria.rt.entity.device.SensorStateIntervalDouble;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Barometer device.
 * 
 * @author jcabello
 *
 */
public interface RTDBarometerInterface extends RTDDeviceInterface  {
	
	/**
	 * Returns the measure units of the sensor (Pascal). 
	 * 
	 * @return MeasureUnit {@link MeasureUnit}
	 * @throws RTException In error case.
	 */
	public MeasureUnit barGetMeasureUnit() throws RTException;
	
	/**
	 * Returns the measure of the sensor. 
	 * 
	 * @return Measure
	 * @throws RTException In error case.
	 */
	public double barGetMeasure() throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation)
	 * @throws RTException In error case.
	 */
	public void barSetMeasureStates(List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> barGetMeasureStates() throws RTException;

}
