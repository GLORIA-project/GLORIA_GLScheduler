package eu.gloria.rt.worker.offshore.bb;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.gloria.tools.log.LogUtil;



public class BBGateway {
	
	private String host;
	private String port;
	private String appName;
	private boolean secure;
	private boolean paramEscape;
	
	public BBGateway(String host, String port, String appName, boolean secure, boolean paramEscape){
		
		this.host = host;
		this.port = port;
		this.appName = appName;
		this.secure = secure;
		this.paramEscape = paramEscape;
		
	}
	
	
	protected String getUrl (String cmd, Hashtable<String, String> params) throws UnsupportedEncodingException{
		
		StringBuilder urlBuffer= new StringBuilder();
		
		//Protocol
		if (secure){
			urlBuffer.append("https://");
		}else{
			urlBuffer.append("http://");
		}
		
		
		//Host & Port
		if (port == null || port.trim().isEmpty()){
			urlBuffer.append(getHost()).append("/");
		}else{
			urlBuffer.append(getHost()).append(":").append(getPort()).append("/");
		}
		
		
		//AppName
		if (getAppName() != null && !getAppName().trim().isEmpty()){
			urlBuffer.append(getAppName()).append("/");
		}
		
		//CMD
		urlBuffer.append(cmd);
		
		//GET Parameters
		if (params.size() > 0){ //adds the parameters
			urlBuffer.append("?");
			
			Enumeration<String> keys = params.keys();
			String key;
			String value;
			while (keys.hasMoreElements()){
				key = keys.nextElement();
				value = params.get(key);
				if (paramEscape)
					urlBuffer.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
				else
					urlBuffer.append(key).append("=").append(value);
				if (keys.hasMoreElements()){
					urlBuffer.append("&");
				}
			}			
		}
		
		return urlBuffer.toString();
	}
	
	public String execute(String cmd, String[] paramNames, String[] paramValues) throws BBCommunicationException{
		
		try{
			
			Hashtable<String, String> params = getParamMap(paramNames, paramValues);
			
			String urlString = getUrl(cmd, params);
			
			LogUtil.info(this, "BB.execute. URL=" + urlString);
			
			URL url = new URL(urlString);
		
			URLConnection urlConnection = url.openConnection();
			
			HttpURLConnection httpUrlConnection = (HttpURLConnection)urlConnection;
			InputStream error;
			
			if (httpUrlConnection.getResponseCode() == 400) {
				error = httpUrlConnection.getErrorStream();
				
				DataInputStream errorStream = new DataInputStream(error);
				String inputLine;	
				StringBuffer errorString = new StringBuffer();

				while ((inputLine = errorStream.readLine()) != null) {
					errorString.append(inputLine);
				}

				errorStream.close();
			    
			    if (errorString.toString().contains("not authorized to write to the device"))			    
			    	throw new BBCommunicationException(errorString.toString());
			    else
			    	throw new BBCommunicationException("Server returned HTTP response code: 400");
			    
			}else{	
				
				StringBuffer sb = new StringBuffer();

				DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
				String inputLine;			


				while ((inputLine = dis.readLine()) != null) {
					sb.append(inputLine);
				}

				dis.close();

				LogUtil.info(this, "BB JSON RESPONSE=" + sb.toString());

				return sb.toString();
			}
			
		} catch (MalformedURLException me) {
			
			LogUtil.severe(this, "BB JSON MalformedURLException: " + me);
            BBCommunicationException newEx = new BBCommunicationException("MalformedURLException. " + me.getMessage());
            throw newEx;
            
        } catch (IOException ioe) {
        	
            LogUtil.severe(this, "BB JSON IOException: " + ioe);
            BBCommunicationException newEx = new BBCommunicationException("IOException. " + ioe.getMessage());
            throw newEx;
            
        }
		
	}
	
	public String targetCreate(String name, String ra, String dec) throws Exception {
		
		try{
			String[] paramNames = {
					"tn",
					"ra",
					"dec"
			};
			
			String[] paramValues = {
					name,
					ra,
					dec
			};
			
			String jsonContent = execute("create_target", paramNames, paramValues);
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
			Map<String, Object> map = (Map<String, Object>) info;
			if (!map.get("ret").toString().equals("1")) {
				throw new BBCommunicationException("The operation cannot be executed.");
			}
			
			return map.get("id").toString();
			
		}catch(Exception ex){
			LogUtil.severe(this, "targetCreate(). Error:" + ex.getMessage());
			throw ex;
		}
		
	}
	
