package apps.window.staticwindow.util;
 
import java.util.List;
import java.util.Vector;

import util.ReferenceDataCache; 
import util.commonUTIL;

import beans.WindowSheet;

import com.jidesoft.grid.Property;

import constants.CommonConstants;
import constants.WindowSheetConstants;

import apps.window.staticwindow.BasePanel;
import apps.window.util.propertyUtil.PropertyGenerator;

 

public abstract class BaseWindowUtil {
	public abstract boolean   validate( );
	public abstract void   windowstartUpData( );
	public abstract Vector<String> fillData(String  action);
	public abstract void clearALL();
		// TODO Auto-generated method stub
		
	
	/* used to map action when any event occurs on panel component .
	Parameters:
	name - of the action -  
	 
	Returns: void */
	public abstract void actionMapper(String action);
	public abstract void setWindow(BasePanel windowName);
	
	/*creates a List<Property>  property

	Parameters:
	name - the name of the window - identifies the properties for the window
	 
	Returns: List<Property>  property */
	public List<Property> generateProperty(String windowName) {
		List<Property> properties = null;
		Vector<WindowSheet> windowPropertys = getWindowProperty(windowName);
		properties = PropertyGenerator.getProperties(windowName,windowPropertys);
		return properties;
	}
	 
	 public Vector<WindowSheet> getWindowProperty(String windowName) {
		 Vector<WindowSheet> windowSheets =  ReferenceDataCache.selectWindowSheets(windowName,WindowSheetConstants.WINDOW);
		 if(commonUTIL.isEmpty(windowSheets)) {
			 //commonUTIL.display(logName, name, message);
			 commonUTIL.display(windowName + " from base Window", "ReferenceDataCache.selectWindowSheets coming null" );
		 }
		 return windowSheets;
	 }
	 /*Dynamic a field validation for null values if specify in Configuration 

		Parameters:
		Object - Object name type of  window - identifies the properties for the window
		 name -  name of the window - identifies the properties for the window
		Returns: true or false */
	 
	 public boolean validate(Object object,String windowName)  {
		 if(object == null) {
			 commonUTIL.showAlertMessage( " To Validate object, object must not be Empty");
			 return false;
		 }
		 if(commonUTIL.isEmpty(windowName)) {
			 commonUTIL.showAlertMessage( " To Validate  Window, Window Name must not be Empty");
			 return false;
		 }
		 Vector<WindowSheet> wsProp   = getWindowProperty(windowName);
		 boolean flag = true;
		 for(int i=0;i<wsProp.size();i++) {
				WindowSheet wSheet = wsProp.get(i);
					if(wSheet.isNullChecked())  {
					Object value =   getValue(wSheet.getDataType(), commonUTIL.getMethodValueFromReflect(object,"get"+wSheet.getFieldName())) ;
					if(value == null)
						 value =   getValue(wSheet.getDataType(), commonUTIL.getMethodValueFromReflect(object,"get"+wSheet.getMethodName()) );
						if(value ==null) {
							commonUTIL.showAlertMessage(wSheet.getFieldName() +" must not be Empty");
							flag = false;
							break;
						}  
					 
					}
			}
		 
		 return flag;
	 }
	 
	 public Object getValue(String returnType,Object object) {
		 Object obj = null;
		  if(object instanceof String) {
			  return (String) object;
		  }
		  if(object instanceof Integer) {
			  return (Integer) object;
		  }
		  if(object instanceof Boolean) {
			  return (Boolean) object;
		  }
		  if(object instanceof Double) {
			  return (Double) object;
		  }
		  return (String) object;
	 }
	
	 
	  
}
