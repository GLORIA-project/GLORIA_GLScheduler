package eu.gloria.rtd;

import java.util.Date;
import java.util.List;

import eu.gloria.rt.entity.device.AxisRateType;
import eu.gloria.rt.entity.device.MountPointingModel;
import eu.gloria.rt.entity.device.TrackingRateType;
import eu.gloria.rt.exception.RTException;


/**
 * This interface defines the methods that control a Mount.
 * 
 * @author jcabello
 *
 */
public interface RTDMountInterface extends RTDDeviceInterface {
	
		
	
	/**
	 * Returns the UTC date/time of the telescope's internal clock.
	 * 
	 * 
	 * @return Date
	 * @throws RTException In error case.
	 */
	public Date mntGetUtcClock() throws RTException;
	
	/**
	 * Sets the UTC date/time of the telescope's internal clock.
	 * 
	 * @param date New date
	 * @throws RTException In error case.
	 */
	public void mntSetUtcClock( Date date) throws RTException;
	
	/**
	 * The local apparent sidereal time from the telescope's internal clock (hours, sidereal). 
	 * Local Apparent Sidereal Time is the sidereal time used for pointing telescopes, and thus 
	 * must be calculated from the Greenwich Mean Sidereal time, longitude, nutation in longitude 
	 * and true ecliptic obliquity.
	 * 
	 * 
	 * @return SiderealDate
	 * @throws RTException In error case.
	 */
	public double mntGetSiderealDate() throws RTException;
	
	/**
	 * Returns true if the mount is stopped in the home position.
	 * 
	 * 
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntIsAtHome() throws RTException;
	
	/**
	 * Returns true if the mount is stopped in the parked position.
	 * 
	 * 
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntIsParked() throws RTException;
	
		
	/**
	 * The right ascension for the target of an equatorial slew operation.
	 * 
	 * 
	 * @return target right ascension 
	 * @throws RTException In error case.
	 */
	public double mntGetTargetRightAscension() throws RTException;
	
	/**
	 * The declination for the target of an equatorial slew operation.
	 * 
	 * 
	 * @return target declination 
	 * @throws RTException In error case.
	 */
	public double mntGetTargetDeclination() throws RTException;
	
	/**
	 * Returns the Declination tracking rate.
	 * 
	 * 
	 * @return declination tracking rate.
	 * @throws RTException In error case.
	 */
	public double mntGetTrackingDeclinationRate() throws RTException;
	
	
	/**
	 * Returns the  Right ascension tracking rate.
	 * 
	 * 
	 * @return ascension rate
	 * @throws RTException In error case.
	 */
	public double mntGetTrackingAscensionRate() throws RTException;
	
	
	
	/**
	 * Returns the current tracking rate of the mount. 
	 * 
	 * 
	 * @return The tracking rate {@link TrackingRateType}.
	 * @throws RTException In error case.
	 */
	public TrackingRateType mntGetTrackingRate() throws RTException;
	
	/**
	 * Sets the current tracking rate of the mount. 
	 * 
	 * @param rate The tracking rate {@link TrackingRateType}.
	 * @throws RTException In error case.
	 */
	public void mntSetTrackingRate( TrackingRateType rate) throws RTException;
	
	
	/**
	 * Access method to the state (on/off) of the telescope's sidereal tracking drive.
	 * 
	 * 
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntGetTracking() throws RTException;
	
	/**
	 * Access method to the state (on/off) of the telescope's sidereal tracking drive.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void mntSetTracking( boolean value) throws RTException;
	
	
	/**
	 * Returns the  current Declination movement rate offset for telescope guiding (degrees/sec).
	 * 
	 * 
	 * @return the declination movement rate offset.
	 * @throws RTException In error case.
	 */
	public double mntGetGuideRateDeclination() throws RTException;
	
