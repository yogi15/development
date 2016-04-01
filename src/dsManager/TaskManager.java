package dsManager;

import java.util.Enumeration;
import java.util.Hashtable;

import beans.Users;
import apps.window.operationwindow.jobpanl.JFrameNewJobPanel;
import apps.window.operationwindow.jobpanl.TaskMainPanel;
import apps.window.tradewindow.JFrameTradeWindowApplication;
import apps.window.tradewindow.TradeApplication;
import apps.window.tradewindow.TradePanel;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import util.commonUTIL;

public class TaskManager extends ControllerManager   {
	String managerName = "TaskManager";
	TaskMainPanel jobPanels = null;
	//JFrameTradeWindowApplication tradePanel;
	TradeApplication   tradePanel;
	JFrameNewJobPanel newJobPanel;
	Hashtable<beans.Users,TradeApplication> tradeWIndow =  new Hashtable<beans.Users,TradeApplication>();
	
	
		 public  void addTradeWindow(Users user, TradeApplication tradeW) {
		tradeWIndow.put(user, tradeW);
	}


/*	public void setTradWIndow(JFrameTradeWindowApplication tradePanel) {
		
		this.tradePanel = tradePanel;
	} */
	
public void setTradWIndow(TradeApplication tradePanel) {
		
		this.tradePanel = tradePanel;
	}
	
	Thread taskManagerThread = null;
	
	public Thread getManagerThread() {
		return taskManagerThread;
	}
	
	
	public TaskManager(String host, String hostName, String managerName,String queueName) {
		super(host, hostName, managerName,queueName);
		// TODO Auto-generated constructor stub
	}
	
	
	public void handleEvent(EventProcessor event)	 {
		if(event instanceof TaskEventProcessor ) {
			
			TaskEventProcessor taskEvent = (TaskEventProcessor) event;
			
			System.out.println("From TaskManager "+ taskEvent.getTaskID() + " *     **** " + taskEvent.getTradeID());
			if(jobPanels != null) {
				jobPanels.processTasks(taskEvent);
			}
			if(newJobPanel != null) {
				newJobPanel.processTasks(taskEvent);
			}
			
			if(tradePanel != null) {
			
				//System.out.println(" from TaskManager " + tradWIndow.getUserName() + " " + taskEvent.getUserID() + " " + tradWIndow.getTradeId() );
				tradePanel.processTasks(taskEvent);
			}
			 
		}
	}
	public TaskMainPanel getJobPanels() {
		return jobPanels;
	}


	public void setJobPanels(TaskMainPanel jobPanels) {
		this.jobPanels = jobPanels;
	}
	public void setNewJobPanel(JFrameNewJobPanel newJobPanel) {
		this.newJobPanel = newJobPanel;
	}
	
	public void stop() {
		try {
			managerThread.interrupt();
			throw new InterruptedException();
		} catch (java.lang.InterruptedException e) {
			// TODO Auto-generated catch block
			commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
		}
		
	}

	public Thread init(TaskManager amanager) {
		
		taskManagerThread = new Thread(amanager);
		return taskManagerThread;
	}
	public static void main(String args[]) {
		TaskManager amanager = new TaskManager("localhost",commonUTIL.getLocalHostName(),"TaskManager","TASK");
	    
		amanager.start(amanager);
	}
	
}
