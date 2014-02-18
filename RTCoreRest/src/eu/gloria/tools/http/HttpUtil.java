package eu.gloria.tools.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import eu.gloria.tools.log.LogUtil;

/**
 * Http Utilities class.
 * 
 * @author jcabello
 *
 */
public class HttpUtil {
	
	public static String readHTTPContent(String url) throws IOException{
		
		LogUtil.info(null, "HttpUtil.readHTTPContent.BEGIN");
		
		StringBuilder sb = new StringBuilder();
	    URL pagina = new URL(url);
	    
	    BufferedReader in = null;
	    try{
	    	in = new BufferedReader(new InputStreamReader(pagina.openStream()));
	    	String input;
	    	while ((input = in.readLine()) != null){
	    		sb.append(input);
	    	}
	    }finally{
	    	if (in != null){
	    		in.close();
	    	}
	    }
	    
	    LogUtil.info(null, "HttpUtil.readHTTPContent.END");
	    
	    return sb.toString();
	}

}
