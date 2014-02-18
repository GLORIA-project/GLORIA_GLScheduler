
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para imageFormat.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="imageFormat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FITS"/>
 *     &lt;enumeration value="JPG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "imageFormat")
@XmlEnum
public enum ImageFormat {

    FITS,
    JPG;

    public String value() {
        return name();
    }

    public static ImageFormat fromValue(String v) {
        return valueOf(v);
    }

}
