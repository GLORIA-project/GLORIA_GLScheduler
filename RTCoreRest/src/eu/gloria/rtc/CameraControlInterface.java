package eu.gloria.rtc;


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
public interface CameraControlInterface extends DeviceManagerInterface {
	
	/**
	 * Return the focuser attached to the camera.
	 * 
	 * @param deviceId Device identifier.
	 * @return Focuser identifier
	 * @throws RTException In error case.
	 */
	public String camGetFocuser (String deviceId) throws RTException;
	
	/**
	 * Returns the list of filter wheels attached to the camera
	 * 
	 * @param deviceId Device identifier.
	 * @return list of filter wheels attached to the camera
	 * @throws RTException In error case.
	 */
	public List <String> camGetFilters (String deviceId) throws RTException;
	
	/**
	 * Returns the camera type.
	 * 
	 * @param deviceId Device identifier.
	 * @return {@link CameraType}
	 * @throws RTException In error case.
	 */
	public CameraType camGetCameraType(String deviceId) throws RTException;
	
	/**
	 *  Width of the camera sensor in unbinned pixels.
	 *  
	 * @param deviceId Device identifier.
	 * @return width.
	 * @throws RTException In error case.
	 */	
	public int camGetXsize(String deviceId) throws RTException;
	
	
	/**
	 * Height of the camera sensor in unbinned pixels.
	 * 
	 * @param deviceId Device identifier
	 * @return height.
	 * @throws RTException In error case.
	 */
	public int camGetYSize(String deviceId) throws RTException;
	
	/**
	 * Returns True if the camera can abort exposures. False if not.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanAbortExposure(String deviceId) throws RTException;
	
	/**
	 * Returns true, the camera can have different binning on the X and Y axes, as determined by BinX and BinY. If False, the binning must be equal on the X and Y axes.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanAsymetricBin(String deviceId) throws RTException;
	
	/**
	 * If True, the camera can return the cooler power level. If False, this information is not available. 
	 * The cooler power level is normally regulated internally to the camera, based on the temperature setpoint.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanGetCoolerPower(String deviceId) throws RTException;
	
	/**
	 * If True, the camera can set on/off the cooler.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanSetCooler(String deviceId) throws RTException;
	
	/**
	 * If True, the camera's cooler setpoint can be adjusted. If False, the camera either uses open-loop cooling or does not have the 
	 * ability to adjust temperature from software, and setting the TemperatureSetpoint property has no effect..
	 * @param deviceId Device identifier
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanSetCCDTemperature(String deviceId) throws RTException;
	
	
	/**
	 * If True, the CCD Temperature and the Heat Sink temperature can be read.
	 * 
	 * @param deviceId Device Identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanControlTemperature(String deviceId) throws RTException;
	
	/**
	 * Some cameras support StopExposure, which allows the exposure to be terminated before the exposure timer completes,
	 * but will still read out the image. Returns True if StopExposure is available, False if not..
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camCanStopExposure(String deviceId) throws RTException;
	
	/**
	 * Returns the present cooler power level, in percent. Returns zero if CoolerOn is False.
	 * 
	 * @param deviceId Device identifier.
	 * @return Measure.
	 * @throws RTException In error case.
	 */
	public float camGetCoolerPower(String deviceId) throws RTException;
	
	
	/**
	 * Returns the gain of the camera in photoelectrons per A/D unit. (Some cameras have multiple gain modes;
	 * these should be selected via the SetupDialog and thus are static during a session.)
	 * 
	 * @param deviceId Device identifier.
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public double camGetElectronsPerADU(String deviceId) throws RTException;
	
	/**
	 * Reports the full well capacity of the camera in electrons, at the current camera settings [FullWellCapacity].
	 * 
	 * @param deviceId Device identifier.
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public double camGetFullWellCapacity(String deviceId) throws RTException;
	
	/**
	 * If True, the camera has a mechanical shutter. If False, the camera does not have a shutter. If there is no shutter,
	 * the StartExposure command will ignore the Light parameter
	 * 
	 * @param deviceId Device identifier
	 * @return Value.
	 * @throws RTException In error case.
	 */
	public boolean camHasShutter(String deviceId) throws RTException;
	
	
	/**
	 * If True, the camera's brightness can be adjusted. If False, the camera can not adjust the brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasBrightness(String deviceId) throws RTException;
	
	/**
	 * If True, the camera's contrast can be adjusted. If False, the camera can not adjust the contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasConstrast(String deviceId) throws RTException;
	
	/**
	 * If True, the camera's gain can be adjusted. If False, the camera can not adjust the gain.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasGain(String deviceId) throws RTException;
	
	/**
	 * If True, the camera’s gamma can be adjusted. If False, the camera can not adjust the gamma.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasGamma(String deviceId) throws RTException;
	
	/**
	 * If True,the camera supports subframes.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasSubframe(String deviceId) throws RTException;
	
	/**
	 * If True, the camera’s exposure time can be changed. If False, the camera can not change the exposure time.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camHasExposureTime(String deviceId) throws RTException;
	
	/**
	 * Returns the current heat sink temperature (called "ambient temperature" by some manufacturers) in degrees Celsius.
	 * Only valid if CanControlTemperature is True .
	 * 
	 * @param deviceId Device identifier.
	 * @return Measure.
	 * @throws RTException In error case.
	 */
	public double camHeatSinkTemperature(String deviceId) throws RTException;
	
