package eu.gloria.rtc;

import eu.gloria.rt.exception.UnknownCatalogObjectException;


/**
 * This interface defines the methods to translate a global object identifier (managed by Gloria)
 * to a local object identifier (managed by the RTS mount device).
 * 
 * Initial mappping:
 * 
 * <option value="0">Sol</option>
 * <option value="1">Mercurio</option>
 * <option value="2">Venus</option>
 * <option value="3">Luna</option>
 * <option value="4">Marte</option>
 * <option value="5">J&#250;piter</option>
 * <option value="6">Saturno</option>
 * <option value="7">Urano</option>
 * <option value="8">Neptuno</option>
 * <option value="9">Plut&#243;n</option></select>
 * 
 * 
 * @author jcabello
 *
 */
public interface ObjectsCatalogTranslatorInterface {
	
	/**
	 * Translates a Global Object identifier to a local Object identifier.
	 * 
	 * @param globalId Global Object Identifier.
	 * @return Local Object identifier.
	 * @throws UnknownCatalogObjectException If it's impossible to translate the global identifier.
	 */
	public String getLocalObjectId(String globalId) throws UnknownCatalogObjectException;

}
