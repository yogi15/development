package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Properties;

import util.CosmosException;
import util.commonUTIL;

import apps.window.util.propertyUtil.BasePropertyTable;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.PropertyGenerator;
import beans.BaseBean;
import beans.JavaFileGenerator;
import beans.Template;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.WindowTableModelMappingConstants;

public abstract class WindowPropertyTable implements PropertyChangeListener {

	public PropertyTable propertyTable = null;
	String name = "";

	/**
	 * @return the bean
	 */
	public BaseBean getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(BaseBean bean) {
		this.bean = bean;

	}

	BaseBean bean = null;

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

	public abstract List<Property> addListenerToProperty(
			List<Property> properties);

	public PropertyTable getPropertyTable(List<Property> properties) {

		setPropertyTable(BasePropertyTable
				.makePropertyTable(addListenerToProperty(properties)));
		return propertyTable;

	}

	public void setfillValues(BaseBean bean) {
		try {
			try {

				List<Property> prop = propertyTable.getPropertyTableModel()
						.getProperties();
				for (int i = 0; i < prop.size(); i++) {
					Property property = prop.get(i);
					if (property.getValue() != null)
						bean.setPropertyValue(property.getName(),
								property.getValue());
				}
				setBean(bean);

			} catch (Exception e) {
				commonUTIL.displayError("JavaFileGeneratorPropertyTable",
						"setfillValues", e);

			}

		} catch (Exception e) {
			commonUTIL.displayError(name, "setfillValues", e);

		}
	}

