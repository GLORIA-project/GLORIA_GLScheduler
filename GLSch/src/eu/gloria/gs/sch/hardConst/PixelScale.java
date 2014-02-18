package eu.gloria.gs.sch.hardConst;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.gloria.gs.rt.db.TelescopesDBManager;
import eu.gloria.gs.rt.db.util.CCDProperties;
import eu.gloria.gs.sch.exception.GLSchException;

/**
 * pixelScale = (pixelSixe(um)/focalLenght(mm))*18*36/pi*binning
 * 
 * @author mclopez
 *
 */
public class PixelScale {
	
	public static void main(String[] args){	
		
	}
	
	/***********************************************************************************************************/
	
	Double pixelScaleMax = null;
	Double pixelScaleMin = null;
	
	List <CCDProperties> ccdList = null;
	
	private static final Logger LOG = Logger.getLogger(PixelScale.class.getName());
	
	private TelescopesDBManager manager= null;
	
	public PixelScale (Double pixelScaleMax, Double pixelScaleMin){
		
		this.pixelScaleMax = pixelScaleMax;
		this.pixelScaleMin = pixelScaleMin;
		
		this.ccdList = new ArrayList <CCDProperties> ();
		
		manager = TelescopesDBManager.getTelescopesDBManager();
	}
	
	public List <Integer> evaluate () throws GLSchException {
		
		List <Integer> telescopes = new ArrayList <Integer>();
		List <CCDProperties> ccdPropertiesList = null;		
		
		try {
			ccdPropertiesList = manager.searchCCDByPixelScale(pixelScaleMax, "MAX");
			
			for (CCDProperties properties : ccdPropertiesList){
				
				List <String> binningList = manager.searchCCDBinning(properties.getCcdid());
				
				for (String binning: binningList){
					
					if (binning != null){
						String data[] = binning.split("x");
						int binX = Integer.parseInt(data[0]);
						int binY = Integer.parseInt(data[1]);
						
						//Only symmetric binning
						if (binX==binY){						
							if ((properties.getPixelSize()/properties.getFocalLength()*206.3*binX) < pixelScaleMin){
								CCDProperties ccd = new CCDProperties();
								ccd.setCcdid(properties.getCcdid());
								ccd.setBinning(binX);
								ccdList.add (ccd);
								
								if (telescopes.indexOf(properties.getCcdid()) == -1)
									telescopes.add(properties.getCcdid());
							}
						}
						
					}
				}
				
			}		
			
		} catch (Exception e) {
			throw new GLSchException (e.getMessage());
		}		
				
		return telescopes;
	}

	
	public List<CCDProperties> getCcdList() {
		return ccdList;
	}
}
