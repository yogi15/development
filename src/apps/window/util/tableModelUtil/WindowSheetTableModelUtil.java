package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
  
import beans.WindowSheet;

public class WindowSheetTableModelUtil extends AbstractTableModel {
    
	 /**
 * 
 */ 
	final String[] columnNames;  
	 String col[] = {"DesignType","WindowName","FieldName","DataType","Default","IsStartUpData","StartDataName","CustomPanel","IsNull","BeanName","MethodName","IsChildField","ParentFieldName"};
	 /**
	 * @return the data
	 */
	public Vector<WindowSheet> getData() {
		return mydata;
	}

	final Vector<WindowSheet> mydata;   
	        
	        
	 public WindowSheetTableModelUtil( Vector<WindowSheet> data  ) {   
	 	this.columnNames = col;
	this.mydata = data;   
	}   
	 public WindowSheetTableModelUtil( Vector<WindowSheet> data ,String [] col ) {   
		 	this.columnNames = col;
		this.mydata = data;   
		}   
	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
	     return mydata.size();   
	     
	 }  
	 public WindowSheet getRow(int i) {   
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
	 
	     WindowSheet  windowSheet = (WindowSheet) mydata.get(row);
		 switch (col) {
		 case 0:
	         value =windowSheet.getDesignType();
	         break;
	     case 1:
	         value =windowSheet.getWindowName();
	         break;
	     case 2:
	         value =windowSheet.getFieldName();
	         break;
	     case 3:
	         value =windowSheet.getDataType();
	         break;
	     case 4:
	         value =windowSheet.getDefaultValue();
	         break;
	     case 5:
	         if(windowSheet.getIsStartupdata() ==1) 
	        	 return true;
	         if(windowSheet.getIsStartupdata() ==0) 
	        	 return false;
	         break;
	     case 6:
	         value =windowSheet.getStartUpDataName();
	         break;
	     case 7:
	         value =windowSheet.getCustomPanelName();
	         break;
	     case 8:
	         value =new Boolean(windowSheet.isNullChecked());
	         break;
	     case 9:
	         value =windowSheet.getBeanName();
	         break;
	     case 10:
	         value =windowSheet.getMethodName();
	         break;
	     case 11:
	         value =new Boolean(windowSheet.isChildField());
	         break;
	     case 12:
	         value = windowSheet.getParentFieldName();
	         break;
		 }
	     return value;
	 }   
	   
	 public boolean isCellEditable(int row, int col) {   
	 return false;   
	 }   
	 
	 public void setValueAt(Object value, int row, int col) {   
	           
	         if(value instanceof WindowSheet) {
	        	 mydata.set(row,(WindowSheet ) value) ;
	     this.fireTableDataChanged();      
	         }
	        
	 }   
	 public void clear() {
		 mydata.clear();
	    	 this.fireTableDataChanged();
	    }
	 public void addRow(Object value) {   
	    
		 mydata.add((WindowSheet ) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
		 mydata.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	       
	  
		 mydata.set(row,(WindowSheet) value) ;
		 this. fireTableCellUpdated(row, col);   
	  
	    
	}
	public void setColumnName(String columnName) {
		// TODO Auto-generated method stub
		col[1] = columnName;
	}   
}