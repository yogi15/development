package dsServices.handler;



import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ServiceInvocationHandler implements InvocationHandler, Serializable {
    /**
     * 
     */
   
    private Remote obj;
    private String serverName;
    

    public ServiceInvocationHandler(Remote obj, String serverName) {
        this.obj = obj;
        this.serverName = serverName;
    }

    public Remote getRemoteObject (){
        return this.obj;
    }

    private static ThreadLocal connectionData = new ThreadLocal();
    
    private static class ConnectionData {
        String className;
        String startTime;
        String baseThreadName;
        
        public ConnectionData(String className) {
            this.className = className;
            DateFormat formatter = new SimpleDateFormat("dd HH:mm:ss.SSS");
            startTime = formatter.format(new Date());
            String fullThreadName = Thread.currentThread().getName();
            int splitIndex = fullThreadName.indexOf("-");
            if(splitIndex != -1) {
                baseThreadName = "RMI" + fullThreadName.substring(splitIndex);
            } else {
                baseThreadName = fullThreadName;
            }
        }

        public String getClassName() {
            return className;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getBaseThreadName() {
            return baseThreadName;
        }
    }
    /**
     *   Processes a method invocation on the given proxy instance
     * 
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            ConnectionData connection = (ConnectionData)connectionData.get();
            if(connection == null) {
                connection = new ConnectionData(method.getDeclaringClass().getName());
                connectionData.set(connection);
            }
            String methodName = method.getName();
            DateFormat formatter = new SimpleDateFormat("dd HH:mm:ss.SSS");
            String timeNow = formatter.format(new Date());
            String threadName = connection.getBaseThreadName() + ": " + connection.getClassName() + "." + method.getName() + " " + connection.getStartTime() + " " + timeNow; 
            Thread.currentThread().setName(threadName);
         //   Monitor.newRequest();
        /*    RequestInfo request = DSConnection.getRequestInfo();
            if(request != null){
                Log.setClientInfo(request.getHostAddress(), RequestInfo.getClientDescription(request) );
            }
            else
            {
                Log.setClientInfo("Unknown", "Unknown" );
            } 
            long _start_ = System.currentTimeMillis();
            long _mem_ = MonitorServer.getMemoryUsed(); */
            try {
                Object resultObj = method.invoke(obj, args);
              //  logRequest(_start_, _mem_, method, args);
                return resultObj;
            } finally {
             /*   if (MonitorServer.TRACE) {
                    MonitorServer.add(MonitorClient.getServiceId(this.serverName),
                                  method.getName(),
                                  _start_,
                                  _mem_);
                } */
            }
        } catch (InvocationTargetException e) {
           System.out.println( e.getTargetException());
            throw e.getTargetException();
        } catch (IllegalAccessException e) {
        	System.out.println( e);
            throw e;
        } catch (IllegalArgumentException e){
        	System.out.println( e);
            throw e;
        } catch (Throwable t) {
        	System.out.println( t);
            throw t;
        }
    }


	
 
    

}
