package apps.window.util.tableModelUtil;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
 import beans.Book;
 public class BookTableModelUtil extends AbstractTableModel {
		final String[] columnNames;  
		 String col[] ={" Currency"} ;
 /**
	 * @return the data
	 */
public Vector<Book> getData() {
	return mydata;
	}
	final Vector<Book> mydata;   
 public BookTableModelUtil( Vector<Book> data  ) {
		 	this.columnNames = col;
		this.mydata = data;   
	}   
 public BookTableModelUtil( Vector<Book> data ,String [] col ) {  
	 	this.columnNames = col;
	this.mydata = data;   
		}   
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
		    public int getRowCount() {   
			     return mydata.size();   
			 }  
			 public Book getRow(int i) {   
			     return mydata.get(i)  ; 
			 }
			 public String getColumnName(int col) {  
		     return columnNames[col];  
		 }   
 public Object getValueAt(int row, int col) {   
		     Object value = null;  	 
		     Book  book = (Book) mydata.get(row);	
  switch (col) {  case 0:
value =  book.getBook_name();
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
} public boolean isCellEditable(int row, int col) {   
  return false;   
		  }   
  public void setValueAt(Object value, int row, int col) {   
  if(value instanceof Book) {
	 	 mydata.set(row,(Book ) value) ;
			     this.fireTableDataChanged();      
 }
 }   
 public void clear() {
	 mydata.clear();
   this.fireTableDataChanged();
}
 public void addRow(Object value) {  
 mydata.add((Book ) value) ;
this.fireTableDataChanged(); 
 }   	
 public void delRow(int row) {   
		  mydata.remove(row);  
 this.fireTableDataChanged(); 
 }   
public void udpateValueAt(Object value, int row, int col) {
	 mydata.set(row,(Book) value) ;
    fireTableCellUpdated(row, col); 
} 
}