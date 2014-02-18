
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceFilter complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceFilter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gloria.eu/rt/entity/device}device">
 *       &lt;sequence>
 *         &lt;element name="activityState" type="{http://gloria.eu/rt/entity/device}activityStateFilter"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceFilter", propOrder = {
    "activityState"
})
public class DeviceFilter
    extends Device
{

    @XmlElement(required = true)
    protected ActivityStateFilter activityState;

    /**
     * Obtiene el valor de la propiedad activityState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateFilter }
     *     
     */
    public ActivityStateFilter getActivityState() {
        return activityState;
    }

    /**
     * Define el valor de la propiedad activityState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateFilter }
     *     
     */
    public void setActivityState(ActivityStateFilter value) {
        this.activityState = value;
    }

}
