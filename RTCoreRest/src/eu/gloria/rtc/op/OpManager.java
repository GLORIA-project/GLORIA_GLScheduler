package eu.gloria.rtc.op;


import java.lang.reflect.Constructor;
import java.util.Date;

import eu.gloria.tools.configuration.Config;
import eu.gloria.tools.log.LogUtil;
import eu.gloria.tools.time.TimeOut;
import eu.gloria.tools.uuid.UUIDGenerator;

public class OpManager {
	
	private static OpManager singleton;
	private boolean logs;
	
	static{
		singleton = new OpManager();
	}
	
	public static OpManager getOpManager(){
		return singleton;
	}
	
	private OpInfo op;
	private IRtsExternalInterrupter interrupter;
	private OpWatchdog watchDog = null;
	private Thread thread = null;
	private boolean enable = true;
	
	private OpManager(){
		
		//Logs
		logs = true;
		
		//Provider...
		String className = "UNKNOWN CLASS";
		try{
			className = Config.getProperty("rt_config", "external.executor.interrupter.provider");
			if (logs) LogUtil.info(null, "OpManager. Constructor. Loading IRtsExternalInterrupter implementation: " + className + " ...");
			Class<?> cls = Class.forName(className);
			Constructor<?> ct = cls.getConstructor();			
			interrupter = (IRtsExternalInterrupter) ct.newInstance();
			if (logs) LogUtil.info(null, "OpManager. Constructor. Loaded IRtsExternalInterrupter implementation: " + className);
			
		}catch (Exception ex){	
			if (logs) LogUtil.severe(null, "OpManager. Constructor. Error instantiating the IRtsExternalInterrupter [" + className + "]. " + ex.getMessage());
			if (logs) LogUtil.severe(null, "OpManager. Constructor. Loading default implementation: RtsExternalInterrupterEmptyExecutor ....");
			interrupter = new RtsExternalInterrupterEmptyExecutor();
			if (logs) LogUtil.severe(null, "OpManager. Constructor. Loaded default implementation: RtsExternalInterrupterEmptyExecutor");
		}	

		//interrupter = new RtsExternalInterrupterDummy();		
		
		//Executor enable
		try{
			enable = Boolean.parseBoolean(Config.getProperty("rt_config", "external.executor.interrupter.enable"));
		}catch(Exception ex){
			if (logs) LogUtil.severe(null, "OpManager. Constructor.Interrupter.enable. Unfound <enable> parameter in the configuration.");
			enable = true;
		}
		
		if (logs) LogUtil.info(null, "OpManager. Constructor.Interrupter.enable= " + enable);
		
		
		
		
	}
	
	public synchronized String start(String uuidOp, String user, long seconds) throws Exception{
		
		if (logs) LogUtil.info(this, "OpManager.start - Starts");
		
		if (enable){
			
			if (uuidOp == null){
				long rtsId = Config.getPropertyLog("rt_config", "uuid.generator.rts.id", 0);
				//long rtsId = 1;				
				UUID uuid = new UUID(rtsId);
				uuidOp = uuid.getValue();
			}
			
			ExtExecInfo info = getExtExecInfo();
			
			switch(info.getState()){
			
			case RUNNING:
				
				if (logs) LogUtil.info(this, "OpManager.start:: Already exists an OP=" + op.getUuid());
				throw new Exception("Already exists an OP=" + op.getUuid());
				
			case BUSY:
				
				if (logs) LogUtil.info(this, "OpManager.start:: The external RTS controller cannot be interrupted. " + info.getDescription());
				throw new Exception("The external RTS controller cannot be interrupted. " + info.getDescription());
				
			case WAIT_TURN:
				
				if (logs) LogUtil.info(this, "OpManager.start:: The external RTS controller cannot be interrupted. " + info.getDescription());
				throw new Exception("The external RTS controller cannot be interrupted. " + info.getDescription());
				
			case IDLE:
				
				if (logs) LogUtil.info(this, "OpManager.start:: The OP can be executed.");
				
				//Create the Op
				OpInfo opInfoTmp = new OpInfo();
				opInfoTmp.setDuration(seconds);
				opInfoTmp.setUser(user);
				opInfoTmp.setUuid(uuidOp);
				opInfoTmp.setTimeout(null);
				opInfoTmp.setDesc("");
				opInfoTmp.setState(OpState.WAITING_TURN);
				if (seconds > 0) opInfoTmp.setTimeout(new TimeOut(seconds*1000));
				
				try{
					
					//Interrupt
					interrupter.interrupt();
					
					//Assigns the OPInfo. 
					this.op = opInfoTmp;
					
					//Creates the thread
					if (seconds > 0){
						this.watchDog = new OpWatchdog();
						this.thread = new Thread(watchDog);
						this.thread.start();
					}else{
						this.thread = null;
						this.watchDog = null;
					}
					
					if (logs) LogUtil.info(this, "OpManager.start:: The OP Launched!!!!: " + uuidOp);
					
				}catch(Exception ex){
					
					if (logs) LogUtil.severe(this, "OpManager.start:: Error:" + ex.getMessage());
					
					try{
						interrupter.resume();
					}catch(Exception exRes){ 
						if (logs) LogUtil.severe(this, "OpManager.start:: Error resuming because cannot be interrupted:" + ex.getMessage());
					}
				}finally{
					
					if (logs) LogUtil.info(this, "OpManager.start - Ends returning uuidOP=" + uuidOp);
					
				}
				
				break;
			}
			
		} else { //DISABLED
			
			if (logs) LogUtil.info(this, "OpManager.start:: [DISABLED]");
			
			uuidOp = null;
			
		}
		
		return uuidOp;
		
	}
	
