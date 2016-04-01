package apps.window.util.property;


import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import javax.swing.AbstractCellEditor;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


import apps.window.util.propertyPane.combox.LESelectionPropertyCombox;
import apps.window.util.propertyPane.editor.AccountSelectorCellEditor;
import apps.window.util.propertyPane.editor.AutoCompleteCellEditor;
import apps.window.util.propertyPane.editor.LESelectionCellEditor;
import apps.window.util.propertyPane.editor.LastTradeTimePropertyEditor; 
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.AbstractComboBoxCellEditor;
import com.jidesoft.grid.BooleanCheckBoxCellEditor;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CellRendererManager;
import com.jidesoft.grid.CheckBoxListComboBoxCellEditor;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;
import com.jidesoft.swing.AutoCompletionComboBox;


public class SDIProperty  extends Property {
	private Object setValue = null;
	public LegalEntity le = null;
	public SDIProperty() {

	}

	public SDIProperty(String name, String description, Class type,
			String category, ConverterContext context,
			java.util.List childProperties) {
		super(name, description, type, category, context, childProperties);
	}

	public SDIProperty(String name, String description, Class type,
			String category, ConverterContext context) {
		super(name, description, type, category, context);
	}

	public SDIProperty(String name, String description, Class type,
			String category) {
		super(name, description, type, category);
	}

	public SDIProperty(String name, String description, Class type) {
		super(name, description, type);
		
		  initDomains();
	}

	public SDIProperty(String name, String description) {
		super(name, description);
	}
	public SDIProperty(String name, String description,Class type,LegalEntity le) {
		super(name, description);
		this.le = le;
	}
	public SDIProperty(String name, String description,Class type,LegalEntity le,String role) {
		super(name, description);
		this.le = le;
	}
	public SDIProperty(String name) {
		super(name);
	}
	static public SDIProperty getProperty(
			String name, String description, Class<?> class1) {

		SDIProperty property = new SDIProperty(name, description, class1);
	     
		return property;
	}
	static public SDIProperty getProperty(
			String name, String description, Class<?> class1,LegalEntity le) {

		SDIProperty property = new SDIProperty(name, description, class1, le);
	     
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
	static public SDIProperty createAutoCompleteComboBox(
			String name, String description,final Collection values) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new AutoCompleteCellEditor(null, values.toArray());
			}
		}, new EditorContext(name));
		SDIProperty property = new SDIProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	
	static public SDIProperty createTimeHHMMBox(
			String name, String description) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new LastTradeTimePropertyEditor(60);
			}
		}, new EditorContext(name));
		SDIProperty property = new SDIProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	static public SDIProperty createLEBox(
			String name, String description,final Collection values,final LegalEntity le,final String role) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
		
			public CellEditor create() {
				System.out.println(role);
				return   new  LESelectionCellEditor(le,role);
				
			}
		}, new EditorContext(name));
		SDIProperty property = new SDIProperty(name, description, String.class,le,role);
		//LESelectionPropertyCombox c = new LESelectionPropertyCombox(name);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	static public SDIProperty createPOBox(
			String name, String description,final Collection values,final LegalEntity le,final String role) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
		
			public CellEditor create() {
				System.out.println(role);
				return   new  LESelectionCellEditor(le,role);
				
			}
		}, new EditorContext(name));
		SDIProperty property = new SDIProperty(name, description, String.class,le,role);
		//LESelectionPropertyCombox c = new LESelectionPropertyCombox(name);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	static public SDIProperty createCheckBOX(String name,String description,Class type)  {
		
		SDIProperty property = new  SDIProperty(name, description,type);
		property.setEditorContext(BooleanCheckBoxCellEditor.CONTEXT);
		
		// property.setValue(Boolean.TRUE);
		
//		propery.setCategory("Appearance");
		   return property;
		
	}
	static public SDIProperty createAccontBox(
			String name, String description,final Collection values,final LegalEntity le,final String currency) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
		
			public CellEditor create() {
			//	System.out.println(role);
				return   new  AccountSelectorCellEditor(currency,le);
				
			}
		}, new EditorContext(name));
		SDIProperty property = new SDIProperty(name, description, String.class,le,currency);
		//LESelectionPropertyCombox c = new LESelectionPropertyCombox(name);
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
	public void setValue(Object value) {
		setValue(value, true);
	}

	@Override
	public Object getValue() {
		//System.out.println(getFullName());
		return setValue;
	}
	
	static class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer {
		

		@Override
		public Component getTableCellRendererComponent(JTable table,Object color,boolean isSelected,boolean hasFocus,int row,int columns) {
			// TODO Auto-generated method stub
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setOpaque(false);
			return this;
		}
	}
	
	static class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
		private JCheckBox cb;
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
		
			return cb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,Object color,boolean isSelected,int row,int columns) { 
			// TODO Auto-generated method stub
			cb = new JCheckBox();
			cb.setHorizontalAlignment(SwingConstants.CENTER);
			cb.setOpaque(false);
			return cb;
			
		}
		
	}
	private void initDomains() {
     //   if (Log.isCategoryLogged("CalypsoJIDEProperty")) {
            addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					// TODO Auto-generated method stub
					
				}
               
            });
     //   }
      //  _uniqueStringKey = getNextKey();
        if (_type == Boolean.class) {
            setEditorContext(BooleanCheckBoxCellEditor.CONTEXT);
        }
    }
	
}
