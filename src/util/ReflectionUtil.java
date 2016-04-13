package util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.Vector;


public class ReflectionUtil {
			
	/**
     * Used in the reflection calls when no argument is needed.
     */
    public static final Object[] EMPTY_ARRAY = new Object[0];
    /**
     * Used in the reflection calls when no argument is needed.
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    
    static ReflectionUtil __onlyOne = null;

    static protected ReflectionUtil get() {
	if(__onlyOne != null) return __onlyOne;
	__onlyOne = new ReflectionUtil();
	return __onlyOne;
    }
    	
    
    static protected Hashtable _hash=new Hashtable();
    static protected Hashtable _hashAll=new Hashtable();

    static public Field getField(Object obj, String fieldName) {
	Field fields[]=null;
	if(fieldName.length() > 2 && (fieldName.charAt(1)=='_'))
	    fields = getAllFields(obj);
	else fields=getFields(obj);
	for(int i=0;i<fields.length;i++) {
	    if(fields[i].getName().equals(fieldName)) return fields[i];
	}
	return null;
    }

    static public  Field[] getFields(Object obj) {
	synchronized(_hash) {
	    Field[] v=(Field[])_hash.get(obj.getClass().getName());
	    if(v != null) return v;
	}
	Vector v= new Vector();
	Class cl=obj.getClass();
	Field field;
	while(cl != Object.class) {
	    Field fields[] = cl.getDeclaredFields();
	    for(int i=0;i<fields.length;i++) {
		field= fields[i];
		if(!(Modifier.isStatic(field.getModifiers())) &&
		   !(Modifier.isTransient(field.getModifiers()))) {
		    String s=field.getName();
		    if(s.length() > 1) {
			if((s.charAt(1) != '_') || (s.charAt(0) != '_'))
			    v.addElement(field);
		    }
		    else v.addElement(field);
		}
	    }
	    cl = cl.getSuperclass();
	}
	v = new ReflectionUtil().sort(v);
	Field fields[] = new Field[v.size()];
	for(int i=0;i<v.size();i++) {
	    fields[i]=(Field)v.elementAt(i);
	    makeAccessible(fields[i]);
	}
	synchronized(_hash) {
	    _hash.put(obj.getClass().getName(), fields);
	}
	return fields;
    }

    static public  Field[] getAllFields(Object obj) {
	synchronized(_hashAll) {
	    Field[] v=(Field[])_hashAll.get(obj.getClass().getName());
	    if(v != null) return v;
	}
	Vector v= new Vector();
	Class cl=obj.getClass();
	Field field;
	while(cl != Object.class) {
	    Field fields[] = cl.getDeclaredFields();
	    for(int i=0;i<fields.length;i++) {
		field= fields[i];
		if(!(Modifier.isStatic(field.getModifiers())) &&
		   !(Modifier.isTransient(field.getModifiers()))
		   //&&(field.getName().charAt(1) != '_')
		   ) {
		    v.addElement(field);
		}
	    }
	    cl = cl.getSuperclass();
	}
	v = new ReflectionUtil().sort(v);
	Field fields[] = new Field[v.size()];
	for(int i=0;i<v.size();i++) {
	    fields[i]=(Field)v.elementAt(i);
	    makeAccessible(fields[i]);
	}
	synchronized(_hashAll) {
	    _hashAll.put(obj.getClass().getName(), fields);
	}
	return fields;
    }
    protected Vector sort(Vector v) {
	Comparator c= new Comparator() {
		public int compare(Object o1,Object o2) {
		    return ((Field)o1).getName().
			compareTo(((Field)o2).getName());
		}
	    };
	return sort(v,c);
    }
     
    static public Vector sort(Collection collection)
    {
    	Vector v = new Vector(collection);
    	Collections.sort(v);
    	return v;
    }
    
    static public Vector sort(Collection collection, Comparator comp)
    {
    	Vector v = new Vector(collection);
    	Collections.sort(v, comp);
    	return v;
    }
    
    static public Method getMethodGet(Object obj, String name) {
    	//should Cache
    	Method mm = null;
    	Method ms[] = obj.getClass().getMethods();// caching required 
    	for(int i=0;i<ms.length;i++) {
    	    Class ar[] = ms[i].getParameterTypes();
    	    if(ar == null || ar.length == 0)
    		if(ms[i].getName().equals(name)) 
    			mm = ms[i];
    	}
    	return mm;
        }
    static public Vector<String> getOnlyNameofMethodStartWithGet(Object obj ) {
    	Vector<String> getMethodName = new Vector<String>();
    	//should Cache
    	
    	Method mm = null;
    	Method ms[] = obj.getClass().getMethods();// caching required 
    	for(int i=0;i<ms.length;i++) {
    	    Class ar[] = ms[i].getParameterTypes();
    	    if(ar == null || ar.length == 0)
    		if(ms[i].getName().startsWith("get")||ms[i].getName().startsWith("is") ) 
    			getMethodName.add(ms[i].getName().substring(3,ms[i].getName().length() ));
    	}
    	return getMethodName;
        }
    static public Method [] getAllMethodOfObject(Object obj ) {
    	//should Cache
    	 
    	return obj.getClass().getMethods();// caching required 
    	 
        }
        static public Method getMethodSet(Object obj, String name,Field field) {
    	//should Cache
    	Method ms[] = obj.getClass().getMethods();
    	
    	for(int i=0;i<ms.length;i++) {
    	    Class ar[] = ms[i].getParameterTypes();
    	    if(ar != null && ar.length == 1 &&
    	       ar[0].equals(field.getType()))
    		if(ms[i].getName().equals(name))
    		return ms[i];
    	}
    	return null;
        }
        class SortFieldByName implements Comparator {
    	public int compare(Object o1,Object o2) {
    	    return ((Field)o1).getName().compareTo(((Field)o2).getName());
    	}
        }

        static protected void makeAccessible(Field field) {
    	get().setAccessible(field);
        }
        static public Object get(Object obj,Field field) {
    	return get().getFieldValue(obj,field);
        }
        static public void set(Object obj,Field field,Object value) {
    	get().setFieldValue(obj,field,value);
        }
        protected void setAccessible(Field field) {
    	field.setAccessible(true);
        }
        protected void setFieldValue(Object obj,Field field,Object value) {
    	try {
    	    field.set(obj, value);
    	}
    	catch (Exception e) {
    	   System.out.println("ReflectionUtil  + Object " + obj.getClass().getName() +
    		      " Field " + field.getName() +
    		      " Type " + field.getType().getName() +  e);
    	}
        }
        protected Object getFieldValue(Object obj,Field field) {
    	try {
    	    return field.get(obj);
    	}
    	catch (Exception e) {  System.out.println(this + " " + e); return null;}
        }
        static public String convertFieldName(Field field) {
    	String fieldName=field.getName();
    	char c=fieldName.charAt(0);
    	String res;
    	if(c=='_') {
    	    c=fieldName.charAt(1);
    	    if(c=='_') {
    		c=fieldName.charAt(2);
    		c = Character.toUpperCase(c);
    		res=  c + fieldName.substring(3);
    	    }
    	    else {
    		c = Character.toUpperCase(c);
    		res=  c + fieldName.substring(2);
    	    }
    	}
    	else {
    	    c = Character.toUpperCase(c);
    	    res=  c + fieldName.substring(1);
    	}
    	return res;
        }

}
