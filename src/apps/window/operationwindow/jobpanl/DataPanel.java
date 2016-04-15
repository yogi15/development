package apps.window.operationwindow.jobpanl;

import swingUtils.XTableColumnModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.jidesoft.grid.SortableTable;

import util.RemoteServiceUtil;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import util.common.DateU;

import dsEventProcessor.TaskEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;

import apps.window.tradewindow.JFrameTradeWindowApplication;
import apps.window.tradewindow.TradeApplication;
import apps.window.util.windowUtil.JDialogBoxForChoice;
import apps.window.util.windowUtil.JDialogBoxForDualChoice;
import beans.Book;
import beans.FilterBean;
import beans.Task;
import beans.Trade;
import beans.Transfer;
import beans.Users;
import beans.WFConfig;

public class DataPanel extends JPanel {
	
	JPanel jPanel6 = null;
	JScrollPane jScrollPane2 = null;
	SortableTable  jTable2 = null;
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
	RemoteTrade remoteTrade = null;
	RemoteBOProcess remoteTranfer = null;
	RemoteTask remoteTask = null;
Vector<String> leftSideColumn = null;
	DateU endDate = null;
	Vector<FilterBean> jobdetails = null;
	 final XTableColumnModel columnModel    = new XTableColumnModel();
     
	TableModelUtil model = null;
	String name = "";
	boolean lock = false;                           
	FilterValues filter = null;
	String col [] = {"Task ID","Trade Id","Transfer ID" ,"Action","Status","EventType","Product","Book","Task Date","Currency","Trade Date","User","TaskStatus"};
	Users user = null;
	public DataPanel(String name,Vector<Task> myData,FilterValues filter) {
		this.name = name;
		this.filter = filter;
		this.myData = myData; 
		jPopupMenu0 = getJPopupMenu0();
		leftSideColumn = new Vector<String>();
	//	this.remoteTrade = remoteTrade;
	//	this.
		//setFilter(filter);
		//setMyData(myData);
	}
	
	/**
	 * @return the leftSideColumn
	 */
	public Vector<String> getLeftSideColumn() {
		return leftSideColumn;
	}

	/**
	 * @param leftSideColumn the leftSideColumn to set
	 */
	public void setLeftSideColumn(Vector<String> leftSideColumn) {
		this.leftSideColumn = leftSideColumn;
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
			jTable2 = new SortableTable(){
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

		//	final swingUtils.XTableColumnModel columnModel = new swingUtils.XTableColumnModel();
	        
			jTable2.setColumnModel(columnModel);
			jTable2.createDefaultColumnsFromModel();

			jTable2.getTableHeader().setReorderingAllowed(false);
			jTable2.getTableHeader().setResizingAllowed(true);
			model = new TableModelUtil(myData, col, filter);
			
			jTable2.setModel(model);
			JTableHeader header = jTable2.getTableHeader();
			header.addMouseListener(new TableHeaderMouseListener(jTable2,col));
		//	jTable2.setComponentPopupMenu(getJPopupMenu0());
		}  
		
		jTable2.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(SwingUtilities.isRightMouseButton(e) == true) 	 {
					if(name.equalsIgnoreCase("NETTINGTRANSFERS")) {
						jMenuItem0.setEnabled(false);
						jMenuItem1.setEnabled(false);
						jMenuItem2.setEnabled(false);
						jMenuItem3.setEnabled(false);
						jMenuItem4.setEnabled(false);
						
					}
					int selectRow = jTable2.getSelectedRow();
					if(selectRow == -1)
						return;
					
					
					int tradeID = ((Integer) model.getValueAt(selectRow, 1)).intValue();
					int transferID = ((Integer) model.getValueAt(selectRow, 2)).intValue();
					if(transferID != 0) {
					
					Transfer transfer = filter.getTransfer(transferID);
					Vector WFConfigs = (Vector)filter.getActionOnTransfer(transfer.getId());
	            	fillActioninPopupMenu(jMenuItem4,WFConfigs,transfer,getUser().getId(),jTable2.getSelectedRow());
					} else {
						jMenuItem4.removeAll();
					}
					
					if(tradeID != 0) {
						
						Trade trade = filter.getTrade(tradeID);
						Vector WFConfigs = (Vector)filter.getActions(trade);
		            	fillActioninPopupMenu(jMenuItem2,WFConfigs,trade,getUser().getId(),jTable2.getSelectedRow());
						} else {
							jMenuItem2.removeAll();
						}
						
					Task task = myData.get(selectRow);
					if(task.getTaskstatus().equalsIgnoreCase("1")) {
						subTaskJMenuItem1.setEnabled(false);
						subTaskJMenuItem2.setEnabled(true);
					} 
					if(task.getTaskstatus().equalsIgnoreCase("0")) {
						subTaskJMenuItem1.setEnabled(true);
						subTaskJMenuItem2.setEnabled(false);
					}
	            	jPopupMenu0.show(e.getComponent(), e.getX(), e.getY());	
				}
				
			}
			
		});
		return jTable2;
	}
