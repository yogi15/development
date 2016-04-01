package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import dsEventProcessor.EventProducerServer;

public class ServerBean implements Serializable {
	
	
	   String _dataServerName; 
	   String _hostName;
	   int _rmiPort;
	   Map rmiServices;
	   String windowSetting;
	   String username;
	   String password;
	   /**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}
	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	int clientID;
	   
	public String get_dataServerName() {
		return _dataServerName;
	}
	public void set_dataServerName(String serverName) {
		_dataServerName = serverName;
	}
	public String get_hostName() {
		return _hostName;
	}
	public void set_hostName(String name) {
		_hostName = name;
	}
	public int get_rmiPort() {
		return _rmiPort;
	}
	public void set_rmiPort(int port) {
		_rmiPort = port;
	}
	public Map getRmiServices() {
		return  rmiServices;
	}
	public void setRmiServices(Map rmiServices) {
		this.rmiServices = rmiServices;
	}
	public String getWindowSetting() {
		return windowSetting;
	}
	public void setWindowSetting(String windowSetting) {
		this.windowSetting = windowSetting;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	  

}
