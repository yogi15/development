package beans;

import java.io.Serializable;

import constants.JavaFileGeneratorConstants;
import constants.JavaScriptConstants;

public class JavaScript implements Serializable,BaseBean {
	
	String windowName;
	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the scriptName
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * @param scriptName the scriptName to set
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	String scriptName;
	

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		Object obj =null;
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaScriptConstants.WINDOWNAME))  {
		 obj = getWindowName();
	 }
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaScriptConstants.SCRIPTNAME))  {
		 obj = getScriptName();
	 }
	 
	 return obj;
	}
	
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		if(propertyPaneColumnName.equalsIgnoreCase(JavaScriptConstants.WINDOWNAME))  {
			setWindowName((String)object);
		 }
		 if(propertyPaneColumnName.equalsIgnoreCase(JavaScriptConstants.SCRIPTNAME))  {
			 setScriptName((String)object);
		 } 
		  
	}

}
