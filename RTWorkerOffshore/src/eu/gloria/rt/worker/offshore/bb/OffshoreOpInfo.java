package eu.gloria.rt.worker.offshore.bb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class OffshoreOpInfo {
	
	private List<String> targetIdList;
	private List<String> opIdList;
	
	public static OffshoreOpInfo getInfo (String jsonContent) throws Exception{
		
		OffshoreOpInfo result = new OffshoreOpInfo();
		
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
		Map<String, Object> map = (Map<String, Object>) info;
		
		@SuppressWarnings("unchecked")
		ArrayList<Object> targets = (ArrayList<Object>) map.get("targets");
		
		for (int x = 0; x < targets.size(); x++){
			
			result.getTargetIdList().add(targets.get(x).toString());
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Object> ops = (ArrayList<Object>) map.get("plans");
		
		for (int x = 0; x < ops.size(); x++){
			
			result.getOpIdList().add(ops.get(x).toString());
		}
		
		return result;
	}
	
	public OffshoreOpInfo(){
		opIdList = new ArrayList<String>(); 
		targetIdList = new ArrayList<String>(); 
	}

	public List<String> getTargetIdList() {
		return targetIdList;
	}

	public void setTargetIdList(List<String> targetIdList) {
		this.targetIdList = targetIdList;
	}

	public List<String> getOpIdList() {
		return opIdList;
	}

	public void setOpIdList(List<String> opIdList) {
		this.opIdList = opIdList;
	}
	
	public String toJSON() throws Exception{
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		Map<String,Object> info = new HashMap<String,Object>();
		info.put("targets", getTargetIdList());
		info.put("plans", getOpIdList());
		
		return mapper.writeValueAsString(info);
		
	}

}
