package mainServer;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import dsServices.ConfigService;
import dsServices.RemoteDeal;
import dsServices.RemoteDealStation;
import dsServices.ServiceInit;

import util.JavaUtil;


public class ServerConnectionUtil {
	 private static final String SERVICE_PATTERN = "rmi://{0}:{1}/{2}_{3}";
	 public final static String SERVER_NAME="DealServer";
	    public final static String SERVER_NICKNAME = "Deal";
	 
	 public static Map rmiServices = Collections.synchronizedMap(new HashMap());
	    private static ThreadLocal requestInfo = new ThreadLocal();
	    protected String _hostName;
	    protected boolean _local = false;
	    protected int _rmiport;
	    protected String userName;
	    protected String password;
	    protected String _dataServerName = "";
	    static public ServerConnectionUtil  _default;
	  //  DealServer server = null;
	    
	    
	    
	    
	    
	    /**
	     * add a Remote service to the currnet list of services
	     * @param serverName
	     * @param service
	     */
	    public void setRMIService(String serverName, Remote service) {
	        if (serverName == null) {
	            throw new IllegalArgumentException();
	        }
	        
	        String name = serverName + "_" + _dataServerName;
	        
	        if (service != null) {
	            rmiServices.put(name, service);
	        } 
	        else {
	            rmiServices.remove(name);
	        }
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
	            System.out.println("No server invocation handlers set for " + serviceName);
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
	        	 System.out.println( "No client invocation handlers set for " + serviceName);
	         }
	         
	         //We set the following from the calypso env property file
	         int rmiPort = 1099;
	         serviceConfig.setRegistryPort(rmiPort);
	         
	         String dsName = "localhost";//Defaults.getDataServerName();
	         serviceConfig.setNodeName(dsName);
	         
	         //we default the following to true for now
	         serviceConfig.setClientSocketFactory("true");
	         serviceConfig.setServerSocketFactory("true");
	         
	         return serviceConfig;
	     }
	     
	    
	    
	    
	    static public ServerConnectionUtil getDefault() {
	        return _default;
	    }
	    
	    
	    
	public static boolean validateURI (String str){
        for (int count=0; count < str.length(); count++){
             char ch  = str.charAt(count);
             if (!Character.isLetterOrDigit(ch) && !(ch == 95)  &&  !(ch == 45)){ //'_' = 95  , '-' = 45
                 return false;
             }
         }
         return true;
     }
	
	
	public Remote getRMIService(String serviceName, int port, String hostName) throws RemoteException {
        String name = serviceName + "_" + _dataServerName;
        Remote ref = (Remote)rmiServices.get(name);
        
        if(ref == null) {
            try {
                ref =  ServiceInit.lookupService(serviceName, port, hostName, _dataServerName);
                rmiServices.put(name, ref);
                
            
            } catch (Exception e) {
            	System.out.println("ServerConnection "+ e);
                throw new RemoteException(e.getMessage(), e);
            }
        }
        
        return ref;
    }
	
	 public Remote getRMIService(String serviceName) throws RemoteException {
	        Remote ref = (Remote)rmiServices.get(serviceName + "_" + _dataServerName);
	        if (ref == null){ 
	            ref= getRMIService(serviceName, _rmiport, _hostName);
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
	    public Remote getRMIService(String serviceName, int port) throws RemoteException {
	        Remote ref = (Remote)rmiServices.get(serviceName + "_" + _dataServerName);
	        if (ref == null) {
	            ref =getRMIService(serviceName, port, _hostName);
	        }
	        return ref;
	    }
	    
	    public RemoteDealStation getRemoteDealStation() throws RemoteException {
	    	return (RemoteDealStation)getRMIService("DealStation");
	    }
	    
	    public RemoteDeal getRemoteDealServer() throws RemoteException {
	    	
	    	return (RemoteDeal) getRMIService("DealServer");
	    }
	    
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
	    
}
