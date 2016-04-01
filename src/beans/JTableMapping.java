package beans;

import java.io.Serializable;

public class JTableMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7134013406895355832L;
	String ObjectName;
	public String getObjectName() {
		return ObjectName;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	String dataType ;
	String displayName;
	
	
	
	
	
}
