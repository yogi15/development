package apps.window.util.tableModelUtil;
import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
 import beans.LegalEntity;
 public class CounterPartyTableModelUtil extends AbstractTableModel  { 
		final String[] columnNames;  
		public  String  col[] ={"Id","Name","Role","     Country","Attributes"} ;
 /**
		 * @return the col
		 */
		public String[] getCol() {
			return col;
		}
/**
	 * @return the data
	 */
public Vector<LegalEntity> getData() {
	return mydata;
	}
	final Vector<LegalEntity> mydata;   
 public CounterPartyTableModelUtil( Vector<LegalEntity> data  ) {
		 	this.columnNames = col;
		this.mydata = data;   
	}   
 public CounterPartyTableModelUtil( Vector<LegalEntity> data ,String [] col ) {  
	 	this.columnNames = col;
	this.mydata = data;   
		}   
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
		    public int getRowCount() {   
			     return mydata.size();   
			 }  
			 public LegalEntity getRow(int i) {   
			     return mydata.get(i)  ; 
			 }
			 public String getColumnName(int col) {  
		     return columnNames[col];  
		 }   
 public Object getValueAt(int row, int col) {   
		     Object value = null;  	 
		     LegalEntity  counterparty = (LegalEntity) mydata.get(row);	
  switch (col) {  case 0:
value =  counterparty.getId();
 break; 
 case 1:
value =  counterparty.getName();
 break; 
 case 2:
value =  counterparty.getRole();
 break; 
 case 3:
value =  counterparty.getCountry();
 break; 
 case 4:
value =  counterparty.getAttributes();
 break; 
 }
 return value;}   
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
  default: 
 return String.class;
 }
}public boolean isCellEditable(int row, int col) {
	if(col == 3 )
		return true;
 return false;
}


  public void setValueAt(Object value, int row, int col) {   
  if(value instanceof LegalEntity) {
	 	 mydata.set(row,(LegalEntity ) value) ;
			     this.fireTableDataChanged();      
 }
   
 if(value instanceof String) {
LegalEntity cc = mydata.get(row);
cc.setCountry((String) value);
 this.fireTableDataChanged(); 
}
 }   
 public void clear() {
	 mydata.clear();
   this.fireTableDataChanged();
}
 public void addRow(Object value) {  
 mydata.add((LegalEntity ) value) ;
this.fireTableDataChanged(); 
 }   	
 public void delRow(int row) {   
		  mydata.remove(row);  
 this.fireTableDataChanged(); 
 }   
public void udpateValueAt(Object value, int row, int col) {
	 mydata.set(row,(LegalEntity) value) ;
    fireTableCellUpdated(row, col); 
} 
}