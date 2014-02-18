
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para alarmState.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="alarmState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="MALFUNCTION"/>
 *     &lt;enumeration value="DRIVER_HW"/>
 *     &lt;enumeration value="DRIVER_SW"/>
 *     &lt;enumeration value="SHUTDOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "alarmState")
@XmlEnum
public enum AlarmState {

    NONE,
    MALFUNCTION,
    DRIVER_HW,
    DRIVER_SW,
    SHUTDOWN;

    public String value() {
        return name();
    }

    public static AlarmState fromValue(String v) {
        return valueOf(v);
    }

}
