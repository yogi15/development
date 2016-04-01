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
import dsServices.RemoteAccount;
import dsServices.RemoteReferenceData;

import apps.window.util.propertyDialog.LESelectionPropertyDialog.TableModelUtil;
import beans.Account;
import beans.Book;
import beans.LegalEntity;

public class AccountSelectionPropertyDialog  extends JDialog  {
	String col[] = { "AccountID", "Account Name ", "Currency" };
	Vector<Account> accounts = new Vector<Account>();
	int poid = 0;
	TableModelUtil modelt  = new TableModelUtil(accounts, col);
	String currency = "";
	
	   private JPanel mainPanel = new JPanel();
	   private JTable accountJTable = new JTable();
	  
	   private JScrollPane JScrollPane1 = new JScrollPane();
	   private Vector<Account> _vectorAccount = null;
	   private String _displayableObjectClass = "Account";
	  
	   private final int WINDOW_WIDTH = 550;
	   private final int WINDOW_DEPTH = 200;
	   public AccountSelectionPropertyDialog(Frame parent, boolean modal ,String displayableObjectClass)
	   {
	       this(parent, modal, null,false, true, displayableObjectClass);
	   }

	   public AccountSelectionPropertyDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter,String objType)
	   {
	       this(parent, modal, comp, showFilter, true, objType);
	   }

	   public AccountSelectionPropertyDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter, boolean isOrderable, String displayableObjectClass)
	   {
	       super(parent, modal);
	       currency = displayableObjectClass;
	       init(displayableObjectClass);
	   }
	   
	   public AccountSelectionPropertyDialog(Frame frame, boolean b,
			String displayableObjectClass, String currency2,
			LegalEntity accountHolder) {
		// TODO Auto-generated constructor stub
		   super(frame, b);
		   poid = accountHolder.getId();
		  
	       currency = currency2;
	       
	       init(displayableObjectClass);
	}

	void init(String displayableObjectClass) {
	       try {
	           jbInit();
	           _displayableObjectClass = displayableObjectClass;
	        //   displayAccounts(_displayableObjectClass,0);
	       } catch (Exception e) {
	          // Log.error(this, e);
	       }
	   }
	   private Map<Integer, Account> contractAtRow = new HashMap<Integer, Account>(0);
	   public Account getAccountAtRow(int row) {
		   
		   Account account = (Account) accounts.get(row);
		   return account;
	   }
	   protected void setFutureContractList(Vector<Account> accounts){
	    	this. accounts = accounts;
	     }
	     protected void jbInit() throws Exception
		   {
			   Container contentPane = getContentPane();
		       contentPane.setLayout(new BorderLayout()); 
		       mainPanel.setLayout(new BorderLayout());
		       try {
		    	   accountJTable.setRowSelectionAllowed(true);
		    	
		    	   modelt = new TableModelUtil(getAccountList(currency,poid),col);
		    	   accountJTable.setModel(modelt);
		       //    _FutureContractSelectorTableModel.setTo(FutContractJTable,true);
		       //    _FutureContractSelectorTableModel.refresh();
		    	   accountJTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		           accountJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		          // TableUtil.adjust(FutContractJTable);
		       }
		       catch (Exception e) {
		          // Log.error(this, e);
		       }
		       JScrollPane1.getViewport().add(accountJTable);
		       accountJTable.setPreferredScrollableViewportSize(new java.awt.Dimension(WINDOW_WIDTH, WINDOW_DEPTH));
		       mainPanel.add(JScrollPane1,BorderLayout.CENTER);
		       contentPane.add(mainPanel);
		   }
	     
	     protected void setAccountList(Vector<Account> accounts){
	    	this. accounts = accounts;
	     }
	     
		   
		   public JPanel getMainPanel(){
			   return mainPanel;
		   }
		   

		private Vector<Account> getAccountList(String currency,int podID) {
			// TODO Auto-generated method stub
			 String sqlW = " currency = '"+currency+ "' and poid =" + podID;
			 RemoteAccount remoateAccount  = RemoteServiceUtil.getRemoteAccountService();
			// Vector<Account> accounts = null;
			try {
				accounts = (Vector)  remoateAccount.getAccountonWhereClause(sqlW);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return accounts;
		}
	   
		
		class TableModelUtil extends AbstractTableModel {

			final String[] columnNames;

			Vector<Account> data;
			RemoteReferenceData remoteRef;
			Hashtable<Integer, Book> books;

			public TableModelUtil(Vector<Account> myData, String col[]) {
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

				Account acct1 = (Account) data.get(row);

				switch (col) {
				case 0:
					value = acct1.getId();
					break;
				case 1:
					value = acct1.getAccountName();
					break;
				case 2:
					value = acct1.getCurrency();
					break;
				

				}
				return value;
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}

			public void setValueAt(Object value, int row, int col) {
				System.out.println("Setting value at " + row + "," + col + " to "
						+ value + " (an instance of " + value.getClass() + ")");
				
					data.set(row, (Account) value);
					this.fireTableDataChanged();
					System.out.println("New value of data:");
				

			}

			public void addRow(Object value) {

				data.add((Account) value);
				this.fireTableDataChanged();

			}

			public void delRow(int row) {
				if (row != -1) {
					data.remove(row);
					this.fireTableDataChanged();
				}

			}

			public void udpateValueAt(Object value, int row, int col) {

				data.set(row, (Account) value);
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


		public JTable getAccountSelectorTabel() {
			// TODO Auto-generated method stub
			return accountJTable;
		}

		
		
	   
}
