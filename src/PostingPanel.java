package apps.window.tradewindow.panelWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import beans.Account;
import beans.Posting;
import dsServices.RemoteAccount;
import dsServices.RemoteReferenceData;
import apps.window.tradewindow.BackOfficePanel;
import beans.Posting;

//VS4E -- DO NOT REMOVE THIS LINE!
public class PostingPanel  extends BackOfficePanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JLabel jLabel3;
	private JTextField jTextField3;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel0;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JTextField jTextField4;
	private JTextField jTextField7;
	private JTextField jTextField6;
	private JTextField jTextField5;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JTextField jTextField8;
	private JTextField jTextField9;
	private JTextField jTextField10;
	private JTextField jTextField11;
	private JLabel jLabel12;
	private JTextField jTextField12;
	private JLabel jLabel13;
	private JTextField jTextField13;
	private JLabel jLabel14;
	private JTextField jTextField14;
	private JLabel jLabel15;
	private JTextField jTextField15;
	private RemoteAccount remoteAccount;
	Vector<Posting> data = new Vector<Posting>();
	 java.util.Hashtable<Integer,String> accontName = new Hashtable();
	 TableModelUtil model = null;
	  String cols [] = {  "Posting ID", "tradeID","transferId","EventType","AccEventType","Type","linkId","Currency","EffectiveDate","BookingDate","CreationDate","Status","RuleName","DebitAcc","creditAcc","DebitAmt","CreditAmt"};
	private RemoteReferenceData remoteRef;
	public PostingPanel() {
		initComponents();
	}

	private void initComponents() {
		setFocusable(true);
		setEnabled(true);
		setVisible(true);
		setDoubleBuffered(true);
		setVerifyInputWhenFocusTarget(true);
		setRequestFocusEnabled(true);
		setOpaque(true);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(14, 848, 12, 12), new Leading(229, 168, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Bilateral(14, 12, 22), new Leading(12, 211, 12, 12)));
		setSize(1030, 402);
	}

	private JTextField getJTextField15() {
		if (jTextField15 == null) {
			jTextField15 = new JTextField();
			//jTextField15.setText("jTextField15");
		}
		return jTextField15;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("CreditAmt");
		}
		return jLabel15;
	}

	private JTextField getJTextField14() {
		if (jTextField14 == null) {
			jTextField14 = new JTextField();
		//	jTextField14.setText("jTextField14");
		}
		return jTextField14;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("CreditA/C");
		}
		return jLabel14;
	}

	private JTextField getJTextField13() {
		if (jTextField13 == null) {
			jTextField13 = new JTextField();
			//jTextField13.setText("jTextField13");
		}
		return jTextField13;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("DebitAmt");
		}
		return jLabel13;
	}

	private JTextField getJTextField12() {
		if (jTextField12 == null) {
			jTextField12 = new JTextField();
		//	jTextField12.setText("jTextField12");
		}
		return jTextField12;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("DebitA/C");
		}
		return jLabel12;
	}

	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
		//	jTextField11.setText("jTextField11");
		}
		return jTextField11;
	}

	private JTextField getJTextField10() {
		if (jTextField10 == null) {
			jTextField10 = new JTextField();
		//	jTextField10.setText("jTextField10");
		}
		return jTextField10;
	}

	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
	//		jTextField9.setText("jTextField9");
		}
		return jTextField9;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
		//	jTextField8.setText("jTextField8");
		}
		return jTextField8;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Status");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("EffectiveDate");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("CreationDate");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("BookingDate");
		}
		return jLabel8;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			//jTextField5.setText("jTextField5");
		}
		return jTextField5;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		//	jTextField6.setText("jTextField6");
		}
		return jTextField6;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
		//	jTextField7.setText("jTextField7");
		}
		return jTextField7;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
		//	jTextField4.setText("jTextField4");
		}
		return jTextField4;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("RuleName");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Type");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("AccEventType");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("EventType");
		}
		return jLabel4;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel12(), new Constraints(new Leading(5, 66, 10, 10), new Leading(122, 10, 10)));
			jPanel0.add(getJLabel14(), new Constraints(new Leading(5, 66, 10, 10), new Leading(146, 10, 10)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(9, 12, 12), new Leading(8, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(93, 71, 12, 12), new Leading(33, 10, 10)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(93, 71, 12, 12), new Leading(5, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(91, 71, 10, 10), new Leading(59, 10, 10)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(91, 71, 10, 10), new Leading(84, 10, 10)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 49, 12, 12), new Leading(34, 12, 12)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(180, 93, 10, 10), new Leading(6, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(178, 93, 10, 10), new Leading(29, 10, 10)));
			jPanel0.add(getJLabel6(), new Constraints(new Leading(174, 93, 10, 10), new Leading(60, 12, 12)));
			jPanel0.add(getJLabel7(), new Constraints(new Leading(174, 81, 10, 10), new Leading(85, 12, 12)));
			jPanel0.add(getJTextField4(), new Constraints(new Leading(275, 137, 10, 10), new Leading(5, 12, 12)));
			jPanel0.add(getJTextField7(), new Constraints(new Leading(275, 137, 10, 10), new Leading(60, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(5, 66, 10, 10), new Leading(64, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(9, 66, 12, 12), new Leading(88, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(609, 75, 10, 10), new Leading(6, 12, 12)));
			jPanel0.add(getJLabel11(), new Constraints(new Leading(413, 54, 10, 10), new Leading(8, 14, 12, 12)));
			jPanel0.add(getJTextField8(), new Constraints(new Leading(696, 137, 10, 10), new Leading(8, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(607, 77, 10, 10), new Leading(34, 12, 12)));
			jPanel0.add(getJTextField9(), new Constraints(new Leading(696, 137, 10, 10), new Leading(30, 12, 12)));
			jPanel0.add(getJTextField10(), new Constraints(new Leading(696, 137, 10, 10), new Leading(56, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(608, 83, 12, 12), new Leading(58, 14, 12, 12)));
			jPanel0.add(getJTextField5(), new Constraints(new Leading(275, 176, 12, 12), new Leading(32, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(273, 176, 12, 12), new Leading(88, 12, 12)));
			jPanel0.add(getJLabel13(), new Constraints(new Leading(278, 66, 10, 10), new Leading(120, 12, 12)));
			jPanel0.add(getJTextField13(), new Constraints(new Leading(352, 137, 10, 10), new Leading(120, 12, 12)));
			jPanel0.add(getJTextField12(), new Constraints(new Leading(89, 166, 12, 12), new Leading(119, 12, 12)));
			jPanel0.add(getJTextField14(), new Constraints(new Leading(91, 166, 12, 12), new Leading(146, 12, 12)));
			jPanel0.add(getJTextField11(), new Constraints(new Leading(460, 137, 12, 12), new Leading(8, 12, 12)));
			jPanel0.add(getJLabel15(), new Constraints(new Leading(275, 66, 12, 12), new Leading(148, 12, 12)));
			jPanel0.add(getJTextField15(), new Constraints(new Leading(349, 137, 10, 10), new Leading(144, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Transfer ID");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Trade Id");
		}
		return jLabel1;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		//	jTextField3.setText("jTextField3");
		}
		return jTextField3;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Link ID");
		}
		return jLabel3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		//	jTextField2.setText("jTextField2");
		}
		return jTextField2;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			//jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("ID");
		}
		return jLabel0;
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
			
			
			 
			  model = new TableModelUtil(data,cols,remoteAccount);
			  jTable0 = new JTable( model )
				{
					public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
					{
						Component c = super.prepareRenderer(renderer, row, column);

						//  Color row based on a cell value

						if (!isRowSelected(row))
						{
							c.setBackground(getBackground());
							int modelRow = convertRowIndexToModel(row);
							String type = (String)getModel().getValueAt(modelRow, 5);
							if ("REVERSAL".equals(type)) c.setBackground(Color.orange);
						//	if ("Sell".equals(type)) c.setBackground(Color.YELLOW);
						}

						return c;
					}
				};

	//		jTable0.setPreferredScrollableViewportSize(new Dimension(500, 70));
		//	jTable0.setFillsViewportHeight(true);
		//	jTable0.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTable0.getTableHeader().setReorderingAllowed(false);
			jTable0.getTableHeader().setResizingAllowed(true);
		//	for(int i=0;i<cols.length;i++) 
			 jTable0.getColumnModel().getColumn(0).setPreferredWidth(240); 
			 jTable0.getColumnModel().getColumn(1).setPreferredWidth(240); 
			 jTable0.getColumnModel().getColumn(2).setPreferredWidth(240); 
			   jTable0.getColumnModel().getColumn(3).setPreferredWidth(440); 
			   jTable0.getColumnModel().getColumn(4).setPreferredWidth(640); 
			   jTable0.getColumnModel().getColumn(5).setPreferredWidth(340); 
			   jTable0.getColumnModel().getColumn(6).setPreferredWidth(440); 
			   jTable0.getColumnModel().getColumn(7).setPreferredWidth(440); 
			   jTable0.getColumnModel().getColumn(8).setPreferredWidth(470); 
			   jTable0.getColumnModel().getColumn(9).setPreferredWidth(470); 
			   jTable0.getColumnModel().getColumn(10).setPreferredWidth(470); 
			   jTable0.getColumnModel().getColumn(11).setPreferredWidth(440); 
			   jTable0.getColumnModel().getColumn(12).setPreferredWidth(10); 
			   jTable0.getColumnModel().getColumn(13).setPreferredWidth(10); 
			   jTable0.getColumnModel().getColumn(14).setPreferredWidth(10); 
			   jTable0.getColumnModel().getColumn(15).setPreferredWidth(2); 
			   
			 
			
		}jTable0.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = jTable0.getSelectedRow();
				Posting posting = (Posting) data.get(selectRow);
				jTextField1.setText(((Integer) posting.getId()).toString());
				jTextField4.setText((String) posting.getEventType());
				jTextField8.setText((String) posting.getBookingDate());
				jTextField0.setText(((Integer)posting.getTradeID()).toString());
				jTextField5.setText((String) posting.getAccEventType());
				jTextField9.setText((String) posting.getCreationDate());
				jTextField2.setText(((Integer)posting.getTransferId()).toString());
				jTextField7.setText((String) posting.getType());
				jTextField10.setText((String) posting.getEffectiveDate());
				jTextField3.setText(((Integer) posting.getLinkId()).toString());
				jTextField6.setText((String) posting.getRuleName());
				jTextField11.setText((String) posting.getStatus());
				jTextField12.setText(((String) getAccName(posting.getDebitAccId())).toString());
				jTextField13.setText(((Double)  posting.getDebitAmount()).toString());
				jTextField14.setText(((String)getAccName(posting.getCreditAccId())).toString());
				jTextField15.setText(((Double) posting.getCreditAmount()).toString());
				
				
				
			
				
			}
        
        	
        });
		return jTable0;
	}@Override
	public void fillJTabel(Vector data) {
		// TODO Auto-generated method stub
		this.data = data;
		model = new TableModelUtil(data,cols,remoteAccount);
		jTable0.setModel(model);
		
	}
	public RemoteAccount getRemoteAccount() {
		return remoteAccount;
	}

	public void setRemoteAccount(RemoteAccount remoteAccount) {
		this.remoteAccount = remoteAccount;
	}

	public RemoteReferenceData getRemoteRef() {
		return remoteRef;
	}

	public void setRemoteRef(RemoteReferenceData remoteRef) {
		this.remoteRef = remoteRef;
	}
		
	private String getAccName(int accountID) {
    	String accName = null;
    	try {
    		 synchronized(accontName) {
    			 if(accontName.containsKey(accountID)) {
    				 accName = (String) accontName.get(accountID);
    			 } else {
    				 Account acc = (Account) remoteAccount.getAccount(accountID);
    			 	accName = acc.getAccountName();
    			 	accontName.put(acc.getId(), acc.getAccountName());
    			 }
			
    		 }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return accName;
    }
    
		
		
	
	 
	 class TableModelUtil extends AbstractTableModel {   
		    
		 final String[] columnNames;  
		  
		 Vector<Posting> data;   
		 RemoteAccount remoteRef ;
		     
		 public TableModelUtil( Vector<Posting> myData,String col [],RemoteAccount remoteAcc ) {   
		 	this.columnNames = col;
		this.data = myData;   
		this.remoteRef = remoteAcc;
		}   
		 
	
        public void setModel(Vector<Posting> data) {
        	this.data = data;
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
		 
		     Posting posting = (Posting) data.get(row);
		   		    
			 switch (col) {
		     case 0:
		         value = posting.getId();
		         break;
		     case 1:
		         value =posting.getTradeID();
		         break;
		     case 2:
		         value =  posting.getTransferId();
		         break;
		     case 3:
		         value =posting.getEventType();
		         break;
		     case 4:
		         value = posting.getAccEventType();
		         break;
		     case 5:
		         value =posting.getType();
		         break;
		     case 6:
		         value =posting.getLinkId();
		         break;
		     case 7:
		         value = posting.getCurrency();
		         break;
		     case 8:
		         value =posting.getEffectiveDate();
		         break;
		     case 9:
		         value =posting.getBookingDate();
		         break;
		     case 10:
		         value =posting.getCreationDate();
		         break;
		     case 11:
		         value =posting.getStatus();
		         break;
		     case 12:
		         value =posting.getRuleName();
		         break;
		     case 13:
		         value =getAccName(posting.getDebitAccId());
		         break;  
		     case 14:
		         value =getAccName(posting.getCreditAccId());
		         break;
		     case 15:
		         value =posting.getDebitAmount();
		         break;
		     case 16:
		         value =posting.getCreditAmount();
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
		         if(value instanceof Posting) {
		     data.set(row,(Posting) value) ;
		     this.fireTableDataChanged();   
		         System.out.println("New value of data:");   
		         }
		        
		 }   
		    
		 public void addRow(Object value) {   
		    
			 data.add((Posting) value) ;
		 this.fireTableDataChanged();   
		   
		 }   
		    
		 public void delRow(int row) {   
		    if(row != -1) {
		 data.remove(row);          
		 this.fireTableDataChanged();   
		    }
		    
		 }   
		 
		 public void udpateValueAt(Object value, int row, int col) {   
		     
		  
		     data.set(row,(Posting) value) ;
		     for(int i=0;i<columnNames.length;i++)
		    	 	fireTableCellUpdated(row, i);   
		    
		}   
		    
		    private String getAccName(int accountID) {
		    	String accName = null;
		    	try {
		    		 synchronized(accontName) {
		    			 if(accontName.containsKey(accountID)) {
		    				 accName = (String) accontName.get(accountID);
		    			 } else {
		    				 Account acc = (Account) remoteAccount.getAccount(accountID);
		    			 	accName = acc.getAccountName();
		    			 	accontName.put(acc.getId(), acc.getAccountName());
		    			 }
					
		    		 }
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return accName;
		    }
		    
		    public void removeALL() {
		    	if(data != null) {
		  	  data.removeAllElements();
		    	} 
		    data = null;
		  	 this.fireTableDataChanged();  
		    }
	}
}
