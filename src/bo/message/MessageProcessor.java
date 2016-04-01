package bo.message;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

import constants.SDIConstants;

import logAppender.MessageServiceAppender;
import logAppender.TransferServiceAppender;

import util.commonUTIL;

import beans.Book;
import beans.LegalEntity;
import beans.Message;
import beans.MessageConfig;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import bo.message.bomessagehandler.BOMessageHandler;
import bo.util.SDISelectorUtil;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsManager.MessageManager;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class MessageProcessor extends Thread {

	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	Hashtable<Integer,Book> books = new Hashtable<Integer,Book>();
	Hashtable<String,Vector<MessageConfig>> messageConfigs = new Hashtable<String,Vector<MessageConfig>>();
	Hashtable<Integer,LegalEntity>  legalEntitys = new  Hashtable<Integer,LegalEntity>();
	Hashtable<String,Integer> duplicateEventCheck = new Hashtable<String,Integer>();
	Vector<Message> publishMessages = new Vector<Message>();
	MessageManager manager = null;
	/**
	 * @return the manager
	 */
	public MessageManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setMessageManager(MessageManager manager) {
		this.manager = manager;
	}


	int counter = 0;
	boolean eventUnderProcess = true;
	public Vector<Message> getPublishMessages() { // this method is access by MesssageManager
		return publishMessages; 
	}
	public void clearPublishMessages() { // this method is access by MesssageManager
		publishMessages.removeAllElements();
		publishMessages.clear(); 
	}
	public void setPublishMessages(Vector<Message> publishMessages1) { // this method is access within Processor 
		synchronized (publishMessages) {
			publishMessages.removeAllElements();
			if((publishMessages1 != null) || (!commonUTIL.isEmpty(publishMessages1))) {
				for(int i=0;i<publishMessages1.size();i++) 
					publishMessages.add(publishMessages1.get(i)); 
			}
		}
		 
		
	}

	public void setRemoteBOProcess(RemoteBOProcess remoteBO) {
		// TODO Auto-generated method stub
		this.remoteBO = remoteBO;
		
	}

	public void setRefData(RemoteReferenceData refData) {
		// TODO Auto-generated method stub
		this.refData = refData;
		
	}

	public void setRemoteTrade(RemoteTrade remoteTrade) {
		// TODO Auto-generated method stub
		this.remoteTrade = remoteTrade;
		
	}

	public synchronized void processMessage(EventProcessor event) {
		// TODO Auto-generated method stub
		Trade trade = null;
		Transfer transfer = null; 
		
		if(event instanceof TradeEventProcessor) {
			
			TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
				
			trade = tradeEvent.getTrade();
			if(trade == null && filteroutTrade(trade) ) {
				try {
					
				trade = 	remoteTrade.getTradeOnVersion(tradeEvent.getObjectID(), tradeEvent.getObjectVersionID());
				tradeEvent.setTrade(trade);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					commonUTIL.displayError("MessageProcesser", "processMessage", e);
					MessageServiceAppender.printLog("ERROR", "MessageProcessor  processMessage "+e );

				}
				 catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						MessageServiceAppender.printLog("ERROR", "MessageProcessor  processMessage tradeEventProcessor "+e);

					}
				
			}
			duplicateEventCheck.put(trade.getId()+"_"+trade.getStatus()+"_"+trade.getVersion(),tradeEvent.getEventid());
		}
		if(event instanceof TransferEventProcessor) {
			
			TransferEventProcessor transferEvent = (TransferEventProcessor) event;
         try {
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor Received new event   *****************************************************" );

			
			
			transfer = transferEvent.getTransfer();
			if(transfer == null) {
				MessageServiceAppender.printLog("ERROR", "MessageProcessor Transfer is null on Event id  "+ transferEvent.getEventid());
			}
				
			trade = transferEvent.getTrade();
			if(transfer == null) {
				MessageServiceAppender.printLog("ERROR", "MessageProcessor Trade is null on Event id  "+ transferEvent.getEventid());
			}
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor Entered in  processing of new Transfer ****** " +   transfer.getId() + " on trade id "+ trade.getId() + " trade status "+trade.getStatus());
			
			duplicateEventCheck.put(transfer.getId()+"_"+transfer.getStatus()+"_"+transfer.getVersion(),transferEvent.getEventid());
         }	catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MessageServiceAppender.printLog("ERROR", "MessageProcessor  processMessage transferEventProcessor "+e);

			}
		}
		Vector<MessageConfig> messConfigs = getMessageConfig(event,trade);
		
		 
		if(commonUTIL.isEmpty(messConfigs)) {
			MessageServiceAppender.printLog("DEBUG",  "Message Configuration not Found on " + event.getEventType() + " for Product " + trade.getProductType() + " subType " + trade.getTradedesc1());
			
			commonUTIL.display("MessageProcessor" , "Message Configuration not Found on " + event.getEventType() + " for Product " + trade.getProductType() + " subType " + trade.getTradedesc1());
			manager.updateEventProcess(event);
			return;
		}
		Hashtable<String,Vector<Message>> filterMessages = new Hashtable<String,Vector<Message>>();
		
		MessageServiceAppender.printLog("DEBUG", "MessageProcessor found   ****** " +   messConfigs.size() + " MessageConfig  on "+ trade.getStatus() + " status ");
		
		for(int i=0;i<messConfigs.size();i++) {
			MessageConfig messConfig = messConfigs.get(i);
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor generating MessageBOHandler for  productTYpe " + messConfig.getProductType() + " and subTYpe " +   trade.getTradedesc1());
			
			BOMessageHandler boHandler = BOMessageHandler.getBOHandler(messConfig.getProductType(), messConfig.getProductSubType());
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor Found MessageBOHandler for  productTYpe " + messConfig.getProductType() + " and subTYpe " +   trade.getTradedesc1());
			
			if(boHandler == null) {
				commonUTIL.display("MessageProcessor" , "Message Handler not Found  for Product " + trade.getProductType() + " subType " + trade.getTradedesc1());
				MessageServiceAppender.printLog("DEBUG", "Message Handler not Found  for Product " + trade.getProductType() + " subType " + trade.getTradedesc1());
				
				//manager.updateEventProcess(event);
				return;
			}
			LegalEntity receiver = getLegalEntity(messConfig.getReceiverID());
			if(receiver == null) {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor Receiver null for messConfig " +   messConfig.getId());
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor selecting Receiver on basis of messConfig " + messConfig.getId() + " receiveer role  as  "+ messConfig.getReceiverRole() );
				
				if(messConfig.getReceiverRole().equalsIgnoreCase("CounterParty")) 
				receiver =  getLegalEntity(trade.getCpID());
					
				
			}
			if(receiver != null)
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor get Receiver " + receiver.getName() + " for messConfig " +   messConfig.getId());
			
			LegalEntity sender = getLegalEntity(messConfig.getPoid());
			sender.setRole(SDIConstants.PO); // messageConfig must know which is sender role , in config window we must provied
			messConfig.setSenderRole(SDIConstants.PO);
			if(sender == null) {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor sender null for messConfig " +   messConfig.getId());
				//manager.updateEventProcess(event);
				return;
				
			}
			if(receiver == null) {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor receiver getting  null for messConfig " +   messConfig.getId() + " selecting agent as receiver on the basis of sender role ");
				
				 Sdi poPerferedSdi = SDISelectorUtil.getSdi(sender.getRole(),sender.getId() ,trade.getCurrency(),trade.getProductType(),messConfig.getFormatType(),0);
				if( poPerferedSdi  == null) {
					MessageServiceAppender.printLog("DEBUG", "MessageProcessor not able Found SDI for sender   "+sender.getId() + " for product "+trade.getProductType());
					return;
					}
				receiver = getLegalEntity(poPerferedSdi.getAgentId());
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor get Sender " + receiver.getName() + " for messConfig " +   messConfig.getId() + " with receiver role as Agent ");
				
			}
			MessageServiceAppender.printLog("DEBUG", "MessageProcessor get Sender " + sender.getName() + " for messConfig " +   messConfig.getId());
			
			//sender.setRole("PO");// this is might be issue 
			Message message = null;
			if(event instanceof TradeEventProcessor) {
				try {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor Starting process to Fill Message Object " + event.getEventType() + " for messConfig " +   messConfig.getId() + " "+trade.getId());
				
			    message = boHandler.fillMessage(trade, null, messConfig,"TRADE",null,(TradeEventProcessor) event,receiver,sender);
			   // messages.add(message); 
			    MessageServiceAppender.printLog("DEBUG", "MessageProcessor Starting process filtering of  Message Object " + event.getEventType() + " for messConfig " +   messConfig.getId() + " "+trade.getId());
				   
			    filterOldMessages(message.getMessageConfigID(),trade.getId(),message,"TRADE",filterMessages);
			    MessageServiceAppender.printLog("DEBUG", "MessageProcessor Complete   filtering of  Message Object t" + event.getEventType() + " for messConfig " +   messConfig.getId() + " "+trade.getId());
			    MessageServiceAppender.printLog("DEBUG", "MessageProcessor Starting saving and update  of  Message Object t" + event.getEventType() + " for messConfig " +   messConfig.getId() + " "+trade.getId());
				
				saveFilterMessages(filterMessages,trade,transfer,event);
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor Ending of  saving and update  of  Message Object t" + event.getEventType() + " for messConfig " +   messConfig.getId() + " "+trade.getId());
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					 commonUTIL.displayError("MessageProcessor", "processMessage", e);
					MessageServiceAppender.printLog("ERROR", "MessageProcessor  processMessage TradeEventProcessor "+e);

				}
				manager.publishMessage(trade,null);
				
			}
			if(event instanceof TransferEventProcessor) {
				try {
				 message = boHandler.fillMessage(trade, transfer, messConfig,"TRANSFER",(TransferEventProcessor) event,null,receiver,sender);
			//	 messages.add(message); 
				 filterOldMessages(message.getMessageConfigID(),transfer.getId(),message,"TRANSFER",filterMessages);
					saveFilterMessages(filterMessages,trade,transfer,event);
				}catch (NullPointerException e) {
						// TODO Auto-generated catch block
						 commonUTIL.displayError("MessageProcessor", "processMessage", e);
						MessageServiceAppender.printLog("ERROR", "MessageProcessor  processMessage TransferEventProcessor "+e);

					}
				manager.publishMessage(trade,transfer);
			}
			
			  
		}
		
		
	
	}
	

	private boolean filteroutTrade(Trade trade) {
		// TODO Auto-generated method stub
		if(trade == null)
			return false;
		if(commonUTIL.isEmpty(trade.getAutoType()) || trade.getAutoType().equalsIgnoreCase("Original"))
		return true;
		return false;
	}

	private LegalEntity getLegalEntity(int id) {
		// TODO Auto-generated method stub
		LegalEntity le = null;
		synchronized (legalEntitys) {
			le = legalEntitys.get(id);
				}
		if(le == null) {
			try {
				le = refData.selectLE(id);
				if(le != null) 
					legalEntitys.put(le.getId(), le);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return le;
	}

	/*public void processMessage(TransferEventProcessor transferEvent, Transfer transfer) {
		// TODO Auto-generated method stub
		Trade trade = transferEvent.getTrade();
		Vector<MessageConfig> messConfigs = getMessageConfig(transferEvent,transferEvent.getTrade());
		Hashtable<String,Vector<Message>> filterMessages = new Hashtable<String,Vector<Message>>();
		Vector<Message> messages = new Vector<Message>();
		if(messConfigs == null || messConfigs.isEmpty() ) {
			commonUTIL.display("MessageProcessor" , "Message Configuration not Found on " + transferEvent.getEventType() + " for Proudct " + trade.getProductType() + " subType " + trade.getTradedesc1());
		    return;
		}
		for(int i=0;i<messConfigs.size();i++) {
			MessageConfig messConfig = messConfigs.get(i);
			BOMessageHandler boHandler = BOMessageHandler.getBOHandler(messConfig.getProductType(), messConfig.getProductSubType());
			LegalEntity receiver = getLegalEntity(messConfig.getReceiverID());
			LegalEntity sender = getLegalEntity(messConfig.getPoid());
			Message message = boHandler.fillMessage(trade, transfer, messConfig,"TRANSFER",transferEvent,null,receiver,sender);
			messages.add(message);
		}
		filterOldMessages(trade.getId(),messages,"TRANSFER",filterMessages);
		
	} */
	
	private void filterOldMessages(int messageConfigid,int objectID,Message message,String eventTriggerON,Hashtable<String,Vector<Message>> filterMessages) {
		// TODO Auto-generated method stub
		   MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages of  Message Object for messageCOnfig " + " "+messageConfigid + " for message eventType " + message.getEventType() + " trade id  " + message.getTradeId());
			
		Vector<Message> insertMessages = new Vector<Message>();
		Vector<Message> updatetMessages = new Vector<Message>();
		if(message == null )
			return;
		//for(int i=0;i<messages.size();i++) {
		//	Message message = messages.get(i);
			Vector<Message> oldmess = null;
			try {
			if(!message.getEventType().contains("CANCELLED"))  {
				oldmess =	getOLDMessage(messageConfigid,objectID,message.getEventType(),eventTriggerON);
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found "+oldmess.size() +"  oldmessages against messconfig " + message.getMessageConfigID() + " for trade ID "+ message.getTradeId() + " against event "+ message.getEventType() + " with subAction as " + message.getSubAction()); 
				}
			if(commonUTIL.isEmpty(oldmess)) {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found zero oldMessages  for trade id " + message.getTradeId() + " eventTYpe "+message.getEventType());
				
				if(message.getEventType().contains("CANCELLED")) {
					Vector<Message> cancelOriginalmessages = getOLDMessageForCancel(message.getEventType(),"TRADE",message.getMessageType(),message.getTradeId(),message.getFormat());
					
					if(!commonUTIL.isEmpty(cancelOriginalmessages)) {
						MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found zero oldMessages  for trade id " + message.getTradeId() + " on Cancel eventTYpe "+message.getEventType());
						
						Message originMess = cancelOriginalmessages.get(0);
						if(originMess.getStatus().equalsIgnoreCase("SEND")) {
							MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessages  for trade id " + message.getTradeId() + " on Cancel eventTYpe "+message.getEventType() + " with oldmess status as "+originMess.getStatus());
							
							message.setSubAction("CANC");
							message.setLinkId(originMess.getId());
							insertMessages.add(message);
						} else {
							MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessages  for trade id " + message.getTradeId() + " on Cancel eventTYpe "+message.getEventType() + " with oldmess status as "+originMess.getStatus());
							
						originMess.setSubAction("CANC");
						originMess.setAction("CANCEL");
						originMess.setEventType(message.getEventType());
						originMess.setUpdateBeforeSend("TRUE");
						updatetMessages.add(originMess);
						}
						MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + originMess.getSubAction() + " aganist message Action "+message.getAction()); 
						
					
					}
					  // this logic needs to be changed as first it must check if confirmation has been send or not if yes then send cancel messages.
				} else {
					message.setSubAction("NEW");
					insertMessages.add(message);
					MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + message.getSubAction() + " aganist message Action "+message.getAction()); 
					
					
				}
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in filterOldMessage adding message in insert Vector as old message are zero "); 
				
				
			} else {
				// checkout code need to added.
			    if(!commonUTIL.isEmpty(oldmess) && oldmess !=null) {
			    	MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found "+oldmess.size() + "  oldMessages on " + message.getTradeId() + " on eventTYpe "+message.getEventType());
					
			    		Message oldMessage = oldmess.get(0);  // always going to first(latest) record bz of we have used order id by in where clause.
			    		
			    			
			    		if(oldMessage.getSubAction().equalsIgnoreCase("NEW") && isMessageWasSend(oldMessage,oldmess)) {
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessage + " +  oldMessage.getId() + " with status " + oldMessage.getStatus() + " and subAction as " + oldMessage.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    			message.setSubAction("AMEND");
			    			message.setLinkId(oldMessage.getId());
			    			insertMessages.add(message);
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages Adding new Message in insert Vector with subAction as  + " +  message.getSubAction() + " on with status " + message.getStatus()+ " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + message.getSubAction() + " aganist message Action "+message.getAction() + " oldMessage found with subaction of oldMessage as "+oldMessage.getSubAction()); 
			    			
			    		} else {
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessage + " +  oldMessage.getId() + " with status " + oldMessage.getStatus() + " and subAction as " + oldMessage.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    			message.setSubAction("NEW");
			    			message.setId(oldMessage.getId());
			    			message.setUpdateBeforeSend("TRUE");
			    			message.setLinkId(oldMessage.getLinkId());
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + message.getSubAction() + " aganist message Action "+message.getAction() + " oldMessage found with subaction of oldMessage as "+oldMessage.getSubAction()); 
			    			
			    			updatetMessages.add(message);
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages not creating new Message and old message in update vector  insert Vector with subAction as  + " +  message.getSubAction() + " on with status " + message.getStatus()+ " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    		//	message.setLinkId(oldMessage.getLinkId());
			    			//message.
			    		}
			    		if(message.getEventType().contains("CANCELLED") && isMessageWasSend(oldMessage,oldmess)) {
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessage + " +  oldMessage.getId() + " with status " + oldMessage.getStatus() + " and subAction as " + oldMessage.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages with message " +  message.getId() + " with status " + message.getStatus() + " and subAction as " + message.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
							
			    			message.setSubAction("CANCEL");
			    			message.setLinkId(oldMessage.getId());
			    			if(isMessageWasSend(oldMessage,oldmess)) {
			    						 insertMessages.add(message);
			    						 MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages with oldMessage are found in send status so new message " +  message.getId() + " with status " + message.getStatus() + " and subAction are set to  " + message.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType() +" and added to insert vector ");
			 										 
			    				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + message.getSubAction() + " aganist message Action "+message.getAction() + " as oldMessage found with subaction of oldMessage as "+oldMessage.getSubAction()); 
					    		
			    		} else {
			    			MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages found oldMessage + " +  oldMessage.getId() + " with status " + oldMessage.getStatus() + " and subAction as " + oldMessage.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType());
			    			 MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages with oldMessage are not  found in send status so new message " +  message.getId() + " with status " + message.getStatus() + " and subAction are set to  " + message.getSubAction() + " on trade "+ message.getTradeId() + " on eventTYpe "+message.getEventType() +" and added to updated  vector ");
	 							
			    			message.setId(oldMessage.getId());
			    			message.setSubAction("NEW");
			    			message.setUpdateBeforeSend("TRUE");
			    				updatetMessages.add(message);
			    				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  filterOldMessages setting subAction " + message.getSubAction() + " aganist message Action "+message.getAction() + " oldMessage found with subaction of oldMessage as "+oldMessage.getSubAction()); 
							    
			    		}
			    				
			    		}
			    		
			    		
			    		
			    	
			    }
		//	}
		}
		filterMessages.put("insert",insertMessages);
		filterMessages.put("update",updatetMessages);
			}catch(NullPointerException e) {
				commonUTIL.displayError("MessageProcessor", "filterOldMessages", e);
				MessageServiceAppender.printLog("ERROR", "MessageProcessor in  filterOldMessages found error");
				
			}
	}

	
	private boolean isMessageWasSend(Message oldMessage,Vector<Message> Oldmessages) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(oldMessage.getStatus().equalsIgnoreCase("SEND"))
			flag =  true;
		for(int i=0;i<Oldmessages.size();i++) {
			Message message  = Oldmessages.get(i);
			if(message.getStatus().equalsIgnoreCase("SEND")) {
				flag =  true;
				break;
			}
			
		}
		return flag;
		
		
	}

	private Vector<Message> getOLDMessage(int messageConfig,int tradeID,String eventType,String messageOn) {
		Vector<Message> message = null;
		try {
			message = remoteBO.getMessages(messageConfig,tradeID, eventType, messageOn);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	private Vector<Message> getOLDMessageForCancel(String event_type,String triggerON,String formattype,int tradeid,String messageType) {
		Vector<Message> message = null;
		try {
			
			message = (Vector<Message>) remoteBO.getOLDMessageForCancel(event_type,formattype,tradeid,messageType,triggerON);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	private Vector<MessageConfig> getMessageConfig(EventProcessor tradeEvent, Trade trade) {
		Vector<MessageConfig> messConfigs = null;
		
		try {
			String messConfig = trade.getProductType()+"_"+trade.getTradedesc1()+"_"+tradeEvent.getEventType()+"_"+getBook(trade.getBookId()).getLe_id();	
			synchronized (messageConfigs) {
				messConfigs = messageConfigs.get(messConfig);				
			}
			if(commonUTIL.isEmpty(messConfigs)) {
				messConfigs = (Vector<MessageConfig>) refData.getMessageConfig(trade.getProductType(), trade.getTradedesc1().toUpperCase(), tradeEvent.getEventType(), getBook(trade.getBookId()).getLe_id());
			   if(!commonUTIL.isEmpty(messConfigs))
				messageConfigs.put(messConfig, messConfigs);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e) {
			commonUTIL.displayError("MessageProcessor", "getMessageConfig", e);
			MessageServiceAppender.printLog("ERROR", "MessageProcessor in  getMessageConfig found error");
			
		}
		return messConfigs;
	}
	
	private Book getBook(int bookID) {
				Book book = null;
				synchronized (books) {
						book = books.get(bookID);
				} 
				if(book == null) {
						book = new Book();
						book.setBookno(bookID);
						try {
								book = refData.selectBook(book);
								books.put(bookID, book);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							commonUTIL.displayError("MessaggeProcessor", "getBook  getting While reterive Book on id == "+bookID , e);
						}
				}
		return book;
		
	}
	
	private void saveFilterMessages(
			Hashtable<String, Vector<Message>> filterMessages,Trade trade,Transfer transfer,EventProcessor event) {
		// TODO Auto-generated method stub
		Vector<Message> newpublishMessages = new Vector<Message>();
		if(filterMessages == null || filterMessages.isEmpty()) {
			return;
		}
		try {
		Vector<Message> insertMessage = filterMessages.get("insert");
		Vector<Message> updateMessage = filterMessages.get("update");
			saveMessages(insertMessage,newpublishMessages,"insert",trade,transfer);
			saveMessages(updateMessage,newpublishMessages,"update",trade,transfer);
			//publishMessages.isEmpty();
			
			setPublishMessages(newpublishMessages);
			manager.updateEventProcess(event);
		}	catch(NullPointerException e) {
				commonUTIL.displayError("MessageProcessor", "saveFilterMessages", e);
				MessageServiceAppender.printLog("ERROR", "MessageProcessor in  saveFilterMessages found error");
				
			}
			
		//remoteBO.save
	}
	
	
    private void saveMessages(Vector<Message> messages,Vector<Message> publishMessage,String sqlType,Trade trade,Transfer transfer) {
    	try {
    		
			Vector<Message> messagesData =	remoteBO.saveMesage(messages, sqlType,trade,transfer);
			if(commonUTIL.isEmpty(messagesData)) {
				MessageServiceAppender.printLog("DEBUG", "MessageProcessor in  saveMessages Method no message are "+ sqlType+ " againg trade "+ trade.getId()); 
			    
				return;
			}
			if(messagesData != null ) {
				for(int i=0;i<messagesData.size();i++) {
					
					publishMessage.add(messagesData.get(i));
				}
			}
			
	} catch (RemoteException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}catch(NullPointerException e) {
		commonUTIL.displayError("MessageProcessor", "saveFilterMessages", e);
		MessageServiceAppender.printLog("ERROR", "MessageProcessor in  saveMessages found error");
		
	}
    }

    public synchronized void run(){
		  for( ; ; ) {
			  try {
				Thread.sleep(300);
				
					 
					
				
				
				if(manager.balance.size() > counter) {
					// System.out.println(":pppp:"+  manager.balance.size() + " counter " + counter);
					
						EventProcessor event = null;
						
							 synchronized (manager.transferEvents) {
					        	 
								 event   = manager.balance.get(counter);
							}
							if(event == null)
								return;
							 
							eventUnderProcess = false;
							if(event instanceof TradeEventProcessor) {
								TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
							     
							     
							      if(!isDuplicateEvent(tradeEvent.getTrade())) {
							    	  processMessage(tradeEvent);
							    	//  manager.publishMessage(tradeEvent.getTrade(), null);
							      }
							}
							if(event instanceof TransferEventProcessor) {
								TransferEventProcessor transferEvent = (TransferEventProcessor) event;
								
							      if(!isDuplicateEvent(transferEvent.getTransfer())) {
							    	  processMessage(transferEvent);
							    	 // manager.publishMessage(transferEvent.getTrade(), transferEvent.getTransfer());
							      }
							}
							 
							 counter = counter + 1;
							// eventUnderProcess = true;
							  
						 
					  
				 }
				
				 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  catch(NullPointerException e) {
					commonUTIL.displayError("MessageProcessor", "run", e);
					MessageServiceAppender.printLog("ERROR", "MessageProcessor in  run found error");
					
				}
		  }
	  }
    private boolean isDuplicateEvent(Trade trade) {
		boolean flag = false;
		String key = trade.getId()+"_"+trade.getStatus()+"_"+trade.getVersion();
		//System.out.println(" for key Trade = " + key); 
		if(duplicateEventCheck.containsKey(key))
			flag = true;
		return flag;
	}
	
	
    private boolean isDuplicateEvent(Transfer transfer) {
		boolean flag = false;
		String key = transfer.getId()+"_"+transfer.getStatus()+"_"+transfer.getVersion();
		//System.out.println(" for key Transfer = " + key); 
		if(duplicateEventCheck.containsKey(key))
			flag = true;
		return flag;
	}
	
	
	
	
}
