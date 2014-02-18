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
 *         &lt;element name="target" type="{http://gloria.eu/gs/sch/entity/plan}target"/>
 *         &lt;element name="filter" type="{http://gloria.eu/gs/sch/entity/plan}filterType"/>
 *         &lt;element name="exposureInfo" type="{http://gloria.eu/gs/sch/entity/plan}exposureTimeInfo"/>
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
    "target",
    "filter",
    "exposureInfo"
})
@XmlRootElement(name = "simpleExposure")
public class SimpleExposure {

    @XmlElement(required = true)
    protected Target target;
    @XmlElement(required = true)
    protected FilterType filter;
    @XmlElement(required = true)
    protected ExposureTimeInfo exposureInfo;

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

    /**
     * Obtiene el valor de la propiedad exposureInfo.
     * 
     * @return
     *     possible object is
     *     {@link ExposureTimeInfo }
     *     
     */
    public ExposureTimeInfo getExposureInfo() {
        return exposureInfo;
    }

    /**
     * Define el valor de la propiedad exposureInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ExposureTimeInfo }
     *     
     */
    public void setExposureInfo(ExposureTimeInfo value) {
        this.exposureInfo = value;
    }

}
