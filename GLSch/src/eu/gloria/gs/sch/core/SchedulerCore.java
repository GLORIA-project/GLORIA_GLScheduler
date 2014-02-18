package eu.gloria.gs.sch.core;

import java.util.ArrayList;
import java.util.List;

import eu.gloria.gs.sch.entity.GloriaOPlan;
import eu.gloria.gs.sch.entity.op.Constraints;
import eu.gloria.gs.sch.entity.op.SimpleExposure;
import eu.gloria.gs.sch.exception.GLSchException;
import eu.gloria.gs.sch.hardConst.ConstantConstraint;
import eu.gloria.gs.sch.hardConst.PixelScale;
import eu.gloria.gs.sch.tarVisib.TargetVisibility;

public class SchedulerCore implements Runnable {

	private Double seeing = null;
	private Double FoV = null;
	private String filterType = null;
	private Double pixelScaleMax = null;
	private Double pixelScaleMin = null;
	private SimpleExposure exposure = null;
	private GloriaOPlan gloriaOP = null;
	private String uuid = null;
	
	List <Integer> telescopeList = new ArrayList <Integer> ();
	
	
	public SchedulerCore (GloriaOPlan gloriaOp, SimpleExposure exposure, String uuid) throws GLSchException{
			
		
		this.gloriaOP = gloriaOp;
		this.exposure = exposure;
		
		this.uuid = uuid;
		
		this.seeing = gloriaOp.getConstraints().getSeeing();
		this.FoV = gloriaOp.getConstraints().getFoV();
		this.filterType = exposure.getFilter().toString();
		
		if (gloriaOp.getConstraints().getPixelScale() != null){
			this.pixelScaleMax= gloriaOp.getConstraints().getPixelScale().getMax();
			this.pixelScaleMin= gloriaOp.getConstraints().getPixelScale().getMin();
			this.exposure = exposure;

			if (pixelScaleMin > pixelScaleMax)
				throw new GLSchException("Invalid constraint value in pixelScale");
		}
		
	}


	public void run() {
		
		try {
			ConstantConstraint cc = new ConstantConstraint(seeing, FoV, filterType);
			telescopeList = cc.evaluate();
			
			if ((pixelScaleMax != null) & (pixelScaleMin != null)){
				PixelScale ps = new PixelScale(pixelScaleMax, pixelScaleMin);
				if (!telescopeList.isEmpty())
					telescopeList.retainAll(ps.evaluate());
				else
					telescopeList = ps.evaluate();
			}
			
			TargetVisibility visibility = new TargetVisibility (telescopeList, gloriaOP, exposure, uuid);
			visibility.evaluate();
			
			
		} catch (GLSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
