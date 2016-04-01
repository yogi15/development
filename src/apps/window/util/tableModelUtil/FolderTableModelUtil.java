package apps.window.util.tableModelUtil;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import beans.Folder;

public class FolderTableModelUtil extends AbstractTableModel {
	final String[] columnNames;
	String col[] = { "FolderName" };

	/**
	 * @return the data
	 */
	public Vector<Folder> getData() {
		return mydata;
	}

	final Vector<Folder> mydata;

	public FolderTableModelUtil(Vector<Folder> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public FolderTableModelUtil(Vector<Folder> data, String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public Folder getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		Folder folder = (Folder) mydata.get(row);
		switch (col) {
		case 0:
			value = folder.getFolder_name();
			break;
		}
		return value;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof Folder) {
			mydata.set(row, (Folder) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((Folder) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (Folder) value);
		fireTableCellUpdated(row, col);
	}
}