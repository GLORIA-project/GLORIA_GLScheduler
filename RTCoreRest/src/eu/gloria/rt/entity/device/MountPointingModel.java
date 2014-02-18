
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mountPointingModel.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="mountPointingModel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQU"/>
 *     &lt;enumeration value="ALT_AZ"/>
 *     &lt;enumeration value="ALT_ALT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "mountPointingModel")
@XmlEnum
public enum MountPointingModel {

    EQU,
    ALT_AZ,
    ALT_ALT;

    public String value() {
        return name();
    }

    public static MountPointingModel fromValue(String v) {
        return valueOf(v);
    }

}
