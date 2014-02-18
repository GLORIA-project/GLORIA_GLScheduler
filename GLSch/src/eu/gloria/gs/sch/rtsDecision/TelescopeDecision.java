package eu.gloria.gs.sch.rtsDecision;

import java.util.List;
import java.util.logging.Logger;

import eu.gloria.gs.sch.db.telescOP.TelescOPManager;
import eu.gloria.gs.sch.db.telescOP.TelescopeList;
import eu.gloria.gs.sch.db.util.DBUtil;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.tarVisib.TargetVisibility;

public class TelescopeDecision {	
	
	private static final Logger LOG = Logger.getLogger(TelescopeDecision.class.getName());
	
	public int getTargetTelescope (List<Integer> availableTelescopesId, List<Integer> preferredTelescope) throws GLSchException{
		
		LOG.info("Executing operation getTargetTelescope");
		
		int targetTelescope = -1;
		TelescOPManager manager = new TelescOPManager();
		
		if (availableTelescopesId.contains(preferredTelescope)){	
			if (preferredTelescope.size() == 1)
				targetTelescope = preferredTelescope.get(0);
			else
				targetTelescope = (int)(manager.getMinTelescopeOffer(preferredTelescope)).getId();
		}else{
			targetTelescope = (int)(manager.getMinTelescopeOffer(availableTelescopesId)).getId();
		}
		
//		manager.incrementTelescopeOffer(targetTelescope);
		
		manager.close();
		
		LOG.info("End of  operation getTargetTelescope " + targetTelescope);
		return targetTelescope;
	}

}
