package eu.gloria.tools.cache;

import java.util.Date;


/**
 * Wrapper for a cached object.
 * 
 * @author jcabello
 *
 */
public class CacheObject {
	
	/**
	 * Datetime of the creation
	 */
	private long creation;
	
	/**
	 * Cached object
	 */
	private Object obj;
	
	/**
	 * Object key
	 */
	private Object key;
	
	/**
	 * Constructor
	 * @param key Object key.
	 * @param obj Object.
	 */
	public CacheObject(Object key, Object obj){
		this.key = key;
		this.obj = obj;
		this.creation = (new Date()).getTime();
	}
	
	/**
	 * Checks if the data is obsoleted.
	 * @param expiration milliseconds
	 * @return boolean
	 */
	public boolean isObsolete(long expiration){
		if (expiration == -1){
			return false;
		}else{
			long now = (new Date()).getTime();
			return creation + expiration < now;
		}
		
	}
	
	/**
	 * Invoked when the object has been accessed.
	 */
	public void hasBeenAccessed(){
		this.creation = (new Date()).getTime();
	}
	
	/**
	 * Access method.
	 * @return value
	 */
	public long getCreation() {
		return creation;
	}
	
	/**
	 * Access method.
	 * @param creation Value
	 */
	public void setCreation(long creation) {
		this.creation = creation;
	}
	
	/**
	 * Access method.
	 * @return value
	 */
	public Object getObj() {
		return obj;
	}
	
	/**
	 * Access method.
	 * @param obj value
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * Access method
	 * @return value.
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * Access method.
	 * @param key value.
	 */
	public void setKey(Object key) {
		this.key = key;
	}

}
