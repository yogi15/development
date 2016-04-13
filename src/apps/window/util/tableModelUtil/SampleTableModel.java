package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.WindowSheet;

public class SampleTableModel extends AbstractTableModel {
	
	
	 

	 String col[] = {"FieldName","CellStyle"};
	private String[] columnNames;
	private Vector<String> mydata;
	
	 public SampleTableModel( Vector<String> data  ) {   
		 	this.columnNames = col;
		this.mydata = data;   
		}   
		 public SampleTableModel( Vector<String> data ,String [] col ) {   
			 	this.columnNames = col;
			this.mydata = data;   
			}   
		    
		 public int getColumnCount() {   
		     return columnNames.length;   
		 }   
		    
		 public int getRowCount() {   
		     return mydata.size();   
		     
		 }  
	
	
	 /**
	 * @return the col
	 */ 

		 public String getRow(int i) {   
		     return mydata.get(i)  ; 
		     
		 }
		 @Override
	     public Class getColumnClass(int column) {
			 switch (column) {
			 case 5:
	        	 return Boolean.class;
	         case 8:
	        	 return Boolean.class;
	         case 11:
	        	 return Boolean.class;
	         default:
	             return String.class;
			 }
		 }
		 public String getColumnName(int col) {   
		     return columnNames[col];   
		 }   
		 public Object getValueAt(int row, int col) {   
		     Object value = null;  	 
		 
		    String fieldName= (String) mydata.get(row);
			 switch (col) {
			 case 0:
		         value =fieldName;
		         break;
		         
			 }
			  return value;
		 }
		 public void setValueAt(Object value, int row, int col) {   
	           
	         if(value instanceof WindowSheet) {
	        	 mydata.set(row,(String ) value) ;
	     this.fireTableDataChanged();      
	         }
	        
	 }   
		 public void delRow(int row) {   
			    
			 mydata.remove(row);          
		 this.fireTableDataChanged();   
		    
		 }   
		 public void addRow(Object value) {   
			    
			 mydata.addElement((String) value);          
		 this.fireTableDataChanged();   
		    
		 }   
		 
		 public void udpateValueAt(Object value, int row, int col) {   
		       
		  
			 mydata.set(row,(String) value) ;
			 this. fireTableCellUpdated(row, col);   
		  
		    
		}
	 public void clear() {
		 mydata.clear();
	    	 this.fireTableDataChanged();
	    }

	}

	 
	
	

