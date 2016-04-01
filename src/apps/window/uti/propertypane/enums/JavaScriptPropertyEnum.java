package apps.window.uti.propertypane.enums;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createStartUPDataProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStringProperty;

import java.util.Vector;

import util.ReferenceDataCache;
import apps.window.util.propertyUtil.PropertyEnum;

import com.jidesoft.grid.Property;

import constants.JavaScriptConstants;
import constants.WindowSheetConstants;

public enum JavaScriptPropertyEnum {
	
	
	WINDOWNAME( getWindowNames()),
	SCRIPTNAME( getScriptNames());
	
	private String propertyName;
	private String _description;
	public  Property commonproperty;
	
	JavaScriptPropertyEnum( Property property ) {
		this.propertyName = property.getName();
		this.commonproperty = property;
		commonproperty.setName(property.getName());
		commonproperty.setCategory(property.getCategory());
	}
	
	public static Property getWindowNames() {
    	Vector<String>  windowNameData = ReferenceDataCache.getStarupData(WindowSheetConstants.WINDOWNAME);
    	   
    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(JavaScriptConstants.WINDOWNAME, JavaScriptConstants.WINDOWNAME, "Script", windowNameData);
    	return renumProperty;
    }
	public static Property getScriptNames() {
    	Vector<String>  windowNameData = ReferenceDataCache.getStarupData("JavaScriptType");
    	   
    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(JavaScriptConstants.SCRIPTNAME, JavaScriptConstants.SCRIPTNAME, JavaScriptConstants.SCRIPTNAME, windowNameData);
    	return renumProperty;
    }

	public Property getProperty() {
		// TODO Auto-generated method stub
		return commonproperty;
	}
}
