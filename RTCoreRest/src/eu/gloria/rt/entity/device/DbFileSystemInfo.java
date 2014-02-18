
package eu.gloria.rt.entity.device;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dbFileSystemInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dbFileSystemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="used" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="free" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dbFileSystemInfo", propOrder = {
    "used",
    "free"
})
public class DbFileSystemInfo {

    protected long used;
    protected long free;

    /**
     * Obtiene el valor de la propiedad used.
     * 
     */
    public long getUsed() {
        return used;
    }

    /**
     * Define el valor de la propiedad used.
     * 
     */
    public void setUsed(long value) {
        this.used = value;
    }

    /**
     * Obtiene el valor de la propiedad free.
     * 
     */
    public long getFree() {
        return free;
    }

    /**
     * Define el valor de la propiedad free.
     * 
     */
    public void setFree(long value) {
        this.free = value;
    }

}
