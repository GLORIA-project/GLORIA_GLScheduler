package eu.gloria.rtc.catalog;

import eu.gloria.rt.exception.UnknownCatalogObjectException;
import eu.gloria.rtc.ObjectsCatalogTranslatorInterface;

/**
 * This Objects Catalog Translator is a simple ECO.
 * 
 * @author jcabello
 *
 */
public class ObjectsCatalogTranslatorECO implements ObjectsCatalogTranslatorInterface {

	/**
	 * Returns the same global identifier.
	 */
	@Override
	public String getLocalObjectId(String globalId) throws UnknownCatalogObjectException {
		
		return globalId;
		
	}

}