	public void setPropertiesValues(BaseBean firstRecord) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String propertyName = "";
		Object obj = null;
		try {
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
				setBean(firstRecord);
		}catch( Exception e) {
			commonUTIL.displayError("WindowPropertyTable", "setPropertiesValues "+ getBean().getClass().getName() + " values on property "+ propertyName + " error "+obj, e);
		}
		
		
		

	}

	// helper method
	public Property getPropertyName(List<Property> properties, String name) {
		Property property = null;
		for (int i = 0; i < properties.size(); i++) {
			property = properties.get(i);
			if (property.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return property;
	}

	// helper method

	public void addNewBeanPropertyToPropertyTable(final Property p,
			final PropertyTable propertyTab, final String newPropertyName,
			final List<Property> properties) {

		if (p.getValue() != null) {
			Property newPropertyp = propertyTab.getPropertyTableModel()
					.getProperty(newPropertyName);
			if (newPropertyp == null) {

				newPropertyp = PropertyGenerator.getBeanNames(newPropertyName,
						newPropertyName, p.getCategory());
				properties.add(newPropertyp);

			} else {
				int index = propertyTab.getPropertyTableModel()
						.getPropertyIndex(newPropertyp);
				propertyTab.getPropertyTableModel().getOriginalProperties()
						.remove(index - 1);
				propertyTab.getPropertyTableModel().refresh();
				newPropertyp = PropertyGenerator.getBeanNames(newPropertyName,
						newPropertyName, p.getCategory());
				properties.add(newPropertyp);
				propertyTab.getPropertyTableModel().refresh();

			}
		}

	}

	public void clearPropertyValues() {
		try {
			if (propertyTable == null)
				return;
			if (propertyTable != null) {
				propertyTable.clearSelection();

				for (int i = 0; i < propertyTable.getPropertyTableModel()
						.getProperties().size(); i++) {
					Property prop = (Property) propertyTable
							.getPropertyTableModel().getProperties().get(i);
					if (prop != null) {
						if (prop instanceof PropertyEnum) {
							PropertyEnum<String> p = (PropertyEnum<String>) prop;
							p.setValue("");

							// p.re
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

			}

		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"clearPropertyValues", e);
			return;
		}

	}

	public void removePropertyFromPropertyList(String propertyName,
			final PropertyTable propertyTab) {
		Property newPropertyp = propertyTable.getPropertyTableModel()
				.getProperty(propertyName);
		if (newPropertyp != null) {
			int index = propertyTab.getPropertyTableModel().getPropertyIndex(
					newPropertyp);
			propertyTab.getPropertyTableModel().getOriginalProperties()
					.remove(index - 1);
			propertyTab.getPropertyTableModel().refresh();

		}
	}

	public void addNewMethodPropertyToPropertyTable(final Property p,
			final PropertyTable propertyTab, final String newPropertyName,
			final List<Property> properties) {

		if (p.getValue() != null) {
			Property newPropertyp = propertyTab.getPropertyTableModel()
					.getProperty(newPropertyName);
			if (newPropertyp == null) {

				newPropertyp = PropertyGenerator.getMethodNames(
						newPropertyName, newPropertyName, p.getCategory());
				properties.add(p);

			} else {
				int index = propertyTab.getPropertyTableModel()
						.getPropertyIndex(p);
				propertyTab.getPropertyTableModel().getOriginalProperties()
						.remove(index - 1);
				propertyTab.getPropertyTableModel().refresh();
				newPropertyp = PropertyGenerator.getMethodNames(
						newPropertyName, newPropertyName, p.getCategory());
				properties.add(p);
				propertyTab.getPropertyTableModel().refresh();

			}
		}

	}

	protected void setCategoryHidden(PropertyTable propertyTable,
			String comboxpropertiesname) {
		// TODO Auto-generated method stub
		List<Property> childs = null;
		List<Property> props = getProperties(propertyTable);
		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++) {
				if (props.get(i).isCategoryRow()) {
					if (props.get(i).getName()
							.equalsIgnoreCase(comboxpropertiesname)) {

						getAllPropertiesForCategory(propertyTable,
								comboxpropertiesname, childs);
						setALLPropertyHidden(childs);
						props.get(i).setExpandable(false);
					}
				}
			}
		}
	}

	protected void setALLPropertyHidden(List<Property> props) {
		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++)
				props.get(i).setHidden(true);
		}
	}

	protected void setCategoryEnable(PropertyTable propertyTable,
			String comboxpropertiesname) {
		// TODO Auto-generated method stub
		List<Property> props = getProperties(propertyTable);
		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++) {
				if (props.get(i).isCategoryRow()) {
					if (props.get(i).getName()
							.equalsIgnoreCase(comboxpropertiesname)) {
						props.get(i).setExpandable(true);

					}
				}
			}
		}
	}

	protected void getAllPropertiesForCategory(PropertyTable propertyTable,
			String comboxpropertiesname, List<Property> categProps) {
		// TODO Auto-generated method stub
		List<Property> props = getProperties(propertyTable);

		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++) {
				if (props.get(i).isCategoryRow()) {
					if (props.get(i).getName()
							.equalsIgnoreCase(comboxpropertiesname)) {
						categProps = (List<Property>) props.get(i)
								.getChildren();
					}
				}
			}
		}
	}

	protected void setCategoryDisable(PropertyTable propertyTable,
			String comboxpropertiesname) {
		// TODO Auto-generated method stub
		List<Property> childs = null;
		List<Property> props = getProperties(propertyTable);
		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++) {
				if (props.get(i).isCategoryRow()) {
					if (props.get(i).getName()
							.equalsIgnoreCase(comboxpropertiesname)) {
						getAllPropertiesForCategory(propertyTable,
								comboxpropertiesname, childs);
						setALLPropertyHidden(childs);
						props.get(i).setExpandable(false);
					}
				}
			}
		}
	}

	protected void setCategoryVisiable(PropertyTable propertyTable,
			String comboxpropertiesname) {
		// TODO Auto-generated method stub
		List<Property> props = getProperties(propertyTable);
		if (!commonUTIL.isEmpty(props)) {
			for (int i = 0; i < props.size(); i++) {
				if (props.get(i).isCategoryRow()) {

					if (props.get(i).getName().toString()
							.equalsIgnoreCase(comboxpropertiesname)) {
						props.get(i).setExpandable(true);
						break;
					}
				}
			}
		}
	}

	private List<Property> getProperties(PropertyTable propertyTable) {
		return propertyTable.getPropertyTableModel().getProperties();

	}
}
