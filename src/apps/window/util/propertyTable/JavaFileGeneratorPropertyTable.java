package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL; 
import apps.window.util.propertyUtil.BasePropertyTable;
import apps.window.util.propertyUtil.PropertyGenerator;
import beans.JavaFileGenerator; 

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.JavaFileGeneratorConstants;
import constants.PropertyPaneConstants; 

public class JavaFileGeneratorPropertyTable implements PropertyChangeListener  {

List< Property> JavaFileGeneratorProperties = null; 

public JavaFileGenerator javaFileGeneratorField ;
 /**
 * @return the propertyTable
 */
public PropertyTable getPropertyTable() {
	return propertyTable;
}

/**
 * @param propertyTable the propertyTable to set
 */
public void setPropertyTable(PropertyTable propertyTable) {
	this.propertyTable = propertyTable;
}


public PropertyTable propertyTable = null;
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		 		
		
	}
	String name = "";
	public JavaFileGeneratorPropertyTable(String name,JavaFileGenerator JavaFileGenerator ) {

		this.name = name;
		setJavaFileGenerator(JavaFileGenerator);
		 
	}

		  
		 public PropertyTable  getJavaFileGeneratorPropertyTable(List< Property>  properties) {      
			 
			 setPropertyTable( BasePropertyTable.makePropertyTable(getWindowSheetProperties(properties)));
			 return propertyTable;
			 
	    }
		 private List< Property> getWindowSheetProperties(List< Property> properties) {
			 for(  int i=0;i<properties.size();i++) {
					Property property = properties.get(i);
					if(property.getName().equalsIgnoreCase(JavaFileGeneratorConstants.BEANNAME)){
						addListenerToProperty(property,properties);
					}
						 
						 
				
				}

			return properties;
		 }
		
		 private void addListenerToProperty(final Property property ,final List< Property> properties  ) {
				if(property.getName().trim().equalsIgnoreCase(JavaFileGeneratorConstants.BEANNAME)) {
				property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() { 
					public void propertyChange(PropertyChangeEvent evt) {
						 
						if(property.getValue() != null) {
							Property p = propertyTable.getPropertyTableModel().getProperty(PropertyPaneConstants.METHODNAME);
							if(p == null) {
						      p  =  PropertyGenerator.getMethodNames(property.getValue().toString(), PropertyPaneConstants.METHODNAME, "JavaMethod");
						            properties.add(p);
							} else {
								 int index = propertyTable.getPropertyTableModel().getPropertyIndex(p);
								 propertyTable.getPropertyTableModel().getOriginalProperties().remove(index-1);
								 propertyTable.getPropertyTableModel().refresh();
								 p  =  PropertyGenerator.getMethodNames(property.getValue().toString(), PropertyPaneConstants.METHODNAME, "JavaMethod");
						            properties.add(p);
								// p  =  PropertyGenerator.getMethodNames(property.getValue().toString(), PropertyPaneConstants.METHODNAME, "JavaMethod");
						         //   properties.add(p);
							}
						
						 
						
					}
						
					}
		
			});
				} 
				 
					  
				 
				  
				
			}

		public  void setfillValues() {
			 try {
				 javaFileGeneratorField = new JavaFileGenerator();
				 List<Property> prop = propertyTable.getPropertyTableModel().getProperties();
				 for(int i=0;i<prop.size();i++)  {
					 Property property = prop.get(i); 
					 if(property.getValue() != null)
					 javaFileGeneratorField.setPropertyValue(property.getName(), property.getValue());
				 }
				  
				  
			  
			 } catch(Exception e) {
				 commonUTIL.displayError("JavaFileGeneratorPropertyTable", "setfillValues", e);
				  
			 } 
			 
		 }
		 
		 public void clearPropertyValues() {
			// CurrencyDefaultPropertyEnumList.clearALLList();
			 try {
			     propertyTable.clearSelection();
			   for(int i=0;i<propertyTable.getRowCount();i++)
				 propertyTable. setValueAt(null, i, 1);
				  
			 }catch(Exception e) {
				 commonUTIL.displayError("JavaFileGeneratorPropertyTable", "clearPropertyValues", e);
				 return;
			 }
			 
		 }
		
		
		
		 
		
		
		private Property getPropertyName(List< Property> properties,String name) {
			Property property = null;
			for(  int i=0;i<properties.size();i++) {
				  property = properties.get(i);
				if(property.getName().equalsIgnoreCase(name)){
					break;
				}
			}
			return property;
		}
		/**
		 * @return the wSheet
		 */
		public JavaFileGenerator getJavaFileGenerator() {
			return javaFileGeneratorField;
		}

		/**
		 * @param wSheet the wSheet to set
		 */
		public void setJavaFileGenerator(JavaFileGenerator jFGeneratorField) {
			this.javaFileGeneratorField = jFGeneratorField;
		}

		public void setPropertiesValues(JavaFileGenerator firstRecord) {
			// TODO Auto-generated method stub
			     propertyTable.clearSelection();
			     List<Property> prop = propertyTable.getPropertyTableModel().getProperties();
				 for(int i=0;i<prop.size();i++)  {
					if(firstRecord.getPropertyValue(prop.get(i).getName()) != null) {
						prop.get(i).setValue(firstRecord.getPropertyValue(prop.get(i).getName()));
					}
						
						
					  
						  
				 }
				  
				 
				 // setwSheet(firstRecord);
				 }
				
		 
}