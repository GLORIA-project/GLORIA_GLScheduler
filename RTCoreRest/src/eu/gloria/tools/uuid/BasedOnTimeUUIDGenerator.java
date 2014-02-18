package eu.gloria.tools.uuid;

import java.util.Date;

import eu.gloria.tools.configuration.Config;

/**
 * UUID generator based on time and prefix.
 * 
 * @author jcabello
 *
 */
public class BasedOnTimeUUIDGenerator extends UUIDGenerator {
	
	
	/**
	 * Prefix based on the RTS ID
	 */
	private long prefix;
	
	/**
	 * Constructor.
	 * @param prefix (64 bits)
	 */
	public BasedOnTimeUUIDGenerator(){
		this.prefix = Config.getPropertyLog("rt_config", "uuid.generator.rts.id", -1);
	}

	
	/**
	 * {@inheritDoc} 
	 *
	 */
	@Override
	public UUID getUUID() throws Exception {
		
		//To avoid generate the same time
		Thread.sleep(100);
		
		String prefixString =  getHexLong(this.prefix);
		
		Date now = new Date();
		String sufixString = getHexLong(now.getTime());
		
		return new UUID(prefixString + sufixString);
	}
	
	/**
	 * {@inheritDoc} 
	 *
	 */
	public UUID getMaxUUID() throws Exception {
		String prefixString =  getHexLong(this.prefix);
		return new UUID(prefixString + getHexLong(Long.MAX_VALUE));
	}
	
	/**
	 * {@inheritDoc} 
	 *
	 */
	public UUID getMinUUID() throws Exception {
		String prefixString =  getHexLong(this.prefix);
		return new UUID(prefixString + getHexLong(0));
	}
	
	/**
	 * Convert a long value to a Hexadecimal string.
	 * @param value long value
	 * @return Hexadecimal string.
	 * @throws Exception In error.
	 */
	private String getHexLong(long value) throws Exception {
		
		String result = Long.toString(value, 16);
		result = getDummyHexContent(16-result.length()) + result;
		
		return result;
		
	}
	
	/**
	 * generates empty hexadecimal content.
	 * @param count 0 repetitions.
	 * @return empty hexadecimal string.
	 */
	private String getDummyHexContent(int count) {
	
		StringBuffer sb = new StringBuffer();
		
		for (int x = 0; x < count; x++){
			sb.append("0");
		}
		
		return sb.toString();
		
	}
	
}
