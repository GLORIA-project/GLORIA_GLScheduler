
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para devicePropertyComplexType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="devicePropertyComplexType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MMAX"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="RADEC"/>
 *     &lt;enumeration value="ALTAZ"/>
 *     &lt;enumeration value="STAT"/>
 *     &lt;enumeration value="RECTANGLE"/>
 *     &lt;enumeration value="ARRAY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "devicePropertyComplexType")
@XmlEnum
public enum DevicePropertyComplexType {

    MMAX,
    NONE,
    RADEC,
    ALTAZ,
    STAT,
    RECTANGLE,
    ARRAY;

    public String value() {
        return name();
    }

    public static DevicePropertyComplexType fromValue(String v) {
        return valueOf(v);
    }

}
