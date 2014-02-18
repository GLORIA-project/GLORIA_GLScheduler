package eu.gloria.rti.sch.core;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;

import eu.gloria.rt.entity.scheduler.plan.CameraSettings;
import eu.gloria.rt.entity.scheduler.plan.Expose;
import eu.gloria.rt.entity.scheduler.plan.Loop;
import eu.gloria.rt.entity.scheduler.plan.Plan;
import eu.gloria.rt.entity.scheduler.plan.Target;
import eu.gloria.rt.exception.RTException;
import eu.gloria.rt.exception.RTSchException;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintMoonAltitude;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintMoonDistance;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintTarget;
import eu.gloria.rti.sch.core.plan.constraint.ConstraintTargetAltitude;
import eu.gloria.rti.sch.core.plan.constraint.Constraints;
import eu.gloria.rti.sch.core.plan.constraint.Coordinates;
import eu.gloria.rti.sch.core.plan.constraint.J2000;
import eu.gloria.rti.sch.core.plan.instruction.Binning;
import eu.gloria.rti.sch.core.plan.instruction.Instruction;
import eu.gloria.tools.log.LogUtil;


public class ObservingPlan extends eu.gloria.rti.sch.core.ObservingPlanBase {
	
	private Plan root;
	
	public ObservingPlan(String file, String xsdFile) throws RTException{
		
		try{
			File schemaFile = new File(xsdFile);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);

			JAXBContext context = JAXBContext.newInstance(Plan.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setSchema(schema);
			File file2 = new File(file);
			root = (Plan) unmarshaller.unmarshal(file2);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RTException(ex.getMessage());
		}
		
	}
	
	public ObservingPlan(InputStream is, String xsdFile) throws RTException{
		
		try{
			File schemaFile = new File(xsdFile);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);

			JAXBContext context = JAXBContext.newInstance(Plan.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setSchema(schema);
			root = (Plan) unmarshaller.unmarshal(is);	
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RTException(ex.getMessage());
		}
		
	}
	
