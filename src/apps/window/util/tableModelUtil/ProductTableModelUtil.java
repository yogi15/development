package apps.window.util.tableModelUtil;

import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
import beans.Product;

public class ProductTableModelUtil extends AbstractTableModel {
	final String[] columnNames;
	String col[] = { "UnderlyingID", " UnderlyingName", "AssetType", "AssetSubType", "Country", "Currency", "Status",
			" Exchange", "CUSIP", "SEDOL", "ISIN", "SYMBOL", "FixedRate", "FaceValue", "RateType", "RateIndex",
			"DayCount", "BusinessDayConvention", "WithHoldingTax", "RollDate", "PeriodAdjusted", "PayDayLag" };

	/**
	 * @return the col
	 */
	public String[] getCol() {
		return col;
	}

	/**
	 * @return the data
	 */
	public Vector<Product> getData() {
		return mydata;
	}

	final Vector<Product> mydata;

	public ProductTableModelUtil(Vector<Product> data) {
		this.columnNames = col;
		this.mydata = data;
	}

	public ProductTableModelUtil(Vector<Product> data, String[] col) {
		this.columnNames = col;
		this.mydata = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return mydata.size();
	}

	public Product getRow(int i) {
		return mydata.get(i);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;
		Product product = (Product) mydata.get(row);
		switch (col) {
		case 0:
			value = product.getId();
			break;
		case 1:
			value = product.getProductname();
			break;
		case 2:
			value = product.getProductType();
			break;
		case 3:
			value = product.getProductSubType();
			break;
		case 4:
			value = product.getCountry();
			break;
		case 5:
			value = product.getCurrency();
			break;
		case 6:
			value = product.getSTATUS();
			break;
		case 7:
			value = product.getEXCHANGE();
			break;
		case 8:
			value = product.getCUSIP();
			break;
		case 9:
			value = product.getSEDOL();
			break;
		case 10:
			value = product.getISIN();
			break;
		case 11:
			value = product.getSYMBOL();
			break;
		case 12:
			value = product.getCoupon().getFixedRate();
			break;
		case 13:
			value = product.getFaceValue();
			break;
		case 14:
			value = product.getCoupon().getRATETYPE();
			break;
		case 15:
			value = product.getCoupon().getRATEINDEX();
			break;
		case 16:
			value = product.getCoupon().getDayCount();
			break;
		case 17:
			value = product.getCoupon().getBusinessDayConvention();
			break;
		case 18:
			value = product.getCoupon().getWithholdingTax();
			break;
		case 19:
			value = product.getCoupon().getROLLDATE();
			break;
		case 20:
			value = product.getCoupon().getPERIODADJUSTED();
			break;
		case 21:
			value = product.getCoupon().getPAYDAYLAG();
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
			return Integer.class;
		case 9:
			return Integer.class;
		case 10:
			return Integer.class;
		case 11:
			return Integer.class;
		case 12:
			return Double.class;
		case 13:
			return Double.class;
		case 14:
			return String.class;
		case 15:
			return String.class;
		case 16:
			return String.class;
		case 17:
			return String.class;
		case 18:
			return String.class;
		case 19:
			return String.class;
		case 20:
			return String.class;
		case 21:
			return String.class;
		default:
			return String.class;
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if (value instanceof Product) {
			mydata.set(row, (Product) value);
			this.fireTableDataChanged();
		}
	}

	public void clear() {
		mydata.clear();
		this.fireTableDataChanged();
	}

	public void addRow(Object value) {
		mydata.add((Product) value);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		mydata.remove(row);
		this.fireTableDataChanged();
	}

	public void udpateValueAt(Object value, int row, int col) {
		mydata.set(row, (Product) value);
		fireTableCellUpdated(row, col);
	}
}