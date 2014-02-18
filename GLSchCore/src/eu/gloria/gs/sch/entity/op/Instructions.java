//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.10 a las 10:54:01 AM CET 
//


package eu.gloria.gs.sch.entity.op;

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
 *         &lt;element name="target" type="{http://gloria.eu/gs/sch/entity/plan}target"/>
 *         &lt;element name="loop" type="{http://gloria.eu/gs/sch/entity/plan}loop" minOccurs="0"/>
 *         &lt;element name="expose" type="{http://gloria.eu/gs/sch/entity/plan}expose"/>
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
    "loop",
    "expose"
})
public class Instructions {

    @XmlElement(required = true)
    protected Target target;
    protected Loop loop;
    @XmlElement(required = true)
    protected Expose expose;

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
     * Obtiene el valor de la propiedad expose.
     * 
     * @return
     *     possible object is
     *     {@link Expose }
     *     
     */
    public Expose getExpose() {
        return expose;
    }

    /**
     * Define el valor de la propiedad expose.
     * 
     * @param value
     *     allowed object is
     *     {@link Expose }
     *     
     */
    public void setExpose(Expose value) {
        this.expose = value;
    }

}
