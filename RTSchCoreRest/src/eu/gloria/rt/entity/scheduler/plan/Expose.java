//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.01.23 a las 12:23:14 PM CET 
//


package eu.gloria.rt.entity.scheduler.plan;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para expose complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="expose">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expositionTime" type="{http://gloria.eu/rt/entity/scheduler/plan}seconds"/>
 *         &lt;choice>
 *           &lt;element name="repeatDuration" type="{http://gloria.eu/rt/entity/scheduler/plan}seconds"/>
 *           &lt;element name="repeatCount" type="{http://gloria.eu/rt/entity/scheduler/plan}positiveInteger"/>
 *         &lt;/choice>
 *         &lt;element name="filter" type="{http://gloria.eu/rt/entity/scheduler/plan}filterType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expose", propOrder = {
    "expositionTime",
    "repeatDuration",
    "repeatCount",
    "filter"
})
public class Expose {

    protected double expositionTime;
    protected Double repeatDuration;
    protected BigInteger repeatCount;
    @XmlElement(required = true)
    protected FilterType filter;

    /**
     * Obtiene el valor de la propiedad expositionTime.
     * 
     */
    public double getExpositionTime() {
        return expositionTime;
    }

    /**
     * Define el valor de la propiedad expositionTime.
     * 
     */
    public void setExpositionTime(double value) {
        this.expositionTime = value;
    }

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
     * Obtiene el valor de la propiedad filter.
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * Define el valor de la propiedad filter.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setFilter(FilterType value) {
        this.filter = value;
    }

}
