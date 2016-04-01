package beans;

import java.io.Serializable;

public class AccessFunction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3964639051779326025L;
	private String groupname;
	private String windowName;
	private String functionName;
	int isAccessable;
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getWindowName() {
		return windowName;
	}
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public int getIsAccessable() {
		return isAccessable;
	}
	public void setIsAccessable(int isAccessable) {
		this.isAccessable = isAccessable;
	}
	
	
}
