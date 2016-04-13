package apps.window.util.propertyPane.combox;

import java.util.Vector;

import apps.window.util.propertyPane.editor.ConditionalPropertyEditor;
import apps.window.util.propertyPane.panel.ConditionalPropertyPanel;
import apps.window.util.propertyPane.panel.DateRulePropertyPanel;
import beans.Book;
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.combobox.AbstractComboBox.DefaultTextFieldEditorComponent;
import com.jidesoft.combobox.AbstractComboBox.EditorComponent;

public class ConditionalPropertyCombox extends AbstractComboBox {
	ConditionalPropertyEditor editor=null;
	Vector<String> conditionalData = null;
	/**
	 * @return the conditionalData
	 */
	public Vector<String> getConditionalData() {
		return conditionalData;
	}

	/**
	 * @param conditionalData the conditionalData to set
	 */
	public void setConditionalData(Vector<String> conditionalData) {
		this.conditionalData = conditionalData;
	}

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the designType
	 */
	public String getDesignType() {
		return designType;
	}

	/**
	 * @param designType the designType to set
	 */
	public void setDesignType(String designType) {
		this.designType = designType;
	}
	String _selectedObjType = null;
	/**
	 * @return the _selectedObjType
	 */
	public String get_selectedObjType() {
		return _selectedObjType;
	}

	/**
	 * @param _selectedObjType the _selectedObjType to set
	 */
	public void set_selectedObjType(String _selectedObjType) {
		this._selectedObjType = _selectedObjType;
	}

	/**
	 * @return the conditionvalue
	 */
	public String getConditionvalue() {
		return conditionvalue;
	}

	/**
	 * @param conditionvalue the conditionvalue to set
	 */
	public void setConditionvalue(String conditionvalue) {
		this.conditionvalue = conditionvalue;
	}
	String  conditionvalue = null;
	@Override
	public boolean commitEdit() {
		return super.commitEdit();
	}
	@Override
	protected boolean validateValueForNonEditable(Object value) {
		return value instanceof String;
	}
	String windowName = null;
	String designType = null;
	String propertyName = null;
	 public ConditionalPropertyCombox(ConditionalPropertyEditor conditionalPropertyEditor) {
		 editor = conditionalPropertyEditor;
		 conditionalData = editor.getConditionalData();
		 windowName = editor.getWindowName();
		 designType = editor.getDesignType();
		 propertyName = editor.getPropertyName();
		 setEditable(true);
		 
		 	        initComponent();
	    }
	 @Override
		public EditorComponent createEditorComponent() {

			return new DefaultTextFieldEditorComponent(String.class) {

				protected String convertElementToString(Object value) {

					String _stringFutCon = null;
					if (value != null) {
						if (value instanceof String) {
							_stringFutCon = (String) value;
							setConditionvalue(_stringFutCon);
						}
					}
					if (value == null)
						return "";
				 
					return _stringFutCon;
				}

			};

			// TODO Auto-generated method stub

		}
	    public PopupPanel createPopupComponent() {
	        return new ConditionalPropertyPanel(this,editor);
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