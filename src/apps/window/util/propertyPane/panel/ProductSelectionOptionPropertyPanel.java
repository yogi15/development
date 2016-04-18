package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import util.commonUTIL;

import apps.window.util.propertyDialog.ProductSelectionOptionPropertyDialog;
import apps.window.util.propertyPane.combox.ProductSelectionOptionPropertyComboBox;
import apps.window.util.propertyPane.editor.ProductSelectionOptionPropertyCellEditor;
import apps.window.util.windowUtil.Frame12;

import com.jidesoft.combobox.PopupPanel;

public class ProductSelectionOptionPropertyPanel extends PopupPanel {
	ProductSelectionOptionPropertyCellEditor editor;
	Vector<String> productTypeData = null;

	public ProductSelectionOptionPropertyPanel(
			ProductSelectionOptionPropertyComboBox comboBox,
			ProductSelectionOptionPropertyCellEditor editor,
			Vector<String> productTypeData) {
		_comboBox = comboBox;
		this.editor = editor;
		this.productTypeData = productTypeData;
		initComponent();
		 
		setTitle("ProductSelectionOptionProperty");
	}

	private ProductSelectionOptionPropertyDialog _dialog = null;
	public ProductSelectionOptionPropertyComboBox _comboBox = null;

	protected void initComponent() {
		setLayout(new BorderLayout());
		add(productSelectionOptionPropertyPanel(), BorderLayout.CENTER);
		setRequestFocusEnabled(true);
		setFocusable(true);
		_comboBox.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println("I am Losting focus");
				 
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println("I am gaining focus");
			}
		});
		 
			
		 
	 
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		if(commonUTIL.isEmpty(value))
			return;
		if(!commonUTIL.isEmpty(value)) {
		this.value = value;
		
		
		}
	}

	String value = null;

	private JComponent productSelectionOptionPropertyPanel() {
		JPanel returnPanel = null;

		if (_dialog == null) {
			_dialog = new ProductSelectionOptionPropertyDialog(
					Frame12.getFrame(), true, productTypeData,this);

			returnPanel = _dialog.getMainPanel();
			
			 
			return returnPanel;
		}
		return returnPanel;
	}

}