	public synchronized void stop() throws Exception{
		
		if (logs) LogUtil.info(this, "OpManager.stop - Starts");
		
		if (enable){
			
			ExtExecInfo info = getExtExecInfo();
			
			switch(info.getState()){
			
			case IDLE:
				if (logs) LogUtil.info(this, "OpManager.start:: State is IDLE. It cannot be stopped.");
				break;
			case BUSY:
				if (logs) LogUtil.info(this, "OpManager.start:: State is BUSY. It cannot be stopped. " + info.getDescription());
				throw new Exception("State is BUSY. It cannot be stopped. " + info.getDescription());
			case RUNNING :
				if (logs) LogUtil.info(this, "OpManager.start:: State is (RUNNING) -> Stopping...");
				
				//stop the thread
				if (this.thread != null){
					this.thread.interrupt();
					this.thread = null;
					this.watchDog = null;
				}
				
				//Resume the External Executor
				try{
					this.op.setDesc("");
					this.op.setState(OpState.DONE);
					interrupter.resume();
				}catch(Exception ex){
					if (logs) LogUtil.severe(this, "OpManager.start:: Error resuming the interrupter. " + ex.getMessage());
				}			
				
				if (logs) LogUtil.info(this, "OpManager.start:: State is RUNNING -> Stopped.");
				
				break;
			case WAIT_TURN :
				
				if (logs) LogUtil.info(this, "OpManager.start:: State is (WAIT_TURN) -> Stopping...");
				
				//stop the thread
				if (this.thread != null){
					this.thread.interrupt();
					this.thread = null;
					this.watchDog = null;
				}
				
				//Resume the External Executor
				try{
					this.op.setDesc("RTS was waiting for turn without success.");
					this.op.setState(OpState.ABORTED);
					interrupter.resume();
				}catch(Exception ex){
					if (logs) LogUtil.severe(this, "OpManager.start:: Error resuming the interrupter. " + ex.getMessage());
				}			
				
				if (logs) LogUtil.info(this, "OpManager.start:: State is RUNNING -> Stopped.");
				
				break;
				
			}
			
		}else{ //DISABLED
			
			if (logs) LogUtil.info(this, "OpManager.stop:: [DISABLED]");
			
		}
		
		
		
	}
	
	public synchronized ExtExecInfo getExtExecInfo(){
		
		ExtExecInfo result = null;
		
		if (enable){
			
			result = new ExtExecInfo();
			
			try{
				
				ExtRtsInterruptionInfo intInfo = interrupter.getInterruptionInfo();
				
				if (intInfo.getState() == ExtExecInterruptionState.INTERRUPTED){ //EXECUTING OP
					
					result.setDescription("");
					result.setState(ExtExecState.RUNNING);
					result.setUuidOp(op.getUuid());
					
					op.setState(OpState.RUNNING);
					op.setDesc("");
					
				}else{
					
					if (intInfo.isInterruptable()){// Can be interrupted
						
						result.setDescription("");
						result.setState(ExtExecState.IDLE);
						result.setUuidOp(null);
						
					}else{ //Cannot be interrupted
						
						if (intInfo.getState() == ExtExecInterruptionState.WAIT_TURN){ //WAIT TURN
							
							result.setDescription(intInfo.getUnInterruptableReason());
							result.setState(ExtExecState.WAIT_TURN);
							if (op != null)	{
								result.setUuidOp(op.getUuid());
								op.setState(OpState.WAITING_TURN);
								op.setDesc("");
							}
							
						}else{ //BUSY (uninterrumpable && NOT WAIT TURN)
							
							result.setDescription(intInfo.getUnInterruptableReason());
							result.setState(ExtExecState.BUSY);
							result.setUuidOp(null);
							
						}
						
					}
					
				}
				
			}catch (Exception e) {
				
				result.setDescription("RTI Error: " + e.getMessage());
				result.setState(ExtExecState.BUSY);
				result.setUuidOp(null);
			}
			
		} else { //DISABLED
			
			result = new ExtExecInfo();
			result.setDescription("");
			result.setState(ExtExecState.RUNNING);
			result.setUuidOp(null);
		}
		
		
		
		return result;
	}
	
