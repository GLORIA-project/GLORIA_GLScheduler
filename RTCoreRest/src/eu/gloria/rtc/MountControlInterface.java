package eu.gloria.rtc;

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
public interface MountControlInterface extends DeviceManagerInterface {
	
	
	/**
	 * Returns the UTC date/time of the telescope's internal clock.
	 * 
	 * @param deviceId Device identifier.
	 * @return Date
	 * @throws RTException In error case.
	 */
	public Date mntGetUtcClock(String deviceId) throws RTException;
	
	/**
	 * Sets the UTC date/time of the telescope's internal clock.
	 * @param deviceId Device identifier.
	 * @param date New date
	 * @throws RTException In error case.
	 */
	public void mntSetUtcClock(String deviceId, Date date) throws RTException;
	
	/**
	 * The local apparent sidereal time from the telescope's internal clock (hours, sidereal). 
	 * Local Apparent Sidereal Time is the sidereal time used for pointing telescopes, and thus 
	 * must be calculated from the Greenwich Mean Sidereal time, longitude, nutation in longitude 
	 * and true ecliptic obliquity.
	 * 
	 * @param deviceId Device identifier.
	 * @return SiderealDate
	 * @throws RTException In error case.
	 */
	public double mntGetSiderealDate(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount is stopped in the home position.
	 * 
	 * @param deviceId Device identifier.
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntIsAtHome(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount is stopped in the parked position.
	 * 
	 * @param deviceId Device identifier.
	 * @return boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntIsParked(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount is pointing at proper object.
	 * @param deviceId Device identifier.
	 * @param object Object name
	 * @param raError RA error (Hours)
	 * @param decError DEC error (Degrees)
	 * @return boolean
	 * @throws RTException in error case.
	 */
	public boolean mntIsPointingAtObject(String deviceId, String object, double raError, double decError) throws RTException;
	
	/**
	 * Returns true if the mount is pointing at proper RADEC coordinate.
	 * @param deviceId Device identifier.
	 * @param ra RA coordinate.
	 * @param dec DEC coordinate.
	 * @param raError RA error.
	 * @param decError DEC error.
	 * @return boolean
	 * @throws RTException In error case
	 */
	public boolean mntIsPointingAtCoordinates(String deviceId, double ra, double dec, double raError, double decError) throws RTException;
	
	/**
	 * The right ascension for the target of an equatorial slew operation.
	 * 
	 * @param deviceId Device identifier.
	 * @return target right ascension 
	 * @throws RTException In error case.
	 */
	public double mntGetTargetRightAscension(String deviceId) throws RTException;
	
	/**
	 * The declination for the target of an equatorial slew operation.
	 * 
	 * @param deviceId Device identifier.
	 * @return target declination 
	 * @throws RTException In error case.
	 */
	public double mntGetTargetDeclination(String deviceId) throws RTException;
	
	/**
	 * Returns the Declination tracking rate.
	 * 
	 * @param deviceId Device identifier.
	 * @return declination tracking rate.
	 * @throws RTException In error case.
	 */
	public double mntGetTrackingDeclinationRate(String deviceId) throws RTException;
	
	
	/**
	 * Returns the  Right ascension tracking rate.
	 * 
	 * @param deviceId Device identifier.
	 * @return ascension rate
	 * @throws RTException In error case.
	 */
	public double mntGetTrackingAscensionRate(String deviceId) throws RTException;
	
	
	
	/**
	 * Returns the current tracking rate of the mount. 
	 * 
	 * @param deviceId Device identifier.
	 * @return The tracking rate {@link TrackingRateType}.
	 * @throws RTException In error case.
	 */
	public TrackingRateType mntGetTrackingRate(String deviceId) throws RTException;
	
	/**
	 * Sets the current tracking rate of the mount. 
	 * 
	 * @param deviceId Device identifier.
	 * @param rate The tracking rate {@link TrackingRateType}.
	 * @throws RTException In error case.
	 */
	public void mntSetTrackingRate(String deviceId, TrackingRateType rate) throws RTException;
	
	
	/**
	 * Access method to the state (on/off) of the telescope's sidereal tracking drive.
	 * 
	 * @param deviceId Device identifier.
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntGetTracking(String deviceId) throws RTException;
	
	/**
	 * Access method to the state (on/off) of the telescope's sidereal tracking drive.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void mntSetTracking(String deviceId, boolean value) throws RTException;
	
	
	/**
	 * Returns the  current Declination movement rate offset for telescope guiding (degrees/sec).
	 * 
	 * @param deviceId Device identifier.
	 * @return the declination movement rate offset.
	 * @throws RTException In error case.
	 */
	public double mntGetGuideRateDeclination(String deviceId) throws RTException;
	
	/**
	 * Returns the current Right Ascension movement rate offset for telescope guiding (degrees/sec).
	 * 
	 * @param deviceId Device identifier.
	 * @return The right ascension movement rate offset.
	 * @throws RTException In error case.
	 */
	public double mntGetDeclinationRateRightAscension(String deviceId) throws RTException;
	
	
	/**
	 * Return true if the mount is moving.
	 * 
	 * @param deviceId Device identifier.
	 * @return boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntIsSlewing(String deviceId) throws RTException;
	
	
	/**
	 * Returns the axis 1 (Right Ascension or Azimuth) current position.
	 *  
	 * @param deviceId Device identifier.
	 * @return Axis1 Position 
	 * @throws RTException In error case.
	 */
	public double mntGetPosAxis1 (String deviceId) throws RTException;
	
		
	/**
	 * Returns the axis 2 (Declination or Altitude) current position.
	 * 
	 * @param deviceId Device identifier.
	 * @return Axis2 Position
	 * @throws RTException In error case.
	 */
	public double  mntGetPosAxis2 (String deviceId) throws RTException;
	
	/**
	 * Returns the axis 3 (Image rotator/de-rotator) current position.
	 * 
	 * @param deviceId Device identifier.
	 * @return Axis3 Position
	 * @throws RTException In error case.
	 */
	public double  mntGetPosAxis3 (String deviceId) throws RTException;
		
	
	/**
	 * Returns true if this property can be set.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanPulseGuide(String deviceId) throws RTException;
	
	/**
	 * True if the guide rate properties used for PulseGuide method can be adjusted.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetGuideRates(String deviceId) throws RTException;
	
	/**
	 *  Returns true if this property can be set.
	 *  
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetPark(String deviceId) throws RTException;
	
	/**
	 * 	Set the parked position in equatorial coordinates
	 * ´
	 * @param deviceId Device identifier.
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSetPark (String deviceId, double ascension, double declination) throws RTException;
	
	/**
	 * Return the parked position ALT coordinate 
	 * 
	 * @param deviceId
	 * @return Parked ALT Position
	 * @throws RTException In error case.
	 */
	public double mntGetALTParkPos (String deviceId) throws RTException;
	
	/**
	 * Return the parked position AZ coordinate 
	 * 
	 * @param deviceId
	 * @return Parked AZ Position
	 * @throws RTException In error case.
	 */
	public double mntGetAZParkPos (String deviceId) throws RTException;
	
	/**
	 * 
	 * Returns true if the  sidereal drive can be change to on/off value.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetTracking(String deviceId) throws RTException;
	
	/**
	 * Returns true if this property can be set.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSetTrackingRate(String deviceId) throws RTException;
	
	
	/**
	 *  Returns true if this telescope is capable of programmed slewing (synchronous) to equatorial.
	 *  
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewCoordinates(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount is capable of programmed slewing (asynchronous) to equatorial.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewCoordinatesAsync(String deviceId) throws RTException;
	
	
	/**
	 * Return true if the mount is capable of slewing to an object.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewObject(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount  is capable of programmed synchronous slewing to local horizontal coordinates.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewAltAz(String deviceId) throws RTException;
	
	/**
	 * Returns true if the mount  is capable of programmed asynchronous slewing to local horizontal coordinates
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanSlewAzAsync(String deviceId) throws RTException;
	
	/**
	 * Returns True if the mount can be controlled about the specified axis via.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean mntCanMoveAzis(String deviceId) throws RTException;
	
	/**
	 * Returns a collection of Rate objects describing the supported rates of motion.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public List<AxisRateType> mntAxisRate(String deviceId) throws RTException;
	
	/**
	 * Returns a collection of Rate values of the TrackingRate property.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public List<TrackingRateType> mntTrackingRates(String deviceId) throws RTException;
	
	/**
	 * Moves the mount to the home position (synchronous).
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntGoHome(String deviceId) throws RTException;
	
	/**
	 * Moves the mount to the parked position and fix the state to PARKED.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntPark(String deviceId) throws RTException;
	
	/**
	 * Moves the mount to a ready position. Before call Unpark(), the mount state must have fixed to READY
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntUnpark(String deviceId) throws RTException;
	
	/**
	 * This synchronous method moves the mount to the given local horizontal coordinates. This Method must be implemented if CanSlewAltAz returns True. Raises an error if the slew fails.
	 * 
	 * @param deviceId Device identifier.
	 * @param azimuth Azimuth value.
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void mntSlewToAltAz(String deviceId, double azimuth, double altitude) throws RTException;
	
	/**
	 * Asynchronous SlewToAltAz method.
	 * 
	 * @param deviceId Device identifier.
	 * @param azimuth Azimuth value.
	 * @param altitude Altitude value.
	 * @throws RTException In error case.
	 */
	public void mntSlewToAltAzAsync(String deviceId, double azimuth, double altitude) throws RTException;
	
	/**
	 * This synchronous method moves the mount to the given ecuatorial coordinates. This Method must be implemented if CanSlewCoordinates returns True. Raises an error if the slew fails.
	 * 
	 * @param deviceId Device identifier.
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSlewToCoordinates(String deviceId, double ascension, double declination) throws RTException;
	
	/**
	 * Asynchronous SlewToCoordinates method.
	 * 
	 * @param deviceId Device identifier.
	 * @param ascension Angle.
	 * @param declination Angle.
	 * @throws RTException In error case.
	 */
	public void mntSlewToCoordinatesAsync(String deviceId, double ascension, double declination) throws RTException;
	
	/**
	 * The mount will start moving at the specified rate about the specified axis and continue indefinitely following the rate of motion. 
	 * This must be implemented if the CanMoveAxis property returns True for the given axis. 
	 * The movement rate must be within the allowed values (AxisRate method).
	 * 
	 * @param deviceId Device identifier.
	 * @param axisType 0-Primary axis (e.g., Right Ascension or Azimuth), 1-Secondary axis (e.g., Declination or Altitude), 2-Tertiary axis (e.g. imager rotator/de-rotator).
	 * @param rate The rate of motion (deg/sec, + = clockwise) about the specified axis.
	 * @throws RTException In error case.
	 */
	public void mntMoveAxis(String deviceId, int axisType, double rate) throws RTException;
	
	/**
	 * Moves the scope in the given direction for the given interval or time at the rate given by the corresponding guide rate property.
	 * 
	 * @param deviceId Device identifier.
	 * @param guideDirection North-0, South-1, East-2, West-3 
	 * @param duration Duration of the movement
	 * @throws RTException In error case.
	 */
	public void mntPulseGuide(String deviceId, int guideDirection, int duration) throws RTException;
	
	/**
	 * To stop the movement in the axis parameter.
	 * 
	 * @param deviceId Device identifier.
	 * @param axisType  0-Primary axis (e.g., Right Ascension or Azimuth), 1-Secondary axis (e.g., Declination or Altitude), 2-Tertiary axis (e.g. imager rotator/de-rotator).
	 * @throws RTException In error case.
	 */
	public void mntStopSlew(String deviceId, int axisType) throws RTException;
	
	/**
	 * To stop all movements.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntStopSlew(String deviceId) throws RTException;
	
	/**
	 * Returns the  mount pointing model.
	 * 
	 * @param deviceId Device identifier.
	 * @return Pointing model {@link MountPointingModel}.
	 * @throws RTException In error case.
	 */
	public MountPointingModel mntGetPointingModel(String deviceId) throws RTException;
	
	/**
	 * The mount moves North during a period of time
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntMoveNorth (String deviceId) throws RTException;
	
	/**
	 * The mount moves East during a period of time
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntMoveEast (String deviceId) throws RTException;
	
	/**
	 * The mount moves South during a period of time
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntMoveSouth (String deviceId) throws RTException;
	
	/**
	 * The mount moves West during a period of time
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public void mntMoveWest (String deviceId) throws RTException;
		
	/**
	 * Sets the slew rate
	 * 
	 * @param deviceId Device identifier.
	 * @param rate String.
	 * @throws RTException In error case.
	 */
	public void mntSetSlewRate (String deviceId, String rate) throws RTException;
	
	
	/**
	 * Gets the slew rate
	 * 
	 * @param deviceId Device identifier
	 * @return Slew Rate
	 * @throws RTException In error case.
	 */
	public String mntGetSlewRate (String deviceId) throws RTException;
	
	/**
	 * Slew to an object
	 * 
	 * @param deviceId Device identifier.
	 * @param object Object to slew to
	 * @throws RTException In error case.
	 */
	public void mntSlewObject (String deviceId, String object) throws RTException;

}
