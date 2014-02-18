
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para sensorStateIntervalLong complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="sensorStateIntervalLong">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beginValue" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="beginClosed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="endValue" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endClosed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="alarm" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="alarmName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorStateIntervalLong", propOrder = {
    "beginValue",
    "beginClosed",
    "endValue",
    "endClosed",
    "alarm",
    "alarmName"
})
public class SensorStateIntervalLong {

    protected long beginValue;
    protected boolean beginClosed;
    protected long endValue;
    protected boolean endClosed;
    protected boolean alarm;
    @XmlElement(required = true)
    protected String alarmName;

    /**
     * Obtiene el valor de la propiedad beginValue.
     * 
     */
    public long getBeginValue() {
        return beginValue;
    }

    /**
     * Define el valor de la propiedad beginValue.
     * 
     */
    public void setBeginValue(long value) {
        this.beginValue = value;
    }

    /**
     * Obtiene el valor de la propiedad beginClosed.
     * 
     */
    public boolean isBeginClosed() {
        return beginClosed;
    }

    /**
     * Define el valor de la propiedad beginClosed.
     * 
     */
    public void setBeginClosed(boolean value) {
        this.beginClosed = value;
    }

    /**
     * Obtiene el valor de la propiedad endValue.
     * 
     */
    public long getEndValue() {
        return endValue;
    }

    /**
     * Define el valor de la propiedad endValue.
     * 
     */
    public void setEndValue(long value) {
        this.endValue = value;
    }

    /**
     * Obtiene el valor de la propiedad endClosed.
     * 
     */
    public boolean isEndClosed() {
        return endClosed;
    }

    /**
     * Define el valor de la propiedad endClosed.
     * 
     */
    public void setEndClosed(boolean value) {
        this.endClosed = value;
    }

    /**
     * Obtiene el valor de la propiedad alarm.
     * 
     */
    public boolean isAlarm() {
        return alarm;
    }

    /**
     * Define el valor de la propiedad alarm.
     * 
     */
    public void setAlarm(boolean value) {
        this.alarm = value;
    }

    /**
     * Obtiene el valor de la propiedad alarmName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmName() {
        return alarmName;
    }

    /**
     * Define el valor de la propiedad alarmName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmName(String value) {
        this.alarmName = value;
    }

}
