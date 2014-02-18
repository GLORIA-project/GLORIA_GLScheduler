package eu.gloria.tools.time;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class DateIterator implements Iterator<Date> {
	
	private GregorianCalendar calendar;
	private int incType;
	private int incAmount;
	private Date limitDate;
	private boolean firstIteration;
	
	public DateIterator (Date date, int incType, int incAmount, Date limitDate){
		
		this.firstIteration = true;
		this.calendar = new GregorianCalendar();
		
		this.calendar.setTime(date);
		
		this.limitDate = limitDate;
		this.incType = incType;
		this.incAmount = incAmount;
	}
	
	public DateIterator (Date date, int incType, int incAmount, int limitDateIncType, int limitDateIncAmount){
		
		this.firstIteration = true;
		this.calendar = new GregorianCalendar();
		
		if (limitDateIncAmount > 0){
			this.calendar.setTime(date);
			this.calendar.add(limitDateIncType, limitDateIncAmount);
			this.limitDate = this.calendar.getTime();
		}
		
		this.calendar.setTime(date);
		this.incType = incType;
		this.incAmount = incAmount;
		this.calendar.setTime(date);
	}

	@Override
	public boolean hasNext() {
		boolean result;
		if (this.firstIteration){
			this.firstIteration = false;
			result = true;
		}else{
			calendar.add(incType, incAmount);
			result = calendar.getTime().compareTo(limitDate) < 0;
		}
		return result;
	}

	@Override
	public Date next() {
		return calendar.getTime();
	}

	@Override
	public void remove() {		
	}

}
