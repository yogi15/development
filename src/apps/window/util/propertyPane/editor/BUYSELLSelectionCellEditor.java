package apps.window.util.propertyPane.editor;
 

import apps.window.util.propertyPane.combox.BUYSELLSelectionPropertyComboxBox; 

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class BUYSELLSelectionCellEditor extends AbstractComboBoxCellEditor {

	
	
	String buysell = "";; 
	/**
	 * @return the date
	 */
	public String getBuySell() {
		return buysell;
	}

	/**
	 * @param date the date to set
	 */
	public void setBuySell(String buysell) {
		this.buysell = buysell;
	}

	@Override
	public AbstractComboBox createAbstractComboBox() {
		// TODO Auto-generated method stub
		return new  BUYSELLSelectionPropertyComboxBox(this);
	}
	
 
}
