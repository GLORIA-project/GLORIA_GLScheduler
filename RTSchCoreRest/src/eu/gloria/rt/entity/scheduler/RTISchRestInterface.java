package eu.gloria.rt.entity.scheduler;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.XMLGregorianCalendar;

import eu.gloria.rt.exception.RTSchException;


public interface RTISchRestInterface {

	@POST
    @Path("/planSearchByFilter/{pageNumber}/{pageSize}")  
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Search OP by specific filter.
	 * 
	 * @param filter
	 * @param pagination
	 * @return
	 * @throws RTSchException
	 */
	public PlanSearchFilterResult planSearchByFilter(@PathParam ("pageNumber") String number, @PathParam ("pageSize") String size, PlanSearchFilter filter) throws RTSchException;
	

	
	@POST
    @Path("/planOffer")
    @Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Offer a list of plans
	 * 
	 * @param planList
	 * @throws RTSchException
	 */
	public void planOffer(List<String> planList) throws RTSchException;
	
	@GET
    @Path("/planSearchByUuid/{uuid}")  
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Search OP by specific uuid.
	 * 
	 * @param planUuidList
	 * @return
	 * @throws RTSchException
	 */
	public PlanInfo planSearchByUuid(@PathParam("uuid") String planUuid) throws RTSchException;
	
	@POST
    @Path("/planAdvertise/{date}")  
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	/**
	 * 
	 * Advertise an OP.
	 * The telescope checks the advertised plans for observability.
	 * 
	 * @param deadline "yyyy-mm-dd"
	 * @param planList
	 * @return
	 * @throws RTSchException
	 */
	public List<String> planAdvertise(@PathParam("date") String deadline,List<String> planList) throws RTSchException;
	
	@POST
    @Path("/planCancel/{uuid}")  
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Central scheduler cancels an offered OP
	 * 
	 * @param planUuidList
	 * @return
	 * @throws RTSchException
	 */
	public List<PlanCancelationInfo> planCancel(@PathParam("uuid") String planUuid) throws RTSchException;
	
}
