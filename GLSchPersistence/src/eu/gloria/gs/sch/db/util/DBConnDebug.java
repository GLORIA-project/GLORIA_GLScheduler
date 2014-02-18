package eu.gloria.gs.sch.db.util;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import eu.gloria.tools.time.DateTools;

public class DBConnDebug {
	
	private EntityManager manager;
	private Date creation;
	private String log;
	
	
	public boolean isPossibleConnError(){
		Date now = new Date();
		Date limit = DateTools.increment(creation, Calendar.SECOND, 60);
		return now.compareTo(limit) > 0;
	}


	public EntityManager getManager() {
		return manager;
	}


	public void setManager(EntityManager manager) {
		this.manager = manager;
	}


	public Date getCreation() {
		return creation;
	}


	public void setCreation(Date creation) {
		this.creation = creation;
	}


	public String getLog() {
		return log;
	}


	public void setLog(String log) {
		this.log = log;
	}
	

}