	public ObservingPlan(String is) throws RTException{
		
		try{      
			
//			eu.gloria.rt.entity.scheduler.plan2.Plan plan = null;
			
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
			mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			root = mapper.readValue(is, Plan.class);
			
//			eu.gloria.rt.entity.scheduler.plan.Metadata rootMetadata = new eu.gloria.rt.entity.scheduler.plan.Metadata();
//			rootMetadata.setDescription(plan.getMetadata().getDescription());
//			rootMetadata.setPredictedExecEnd(plan.getMetadata().getPredictedExecEnd());
//			rootMetadata.setPredictedExecIni(plan.getMetadata().getPredictedExecIni());
//			rootMetadata.setPredictedExecTime(plan.getMetadata().getPredictedExecTime());
//			rootMetadata.setPriority(plan.getMetadata().getPriority());
//			rootMetadata.setType(eu.gloria.rt.entity.scheduler.plan.PlanType.valueOf(plan.getMetadata().getType().toString()));
//			rootMetadata.setUser(plan.getMetadata().getUser());
//			rootMetadata.setUuid(plan.getMetadata().getUuid());
//			
//			root.setMetadata(rootMetadata);
//			
//			eu.gloria.rt.entity.scheduler.plan.Constraints rootConstraints = new eu.gloria.rt.entity.scheduler.plan.Constraints();
//			eu.gloria.rt.entity.scheduler.plan.PositiveDoubleIterval interval = new eu.gloria.rt.entity.scheduler.plan.PositiveDoubleIterval();
//			interval.setMax(plan.getConstraints().getExposureTime().getMax());
//			interval.setMin(plan.getConstraints().getExposureTime().getMin());
//			rootConstraints.setExposureTime(interval);
//			
//			List <eu.gloria.rt.entity.scheduler.plan2.FilterType> filters = (List<FilterType>) plan.getConstraints().getFilters();
//			eu.gloria.rt.entity.scheduler.plan.Filters rootFilters = new eu.gloria.rt.entity.scheduler.plan.Filters();
//			for (FilterType filter: filters){
//				rootFilters.getFilter().add(eu.gloria.rt.entity.scheduler.plan.FilterType.valueOf(filter.toString()));				
//			}
//			rootConstraints.setFilters(rootFilters);
//			
//			eu.gloria.rt.entity.scheduler.plan.DoubleIterval interval2 = new eu.gloria.rt.entity.scheduler.plan.DoubleIterval();
//			interval2.setMax(plan.getConstraints().getFoV().getMax());
//			interval2.setMin(plan.getConstraints().getFoV().getMin());
//			rootConstraints.setFoV(interval2);
//			
//			rootConstraints.setFWHM(plan.getConstraints().getFWHM());
//			rootConstraints.setMode(eu.gloria.rt.entity.scheduler.plan.Mode.valueOf(plan.getConstraints().getMode().toString()));
//			rootConstraints.setMoonAltitude(plan.getConstraints().getMoonAltitude());
//			rootConstraints.setMoonDistance(plan.getConstraints().getMoonDistance());
//			rootConstraints.setNotAfterDate(plan.getConstraints().getNotAfterDate());
//			rootConstraints.setNotBeforeDate(plan.getConstraints().getNotBeforeDate());
//			
//			eu.gloria.rt.entity.scheduler.plan.DoubleIterval interval3 = new eu.gloria.rt.entity.scheduler.plan.DoubleIterval();
//			interval3.setMax(plan.getConstraints().getPixelScale().getMax());
//			interval3.setMin(plan.getConstraints().getPixelScale().getMin());
//			rootConstraints.setPixelScale(interval3);
//			
//			rootConstraints.setPointingError(plan.getConstraints().getPointingError());
//			rootConstraints.setReadoutTime(plan.getConstraints().getReadoutTime());
//			rootConstraints.setSeeing(plan.getConstraints().getSeeing());
//			rootConstraints.setTargetAltitude(plan.getConstraints().getTargetAltitude());
//			
//			List <eu.gloria.rt.entity.scheduler.plan2.Target> targets = (List<eu.gloria.rt.entity.scheduler.plan2.Target>) plan.getConstraints().getTargets();
//			eu.gloria.rt.entity.scheduler.plan.Targets rootTargets = new eu.gloria.rt.entity.scheduler.plan.Targets();
//			for (eu.gloria.rt.entity.scheduler.plan2.Target target: targets){
//				eu.gloria.rt.entity.scheduler.plan.Target rootTarget = new eu.gloria.rt.entity.scheduler.plan.Target();
//				rootTarget.setObjName(target.getObjName());
//				eu.gloria.rt.entity.scheduler.plan.Coordinates coordinates = new eu.gloria.rt.entity.scheduler.plan.Coordinates();
//				eu.gloria.rt.entity.scheduler.plan.J2000 j= new eu.gloria.rt.entity.scheduler.plan.J2000();
//				j.setDEC(target.getCoordinates().getJ2000().getDEC());
//				j.setRA(target.getCoordinates().getJ2000().getRA());
//				coordinates.setJ2000(j);
//				rootTarget.setCoordinates(coordinates);	
//				
//				rootTargets.getTarget().add(rootTarget);
//			}						
//			rootConstraints.setTracking(eu.gloria.rt.entity.scheduler.plan.TrackingRateType.valueOf(plan.getConstraints().getTracking().toString()));
//			
//			root.setConstraints(rootConstraints);
//			
//			eu.gloria.rt.entity.scheduler.plan.Instructions rootInstructions = new eu.gloria.rt.entity.scheduler.plan.Instructions();
//			
//			eu.gloria.rt.entity.scheduler.plan.CameraSettings cameraSettings = new eu.gloria.rt.entity.scheduler.plan.CameraSettings();
//			eu.gloria.rt.entity.scheduler.plan.Binning binning = new  eu.gloria.rt.entity.scheduler.plan.Binning();
//			binning.setBinX(plan.getInstructions().getCameraSettings().getBinning().getBinX());
//			binning.setBinY(plan.getInstructions().getCameraSettings().getBinning().getBinY());
//			cameraSettings.setBinning(binning);		
//			
//			rootInstructions.getTargetOrCameraSettingsOrLoop().add(cameraSettings);
//			
//			List <eu.gloria.rt.entity.scheduler.plan2.Expose> exposes = (List<eu.gloria.rt.entity.scheduler.plan2.Expose>) plan.getInstructions().getExpose();
//			
//			for (eu.gloria.rt.entity.scheduler.plan2.Expose expose: exposes){
//				eu.gloria.rt.entity.scheduler.plan.Expose rootExpose = new eu.gloria.rt.entity.scheduler.plan.Expose();
//				rootExpose.setExpositionTime(expose.getExpositionTime());
//				rootExpose.setFilter(eu.gloria.rt.entity.scheduler.plan.FilterType.valueOf(expose.getFilter().toString()));
//				rootExpose.setRepeatCount(expose.getRepeatCount());
//				rootExpose.setRepeatDuration(expose.getRepeatDuration());
//				
//				rootInstructions.getTargetOrCameraSettingsOrLoop().add(rootExpose);
//			}
//				
//				
//			plan.getInstructions().getLoop()
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RTException(ex.getMessage());
		}

	}

