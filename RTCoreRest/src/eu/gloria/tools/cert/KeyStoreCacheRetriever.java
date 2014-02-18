package eu.gloria.tools.cert;

import java.security.KeyStore;
import java.util.Map;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.cache.ICacheRetriever;
import eu.gloria.tools.log.LogUtil;

public class KeyStoreCacheRetriever implements ICacheRetriever {

	/**
	 * {@inheritDoc}
	 * <p>
	 * Parameters for the retriever:
	 * - KEYSTORE_FILE::String
	 * - KEYSTORE_PW::String
	 */
	@Override
	public Object retrieve(Map<String, Object> params) throws RTException {
		
		KeyStore ks = null;
		
		try{
			String ksFile = (String) params.get("KEYSTORE_FILE");
			String ksPw = (String) params.get("KEYSTORE_PW");
			
			ks = KeyStore.getInstance(KeyStore.getDefaultType());

			// get user password and file input stream
			char[] password = ksPw.toCharArray();

			java.io.FileInputStream fis = null;
			try {
				fis = new java.io.FileInputStream(ksFile);
				ks.load(fis, password);
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
			
		}catch(Exception ex){
			
			String message = "KeyStoreCache.retrieve. " + ex.getMessage();
			LogUtil.severe(this, message);
			throw new RTException(message);
			
		}
		
		return ks;
	}

}
