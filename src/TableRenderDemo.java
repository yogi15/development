import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
/**
 * TableRenderDemo is just like TableDemo, except that it explicitly initializes
 * column sizes and it uses a combo box as an editor for the Sport column.
 */
public class TableRenderDemo extends JPanel implements ActionListener {
	private JTable table;
	private JCheckBox rowCheck;
	private JCheckBox columnCheck;
	private JCheckBox cellCheck;
	private ButtonGroup buttonGroup;
	private JTextArea output;

	public TableRenderDemo() {

		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		table = new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		table.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ColumnListener());
		add(new JScrollPane(table));

		add(new JLabel("Selection Mode"));
		buttonGroup = new ButtonGroup();
		addRadio("Multiple Interval Selection").setSelected(true);
		addRadio("Single Selection");
		addRadio("Single Interval Selection");

		add(new JLabel("Selection Options"));
		rowCheck = addCheckBox("Row Selection");
		rowCheck.setSelected(true);
		columnCheck = addCheckBox("Column Selection");
		cellCheck = addCheckBox("Cell Selection");
		cellCheck.setEnabled(false);

		output = new JTextArea(5, 40);
		output.setEditable(false);
		add(new JScrollPane(output));
	}

	private JCheckBox addCheckBox(String text) {
		JCheckBox checkBox = new JCheckBox(text);
		checkBox.addActionListener(this);
		add(checkBox);
		return checkBox;
	}

	private JRadioButton addRadio(String text) {
		JRadioButton b = new JRadioButton(text);
		b.addActionListener(this);
		buttonGroup.add(b);
		add(b);
		return b;
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		// Cell selection is disabled in Multiple Interval Selection
		// mode. The enabled state of cellCheck is a convenient flag
		// for this status.
		if ("Row Selection" == command) {
			table.setRowSelectionAllowed(rowCheck.isSelected());
			// In MIS mode, column selection allowed must be the
			// opposite of row selection allowed.
			if (!cellCheck.isEnabled()) {
				table.setColumnSelectionAllowed(!rowCheck.isSelected());
			}
		} else if ("Column Selection" == command) {
			table.setColumnSelectionAllowed(columnCheck.isSelected());
			// In MIS mode, row selection allowed must be the
			// opposite of column selection allowed.
			if (!cellCheck.isEnabled()) {
				table.setRowSelectionAllowed(!columnCheck.isSelected());
			}
		} else if ("Cell Selection" == command) {
			table.setCellSelectionEnabled(cellCheck.isSelected());
		} else if ("Multiple Interval Selection" == command) {
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			// If cell selection is on, turn it off.
			if (cellCheck.isSelected()) {
				cellCheck.setSelected(false);
				table.setCellSelectionEnabled(false);
			}
			// And don't let it be turned back on.
			cellCheck.setEnabled(false);
		} else if ("Single Interval Selection" == command) {
			table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			// Cell selection is ok in this mode.
			cellCheck.setEnabled(true);
		} else if ("Single Selection" == command) {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// Cell selection is ok in this mode.
			cellCheck.setEnabled(true);
		}

		// Update checkboxes to reflect selection mode side effects.
		rowCheck.setSelected(table.getRowSelectionAllowed());
		columnCheck.setSelected(table.getColumnSelectionAllowed());
		if (cellCheck.isEnabled()) {
			cellCheck.setSelected(table.getCellSelectionEnabled());
		}
	}

	private void outputSelection() {
		output.append(String.format("Lead: %d, %d. ", table.getSelectionModel()
				.getLeadSelectionIndex(), table.getColumnModel()
				.getSelectionModel().getLeadSelectionIndex()));
		output.append("Rows:");
		for (int c : table.getSelectedRows()) {
			output.append(String.format(" %d", c));
		}
		output.append(". Columns:");
		for (int c : table.getSelectedColumns()) {
			 output.append(String.format(" %d", c));
			System.out.println( table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
		}
		output.append(".\n");
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			System.out.println(event.getSource());
			output.append("ROW SELECTION EVENT. ");
			outputSelection();
		}
	}

	private class ColumnListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			System.out.println(event.getSource());
			output.append("COLUMN SELECTION EVENT. ");
			outputSelection();
		}
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };
		private Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		 @Override
         public Class getColumnClass(int column) {
             switch (column) {
                 case 0:
                     return String.class;
                 case 1:
                     return String.class;
                 case 2:
                     return Integer.class;
                 case 3:
                     return Double.class;
                 default:
                     return Boolean.class;
             }
		 }
		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		 

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Disable boldface controls.
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Create and set up the window.
		JFrame frame = new JFrame("TableSelectionDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		TableRenderDemo newContentPane = new TableRenderDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
