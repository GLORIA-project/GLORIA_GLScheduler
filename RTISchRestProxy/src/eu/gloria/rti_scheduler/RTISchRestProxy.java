package eu.gloria.rti_scheduler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import eu.gloria.rt.entity.scheduler.PlanInfo;
import eu.gloria.rt.entity.scheduler.PlanSearchFilter;
import eu.gloria.rt.entity.scheduler.PlanSearchFilterResult;
import eu.gloria.rt.entity.scheduler.RTISchRestInterface;
import eu.gloria.rt.exception.RTSchException;


public class RTISchRestProxy {
	
	public static void main(String[] args) {
		
		RTISchRestProxy proxy = new RTISchRestProxy("localhost","8080","RTISchRest/GloriaRTISch");
		
		
		List<String> list = new ArrayList <String> ();
		
		File file = new File("C:\\repositorio\\20130403002Json.txt");
		byte[] buffer = new byte[(int) file.length()];
		DataInputStream in;
		try {
//			/*****planAdvertise*****/
			in = new DataInputStream(new FileInputStream(file));			
			in.readFully(buffer);	        
	        list.add(new String(buffer));
			proxy.getConnection().planAdvertise("2014-01-22", list);
			
			/*****planCancel*****/			
			
//			List<PlanCancelationInfo> cancelInfo = proxy.getConnection().planCancel("20130403002");
//			
//			System.out.println(cancelInfo.get(0).getState()+"\n");
//			System.out.println(cancelInfo.get(0).getUuid()+"\n");
			
			/*****planSearchByUuid*****/
//						
//			List<PlanInfo> planList = proxy.getConnection().planSearchByUuid("20130403002");
//			
//			System.out.println(planList.get(0).getUser()+"\n");
//			System.out.println(planList.get(0).getUuid()+"\n");
			
			/*****planOffer*****/
//			in = new DataInputStream(new FileInputStream(file));			
//			in.readFully(buffer);	        
//	        list.add(new String(buffer));  
//			proxy.getConnection().planOffer(list);
			
			/*****planSearchByFilter*****/			
			
//			
			PlanSearchFilter filter = new PlanSearchFilter();
			filter.setUser("a");
			
			PlanSearchFilterResult result = proxy.getConnection().planSearchByFilter("1", "10", filter);
			
		} catch (RTSchException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	private RTISchRestInterface RTISch;
	
	public RTISchRestProxy (String ip, String port, String wsName){
		
		JacksonJsonProvider provider = new JacksonJsonProvider();
		List <Object> providerList = new ArrayList <Object> ();
		providerList.add(provider);
		
//		"http://localhost:8080/RTISchRest/GloriaRTISch/"
		String url = "http://" + ip + ":" + port + "/" + wsName + "/";
		
		RTISch = JAXRSClientFactory.create(url, RTISchRestInterface.class, providerList);
	}
	
	public RTISchRestInterface getConnection(){
		
		return RTISch;
	}

}
