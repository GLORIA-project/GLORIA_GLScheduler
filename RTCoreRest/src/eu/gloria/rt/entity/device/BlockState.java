
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para blockState.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="blockState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNBLOCK"/>
 *     &lt;enumeration value="BLOCKED_EXPOSURE"/>
 *     &lt;enumeration value="BLOCKED_READOUT"/>
 *     &lt;enumeration value="BLOCKED_TEL_MOVE"/>
 *     &lt;enumeration value="BLOCKED_WILL_EXPOSURE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "blockState")
@XmlEnum
public enum BlockState {

    UNBLOCK,
    BLOCKED_EXPOSURE,
    BLOCKED_READOUT,
    BLOCKED_TEL_MOVE,
    BLOCKED_WILL_EXPOSURE;

    public String value() {
        return name();
    }

    public static BlockState fromValue(String v) {
        return valueOf(v);
    }

}
