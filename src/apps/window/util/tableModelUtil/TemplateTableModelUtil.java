package apps.window.util.tableModelUtil;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import beans.Template;
public class TemplateTableModelUtil extends AbstractTableModel {
    
	 /**
 * 
 */
private static final long serialVersionUID = -8215525061025568783L;
	final String[] columnNames;  
	 String col[] = {"WindowName","FieldName","DataType","Default","IsStartUpData","StartDataName","CustomPanel","IsNull"};
	 /**
	 * @return the data
	 */
	public Vector<Template> getData() {
		return mydata;
	}

	final Vector<Template> mydata;   
	        
	        
	 public TemplateTableModelUtil( Vector<Template> data  ) {   
	 	this.columnNames = col;
	this.mydata = data;   
	}   
	 public TemplateTableModelUtil( Vector<Template> data ,String [] col ) {   
		 	this.columnNames = col;
		this.mydata = data;   
		}   
	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
	     return mydata.size();   
	     
	 }  
	 public Template getRow(int i) {   
	     return mydata.get(i)  ; 
	     
	 }
	 public String getColumnName(int col) {   
	     return columnNames[col];   
	 }   
	 public Object getValueAt(int row, int col) {   
	     Object value = null;  	 
	 
	     Template  template = (Template) mydata.get(row);
		 switch (col) {
	     case 0:
	         value =template.getWindowName();
	         break;
	     case 1:
	         value =template.getFieldName();
	         break;
	     case 2:
	         value =template.getDataType();
	         break;
	     case 3:
	         value =template.getDefaultValue();
	         break;
	     case 4:
	         value =template.getIsStartupdata();
	         break;
	     case 5:
	         value =template.getStartUpDataName();
	         break;
	     case 6:
	         value =template.getCustomPanelName();
	         break;
	     case 7:
	         value =template.isNullChecked();
	         break;
		 }
	     return value;
	 }   
	   
	 public boolean isCellEditable(int row, int col) {   
	 return false;   
	 }   
	 
	 public void setValueAt(Object value, int row, int col) {   
	           
	         if(value instanceof Template) {
	        	 mydata.set(row,(Template ) value) ;
	     this.fireTableDataChanged();      
	         }
	        
	 }   
	 public void clear() {
		 mydata.clear();
	    this.fireTableDataChanged();
	    }
	 public void addRow(Object value) {   
	    
		 mydata.add((Template ) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    
		 mydata.remove(row);          
	 this.fireTableDataChanged();   
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {  
		 mydata.set(row,(Template) value) ;
	     fireTableCellUpdated(row, col);  
	    
	}   
}