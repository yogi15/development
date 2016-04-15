package apps.window.util.propertyPane.combox;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JTextField;

import apps.window.util.propertyPane.editor.SearchPropertyEditor;
import apps.window.util.propertyPane.panel.DateRulePropertyPanel;
import apps.window.util.propertyPane.panel.SearchPropertyPanel;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class SearchPropertyComboxBox extends AbstractComboBox {
	SearchPropertyEditor editor;
	String searchType = "";
	 public SearchPropertyComboxBox(SearchPropertyEditor searchPropertyEditor) {
		  super(AbstractComboBox.DROPDOWN);
		 
		 setEditable(true);
	        initComponent();
	        this.editor = searchPropertyEditor;
	        setSearchType(searchPropertyEditor.getSearchType());
	    }
	 
	    public void setSearchType(String searchType) {
		// TODO Auto-generated method stub
	    	this.searchType = searchType;
	    	
		
	}
	    public String getSearchType( ) {
			// TODO Auto-generated method stub
		    	return editor.getSearchType();
		    	
			
		}
	    @Override
	    public boolean commitEdit() {
			 return super.commitEdit();    
		}
		public PopupPanel createPopupComponent() {
	        return new SearchPropertyPanel(this);
	    }
		 @Override
	    public EditorComponent createEditorComponent() {
	    	return new DefaultTextFieldEditorComponent(String.class) {

				protected String convertElementToString(Object value) {

					String _stringFutCon = null;
					if (value != null) {
						if (value instanceof String) {
							_stringFutCon = (String) value;
							//setConditionvalue(_stringFutCon);
						}
					}
					if (value == null)
						return "";
				 
					return _stringFutCon;
				}
         
			};
             
	    }
	    
	    public void showPopupPanelAsPopup(boolean show) {
	        super.showPopupPanelAsPopup(show);
	    }
	    
	    public void hidePopup(){
	    	setPopupVisible(false);
	    }
	    
	    public void setSelectedItem(Object val) {
	    	super.setSelectedItem(val);
	    }
}