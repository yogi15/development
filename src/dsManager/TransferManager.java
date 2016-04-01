package dsManager;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;

import logAppender.TransferServiceAppender;
import util.commonUTIL;
import beans.StartUPData;
import beans.Trade;
import beans.Transfer;
import bo.transfer.TransferProcessor;
import dsEventProcessor.AdminEventProcessor;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsServices.RemoteAdminManager;
import dsServices.RemoteBOProcess;
import dsServices.RemoteEvent;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class TransferManager extends ControllerManager {
	
	
	
	String managerName = "TransferManager";
	TransferManagerStartup startUpManager;
	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	RemoteAdminManager remoteAdmin;
	RemoteEvent remoteEvent;
	TransferProcessor processor = null;
	Thread transferManagerThread = null;
	Transfer transfer = null;
	String queueName = "TRANSFER";
	String transferTriggerEvt = "TRANSFERTriggerEvent";
	String canceltransferTriggerEvt = "TRANSFERCancelTriggerEvent";
	Vector<String> transferTriggerEvts = new Vector<String>();
	Hashtable<String, TransferEventProcessor> publishEventsData = new Hashtable<String, TransferEventProcessor> ();
	Vector<EventProcessor> eventNotProcess = new Vector<EventProcessor>();
	boolean newEvent = false;
	boolean serviceStarted = false;
	
	/**
	 * @return the serviceStarted
	 */
	public boolean isServiceStarted() {
		return serviceStarted;
	}


	/**
	 * @param serviceStarted the serviceStarted to set
	 */
	public void setServiceStarted(boolean serviceStarted) {
		this.serviceStarted = serviceStarted;
	}

	Hashtable<String,Integer> duplicateEventCheck = new Hashtable<String,Integer>();
	public boolean isNewEvent() {
		//System.out.println("PPPPPPPPPPPPPPPPPPPPPPP  isNewEvent " + newEvent);
		return newEvent;
	}


	public void setNewEvent(boolean newEvent) {
		this.newEvent = newEvent;
		//System.out.println("PPPPPPPPPPPPPPPPPPPPPPP " + newEvent);
	}

	int counter = 0;
	
	public Hashtable<Integer,TradeEventProcessor> transferEvents = new Hashtable<Integer,TradeEventProcessor>();
	public Map<Integer,TradeEventProcessor> balance = new ConcurrentHashMap<Integer,TradeEventProcessor> ();
	public String getQueueName() {
		return queueName;
	}
	private boolean isDuplicateEvent(Trade trade) {
		boolean flag = false;
		 if(trade == null) {
			 commonUTIL.display("TransferManager", "Trade is null from isDuplicateEvent");
			 TransferServiceAppender.printLog("ERROR", "TransferService Trade is null from isDuplicateEvent");
				
		 }
		String key = trade.getId()+"_"+trade.getStatus()+"_"+trade.getVersion();
		 
		if(duplicateEventCheck.containsKey(key)) {
		//	System.out.println(" getting duplicat key ***** for key = " + key);
			flag = true;
		}
		return flag;
	}
	

	public TransferManager(String host,String hostName,String managerName,TransferManagerStartup startUpManager,String queueName ) throws JMSException {
		
		super(host,hostName,managerName,startUpManager.getUser(),startUpManager,queueName);
		this.startUpManager = startUpManager;
		
		try {
			if(getDe() == null) {
				 commonUTIL.display("TransferManager", "getDe() is null from TransferManagerConstructor");
				TransferServiceAppender.printLog("INFO", "TransferService not getting connection <<<<<<<<<<<<<<<<<<<< on "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
				return;
			} 
			TransferServiceAppender.printLog("INFO", "TransferService connecting... to "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
			
			TransferServiceAppender.printLog("INFO", "TransferService connected >>>>>>>>>>>>>>>>>>>>> on "+ host + " "+hostName + " with User "+startUpManager.getUser().getUser_name());
			
			processor = new TransferProcessor();		
			remoteBO = (RemoteBOProcess) getDe().getRMIService("BOProcess");
			remoteTrade = (RemoteTrade) getDe().getRMIService("Trade");
	   		refData = (RemoteReferenceData) getDe().getRMIService("ReferenceData");
	   		remoteAdmin = (RemoteAdminManager) getDe().getRMIService("ServerController");
	   		remoteEvent = (RemoteEvent) getDe().getRMIService("Event");
	   	    setTransferTriggerEvt(refData.getStartUPData(transferTriggerEvt));
	   	    eventNotProcess = (Vector<EventProcessor>) remoteEvent.getEventNotProcessed("Transfer");
	   	    processor.setCancelTransferTriggerEvent(refData.getStartUPData(canceltransferTriggerEvt));
	   	    processor.setRemoteBOProcess(remoteBO);
			processor.setRefData(refData);
			processor.setRemoteTrade(remoteTrade);
	   	    processor.setTransferManager(this);
	   	    startUpManager.setClientID(getDe().getClientID());
	   	    startUpManager.startProducingMessage(managerName, hostName, ":61616", startUpManager);
	   	 processor.start();
	   	 TransferServiceAppender.printLog("DEBUG", "TransferService starting process of all unconsumed events");
	   	    if(!commonUTIL.isEmpty(eventNotProcess) && eventNotProcess.size() > 0) {
	   	    	for(int i=0;i<eventNotProcess.size();i++) {  // first process all event are not consumed. 
	   	    		EventProcessor event = eventNotProcess.get(i); 
	   	    		handleEvent(event);
	   	    	}
	   	    }
	   	 TransferServiceAppender.printLog("DEBUG", "TransferService Ending process of all unconsumed events");
		   	
	   	   
	   	  
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TransferManager1", "In Constructor", e);
			TransferServiceAppender.printLog("ERROR", "TransferService  getting Error on Startup "+e);
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TransferManager1", "In Constructor", e);
			TransferServiceAppender.printLog("ERROR", "TransferService getting Error on publishing SiginalEvents "+e);
			
		}
   		 
   		
		
   		
	}
	
	// this method when engine is started. 
		public void publishStartEvent(String engineName,String siginal) {
			
			try {
		
				remoteAdmin.addEngines(engineName+"_"+siginal,startUpManager.getClientID(),startUpManager.getUser());
			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferManager2", "publishStartEvent", e);
				TransferServiceAppender.printLog("ERROR", "TransferService getting Error on publishing SiginalEvents "+e);
				
			}catch (NullPointerException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferManager2", "publishStartEvent", e);
				TransferServiceAppender.printLog("ERROR", "TransferService getting Error on publishing SiginalEvents "+e);
				
			}
			
		
		
	}
// this event is used when transfer event successfully executed the Event.
public void updateEventProcess(EventProcessor event) {
	    try {
	        
	    	event.setSubscribableList("Transfer");
			remoteTrade.isEventExceuted(event);
			TransferServiceAppender.printLog("DEBUG", "TransferService processed event "+event.getEventid() + " on event for Trade event "+event.getEventType());
				
		} catch (RemoteException e) {
			// TODO Auto-generated catch blockcommonUTIL.displayError("TransferManager", "publishStartEvent", e);
			commonUTIL.displayError("TransferManager3", "updateEventProcess", e);
			TransferServiceAppender.printLog("ERROR", "TransferService getting Error on updateEventProcess "+e);
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TransferManager2", "updateEventProcess", e);
			TransferServiceAppender.printLog("ERROR", "TransferService getting Error on publishing SiginalEvents "+e);
			
		}
}
	
	private void setTransferTriggerEvt(Collection startUPData) {
		// TODO Auto-generated method stub
		try {
		if(commonUTIL.isEmpty(startUPData)) {
			TransferServiceAppender.printLog("INFO", "TransferService Triggering Evt are missing");
			TransferServiceAppender.printLog("DEBUG", "TransferService Triggering Evt are missing");
		return;
		}
		
		Vector<StartUPData> startupData = (Vector)startUPData;
		if((startupData != null) || (!startupData.isEmpty())) {
			for(int i=0;i<startupData.size();i++) {
				transferTriggerEvts.addElement(((StartUPData) startupData.get(i)).getName());
			}
		}
		} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferManager2", "setTransferTriggerEvt", e);
				TransferServiceAppender.printLog("ERROR", "TransferService setTransferTriggerEvt "+e);
				
			}
		
	}


	public  synchronized void handleEvent(EventProcessor event)	 {
	 
	//	System.out.println("Starting of  ****** " +   event.getTradeID());
	//	setNewEvent(true);
		try {
		TradeEventProcessor tradeEvent = null;
	//	System.out.println(event.getEventType() + " " + event.getClassName());
		if(event instanceof AdminEventProcessor ) {
			  AdminEventProcessor  adminEvent = (AdminEventProcessor) event;
				
			  if(adminEvent.getEngineStartedSignal().contains(managerName))  {
				  if(adminEvent.getEngineStartedSignal().contains(managerName)) {
					  TransferServiceAppender.printLog("DEBUG", "TransferService recevied Admin Event to stop TransferService");
						
				  if(adminEvent.getClientID() == startUpManager.getClientID())
				     startUpManager.stop();
				  }
			  }
		  }
		if(event instanceof TradeEventProcessor) {
			tradeEvent = (TradeEventProcessor) event;
			
		//	System.out.println("Starting of  ****** " +   tradeEvent.getTradeID() + " " + transferEvents.size());
			//System.out.println(tradeEvent.getTrade().getId());
			if(transferTriggerEvts.contains(event.getEventType())) {
				  TransferServiceAppender.printLog("DEBUG", "TransferService starting processing  TransferEvent for Trade "+tradeEvent.getTrade().getId() + " on event of  "+event.getEventType());
					
					      
					        synchronized (transferEvents) {
					        	if( tradeEvent.getTrade() == null && filteroutTrade(tradeEvent.getTrade())) {
					        		Trade trade;
									try {
										trade = remoteTrade.getTradeOnVersion(tradeEvent.getObjectID(),tradeEvent.getObjectVersionID());
										if(trade != null) 
										tradeEvent.setTrade(trade);
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										commonUTIL.displayError("TransferManager", "handleEvent", e);
										TransferServiceAppender.printLog("ERROR", "TransferService getting Error HandleEvent for Executing Events "+e);
										
									}
					        		
					        	}
					        	if(!isDuplicateEvent(tradeEvent.getTrade())) {
					        		//  System.out.println(" adding at  counter *****  = " + counter + tradeEvent.getTrade().getId() + " " + tradeEvent.getTrade().getStatus() + " " + tradeEvent.getTrade().getVersion());
					        	       
					        		  balance.put(counter, tradeEvent);
					        		 
					        		  duplicateEventCheck.put(tradeEvent.getTrade().getId()+"_"+tradeEvent.getTrade().getStatus()+"_"+tradeEvent.getTrade().getVersion(),tradeEvent.getTrade().getId());
					        		  counter = counter + 1;
					        	} else {
					        		//  System.out.println(" Noting addding value = " + counter + tradeEvent.getTrade().getId() + " " + tradeEvent.getTrade().getStatus() + " " + tradeEvent.getTrade().getVersion());
					        	}
					        	 
							}
						 
						  
					   
					   
				   
				
		    	
			} else {
				 TransferServiceAppender.printLog("DEBUG", "TransferService starting not processing TransferEvent for Trade "+tradeEvent.getTradeID() + " on event of  "+event.getEventType());
					
			updateEventProcess(tradeEvent);
			}
			
			//System.out.println("End  of ****** " +   tradeEvent.getTradeID());
		}
		}
		catch(NullPointerException e) {
			commonUTIL.displayError("TransferManager4", "HandleEvent", e);
			
			TransferServiceAppender.printLog("ERROR", "TransferService getting Error HandleEvent for Executing Events "+e);
			
		}
	//	setNewEvent(false);
		//tradeEvent = null;
	}
	
	// filterout split trades
	private boolean filteroutTrade(Trade trade) {
		// TODO Auto-generated method stub
		if(commonUTIL.isEmpty(trade.getAutoType()) || trade.getAutoType().equalsIgnoreCase("Original"))
		return true;
		return false;
	}
	
	private boolean checkEventAlreadPublish(TransferEventProcessor event) {
		String eventU = event.getType()+"_"+event.getTradeID()+"_"+event.getTradeVersion()+"_"+event.getTransferID()+"_"+event.getTransferVerson();
		//System.out.println(eventU + " ))))))))))))))))))))))) ");
		if(publishEventsData.containsKey(eventU))
			return true;
		return false;
	}
	// this method is called when trade event is consumed by transfer service and publish transfer event. 
public void publishTransfer(Trade trade) throws JMSException {
		
		
			 Vector<Transfer> transfers = 	processor.getPublishtransfers();
			
			 if(transfers != null || (!transfers.isEmpty())) {
			//	 System.out.println();
				 for(int i=0;i<transfers.size();i++)
					try {
						commonUTIL.display("TransferManager","publishing transfer id "+ transfers.get(i).getId() + " for trade   "+ trade.getId() + "  on event " );
						  TransferServiceAppender.printLog("DEBUG", "TransferService creating  TransferEvent for"+transfers.get(i).getId() + " on Trade trade "+trade.getId());
							
						TransferEventProcessor event = getTransferEvent(transfers.get(i),trade);
						if(event != null && !checkEventAlreadPublish(event)) {
						  remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",event);
						  String eventU = event.getType()+"_"+event.getTradeID()+"_"+event.getTradeVersion()+"_"+event.getTransfer()+"_"+event.getTransferVerson();
						  publishEventsData.put(eventU, event);
						  TransferServiceAppender.printLog("DEBUG", "TransferService published  TransferEvent with"+event.getEventType() + " on Trade trade "+trade.getId());
							
						} 
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("TransferManager5", "publishTransfer", e);
						TransferServiceAppender.printLog("ERROR", "TransferService getting Error in publishTransfer Events "+e);
						
					}catch(NullPointerException e) {
						commonUTIL.displayError("TransferManager5", "publishTransfer", e);
						TransferServiceAppender.printLog("ERROR", "TransferService getting Error in publishTransfer Events "+e);
						
						
					}
				 
				// transferexist = false;
			  }
			
		
	}



public TransferEventProcessor getTransferEvent(Transfer transfer,Trade trade ) {
	try {
	if(transfer == null) {
		TransferServiceAppender.printLog("INFO", "TransferService not able to create TransferEvent on trade "+trade.getId() +" <<<<<<<<<<<<< ");
		return null;
	}
	
	
	TransferEventProcessor transferEvent = new TransferEventProcessor();
	transferEvent.setTransferID(transfer.getId());
	transferEvent.setTradeID(transfer.getTradeId());
	transferEvent.setTrade(trade);
	transferEvent.setTransfer(transfer);
	transferEvent.setProcessName("TransferEngineProcess");
	transferEvent.setPublish(true);
	transferEvent.setSavetoDB(true);
	transferEvent.setEventType(transfer.getStatus()+"_"+transfer.getEventType());
	
	transferEvent.setType("Transfer");
	transferEvent.setObjectID(transfer.getId());
	transferEvent.setObjectVersionID(transfer.getVersion());
	transferEvent.setComments(" Transfer " +transfer.getId()+ " for consuming Trade_"+trade.getId()+"_Version_"+trade.getVersion()+"_"+trade.getStatus());
	TransferServiceAppender.printLog("DEBUG", "TransferService creating  TransferEvent with"+transferEvent.getEventType() + " on Trade trade "+trade.getId());
	
	commonUTIL.display("TransferManager",transferEvent.getEventType());
	return transferEvent;
	}catch(NullPointerException e) {
		commonUTIL.displayError("TransferManager5", "getTransferEvent", e);
		TransferServiceAppender.printLog("ERROR", "TransferService getting Error in getTransferEvent Events "+e);
		
		return null;
	}
}

 
}