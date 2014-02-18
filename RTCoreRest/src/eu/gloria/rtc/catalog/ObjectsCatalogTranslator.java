package eu.gloria.rtc.catalog;

import eu.gloria.rt.exception.UnknownCatalogObjectException;
import eu.gloria.rtc.DeviceDiscovererInterface;
import eu.gloria.rtc.ObjectsCatalogTranslatorInterface;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;

/**
 * Catalog Object name translator interface
 * @author jcabello
 *
 */
public class ObjectsCatalogTranslator {
	
	/**
	 * Custom RTDFactory
	 */
	private static ObjectsCatalogTranslatorInterface translator;
	
	/**
	 * Static constructor
	 */
	static{
		
		try {
			
			String factoryClassName = Config.getProperty("rt_config", "catalog.object.translator");
			
			LogUtil.info(null, "Creating...ObjectsCatalogTranslatorInterface: " + factoryClassName);
			
			translator = (ObjectsCatalogTranslatorInterface)Class.forName(factoryClassName).newInstance();
			
		} catch (InstantiationException e) {
			LogUtil.severe(null, e.getMessage());
		} catch (IllegalAccessException e) {
			LogUtil.severe(null, e.getMessage());
		} catch (ClassNotFoundException e) {
			LogUtil.severe(null, e.getMessage());
		}
		
	}
	
	/**
	 * Translates a Global Object identifier to a local Object identifier.
	 * 
	 * @param globalId Global Object Identifier.
	 * @return Local Object identifier.
	 * @throws UnknownCatalogObjectException If it's impossible to translate the global identifier.
	 */
	static public String getLocalObjectId(String globalId) throws UnknownCatalogObjectException{
		
		if (translator != null){
			return translator.getLocalObjectId(globalId);
		}
		
		throw new UnknownCatalogObjectException("The singleton ObjectsCatalogTranslator does not exist.");
	}
	

}
