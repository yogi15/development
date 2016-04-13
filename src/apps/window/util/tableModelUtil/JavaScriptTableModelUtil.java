package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.JavaScript;
import beans.JavaScript;

public class JavaScriptTableModelUtil extends AbstractTableModel {
    
	 /**
 * 
 */
private static final long serialVersionUID = -8215525061025568783L;
	final String[] columnNames;  
	 String col[] = {"WindowName","FieldName","DataType","Default","IsStartUpData","StartDataName","CustomPanel","IsNull"};
	 /**
	 * @return the data
	 */
	public Vector<JavaScript> getData() {
		return mydata;
	}

	final Vector<JavaScript> mydata;   
	        
	        
	 public JavaScriptTableModelUtil( Vector<JavaScript> data  ) {   
	 	this.columnNames = col;
	this.mydata = data;   
	}   
	 public JavaScriptTableModelUtil( Vector<JavaScript> data ,String [] col ) {   
		 	this.columnNames = col;
		this.mydata = data;   
		}   
	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
	     return mydata.size();   
	     
	 }  
	 public JavaScript getRow(int i) {   
	     return mydata.get(i)  ; 
	     
	 }
	 public String getColumnName(int col) {   
	     return columnNames[col];   
	 }   
	 public Object getValueAt(int row, int col) {   
	     Object value = null;  	 
	 
	     JavaScript  javaScript = (JavaScript) mydata.get(row);
		 switch (col) {
	     case 0:
	         value =javaScript.getWindowName();
	         break;
	     case 1:
	         value =javaScript.getScriptName();
	         break;
	     
		 }
	     return value;
	 }   
	   
	 public boolean isCellEditable(int row, int col) {   
	 return false;   
	 }   
	 
	 public void setValueAt(Object value, int row, int col) {   
	           
	         if(value instanceof JavaScript) {
	        	 mydata.set(row,(JavaScript ) value) ;
	     this.fireTableDataChanged();      
	         }
	        
	 }   
	 public void clear() {
		 mydata.clear();
	    	 this.fireTableDataChanged();
	    }
	 public void addRow(Object value) {   
	    
		 mydata.add((JavaScript ) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
		 mydata.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	       
	  
		 mydata.set(row,(JavaScript) value) ;
	 fireTableCellUpdated(row, col);   
	  
	    
	}   
}