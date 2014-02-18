
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para devicePropertyBasicType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="devicePropertyBasicType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="INTEGER"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="DOUBLE"/>
 *     &lt;enumeration value="FLOAT"/>
 *     &lt;enumeration value="BOOL"/>
 *     &lt;enumeration value="SELECTION"/>
 *     &lt;enumeration value="LONGINT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "devicePropertyBasicType")
@XmlEnum
public enum DevicePropertyBasicType {

    STRING,
    INTEGER,
    TIME,
    DOUBLE,
    FLOAT,
    BOOL,
    SELECTION,
    LONGINT;

    public String value() {
        return name();
    }

    public static DevicePropertyBasicType fromValue(String v) {
        return valueOf(v);
    }

}
