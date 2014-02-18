
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para activityStateDomeOpening.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="activityStateDomeOpening">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_DEFINED"/>
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="BUSY"/>
 *     &lt;enumeration value="OFF"/>
 *     &lt;enumeration value="MAINTENANCE"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="OPEN"/>
 *     &lt;enumeration value="CLOSE"/>
 *     &lt;enumeration value="OPENING"/>
 *     &lt;enumeration value="CLOSING"/>
 *     &lt;enumeration value="STOP"/>
 *     &lt;enumeration value="PARTS_COMPOSITION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "activityStateDomeOpening")
@XmlEnum
public enum ActivityStateDomeOpening {

    NOT_DEFINED, READY,
    BUSY,
    OFF,
    MAINTENANCE,
    ERROR,
    OPEN,
    CLOSE,
    OPENING,
    CLOSING,
    STOP,
    PARTS_COMPOSITION;

    public String value() {
        return name();
    }

    public static ActivityStateDomeOpening fromValue(String v) {
        return valueOf(v);
    }

}
