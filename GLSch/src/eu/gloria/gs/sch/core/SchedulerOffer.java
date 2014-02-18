package eu.gloria.gs.sch.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.gloria.gs.rt.db.TelescopeConnection;
import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOP;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeOPState;
import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.op.SimpleExposure;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.opTransf.OPtransformation;
import eu.gloria.gs.sch.rtsDecision.TelescopeDecision;
import eu.gloria.gs.sch.tarVisib.AdvertiseState;
import eu.gloria.gs.sch.tarVisib.TargetVisibility;
import eu.gloria.rt.entity.scheduler.plan.Plan;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rti_scheduler.RTISchRestProxy;
import eu.gloria.tools.configuration.Config;

public class SchedulerOffer {
	
	private String uuid = null;
	private String gloriaOPText = null;
	private GloriaOPlan op = null;
	private SimpleExposure exposure = null;
	
	private String advBasePath =  Config.getProperty("DEV_sch", "opsXmlFolder");
	
	private static final Logger LOG = Logger.getLogger(SchedulerOffer.class.getName());
	
	
	public SchedulerOffer (String seUUID, SimpleExposure exposure) throws GLSchException{
		
		this.uuid = seUUID;
		
		this.exposure = exposure;
		
		GloriaOPManager manager = new GloriaOPManager();
		GloriaOP opDB = manager.getGloriaOPBySE(seUUID);
		
		String fullFileName = advBasePath + opDB.getGlOPid() + ".xml";
		File file = new File(fullFileName);
		byte[] buffer = new byte[(int) file.length()];
		DataInputStream in;
		try {
			in = new DataInputStream(new FileInputStream(file));
			in.readFully(buffer);
			
			gloriaOPText = new String (buffer);
			
			op = new GloriaOPlan(gloriaOPText);
			
		} catch (FileNotFoundException e) {
			throw new GLSchException(e.getMessage());
		} catch (IOException e) {
			throw new GLSchException(e.getMessage());
		}	
		
		//When arriving from Accept
		//Now only one exposure per OP
		if (this.exposure == null){
			this.exposure = new SimpleExposure();
			this.exposure.setExposureInfo(op.getInstructions().getExpose().getExposureTimeInfo());
			this.exposure.setFilter(op.getInstructions().getExpose().getFilter());
			this.exposure.setTarget(op.getInstructions().getTarget());			
		}
		
		manager.close();
		
	}
		
	
	
	public void continueScheduling (List <Integer> availableTelescopes) throws GLSchException{
		
		TelescopeDecision decision = new TelescopeDecision();
		int chosenTelescope = decision.getTargetTelescope(availableTelescopes, op.getConstraints().getPreferredTelescope());
		
		TelescOPManager managerOP = new TelescOPManager();
		managerOP.updateSimpleExposureTelescope(uuid, chosenTelescope);
		
		
		OPtransformation transfor = new OPtransformation(chosenTelescope, op, exposure, uuid);
    	
    	Plan plan = transfor.createRTIPlan();
    	
    	StringWriter planJson = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			objectMapper.writeValue(planJson, plan);
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TelescopesDBManager dbManager = TelescopesDBManager.getTelescopesDBManager ();
		TelescopeConnection connection;
//		try {
//			connection = dbManager.getTelescopeConnection(chosenTelescope);
			
			connection = new TelescopeConnection();
			connection.setIp("localhost");
			connection.setPort("8080");
			connection.setPass("12345");
			connection.setUser("gloria_user");
			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
//		TelescOPManager telescManager = new TelescOPManager();
		managerOP.updateSimpleExposureState(uuid, TelescopeOPState.OFFERED);
		managerOP.close();
		
		TargetVisibility visibility = new TargetVisibility(uuid);
		visibility.updateExposureMap (chosenTelescope, AdvertiseState.OFFERED);
		
		RTISchRestProxy rtiSchProxy = new RTISchRestProxy(connection.getIp(), connection.getPort(), "RTISchRest/GloriaRTISch");
		
		List<String> opList = new ArrayList <String> ();
		opList.add(planJson.toString());
		
		try {
			
			rtiSchProxy.getConnection().planOffer(opList);			
			
			
		} catch (RTSchException e) {
			throw new GLSchException(e.getMessage());
		}
	}

}
