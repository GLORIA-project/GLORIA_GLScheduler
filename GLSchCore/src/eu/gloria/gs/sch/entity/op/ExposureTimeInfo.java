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
 * <p>Clase Java para exposureTimeInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="exposureTimeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="expositionTime" type="{http://gloria.eu/gs/sch/entity/plan}seconds"/>
 *         &lt;sequence>
 *           &lt;element name="magnitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *           &lt;element name="SNR" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exposureTimeInfo", propOrder = {
    "expositionTime",
    "magnitude",
    "snr"
})
public class ExposureTimeInfo {

    protected Double expositionTime;
    protected Double magnitude;
    @XmlElement(name = "SNR")
    protected Double snr;

    /**
     * Obtiene el valor de la propiedad expositionTime.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExpositionTime() {
        return expositionTime;
    }

    /**
     * Define el valor de la propiedad expositionTime.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExpositionTime(Double value) {
        this.expositionTime = value;
    }

    /**
     * Obtiene el valor de la propiedad magnitude.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMagnitude() {
        return magnitude;
    }

    /**
     * Define el valor de la propiedad magnitude.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMagnitude(Double value) {
        this.magnitude = value;
    }

    /**
     * Obtiene el valor de la propiedad snr.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSNR() {
        return snr;
    }

    /**
     * Define el valor de la propiedad snr.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSNR(Double value) {
        this.snr = value;
    }

}
