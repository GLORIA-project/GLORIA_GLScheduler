
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cameraAcquisitionMode.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="cameraAcquisitionMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Continuous"/>
 *     &lt;enumeration value="OneShot"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cameraAcquisitionMode")
@XmlEnum
public enum CameraAcquisitionMode {

    @XmlEnumValue("Continuous")
    CONTINUOUS("Continuous"),
    @XmlEnumValue("OneShot")
    ONE_SHOT("OneShot");
    private final String value;

    CameraAcquisitionMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CameraAcquisitionMode fromValue(String v) {
        for (CameraAcquisitionMode c: CameraAcquisitionMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
