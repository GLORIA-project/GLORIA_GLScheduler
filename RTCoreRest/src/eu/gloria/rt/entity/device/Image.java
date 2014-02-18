
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Image complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Image">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="format" type="{http://gloria.eu/rt/entity/device}imageFormat"/>
 *         &lt;element name="numX" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numY" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numPlanes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imageContent" type="{http://gloria.eu/rt/entity/device}ImageContent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Image", propOrder = {
    "format",
    "numX",
    "numY",
    "numPlanes",
    "imageContent"
})
public class Image {

    @XmlElement(required = true)
    protected ImageFormat format;
	protected int numX;
    protected int numY;
    protected int numPlanes;
    @XmlElement(required = true)
    protected ImageContent imageContent;

    /**
     * Obtiene el valor de la propiedad format.
     * 
     * @return
     *     possible object is
     *     {@link ImageFormat }
     *     
     */
    public ImageFormat getFormat() {
        return format;
    }

	/**
     * Define el valor de la propiedad format.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageFormat }
     *     
     */
    public void setFormat(ImageFormat value) {
        this.format = value;
    }

	/**
     * Obtiene el valor de la propiedad numX.
     * 
     */
    public int getNumX() {
        return numX;
    }

    /**
     * Define el valor de la propiedad numX.
     * 
     */
    public void setNumX(int value) {
        this.numX = value;
    }

    /**
     * Obtiene el valor de la propiedad numY.
     * 
     */
    public int getNumY() {
        return numY;
    }

    /**
     * Define el valor de la propiedad numY.
     * 
     */
    public void setNumY(int value) {
        this.numY = value;
    }

    /**
     * Obtiene el valor de la propiedad numPlanes.
     * 
     */
    public int getNumPlanes() {
        return numPlanes;
    }

    /**
     * Define el valor de la propiedad numPlanes.
     * 
     */
    public void setNumPlanes(int value) {
        this.numPlanes = value;
    }

    /**
     * Obtiene el valor de la propiedad imageContent.
     * 
     * @return
     *     possible object is
     *     {@link ImageContent }
     *     
     */
    public ImageContent getImageContent() {
        return imageContent;
    }

    /**
     * Define el valor de la propiedad imageContent.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageContent }
     *     
     */
    public void setImageContent(ImageContent value) {
        this.imageContent = value;
    }

}