	/**
	 * Returns the current Right Ascension movement rate offset for telescope guiding (degrees/sec).
	 * 
	 * 
	 * @return The right ascension movement rate offset.
	 * @throws RTException In error case.
	 */
	public double mntGetDeclinationRateRightAscension() throws RTException;
	
	
	/**
	 * Return true if the mount is moving.
	 * 
	 * 
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntIsSlewing() throws RTException;
	
	
	/**
	 * Returns the axis 1 (Right Ascension or Azimuth) current position.
	 *  
	 * 
	 * @return Axis1 Position 
	 * @throws RTException In error case.
	 */
	public double mntGetPosAxis1 () throws RTException;
	
		
	/**
	 * Returns the axis 2 (Declination or Altitude) current position.
	 * 
	 * 
	 * @return Axis2 Position
	 * @throws RTException In error case.
	 */
	public double  mntGetPosAxis2 () throws RTException;
	
	/**
	 * Returns the axis 3 (Image rotator/de-rotator) current position.
	 * 
	 * 
	 * @return Axis3 Position
	 * @throws RTException In error case.
	 */
	public double  mntGetPosAxis3 () throws RTException;
		
	
	/**
	 * Returns true if this property can be set.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanPulseGuide() throws RTException;
	
	/**
	 * True if the guide rate properties used for PulseGuide method can be adjusted.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetGuideRates() throws RTException;
	
	/**
	 *  Returns true if this property can be set.
	 *  
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetPark() throws RTException;
	
	/**
	 * 	Set the parked position in equatorial coordinates
	 * ´
	 * 
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSetPark ( double ascension, double declination) throws RTException;
	
	/**
	 * Return the parked position ALT coordinate 
	 * 
	 * @param deviceId
	 * @return Parked ALT Position
	 * @throws RTException In error case.
	 */
	public double mntGetALTParkPos () throws RTException;
	
	/**
	 * Return the parked position AZ coordinate 
	 * 
	 * @param deviceId
	 * @return Parked AZ Position
	 * @throws RTException In error case.
	 */
	public double mntGetAZParkPos () throws RTException;
	
	/**
	 * 
	 * Returns true if the  sidereal drive can be change to on/off value.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetTracking() throws RTException;
	
	/**
	 * Returns true if this property can be set.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetTrackingRate() throws RTException;
	
		
	/**
	 *  Returns true if this telescope is capable of programmed slewing (synchronous) to equatorial.
	 *  
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewCoordinates() throws RTException;
	
	/**
	 * Returns true if the mount is capable of programmed slewing (asynchronous) to equatorial.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewCoordinatesAsync() throws RTException;
	
	/**
	 * Return true if the mount is capable of slewing to an object.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewObject() throws RTException;
	
	/**
	 * Returns true if the mount  is capable of programmed synchronous slewing to local horizontal coordinates.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewAltAz() throws RTException;
	
	/**
	 * Returns true if the mount  is capable of programmed asynchronous slewing to local horizontal coordinates
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewAzAsync() throws RTException;
	
	/**
	 * Returns True if the mount can be controlled about the specified axis via.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanMoveAzis() throws RTException;
	
	/**
	 * Returns a collection of Rate objects describing the supported rates of motion.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public List<AxisRateType> mntAxisRate() throws RTException;
	
	/**
	 * Returns a collection of Rate values of the TrackingRate property.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public List<TrackingRateType> mntTrackingRates() throws RTException;
	
	/**
	 * Moves the mount to the home position (synchronous).
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void mntGoHome() throws RTException;
	
	/**
	 * Moves the mount to the parked position and fix the state to PARKED.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void mntPark() throws RTException;
	
	/**
	 * Moves the mount to a ready position. Before call Unpark(), the mount state must have fixed to READY
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void mntUnpark() throws RTException;
	
	/**
	 * This synchronous method moves the mount to the given local horizontal coordinates. This Method must be implemented if CanSlewAltAz returns True. Raises an error if the slew fails.
	 * 
	 * 
	 * @param azimuth Azimuth value.
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void mntSlewToAltAz( double azimuth, double altitude) throws RTException;
	
	/**
	 * Asynchronous SlewToAltAz method.
	 * 
	 * 
	 * @param azimuth Azimuth value.
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void mntSlewToAltAzAsync( double azimuth, double altitude) throws RTException;
	
	/**
	 * This synchronous method moves the mount to the given ecuatorial coordinates. This Method must be implemented if CanSlewCoordinates returns True. Raises an error if the slew fails.
	 * 
	 * 
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSlewToCoordinates( double ascension, double declination) throws RTException;
	
	/**
	 * Asynchronous SlewToCoordinates method.
	 * 
	 * 
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSlewToCoordinatesAsync( double ascension, double declination) throws RTException;
	
	/**
	 * The mount will start moving at the specified rate about the specified axis and continue indefinitely following the rate of motion. 
	 * This must be implemented if the CanMoveAxis property returns True for the given axis. 
	 * The movement rate must be within the allowed values (AxisRate method).
	 * 
	 * 
	 * @param axisType 0-Primary axis (e.g., Right Ascension or Azimuth), 1-Secondary axis (e.g., Declination or Altitude), 2-Tertiary axis (e.g. imager rotator/de-rotator).
	 * @param rate The rate of motion (deg/sec, + = clockwise) about the specified axis.
	 * @throws RTException In error case.
	 */
	public void mntMoveAxis( int axisType, double rate) throws RTException;
	