private JPopupMenu getJPopupMenu0() {
		
		if (jPopupMenu0 == null) {
			jPopupMenu0 = new JPopupMenu();
			jPopupMenu0.add(getJMenuItem0());
			jPopupMenu0.add(getJMenuItem1());
			jPopupMenu0.add(getJMenuItem2());
			jPopupMenu0.add(getJMenuItem3());
			jPopupMenu0.add(getJMenuItem4());
			jPopupMenu0.add(getJMenuItem5());
		//	jPopupMenu0.add(getJMenuItem4());
		//	jPopupMenu0.add(getJMenuItem5());
		//	jPopupMenu0.add(getJMenuItem6());
		}
		return jPopupMenu0;
	}
	private JMenuItem getJMenuItem0() {
		
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("Trade");
			
		//	jMenuItem0.setOpaque(false);
		}jMenuItem0.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	int selectRow = jTable2.getSelectedRow();
				int i = (Integer) jTable2.getValueAt(selectRow, 1);
				Task task = myData.get(selectRow);
				Trade trade = task.getTrade();
				if(trade == null) {
					trade = filter.getTrade(task.getTradeID());
				}
				//JFrameTradeWindowApplication tradeWindow = new JFrameTradeWindowApplication(trade.getProductType(),getUser());
				TradeApplication tradeWindow = new TradeApplication(trade.getProductType(),getUser());
				tradeWindow.setTitle(trade.getProductType() + " Trade Window : " + getUser().getUser_name() + ":" + getUser().getId());
	        	tradeWindow.setUserName(user);
	        	tradeWindow.setOpenTrade(trade);
	        //	tradeWindow
	           tradeWindow.setVisible(true);
				
	        }
});		
		return jMenuItem0;
	}

private JMenuItem getJMenuItem1() {
		
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Transfer");
			
		//	jMenuItem0.setOpaque(false);
		}jMenuItem1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	int selectRow = jTable2.getSelectedRow();
				int i = (Integer) jTable2.getValueAt(selectRow, 2);
				Task task = myData.get(selectRow);
				Transfer transfer  = task.getTransfer();
				if(transfer == null) {
					transfer = filter.getTransfer(task.getTransferID());
				}
				TransferPanel transferW = new TransferPanel(transfer);
	        	
	        //	tradeWindow
		//		transferW.setSize(200, 700);
				transferW.setEnabled(true);
				transferW.setVisible(true);
				JFrame frame = new JFrame("Transfer");
				frame.add(transferW);
				frame.setLocationRelativeTo(frame);
				frame.setLocation(200, 200);
				frame.setFocusable(false);
				frame.setVisible(true);
				frame.setSize(950, 350);
				
				
				
	        }
});		
		return jMenuItem1;
	}
