package apps.window.util.propertyPane.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import apps.window.util.propertyPane.combox.ContractSelectorComboBox;
import apps.window.util.propertyPane.combox.DateRulePropertyCombox;
import apps.window.util.propertyPane.combox.LESelectionPropertyCombox;
import apps.window.util.propertyPane.combox.LastTradeTimePropertyComboxBox;
import beans.LegalEntity;
import beans.Product;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class LESelectionCellEditor extends AbstractComboBoxCellEditor {

	
	
private String _role = "";
private  LegalEntity _le = null;
	/**
 * @return the _le
 */
public LegalEntity get_le() {
	return _le;
}

/**
 * @param _le the _le to set
 */
public void set_le(LegalEntity _le) {
	this._le = _le;
}

	public LESelectionCellEditor(LegalEntity le,String role){
		_role = role;
		_le = le;
		customizeAbstractComboBox();
	}
	
	public String getRole(){
		return _role;
	}
	
	public AbstractComboBox createAbstractComboBox() {
		return new  LESelectionPropertyCombox(this);
	}

	

	 
}
