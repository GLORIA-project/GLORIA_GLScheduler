package eu.gloria.gs.sch.entity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eu.gloria.gs.sch.exception.GLSchException;


public interface GLRTISchInterface {

	
	@POST
    @Path("/planAccept/{uuid}/{telescope}")
	/**
	 * 
	 * Accept an advertised OP
	 * 
	 * @param id OP identifier
	 * @return -1 if error
	 */
	public int planAccept(@PathParam("uuid") String id, @PathParam("telescope") int telescopeId) throws GLSchException;
	
	@POST
    @Path("/planReject/{uuid}/{telescope}")
	/**
	 * 
	 * Reject an advertised OP
	 * 
	 * @param id OP identifier
	 * @return -1 if error
	 */
	public int planReject(@PathParam("uuid") String id, @PathParam("telescope") int telescopeId) throws GLSchException;
	
	@POST
    @Path("/planConfirm/{uuid}/{telescope}")
	/**
	 * 
	 * Confirm an offered OP
	 * 
	 * @param id OP identifier
	 * @return -1 if error
	 */
	public int planConfirm(@PathParam("uuid") String id, @PathParam("telescope") int telescopeId) throws GLSchException;
	
	@POST
    @Path("/planCancel/{uuid}/{telescope}")
	/**
	 * 
	 * Cancel an offered OP
	 * 
	 * @param id OP identifier
	 * @return -1 if error
	 */
	public int planCancel(@PathParam("uuid") String id, @PathParam("telescope") int telescopeId) throws GLSchException;
	
	
	
}
