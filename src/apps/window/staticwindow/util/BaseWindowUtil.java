package apps.window.staticwindow.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import util.CosmosException;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;

import beans.WindowSheet;

import com.jidesoft.grid.Property;

import constants.CommonConstants;
import constants.WindowSheetConstants;

import apps.window.staticwindow.BasePanel;
import apps.window.util.propertyUtil.PropertyGenerator;

public abstract class BaseWindowUtil {
	public abstract boolean validate();

	public abstract void windowstartUpData()  throws CosmosException;

	public abstract Vector<String> fillData(String action);

	public abstract void clearALL();

	public boolean clearAllPropertiesFields = false;

	// TODO Auto-generated method stub

	/*
	 * used to map action when any event occurs on panel component . Parameters:
	 * name - of the action -
	 * 
	 * Returns: void
	 */
	public abstract void actionMapper(String action);

	public abstract void setWindow(BasePanel windowName);

	/*
	 * creates a List<Property> property
	 * 
	 * Parameters: name - the name of the window - identifies the properties for
	 * the window
	 * 
	 * Returns: List<Property> property
	 */
	public List<Property> generateProperty(String windowName) throws CosmosException {
		List<Property> properties = null;
		Vector<WindowSheet> windowPropertys = getWindowProperty(windowName);
		properties = PropertyGenerator.getProperties(windowName,
				windowPropertys);
		if(commonUTIL.isEmpty(properties)) {
		     throw new CosmosException("Properties issues in Window " + windowName, new CosmosException());
		}
		for (int i = 0; i < properties.size(); i++) {
			final Property prop = properties.get(i);
			prop.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							// TODO Auto-generated method stub

						}
					});
		}
		return properties;
	}
	/*
	 * creates a List<Property> property on Attributes
	 * 
	 * Parameters: name - the name of the window - identifies the properties for
	 * the window
	 * 
	 * Returns: List<Property> property
	 */
	public List<Property> generateAttributesProperty(String windowName) throws CosmosException {
		
		List<Property> properties = null; 
		Vector<WindowSheet> windowPropertys = getWindowProperty(windowName,"Attribute");
		properties = PropertyGenerator.getProperties(windowName,
				windowPropertys);
		if(commonUTIL.isEmpty(properties)) {
		     throw new CosmosException("Attributes Properties issues in Window " + windowName, new CosmosException());
		}
		for (int i = 0; i < properties.size(); i++) {
			final Property prop = properties.get(i);
			prop.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							// TODO Auto-generated method stub

						}
					});
		}
		return properties;
	}
	public static Vector<WindowSheet> getWindowProperty(String windowName) {
		Vector<WindowSheet> windowSheets = ReferenceDataCache
				.selectWindowSheets(windowName, WindowSheetConstants.WINDOW);
		if (commonUTIL.isEmpty(windowSheets)) {
			// commonUTIL.display(logName, name, message);
			commonUTIL.display(windowName + " from base Window",
					"ReferenceDataCache.selectWindowSheets coming null");
		}
		return windowSheets;
	}
	public static Vector<WindowSheet> getWindowProperty(String windowName,String designType) {
		Vector<WindowSheet> windowSheets = ReferenceDataCache
				.selectWindowSheets(windowName, designType);
		if (commonUTIL.isEmpty(windowSheets)) {
			// commonUTIL.display(logName, name, message);
			commonUTIL.display(windowName + " from base Window",
					"ReferenceDataCache.selectWindowSheets coming null");
		}
		return windowSheets;
	}
	/*
	 * Dynamic a field validation for null values if specify in Configuration
	 * 
	 * Parameters: Object - Object name type of window - identifies the
	 * properties for the window name - name of the window - identifies the
	 * properties for the window Returns: true or false
	 */

	public boolean validate(Object object, String windowName) {
		if (object == null) {
			commonUTIL
					.showAlertMessage(" To Validate object, object must not be Empty");
			return false;
		}
		if (commonUTIL.isEmpty(windowName)) {
			commonUTIL
					.showAlertMessage(" To Validate  Window, Window Name must not be Empty");
			return false;
		}
		Vector<WindowSheet> wsProp = getWindowProperty(windowName);
		boolean flag = true;
		for (int i = 0; i < wsProp.size(); i++) {
			WindowSheet wSheet = wsProp.get(i);
			if (wSheet.isNullChecked()) {
				Object value = getValue(
						wSheet.getDataType(),
						commonUTIL.getMethodValueFromReflect(object, "get"
								+ wSheet.getFieldName()));
				if (value == null)
					value = getValue(
							wSheet.getDataType(),
							commonUTIL.getMethodValueFromReflect(object, "get"
									+ wSheet.getMethodName()));
				if (value == null) {
					commonUTIL.showAlertMessage(wSheet.getFieldName()
							+ " must not be Empty");
					flag = false;
					break;
				}

			}
		}

		return flag;
	}

	public Object getValue(String returnType, Object object) {
		Object obj = null;
		if (object instanceof String) {
			return (String) object;
		}
		if (object instanceof Integer) {
			return (Integer) object;
		}
		if (object instanceof Boolean) {
			return (Boolean) object;
		}
		if (object instanceof Double) {
			return (Double) object;
		}
		return (String) object;
	}

}
