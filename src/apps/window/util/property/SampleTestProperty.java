package apps.window.util.property;

import java.util.Collection;

import javax.swing.CellEditor;

import apps.window.util.propertyPane.editor.AutoCompleteCellEditor;
import apps.window.util.propertyPane.editor.DateRulePropertyEditor;
import apps.window.util.propertyPane.editor.LastTradeTimePropertyEditor;

import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;

public class SampleTestProperty extends Property{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5704990155633153240L;
	private Object setValue = null;

	public SampleTestProperty() {

	}

	
	public SampleTestProperty(String name, String description, Class type,
			String category, ConverterContext context,
			java.util.List childProperties) {
		super(name, description, type, category, context, childProperties);
	}

	public SampleTestProperty(String name, String description, Class type,
			String category, ConverterContext context) {
		super(name, description, type, category, context);
	}

	public SampleTestProperty(String name, String description, Class type,
			String category) {
		super(name, description, type, category);
	}

	public SampleTestProperty(String name, String description, Class type) {
		super(name, description, type);
	}

	public SampleTestProperty(String name, String description) {
		super(name, description);
	}

	public SampleTestProperty(String name) {
		super(name);
	}
	static public SampleTestProperty getProperty(
			String name, String description, Class<?> class1) {

		SampleTestProperty property = new SampleTestProperty(name, description, class1);
	     
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
	static public SampleTestProperty createAutoCompleteComboBox(
			String name, String description, final Collection values) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new AutoCompleteCellEditor(null,values.toArray());
			}
		}, new EditorContext(name));
		SampleTestProperty property = new SampleTestProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	static public SampleTestProperty createDateRuleDialogBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new DateRulePropertyEditor();
			}
		}, new EditorContext(name));
		SampleTestProperty property = new SampleTestProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	static public SampleTestProperty createTimeHHMMBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new LastTradeTimePropertyEditor(60);
			}
		}, new EditorContext(name));
		SampleTestProperty property = new SampleTestProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	
	public void setValue(Object value, boolean fireEvent) {
		Object oldValue = setValue;
		setValue = value;
		if (oldValue != setValue && fireEvent) {
			firePropertyChange(PROPERTY_VALUE, oldValue, value);
		}
	}
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return setValue;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		setValue(value, true);
		
	} 


}
