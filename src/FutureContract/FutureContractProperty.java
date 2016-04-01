package FutureContract;

import java.util.Collection;

import javax.swing.AbstractCellEditor;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


import apps.productwindow.util.AutoCompleteCellEditor;
import apps.productwindow.util.DateRulePropertyE;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.AbstractComboBoxCellEditor;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;
import com.jidesoft.swing.AutoCompletionComboBox;

public class FutureContractProperty extends Property {

	private Object setValue = null;

	public FutureContractProperty() {

	}

	public FutureContractProperty(String name, String description, Class type,
			String category, ConverterContext context,
			java.util.List childProperties) {
		super(name, description, type, category, context, childProperties);
	}

	public FutureContractProperty(String name, String description, Class type,
			String category, ConverterContext context) {
		super(name, description, type, category, context);
	}

	public FutureContractProperty(String name, String description, Class type,
			String category) {
		super(name, description, type, category);
	}

	public FutureContractProperty(String name, String description, Class type) {
		super(name, description, type);
	}

	public FutureContractProperty(String name, String description) {
		super(name, description);
	}

	public FutureContractProperty(String name) {
		super(name);
	}

	public void setValue(Object value, boolean fireEvent) {
		Object oldValue = setValue;
		setValue = value;
		if (oldValue != setValue && fireEvent) {
			firePropertyChange(PROPERTY_VALUE, oldValue, value);
		}
	}

	@Override
	public void setValue(Object value) {
		setValue(value, true);
	}

	@Override
	public Object getValue() {
		//System.out.println(getFullName());
		return setValue;
	}
	
	static public FutureContractProperty getProperty(
			String name, String description, Class<?> class1) {

		FutureContractProperty property = new FutureContractProperty(name, description, class1);
	
		return property;
	}
	
	/**
	 * Set's up a ComboBox from a list of strings in a collection.
	 * 
	 * @param name
	 *            the name of the property
	 * @param description
	 *            usually shows up as a tool tip on the property table (note
	 *            JIDE tool tips differ from swing)
	 * @param values
	 *            collection of strings
	 * @return Property - property setup with auto-complete combo box
	 *         selection
	 */
	static public FutureContractProperty createAutoCompleteComboBox(
			String name, String description, final Collection values) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new AutoCompleteCellEditor(values.toArray());
			}
		}, new EditorContext(name));
		FutureContractProperty property = new FutureContractProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	static public FutureContractProperty createDateRuleDialogBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new DateRulePropertyE();
			}
		}, new EditorContext(name));
		FutureContractProperty property = new FutureContractProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
}
