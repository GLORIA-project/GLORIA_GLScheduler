//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.01.23 a las 12:23:14 PM CET 
//


package eu.gloria.rt.entity.scheduler.plan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadata" type="{http://gloria.eu/rt/entity/scheduler/plan}metadata" minOccurs="0"/>
 *         &lt;element name="constraints" type="{http://gloria.eu/rt/entity/scheduler/plan}constraints"/>
 *         &lt;element name="instructions" type="{http://gloria.eu/rt/entity/scheduler/plan}instructions"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadata",
    "constraints",
    "instructions"
})
@XmlRootElement(name = "plan")
public class Plan {

    protected Metadata metadata;
    @XmlElement(required = true)
    protected Constraints constraints;
    @XmlElement(required = true)
    protected Instructions instructions;

    /**
     * Obtiene el valor de la propiedad metadata.
     * 
     * @return
     *     possible object is
     *     {@link Metadata }
     *     
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Define el valor de la propiedad metadata.
     * 
     * @param value
     *     allowed object is
     *     {@link Metadata }
     *     
     */
    public void setMetadata(Metadata value) {
        this.metadata = value;
    }

    /**
     * Obtiene el valor de la propiedad constraints.
     * 
     * @return
     *     possible object is
     *     {@link Constraints }
     *     
     */
    public Constraints getConstraints() {
        return constraints;
    }

    /**
     * Define el valor de la propiedad constraints.
     * 
     * @param value
     *     allowed object is
     *     {@link Constraints }
     *     
     */
    public void setConstraints(Constraints value) {
        this.constraints = value;
    }

    /**
     * Obtiene el valor de la propiedad instructions.
     * 
     * @return
     *     possible object is
     *     {@link Instructions }
     *     
     */
    public Instructions getInstructions() {
        return instructions;
    }

    /**
     * Define el valor de la propiedad instructions.
     * 
     * @param value
     *     allowed object is
     *     {@link Instructions }
     *     
     */
    public void setInstructions(Instructions value) {
        this.instructions = value;
    }

}
