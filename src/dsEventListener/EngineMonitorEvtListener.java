package dsEventListener;

import dsEventProcessor.EventProcessor;
import dsManager.ControllerManager;
import dsServices.EngineMonitorService;

public class EngineMonitorEvtListener extends ControllerManager {
	String managerName = "EngineMonitorEvtListener";
    Thread engineMonitorThread = null;
    EngineMonitorService engineMonitorService = null;
    String monitorService = "Monitor";
	public Thread getManagerThread() {
		return engineMonitorThread;
	}
	public  EngineMonitorEvtListener(String host, String hostName, String managerName) {
		super(host, hostName, managerName,"Monitor");
		// TODO Auto-generated constructor stub
	}
	public void setEngineMonitorService(EngineMonitorService engineMonitorService ) {
		this.engineMonitorService = engineMonitorService;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void handleEvent(EventProcessor event)	 {
	
		engineMonitorService.monitorLiveEngineService(event);
			
		
	}
	

}