	/**
	 * If True, pulse guiding is in progress. Required if the PulseGuide() method (which is non-blocking) is implemented.
	 * See the PulseGuide.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean camIsPulseGuiding(String deviceId) throws RTException;
	
	/**
	 * Reports the last error condition reported by the camera hardware or communications link. The string may contain a text message or simply an error code.
	 * The error value is cleared the next time any method is called.
	 * 
	 * @param deviceId Device identifier.
	 * @return String
	 * @throws RTException In error case.
	 */
	public String camGetLastError(String deviceId) throws RTException;
	
		
	/**
	 * Reports the actual exposure duration in seconds (i.e. shutter open time). This may differ from the exposure time requested due to shutter latency,
	 * camera timing precision, etc.
	 * 
	 * @param deviceId Device identifier.
	 * @return Seconds.
	 * @throws RTException In error case.
	 */
	public double camGetLastExposureDuration(String deviceId) throws RTException;
	
	
	/**
	 * Reports the actual exposure start. It could be formatted in the FITS-standard CCYY-MM-DDThh:mm:ss[.sss...] format.
	 * 
	 * @param deviceId Device identifier.
	 * @return timestamp.
	 * @throws RTException In error case.
	 */
	public Date camGetLastExposureStart(String deviceId) throws RTException;
	
	/**
	 * Reports the maximum ADU value the camera can produce.
	 * 
	 * @param deviceId Device identifier.
	 * @return maximum ADU.
	 * @throws RTException In error case.
	 */
	public long camGetMaxAdu(String deviceId) throws RTException;
	
	/**
	 * If AsymmetricBinning = False, returns the maximum allowed binning factor. If AsymmetricBinning = True,
	 * returns the maximum allowed binning factor for the X axis.
	 * 
	 * @param deviceId Device identifier.
	 * @return The maximum allowed binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public int camGetMaxBinX(String deviceId) throws RTException;
	
	/**
	 * If AsymmetricBinning = False, equals MaxBinX. If AsymmetricBinning = True,
	 * returns the maximum allowed binning factor for the Y axis.
	 * 
	 * @param deviceId Device identifier.
	 * @return The maximum allowed binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetMaxBinY(String deviceId) throws RTException;
	
	/**
	 * Returns the width of the sensor chip pixels in microns, as provided by the camera driver.
	 * 
	 * @param deviceId Device identifier.
	 * @return Width
	 * @throws RTException In error case.
	 */
	public int camGetPixelSizeX(String deviceId) throws RTException;
	
	/**
	 * Returns the heigth of the sensor chip pixels in microns, as provided by the camera driver.
	 * 
	 * @param deviceId Device identifier.
	 * @return Heigth.
	 * @throws RTException In error case.
	 */
	public int camGetPixelSizeY(String deviceId) throws RTException;
	
	/**
	 * If it is Continuous mode, the camera will take images according to the ExposureTime and FPS values established.
	 * If it is OneShot mode, only one image will be taken according to the ExposureTime.
	 * @param deviceId Device identifier
	 * @return Continuous, OneShot.
	 * @throws RTException In error case.
	 */
	public CameraAcquisitionMode camGetAcquisitionMode(String deviceId) throws RTException;
	
