
package eu.gloria.rt.entity.device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ImageContent complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ImageContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentType" type="{http://gloria.eu/rt/entity/device}imageContentType"/>
 *         &lt;element name="contentLong" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contentDouble" type="{http://www.w3.org/2001/XMLSchema}double" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contentShort" type="{http://www.w3.org/2001/XMLSchema}short" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageContent", propOrder = {
    "contentType",
    "contentLong",
    "contentDouble",
    "contentShort"
})
public class ImageContent {

    @XmlElement(required = true)
    protected ImageContentType contentType;
    @XmlElement(type = Long.class)
    protected List<Long> contentLong;
    @XmlElement(type = Double.class)
    protected List<Double> contentDouble;
    @XmlElement(type = Short.class)
    protected List<Short> contentShort;

    /**
     * Obtiene el valor de la propiedad contentType.
     * 
     * @return
     *     possible object is
     *     {@link ImageContentType }
     *     
     */
    public ImageContentType getContentType() {
        return contentType;
    }

    /**
     * Define el valor de la propiedad contentType.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageContentType }
     *     
     */
    public void setContentType(ImageContentType value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the contentLong property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentLong property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentLong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getContentLong() {
        if (contentLong == null) {
            contentLong = new ArrayList<Long>();
        }
        return this.contentLong;
    }

    /**
     * Gets the value of the contentDouble property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentDouble property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentDouble().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getContentDouble() {
        if (contentDouble == null) {
            contentDouble = new ArrayList<Double>();
        }
        return this.contentDouble;
    }

    /**
     * Gets the value of the contentShort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentShort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentShort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Short }
     * 
     * 
     */
    public List<Short> getContentShort() {
        if (contentShort == null) {
            contentShort = new ArrayList<Short>();
        }
        return this.contentShort;
    }

}
