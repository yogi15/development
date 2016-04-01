package util.PropertiesReader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import util.commonUTIL;




import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;



public class ConfigPropertiesReader {
	public static Hashtable<String, String> serverConfigTable;
	
	static{
		Properties prop = new Properties();
		InputStream input = null;
		input = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/serverConfig.properties");
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/serverConfig.properties"));
		} catch (IOException e) {
			commonUTIL.showAlertMessage("Error during loading Configuration file. Properies file not found in classpath");
			System.exit(1);
		}
		
		serverConfigTable = new Hashtable<String, String>();
		Enumeration<?> propertyEnum = prop.propertyNames();
				
		for (final String name: prop.stringPropertyNames()){
			serverConfigTable.put(name, prop.getProperty(name));
		}
	} 
	
	public String getPropertyValue(String propertyName) {
		return serverConfigTable.get(propertyName);
	}
}
