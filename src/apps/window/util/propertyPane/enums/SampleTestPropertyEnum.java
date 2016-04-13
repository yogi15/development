package apps.window.util.propertyPane.enums;
  
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBooleanProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createEnumProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createIntegerProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStringProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createAttributeProperty;

import java.awt.Color;
import java.util.Vector;

import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createStartUPDataProperty;
import apps.window.util.property.CellDesignProperty;
import apps.window.util.propertyUtil.AttributeProperty;
import apps.window.util.propertyUtil.PropertyDate;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.Date.Provider.ProviderHoliday;
import beans.AttributeContainer;

import com.jidesoft.grid.Property;

import constants.CommonConstants;
import constants.SamplesTestContant;

public enum SampleTestPropertyEnum {
	NAME( createStringProperty(SamplesTestContant.NAME, SamplesTestContant.NAME, SamplesTestContant.NAME)),
	NUMBERFIELD(createIntegerProperty(SamplesTestContant.NUMBERTEXT, SamplesTestContant.NUMBERTEXT, SamplesTestContant.NUMBERTEXT)),
	BOOLEANFIELD( createBooleanProperty( SamplesTestContant.BOOLEANFIELD, SamplesTestContant.BOOLEANFIELD,SamplesTestContant.BOOLEANFIELD) ),
	ENUMTEST(  createEnumProperty("refreshed", "Refreshed Prop", "Other", "aa", "ab", "ac") ),
	STARTUPDATA( getcurrency() ),
	ATTRIBUTEDATA(getAttributeProperty()),
	DATE(getDateProperty());
	
	private String propertyName;
	private String _description;
	public  Property commonproperty;
	
	SampleTestPropertyEnum(String propertyName,Property property,String category) {
		this.propertyName = propertyName;
		this.commonproperty = property;
		commonproperty.setName(propertyName);
		commonproperty.setCategory(category);
	}

	SampleTestPropertyEnum(Property property) {
		this.propertyName = property.getName();
		this.commonproperty = property;
		commonproperty.setName(property.getName());
		commonproperty.setCategory(property.getCategory());
	}
	public static  Property getEnumProperty( ) {
		PropertyEnum<String> renumProperty  =  createEnumProperty("refreshed", "Refreshed Prop", "Other", "aa", "ab", "ac");
		    	return renumProperty;
			
		}
	public static  PropertyDate getDateProperty( ) {
		ProviderHoliday noHolidays = new ProviderHoliday() {
            public Vector getHolidays() {
               return new Vector();
            }
        };   

	 PropertyDate propertyDate	 = new PropertyDate(  "startDate",       "startDate",     "startDate",noHolidays);
	 propertyDate.setEditable(true);
	// propertyDate.setDisplayName("startDate");
//	 propertyDate.setCategory("startDate");
	 propertyDate.setName("startDate");
 
        return  propertyDate ;
			
		}
    public static Property getcurrency() {
    	Vector<String>  currencyData = ReferenceDataCache.getStarupData(CommonConstants.CURRENCY);
    	   
    	PropertyEnum<String> renumProperty  = createStartUPDataProperty(CommonConstants.CURRENCY, CommonConstants.CURRENCY, "StartUpData", currencyData);
    	return renumProperty;
    }
    public static Property getAttributeProperty( ) {
    	Vector<String>  attributeData = ReferenceDataCache.getStarupData("BookAttributes");
    	AttributeContainer attributebean = new AttributeContainer();
    	attributebean.setAttributeName("BookAttributes");
    	attributebean.setAttributes( commonUTIL.convertStartupVectorToAtrributeVector(attributeData));
    	
    	   
    	AttributeProperty renumProperty  = createAttributeProperty(attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean);
    	return renumProperty;
    }
     
public Property getProperty() {
	// TODO Auto-generated method stub
	return commonproperty;
}

   
   
   

}
