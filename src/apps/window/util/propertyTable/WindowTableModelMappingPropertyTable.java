package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL;
 

import apps.window.util.propertyUtil.PropertyGenerator;
import beans.WindowTableModelMapping;
import com.jidesoft.grid.Property; 

import constants.WindowSheetConstants;
import constants.WindowTableModelMappingConstants;

public class WindowTableModelMappingPropertyTable extends WindowPropertyTable {
	List<Property> windowtablemodelmappingProperties = null;
	public WindowTableModelMapping windowtablemodelmapping;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public WindowTableModelMappingPropertyTable(String name,
			WindowTableModelMapping windowtablemodelmapping) {
		this.name = name;
		setWindowTableModelMapping(windowtablemodelmapping);
	}

	@Override
	public List<Property> addListenerToProperty(List<Property> properties) {
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			if (property.getName().equalsIgnoreCase(
					WindowTableModelMappingConstants.BEANNAME)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowTableModelMappingConstants.COLUMNDATATYPE)) {
				addListenerToProperty(property, properties);
			}
			 
			if (property.getName().equalsIgnoreCase(
					WindowTableModelMappingConstants.ISSTARTUPDATA)) {
				addListenerToProperty(property, properties);
			}

		}
		return properties;
	}

	// add listener to the property

	private void addListenerToProperty(final Property property,
			final List<Property> properties) {
		if (property.getName().trim()
				.equalsIgnoreCase(WindowTableModelMappingConstants.BEANNAME)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {

							if ((!commonUTIL.isEmpty((String)property.getValue()))  ) { // make sure u check null and empty values.
								if( ((String)property.getValue()).equalsIgnoreCase("NONE")) {
									Property p = propertyTable
											.getPropertyTableModel()
											.getProperty(
													WindowTableModelMappingConstants.METHODNAME);
									p.setEditable(false);
									return;
								} else{ 
								
								 
									Property p = PropertyGenerator
											.getMethodNames(
													property.getValue()
															.toString(),
													WindowTableModelMappingConstants.METHODNAME,
													property.getCategory());
									 

									    Property parentProp = null;
									   List<Property> childProp = (List<Property>) property.getChildren();
									   int index = 0;
									   
									   if(childProp != null) {
											for (int c = 0; c < childProp.size(); c++) {
												if(childProp.get(c) != null) { 
												    
												  
												      index =   property.getChildIndex(childProp.get(c));
												      parentProp =  childProp.get(c);
												}
											}
									   } 
									   property.removeChild(parentProp);
									   property.addChild(index,p );
											propertyTable.getPropertyTableModel().refresh(); 

							}
							}
						}

					});
		}
		if (property
				.getName()
				.trim()
				.equalsIgnoreCase(
						WindowTableModelMappingConstants.COLUMNDATATYPE)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							if (!commonUTIL.isEmpty((String)property.getValue())  
									&& ((String) property.getValue())
											.equalsIgnoreCase("Others")) {
								addNewBeanPropertyToPropertyTable(
										property,
										propertyTable,
										WindowTableModelMappingConstants.CUSTOMCOLUMNNAME,
										properties);
							} else {
								removePropertyFromPropertyList(
										WindowTableModelMappingConstants.CUSTOMCOLUMNNAME,
										propertyTable);
							}
						}
					});

		}
		 
		
		if (property.getName().trim()
				.equalsIgnoreCase(WindowTableModelMappingConstants.ISSTARTUPDATA)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							Boolean IsStartUpData = (Boolean) evt.getNewValue();
							if (IsStartUpData != null) {
								if (IsStartUpData.booleanValue() == true)
									(getPropertyName(
											properties,
											WindowTableModelMappingConstants.STARTUPDATANAME))
											.setEditable(true);
								if (IsStartUpData.booleanValue() == false) {
									(getPropertyName(
											properties,
											WindowTableModelMappingConstants.STARTUPDATANAME))
											.setValue(null);
									(getPropertyName(
											properties,
											WindowTableModelMappingConstants.STARTUPDATANAME))
											.setEditable(false);
								}
							}

						}
					});

		}
	}

	/**
	 * @return the windowtablemodelmapping
	 */
	public WindowTableModelMapping getWindowTableModelMapping() {
		return windowtablemodelmapping;
	}

	/**
	 * @param windowtablemodelmapping
	 *            the windowtablemodelmapping to set
	 */
	public void setWindowTableModelMapping(WindowTableModelMapping bean) {
		this.windowtablemodelmapping = bean;
	}
}
