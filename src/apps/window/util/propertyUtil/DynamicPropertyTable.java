package apps.window.util.propertyUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import util.ImageUploader;
 
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.combobox.ListComboBoxSearchable;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;
import com.jidesoft.grid.Row;
import com.jidesoft.swing.ComboBoxSearchable;
import com.jidesoft.swing.NullLabel;
import com.jidesoft.swing.NullPanel;

public class DynamicPropertyTable  extends PropertyTable {
    public static final String CLIENT_PROPERTY_NO_POPUP_KEY = "nopopup";
 

    public DynamicPropertyTable() {
        setAutoSelectTextWhenStartsEditing(true);
      //  firePropertyChange(propertyName, oldValue, newValue) {
        
              
        	
      //  }
        addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stubProperty property = getSelectedProperty();
				 
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (button1DblClicked(e)) {
                    final Point at = getCellAt(e.getPoint());
                    if (at.x == 0) {
                        Row row = getRowAt(at.y);
                        if (row instanceof DuplicateProperty && ((DuplicateProperty) row).hasLockFeature()) {
                            new RevertPropertyLockAction((DuplicateProperty) row).actionPerformed(null);
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	
            	 
                if (button1DblClicked(e)) {
                    final Point at = getCellAt(e.getPoint());
                    if (at.x == 0) {
                        Row row = getRowAt(at.y);
                        if (row instanceof DuplicateProperty && ((DuplicateProperty) row).hasLockFeature()) {
                            new RevertPropertyLockAction((DuplicateProperty) row).actionPerformed(null);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	 
                if (button3SimpleClicked(e) && DynamicPropertyTable.this.getClientProperty(CLIENT_PROPERTY_NO_POPUP_KEY) == null) {
                    final Point at = getCellAt(e.getPoint());
                    Row row = getRowAt(at.y);
                    if (row instanceof DuplicateProperty && ((DuplicateProperty) row).hasLockFeature()) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(new RevertPropertyLockAction((DuplicateProperty) row));
                        popupMenu.addSeparator();
                        popupMenu.add(new LockUnlockAllAction(true, getPropertyTableModel()));
                        popupMenu.add(new LockUnlockAllAction(false, getPropertyTableModel()));
                        popupMenu.show(DynamicPropertyTable.this, e.getX(), e.getY());
                    }
                }
            }
        });
        addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				 
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				 
			}
		});
    }
    
    private boolean button1DblClicked(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2;
    }

    private boolean button3SimpleClicked(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1;
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        Point point = getCellAt(event.getPoint());
        if (point != null && needToolTip(point.y, point.x, event)) {
            Property property = getPropertyTableModel().getPropertyAt(point.y);
            if (point.x == 1) {
                String stringValue = property.getDescription();
                // to replace / with the ASCII because it will cause html invalid. See bug report at http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5046997
                return stringValue == null ? "" : "<HTML>" + stringValue.replaceAll("\n", "<BR>").replaceFirst("/", "&#47;") + "</HTML>";
            }
            else {
                return property.getDisplayName();
            }
        }
        return null;
    }

    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        stopEditingAnywhereElse();

        Component editorComponent = super.prepareEditor(editor, row, column);
        if (editorComponent instanceof ListComboBox) {
            new ListComboBoxSearchable((ListComboBox) editorComponent);
        } else if (editorComponent instanceof JComboBox) {
            new ComboBoxSearchable((JComboBox) editorComponent);
        }
        return editorComponent;
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        final Component component = super.prepareRenderer(renderer, row, column);
        if (component instanceof JLabel)
            ((JLabel) component).setHorizontalAlignment(JLabel.LEFT);
        final Property prop = getPropertyTableModel().getPropertyAt(row);
        boolean isLockable = prop instanceof DuplicateProperty && (((DuplicateProperty) prop).hasLockFeature());
        if (column == 1 && isLockable) return new NullPanel() {{
            setLayout(new BorderLayout());
            add(component, BorderLayout.CENTER);
            Icon icon = ((DuplicateProperty) prop).isLocked() ? ImageUploader.getIcon("lock_sm.gif") : ImageUploader.getIcon("unlock_sm.gif");
            add(new NullLabel(icon), BorderLayout.EAST);
        }};
        else return component;
    }

    private void stopEditingAnywhereElse() {
        JComponent nextComponent = (JComponent) getClientProperty("nextTable");
        while (nextComponent != null && nextComponent != this) {
            // stop editing
            if (nextComponent instanceof PropertyTable) {
                PropertyTable nextTable = (PropertyTable) nextComponent;
                TableCellEditor cellEditor = nextTable.getCellEditor();
                if (cellEditor != null)
                    nextTable.getCellEditor().stopCellEditing();
                nextTable.clearSelection();
            }
            nextComponent = (JComponent) nextComponent.getClientProperty("nextTable");
        }
    }

    public void setOrder(int order) {
        Property property = getSelectedProperty();
        clearSelection();
        if (isEditing()) {
            getCellEditor().stopCellEditing();
        }
        PropertyTableModel propertyTableModel = getPropertyTableModel();
        if (propertyTableModel != null) {
            propertyTableModel.setOrder(order);
        }
        revalidate();
        repaint();
        setSelectedProperty(property);
    }
}