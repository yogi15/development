package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import util.commonUTIL;

import apps.window.util.propertyUtil.BasePropertyTable;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.PropertyGenerator;
import beans.WindowSheet;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.JavaFileGeneratorConstants;
import constants.WindowSheetConstants;
import constants.WindowTableModelMappingConstants;

public class WindowSheetPropertyTable implements PropertyChangeListener {

	List<Property> windowSheetProperties = null;

	public WindowSheet wSheet;

	/**
	 * @return the propertyTable
	 */
	public PropertyTable getPropertyTable() {
		return propertyTable;
	}

	/**
	 * @param propertyTable
	 *            the propertyTable to set
	 */
	public void setPropertyTable(PropertyTable propertyTable) {
		this.propertyTable = propertyTable;
	}

	public PropertyTable propertyTable = null;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Property property = propertyTable.getSelectedProperty();

	}

	String name = "";

	public WindowSheetPropertyTable(String name, WindowSheet windowSheet) {

		this.name = name;
		setwSheet(windowSheet);

	}

	public PropertyTable getWindowSheetPropertyTable() {

		// windowSheetProperties = getWindowSheetProperties();
		setPropertyTable(BasePropertyTable
				.makePropertyTable(windowSheetProperties));
		return propertyTable;

	}

	public PropertyTable getWindowSheetPropertyTable(List<Property> properties) {

		windowSheetProperties = getWindowSheetProperties(properties);
		setPropertyTable(BasePropertyTable
				.makePropertyTable(windowSheetProperties));
		return propertyTable;

	}

	private List<Property> getWindowSheetProperties(List<Property> properties) {
		// TODO Auto-generated method stub

		// final List< Property> properties =
		// WindowSheetEnumList.WINDOWSHEET.getPropertyList(WindowSheetConstants.WINDOWNAME);
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.ISSTARTUPDATA)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.STARTUPDATANAME)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					JavaFileGeneratorConstants.BEANNAME)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.DESIGNTYPE)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.ISHIERARACHICALWINDOW)) {
				addListenerToProperty(property, properties);
			}

		}

		return properties;
	}

	public void setfillValues() {
		try {
			wSheet = new WindowSheet();
			 
			 List<Property> prop = propertyTable.getPropertyTableModel().getProperties();
				for(int i=0;i<prop.size();i++) {
					Property property  = prop.get(i);
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.DESIGNTYPE)) {
						  wSheet.setDesignType((String) property.getValue());
					 
					}
					if (property.getName()
							.equalsIgnoreCase(WindowSheetConstants.WINDOWNAME) || property.getName()
							.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
						if(property.getName()
						.equalsIgnoreCase(WindowSheetConstants.ATTRIBUTENAME)) {
							 wSheet.setWindowName((String) property.getValue() + "Attributes");
						} else {
							 wSheet.setWindowName((String) property.getValue());
						}
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.FIELDNAME)) {
						  wSheet.setFieldName((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.DATATYPE)) {
						  wSheet.setDataType((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.CATEGORYNAME)) {
						  wSheet.setCategory((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.ISSTARTUPDATA)) {
						if ( (Boolean) property.getValue() == true)
							  wSheet.setIsStartupdata(1);
						else
							 wSheet.setIsStartupdata(0);
						
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.STARTUPDATANAME)) {
						  wSheet.setStartUpDataName((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.CUSTOMPANELNAME)) {
						  wSheet.setCustomPanelName((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.NULLCHECKED)) {
						wSheet.setNullChecked(((Boolean) property.getValue()).booleanValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.BEANNAME)) {
						  wSheet.setBeanName((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.METHODNAME)) {
						  wSheet.setMethodName((String) property.getValue());
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.ISCHILDFIELD)) {
					 
							  wSheet.setChildField(((Boolean) property.getValue()).booleanValue());
					 
					 
					}
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.PARENTFIELDNAME)) {
						  wSheet.setParentFieldName((String) property.getValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.ISHIERARACHICALWINDOW)) {
						  wSheet.setIsHierarachicalWindow(((Boolean) property.getValue()).booleanValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.CHILDWINDOWNAME)) {
						  wSheet.setChildWindowName((String) property.getValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.MAPJAVAOBJECT)) {
						  wSheet.setMapJavaObject(((Boolean) property.getValue()).booleanValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.JAVAOBJECTNAME)) {
						  wSheet.setJavaObjectName((String) property.getValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.ISEDITABLE)) {
						  wSheet.setEditable(((Boolean) property.getValue()).booleanValue());
					} 
					if(property.getName().equalsIgnoreCase(WindowSheetConstants.ISHIDDEN)) {
						  wSheet.setHidden(((Boolean) property.getValue()).booleanValue());
					} 
					
				} 
			 
		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"setfillValues", e);

		}

	}

	public void clearPropertyValues() { 
		try {
			if(propertyTable != null) {
				 
				 
			 for(int i=0;i<propertyTable.getPropertyTableModel().getProperties().size();i++) {
				 Property prop  = (Property ) propertyTable.getPropertyTableModel().getProperties().get(i);
				 if(prop instanceof PropertyEnum ) {
					 PropertyEnum<String> p = ( PropertyEnum<String>) prop;
					p.setValue("");
					
					// p.re
				 } else {
					 
					 if(prop.getValue() instanceof String) {
						 prop.setValue("");
					 } else
					 if(prop.getValue() instanceof Double) {
						 prop.setValue(0.0);
					 }else if(prop.getValue() instanceof Boolean) {
						 prop.setValue(false);
					 }else if(prop.getValue() instanceof Integer) {
						 prop.setValue(0);
					 }
				 }
				  
				
				 
			 }
			
			 
			}

		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"clearPropertyValues", e);
			return;
		}

	}

	private void addListenerToProperty(final Property property,
			final List<Property> properties) {
		 
		 
		 

		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.BEANNAME)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {

							if (property.getValue() != null) {
								String value = (String) property.getValue();
								if(commonUTIL.isEmpty(value))
									return;
								Property p = propertyTable
										.getPropertyTableModel()
										.getProperty(
												WindowSheetConstants.METHODNAME);
								if (p == null
										&& !value.equalsIgnoreCase("NONE")) {

									p = PropertyGenerator
											.getMethodNames(
													property.getValue()
															.toString(),
													WindowTableModelMappingConstants.METHODNAME,
													property.getCategory());
									properties.add(p);
								} else {
									if (!value.equalsIgnoreCase("NONE")) {
										int index = propertyTable
												.getPropertyTableModel()
												.getPropertyIndex(p);
										propertyTable.getPropertyTableModel()
												.getOriginalProperties()
												.remove(index - 1);
										propertyTable.getPropertyTableModel()
												.refresh();
										p = PropertyGenerator
												.getMethodNames(
														property.getValue()
																.toString(),
														WindowTableModelMappingConstants.METHODNAME,
														property.getCategory());
										properties.add(p);
										propertyTable.getPropertyTableModel()
												.refresh();
									} else {
										int index = propertyTable
												.getPropertyTableModel()
												.getPropertyIndex(p);
										propertyTable.getPropertyTableModel()
												.getOriginalProperties()
												.remove(index - 1);
										propertyTable.getPropertyTableModel()
												.refresh();
									}

								}

							}

						}

					});

		}
		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.DESIGNTYPE)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							String name = (String) evt.getNewValue();
							if(commonUTIL.isEmpty(name))
								return;
							Property prop = PropertyGenerator
									.createPropertyFromStartUp(name + "Name",
											name + "Name",
											property.getCategory());
							if (prop.getName().equalsIgnoreCase(
									WindowSheetConstants.WINDOWNAME)) {
								Property p = propertyTable
										.getPropertyTableModel().getProperty(
												WindowSheetConstants.FIELDNAME);

								Property propfield = PropertyGenerator
										.getStringProperty(
												WindowSheetConstants.FIELDNAME,
												WindowSheetConstants.FIELDNAME,
												property.getCategory());
								if (propfield != null) {
									int index = propertyTable
											.getPropertyTableModel()
											.getPropertyIndex(p);

									propertyTable.getPropertyTableModel()
											.getOriginalProperties()
											.remove(index - 1);
									propertyTable.getPropertyTableModel()
											.refresh();

									properties.add(index - 1, propfield);
									propertyTable.getPropertyTableModel()
											.refresh();
								}
							}

							if (prop != null) {

								propertyTable.getPropertyTableModel()
										.getOriginalProperties().remove(1);
								propertyTable.getPropertyTableModel().refresh();

								prop.addPropertyChangeListener(
										Property.PROPERTY_VALUE,
										new PropertyChangeListener() {
											public void propertyChange(
													PropertyChangeEvent evt) {
												Property attributeTypeName = (Property) evt
														.getSource();
												if (attributeTypeName
														.getName()
														.equalsIgnoreCase(
																WindowSheetConstants.ATTRIBUTENAME)) {
													Property p = propertyTable
															.getPropertyTableModel()
															.getProperty(
																	WindowSheetConstants.FIELDNAME);

													Property propfield = PropertyGenerator
															.createPropertyFromStartUp(
																	WindowSheetConstants.FIELDNAME,
																	attributeTypeName
																			.getValue()
																			+ "Attributes",
																	property.getCategory());
													if (propfield != null) {
														int index = propertyTable
																.getPropertyTableModel()
																.getPropertyIndex(
																		p);

														propertyTable
																.getPropertyTableModel()
																.getOriginalProperties()
																.remove(index - 1);
														propertyTable
																.getPropertyTableModel()
																.refresh();

														properties.add(
																index - 1,
																propfield);
														propertyTable
																.getPropertyTableModel()
																.refresh();
													}

												} else if (attributeTypeName
														.getName()
														.equalsIgnoreCase(
																WindowSheetConstants.WINDOWNAME)) {
													Property p = propertyTable
															.getPropertyTableModel()
															.getProperty(
																	WindowSheetConstants.FIELDNAME);

													Property propfield = PropertyGenerator
															.getStringProperty(
																	WindowSheetConstants.FIELDNAME,
																	WindowSheetConstants.FIELDNAME,
																	property.getCategory());
													if (propfield != null) {
														int index = propertyTable
																.getPropertyTableModel()
																.getPropertyIndex(
																		p);

														propertyTable
																.getPropertyTableModel()
																.getOriginalProperties()
																.remove(index - 1);
														propertyTable
																.getPropertyTableModel()
																.refresh();

														properties.add(
																index - 1,
																propfield);
														propertyTable
																.getPropertyTableModel()
																.refresh();
													}
												}
											}

										});
								properties.add(1, prop);

							}
						}

					});
		}

	}

	private Property getPropertyName(List<Property> properties, String name) {
		Property property = null;
		for (int i = 0; i < properties.size(); i++) {
			property = properties.get(i);
			if (property.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return property;
	}

	/**
	 * @return the wSheet
	 */
	public WindowSheet getwSheet() {
		return wSheet;
	}

	/**
	 * @param wSheet
	 *            the wSheet to set
	 */
	public void setwSheet(WindowSheet window) {
		this.wSheet = window;
	}
	Vector<String> fieldNames = new Vector<String>();
	/**
	 * @return the fieldNames
	 */
	public Vector<String> getFieldNames() {
		return fieldNames;
	}

	public void addFieldName( String fieldName) {
		 
		fieldNames.addElement(fieldName);
		
		
	}

	public void setPropertiesValues(WindowSheet firstRecord) {
		// TODO Auto-generated method stub
		propertyTable.clearSelection();
		String propertyName = "";
		Object obj = null;
		 
				propertyTable.clearSelection();
				List<Property> prop = propertyTable.getPropertyTableModel()
						.getProperties();
				for (int i = 0; i < prop.size(); i++) {
					
						if(prop.get(i) != null && !prop.get(i).isCategoryRow()) { 
							propertyName = prop.get(i).getName();
							 if(!prop.get(i).hasChildren()) {
								  obj = firstRecord.getPropertyValue(prop.get(i).getName()) ;
								if(obj != null){
									Property p = (Property)	propertyTable.getPropertyTableModel().getProperty(propertyName);
										if(p != null && p.getName().equalsIgnoreCase(prop.get(i).getName())) {
												int propertyIndex = 	propertyTable.getPropertyTableModel().getPropertyIndex(p); 
												propertyTable.setValueAt(obj,propertyIndex  , 1);
											} 
								}
						} else {
							obj = firstRecord.getPropertyValue(prop.get(i).getName()) ; 
							Property cp = (Property)	propertyTable.getPropertyTableModel().getProperty(propertyName);
							 if(cp != null){ 
									Property cpI  = (Property)	propertyTable.getPropertyTableModel().getProperty(propertyName);
									if(cpI  != null && cpI .getName().equalsIgnoreCase(prop.get(i).getName())) {
										int index = 	propertyTable.getPropertyTableModel().getPropertyIndex(cpI ); 
										propertyTable.setValueAt(obj,index  , 1);
									}
							 }
							List<Property> childPs = (List<Property>) prop.get(i).getChildren();
							 Object child = null;
							for (int c = 0; c < childPs.size(); c++) {
									if(childPs.get(c) != null) { 
									     String childPropName = childPs.get(c).getName();
									     child = firstRecord.getPropertyValue(childPs.get(c).getName()) ;
										 if(child != null){ 
												Property cprop = (Property)	propertyTable.getPropertyTableModel().getProperty(prop.get(i).getName());
												List<Property> childcs =(List<Property>) prop.get(i).getChildren();
												for (int cs = 0; cs < childcs.size(); cs++) { 
													if(childcs.get(cs) != null && childcs.get(cs).getName().equalsIgnoreCase(childPs.get(c).getName())) {
													 	childcs.get(cs).setValue(child); 
													}
												}
										 }
									
									}
									child = null;
							}
							 
							
						}
					}
						obj = null;
				}
		
		setwSheet(firstRecord);
	}

}