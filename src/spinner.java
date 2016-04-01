import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;


public class spinner {
	
	JTable table = new JTable();
	DefaultTableModel model23 = (DefaultTableModel) table.getModel();
	
	// Add some columns
	
	model.addColumn("A", new Object[]{"item1"});
//	model.addColumn("B", new Object[]{"item2"});

	// These are the spinner values
	String[] values = new String[]{"item1", "item2", "item3"};

	// Set the spinner editor on the 1st visible column
	int vColIndex = 0;
	TableColumn col = table.getColumnModel().getColumn(vColIndex);
	col.setCellEditor(new SpinnerEditor(values));

	// If you want to make the cell appear like a spinner in its
	// non-editing state, also set the spinner renderer
	col.setCellRenderer(new SpinnerRenderer(values));

	public class SpinnerEditor extends AbstractCellEditor
	        implements TableCellEditor {
	    final JSpinner spinner = new JSpinner();

	    // Initializes the spinner.
	    public SpinnerEditor(String[] items) {
	        spinner.setModel(new SpinnerListModel(java.util.Arrays.asList(items)));
	    }

	    // Prepares the spinner component and returns it.
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        spinner.setValue(value);
	        return spinner;
	    }

	    // Enables the editor only for double-clicks.
	    public boolean isCellEditable(EventObject evt) {
	        if (evt instanceof MouseEvent) {
	            return ((MouseEvent)evt).getClickCount() >= 2;
	        }
	        return true;
	    }

	    // Returns the spinners current value.
	    public Object getCellEditorValue() {
	        return spinner.getValue();
	    }
	}

}
}
