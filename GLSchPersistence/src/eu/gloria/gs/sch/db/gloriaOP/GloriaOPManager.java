package eu.gloria.gs.sch.db.gloriaOP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.util.DBPaginationSearch;
import eu.gloria.gs.sch.db.util.DBUtil;
import eu.gloria.gs.sch.entity.PlanState;
import eu.gloria.gs.sch.entity.PlanType;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.tools.log.LogUtil;




public class GloriaOPManager {
	
	public static void main(String[] args){		

		EntityManager em = DBUtil.getEntityManager();
		
		GloriaOPManager manager = new GloriaOPManager();
		
//		GloriaOP op = new GloriaOP();	
//		op.setGlOPid("2");
//		op.setStateId(GloriaOPState.SUBMITTED);
//		
//		manager.createGloriaOP(em, op);
		
//		manager.updateGloriaOPState("1", GloriaOPState.CONFIRMED);
		manager.getSimpleExposureByGlOP("20140210000001441b306e56v001");
	
	}
	/*************************************************************************************************************/
	private EntityManager em = null;
	
	
	public GloriaOPManager() {
		
		em = DBUtil.getEntityManager();
		
	}
	
	public void close(){
		DBUtil.close(em);
	}
	
	public GloriaOP getGloriaOP(String uuid){

		GloriaOP result = null;

		try{
			
			Query query = em.createQuery("SELECT op FROM GloriaOP op where op.glOPid=?1");
			query.setParameter(1, uuid);

			result = (GloriaOP) query.setMaxResults(1).getSingleResult();

		}catch(NoResultException ex){
			//All right...
		}

		return result;
	}
	
	public List <String> getSimpleExposureByGlOP (String uuid){

		List <String> result = null;

		try{

			Query query = em.createNativeQuery("SELECT simpleEXid FROM OPsegmentation where OPsegmentation.glOPid=?1");
			query.setParameter(1, uuid);			
			
			result = query.getResultList();

		}catch(NoResultException ex){
			//All right...
		}

		return result;
	}

	public void createGloriaOP(GloriaOP op){

				
		try {
			
			DBUtil.beginTransaction(em);

			em.persist(op);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error creating a GloriaOP: " + ex.getMessage());
			DBUtil.rollback(em);
		} 
	}
	
	public void updateGloriaOPState( String glOPid, GloriaOPState state){

				
		try {
			
			DBUtil.beginTransaction(em);

//			Query query = em.createNativeQuery("UPDATE GloriaOP SET stateId=?1 WHERE glOPid=?2");
//			query.setParameter(1, state.ordinal());
//			query.setParameter(2, glOPid);
//			
//			query.executeUpdate();
			
			GloriaOP exposure = getGloriaOP( glOPid);
			exposure.setStateId(state);
			
			em.merge(exposure);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error updating the state in SimpleExposure " + ex.getMessage());
			DBUtil.rollback(em);
		} 
	}
	
	
	public void createOPsegmentation(OPsegmentation segmentation){

				
		try {
			
			DBUtil.beginTransaction(em);

			Query query = em.createNativeQuery("INSERT INTO OPsegmentation (simpleEXid, glOpid) values (?1, ?2)");
			query.setParameter(1, segmentation.getSimpleExposure().getSimpleExid());
			query.setParameter(2, segmentation.getGloriaOp().getGlOPid());
			
			query.executeUpdate();
			
			//It doesn't work when no primary keys are used for the joint.
			//em.persist(segmentation);

			DBUtil.commit(em);

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error creating an OPsegmentation: " + ex.getMessage());
			DBUtil.rollback(em);
		} 
	}
	
	public GloriaOP getGloriaOPBySE (String uuid){

		GloriaOP result = null;
		
		TelescOPManager manager = new TelescOPManager();

		try{

						
			Query query = em.createNativeQuery("SELECT glOPid FROM centralSCH.OPsegmentation where simpleExid=?1");
			query.setParameter(1, uuid);
			
			String id = (String) query.setMaxResults(1).getSingleResult();
			
			result = getGloriaOP(id);
			 

		}catch(NoResultException ex){
			ex.printStackTrace();
		}

		return result;
	}
	
