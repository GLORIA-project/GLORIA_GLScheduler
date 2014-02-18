package eu.gloria.rtd;


import java.util.Date;
import java.util.List;

import eu.gloria.rt.entity.device.CameraAcquisitionMode;
import eu.gloria.rt.entity.device.CameraDigitizingMode;
import eu.gloria.rt.entity.device.CameraType;
import eu.gloria.rt.entity.device.Image;
import eu.gloria.rt.entity.device.ImageContentType;
import eu.gloria.rt.entity.device.ImageFormat;
import eu.gloria.rt.exception.RTException;


/**
 * This is the interface that controls a camera device.
 * 
 * @author jcabello
 *
 */
public interface RTDCameraInterface extends RTDDeviceInterface {
	
	/**
	 * Return the focuser attached to the camera.
	 * 
	 * @return Focuser identifier
	 * @throws RTException In error case.
	 */
	public String camGetFocuser () throws RTException;
	
	
	/**
	 * Returns the list of filter wheels attached to the camera
	 * 
	 * @return list of filter wheels attached to the camera
	 * @throws RTException In error case.
	 */
	public List <String> camGetFilters () throws RTException;
	
	/**
	 * Returns the camera type.
	 * 
	 * 
	 * @return CameraType.
	 * @throws RTException In error case.
	 */
	public CameraType camGetCameraType() throws RTException;
	
	/**
	 *  Width of the camera sensor in unbinned pixels.
	 *  
	 * 
	 * @return width.
	 * @throws RTException In error case.
	 */	
	public int camGetXsize() throws RTException;
	
	
	/**
	 * Height of the camera sensor in unbinned pixels.
	 * 
	 * 
	 * @return height.
	 * @throws RTException In error case.
	 */
	public int camGetYSize() throws RTException;
	
	/**
	 * Returns True if the camera can abort exposures. False if not.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanAbortExposure() throws RTException;
	
	/**
	 * Returns true, the camera can have different binning on the X and Y axes, as determined by BinX and BinY. If False, the binning must be equal on the X and Y axes.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanAsymetricBin() throws RTException;
	
	/**
	 * If True, the camera can return the cooler power level. If False, this information is not available. 
	 * The cooler power level is normally regulated internally to the camera, based on the temperature setpoint.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanGetCoolerPower() throws RTException;
	
	/**
	 * If True, the camera can set on/off the cooler.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanSetCooler() throws RTException;
	
	/**
	 * If True, the camera's cooler setpoint can be adjusted. If False, the camera either uses open-loop cooling or does not have the 
	 * ability to adjust temperature from software, and setting the TemperatureSetpoint property has no effect..
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanSetCCDTemperature() throws RTException;
	
	
	/**
	 * If True, the CCD Temperature and the Heat Sink temperature can be read.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanControlTemperature() throws RTException;
	
	/**
	 * Some cameras support StopExposure, which allows the exposure to be terminated before the exposure timer completes,
	 * but will still read out the image. Returns True if StopExposure is available, False if not..
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanStopExposure() throws RTException;
	
	/**
	 * Returns the present cooler power level, in percent. Returns zero if CoolerOn is False.
	 * 
	 * 
	 * @return Measure.
	 * @throws RTException In error case.
	 */
	public float camGetCoolerPower() throws RTException;
	
	
	/**
	 * Returns the gain of the camera in photoelectrons per A/D unit. (Some cameras have multiple gain modes;
	 * these should be selected via the SetupDialog and thus are static during a session.)
	 * 
	 * 
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public double camGetElectronsPerADU() throws RTException;
	
	/**
	 * Reports the full well capacity of the camera in electrons, at the current camera settings [FullWellCapacity].
	 * 
	 * 
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public double camGetFullWellCapacity() throws RTException;
	
	/**
	 * If True, the camera has a mechanical shutter. If False, the camera does not have a shutter. If there is no shutter,
	 * the StartExposure command will ignore the Light parameter
	 * 
	 * 
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public boolean camHasShutter() throws RTException;
	
	
	/**
	 * If True, the camera's brightness can be adjusted. If False, the camera can not adjust the brightness.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasBrightness() throws RTException;
	
	/**
	 * If True, the camera's contrast can be adjusted. If False, the camera can not adjust the contrast.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasConstrast() throws RTException;
	
	/**
	 * If True, the camera's gain can be adjusted. If False, the camera can not adjust the gain.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasGain() throws RTException;
	
	/**
	 * If True, the camera’s gamma can be adjusted. If False, the camera can not adjust the gamma.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasGamma() throws RTException;
	
	/**
	 * If True,the camera supports subframes.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasSubframe() throws RTException;
	
	/**
	 * If True, the camera’s exposure time can be changed. If False, the camera can not change the exposure time.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasExposureTime() throws RTException;
	
	/**
	 * Returns the current heat sink temperature (called "ambient temperature" by some manufacturers) in degrees Celsius.
	 * Only valid if CanControlTemperature is True .
	 * 
	 * 
	 * @return Measure.
	 * @throws RTException In error case.
	 */
	public double camHeatSinkTemperature() throws RTException;
	
