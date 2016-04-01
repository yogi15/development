package apps.window.uti.propertypane.enums;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createBooleanProperty; 
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStartUPDataProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStringProperty;

import java.util.Vector;

import util.ReferenceDataCache;

import apps.window.util.propertyUtil.PropertyEnum;

import com.jidesoft.grid.Property;
  
import constants.WindowSheetConstants;

public enum WindowSheetPropertyEnum {
	DESIGNTYPE( getDesignNames()),
	WINDOWNAME( getWindowNames()),
	FIELDNAME( createStringProperty(WindowSheetConstants.FIELDNAME, WindowSheetConstants.FIELDNAME, "WindowSheet")),
	DATATYPE(getDataTypes()),
	CATEGORYNAME( createStringProperty(WindowSheetConstants.CATEGORYNAME, WindowSheetConstants.CATEGORYNAME, "WindowSheet")),

	ISSTARTUPDATA( createBooleanProperty( WindowSheetConstants.ISSTARTUPDATA, WindowSheetConstants.ISSTARTUPDATA,"WindowSheet") ),
	STARTUPDATANAME(  getStartUpData() ),
	DEFAULTVALUE(createStringProperty(WindowSheetConstants.DEFAULTVALUE, WindowSheetConstants.DEFAULTVALUE, "WindowSheet")),
	CUSTOMPANELNAME(getCustDatomPanlData()),
	NULLCHECKED(createBooleanProperty( WindowSheetConstants.NULLCHECKED, WindowSheetConstants.NULLCHECKED,"WindowSheet"));
	 
	
	private String propertyName;
	private String _description;
	public  Property commonproperty;
	
	WindowSheetPropertyEnum(String propertyName,Property property,String category) {
		this.propertyName = propertyName;
		this.commonproperty = property;
		commonproperty.setName(propertyName);
		commonproperty.setCategory(category);
	}

	WindowSheetPropertyEnum(Property property) {
		this.propertyName = property.getName();
		this.commonproperty = property;
		commonproperty.setName(property.getName());
		commonproperty.setCategory(property.getCategory());
	}
	  public static Property getWindowNames() {
	    	Vector<String>  windowNameData = ReferenceDataCache.getStarupData(WindowSheetConstants.WINDOWNAME);
	    	   
	    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(WindowSheetConstants.WINDOWNAME, WindowSheetConstants.WINDOWNAME, "WindowSheet", windowNameData);
	    	return renumProperty;
	    }
	  public static Property getDataTypes() {
	    	Vector<String>  dataTypata = ReferenceDataCache.getStarupData(WindowSheetConstants.DATATYPE);
	    	   
	    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(WindowSheetConstants.DATATYPE, WindowSheetConstants.DATATYPE, "WindowSheet", dataTypata);
	    	return renumProperty;
	    }
	  public static Property getStartUpData() {
	    	Vector<String>  initialData = ReferenceDataCache.getStarupData("InitialData");
	    	   
	    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(WindowSheetConstants.STARTUPDATANAME, WindowSheetConstants.STARTUPDATANAME, "WindowSheet", initialData);
	    	return renumProperty;
	    }
	  public static Property getCustDatomPanlData() {
	    	Vector<String>  initialData = ReferenceDataCache.getStarupData(WindowSheetConstants.CUSTOMPANELNAME);
	    	   
	    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(WindowSheetConstants.CUSTOMPANELNAME, WindowSheetConstants.CUSTOMPANELNAME, "WindowSheet", initialData);
	    	return renumProperty;
	    }
	  public static Property getDesignNames() {
	    	Vector<String>  initialData = ReferenceDataCache.getStarupData("DesignType");
	    	   
	    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(WindowSheetConstants.STARTUPDATANAME, WindowSheetConstants.STARTUPDATANAME, "WindowSheet", initialData);
	    	return renumProperty;
	    }
	  public Property getProperty() {
			// TODO Auto-generated method stub
			return commonproperty;
		}
}
