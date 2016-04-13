package apps.window.util.tableModelUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
  
import beans.WindowSheet;

public class WindowSheetTableModelUtil extends AbstractTableModel {
    
	 /**
 * 
 */ 
	final String[] columnNames;  
	 String col[] = {"ColumnSequence","DesignType","WindowName","FieldName","DataType","Default","IsStartUpData","StartDataName","CustomPanel","IsNull","BeanName","MethodName","IsChildField","ParentFieldName","IsHieRarachicalWindow","ChildWindowName","ChildKey","MapJavaObject","IsEditAble","IsHidden","IsConditional","ConfigureIfElseCondition"};
	 /**
	 * @return the col
	 */
	public String[] getCol() {
		return col;
	}
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
     public Class<?>  getColumnClass(int column) {
		 switch (column) {
		 case 6:
        	 return Boolean.class ;
         case 9:
        	 return Boolean.class;
         case 12:
        	 return Boolean.class;
         case 18:
        	 return Boolean.class;
         case 19:
        	 return Boolean.class;
         case 16:
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
		         value =windowSheet.getColunmSequenceNo();
		          break;
		 case 1:
	         value =windowSheet.getDesignType();
	         break;
	     case 2:
	         value =windowSheet.getWindowName();
	         break;
	     case 3:
	         value =windowSheet.getFieldName();
	         break;
	     case 4:
	         value =windowSheet.getDataType();
	         break;
	     case 5:
	         value =windowSheet.getDefaultValue();
	         break;
	     case 6:
	         if(windowSheet.getIsStartupdata() ==1) 
	        	 return new Boolean(true) ;
	         if(windowSheet.getIsStartupdata() ==0) 
	        	 return new Boolean(false) ;
	         break;
	     case 7:
	         value =windowSheet.getStartUpDataName();
	         break;
	     case 8:
	         value =windowSheet.getCustomPanelName();
	         break;
	     case 9:
	         value =new Boolean(windowSheet.isNullChecked());
	         break;
	     case 10:
	         value =windowSheet.getBeanName();
	         break;
	     case 11:
	         value =windowSheet.getMethodName();
	         break;
	     case 12:
	         value =new Boolean(windowSheet.isChildField());
	         break;
	     case 13:
	         value = windowSheet.getParentFieldName();
	         break;
	     case 14:
	         value = new Boolean(windowSheet.IsHierarachicalWindow());
	         break;
	     case 15:
	         value = windowSheet.getChildWindowName();
	         break;
	     case 16:
	         value =new Boolean( windowSheet.isMapJavaObject());
	          break;
	     case 17:
	         value =windowSheet.getJavaObjectName();
	          break;
	     case 18:
	         value =new Boolean( windowSheet.isEditable());
	          break;
	     case 19:
	         value =new Boolean( windowSheet.isHidden());
	          break;
	     case 20:
	         value =new Boolean( windowSheet.isCondition());
	          break;
	     case 21:
	         value =windowSheet.getConfigureIfelseCondition();
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