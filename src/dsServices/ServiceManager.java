package dsServices;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.PropertyConfigurator;

import dsEventProcessor.EngineEventMonitorProcessor;

import mqServices.messageProducer.CreateNewMessage;
import util.commonUTIL;

public abstract class ServiceManager  implements Runnable, Serializable {
	String applicationName = "";
	Thread engineMonitorThread = null;
	 EngineEventMonitorProcessor  event = null;
	 String monitorService = "Monitor";
	 /**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	int clientID = 0;
	 
	 static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
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
	public abstract void start();
	public abstract void stop();
	public ServiceManager() {
		// getLog4IntputStream();
		 //  PropertyConfigurator.configure(log4P);
	         
	}
	 Properties log4P = new Properties();
	  private void getLog4IntputStream() {
		   try {
			   log4P.load(this.getClass().getClassLoader().getResourceAsStream("resources/log4j.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	CreateNewMessage newMessage = null;
	public void setNewMessage(CreateNewMessage newMessage) {
		this.newMessage = newMessage;
	}
	
	public synchronized void publishnewEvent(String messageIndicator,String messageType,Object object) {
		//	System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPP   ");
			try{   
				if(newMessage == null)
					startProducingMessage();
				
				newMessage.produceNewMessage(messageIndicator,monitorService,messageType,(Serializable) object,null); 
				//newMessage.run();
				
				}catch(Exception e){
						commonUTIL.displayError("ServiceManager "+getApplicationName(), "publishnewTrade", e);
						}   
		//de.publishEvent(messageIndicator,"TRADE",messageType,(Serializable) object);
			} 
	 public void startProducingMessage() {
		   newMessage	 = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616"); // this 
		  
			Thread sendMessage =  new Thread(newMessage);
			 setNewMessage(newMessage);
			// sendMessage.start();
			 
	   }
	 public void startProducingMessage(String applicationName,String hostName,String portNo,ServiceManager serviceManager) {
		 try {
		 setApplicationName(applicationName);
		event =  new EngineEventMonitorProcessor();
		clientID = serviceManager.getClientID();
		 engineMonitorThread = new Thread(serviceManager,applicationName);
		 commonUTIL.display(getApplicationName(), "Registering "+ getApplicationName() + "  Monitoring Engine ...... to servier at "+hostName);
		   newMessage	 = new CreateNewMessage(hostName+portNo); // this 
		   engineMonitorThread.start();
			//Thread sendMessage =  new Thread(newMessage);
		  
			 setNewMessage(newMessage);
		 } catch(NullPointerException e) {
				System.out.println(" ServiceManager " +getApplicationName()+ "  startProducingMessage");
			
	 } catch(NumberFormatException e) {
			System.out.println(" ServiceManager " +getApplicationName()+ "  startProducingMessage");
		}
			// sendMessage.start();
			 
	   }

	 
	 public int getClientID() {
		// TODO Auto-generated method stub
		return clientID;
	}
	public void run() {
		 for( ; ; ) {
			 try {
				
				
				publishnewEvent("TRANS_NEWTRANSFER",monitorService,getEngineMonitorEvent(applicationName));
				Thread.sleep(7000);
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
		 if(event == null) 
			  event = new EngineEventMonitorProcessor();
		 event.setClientID(clientID);
		  event.setEngineName(applicationName);
		  event.setAlive(true);
		  event.setSavetoDB(false);
		  event.setIsAliveAtdateTime(format.format(commonUTIL.getCurrentDate()));
	 // System.out.println(event.getEngineName() + " published on  " + event.getIsAliveAtdateTime());
		 return event;
		 
	 }
	 
	public CreateNewMessage getNewMessage() {
		return newMessage;
	}

}
