package eu.gloria.tools.time;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;

@SuppressWarnings("unchecked")
public class RunTimeCounter {
	
	private static List<String> keys;
	
	static{
		
		try{
			
			keys = new Vector<String>();
			
			String list = Config.getProperty("rt_config", "enable_run_time_counters");
			if (list != null){
				String[] keysToken = list.split(",");
				if (keysToken != null && keysToken.length > 0){
					for (int x = 0; x < keysToken.length; x++){
						LogUtil.info(null,"RunTimeCounter.StaticConstructor: Added:" + keysToken[x].trim() );
						keys.add(keysToken[x].trim());
					}
				}else{
					LogUtil.info(null,"RunTimeCounter.StaticConstructor: no tokens in enable_run_time_counters properties.");
				}
				
			}else{
				LogUtil.info(null,"RunTimeCounter.StaticConstructor: no enable_run_time_counters set.");
			}
			
		}catch(Exception ex){
			LogUtil.severe(null,"RunTimeCounter.StaticConstructor: Error reading the enable keys. " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	private long initTime;
	private long finishTime;
	private String taskId;
	private String key;
	private boolean disable;
	
	public RunTimeCounter(String taskId, String key){
		
		this.initTime = 0;
		this.finishTime = 0;
		this.taskId = taskId;
		this.key = key;
		
		disable = keys.indexOf(taskId) == -1;
		
	}
	
	public void start(){
		
		if (disable) return;
		
		Date now = new Date();
		initTime = now.getTime();
	}
	
	public void stop(){
		
		if (disable) return;
		
		Date now = new Date();
		finishTime = now.getTime();
	}
	
	public long getSpentTime(){
		
		if (disable) return 0;
		
		if (initTime == 0){
			return 0;
		}else if (initTime > 0 && finishTime == 0){
			return (new Date().getTime()) - initTime;
		}else{
			return finishTime - initTime;
		}
		
	}
	
	public boolean isReached(long time){
		
		Date now = new Date();
		return  (initTime + now.getTime() >= time);
		
	}
	
	public String getLogSpentTime(String desc) {
		
		if (disable) return "";
		
		String[] names = {"TaskId", "Key", "SpentTime", "Desc"};
		String[] values = {taskId, key, String.valueOf(getSpentTime()), desc};
		
		return "Spent time in action::" + LogUtil.getLog(names, values);
		
	}
	
	public void writeLog(String desc) {
		
		try{
			
			if (disable) return;
			
			LogUtil.info(this, getLogSpentTime(desc));
			
		}catch(Exception ex){
			
		}
		
	}
	
	public void writeLog() {
		
		try{
			
			if (disable) return;
			
			LogUtil.info(this, getLogSpentTime(""));
			
		}catch(Exception ex){
			
		}
		
	}

}
