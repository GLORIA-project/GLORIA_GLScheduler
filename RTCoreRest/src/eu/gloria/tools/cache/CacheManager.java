package eu.gloria.tools.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import eu.gloria.rt.exception.RTException;

/**
 * Manager for all caches in the Virtual Machine.
 * @author jcabello
 *
 */
public class CacheManager {

	/**
	 * Caches
	 */
	private static Map<String, Cache> caches;
	
	/**
	 * Static constructor
	 */
	static{
		
		caches = Collections.synchronizedMap(new HashMap<String, Cache>());
	}
	
	/**
	 * Creates a new cache, if it exists then it is deleted previously.
	 * @param cacheName Cache name
	 * @param expiration Expiration in milliseconds
	 * @param retriever Retriever (if it exists)
	 */
	public static void createCache(String cacheName, long expiration, ICacheRetriever retriever){
		Cache tmp = new Cache(cacheName, retriever, expiration);
		caches.remove(cacheName);
		caches.put(cacheName, tmp);
	}
	
	/**
	 * Creates a new cache, if it exists then it is deleted previously.
	 * @param cacheName Cache name
	 * @param expiration Expiration in milliseconds
	 * @param retriever Retriever (if it exists)
	 * @param purgationtime Purgation delay in milliseconds.
	 */
	public static void createCache(String cacheName, long expiration, ICacheRetriever retriever, long purgationTime){
		Cache tmp = new Cache(cacheName, retriever, expiration, purgationTime);
		caches.remove(cacheName);
		caches.put(cacheName, tmp);
	}
	
	/**
	 * Recovers a cached object.
	 * @param cacheName Cache name
	 * @param key Object Key
	 * @param params Parameters for the retriever.
	 * @return Cached Object.
	 * @throws RTException In error case
	 */
	public static Object getObject(String cacheName, Object key, Map<String, Object> params) throws RTException{
		Object result = null;
		Cache cache = caches.get(cacheName);
		if (cache != null){
			result = cache.getObject(key, params);
		}
		return result;
	}
	
	/**
	 * Stores the an object under a key in the cache
	 * @param cacheName Cache name
	 * @param key Key
	 * @param value Object value
	 * @throws RTException In error case.
	 */
	public static void putObject(String cacheName, Object key, Object value) throws RTException{
		Cache cache = caches.get(cacheName);
		if (cache != null){
			cache.putObject(key, value);
		}else{
			throw new RTException("Unexisting cache:" + cacheName);
		}
	}
	
	/**
	 * Deletes a cache.
	 * @param cacheName
	 */
	public static void deleteCache(String cacheName){
		caches.remove(cacheName);
	}
	
	
	/**
	 * Returns a cache.
	 * @param cacheName Cache name
	 * @return Cache
	 */
	public static Cache getCache(String cacheName){
		return caches.get(cacheName);
	}
}