private JMenuItem getJMenuItem2() {
	
	if (jMenuItem2 == null) {
		jMenuItem2 = new JMenu();
		jMenuItem2.setText("TradeAction");
	     if(name.equalsIgnoreCase("NETTINGTRANSFERS")) 
	        jMenuItem2.setEnabled(false); 
		
	//	jMenuItem0.setOpaque(false);
	}
	return jMenuItem2;
}
private JMenuItem getJMenuItem4() {
	
	if (jMenuItem4 == null) {
		jMenuItem4 = new JMenu();
		jMenuItem4.setText("TransferAction");
		 if(name.equalsIgnoreCase("NETTINGTRANSFERS")) 
		        jMenuItem2.setEnabled(false); 
	//	jMenuItem0.setOpaque(false);
	}
	
	return jMenuItem4;
}

private JMenuItem getJMenuItem5() {
	
	if (jMenuItem5 == null) {
		jMenuItem5 =new JMenuItem();
		jMenuItem5.setText("NettingTransfer");
		
	//	jMenuItem0.setOpaque(false);
	}jMenuItem5.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
        	int selectRow = jTable2.getSelectedRow();
			int i = (Integer) jTable2.getValueAt(selectRow, 2);
			Task task = myData.get(selectRow);
			Transfer transfer = null;
			if(task.getTransfer() == null) {
				transfer = filter.getTransfer(task.getTransferID());
			} else {
				transfer = task.getTransfer();
			}
			
			NettingFrame transferW = new NettingFrame();
			transferW.setFilter(filter);
			transferW.setUsers(getUser());
			//remoteTranfer.getTransfer()
			Vector nettedTransfer = new Vector();
			nettedTransfer.add(transfer);
			transferW.fillJTabel(nettedTransfer, task);
        	
        //	tradeWindow
	//		transferW.setSize(200, 700);
			transferW.setEnabled(true);
			transferW.setVisible(true);
			
			transferW.setLocationRelativeTo(transferW);
			transferW.setLocation(200, 200);
			transferW.setFocusable(false);
			transferW.setVisible(true);
			transferW.setSize(950, 350);
			
			
			
        }
});	
	
	return jMenuItem5;
}
protected void fillActioninPopupMenu(JMenuItem menuItem,Vector onlyAction, final Transfer transfer,final int userID,final int row) {
	// TODO Auto-generated method stub
	
	menuItem.removeAll();
		if(onlyAction == null || onlyAction.isEmpty())
			return;
		JMenuItem actionItem [] = new JMenuItem[onlyAction.size()];
		 
		for(int a=0;a<onlyAction.size();a++) {
			WFConfig wf = (WFConfig)onlyAction.get(a);
			actionItem[a] = new JMenuItem(wf.getAction());
			
			menuItem.add(actionItem[a]);
			actionItem[a].addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent arg0) {
			        	int selectRow = jTable2.getSelectedRow();
						String checkTaskLock = (String) jTable2.getValueAt(selectRow, 12);
						if(checkTaskLock.equalsIgnoreCase("UnLock")) {
							commonUTIL.showAlertMessage("Please Mark Task to Lock");
							return;
						}
			        	 String action = arg0.getActionCommand().toString();
			        	 transfer.setAction(action);
			     	updateTransferOnAction(transfer, userID, row);
			        }
		});			// TODO Auto-generated method stub
		
		} 

	
}


protected void fillActioninPopupMenu(JMenuItem menuItem,Vector onlyAction, final Trade trade,final int userID,final int row) {
	// TODO Auto-generated method stub
	
	menuItem.removeAll();
		if(onlyAction == null || onlyAction.isEmpty())
			return;
		JMenuItem actionItem [] = new JMenuItem[onlyAction.size()];
		for(int a=0;a<onlyAction.size();a++) {
			WFConfig wf = (WFConfig)onlyAction.get(a);
			actionItem[a] = new JMenuItem(wf.getAction());
			menuItem.add(actionItem[a]);
			actionItem[a].addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent arg0) {
			        	int selectRow = jTable2.getSelectedRow();
						String checkTaskLock = (String) jTable2.getValueAt(selectRow, 12);
						if(checkTaskLock.equalsIgnoreCase("UnLock")) {
							commonUTIL.showAlertMessage("Please Mark Task to Lock");
							return;
						}
			        	 String action = arg0.getActionCommand().toString();
			        	 trade.setAction(action);
			        	 updateTradeOnAction(trade, userID, row);
			        }
		});			// TODO Auto-generated method stub
		
		} 

	
}


