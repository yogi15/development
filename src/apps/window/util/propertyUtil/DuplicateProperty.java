package apps.window.util.propertyUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.table.TableCellRenderer;
 
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Expandable;
import com.jidesoft.grid.Node;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.Row;
import com.jidesoft.grid.TreeTableModel;

public class DuplicateProperty extends DefaultProperty {
    private DefaultProperty underlyingProperty;
    private boolean lockFeature;
    private boolean notifyOnChange = false;
    private boolean locked;
    private HashMap<PropertyChangeListener, ConditionalPropertyChangeListener> listenersMap;

    public DuplicateProperty(DefaultProperty underlyingProperty) {
        this (underlyingProperty, true);
    }

    public DuplicateProperty(DefaultProperty underlyingProperty, boolean lockFeature) {
        this.underlyingProperty = underlyingProperty;
        this.lockFeature = lockFeature;
        listenersMap = new HashMap<PropertyChangeListener, ConditionalPropertyChangeListener>();
    }

    /**
     * @return true of this property can be locked
     */
    public boolean hasLockFeature() {
        return lockFeature;
    }

    /**
     * @return true if this property does not currently fire property change events
     */
    public boolean isNotifyOnChange() {
        return notifyOnChange;
    }

    /**
     * enables or disables firing property change events
     * @param notifyOnChange
     */
    public void setNotifyOnChange(boolean notifyOnChange) {
        this.notifyOnChange = notifyOnChange;
    }

    /**
     * @return true if the property is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * locks or unlocks the property
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
        setEditable(!locked);
        notifyCellUpdated(null,1);
    }

    /**
     * sets the value on the underlying property, unless the property is locked. Property change events will be fired if the
     * property is unlocked
     */
    @Override
    public void setValue(Object value) {
        if (locked)
            return;
        underlyingProperty.setValue(value);
    }

    /**
     * sets the value on the underlying property and uses the fireEvent parameter to control firing property change events
     *
     * @param value
     * @param fireEvent
     */
    public void setValue(Object value, boolean fireEvent) {
        boolean tmp = isNotifyOnChange();
        setNotifyOnChange(tmp && fireEvent);
        setValue(value);
        setNotifyOnChange(tmp);
    }

    @Override
    public Object getValue() {
        return underlyingProperty.getValue();
    }

    @Override
    public boolean hasValue() {
        return underlyingProperty.hasValue();
    }

    @Override
    public String getName() {
        return underlyingProperty.getName();
    }

    @Override
    public void setName(String name) {
        underlyingProperty.setName(name);
    }

    @Override
    public String getDisplayName() {
        return underlyingProperty.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        underlyingProperty.setDisplayName(displayName);
    }

    @Override
    public String getDescription() {
        return underlyingProperty.getDescription();
    }

    @Override
    public void setDescription(String description) {
        underlyingProperty.setDescription(description);
    }

    @Override
    public Class<?> getType() {
        return underlyingProperty.getType();
    }

    @Override
    public void setType(Class<?> type) {
        underlyingProperty.setType(type);
    }

    @Override
    public String getCategory() {
        return underlyingProperty.getCategory();
    }

    @Override
    public void setCategory(String category) {
        underlyingProperty.setCategory(category);
    }

    @Override
    public String getFullName() {
        return underlyingProperty.getFullName();
    }

    @Override
    public boolean isEditable() {
        return underlyingProperty.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        underlyingProperty.setEditable(editable && !locked);
    }

    @Override
    public boolean isCategoryRow() {
        return underlyingProperty.isCategoryRow();
    }

    @Override
    public void setCategoryRow(boolean categoryRow) {
        underlyingProperty.setCategoryRow(categoryRow);
    }

    @Override
    public int getLevel() {
        return underlyingProperty.getLevel();
    }

    @Override
    public ConverterContext getConverterContext() {
        return underlyingProperty.getConverterContext();
    }

    @Override
    public void setConverterContext(ConverterContext converterContext) {
        underlyingProperty.setConverterContext(converterContext);
    }

    @Override
    public EditorContext getEditorContext() {
        return underlyingProperty.getEditorContext();
    }

    @Override
    public void setEditorContext(EditorContext editorContext) {
        underlyingProperty.setEditorContext(editorContext);
    }

    @Override
    public CellEditor getCellEditor() {
        return underlyingProperty.getCellEditor();
    }

    @Override
    public CellEditor getCellEditor(int column) {
        return underlyingProperty.getCellEditor(column);
    }

    @Override
    public TableCellRenderer getTableCellRenderer() {
        return underlyingProperty.getTableCellRenderer();
    }

    @Override
    public TableCellRenderer getTableCellRenderer(int column) {
        return underlyingProperty.getTableCellRenderer(column);
    }

