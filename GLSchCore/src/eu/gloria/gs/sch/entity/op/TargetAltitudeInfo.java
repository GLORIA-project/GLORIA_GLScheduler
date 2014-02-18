//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.10 a las 10:54:01 AM CET 
//


package eu.gloria.gs.sch.entity.op;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para targetAltitudeInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="targetAltitudeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="targetAltitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="airmass" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "targetAltitudeInfo", propOrder = {
    "targetAltitude",
    "airmass"
})
public class TargetAltitudeInfo {

    protected Double targetAltitude;
    protected Double airmass;

    /**
     * Obtiene el valor de la propiedad targetAltitude.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTargetAltitude() {
        return targetAltitude;
    }

    /**
     * Define el valor de la propiedad targetAltitude.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTargetAltitude(Double value) {
        this.targetAltitude = value;
    }

    /**
     * Obtiene el valor de la propiedad airmass.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAirmass() {
        return airmass;
    }

    /**
     * Define el valor de la propiedad airmass.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAirmass(Double value) {
        this.airmass = value;
    }

}
