package apps.window.util.propertyPane.combox;

import apps.window.util.propertyPane.editor.AccountSelectorCellEditor;
import apps.window.util.propertyPane.editor.LESelectionCellEditor;
import apps.window.util.propertyPane.panel.AccountSelectionPropertyPanel;
import apps.window.util.propertyPane.panel.LESelectionPropertyPanel;
import beans.Account;
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class AccountSelectorCombox  extends AbstractComboBox {
	String _selectedObjType = null;
	LegalEntity accountHolder = null;
	/**
	 * @return the accountHolder
	 */
	public LegalEntity getAccountHolder() {
		return accountHolder;
	}
	/**
	 * @param accountHolder the accountHolder to set
	 */
	public void setAccountHolder(LegalEntity accountHolder) {
		this.accountHolder = accountHolder;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	String currency = null;
	AccountSelectorCellEditor  _accountSelectCellEditor = null;
	@Override
	public EditorComponent createEditorComponent() {
		return new DefaultTextFieldEditorComponent(Account.class) {
			protected String convertElementToString(Object value) {
				String _stringFutCon = null;
				if(value!= null){
					if(value instanceof Account)  {
						setAccount((Account)value);
				_stringFutCon = String.valueOf(((Account)value).getAccountName());
					}
				}
				System.out.println(_stringFutCon);
			return 	_stringFutCon;
			}

			
		};
		
		// TODO Auto-generated method stub
	
	}
	public AccountSelectorCombox(String currency,LegalEntity accountHolder) {
		this.currency = currency;
		this.accountHolder = accountHolder;
		setCurrency(currency);
		setAccountHolder(accountHolder);
		setEditable(false);
		initComponent();
		
	}
	private void setAccount(Account value) {
		// TODO Auto-generated method stub
	//	this._
	}
	 public void showPopupPanelAsPopup(boolean show) {
	        super.showPopupPanelAsPopup(show);
	    }
	@Override
	public PopupPanel createPopupComponent() {
		// TODO Auto-generated method stub
		return new AccountSelectionPropertyPanel(this,getCurrency(),getAccountHolder());
	}
	public AccountSelectorCombox(String displayObj){
		_selectedObjType = displayObj;
		setEditable(false);
		initComponent();
	}
	public AccountSelectorCombox(AccountSelectorCellEditor accountSelectCellEditor) {
		// TODO Auto-generated constructor stub
		_accountSelectCellEditor = accountSelectCellEditor;
		_selectedObjType = _accountSelectCellEditor.get_currency();
		accountHolder = _accountSelectCellEditor.getBeneficiary();
		setEditable(false);
		initComponent();
	}
}
