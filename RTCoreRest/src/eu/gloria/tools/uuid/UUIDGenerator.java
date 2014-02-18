package eu.gloria.tools.uuid;

import java.lang.reflect.Constructor;


import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;

/**
 * UUID generator.
 * 
 * @author jcabello
 *
 */
public abstract class UUIDGenerator {
	
	/**
	 * Singleton generator.
	 */
	public static UUIDGenerator singleton;
	
	/**
	 * Static initialization.
	 */
	static{
		
		String className = "undefined";
		
		try{
			className = Config.getProperty("rt_config", "uuid.generator.provider");
			Class<?> cls = Class.forName(className);
			Constructor<?> ct = cls.getConstructor();			
			singleton = (UUIDGenerator) ct.newInstance();
			
		}catch (Exception ex){	
			LogUtil.severe(null, "UUIDGenerator. static constructor. Error instantiating the UUIDGenerator [" + className + "]. " + ex.getMessage());
		}	
		
	}
	
	
	/**
	 * Generates the next UUID.
	 * @return Next UUID
	 * @throws Exception In error case.
	 */
	public abstract UUID getUUID() throws Exception;
	
	/**
	 * Returns the highest UUID.
	 * @return Highest UUID.
	 * @throws Exception In error case.
	 */
	public abstract UUID getMaxUUID() throws Exception;
	
	
	/**
	 * Returns the minimal UUID.
	 * @return Minimal UUID
	 * @throws Exception In error case.
	 */
	protected abstract UUID getMinUUID() throws Exception;

}
