package eu.gloria.rtd;

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
public interface RTDWeatherVaneInterface extends RTDDeviceInterface {
	
	/**
	 * Returns the measure units of the sensor (DEGREES). 
	 * 
	 * @return MeasureUnit {@link MeasureUnit}
	 * @throws RTException In error case.
	 */
	public MeasureUnit wvnGetMeasureUnit() throws RTException;
	
	/**
	 * Returns the measure of the sensor.
	 * 
	 * @return measurement
	 * @throws RTException In error case.
	 */
	public double wvnGetMeasure() throws RTException;
	
	
	/**
	 * Is the absolute orientation (Device configuration).
	 * @return degrees.
	 * @throws RTException In error case.
	 */
	public double wvnGetAbosluteDegrees() throws RTException;
	
	/**
	 * Sets the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @param states State (measure interval and alarm activation).
	 * @throws RTException In error case.
	 */
	public void wvnSetMeasureStates(List<SensorStateIntervalDouble> states) throws RTException;
	
	/**
	 * Returns the list of the measure interval states. If an intermediate interval is not specified, it is
	 * considered as a hysteresis one.
	 * 
	 * @return List of states information.
	 * @throws RTException In error case.
	 */
	public List<SensorStateIntervalDouble> wvnGetMeasureStates() throws RTException;

}
