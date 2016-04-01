package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Country  implements Serializable {
	
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsocode() {
		return isocode;
	}
	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	 final public static int    ADDRESS_MAX_LINE_NUMBER = 4;
	String isocode;
	String name;
	String timeZone;
	 final public static String SPACE = "Space";
	    final public static String COMMA = ",";
	    final public static String DOT = ".";
	   public static HashMap __addressSeparatorsHashMap = null;
	static boolean isAddressSeparator(String formatElement) {
        // We might be in multithread context
        // we hence use a local array
        HashMap addressSeparatorsHashMap = __addressSeparatorsHashMap;
        if (addressSeparatorsHashMap == null)
            addressSeparatorsHashMap = getAddressSeparators();
        __addressSeparatorsHashMap = addressSeparatorsHashMap;
        return (addressSeparatorsHashMap.get(formatElement) != null);
    }

    static HashMap getAddressSeparators() {
        HashMap addressSeparatorsHashMap = new HashMap();
        addressSeparatorsHashMap.put(bo.swift.SwiftMessage.ENDOFLINE, bo.swift.SwiftMessage.ENDOFLINE);
        addressSeparatorsHashMap.put(" ", " ");
        addressSeparatorsHashMap.put(DOT, DOT);
        addressSeparatorsHashMap.put(COMMA, COMMA);
        Vector separators = null;
        if (separators != null) {
            for (int i = 0; i < separators.size(); i++) {
                String separator = (String)separators.elementAt(i);
                addressSeparatorsHashMap.put(separator, separator);
            }
        }
        return addressSeparatorsHashMap;
    }
}
