package beans;

import util.commonUTIL;
import constants.WindowSheetConstants;

public class WindowSheet implements BaseBean {

	String designType;
	boolean isChildField;
	String ChildWindowName;
	boolean mapJavaObject;
	boolean isEditable;

	/**
	 * @return the isEditable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * @param isEditable
	 *            the isEditable to set
	 */
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * @return the isHidden
	 */
	public boolean isHidden() {
		return isHidden;
	}

	/**
	 * @param isHidden
	 *            the isHidden to set
	 */
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	boolean isHidden;

	/**
	 * @return the mapJavaObject
	 */
	public boolean isMapJavaObject() {
		return mapJavaObject;
	}

	/**
	 * @param mapJavaObject
	 *            the mapJavaObject to set
	 */
	public void setMapJavaObject(boolean mapJavaObject) {
		this.mapJavaObject = mapJavaObject;
	}

	/**
	 * @return the javaObjectName
	 */
	public String getJavaObjectName() {
		return JavaObjectName;
	}

	/**
	 * @param javaObjectName
	 *            the javaObjectName to set
	 */
	public void setJavaObjectName(String javaObjectName) {
		JavaObjectName = javaObjectName;
	}

	String JavaObjectName;

	/**
	 * @return the parentWindowName
	 */
	public String getChildWindowName() {
		return ChildWindowName;
	}

	/**
	 * @param parentWindowName
	 *            the parentWindowName to set
	 */
	public void setChildWindowName(String childWindowName) {
		ChildWindowName = childWindowName;
	}

	/**
	 * @return the isHierarachicalWindow
	 */
	public boolean IsHierarachicalWindow() {
		return IsHierarachicalWindow;
	}

	/**
	 * @param isHierarachicalWindow
	 *            the isHierarachicalWindow to set
	 */
	public void setIsHierarachicalWindow(boolean isHierarachicalWindow) {
		IsHierarachicalWindow = isHierarachicalWindow;
	}

	boolean IsHierarachicalWindow;

	/**
	 * @return the isChild
	 */
	public boolean isChildField() {
		return isChildField;
	}

	/**
	 * @param isChild
	 *            the isChild to set
	 */
	public void setChildField(boolean isChildField) {
		this.isChildField = isChildField;
	}

	/**
	 * @return the parentName
	 */
	public String getParentFieldName() {
		return parentFieldName;
	}

	/**
	 * @param parentName
	 *            the parentName to set
	 */
	public void setParentFieldName(String parentFieldName) {
		this.parentFieldName = parentFieldName;
	}

	String parentFieldName;

	/**
	 * @return the designType
	 */
	public String getDesignType() {
		return designType;
	}

