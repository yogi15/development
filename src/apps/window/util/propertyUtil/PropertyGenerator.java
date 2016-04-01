package apps.window.util.propertyUtil;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createStartUPDataProperty; 
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import util.ClassInstantiateUtil;
import util.FileSearch;
import util.ReferenceDataCache;
import util.ReflectionUtil;
import util.commonUTIL;

import beans.AttributeContainer;
import beans.WindowSheet;

import com.jidesoft.grid.Property; 
import constants.JavaFileGeneratorConstants;
import constants.PropertyPaneConstants;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createNumberProperty;  

import static apps.window.util.propertyUtil.CommonPropertyUtil.createPassWordProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createAttributeProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createPositiveNumberProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBooleanProperty;  
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStringProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createIntegerProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createMultipleSelectionListProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createObjectMethodNameProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createBeanNameProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createLegalEntityProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createFolderProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createTimeZoneProperty;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createBookProperty;

public class PropertyGenerator {
	
	
	
	 

	public static List<Property> getProperties(String windowName,
			Vector<WindowSheet> windowProperty) {
		// TODO Auto-generated method stub
		List< Property> propertyList = new ArrayList< Property>();
		try {
		if(!commonUTIL.isEmpty(windowProperty)) {
			 for(int i=0;i<windowProperty.size();i++) {
				 WindowSheet ws = windowProperty.get(i);
				 String dataType = ws.getDataType(); 
				 if(!commonUTIL.isEmpty(dataType))  {
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 1)) {
					 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
					 if(!commonUTIL.isEmpty(sData)) {
						 PropertyEnum<String> renumProperty  = createStartUPDataProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData);
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.MULTISELECTION)) && (ws.getIsStartupdata()== 1)) {
					 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
					 if(!commonUTIL.isEmpty(sData)) {
						PropertyListMultipleSelection  renumProperty  = createMultipleSelectionListProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory(),sData );
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.BOOK))  ) {
					 	PropertyBook  renumProperty  = createBookProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory()  );
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.TIMEZONE)) ) {
					 
					 PropertyEnum<String>  renumProperty  = createTimeZoneProperty(ws.getFieldName(),ws.getFieldName(),ws.getCategory()  );
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.FOLDER))   ) {
					 Vector<String> sData = getFolderData();
					 if(!commonUTIL.isEmpty(sData)) {
						 PropertyEnum<String>  renumProperty  = createFolderProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory(),sData );
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.LEGALENTITY))  ) {
					 
						 PropertyLegalEntity renumProperty  = createLegalEntityProperty(ws.getFieldName(),ws.getFieldName(), ws.getCategory(),"PO" );
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.ATTRIBUTE) )  ) {
					 Vector<String>  attributeData = ReferenceDataCache.getStarupData(ws.getWindowName()+"Attributes");
				    	AttributeContainer attributebean = new AttributeContainer();
				    	attributebean.setAttributeName(ws.getWindowName()+"Attributes");
				    	attributebean.setAttributes( commonUTIL.convertStartupVectorToAtrributeVector(attributeData));
				    	AttributeProperty renumProperty  = createAttributeProperty(attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean);
				    	propertyList.add(renumProperty);
					 
					 
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 0)) {
					 PropertyString renumProperty  = createStringProperty(ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
						 if(!commonUTIL.isEmpty(ws.getDefaultValue()))
						 renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 
				 }
				 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.BOOLEAN))  ) {
					 PropertyBoolean renumProperty  = createBooleanProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
					 propertyList.add(renumProperty);
				 
			 }if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.INTEGER))  ) {
				 
				  
				 PropertyInteger renumProperty  = createIntegerProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
				 renumProperty.setValue(0);
				 propertyList.add(renumProperty);
			 
		 }
			 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.INTEGER))  ) {
				 
				  
				 PropertyInteger renumProperty  = createIntegerProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
				 renumProperty.setValue(0);
				 propertyList.add(renumProperty);
			 
		 }
			 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.NUMBER))  ) {
				 
				  
				 PropertyNumber renumProperty  = createNumberProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() ,10);
				 renumProperty.setValue(0);
				 propertyList.add(renumProperty);
			 
		 }
			 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.POSITIVENUMBER))  ) {
				 
				  
				 PropertyNumber  renumProperty = createPositiveNumberProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() ,10);
				 renumProperty.setValue(0);
				 propertyList.add(renumProperty);
			 
		 }	 
			 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.PASSWORD))  ) {
				 
				  
				 PropertyPassword  renumProperty = createPassWordProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim()  );
				 
				 propertyList.add(renumProperty);
			 
		 }	 
				 
			 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.BEANNAMES))  ) { 
				 Vector<String> sData =  getBeanFiles(JavaFileGeneratorConstants.BEANSPATH);
				 if(!commonUTIL.isEmpty(sData)) {
					 sData.add("NONE"); // when we don't want to select BEANNAME remove method 
					 PropertyEnum<String> renumProperty  = createBeanNameProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData);
					// renumProperty.setValue(ws.getDefaultValue() );
					 propertyList.add(renumProperty);
				 }
			 }
		 
			 
			
			
		}
			 }
		}
		return propertyList;
		}catch(NullPointerException e) {
			   commonUTIL.display("PropertyGenerator", "No Properities found for this window");
			    return propertyList;
		}
	}
	
	public static PropertyEnum<String> getBeanNames(String propertyName,String displayName,String category) {
		 Vector<String> sData =  getBeanFiles(JavaFileGeneratorConstants.BEANSPATH);
		 if(!commonUTIL.isEmpty(sData)) {
			 sData.add("NONE"); // when we don't want to select BEANNAME remove method 
			 PropertyEnum<String> renumProperty  = createBeanNameProperty(propertyName,displayName, category,sData );
		     return renumProperty;
		 } else {
			 return null;
		 }
	}
	
	
	public static PropertyEnum<String>  getMethodNames(String nameObject,String fieldName,String categoryName) {
		try {
			String path = "beans."+ nameObject;
			Class c1 = ClassInstantiateUtil.getClass(path, false);
			PropertyEnum<String> renumProperty = null;
			try {
				Vector<String> methodNames = getMethodNameOnObject(c1.newInstance());
				renumProperty = createObjectMethodNameProperty(fieldName,fieldName,categoryName, methodNames);
				return renumProperty;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static Vector<String> getBeanFiles(String path) {
		 
		return FileSearch.searchInFile(path);
		
	}
	
	private static Vector<String> getMethodNameOnObject(Object objectName) {
		 
		return ReflectionUtil.getOnlyNameofMethodStartWithGet(objectName );
		
	}
	private static Vector<String> getStartUpData(String startUpData) {
		return ReferenceDataCache.getStarupData(startUpData);
	}

	private static Vector<String> getFolderData( ) {
		return ReferenceDataCache.getFolderData();
	}
	public static PropertyString getStringProperty(String propertyName,String propertyDisplay,String category) {
	PropertyString renumProperty  = createStringProperty(propertyName, propertyDisplay,category);
	  
	 return renumProperty;
	}

	public static List<Property> getPropertiesOnAttributes(
			Vector<WindowSheet> windowProperty) {
		// TODO Auto-generated method stub
		List< Property> propertyList = new ArrayList< Property>();
		try {
		if(!commonUTIL.isEmpty(windowProperty)) {
			 for(int i=0;i<windowProperty.size();i++) {
				 WindowSheet ws = windowProperty.get(i);
				 String dataType = ws.getDataType(); 
				 if(!commonUTIL.isEmpty(dataType))  {
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 1)) {
					 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
					 if(!commonUTIL.isEmpty(sData)) {
						 PropertyEnum<String> renumProperty  = createStartUPDataProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData);
						// renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 0)) {
					 PropertyString renumProperty  = createStringProperty(ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
						 if(!commonUTIL.isEmpty(ws.getDefaultValue()))
						 renumProperty.setValue(ws.getDefaultValue() );
						 propertyList.add(renumProperty);
					 
				 }
				 }
			 }
		}
			 return propertyList;
		}catch(NullPointerException e) {
			   commonUTIL.display("PropertyGenerator", "No Properities found for this Attributes ");
			    return propertyList;
		}
	
	}

	public static  PropertyEnum<String> createPropertyFromStartUp(String propertyName,String nameFromStartUp,String category) {
		 Vector<String> sData =  getStartUpData(nameFromStartUp);
		 if(!commonUTIL.isEmpty(sData)) {
			 PropertyEnum<String> renumProperty  = createStartUPDataProperty(propertyName, propertyName, category, sData);
				// renumProperty.setValue(ws.getDefaultValue() );
				return renumProperty;
		 }
		 return null;
		// TODO Auto-generated method stub
		
	}

}
