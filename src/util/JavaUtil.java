package util;

import java.net.InetAddress;
import java.util.Collection;

public class JavaUtil {
	
	
	
	 /**
     * Returns true if a given String is empty, or false otherwise.
     * <p>
     * Empty means null, "" or "           ".
     * @param s a String
     */
    static public boolean isEmpty(String s) {
        return ((s == null) || (s.trim().length() == 0));
    }
    
    /**
     * @param collection
     * @return true if collection is not null and not empty
     * @see java.util.Collection#isEmpty()
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }
    /**
     * Returns the LocalHostName of the host we're running on.<br>
     * If the Name is not available, the IP address will be returned.<br>
     * If the IP address is not available, "localhost" will be returned.
     */
    public static String getLocalHostName() {
        InetAddress addr = null;
        String strAddr;
        try {
            addr = InetAddress.getLocalHost();
        }
        catch (Exception e) {
            // Do not log anything here - the logger calls this and if there
            // is a log call in here then we will likely be recursive.
        }
        if(addr != null) {
            strAddr = addr.getHostName();
            if(strAddr == null) strAddr = addr.getHostAddress();
            return strAddr;
        }
        else return "localhost";
    }
}
