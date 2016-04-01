package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import util.RemoteServiceUtil;
import dsServices.RemoteReferenceData;

import beans.Book;

public class BookSelectionPropertyDialog extends JDialog {

	String col[] = { "Name " };
	Vector<Book> books = new Vector<Book>();
	public TableModelUtil bookModel = new TableModelUtil(books, col);
	private JPanel mainPanel = new JPanel();
	private JTable bookTable = new JTable();
	private JScrollPane JScrollPane1 = new JScrollPane();
	private Vector<Book> _vectorLEs = null;
	private String _displayableObjectClass = "Book";
	private String role = "";
	private final int WINDOW_WIDTH = 150;
	private final int WINDOW_DEPTH = 200;

	public BookSelectionPropertyDialog(Frame parent, boolean modal,
			String displayableObjectClass) {
		this(parent, modal, null, false, true, displayableObjectClass);
	}

	public BookSelectionPropertyDialog(Frame parent, boolean modal,
			Comparator comp, boolean showFilter, String objType) {
		this(parent, modal, comp, showFilter, true, objType);
	}

	public BookSelectionPropertyDialog(Frame parent, boolean modal,
			Comparator comp, boolean showFilter, boolean isOrderable,
			String displayableObjectClass) {
		super(parent, modal);
		role = displayableObjectClass;
		init(displayableObjectClass);
	}

	private Map<Integer, Book> bookAtRow = new HashMap<Integer, Book>(0);

	public Book getBookAtRow(int row) {
		Book book = (Book) bookModel.getData(row);
		return bookAtRow.get(book.getBookno());
	}

	void init(String displayableObjectClass) {
		try {
			jbInit();
			_displayableObjectClass = displayableObjectClass;
			displayBooks(_displayableObjectClass, 0);
		} catch (Exception e) {
			// Log.error(this, e);
		}
	}

	public void reloadData(String displayObjectClassType, int productID) {
		displayBooks(displayObjectClassType, productID);
	}

	protected Vector<Book> getBookList() {
		return getAllBooks(_displayableObjectClass);
	}

	public void displayBooks(String objType, int bookID) {
		RemoteReferenceData remoteRefeData = RemoteServiceUtil
				.getRemoteReferenceDataService();

		int id = 0;
		try {
			Book b = new Book();
			b.setBookno(bookID);
			Book book = (Book) remoteRefeData.selectBook(b);
			if (book != null) {
				if (book.getBookno() > 0) {
					bookModel.addRow(book);
					bookAtRow.put(book.getBookno(), book);
				}
			}
		} catch (Exception e) {

		}
	}

	protected Vector<Book> getAllBooks(String displayObjectClassType) {
		if (_vectorLEs == null) {

			RemoteReferenceData remoteReference = RemoteServiceUtil
					.getRemoteReferenceDataService();

			int id = 0;
			try {

				_vectorLEs = (Vector) remoteReference.selectALLBooks();

				for (int i = 0; i < _vectorLEs.size(); i++) {
					Book book = (Book) _vectorLEs.get(i);
					bookAtRow.put(book.getBookno(), book);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return _vectorLEs;
	}

	protected void setBookList(Vector<Book> books) {
		_vectorLEs = books;
	}

	protected void jbInit() throws Exception {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		try {
			bookTable.setRowSelectionAllowed(true);

			bookModel = new TableModelUtil(getBookList(), col);
			bookTable.setModel(bookModel);
			// _FutureContractSelectorTableModel.setTo(bookTable,true);
			// _FutureContractSelectorTableModel.refresh();
			bookTable.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			// TableUtil.adjust(bookTable);
		} catch (Exception e) {
			// Log.error(this, e);
		}
		JScrollPane1.getViewport().add(bookTable);
		bookTable
				.setPreferredScrollableViewportSize(new java.awt.Dimension(
						WINDOW_WIDTH, WINDOW_DEPTH));
		mainPanel.add(JScrollPane1, BorderLayout.CENTER);
		contentPane.add(mainPanel);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void getClear() {
		bookModel.removeALL();

	}

	public JTable getBookTable() {
		return bookTable;
	}

	class TableModelUtil extends AbstractTableModel {

		final String[] columnNames;

		Vector<Book> data;
		RemoteReferenceData remoteRef;
		Hashtable<Integer, Book> books;

		public TableModelUtil(Vector<Book> myData, String col[]) {
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
public Object getData(int row) {
	return data.get(row);
}
		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			Object value = null;

			Book book = (Book) data.get(row);

			switch (col) {
			case 0:
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
			System.out.println("New value of data:");

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

}
