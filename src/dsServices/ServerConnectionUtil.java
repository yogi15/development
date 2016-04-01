package dsServices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import logAppender.ServerServiceAppender;

import beans.ServerBean;
import beans.Users;

import util.JavaUtil;
import util.commonUTIL;

public class ServerConnectionUtil {
	
	
	Remote ref;
	protected  String _dataServerName; 
	protected   String _hostName;
	protected  int _rmiPort;
	 String username;
	 String password;
	 /**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}
	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}




	int clientID = 0;
	public   Map rmiServices = Collections.synchronizedMap(new HashMap());
	private   String serverName = "ServerController";
	static public ServerConnectionUtil _default;
	
	static RemoteDeal remoteDeal = null;
	
	
	
	public static ServerConnectionUtil   connect(String _dsName,int rmiPort,String hostName)  {
		 ServerConnectionUtil sutil = new ServerConnectionUtil();
try {
	sutil.set_dataServerName(_dsName);
	
	sutil.set_rmiPort(rmiPort);
	sutil.set_hostName(hostName);
			
	remoteDeal	= (RemoteDeal) sutil.getRMIService(sutil.serverName);
			ServerBean s = (ServerBean) remoteDeal.connect();
			
			
				sutil.set_dataServerName(s.get_dataServerName()); 
			//	sutil.set_dataServerName(s.get_dataServerName()); 
				//sutil.set_dataServerName(s.get_dataServerName()); 
				sutil.setUsername(s.getUsername());
				sutil.setPassword(s.getPassword());
					 sutil.setRmiServices(s.getRmiServices());
					 sutil.setdefault(sutil);
					// System.out.println("connect to Server...." + s.get_hostName() + " "+s.get_rmiPort());
					
					 sutil.setClientID(s.getClientID());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return getdefault();
	
	}
	public static ServerConnectionUtil   connectServer(String _dsName,int rmiPort,String hostName,String applicationName,String userName,String password)  {
		 ServerConnectionUtil sutil = new ServerConnectionUtil();
try {
	sutil.set_dataServerName(_dsName);
	sutil.set_rmiPort(rmiPort);
	sutil.set_hostName(hostName);
			
	remoteDeal	= (RemoteDeal) sutil.getRMIService(sutil.serverName);
			ServerBean s = (ServerBean) remoteDeal.connect(userName, password, applicationName);
			
			
				sutil.set_dataServerName(s.get_dataServerName()); 
			//	sutil.set_dataServerName(s.get_dataServerName()); 
				//sutil.set_dataServerName(s.get_dataServerName()); 
				sutil.setUsername(s.getUsername());
				sutil.setPassword(s.getPassword());
					 sutil.setRmiServices(s.getRmiServices());
					 sutil.setdefault(sutil);
					 sutil.setClientID(s.getClientID());
		//System.out.println(applicationName + "ServiceManager" + " connect to Server.... on " + s.get_hostName() + " "+s.get_rmiPort() + " with UserName " + userName );
					
						
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return getdefault();
	
	}
	public static ServerConnectionUtil   connectServer(String _dsName,int rmiPort,String hostName,String applicationName,Users user)  {
		 ServerConnectionUtil sutil = new ServerConnectionUtil();
try {
	
	sutil.set_dataServerName(_dsName);
	sutil.set_rmiPort(rmiPort);
	sutil.set_hostName(hostName);

	remoteDeal	= (RemoteDeal) sutil.getRMIService(sutil.serverName);
			ServerBean s = (ServerBean) remoteDeal.connect(user.getUser_name(),user.getPassword(), applicationName);
			if(s.getClientID() == 0) {
				return null;
			}
				sutil.set_dataServerName(s.get_dataServerName()); 
			//	sutil.set_dataServerName(s.get_dataServerName()); 
				//sutil.set_dataServerName(s.get_dataServerName()); 
				sutil.setUsername(s.getUsername());
				sutil.setPassword(s.getPassword());
					 sutil.setRmiServices(s.getRmiServices());
					 sutil.setdefault(sutil);
					 sutil.setClientID(s.getClientID());
		//System.out.println(applicationName + "ServiceManager" + " connect to Server.... on " + s.get_hostName() + " "+s.get_rmiPort() + " with UserName " + userName );
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return getdefault();
	
	}
	  
	public void publishEvent(String messageIndicator,String queueName,String messageType,Object object) {
		try {
			remoteDeal.publishEvent(messageIndicator,queueName ,messageType, object);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	  
	  public ServerConnectionUtil() {
		 
		  
	  }
	  public ServerConnectionUtil(String _dsName,int rmiPort,String hostName,String username,String password) {
		 // connect();
		  _dataServerName = _dsName;
			_hostName = hostName;
			_rmiPort = rmiPort;
			connect(_dsName,rmiPort,hostName);
			
	  }
	
	/**
     * get remote service by its name
     * The hostname & port will be defaulted from the env. property file 
     * @param serviceName
     * @return
     * @throws RemoteException
     */
    public  Remote getRMIService(String serviceName) throws RemoteException {
        Remote ref = (Remote) rmiServices.get(serviceName + "_" + _dataServerName);
        if (ref == null){ 
            ref= getRMIService(serviceName, _rmiPort, _hostName);
        }
        return ref;
    }

    /**
     * get remote services by its name, registry port. 
     * The host name will be the defaulted from the env. property file
     * 
     * @param serviceName
     * @param port
     * @return
     * @throws RemoteException
     */
    public  Remote getRMIService(String serviceName, int port) throws RemoteException {
        Remote ref = (Remote) rmiServices.get(serviceName + "_" + _dataServerName);
        if (ref == null) {
            ref =getRMIService(serviceName, port, _hostName);
        }
        return ref;
    }
	
	
	
