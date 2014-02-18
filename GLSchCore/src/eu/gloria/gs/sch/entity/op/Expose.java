//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.10 a las 10:54:01 AM CET 
//


package eu.gloria.gs.sch.entity.op;

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
 *         &lt;element name="exposureTimeInfo" type="{http://gloria.eu/gs/sch/entity/plan}exposureTimeInfo"/>
 *         &lt;choice>
 *           &lt;element name="repeatDuration" type="{http://gloria.eu/gs/sch/entity/plan}seconds"/>
 *           &lt;element name="repeatCount" type="{http://gloria.eu/gs/sch/entity/plan}positiveInteger"/>
 *         &lt;/choice>
 *         &lt;element name="filter" type="{http://gloria.eu/gs/sch/entity/plan}filterType"/>
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
    "exposureTimeInfo",
    "repeatDuration",
    "repeatCount",
    "filter"
})
public class Expose {

    @XmlElement(required = true)
    protected ExposureTimeInfo exposureTimeInfo;
    protected Double repeatDuration;
    protected BigInteger repeatCount;
    @XmlElement(required = true)
    protected FilterType filter;

    /**
     * Obtiene el valor de la propiedad exposureTimeInfo.
     * 
     * @return
     *     possible object is
     *     {@link ExposureTimeInfo }
     *     
     */
    public ExposureTimeInfo getExposureTimeInfo() {
        return exposureTimeInfo;
    }

    /**
     * Define el valor de la propiedad exposureTimeInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ExposureTimeInfo }
     *     
     */
    public void setExposureTimeInfo(ExposureTimeInfo value) {
        this.exposureTimeInfo = value;
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
