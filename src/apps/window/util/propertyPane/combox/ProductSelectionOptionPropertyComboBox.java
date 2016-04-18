package apps.window.util.propertyPane.combox;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import apps.window.util.propertyPane.editor.ProductSelectionOptionPropertyCellEditor;
import apps.window.util.propertyPane.panel.ProductSelectionOptionPropertyPanel;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;

public class ProductSelectionOptionPropertyComboBox  extends AbstractComboBox {
	ProductSelectionOptionPropertyCellEditor editor=null;
	String productType = "";
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	Vector<String> productTypeData = null;
	/**
	 * @return the conditionalData
	 */
	public Vector<String> getProductTypeData() {
		return productTypeData;
	}

	/**
	 * @param conditionalData the conditionalData to set
	 */
	public void setProductTypeData(Vector<String> productTypeData) {
		this.productTypeData = productTypeData;
	}

	/**
	 * @return the windowName
	 */
	 
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

	 

	 
	@Override
	public boolean commitEdit() {
		return super.commitEdit();
	}
	@Override
	protected boolean validateValueForNonEditable(Object value) {
		return value instanceof String;
	}
	 public ProductSelectionOptionPropertyComboBox(Vector<String> productTypeData) {
		 
		 setProductTypeData(productTypeData);
		 setEditable(false);
		 
	        initComponent();
	         addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					hidePopup();
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					showPopup();
					
				}
			});
		 
	 }
	 public ProductSelectionOptionPropertyComboBox(ProductSelectionOptionPropertyCellEditor productSelectionOptionPropertyEditor) {
		 //super(AbstractComboBox.DROPDOWN);
		 editor = productSelectionOptionPropertyEditor;
		 
		 setEditable(false);
		 
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
							setProductType(_stringFutCon);
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
	        return new ProductSelectionOptionPropertyPanel(this,editor,getProductTypeData());
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