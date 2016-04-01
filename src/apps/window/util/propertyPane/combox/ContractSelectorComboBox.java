package apps.window.util.propertyPane.combox;

import apps.window.util.propertyPane.panel.ContractSelectorPanel;

import beans.Product;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;

public class ContractSelectorComboBox extends AbstractComboBox{
	String _selectedObjType = null;

	
	public ContractSelectorComboBox(String displayObj){
		_selectedObjType = displayObj;
		setEditable(false);
		initComponent();
	}
	@Override
	public EditorComponent createEditorComponent() {
		return new DefaultTextFieldEditorComponent(Product.class) {
			protected String convertElementToString(Object value) {
				String _stringFutCon = null;
				if(value!= null){
				_stringFutCon = ((Product)value).getProductname();
				}
			return 	_stringFutCon;
			}
		};
		
		// TODO Auto-generated method stub
	
	}
	 public void reloadData(int productID){
	    	if(getPopupPanel() != null)
	    		((ContractSelectorPanel)getPopupPanel()).reloadData(_selectedObjType,productID);
	    }
    public void showPopupPanelAsPopup(boolean show) {
        super.showPopupPanelAsPopup(show);
    }
	@Override
	public PopupPanel createPopupComponent() {
		return new ContractSelectorPanel(this,_selectedObjType);
		
	}
	

}
