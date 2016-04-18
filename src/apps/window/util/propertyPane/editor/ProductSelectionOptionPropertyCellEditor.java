package apps.window.util.propertyPane.editor;

import java.util.Vector;

import apps.window.util.propertyPane.combox.ProductSelectionOptionPropertyComboBox;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class ProductSelectionOptionPropertyCellEditor extends AbstractComboBoxCellEditor {
	
	Vector<String> productType= null;
	/**
	 * @return the conditionalData
	 */
	public Vector<String> getProductTypeData() {
		return productType;
	}

	/**
	 * @param conditionalData the conditionalData to set
	 */
	public void setProductTypeData(Vector<String> productType) {
		this.productType = productType;
	}

  

	public ProductSelectionOptionPropertyCellEditor(Vector<String> sdata) {
		// TODO Auto-generated constructor stub
		this.productType = sdata;
		setProductTypeData(sdata);
	 
		
		 
		customizeAbstractComboBox();
		
	}

	@Override
	public AbstractComboBox createAbstractComboBox() {
		// TODO Auto-generated method stub
		return new ProductSelectionOptionPropertyComboBox(this);
	}
	}

