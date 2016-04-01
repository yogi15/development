package apps.window.util.tableModelUtil;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import beans.MenuConfiguration;

public class MenuConfigurationTableModelUtil extends AbstractTableModel {
	final String[] columnNames;
	String col[] = { "MainMenuName", "WindowName", "ParentMenuName",
			"ChildMenuName", "Width" };

	/**
	 * @return the data
	 */
	public Vector<MenuConfiguration> getData() {
		return mydata;
	}

	final Vector<MenuConfiguration> mydata;

	public MenuConfigurationTableModelUtil(Vector<MenuConfiguration> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public MenuConfigurationTableModelUtil(Vector<MenuConfiguration> data,
			String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public MenuConfiguration getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		MenuConfiguration menuconfiguration = (MenuConfiguration) mydata
				.get(row);
		switch (col) {
		case 0:
			value = menuconfiguration.getMainMenuName();
			break;
		case 1:
			value = menuconfiguration.getWindowName();
			break;
		case 2:
			value = menuconfiguration.getParentMenuName();
			break;
		case 3:
			value = menuconfiguration.getChildMenuName();
			break;
		case 4:
			value = menuconfiguration.getWidth();
			break;
		}
		return value;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof MenuConfiguration) {
			mydata.set(row, (MenuConfiguration) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((MenuConfiguration) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (MenuConfiguration) value);
		fireTableCellUpdated(row, col);
	}
}