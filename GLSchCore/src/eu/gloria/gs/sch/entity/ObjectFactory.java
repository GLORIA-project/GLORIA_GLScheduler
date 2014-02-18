//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.02.17 a las 10:37:04 AM CET 
//


package eu.gloria.gs.sch.entity;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.gloria.gs.sch.entity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.gloria.gs.sch.entity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlanSearchPagination }
     * 
     */
    public PlanSearchPagination createPlanSearchPagination() {
        return new PlanSearchPagination();
    }

    /**
     * Create an instance of {@link PlanSearchPaginationInfo }
     * 
     */
    public PlanSearchPaginationInfo createPlanSearchPaginationInfo() {
        return new PlanSearchPaginationInfo();
    }

    /**
     * Create an instance of {@link PlanInfo }
     * 
     */
    public PlanInfo createPlanInfo() {
        return new PlanInfo();
    }

    /**
     * Create an instance of {@link DateInterval }
     * 
     */
    public DateInterval createDateInterval() {
        return new DateInterval();
    }

    /**
     * Create an instance of {@link PlanSearchFilter }
     * 
     */
    public PlanSearchFilter createPlanSearchFilter() {
        return new PlanSearchFilter();
    }

    /**
     * Create an instance of {@link PlanSearchFilterResult }
     * 
     */
    public PlanSearchFilterResult createPlanSearchFilterResult() {
        return new PlanSearchFilterResult();
    }

    /**
     * Create an instance of {@link PlanStateInfo }
     * 
     */
    public PlanStateInfo createPlanStateInfo() {
        return new PlanStateInfo();
    }

}
