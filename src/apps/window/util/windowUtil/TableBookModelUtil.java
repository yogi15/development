package apps.window.util.windowUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.Book;

public class TableBookModelUtil extends AbstractTableModel {

	final String[] columnNames;

	Vector<Book> data;
	
	

	public TableBookModelUtil(Vector<Book> myData, String col[]) {
		this.columnNames = col;
		this.data = myData;
	
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

		Book book = (Book) data.get(row);

		switch (col) {
		case 0:
			value = book.getBookno();
			break;
		case 1:
			value = book.getBook_name();
			break;
		

		}
		return value;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		
			data.set(row, (Book) value);
			this.fireTableDataChanged();
		
		

	}

	public void addRow(Object value) {

		data.add((Book) value);
		this.fireTableDataChanged();

	}

	public void delRow(int row) {
		if (row != -1) {
			data.remove(row);
			this.fireTableDataChanged();
		}

	}

	public void udpateValueAt(Object value, int row, int col) {

		data.set(row, (Book) value);
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