	@Override
	public long getPredictedExecTime(IObservingPlanExecTimePredictor predictor) throws RTSchException {
		
		long time = 0;
		
		try{
			
			if (root.getMetadata() != null && root.getMetadata().getPredictedExecTime() != null){
				//The OP contains predicted execution time
				
				Double tmpMillisecs = root.getMetadata().getPredictedExecTime() * 1000;
				time = tmpMillisecs.longValue();
				LogUtil.info(this, "Prediction: estimation time (inside OP)=" + time);
				
			}else{
				//The OP does not contain predicted execution time
				
				if (predictor == null) predictor = new ObservingPlanExecTimePredictor();

				time = predictor.getPredictExecTime(this);
				LogUtil.info(this, "Prediction: estimation time (by ObservingPlanExecTimePredictor) =" + time);
				
			}
			
		}catch(Exception ex){
			LogUtil.info(this, "Prediction: ERROR" + ex.getMessage());
			System.out.println();
			ex.printStackTrace();
		}
		return time;
	}

	@Override
	public Constraints getConstraints() {
		
		Constraints result = new Constraints();
		
		if (root != null && root.getConstraints() != null){
			
			
			if (root.getConstraints().getMoonAltitude() != null){
				ConstraintMoonAltitude moonAltitude = new ConstraintMoonAltitude();
				moonAltitude.setAltitude(root.getConstraints().getMoonAltitude());
				result.setMoonAltitude(moonAltitude);
			}
			
			if (root.getConstraints().getMoonDistance() != null){
				ConstraintMoonDistance moonDistance = new ConstraintMoonDistance();
				moonDistance.setDistance(root.getConstraints().getMoonDistance());
				result.setMoonDistance(moonDistance);
			}
			
			if (root.getConstraints().getTargetAltitude() != null){
				ConstraintTargetAltitude targetAltitude = new ConstraintTargetAltitude();
				targetAltitude.setAltitude(root.getConstraints().getTargetAltitude());
				result.setTargetAltitude(targetAltitude);
			}
			
			if (root.getConstraints().getTargets() != null && root.getConstraints().getTargets().getTarget() != null) {
				
				for (int x = 0; x < root.getConstraints().getTargets().getTarget().size(); x++){
					Target target = root.getConstraints().getTargets().getTarget().get(x);
					ConstraintTarget targetC = new ConstraintTarget();
					
					if (target.getObjName() != null){
						targetC.setObjName(target.getObjName());
					}else{
						targetC.setCoordinates(new Coordinates());
						J2000 radec =  new J2000();
						radec.setDec(target.getCoordinates().getJ2000().getDEC());
						radec.setRa(target.getCoordinates().getJ2000().getRA());
						targetC.getCoordinates().setJ2000(radec);
					}
					
					result.getTargets().add(targetC);
				}
			}
			
		}
		
		
		return result;
		
		
	}

	@Override
	public Metadata getMetadata() {

		Metadata result = new Metadata();
		
		if (root != null && root.getMetadata() != null){
			
			result.setUuid(root.getMetadata().getUuid());
			result.setUser(root.getMetadata().getUser());
			result.setPriority(root.getMetadata().getPriority());
			result.setDescription(root.getMetadata().getDescription());
			
			if (root.getMetadata().getPredictedExecIni() != null){
				result.setPredictedExecIni(root.getMetadata().getPredictedExecIni().toGregorianCalendar());
			}
			
			if (root.getMetadata().getPredictedExecEnd() != null){
				result.setPredictedExecEnd(root.getMetadata().getPredictedExecEnd().toGregorianCalendar());
			}
			
			if (root.getMetadata().getPredictedExecTime() != null){
				result.setPredictedExecTime(root.getMetadata().getPredictedExecTime());
			}
			
		}
		
		
		return result;
	}

