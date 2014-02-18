//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.10 a las 10:54:01 AM CET 
//


package eu.gloria.gs.sch.entity.op;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para constraints complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="constraints">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="targets" type="{http://gloria.eu/gs/sch/entity/plan}targets"/>
 *         &lt;element name="mode" type="{http://gloria.eu/gs/sch/entity/plan}mode"/>
 *         &lt;element name="filters" type="{http://gloria.eu/gs/sch/entity/plan}filters"/>
 *         &lt;element name="tracking" type="{http://gloria.eu/gs/sch/entity/plan}trackingRateType" minOccurs="0"/>
 *         &lt;element name="notBeforeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="notAfterDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pointing-error" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="FoV" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="moonDistance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="moonAltitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="seeing" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="FWHM" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pixelScale" type="{http://gloria.eu/gs/sch/entity/plan}doubleIterval" minOccurs="0"/>
 *         &lt;element name="exposureTime" type="{http://gloria.eu/gs/sch/entity/plan}positiveDoubleIterval" minOccurs="0"/>
 *         &lt;element name="readoutTime" type="{http://gloria.eu/gs/sch/entity/plan}positiveDouble" minOccurs="0"/>
 *         &lt;element name="DaysFromNewMoon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="preferredTelescope" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="targetAltitudeInfo" type="{http://gloria.eu/gs/sch/entity/plan}targetAltitudeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "constraints", propOrder = {
    "targets",
    "mode",
    "filters",
    "tracking",
    "notBeforeDate",
    "notAfterDate",
    "pointingError",
    "foV",
    "moonDistance",
    "moonAltitude",
    "seeing",
    "fwhm",
    "pixelScale",
    "exposureTime",
    "readoutTime",
    "daysFromNewMoon",
    "preferredTelescope",
    "targetAltitudeInfo"
})
public class Constraints {

    @XmlElement(required = true)
    protected Targets targets;
    @XmlElement(required = true)
    protected Mode mode;
    @XmlElement(required = true)
    protected Filters filters;
    protected TrackingRateType tracking;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notBeforeDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notAfterDate;
    @XmlElement(name = "pointing-error")
    protected Double pointingError;
    @XmlElement(name = "FoV")
    protected Double foV;
    protected Double moonDistance;
    protected Double moonAltitude;
    protected Double seeing;
    @XmlElement(name = "FWHM")
    protected Double fwhm;
    protected DoubleIterval pixelScale;
    protected PositiveDoubleIterval exposureTime;
    protected Double readoutTime;
    @XmlElement(name = "DaysFromNewMoon")
    protected Integer daysFromNewMoon;
    @XmlElement(type = Integer.class)
    protected List<Integer> preferredTelescope;
    protected TargetAltitudeInfo targetAltitudeInfo;

    /**
     * Obtiene el valor de la propiedad targets.
     * 
     * @return
     *     possible object is
     *     {@link Targets }
     *     
     */
    public Targets getTargets() {
        return targets;
    }

    /**
     * Define el valor de la propiedad targets.
     * 
     * @param value
     *     allowed object is
     *     {@link Targets }
     *     
     */
    public void setTargets(Targets value) {
        this.targets = value;
    }

    /**
     * Obtiene el valor de la propiedad mode.
     * 
     * @return
     *     possible object is
     *     {@link Mode }
     *     
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Define el valor de la propiedad mode.
     * 
     * @param value
     *     allowed object is
     *     {@link Mode }
     *     
     */
    public void setMode(Mode value) {
        this.mode = value;
    }

    /**
     * Obtiene el valor de la propiedad filters.
     * 
     * @return
     *     possible object is
     *     {@link Filters }
     *     
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * Define el valor de la propiedad filters.
     * 
     * @param value
     *     allowed object is
     *     {@link Filters }
     *     
     */
    public void setFilters(Filters value) {
        this.filters = value;
    }

    /**
     * Obtiene el valor de la propiedad tracking.
     * 
     * @return
     *     possible object is
     *     {@link TrackingRateType }
     *     
     */
    public TrackingRateType getTracking() {
        return tracking;
    }

    /**
     * Define el valor de la propiedad tracking.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingRateType }
     *     
     */
    public void setTracking(TrackingRateType value) {
        this.tracking = value;
    }