	/**
	 * If True, pulse guiding is in progress. Required if the PulseGuide() method (which is non-blocking) is implemented.
	 * See the PulseGuide.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camIsPulseGuiding() throws RTException;
	
	/**
	 * Reports the last error condition reported by the camera hardware or communications link. The string may contain a text message or simply an error code.
	 * The error value is cleared the next time any method is called.
	 * 
	 * 
	 * @return String
	 * @throws RTException In error case.
	 */
	public String camGetLastError() throws RTException;
	
		
	/**
	 * Reports the actual exposure duration in seconds (i.e. shutter open time). This may differ from the exposure time requested due to shutter latency,
	 * camera timing precision, etc.
	 * 
	 * 
	 * @return Seconds.
	 * @throws RTException In error case.
	 */
	public double camGetLastExposureDuration() throws RTException;
	
	
	/**
	 * Reports the actual exposure start. It could be formatted in the FITS-standard CCYY-MM-DDThh:mm:ss[.sss...] format.
	 * 
	 * 
	 * @return timestamp.
	 * @throws RTException In error case.
	 */
	public Date camGetLastExposureStart() throws RTException;
	
	/**
	 * Reports the maximum ADU value the camera can produce.
	 * 
	 * 
	 * @return maximum ADU.
	 * @throws RTException In error case.
	 */
	public long camGetMaxAdu() throws RTException;
	
	/**
	 * If AsymmetricBinning = False, returns the maximum allowed binning factor. If AsymmetricBinning = True,
	 * returns the maximum allowed binning factor for the X axis.
	 * 
	 * 
	 * @return The maximum allowed binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public int camGetMaxBinX() throws RTException;
	
	/**
	 * If AsymmetricBinning = False, equals MaxBinX. If AsymmetricBinning = True,
	 * returns the maximum allowed binning factor for the Y axis.
	 * 
	 * 
	 * @return The maximum allowed binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetMaxBinY() throws RTException;
	
	/**
	 * Returns the width of the sensor chip pixels in microns, as provided by the camera driver.
	 * 
	 * 
	 * @return Width
	 * @throws RTException In error case.
	 */
	public int camGetPixelSizeX() throws RTException;
	
	/**
	 * Returns the heigth of the sensor chip pixels in microns, as provided by the camera driver.
	 * 
	 * 
	 * @return Heigth.
	 * @throws RTException In error case.
	 */
	public int camGetPixelSizeY() throws RTException;
	
	/**
	 * If it is Continuous mode, the camera will take images according to the ExposureTime and FPS values established.
	 * If it is OneShot mode, only one image will be taken according to the ExposureTime.
	 * 
	 * @return Continuous, OneShot.
	 * @throws RTException In error case.
	 */
	public CameraAcquisitionMode camGetAcquisitionMode() throws RTException;
	
	/**
	 * Frames Per Second. It only takes effect in the “Continuous” Acquisition Mode.
	 * 
	 * 
	 * @return Frames Per Second
	 * @throws RTException In error case.
	 */
	public float camGetFPS() throws RTException;
	
	
	/**
	 * If it is Automatic mode, the camera will take images according to the Brightness, Contrast,
	 * Gamma and Gain values calculated automatically by the camera itself. If it is Manual mode, the camera will
	 * take images according to the Brightness, Contrast, Gamma and Gain values established by the variables.
	 * 
	 * 
	 * @return Automatic, Manual.
	 * @throws RTException In error case.
	 */
	public CameraDigitizingMode camGetDigitilizingMode() throws RTException;
	
	/**
	 * Gets the binning factor for the X axis. Also returns the current value.
	 * 
	 * 
	 * @return The binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public int camGetBinX() throws RTException;
	
	
	/**
	 * Sets the binning factor for the X axis. Also returns the current value.
	 * 
	 * 
	 * @param value The binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public void camSetBinX( int value) throws RTException;
	
	/**
	 * Gets the binning factor for the Y axis. Also returns the current value..
	 * 
	 * 
	 * @return The binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetBinY() throws RTException;
	
	/**
	 * Sets the binning factor for the Y axis. Also returns the current value..
	 * 
	 * @param value The binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public void camSetBinY( int value) throws RTException;
	
	/**
	 * Returns true if the camera cooler is on.
	 * 
	 * 
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean camIsCoolerOn() throws RTException;
	
	/**
	 * Turns on and off the camera cooler.
	 * 
	 * 
	 * @param value new value
	 * @throws RTException In error case.
	 */
	public void camSetCoolerOn( boolean value) throws RTException;
	
