package dsServices;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import mqServices.messageProducer.CreateNewMessage;
import util.commonUTIL;
import dsEventProcessor.EngineEventMonitorProcessor;

public class EngineMonitorUtil extends Thread {
	
	CreateNewMessage newMessage = null;
	String applicationName = "";
	EngineEventMonitorProcessor event = null;
	 static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	 
	 public EngineMonitorUtil(String name) {
		 event = new EngineEventMonitorProcessor();
		 setApplicationName(applicationName);
			System.out.println(name + " Starting Cosmos   Monitoring Engine  * ** *** ** for "+applicationName+ " Service Manager  ");
			
		//	this.start();
		}
		public void startMonitor() {
			this.start();
			
		}
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setNewMessage(CreateNewMessage newMessage) {
		this.newMessage = newMessage;
	}
	
	public synchronized void publishnewEvent(String messageIndicator,String messageType,Object object) {
		//	System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPP   ");
			try{   
				if(newMessage == null)
					startProducingMessage();
				
				newMessage.produceNewMessage(messageIndicator,"TRADE",messageType,(Serializable) object,null); 
				//newMessage.run();
				
				}catch(Exception e){
						commonUTIL.displayError("ServiceManager "+getApplicationName(), "publishnewTrade", e);
						}   
		//de.publishEvent(messageIndicator,"TRADE",messageType,(Serializable) object);
			} 
	 public void startProducingMessage() {
		   newMessage	 = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616"); // this 
		  
		//	Thread sendMessage =  new Thread(newMessage);
			 setNewMessage(newMessage);
			// sendMessage.start();
			 
	   }
	 public void startProducingMessage(String applicationName,String hostName,String portNo,ServiceManager serviceManager) {
		 setApplicationName(applicationName);
		event =  new EngineEventMonitorProcessor();
		
		// engineMonitorThread = new Thread(serviceManager,applicationName);
		 commonUTIL.display("TransferManager", "Registering TransferManager Monitoring Engine ...... to servier at "+hostName);
		   newMessage	 = new CreateNewMessage(hostName+portNo); // this 
		 //  engineMonitorThread.start();
			//Thread sendMessage =  new Thread(newMessage);
		  
			 setNewMessage(newMessage);
			
			// sendMessage.start();
			 
	   }

	 
	 public void run() {
		 for( ; ; ) {
			 try {
				Thread.sleep(1000);
				publishnewEvent("TRANS_NEWTRANSFER","TRADE",getEngineMonitorEvent(applicationName));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			   commonUTIL.displayError("ServiceManager "+getApplicationName(), "Run Method", e);
			
		 } catch (NullPointerException e) {
				// TODO Auto-generated catch block
			   commonUTIL.displayError("ServiceManager "+getApplicationName(), "Run Method", e);
			
		 }
		 }
			 
	 }
	 
	 public EngineEventMonitorProcessor getEngineMonitorEvent(String applicationName) {
		  event.setEngineName(applicationName);
		  event.setAlive(true);
		  event.setSavetoDB(false);
		  event.setIsAliveAtdateTime(format.format(commonUTIL.getCurrentDate()));
		//  System.out.println(event.getEngineName() + " publish " + event.getIsAliveAtdateTime());
		 return event;
		 
	 }
	 
	public CreateNewMessage getNewMessage() {
		return newMessage;
	}
}
