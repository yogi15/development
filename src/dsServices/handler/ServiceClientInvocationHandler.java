package dsServices.handler;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.Remote;

public class ServiceClientInvocationHandler implements InvocationHandler, Serializable {
    
	
	 private String serverName;
	 private Remote obj;
	    
	    public ServiceClientInvocationHandler(Remote obj, String serverName) {
	        this.obj = obj;
	        this.serverName = serverName;
	    }
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		 Object result = method.invoke(obj, args);
		return result;
	}

}
