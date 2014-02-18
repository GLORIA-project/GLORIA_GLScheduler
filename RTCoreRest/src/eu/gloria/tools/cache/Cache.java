package eu.gloria.tools.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.log.LogUtil;

/**
 * Cache for objects
 * @author jcabello
 *
 */
public class Cache  extends TimerTask{
	
	/**	
	 * Object retriever
	 */
	private ICacheRetriever retriever;
	
	/**
	 * Life for all cached objects in milliseconds.
	 */
	private long expiration;
	
	/**
	 * Time interval (milliseconds) between two purgation process.
	 */
	private long purgationTime;
	
	/**
	 * Cache name
	 */
	private String name;
	
	/**
	 * Cached objects
	 */
	private Map<Object, CacheObject> objs;
	
	/**
	 * Timer that launch the purgation process.
	 */
	private Timer purgationTimer;
	
	/**
	 * Parameters for tracing.
	 */
	private String logParameters;
	
	
	
	/**
	 * Constructor
	 * @param name Cache name
	 * @param retriever Object retriever
	 * @param expiration Life for all cached objects in milliseconds.
	 */
	public Cache(String name, ICacheRetriever retriever, long expiration){
		this.purgationTime = -1;
		this.purgationTimer = null;
		this.objs = Collections.synchronizedMap(new HashMap<Object, CacheObject>());
		this.expiration = expiration;
		this.retriever = retriever;
		
		String[] names = {
				"CacheName",
				"HasRetriever",
				"expirationTime",
				"purgationTime"
		};
		
		String[] values = {
				name,
				String.valueOf(retriever != null),
				String.valueOf(expiration),
				String.valueOf(purgationTime)
		};
		
		logParameters = LogUtil.getLog(names, values);
		
		LogUtil.info(this, logParameters + " Created Cache without purger.");
	}
	
	/**
	 * Constructor
	 * @param name Cache name
	 * @param retriever Object retriever
	 * @param expiration Life for all cached objects in milliseconds.
	 * @param purgationTime Time interval (milliseconds) between two purgation process.
	 */
	public Cache(String name, ICacheRetriever retriever, long expiration, long purgationTime){
		this.purgationTime = purgationTime;
		this.purgationTimer = new Timer(true);
		this.objs = Collections.synchronizedMap(new HashMap<Object, CacheObject>());
		this.expiration = expiration;
		this.retriever = retriever;
		if (this.purgationTime > 0){
			this.purgationTimer.schedule(this, 0, this.purgationTime);
		}
		String[] names = {
				"CacheName",
				"HasRetriever",
				"expirationTime",
				"purgationTime"
		};
		
		String[] values = {
				name,
				String.valueOf(retriever != null),
				String.valueOf(expiration),
				String.valueOf(purgationTime)
		};
		
		logParameters = LogUtil.getLog(names, values);
		
		LogUtil.info(this, logParameters + " Created CACHE with purger.");
	}
	
	/**
	 * Stops the purgation timer
	 */
	@Override
	protected void finalize() throws Throwable
	{
		
		LogUtil.info(this, logParameters + " Finalizing CACHE.");
		//Stops the purgation timer.
		if (this.purgationTimer != null){
			try{
				this.purgationTimer.cancel();
			}catch(Exception ex){
				LogUtil.severe(this, logParameters + " Error finalizing CACHE. " + ex.getMessage());
			}
		}
		
		LogUtil.info(this, logParameters + " Finalized CACHE.");
		
		//Super finalize
	    super.finalize(); //not necessary if extending Object.
	} 
	
