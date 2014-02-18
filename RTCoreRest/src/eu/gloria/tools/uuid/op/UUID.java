package eu.gloria.tools.uuid.op;

import java.util.Date;

import eu.gloria.tools.time.DateTools;



/**
 * ObservingPlan UUID.
 * 
 * @author jcabello
 *
 */
public class UUID {
	
	public static void main(String[] args) throws Exception {
		
		UUID uuid1 = new UUID(1);
		String kk1 = uuid1.getValue();
		
		UUID uuid2 = new UUID(kk1);
		
		System.out.print("end");
		
	}
	
	private static String LOCK = "LOCK";
	
	
	private String telescope;
	private String date;
	private String timeStamp;
	private String version;
	
	public UUID(long rtsId) throws Exception{
		this(rtsId, new Date());
	}
	
	public UUID(long rtsId, Date dateIn) throws Exception{
		
		Date now = null;
		synchronized (LOCK) {
			
			//To avoid generate the same time
			Thread.sleep(100);
			now = new Date();
			
		}
		
		telescope = getHexLong(rtsId, 8); //8
		date = DateTools.getDate(dateIn, "yyyyMMdd"); //8
		timeStamp = getHexLong(now.getTime(), 16); //16
		version = "v001"; //4
		
	}
	
	public UUID(String value) throws Exception{
		
		if (value == null || value.length() != 36){
			throw new Exception("Invalid HEX-UUID: " + value);
		}
		
		telescope = value.substring(0, 8);
		date = value.substring(8, 16);
		timeStamp = value.substring(16, 32);
		version = value.substring(32, 36);
		
	}
	
	public long getTelescope(){
		return Long.parseLong(telescope);
	}
	
	
	public String getDate(){
		return date;
	}
	
	public String getVersion(){
		return version;
	}
	
	/**
	 * Returns the Hexadecimal  number (36 digits)
	 * @return Number Hexadecimal  number.
	 */
	public String getValue()  {
		return telescope + date + timeStamp + version;
	}
	
	/**
	 * Convert a long value to a Hexadecimal string.
	 * @param value long value
	 * @param length Result length
	 * @return Hexadecimal string.
	 * @throws Exception In error.
	 */
	private String getHexLong(long value, int length) throws Exception {
		
		String result = Long.toString(value, 16);
		result = getDummyHexContent(length-result.length()) + result;
		
		return result;
		
	}
	
	
	/**
	 * Generates empty hexadecimal content.
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

	public String getTimeStamp() {
		return timeStamp;
	}

}

