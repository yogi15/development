package beans;

import java.io.Serializable;

public class Users implements Serializable {
	
	String user_groups;
	String user_name;
	String password;
	String hostName;
	String attributes;
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the hostName
	 */
	public String getAttributes() {
		return attributes;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	/**
	 * @return the applicattionNameLoginOn
	 */
	public String getApplicattionNameLoginOn() {
		return applicattionNameLoginOn;
	}
	/**
	 * @param applicattionNameLoginOn the applicattionNameLoginOn to set
	 */
	public void setApplicattionNameLoginOn(String applicattionNameLoginOn) {
		this.applicattionNameLoginOn = applicattionNameLoginOn;
	}
	/**
	 * @return the instanceNameofAapplication
	 */
	public String getInstanceNameofAapplication() {
		return instanceNameofAapplication;
	}
	/**
	 * @param instanceNameofAapplication the instanceNameofAapplication to set
	 */
	public void setInstanceNameofAapplication(String instanceNameofAapplication) {
		this.instanceNameofAapplication = instanceNameofAapplication;
	}
	String applicattionNameLoginOn;
	String instanceNameofAapplication;
	int id;
	public String getUser_groups() {
		return user_groups;
	}
	public void setUser_groups(String user_groups) {
		this.user_groups = user_groups;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	


	
	

}