	/**
	 * Returns the subframe width. If binning is active, value is in binned pixels.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public int camGetROINumX() throws RTException;
	
	/**
	 * Sets the subframe width. If binning is active, value is in binned pixels.
	 * 
	 * 
	 * @param value new value.
	 * @throws RTException In error case.
	 */
	public void camSetROINumX( int value) throws RTException;
	
	/**
	 *  Sets the subframe height. If binning is active, value is in binned pixels.
	 *  
	 * 
	 * @param value New value
	 * @throws RTException In error case.
	 */
	public void camSetROINumY( int value) throws RTException;
	
	/**
	 * Returns the subframe height. If binning is active, value is in binned pixels.
	 * 
	 * @return subframe height.
	 * @throws RTException In error case.
	 */
	public int camGetROINumY() throws RTException;
	
	/**
	 * Sets the subframe start position for the X axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * 
	 * @param ROIStartX new value
	 * @throws RTException In error case.
	 */
	public void camSetROIStartX( int ROIStartX) throws RTException;
	
	/**
	 * Returns the subframe start position for the X axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * 
	 * @return Subframe start position for the X axis
	 * @throws RTException In error case.
	 */
	public int camGetROIStartX() throws RTException;
	
	
	/**
	 *  Sets the subframe start position for the Y axis (0 based). If binning is active, value is in binned pixels.
	 *  
	 * 
	 * @param value The subframe start position for the Y axis.
	 * @throws RTException In error case.
	 */
	public void camSetROIStartY( int value) throws RTException;
	
	/**
	 * Returns The subframe start position for the Y axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * 
	 * @return The subframe start position for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetROIStartY() throws RTException;
	
	/**
	 * Sets the value for image brightness.
	 * 
	 * 
	 * @param value brightness.
	 * @throws RTException In error case.
	 */
	public void camSetBrightness( long value) throws RTException;
	
	/**
	 * Returns the value of image brightness.
	 * 
	 * 
	 * @return image brightness.
	 * @throws RTException In error case.
	 */
	public long camGetBrightness() throws RTException;
	
	/**
	 * Sets the value for image contrast.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetContrast( long value) throws RTException;
	
	/**
	 * Returns the image contrast.
	 * 
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetContrast() throws RTException;
	
	/**
	 * Sets the value for image gain.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetGain( long value) throws RTException;
	
	/**
	 * Returns the image gain.
	 * 
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetGain() throws RTException;
	
	/**
	 * Sets the value for image gamma correction.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetGamma( long value) throws RTException;
	
	/**
	 * Returns the image gamma correction.
	 * 
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetGamma() throws RTException;
	
	/**
	 * Sets the value in seconds for the image exposure time.
	 * 
	 * @param value New value
	 * @throws RTException In error case.
	 */
	public void camSetExposureTime( double value) throws RTException;
	
	/**
	 * Returns  the value in seconds for the image exposure time.
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public Double camGetExposureTime() throws RTException;
	
	/**
	 * Sets the camera cooler setpoint in degrees Kelvin.
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetCCDTemperature( float value) throws RTException;
	
	/**
	 * Returns the camera cooler setpoint in degrees Kelvin
	 * 
	 * @return value
	 * @throws RTException In error case.
	 */
	public float camGetCCDTemperature() throws RTException;
	
	/**
	 * Returns the current CCD temperature in degrees Kelvin. Only valid if CanControlTemperature is True.
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public float camGetCCDCurrentTemperature() throws RTException;
	
	
	/**
	 * Aborts the current exposure, if any, and returns the camera to READY state.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void camAbortExposure() throws RTException;
	
	/**
	 * This method may return immediately after the move has started, in which case back-to-back dual axis pulse-guiding can be supported. Use the IsPulseGuiding property to detect when all moves have completed
	 * 
	 * 
	 * @param direction direction in which the guide-rate motion is to be made. The values for GuideDirections are: 0=guideNorth, 1=guideSouth, 2=guideEast, 3=guideWest.
	 * @param duration Duration of the guide-rate motion (milliseconds)
	 * @throws RTException In error case.
	 */
	public void camPulseGuide( int direction, long duration) throws RTException;
	
	/**
	 * Starts an exposure. Use ImageReady to check when the exposure is complete, and also use the AcquisitionMode and DigitizingMode values. This method uses ExposureTime variable.
	 * 
	 * 
	 * @param light Frame type (true: light frame, false: darkframe).
	 * @return String Image uid 
	 * @throws RTException In error case.
	 */
	public String camStartExposure( boolean light) throws RTException;
	
