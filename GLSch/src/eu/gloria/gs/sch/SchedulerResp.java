package eu.gloria.gs.sch;

import java.util.logging.Logger;

import javax.ws.rs.Path;

import eu.gloria.gs.sch.db.gloriaOP.GloriaOP;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPManager;
import eu.gloria.gs.sch.db.gloriaOP.GloriaOPState;
import eu.gloria.gs.sch.db.telescOP.AvailableTelescope;
import eu.gloria.gs.sch.db.telescOP.SimpleExposure;
import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeOPState;
import eu.gloria.gs.sch.entity.GLRTISchInterface;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.tarVisib.AdvertiseState;
import eu.gloria.gs.sch.tarVisib.TargetVisibility;
import eu.gloria.tools.uuid.sch.op.UUID;

@Path("SchedulerResp")
public class SchedulerResp implements GLRTISchInterface {
	
	private static final Logger LOG = Logger.getLogger(SchedulerResp.class.getName());
	

	public int planAccept(String id, int telescopeId) throws GLSchException {

		LOG.info("Executing operation planAccept");
		
		try{
			
			UUID uuid = new UUID(id);
			String seUUID = uuid.getSEValue();
			
			//Database update
			TelescOPManager manager = new TelescOPManager();
			SimpleExposure simpleExposure = manager.getSE(seUUID);
			
			if(simpleExposure.getStateId() == TelescopeOPState.ADVERTISED){
				
				AvailableTelescope telescope = new AvailableTelescope();
				telescope.setSimpleExposure(simpleExposure);			
				telescope.setTelescopeList(manager.getTelescopeList(telescopeId));

				manager.createAvailableTelescope(telescope);

				//TelescopeList management
				TargetVisibility visibility = new TargetVisibility(seUUID);
				visibility.updateExposureMap (telescopeId, AdvertiseState.ACCEPTED);
				visibility.checkAdvertiseProcess();
				
			}else{
				throw new GLSchException ("Simple exposure NOT in advertised state " + simpleExposure.getStateId());
			}
			manager.close();
		}catch (GLSchException e){
			LOG.severe("Exception executing operation planAccept: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		} catch (Exception e) {
			LOG.severe("Exception executing operation planAccept: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		}
		
		return 0;
	}

	public int planReject(String id, int telescopeId) throws GLSchException{
		
		LOG.info("Executing operation planReject");
		
		try{				
			UUID uuid = new UUID(id);
			String seUUID = uuid.getSEValue();
			
			TelescOPManager manager = new TelescOPManager();
			SimpleExposure simpleExposure = manager.getSE(seUUID);
			
			if(simpleExposure.getStateId() == TelescopeOPState.ADVERTISED){
				//TelescopeList management
				TargetVisibility visibility = new TargetVisibility(seUUID);
				visibility.updateExposureMap (telescopeId, AdvertiseState.REJECTED);
				visibility.checkAdvertiseProcess();
			}else{
				throw new GLSchException ("Simple exposure NOT in advertised state " + simpleExposure.getStateId());
			}
			manager.close();
		}catch (GLSchException e){
			LOG.severe("Exception executing operation planReject: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		} catch (Exception e) {
			LOG.severe("Exception executing operation planReject: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		}
		
		return 0;
		
	}

	public int planConfirm(String id, int telescopeId) throws GLSchException{
		
		LOG.info("Executing operation planConfirm");
		
		try{
			UUID uuid = new UUID(id);
			String seUUID = uuid.getSEValue();
			
			TelescOPManager manager = new TelescOPManager();
			SimpleExposure simpleExposure = manager.getSE(seUUID);
			
			if(simpleExposure.getStateId() == TelescopeOPState.OFFERED){
				manager.updateSimpleExposureState(seUUID, TelescopeOPState.CONFIRMED);
				manager.incrementTelescopeOffer(telescopeId);
				
				//TODO modify when multiples simple exposure allowed
				GloriaOPManager glManager = new GloriaOPManager();
				GloriaOP op = glManager.getGloriaOPBySE(seUUID);
				glManager.updateGloriaOPState(op.getGlOPid(), GloriaOPState.CONFIRMED);
				
			}else{
				throw new GLSchException ("Simple exposure NOT in offered state " + simpleExposure.getStateId());
			}
			manager.close();
		}catch (GLSchException e){
			LOG.severe("Exception executing operation planConfirm: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		} catch (Exception e) {
			LOG.severe("Exception executing operation planConfirm: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		}
		
		return 0;
	}

	public int planCancel(String id, int telescopeId) throws GLSchException{
		
		LOG.info("Executing operation planCancel");
		
		try{
			UUID uuid = new UUID(id);
			String seUUID = uuid.getSEValue();
			
			TelescOPManager manager = new TelescOPManager();
			SimpleExposure simpleExposure = manager.getSE(seUUID);
			
			if ((simpleExposure.getStateId() == TelescopeOPState.CONFIRMED) || (simpleExposure.getStateId() == TelescopeOPState.OFFERED)){
				manager.updateSimpleExposureState(seUUID, TelescopeOPState.CANCELLED);
				
				//TODO reallocation
			}else{
				throw new GLSchException ("Simple exposure NOT in offered/confirmed state " + simpleExposure.getStateId());
			}
			manager.close();			
		}catch (GLSchException e){
			LOG.severe("Exception executing operation planCancel: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		} catch (Exception e) {
			LOG.severe("Exception executing operation planCancel: " + e.getMessage());
			e.printStackTrace();
			throw new GLSchException (e.getMessage());
		}
		
		return 0;
	}

}