	@Override
	public List<Instruction>  getInstructions() {
		
		List<Instruction> result = new ArrayList<Instruction>();
		
		if (root != null && root.getInstructions()!= null && root.getInstructions().getTargetOrCameraSettingsOrLoop() != null){
			
			List<Object> list =  root.getInstructions().getTargetOrCameraSettingsOrLoop();
			
			result.addAll(getInstructions(list));
			
		}
		
				
		return result;
	}
	
	private List<Instruction> getInstructions(List<Object> list){
		
		List<Instruction> result = new ArrayList<Instruction>();
		
		for (int x = 0; x < list.size(); x++){
			
			Object item = list.get(x);
			
			if (item instanceof Target){
				
				Target tmpItemSource = (Target) item;
				eu.gloria.rti.sch.core.plan.instruction.Target tmpItemTarget = new eu.gloria.rti.sch.core.plan.instruction.Target();
				
				tmpItemTarget.setObjName(tmpItemSource.getObjName());
				if (tmpItemSource.getCoordinates() != null && tmpItemSource.getCoordinates().getJ2000() != null){
					
					eu.gloria.rti.sch.core.plan.instruction.J2000 j2000 = new eu.gloria.rti.sch.core.plan.instruction.J2000();
					j2000.setDec(tmpItemSource.getCoordinates().getJ2000().getDEC());
					j2000.setRa(tmpItemSource.getCoordinates().getJ2000().getRA());
					
					eu.gloria.rti.sch.core.plan.instruction.Coordinates coordinates = new eu.gloria.rti.sch.core.plan.instruction.Coordinates();
					coordinates.setJ2000(j2000);
					
					tmpItemTarget.setCoordinates(coordinates);
					
				}
				
				result.add(tmpItemTarget);
				
			}else if (item instanceof CameraSettings){
				
				CameraSettings tmpItemSource = (CameraSettings) item;
				eu.gloria.rti.sch.core.plan.instruction.CameraSettings tmpItemTarget = new eu.gloria.rti.sch.core.plan.instruction.CameraSettings();
				
				if (tmpItemSource.getBinning() != null){
					
					eu.gloria.rti.sch.core.plan.instruction.Binning binning = new Binning();
					binning.setBinX(tmpItemSource.getBinning().getBinX());
					binning.setBinY(tmpItemSource.getBinning().getBinY());
					tmpItemTarget.setBinning(binning);
					
				}
				
				result.add(tmpItemTarget);
				
			}else if (item instanceof Loop){
				
				Loop tmpItemSource = (Loop) item;
				eu.gloria.rti.sch.core.plan.instruction.Loop tmpItemTarget = new eu.gloria.rti.sch.core.plan.instruction.Loop();
				
				tmpItemTarget.setRepeatCount(tmpItemSource.getRepeatCount());
				tmpItemTarget.setRepeatDuration(tmpItemSource.getRepeatDuration());
				tmpItemTarget.setInstructions( getInstructions(tmpItemSource.getTargetOrCameraSettingsOrLoop()));
				
				result.add(tmpItemTarget);
				
			}else if (item instanceof Expose){
				
				Expose tmpItemSource = (Expose) item;
				eu.gloria.rti.sch.core.plan.instruction.Expose tmpItemTarget = new eu.gloria.rti.sch.core.plan.instruction.Expose();
				
				tmpItemTarget.setExpositionTime(tmpItemSource.getExpositionTime());
				tmpItemTarget.setFilter(tmpItemSource.getFilter().toString());
				tmpItemTarget.setRepeatCount(tmpItemSource.getRepeatCount());
				tmpItemTarget.setRepeatDuration(tmpItemSource.getRepeatDuration());
				
				result.add(tmpItemTarget);
				
			}
			
		}
		
		return result;
	}
	
	

}