	public long getCountByFilter(DBPaginationSearch pagInfo, String user, List<PlanType> planTypes, List<PlanState> planStates,			
			Date observationSession	) throws Exception{
	
		try {
			
			

			DBUtil.beginTransaction(em);
			
			StringBuilder sb = new StringBuilder();
			int paramCount = 1;
			List<Object> params = new ArrayList<Object>();
			
			sb.append(" SELECT COUNT(op.id) FROM GloriaOP op where 0=0 ");
			
			if (user != null && !user.trim().isEmpty()){
				sb.append(" AND user='").append(user).append("'");
			}
			
//			if (planTypes != null && !planTypes.isEmpty()){
//				sb.append(" AND type in (");
//				for (int x = 0; x < planTypes.size(); x++){
//					if (x > 0) sb.append(",");
//					sb.append(planTypes.get(x).ordinal());
//				}
//				sb.append(") ");
//			}
			
			if (planStates != null && !planStates.isEmpty()){
				sb.append(" AND stateId in (");
				for (int x = 0; x < planStates.size(); x++){
					if (x > 0) sb.append(",");
					sb.append(planStates.get(x).ordinal());
				}
				sb.append(") ");
			}
			
			
			
//			if (observationSession!= null){
//				sb.append(" AND execDateObservationSession=?" + paramCount + " ");
//				paramCount++;
//				params.add(observationSession);
//			}
			
			Query query=em.createQuery(sb.toString());
			
			for (int tmpCount = 0; tmpCount < params.size(); tmpCount++) {
				query.setParameter(tmpCount+1, params.get(tmpCount));
			}
			
			Number countResult = (Number) query.getSingleResult();
			
			DBUtil.commit(em);
			
			return countResult.longValue();

		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error recovering the CountByFilter : " + ex.getMessage());
			DBUtil.rollback(em);
			throw ex;
		} 
		
	}
	
	public List<GloriaOP> getByFilter(DBPaginationSearch pagInfo, String user,
			List<PlanType> planTypes,List<PlanState> planStates,Date observationSession
			) throws Exception{
		
		
		List<GloriaOP> result = new ArrayList<GloriaOP>();
			
		try{			

			DBUtil.beginTransaction(em);
			
			StringBuilder sb = new StringBuilder();
			int paramCount = 1;
			List<Object> params = new ArrayList<Object>();
			
			sb.append(" SELECT op FROM GloriaOP op where 0=0 ");
			
			if (user != null && !user.trim().isEmpty()){
				sb.append(" AND user='").append(user).append("'");
			}
			
//			if (planTypes != null && !planTypes.isEmpty()){
//				sb.append(" AND type in (");
//				for (int x = 0; x < planTypes.size(); x++){
//					if (x > 0) sb.append(",");
//					sb.append(planTypes.get(x).ordinal());
//				}
//				sb.append(") ");
//			}
			
			if (planStates != null && !planStates.isEmpty()){
				sb.append(" AND stateId in (");
				for (int x = 0; x < planStates.size(); x++){
					if (x > 0) sb.append(",");
					sb.append(planStates.get(x).ordinal());
				}
				sb.append(") ");
			}
			
//			if (execPredictedDateIntevalIni != null) {
//				sb.append(" AND predAstr>=?" + paramCount + " ");
//				paramCount++;
//				params.add(execPredictedDateIntevalIni);
//			}
//			
//			if (execPredictedDateIntevalEnd != null) {
//				sb.append(" AND predAstr<=?" + paramCount + " ");
//				paramCount++;
//				params.add(execPredictedDateIntevalEnd);
//			}
//			
//			if (execBeginDateIntevalIni != null) {
//				sb.append(" AND execDateIni>=?" + paramCount + " ");
//				paramCount++;
//				params.add(execBeginDateIntevalIni);
//			}
//			
//			if (execBeginDateIntevalEnd != null) {
//				sb.append(" AND execDateIni<=?" + paramCount + " ");
//				paramCount++;
//				params.add(execBeginDateIntevalEnd);
//			}
//			
//			if (execEndDateIntevalIni != null) {
//				sb.append(" AND execDateEnd>=?" + paramCount + " ");
//				paramCount++;
//				params.add(execEndDateIntevalIni);
//			}
//			
//			if (execEndDateIntevalEnd != null) {
//				sb.append(" AND execDateEnd<=?" + paramCount + " ");
//				paramCount++;
//				params.add(execEndDateIntevalEnd);
//			}
//			
//			if (observationSession!= null){
//				sb.append(" AND execDateObservationSession=?" + paramCount + " ");
//				paramCount++;
//				params.add(observationSession);
//			}
				
			
			sb.append(" ORDER BY op.id DESC ");
			
			/*if (pagInfo != null){
				
				sb.append(" LIMIT ");
				sb.append(pagInfo.getFirstPageRow());
				sb.append(",");
				sb.append(pagInfo.getPageSize());
			}*/
			
			String queryString = sb.toString();
			LogUtil.info(this, "ObservingPlansByFilter query= " + queryString);
			Query query=em.createQuery(queryString);
			
			if (pagInfo != null){
				query.setFirstResult(pagInfo.getFirstPageRowInt());
				query.setMaxResults(pagInfo.getPageSize());
			}else{
				query.setMaxResults(20); //No more...
			}
			
			for (int tmpCount = 0; tmpCount < params.size(); tmpCount++) {
				query.setParameter(tmpCount+1, params.get(tmpCount));
			}
				
			List items = query.getResultList();
			if (items != null){
				for (int x = 0; x < items.size(); x++){
					result.add((GloriaOP)items.get(x));
				}
			}
			
			DBUtil.commit(em);
				
		}catch(NoResultException ex){
			//All right...
		}catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.severe(this, "Error recovering the ObservingPlansByFilter : " + ex.getMessage());
			DBUtil.rollback(em);
			throw ex;
		} 
				
		return result;
				
	}

}