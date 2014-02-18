package eu.gloria.tools.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
	
	//public static final String FORMAT_STRING = "%1$tF %1$tT - %2$s%n";
	public static final String FORMAT_STRING = "[THREAD=%1$d][%2$tY/%2$tm/%2$td %2$tH:%2$tS:%2$tS]::%3$s%n";
	
	public LogFormatter(){
		super();
	}

	@Override
	public String format(LogRecord record) {
		
		try{
		
			/*long time = record.getMillis();
			String msg = record.getMessage() == null ? "no message" :
			record.getMessage();
			return String.format(FORMAT_STRING, time, msg);
			 */
		
			Date now = new Date();
			String msg = record.getMessage() == null ? "no message" : record.getMessage();
			String result = String.format(FORMAT_STRING, Thread.currentThread().getId(), now, "(TOMA)" + msg);
			return result;
			
		}catch(Exception ex){
			System.err.println("FORMATERROR:" + ex.getMessage());
			return  record.getMessage() == null ? "no message (YA)" : record.getMessage() + "(YA)";
			
		}
		
	}

}
