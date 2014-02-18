package eu.gloria.rtc;

import java.util.List;

import eu.gloria.rt.entity.device.Device;
import eu.gloria.rt.exception.RTException;
import eu.gloria.rtd.RTDDeviceInterface;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;

/**
 * 
 * @author jcabello
 *
 */
public class DeviceDiscoverer {
	
	/**
	 * Custom RTDFactory
	 */
	private static DeviceDiscovererInterface discoverer;
	
	/**
	 * Static constructor
	 */
	static{
		
		try {
			
			String factoryClassName = Config.getProperty("rt_config", "device.discoverer.provider");
			
			LogUtil.info(null, "Creating...DeviceDiscovery: " + factoryClassName);
			
			discoverer = (DeviceDiscovererInterface)Class.forName(factoryClassName).newInstance();
			
		} catch (InstantiationException e) {
			LogUtil.severe(null, e.getMessage());
		} catch (IllegalAccessException e) {
			LogUtil.severe(null, e.getMessage());
		} catch (ClassNotFoundException e) {
			LogUtil.severe(null, e.getMessage());
		}
		
	}
	
	
	/**
	 * Creates a RTDDevice instance based on the device identifier.
	 * @param deviceId device identifier.
	 * @return Device.
	 */
	public static RTDDeviceInterface getRTD(String deviceId) throws RTException{
		
		RTDDeviceInterface result = null;
		if (discoverer != null){
			result = discoverer.getRTD(deviceId);
		}
		
		return result;
	}
	
	/**
	 * Returns the full devices list.
	 * 
	 *  @param allProperties true->retrieves all properties.
	 * @return Current devices list.
	 * @throws RTException In error case.
	 */
	public static List<Device> devGetDevices(boolean allProperties) throws RTException{
		
		List<Device> result = null;
		if (discoverer != null){
			result = discoverer.getDevices(allProperties);
		}
		
		return result;
	}
	
	
	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier.
	 * @param allProperties true->with all properties, false->no property
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public static Device devGetDevice(String deviceId, boolean allProperties) throws RTException{
		
		Device result = null;
		if (discoverer != null){
			result = discoverer.getDevice(deviceId, allProperties);
		}
		
		return result;
	}
	
	/**
	 * Returns the device information
	 * 
	 * @param deviceId Device Identifier.
	 * @param propertyNames List of properties to recover.
	 * @return Current device data.
	 * @throws RTException In error case.
	 */
	public static Device devGetDevice(String deviceId, List<String> propertyNames) throws RTException{
		
		Device result = null;
		if (discoverer != null){
			result = discoverer.getDevice(deviceId, propertyNames);
		}
		
		return result;
		
	}
	
	/**
	 * Recover the devices Ids.
	 * @return Device identifiers list.
	 * @throws RTException In error case.
	 */
	public static List<String> devGetDeviceIds() throws RTException{
		
		List<String> result = null;
		if (discoverer != null){
			result = discoverer.getDeviceIds();
		}
		return result;
	}
	
	
	
}
