
package eu.gloria.rt.entity.device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceDome complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceDome">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gloria.eu/rt/entity/device}device">
 *       &lt;sequence>
 *         &lt;element name="synch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="activityState" type="{http://gloria.eu/rt/entity/device}activityStateDome"/>
 *         &lt;element name="activityStateOpening" type="{http://gloria.eu/rt/entity/device}activityStateDomeOpening"/>
 *         &lt;element name="activityStateOpeningParts" type="{http://gloria.eu/rt/entity/device}activityStateDomeOpening" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceDome", propOrder = {
    "synch",
    "activityState",
    "activityStateOpening",
    "activityStateOpeningParts"
})
public class DeviceDome
    extends Device
{

    protected boolean synch;
    @XmlElement(required = true)
    protected ActivityStateDome activityState;
    @XmlElement(required = true)
    protected ActivityStateDomeOpening activityStateOpening;
    protected List<ActivityStateDomeOpening> activityStateOpeningParts;

    /**
     * Obtiene el valor de la propiedad synch.
     * 
     */
    public boolean isSynch() {
        return synch;
    }

    /**
     * Define el valor de la propiedad synch.
     * 
     */
    public void setSynch(boolean value) {
        this.synch = value;
    }

    /**
     * Obtiene el valor de la propiedad activityState.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateDome }
     *     
     */
    public ActivityStateDome getActivityState() {
        return activityState;
    }

    /**
     * Define el valor de la propiedad activityState.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateDome }
     *     
     */
    public void setActivityState(ActivityStateDome value) {
        this.activityState = value;
    }

    /**
     * Obtiene el valor de la propiedad activityStateOpening.
     * 
     * @return
     *     possible object is
     *     {@link ActivityStateDomeOpening }
     *     
     */
    public ActivityStateDomeOpening getActivityStateOpening() {
        return activityStateOpening;
    }

    /**
     * Define el valor de la propiedad activityStateOpening.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityStateDomeOpening }
     *     
     */
    public void setActivityStateOpening(ActivityStateDomeOpening value) {
        this.activityStateOpening = value;
    }

    /**
     * Gets the value of the activityStateOpeningParts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activityStateOpeningParts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivityStateOpeningParts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivityStateDomeOpening }
     * 
     * 
     */
    public List<ActivityStateDomeOpening> getActivityStateOpeningParts() {
        if (activityStateOpeningParts == null) {
            activityStateOpeningParts = new ArrayList<ActivityStateDomeOpening>();
        }
        return this.activityStateOpeningParts;
    }

}
