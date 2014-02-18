package eu.gloria.gs.sch.entity;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.gloria.gs.sch.exception.GLSchException;


public interface GLSchInterface {
	
	@POST
    @Path("/planSubmit")
    @Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Offer a list of plans
	 * 
	 * @param planList
	 * @throws RTSchException
	 */
	public String planSubmit(String planList) throws GLSchException;
	
		
	@POST
    @Path("/getOPByFilter/{pageNumber}/{pageSize}")  
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
	public PlanSearchFilterResult getOPByFilter (@PathParam ("pageNumber") String number, @PathParam ("pageSize") String size, PlanSearchFilter filter) throws GLSchException;
	
	
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
	public PlanInfo getOPByUuid(@PathParam("uuid") String planUuid) throws GLSchException;

}
