import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import beans.Trade;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RollTradePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
    public Vector<Trade> rollData;
    String col [] = {"TradeID","ProductType","TradeDate","End Date","Buy Curr","AMT","Sell Curr","Amt","Rate","RolloverTo","RollOverFrom"};
	public RollTradePanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(8, 1112, 10, 10), new Leading(7, 225, 10, 10)));
		setSize(1128, 240);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(8, 1099, 10, 10), new Leading(6, 212, 10, 10)));
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			TableModelUtil model = new TableModelUtil(rollData,col);
			jTable0.setModel(model);
		}
		return jTable0;
	}



class TableModelUtil extends AbstractTableModel {   
    
	 final String[] columnNames;  
	    
	 Vector<Trade> data;   
	
	        
	 public TableModelUtil( Vector<Trade> myData,String col [] ) {   
	 	this.columnNames = col;
	this.data = myData;   
	
	}   

	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
		 if(data != null)
	     return data.size();   
		 return 0;
	 }   
	 public String getColumnName(int col) {   
	     return columnNames[col];   
	 }   
	 public Object getValueAt(int row, int col) {   
	     Object value = null;  	 
	 
	     Trade trade = (Trade) data.get(row);
	    
		 switch (col) {
	     case 0:
	         value = trade.getId();
	         break;
	     case 1:
	         value =trade.getTradedesc1();
	         break;
	     case 2:
	    
	         value = trade.getTradeDate();
	         break;
	     case 3:;
	         value =trade.getDelivertyDate();
	         break;
	     case 4:
	         value = trade.getType();
	         break;
	     case 5:
	         value =trade.getQuantity();
	         break;
	     case 6:
	    	 if(trade.getType().equalsIgnoreCase("BUY"))
	    		 value = "SELL";
	    	 else 
	    		 value = "BUY";
	         
	         break;
	     case 7:
	         value =trade.getNominal();
	         break;
	     case 8:
	         value =trade.getPrice();
	         break;
	     case 9:
	         value =trade.getRollOverFrom();
	         break;
	     case 10:
	         value =trade.getRollOverTo();
	         break;
		 }
	     return value;
	 }   
	   
	 public boolean isCellEditable(int row, int col) {   
	 return false;   
	 }   
	 public void setValueAt(Object value, int row, int col) {   
	         System.out.println("Setting value at " + row + "," + col   
	                            + " to " + value   
	                            + " (an instance of "    
	                            + value.getClass() + ")");  
	         if(value instanceof Trade) {
	     data.set(row,(Trade) value) ;
	     this.fireTableDataChanged();   
	         System.out.println("New value of data:");   
	         }
	        
	 }   
	    
	 public void addRow(Object value) {   
	    
		 data.add((Trade) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    if(row != -1) {
	 data.remove(row);          
	 this.fireTableDataChanged();   
	    }
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	     
	  
	     data.set(row,(Trade) value) ;
	     for(int i=0;i<columnNames.length;i++)
	    	 	fireTableCellUpdated(row, i);   
	    
	}   
	    
	   
	    public void removeALL() {
	    	if(data != null) {
	  	  data.removeAllElements();
	    	} 
	    data = null;
	  	 this.fireTableDataChanged();  
	    }
}


  public void setData(Vector trades) {
	  if(trades == null || trades.isEmpty()) {
		  return;
		  
	  }
	  Vector<Trade> rollV = new Vector<Trade>();
	  for(int i=0;i<trades.size();i++) {
		  Trade tdata =(Trade) trades.get(i);
		  Trade sdata =(Trade) trades.get(i);
		 
		  if(tdata.getType().equalsIgnoreCase("BUY/SELL")) {
			  tdata.setType("BUY");
			
			  sdata.setType("SELL");
			 
			 
			  
		  } else if(tdata.getType().equalsIgnoreCase("SELL/BUY")) {
			  tdata.setType("SELL");
			  sdata.setType("BUY");
		  }
		  sdata.setQuantity(sdata.getTradeAmount());
		  sdata.setNominal(sdata.getYield());
		  sdata.setPrice(sdata.getSecondPrice());
		  rollV.add(tdata);
		  if(tdata.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
			  rollV.add(sdata);
		  }
		  
	  }
	  TableModelUtil model = new TableModelUtil(rollV, col);
	  jTable0.setModel(model);
  }

}
