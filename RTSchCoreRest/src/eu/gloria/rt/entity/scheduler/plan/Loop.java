//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.01.23 a las 12:23:14 PM CET 
//


package eu.gloria.rt.entity.scheduler.plan;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para loop complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="loop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="repeatDuration" type="{http://gloria.eu/rt/entity/scheduler/plan}seconds"/>
 *           &lt;element name="repeatCount" type="{http://gloria.eu/rt/entity/scheduler/plan}positiveInteger"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="target" type="{http://gloria.eu/rt/entity/scheduler/plan}target"/>
 *           &lt;element name="cameraSettings" type="{http://gloria.eu/rt/entity/scheduler/plan}cameraSettings"/>
 *           &lt;element name="loop" type="{http://gloria.eu/rt/entity/scheduler/plan}loop"/>
 *           &lt;element name="expose" type="{http://gloria.eu/rt/entity/scheduler/plan}expose"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loop", propOrder = {
    "repeatDuration",
    "repeatCount",
    "targetOrCameraSettingsOrLoop"
})
public class Loop {

    @XmlElement(defaultValue = "0")
    protected Double repeatDuration;
    @XmlElement(defaultValue = "1")
    protected BigInteger repeatCount;
    @XmlElements({
        @XmlElement(name = "target", type = Target.class),
        @XmlElement(name = "cameraSettings", type = CameraSettings.class),
        @XmlElement(name = "loop", type = Loop.class),
        @XmlElement(name = "expose", type = Expose.class)
    })
    protected List<Object> targetOrCameraSettingsOrLoop;

    /**
     * Obtiene el valor de la propiedad repeatDuration.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRepeatDuration() {
        return repeatDuration;
    }

    /**
     * Define el valor de la propiedad repeatDuration.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRepeatDuration(Double value) {
        this.repeatDuration = value;
    }

    /**
     * Obtiene el valor de la propiedad repeatCount.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRepeatCount() {
        return repeatCount;
    }

    /**
     * Define el valor de la propiedad repeatCount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRepeatCount(BigInteger value) {
        this.repeatCount = value;
    }

    /**
     * Gets the value of the targetOrCameraSettingsOrLoop property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetOrCameraSettingsOrLoop property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetOrCameraSettingsOrLoop().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Target }
     * {@link CameraSettings }
     * {@link Loop }
     * {@link Expose }
     * 
     * 
     */
    public List<Object> getTargetOrCameraSettingsOrLoop() {
        if (targetOrCameraSettingsOrLoop == null) {
            targetOrCameraSettingsOrLoop = new ArrayList<Object>();
        }
        return this.targetOrCameraSettingsOrLoop;
    }

}