	/**
	 * Frames Per Second. It only takes effect in the “Continuous” Acquisition Mode.
	 * 
	 * @param deviceId Device identifier.
	 * @return Frames Per Second
	 * @throws RTException In error case.
	 */
	public float camGetFPS(String deviceId) throws RTException;
	
	
	/**
	 * If it is Automatic mode, the camera will take images according to the Brightness, Contrast,
	 * Gamma and Gain values calculated automatically by the camera itself. If it is Manual mode, the camera will
	 * take images according to the Brightness, Contrast, Gamma and Gain values established by the variables.
	 * 
	 * @param deviceId Device identifier.
	 * @return Automatic, Manual.
	 * @throws RTException In error case.
	 */
	public CameraDigitizingMode camGetDigitilizingMode(String deviceId) throws RTException;
	
	/**
	 * Gets the binning factor for the X axis. Also returns the current value.
	 * 
	 * @param deviceId Device identifier.
	 * @return The binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public int camGetBinX(String deviceId) throws RTException;
	
	
	/**
	 * Sets the binning factor for the X axis. Also returns the current value.
	 * 
	 * @param deviceId Device identifier.
	 * @param value The binning factor for the X axis.
	 * @throws RTException In error case.
	 */
	public void camSetBinX(String deviceId, int value) throws RTException;
	
	/**
	 * Gets the binning factor for the Y axis. Also returns the current value..
	 * 
	 * @param deviceId Device identifier.
	 * @return The binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetBinY(String deviceId) throws RTException;
	
	/**
	 * Sets the binning factor for the Y axis. Also returns the current value..
	 * @param deviceId Device identifier.
	 * @param value The binning factor for the Y axis.
	 * @throws RTException In error case.
	 */
	public void camSetBinY(String deviceId, int value) throws RTException;
	
	/**
	 * Returns true if the camera cooler is on.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value
	 * @throws RTException In error case.
	 */
	public boolean camIsCoolerOn(String deviceId) throws RTException;
	
	/**
	 * Turns on and off the camera cooler.
	 * 
	 * @param deviceId Device identifier
	 * @param value new value
	 * @throws RTException In error case.
	 */
	public void camSetCoolerOn(String deviceId, boolean value) throws RTException;
	
	/**
	 * Returns the subframe width. If binning is active, value is in binned pixels.
	 * 
	 * @param deviceId Device identifier.
	 * @throws RTException In error case.
	 */
	public int camGetROINumX(String deviceId) throws RTException;
	
	/**
	 * Sets the subframe width. If binning is active, value is in binned pixels.
	 * 
	 * @param deviceId Device identifier
	 * @param value new value.
	 * @throws RTException In error case.
	 */
	public void camSetROINumX(String deviceId, int value) throws RTException;
	
	/**
	 *  Sets the subframe height. If binning is active, value is in binned pixels.
	 *  
	 * @param deviceId Device identifier
	 * @param value New value
	 * @throws RTException In error case.
	 */
	public void camSetROINumY(String deviceId, int value) throws RTException;
	
	/**
	 * Returns the subframe height. If binning is active, value is in binned pixels.
	 * @param deviceId Device identifier.
	 * @return subframe height.
	 * @throws RTException In error case.
	 */
	public int camGetROINumY(String deviceId) throws RTException;
	
	/**
	 * Sets the subframe start position for the X axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * @param deviceId Device identifier
	 * @param ROIStartX new value
	 * @throws RTException In error case.
	 */
	public void camSetROIStartX(String deviceId, int ROIStartX) throws RTException;
	
	/**
	 * Returns the subframe start position for the X axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * @param deviceId Device identifier.
	 * @return Subframe start position for the X axis
	 * @throws RTException In error case.
	 */
	public int camGetROIStartX(String deviceId) throws RTException;
	
	
	/**
	 *  Sets the subframe start position for the Y axis (0 based). If binning is active, value is in binned pixels.
	 *  
	 * @param deviceId Device identifier.
	 * @param value The subframe start position for the Y axis.
	 * @throws RTException In error case.
	 */
	public void camSetROIStartY(String deviceId, int value) throws RTException;
	
