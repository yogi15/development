package apps.window.util.propertyPane.editor;

import apps.window.util.propertyPane.combox.AccountSelectorCombox;
import apps.window.util.propertyPane.combox.LESelectionPropertyCombox;
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class AccountSelectorCellEditor extends AbstractComboBoxCellEditor {

	
	
/**
	 * @return the _currency
	 */
	public String get_currency() {
		return _currency;
	}


	/**
	 * @param _currency the _currency to set
	 */
	public void set_currency(String _currency) {
		this._currency = _currency;
	}


	/**
	 * @return the beneficiary
	 */
	public LegalEntity getBeneficiary() {
		return beneficiary;
	}


	/**
	 * @param beneficiary the beneficiary to set
	 */
	public void setBeneficiary(LegalEntity beneficiary) {
		this.beneficiary = beneficiary;
	}


private String _currency = "";
private LegalEntity beneficiary = null;

	
	public AccountSelectorCellEditor(String currency,LegalEntity accountHolder){
		_currency = currency;
		beneficiary = accountHolder;
		customizeAbstractComboBox();
	}
	
	
	public AbstractComboBox createAbstractComboBox() {
		return new  AccountSelectorCombox(this);
	
	}

}
