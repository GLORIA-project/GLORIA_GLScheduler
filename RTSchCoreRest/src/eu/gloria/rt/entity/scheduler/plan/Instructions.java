//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.01.23 a las 12:23:14 PM CET 
//


package eu.gloria.rt.entity.scheduler.plan;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para instructions complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="instructions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="target" type="{http://gloria.eu/rt/entity/scheduler/plan}target"/>
 *         &lt;element name="cameraSettings" type="{http://gloria.eu/rt/entity/scheduler/plan}cameraSettings"/>
 *         &lt;element name="loop" type="{http://gloria.eu/rt/entity/scheduler/plan}loop" minOccurs="0"/>
 *         &lt;element name="expose" type="{http://gloria.eu/rt/entity/scheduler/plan}expose" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instructions", propOrder = {
    "target",
    "cameraSettings",
    "loop",
    "expose"
})
public class Instructions {

    @XmlElement(required = true)
    protected Target target;
    @XmlElement(required = true)
    protected CameraSettings cameraSettings;
    protected Loop loop;
    @XmlElement(required = true)
    protected List<Expose> expose;
    
   //mclopez custom
    public List<Object> getTargetOrCameraSettingsOrLoop() {
    	
    	List<Object> list = new ArrayList <Object> ();
		
		list.add(cameraSettings);
		list.add(loop);
		list.add(target);
		
		for (Expose ex: expose)
			list.add(ex);
		
		return list;
    }

    /**
     * Obtiene el valor de la propiedad target.
     * 
     * @return
     *     possible object is
     *     {@link Target }
     *     
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Define el valor de la propiedad target.
     * 
     * @param value
     *     allowed object is
     *     {@link Target }
     *     
     */
    public void setTarget(Target value) {
        this.target = value;
    }

    /**
     * Obtiene el valor de la propiedad cameraSettings.
     * 
     * @return
     *     possible object is
     *     {@link CameraSettings }
     *     
     */
    public CameraSettings getCameraSettings() {
        return cameraSettings;
    }

    /**
     * Define el valor de la propiedad cameraSettings.
     * 
     * @param value
     *     allowed object is
     *     {@link CameraSettings }
     *     
     */
    public void setCameraSettings(CameraSettings value) {
        this.cameraSettings = value;
    }

    /**
     * Obtiene el valor de la propiedad loop.
     * 
     * @return
     *     possible object is
     *     {@link Loop }
     *     
     */
    public Loop getLoop() {
        return loop;
    }

    /**
     * Define el valor de la propiedad loop.
     * 
     * @param value
     *     allowed object is
     *     {@link Loop }
     *     
     */
    public void setLoop(Loop value) {
        this.loop = value;
    }

    /**
     * Gets the value of the expose property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expose property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpose().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Expose }
     * 
     * 
     */
    public List<Expose> getExpose() {
        if (expose == null) {
            expose = new ArrayList<Expose>();
        }
        return this.expose;
    }

}
