package beans;

import constants.JavaFileGeneratorConstants;

public class JavaFileGenerator implements BaseBean {
	
	
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
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the dataType
	 */
	public String getParameterType() {
		return paramerterType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setParameterType(String paramerterType) {
		this.paramerterType = paramerterType;
	}
	String methodName;
	String paramerterType;
	String returnType;
	public String getReturnType() {
		// TODO Auto-generated method stub
		return returnType;
	}
	
	public int getInt() {
		return 0;
	}
	public void setReturnType(String returnTypes) {
		// TODO Auto-generated method stub
		returnType = returnTypes;
	}
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		Object obj =null;
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.WINDOWNAME))  {
		 obj = getWindowName();
	 }
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.PARAMETERTYPE))  {
		 obj = getParameterType();
	 }
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.METHODNAME))  {
		 obj = getMethodName();
	 }
	 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.RETURNTYPE))  {
		 obj = getReturnType();
	 }
	 if(propertyPaneColumnName.equalsIgnoreCase("Int"))  {
		 obj = Integer.valueOf(getInt());
	 }
	 return obj;
	}
	
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.WINDOWNAME))  {
			setWindowName((String)object);
		 }
		 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.METHODNAME))  {
			setMethodName((String)object);
		 }
		 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.RETURNTYPE))  {
			 setReturnType((String)object);
		 }
		 if(propertyPaneColumnName.equalsIgnoreCase(JavaFileGeneratorConstants.PARAMETERTYPE))  {
			setParameterType((String) object);
		 }
		  
	}
	
	
	
	
}
