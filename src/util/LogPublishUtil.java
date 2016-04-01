package util;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import constants.logConstants;

import dbSQL.EventSQL;
import dbSQL.dsSQL;
import dsEventProcessor.DebugEventProcessor;
import dsEventProcessor.EventProcessor;

import logAppender.DebugAppender;
import logAppender.SQLAppender;
import mqServices.messageProducer.CreateNewMessage;

public class LogPublishUtil extends Thread {
	
	
	static Hashtable<Integer,String> messages = new  Hashtable<Integer,String>();
	static int producerCounter = 0;
	static int counsumerCounter = 0;
	boolean isCrossLimit =false;
	
	public LogPublishUtil(String name) {
		System.out.println(name + " Starting Cosmos  Log  publisher  * ** *** **  Started  ");
		
		this.start();
	}
	public static  void addMessage(String message) {	
		synchronized (messages) {
			producerCounter++;
			//System.out.println(" producerCounter " + producerCounter);
			messages.put(producerCounter, message);
		}
		
		
		
	}
	DebugEventProcessor event = new DebugEventProcessor();
	boolean lock = true;
	final ReentrantLock r1 = new ReentrantLock();
	public synchronized void run() {
		
		   
		 r1.lock();
			 try {
			     
				for( ; ; ) {
				 if(r1.isHeldByCurrentThread()) {
					Thread.sleep(220);
					
					if(counsumerCounter < producerCounter) {
						String message = (String) messages.get(counsumerCounter);
						
						if(message != null && message.contains("SQL")) {
						//	System.out.println(message);
							SQLAppender.printLog(logConstants.INFO, counsumerCounter + " "+message);
						}
						if(message != null && message.contains("DEBUG")) {
							//	System.out.println(message);
								DebugAppender.printLog(logConstants.INFO, counsumerCounter + " "+message);
							}
						//event.setComments(message);
						
						//	publishnewTrade("TRANS_NEWTRANSFER","TRADE",event);
						//	System.out.println(lock + " producerCounter " + producerCounter + "  " + "counsumerCounter  "+counsumerCounter + " "+message);
							
						
						counsumerCounter++;
					
					}
					
					if(producerCounter == 10000000) {
						producerCounter = 0;
						counsumerCounter = 0;
						messages.clear();
					}
					
				}
				}
			 
			 } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					r1.unlock();
				}
			 
		
		
	}
	CreateNewMessage newMessage = null;
	public void setNewMessage(CreateNewMessage newMessage) {
		this.newMessage = newMessage;
	}
	public synchronized void publishnewTrade(String messageIndicator,String messageType,Object object) throws RemoteException {
		//	System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPP   ");
			try{   
				if(newMessage == null)
					startProducingMessage();
				
				newMessage.produceNewMessage(messageIndicator,"TRADE",messageType,(Serializable) object,null); 
				//newMessage.run();
				
				}catch(Exception e){
						commonUTIL.displayError("LogPublishUtil", "publishnewTrade", e);
						}   
		//de.publishEvent(messageIndicator,"TRADE",messageType,(Serializable) object);
			} 
	 public void startProducingMessage() {
		   newMessage	 = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616");
		  
			Thread sendMessage =  new Thread(newMessage);
			 setNewMessage(newMessage);
			 sendMessage.start();
			 
	   }

	public CreateNewMessage getNewMessage() {
		return newMessage;
	}
}
