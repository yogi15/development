package dsEventListener;

import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsManager.ControllerManager;

public class AdminEvtListener extends ControllerManager {
	String managerName = " AdminListener";
    Thread adminManagerThread = null;
    apps.window.adminmonitor.AdminMonitorWindow adminEvntConsumer = null;
	public Thread getManagerThread() {
		return adminManagerThread;
	}
	public  AdminEvtListener(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		// TODO Auto-generated constructor stub
	}
	public  AdminEvtListener(String host, String hostName, String managerName,String queueName) {
		super(host, hostName, managerName,queueName);
		// TODO Auto-generated constructor stub
	}
	public void setAdminEvtListener(apps.window.adminmonitor.AdminMonitorWindow transferEvtConsumer) {
		this.adminEvntConsumer = transferEvtConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void handleEvent(EventProcessor event)	 {
	
					adminEvntConsumer.addtaskData(event);
			
		
	}
	
}
