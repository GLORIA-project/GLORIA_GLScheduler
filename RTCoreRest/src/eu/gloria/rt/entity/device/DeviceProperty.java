
package eu.gloria.rt.entity.device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceProperty complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deviceProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basicType" type="{http://gloria.eu/rt/entity/device}devicePropertyBasicType"/>
 *         &lt;element name="complexType" type="{http://gloria.eu/rt/entity/device}devicePropertyComplexType"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="readonly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="possibleValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="minmax" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceProperty", propOrder = {
    "name",
    "basicType",
    "complexType",
    "description",
    "mandatory",
    "defaultValue",
    "readonly",
    "value",
    "possibleValue",
    "minmax"
})
public class DeviceProperty {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected DevicePropertyBasicType basicType;
    @XmlElement(required = true)
    protected DevicePropertyComplexType complexType;
    @XmlElement(required = true)
    protected String description;
    protected boolean mandatory;
    @XmlElement(required = true)
    protected String defaultValue;
    protected boolean readonly;
    protected List<String> value;
    protected List<String> possibleValue;
    protected List<String> minmax;

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad basicType.
     * 
     * @return
     *     possible object is
     *     {@link DevicePropertyBasicType }
     *     
     */
    public DevicePropertyBasicType getBasicType() {
        return basicType;
    }

    /**
     * Define el valor de la propiedad basicType.
     * 
     * @param value
     *     allowed object is
     *     {@link DevicePropertyBasicType }
     *     
     */
    public void setBasicType(DevicePropertyBasicType value) {
        this.basicType = value;
    }

    /**
     * Obtiene el valor de la propiedad complexType.
     * 
     * @return
     *     possible object is
     *     {@link DevicePropertyComplexType }
     *     
     */
    public DevicePropertyComplexType getComplexType() {
        return complexType;
    }

    /**
     * Define el valor de la propiedad complexType.
     * 
     * @param value
     *     allowed object is
     *     {@link DevicePropertyComplexType }
     *     
     */
    public void setComplexType(DevicePropertyComplexType value) {
        this.complexType = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad mandatory.
     * 
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Define el valor de la propiedad mandatory.
     * 
     */
    public void setMandatory(boolean value) {
        this.mandatory = value;
    }

    /**
     * Obtiene el valor de la propiedad defaultValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Define el valor de la propiedad defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Obtiene el valor de la propiedad readonly.
     * 
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * Define el valor de la propiedad readonly.
     * 
     */
    public void setReadonly(boolean value) {
        this.readonly = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValue() {
        if (value == null) {
            value = new ArrayList<String>();
        }
        return this.value;
    }

    /**
     * Gets the value of the possibleValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the possibleValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPossibleValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPossibleValue() {
        if (possibleValue == null) {
            possibleValue = new ArrayList<String>();
        }
        return this.possibleValue;
    }

    /**
     * Gets the value of the minmax property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the minmax property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinmax().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMinmax() {
        if (minmax == null) {
            minmax = new ArrayList<String>();
        }
        return this.minmax;
    }

}
