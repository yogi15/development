package dsServices;

import java.util.List;

public class ConfigService {
	
	private String serviceName;
    private String serviceClassName;
    private int port; //port to export remote object on to make it available for incoming calls
    private String clientSocketFactory;
    private String serverSocketFactory;
    private int registryPort; //registry port
    private int clientPort; //port to create listening socket on for incoming clients
    private String nodeName;
    private List serverInvocationHandlers;
    private List clientInvocationHandlers;
    
    
    public ConfigService(){
    }
    
    public ConfigService(String serviceName,
            String serviceClassName,
            int port,
            String clientSocketFactory,
            String serverSocketFactory) {
        this(serviceName,
             serviceClassName,
             port,
             clientSocketFactory,
             serverSocketFactory,
             1099, //for registry port, we are going to default it to 1099
             0);
    }
    public ConfigService(String serviceName,
            String serviceClassName,
            int port,
            String clientSocketFactory,
            String serverSocketFactory,
            int registryPort,
            int clientPort) {
        this.serviceName = serviceName;
        this.serviceClassName = serviceClassName;
        this.port = port;
        this.clientSocketFactory = clientSocketFactory;
        this.serverSocketFactory = serverSocketFactory;
        this.registryPort = registryPort;
        this.clientPort = clientPort;
    }
    
    public void setNodeName(String nodeName){
        this.nodeName = nodeName;
    }
  
    public String getNodeName(){
        return this.nodeName;
    }
    
    public void setRegistryPort(int registryPort){
        this.registryPort = registryPort;
    }

    public int getRegistryPort() {
        return this.registryPort;
    }
    
    public void setClientPort (int clientPort){
        this.clientPort = clientPort;
    }
    
    public int getClientPort() {
        return this.clientPort;
    }    
    
    public void setServiceName(String name) {
        this.serviceName = name;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClassName = serviceClass;
    }

    public String getServiceClass() {
        return this.serviceClassName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setClientSocketFactory(String clientSocketFactory) {
        this.clientSocketFactory = clientSocketFactory; // we are doing this as
                                                        // a string for right
                                                        // now since for next
                                                        // phase, we will
                                                        // actually take the
                                                        // name of the class
    }

    public String getClientSocketFactory() {
        return this.clientSocketFactory;
    }

    public void setServerSocketFactory(String serverSocketFactory) {
        this.serverSocketFactory = serverSocketFactory;
    }

    public String getServerSocketFactory() {
        return this.serverSocketFactory;
    }
    
    public void setServerInvocationHandlers (List serverInvocationHandlers) {
        this.serverInvocationHandlers = serverInvocationHandlers;
    }
    
    public List getServerInvocationHandlers () {
        return this.serverInvocationHandlers;
    }
    
    public void setClientInvocationHandlers (List clientInvocationHandlers) {
        this.clientInvocationHandlers = clientInvocationHandlers;
    }
    
    public List getClientInvocationHandlers () {
        return this.clientInvocationHandlers;
    }
    
    
    
    /**
     * This method retrieves the list of interfaces of a given class
     * recursively. The base case is when there is no superClass. 
     *      
     * @param clazz The class whose interfaces are to be determined
     * @return the list of interfaces implemented by this class
     */
    public static Class[] getInterfaces(Class clazz) {
        return getInterfaces(clazz, null);
    }
    
    
    private static Class[] getInterfaces(Class clazz, Class[] interfaces){
        if (clazz.getSuperclass() == null) {
            return interfaces;
        } else {
            int interfacesLength = (interfaces != null) ? interfaces.length : 0;
            Class[] ownInterfaces = clazz.getInterfaces();
            //combining our own interfaces with the interfaces passed in the argument
            Class[] allInterfaces = new Class[interfacesLength + ownInterfaces.length];
            System.arraycopy(ownInterfaces, 0, allInterfaces, 0, ownInterfaces.length);
            if (interfaces != null) {
                System.arraycopy(interfaces, 0, allInterfaces, ownInterfaces.length, interfaces.length);
            }
            return getInterfaces(clazz.getSuperclass(), allInterfaces);
        }
    
    }

}
