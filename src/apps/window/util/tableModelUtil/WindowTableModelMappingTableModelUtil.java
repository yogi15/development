package apps.window.util.tableModelUtil;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import beans.WindowTableModelMapping;

public class WindowTableModelMappingTableModelUtil extends AbstractTableModel {
	final String[] columnNames;
	String col[] = { "WindowName", "BeanName", "ColumnName", "MethodName" };

	/**
	 * @return the data
	 */
	public Vector<WindowTableModelMapping> getData() {
		return mydata;
	}

	final Vector<WindowTableModelMapping> mydata;

	public WindowTableModelMappingTableModelUtil(
			Vector<WindowTableModelMapping> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public WindowTableModelMappingTableModelUtil(
			Vector<WindowTableModelMapping> data, String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public WindowTableModelMapping getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		WindowTableModelMapping windowtablemodelmapping = (WindowTableModelMapping) mydata
				.get(row);
		switch (col) {
		case 0:
			value = windowtablemodelmapping.getWindowName();
			break;
		case 1:
			value = windowtablemodelmapping.getBeanName();
			break;
		case 2:
			value = windowtablemodelmapping.getColumnName();
			break;
		case 3:
			value = windowtablemodelmapping.getMethodName();
			break;
		}
		return value;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof WindowTableModelMapping) {
			mydata.set(row, (WindowTableModelMapping) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((WindowTableModelMapping) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (WindowTableModelMapping) value);
		fireTableCellUpdated(row, col);
	}

}
