package apps.window.util.propertyPane.combox;

import apps.window.util.propertyPane.editor.LESelectionCellEditor;
import apps.window.util.propertyPane.panel.LESelectionPropertyPanel;
import beans.LegalEntity;
 
import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class LESelectionPropertyCombox    extends AbstractComboBox {
	String _selectedObjType = null;
	LegalEntity le = null;
	LESelectionCellEditor _lESelectionCellEditor = null;
	
	  @Override
      protected boolean validateValueForNonEditable(Object value) {
          return value instanceof LegalEntity;
      }

      
	/**
	 * @return the le
	 */
	public LegalEntity getLe() {
		return le;
	}
	/**
	 * @param le the le to set
	 */
	public void setLe(LegalEntity le) {
		this.le = le;
	}
	public LESelectionPropertyCombox(String role){
		_selectedObjType = role;
		
		setEditable(true);
		initComponent();
	}
	public String getRole() {
		if(_lESelectionCellEditor != null) 
			return _lESelectionCellEditor.getRole();
		return _selectedObjType;
	}

	public LESelectionPropertyCombox(LESelectionCellEditor leSelectionCellEditor) {
		// TODO Auto-generated constructor stub
		_lESelectionCellEditor = leSelectionCellEditor;
		_selectedObjType = _lESelectionCellEditor.getRole();
		setLe(_lESelectionCellEditor.get_le());
		setEditable(true);
		initComponent();
	}
	@Override
    public boolean commitEdit() {
		 return super.commitEdit();    
	}
	@Override
	public EditorComponent createEditorComponent() {
		
		return new DefaultTextFieldEditorComponent(LegalEntity.class) {
			
			
			protected String convertElementToString(Object value) {
				
				String _stringFutCon = null;
				if(value!= null){
					if(value instanceof LegalEntity)  {
						setLe((LegalEntity)value);
				_stringFutCon = String.valueOf(((LegalEntity)value).getName());
				if(getLe() != null  && getLe().getRole().equalsIgnoreCase("PO")) 
					return "";
					}
				}
				if(value == null)
					return "";
				System.out.println(_stringFutCon);
			return 	_stringFutCon;
			}
			
		};
		
		
		// TODO Auto-generated method stub
	
	}
	
	
	
	public LegalEntity getSelectLegalEntity() {
		return getLe();
	}
	 public void reloadData(int productID){
	    	if(getPopupPanel() != null)
	    		((LESelectionPropertyPanel)getPopupPanel()).reloadData(_selectedObjType,productID);
	    }
	 public void keyTypeData(String keyTypeD){
	    	if(getPopupPanel() != null)
	    		((LESelectionPropertyPanel)getPopupPanel()).reloadKeyPress(keyTypeD);
	    }
    public void showPopupPanelAsPopup(boolean show) {
        super.showPopupPanelAsPopup(show);
    }
	@Override
	public PopupPanel createPopupComponent() {
		
		return new LESelectionPropertyPanel(this,getRole(),getLe());
		
	}
	
    
}
