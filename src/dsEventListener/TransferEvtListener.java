package dsEventListener;


import java.rmi.RemoteException;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.PositionEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsManager.ControllerManager;
import dsServices.RemoteMO;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import apps.window.positionwindow.DataPanel;
import beans.Transfer;
import util.commonUTIL;
public class TransferEvtListener  extends ControllerManager {
	String managerName = " TransferListener";
    Thread taskManagerThread = null;
	
	public Thread getManagerThread() {
		return taskManagerThread;
	}
	public  TransferEvtListener(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		// TODO Auto-generated constructor stub
	}
	apps.window.tradewindow.panelWindow.TransferPanel transferEvtConsumer = null;
	apps.window.operationwindow.jobpanl.NettingFrame nettingFrame = null;
	public void setTransferPanelEvtListener(apps.window.tradewindow.panelWindow.TransferPanel transferEvtConsumer) {
		this.transferEvtConsumer = transferEvtConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void setNettingEvtListener(apps.window.operationwindow.jobpanl.NettingFrame nettingFrameConsumer) {
		this.nettingFrame = nettingFrameConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	public void handleEvent(EventProcessor event)	 {
		if(event instanceof TaskEventProcessor ) {
			TaskEventProcessor taskEvent = (TaskEventProcessor) event;
			if(transferEvtConsumer != null) {
				if(taskEvent != null)
			    transferEvtConsumer.addtaskData(taskEvent);
			}
			if(nettingFrame != null)
			nettingFrame.addtaskData(taskEvent);
			 
		}
	}

}
