package dsServices;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import util.LogPublishUtil;
import util.commonUTIL;

import logAppender.ServerServiceAppender;
import mqServices.Broker.StartMQBroker;

import dbSQL.UsersSQL;
import dbSQL.dsSQL;

import dsEventProcessor.AdminEventProcessor;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.EventProducerServer;

import beans.DealBean;
import beans.ServerBean;
import beans.Users;


public class ServerControllerImp  extends ServerManager implements RemoteDeal ,RemoteAdminManager,RemoteEngineMonitor 
{
	 
   int      thisPort;
   String   thisAddress;
   Registry registry; 
   String username;
   String password;
   ServerConnectionUtil sconn = null;
   EventProducerServer eventServer = null;
 static  ConcurrentHashMap<String,String> runningEngines = new ConcurrentHashMap<String,String>();
   ConcurrentHashMap<Integer,Users> connectedUserData  = new ConcurrentHashMap<Integer,Users>();

  static HashMap<String,Users> monitorConnectedUserData  = new  HashMap<String,Users>();
  static boolean holdRemoveEngineSignals = false;
static ConcurrentHashMap<String, String> engineSignals = new ConcurrentHashMap<String, String> ();
   static int clientID = 1544;
   RemoteTrade remoteTrade = null; // used for publishing event 
   EngineMonitorService monitorService = null;
   public void start()     {
     // get the address of this host.
	  // getLog4IntputStream();
	 // PropertyConfigurator.configure(log4P);
          
	
          
       
        
       System.out.println("this address="+thisAddress+",port="+thisPort);
       ExecutorService executor = Executors.newFixedThreadPool(1);
       try{
    	   thisPort=1099; 
    	   executor.execute(new LogPublishUtil("ServerControllerImp"));
    	//   executor.execute(new LogPublishUtil("ServerControllerImp"));
    	
    	   	
           try {
			thisAddress = (InetAddress.getLocalHost()).toString();
			executor.awaitTermination(5, TimeUnit.MILLISECONDS);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
           sconn = new ServerConnectionUtil();
           sconn.setdefault(sconn);
           sconn._dataServerName = "localhost";
           sconn._rmiPort = 1099;
           sconn._hostName = thisAddress;
           String host = thisAddress.substring(0, thisAddress.indexOf('/'));
           System.out.println("Server starting at port "+ sconn._rmiPort + " hostName " + sconn._hostName );
     
       registry = LocateRegistry.createRegistry( thisPort );
   
       getInputStream();
       
       startRMIServices(props,registry);
       remoteTrade =ServiceInit.getRemoteTrade();
       monitorService = new EngineMonitorService(this);
       StartMQBroker startMQ = new StartMQBroker(host,61616);
       
       
   //    registry.rebind("rmiServer", this); 
      
      
       }
       catch(RemoteException e){
    	   commonUTIL.displayError("Server","Error in Starting Server  <<<<<<<<<<<<<  " , e);
    	   System.exit(0);
       } finally {
    	   executor.shutdownNow();
       }

   	
   }
 
   public void stop() {
   	try {
   		
				try {
					registry.unbind("rmiServer");
					System.out.println(" rmiServer going down");
				} catch (AccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	
   	
   }
   public static String Path = null;
	public static String getPath() {
		return Path;
	}

	public static void setPath(String path) {
		Path = path;
	}
	 Properties props = new Properties();
   private void getInputStream() {
	      try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("resources/RMIServices.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
   private void startRMIServices(Properties props,Registry regist) {
   	String serviceList = props.getProperty("services");
       String[] services = serviceList.split(",");
       for (int i = 0; i < services.length; i++) {

           String service = services[i];
           try {
               System.out.println( "Starting service: "
                       + service);
               ServerServiceAppender.printLog("INFO", "Starting service: "
                       + service);
               
               ConfigService serviceConfig = ServerConnectionUtil.loadServiceConfiguration(service,
                                                                             props);
               ServiceInit.startService(serviceConfig,regist);
           } catch (Exception e) {
        	    ServerServiceAppender.printLog("ERROR", " Error starting service " + service + " "+e);
                
           	System.out.println( " Error starting service " + service + " "+ e);
               throw new RuntimeException("Error starting service "
                       + service, e);
           }
       }
   }
   static public void main(String args[])
   
   {
	   ServerControllerImp s = null;
       try{

       s=new ServerControllerImp();
      
       s.start();
     

   } 

   catch (Exception e) {

          e.printStackTrace();
       //   s.stop();
         System.exit(1);

   } finally {
   	//s.stop();
   	System.out.println("coming");
   }

    

}

@Override
public ServerBean connect(String username,String password) throws RemoteException {
	// TODO Auto-generated method stub
	ServerBean sbean = new ServerBean();
	Users user = (Users) UsersSQL.selectUsers(username, password, dsSQL.getConn());
	
	if(user == null) {
		commonUTIL.display("INFO", "Connect "+user.getUser_name() +" Not Register in Database");
		return sbean;
		
	}
	try {
		user.setHostName(java.rmi.server.RemoteServer.getClientHost());
	} catch (ServerNotActiveException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sbean.set_dataServerName(sconn.getdefault()._dataServerName);
	sbean.setRmiServices(sconn.getdefault().getRmiServices());
	sbean.set_rmiPort(sconn.getdefault()._rmiPort);
	sbean.set_hostName(sconn.getdefault()._hostName);
	sbean.setWindowSetting("String");
	
	sbean.setClientID(clientID++);
	synchronized (connectedUserData) {
		connectedUserData.put(sbean.getClientID(), user);
		
	}
	
	System.out.println("Connected ... " + sconn.getdefault()._hostName  + " with user " + user.getUser_name());
	ServerServiceAppender.printLog("INFO","Connected ... client with client ID " + sbean.getClientID()  + "  "+ sconn.getdefault()._hostName + " with user " + user.getUser_name());
	
	
	
	return sbean;
}


@Override
public ServerBean connect() throws RemoteException {
	// TODO Auto-generated method stub
	ServerBean sbean = new ServerBean();
	try {
		System.out.println("Client Host "+java.rmi.server.RemoteServer.getClientHost());
	} catch (ServerNotActiveException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sbean.set_dataServerName(sconn.getdefault()._dataServerName);
	sbean.setRmiServices(sconn.getdefault().getRmiServices());
	sbean.set_rmiPort(sconn.getdefault()._rmiPort);
	sbean.set_hostName(sconn.getdefault()._hostName);
	sbean.setWindowSetting("String");
	sbean.setClientID(clientID++);
	
	System.out.println("Connected ... client with client ID " + sbean.getClientID()  + "  "+ sconn.getdefault()._hostName);
	ServerServiceAppender.printLog("INFO","Connected ... client with client ID " + sbean.getClientID()  + "  "+ sconn.getdefault()._hostName);	
	
	
	
	return sbean;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public int publishEvent(String messageIndicator,String queueName, String messageType,
		Object object) throws RemoteException {
	// TODO Auto-generated method stub
	if(eventServer == null)
	 eventServer = new EventProducerServer(commonUTIL.getLocalHostName()+":61616");
	
	if(!eventServer.isFlagStartup()) {
	Thread sendMessage =  new Thread(eventServer);
	// setNewMessage(messageProducer);
	 sendMessage.start();
	} else {
	eventServer.produceNewMessage(messageIndicator, messageType, messageType,(Serializable) object,null);
	}
	return 0;
}
@Override
public void addEngines(String engineName,int clientID,Users user) {
	// TODO Auto-generated method stub

	System.out.println("addEngines addEngines " + engineName + " " + clientID);
	holdRemoveEngineSignals = true;
	String engineData [] = engineName.split("_");
	String name = engineData[0];
	String siginal =  engineData[1];
	if(siginal.equalsIgnoreCase("Stopped")) {
		holdRemoveEngineSignals = false;
		int id =0;
		try {
			String applicationID = engineSignals.get(name);
			if(!commonUTIL.isEmpty(applicationID)) {
		 id =  Integer.parseInt(applicationID);
			} else {
				return;
			}
		}catch(NumberFormatException n) {
			System.out.println("AddEngines " +   engineName + " " +  n);
			id = 0;
		}
		if(id == clientID) {
		removeEngine(engineName,clientID); 
		//removeUserforMonitor(name, user, clientID);
		}
	}else {
	AdminEventProcessor adminEvent = new AdminEventProcessor();
	adminEvent.setEngineStartedSignal(name+"_"+siginal+"_"+clientID);
	//System.out.println("AddEngines Method == " + adminEvent.getEngineStartedSignal());
	adminEvent.setEngineViewerPanel(true);
	adminEvent.setClientID(clientID);
	adminEvent.setUser(user);
	adminEvent.setSavetoDB(false);
	try {
		user.setHostName(java.rmi.server.RemoteServer.getClientHost());
	} catch (ServerNotActiveException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	addUserforMonitor(name,user,clientID);
	
	addEngines(name,siginal+"_"+clientID);
	synchronized (engineSignals) {
		
		engineSignals.put(name,String.valueOf(clientID));
		holdRemoveEngineSignals = false;
	}
	
	try {
		if(remoteTrade == null)
			remoteTrade = ServiceInit.getRemoteTrade();
		remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",adminEvent);
		
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

//	System.out.println("End of addEngines addEngines " + engineName + " " + clientID);
	}
}


private  void addUserforMonitor(String name, Users user, int clientID2) {
	// TODO Auto-generated method stub
	monitorConnectedUserData.put(name+"_"+clientID2,user);
	
}

private  void removeUserforMonitor(String name, Users user, int clientID2) {
	// TODO Auto-generated method stub
	monitorConnectedUserData.put(name+"_"+clientID2,user);
	System.out.println("********************************************" + monitorConnectedUserData.size());
	
}

private synchronized void addEngines(String engineName,String siginal) {
	if(engineName.contains("Manager"))
	runningEngines.put(engineName, engineName+"_"+siginal);
}

private synchronized void removeEngines(String engineName,String siginal) {
	if(engineName.contains("Manager"))
	runningEngines.put(engineName, engineName+"_"+siginal);
}

@Override
public void removeEngine(String engineName,int clientID) {
	// TODO Auto-generated method stub
	System.out.println("removeEngine method " + engineName + " " + clientID);
	try {
	AdminEventProcessor adminE = null;
	int id = 0;
	if(holdRemoveEngineSignals)
		return;
    if(engineName.contains("_")) {
    	String name1 = engineName.substring(0, engineName.indexOf("_"));
    	synchronized (engineSignals) {
    		String cID = engineSignals.get(name1);
    		if(cID == null)
    			return;
    		id  =  Integer.parseInt(cID);
    		if(id == clientID)
    			engineSignals.remove(name1);
    		
    	}
    	
    	if(id == clientID) {
    		
    		String name = engineName.substring(0, engineName.indexOf("_"));
    		String siginal = engineName.substring(engineName.indexOf("_")+1,engineName.length() );
    		AdminEventProcessor adminEvent = new AdminEventProcessor();
    		adminEvent.setEngineStartedSignal(name+"_"+siginal+"_"+clientID);
    		adminEvent.setEngineViewerPanel(true);
    		adminEvent.setSavetoDB(false);
    		adminEvent.setClientID(clientID);
    		removeEngines(name,siginal+"_"+clientID);
    		Users user = monitorConnectedUserData.get(name+"_"+clientID);
    		user.setApplicattionNameLoginOn("NONE");
    		monitorConnectedUserData.put(name+"_"+clientID,user);
    		System.out.println("Client ID " +clientID + " Disconneted from .... " + user.getHostName() + " "+ user.getUser_name() + " " + user.getApplicattionNameLoginOn());
    		ServerServiceAppender.printLog("INFO","Client ID " +clientID + " Disconneted from .... " + user.getHostName() + " "+ user.getUser_name() + " " + user.getApplicattionNameLoginOn());	
    		
    		adminEvent.setUser(user);
    		
    		try {
    			if(remoteTrade == null)
    				remoteTrade = ServiceInit.getRemoteTrade();
    			  remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",adminEvent);
    		//	runningEngines.put
    			
    		} catch (RemoteException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}

    	System.out.println("End of removeEngine method " + engineName + " " + clientID);
    }
	}catch(NumberFormatException e) {
		System.out.println("ServerControllerImp removeEngine "+ engineName);
	}
	
	
}



@Override
public Vector<String> getALLRunningEngines() {
	// TODO Auto-generated method stub
Vector<String> runningEng = new Vector<String>();
  Collection c = runningEngines.values();
   if(c != null) {
	   Iterator<String> data = c.iterator();
	   while(data.hasNext()) {
		   runningEng.add(data.next());
	   }
   }
  
  return runningEng;
}

@Override
public ServerBean connect(String username, String password,
		String applicationName) throws RemoteException {
	// TODO Auto-generated method stub
	ServerBean sbean = new ServerBean();
	
	Users user = (Users) UsersSQL.selectUsers(username, password, dsSQL.getConn());
	if(user == null) {
		commonUTIL.display("INFO", "applicationName with username "+ username +" Not Register in Database");
		
		ServerServiceAppender.printLog("INFO", " username "+ username +" Not Register in Database for application " + applicationName);			
		
		
	} else {
		sbean.set_dataServerName(sconn.getdefault()._dataServerName);
		sbean.setRmiServices(sconn.getdefault().getRmiServices());
		sbean.set_rmiPort(sconn.getdefault()._rmiPort);
		sbean.set_hostName(sconn.getdefault()._hostName);
		sbean.setWindowSetting("String");
	
	sbean.setClientID(clientID++);System.out.println(  applicationName + "ServiceManager"+applicationName+"  to Server Connected ... with id " + sbean.getClientID() + sconn.getdefault()._hostName  + " with user " + user.getUser_name());
	synchronized (connectedUserData) {
		connectedUserData.put(sbean.getClientID(), user);
		
	}
	}
	return sbean;
}

@Override
public ServerBean connect(Users user1,
		String applicationName) throws RemoteException {
	// TODO Auto-generated method stub
	Users user = null;
	try {
	//	System.out.println(java.rmi.server.RemoteServer.getClientHost());
		user = (Users) UsersSQL.selectUsers(user1.getUser_name(), user1.getPassword(), dsSQL.getConn());
		if(user == null) {
			
			ServerServiceAppender.printLog("INFO", " username "+ user1.getUser_name() +" Not Register in Database for application " + applicationName);			
		
			ServerBean sbean = new ServerBean();
			return sbean;
		}
		user.setHostName(java.rmi.server.RemoteServer.getClientHost());
	} catch (ServerNotActiveException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ServerBean sbean = new ServerBean();
	sbean.set_dataServerName(sconn.getdefault()._dataServerName);
	sbean.setRmiServices(sconn.getdefault().getRmiServices());
	sbean.set_rmiPort(sconn.getdefault()._rmiPort);
	sbean.set_hostName(sconn.getdefault()._hostName);
	sbean.setWindowSetting("String");
	
	sbean.setClientID(clientID++);
	
	synchronized (connectedUserData) {
		connectedUserData.put(sbean.getClientID(), user);
			}
	System.out.println(  applicationName + "ServiceManager"+applicationName+"  to Server Connected ... with id " + sbean.getClientID() + sconn.getdefault()._hostName  + " with user " + user.getUser_name());
	ServerServiceAppender.printLog("INFO", applicationName + "ServiceManager"+applicationName+"  to Server Connected ... with id " + sbean.getClientID() + sconn.getdefault()._hostName  + " with user " + user.getUser_name());	
	
	return sbean;
}

@Override
public void monitorLiveEngineService(EventProcessor event) {
	// TODO Auto-generated method stub
	
}

@Override
public Vector<String> getALLUserConnected() throws RemoteException {
	Vector<String > userD = new Vector<String>();
	 for(Map.Entry<String, Users> entry : monitorConnectedUserData.entrySet()) {
		 String userData = entry.getKey();
		 String userS = "";
		 Users user = entry.getValue();
		 if(!user.getApplicattionNameLoginOn().equalsIgnoreCase("NONE")) {
			 userS = userData + "_"+user.getApplicattionNameLoginOn() + "_"+user.getHostName();
			 userD.add(userS);
		 }
		 
		
	 }

	return  userD;
}

	

}