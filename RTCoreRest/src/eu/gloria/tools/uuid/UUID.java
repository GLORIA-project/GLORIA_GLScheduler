package eu.gloria.tools.uuid;

/**
 * UUID.
 * 
 * @author jcabello
 *
 */
public class UUID {
	
	/**
	 * Wrapper value. The Hexadecimal  number (32 digits)
	 */
	private String value;
	
	/**
	 * The first 16 hexadecimal digits of the original value.
	 */
	private String prefix;
	
	/**
	 * The last 16 hexadecimal digits of the original value.
	 */
	private String suffix;
	
	/**
	 * Constructor
	 * @param value Hexadecimal with 32 digits.
	 * @throws Exception In error case
	 */
	public UUID(String value) throws Exception{
		
		if (value == null || value.length() != 32){
			throw new Exception("Invalid HEX-UUID: " + value);
		}
		
		this.value = value;
		this.prefix = value.substring(0, 16);
		this.suffix = value.substring(16, 32);
	}

	/**
	 * Returns the Hexadecimal  number (32 digits)
	 * @return Number Hexadecimal  number.
	 */
	public String getValue()  {
		return value;
	}
	
	/**
	 * Returns the first 16 hexadecimal digits of the original value.
	 * @return Number Hexadecimal  number.
	 */
	public String getPrefix(){
		return prefix;
	}
	
	/**
	 * Returns the last 16 hexadecimal digits of the original value.
	 * @return Number Hexadecimal  number.
	 */
	public String getSuffix(){
		return suffix;
	}
	
	/**
	 * Returns the first 16 hexadecimal digits of the original value converted into a positive long value.
	 * @return Number Hexadecimal  number.
	 */
	public long getLongPrefix(){
		return Long.parseLong(getPrefix(), 16);
	}
	
	/**
	 * Returns the last 16 hexadecimal digits of the original value converted into a positive long value.
	 * @return Number Hexadecimal  number.
	 */
	public long getLongSuffix(){
		return Long.parseLong(getSuffix(), 16);
	}

	
	

}