protected void updateTradeOnAction(Trade trade, int userID,int row) {
	// TODO Auto-generated method stub
	
		Vector<String> mess = filter.updateTraderAndPublissh(trade,userID);
		if(!commonUTIL.isEmpty(mess) && mess.size() == 1) {
			
 			commonUTIL.showAlertMessage(mess.elementAt(0));
    			return ;
    		 }
		model.delRow(row);
		jTable2.repaint();
		
	
}

protected void updateTransferOnAction(Transfer transfer, int userID,int row) {
	// TODO Auto-generated method stub
	
		filter.updateTransferAndPublissh(transfer,userID);
		
	//	model.delRow(row);
		jTable2.repaint();
		
	
}
private JMenu getJMenuItem3() {
	
	if (jMenuItem3 == null) {
		jMenuItem3 = new JMenu();
		jMenuItem3.setText("TaskAction");
		jMenuItem3.add(getSubTaskJMenuItem1());
		jMenuItem3.add(getSubTaskJMenuItem2());
		jMenuItem3.add(getSubTaskJMenuItem3());
		
	//	jMenuItem0.setOpaque(false);
	}
	return jMenuItem3;
}

private JMenuItem getSubTaskJMenuItem1() {
	
	if (subTaskJMenuItem1 == null) {
		subTaskJMenuItem1 = new JMenuItem();
		subTaskJMenuItem1.setText("Lock Task");
		if(lock)
			subTaskJMenuItem1.setEnabled(false);
	//	subTaskJMenuItem2.setEnabled(true);
		if(!lock) {
			subTaskJMenuItem1.setEnabled(true);
		//	subTaskJMenuItem2.setEnabled(false);
		}
	//	jMenuItem0.setOpaque(false);
	}subTaskJMenuItem1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
       	 String action = arg0.getActionCommand().toString();
       	               int selectRow = jTable2.getSelectedRow();
       	            Task task =  myData.get(selectRow);
       	            task.setUserid(getUser().getId());
      	            if(task.getTaskstatus().equalsIgnoreCase("0")) {
      	            	 task.setTaskstatus("1");
      	            	subTaskJMenuItem2.setEnabled(true);
      	            	filter.savePublishTask(task,"1");
      	            }
      	          if(task.getTaskstatus().equalsIgnoreCase("1")) {
      	        	subTaskJMenuItem1.setEnabled(false);
   	            }
       }
});			
	return subTaskJMenuItem1;
}
private JMenuItem getSubTaskJMenuItem2() {
	
	if (subTaskJMenuItem2 == null) {
		subTaskJMenuItem2 = new JMenuItem();
		subTaskJMenuItem2.setText("UnLock Task");
		if(lock) {
			//subTaskJMenuItem1.setEnabled(false);
		
			subTaskJMenuItem2.setEnabled(true);
		} 
		if(!lock) {
		//	subTaskJMenuItem1.setEnabled(true);
			subTaskJMenuItem1.setEnabled(false);
		}
	//	jMenuItem0.setOpaque(false);
	}subTaskJMenuItem2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          	 String action = arg0.getActionCommand().toString();
          	               int selectRow = jTable2.getSelectedRow();
          	            Task task =  myData.get(selectRow);
          	            if(task.getTaskstatus().equalsIgnoreCase("1")) {
          	            	 task.setTaskstatus("0");
          	            	subTaskJMenuItem1.setEnabled(true);
          	          	filter.savePublishTask(task,"0");
          	            }
          	          if(task.getTaskstatus().equalsIgnoreCase("0")) {
          	        	subTaskJMenuItem2.setEnabled(false);
       	            }
          }
   });		
	return subTaskJMenuItem2;
}
private JMenuItem getSubTaskJMenuItem3() {
	JMenuItem subTaskJMenuItem3 = null;
	if (subTaskJMenuItem3 == null) {
		subTaskJMenuItem3 = new JMenuItem();
		subTaskJMenuItem3.setText("Completed");
		
	//	jMenuItem0.setOpaque(false);
	}
	return subTaskJMenuItem3;
}

   
	public boolean checkTaskforDisplay(Vector<FilterBean> jobdetails,Task task,DateU startDate,DateU endDate) {
		// TODO Auto-generated method stub
		//if(!name.toUpperCase().contains(task.getType().trim())) 
		//	return false;
		boolean flag = false;
		String where  ="";
		Task sqlWhere = new Task();
		if(jobdetails == null || jobdetails.size() == 0 || jobdetails.isEmpty()) 
			return flag;
		flag = checkTaskExistForSQLCriertia(task,jobdetails);
			return flag;
		
		
	}

	
	private boolean checkTaskExistForSQLCriertia(Task task,Vector<FilterBean> jobdetails) {
		boolean flag = true;
		DateU taskDate = DateU.valueOf(commonUTIL.stringToDate(task.getTaskDate(), true));
	//	if(!name.toUpperCase().contains(task.getType())) 
	//		return false;
		try {
		for(int i=0;i<jobdetails.size();i++) {
			
			
			FilterBean bean = (FilterBean) jobdetails.get(i);
			   if(bean.getColumnName().equalsIgnoreCase("Book")) {
				   if(!checkonBook(bean.getIdSelected(),task.getBookid())) {
					   return false;
				   } 
			   }
				   if(bean.getColumnName().equalsIgnoreCase("ProductType")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getProductType()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("Currency")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getCurrency()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("Type")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getType()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("WFType")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getWFType()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("Action")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getAction()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("Status")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getStatus()))
							   return false;
				   }
				   if(bean.getColumnName().equalsIgnoreCase("EventType")) {
					   if(!checkonBasicCritera(bean.getColumnValues(),task.getEvent_type()))
							   return false;
				   }

if(bean.getColumnName().equalsIgnoreCase("TaskDate")) {
					   	if(endDate != null) {
						
						DateU endDat = DateU.valueOf(commonUTIL.stringToDate(bean.getAnd_or(), true));	
						if(taskDate.lte(endDat))
							flag = true;
					}
					   DateU startD = DateU.valueOf(commonUTIL.stringToDate(bean.getColumnValues(), true));	
						if(taskDate.gte(startD)) 
							flag = true;
				   }
			   
		}
	}catch(ArrayIndexOutOfBoundsException  e) {
		System.out.println("DataPanel checkExistsTask "+e);
		return flag;
	
	}
		return flag;
	}
	
	
	private boolean checkonBasicCritera(String values,String value) {
		boolean flag = false;
		if(values.contains(","))  {
			String s [] = values.split(",");
			for(int v=0;v<s.length;v++) {
				if(s[v].trim().equalsIgnoreCase(value.trim()))  {
					flag = true;
					break;
				}
			}
		} else  {
		
		    if(values.equalsIgnoreCase(value))
		    	flag = true;
		}
		    
		    return flag;
		
	}
	
	
	private boolean checkonBook(String values,int bookID) {
		boolean flag = false;
		if(values.contains(","))  {
			String s [] = values.split(",");
			for(int v=0;v<s.length;v++) {
				int val =  new Integer(s[v].trim()).intValue();
				if(val == bookID)  {
					flag = true;
					break;
				}
			}
		} else  {
			int i = new Integer(values).intValue();
		    if(i == bookID)
		    	flag = true;
		}
		    
		    return flag;
		
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

	public Vector<FilterBean> getJobdetails() {
		return jobdetails;
	}

	public void setJobdetails(Vector<FilterBean> jobdetails) {
		this.jobdetails = jobdetails;
	}
class TableModelUtil extends AbstractTableModel {   
    
	 final String[] columnNames;  
	    
	 Vector<Task> data;   
	 FilterValues remoteRef ;
	        
	 public TableModelUtil( Vector<Task> myData,String col [],FilterValues filterV ) {   
	 	this.columnNames = col;
	this.data = myData;   
	this.remoteRef = filterV;
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
	         value =     task.getProductType();
	         break;
	     case 7:
	        Book book = ReferenceDataCache.getBook(task.getBookid());
	        if(book != null)
	         value = book.getBook_name();
	        else 
	        	value = "0";
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
	    	 Users user = ReferenceDataCache.getUser(task.getUserid());
		        if(user != null)
		         value = user.getUser_name();
		        else 
		        	value = "0";
	       
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
	           
	         if(value instanceof Task) {
	     data.set(row,(Task) value) ;
	     this.fireTableDataChanged();   
	          
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
public Vector<Task> getMyData() {
	return myData;
}
public void setMyData(Vector<Task> myData) {
	this.myData = myData;
}
public FilterValues getFilter() {
	return filter;
}
public void setFilter(FilterValues filter) {
	this.filter = filter;
}
public void setDataCreteria(Vector<Task> data, FilterValues filtersValues) {
	
	setMyData(data);
	setFilter(filtersValues);
	model = new TableModelUtil(data, col, filtersValues);
	jTable2.setModel(model);
	// TODO Auto-generated method stub
	
}

public void setDBDataCreteria(Vector<Task> data, FilterValues filtersValues) {
	
	setMyData(data);
	setFilter(filtersValues);

	// TODO Auto-generated method stub
	
}

public synchronized void addtaskData(TaskEventProcessor task) {
	// TODO Auto-generated method stub
	//if(!name.equalsIgnoreCase(task.getTask().getType())) 
	//	return;
	Task t1 = task.getTask();
	 int id = checkExistsTask(t1); // check if task
	// System.out.println(myData.size());
	// System.out.println(id);
	/* if(t1.getTaskstatus().equalsIgnoreCase("2"))  {
		
			 model.delRow(id);
			 model.fireTableDataChanged();
				jTable2.repaint();
		 return;
	 }*/
	 try {
	 boolean flag = checkTaskforDisplay(getJobdetails(),t1,startDate,endDate);
	if(flag) {		 
		if(id  >0) {
			
				model.setValueAt(t1, id);
				model.fireTableDataChanged();
				jTable2.repaint();
			
		}
		if(id == -1) {
			     t1.setTrade(task.getTrade());
			     t1.setTransfer(task.getTransfer());
		           model.addRow(t1);
		           model.fireTableDataChanged();
		           jTable2.repaint();
		}
		
	} 
	if(!flag)  {
		
		if(  id >= 0) {
			model.delRow(id);
			model.fireTableDataChanged();
			jTable2.repaint();
	}
	}
	 }catch(ArrayIndexOutOfBoundsException  e) {
			System.out.println("DataPanel addtaskData "+e);
			 
		
		}
	
}

   // this code need to changed when any new Processor is added. 
private int checkExistsTask(Task task) {
	int checkID = -1;
	try {
	for(int i=0;i<model.data.size();i++) {
		Task t = model.data.get(i);
		//System.out.println(t.getId());
		if(t.getType().equalsIgnoreCase(task.getType() )) {
			if(t.getType().equalsIgnoreCase("TRADE") && t.getTradeID() == task.getTradeID()) {
			checkID = i;
			break;
			}
			if(t.getType().equalsIgnoreCase("TRANSFER")  && t.getTransfer() == task.getTransfer()) {
				checkID = i;
				break;
				}
			if(t.getType().equalsIgnoreCase("MESSAGE") && t.getMessageID() == task.getMessageID() ) {
				checkID = i;
				break;
				}
		}
	}
	}catch(ArrayIndexOutOfBoundsException  e) {
		System.out.println("DataPanel checkExistsTask "+e);
	
	}
	return checkID;
}

public void setUser(Users user2) {
	this.user = user2;
	// TODO Auto-generated method stub
	
}
public Users getUser() {
	return user;
	// TODO Auto-generated method stub
	
}
public class TableHeaderMouseListener extends MouseAdapter { 
	Vector<String> RvectorI = null; 
	JDialogBoxForDualChoice  jdal = null;
	/**
	 * @return the rvectorI
	 */
	public Vector<String> getRvectorI() {
		return RvectorI;
	}

	/**
	 * @param rvectorI the rvectorI to set
	 */
	public void setRvectorI(Vector<String> rvectorI) {
		this.RvectorI = rvectorI;
	}

	/**
	 * @return the lvectorI
	 */
	public Vector<String> getLvectorI() {
		return lvectorI;
	}

	/**
	 * @param lvectorI the lvectorI to set
	 */
	public void setLvectorI(Vector<String> lvectorI) {
		this.lvectorI = lvectorI;
	}

	Vector<String> lvectorI = null;
	boolean isDialogOpen = false;
	/**
	 * @return the isDialogOpen
	 */
	public boolean isDialogOpen() {
		return isDialogOpen;
	}

	/**
	 * @param isDialogOpen the isDialogOpen to set
	 */
	public void setDialogOpen(boolean isDialogOpen) {
		this.isDialogOpen = isDialogOpen;
	}
	public void setFocusOnDialog( ) {
		 if(jdal != null) {
		//	 jdal.setFocusableWindowState(true);
			// jdal.set
		jdal.setFocusable(true);
		 
		 }
	}
	public void setChoiceDialogF(JDialogBoxForDualChoice  jdialog) {
		 
		this.jdal = jdialog;
	}
	public TableHeaderMouseListener(JTable jTable2,String []cols) {
		// TODO Auto-generated constructor stub
		RvectorI = new Vector<String>(Arrays.asList(cols));
		lvectorI = new Vector<String>();
	 
		columnModel.setLTableColumns(lvectorI);
	}

	public void mouseClicked(MouseEvent event) {         // do something when mouse clicked...     }
		String[] cols = col;
		
		if(isDialogOpen) {
			setFocusOnDialog( );
			return;
		}
		if(event.getClickCount() == 2) {
			 
			Vector<String> Rvector =new Vector<String>(Arrays.asList(cols));
				if(!commonUTIL.isEmpty(columnModel.getUnVisibleColumn())) {
					
					Vector<String> unV = columnModel.getUnVisibleColumn();
					for(int i=0;i<unV.size();i++) {
						if(RvectorI.contains(unV.get(i))) 
							Rvector.remove(unV.get(i));
					}
					
					
					lvectorI =columnModel.getUnVisibleColumn();
				}
				lvectorI.add(0, "Hidden Columns");
		 
		Vector<String> lvector = lvectorI;
		final JDialogBoxForDualChoice choice12 = new JDialogBoxForDualChoice(  lvector,Rvector);
		choice12.setLocationRelativeTo(choice12);
		
		//choice12.setSize(200,200);
	//	choice12.jList3.setModel(templates);
		setDialogOpen(true);
		choice12.setVisible(true);
		
		
		choice12.addWindowListener(new WindowAdapter() {            
            public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
            	 Vector<String> lvector = choice12.getLeftObj();
            	 Vector<String> rerangeIndex = choice12.getRightObj();
            	 if(!commonUTIL.isEmpty(lvector)) {
            		 columnModel.setAllColumnsVisible();
            		 columnModel.clearUnVisableColumns();
            		 for(int i=0;i<lvector.size();i++) {
            			 int index = columnModel.getColumnIndexOnName(lvector.get(i));
            		if( index >= 0){
            			
            			TableColumn column  = columnModel.getColumnByModelIndex(index);
            			 
            			boolean     visible = columnModel.isColumnVisible(column);
            			if(visible)
            			columnModel.setColumnVisible(column, !visible);
            			 
            		 }
            		 }
            		 for(int i=rerangeIndex.size()-1;i>0;i--) {
            			 int index = columnModel.getColumnIndexOnName(rerangeIndex.get(i));
            			 if( index >= 0){
            				 TableColumn column  = columnModel.getColumnByModelIndex(index);
            				 columnModel.moveColumn(columnModel.getColumnCount() -1 , i);
            			 }
            			 
            		 }
            		 
            		 
            		 if(lvector.size() == 1) {
            			 columnModel.clearUnVisableColumns();
            		 }
            		 
            	 }
                 jdal = null;
                 isDialogOpen = false;
				 choice12.ClearALL();
				choice12.dispose();
			}  

			
		});
		choice12.setLocationRelativeTo(choice12);
		choice12.setVisible(true);
		
		
		}  
	}
	}


}
