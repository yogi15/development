package apps.window.util.property;

import java.util.Collection;

import javax.swing.AbstractCellEditor;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;


import apps.window.util.propertyPane.editor.AutoCompleteCellEditor;
import apps.window.util.propertyPane.editor.DateRulePropertyEditor;
import apps.window.util.propertyPane.editor.LastTradeTimePropertyEditor;
import apps.window.util.propertyPane.editor.*;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.AbstractComboBoxCellEditor;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.DoubleCellEditor;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.IntegerCellEditor;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTableCellRenderer;
import com.jidesoft.swing.AutoCompletionComboBox;
public class AccountingProperty  extends Property {
	private Object setValue = null;

	public AccountingProperty() {

	}

	public AccountingProperty(String name, String description, Class type,
			String category, ConverterContext context,
			java.util.List childProperties) {
		super(name, description, type, category, context, childProperties);
	}

	public AccountingProperty(String name, String description, Class type,
			String category, ConverterContext context) {
		super(name, description, type, category, context);
	}

	public AccountingProperty(String name, String description, Class type,
			String category) {
		super(name, description, type, category);
	}

	public AccountingProperty(String name, String description, Class type) {
		super(name, description, type);
	}

	public AccountingProperty(String name, String description) {
		super(name, description);
	}

	public AccountingProperty(String name) {
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
	
	static public AccountingProperty getProperty(
			String name, String description, Class<?> class1) {

		AccountingProperty property = new AccountingProperty(name, description, class1);
	     
		return property;
	}
	
	static public AccountingProperty createIntegerLeftAlignProperty(
			String name, String description, Class<?> classType) {
			AccountingProperty property = AccountingProperty.getProperty(name, description, classType);
			CellEditorManager.registerEditor(classType, 
			new CellEditorFactory() {
			            public CellEditor create() {
			                return new IntegerCellEditor() {
			                    @Override
			                    protected void customizeTextField() {
			                        super.customizeTextField();
			                        _textField.setHorizontalAlignment(SwingConstants.LEFT);
			                    }
			                };
			            }
			        }, new EditorContext("LeftAlign"));
			        
			        property.setEditorContext(new EditorContext("LeftAlign"));
			        
			        PropertyTableCellRenderer propertyTableCellRenderer = new PropertyTableCellRenderer();
			        propertyTableCellRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			        property.setTableCellRenderer(propertyTableCellRenderer);
			        
			        return property;
			}
	
	static public AccountingProperty createDoubleLeftAlignProperty(
			String name, String description, Class<?> classType) {
			AccountingProperty property = AccountingProperty.getProperty(name, description, classType);
			CellEditorManager.registerEditor(classType, 
			new CellEditorFactory() {
			            public CellEditor create() {
			                return new DoubleCellEditor() {
			                    @Override
			                    protected void customizeTextField() {
			                        super.customizeTextField();
			                        _textField.setHorizontalAlignment(SwingConstants.LEADING);
			                    }
			                };
			            }
			        }, new EditorContext("LeftAlign"));
			        
			        property.setEditorContext(new EditorContext("LeftAlign"));
			        
			        PropertyTableCellRenderer propertyTableCellRenderer = new PropertyTableCellRenderer();
			        propertyTableCellRenderer.setHorizontalAlignment(SwingConstants.LEADING);
			        property.setTableCellRenderer(propertyTableCellRenderer);
			        
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
	static public AccountingProperty createAutoCompleteComboBox(
			String name, String description, final Collection values) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new AutoCompleteCellEditor(null,values.toArray());
			}
		}, new EditorContext(name));
		AccountingProperty property = new AccountingProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	static public AccountingProperty createDateRuleDialogBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new DateRulePropertyEditor();
			}
		}, new EditorContext(name));
		AccountingProperty property = new AccountingProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	static public AccountingProperty createTimeHHMMBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new LastTradeTimePropertyEditor(60);
			}
		}, new EditorContext(name));
		AccountingProperty property = new AccountingProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
}
