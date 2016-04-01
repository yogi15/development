package beans;

import constants.WindowTableModelMappingConstants;

public class WindowTableModelMapping implements BaseBean {

	String windowName;
	String columnDataType;
	String customColumnName;
	boolean IsCombobox;
	/**
	 * @return the isCombobox
	 */
	public boolean IsCombobox() {
		return IsCombobox;
	}

	/**
	 * @param isCombobox the isCombobox to set
	 */
	public void setIsCombobox(boolean isCombobox) {
		this.IsCombobox = isCombobox;
	}

	/**
	 * @return the customColumnName
	 */
	public String getCustomColumnName() {
		return customColumnName;
	}

	/**
	 * @param customColumnName the customColumnName to set
	 */
	public void setCustomColumnName(String customColumnName) {
		this.customColumnName = customColumnName;
	}

	/**
	 * @return the customMethodName
	 */
	public String getCustomMethodName() {
		return customMethodName;
	}

	/**
	 * @param customMethodName the customMethodName to set
	 */
	public void setCustomMethodName(String customMethodName) {
		this.customMethodName = customMethodName;
	}

	String customMethodName;
	/**
	 * @return the columnDataType
	 */
	public String getColumnDataType() {
		return columnDataType;
	}

	/**
	 * @param columnDataType the columnDataType to set
	 */
	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	/**
	 * @return the columnDisplayName
	 */
	public String getColumnDisplayName() {
		return columnDisplayName;
	}

	/**
	 * @param columnDisplayName the columnDisplayName to set
	 */
	public void setColumnDisplayName(String columnDisplayName) {
		this.columnDisplayName = columnDisplayName;
	}

	String columnDisplayName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	String beanName;
	String columnName;
	String methodName;

	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj = null;
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.METHODNAME)) {
			obj = getMethodName();
		} 
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNNAME)) {
			obj = getColumnName();
		} 
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.WINDOWNAME)) {
			obj = getWindowName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.BEANNAME)) {
			obj = getBeanName();
			
			
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDISPLAYNAME)) {
			obj = getColumnDisplayName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDATATYPE)) {
			obj =getColumnDataType();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.CUSTOMCOLUMNNAME)) {
			obj = getCustomColumnName( );
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.CUSTOMMETHODNAME)) {
			obj = getCustomMethodName( );
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.ISCOMBOX)) {
			obj = IsCombobox();
		}
		 
		return obj;
	}

	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.METHODNAME)) {
			setMethodName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNNAME)) {
			setColumnName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.WINDOWNAME)) {
			setWindowName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.BEANNAME)) {
			setBeanName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDISPLAYNAME)) {
			setColumnDisplayName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDATATYPE)) {
			setColumnDataType((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.CUSTOMCOLUMNNAME)) {
			setCustomColumnName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.CUSTOMMETHODNAME)) {
			setCustomMethodName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowTableModelMappingConstants.ISCOMBOX)) {
			setIsCombobox( (Boolean) object);
		}
	}
int id =0 ;
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	public int getId( ) {
		// TODO Auto-generated method stub
		return id;
	}
}
