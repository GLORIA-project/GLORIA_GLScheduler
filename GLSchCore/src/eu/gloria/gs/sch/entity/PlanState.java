//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.17 a las 10:37:04 AM CET 
//


package eu.gloria.gs.sch.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para planState.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="planState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUBMITTED"/>
 *     &lt;enumeration value="REJECTED"/>
 *     &lt;enumeration value="CONFIRMED"/>
 *     &lt;enumeration value="DONE"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="ABORTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "planState")
@XmlEnum
public enum PlanState {

    SUBMITTED,
    REJECTED,
    CONFIRMED,
    DONE,
    ERROR,
    ABORTED;

    public String value() {
        return name();
    }

    public static PlanState fromValue(String v) {
        return valueOf(v);
    }

}
