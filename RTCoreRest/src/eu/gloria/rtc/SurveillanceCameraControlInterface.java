package eu.gloria.rtc;

import eu.gloria.rt.entity.device.CameraAcquisitionMode;
import eu.gloria.rt.entity.device.CameraDigitizingMode;
import eu.gloria.rt.entity.device.Image;
import eu.gloria.rt.exception.RTException;


/**
* This is the interface that controls a surveillance camera device.
 * 
 *     
 * @author jcabello
 *
 */
public interface SurveillanceCameraControlInterface extends DeviceManagerInterface {
	
	
	/**
	 * If True, the camera's brightness can be adjusted. If False, the camera can not adjust the brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamHasBrightness(String deviceId) throws RTException;
	
	/**
	 * If True, the camera's contrast can be adjusted. If False, the camera can not adjust the contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamHasContrast(String deviceId) throws RTException;
	
	/**
	 * If it is Continuous mode, the camera will take a video streaming. If it is OneShot mode, only one image will be taken.
	 * 
	 * @param deviceId Device identifier.
	 * @return Continuous, OneShot.
	 * @throws RTException In error case.
	 */
	public CameraAcquisitionMode scamAcquisitionMode(String deviceId) throws RTException;
	
	/**
	 * Frames Per Second. It only takes effect in the “Continuous” Acquisition Mode.
	 * 
	 * @param deviceId Device identifier
	 * @return FPS
	 * @throws RTException In error case.
	 */
	public float scamGetFPS(String deviceId) throws RTException;
	
	/**
	 * If it is Automatic mode, the camera will take images according to the Brightness, Contrast,
	 * Gamma and Gain values calculated automatically by the camera itself. If it is Manual mode, the camera will
	 * take images according to the Brightness, Contrast, Gamma and Gain values established by the variables.
	 * 
	 * @param deviceId Device identifier.
	 * @return Automatic, Manual.
	 * @throws RTException In error case.
	 */
	public CameraDigitizingMode scamGetDigitizingMode(String deviceId) throws RTException;
	
	/**
	 * Returns if the camera supports Pan and Tilt.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsPTSupported(String deviceId) throws RTException;
	
	/**
	 * Returns if the camera supports Zoom.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsZoomSupported(String deviceId) throws RTException;
	
	/**
	 * Returns the minimum degrees for the Pan movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * @param deviceId Device identifier.
	 * @return The minimum degrees for the Pan movement.
	 * @throws RTException In error case.
	 */
	public int scamGetPanMin(String deviceId) throws RTException;
	
	/**
	 * Returns the maximum degrees for the Pan movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * @param deviceId Device identifier.
	 * @return The maximum degrees for the Pan movement.
	 * @throws RTException In error case.
	 */
	public int scamGetPanMax(String deviceId) throws RTException;
	
	/**
	 * Returns the minimum degrees for the Tilt movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * @param deviceId Device identifier
	 * @return The minimum degrees for the Tilt movement.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltMin(String deviceId) throws RTException;
	
	/**
	 * Returns the maximum degrees for the Tilt movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * @param deviceId Device identifier.
	 * @return The maximum degrees for the Tilt movement.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltMax(String deviceId) throws RTException;
	
	/**
	 * Gets the maximum zoom value allowed. This is the maximum value that applications can set.
	 * Applications should call ZoomSupported(bool) before using this property. Minimun zoom value is zero.
	 * 
	 * @param deviceId Device identifier.
	 * @return The maximum zoom value allowed.
	 * @throws RTException In error case.
	 */
	public int scamGetZoomMax(String deviceId) throws RTException;
	
	/**
	 * Gets the value in seconds for the image exposure time. 
	 * 
	 * @param deviceId Device identifier.
	 * @return The value in seconds for the image exposure time
	 * @throws RTException In error case.
	 */
	public double scamGetExposureTime(String deviceId) throws RTException;
	
