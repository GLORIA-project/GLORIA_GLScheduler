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
 * <p>Clase Java para coordinates complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="coordinates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="J2000" type="{http://gloria.eu/rt/entity/scheduler/plan}J2000"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coordinates", propOrder = {
    "j2000"
})
public class Coordinates {

    @XmlElement(name = "J2000")
    protected J2000 j2000;

    /**
     * Obtiene el valor de la propiedad j2000.
     * 
     * @return
     *     possible object is
     *     {@link J2000 }
     *     
     */
    public J2000 getJ2000() {
        return j2000;
    }

    /**
     * Define el valor de la propiedad j2000.
     * 
     * @param value
     *     allowed object is
     *     {@link J2000 }
     *     
     */
    public void setJ2000(J2000 value) {
        this.j2000 = value;
    }

}
