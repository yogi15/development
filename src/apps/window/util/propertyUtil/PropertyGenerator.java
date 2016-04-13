package apps.window.util.propertyUtil;

import static apps.window.util.propertyUtil.CommonPropertyUtil.createAttributeProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBeanNameProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBookProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBooleanProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createBuySellProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createDateProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createFieldListProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createFolderProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createIntegerProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createLegalEntityProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createMultipleSelectionListProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createNumberProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createObjectMethodNameProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createPassWordProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createPositiveNumberProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createPropertyConditional;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createSearchBoxProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStartUPDataProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createStringProperty;
import static apps.window.util.propertyUtil.CommonPropertyUtil.createTimeZoneProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import util.ClassInstantiateUtil;
import util.FileSearch;
import util.ReflectionUtil;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import beans.AttributeContainer;
import beans.HolidayCode;
import beans.WindowSheet;

import com.jidesoft.grid.Property;

import constants.BeanConstants;
import constants.HolidayCodeConstants;
import constants.JavaFileGeneratorConstants;
import constants.PropertyPaneConstants;
import constants.WindowSheetConstants;

public class PropertyGenerator {

	public static List<Property> getProperties(String windowName,
			Vector<WindowSheet> windowProperty ) {
		WindowSheet ws = null;
		// TODO Auto-generated method stub
		List< Property> propertyList = new ArrayList< Property>();
		try {
		if(!commonUTIL.isEmpty(windowProperty)) {
			 for(int i=0;i<windowProperty.size();i++) {
				 ws = windowProperty.get(i);
				 String dataType = ws.getDataType(); 
				 Property renumProperty = null;
				 if(!commonUTIL.isEmpty(dataType) && !ws.isMapJavaObject() )  {
					 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 1)) {
					 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
					 if(!commonUTIL.isEmpty(sData)) { 
						renumProperty  = createStartUPDataProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData); 			
						renumProperty.setValue(ws.getDefaultValue());
					 }
				 }
					 if((dataType.equalsIgnoreCase(PropertyPaneConstants.INITIALDATA)) ) {
						 Vector<String> sData =  getStartUpData(PropertyPaneConstants.INITIALDATA);
						 if(!commonUTIL.isEmpty(sData)) { 
							renumProperty  = createStartUPDataProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData); 						 
						 }
					 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.MULTISELECTION)) && (ws.getIsStartupdata()== 1)) {
					 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
					 if(!commonUTIL.isEmpty(sData)) {
						   renumProperty  = createMultipleSelectionListProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory(),sData );
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.MULTICOLUMNNAMESSELECTION)) ) {
					  renumProperty  = 	 getMultiColumnMethodNames(ws.getBeanName(),ws.getFieldName(),ws.getCategory() );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.IFELSECONDITIONAL)) ) {
					 Vector<String> sdata =  new Vector<String> ();  
					 
					  renumProperty  = 	 createPropertyConditional(ws.getFieldName(),ws.getFieldName(),ws.getCategory(),sdata,windowName,ws.getDesignType() );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.DATE))  ) {
					 
						   renumProperty  = createDateProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory()  );
					 
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.BUYSELL))  ) {
					 
					   renumProperty  = createBuySellProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory()  );
				 
			 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.HOLIDAYCODE))  ) {
					 
					   renumProperty  = createHolidayCodeProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory()  );
				 
			 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.BOOK))  ) {
					 	   renumProperty  = createBookProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory()  );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.TIMEZONE)) ) {
					 
					    renumProperty  = createTimeZoneProperty(ws.getFieldName(),ws.getFieldName(),ws.getCategory()  );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.FOLDER))   ) {
					 Vector<String> sData = getFolderData();
					 if(!commonUTIL.isEmpty(sData)) {
						  renumProperty  = createFolderProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory(),sData );
					 }
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.LEGALENTITY))  ) {
					 
						 renumProperty  = createLegalEntityProperty(ws.getFieldName(),ws.getFieldName(), ws.getCategory(),"PO" );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.SEARCHBOX))  ) {
					 
					 renumProperty  = createSearchBoxProperty(ws.getFieldName(),ws.getFieldName(), ws.getCategory(),"Product" );
			 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.ATTRIBUTE) )  ) {
					 Vector<String>  attributeData = ReferenceDataCache.getStarupData(ws.getWindowName()+"Attributes");
				    	AttributeContainer attributebean = new AttributeContainer();
				    	attributebean.setAttributeName(ws.getWindowName()+"Attributes");
				    	attributebean.setAttributes( commonUTIL.convertStartupVectorToAtrributeVector(attributeData));
				      renumProperty  = createAttributeProperty(attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean);
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.STRING)) && (ws.getIsStartupdata()== 0)) {
					 renumProperty  = createStringProperty(ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
						 if(!commonUTIL.isEmpty(ws.getDefaultValue()))
						 renumProperty.setValue(ws.getDefaultValue() );
				 }
				 if((dataType.equalsIgnoreCase(PropertyPaneConstants.FIELDNAMES)) && (ws.getIsStartupdata()== 0)) {
					 renumProperty  = createFieldListProperty(ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim(),ws.getWindowName() );
						 
				 }
				 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.BOOLEAN))  ) {
					      
					  renumProperty  = createBooleanProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
					  if(ws.getFieldName().equalsIgnoreCase(WindowSheetConstants.ISEDITABLE)) {
					    	if(renumProperty != null) {
					    		 
					    		    renumProperty.setValue(new Boolean(true));
					    	}
					     }
				 }  if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.INTEGER))  ) {
					  renumProperty  = createIntegerProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() );
						 renumProperty.setValue(0);
					//	 renumProperty.setEditable(true);
				 }
					 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.NUMBER))  ) {
						  renumProperty  = createNumberProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() ,10);
						 renumProperty.setValue(0);
				 }
					 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.POSITIVENUMBER))  ) {
						  renumProperty = createPositiveNumberProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim() ,10);
						 renumProperty.setValue(0);
				 }	 
					 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.PASSWORD))  ) {
						  renumProperty = createPassWordProperty( ws.getFieldName().trim(), ws.getFieldName().trim(), ws.getCategory().trim()  );
				 }		 
					 if((dataType.trim().equalsIgnoreCase(PropertyPaneConstants.BEANNAMES))  ) { 
						 Vector<String> sData =  getBeanFiles(JavaFileGeneratorConstants.BEANSPATH);
						 if(!commonUTIL.isEmpty(sData)) {
							 sData.add("NONE"); // when we don't want to select BEANNAME remove method 
							  renumProperty  = createBeanNameProperty(ws.getFieldName(), ws.getFieldName(), ws.getCategory(), sData);
							// renumProperty.setValue(ws.getDefaultValue() );
							
						 }
					 }
		 
			 
			if(renumProperty != null ) {
				if(!ws.isEditable()) {
					renumProperty.setEditable(false);
					
				}
				if(commonUTIL.isEmpty(ws.getParentFieldName())) {
					if( ws.isCondition() && !commonUTIL.isEmpty(ws.getConfigureIfelseCondition())) {
						 
					Hashtable<String,String> conditions = 		getconditions(ws.getConfigureIfelseCondition());
					//renumProperty.addPropertyChangeListener(arg0, arg1) 
					addConditionalProperty(renumProperty,conditions);
					}
			       propertyList.add(renumProperty);
				} else {
					Property parentProperty = getParentPropertyFromList(propertyList,ws.getParentFieldName());
					if(parentProperty != null) { 
					addChildProperty(parentProperty,renumProperty );
					} else {
						 commonUTIL.display("PropertyGenerator", "Parent Property Field is Null on Window " + ws.getWindowName() + " for Child Property " +    ws .getFieldName() + " dataType ==  "+ ws.getDataType());
						    return propertyList;
					}
					
				}
				
				
				
					
			}
		}
			 }
		}
		return propertyList;
		}catch(NullPointerException e) {
			   commonUTIL.display("PropertyGenerator", "No Properities found for this window on " +  ws .getFieldName() + " dataType ==  "+ ws.getDataType());
			    return propertyList;
		}
	}

	private static void addConditionalProperty(Property conditionalProperty,final Hashtable<String,String> conditions ) {
		conditionalProperty.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				
				 List<Property> childList = (List<Property>) ((Property) evt.getSource()).getChildren();
				setAllChildHidden(childList);
				 String newValue = (String) evt.getNewValue();
				 for (Map.Entry<String, String> entry : conditions.entrySet()) { 
			            if(newValue.equalsIgnoreCase( entry.getKey())) {
			            	String conds [] = entry.getValue().split(","); 
			            	if(!commonUTIL.isEmpty(childList)) {
								   for(int i=0;i<childList.size();i++) {
									  for(int s=0;s<conds.length;s++) {
										  if(conds[s].equalsIgnoreCase(childList.get(i).getName())) {
											  childList.get(i).setHidden(false);
										   
									  }
										  
								   }
								   }
							   }
							
			            }
			        }
				 ((Property) evt.getSource()).setExpanded(true);
	                
			}
		});
			
		}
		private static void setAllChildHidden( List<Property> childList) {
			   for(int i=0;i<childList.size();i++)  
				   childList.get(i).setHidden(true);
			
		}
							 
	
	public static Property createWindowFieldName(String windowName,
			String fieldName, String category) {
		Property	property =  createFieldListProperty(fieldName,fieldName, category,windowName );
		return property;
	}
	private static Property createHolidayCodeProperty(String windowName,
			String fieldName, String category) {
		// TODO Auto-generated method stub
		 Property renumProperty = null;
		Vector<HolidayCode> sData = (Vector<HolidayCode>) ReferenceDataCache.selectKeyColumns(HolidayCodeConstants.SELECTHOLIDAYCODECOLUMN, BeanConstants.HOLIDAY);
	 
	Vector<String> codeData = 	commonUTIL.convertHolidayCodetoString(sData );
		if(!commonUTIL.isEmpty(sData)) {
			   renumProperty  = createMultipleSelectionListProperty(windowName,fieldName,category,codeData );
		 }
		 return renumProperty;
	}

	private static   Property   getParentPropertyFromList(List<Property> propertyList,String parentPropertyName) {
		Property parentProperty = null;
		if(!commonUTIL.isEmpty(propertyList)) {
			 
			for(int i=0;i<propertyList.size();i++) {
				parentProperty = propertyList.get(i);
				if(parentProperty != null && parentProperty.getName().equalsIgnoreCase(parentPropertyName)) {
					break;
				}
			}
		}
		return parentProperty;
		// TODO Auto-generated method stub
		
	}

	
	public static   Property   getAttributeProperty( String AttributesName) {
		Vector<String>  attributeData = ReferenceDataCache.getStarupData(AttributesName);
    	AttributeContainer attributebean = new AttributeContainer();
    	attributebean.setAttributeName(AttributesName);
    	attributebean.setAttributes( commonUTIL.convertStartupVectorToAtrributeVector(attributeData));
    	Property  renumProperty  = createAttributeProperty(attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean.getAttributeName(), attributebean);
		// TODO Auto-generated method stub
		return renumProperty;
	}
	
	private static Hashtable getconditions(String condition) {
		Hashtable<String,String> conditons = new Hashtable<String,String>();
		if(!commonUTIL.isEmpty(condition))  {
			StringTokenizer conds  = new StringTokenizer(condition,"|");
			while(conds.hasMoreElements()) {
				String cond = conds.nextToken();
				String key = cond.substring(0, cond.indexOf("="));
				String value = cond.substring(key.length()+1,cond.length());
				conditons.put(key, value);
			 
				
			}
		}
		return conditons;
		
	}
	private static void addChildProperty(Property parentProperty,Property childProperty ) {
		if(parentProperty != null)
			parentProperty.addChild(childProperty);
			parentProperty.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					
					if(  evt.getNewValue() instanceof  Boolean) {
					Boolean parentSignal = (Boolean) evt
							.getNewValue();

					if (parentSignal != null) {
						if (parentSignal.booleanValue() == true) {
						   List<Property> childList = (List<Property>) ((Property) evt.getSource()).getChildren();
						   if(!commonUTIL.isEmpty(childList)) {
							   for(int i=0;i<childList.size();i++) {
								  ( (Property)childList.get(i)).setHidden(false);
								 // clearPropertyValue((Property)childList.get(i));
							   }
						   }
						   ((Property) evt.getSource()).setExpanded(true);
						} 
						if (parentSignal.booleanValue() == false) {
							   List<Property> childList = (List<Property>) ((Property) evt.getSource()).getChildren();
							   if(!commonUTIL.isEmpty(childList)) {
								   for(int i=0;i<childList.size();i++) {
									  ( (Property)childList.get(i)).setHidden(true);
									  clearPropertyValue((Property)childList.get(i));
								   }
							   }
							   ((Property) evt.getSource()).setExpanded(false);
							}
					}
					
					
				}
				}
			});
	}
	
	
	
	public static PropertyEnum<String> getBeanNames(String propertyName,
			String displayName, String category) {
		Vector<String> sData = getBeanFiles(JavaFileGeneratorConstants.BEANSPATH);
		if (!commonUTIL.isEmpty(sData)) {
			sData.add("NONE"); // when we don't want to select MethodName remove
								// method property we set BEANNAME AS NONE.
			PropertyEnum<String> renumProperty = createBeanNameProperty(
					propertyName, displayName, category, sData);
			return renumProperty;
		} else {
			return null;
		}
	}

	public static PropertyEnum<String> getMethodNames(String nameObject,
			String fieldName, String categoryName) {
		try {
			if (commonUTIL.isEmpty(nameObject)
					|| nameObject.equalsIgnoreCase("NONE"))
				return null;
			String path = "beans." + nameObject;
			Class c1 = ClassInstantiateUtil.getClass(path, false);
			PropertyEnum<String> renumProperty = null;
			try {
				Vector<String> methodNames = getMethodNameOnObject(c1
						.newInstance());
				renumProperty = createObjectMethodNameProperty(fieldName,
						fieldName, categoryName, methodNames);
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
	public static PropertyListMultipleSelection getMultiColumnMethodNames(String nameObject,
			String fieldName, String categoryName) {
		try {
			if (commonUTIL.isEmpty(nameObject)
					|| nameObject.equalsIgnoreCase("NONE"))
				return null;
			String path = "beans." + nameObject;
			Class c1 = ClassInstantiateUtil.getClass(path, false);
			PropertyListMultipleSelection renumProperty = null;
			try {
				Vector<String> methodNames = getMethodNameOnObject(c1
						.newInstance());
				renumProperty = createMultipleSelectionListProperty(fieldName,
						fieldName, categoryName, methodNames);
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

		return ReflectionUtil.getOnlyNameofMethodStartWithGet(objectName);

	}

	private static Vector<String> getStartUpData(String startUpData) {
		return ReferenceDataCache.getStarupData(startUpData);
	}

	private static Vector<String> getFolderData() {
		return ReferenceDataCache.getFolderData();
	}

	public static PropertyString getStringProperty(String propertyName,
			String propertyDisplay, String category) {
		PropertyString renumProperty = createStringProperty(propertyName,
				propertyDisplay, category);

		return renumProperty;
	}

	public static List<Property> getPropertiesOnAttributes(
			Vector<WindowSheet> windowProperty) {
		// TODO Auto-generated method stub
		List<Property> propertyList = new ArrayList<Property>();
		try {
			if (!commonUTIL.isEmpty(windowProperty)) {
				for (int i = 0; i < windowProperty.size(); i++) {
					WindowSheet ws = windowProperty.get(i);
					String dataType = ws.getDataType();
					if (!commonUTIL.isEmpty(dataType)) {
						if ((dataType
								.equalsIgnoreCase(PropertyPaneConstants.STRING))
								&& (ws.getIsStartupdata() == 1)) {
							Vector<String> sData = getStartUpData(ws
									.getStartUpDataName().trim());
							if (!commonUTIL.isEmpty(sData)) {
								PropertyEnum<String> renumProperty = createStartUPDataProperty(
										ws.getFieldName(), ws.getFieldName(),
										ws.getCategory(), sData);
								// renumProperty.setValue(ws.getDefaultValue()
								// );
								propertyList.add(renumProperty);
							}
						}
						if ((dataType
								.equalsIgnoreCase(PropertyPaneConstants.STRING))
								&& (ws.getIsStartupdata() == 0)) {
							PropertyString renumProperty = createStringProperty(
									ws.getFieldName().trim(), ws.getFieldName()
											.trim(), ws.getCategory().trim());
							if (!commonUTIL.isEmpty(ws.getDefaultValue()))
								renumProperty.setValue(ws.getDefaultValue());
							propertyList.add(renumProperty);

						}
						 if((dataType.equalsIgnoreCase(PropertyPaneConstants.MULTISELECTION)) && (ws.getIsStartupdata()== 1)) {
							 Vector<String> sData =  getStartUpData(ws.getStartUpDataName().trim());
							 if(!commonUTIL.isEmpty(sData)) {
								 propertyList.add(  createMultipleSelectionListProperty(ws.getWindowName(),ws.getFieldName(),ws.getCategory(),sData ));
							 }
						 }
					}
				}
			}
			return propertyList;
		} catch (NullPointerException e) {
			commonUTIL.display("PropertyGenerator",
					"No Properities found for this Attributes ");
			return propertyList;
		}

	}

	public static PropertyEnum<String> createPropertyFromStartUp(
			String propertyName, String nameFromStartUp, String category) {
		Vector<String> sData = getStartUpData(nameFromStartUp);
		if (!commonUTIL.isEmpty(sData)) {
			PropertyEnum<String> renumProperty = createStartUPDataProperty(
					propertyName, propertyName, category, sData);
			// renumProperty.setValue(ws.getDefaultValue() );
			return renumProperty;
		}
		return null;
		// TODO Auto-generated method stub

	}

	public static PropertyEnum<String> createPropertyFromStartUp(
			String propertyName, String nameFromStartUp, String category,
			Vector<String> sData) {

		if (!commonUTIL.isEmpty(sData)) {
			PropertyEnum<String> renumProperty = createStartUPDataProperty(
					propertyName, propertyName, category, sData);
			// renumProperty.setValue(ws.getDefaultValue() );
			return renumProperty;
		}
		return null;
		// TODO Auto-generated method stub

	}
	
	
	private static void clearPropertyValue(Property prop ) {
		if(prop == null)
			return;
		if (prop instanceof PropertyEnum) {
			PropertyEnum<String> p = (PropertyEnum<String>) prop;
			p.setValue(""); 
		} else {
			if (prop.getValue() instanceof String) {
				prop.setValue("");
			} else if (prop.getValue() instanceof Double) {
				prop.setValue(0.0);
			} else if (prop.getValue() instanceof Boolean) {
				prop.setValue(false);
			} else if (prop.getValue() instanceof Integer) {
				prop.setValue(0);
			}
		}
		 }
	


}
