
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cameraType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="cameraType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AllSkyCamera"/>
 *     &lt;enumeration value="DeepSkyCamera"/>
 *     &lt;enumeration value="PlanetaryCamera"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cameraType")
@XmlEnum
public enum CameraType {

    @XmlEnumValue("AllSkyCamera")
    ALL_SKY_CAMERA("AllSkyCamera"),
    @XmlEnumValue("DeepSkyCamera")
    DEEP_SKY_CAMERA("DeepSkyCamera"),
    @XmlEnumValue("PlanetaryCamera")
    PLANETARY_CAMERA("PlanetaryCamera");
    private final String value;

    CameraType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CameraType fromValue(String v) {
        for (CameraType c: CameraType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
