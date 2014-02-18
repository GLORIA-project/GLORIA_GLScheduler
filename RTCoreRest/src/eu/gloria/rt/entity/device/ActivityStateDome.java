
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para activityStateDome.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="activityStateDome">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_DEFINED"/>
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="BUSY"/>
 *     &lt;enumeration value="OFF"/>
 *     &lt;enumeration value="MAINTENANCE"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="STOP"/>
 *     &lt;enumeration value="MOVING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "activityStateDome")
@XmlEnum
public enum ActivityStateDome {

    NOT_DEFINED, READY,
    BUSY,
    OFF,
    MAINTENANCE,
    ERROR,
    STOP,
    MOVING;

    public String value() {
        return name();
    }

    public static ActivityStateDome fromValue(String v) {
        return valueOf(v);
    }

}
