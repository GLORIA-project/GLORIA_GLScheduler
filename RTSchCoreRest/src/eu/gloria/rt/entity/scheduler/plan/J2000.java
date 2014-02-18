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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para J2000 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="J2000">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DEC" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "J2000", propOrder = {
    "ra",
    "dec"
})
public class J2000 {

    @XmlElement(name = "RA")
    protected double ra;
    @XmlElement(name = "DEC")
    protected double dec;

    /**
     * Obtiene el valor de la propiedad ra.
     * 
     */
    public double getRA() {
        return ra;
    }

    /**
     * Define el valor de la propiedad ra.
     * 
     */
    public void setRA(double value) {
        this.ra = value;
    }

    /**
     * Obtiene el valor de la propiedad dec.
     * 
     */
    public double getDEC() {
        return dec;
    }

    /**
     * Define el valor de la propiedad dec.
     * 
     */
    public void setDEC(double value) {
        this.dec = value;
    }

}
