package eu.gloria.tools.time;

import java.util.Date;


public class JulianDate {
	
	
	
	public static void main(String[] args) {
		
		Date date = new Date();
		date.setDate(29);
		date.setMonth(2-1);	//Month between 0-11
		date.setYear(2016);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		System.out.println(getJulianDate(date)+"\n");
	}
	
	
	public static long getJulianDateNumber (Date date){		
		 
		int a = (14 - (date.getMonth()+1))/12;
		int y = date.getYear() +4800 - a;
		int m = (date.getMonth() + 1) +12*a -3;
		
		return (date.getDate() + ((153*m+2)/5) + 365*y + y/4 - y/100 + y/400 - 32045);
		
		
	}

	
	public static double getJulianDate (Date date){
		
		double JDN = getJulianDateNumber(date);
		double h = (date.getHours()-12)/24.0;
		double m = date.getMinutes()/1440.0;
		double s = date.getSeconds()/86400.0;
		
		return (JDN + h + m + s);
	}
}
