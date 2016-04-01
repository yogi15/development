package apps.window.util.propertyPane.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import beans.LegalEntity;

import com.jidesoft.swing.AutoCompletionComboBox;

/**
 * Table cell editor that provides auto complete facility. Based on jide's auto
 * completion combo box.
 * 
 * @author
 * 
 */
public class AutoCompleteCellEditor extends AbstractCellEditor
        implements
        TableCellEditor {

    /**
     * 
     */
    private static final long serialVersionUID = -6626989560132270563L;
    private AutoCompletionComboBox autoCompleteComboBox;
    /**
	 * @return the _le
	 */
	public LegalEntity get_le() {
		return _le;
	}

	/**
	 * @param _le the _le to set
	 */
	public void set_le(LegalEntity _le) {
		this._le = _le;
	}

	LegalEntity _le = null;

    /**
     * Constructor
     * 
     * @param items
     */
    public AutoCompleteCellEditor(LegalEntity le, Object[] items) {
        autoCompleteComboBox = createComboBox(le,items);
        set_le(le);
        // Add support to stop editing upon ENTER key.
        autoCompleteComboBox.getEditor()
                .getEditorComponent()
                .addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            stopCellEditing();
                        }
                    }
                });
        autoCompleteComboBox.getEditor()
                .getEditorComponent()
                .addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        stopCellEditing();
                    }
                });
    }

    /**
     * Called from the constructor to create the auto completion combo box.
     * 
     * @param items
     *            the items to use for the combo box model; cannot be null but
     *            may be empty
     * @return a new combo box, never null
     */
    protected AutoCompletionComboBox createComboBox(LegalEntity le,Object[] items) {
        return new AutoCompletionComboBox( items);
    }

    /**
     * Returns the auto completion combo box
     * 
     * @return the combo box, never null
     */
    protected AutoCompletionComboBox getComboBox() {
        return autoCompleteComboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return autoCompleteComboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        // Set the previously selected value
        autoCompleteComboBox.setSelectedItem(value);

        // Prepare for easy text entry like excel.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                autoCompleteComboBox.getEditor().selectAll();
            }
        });

        return autoCompleteComboBox;
    }

    @Override
    public boolean stopCellEditing() {
        // Overriden to commit current value
    	if(get_le() != null && get_le().getRole().equalsIgnoreCase("PO")) 
    		autoCompleteComboBox.setEditable(false);
        if (autoCompleteComboBox.isEditable()) {
            // Commit edited value.
            autoCompleteComboBox.actionPerformed(new ActionEvent(this, 0, ""));
        }
        return super.stopCellEditing();
    }

}
