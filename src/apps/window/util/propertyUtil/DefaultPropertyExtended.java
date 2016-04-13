package apps.window.util.propertyUtil;

import java.awt.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.CellEditor;

import util.commonUTIL;
  
import com.jidesoft.grid.AbstractJideCellEditor;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.JideCellEditor;
import com.jidesoft.grid.Property;
import com.jidesoft.validation.Validator;

public class DefaultPropertyExtended<T> extends DefaultProperty {
    private boolean _autoExpandOnEdition = false; // once the Property is set to true, Property is expanded
    private T _defaultValueIfNull = null;
    private final Set<Validator> _validators = new HashSet<Validator>();
    
    /**
     * Constructor may call this initialization helper method
     * @param name
     * @param description
     * @param type
     * @param defaultValueIfNull
     */
    protected void setup(String name, String description, Class<?> type, T defaultValueIfNull) {
        setName(name);
        setDisplayName(name);
        setDescription(description);
        setType(type);
        setDefaultValueIfNull(defaultValueIfNull);
    }

    /**
     * If Property is managed by a TreeTableModel, setting autoExpandOnEditable = true will:
     * <li>Make the Property expandable and expand Property if editable is true
     * <li>Collapse the Property and make it not expandable if editable is false 
     * @param autoExpandWhenEditable - default is false
     */
    public void setAutoExpandWhenEditable(boolean autoExpandWhenEditable) {
        _autoExpandOnEdition = autoExpandWhenEditable;
    }
    
    public boolean isAutoExpandWhenEditable() {
        return _autoExpandOnEdition;
     }
    
    /**
     * Purpose is to allow override the default Component returned by CellEditor:
     * <li> a custom Component returned by registered CellEditor when this Property is editable
     * <li> a JTextField returned by TextFiledCellEditor when this Property is NOT editable  
     * @param originalComponent returned by PropertyTable.prepareEditor(TableCellEditor, row, column)
     * @return
     */
    public Component prepareEditorComponent(Component originalComponent) {
        return originalComponent;
    }
    
    /**
     * If Property is managed by a TreeTableModel, when autoExpandOnEditable = true then:
     * <li>Make the Property expandable and expand Property if editable is true
     * <li>Collapse the Property and make it not expandable if editable is false 
     * @param editable
     */    
    @Override
    public void setEditable(boolean editable) {
        // changing the editable state should also be reflected in a collapse or expand of the Property
        if (isAutoExpandWhenEditable() && editable ^ isEditable() && getTreeTableModel() != null) {
            if (editable) setExpandable(editable);
            getTreeTableModel().expandRow(this, editable);
            if (!editable) setExpandable(editable);
        }
        super.setEditable(editable);
    }
    
    @Override
    protected synchronized void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (isAutoExpandWhenEditable() && !isExpanded() && PROPERTY_VALUE.equals(propertyName) && getTreeTableModel() != null) {
            getTreeTableModel().expandRow(this, true);
        }
        super.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    @Override
    public T getValue() {
        return super.getValue() == null ? getDefaultValueIfNull() : (T)super.getValue();
    }
    
    /**
     * Default implementation return null; ie. by default the Property value is nullable.
     * @see com.calypso.apps.util.derivatives.properties.ExtendedPropertyEdition#getDefaultValueIfNull()
     */
    public T getDefaultValueIfNull() {
        return _defaultValueIfNull;
    }
    
    public void setDefaultValueIfNull(T defaultValueIfNull) {
        _defaultValueIfNull = defaultValueIfNull;
    }
    
    /**
     * Set a typed Property value, of the type parmeterized for this Porperty instance.
     * Calls super.setValue(). 
     * @see com.jidesoft.grid.Property#setValue(java.lang.Object)
     * @param value of parmeterized type T
     */
    public void setTypedValue(T value) {
        super.setValue(value);
    }

    /**
     * Calling this.setTypedValue() enforce setting a value of the expected type
     * @see ExtendedEditionProperty#setTypedValue(Object)
     * @see com.jidesoft.grid.Property#setValue(java.lang.Object)
     */
    @Override
    public void setValue(Object value) {
        this.setTypedValue((T)value); // if ClassCastExcpetion thrown here, then fix code: should not set an unexpected value type
    }
    
    /**
     * In a Property TreeTable, we may want to make children of a property editable, like parent property
     * 
     * @param editable
     * @param applyToChildren
     */
    public void setEditable(boolean editable, boolean applyToChildren) {
        setEditable(this, editable, applyToChildren);
    }
    
    private void setEditable(Property prop, boolean editable, boolean applyToChildren) {
        prop.setEditable(editable);
        if (applyToChildren && !commonUTIL.isEmpty(prop.getChildren())) {
            for (Property child: (List<Property>)prop.getChildren()) {
                setEditable(child, editable, applyToChildren);
            }
        }        
    }
    
    /**
     * Purpose is to validate CellEditor input before Property.setValue(input).
     * Work only for {@link JideCellEditor}
     * @param v
     */
    public void addValidationListener(Validator v) {
        _validators.add(v);
    }
    
    /**
     * @param v the listener to be removed
     */
    public void removeValidationListener(Validator v) {
        _validators.remove(v);
    }

    /**
     * @return all of the <code>Validator</code>s added or an empty Set if no listeners have been added
     */
    public Set<Validator> getValidationListeners() {
        return Collections.unmodifiableSet(_validators);
    }
    
    /**
     * Add validation on edition
     * @see com.jidesoft.grid.Property#getCellEditor()
     */
    @Override
    public CellEditor getCellEditor() {
        CellEditor cellEditor = super.getCellEditor();
        if (cellEditor instanceof AbstractJideCellEditor) {
            installCellEditorValidation((AbstractJideCellEditor) cellEditor);
        }
        return cellEditor;
    }
    
    protected void installCellEditorValidation(AbstractJideCellEditor cellEditor) {
        if (commonUTIL.isEmpty(_validators)) 
            return;
        for (Validator v : _validators) {
            cellEditor.removeValidationListener(v);
            cellEditor.addValidationListener(v);
        }
    }
}

