package apps.window.util.propertyUtil;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.swing.JideButton;

/**/
public class PropertyAction extends DefaultProperty {
    private final Action action;

    public PropertyAction(Action action) {
        this.action = action;
    }

    @Override
    public TableCellRenderer getTableCellRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JideButton((String) action.getValue(Action.NAME));
            }
        };
    }

    @Override
    public CellEditor getCellEditor() {
        return new DefaultCellEditor(new JCheckBox()) {
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return new JideButton(new AbstractAction("Setup") {
                    public void actionPerformed(ActionEvent e) {
                        action.actionPerformed(null);
                        stopCellEditing();
                    }
                });
            }

            public Object getCellEditorValue() {
                return null;
            }

            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }
        };
    }
}
