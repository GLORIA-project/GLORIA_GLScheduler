package eu.gloria.rtc.environment.config.device;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import eu.gloria.rt.entity.environment.config.device.DeviceType;
import eu.gloria.rt.entity.environment.config.device.Device;
import eu.gloria.rt.entity.environment.config.device.DeviceList;
import eu.gloria.rt.entity.environment.config.device.DeviceProperty;
import eu.gloria.rt.entity.environment.config.device.DevicePropertyBasicType;
import eu.gloria.rt.entity.environment.config.device.DevicePropertyComplexType;
import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;

/*import eu.gloria.rt.entity.environment.config.device.DeviceProperty;
import eu.gloria.rt.entity.environment.config.device.DevicePropertyBasicType;
import eu.gloria.rt.entity.environment.config.device.DevicePropertyComplexType;
import eu.gloria.rt.entity.environment.config.device.DeviceType;
import javax.xml.bind.Marshaller;*/

/**
 * Manager to provide the Device configuration list (environment).
 * 
 * @author jcabello
 *
 */
public class ConfigDeviceManager {
	
	/**
	 * XSD file
	 */
	private String xsdFile;
	
	/**
	 * XML file
	 */
	private String xmlFile;
	
	/**
	 * Device list read form the xml.
	 */
	private DeviceList deviceList;

	/**
	 * Main method for testing purpose.
	 * @param args arguments
	 */
	public static void main(String[] args) throws Exception{
		
		ConfigDeviceManager manage = new ConfigDeviceManager();
		manage.writeFileExample("c:\\dummy\\environment_deviceList.xml");
		DeviceList list = manage.getDeviceList();
		
		System.out.print("");

	}
	
	/**
	 * Constructor.
	 * 
	 * Uses from rt_config.properties file the following properties:
	 * 
	 * - device.discoverer.config.device.list.xsd
	 * - device.discoverer.config.device.list.xml
	 * 
	 * @throws Exception in error case.
	 */
	public ConfigDeviceManager() throws Exception {
		
		this.xsdFile = Config.getProperty("rt_config", "device.discoverer.config.device.list.xsd");
		this.xmlFile = Config.getProperty("rt_config", "device.discoverer.config.device.list.xml");
		
		LogUtil.info(this, "ConfigDeviceManager::device.discoverer.config.device.list.xsd=" + this.xsdFile);
		LogUtil.info(this, "ConfigDeviceManager::device.discoverer.config.device.list.xml=" + this.xmlFile);
		
		File schemaFile = new File(xsdFile);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(schemaFile);

		JAXBContext context = JAXBContext.newInstance(DeviceList.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(schema);
		
		File file = new File(xmlFile);
		this.deviceList = (DeviceList) unmarshaller.unmarshal(file);
		
		for (Device dev: deviceList.getDevice()){
			LogUtil.info(this, "ConfigDeviceManager inventory: " + dev.getShortName());
		}
		
	}
	
	/**
	 * Access method.
	 * @return Device list.
	 */
	public DeviceList getDeviceList() {
		
		return deviceList;
		
	}
	
	/**
	 * Returns a Device information.
	 * @param deviceId Device identifier (shortName)
	 * @return Device data.
	 */
	public Device getDevice(String deviceId){
		
		Device result = null;
		
		for (eu.gloria.rt.entity.environment.config.device.Device dev : deviceList.getDevice()) {
			if (dev.getShortName().equals(deviceId)){
				result = dev;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Returns a device property
	 * @param deviceId Device identifier
	 * @param propName PropName
	 * @return property or null
	 */
	public DeviceProperty getProperty(String deviceId, String propName){
		
		DeviceProperty result = null;
		
		Device dev =  getDevice(deviceId);
		
		if (dev != null){
			for (DeviceProperty prop: dev.getProperty()){
				if (prop.getName().equals(propName)){
					result = prop;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Only for test purpose
	 * @param outputFile output xml file
	 * @throws Exception In error case
	 */
	private  void writeFileExample(String outputFile) throws Exception{
		
		DeviceProperty prop1 = new DeviceProperty();
		prop1.setName("prop1");
		prop1.setBasicType(DevicePropertyBasicType.INTEGER);
		prop1.setComplexType(DevicePropertyComplexType.NONE);
		prop1.setDefaultValue("1");
		prop1.setDescription("desc");
		
		DeviceProperty prop2 = new DeviceProperty();
		prop2.setName("prop1");
		prop2.setBasicType(DevicePropertyBasicType.STRING);
		prop2.setComplexType(DevicePropertyComplexType.NONE);
		prop2.setDefaultValue("2");
		prop2.setDescription("desc");
		
		DeviceList list = new DeviceList();
		
		Device dev = new Device();
		dev.setDescription("Device Description 1");
		dev.setShortName("T0");
		dev.setSubtype("subtype1");
		dev.setType(DeviceType.MOUNT);
		dev.setVersion("1.0");
		
		dev.getProperty().add(prop1);
		dev.getProperty().add(prop2);
		
		list.getDevice().add(dev);
		
		dev = new Device();
		dev.setDescription("Device Description 2");
		dev.setShortName("T1");
		dev.setSubtype("subtype2");
		dev.setType(DeviceType.MOUNT);
		dev.setVersion("2.0");
		list.getDevice().add(dev);
		
		File schemaFile = new File(this.xsdFile);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(schemaFile);

		JAXBContext context = JAXBContext.newInstance(DeviceList.class);
		File output = new File(outputFile);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setSchema(schema);
		marshaller.marshal(list, output);
		
	}
	
}
