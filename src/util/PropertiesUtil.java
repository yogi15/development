package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil instance = null;
	public static String libPath = null;
	
	public static String Path = null;
	public static String getPath() {
		
		return Path;
	}

	public static void setPath(String path) {
		Path = path;
	}
	public static PropertiesUtil getInstance() {
		System.out.println("Start -Server *******************  getInstance");
		if(instance == null) {
			instance = new PropertiesUtil();
		}
		return instance;
	}
	public String getPropertyList() {
		FileInputStream fis = null;
		Properties properties = new Properties();
		Path = (getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6));
		System.out.println("Server Running from *******************  " + Path);
		if(Path.contains(".jar")) {
			//System.out.println("Before setting library path "+ System.getProperty("java.library.path"));
			Path = (getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6)).replace("Server.jar","");
			Path = Path + "/resources/";
			System.out.println(Path);
			setPath(Path);
		} 
		return getPath();
		//System.out.println(System.getProperty("java.library.path"));
	//	System.setProperty("java.library.path",Path + "lib");
		//System.out.println(System.getProperty("java.library.path"));
		
		
		
		
	}
	 static InputStream getStream(String name)  {
	        try {
	            ClassLoader cl = PropertiesUtil.class.getClassLoader();
	            java.net.URL url = cl.getResource(name);
	            if (url != null)
	                return url.openStream();
	        } catch (Exception x) {
	        	
	        }
	        return null;
	    }
	 
	 
	 public static void main(String args[]) {
		 PropertiesUtil.getInstance().getPropertyList();
		// PropertiesUtil.getStream("RMIServices.properties");
	 }
}
