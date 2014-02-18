package eu.gloria.rtd;

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
public interface RTDSurveillanceCameraInterface extends RTDDeviceInterface {
	
	
	/**
	 * If True, the camera's brightness can be adjusted. If False, the camera can not adjust the brightness.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamHasBrightness() throws RTException;
	
	/**
	 * If True, the camera's contrast can be adjusted. If False, the camera can not adjust the contrast.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamHasContrast() throws RTException;
	
	/**
	 * If it is Continuous mode, the camera will take a video streaming. If it is OneShot mode, only one image will be taken.
	 * 
	 * 
	 * @return Continuous, OneShot.
	 * @throws RTException In error case.
	 */
	public CameraAcquisitionMode scamAcquisitionMode() throws RTException;
	
	/**
	 * Frames Per Second. It only takes effect in the “Continuous” Acquisition Mode.
	 * 
	 * 
	 * @return FPS
	 * @throws RTException In error case.
	 */
	public float scamGetFPS() throws RTException;
	
	/**
	 * If it is Automatic mode, the camera will take images according to the Brightness, Contrast,
	 * Gamma and Gain values calculated automatically by the camera itself. If it is Manual mode, the camera will
	 * take images according to the Brightness, Contrast, Gamma and Gain values established by the variables.
	 * 
	 * 
	 * @return Automatic, Manual.
	 * @throws RTException In error case.
	 */
	public CameraDigitizingMode scamGetDigitizingMode() throws RTException;
	
	/**
	 * Returns if the camera supports Pan and Tilt.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsPTSupported() throws RTException;
	
	/**
	 * Returns if the camera supports Zoom.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsZoomSupported() throws RTException;
	
	/**
	 * Returns the minimum degrees for the Pan movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * 
	 * @return The minimum degrees for the Pan movement.
	 * @throws RTException In error case.
	 */
	public int scamGetPanMin() throws RTException;
	
	/**
	 * Returns the maximum degrees for the Pan movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * 
	 * @return The maximum degrees for the Pan movement.
	 * @throws RTException In error case.
	 */
	public int scamGetPanMax() throws RTException;
	
	/**
	 * Returns the minimum degrees for the Tilt movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * 
	 * @return The minimum degrees for the Tilt movement.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltMin() throws RTException;
	
	/**
	 * Returns the maximum degrees for the Tilt movement. This property will be defined if the camera supports Pan and Tilt feature.
	 * 
	 * 
	 * @return The maximum degrees for the Tilt movement.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltMax() throws RTException;
	
	/**
	 * Gets the maximum zoom value allowed. This is the maximum value that applications can set.
	 * Applications should call ZoomSupported(bool) before using this property. Minimun zoom value is zero.
	 * 
	 * 
	 * @return The maximum zoom value allowed.
	 * @throws RTException In error case.
	 */
	public int scamGetZoomMax() throws RTException;
	
	/**
	 * Gets the value in seconds for the image exposure time. 
	 * 
	 * 
	 * @return The value in seconds for the image exposure time
	 * @throws RTException In error case.
	 */
	public double scamGetExposureTime() throws RTException;
	
	/**
	 * Sets the value in seconds for the image exposure time. 
	 * 
	 * 
	 * @param value The value in seconds for the image exposure time
	 * @throws RTException In error case.
	 */
	public void scamSetExposureTime( double value) throws RTException;
	
	/**
	 * If True, there is an image from the camera available. If False, no image is available and attempts to use the ImageArray method will produce an exception.
	 * 
	 * 
	 * @return Boolean value.
	 * @throws RTException In error case.
	 */
	public boolean scamIsImageReady() throws RTException;
	
	/**
	 * URL for streaming.
	 * 
	 * @return URL for streaming.
	 * @throws RTException In error case.
	 */
	public String scamGetVideoStreamingURL() throws RTException;
	
	/**
	 * Returns the URL where the image is stored.
	 * 
	 * @return String URL
	 * @throws RTException In error case
	 */
	public String scamGetImageURL () throws RTException;
	
	/**
	 * Sets the value for image brightness.
	 * 
	 * 
	 * @param value brightness.
	 * @throws RTException In error case.
	 */
	public void scamSetBrightness( long value) throws RTException;
	
	/**
	 * Returns the value of image brightness.
	 * 
	 * 
	 * @return image brightness.
	 * @throws RTException In error case.
	 */
	public long scamGetBrightness() throws RTException;
	
	/**
	 * Sets the value for image contrast.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetContrast( long value) throws RTException;
	
	/**
	 * Returns the image contrast.
	 * 
	 * 
	 * @return Current value.
	 * @throws RTException In error case.
	 */
	public long scamGetContrast() throws RTException;
	
	
	/**
	 * Returns the current pan rotation in degrees.
	 * 
	 * @return The current pan rotation in degrees.
	 * @throws RTException In error case.
	 */
	public int scamGetPanRotation() throws RTException;
	
	/**
	 *  
	 * The pan will be moved until the desired position in degrees.
	 *  
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetPanRotation( int value) throws RTException;
	
	/**
	 * Return the current tilt rotation in degrees.
	 * 
	 * 
	 * @return The current tilt rotation in degrees.
	 * @throws RTException In error case.
	 */
	public int scamGetTiltRotation() throws RTException;
	
	/**
	 * The tilt will be moved until the desired position in degrees.
	 * 
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetTiltRotation( int value) throws RTException;
	
	/**
	 * Return the current zoom value.
	 * 
	 * 
	 * @return The current zoom value
	 * @throws RTException In error case.
	 */
	public int scamGetZoom() throws RTException;
	
	/**
	 * The zoom will be moved until the desired position in degrees.
	 * 
	 * @param value New value.
	 * @throws RTException In error case.
	 */
	public void scamSetZoom( int value) throws RTException;
	
	/**
	 * Aborts the current exposure, if any, and returns the camera to READY state.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void scamAbortExposure() throws RTException;
	
	/**
	 * Starts an exposure. Use ImageReady to check when the exposure is complete, and also use the AcquisitionMode and DigitizingMode values. This method uses ExposureTime variable.
	 * 
	 * 
	 * @param light Frame type (true: light frame, false: darkframe).
	 * @throws RTException In error case.
	 */
	public void scamStartExposure( boolean light) throws RTException;
	
	/**
	 * Stops the current exposure, if any. If an exposure is in progress, the readout process is initiated. Ignored if readout is already in process.
	 * 
	 * 
	 * @throws RTException In error case.
	 */
	public void scamStopExposure() throws RTException;
	
	/**
	 * Returns an image from the last exposure. 
	 * The image could be an array of short, long, double (imageContentType attribute shows the type).
	 * @return Image data
	 * @throws RTException In error case.
	 */
	public Image scamGetImage() throws RTException;
	
}
