package FutureContract;

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
		return new DefaultTextFieldEditorComponent(EntityObject.class) {
			protected String convertElementToString(Object value) {
				String _stringFutCon = null;
				if(value!= null){
				_stringFutCon = ((EntityObject)value).getName() + "/" +
				((EntityObject)value).getName()+ "/" 
				+((EntityObject)value).getName() + "/"
				+((EntityObject)value).getName();
				}
			return 	_stringFutCon;
			}
		};
		
		// TODO Auto-generated method stub
	
	}
	 public void reloadData(){
	    	if(getPopupPanel() != null)
	    		((ContractSelectorPanel)getPopupPanel()).reloadData(_selectedObjType);
	    }
    public void showPopupPanelAsPopup(boolean show) {
        super.showPopupPanelAsPopup(show);
    }
	@Override
	public PopupPanel createPopupComponent() {
		return new ContractSelectorPanel(this,_selectedObjType);
		
	}
	

}