	/**
	 * Stops the current exposure, if any. If an exposure is in progress, the readout process is initiated. Ignored if readout is already in process.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void camStopExposure() throws RTException;
	

	/**
	 * Returns an image (raw data) from the last exposure. 
	 * The image could be an array of short, long, double.
	 * If the application cannot handle multispectral images, it should use just the first plane
	 * 
	 * @param format Image format.
	 * @return Image data
	 * @throws RTException In error case.
	 */
	public Image camGetImage(ImageFormat format) throws RTException;
	
	/**
	 * If True, there is an image from the camera available. 
	 * If False, no image is available and attempts to use the ImageArray method will produce an exception.
	 * 
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean camImageReady () throws RTException;
	
	/**
	 * Gets the image data type (Short, Long, Double)
	 * 
	 * @return ImageContentType
	 * @throws RTException In error case
	 */
	public ImageContentType camGetImageDataType () throws RTException;
	
	
	/**
	 * Sets the image data type (Short, Long, Double)
	 * 
	 * @throws RTException In error case
	 */
	//public void camSetImageDataType ( ImageContentType type) throws RTException;
	
	/**
	 * Gets the current CCD Bit Depth.
	 * 
	 * @return Current CCD Bit Depth
	 * @throws RTException In error case
	 */
	public int camGetBitDepth () throws RTException;
	
	/**
	 * Sets the CCD Bit Depth.
	 * 
	 * @param bits Bit Depth
	 * @throws RTException In error case
	 */
	public void camSetBitDepth (int bits) throws RTException;
	
	/**
	 * Gets the quality in continue mode
	 * 
	 * @return int Quality value 
	 * @throws RTException In error case
	 */
	public int camGetContinueModeQuality () throws RTException;
	
	/**
	 * Sets the quality in continue mode
	 * 
	 * @param value Quality value (int)
	 * @throws RTException In error case
	 */
	public void camSetContinueModeQuality (int value) throws RTException;
	
	/**
	 * Gets the quality in oneShot mode
	 * 
	 * @return int Quality value 
	 * @throws RTException In error case
	 */
	public int camGetOneShotModeQuality () throws RTException;
	
	/**
	 * Sets the quality in oneShot mode
	 * 
	 * @param value Quality value (int)
	 * @throws RTException In error case
	 */
	public void camSetOneShotModeQuality (int value) throws RTException;
	
	/**
	 * Gets the image path where images taken in continue mode are stored.
	 * 
	 * @return String The image path
	 * @throws RTException In error case
	 */
	public String camGetContinueModeImagePath () throws RTException;
	
	/**
	 * Gets the image path where images taken in oneShot mode are stored.
	 * 
	 * @return String The image path
	 * @throws RTException In error case
	 */
	public String camGetOneShotModeImagePath () throws RTException;
	
	/**
	 * Gets is gain parameter is auto-adjusted
	 * 
	 * @return True if gain parameter is auto-adjusted
	 * @throws RTException In error case
	 */
	public boolean camGetAutoGain () throws RTException;
	
	/**
	 * Sets the gain parameter as auto-adjusted or not.
	 * 
	 * @param value True if gain parameter is auto-adjusted.
	 * @throws RTException In error case
	 */
	public void camSetAutoGain (boolean value) throws RTException;
	
	/**
	 * Gets is exposure time parameter is auto-adjusted
	 * 
	 * @return True if exposure time parameter is auto-adjusted
	 * @throws RTException In error case
	 */
	public boolean camGetAutoExposureTime () throws RTException;
	
	/**
	 * Sets the exposure time parameter as auto-adjusted or not.
	 * 
	 * @param value True if exposure time parameter is auto-adjusted.
	 * @throws RTException In error case
	 */
	public void camSetAutoExposureTime (boolean value) throws RTException;
	
	
	/**
	 * Starts the continue mode.
	 * 
	 * @return String Image uid 
	 * @throws RTException In error case
	 */
	public String camStartContinueMode () throws RTException;
	
	/**
	 * Stops the continue mode.
	 * 
	 * @throws RTException In error case
	 */
	public void camStopContinueMode () throws RTException;
	
	/**
	 * Returns the URL where the image is stored.
	 * @param uid Image identifier
	 * @param format Image Format.
	 * @return String URL
	 * @throws RTException In error case
	 */
	public String camGetImageURL (String uid, ImageFormat format) throws RTException;
	
	/**
	 * Returns the supported Image formats for OneShotMode.
	 * @return Supported Image format list.
	 * @throws RTException In error case.
	 */
	public List<ImageFormat> camGetOneShotModeImageFormats() throws RTException;
	
	/**
	 * Returns the supported Image formats for Continue mode.
	 * @return Supported Image format list.
	 * @throws RTException In error case.
	 */
	public List<ImageFormat> camGetContinueModeImageFormats() throws RTException;
	

}