    /**
     * Obtiene el valor de la propiedad notBeforeDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotBeforeDate() {
        return notBeforeDate;
    }

    /**
     * Define el valor de la propiedad notBeforeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotBeforeDate(XMLGregorianCalendar value) {
        this.notBeforeDate = value;
    }

    /**
     * Obtiene el valor de la propiedad notAfterDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotAfterDate() {
        return notAfterDate;
    }

    /**
     * Define el valor de la propiedad notAfterDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotAfterDate(XMLGregorianCalendar value) {
        this.notAfterDate = value;
    }

    /**
     * Obtiene el valor de la propiedad pointingError.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPointingError() {
        return pointingError;
    }

    /**
     * Define el valor de la propiedad pointingError.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPointingError(Double value) {
        this.pointingError = value;
    }

    /**
     * Obtiene el valor de la propiedad foV.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFoV() {
        return foV;
    }

    /**
     * Define el valor de la propiedad foV.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFoV(Double value) {
        this.foV = value;
    }

    /**
     * Obtiene el valor de la propiedad moonDistance.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMoonDistance() {
        return moonDistance;
    }

    /**
     * Define el valor de la propiedad moonDistance.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMoonDistance(Double value) {
        this.moonDistance = value;
    }

    /**
     * Obtiene el valor de la propiedad moonAltitude.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMoonAltitude() {
        return moonAltitude;
    }

    /**
     * Define el valor de la propiedad moonAltitude.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMoonAltitude(Double value) {
        this.moonAltitude = value;
    }

    /**
     * Obtiene el valor de la propiedad seeing.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSeeing() {
        return seeing;
    }

    /**
     * Define el valor de la propiedad seeing.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSeeing(Double value) {
        this.seeing = value;
    }

    /**
     * Obtiene el valor de la propiedad fwhm.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFWHM() {
        return fwhm;
    }

    /**
     * Define el valor de la propiedad fwhm.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFWHM(Double value) {
        this.fwhm = value;
    }

    /**
     * Obtiene el valor de la propiedad pixelScale.
     * 
     * @return
     *     possible object is
     *     {@link DoubleIterval }
     *     
     */
    public DoubleIterval getPixelScale() {
        return pixelScale;
    }

    /**
     * Define el valor de la propiedad pixelScale.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleIterval }
     *     
     */
    public void setPixelScale(DoubleIterval value) {
        this.pixelScale = value;
    }

    /**
     * Obtiene el valor de la propiedad exposureTime.
     * 
     * @return
     *     possible object is
     *     {@link PositiveDoubleIterval }
     *     
     */
    public PositiveDoubleIterval getExposureTime() {
        return exposureTime;
    }

    /**
     * Define el valor de la propiedad exposureTime.
     * 
     * @param value
     *     allowed object is
     *     {@link PositiveDoubleIterval }
     *     
     */
    public void setExposureTime(PositiveDoubleIterval value) {
        this.exposureTime = value;
    }

    /**
     * Obtiene el valor de la propiedad readoutTime.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReadoutTime() {
        return readoutTime;
    }

    /**
     * Define el valor de la propiedad readoutTime.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReadoutTime(Double value) {
        this.readoutTime = value;
    }

    /**
     * Obtiene el valor de la propiedad daysFromNewMoon.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDaysFromNewMoon() {
        return daysFromNewMoon;
    }

    /**
     * Define el valor de la propiedad daysFromNewMoon.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDaysFromNewMoon(Integer value) {
        this.daysFromNewMoon = value;
    }

    /**
     * Gets the value of the preferredTelescope property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preferredTelescope property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreferredTelescope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getPreferredTelescope() {
        if (preferredTelescope == null) {
            preferredTelescope = new ArrayList<Integer>();
        }
        return this.preferredTelescope;
    }

    /**
     * Obtiene el valor de la propiedad targetAltitudeInfo.
     * 
     * @return
     *     possible object is
     *     {@link TargetAltitudeInfo }
     *     
     */
    public TargetAltitudeInfo getTargetAltitudeInfo() {
        return targetAltitudeInfo;
    }

    /**
     * Define el valor de la propiedad targetAltitudeInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetAltitudeInfo }
     *     
     */
    public void setTargetAltitudeInfo(TargetAltitudeInfo value) {
        this.targetAltitudeInfo = value;
    }

}
