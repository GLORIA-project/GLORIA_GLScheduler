
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceMirror complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceMirror">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gloria.eu/rt/entity/device}device">
 *       &lt;sequence>
 *         &lt;element name="activityState" type="{http://gloria.eu/rt/entity/device}activityStateMirror"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceMirror", propOrder = {
    "activityState"
})
public class DeviceMirror
    extends Device
{

    @XmlElement(required = true)
    protected ActivityStateMirror activityState;

    /**
     * Obtiene el valor de la propiedad activityState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateMirror }
     *     
     */
    public ActivityStateMirror getActivityState() {
        return activityState;
    }

    /**
     * Define el valor de la propiedad activityState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateMirror }
     *     
     */
    public void setActivityState(ActivityStateMirror value) {
        this.activityState = value;
    }

}
