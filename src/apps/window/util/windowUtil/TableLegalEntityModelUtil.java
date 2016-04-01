package apps.window.util.windowUtil;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.Book;
import beans.LegalEntity;
import dsServices.RemoteReferenceData;

public class TableLegalEntityModelUtil extends AbstractTableModel {

	final String[] columnNames;

	Vector<LegalEntity> data;
	RemoteReferenceData remoteRef;
	Hashtable<Integer, Book> books;

	public TableLegalEntityModelUtil(Vector<LegalEntity> myData, String col[]) {
		this.columnNames = col;
		this.data = myData;
		this.books = books;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if (data != null)
			return data.size();
		return 0;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;

		LegalEntity currSplit = (LegalEntity) data.get(row);

		switch (col) {
		case 0:
			value = currSplit.getId();
			break;
		case 1:
			value = currSplit.getName();
			break;
		

		}
		return value;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		
			data.set(row, (LegalEntity) value);
			this.fireTableDataChanged();
		
		

	}

	public void addRow(Object value) {

		data.add((LegalEntity) value);
		this.fireTableDataChanged();

	}

	public void delRow(int row) {
		if (row != -1) {
			data.remove(row);
			this.fireTableDataChanged();
		}

	}

	public void udpateValueAt(Object value, int row, int col) {

		data.set(row, (LegalEntity) value);
		for (int i = 0; i < columnNames.length; i++)
			fireTableCellUpdated(row, i);

	}

	public void removeALL() {
		if (data != null) {
			data.removeAllElements();
		}
		data = null;
		this.fireTableDataChanged();
	}
}