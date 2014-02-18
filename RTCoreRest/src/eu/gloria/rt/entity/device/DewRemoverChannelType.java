
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dewRemoverChannelType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="dewRemoverChannelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TEMPERATURE_CONTROLLED"/>
 *     &lt;enumeration value="DUTY_CYCLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dewRemoverChannelType")
@XmlEnum
public enum DewRemoverChannelType {

    TEMPERATURE_CONTROLLED,
    DUTY_CYCLE;

    public String value() {
        return name();
    }

    public static DewRemoverChannelType fromValue(String v) {
        return valueOf(v);
    }

}
