package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.entity.device.DeviceProperty;
import eu.gloria.rt.exception.RTException;

/**
 * This is the interface that provides:
 * 
 * - The inventory of devices.
 * - Management of devices properties. A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.
 * 
 * @author jcabello
 *
 */
public interface DeviceManagerInterface  {
	
	
	/**
	 * Returns the full devices list.
	 * 
	 * @param allProperties true->with all properties, false->no property
	 * @return Current devices list.
	 * @throws RTException In error case.
	 */
	public List<Device> devGetDevices( boolean allProperties) throws RTException;
	
	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier.
	 * @param allProperties true->with all properties, false->no property
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public Device devGetDevice(String deviceId, boolean allProperties) throws RTException;
	
	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier. 
	 * @param propertyNames List of properties to recover.
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public Device devGetDevice(String deviceId, List<String> propertyNames) throws RTException;

		
	/**
	 * Returns the properties list associated to a device.
	 *
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @param deviceId Device Identifier.
	 * @return Properties List.
	 * @throws RTException In error case.
	 */
	public List<DeviceProperty> devGetDeviceProperties(String deviceId) throws RTException;
	
	/**
	 * Return the specified device property 
	 * 
	 * @param deviceId Device Identifier.
	 * @param name Property name	 
	 * @return DeviceProperty
	 * @throws RTException In error case.
	 */
	public DeviceProperty devGetDeviceProperty (String deviceId, String name) throws RTException;
	
	/**
	 * Updates a property into a Device configuration. If the property is a readonly property or the value cannot be (internally) transformed into the proper type, an exception will be throw.
	 * 
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @param deviceId Device Identifier
	 * @param name Property name
	 * @param value Values of the property
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devUpdateDeviceProperty(String deviceId, String name, List<String> value) throws RTException;
	
	/**
	 * Updates a property into a Device configuration. If the property is a readonly property or the value cannot be (internally) transformed into the proper type, an exception will be throw.
	 * Asynchronous call.
	 * 
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @param deviceId Device Identifier
	 * @param name Property name
	 * @param value Values of the property
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devUpdateDevicePropertyAsync(String deviceId, String name,List<String> value) throws RTException;
	
	/**
	 * Checks the link between the driver and the device. If True, the link is enable.
	 * 
	 * @param deviceId Device Identifier
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devIsConnected(String deviceId) throws RTException;
	
	/**
	 * Creates the link between the driver and the device.
	 * @param deviceId Device Identifier.
	 * @throws RTException In error case.
	 */
	public void devConnect(String deviceId) throws RTException;
	
	/**
	 * Controls the link between the driver and the device. Set True to enable the link. Set False to disable the link.
	 * 
	 * @param deviceId Device Identifier
	 * @throws RTException In error case.
	 */
	public void devDisconnect(String deviceId) throws RTException;
	
	/**
	 * Returns a String containing a full configuration information. For logging purpose.
	 * @param deviceId Device Id
	 * @return Configuration in a string
	 * @throws RTException In error case.
	 */
	public String devGetConfiguration(String deviceId) throws RTException;
	
	/**
	 * Returns true if the device is blocked because of its blocked state or some dependency to other device.
	 * @param deviceId Device Id
	 * @return Boolean.
	 * @throws RTException In error case.
	 */
	public boolean devIsBlocked(String deviceId) throws RTException;
	
	
	/**
	 * Returns true if the executor is running. It means the RTDs are accessible.
	 * @return boolean 
	 * @throws RTException In erro case.
	 */
	public boolean execIsRunningOp() throws RTException;
	
	
	
}
