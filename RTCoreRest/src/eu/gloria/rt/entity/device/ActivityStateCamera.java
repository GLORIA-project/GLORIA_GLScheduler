
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para activityStateCamera.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="activityStateCamera">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_DEFINED"/>
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="BUSY"/>
 *     &lt;enumeration value="OFF"/>
 *     &lt;enumeration value="MAINTENANCE"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="WAITING"/>
 *     &lt;enumeration value="EXPOSING"/>
 *     &lt;enumeration value="READING"/>
 *     &lt;enumeration value="DOWNLOADING"/>
 *     &lt;enumeration value="COOLING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "activityStateCamera")
@XmlEnum
public enum ActivityStateCamera {

    NOT_DEFINED, READY,
    BUSY,
    OFF,
    MAINTENANCE,
    ERROR,
    WAITING,
    EXPOSING,
    READING,
    DOWNLOADING,
    COOLING;

    public String value() {
        return name();
    }

    public static ActivityStateCamera fromValue(String v) {
        return valueOf(v);
    }

}
