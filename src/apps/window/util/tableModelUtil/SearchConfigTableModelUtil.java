package apps.window.util.tableModelUtil;

import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
import beans.SearchConfig;

public class SearchConfigTableModelUtil extends AbstractTableModel {
	final String[] columnNames;
	String col[] = { "ID", "SearchType", "ColumnNames" };

	/**
	 * @return the col
	 */
	public String[] getCol() {
		return col;
	}

	/**
	 * @return the data
	 */
	public Vector<SearchConfig> getData() {
		return mydata;
	}

	final Vector<SearchConfig> mydata;

	public SearchConfigTableModelUtil(Vector<SearchConfig> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public SearchConfigTableModelUtil(Vector<SearchConfig> data, String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public SearchConfig getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		SearchConfig searchconfig = (SearchConfig) mydata.get(row);
		switch (col) {
		case 0:
			value = searchconfig.getID();
			break;
		case 1:
			value = searchconfig.getSearchType();
			break;
		case 2:
			value = searchconfig.getColumnNames();
			break;
		}
		return value;
	}

	@Override
	public Class getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		default:
			return String.class;
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof SearchConfig) {
			mydata.set(row, (SearchConfig) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((SearchConfig) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (SearchConfig) value);
		fireTableCellUpdated(row, col);
	}
}