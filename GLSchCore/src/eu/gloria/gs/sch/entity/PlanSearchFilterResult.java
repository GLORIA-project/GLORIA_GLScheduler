//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.17 a las 10:37:04 AM CET 
//


package eu.gloria.gs.sch.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para planSearchFilterResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="planSearchFilterResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paginationInfo" type="{http://gloria.eu/gs/sch/entity}planSearchPaginationInfo"/>
 *         &lt;element name="items" type="{http://gloria.eu/gs/sch/entity}planInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planSearchFilterResult", propOrder = {
    "paginationInfo",
    "items"
})
public class PlanSearchFilterResult {

    @XmlElement(required = true)
    protected PlanSearchPaginationInfo paginationInfo;
    protected List<PlanInfo> items;

    /**
     * Obtiene el valor de la propiedad paginationInfo.
     * 
     * @return
     *     possible object is
     *     {@link PlanSearchPaginationInfo }
     *     
     */
    public PlanSearchPaginationInfo getPaginationInfo() {
        return paginationInfo;
    }

    /**
     * Define el valor de la propiedad paginationInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanSearchPaginationInfo }
     *     
     */
    public void setPaginationInfo(PlanSearchPaginationInfo value) {
        this.paginationInfo = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanInfo }
     * 
     * 
     */
    public List<PlanInfo> getItems() {
        if (items == null) {
            items = new ArrayList<PlanInfo>();
        }
        return this.items;
    }

}
