package apps.window.util.propertyPane.combox;

import util.commonUTIL;
import apps.window.util.propertyPane.editor.LastTradeTimePropertyEditor;
import apps.window.util.propertyPane.panel.LastTradeTimePropertyPane1;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class LastTradeTimePropertyComboxBox  extends AbstractComboBox {

	private LastTradeTimePropertyEditor _editor;
	
	public LastTradeTimePropertyComboxBox(LastTradeTimePropertyEditor editor) {
		_editor = editor;
		initComponent();
	}
	public PopupPanel createPopupComponent() {
		return new LastTradeTimePropertyPane1(_editor);
	}
	 public EditorComponent createEditorComponent() {
	    	return new DefaultTextFieldEditorComponent(String.class){
	        	protected String convertElementToString(Object value){
	        		String _lastTradedTimeinMinutes = null;
	           		if(!commonUTIL.isEmpty((String)value)){
	        			int index = ((String)value).lastIndexOf(':');
	        			String _hours = ((String)value).substring(0, index);
	        			String _min = ((String)value).substring(index+1);
	       				_lastTradedTimeinMinutes =  _hours +":" +_min;
	        		}
	        		else{
	        			_lastTradedTimeinMinutes = "00:00";
	        		}
	        		return _lastTradedTimeinMinutes;
	        	}
	        };
	        
	    }
	    

	    public void showPopupPanelAsPopup(boolean show) {
	        super.showPopupPanelAsPopup(show);
	    }
	    
	    public void hidePopup(){
	    	setPopupVisible(false);
	    }

	
}
