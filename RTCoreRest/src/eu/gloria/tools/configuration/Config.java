package eu.gloria.tools.configuration;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.log.LogUtil;


/**
 * Configuration management based on properties files.
 * @author jcabello
 *
 */
public class Config {
	
	/**
	 * ResourceBundle cache.
	 */
	private static HashMap<String, ResourceBundle> cache;
	
	/**
	 * static initialization.
	 */
	static {
		cache = new HashMap<String, ResourceBundle>();
	}
	
	/**
	 * Returns the proper resourcebundle from cache.
	 * @param filename properties files name without extension.
	 * @return ResourceBundle
	 */
	private static ResourceBundle getResourceBundle(String filename){
		
		ResourceBundle rb = cache.get(filename);
		if (rb == null){
			rb = ResourceBundle.getBundle(filename); 
			cache.put(filename, rb);
		}
		
		return rb;
	}
	
	
	/**
	 * Recovers a configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @return The configuration value.
	 */
	public static String getProperty(String filename, String key){
		
		ResourceBundle rb = getResourceBundle(filename);
		
		return rb.getString(key);
	} 
	
	/**
	 * Recovers a configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @param defaultValue default value in error case.
	 * @return The configuration value.
	 */
	public static String getProperty(String filename, String key, String defaultValue){
		
		String result = defaultValue;
		
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			result = rb.getString(key);
			if (result == null || result.trim().length() == 0){
				result = defaultValue;
			}
			
		}catch(Exception ex){
			String[] names = {"filename", "key"};
			String[] values = {filename, key};
			String desc = "Config.getProperty(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
		}
		
		return result;
		
	} 
	
	/**
	 * Recovers the key of a configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param Value Parameter name
	 * @param defaultKey default key in error case.
	 * @return The configuration value.
	 */
	public static String getKey(String filename, String value, String defaultKey){
		
		String result = defaultKey;
		if (value != null){
		
			try{
			
				ResourceBundle rb = getResourceBundle(filename);
				Enumeration<String> keys = rb.getKeys();
				while (keys.hasMoreElements()){
					String currentKey = keys.nextElement();
					if (value.equals(rb.getString(currentKey))){
						result = currentKey;
						break;
					}
				}
			
				if (result == null || result.trim().length() == 0){
					result = defaultKey;
				}
			
			}catch(Exception ex){
				String[] names = {"filename", "value", "defaultKey"};
				String[] values = {filename, value, defaultKey};
				String desc = "Config.getKey(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
				LogUtil.severe(null, desc);
			}
		}
		
		return result;
		
	} 
	
	/**
	 * Recovers a long configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @param defaultValue default value in error case.
	 * @return The configuration value.
	 */
	public static long getPropertyLog(String filename, String key, long defaultValue){
		
		long result = defaultValue;
		
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			result = Long.parseLong(rb.getString(key));
			
		}catch(Exception ex){
			String[] names = {"filename", "key"};
			String[] values = {filename, key};
			String desc = "Config.getPropertyLog(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
		}
		
		return result;
		
	}
	
	/**
	 * Recovers an int configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @param defaultValue default value in error case.
	 * @return The configuration value.
	 */
	public static int getPropertyInt(String filename, String key, int defaultValue){
		
		int result = defaultValue;
		
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			result = Integer.parseInt(rb.getString(key));
			
		}catch(Exception ex){
			String[] names = {"filename", "key"};
			String[] values = {filename, key};
			String desc = "Config.getPropertyInt(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
		}
		
		return result;
		
	}
	
	/**
	 * Recovers a double configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @return The configuration value.
	 * @throws RTException in error case.
	 */
	public static double getPropertyDouble(String filename, String key) throws RTException{
		
		double result = 0;
		
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			result = Double.parseDouble(rb.getString(key));
			
		}catch(Exception ex){
			String[] names = {"filename", "key"};
			String[] values = {filename, key};
			String desc = "Config.getPropertyDouble(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
			throw new RTException(desc);
		}
		
		return result;
		
	}
	
	/**
	 * Recovers a boolean configuration parameter from a properties file.
	 * @param filename Properties file name (without extension).
	 * @param key Parameter name
	 * @param defaultValue default value in error case.
	 * @return The configuration value.
	 */
	public static boolean getPropertyBoolean(String filename, String key, boolean defaultValue){
		
		boolean result = defaultValue;
		
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			result = Boolean.parseBoolean(rb.getString(key));
			
		}catch(Exception ex){
			String[] names = {"filename", "key"};
			String[] values = {filename, key};
			String desc = "Config.getPropertyBoolean(). Error recovering a configuration parameter." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
		}
		
		return result;
		
	}
	
    /**
     * Convert ResourceBundle into a Properties object.
     *
     * @param filename Properties file name (without extension).
     * @return Properties a properties version of the resource bundle.
     */
    public static Properties getProperties(String filename) {
    	
		try{
			
			ResourceBundle rb = getResourceBundle(filename);
			
			Properties properties = new Properties();
			 
	        Enumeration<String> keys = rb.getKeys();
	        while (keys.hasMoreElements()) {
	            String key = keys.nextElement();
	            properties.put(key, rb.getString(key));
	        }
	        
	        return properties;
			
		}catch(Exception ex){
			String[] names = {"filename"};
			String[] values = {filename};
			String desc = "Config.getProperties(). Error recovering a Properties object." + LogUtil.getLog(names, values);
			LogUtil.severe(null, desc);
			
			return new Properties();
		}
    	
    }
	
}
