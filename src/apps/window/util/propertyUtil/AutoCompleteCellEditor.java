package apps.window.util.propertyUtil;

import java.awt.Color;
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
     * Constructor
     * 
     * @param items
     */
    public AutoCompleteCellEditor(Object[] items) {
        autoCompleteComboBox = createComboBox(items);
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
    protected AutoCompletionComboBox createComboBox(Object[] items) {
    	AutoCompletionComboBox aa = new AutoCompletionComboBox(items);
       // aa.setRenderer(new BuySellCellRenderer());
        return aa;
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
    private final String BUY = "BUY";
	private final String SELL = "SELL";
    @Override
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        // Set the previously selected value
    	 String grade = (String) value;
	        if(grade.equalsIgnoreCase(BUY)) {
	        	autoCompleteComboBox.setBackground(Color.GREEN);
	        }
	        if(grade.equalsIgnoreCase(SELL)) {
	        	autoCompleteComboBox.setBackground(Color.RED);
	        }
        autoCompleteComboBox.setSelectedItem(value);
       // autoCompleteComboBox.setb
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
        if (autoCompleteComboBox.isEditable()) {
            // Commit edited value.
            autoCompleteComboBox.actionPerformed(new ActionEvent(this, 0, ""));
        }
        return super.stopCellEditing();
    }

}
