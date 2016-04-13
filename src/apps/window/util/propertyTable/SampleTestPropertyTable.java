package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import util.cacheUtil.ReferenceDataCache;

import apps.window.util.propertyPane.enumsList.CurrencyDefaultPropertyEnumList;
import apps.window.util.propertyPane.enumsList.SampleTestPropertyEnumList;
import apps.window.util.propertyUtil.BasePropertyTable;
import beans.PropertyCellStyle;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.BeanConstants;
import constants.CurrencyDefaultConstant;
import constants.SamplesTestContant;

public class SampleTestPropertyTable extends BasePropertyTable implements PropertyChangeListener  {

	 
	List< Property> sampleTestProperties = null;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		   
          
		
	}
	String name = "";
	List<Property> prop;
	public SampleTestPropertyTable(String name) {

		this.name = name;
	

	}
	public SampleTestPropertyTable(List <Property> prop) {

		this.prop = prop;
	

	}
		 public PropertyTable getSampleTestPropertyTable() {
	       
			 sampleTestProperties = getSampleTestProperties();
			 return makePropertyTable(sampleTestProperties);
			 
	    }
		 public PropertyTable getSampleTestPropertyTable(List <Property> prop1,String windowName) {
			   String sql = "WindowName = '"+windowName+"'";
		     Vector<PropertyCellStyle> propCellSty =   (Vector<PropertyCellStyle>) ReferenceDataCache.selectWhere(sql, BeanConstants.PROPERTYCELLSTYLE);
			 sampleTestProperties =  prop1;
			 
			 return makePropertyTable(sampleTestProperties,propCellSty);
			 
	    }
		private List< Property> getSampleTestProperties() {
			// TODO Auto-generated method stub
			 

			 

			final  List< Property> properties  = SampleTestPropertyEnumList.SAMPLETEST.getPropertyList(SamplesTestContant.SAMPLETEST);
				for(  int i=0;i<properties.size();i++) {
					Property property = properties.get(i);
					if(property.getName().equalsIgnoreCase(SamplesTestContant.BOOLEANFIELD)){
						addListenerToProperty(property,properties);
				}
				}

			return properties;
		} 
		
		private void addListenerToProperty(Property property ,final List< Property> properties  ) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() { 
				public void propertyChange(PropertyChangeEvent evt) {
					Boolean isCurrentTimeCheck = (Boolean)evt.getNewValue();
					  
			          if (isCurrentTimeCheck != null) {
			        	  if(isCurrentTimeCheck.booleanValue() == true) 
			        	  (getPropertyName(properties,SamplesTestContant.NUMBERTEXT)).setEditable(false);
			        	  if(isCurrentTimeCheck.booleanValue() == false) 
			        		  (getPropertyName(properties,SamplesTestContant.NUMBERTEXT)).setEditable(true);
			          }
					
				}
	
		});
			
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

}
