package eu.gloria.gs.sch.db.telescOP;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import eu.gloria.gs.sch.db.gloriaOP.OPsegmentation;
import eu.gloria.gs.sch.db.util.DBUtil;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.tools.log.LogUtil;


public class TelescOPManager {

		public static void main(String[] args){		

		EntityManager em = DBUtil.getEntityManager();
		
		TelescOPManager manager = new TelescOPManager();
		
//		AvailableTelescope telescope = new AvailableTelescope();
//		telescope.setSimpleExposure(manager.getSE(em, "1"));
//		telescope.setTelescopeList(manager.getTelescopeList(em, 3));
//		
//		manager.createAvailableTelescope(em, telescope);
		
//		manager.incrementTelescopeOffer(em,  1);
		
		List <Integer> list = new ArrayList <Integer> ();
		list.add(1);
		list.add(2);
		try {
//			manager.updateSimpleExposureTelescope("201402050000014401922384v001se1", 6);
			manager.updateSimpleExposureState("20140206000001440670b439v001se1", TelescopeOPState.OFFERED);
		} catch (GLSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/***************************************************************************************************/
	
	private EntityManager em = null;
		
	private static final Logger LOG = Logger.getLogger(TelescOPManager.class.getName());
	
	public TelescOPManager() {
		em = DBUtil.getEntityManager();
	}
		
	public void close(){
		DBUtil.close(em);
	}
	
	public AvailableTelescope getAvailableTelescope(int id, SimpleExposure exposure) throws GLSchException{
		
		LOG.info("Executing operation getAvailableTelescope " + id + " " +  exposure);

		AvailableTelescope result = null;
		
		
		try{
			
			Query query = em.createQuery("SELECT telesc FROM AvailableTelescope telesc where telesc.telescopeList=?1 and telesc.simpleExposure=?2");
			query.setParameter(1, getTelescopeList(id));
			query.setParameter(2, exposure);

			result = (AvailableTelescope) query.setMaxResults(1).getSingleResult();

		}catch(NoResultException ex){
			throw new GLSchException(ex.getMessage());
		}

		LOG.info("End of operation getAvailableTelescope " +result);
		
		return result;
	}
	
	public TelescopeList getTelescopeList(int id) throws GLSchException{

		LOG.info("Executing operation getTelescopeList " + id);
		
		TelescopeList result = null;

		try{
			
			Query query = em.createQuery("SELECT telesc FROM TelescopeList telesc where telesc.id=?1");
			query.setParameter(1, id);

			result = (TelescopeList) query.setMaxResults(1).getSingleResult();

		}catch(NoResultException ex){
			throw new GLSchException(ex.getMessage());
		}

		LOG.info("End of operation getTelescopeList " +result);
		return result;
	}
	
	/**
	 * Return single exposure row specifying the single exposure id
	 * 
	 * @param em
	 * @param uuid
	 * @return
	 * @throws GLSchException 
	 */
	public SimpleExposure getSE(String uuid) throws GLSchException{
		
		LOG.info("Executing operation getSE " + uuid);

		SimpleExposure result = null;

		try{			
//			Query query = em.createQuery("SELECT se FROM SimpleExposure se where se.simpleExid=?1");
//			query.setParameter(1, uuid);

			Query query = em.createNativeQuery("SELECT * FROM SimpleExposure WHERE simpleExid=?1");
			query.setParameter(1, uuid);
			
			Object [] data =  (Object[]) query.setMaxResults(1).getSingleResult();
			result = new SimpleExposure ();
			result.setId(((BigInteger) data[0]).toString());
			result.setSimpleExid((String) data[1]);
			result.setStateId(TelescopeOPState.valueOf((Integer)data[2]));
			result.setTargetName((String) data[3]);
			result.setTargetRADEC((String) data[4]);
			result.setFilter((String) data[5]);
			
			if (data[6] == null){
				result.setAvailableTelescope(null);
			}else{
				AvailableTelescope telescope = new AvailableTelescope();
				TelescopeList telescopeList = new TelescopeList();
				telescopeList.setId((Integer) data[6]);
				telescope.setTelescopeList(telescopeList);
				result.setAvailableTelescope(telescope);
			}
			result.setReallocated((Integer) data[7]);

		}catch(NoResultException ex){
			throw new GLSchException(ex.getMessage());
		}

		LOG.info("End of operation getSE " +result);
		return result;
	}
	
	public void createSimpleExposure(SimpleExposure exposure) throws GLSchException{

		LOG.info("Executing operation createSimpleExposure " + exposure);
		
		
		
		try {			
		
			DBUtil.beginTransaction(em);

			em.persist(exposure);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error creating a SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		} 
		
		LOG.info("End of operation createSimpleExposure ");
	}
	
	
		
	public void createAvailableTelescope(AvailableTelescope telescope) throws GLSchException{

		LOG.info("Executing operation createAvailableTelescope " + telescope);
		
		
		try {
			
			DBUtil.beginTransaction(em);

			em.persist(telescope);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error creating a SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		} 
		
		LOG.info("End of operation createAvailableTelescope ");
	}
	
	public void updateSimpleExposureState(String SEid, TelescopeOPState state) throws GLSchException{

		LOG.info("Executing operation updateSimpleExposureState " + SEid + " " + state);
		
	
		try {
			
			DBUtil.beginTransaction(em);

			Query query = em.createNativeQuery("UPDATE SimpleExposure SET stateId=?1 WHERE simpleExid=?2");
			query.setParameter(1, state.ordinal());
			query.setParameter(2, SEid);
			
			query.executeUpdate();
			//When chosenTelescopeId is set, it doesn't work properly.
//			SimpleExposure exposure = getSE(SEid);
//			exposure.setStateId(state);
			
//			em.merge(exposure);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error updating the state in SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		} 
		
		LOG.info("End of operation updateSimpleExposureState ");
	}
	
	public void updateSimpleExposureTelescope(  String SEid, int telescopeId) throws GLSchException{

		LOG.info("Executing operation updateSimpleExposureTelescope " + SEid + " " + telescopeId);
		
		
		try {
						
			DBUtil.beginTransaction(em);
			
			
			Query query = em.createNativeQuery("UPDATE SimpleExposure SET chosenTelescopeId=?1 WHERE simpleExid=?2");
			query.setParameter(1, telescopeId);
			query.setParameter(2, SEid);
			
			query.executeUpdate();
			
//			SimpleExposure exposure = getSE(SEid);
//			exposure.setAvailableTelescope(getAvailableTelescope(telescopeId, exposure));
//			
//			em.merge(exposure);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error updating the state in SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		} 
		
		LOG.info("End of operation updateSimpleExposureTelescope ");
	}
	
	public void incrementSimpleExposureReallocation(String SEid) throws GLSchException{

		LOG.info("Executing operation incrementSimpleExposureReallocation " + SEid);
		
		
		try {
			DBUtil.beginTransaction(em);
			
			SimpleExposure exposure = getSE(SEid);
			exposure.setReallocated(exposure.getReallocated()+1);
			
			em.merge(exposure);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error updating the state in SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		}
		
		LOG.info("End of operation incrementSimpleExposureReallocation ");
		
	}
	
	public TelescopeList getMinTelescopeOffer(List<Integer> telescopeId) throws GLSchException{
		
		LOG.info("Executing operation getMinTelescopeOffer " + telescopeId);
		
		TelescopeList result = null;

		try{
			
			Query query = em.createQuery("SELECT telescope FROM TelescopeList telescope where telescope.offered = " +
					"(SELECT MIN(telescope.offered) FROM telescope WHERE telescope.id in (:collection)) AND telescope.id in (:collection)");
	
			query.setParameter("collection", telescopeId);
			result =  (TelescopeList) query.setMaxResults(1).getSingleResult();			
			
		}catch(NoResultException ex){
			throw new GLSchException(ex.getMessage());
		}

		LOG.info("End of operation getMinTelescopeOffer " + result.getId());
		return result;
		
	}
	
	public void incrementTelescopeOffer(int id) throws GLSchException{

		LOG.info("Executing operation incrementTelescopeOffer " + id);
		
		try {
			
			DBUtil.beginTransaction(em);
			
			TelescopeList telescope = getTelescopeList(id);
			telescope.setOffered(telescope.getOffered()+1);
			
			em.merge(telescope);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error updating the state in TelescopeList " + ex.getMessage());
			DBUtil.rollback(em);
			
			throw new GLSchException(ex.getMessage());
		}
		
		LOG.info("End of operation incrementTelescopeOffer " );
	}
	
}
