package eu.gloria.rtd;

import eu.gloria.rt.entity.device.DewRemoverChannelType;
import eu.gloria.rt.exception.RTException;

/**
 * This interface defines the methods that control a Dew remover device.
 * 
 * @author jcabello
 *
 */
public interface RTDDewRemoverInterface extends RTDDeviceInterface {

	/**
	 * Returns the channels number.
	 * 
	 * @return Channel number.
	 * @throws RTException In error case.
	 */
	public int dwrGetChannelsNumber() throws RTException;
	
	/**
	 * Returns the type of a channel (duty cycle, temperature controlled).
	 * 
	 * @param channel channel id (order).
	 * @return Type.
	 * @throws RTException In error case.
	 */
	public DewRemoverChannelType dwrGetChannelType(int channel) throws RTException;
	
	/**
	 * Set the temperature threshold for the selected channel. If this channel is not temperature controlled an exception will  be raised.
	 * 
	 * @param channel Channel id (order).
	 * @param temperature threshold.
	 * @throws RTException In error case.
	 */
	public void dwrSetTemperatureThreshold(int channel, double temperature) throws RTException;
	
	/**
	 *  Set the duty cycle  for the channel introduced as parameter. If this channel is temperature controlled an exception will  be raised.
	 *  
	 * @param channel Channel id (order).
	 * @param cycle duty cycle.
	 * @throws RTException In error case.
	 */
	public void dwrSetCycleThreshold(int channel, int cycle) throws RTException;
}
