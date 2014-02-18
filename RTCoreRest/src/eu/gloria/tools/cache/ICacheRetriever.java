package eu.gloria.tools.cache;

import java.util.Map;

import eu.gloria.rt.exception.RTException;

/**
 * Interface for a Cache objects retriever.
 * @author jcabello
 *
 */
public interface ICacheRetriever {

	/**
	 * Recover the object if the cache fails
	 * @param params Parameters to recover the object from the target. Its content depends on the data needed by the retriever
	 * @return the object
	 */
	public Object retrieve(Map<String, Object> params) throws RTException;
	
}
