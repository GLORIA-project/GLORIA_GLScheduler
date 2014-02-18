
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para communicationState.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="communicationState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="IDLE"/>
 *     &lt;enumeration value="BUSY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "communicationState")
@XmlEnum
public enum CommunicationState {

    READY,
    IDLE,
    BUSY;

    public String value() {
        return name();
    }

    public static CommunicationState fromValue(String v) {
        return valueOf(v);
    }

}
