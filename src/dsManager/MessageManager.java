package dsManager;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import logAppender.MessageServiceAppender;
import logAppender.TransferServiceAppender;

import util.commonUTIL;
import beans.Message;
import beans.StartUPData;
import beans.Task;
import beans.Trade;
import beans.Transfer;
import bo.message.MessageProcessor;
import dsEventProcessor.AdminEventProcessor;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsEventProcessor.MessageEventProcessor;
import dsServices.RemoteAdminManager;
import dsServices.RemoteBOProcess;
import dsServices.RemoteEvent;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class MessageManager extends ControllerManager {
	String managerName = "MessageManager";
	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	RemoteAdminManager remoteAdmin;
	RemoteEvent remoteEvent;
	MessageProcessor processor = null;
	Thread messageManagerThread = null;
	Message message = null;
	String queueName = "MESSAGE";
	MessageManagerStartup startUpManager ;
	Vector<EventProcessor> eventNotProcess = new Vector<EventProcessor>();
	Hashtable<String, MessageEventProcessor> publishEventsData = new Hashtable<String, MessageEventProcessor> ();
	
int counter = 0;
Hashtable<String,Integer> duplicateEventCheck = new Hashtable<String,Integer>();
	public Hashtable<Integer,EventProcessor> transferEvents = new Hashtable<Integer,EventProcessor>();
	public Map<Integer,EventProcessor> balance = new ConcurrentHashMap<Integer,EventProcessor> ();
	public String getQueueName() {
		return queueName;
	}
	
	private boolean isDuplicateEvent(Trade trade) {
		boolean flag = false;
		String key = trade.getId()+"_"+trade.getStatus()+"_"+trade.getVersion();
		 
		if(duplicateEventCheck.containsKey(key)) {
			System.out.println(" getting duplicat key ***** for key = " + key);
			flag = true;
		}
		return flag;
	}
	private boolean isDuplicateEvent(Transfer transfer) {
		boolean flag = false;
		String key = transfer.getId()+"_"+transfer.getStatus()+"_"+transfer.getVersion();
		 
		if(duplicateEventCheck.containsKey(key)) {
			System.out.println(" getting duplicat key ***** for key = " + key);
			flag = true;
		}
		return flag;
	}
	
	
	
	
	
	public void publishStartEvent(String engineName,String siginal,int clientID) {
		
		try {
			remoteAdmin.addEngines(engineName+"_"+siginal,startUpManager.getClientID(),startUpManager.getUser());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MessageManager", "publishStartEvent", e);
			MessageServiceAppender.printLog("ERROR", "MessageService getting Error on publishing SiginalEvents "+e);
			
		}
		
	}
	
	public MessageManager(String host,String hostName,String managerName,MessageManagerStartup startUpManager,String queueName) {
		
		

		super(host,hostName,managerName,startUpManager.getUser(),startUpManager,queueName);
		MessageServiceAppender.printLog("INFO", "MessageService connecting... to "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
		
		
		this.startUpManager = startUpManager;
		try {
			if(getDe() == null) {
				MessageServiceAppender.printLog("INFO", "MessageService not getting connection <<<<<<<<<<<<<<<<<<<< on "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
				//publishStartEvent(managerName,"Stopped",0);
				return;
			} 
			MessageServiceAppender.printLog("INFO", "MessageService connected >>>>>>>>>>>>>>>>>>>>> on "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
			
			processor = new MessageProcessor();
			remoteBO = (RemoteBOProcess) getDe().getRMIService("BOProcess");
			remoteTrade = (RemoteTrade) getDe().getRMIService("Trade");
	   		refData = (RemoteReferenceData) getDe().getRMIService("ReferenceData");
	   		remoteAdmin = (RemoteAdminManager) getDe().getRMIService("ServerController");
	   		remoteEvent = (RemoteEvent) getDe().getRMIService("Event");
	   		eventNotProcess = (Vector<EventProcessor>) remoteEvent.getEventNotProcessed("Message");
	   		processor.setRemoteBOProcess(remoteBO);
			processor.setRefData(refData);
			processor.setRemoteTrade(remoteTrade);
	   	 processor.setMessageManager(this);
	   	 startUpManager.setClientID(getDe().getClientID());
	   	 startUpManager.startProducingMessage(managerName, hostName, ":61616", startUpManager);
	   	if(eventNotProcess.size() > 0) {
   	    	for(int i=0;i<eventNotProcess.size();i++) {  // first process all event are not consumed. 
   	    		EventProcessor event = eventNotProcess.get(i); 
   	    		handleEvent(event);
   	    	}
   	    }
	   	processor.start();
	   	        // setTransferTriggerEvt(refData.getStartUPData(transferTriggerEvt));
	   	    //  processor.setCancelTransferTriggerEvent(refData.getStartUPData(canceltransferTriggerEvt));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MessageManager", "In Constructor", e);
			MessageServiceAppender.printLog("ERROR", "MessageService  getting Error on Startup "+e);
		}catch(NullPointerException e) {
			commonUTIL.displayError("MessageManager", "In Constructor", e);
			MessageServiceAppender.printLog("ERROR", "MessageService  getting Error on Startup "+e);
		}
   		
   		
		
		
   		
	}
	
  public synchronized void handleEvent(EventProcessor event)	 {
	  System.out.println("Message Handle Event coming  "+event.getClassName() + " "+event.getEventType()+ " " + event.getTradeID());
	  commonUTIL.display("Message coming in HandleEvent  ", event.getClassName() + " "+event.getEventType()+ " " + event.getTradeID() );
	 if(event instanceof AdminEventProcessor ) {
		  AdminEventProcessor  adminEvent = (AdminEventProcessor) event;
		  if(adminEvent.getEngineStartedSignal().contains(managerName))  {
			  if(adminEvent.getEngineStartedSignal().contains(managerName)) {
				  if(adminEvent.getClientID() == startUpManager.getClientID()) {
					  MessageServiceAppender.printLog("DEBUG", "MessageService recevied Admin Event to stop MessageService");
						
					     startUpManager.stop();
					  }
			  }
		  }
	  } else {
	 balance.put(balance.size(), event);
	  
	  }
		/*if(event instanceof TradeEventProcessor) {
			processor.clearPublishMessages();
			TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
			//System.out.println(tradeEvent.getTrade().getId()); 
			 MessageServiceAppender.printLog("DEBUG", "MessageService Starting process Message for trade "+tradeEvent.getTrade().getId() + " on Trade status "+ tradeEvent.getTrade().getStatus());
				
		    	processor.processMessage(tradeEvent);
			    publishMessage(tradeEvent.getTrade(),null);
			
		}if(event instanceof TransferEventProcessor) {
		processor.clearPublishMessages();
			TransferEventProcessor transferEvent = (TransferEventProcessor) event;
			//System.out.println(transferEvent.getTrade().getId());
			 MessageServiceAppender.printLog("DEBUG", "MessageService Starting process Message for Transfer "+transferEvent.getTransfer().getId());
				
		    	processor.processMessage(transferEvent);
			   publishMessage(transferEvent.getTrade(),transferEvent.getTransfer());
			
		}*/
		
	}
public void updateEventProcess(EventProcessor event) {
	    try {
	    	event.setSubscribableList("Message");
			remoteTrade.isEventExceuted(event);
			MessageServiceAppender.printLog("DEBUG", "MessageService   updating  event table for event id "+event.getEventid() + " on event for Trade event "+event.getEventType());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MessageManager", "updateEventProcess", e);
			MessageServiceAppender.printLog("ERROR", "MessageService getting Error on updateEventProcess "+e);
			
		}catch(NullPointerException e) {
			commonUTIL.displayError("MessageManager", "updateEventProcess", e);
			MessageServiceAppender.printLog("ERROR", "MessageService getting Error on updateEventProcess s "+e);
			
		}
		
}

private boolean checkEventAlreadPublish(MessageEventProcessor event) {
	 
	String eventU = event.getType()+"_"+event.getTradeID()+"_"+event.getTradeVersion()+"_"+event.getTransferID()+"_"+event.getTransferVerson();
	//System.out.println(eventU + " ))))))))))))))))))))))) ");
	if(publishEventsData.containsKey(eventU))
		return true;
	return false;
}
	
public void publishMessage(Trade trade,Transfer transfer) {
		
		
			 Vector<Message> messages = 	processor.getPublishMessages();
			 
			
			 if(messages != null || (!messages.isEmpty())) {
			//	 System.out.println();
				 for(int i=0;i<messages.size();i++)
					try {
						commonUTIL.display("MessageManager"," publishing message id "+ messages.get(i).getId() + " for trade   "+ trade.getId() + "  on event " );
						
						MessageEventProcessor event = getMessageEvent(messages.get(i),transfer,trade);
					//	MessageServiceAppender.printLog("DEBUG", "MessageService  publishing message id "+ messages.get(i).getId() + " for trade   "+ trade.getId() + "  on transfer  " +transfer.getId() + " on event type " + event.getEventType() + " "+event.getEventid());
						
						if(event != null && !checkEventAlreadPublish(event)) {
						  remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",event);
						  MessageServiceAppender.printLog("DEBUG", "MessageService  published message id "+ event.getMessage().getId()  + " for trade   "+ trade.getId() );// + "  on transfer  " +transfer.getId() + " on event type " + event.getEventType() + " "+event.getEventid());
							
							
						} //else {
							//commonUTIL.display("MessageManager","Event not publish Trade is Cancel or Terminated  <<<<<<<<<<<<< ");	 					}
					} 
				 catch (RemoteException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("MessageManager", "publishMessage", e);
						MessageServiceAppender.printLog("ERROR", "MessageService getting Error on publishMessage save messages "+e);
						
					}catch(NullPointerException e) {
						commonUTIL.displayError("MessageManager", "publishMessage", e);
						MessageServiceAppender.printLog("ERROR", "MessageService getting Error on publishMessage save messages "+e);
						
					}
					
				 
				// transferexist = false;
			  }
			
		
	}

public MessageEventProcessor getMessageEvent(Message message,Transfer transfer,Trade trade ) {
	
	MessageEventProcessor messageEvent = new MessageEventProcessor();
	try {
		if(message.getId() == 0)
			return null;
	
	messageEvent.setTransferID(message.getTransferId());
	messageEvent.setTradeID(message.getTradeId());
	messageEvent.setTrade(trade);
	messageEvent.setTransfer(transfer);
	messageEvent.setMessage(message);
	messageEvent.setProcessName("MessageEngineProcess");
	messageEvent.setPublish(true);
	messageEvent.setSavetoDB(true);
	messageEvent.setEventType(message.getEventType());
	messageEvent.setType("Message");
	messageEvent.setObjectVersionID(message.getVersion());
	messageEvent.setObjectID(message.getId());
	 if(transfer != null && transfer.getId() > 0)
		messageEvent.setComments(" Message " + message.getId() + " for consuming Transfer_ "+transfer.getId()+"_Version_"+transfer.getVersion()+"_"+transfer.getStatus());
	
	else 
	messageEvent.setComments(" Message " + message.getId() + " for consuming Trade "+trade.getId()+"_Version_"+trade.getVersion()+"_"+trade.getStatus());
	
	MessageServiceAppender.printLog("DEBUG", "MessageService creating  messageEvent with"+messageEvent.getEventType() + " on Trade trade "+trade.getId() );
	
	commonUTIL.display("MessageManager",messageEvent.getEventType());
	commonUTIL.display("","");
	return messageEvent;
	}catch(NullPointerException e) {
		commonUTIL.displayError("MessageManager", "getMessageEvent", e);
		MessageServiceAppender.printLog("DEBUG", "MessageService creating  messageEvent with"+messageEvent.getEventType() + " on Trade trade "+trade.getId() );
		
		return null;
	}

}
public Thread init(MessageManager amanager,Trade trade) {
	
	messageManagerThread = new Thread(amanager);
	return messageManagerThread;
}


public static void main(String args[]) {
	//MessageManager mmanager = new MessageManager("localhost",commonUTIL.getLocalHostName(),"MessageManager");
//	commonUTIL.display("MessageManager", "Started Message Manager >>>>>>>> ");
	//mmanager.start(mmanager);
}

public MessageEventProcessor getMessageEvent(Message message,Trade trade,Transfer transfer,String eventType ) {
	
	MessageEventProcessor messageEvent = new MessageEventProcessor();
	try {
	messageEvent.setTransferID(transfer.getId());
	messageEvent.setTradeID(transfer.getTradeId());
	messageEvent.setTrade(trade);
	messageEvent.setTransfer(transfer);
	messageEvent.setProcessName("MessageEngineProcess" );

	messageEvent.setEventType(eventType);
	
	messageEvent.setPublish(true);
	messageEvent.setSavetoDB(true);
	messageEvent.setEventType(message.getEventType());
	messageEvent.setType("Message");
	messageEvent.setObjectID(message.getId());
	messageEvent.setComments(" Message status on " + message.getStatus() + " for Action "+message.getAction() );
	MessageServiceAppender.printLog("DEBUG", "MessageService creating  messageEvent with"+messageEvent.getEventType() + " on Trade trade "+trade.getId() + " on transfer id " + transfer.getId());
	
	commonUTIL.display("MessageManager",messageEvent.getEventType());
	return messageEvent;
	}catch(NullPointerException e) {
		commonUTIL.displayError("MessageManager", "getMessageEvent", e);
		MessageServiceAppender.printLog("DEBUG", "MessageService creating  messageEvent with"+messageEvent.getEventType() + " on Trade trade "+trade.getId() );
		
		return null;
	}
	
	
}


}