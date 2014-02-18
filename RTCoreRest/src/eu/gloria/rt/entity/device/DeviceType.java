
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deviceType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="deviceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNKNOWN"/>
 *     &lt;enumeration value="SERV_SERVERD"/>
 *     &lt;enumeration value="MOUNT"/>
 *     &lt;enumeration value="CCD"/>
 *     &lt;enumeration value="DOME"/>
 *     &lt;enumeration value="GENERIC_WEATHER_SENSOR"/>
 *     &lt;enumeration value="ROTATOR"/>
 *     &lt;enumeration value="PHOTOMETER"/>
 *     &lt;enumeration value="SERV_PLAN"/>
 *     &lt;enumeration value="SERV_GRB"/>
 *     &lt;enumeration value="FOCUS"/>
 *     &lt;enumeration value="MIRROR"/>
 *     &lt;enumeration value="CUPOLA"/>
 *     &lt;enumeration value="FW"/>
 *     &lt;enumeration value="SERV_AUGERSH"/>
 *     &lt;enumeration value="GENERIC_SENSOR"/>
 *     &lt;enumeration value="SERV_EXECUTOR"/>
 *     &lt;enumeration value="SERV_IMGPROC"/>
 *     &lt;enumeration value="SERV_SELECTOR"/>
 *     &lt;enumeration value="SERV_XMLRPC"/>
 *     &lt;enumeration value="SERV_INDI"/>
 *     &lt;enumeration value="SERV_LOGD"/>
 *     &lt;enumeration value="SERV_SCRIPTOR"/>
 *     &lt;enumeration value="SURVEILLANCE_CAMERA"/>
 *     &lt;enumeration value="CLOUD_DETECTOR"/>
 *     &lt;enumeration value="WIND_SPEED_SENSOR"/>
 *     &lt;enumeration value="RAIN_SENSOR"/>
 *     &lt;enumeration value="STORM_SENSOR"/>
 *     &lt;enumeration value="RH_SENSOR"/>
 *     &lt;enumeration value="WEATHER_VANE"/>
 *     &lt;enumeration value="DEW_REMOVER"/>
 *     &lt;enumeration value="TEMPERATURE_SENSOR"/>
 *     &lt;enumeration value="BAROMETER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "deviceType")
@XmlEnum
public enum DeviceType {

    UNKNOWN,
    SERV_SERVERD,
    MOUNT,
    CCD,
    DOME,
    GENERIC_WEATHER_SENSOR,
    ROTATOR,
    PHOTOMETER,
    SERV_PLAN,
    SERV_GRB,
    FOCUS,
    MIRROR,
    CUPOLA,
    FW,
    SERV_AUGERSH,
    GENERIC_SENSOR,
    SERV_EXECUTOR,
    SERV_IMGPROC,
    SERV_SELECTOR,
    SERV_XMLRPC,
    SERV_INDI,
    SERV_LOGD,
    SERV_SCRIPTOR,
    SURVEILLANCE_CAMERA,
    CLOUD_DETECTOR,
    WIND_SPEED_SENSOR,
    RAIN_SENSOR,
    STORM_SENSOR,
    RH_SENSOR,
    WEATHER_VANE,
    DEW_REMOVER, TEMPERATURE_SENSOR, BAROMETER;

    public String value() {
        return name();
    }

    public static DeviceType fromValue(String v) {
        return valueOf(v);
    }

}
