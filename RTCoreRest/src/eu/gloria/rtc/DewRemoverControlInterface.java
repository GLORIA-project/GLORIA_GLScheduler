package eu.gloria.rtc;

import eu.gloria.rt.entity.device.DewRemoverChannelType;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Dew remover device.
 * 
 * @author jcabello
 *
 */
public interface DewRemoverControlInterface extends DeviceManagerInterface {

	/**
	 * Returns the channels number.
	 * 
	 * @param deviceId Device Identifier.
	 * @return Channel number.
	 * @throws RTException In error case.
	 */
	public int dwrGetChannelsNumber(String deviceId) throws RTException;
	
	/**
	 * Returns the type of a channel (duty cycle, temperature controlled).
	 * 
	 * @param deviceId Device Identifier.
	 * @param channel channel id (order).
	 * @return Type.
	 * @throws RTException In error case.
	 */
	public DewRemoverChannelType dwrGetChannelType(String deviceId, int channel) throws RTException;
	
	/**
	 * Set the temperature threshold for the selected channel. If this channel is not temperature controlled an exception will  be raised.
	 * 
	 * @param deviceId Device Identifier.
	 * @param channel Channel id (order).
	 * @param temperature threshold.
	 * @throws RTException In error case.
	 */
	public void dwrSetTemperatureThreshold(String deviceId, int channel, double temperature) throws RTException;
	
	/**
	 *  Set the duty cycle  for the channel introduced as parameter. If this channel is temperature controlled an exception will  be raised.
	 *  
	 * @param deviceId Device Identifier.
	 * @param channel Channel id (order).
	 * @param cycle duty cycle.
	 * @throws RTException In error case.
	 */
	public void dwrSetCycleThreshold(String deviceId, int channel, int cycle) throws RTException;
}
