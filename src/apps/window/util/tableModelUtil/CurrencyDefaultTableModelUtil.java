package apps.window.util.tableModelUtil;
import java.util.Vector;
import com.jidesoft.grid.HierarchicalTableModel;
import javax.swing.table.AbstractTableModel;
 import beans.CurrencyDefault;
 public class CurrencyDefaultTableModelUtil extends AbstractTableModel implements HierarchicalTableModel { 
		final String[] columnNames;  
		 String col[] ={"ISOCODE","Country","Holiday","SpotDays","Rounding","Decimals","RateDecimals"," Precious Metal","Non Deliverable","CCIL","CLS","SettlementCutOff"," DayCount"," BusinessConvention","Status"} ;
   /**
  * @return the col
 */
public String[] getCol() {
return col;
}
 /**
	 * @return the data
	 */
public Vector<CurrencyDefault> getData() {
	return mydata;
	}
	final Vector<CurrencyDefault> mydata;   
 public CurrencyDefaultTableModelUtil( Vector<CurrencyDefault> data  ) {
		 	this.columnNames = col;
		this.mydata = data;   
	}   
 public CurrencyDefaultTableModelUtil( Vector<CurrencyDefault> data ,String [] col ) {  
	 	this.columnNames = col;
	this.mydata = data;   
		}   
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
		    public int getRowCount() {   
			     return mydata.size();   
			 }  
			 public CurrencyDefault getRow(int i) {   
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
		     CurrencyDefault  currencydefault = (CurrencyDefault) mydata.get(row);	
  switch (col) {  case 0:
value =  currencydefault.getIso_code();
 break; 
 case 1:
value =  currencydefault.getCountry();
 break; 
 case 2:
value =  currencydefault.getDefault_holiday();
 break; 
 case 3:
value =  currencydefault.getSpot_days();
 break; 
 case 4:
value =  currencydefault.getRounding();
 break; 
 case 5:
value =  currencydefault.getCurrencyDecimal();
 break; 
 case 6:
value =  currencydefault.getRateDecimals();
 break; 
 case 7:
value =  currencydefault.getIs_precious_metal_b();
 break; 
 case 8:
value =  currencydefault.getNon_deliverable_b();
 break; 
 case 9:
value =  currencydefault.getCCIL();
 break; 
 case 10:
value =  currencydefault.getCLS();
 break; 
 case 11:
value =  currencydefault.getSettlementCutOffTime();
 break; 
 case 12:
value =  currencydefault.getDayCount();
 break; 
 case 13:
value =  currencydefault.getBDC();
 break; 
 case 14:
value =  currencydefault.getSTATUS();
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
 case 5:
 return Integer.class; 
 case 6:
 return Integer.class; 
 case 7:
 return Boolean.class; 
 case 8:
 return Integer.class; 
 case 9:
 return Boolean.class; 
 case 10:
 return Boolean.class; 
 case 11:
 return Boolean.class; 
 case 12:
 return String.class; 
 case 13:
 return String.class; 
 case 14:
 return Boolean.class; 
  default: 
 return String.class;
 }
}public boolean isCellEditable(int row, int col) {
 return false;
}


  public void setValueAt(Object value, int row, int col) {   
  if(value instanceof CurrencyDefault) {
	 	 mydata.set(row,(CurrencyDefault ) value) ;
			     this.fireTableDataChanged();      
 }
  }   
 public void clear() {
	 mydata.clear();
   this.fireTableDataChanged();
}
 public void addRow(Object value) {  
 mydata.add((CurrencyDefault ) value) ;
this.fireTableDataChanged(); 
 }   	
 public void delRow(int row) {   
		  mydata.remove(row);  
 this.fireTableDataChanged(); 
 }   
public void udpateValueAt(Object value, int row, int col) {
	 mydata.set(row,(CurrencyDefault) value) ;
    fireTableCellUpdated(row, col); 
} 
}