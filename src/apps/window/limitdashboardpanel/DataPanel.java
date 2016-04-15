package apps.window.limitdashboardpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import util.common.DateU;
import apps.window.operationwindow.jobpanl.FilterValues;
import beans.FilterBean;
import beans.Task;
import beans.Users;

public class DataPanel extends JPanel {
	
	JPanel jPanel6 = null;
	JScrollPane jScrollPane2 = null;
	JTable  jTable2 = null;
	JPopupMenu  jPopupMenu0 = null;
	JMenuItem jMenuItem0  = null;
	JMenuItem subTaskJMenuItem2 = null;
	JMenuItem jMenuItem1  = null;
	JMenu jMenuItem2  = null;
	JMenu jMenuItem3  = null;
	JMenu jMenuItem4  = null;
	JMenuItem jMenuItem5  = null;
	JMenuItem jMenuItem6  = null;
	JMenuItem jMenuItem7  = null;
	Vector<Task> myData = null;
	JMenuItem subTaskJMenuItem1 = null;
	DateU startDate = null;
	LimitFilterValues filter = null;
	DateU endDate = null;
	TableModelUtil model = null;
	String name = "";
	boolean lock = false;
	String cols [] = {"LimitID","LimitType","LimitName","LimitMax","LimitMin","AmountUsed","StarDate","ExpiryDate","Warning","Threshold"};
	Users user = null;
	public DataPanel(String name,Vector<Task> myData,LimitFilterValues filter) {
		this.name = name;
		this.filter = filter;
		this.myData = myData; 
		//jPopupMenu0 = getJPopupMenu0();
		
	
	}
	
	public JPanel getJPanel6() {
		if (jPanel6 == null) {
			
			jPanel6 = new JPanel();
		//	jPanel6.setBorder(new LineBorder(Color.black, 1, false));
			jPanel6.setLayout(new BorderLayout());
			jPanel6.add(getJScrollPane2(),BorderLayout.CENTER);
		
		}
		return jPanel6;
	}
	private JScrollPane getJScrollPane2() {
		
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}
	
private JTable getJTable2() {
		
		if (jTable2 == null) {
			jTable2 = new JTable(){
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
				{
					Component c = super.prepareRenderer(renderer, row, column);

					//  Color row based on a cell value

					if (!isRowSelected(row))
					{
						c.setBackground(getBackground());
						int modelRow = convertRowIndexToModel(row);
						String type = (String)getModel().getValueAt(modelRow, 12);
						Color colr = new Color(112,231,222);
						if ("Lock".equals(type)) c.setBackground(colr);
						//if ("UNLOCK".equals(type)) c.setBackground(Color.RED);
					
					}

					return c;
				}
			};


			jTable2.getTableHeader().setReorderingAllowed(false);
			jTable2.getTableHeader().setResizingAllowed(true);
			//model = new TableModelUtil(myData, col, filter);
			
		//	jTable2.setModel(model);
		//	jTable2.setComponentPopupMenu(getJPopupMenu0());
		}jTable2.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				
			}
		});
		return jTable2;
	}

			public DateU getStartDate() {
				return startDate;
			}
			
			public void setStartDate(DateU startDate) {
				this.startDate = startDate;
			}
			
			public DateU getEndDate() {
				return endDate;
			}
			
			public void setEndDate(DateU endDate) {
				this.endDate = endDate;
			}

			Vector<FilterBean> jobdetails = null;
public Vector<Task> getMyData() {
	return myData;
}
public void setMyData(Vector<Task> myData) {
	this.myData = myData;
}
public LimitFilterValues getFilter() {
	return filter;
}

public void setJobdetails(Vector<FilterBean> jobdetails) {
	this.jobdetails = jobdetails;
}
public void setFilter(LimitFilterValues filter) {
	this.filter = filter;
}
public void setDataCreteria(Vector<Task> data, LimitFilterValues filtersValues) {
	
	setMyData(data);
	setFilter(filtersValues);
	model = new TableModelUtil(data, cols, filtersValues);
	jTable2.setModel(model);
	// TODO Auto-generated method stub
	
}


public void setUser(Users user2) {
	this.user = user2;
	// TODO Auto-generated method stub
	
}
public Users getUser() {
	return user;
	// TODO Auto-generated method stub
	
}
			class TableModelUtil extends AbstractTableModel {
				final String[] columnNames;  
				LimitFilterValues  limitFilters ;
				Vector<Task> data;  
				 public TableModelUtil( Vector<Task> myData,String col [],LimitFilterValues filterV ) {   
					 	this.columnNames = col;
					this.data = myData;   
					this.limitFilters = filterV;
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
				 
				     Task task = (Task) data.get(row);
				     
					 switch (col) {
				     case 0:
				         value = task.getId();
				         break;
				     case 1:
				         value =task.getTradeID();
				         break;
				     case 2:
				    	 value =task.getTransferID();
				         break;
				     case 3:
				         value =task.getAction();
				         break;
				     case 4:
				         value = task.getStatus();
				         break;
				     case 5:
				         value =task.getEvent_type();
				         break;
				     case 6:
				         value = task.getProductID();
				         break;
				     case 7:
				         value =task.getBookid();
				         break;
				     case 8:
				         value =task.getTaskDate();
				         break;
				     case 9:
				         value =task.getCurrency();
				         break;
				     case 10:
				         value =task.getTradeDate();
				         break;
				     case 11:
				         value =task.getUserid();
				         break;
				     case 12:
				    	 if(task.getTaskstatus().equalsIgnoreCase("1"))
				         value ="Lock";
				    	 else
				    		 value ="UnLock";
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
				         if(value instanceof Task) {
				     data.set(row,(Task) value) ;
				     this.fireTableDataChanged();   
				         System.out.println("New value of data:");   
				         }
				        
				 }   
				    
				 public void addRow(Object value) {   
				    
					 data.add((Task) value) ;
					
				 this.fireTableDataChanged();   
				   
				 }   
				    
				 public void delRow(int row) {   
				    if(row != -1) {
				 data.remove(row);          
				 this.fireTableDataChanged();   
				    }
				    
				 }   
				 
				 public void setValueAt(Object value, int row) {   
			         System.out.println("Setting value at " + row + ","  + value   
			                            + " (an instance of "    
			                            + value.getClass() + ")");  
			         if(value instanceof Task) {
			     data.set(row,(Task) value) ;
			     this.fireTableDataChanged();   
			         System.out.println("New value of data:");   
			         }
			        
			 }   
				 
				 public void udpateValueAt(Object value, int row, int col) {   
				     
				  
				     data.set(row,(Task) value) ;
				     for(int i=0;i<columnNames.length;i++)
				    	 	fireTableCellUpdated(row, i);   
				    
				}   
				    
				  /*  private LegalEntity getLeName(int leID) {
				    	LegalEntity le = null;
				    	try {
						//	le = filterV.
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	return le;
				    } */
				    
				    public void removeALL() {
				    	if(data != null) {
				  	  data.removeAllElements();
				    	} 
				    data = null;
				  	 this.fireTableDataChanged();  
				    }
			
				
			}
			

	

}
