package eu.gloria.gs.sch.db.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;


import eu.gloria.gs.sch.db.gloriaOP.*;
import eu.gloria.gs.sch.db.telescOP.*;
import eu.gloria.tools.log.LogUtil;




/**
 * Database JPA util.
 * 
 * @author jcabello
 *
 */
public class DBUtil {
	
	private static DBUtil singleton;
	private static boolean debugConnections = false;
	private static ArrayList<DBConnDebug> connDebugList;
	

	public static void main(String[] args){		
		
		EntityManager em = DBUtil.getEntityManager();
		DBUtil.beginTransaction(em);
		
		GloriaOPManager manager = new GloriaOPManager();
		
		SimpleExposure exp = new SimpleExposure();
//		exp.setId("3");
//		exp.setStateId(1);
//		exp.setTelescopeId(1);
		
//		exp = manager.getSE(em, "2");
//		
//		OPsegmentation data = new OPsegmentation();
//		data.setId("2");
//		data.setGlOPid(new BigInteger("2"));
//		data.setSimpleExposure(exp);
		
//		em.persist(exp);
//		em.persist(data);
		
//		AvailableTelescope telescope1 = new AvailableTelescope();
//		telescope1.setSimpleExposure(exp);
//		telescope1.setTelescopeId(1);
//		
//		em.persist(telescope1);
		
//		AvailableTelescope telescope2 = new AvailableTelescope();
//		telescope2.setSimpleExposure(exp);
//		telescope2.setTelescopeId(2);
//		
//		em.persist(telescope2);
		
		DBUtil.commit(em);
		
		DBUtil.close(em);
	}
	
	
	
	
	static{
		
		singleton = new DBUtil();
		
		connDebugList = new ArrayList<DBConnDebug>();
	}
	
	private EntityManagerFactory factory;
	
	
	private DBUtil(){
		factory = Persistence.createEntityManagerFactory("GLSchPersistence");
	}
	
	protected void finalize() throws Throwable
	{
		
		if (factory != null) factory.close();
		
		super.finalize(); 
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		return singleton.factory;
	}
	
	public static EntityManager getEntityManager(){
		
		EntityManager result = singleton.factory.createEntityManager();
		result.setFlushMode(FlushModeType.COMMIT);
		
		if (debugConnections){
			
			traceZombiConn();
			
			DBConnDebug connDebug = new DBConnDebug();
			connDebug.setCreation(new Date());
			connDebug.setManager(result);
			
			try{
				throw new RuntimeException();
			}catch(Exception ex){
				if (ex.getStackTrace() != null){
					
					StringBuffer sb = new StringBuffer();
					for (int x = 0 ;x < ex.getStackTrace().length; x++){
						sb.append(ex.getStackTrace()[x].toString()).append("|");
					}
					
					connDebug.setLog(sb.toString());
				}
			}
			
			synchronized (connDebugList) {
				connDebugList.add(connDebug);
			}
			
		}
		
		return result;
	}
	
	public static void close(EntityManager manager){
		
		try{
			
			synchronized (connDebugList) {
				
				//LogUtil.severe(null, "DBUtil.traceZombiConn(). TRACER: SIZE(CLOSING-INI):" + connDebugList.size());
				
				for (int x = 0; x < connDebugList.size(); x++){
					//LogUtil.severe(null, "DBUtil.traceZombiConn(). hashCodes[" + connDebugList.get(x).hashCode()  + ", " + manager.hashCode() + "]:" + connDebugList.size());
					if (connDebugList.get(x).getManager().hashCode() == manager.hashCode()){
						//LogUtil.severe(null, "DBUtil.traceZombiConn(). TRACER: removing index(" + x + "):" + connDebugList.size());
						connDebugList.remove(x);
					}
				}
			}
			
			if (manager != null){
				manager.close();
			}
			
			//LogUtil.severe(null, "DBUtil.traceZombiConn(). TRACER: SIZE(CLOSING-END):" + connDebugList.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
			LogUtil.severe(null, "DBUtil.close(EntityManager). Error closing EntityManager: " + ex.getMessage());
		}
		
	}
	
	public static void beginTransaction(EntityManager manager){
		if (manager != null){
			manager.getTransaction().begin();
		}
	}
	
	public static void commit(EntityManager manager){
		if (manager != null){
			manager.getTransaction().commit();
		}
	}
	
	public static void rollback(EntityManager manager){
		if (manager != null){
			EntityTransaction tx = manager.getTransaction();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		
	}
	
	private static void traceZombiConn(){
		
		synchronized (connDebugList) {
			
			LogUtil.severe(null, "DBUtil.traceZombiConn(). TRACER. SIZE=: " + connDebugList.size());
			
			for (int x = 0; x < connDebugList.size(); x++){
				if (connDebugList.get(x).isPossibleConnError()){
					LogUtil.severe(null, "DBUtil.traceZombiConn(). TRACER: " + connDebugList.get(x).getLog());
				}
			}
			
		}
		
	}
	


}
