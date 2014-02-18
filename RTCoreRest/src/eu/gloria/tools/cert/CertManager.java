package eu.gloria.tools.cert;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.HashMap;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.cache.CacheManager;
import eu.gloria.tools.log.LogUtil;

public class CertManager {
	
	/**
	 * Cache name for certificates keystores
	 */
	public static String CACHE_KEYSTORE = "CACHE_KEYSTORE";
	
	/**
	 * Static initialization
	 */
	static{
		CacheManager.createCache(CACHE_KEYSTORE, -1, new KeyStoreCacheRetriever());
	}
	
	/**
	 * Retrieves the KeyStore from the CACHE of keystores
	 * @param keyStoreFile KeyStore file path.
	 * @param keyStorePW KeyStore password.
	 * @return The keystore.
	 * @throws RTException In error case.
	 */
	public KeyStore getKeyStore(String keyStoreFile, String keyStorePW) throws RTException{
		
		HashMap<String, Object> retrieverParams = new HashMap<String, Object>();
		retrieverParams.put("KEYSTORE_FILE", keyStoreFile);
		retrieverParams.put("KEYSTORE_PW", keyStorePW);
		
		return (KeyStore)CacheManager.getCache(CACHE_KEYSTORE).getObject(keyStoreFile, retrieverParams);
	}
	
	/**
	 * Retrieves the Private key of an existing alias in the keystore.
	 * @param ks Keystore.
	 * @param alias Alias in the keystore.
	 * @param keyPassword Password of the private key.
	 * @return The private key.
	 * @throws RTException In error case.
	 */
	public PrivateKey getPrivateKey(KeyStore ks, String alias, String keyPassword) throws RTException{
		
		try{
			
			return (PrivateKey) ks.getKey(alias, keyPassword.toCharArray());
			
		}catch(Exception ex){
			String message = "CertManager.getPrivateKey(). Impossible to retrieve the private key. Alias: " + alias + "." + ex.getMessage();
			LogUtil.severe(this, message);
			throw new RTException(message);
		}
	}
	
	/**
	 * Retrieves the Public key of an existing alias in the keystore.
	 * @param ks Keystore.
	 * @param alias Alias in the keystore.
	 * @return the public key
	 * @throws RTException In error case.
	 */
	public PublicKey getPublicKey(KeyStore ks, String alias) throws RTException{
		
		try{
			
			return ks.getCertificate(alias).getPublicKey();
			
		}catch(Exception ex){
			String message = "CertManager.getPublicKey(). Impossible to retrieve the public key. Alias: " + alias + "." + ex.getMessage();
			LogUtil.severe(this, message);
			throw new RTException(message);
		}
	}
	
	/**
	 * Generates a signature.
	 * @param content The content to sign.
	 * @param privateKey The private key.
	 * @param algorithmName The used algorithm name. If null, SHA1withRSA will be used.
	 * @return Signature.
	 * @throws RTException In error case.
	 */
	public byte[] signatureCreate(byte[] content, PrivateKey privateKey, String algorithmName) throws RTException{
		
		try{
			
			if (algorithmName == null){
				algorithmName = "SHA1withRSA";
			}
			
			Signature signature = Signature.getInstance(algorithmName);
			signature.initSign(privateKey);
			signature.update(content);
			return signature.sign();
			
		}catch(Exception ex){
			String message = "CertManager.signatureCreate(). Impossible to create the signature. algorithmName: " + algorithmName + "." + ex.getMessage();
			LogUtil.severe(this, message);
			throw new RTException(message);
			
		}
		
	}
	
	/**
	 * Verifies a signature.
	 * @param signatureContent Signature to verify.
	 * @param clearContent Clear content, source of the signature.
	 * @param publicKey Public key
	 * @param algorithmName  The used algorithm name. If null, SHA1withRSA will be used.
	 * @return true if the signature is verified.
	 * @throws RTException In error case.
	 */
	public boolean signatureVerify(byte[] signatureContent, byte[] clearContent, PublicKey publicKey, String algorithmName) throws RTException{
		
		try{
			
			if (algorithmName == null){
				algorithmName = "SHA1withRSA";
			}
			
			Signature signature = Signature.getInstance(algorithmName);
			signature.initVerify(publicKey);
			signature.update(clearContent);
			return signature.verify(signatureContent);
			
		}catch(Exception ex){
			String message = "CertManager.signatureVerify(). Impossible to verify the signature. algorithmName: " + algorithmName + "." + ex.getMessage();
			LogUtil.severe(this, message);
			throw new RTException(message);
			
		}
		
	}

}
