package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.exception.RTException;
import eu.gloria.rtd.RTDDeviceInterface;

/**
 * This interface must be implemented by each RTC environment because each RTDDevice must have its own implementation and device discovery.
 * @author jcabello
 *
 */
public interface DeviceDiscovererInterface {
	
	
	/**
	 * returns all device identifies.
	 * @return Id list.
	 * @throws RTException In error case.
	 */
	public List<String> getDeviceIds() throws RTException;
	
	
	/**
	 * Returns the full devices list.
	 * 
	 * @param allProperties true->retrieves all properties.
	 * @return Current devices list.
	 * @throws RTException In error case.
	 */
	public List<Device> getDevices(boolean allProperties) throws RTException;

	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier.
	 * @param allProperties true->with all properties, false->no property
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public Device getDevice(String deviceId, boolean allProperties) throws RTException;
	
	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier.
	 * @param propertyNames List of properties to recover.
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public Device getDevice(String deviceId, List<String> propertyNames) throws RTException;
	
	
	/**
	 * Recovers the proper RTDDevice Object based on the device id.
	 * @param deviceId Device identifier
	 * @return RTDDevice RTD Device.
	 * @throws RTException In error case.
	 */
	public RTDDeviceInterface getRTD (String deviceId) throws RTException;

}
