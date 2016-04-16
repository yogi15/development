package apps.window.util.tableModelUtil;

import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
import beans.LeContacts;

public class ContactTableModelUtil extends AbstractTableModel implements HierarchicalTableModel {
	final String[] columnNames;
	String col[] = { " ID", "ContactType", "LealEntity", "Role", "ContactType", "FirstName", "LastName", "MailAddress1",
			"MailAddress2", "Country", "City", "State", "ZipCode", "SwiftCode", "Phone", "Fax", "Email",
			"ProductType" };

	/**
	 * @return the col
	 */
	public String[] getCol() {
		return col;
	}

	/**
	 * @return the data
	 */
	public Vector<LeContacts> getData() {
		return mydata;
	}

	final Vector<LeContacts> mydata;

	public ContactTableModelUtil(Vector<LeContacts> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public ContactTableModelUtil(Vector<LeContacts> data, String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public LeContacts getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getChildValueAt(int arg0) {
		return null;
	}

	@Override
	public boolean hasChild(int arg0) {
		return true;
	}

	@Override
	public boolean isExpandable(int arg0) {
		return true;
	}

	@Override
	public boolean isHierarchical(int arg0) {
		return true;
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		LeContacts contact = (LeContacts) mydata.get(row);
		switch (col) {
		case 0:
			value = contact.getId();
			break;
		case 1:
			value = contact.getContactType();
			break;
		case 2:
			value = contact.getLeId();
			break;
		case 3:
			value = contact.getLeRole();
			break;
		case 4:
			value = contact.getContactType();
			break;
		case 5:
			value = contact.getLeFirstName();
			break;
		case 6:
			value = contact.getLeLastName();
			break;
		case 7:
			value = contact.getMailingAddress1();
			break;
		case 8:
			value = contact.getMailingAddress2();
			break;
		case 9:
			value = contact.getCountry();
			break;
		case 10:
			value = contact.getCity();
			break;
		case 11:
			value = contact.getState();
			break;
		case 12:
			value = contact.getZipCode();
			break;
		case 13:
			value = contact.getSwift();
			break;
		case 14:
			value = contact.getPhone();
			break;
		case 15:
			value = contact.getFax();
			break;
		case 16:
			value = contact.getEmailID();
			break;
		case 17:
			value = contact.getProductType();
			break;
		}
		return value;
	}

	@Override
	public Class getColumnClass(int column) {
		switch (column) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		case 9:
			return String.class;
		case 10:
			return String.class;
		case 11:
			return String.class;
		case 12:
			return String.class;
		case 13:
			return String.class;
		case 14:
			return String.class;
		case 15:
			return String.class;
		case 16:
			return String.class;
		case 17:
			return String.class;
		default:
			return String.class;
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof LeContacts) {
			mydata.set(row, (LeContacts) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((LeContacts) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (LeContacts) value);
		fireTableCellUpdated(row, col);
	}
}