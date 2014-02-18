
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para imageContentType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="imageContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SHORT"/>
 *     &lt;enumeration value="LONG"/>
 *     &lt;enumeration value="DOUBLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "imageContentType")
@XmlEnum
public enum ImageContentType {

    SHORT,
    LONG,
    DOUBLE;

    public String value() {
        return name();
    }

    public static ImageContentType fromValue(String v) {
        return valueOf(v);
    }

}
