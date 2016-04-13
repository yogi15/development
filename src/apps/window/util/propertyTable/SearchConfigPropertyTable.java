package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import apps.window.util.propertyUtil.AttributeProperty;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.PropertyGenerator;
import apps.window.util.propertyUtil.PropertyListMultipleSelection;
import apps.window.util.propertyUtil.Selection;
import beans.AttributeContainer;
import beans.SearchConfig;
import com.jidesoft.grid.Property;

import constants.SearchConfigConstants;
import constants.WindowTableModelMappingConstants;

public class SearchConfigPropertyTable extends WindowPropertyTable {
	List<Property> searchconfigProperties = null;
	public SearchConfig searchconfig;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public SearchConfigPropertyTable(String name, SearchConfig searchconfig) {
		this.name = name;
		setSearchConfig(searchconfig);
	}

	@Override
	public List<Property> addListenerToProperty(List<Property> properties) {
		searchconfigProperties = properties;
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			if (property.getName().equalsIgnoreCase(
					SearchConfigConstants.SEARCHATTRIBUTES)  ) {
				addListenerToProperty(property, searchconfigProperties);
			}
			if (property.getName().equalsIgnoreCase(
					SearchConfigConstants.BEANNAME)  ) {
				addListenerToProperty(property, properties);
			}
			 

		}
		return properties;
	}

	// add listener to the property
	public void addListenerToProperty(final Property property,
			final List<Property> properties) {
		if (property.getName().trim()
				.equalsIgnoreCase(SearchConfigConstants.SEARCHATTRIBUTES)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							if(evt.getNewValue() != null) {
								String searchType = (String) evt.getNewValue();
								 
							
								AttributeProperty  p = (AttributeProperty) propertyTable
										.getPropertyTableModel()
										.getProperty(
												SearchConfigConstants.ATTRIBUTES);
							
								
								AttributeProperty attributes = 	(AttributeProperty) PropertyGenerator.getAttributeProperty(searchType);
								p.setName(searchType);
								p.setName(searchType);
								 
							    AttributeContainer attBeans = attributes.getValue();
							    attBeans.setAttributeName(searchType);
							     p.setValue(attBeans);
							    							 
							 
								 
							}
						}
		
					});
		}
			if (property.getName().trim()
					.equalsIgnoreCase(SearchConfigConstants.BEANNAME)) {
				property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent evt) {
								if(evt.getNewValue() != null) {
									String beanName = (String) evt.getNewValue();
									PropertyListMultipleSelection  ps = (PropertyListMultipleSelection) propertyTable
											.getPropertyTableModel()
											.getProperty(
													SearchConfigConstants.COLUMNNAMES);
									PropertyListMultipleSelection  newProp =      PropertyGenerator.getMultiColumnMethodNames(beanName, SearchConfigConstants.COLUMNNAMES, property.getCategory());
								    		int index = propertyTable.getPropertyTableModel().getPropertyIndex(ps)	;	
								    		
								           removePropertyFromPropertyList(SearchConfigConstants.COLUMNNAMES, propertyTable);
								      // 	properties.add(newPropertyp);
								           propertyTable.getPropertyTableModel().refresh();
								           properties.add(index,newProp);
								           propertyTable.getPropertyTableModel().refresh();
								       
									 
								}
							}
			
						});
			}
	
		
	}

	/**
	 * @return the searchconfig
	 */
	public SearchConfig getSearchConfig() {
		return searchconfig;
	}

	/**
	 * @param searchconfig
	 *            the searchconfig to set
	 */
	public void setSearchConfig(SearchConfig bean) {
		this.searchconfig = bean;
	}
}
