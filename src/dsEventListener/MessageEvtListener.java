package dsEventListener;

import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsManager.ControllerManager;

public class MessageEvtListener extends ControllerManager {
	String managerName = " TransferListener";
    Thread taskManagerThread = null;
	
	public Thread getManagerThread() {
		return taskManagerThread;
	}
	public  MessageEvtListener(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		// TODO Auto-generated constructor stub
	}
	apps.window.tradewindow.panelWindow.MessagePanel messageEvtConsumer = null;
	apps.window.operationwindow.jobpanl.NettingFrame nettingFrame = null;
	public void setTransferPanelEvtListener(apps.window.tradewindow.panelWindow.MessagePanel messageEvtConsumer) {
		this.messageEvtConsumer = messageEvtConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void setNettingEvtListener(apps.window.operationwindow.jobpanl.NettingFrame nettingFrameConsumer) {
		this.nettingFrame = nettingFrameConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void handleEvent(EventProcessor event)	 {
		if(event instanceof TaskEventProcessor ) {
			TaskEventProcessor taskEvent = (TaskEventProcessor) event;
			if(messageEvtConsumer != null) {
				if(taskEvent != null)
					messageEvtConsumer.addtaskData(taskEvent);
			}
			if(nettingFrame != null)
			nettingFrame.addtaskData(taskEvent);
			 
		}
	}

}