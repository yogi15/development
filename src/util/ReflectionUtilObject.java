package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class ReflectionUtilObject {
	
	
	String objectClassName;
	
	public static  Object getObject(String objectClassName,String tvalue ) {
		String values [] = tvalue.split(";");
		Object t = null;
		try{
			Class<?> c = Class.forName(objectClassName);
		
				t = c.newInstance();
			
			Method [] allmethods = c.getDeclaredMethods();
			for(Method m : allmethods) {
				String methodName =  m.getName();
				final Class<?>[] classType =  m.getParameterTypes();
				if(classType[0].equals(String.class)) {
					String value =  getValueofMethod(methodName,values);
				
						m.invoke(new String(value), 0);
					
				}
				else if (classType[0].equals(double.class)
		                || classType[0].equals(Double.class)) {
					String value =  getValueofMethod(methodName,values);
					m.invoke(Double.valueOf(value), 0);
				}
				else if (classType[0].equals(int.class)
		                || classType[0].equals(int.class)) {
					String value =  getValueofMethod(methodName,values);
					m.invoke(Integer.valueOf(value), 0);
				}
			}
			 
		
			} catch (IllegalArgumentException e ) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ReflectionUtilObject", "getObject", e);
				return null;
			
		}catch(ClassNotFoundException e) {
			commonUTIL.displayError("ReflectionUtilObject", "getObject", e);
			return null;
		}
		 catch (InstantiationException e) {
			// TODO Auto-generated catch block
			 commonUTIL.displayError("ReflectionUtilObject", "getObject", e);
				return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("ReflectionUtilObject", "getObject", e);
			return null;
		} catch (InvocationTargetException e ) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("ReflectionUtilObject", "getObject", e);
			return null;
		
	}
		return t;
	}
	
	private static String getValueofMethod(String methodName,String [] values) {
		String value = "";
		if(values == null)
			return "";
		for(String v : values) {
			String mName = v.substring(0, v.indexOf("="));
			if(methodName.equalsIgnoreCase(mName)) {
		     	value= v.substring(v.indexOf(mName)+1,v.length());
		     	break;
			}
			
			
		}
		return value;
		
	}
	public Class<?>[] getDataType() {
        return types.toArray(new Class[0]);
    }
	final Vector<Class> types = new Vector<Class>();
	

}
