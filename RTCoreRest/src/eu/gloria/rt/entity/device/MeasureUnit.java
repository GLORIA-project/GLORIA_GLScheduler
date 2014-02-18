
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para measureUnit.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="measureUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="KM/H"/>
 *     &lt;enumeration value="CYCLE"/>
 *     &lt;enumeration value="INCH"/>
 *     &lt;enumeration value="MILLE"/>
 *     &lt;enumeration value="KM"/>
 *     &lt;enumeration value="PERCENT"/>
 *     &lt;enumeration value="DEGREE"/>
 *     &lt;enumeration value="OHM"/>
 *     &lt;enumeration value="A/V"/>
 *     &lt;enumeration value="KELVIN"/>
 *     &lt;enumeration value="CELSIUS"/>
 *     &lt;enumeration value="PASCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "measureUnit")
@XmlEnum
public enum MeasureUnit {

    NONE("NONE"),
    @XmlEnumValue("KM/H")
    KM_H("KM/H"),
    CYCLE("CYCLE"),
    INCH("INCH"),
    MILLE("MILLE"),
    KM("KM"),
    PERCENT("PERCENT"),
    DEGREE("DEGREE"),
    OHM("OHM"),
    @XmlEnumValue("A/V")
    A_V("A/V"), KELVIN("KELVIN"), CELSIUS("CELSIUS"), PASCAL("PASCAL");
    private final String value;

    MeasureUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasureUnit fromValue(String v) {
        for (MeasureUnit c: MeasureUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
