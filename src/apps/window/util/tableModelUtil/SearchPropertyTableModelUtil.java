package apps.window.util.tableModelUtil;
import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
 import beans.SearchProperty;
 public class SearchPropertyTableModelUtil extends AbstractTableModel  { 
		final String[] columnNames;  
		 String col[] ={" Attributes"} ;
   /**
  * @return the col
 */
public String[] getCol() {
return col;
}
 /**
	 * @return the data
	 */
public Vector<SearchProperty> getData() {
	return mydata;
	}
	final Vector<SearchProperty> mydata;   
 public SearchPropertyTableModelUtil( Vector<SearchProperty> data  ) {
		 	this.columnNames = col;
		this.mydata = data;   
	}   
 public SearchPropertyTableModelUtil( Vector<SearchProperty> data ,String [] col ) {  
	 	this.columnNames = col;
	this.mydata = data;   
		}   
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
		    public int getRowCount() {   
			     return mydata.size();   
			 }  
			 public SearchProperty getRow(int i) {   
			     return mydata.get(i)  ; 
			 }
			 public String getColumnName(int col) {  
		     return columnNames[col];  
		 }   
 public Object getValueAt(int row, int col) {   
		     Object value = null;  	 
		     SearchProperty  searchproperty = (SearchProperty) mydata.get(row);	
  switch (col) {  case 0:
value =  searchproperty.getAttributesName();
 break; 
 }
 return value;}   
 @Override 
      public Class getColumnClass(int column) { 
 switch (column) {
 case 0:
 return String.class; 
  default: 
 return String.class;
 }
}public boolean isCellEditable(int row, int col) {
 return false;
}


  public void setValueAt(Object value, int row, int col) {   
  if(value instanceof SearchProperty) {
	 	 mydata.set(row,(SearchProperty ) value) ;
			     this.fireTableDataChanged();      
 }
  }   
 public void clear() {
	 mydata.clear();
   this.fireTableDataChanged();
}
 public void addRow(Object value) {  
 mydata.add((SearchProperty ) value) ;
this.fireTableDataChanged(); 
 }   	
 public void delRow(int row) {   
		  mydata.remove(row);  
 this.fireTableDataChanged(); 
 }   
public void udpateValueAt(Object value, int row, int col) {
	 mydata.set(row,(SearchProperty) value) ;
    fireTableCellUpdated(row, col); 
} 
}