package dsServices.handler;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;


public class ServiceClientHandler {

	
	public static InvocationHandler createInstance(String className, Remote object, String serviceName) {
        try {   
            Class[] argKlass = new Class[] {Remote.class, String.class};
            Object[] initargs = new Object[] {object, serviceName};
            Class slass = Class.forName(className);
            Constructor ctor = slass.getConstructor(argKlass);
            return (InvocationHandler) ctor.newInstance(initargs);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Cannot find implementation " + className, ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException("Cannot instantiate " + className, ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("Cannot access " + className, ex);        
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Cannot find constructor", ex);        
        } catch (InvocationTargetException ex) {
            throw new RuntimeException("Exception instantiating " + className, ex);
        }
    }  
}
