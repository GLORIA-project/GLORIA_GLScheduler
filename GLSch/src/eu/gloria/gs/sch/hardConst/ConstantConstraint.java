package eu.gloria.gs.sch.hardConst;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.sch.Scheduler;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.rt.entity.device.FilterType;

public class ConstantConstraint {
	
	public static void main(String[] args){	
		
		ConstantConstraint cc;
		try {
			cc = new ConstantConstraint(0.5, 30.0, "GENERIC_V");
			System.out.println (cc.evaluate());
		} catch (GLSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
	/***********************************************************************************************************/
	
	Double seeing = null;
	Double FoV = null;
	String filterType = null;
	
	private TelescopesDBManager manager= null;
	
	private static final Logger LOG = Logger.getLogger(ConstantConstraint.class.getName());
	
	public ConstantConstraint (Double seeing, Double FoV, String type) throws GLSchException{
					
		this.seeing = seeing;
		this.FoV = FoV;
		this.filterType = type;
		
		manager = TelescopesDBManager.getTelescopesDBManager();
		
	}
	
	public List <Integer> evaluate() throws GLSchException{
		
		List <Integer> telescopesByType = new ArrayList <Integer>();
		List <Integer> telescopesByConstr = new ArrayList <Integer>();
		
		String data[] = filterType.split("GENERIC_");
		
		String pattern;
		try {
			if (data.length>1){
				pattern = data[1];
				telescopesByType = manager.searchTelescopeByGenericFilterType(pattern);
			}else{
				pattern = filterType;
				telescopesByType = manager.searchTelescopeByFilterType(pattern);
			}
			
			LOG.info("Telescopes with filter: "+filterType+" "+ telescopesByType+"\n");

			if ((seeing != null) & (FoV != null))
				telescopesByConstr = manager.searchTelescopeByConstantConstraints(seeing, FoV);
			else if (seeing != null)
				telescopesByConstr = manager.searchTelescopeByConstantConstraintsSeeing(seeing);
			else if (FoV != null)
				telescopesByConstr = manager.searchTelescopeByConstantConstraintsFoV(FoV);
			else
				telescopesByConstr = manager.searchTelescopeByConstantConstraintsMode();
				
			LOG.info("Telescopes with proper constraints: " + telescopesByConstr+"\n");
		} catch (SQLException e) {
			throw new GLSchException (e.getMessage());
		}
		
		telescopesByType.retainAll(telescopesByConstr);
		
		LOG.info("Telescopes with proper constraints & filter: " + telescopesByType+"\n");
		
		return telescopesByType;
	}

}