	/**
	 * Sets the value in seconds for the image exposure time. 
	 * 
	 * @param deviceId Device identifier.
	 * @param value The value in seconds for the image exposure time
	 * @throws RTException In error case.
	 */
	public void scamSetExposureTime(String deviceId, double value) throws RTException;
	
	/**
	 * If True, there is an image from the camera available. If False, no image is available and attempts to use the ImageArray method will produce an exception.
	 * 
	 * @param deviceId Device identifier.
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsImageReady(String deviceId) throws RTException;
	
	/**
	 * URL for streaming.
	 * @param deviceId Device identifier.
	 * @return URL for streaming.
	 * @throws RTException In error case.
	 */
	public String scamGetVideoStreamingURL(String deviceId) throws RTException;
	
	/**
	 * Gets the image URL.
	 * @param deviceId Device identifier.
	 * @return URL for streaming.
	 * @throws RTException In error case.
	 */
	public String scamGetImageURL(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @param value brightness.
	 * @throws RTException In error case.
	 */
	public void scamSetBrightness(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the value of image brightness.
	 * 
	 * @param deviceId Device identifier.
	 * @return image brightness.
	 * @throws RTException In error case.
	 */
	public long scamGetBrightness(String deviceId) throws RTException;
	
	/**
	 * Sets the value for image contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetContrast(String deviceId, long value) throws RTException;
	
	/**
	 * Returns the image contrast.
	 * 
	 * @param deviceId Device identifier.
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long scamGetContrast(String deviceId) throws RTException;
	
	
	/**
	 * Returns the current pan rotation in degrees.
	 * @param deviceId Device identifier.
	 * @return The current pan rotation in degrees.
	 * @throws RTException In error case.
	 */
	public int scamGetPanRotation(String deviceId) throws RTException;
	
	/**
	 *  
	 * The pan will be moved until the desired position in degrees.
	 *  
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetPanRotation(String deviceId, int value) throws RTException;
	
	/**
	 * Return the current tilt rotation in degrees.
	 * 
	 * @param deviceId Device identifier.
	 * @return The current tilt rotation in degrees.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltRotation(String deviceId) throws RTException;
	
	/**
	 * The tilt will be moved until the desired position in degrees.
	 * 
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetTiltRotation(String deviceId, int value) throws RTException;
	
	/**
	 * Return the current zoom value.
	 * 
	 * @param deviceId Device identifier.
	 * @return The current zoom value
	 * @throws RTException In error case.
	 */
	public int scamGetZoom(String deviceId) throws RTException;
	
	/**
	 * The zoom will be moved until the desired position in degrees.
	 * @param deviceId Device identifier.
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetZoom(String deviceId, int value) throws RTException;
	
	/**
	 * Aborts the current exposure, if any, and returns the camera to READY state.
	 * 
	 * @param deviceId Device identifier
	 * @throws RTException In error case.
	 */
	public void scamAbortExposure(String deviceId) throws RTException;
	
	/**
	 * Starts an exposure. Use ImageReady to check when the exposure is complete, and also use the AcquisitionMode and DigitizingMode values. This method uses ExposureTime variable.
	 * 
	 * @param deviceId Device identifier
	 * @param light Frame type (true: light frame, false: darkframe).
	 * @throws RTException In error case.
	 */
	public void scamStartExposure(String deviceId, boolean light) throws RTException;
	
	/**
	 * Stops the current exposure, if any. If an exposure is in progress, the readout process is initiated. Ignored if readout is already in process.
	 * 
	 * @param deviceId Device identifier
	 * @param deviceId Device identifier
	 * @throws RTException In error case.
	 */
	public void scamStopExposure(String deviceId) throws RTException;
	
	/**
	 * Returns an image from the last exposure. 
	 * The image could be an array of short, long, double (imageContentType attribute shows the type).
	 * 
	 * @param deviceId Device identifier
	 * @return Image data
	 * @throws RTException In error case.
	 */
	public Image scamGetImage(String deviceId) throws RTException;
	
}