	/**
	 * @param designType
	 *            the designType to set
	 */
	public void setDesignType(String designType) {
		this.designType = designType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -495377846383122604L;
	String windowName;

	/**
	 * @return the columnSerialNo
	 */
	public int getColumnSerialNo() {
		return columnSerialNo;
	}

	/**
	 * @param columnSerialNo
	 *            the columnSerialNo to set
	 */
	public void setColumnSerialNo(int columnSerialNo) {
		this.columnSerialNo = columnSerialNo;
	}

	int columnSerialNo = 0;

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
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the isStartupdata
	 */
	public int getIsStartupdata() {
		return isStartupdata;
	}

	/**
	 * @param isStartupdata
	 *            the isStartupdata to set
	 */
	public void setIsStartupdata(int isStartupdata) {
		this.isStartupdata = isStartupdata;
	}

	/**
	 * @return the startUpDataName
	 */
	public String getStartUpDataName() {
		return startUpDataName;
	}

	/**
	 * @param startUpDataName
	 *            the startUpDataName to set
	 */
	public void setStartUpDataName(String startUpDataName) {
		this.startUpDataName = startUpDataName;
	}

	/**
	 * @return the customPanelName
	 */
	public String getCustomPanelName() {
		return customPanelName;
	}

	/**
	 * @param customPanelName
	 *            the customPanelName to set
	 */
	public void setCustomPanelName(String customPanelName) {
		this.customPanelName = customPanelName;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the nullChecked
	 */
	public boolean isNullChecked() {
		return nullChecked;
	}

	/**
	 * @param nullChecked
	 *            the nullChecked to set
	 */
	public void setNullChecked(boolean nullChecked) {
		this.nullChecked = nullChecked;
	}

	String fieldName;
	String dataType;
	int isStartupdata = 0;
	String startUpDataName;
	String customPanelName;
	String defaultValue;
	boolean nullChecked = false;

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
	String methodName;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	String category;

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj = null;
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.BEANNAME)) {
			return obj = getBeanName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CATEGORYNAME)) {
			return obj = getCategory();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CUSTOMPANELNAME)) {
			return obj = getCustomPanelName();
		}

		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DATATYPE)) {
			return obj = getDataType();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DEFAULTVALUE)) {
			return obj = getDefaultValue();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DESIGNTYPE)) {
			return obj = getDesignType();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.FIELDNAME)) {
			return obj = getFieldName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISSTARTUPDATA)) {
			if (getIsStartupdata() == 1) {
				return true;
			} else {
				return false;
			}
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.STARTUPDATANAME)) {
			return obj = getStartUpDataName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.PARENTFIELDNAME)) {
			return obj = getParentFieldName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISCHILDFIELD)) {
			return obj = isChildField();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.WINDOWNAME)
				|| propertyPaneColumnName
						.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
			if (propertyPaneColumnName
					.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
				String ss = (String) getWindowName();
				if (!commonUTIL.isEmpty(ss) && ss.contains("Attributes")) {
					ss = ss.substring(0, ss.indexOf("Attributes"));
					return ss;
				}

			} else {
				return obj = getWindowName();
			}
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.NULLCHECKED)) {
			return obj = isNullChecked();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISHIERARACHICALWINDOW)) {
			return obj = IsHierarachicalWindow();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.MAPJAVAOBJECT)) {
			return obj = isMapJavaObject();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.JAVAOBJECTNAME)) {
			return obj = getJavaObjectName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CHILDWINDOWNAME)) {
			return obj = getChildWindowName();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISEDITABLE)) {
			return obj = isEditable();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISHIDDEN)) {
			return obj = isHidden();
		}
		return obj;
	}

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.BEANNAME)) {
			setBeanName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CATEGORYNAME)) {
			setCategory((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CUSTOMPANELNAME)) {
			setCustomPanelName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DATATYPE)) {
			setDataType((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DEFAULTVALUE)) {
			setDefaultValue((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.DESIGNTYPE)) {
			setDesignType((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.FIELDNAME)) {
			setFieldName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISSTARTUPDATA)) {
			if (((Boolean) object).booleanValue() == true)
				setIsStartupdata(1);
			else
				setIsStartupdata(0);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.STARTUPDATANAME)) {
			setStartUpDataName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.PARENTFIELDNAME)) {
			setParentFieldName((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISCHILDFIELD)) {
			setChildField(((Boolean) object).booleanValue());
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.WINDOWNAME)
				|| propertyPaneColumnName
						.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
			if (propertyPaneColumnName
					.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
				setWindowName((String) object + "Attributes");
			} else {
				setWindowName((String) object);
			}
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.NULLCHECKED)) {
			setNullChecked(((Boolean) object).booleanValue());
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISHIERARACHICALWINDOW)) {
			setIsHierarachicalWindow(((Boolean) object).booleanValue());
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.CHILDWINDOWNAME)) {
			setChildWindowName(((String) object));
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.MAPJAVAOBJECT)) {
			setMapJavaObject(((Boolean) object).booleanValue());
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.JAVAOBJECTNAME)) {
			setJavaObjectName(((String) object));
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISEDITABLE)) {
			setEditable(((Boolean) object).booleanValue());
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(WindowSheetConstants.ISHIDDEN)) {
			setHidden(((Boolean) object).booleanValue());
		}
	}

}
