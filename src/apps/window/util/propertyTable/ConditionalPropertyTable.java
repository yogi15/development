package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.util.List;

import apps.window.util.property.FutureContractProperty;
import beans.SearchProperty;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

public class ConditionalPropertyTable extends WindowPropertyTable {
	List<Property> searchpropertyProperties = null;
	public SearchProperty searchproperty;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public ConditionalPropertyTable( ) {
		//this.name = name;
		 
	}
	 public PropertyTable getConditionalPropertyTable(List<Property> Properties) {
	       
		  
		 propertyTable = new PropertyTable();

		PropertyTableModel<Property> model = new PropertyTableModel<Property>(
				Properties);
		model.setMiscCategoryName(" ");
		// model.addPropertyChangeListener(listener);
		propertyTable.setModel(model);
		propertyTable.getColumnModel().getColumn(0).setMinWidth(100);
		propertyTable.expandAll();
		propertyTable.addPropertyChangeListener(this);
		//propertyTable.addPropertyChangeListener("Exchange", this);
		return propertyTable;
    }
	@Override
	public List<Property> addListenerToProperty(List<Property> properties) {
		return properties;
	}

	// add listener to the property
	public void addListenerToProperty(final Property property,
			final List<Property> properties) {
	}

	/**
	 * @return the searchproperty
	 */
	public SearchProperty getSearchProperty() {
		return searchproperty;
	}

	/**
	 * @param searchproperty
	 *            the searchproperty to set
	 */
	public void setSearchProperty(SearchProperty bean) {
		this.searchproperty = bean;
	}
}
