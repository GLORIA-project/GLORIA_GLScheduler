package eu.gloria.gs.sch.entity;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import eu.gloria.gs.sch.entity.op.Constraints;
import eu.gloria.gs.sch.entity.op.Instructions;
import eu.gloria.gs.sch.entity.op.Metadata;
import eu.gloria.gs.sch.entity.op.Plan;
import eu.gloria.gs.sch.entity.op.TargetAltitudeInfo;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.uuid.op.UUID;



public class GloriaOPlan {
	
	private Plan root;
	
	private String moonDistanceDefault =  Config.getProperty("DEV_sch", "moonDistance");
	private String moonAltitudeDefault =  Config.getProperty("DEV_sch", "moonAltitude");
	private String targetAltitudeDefault =  Config.getProperty("DEV_sch", "targetAltitude");
	
	
	public GloriaOPlan (String is) throws GLSchException{
		
		try{
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
			mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			root = mapper.readValue(is, Plan.class);
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new GLSchException(ex.getMessage());
		}
		
	}
	
	public void setUuid(String value){
		if (root.getMetadata().getUuid() == null){				
			root.getMetadata().setUuid(value);			
		}
	}
	
	
	public Metadata getMetadata() {

		Metadata result = new Metadata();
		
		if (root != null && root.getMetadata() != null){
			
			result.setUuid(root.getMetadata().getUuid());
			result.setUser(root.getMetadata().getUser());
			result.setPriority(root.getMetadata().getPriority());
			result.setDescription(root.getMetadata().getDescription());
			
			
//			if (root.getMetadata().getPredictedExecIni() != null){
//				result.setPredictedExecIni(root.getMetadata().getPredictedExecIni().toGregorianCalendar());
//			}
//			
//			if (root.getMetadata().getPredictedExecEnd() != null){
//				result.setPredictedExecEnd(root.getMetadata().getPredictedExecEnd().toGregorianCalendar());
//			}
//			
//			if (root.getMetadata().getPredictedExecTime() != null){
//				result.setPredictedExecTime(root.getMetadata().getPredictedExecTime());
//			}
			
		}		
		
		return result;
	}
	
	public Constraints getConstraints() {
		
		Constraints result = new Constraints();
		
		if (root != null && root.getConstraints() != null){
			
			
			if (root.getConstraints().getMoonAltitude() != null){				
				result.setMoonAltitude(root.getConstraints().getMoonAltitude());
			}else{
				result.setMoonAltitude(Double.parseDouble(moonAltitudeDefault));
			}
			
			if (root.getConstraints().getMoonDistance() != null){				
				result.setMoonDistance(root.getConstraints().getMoonDistance());
			}else{
				result.setMoonDistance(Double.parseDouble(moonDistanceDefault));
			}
			
			if ((root.getConstraints().getTargetAltitudeInfo().getAirmass() != null) || (root.getConstraints().getTargetAltitudeInfo().getTargetAltitude() != null)){
				result.setTargetAltitudeInfo(root.getConstraints().getTargetAltitudeInfo());
			}else{
				TargetAltitudeInfo info = new TargetAltitudeInfo();
				info.setTargetAltitude(Double.parseDouble(targetAltitudeDefault));
				
				result.setTargetAltitudeInfo(info);
			}
			
			if (root.getConstraints().getTargets() != null && root.getConstraints().getTargets().getTarget() != null) {
				
				result.setTargets(root.getConstraints().getTargets());
				
			}
			
		}
		
		
		return result;
		
		
	}
	
	public Instructions  getInstructions() {
		
		
		return root.getInstructions();
		
		
	}
	

}
