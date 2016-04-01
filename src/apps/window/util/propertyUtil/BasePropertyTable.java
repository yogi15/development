package apps.window.util.propertyUtil;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;
 
import apps.window.util.propertyTable.PropertyTableBuilder;
   
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;
import com.jidesoft.icons.JideIconsFactory;

public class BasePropertyTable extends PropertyTable {
	
	 private static final String CLIENT_PROP_NEXT_COMPONENT = "nextTable";
	    private static final String CLIENT_PROP_PREV_COMPONENT = "prevTable";
	    private static final String ACTION_KEY_SHIFT_TAB = "shift-tab";
	    private static final String ACTION_KEY_TAB = "tab";
	    
	    
	    public static PropertyTable makePropertyTable(Property... properties) {
	        return makePropertyTable(Arrays.asList(properties), new PropertyTableBuilder() {
	            public PropertyTable createPropertyTable() {
	                return new DynamicPropertyTable();
	            }
	        }, false);
	    }
	
	    public static PropertyTable makePropertyTable(List<Property> props) {
	        PropertyTable propertyTable = makePropertyTable(props, new PropertyTableBuilder() {
	            public PropertyTable createPropertyTable() {
	                return new DynamicPropertyTable();
	            }
	        }, true);
	        if (propertyTable instanceof DynamicPropertyTable) { // to enable categorizes
	            ((DynamicPropertyTable) propertyTable).setOrder(PropertyTableModel.CATEGORIZED);
	            propertyTable.expandFirstLevel();
	        }
	        return propertyTable;
	    }
	    
	    public static PropertyTable makePropertyTable(List<Property> props, PropertyTableBuilder builder, boolean showCategories) {
	        final PropertyTable propertyTable = builder.createPropertyTable();

	        propertyTable.setRowSelectionAllowed(true);
	        propertyTable.setColumnSelectionAllowed(true);

	        final PropertyTableModel model = new PropertyTableModel(props);
	      
	       if (showCategories) // to disable Catergorizes
	            model.setOrder(PropertyTableModel.CATEGORIZED);
	        model.setOrder(PropertyTableModel.UNSORTED);
             
	        PropertyChangeListener listener = new PropertyChangeListener() {
	            public void propertyChange(PropertyChangeEvent evt) {
	                final int selectedRow = propertyTable.getSelectionModel().getMinSelectionIndex();
	                final int selectedCol = propertyTable.getColumnModel().getSelectionModel().getMinSelectionIndex();
	                model.refresh();
	                EventQueue.invokeLater(new Runnable() {
	                    public void run() {
	                        propertyTable.getSelectionModel().setSelectionInterval(selectedRow,  selectedRow);
	                        propertyTable.getColumnModel().getSelectionModel().setSelectionInterval(selectedCol,  selectedCol);
	                    }
	                });
	            }
	        };
	        for (Object o : props) {
	            ((Property) o).addPropertyChangeListener(Property.PROPERTY_EDITABLE, listener);
	            ((Property) o).addPropertyChangeListener(Property.PROPERTY_HIDDEN, listener); //
	            ((Property) o).addPropertyChangeListener(Property.PROPERTY_DISPLAY_NAME, listener);
	            ((Property) o).addPropertyChangeListener(Property.PROPERTY_VALUE, listener);
	        }

	        propertyTable.setModel(model);

	        propertyTable.setPaintMarginBackground(false);
	        propertyTable.setShowTreeLines(false);
	        propertyTable.setShowsRootHandles(false);
	        propertyTable.setSurrendersFocusOnKeystroke(true);
	        propertyTable.putClientProperty("JTable.autoStartsEdit", Boolean.TRUE);

	        ListSelectionListener propertyTableSelectionListener = new ListSelectionListener() {
	            Timer timer = new Timer(170, new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    timer.stop();
	                    int selectedRow = propertyTable.getSelectedRow();
	                    if (selectedRow == -1)
	                        return;
	                    initiateComboBoxEditing(selectedRow, propertyTable);
	                }
	            });

