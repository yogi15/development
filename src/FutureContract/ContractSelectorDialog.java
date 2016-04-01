package FutureContract;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
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



public class ContractSelectorDialog extends JDialog 
{
	ContractSelectorTableModel _FutureContractSelectorTableModel;
	   private JPanel mainPanel = new JPanel();
	   private JTable FutContractJTable = new JTable();
	   private JScrollPane JScrollPane1 = new JScrollPane();
	   private Vector<EntityObject> _vectorFutureContract = null;
	   private String _displayableObjectClass = "EntityObject";
	   
	   private final int WINDOW_WIDTH = 550;
	   private final int WINDOW_DEPTH = 200;
	   
	   
	   public ContractSelectorDialog(Frame parent, boolean modal ,String displayableObjectClass)
	   {
	       this(parent, modal, null,false, true, displayableObjectClass);
	   }

	   public ContractSelectorDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter,String objType)
	   {
	       this(parent, modal, comp, showFilter, true, objType);
	   }

	   public ContractSelectorDialog(Frame parent, boolean modal, Comparator comp,boolean showFilter, boolean isOrderable, String displayableObjectClass)
	   {
	       super(parent, modal);
	       init(displayableObjectClass);
	   }
	   
	   void init(String displayableObjectClass) {
	       try {
	           jbInit();
	           _displayableObjectClass = displayableObjectClass;
	           displayContracts(_displayableObjectClass);
	       } catch (Exception e) {
	          // Log.error(this, e);
	       }
	   }
	   
	   public void reloadData(String displayObjectClassType){
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
	    	 displayContracts(displayObjectClassType);
	     }
	     
	     protected Vector<EntityObject> getFutureContractList(){
	    	 return getAllFutureContracts(_displayableObjectClass);
	     }
	     
	     String col [] = {"String","ID"};
		   public void displayContracts(String objType){
			   if(_vectorFutureContract == null){
				   _vectorFutureContract = getAllFutureContracts(objType);
			   }
			   if(_FutureContractSelectorTableModel !=null)
				   FutContractJTable.setModel(null);
				 //  _FutureContractSelectorTableModel.removeFrom(FutContractJTable);
			   if(_vectorFutureContract!=null)
				   _FutureContractSelectorTableModel = new ContractSelectorTableModel(_vectorFutureContract,col);
			   else return;
			   contractAtRow = new HashMap<String, EntityObject>(0);
			//   _FutureContractSelectorTableModel.notifyOnNewValue(false);
			   //Here I have all the Futurecontracts in the Vector v
			//   _FutureContractSelectorTableModel = new ContractSelectorTableModel(myData, col)
			       
		    	//   _FutureContractSelectorTableModel.notifyOnNewValue(true);
			   FutContractJTable.setModel(_FutureContractSelectorTableModel);
		    	 //  _FutureContractSelectorTableModel.setTo(FutContractJTable, true);
		    	   
		    	 //  FutContractJTable.removeColumn(FutContractJTable.getColumn(_FutureContractSelectorTableModel.ID));
		    	 //  _FutureContractSelectorTableModel.refresh();
		       //    TableUtil.adjust(FutContractJTable);	    	   
		   }
		   
		   private Map<String, EntityObject> contractAtRow = new HashMap<String, EntityObject>(0);
		   
		   EntityObject getContractAtRow(int row) {
			   String name = (String) FutContractJTable.getValueAt(row, 0);
			   return contractAtRow.get(name);
		   }
		   
		   
	   protected Vector<EntityObject> getAllFutureContracts(String displayObjectClassType){
	  	   if(_vectorFutureContract == null){
	  			   try{
	  		  		   if(displayObjectClassType.equals("EntityObject")){
	  		  			   EntityObject e1 = new EntityObject();
	  		  			   e1.setId(10);
	  		  			   e1.setName("testing1");
	  		  			 EntityObject e2 = new EntityObject();
	  		  			   e2.setId(12);
	  		  			   e2.setName("testing2");
	  		  			 EntityObject e3 = new EntityObject();
	  		  			   e3.setId(13);
	  		  			   e3.setName("testing3");
	  		  			_vectorFutureContract = new Vector<EntityObject>();
	  		  			_vectorFutureContract.add(e2);
	  		  		contractAtRow.put("testing2",e2);
	  		  		_vectorFutureContract.add(e3);
	  		  	contractAtRow.put("testing3",e3);
	  		  	_vectorFutureContract.add(e1);
	  			contractAtRow.put("testing1",e1);
	  		  	
	  		  		//	_vectorFutureContract = DSConnection.getDefault().getRemoteProduct().getAllFutureContracts();
	  		  		   }
	  		  		   else{
	  		  			 //  _vectorFutureContract = DSConnection.getDefault().getRemoteProduct().getAllFutureOptionContracts();
	  		  		   }
	  			   }
	  			   catch(Exception e){
	  				  // Log.error(this, e);
	  			   }
	  		   }
	    	 return _vectorFutureContract;
	     }
		   
	     protected void setFutureContractList(Vector<EntityObject> futureContracts){
	    	 _vectorFutureContract = futureContracts;
	     }
	     protected void jbInit() throws Exception
		   {
			   Container contentPane = getContentPane();
		       contentPane.setLayout(new BorderLayout()); 
		       mainPanel.setLayout(new BorderLayout());
		       try {
		    	   FutContractJTable.setRowSelectionAllowed(true);
		    	
		    	   _FutureContractSelectorTableModel = new ContractSelectorTableModel(getFutureContractList(),col);
		    	   FutContractJTable.setModel(_FutureContractSelectorTableModel);
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
		   
		   public JTable getFutureContractSelectorTabel(){
			   return FutContractJTable;
		   }
	 class  ContractSelectorTableModel extends AbstractTableModel {
		 final String[] columnNames;  
		    
		 final Vector<EntityObject> data;   
		 public ContractSelectorTableModel( Vector<EntityObject> myData,String col [] ) {   
			 	this.columnNames = col;
			this.data = myData;  
		 }
		 public int getColumnCount() {   
		     return columnNames.length;   
		 }   
		    
		 public int getRowCount() {   
		     return data.size();   
		 }   
		 public String getColumnName(int col) {   
		     return columnNames[col];   
		 }  
		
 public void setValueAt(Object value, int row, int col) {   
	         
	         if(value instanceof EntityObject) {
	     data.set(row,(EntityObject) value) ;
	     this.fireTableDataChanged();   
	         }
	         
	        
	 }   
	    
	 public void addRow(Object value) {   
	    
		 data.add((EntityObject) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
	 data.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	    
	  
	     data.set(row,(EntityObject) value) ;
	 fireTableCellUpdated(row, col);   
	     System.out.println("New value of data:");   
	    
	}   
		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			Object value = null;  	 
			 
			EntityObject book = (EntityObject) data.get(row);
			 switch (col) {
		     case 0:
		         value = book.getName();
		         break;
		     case 1:
		         value = book.getId();
		         break;
			 }
		     return value;
		   
	   }
	 }

}