    @Override
    public List<String> getDependingProperties() {
        return underlyingProperty.getDependingProperties();
    }

    @Override
    public boolean addDependingProperty(String name) {
        return underlyingProperty.addDependingProperty(name);
    }

    @Override
    public boolean addDependingProperty(String[] names) {
        return underlyingProperty.addDependingProperty(names);
    }

    @Override
    public boolean removeDependingProperty(String name) {
        return underlyingProperty.removeDependingProperty(name);
    }

    @Override
    public void clearDependingProperties() {
        underlyingProperty.clearDependingProperties();
    }

    @Override
    public int compareTo(Property property) {
        return underlyingProperty.compareTo(property);
    }

    @Override
    public boolean isExpert() {
        return underlyingProperty.isExpert();
    }

    @Override
    public void setExpert(boolean expert) {
        underlyingProperty.setExpert(expert);
    }

    @Override
    public boolean isHidden() {
        return underlyingProperty.isHidden();
    }

    @Override
    public void setHidden(boolean hidden) {
        underlyingProperty.setHidden(hidden);
    }

    @Override
    public boolean isPreferred() {
        return underlyingProperty.isPreferred();
    }

    @Override
    public void setPreferred(boolean preferred) {
        underlyingProperty.setPreferred(preferred);
    }

    @Override
    public boolean isIndentNonCategoryRow() {
        return underlyingProperty.isIndentNonCategoryRow();
    }

    @Override
    public void setIndentNonCategoryRow(boolean indent) {
        underlyingProperty.setIndentNonCategoryRow(indent);
    }

    @Override
    public Object getValueAt(int columnIndex) {
        return underlyingProperty.getValueAt(columnIndex);
    }

    @Override
    public void setValueAt(Object value, int columnIndex) {
        underlyingProperty.setValueAt(value, columnIndex);
    }

    @Override
    public boolean isCellEditable(int columnIndex) {
        return underlyingProperty.isCellEditable(columnIndex);
    }

    @Override
    public ConverterContext getConverterContextAt(int columnIndex) {
        return underlyingProperty.getConverterContextAt(columnIndex);
    }

    @Override
    public EditorContext getEditorContextAt(int columnIndex) {
        return underlyingProperty.getEditorContextAt(columnIndex);
    }

    @Override
    public Class<?> getCellClassAt(int columnIndex) {
        return underlyingProperty.getCellClassAt(columnIndex);
    }

    @Override
    public void cellUpdated(int columnIndex) {
        underlyingProperty.cellUpdated(columnIndex);
    }

    @Override
    public void rowUpdated() {
        underlyingProperty.rowUpdated();
    }

    @Override
    public void notifyCellUpdated(Object child, int columnIndex) {
        underlyingProperty.notifyCellUpdated(child, columnIndex);
    }

    @Override
    public List<?> getChildren() {
        return underlyingProperty.getChildren();
    }

    @Override
    public void setChildren(List<?> children) {
        if (underlyingProperty != null) // this can only happen during early init phase and in this case children is null
            underlyingProperty.setChildren(children);
    }

    @Override
    public boolean isExpanded() {
        return underlyingProperty.isExpanded();
    }

    @Override
    public void setExpanded(boolean expanded) {
        underlyingProperty.setExpanded(expanded);
    }

    @Override
    public boolean isExpandable() {
        return underlyingProperty.isExpandable();
    }

    @Override
    public void setExpandable(boolean expandable) {
        underlyingProperty.setExpandable(expandable);
    }

    @Override
    public boolean hasChildren() {
        return underlyingProperty.hasChildren();
    }

    @Override
    public void removeAllChildren() {
        underlyingProperty.removeAllChildren();
    }

    @Override
    public int getNumberOfVisibleExpandable() {
        return underlyingProperty.getNumberOfVisibleExpandable();
    }

    @Override
    public int getNumberOfVisibleChildren() {
        return underlyingProperty.getNumberOfVisibleChildren();
    }

    @Override
    public boolean hasVisibleChildren() {
        return underlyingProperty.hasVisibleChildren();
    }

    @Override
    public int getChildrenCount() {
        return underlyingProperty.getChildrenCount();
    }

    @Override
    public int getAllVisibleChildrenCount() {
        return underlyingProperty.getAllVisibleChildrenCount();
    }

    @Override
    public int getAllChildrenCount(boolean leafOnly) {
        return underlyingProperty.getAllChildrenCount(leafOnly);
    }

    @Override
    public Object addChild(Object child) {
        return underlyingProperty.addChild(child);
    }

    @Override
    public Object addChild(int index, Object child) {
        return underlyingProperty.addChild(index, child);
    }

