package dsServices;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;


import dsServices.handler.ServiceClientHandler;
import util.JavaUtil;
import util.ClassInstantiateUtil;
import util.ReflectionUtil;

public class ServiceInit {
	
	private static List serverProxies = Collections.synchronizedList(new ArrayList());
	
	
	
	/**
     * Exports the remote object to make it available to receive incoming calls 
     * 
     * @param object 
     *           Remote object to be exported
     * @param port
     *           Port to be exported on
     * @param clientSocketFactory
     *           RMIClientSocketFactory
     * @param serverSocketFactory
     *           RMIServerSocketFactor
     * @throws RemoteException
     */
    public static Remote exportObject (Remote object, int port, RMIClientSocketFactory clientSocketFactory, RMIServerSocketFactory serverSocketFactory)throws RemoteException {
        return UnicastRemoteObject.exportObject(object, port);//, clientSocketFactory, serverSocketFactory);
    }
    
    public static RemoteTrade getRemoteTrade() {
    	RemoteTrade remoteTrade = null;
    	try {
			return (RemoteTrade) ServerConnectionUtil.getdefault().getRMIService("Trade");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return remoteTrade;
    }
    
    public static void startService (ConfigService configService, Registry regist) throws Exception {
         ServerConnectionUtil servc = ServerConnectionUtil.getdefault();
    	String serviceName = configService.getServiceName();
        
        if (JavaUtil.isEmpty(serviceName) || JavaUtil.isEmpty(configService.getServiceClass()) || JavaUtil.isEmpty(configService.getNodeName())) {
            throw new RuntimeException("Either the service name, class, or node Name is null");
        }
        
        System.out.println("Service for "+serviceName);
        System.out.println ("RMI Registry port for   " + serviceName  +  "  " + configService.getRegistryPort());
        String nodeName = configService.getNodeName();
        String URI = serviceName +"_" + nodeName;
        if (!validateURI(URI)) {
            throw new RuntimeException("Invalid URI: " + URI + ". Valid characters [a-zA-Z0-9_-]");
        }
        
        Remote _serverInstance = null;
        
        //Load class 
        Class clazz = null;
        try {
            clazz = ClassInstantiateUtil.getClass(configService.getServiceClass(), true);
        } catch (Exception e){
            System.out.println( "Cannot locate class for " + configService.getServiceClass());
        }
        
        if (clazz == null) {
            clazz = ClassInstantiateUtil.getClass("dsService" + configService.getServiceClass(), true);
           
        }
        
        Constructor constructor12 = null;
        Class[] paratypes = null;
        try {
        	System.out.println( "Creating Remote instance with constructor with client port " + configService.getClientPort());
        	 Constructor[] constructors = clazz.getDeclaredConstructors();
        	 for (Constructor constructor : constructors) {
 		             		 paratypes  = constructor.getParameterTypes();
 		       		    }
        	

           constructor12 = clazz.getDeclaredConstructor(paratypes);
            _serverInstance = (Remote)constructor12.newInstance(paratypes );
        } catch (Exception e){
        	System.out.println( "Unable to create instance with constuctor with client port" + e);
        }
        
        
        if (_serverInstance == null) {
            try {
            	System.out.println("Could not create Remote instance with constructor that takes a port, Creating Remote instance with default constructor");
                constructor12 = clazz.getConstructor(ReflectionUtil.EMPTY_CLASS_ARRAY);
                _serverInstance = (Remote) constructor12.newInstance(ReflectionUtil.EMPTY_ARRAY);
            } catch (Exception e) {
            	System.out.println("Unable to create instance with default constructor nor with client port" + e);
            }
        }
     
        
        if(_serverInstance == null) {
            throw new RuntimeException("Unable to create new Remote instance for class " + clazz.getName());
        }
        if (_serverInstance instanceof UnicastRemoteObject) {
            throw new RuntimeException (serviceName + " service should not extend UnicastRemoteObject");
        }
        
      //Retrieve Interfaces that the above class implements
        Class[] interfaces = configService.getInterfaces(clazz);
     
        //wrap the remote service in a server proxy 
        List serverInvocationHandlers = configService.getServerInvocationHandlers();
        Remote serverProxy = getProxy(_serverInstance, serviceName, interfaces, serverInvocationHandlers);
        serverProxies.add(serverProxy);
        
        //Instantiate socket factory for both client & server side
        RMIClientSocketFactory clientSocketFactory = null;
        RMIServerSocketFactory serverSocketFactory = null;
        
        System.out.println("Exporting object to port " + configService.getPort());
        Remote exportedReference = ServiceInit.exportObject(serverProxy,
        		configService.getPort(),
                                                               clientSocketFactory,
                                                              serverSocketFactory);
        
      
        // bind services
        System.out.println("Binding Services for "+ serviceName);
        servc.setRMIService(serviceName, exportedReference);
        
        List clientInvocationHandlers = configService.getClientInvocationHandlers();
        regist.rebind(URI, getProxy(exportedReference,
                                   serviceName,
                                   interfaces,
                                   clientInvocationHandlers));
        System.out.println(URI+" bound");
    }
    
    
    public static void startService(ConfigService configService) throws Exception {
        int registryPort = configService.getRegistryPort();
        Registry regist = null;
        try {
        	System.out.println ("Looking >>> registry on port " + registryPort);
            regist = LocateRegistry.createRegistry(registryPort);
        } catch (Exception ex) {
            //If we are here, we probably have created registry already, so we will only warn 
            //and proceed to get the reference to the registry instead
        	System.out.println("Could not create registry on port " + registryPort + ",Proceeding to get reference to registry instead");
        }
        try {
            regist = LocateRegistry.getRegistry(registryPort);
           
        } catch (Exception e) {
        	System.out.println( "Error encountered when getting reference to registry on port " + registryPort + e);
        }
        if (regist == null) {
            throw new java.rmi.ConnectException("RMI could not create "+
                                "registry on port : "+
                                configService.getRegistryPort());
        }
        startService(configService, regist);
    }
    /**
     * Creates a wrapped Proxy instance of the given remote object using the list of given invocation handlers
     *  
     * @param object
     * @param serviceName
     * @param interfaces
     * @param invocationHandlers
     * @return
     */
    public static Remote getProxy (Remote object, String serviceName, Class[] interfaces, List invocationHandlers) {
        Remote remote = object;
        if (!JavaUtil.isEmpty(invocationHandlers)) {
            for (int i=0; i < invocationHandlers.size(); i++){
                String handlerClass = (String) invocationHandlers.get(i);
                InvocationHandler h = ServiceClientHandler.createInstance(handlerClass, remote, serviceName);
                remote = (Remote) Proxy.newProxyInstance(remote.getClass().getClassLoader(), interfaces, h);
            }
        }
        return remote;
    }
    /**
     * Validates URI
     * The only characters allowed are a-z, A-Z, 0-9, '_', '-'
     * @param str the URI to be validated
     * @return true if it is valid, false otherwise
     */
    public static boolean validateURI (String str){
        for (int count=0; count < str.length(); count++){
             char ch  = str.charAt(count);
             if (!Character.isLetterOrDigit(ch) && !(ch == 95)  &&  !(ch == 45)){ //'_' = 95  , '-' = 45
                 return false;
             }
         }
         return true;
     }
    
    /**
     * Locates the registry on the given rmiPort and unbinds
     * 
     * @param serviceName
     * @param nodeName
     * @param rmiPort
     * @return true if the service is stopped successfully, false otherwise
     */
    public static boolean stopService(String serviceName, String nodeName, int rmiPort) {
        String uri = serviceName + "_" + nodeName;
        try {
            Registry regist = LocateRegistry.getRegistry(rmiPort);
            if (regist == null) {
                throw new java.rmi.ConnectException("Error in locating  registry on port " +  rmiPort);
            }
            regist.unbind(uri);
            return true;
        } catch (RemoteException e) {
           System.out.println( e);
            return false;
        } catch (NotBoundException e) {
        	System.out.println( e);
            return false;
        }
        
    }
    
    /**
     * lookup a given service by its uri, service uri is generated using the following pattern
     * rmi://SERVER:PORT/SERVICE_DATASERVER
     * 
     * Only the characters [a-zA-Z0-9_-] are allowed in the SERVICE_PROVIDER string
     * 
     * @param serviceName
     * @return
     * @throws com.calypso.tk.util.ConnectException
     * @throws MalformedURLException 
     */
    public static Remote lookupService(String serviceName, int port, String hostName, String serverConfigName) throws ServiceException, MalformedURLException {
        String URI = serviceName + "_" + serverConfigName;
        
        if (!validateURI(URI)) {
            throw new ServiceException("Invalid URI: " + URI + ". Valid characters [a-zA-Z0-9_-]");
        }
        
        //"rmi://{0}:{1}/{2}_{3}";
        String url= MessageFormat.format(
                     SERVICE_PATTERN, 
                     new Object[] {hostName, Integer.toString(port), serviceName, serverConfigName}
        );
        try {
          // System.out.println("Getting Service on "+url );
         //  System.out.println("Getting Service "+ Arrays.asList(Naming.list("rmi://" + hostName +":" + port)) );
            return Naming.lookup(url);
        } catch (RemoteException e) {
        	System.out.println("Naming lookup failed for service "+url + " "+  e);
            throw new ServiceException("Connection to " +  serviceName + " failed (" + e.getMessage() + ")");
        } catch (MalformedURLException e) {
            // should not occur normally, hence log it as error.
        	System.out.println( "Malformed URL "+url + "  "+ e);
            throw e;
        } catch (NotBoundException e) {
        	System.out.println("Name not bound "+url + " " +  e);
            throw new ServiceException("Connection to " + serviceName + " failed, Name not bound (" + e + ")");
        }
    }
    

    private static final String SERVICE_PATTERN = "rmi://{0}:{1}/{2}_{3}";
}
