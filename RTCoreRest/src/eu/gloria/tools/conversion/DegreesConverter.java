package eu.gloria.tools.conversion;

import java.text.DecimalFormat;

import eu.gloria.rt.exception.RTException;

public class DegreesConverter {
	
	public static String toDDMMSS (double data){
		
		boolean negative = data < 0;
		if (negative) data = data * -1;
	
		int DD = (int) Math.abs(data);
		int MM = (int) Math.abs((data - DD)*60);
		double SS = (((data - DD)*60) - MM)*60;	
		
		if (negative) DD = DD * -1;
		
		int ss = (int) Math.abs(SS*100);
		return (String.valueOf(DD)+":"+String.valueOf(MM)+":" + String.valueOf((double) ss/100));
	}
	
	public static double toDegreesD (int DD, int MM, double SS) throws RTException{
		
		if (MM < 0 || SS < 0) throw new RTException("Invalid negative number.");
		
		boolean negative = DD < 0;
		if (negative) DD = Math.abs(DD);
		
		double result = ((SS/60 + MM)/60 + DD);
		
		if (negative) result = result * -1;
		
		return result;
		
	}
	
	public static String toHHMMSS (double data){
		
		double hh = 24*data/360;
		int HH = (int) Math.abs(hh);
		double mm = (hh - HH)*60;
		int MM = (int) Math.abs(mm);
		double SS = (mm - MM)*60;
		
				
		int ss = (int) Math.abs(SS*100);
		
       // DecimalFormat df = new DecimalFormat("#.######");
		return (String.valueOf(HH)+":"+String.valueOf(MM)+":"+String.valueOf((double) ss/100));
		
	}
	
	public static double toDegreesH (int HH, int MM, double SS) throws RTException{
		
		if (HH < 0 || MM < 0 || SS < 0) throw new RTException("Invalid negative number.");
		
		return (((SS/60 + MM)/60 +HH)*360/24 );
	}
	

}
