package dsManager;

import java.io.InterruptedIOException;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import logAppender.TransferServiceAppender;

import org.apache.activemq.ActiveMQConnectionFactory;

import util.commonUTIL;
import beans.Users;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsServices.ServerConnectionUtil;
import dsServices.ServiceManager;

public abstract class ControllerManager  implements Runnable , ExceptionListener {
	
	 static private String hostName = "";
	 public static  ServerConnectionUtil de = null;
	 public String managerName = "";
	 boolean   isJMSConnectionON = false;
	 public ServiceManager serviceManager;
	 public String queueName = "TRADE";
	 Thread managerThread = null;
	 Session session = null;
	 Connection connection;
	 public String getQueueName() {
		return queueName;
	}
	

	ControllerManager manager = null;
	 public String getManagerName() {
		return managerName;
	}

			
		
	public static ServerConnectionUtil getDe() {
		return de;
	}
	 
	 public ControllerManager(String host,String hostName,String managerName) {
		  
		 de =ServerConnectionUtil.connect(host, 1099,commonUTIL.getServerIP());
		 this.hostName = hostName;
		 this.managerName = managerName;
		 queueName = "TRADE";
		 
	 }
	 
	 public ControllerManager(String host,String hostName,String managerName,String queueName) {
		
		 de =ServerConnectionUtil.connect(host, 1099,commonUTIL.getServerIP());
		 this.hostName = hostName;
		 this.managerName = managerName;
		 this.queueName = queueName;
		  
	 }
	 public ControllerManager(String host,String hostName,String managerName,String userName,String Password) {
		
		 de =ServerConnectionUtil.connectServer(host, 1099,commonUTIL.getServerIP(),managerName,userName,Password);
		 this.hostName = hostName;
		 this.managerName = managerName;
		 queueName = "TRADE";
		 
	 }
	 
	 public ControllerManager(String host,String hostName,String managerName,Users user) {
		
		 de =ServerConnectionUtil.connectServer(host, 1099,commonUTIL.getServerIP(),managerName,user);
		
		 this.hostName = hostName;
		 this.managerName = managerName;
		 queueName = "TRADE";
		 
	 }
	 public ControllerManager(String host,String hostName,String managerName,Users user,ServiceManager serviceManager) {
		 
		 de =ServerConnectionUtil.connectServer(host, 1099,commonUTIL.getServerIP(),managerName,user);
		 this.serviceManager = serviceManager;
		 this.hostName = hostName;
		 this.managerName = managerName;
		 queueName = "TRADE";
	 }
	 public ControllerManager(String host,String hostName,String managerName,Users user,ServiceManager serviceManager,String queueN ) {
		  
		 de =ServerConnectionUtil.connectServer(host, 1099,commonUTIL.getServerIP(),managerName,user);
		 this.serviceManager = serviceManager;
		 this.hostName = hostName;
		 this.managerName = managerName;
		 queueName = queueN;
	 }
	@Override
		public void onException(JMSException e) {
		
			commonUTIL.displayError( getManagerName(),"Error in listening" , e);
			TransferServiceAppender.printLog("ERROR", getManagerName() + "  getting Down as getting Error in listening JMS Service "+e);
			//if(serviceManager != null)
		//	serviceManager.stop();
			//System.exit(0);
		}

		@Override
		public synchronized void run() {
			  while (!isInterrupted()) {

				try {
					
					 if(!isJMSConnectionON) {
						 Jmsconnection(hostName);
					 }

			            // Create a Session
			          

			            // Create the destination (Topic or Queue)
			           
			            
			            Destination destination =  session.createTopic(queueName);
			         //   if(queueName.equalsIgnoreCase("TRADE"))            	
			                //System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTT producer " +    queueName);
			    	         // Create a MessageConsumer from the Session to the Topic or Queue
			            MessageConsumer consumer = session.createConsumer(destination);
			           
			            Message message = consumer.receive();

			            if (message instanceof ObjectMessage) {
			            	ObjectMessage oMessage = (ObjectMessage) message;
			            //	System.out.println(manager.managerName + "       >>>>>>>>>>>>>>>   " + ((EventProcessor)oMessage.getObject()).getClassName());
			            
			            	//if(checkEvents((EventProcessor)  oMessage.getObject())) {
			            	manager.handleEvent((EventProcessor) oMessage.getObject());
			            	//}
			            	
			               // pframe.refresh();
			    			//Thread.sleep(8000);
			            } 

			            consumer.close();
			           
			            Thread.sleep(10);
				 } catch (java.lang.NullPointerException e) {
						// TODO Auto-generated catch block
					   commonUTIL.displayError("ControllerManager " +  getManagerName(), "run()", e);
					   return;
				//	   if(serviceManager != null)
							//serviceManager.stop();
					      
					
				} catch (java.lang.InterruptedException e) {
					// TODO Auto-generated catch block
					commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
					//if(serviceManager != null)
						//serviceManager.stop();
					//System.exit(0);
				} catch (JMSException j) {
					// TODO Auto-generated catch block
					commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
				//	if(serviceManager != null)
						//serviceManager.stop();
					//System.exit(0);
				} catch(Exception e) {
					 commonUTIL.displayError( getManagerName(), "run()", e);
					// if(serviceManager != null)
							//serviceManager.stop();
					// System.exit(0);
				 
				}

			}
			
		}
		
        private boolean checkEvents(EventProcessor event) {
        	boolean flag = true;
        	if(event instanceof TaskEventProcessor) 
        		flag = false;
        	return flag;
        }
		private boolean isInterrupted() {
			// TODO Auto-generated method stub
			if(managerThread != null)
			 return managerThread.isInterrupted();
			return true;
		}


		public void publishEvent(String messageIndicator,String queueName,String messageType, EventProcessor event) {
			
			de.publishEvent(messageIndicator, queueName, messageType, (Serializable) event);
		}
	public void handleEvent(EventProcessor event)	 {
		//System.out.println(event.getOccurrenceTime());
	}
		
	
	public void start(ControllerManager manager) {
		this.manager = manager;
		managerThread = new Thread(manager);
		
		System.out.println("Starting >>>  " + manager.getManagerName() + " processor ");
		managerThread.start();
		
	}
	
	public void stop() {
	//	commonUTIL.display(manager.getManagerName(), "Stop <<<<<<<<<  " +manager.getManagerName());
		try {
			
			managerThread.interrupt();
			throw new InterruptedIOException();
			
			
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
if(managerThread.isInterrupted()) {
	System.out.println(manager.getManagerName() + " stop");
	TransferServiceAppender.printLog("DEBUG", "TransferService is stop ");
	
	commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
	if(serviceManager != null)
		serviceManager.stop();
	//System.exit(0);
			}
			
		}
		
	//manager
		
		
	}
	private void Jmsconnection(String hostname) {
		
		 ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+hostname+":61616");

        // Create a Connection
       
		try {
			connection = connectionFactory.createConnection();
			connection.start();

	         connection.setExceptionListener(this);
	           session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	           isJMSConnectionON = true;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
