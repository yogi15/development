package apps.window.util.tableModelUtil;
import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
 import beans.CurrencyPair;
 public class CurrencyPairTableModelUtil extends AbstractTableModel implements HierarchicalTableModel { 
		final String[] columnNames;  
		 String col[] ={"Primary Currency","Quoting Currency","Pair Name","Rate Decimal","Spot Days"} ;
   /**
  * @return the col
 */
public String[] getCol() {
return col;
}
 /**
	 * @return the data
	 */
public Vector<CurrencyPair> getData() {
	return mydata;
	}
	final Vector<CurrencyPair> mydata;   
 public CurrencyPairTableModelUtil( Vector<CurrencyPair> data  ) {
		 	this.columnNames = col;
		this.mydata = data;   
	}   
 public CurrencyPairTableModelUtil( Vector<CurrencyPair> data ,String [] col ) {  
	 	this.columnNames = col;
	this.mydata = data;   
		}   
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
		    public int getRowCount() {   
			     return mydata.size();   
			 }  
			 public CurrencyPair getRow(int i) {   
			     return mydata.get(i)  ; 
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
		     CurrencyPair  currencypair = (CurrencyPair) mydata.get(row);	
  switch (col) {  case 0:
value =  currencypair.getPrimary_currency();
 break; 
 case 1:
value =  currencypair.getQuoting_currency();
 break; 
 case 2:
value =  currencypair.getPairName();
 break; 
 case 3:
value =  currencypair.getRounding();
 break; 
 case 4:
value =  currencypair.getSpot_days();
 break; 
 }
 return value;}   
 @Override 
      public Class getColumnClass(int column) { 
 switch (column) {
 case 0:
 return String.class; 
 case 1:
 return String.class; 
 case 2:
 return String.class; 
 case 3:
 return Integer.class; 
 case 4:
 return Integer.class; 
  default: 
 return String.class;
 }
}public boolean isCellEditable(int row, int col) {
 return false;
}


  public void setValueAt(Object value, int row, int col) {   
  if(value instanceof CurrencyPair) {
	 	 mydata.set(row,(CurrencyPair ) value) ;
			     this.fireTableDataChanged();      
 }
  }   
 public void clear() {
	 mydata.clear();
   this.fireTableDataChanged();
}
 public void addRow(Object value) {  
 mydata.add((CurrencyPair ) value) ;
this.fireTableDataChanged(); 
 }   	
 public void delRow(int row) {   
		  mydata.remove(row);  
 this.fireTableDataChanged(); 
 }   
public void udpateValueAt(Object value, int row, int col) {
	 mydata.set(row,(CurrencyPair) value) ;
    fireTableCellUpdated(row, col); 
} 
}