	public synchronized boolean processEvent() throws ExtRtsInterruptionException{
		
		boolean result = false;
		
		ExtEventResume event =  interrupter.getEventResume();
		if (event != null){
			
			try{
				op.setDesc(event.getDescription());
				op.setState(OpState.ABORTED);
				result = true;
				interrupter.resume();
			}catch(Exception ex){
				if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Error resuming external Exec. " + ex.getMessage());
			}
		}
		
		return result;
	}
	
	class OpWatchdog implements Runnable {

		@Override
		public void run() {

			try {
				
				if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Thread - Starts");

				while (true) {
					
					if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Wake up!!!!");
					
					ExtRtsInterruptionInfo intInfo = interrupter.getInterruptionInfo();
					
					switch(intInfo.getState()){
					
					case INTERRUPTED:
						
						op.setState(OpState.RUNNING);
						op.setDesc("");
						
						if (op.getTimeout().timeOut()){ //TIMEOUT-> DONE
							
							try{
								op.setDesc("");
								op.setState(OpState.DONE);
								interrupter.resume();
							}catch(Exception ex){
								if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Error resuming external Exec. " + ex.getMessage());
							}
							
							return;
							
						}else{
							
							if (processEvent()) return;
						}
						
						break;
						
					case WAIT_TURN:
						
						op.setState(OpState.WAITING_TURN);
						op.setDesc("");
						
						if (op.getTimeout().timeOut()){ //TIMEOUT-> DONE
							
							try{
								op.setDesc("Timeout. RTS was waiting for turn without success.");
								op.setState(OpState.ABORTED);
								interrupter.resume();
							}catch(Exception ex){
								if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Error resuming external Exec. " + ex.getMessage());
							}
							
							return;
							
						}else{
							if (processEvent()) return;
						}
						
						break;
					}
					
					if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Sleeping ZZZzzzzZZZZzzz...");
					Thread.sleep(1000); //1 second
					
				}

			} catch (InterruptedException iex) {
				
				ExtRtsInterruptionInfo intInfo = interrupter.getInterruptionInfo();
				
				switch(intInfo.getState()){
				
				case INTERRUPTED:
					op.setDesc("");
					op.setState(OpState.DONE);
					try{
						interrupter.resume();
					}catch(Exception ex){
						if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: catch(InterruptedException). Error resuming external Exec. " + ex.getMessage());
					}
					break;
				case WAIT_TURN:
					op.setDesc("OP stopped waiting for turn.");
					op.setState(OpState.ABORTED);
					try{
						interrupter.resume();
					}catch(Exception ex){
						if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: catch(InterruptedException). Error resuming external Exec. " + ex.getMessage());
					}
					break;
				}
				
			} catch (Exception e) {
				
				ExtRtsInterruptionInfo intInfo = interrupter.getInterruptionInfo();
				
				switch(intInfo.getState()){
				
				case INTERRUPTED:
					op.setDesc("OpWatchdog error:" + e.getMessage());
					op.setState(OpState.ABORTED);
					try{
						interrupter.resume();
					}catch(Exception ex){
						if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: catch(Exception). Error resuming external Exec. " + ex.getMessage());
					}
					break;
				case WAIT_TURN:
					op.setDesc("OpWatchdog error:" + e.getMessage());
					op.setState(OpState.ABORTED);
					try{
						interrupter.resume();
					}catch(Exception ex){
						if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: catch(Exception). Error resuming external Exec. " + ex.getMessage());
					}
					break;
				}

			} finally {
				if (logs) LogUtil.info(this, "OpManager.OpWatchdog.run:: Thread - Ends");
			}

		}	
		
	}
	
	
	
	
	public final static void main(String[] args) throws Exception {
		
		OpManager manager = OpManager.getOpManager();
		
		String uuid = manager.start(null, "jcabello", 50);
		
		Thread.sleep(40000);
		
		
		try{
			manager.start("UUID", "jcabello", 180);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		while (manager.getExtExecInfo().getState() != ExtExecState.RUNNING){
			System.out.println((new Date()) + "(1). STATE: " + manager.getExtExecInfo().getState());
			String lastOp = "NO DATA";
			if (manager.getOp() != null){
				lastOp = manager.getOp().toString();
			}
			System.out.println(new Date() + "(1). LastOp= " + lastOp);
			Thread.sleep(5000); 
		}
		
		while (manager.getExtExecInfo().getState() != ExtExecState.IDLE){
			System.out.println((new Date()) + "(2). STATE: " + manager.getExtExecInfo().getState());
			String lastOp = "NO DATA";
			if (manager.getOp() != null){
				lastOp = manager.getOp().toString();
			}
			System.out.println(new Date() + "(2). LastOp= " + lastOp);
			Thread.sleep(10000); //10 seconds
		}
		
		System.out.println((new Date()) + "(3). STATE: " + manager.getExtExecInfo().getState());
		String lastOp = "NO DATA";
		if (manager.getOp() != null){
			lastOp = manager.getOp().toString();
		}
		System.out.println(new Date() + "(3). LastOp= " + lastOp);
		
	
	}

	public OpInfo getOp() {
		return op;
	}

	public void setOp(OpInfo op) {
		this.op = op;
	}

}
