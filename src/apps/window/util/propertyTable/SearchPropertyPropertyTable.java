package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.SearchProperty;
import com.jidesoft.grid.Property;

public class SearchPropertyPropertyTable extends WindowPropertyTable {
	List<Property> searchpropertyProperties = null;
	public SearchProperty searchproperty;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public SearchPropertyPropertyTable(String name,
			SearchProperty searchproperty) {
		this.name = name;
		setSearchProperty(searchproperty);
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
