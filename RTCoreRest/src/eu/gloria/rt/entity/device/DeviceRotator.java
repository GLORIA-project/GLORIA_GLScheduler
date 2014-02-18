
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceRotator complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceRotator">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gloria.eu/rt/entity/device}device">
 *       &lt;sequence>
 *         &lt;element name="activityState" type="{http://gloria.eu/rt/entity/device}activityStateRotator"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceRotator", propOrder = {
    "activityState"
})
public class DeviceRotator
    extends Device
{

    @XmlElement(required = true)
    protected ActivityStateRotator activityState;

    /**
     * Obtiene el valor de la propiedad activityState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateRotator }
     *     
     */
    public ActivityStateRotator getActivityState() {
        return activityState;
    }

    /**
     * Define el valor de la propiedad activityState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateRotator }
     *     
     */
    public void setActivityState(ActivityStateRotator value) {
        this.activityState = value;
    }

}
