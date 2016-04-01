package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.RemoteServiceUtil;
import util.commonUTIL;

import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;

import beans.Book;
import beans.LegalEntity;
import beans.Product;
 
public class LESelectionPropertyDialog extends JDialog {
	
	String col[] = {   "Name "  };
	Vector<LegalEntity> legalEntitys = new Vector<LegalEntity>();
	public TableModelUtil modelt  = new TableModelUtil(legalEntitys, col);
	   private JPanel mainPanel = new JPanel();
	   private JTable FutContractJTable = new JTable();
	   private JScrollPane JScrollPane1 = new JScrollPane();
	   private Vector<LegalEntity> _vectorLEs = null;
	   private String _displayableObjectClass = "LegalEntity";
	   private String role = "";
	   private final int WINDOW_WIDTH = 150;
	   private final int WINDOW_DEPTH = 200;
	   
	   
	   public LESelectionPropertyDialog(Frame parent, boolean modal ,String displayableObjectClass)
	   {
	       this(parent, modal, null,false, true, displayableObjectClass);
	   }

	   public LESelectionPropertyDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter,String objType)
	   {
	       this(parent, modal, comp, showFilter, true, objType);
	   }

	   public LESelectionPropertyDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter, boolean isOrderable, String displayableObjectClass)
	   {
	       super(parent, modal);
	       role = displayableObjectClass;
	       init(displayableObjectClass);
	   }
	   
	   void init(String displayableObjectClass) {
	       try {
	           jbInit();
	           _displayableObjectClass = displayableObjectClass;
	           displayContracts(_displayableObjectClass,0);
	       } catch (Exception e) {
	          // Log.error(this, e);
	       }
	   }
	   
	   public void reloadData(String displayObjectClassType,int productID){
	    	 try{
	    		 
	    		 if(displayObjectClassType.equals("FutureContract")){
	    			// _vectorFutureContract = DSConnection.getDefault().getRemoteProduct().getAllFutureContracts();
	    		 }
	    		 else{
	    			// _vectorFutureContract = DSConnection.getDefault().getRemoteProduct().getAllFutureOptionContracts();
	    		 }
	    	 }
	    	 catch(Exception e){
	    		// Log.error(this, e);
	    	 }
	    	 displayContracts(displayObjectClassType,productID);
	     }
	     
	     protected Vector<LegalEntity> getLeList(){
	    	 return getAllFutureContracts(_displayableObjectClass);
	     }
		   public void displayContracts(String objType,int productID){
			  
			   RemoteReferenceData remoteRefeData = RemoteServiceUtil.getRemoteReferenceDataService();
				
				int id = 0;
				try {
					LegalEntity product = (LegalEntity) remoteRefeData.getLegalEntityDataOnRole(objType);
					if(product != null) {
						if(product.getId() > 0)  {
							modelt.addRow(product);
					contractAtRow.put(product.getId(), product);
						}
					}
				}catch(Exception e) {
					
				}
			/*   if(_vectorFutureContract == null){ 
				   _vectorFutureContract = getAllFutureContracts(objType);
			   }
			   if(_FutureContractSelectorTableModel !=null)
				   FutContractJTable.setModel(null);
				 //  _FutureContractSelectorTableModel.removeFrom(FutContractJTable);
			   if(_vectorFutureContract!=null)
				   _FutureContractSelectorTableModel = new ContractSelectorTableModel(_vectorFutureContract,col);
			   else return;
			   contractAtRow = new HashMap<Integer, Product>(0);
			//   _FutureContractSelectorTableModel.notifyOnNewValue(false);
			   //Here I have all the Futurecontracts in the Vector v
			//   _FutureContractSelectorTableModel = new ContractSelectorTableModel(myData, col)
			       
		    	//   _FutureContractSelectorTableModel.notifyOnNewValue(true);
			   FutContractJTable.setModel(_FutureContractSelectorTableModel);*/
		    	 //  _FutureContractSelectorTableModel.setTo(FutContractJTable, true);
		    	   
		    	 //  FutContractJTable.removeColumn(FutContractJTable.getColumn(_FutureContractSelectorTableModel.ID));
		    	 //  _FutureContractSelectorTableModel.refresh();
		       //    TableUtil.adjust(FutContractJTable);	    	   
		   }
		   
		   private Map<Integer, LegalEntity> contractAtRow = new HashMap<Integer, LegalEntity>(0);
		   
		   public LegalEntity getContractAtRow(int row) {
			   LegalEntity le = (LegalEntity) modelt.getData(row);
			   int id = le.getId();
			   return contractAtRow.get(id);
		   }
		   
		   
	   protected Vector<LegalEntity> getAllFutureContracts(String displayObjectClassType){
	  	   if(_vectorLEs == null){
	  			   
	  		  		   
	  		  			  /* Product e1 = new Product();
	  		  			   e1.setId(10);
	  		  			   e1.setName("testing1");
	  		  			 Product e2 = new Product();
	  		  			   e2.setId(12);
	  		  			   e2.setName("testing2");
	  		  			 Product e3 = new Product();
	  		  			   e3.setId(13);
	  		  			   e3.setName("testing3");
	  		  			_vectorFutureContract = new Vector<Product>();
	  		  			_vectorFutureContract.add(e2);
	  		  		contractAtRow.put("testing2",e2);
	  		  		_vectorFutureContract.add(e3);
	  		  	contractAtRow.put("testing3",e3);
	  		  	_vectorFutureContract.add(e1);
	  			contractAtRow.put("testing1",e1); */
	  			RemoteReferenceData remoteReference = RemoteServiceUtil.getRemoteReferenceDataService();
				
				int id = 0;
				try {
					
					if(role.equalsIgnoreCase("ALL")) {
						_vectorLEs = (Vector) remoteReference.selectAllLs();
					} else {
						_vectorLEs = (Vector) remoteReference.getLegalEntityDataOnRole(role);
					}
					for(int i=0;i<_vectorLEs.size();i++) {
						LegalEntity product = (LegalEntity)_vectorLEs.get(i);
						contractAtRow.put(product.getId(), product);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	  	   }
	  		   
	    	 return _vectorLEs;
	     }
		   
	     protected void setLEList(Vector<LegalEntity> futureContracts){
	    	 _vectorLEs = futureContracts;
	     }
	     protected void jbInit() throws Exception
		   {
			   Container contentPane = getContentPane();
		       contentPane.setLayout(new BorderLayout()); 
		       mainPanel.setLayout(new BorderLayout());
		       try {
		    	   FutContractJTable.setRowSelectionAllowed(true);
		    	
		    	   modelt = new TableModelUtil(getLeList(),col);
		    	   FutContractJTable.setModel(modelt);
		       //    _FutureContractSelectorTableModel.setTo(FutContractJTable,true);
		       //    _FutureContractSelectorTableModel.refresh();
		           FutContractJTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		           FutContractJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		          // TableUtil.adjust(FutContractJTable);
		       }
		       catch (Exception e) {
		          // Log.error(this, e);
		       }
		       JScrollPane1.getViewport().add(FutContractJTable);
		       FutContractJTable.setPreferredScrollableViewportSize(new java.awt.Dimension(WINDOW_WIDTH, WINDOW_DEPTH));
		       mainPanel.add(JScrollPane1,BorderLayout.CENTER);
		       contentPane.add(mainPanel);
		   }
		   
		   public JPanel getMainPanel(){
			   return mainPanel;
		   }
		   public void getClear(){
			   modelt.removeALL();
			   
		   }
		   public JTable getFutureContractSelectorTabel(){
			   return FutContractJTable;
		   }
		   class TableModelUtil extends AbstractTableModel {

				final String[] columnNames;

				Vector<LegalEntity> data;
				RemoteReferenceData remoteRef;
				Hashtable<Integer, Book> books;

				public TableModelUtil(Vector<LegalEntity> myData, String col[]) {
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
                public Object getData(int row) {
                	return data.get(row);
                }
				public Object getValueAt(int row, int col) {
					Object value = null;

					LegalEntity legalEntity = (LegalEntity) data.get(row);

					switch (col) {
					case 0:
						value = legalEntity.getName();
						 
						break;
					case 1:
						value = legalEntity.getId();
						 
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
					
						data.set(row, (LegalEntity) value);
						this.fireTableDataChanged();
						System.out.println("New value of data:");
					

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


}
