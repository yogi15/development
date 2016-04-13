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
		 // super(AbstractComboBox.REVERT);
		 
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

	    public EditorComponent createEditorComponent() {
            AbstractComboBox.DefaultTextFieldEditorComponent comp  = 
                    new AbstractComboBox.DefaultTextFieldEditorComponent(String.class){
                    public Component getEditorComponent(){
                        JTextField f = (JTextField)super.getEditorComponent();
                        f.setEditable(true);
                        return f;
                    }
                }
                ;
                return comp;
            
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