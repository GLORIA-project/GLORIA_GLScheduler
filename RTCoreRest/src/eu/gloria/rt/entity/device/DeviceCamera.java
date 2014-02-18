
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceCamera complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceCamera">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gloria.eu/rt/entity/device}device">
 *       &lt;sequence>
 *         &lt;element name="hasImage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="activityState" type="{http://gloria.eu/rt/entity/device}activityStateCamera"/>
 *         &lt;element name="activityContinueState" type="{http://gloria.eu/rt/entity/device}activityContinueStateCamera"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceCamera", propOrder = {
    "hasImage",
    "activityState",
    "activityContinueState"
})
public class DeviceCamera
    extends Device
{

    protected boolean hasImage;
    @XmlElement(required = true)
    protected ActivityStateCamera activityState;
    @XmlElement(required = true)
    protected ActivityContinueStateCamera activityContinueState;

    /**
     * Obtiene el valor de la propiedad hasImage.
     * 
     */
    public boolean isHasImage() {
        return hasImage;
    }

    /**
     * Define el valor de la propiedad hasImage.
     * 
     */
    public void setHasImage(boolean value) {
        this.hasImage = value;
    }

    /**
     * Obtiene el valor de la propiedad activityState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateCamera }
     *     
     */
    public ActivityStateCamera getActivityState() {
        return activityState;
    }

    /**
     * Define el valor de la propiedad activityState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateCamera }
     *     
     */
    public void setActivityState(ActivityStateCamera value) {
        this.activityState = value;
    }

    /**
     * Obtiene el valor de la propiedad activityContinueState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityContinueStateCamera }
     *     
     */
    public ActivityContinueStateCamera getActivityContinueState() {
        return activityContinueState;
    }

    /**
     * Define el valor de la propiedad activityContinueState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityContinueStateCamera }
     *     
     */
    public void setActivityContinueState(ActivityContinueStateCamera value) {
        this.activityContinueState = value;
    }

}