	public boolean targetDelete(String id) throws Exception {
		
		try{
			String[] paramNames = {
					"id"
			};
			
			String[] paramValues = {
					id,
			};
			
			String jsonContent = execute("delete_target", paramNames, paramValues);
			
			return isRightReturnControl(jsonContent);
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "targetDelete(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}
	
	
	public String opCreate(String targetId, String exposure, String minAltitude, String maxMoonaltitude, String minMoonDistace, String filter, String uuid) throws Exception {
		
		try{
			String[] paramNames = {
					"id",
					"exposure",
					"min_altitude",
					"max_moon_altitude",
					"min_moon_distance",
					"filter",
					"uuid"
			};
			
			String[] paramValues = {
					targetId,
					exposure,
					minAltitude,
					maxMoonaltitude,
					minMoonDistace,
					filter,
					uuid
			};
			
			String jsonContent = execute("schedule", paramNames, paramValues);
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
			Map<String, Object> map = (Map<String, Object>) info;
			if (!map.get("ret").toString().equals("1")) {
				throw new BBCommunicationException("The operation cannot be executed.");
			}
			
			return map.get("id").toString();
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "opCreate(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}
	
	public boolean opCancel(String opId) throws Exception {
		
		try{
			String[] paramNames = {
					"id",
			};
			
			String[] paramValues = {
					opId
			};
			
			String jsonContent = execute("cancel", paramNames, paramValues);
			
			return isRightReturnControl(jsonContent);
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "opCancel(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}
	
	public boolean opConfirm(String opId) throws Exception {
		
		try{
			String[] paramNames = {
					"id",
			};
			
			String[] paramValues = {
					opId
			};
			
			String jsonContent = execute("confirm", paramNames, paramValues);
			
			return isRightReturnControl(jsonContent);
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "opConfirm(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}
	
	public BBObservingPlanState opState(String opId) throws Exception {
		
		try{
			String[] paramNames = {
					"id"
			};
			
			String[] paramValues = {
					opId
			};
			
			String jsonContent = execute("status", paramNames, paramValues);
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
			Map<String, Object> map = (Map<String, Object>) info;
			if (!map.get("ret").toString().equals("1")) {
				throw new BBCommunicationException("The operation cannot be executed.");
			}
			
			String state =  map.get("status").toString();
			return BBObservingPlanState.valueOf(state);
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "opState(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}
	
	public BBObservingPlanInfo opInfo(String opId) throws Exception {
		
		try{
			
			BBObservingPlanInfo result = null;
			
			String[] paramNames = {
					"id"
			};
			
			String[] paramValues = {
					opId
			};
			
			String jsonContent = execute("list", paramNames, paramValues);
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
			Map<String, Object> map = (Map<String, Object>) info;
			if (!map.get("ret").toString().equals("1")) {
				throw new BBCommunicationException("The operation cannot be executed.");
			}
			
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> opList = (ArrayList<Object>) map.get("plans");
			
			if (opList == null || opList.size() != 1) throw new BBCommunicationException("The Observing Plan does not exist or there is more than one.");
			
			HashMap<String, Object> op = (HashMap<String, Object>) opList.get(0);
				
			result = new BBObservingPlanInfo();
			result.setState(BBObservingPlanState.valueOf(op.get("status").toString()));
			result.setMinMoonDistance(op.get("min_moon_distance").toString());
			result.setTargetId(op.get("target_id").toString());
			result.setMinAltitude(op.get("min_altitude").toString());
			result.setId(op.get("id").toString());
			result.setExposure(op.get("exposure").toString());
			result.setName(op.get("name").toString());
			result.setUuid(op.get("uuid").toString());
			result.setFilter(op.get("filter").toString());
			result.setRa(op.get("ra").toString());
			result.setMaxMoonAltitude(op.get("max_moon_altitude").toString());
			result.setDec(op.get("dec").toString());
			
			result.setImgs(imgList(opId));
			
			return result;
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "opInfo(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}

	
	public List<BBImageInfo> imgList(String opId) throws Exception {
		
		try{
			ArrayList<BBImageInfo> result = new ArrayList<BBImageInfo>();
			
			String[] paramNames = {
					"id"
			};
			
			String[] paramValues = {
					opId
			};
			
			String jsonContent = execute("list_images", paramNames, paramValues);
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
			Map<String, Object> map = (Map<String, Object>) info;
			if (!map.get("ret").toString().equals("1")) {
				throw new BBCommunicationException("The operation cannot be executed.");
			}
			
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> images = (ArrayList<Object>) map.get("images");
			
			for (int x = 0; x < images.size(); x++){
				
				HashMap<String, Object> img = (HashMap<String, Object>) images.get(x);
				
				BBImageInfo item = new BBImageInfo();
				item.setUrlFits(img.get("url").toString());
				item.setUrlJpg(img.get("preview").toString());
				item.setOpId(img.get("plan_id").toString());
				item.setImgId(img.get("id").toString());
				item.setFileName(img.get("filename").toString());
				
				result.add(item);
			}
			
			return result;
			
		}catch(Exception ex){
			
			LogUtil.severe(this, "imgList(). Error:" + ex.getMessage());
			throw ex;
			
		}
		
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}


	public boolean isSecure() {
		return secure;
	}


	public void setSecure(boolean secure) {
		this.secure = secure;
	}
	
	private Hashtable<String, String> getParamMap(String[] names, String[] values){
		
		Hashtable<String, String> result = new Hashtable<String, String>();
		
		for(int x = 0; x < names.length; x++ ){
			if (names[x] != null && values[x] != null){
				result.put(names[x], values[x]);
			}
		}
		
		return result;
		
	}
	
	
	public boolean isRightReturnControl (String jsonContent) throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> info = (HashMap<String, Object>) mapper.readValue(jsonContent, Object.class);
		Map<String, Object> map = (Map<String, Object>) info;
		if (map.get("ret").toString().equals("1")) 
			return true;
		else 
			return false;

	}
	

}