	 public  Remote getRMIService(String serviceName, int port, String hostName) throws RemoteException {
        String name = serviceName + "_" + _dataServerName;
        Remote ref = (Remote) rmiServices.get(name);
        
        if(ref == null) {
            try {
                ref =  (Remote) ServiceInit.lookupService(serviceName, port, hostName, _dataServerName);
                rmiServices.put(name, ref);
              //  int serviceId = MonitorClient.getServiceId(serviceName);
             /*   String nickname = MonitorClient.getServiceNickname(serviceId);
                if (nickname == null) {
                    MonitorClient.setServiceNickname(serviceId, "Test"
                            + serviceName);
                } */
            } catch (ServiceException e) {
               System.out.println("ServiceConntionUtil "+  e);
                throw new RemoteException(e.getMessage(), e);
            } catch (Exception e) {
            	 System.out.println("ServiceConntionUtil "+  e);
                throw new RemoteException(e.getMessage(), e);
            }
        }
        
        return ref;
    }
	 
	  public static ConfigService loadServiceConfiguration(String service, Properties props){
		 ConfigService serviceConfig = new ConfigService();
         validateProperties(props, 
                            new String[] { service+".name", service+".class", service+".port"}, 
                            "Missing required properties for service "+service);
        
         //The following are properties are loaded from the property file : 
         // (1) serviceName (2) Service class (3) service Port (port where the object is to be exported to)
         String serviceName = props.getProperty(service+".name"); 
         serviceConfig.setServiceName(serviceName);
         
         String serviceClass = props.getProperty(service+".class");
         serviceConfig.setServiceClass(serviceClass);
         
         int servicePort = Integer.parseInt(props.getProperty(service+".port"));
         serviceConfig.setPort(servicePort);
         
         //Retrieve list of server invocation handlers
         String serverInvocationHandlerList = props.getProperty(service+".server-handlers");
         if (!JavaUtil.isEmpty(serverInvocationHandlerList)){
             String[] invocationHandlers = serverInvocationHandlerList.split(",");
             List list = new ArrayList();
             for (int count=0; count < invocationHandlers.length; count++) {
                 list.add(invocationHandlers[count]);
             }
             serviceConfig.setServerInvocationHandlers(list);
         } else {
             System.out.println( "No server invocation handlers set for " + serviceName);
         }
         
         
         //Retrieve list of client invocation handlers
         String clientInvocationHandlerList = props.getProperty(service+".client-handlers");
         if (!JavaUtil.isEmpty(clientInvocationHandlerList)){
             String[] invocationHandlers = clientInvocationHandlerList.split(",");
             List list = new ArrayList();
             for (int count=0; count < invocationHandlers.length; count++) {
                 list.add(invocationHandlers[count]);
             }
             serviceConfig.setClientInvocationHandlers(list);
         } else {
        	 System.out.println(  "No client invocation handlers set for " + serviceName);
         }
         
         //We set the following from the calypso env property file
         int rmiPort = 1099; // this must come from properties files
         serviceConfig.setRegistryPort(rmiPort);
         
         String dsName = "localhost";// come from property file.  Defaults.getDataServerName();
         serviceConfig.setNodeName(dsName);
         
         //we default the following to true for now
         serviceConfig.setClientSocketFactory("true");
         serviceConfig.setServerSocketFactory("true");
         
         return serviceConfig;
     }
	 
	 /**
      * Validate the given properties file
      * 
      * @param props : properties file to be validated
      * @param required : reqiuired fileds to be specified in the properties file
      * @param msg : msg to display if the properties file is missing the required fields
      */
     
     private static void validateProperties(Properties props, String[] required, String msg) {
         List missing = new ArrayList();
         
         for(int i=0; i < required.length; i++) {
             String value = props.getProperty(required[i]);
             if( JavaUtil.isEmpty(value) ) {
                 missing.add(required[i]);
             }
         }
         
         if(!missing.isEmpty()) {
             throw new RuntimeException(msg+": "+missing);
         }
     }
     
     public static void main(String agrs[]) {
    	 
  //  	 ServerConnectionUtil connect = new ServerConnectionUtil
    	 ServerConnectionUtil de =  ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
    	 try {
			RemoteDealStation deals = (RemoteDealStation) de.getRMIService("DealStation");
			
			System.out.println(deals.toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

	public static ServerConnectionUtil getdefault() {
		return _default ;
	}

	public static void setdefault(ServerConnectionUtil Udefault) {
		_default = Udefault;
	}
	/**
     * add a Remote service to the currnet list of services
     * @param serverName
     * @param exportedReference
     */
    public  void setRMIService(String serverName, Remote exportedReference) {
        if (serverName == null) {
            throw new IllegalArgumentException();
        }
        
        String name = serverName + "_" + _dataServerName;
        
        if (exportedReference != null) {
        	rmiServices.put(name, exportedReference);
        } 
        else {
        	rmiServices.remove(name);
        }
    }

	public Map getRmiServices() {
		return rmiServices;
	}

	public void setRmiServices(Map Rmi) {
		rmiServices = Rmi;
	}


	public String get_dataServerName() {
		return _dataServerName;
	}




	public void set_dataServerName(String serverName) {
		_dataServerName = serverName;
	}




	public String get_hostName() {
		return _hostName;
	}




	public void set_hostName(String name) {
		_hostName = name;
	}




	public int get_rmiPort() {
		return _rmiPort;
	}




	public void set_rmiPort(int port) {
		_rmiPort = port;
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

	
}
