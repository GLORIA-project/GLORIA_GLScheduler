
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para activityStateMount.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="activityStateMount">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_DEFINED"/>
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="BUSY"/>
 *     &lt;enumeration value="OFF"/>
 *     &lt;enumeration value="MAINTENANCE"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="PARKED"/>
 *     &lt;enumeration value="PARKING"/>
 *     &lt;enumeration value="MOVING"/>
 *     &lt;enumeration value="STOP"/>
 *     &lt;enumeration value="TRACKING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "activityStateMount")
@XmlEnum
public enum ActivityStateMount {

    NOT_DEFINED, READY,
    BUSY,
    OFF,
    MAINTENANCE,
    ERROR,
    PARKED,
    PARKING,
    MOVING,
    STOP,
    TRACKING;

    public String value() {
        return name();
    }

    public static ActivityStateMount fromValue(String v) {
        return valueOf(v);
    }

}
