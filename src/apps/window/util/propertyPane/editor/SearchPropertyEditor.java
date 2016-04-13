package apps.window.util.propertyPane.editor;
 
import apps.window.util.propertyPane.combox.SearchPropertyComboxBox;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class SearchPropertyEditor  extends AbstractComboBoxCellEditor {
	
	
	String searchType;
	

	public SearchPropertyEditor(String searchType) {
		// TODO Auto-generated constructor stub
		this.searchType=searchType;
		customizeAbstractComboBox();
	}


	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}


	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	@Override
	public AbstractComboBox createAbstractComboBox() {
		// TODO Auto-generated method stub
		return new SearchPropertyComboxBox(this);
	}

}
