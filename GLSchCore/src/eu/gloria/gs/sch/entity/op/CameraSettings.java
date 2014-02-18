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
 * <p>Clase Java para cameraSettings complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cameraSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="binning" type="{http://gloria.eu/gs/sch/entity/plan}binning" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cameraSettings", propOrder = {
    "binning"
})
public class CameraSettings {

    protected Binning binning;

    /**
     * Obtiene el valor de la propiedad binning.
     * 
     * @return
     *     possible object is
     *     {@link Binning }
     *     
     */
    public Binning getBinning() {
        return binning;
    }

    /**
     * Define el valor de la propiedad binning.
     * 
     * @param value
     *     allowed object is
     *     {@link Binning }
     *     
     */
    public void setBinning(Binning value) {
        this.binning = value;
    }

}
