package eu.gloria.tools.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

//import eu.gloria.tools.configuration.Config;


/**
 * This class is used for logging.
 * Makes easier to write logs inside the code.
 * 
 * NOTE: The log configuration file must be set in a system properties (VM):
 *       -Djava.util.logging.config.file=./config/logging.properties 
 * 
 * @author jcabello
 *
 */
public class LogUtil {
	
	static{
		System.out.print (LogUtil.class.getName());
		Logger logger = Logger.getLogger(LogUtil.class.getName());
		logger.severe("RTI::INFO:: LogUtil. Log System property[java.util.logging.config.file] = " + System.getProperty("java.util.logging.config.file"));
	}
	
	
	public static void main(String[] args){			
		
		LogUtil.severe(null, "This is a test.......");		
		
	}	
	
	private static String getLog(String sourceClassName, String msg){
		
		Date date = new Date();
	    String DATE_FORMAT = "yyyy/dd/MM HH:mm:ss.SSSS";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    //String rtsName = Config.getProperty("rt_config", "rts_name", "RTS_DEFAULT");
	    
		StringBuffer sb = new StringBuffer();
		//sb.append("[RTI-").append(rtsName).append("]::");
		sb.append("[").append(sdf.format(date)).append("]");
		sb.append("[THREAD=").append(Thread.currentThread().getId()).append("]");
		sb.append("[SOURCE=").append(sourceClassName).append("]");
		sb.append(" ").append(msg);
		return sb.toString();
		
	}
	

	/**
	 * Writes severe log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void severe(Object source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getClass().getName();
		}
		logger = Logger.getLogger(sourceClassName);
				
		logger.severe(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes severe log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void severe(Class source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getName();
		}
		logger = Logger.getLogger(sourceClassName);
				
		logger.severe(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes info log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void info(Object source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getClass().getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.info(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes info log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void info(Class source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.info(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes fine log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void fine(Object source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getClass().getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.fine(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes fine log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void fine(Class source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.fine(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes finer log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void finer(Object source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getClass().getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.finer(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes finer log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void finer(Class source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.finer(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes finest log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void finest(Object source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getClass().getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.finest(getLog(sourceClassName, msg));
	}
	
	/**
	 * Writes finest log.
	 * @param source Object source of the log.
	 * @param msg Message.
	 */
	public static void finest(Class source, String msg){
		Logger logger = null;
		String sourceClassName = null;
		if (source == null){
			sourceClassName = LogUtil.class.getName();
		}else{
			sourceClassName = source.getName();
		}
		logger = Logger.getLogger(sourceClassName);
		
		logger.finest(getLog(sourceClassName, msg));
	}
	
	/**
	 * Returns a String like this: [name1=value1, name2=value2....]
	 * @param names List of names.
	 * @param values List of values
	 * @return String.
	 */
	public static String getLog(String[] names, String[] values){
		if (names != null && values != null && names.length == values.length){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int x = 0; x < values.length; x++){
				if (x > 0) {
					sb.append(", ");
				}
				sb.append(names[x]).append("=").append(values[x]);
			}
			sb.append("] ");
			return sb.toString();
			
		}else{
			
			return "";
		}
		
	}
	
	/**
	 * Recovers the exception description (including inner classes)
	 * 
	 * @param ex Exception
	 * @return [EX1]::ErrorMesage. [EX2]::ErrorMesage....
	 */
	public static String getExceptionMessage(Throwable ex){
		
		String result = "";
		
		if (ex != null){
		
			//local message
			if (ex.getMessage() != null) result = "[" + ex.getClass().getName() + "]::" + ex.getMessage() + ". ";
		
			//Inner Exception Message
			if (ex.getCause() != null) result = result + getExceptionMessage(ex.getCause());
			
		}
		
		return result;
	}
	
}