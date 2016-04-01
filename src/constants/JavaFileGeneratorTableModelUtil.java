package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.JavaFileGenerator;
import beans.JavaFileGenerator;

public class JavaFileGeneratorTableModelUtil extends AbstractTableModel {
    
	 /**
 * 
 */
private static final long serialVersionUID = -8215525061025568783L;
	final String[] columnNames;  
	 String col[] = {"WindowName","MethodName","Parameter","ReturnType" };
	 /**
	 * @return the data
	 */
	public Vector<JavaFileGenerator> getData() {
		return mydata;
	}

	final Vector<JavaFileGenerator> mydata;   
	        
	        
	 public JavaFileGeneratorTableModelUtil( Vector<JavaFileGenerator> data  ) {   
	 	this.columnNames = col;
	this.mydata = data;   
	}   
	 public JavaFileGeneratorTableModelUtil( Vector<JavaFileGenerator> data ,String [] col ) {   
		 	this.columnNames = col;
		this.mydata = data;   
		}   
	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
	     return mydata.size();   
	     
	 }  
	 public JavaFileGenerator getRow(int i) {   
	     return mydata.get(i)  ; 
	     
	 }
	 public String getColumnName(int col) {   
	     return columnNames[col];   
	 }   
	 public Object getValueAt(int row, int col) {   
	     Object value = null;  	 
	 
	     JavaFileGenerator  JavaFileGenerator = (JavaFileGenerator) mydata.get(row);
		 switch (col) {
	     case 0:
	         value =JavaFileGenerator.getWindowName();
	         break;
	     case 1:
	         value =JavaFileGenerator.getMethodName();
	         break;
	     case 2:
	         value =JavaFileGenerator.getParameterType();
	         break;
	     case 3:
	         value =JavaFileGenerator.getReturnType();
	         break; 
		 }
	     return value;
	 }   
	   
	 public boolean isCellEditable(int row, int col) {   
	 return false;   
	 }   
	 
	 public void setValueAt(Object value, int row, int col) {   
	           
	         if(value instanceof JavaFileGenerator) {
	        	 mydata.set(row,(JavaFileGenerator ) value) ;
	     this.fireTableDataChanged();      
	         }
	        
	 }   
	 public void clear() {
		 mydata.clear();
	    	 this.fireTableDataChanged();
	    }
	 public void addRow(Object value) {   
	    
		 mydata.add((JavaFileGenerator ) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
		 mydata.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	       
	  
		 mydata.set(row,(JavaFileGenerator) value) ;
	 fireTableCellUpdated(row, col);   
	  
	    
	}   
}