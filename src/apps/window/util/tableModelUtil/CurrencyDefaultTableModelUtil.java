package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.CurrencyDefault;
import beans.Folder;

public class CurrencyDefaultTableModelUtil  extends AbstractTableModel {
	    
		 /**
	 * 
	 */
	private static final long serialVersionUID = -8215525061025568783L;
		final String[] columnNames;  
		 String col[] = {"PrimaryCurr","QuotingCurr"};
		 final Vector<CurrencyDefault> data;   
		        
		        
		 public CurrencyDefaultTableModelUtil( Vector<CurrencyDefault> myData  ) {   
		 	this.columnNames = col;
		this.data = myData;   
		}   
		 public CurrencyDefaultTableModelUtil( Vector<CurrencyDefault> myData ,String [] col ) {   
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
		 public Object getValueAt(int row, int col) {   
		     Object value = null;  	 
		 
		     CurrencyDefault currencyDef = (CurrencyDefault) data.get(row);
			 switch (col) {
		     case 0:
		         value =currencyDef.getCountry();
		         break;
		     case 1:
		         value =currencyDef.getCurrency_code();
		         break;
			 }
		     return value;
		 }   
		   
		 public boolean isCellEditable(int row, int col) {   
		 return false;   
		 }   
		 public void setValueAt(Object value, int row, int col) {   
		           
		         if(value instanceof Folder) {
		     data.set(row,(CurrencyDefault) value) ;
		     this.fireTableDataChanged();      
		         }
		        
		 }   
		    
		 public void addRow(Object value) {   
		    
			 data.add((CurrencyDefault) value) ;
		 this.fireTableDataChanged();   
		   
		 }   
		    
		 public void delRow(int row) {   
		    
		 data.remove(row);          
		 this.fireTableDataChanged();   
		    
		 }   
		 
		 public void udpateValueAt(Object value, int row, int col) {   
		       
		  
		     data.set(row,(CurrencyDefault) value) ;
		 fireTableCellUpdated(row, col);   
		  
		    
		}   
		    
	
}
