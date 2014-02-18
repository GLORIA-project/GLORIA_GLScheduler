package eu.gloria.gs.sch.tarVisib;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.gloria.gs.rt.db.TelescopeConnection;
import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.sch.core.SchedulerOffer;
import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeOPState;
import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.op.SimpleExposure;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.opTransf.OPtransformation;
import eu.gloria.rt.entity.scheduler.plan.Plan;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rti_scheduler.RTISchRestProxy;
import eu.gloria.tools.time.TimeOut;


public class TargetVisibility {
	
	private List <Integer> telescopeList = null;
	private static HashMap <String, HashMap<Integer, AdvertiseState>> exposureMap = new HashMap <String, HashMap<Integer, AdvertiseState>> ();
	private HashMap <Integer, AdvertiseState> telescopes = null;
	private SimpleExposure exposure = null;
	private GloriaOPlan gloriaOP = null;
	private String uuid = null;
	
	private Timer timerTimeOut = null; 
	
	private static final Logger LOG = Logger.getLogger(TargetVisibility.class.getName());	
	
	
	public TargetVisibility (String uuid){
		
		this.uuid = uuid;
		
		this.timerTimeOut = new Timer(true);		
	}
	
	public TargetVisibility (List <Integer> telescopes, GloriaOPlan gloriaOp, SimpleExposure exposure, String uuid){
		
		this.telescopeList = telescopes;
		this.exposure = exposure;
		this.gloriaOP = gloriaOp;
		this.uuid = uuid;
		
		this.timerTimeOut = new Timer(true);		
				
	}
	
	public void evaluate () throws GLSchException{
		
				
		for (int id : telescopeList){

			rtsAdvertise adv = new rtsAdvertise (id);
			new Thread (adv).start();
			
		}
		
//		this.timerTimeOut.schedule(new TaskTimeOut(uuid), 0, 1000);
	}
	
	public synchronized static void deleteExposureMap (String seUUID){		
		exposureMap.remove(seUUID);
	}
	
	public synchronized void updateExposureMap (int telescopeID, AdvertiseState state) throws GLSchException{
		
		telescopes = exposureMap.get(uuid);		
		
		if (telescopes == null){
			if (state == AdvertiseState.ADVERTISED)
				telescopes = new HashMap<Integer, AdvertiseState> ();
			else
				throw new GLSchException ("Simple exposure NO previously advertised " + uuid);
		}
			
		if (telescopes.get(telescopeID) == AdvertiseState.TIMEOUT)
			throw new GLSchException("TimeOut " + uuid);
		
		telescopes.put(telescopeID, state);					
		
				
		exposureMap.put(uuid, telescopes);			
		
	}
	
	public void checkAdvertiseProcess () throws GLSchException {
		
		boolean schedulerContinue;
		List <Integer> telescopesId = null;
 		
		synchronized (this){
			telescopes = exposureMap.get(uuid);		

			if (telescopes == null)
				throw new GLSchException ("No data related to simple exposure: " + uuid);
			
						
			schedulerContinue = (!(telescopes.containsValue(AdvertiseState.ADVERTISED)) && !(telescopes.containsValue(AdvertiseState.OFFERED)));		
			
			if (schedulerContinue){
				telescopesId = new ArrayList <Integer>();
				for(Integer id: telescopes.keySet()){
					if (telescopes.get(id) == AdvertiseState.ACCEPTED)
						telescopesId.add(id);
				}
 				
			}
		}
		
		if (schedulerContinue){	
			continueScheduling(telescopesId, uuid);
		}
		
		
	}
	
	public void advertiseProcessTimeout (String uuid) throws GLSchException {
			
		List <Integer> telescopesId = new ArrayList <Integer> ();
 		
		synchronized (this){
			telescopes = exposureMap.get(uuid);		

			if (telescopes == null)
				throw new GLSchException ("No data related to simple exposure: " + uuid);
			
			for(Integer id: telescopes.keySet()){
				if (telescopes.get(id) == AdvertiseState.ADVERTISED)
					telescopes.put(id, AdvertiseState.TIMEOUT);
				
				if (telescopes.get(id) == AdvertiseState.ACCEPTED)
					telescopesId.add(id);
					
			}
			
		}
		
		continueScheduling(telescopesId, uuid);		
		
		
	}	
	
	private void continueScheduling (List <Integer> telescopeList, String seUUID) throws GLSchException{
		
		if (telescopeList.size() > 0){
			SchedulerOffer offer = new SchedulerOffer(seUUID, exposure);
			offer.continueScheduling(telescopeList);
		}else{
			//TODO reallocate
			
			TelescOPManager telescManager = new TelescOPManager();
			telescManager.updateSimpleExposureState(seUUID, TelescopeOPState.CANCELLED);
			telescManager.close();
		}
	}
	
	
	private class rtsAdvertise implements Runnable {

		private int telescopeID;
		private TelescopeConnection connection;
		private TelescopesDBManager manager= null;
		private TelescOPManager telescManager = null;
		
		private  RTISchRestProxy rtiSchProxy;
		
		public rtsAdvertise (int id) throws GLSchException{
			
			telescopeID = id;
			
			manager = TelescopesDBManager.getTelescopesDBManager ();
			
			telescManager = new TelescOPManager();
			
			
//			try {
//				connection = manager.getTelescopeConnection(id);
				
				connection = new TelescopeConnection();
				connection.setIp("localhost");
				connection.setPort("8080");
				connection.setPass("12345");
				connection.setUser("gloria_user");
				
				
				rtiSchProxy = new RTISchRestProxy(connection.getIp(), connection.getPort(), "RTISchRest/GloriaRTISch");
			
//			} catch (SQLException e) {
//				
//				throw new GLSchException(e.getMessage());
//				
//			}
			
		}
		
		public void run(){
						
	        try {
	        	
	        	OPtransformation transfor = new OPtransformation(telescopeID, gloriaOP, exposure, uuid);
	        	
	        	Plan plan = transfor.createRTIPlan();
	        	
	        	StringWriter planJson = new StringWriter();
				ObjectMapper objectMapper = new ObjectMapper();
				
				objectMapper.writeValue(planJson, plan);
				
				List <String> list = new ArrayList <String> ();
		        list.add(planJson.toString());
				
		        Date now = new Date();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		        
				rtiSchProxy.getConnection().planAdvertise(dateFormat.format(now), list);
				
				telescManager.updateSimpleExposureState(uuid, TelescopeOPState.ADVERTISED);
								
				updateExposureMap (telescopeID, AdvertiseState.ADVERTISED);
				telescManager.close();
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RTSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GLSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	        
	        			
		}
		
	}
	
	
	public class TaskTimeOut extends TimerTask {
		
		private TimeOut timeOut;
		private String uuid = null;
		
		public TaskTimeOut(String seID){
			
			this.timeOut = new TimeOut(120000);
			this.uuid = seID;
			
		}		
		
		
		@Override
		public void run() {
			
			if (timeOut.timeOut()){
				try {
					advertiseProcessTimeout(uuid);
				} catch (GLSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.cancel();
			}
				
		}
	}

}