	/**
	 * Moves the scope in the given direction for the given interval or time at the rate given by the corresponding guide rate property.
	 * 
	 * 
	 * @param guideDirection North-0, South-1, East-2, West-3 
	 * @param duration Duration of the movement
	 * @throws RTException In error case.
	 */
	public void mntPulseGuide( int guideDirection, int duration) throws RTException;
	
	/**
	 * To stop the movement in the axis parameter.
	 * 
	 * 
	 * @param axisType  0-Primary axis (e.g., Right Ascension or Azimuth), 1-Secondary axis (e.g., Declination or Altitude), 2-Tertiary axis (e.g. imager rotator/de-rotator).
	 * @throws RTException In error case.
	 */
	public void mntStopSlew( int axisType) throws RTException;
	
	/**
	 * To stop all movements.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void mntStopSlew() throws RTException;
	
	/**
	 * Returns the  mount pointing model.
	 * 
	 * @return Pointing model {@link MountPointingModel}.
	 * @throws RTException In error case.
	 */
	public MountPointingModel mntGetPointingModel() throws RTException;
	
	/**
	 * The mount moves North during a period of time
	 * 
	 * @throws RTException In error case.
	 */
	public void mntMoveNorth () throws RTException;
	
	/**
	 * The mount moves East during a period of time
	 * 
	 * @throws RTException In error case.
	 */
	public void mntMoveEast () throws RTException;
	
	/**
	 * The mount moves South during a period of time
	 * 
	 * @throws RTException In error case.
	 */
	public void mntMoveSouth () throws RTException;
	
	/**
	 * The mount moves West during a period of time
	 * 
	 * @throws RTException In error case.
	 */
	public void mntMoveWest () throws RTException;
		
	/**
	 * Sets the slew rate
	 * 
	 * @param rate String.
	 * @throws RTException In error case.
	 */
	public void mntSetSlewRate (String rate) throws RTException;
	
	/**
	 * Gets the slew rate
	 * 
	 * @return Slew Rate
	 * @throws RTException In error case.
	 */
	public String mntGetSlewRate () throws RTException;
	
	
	/**
	 * Slew to an object
	 * 
	 * @param object Object to slew to
	 * @throws RTException In error case.
	 */
	public void mntSlewObject (String object) throws RTException;
	
	/**
	 * Returns true if the mount is pointing at proper object.
	 * @param object Object name
	 * @param raError RA error (Hours)
	 * @param decError DEC error (Degrees)
	 * @return boolean
	 * @throws RTException in error case.
	 */
	public boolean mntIsPointingAtObject(String object, double raError, double decError) throws RTException ;

	/**
	 * Returns true if the mount is pointing at proper RADEC coordinate.
	 * @param ra RA coordinate.
	 * @param dec DEC coordinate.
	 * @param raError RA error.
	 * @param decError DEC error.
	 * @return boolean
	 * @throws RTException In error case
	 */
	public boolean mntIsPointingAtCoordinates(double ra, double dec, double raError, double decError) throws RTException;

}