	/**
	 * Returns the object from the cache.
	 * 
	 * if fails, the retriever will return the object.
	 * if expired, the retriever will return the object.
	 * 
	 * @param key Object key
	 * @param params parameter for the retriever.
	 * @return the cached object.
	 * @throws RTException In error case.
	 */
	public Object getObject(Object key, Map<String, Object> params) throws RTException{
		
		synchronized (this) {
			
			CacheObject cacheObj = objs.get(key);
			if (cacheObj != null){
				if (cacheObj.isObsolete(expiration)){ //Expired -> out of cache
					LogUtil.fine(this, logParameters + "CACHE-getObject:: cached&expired-element:" + key);
					objs.remove(key);
					cacheObj = null;
				}else{ //Valid -> update the creation time
					cacheObj.hasBeenAccessed();
					LogUtil.fine(this, logParameters + "CACHE-getObject:: cached&valid-element:" + key);
				}
			}
			
			Object result = null;
			if (cacheObj == null){ //is recovered and put into the cache.
				
				if (retriever != null){
					LogUtil.fine(this, logParameters + "CACHE-getObject:: uncached&recovering-element:" + key);
					result = retriever.retrieve(params);
					if (result != null){
						CacheObject tmp = new CacheObject(key, result);
						objs.put(key, tmp);
					}
				}
				
			}else{
				
				result = cacheObj.getObj();
				
			}
			
			return result;
		}
		
	}
	
	/**
	 * Removes the cached object
	 * @param key Object key
	 */
	public void deleteObject(Object key){
		
		synchronized (this) {
			objs.remove(key);
			LogUtil.fine(this, logParameters + "CACHE-deleteObject:: element:" + key);
		}
		
	}
	
	
	/**
	 * Put an object into the cache
	 * @param key Object key
	 * @param obj object to cache
	 */
	public void putObject(Object key, Object obj){
		
		synchronized (this) {
			CacheObject cacheObj = objs.get(key);
			if (cacheObj != null){
				objs.remove(key);
			}
			
			CacheObject tmp = new CacheObject(key, obj);
			objs.put(key, tmp);
			LogUtil.fine(this, logParameters + "CACHE-putObject:: element:" + key);
		}
		
	}
	
	/**
	 * Purge the obsolete objects
	 */
	private void purge(){
		
		synchronized (this) {
			
			LogUtil.info(this, logParameters + " CACHE-PURGE Start.");
			
			List<CacheObject> listToRemove = new ArrayList<CacheObject>();
			
			//Select the obsolete objects
			Collection<CacheObject> collection = this.objs.values();
			for (CacheObject cacheObject : collection) {
				if (cacheObject.isObsolete(expiration)){
					listToRemove.add(cacheObject);
				}
			}
			
			//Deletes the obsolete objects
			for (CacheObject cacheObject : listToRemove) {
				LogUtil.info(this, logParameters + " CACHE-PURGE element-key=." + cacheObject.getKey() );
				this.objs.remove(cacheObject.getKey());
			}
			
			LogUtil.info(this, logParameters + " CACHE-PURGE End.");
		}
		
	}

	/**
	 * Access method.
	 * @return Retriever.
	 */
	public ICacheRetriever getRetriever() {
		
		synchronized (this) {
			return retriever;
		}
		
	}

	/**
	 * Access method.
	 * @param retriever Retriever.
	 */
	public void setRetriever(ICacheRetriever retriever) {
		
		synchronized (this) {
			this.retriever = retriever;
		}
	}

	/**
	 * Access method.
	 * @return Value
	 */
	public long getExpiration() {
		return expiration;
	}

	/**
	 * Access method
	 * @param expiration Value
	 */
	public void setExpiration(long expiration) {
		synchronized (this) {
			this.expiration = expiration;
		}
	}

	/**
	 * Access method.
	 * @return value
	 */
	public String getName() {
		return name;
	}

	/**
	 * Access method.
	 * @param name value
	 */
	public void setName(String name) {
		synchronized (this) {
			this.name = name;
		}
	}

	/**
	 * Access method.
	 * @return Value
	 */
	public Map<Object, CacheObject> getObjs() {
		return objs;
	}

	/**
	 * Access method.
	 * @param objs value
	 */
	public void setObjs(Map<Object, CacheObject> objs) {
		synchronized (this) {
			this.objs = objs;
		}
	}

	@Override
	public void run() {
		purge();
		
	}

}