	            public void valueChanged(ListSelectionEvent e) {
	                Exception exception = new Exception();
	                exception.fillInStackTrace();
	                StackTraceElement[] stackTraceElements = exception.getStackTrace();
	                for (StackTraceElement stackTraceElement : stackTraceElements)
	                    if (stackTraceElement.getClassName().equals(JComponent.class.getName()) && stackTraceElement.getMethodName().equals("processMouseEvent"))
	                        return; // not interested in selection changes triggered by mouse events
	                if (e.getValueIsAdjusting())
	                    return;

	                if (propertyTable.getSelectedColumn() == 0)
	                    return;

	                if (timer.isRunning())
	                    timer.restart();
	                else
	                    timer.start();
	            }
	        };
	        propertyTable.getSelectionModel().addListSelectionListener(propertyTableSelectionListener);
	        propertyTable.getColumnModel().getSelectionModel().addListSelectionListener(propertyTableSelectionListener);
	        Action tabAction = new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
	                propertyTable.editingStopped(new ChangeEvent(this));
	                final int initialSelectedRow = propertyTable.getSelectedRow();
	                final int initialSelectedColumn = propertyTable.getSelectedColumn();
	                if (initialSelectedColumn == -1 || initialSelectedRow == -1)
	                    return;

	                int selectedRow = initialSelectedRow;

	                if (initialSelectedColumn == 1) {
	                    final PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
	                    final int rowCount = tableModel.getRowCount();

	                    selectedRow++;
	                    while (selectedRow < rowCount && (tableModel.getPropertyAt(selectedRow).isCategoryRow() || !tableModel.getPropertyAt(selectedRow).isEditable()))
	                        selectedRow ++;
	                    if (selectedRow == rowCount) {
	                        propertyTable.clearSelection();
	                        jumpToNextComponent(propertyTable);
	                        return;
	                    }
	                }

	                propertyTable.setRowSelectionInterval(selectedRow, selectedRow);
	                propertyTable.setColumnSelectionInterval(1, 1);
	            }
	        };
	        propertyTable.getActionMap().put(ACTION_KEY_TAB, tabAction);
	        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), ACTION_KEY_TAB);
	        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ACTION_KEY_TAB);

	        Action shiftTabAction = new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
	                propertyTable.editingStopped(new ChangeEvent(this));
	                final int initialSelectedRow = propertyTable.getSelectedRow();
	                final int initialSelectedColumn = propertyTable.getSelectedColumn();
	                int selectedRow = initialSelectedRow;
	                if (initialSelectedColumn == 1) {
	                    final PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
	                    final int rowCount = tableModel.getRowCount();

	                    selectedRow--;
	                    while (selectedRow > -1 && (tableModel.getPropertyAt(selectedRow).isCategoryRow() || !tableModel.getPropertyAt(selectedRow).isEditable()))
	                        selectedRow --;
	                    if (selectedRow == -1) {
	                        propertyTable.clearSelection();
	                        jumpToPreviousComponent(propertyTable);
	                        return;
	                    }
	                }

	                propertyTable.setRowSelectionInterval(selectedRow, selectedRow);
	                propertyTable.setColumnSelectionInterval(1, 1);
	            }
	        };
	        propertyTable.getActionMap().put(ACTION_KEY_SHIFT_TAB, shiftTabAction);
	        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK), ACTION_KEY_SHIFT_TAB);
	        propertyTable.setPaintMarginBackground(false);
	        propertyTable.setMarginRenderer(new DefaultTableCellRenderer() {
             

			@Override
             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                 JLabel label = (JLabel) super.getTableCellRendererComponent(table, "", false, false, row, column);
                 label.setHorizontalAlignment(SwingConstants.CENTER);
                 label.setOpaque(false);
                 if (!((Property) value).hasChildren() && isSelected) {
                     label.setIcon(JideIconsFactory.getImageIcon(JideIconsFactory.Arrow.RIGHT));
                 }
                 else {
                     label.setIcon(null);
                 }
                 return label;
             }
         });
	       
	        return propertyTable;
	    }
	
	 /**
     * stops cell editing on all tables in the chain
     * @param component
     */
    public void stopTableEditing(JComponent component) {
        JComponent runner = component;
        boolean stay = true;
        while (stay) {
            if (runner instanceof JTable) {
                final TableCellEditor tableCellEditor = ((JTable) runner).getCellEditor();
                if (tableCellEditor != null)
                    tableCellEditor.stopCellEditing();
            }

            runner = (JComponent) runner.getClientProperty(CLIENT_PROP_NEXT_COMPONENT);
            stay = runner != null && runner != component;
        }
    }


    static  boolean canReceiveFocus(JComponent component) {
        if (component == null)
            return false;
        
        if (!component.isVisible()) {
            return false;
        }
        if (component instanceof PropertyTable)
            return true;
        if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            if (textComponent.isShowing() && textComponent.isVisible() && textComponent.isEditable() && textComponent.isEnabled())
                return true;
        }
        if (component instanceof JComboBox &&
            component.isEnabled()) {
            return true;
        }
        if (component instanceof AbstractButton &&
            component.isEnabled()) {
            return true;
        }
        return false;
    }
    /**
     * link property tables together so that focus jumps from one table to the next when hitting tab / shift-tab 
     *
     * @param components
     */
    public static void linkPropertyTables(final JComponent... components) {
        if (components.length < 2)
            return;

        for (int i = 0; i < components.length; i ++) {
            final int ii = i;
            components[i].putClientProperty(CLIENT_PROP_NEXT_COMPONENT, components[nextCyclicId(i, components.length)]);
            components[i].putClientProperty(CLIENT_PROP_PREV_COMPONENT, components[prevCyclicId(i, components.length)]);


            installComponentLinkingListeners(components[i], components[ii]);
        }
    }
    static void jumpToPreviousComponent(JComponent originalComponent) {
        JComponent prevComponent = (JComponent) (originalComponent.getClientProperty(CLIENT_PROP_PREV_COMPONENT));
        if (prevComponent == null) {
            if (originalComponent instanceof PropertyTable)
                selectPrevProperty((PropertyTable) originalComponent);
        } else {
            while (!canReceiveFocus(prevComponent) && prevComponent != originalComponent)
                prevComponent = (JComponent) prevComponent.getClientProperty(CLIENT_PROP_PREV_COMPONENT);
            if (prevComponent instanceof PropertyTable) {
                selectPrevProperty((PropertyTable) prevComponent);
            } else
                prevComponent.requestFocusInWindow();
        }
    }
    private static void installComponentLinkingListeners(final JComponent component, final JComponent component1) {
        if (!(component instanceof PropertyTable)) {
            component.setFocusTraversalKeysEnabled(false);
            KeyAdapter KeyAdapter = new KeyAdapter() {
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.VK_TAB && event.getModifiers() == 0/*|| event.getKeyChar() == '\t'*/) {
                        jumpToNextComponent(component);
                        event.consume();
                    }

                    if (event.getKeyCode() == KeyEvent.VK_TAB && event.getModifiers() == InputEvent.SHIFT_MASK/*|| event.getKeyChar() == '\t'*/) {
                        jumpToPreviousComponent(component);
                        event.consume();
                    }
                }

				private void jumpToPreviousComponent(JComponent component) {
					// TODO Auto-generated method stub
					
				}
           };
            KeyAdapter previousAdapter = (KeyAdapter) component.getClientProperty("PROP_TABLE_CHAIN");
            if (previousAdapter != null)
                component.removeKeyListener(previousAdapter);
            component.addKeyListener(KeyAdapter);
        }
    }

    public static void looseLinkComponents(final JComponent... components) {
        if (components.length < 2)
            return;
        else
            for (int i = 0; i < components.length - 1; i ++) {

                components[i].putClientProperty(CLIENT_PROP_NEXT_COMPONENT, components[i + 1]);
                components[i + 1].putClientProperty(CLIENT_PROP_PREV_COMPONENT, components[i]);
                installComponentLinkingListeners(components[i], components[i+1]);
            }
    }
	
	private static void selectPrevProperty(PropertyTable propertyTable) {
        if (propertyTable.getModel().getRowCount() == 0)  {
            jumpToPreviousComponent(propertyTable);
        } else {
            int lastNdx = propertyTable.getRowCount() - 1;
            PropertyTableModel propertyTableModel = propertyTable.getPropertyTableModel();
            while (lastNdx > -1 && ( propertyTableModel.getPropertyAt(lastNdx).isCategoryRow() || !propertyTableModel.getPropertyAt(lastNdx).isEditable()))
                lastNdx --;
            if (lastNdx == -1)
                lastNdx = propertyTable.getRowCount() - 1;
            propertyTable.setRowSelectionInterval(lastNdx, lastNdx);
            propertyTable.setColumnSelectionInterval(1, 1);
            propertyTable.requestFocusInWindow();
        }
    }

    private static void jumpToNextComponent(JComponent originalComponent) {
        JComponent nextComponent = (JComponent) (originalComponent.getClientProperty(CLIENT_PROP_NEXT_COMPONENT));
        if (nextComponent == null) {
            if (originalComponent instanceof PropertyTable)
                selectNextProperty((PropertyTable) originalComponent);
        } else {
            while (!canReceiveFocus(nextComponent) && nextComponent != originalComponent)
                nextComponent = (JComponent) nextComponent.getClientProperty(CLIENT_PROP_NEXT_COMPONENT);
            if (nextComponent instanceof PropertyTable)
                selectNextProperty((PropertyTable) nextComponent);
            else
                nextComponent.requestFocusInWindow();
        }
    }
	
	private static void selectNextProperty(PropertyTable propertyTable) {
        if (propertyTable.getModel().getRowCount() == 0) {
            jumpToNextComponent(propertyTable);
        } else {
            int firstNdx = 0;
            int rowCount = propertyTable.getRowCount();
            PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
            while (firstNdx < rowCount && (tableModel.getPropertyAt(firstNdx).isCategoryRow() || !tableModel.getPropertyAt(firstNdx).isEditable()))
                firstNdx ++;
            if (firstNdx == rowCount)
                firstNdx = 0;
            propertyTable.setRowSelectionInterval(firstNdx, firstNdx);
            propertyTable.setColumnSelectionInterval(1, 1);
            propertyTable.requestFocusInWindow();
        }
    }

    private static int nextCyclicId(int crt, int max) {
        return crt == max - 1 ? 0 : crt + 1;
    }

    private static int prevCyclicId(int crt, int max) {
        return crt == 0 ? max - 1 : crt - 1;
    }
        
        
        private static void initiateComboBoxEditing(int selectedRow, PropertyTable propertyTable) {
            PropertyTableModel propertyTableModel = propertyTable.getPropertyTableModel();
            int actualRow = propertyTable.getActualRowAt(selectedRow);
            Property property = propertyTableModel.getPropertyAt(actualRow);

            TableCellEditor editor = property == null ? null : propertyTable.getCellEditor(selectedRow, 1);
            if (editor != null) {
                Component editorComponent = editor.getTableCellEditorComponent(propertyTable, propertyTable.getModel().getValueAt(selectedRow, 1), true, selectedRow, 1);
                if (editorComponent instanceof ListComboBox || editorComponent instanceof JComboBox || editorComponent instanceof JButton) {
                    propertyTable.editCellAt(selectedRow, 1);
                    if (propertyTable.getEditorComponent() != null)
                        propertyTable.getEditorComponent().requestFocus();
                }
            }
        }

}
