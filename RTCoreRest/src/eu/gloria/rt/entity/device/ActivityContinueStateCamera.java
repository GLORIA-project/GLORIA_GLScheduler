
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para activityContinueStateCamera.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="activityContinueStateCamera">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_DEFINED"/>
 *     &lt;enumeration value="OFF"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="MAITENANCE"/>
 *     &lt;enumeration value="BUSY"/>
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="EXPOSING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "activityContinueStateCamera")
@XmlEnum
public enum ActivityContinueStateCamera {

    NOT_DEFINED, OFF,
    ERROR,
    MAITENANCE,
    BUSY,
    READY,
    EXPOSING;

    public String value() {
        return name();
    }

    public static ActivityContinueStateCamera fromValue(String v) {
        return valueOf(v);
    }

}
