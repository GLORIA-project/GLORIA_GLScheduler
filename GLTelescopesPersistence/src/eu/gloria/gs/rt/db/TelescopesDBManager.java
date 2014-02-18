package eu.gloria.gs.rt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import eu.gloria.gs.rt.db.util.CCDProperties;
import eu.gloria.gs.rt.db.util.DBUtil;



public class TelescopesDBManager {
	
	
		
	public static void main(String[] args){		
		
		try {
			TelescopesDBManager manager = new TelescopesDBManager();
			
			System.out.println (manager.searchTelescopeFilters(6));
			System.out.println (manager.searchTelescopeFilters(7));
			System.out.println (manager.searchTelescopeFilters(15));
			System.out.println (manager.searchTelescopeFilters(14));
			System.out.println (manager.searchTelescopeFilters(8));
			System.out.println (manager.searchTelescopeFilters(10));
			System.out.println (manager.searchTelescopeFilters(11));
//			System.out.println (manager.searchTelescopeByConstantConstraints((double) 14, 0.5) ); 
//			System.out.println (manager.searchTelescopeByBinning("1x1"));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}
	
	private static TelescopesDBManager manager = null;
	
	public static TelescopesDBManager getTelescopesDBManager (){
		
		if (manager == null)						
			manager = new TelescopesDBManager();
				
			
		return manager;
	}
	
	private TelescopesDBManager()  {
		
		//Hay que parametrizar la BBDD y user:pass. Esperar hasta ver cómo se enlaza con GLORIA
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
			
		
		
	}
	
	private Connection getConection () throws SQLException{
		
		return DriverManager.getConnection ("jdbc:mysql://localhost:3306/telescopes?autoReconnect=true","", "");
	}
	
	public TelescopeConnection getTelescopeConnection (int telescopeId) throws SQLException{
		
		TelescopeConnection telescopeConnection = new TelescopeConnection();
		
		Statement s;
		ResultSet rs;
		
		Connection connection = getConection();

		s = connection.createStatement();
		rs = s.executeQuery ("SELECT ip, port, user, pass FROM telescopes.TelescopeConnection where telescopeId='"+telescopeId+"'");

		rs.next();
		telescopeConnection.setIp(rs.getNString(1));
		telescopeConnection.setPort(rs.getNString(2));
		telescopeConnection.setUser(rs.getNString(3));
		telescopeConnection.setPass(rs.getNString(4));

		
		connection.close();
		
		return telescopeConnection;
		
	}
	
	public List <Integer> searchTelescopeByFilterType (String type) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;
		
		Connection connection = getConection();

		s = connection.createStatement();
		rs = s.executeQuery ("select telescopeId from TelescopeCCDList where ccdId in " +
				"(select ccdId from CCDFilterWheelList where filterWheelId in " +
				"(select wheelId from CCDFilterList where filterId in " +
				"(select id from GLFilterList where description = '"+type+"')))");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
				
		return telescopeList;
		
	}
	
	public List <Integer> searchTelescopeByGenericFilterType (String type) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;
		
		Connection connection = getConection();

		s = connection.createStatement();
		rs = s.executeQuery ("select telescopeId from TelescopeCCDList where ccdId in " +
				"(select ccdId from CCDFilterWheelList where filterWheelId in " +
				"(select wheelId from CCDFilterList where filterId in " +
				"(select id from GLFilterList where description like '%_"+type+"')))");



		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
				
		return telescopeList;
		
	}
	
	public List <Integer> searchTelescopeByConstantConstraints (Double seeing, Double FoV) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("SELECT telescopeId FROM TelescopeProperties where telescopeId in " +
				"(select telescopeId from TelescopeCCDList where FoV>"+FoV+") and seeing <" + seeing +"and mode >=1");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
		
		
		return telescopeList;
		
	}
	
	public List <Integer> searchTelescopeByConstantConstraintsSeeing (Double seeing) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("SELECT telescopeId FROM TelescopeProperties where seeing <" + seeing +"and mode >=1");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
		
		
		return telescopeList;
		
	}
	
	public List <Integer> searchTelescopeByConstantConstraintsFoV (Double FoV) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("SELECT telescopeId FROM TelescopeProperties where telescopeId in " +
				"(select telescopeId from TelescopeCCDList where FoV>"+FoV+") and mode >=1");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
		
		
		return telescopeList;
		
	}
	
	public List <Integer> searchTelescopeByConstantConstraintsMode () throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("SELECT telescopeId FROM TelescopeProperties where mode >=1");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
		
		
		return telescopeList;
		
	}
	
	public List <String> searchTelescopeFilters (int  telescopeId) throws SQLException{
		
		List <String> filterList = new ArrayList <String>();
		
		Statement s;
		ResultSet rs;
		
		Connection connection = getConection();

		s = connection.createStatement();
		rs = s.executeQuery("select description from GLFilterList where id in " +
				"(select filterId from CCDFilterList where wheelId in" +
				"(select filterWheelId from CCDFilterWheelList where ccdId in" +
				"(select ccdId from TelescopeCCDList where telescopeId =" + telescopeId + ")))");
		
		

		while (rs.next()) 
		{ 
			filterList.add(rs.getString(1));

		}

		connection.close();
				
		return filterList;
		
	}
	
	public List <Integer> searchCCDByBinning (String binning) throws SQLException{
		
		List <Integer> telescopeList = new ArrayList <Integer>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("select ccdId from TelescopeCCDList join CCDBinning on " +
				"TelescopeCCDList.ccdId=CCDBinning.ccdId where CCDBinning.binning='"+binning+"'");


		while (rs.next()) 
		{ 
			telescopeList.add(rs.getInt (1));

		}

		connection.close();
		
		
		return telescopeList;
		
	}
	
	public List <String> searchCCDBinning (int ccdId) throws SQLException{
		
		List <String> binningList = new ArrayList <String>();
		
		Statement s;
		ResultSet rs;

		Connection connection = getConection();
		
		s = connection.createStatement();
		rs = s.executeQuery ("select binning from CCDBinning where ccdId="+ ccdId);
		
		
		while (rs.next()) 
		{ 
			binningList.add(rs.getString(1));

		}

		connection.close();
		
		
		return binningList;
		
	}
	
	public List <CCDProperties> searchCCDByPixelScale (Double pixelScale, String mode) throws Exception{
		
		List <CCDProperties> ccdList = new ArrayList <CCDProperties>();
		
		Statement s;
		ResultSet rs = null;

		Connection connection = getConection();
		
		s = connection.createStatement();
		
		if (mode.equals("MAX"))
			rs = s.executeQuery ("select ccdId, pixelSixe, focalLength from telescopes.TelescopeCCDList where pixelSize/focalLength*206.3>"+pixelScale);
		else if (mode.equals("MIN"))
			rs = s.executeQuery ("select ccdId, pixelSixe, focalLength from telescopes.TelescopeCCDList where pixelSize/focalLength*206.3<"+pixelScale);
		else
			throw new Exception ("Error in mode value");
		
		while (rs.next()) 
		{ 
			CCDProperties properties = null;
			
			properties.setCcdid(rs.getInt (1));
			properties.setPixelSize(rs.getDouble(2));
			properties.setFocalLength(rs.getDouble(3));
			
			ccdList.add(properties);

		}

		connection.close();
		
		
		return ccdList;
		
	}
	
	
}