    @Override
    public void addChildren(int index, List<? extends Row> children) {
        underlyingProperty.addChildren(index, children);
    }

    @Override
    public boolean removeChild(Object child) {
        return underlyingProperty.removeChild(child);
    }

    @Override
    public boolean removeChildren(List<? extends Row> children) {
        return underlyingProperty.removeChildren(children);
    }

    @Override
    public Object getChildAt(int index) {
        return underlyingProperty.getChildAt(index);
    }

    @Override
    public int getChildIndex(Object child) {
        return underlyingProperty.getChildIndex(child);
    }

    @Override
    public boolean moveUpChild(Object child) {
        return underlyingProperty.moveUpChild(child);
    }

    @Override
    public boolean moveDownChild(Object child) {
        return underlyingProperty.moveDownChild(child);
    }

    @Override
    public void notifyChildInserted(Object child, int childIndex) {
        underlyingProperty.notifyChildInserted(child, childIndex);
    }

    @Override
    public void notifyChildrenInserted(List children, int firstIndex) {
        underlyingProperty.notifyChildrenInserted(children, firstIndex);
    }

    @Override
    public void notifyChildDeleted(Object child) {
        underlyingProperty.notifyChildDeleted(child);
    }

    @Override
    public void notifyChildrenDeleted(List<? extends Row> children) {
        underlyingProperty.notifyChildrenDeleted(children);
    }

    @Override
    public void notifyChildUpdated(Object child) {
        underlyingProperty.notifyChildUpdated(child);
    }

    @Override
    public void notifyChildrenUpdated(List<? extends Row> children) {
        underlyingProperty.notifyChildrenUpdated(children);
    }

    @Override
    public TreeTableModel getTreeTableModel() {
        return underlyingProperty.getTreeTableModel();
    }

    @Override
    public boolean isAdjusting() {
        return underlyingProperty.isAdjusting();
    }

    @Override
    public void setAdjusting(boolean adjusting) {
        underlyingProperty.setAdjusting(adjusting);
    }

    @Override
    public Expandable getParent() {
        return underlyingProperty.getParent();
    }

    @Override
    public void setParent(Expandable parent) {
        underlyingProperty.setParent(parent);
    }

    @Override
    public Node getNextSibling() {
        return underlyingProperty.getNextSibling();
    }

    @Override
    public Node getPreviousSibling() {
        return underlyingProperty.getPreviousSibling();
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        ConditionalPropertyChangeListener conditionalListener = registerListener(listener);
        underlyingProperty.addPropertyChangeListener(conditionalListener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        ConditionalPropertyChangeListener conditionalListener = listenersMap.get(listener);
        if (conditionalListener != null)
            underlyingProperty.removePropertyChangeListener(listener);
    }

    @Override
    public PropertyChangeListener[] getPropertyChangeListeners() {
        PropertyChangeListener[] changeListeners = underlyingProperty.getPropertyChangeListeners();
        return conditionalToOriginals(changeListeners);
    }


    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        ConditionalPropertyChangeListener conditionalListener = registerListener(listener);
        underlyingProperty.addPropertyChangeListener(propertyName, conditionalListener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        ConditionalPropertyChangeListener conditionalListener = listenersMap.get(listener);
        if (conditionalListener != null)
            underlyingProperty.removePropertyChangeListener(propertyName, conditionalListener);
    }

    @Override
    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        PropertyChangeListener[] changeListeners = underlyingProperty.getPropertyChangeListeners(propertyName);
        return conditionalToOriginals(changeListeners);
    }

    private ConditionalPropertyChangeListener registerListener(PropertyChangeListener listener) {
        ConditionalPropertyChangeListener conditionalListener = listenersMap.get(listener);
        if (conditionalListener == null)
            conditionalListener = new ConditionalPropertyChangeListener(listener);
        listenersMap.put(listener, conditionalListener);
        return conditionalListener;
    }

    private PropertyChangeListener[] conditionalToOriginals(PropertyChangeListener[] changeListeners) {
        PropertyChangeListener[] ret = new PropertyChangeListener[changeListeners.length];
        for (int i = 0; i < changeListeners.length; i ++)
            ret[i] = ((ConditionalPropertyChangeListener) changeListeners[i]).getOriginalListener();

        return ret;
    }

    private class ConditionalPropertyChangeListener implements PropertyChangeListener {
        private final PropertyChangeListener listener;

        public ConditionalPropertyChangeListener(PropertyChangeListener listener) {
            this.listener = listener;
        }

        public void propertyChange(PropertyChangeEvent evt) {
            if (notifyOnChange)
               listener.propertyChange(evt);
        }

        public PropertyChangeListener getOriginalListener() {
            return listener;
        }
    }
}