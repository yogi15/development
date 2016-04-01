package util.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Vector;

import util.commonUTIL;


public class DynUtil {

	public static String name = "DynUtil";
	
	/**
     * Used in the reflection calls when no argument is needed.
     */
    public static final Object[] EMPTY_ARRAY = new Object[0];
    /**
     * Used in the reflection calls when no argument is needed.
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];

    static DynUtil  __theOne = null;
    static protected DynUtil get() {
        if (__theOne != null) return __theOne;
        __theOne = new DynUtil();
        return __theOne;
    }
    static protected void makeAccessible(Field field) {
        get().setAccessible(field);
    }

    static public Object get(Object obj, Field field) {
        return get().getFieldValue(obj, field);
    }
    static protected Hashtable _hash = new Hashtable();
    static protected Hashtable _hashAll = new Hashtable();

    static public Field getField(Object obj, String fieldName) {
        Field fields[] = null;
        if (fieldName.length() > 2 && (fieldName.charAt(1) == '_'))
            fields = getAllFields(obj);
        else fields = getFields(obj);
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) return fields[i];
        }
        return null;
    }

    static public Field[] getFields(Object obj) {
        synchronized (_hash) {
            Field[] v = (Field[]) _hash.get(obj.getClass().getName());
            if (v != null) return v;
        }
        Vector v = new Vector();
        Class cl = obj.getClass();
        Field field;
        while (cl != Object.class) {
            Field fields[] = cl.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                field = fields[i];
                if (!(Modifier.isStatic(field.getModifiers())) &&
                        !(Modifier.isTransient(field.getModifiers()))) {
                    String s = field.getName();
                    if (s.length() > 1) {
                        if ((s.charAt(1) != '_') || (s.charAt(0) != '_'))
                            v.addElement(field);
                    } else v.addElement(field);
                }
            }
            cl = cl.getSuperclass();
        }
        v = new DynUtil().sort(v);
        Field fields[] = new Field[v.size()];
        for (int i = 0; i < v.size(); i++) {
            fields[i] = (Field) v.elementAt(i);
            makeAccessible(fields[i]);
        }
        synchronized (_hash) {
            _hash.put(obj.getClass().getName(), fields);
        }
        return fields;
    }

    static public Field[] getAllFields(Object obj) {
        synchronized (_hashAll) {
            Field[] v = (Field[]) _hashAll.get(obj.getClass().getName());
            if (v != null) return v;
        }
        Vector v = new Vector();
        Class cl = obj.getClass();
        Field field;
        while (cl != Object.class) {
            Field fields[] = cl.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                field = fields[i];
                if (!(Modifier.isStatic(field.getModifiers())) &&
                        !(Modifier.isTransient(field.getModifiers()))
                    //&&(field.getName().charAt(1) != '_')
                        ) {
                    v.addElement(field);
                }
            }
            cl = cl.getSuperclass();
        }
        v = new DynUtil().sort(v);
        Field fields[] = new Field[v.size()];
        for (int i = 0; i < v.size(); i++) {
            fields[i] = (Field) v.elementAt(i);
            makeAccessible(fields[i]);
        }
        synchronized (_hashAll) {
            _hashAll.put(obj.getClass().getName(), fields);
        }
        return fields;
    }

    protected Vector sort(Vector v) {
        Comparator c = new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Field) o1).getName().
                        compareTo(((Field) o2).getName());
            }
        };
        return util.SortShell.sort(v, c);
    }
    protected void setAccessible(Field field) {
        field.setAccessible(true);
    }
	 protected Object getFieldValue(Object obj, Field field) {
	        try {
	            return field.get(obj);
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(name, "getFieldValue", e);
	            return null;
	        }
	    }

	    static public String convertFieldName(Field field) {
	        String fieldName = field.getName();
	        char c = fieldName.charAt(0);
	        String res;
	        if (c == '_') {
	            c = fieldName.charAt(1);
	            if (c == '_') {
	                c = fieldName.charAt(2);
	                c = Character.toUpperCase(c);
	                res = c + fieldName.substring(3);
	            } else {
	                c = Character.toUpperCase(c);
	                res = c + fieldName.substring(2);
	            }
	        } else {
	            c = Character.toUpperCase(c);
	            res = c + fieldName.substring(1);
	        }
	        return res;
	    }
	    class SortFieldByName implements Comparator {
	        public int compare(Object o1, Object o2) {
	            return ((Field) o1).getName().compareTo(((Field) o2).getName());
	        }
	    }
}
