package eu.gloria.tools.runtime;


import java.io.InputStream;

import eu.gloria.rt.exception.RTException;
import eu.gloria.tools.log.LogUtil;

public class Executor {
	
	
	public ExecResult execute(String script, String[] params) throws RTException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(script);
		sb.append(" ");
		
		if (params != null){
			for (int x = 0; x < params.length; x++){
				sb.append(params[x]);
				sb.append(" ");
			}
		}
		
		String cmd = sb.toString();
		
		String[] names = {"cmd"};
		String[] values = {cmd,};
		
		LogUtil.info(this, "Executor. Executing:" + LogUtil.getLog(names, values));
		
		Process proc;
		int exCode = 0;
		StringBuffer ret = new StringBuffer();
		try {
			
			proc = Runtime.getRuntime().exec(cmd);
			InputStream is = proc.getInputStream();
			int size;
			String s;
			exCode = proc.waitFor();
			
			while((size = is.available()) != 0) {
				byte[] b = new byte[size]; 
				is.read(b);
				s = new String(b);
				ret.append(s);
			}
			
		} catch (Exception ex) {
			String[] names2 = {"cmd", "exception"};
			String[] values2 = {cmd, ex.getMessage()};
			throw new RTException("Error executing CMD. " + LogUtil.getLog(names2, values2));
		}
		
		ExecResult result = new ExecResult();
		result.setCode(exCode);
		result.setOutput(ret.toString());
		
		return result;
		
	}

}
