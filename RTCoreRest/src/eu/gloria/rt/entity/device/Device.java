
package eu.gloria.rt.entity.device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para device complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="device">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="configuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://gloria.eu/rt/entity/device}deviceType"/>
 *         &lt;element name="communicationState" type="{http://gloria.eu/rt/entity/device}communicationState"/>
 *         &lt;element name="blockState" type="{http://gloria.eu/rt/entity/device}blockState"/>
 *         &lt;element name="alarmState" type="{http://gloria.eu/rt/entity/device}alarmState"/>
 *         &lt;element name="error" type="{http://gloria.eu/rt/entity/device}error"/>
 *         &lt;element name="activityStateDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="properties" type="{http://gloria.eu/rt/entity/device}deviceProperty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="measureUnit" type="{http://gloria.eu/rt/entity/device}measureUnit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "device", propOrder = {
    "shortName",
    "version",
    "description",
    "info",
    "configuration",
    "type",
    "communicationState",
    "blockState",
    "alarmState",
    "error",
    "activityStateDesc",
    "properties",
    "measureUnit"
})
@XmlSeeAlso({
    DeviceFilter.class,
    DeviceMount.class,
    DeviceRotator.class,
    DeviceDome.class,
    DeviceFocuser.class,
    DeviceMirror.class,
    DeviceGeneral.class,
    DeviceCamera.class
})
public class Device {

    @XmlElement(required = true)
    protected String shortName;
    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String info;
    @XmlElement(required = true)
    protected String configuration;
    @XmlElement(required = true)
    protected DeviceType type;
    @XmlElement(required = true)
    protected CommunicationState communicationState;
    @XmlElement(required = true)
    protected BlockState blockState;
    @XmlElement(required = true)
    protected AlarmState alarmState;
    @XmlElement(required = true)
    protected Error error;
    @XmlElement(required = true)
    protected String activityStateDesc;
    protected List<DeviceProperty> properties;
    @XmlElement(required = true)
    protected MeasureUnit measureUnit;

    /**
     * Obtiene el valor de la propiedad shortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Define el valor de la propiedad shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     * Obtiene el valor de la propiedad info.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * Define el valor de la propiedad info.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

    /**
     * Obtiene el valor de la propiedad configuration.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * Define el valor de la propiedad configuration.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfiguration(String value) {
        this.configuration = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link DeviceType }
     *     
     */
    public DeviceType getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceType }
     *     
     */
    public void setType(DeviceType value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad communicationState.
     * 
     * @return
     *     possible object is
     *     {@link CommunicationState }
     *     
     */
    public CommunicationState getCommunicationState() {
        return communicationState;
    }

    /**
     * Define el valor de la propiedad communicationState.
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationState }
     *     
     */
    public void setCommunicationState(CommunicationState value) {
        this.communicationState = value;
    }

    /**
     * Obtiene el valor de la propiedad blockState.
     * 
     * @return
     *     possible object is
     *     {@link BlockState }
     *     
     */
    public BlockState getBlockState() {
        return blockState;
    }

    /**
     * Define el valor de la propiedad blockState.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockState }
     *     
     */
    public void setBlockState(BlockState value) {
        this.blockState = value;
    }

    /**
     * Obtiene el valor de la propiedad alarmState.
     * 
     * @return
     *     possible object is
     *     {@link AlarmState }
     *     
     */
    public AlarmState getAlarmState() {
        return alarmState;
    }

    /**
     * Define el valor de la propiedad alarmState.
     * 
     * @param value
     *     allowed object is
     *     {@link AlarmState }
     *     
     */
    public void setAlarmState(AlarmState value) {
        this.alarmState = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad activityStateDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityStateDesc() {
        return activityStateDesc;
    }

    /**
     * Define el valor de la propiedad activityStateDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityStateDesc(String value) {
        this.activityStateDesc = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeviceProperty }
     * 
     * 
     */
    public List<DeviceProperty> getProperties() {
        if (properties == null) {
            properties = new ArrayList<DeviceProperty>();
        }
        return this.properties;
    }

    /**
     * Obtiene el valor de la propiedad measureUnit.
     * 
     * @return
     *     possible object is
     *     {@link MeasureUnit }
     *     
     */
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    /**
     * Define el valor de la propiedad measureUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureUnit }
     *     
     */
    public void setMeasureUnit(MeasureUnit value) {
        this.measureUnit = value;
    }

}