	/**
	 * Returns The subframe start position for the Y axis (0 based). If binning is active, value is in binned pixels.
	 * 
	 * @param deviceId Device identifier.
	 * @return The subframe start position for the Y axis.
	 * @throws RTException In error case.
	 */
	public int camGetROIStartY(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @param value brightness.
	 * @throws RTException In error case.
	 */
	public void camSetBrightness(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the value of image brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @return image brightness.
	 * @throws RTException In error case.
	 */
	public long camGetBrightness(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetContrast(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the image contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetContrast(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image gain.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetGain(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the image gain.
	 * 
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetGain(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image gamma correction.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetGamma(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the image gamma correction.
	 * 
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long camGetGamma(String deviceId) throws RTException;
	
	/**
	 * Sets the value in seconds for the image exposure time.
	 * @param deviceId Device identifier.
	 * @param value New value
	 * @throws RTException In error case.
	 */
	public void camSetExposureTime(String deviceId, double value) throws RTException;
	
	/**
	 * Returns  the value in seconds for the image exposure time.
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public Double camGetExposureTime(String deviceId) throws RTException;
	
	/**
	 * Sets the camera cooler setpoint in degrees Kelvin.
	 * @param deviceId Device identifier
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void camSetCCDTemperature(String deviceId, float value) throws RTException;
	
	/**
	 * Returns the camera cooler setpoint in degrees Kelvin
	 * @param deviceId Device identifier
	 * @return value
	 * @throws RTException In error case.
	 */
	public float camGetCCDTemperature(String deviceId) throws RTException;
	
	/**
	 * Returns the current CCD temperature in degrees Kelvin. Only valid if CanControlTemperature is True.
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public float camGetCCDCurrentTemperature(String deviceId) throws RTException;
	
	
	/**
	 * Aborts the current exposure, if any, and returns the camera to READY state.
	 * 
	 * @param deviceId Device identifier
	 * @throws RTException In error case.
	 */
	public void camAbortExposure(String deviceId) throws RTException;
	
	/**
	 * This method may return immediately after the move has started, in which case back-to-back dual axis pulse-guiding can be supported. Use the IsPulseGuiding property to detect when all moves have completed
	 * 
	 * @param deviceId Device identifier
	 * @param direction direction in which the guide-rate motion is to be made. The values for GuideDirections are: 0=guideNorth, 1=guideSouth, 2=guideEast, 3=guideWest.
	 * @param duration Duration of the guide-rate motion (milliseconds)
	 * @throws RTException In error case.
	 */
	public void camPulseGuide(String deviceId, int direction, long duration) throws RTException;
	
	/**
	 * Starts an exposure. Use ImageReady to check when the exposure is complete, and also use the AcquisitionMode and DigitizingMode values. This method uses ExposureTime variable.
	 * 
	 * @param deviceId Device identifier
	 * @param light Frame type (true: light frame, false: darkframe).
	 * @return String Image uid 
	 * @throws RTException In error case.
	 */
	public String camStartExposure(String deviceId, boolean light) throws RTException;
	
	/**
	 * Stops the current exposure, if any. If an exposure is in progress, the readout process is initiated. Ignored if readout is already in process.
	 * 
	 * @param deviceId Device identifier
	 * @throws RTException In error case.
	 */
	public void camStopExposure(String deviceId) throws RTException;
	

	/**
	 * Returns an image (raw data) from the last exposure. 
	 * The image could be an array of short, long, double.
	 * If the application cannot handle multispectral images, it should use just the first plane
	 * @param deviceId Device identifier.
	 * @param format Image format.
	 * @return Image data
	 * @throws RTException In error case.
	 */
	public Image camGetImage(String deviceId, ImageFormat format) throws RTException;
	
	/**
	 * If True, there is an image from the camera available. 
	 * If False, no image is available and attempts to use the ImageArray method will produce an exception.
	 * @param deviceId Device identifier
	 * @return boolean
	 * @throws RTException In error case.
	 */
	public boolean camImageReady (String deviceId) throws RTException;
	
	/**
	 * Gets the image data type (Short, Long, Double)
	 * @param deviceId Device identifier
	 * @return ImageContentType
	 * @throws RTException In error case
	 */
	public ImageContentType camGetImageDataType (String deviceId) throws RTException;
	
	
	/**
	 * Sets the image data type (Short, Long, Double)
	 * @param deviceid Device identifier
	 * @throws RTException In error case
	 */
	//public void camSetImageDataType (String deviceId, ImageContentType type) throws RTException;
	
	/**
	 * Gets the current CCD Bit Depth.
	 * 
	 * @param deviceId Device identifier
	 * @return Current CCD Bit Depth
	 * @throws RTException In error case
	 */
	public int camGetBitDepth (String deviceId) throws RTException;
	
	/**
	 * Sets the CCD Bit Depth.
	 * 
	 * @param deviceId Device identifier
	 * @param bits Bit Depth
	 * @throws RTException
	 */
	public void camSetBitDepth (String deviceId, int bits) throws RTException;
	
	/**
	 * Gets the quality in continue mode
	 * 
	 * @param deviceId Device identifier
	 * @return int Quality value 
	 * @throws RTException In error case
	 */
	public int camGetContinueModeQuality (String deviceId) throws RTException;
	
	/**
	 * Sets the quality in continue mode
	 * 
	 * @param deviceId Device identifier
	 * @param value Quality value (int)
	 * @throws RTException In error case
	 */
	public void camSetContinueModeQuality (String deviceId, int value) throws RTException;
	
	/**
	 * Gets the quality in oneShot mode
	 * 
	 * @param deviceId Device identifier
	 * @return int Quality value 
	 * @throws RTException In error case
	 */
	public int camGetOneShotModeQuality (String deviceId) throws RTException;
	
	/**
	 * Sets the quality in oneShot mode
	 * 
	 * @param deviceId Device identifier
	 * @param value Quality value (int)
	 * @throws RTException In error case
	 */
	public void camSetOneShotModeQuality (String deviceId, int value) throws RTException;
	
	/**
	 * Gets the image path where images taken in continue mode are stored.
	 * 
	 * @param deviceId Device identifier
	 * @return String The image path
	 * @throws RTException In error case
	 */
	public String camGetContinueModeImagePath (String deviceId) throws RTException;
	
	/**
	 * Gets the image path where images taken in oneShot mode are stored.
	 * 
	 * @param deviceId Device identifier
	 * @return String The image path
	 * @throws RTException In error case
	 */
	public String camGetOneShotModeImagePath (String deviceId) throws RTException;
	
	/**
	 * Gets is gain parameter is auto-adjusted
	 * 
	 * @param deviceId Device identifier
	 * @return True if gain parameter is auto-adjusted
	 * @throws RTException In error case
	 */
	public boolean camGetAutoGain (String deviceId) throws RTException;
	
	/**
	 * Sets the gain parameter as auto-adjusted or not.
	 * 
	 * @param deviceId Device identifier
	 * @param value True if gain parameter is auto-adjusted.
	 * @throws RTException In error case
	 */
	public void camSetAutoGain (String deviceId, boolean value) throws RTException;
	
	/**
	 * Gets is exposure time parameter is auto-adjusted
	 * 
	 * @param deviceId Device identifier
	 * @return True if exposure time parameter is auto-adjusted
	 * @throws RTException In error case
	 */
	public boolean camGetAutoExposureTime (String deviceId) throws RTException;
	
	/**
	 * Sets the exposure time parameter as auto-adjusted or not.
	 * 
	 * @param deviceId Device identifier
	 * @param value True if exposure time parameter is auto-adjusted.
	 * @throws RTException In error case
	 */
	public void camSetAutoExposureTime (String deviceId, boolean value) throws RTException;
	
	
	/**
	 * Starts the continue mode.
	 * 
	 * @param deviceId Device identifier
	 * @return String Image uid 
	 * @throws RTException In error case
	 */
	public String camStartContinueMode (String deviceId) throws RTException;
	
	/**
	 * Stops the continue mode.
	 * 
	 * @param deviceId Device identifier
	 * @throws RTException In error case
	 */
	public void camStopContinueMode (String deviceId) throws RTException;
	
	/**
	 * Returns the URL where the image is stored.
	 * 
	 * @param deviceId Device identifier
	 * @param format Image Format.
	 * @return String URL
	 * @throws RTException In error case
	 */
	public String camGetImageURL (String deviceId, String uid,  ImageFormat format) throws RTException;
	
	/**
	 * Returns the supported Image formats for OneShot mode.
	 * @param deviceId Device identifier.
	 * @return Supported Image format list.
	 * @throws RTException In error case.
	 */
	public List<ImageFormat> camGetOneShotModeImageFormats(String deviceId) throws RTException;
	
	/**
	 * Returns the supported Image formats for Continue mode.
	 * @param deviceId Device identifier.
	 * @return Supported Image format list.
	 * @throws RTException In error case.
	 */
	public List<ImageFormat> camGetContinueModeImageFormats(String deviceId) throws RTException;

}
