package mo.positionmang;


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
import dsEventProcessor.TaskEventProcessor;
import dsManager.ControllerManager;
import dsServices.RemoteMO;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import apps.window.positionwindow.DataPanel;
import beans.Trade;
import util.commonUTIL;

public class PositionEvtListener  extends ControllerManager {
	String managerName = "PositionListener";
    Thread taskManagerThread = null;
	
	public Thread getManagerThread() {
		return taskManagerThread;
	}
	public PositionEvtListener(String host, String hostName, String managerName) {
		super(host, hostName, managerName,"LIQPOSITION");
		// TODO Auto-generated constructor stub
	}
	apps.window.positionwindow.DataPanel positionEvtConsumer = null;
	
	public void setPositionEvtListener(DataPanel positionEvtConsumer) {
		this.positionEvtConsumer = positionEvtConsumer;
	
				
			//System.out.println(remoteBORef);
		
	   	 } 
	
	public void handleEvent(EventProcessor event)	 {
		if(event instanceof PositionEventProcessor ) {
			PositionEventProcessor taskEvent = (PositionEventProcessor) event;
			positionEvtConsumer.addtaskData(taskEvent);
			 
		}
	}
	    
}
