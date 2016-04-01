package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassInstantiateUtil {
	static private final List _packages;
    static private final List _invertPackages;
    static private final Map _classes;
    static private final Map _interfaces;
    static private final ClassWrapper FAILED = new ClassWrapper(null);
	 static {
	        _packages = new ArrayList();
	        _invertPackages = new ArrayList();
	        _classes = new ConcurrentHashMap();
	        _interfaces = new ConcurrentHashMap();
	      
	    }
	 
	
	
	static public Class getClass(String name,boolean invert)
    throws InstantiationException{
    ClassWrapper cw = (ClassWrapper)_classes.get(name);
    
    // Check to see if we have tried and failed before...
    if (cw == FAILED) {
    	 System.out.println("ClassInstantiateUtil Class Instance  InstantiateUtil1 Class: " + name + " "
                + "tried and failed before..");
        throw new InstantiationException("Class " + name
            + " not found. See previous exception.");
    } 
    Class cl = null;
    if(cw != null) {
        cl = cw.getClassInstance();
    }
    if (cl == null) {
        List packages = (invert
                               ? _invertPackages : _packages);
        for (Iterator i = packages.iterator(); i.hasNext();) {
            String packageName = (String)i.next();
            String cname = packageName + "." + name;
            cl = getClass(cname);
            if (cl != null)
                break;
        }
        // We should also check full name given as parameter.
        // this permits the centralized caching of all classes
        // without needing to implement different caches in
        // instances where we already have a fully qualified class
        // name.
        if (cl == null) {
            cl = getClass(name);
        }
        if (cl == null) {
            _classes.put(name, FAILED);
            throw new InstantiationException("Class "
                                             + name + " not found");
        } else
            _classes.put(name, new ClassWrapper(cl));
    }
    return cl;
}
	
	static private Class getClass(String fullyQualifiedClassName) {
        Class cl = null;
         
        try {
            cl = Class.forName(fullyQualifiedClassName);
            
            
        }
	catch (ClassNotFoundException notFound) {
		System.out.println("ClassInstantiateUtil Class Instance " + fullyQualifiedClassName +   " not found ");
	} catch (Throwable e) {
		System.out.println("ClassInstantiateUtil Class Instance "+  e);
        }
        return cl;
    }
	
	
	
	private static class ClassWrapper {
        private Class classInstance;
        public ClassWrapper(Class classInstance) {
            this.classInstance = classInstance;
        }
        public Class getClassInstance() {
            return classInstance;
        }
    }
}
