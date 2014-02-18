package eu.gloria.rtd;

import java.util.List;

import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.entity.device.DeviceProperty;
import eu.gloria.rt.exception.RTException;

/**
 * This is the interface that provides:
 * 
 * 
 * - Management of devices properties. A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.
 * 
 * @author jcabello
 *
 */
public interface RTDDeviceInterface  {
	
	/**
	 * Returns the device identification
	 * 
	 * @return Device identification
	 */
	public String getDeviceId()  throws RTException;

	
	/**
	 * Sets the device identification
	 * 
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId)  throws RTException;
		
		
	/**
	 * Returns the properties list associated to a device.
	 *
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @return Properties List.
	 * @throws RTException In error case.
	 */
	public List<DeviceProperty> devGetDeviceProperties() throws RTException;
	
	/**
	 * Return the specified device property 
	 * 
	 * @param name Property name	 
	 * @return DeviceProperty
	 * @throws RTException In error case.
	 */
	public DeviceProperty devGetDeviceProperty (String name) throws RTException;
	
	/**
	 * Return the Device information.
	 * @param allProperties false if it is not needed all properties (performance purpose).
	 * @return Device information.
	 * @throws RTException In error case.
	 */
	public Device devGetDevice(boolean allProperties)  throws RTException;
	
	/**
	 * Updates a property into a Device configuration. If the property is a readonly property or the value cannot be (internally) transformed into the proper type, an exception will be throw.
	 * 
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @param name Property name
	 * @param value Values of the property
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devUpdateDeviceProperty(String name, List<String> value) throws RTException;
	
	/**
	 * Updates a property into a Device configuration. If the property is a readonly property or the value cannot be (internally) transformed into the proper type, an exception will be throw.
	 * Asynchronous call.
	 * 
	 * (A property can be considered as a additional configuration parameter. At the moment, this way of configuration will not be used.) 
	 * 
	 * @param name Property name
	 * @param value Values of the property
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devUpdateDevicePropertyAsync(String name,List<String> value) throws RTException;
	
	/**
	 * Checks the link between the driver and the device. If True, the link is enable.
	 * 
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean devIsConnected() throws RTException;
	
	/**
	 * Creates the link between the driver and the device.
	 * @throws RTException In error case.
	 */
	public void devConnect() throws RTException;
	
	/**
	 * Controls the link between the driver and the device. Set True to enable the link. Set False to disable the link.
	 * 
	 * @throws RTException In error case.
	 */
	public void devDisconnect() throws RTException;
	
	/**
	 * Returns a String containing a full configuration information. For logging purpose.
	 * @return Configuration in a string
	 * @throws RTException In error case.
	 */
	public String devGetConfiguration() throws RTException;
	
	/**
	 * Returns true if the device is blocked because of its blocked state or some dependency to other device.
	 * @return Boolean.
	 * @throws RTException In error case.
	 */
	public boolean devIsBlocked() throws RTException;
	
	
	
}
