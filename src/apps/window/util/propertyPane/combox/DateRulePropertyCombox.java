package apps.window.util.propertyPane.combox;


import apps.window.util.propertyPane.panel.DateRulePropertyPanel;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class DateRulePropertyCombox extends AbstractComboBox {

	 public DateRulePropertyCombox() {
	        initComponent();
	    }
	 
	    public PopupPanel createPopupComponent() {
	        return new DateRulePropertyPanel(this);
	    }

	    public EditorComponent createEditorComponent() {
	        return new DefaultTextFieldEditorComponent(String.class);
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

