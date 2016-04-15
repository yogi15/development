package apps.window.limitdashboardpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.commonUTIL;
 

public class JobDatePanel extends javax.swing.JPanel {
	public JToolBar jToolBar2;
	public JLabel jLabel3;
	public JLabel jLabel4; 
	public  JScrollPane jScrollPane0;
	private JComboBox jComboBox0;
	private JTextField jTextField0;
	 public JTable jTable1;
	  String[] columnNames =  {"", "","",""};
	  Object data[] [] = { {" StartDate", ""," EndDate" ,""}}; 
	  Object dataTrade[] [] = { {" TradeStartDate", ""," TradeEndDate" ,""},{" Settlement StartDate", "","Settlement   EndDate" ,""},{" Marturity StartDate", ""," Marturity EndDate" ,""}}; 
	 //{"STARTDATE",commonUTIL.getDateFormat((commonUTIL.getCurrentDate()))," END DATE",commonUTIL.getDateFormat((commonUTIL.getCurrentDate()))};
	 MyTableModel1 model = null;
	public	JPanel jPanel4;
	String name = "Task";
	 public JobDatePanel(String name) {
		 this.name = name;
		 if(name.equalsIgnoreCase("Task"))
			 model =  new MyTableModel1(data, columnNames);
		 else 
			 model =  new MyTableModel1(dataTrade, columnNames);
	 }
	 public JPanel getJPanel4() {
			
		
			if (jPanel4 == null) {
				jPanel4 = new JPanel();
				jPanel4.setLayout(new GroupLayout());
				//jPanel4.add(getJScrollPane0(), new Constraints(new Bilateral(5, 10, 13), new Leading(7, 23, 10, 10)));
				//jPanel4.add(getJScrollPane0(),new Constraints(new Leading(5, 710, 10, 10), new Leading(5, 63, 10, 10)));
				jPanel4.add(getJScrollPane0(), new Constraints(new Leading(4, 710, 10, 10), new Leading(32, 63, 10, 10)));
				jPanel4.add(getJToolBar2(), new Constraints(new Leading(8, 697, 10, 10), new Leading(3, 23, 11, 11)));
			}
			return jPanel4;
		}
	 
	 private JScrollPane getJScrollPane0() {
		
			if (jScrollPane0 == null) {
				jScrollPane0 = new JScrollPane();
				jScrollPane0.setViewportView(getJTable1());
			}
			return jScrollPane0;
		}
	 
	 public JToolBar getJToolBar2() {
			
		
			if (jToolBar2 == null) {
				jToolBar2 = new JToolBar();
				addButtonsInToolBar();
			//	jToolBar2.setBorder(new LineBorder(Color.gray, 1, false));
				//jToolBar2.add(panel);
			}
			return jToolBar2;
		}
	 
	 public void addButtonsInToolBar() {
		 jToolBar2.add(Box.createHorizontalGlue());
		 jToolBar2.add(getJLabel3() );
		 jToolBar2.add(getJComboBox0() );
		 jToolBar2.add(getJLabel5() );
		 jToolBar2.add(getJTextField0() );
		 jToolBar2.add(Box.createHorizontalGlue());
		 jToolBar2.add(getJLabel4() );
		 
	 }
	 private JComboBox getJComboBox0() {
			if (jComboBox0 == null) {
				jComboBox0 = new JComboBox();
				jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "Transfer   ", "Trade   "}));
			}
			return jComboBox0;
		}
	 private JTextField getJTextField0() {
		
			if (jTextField0 == null) {
				jTextField0 = new JTextField();
				jTextField0.setSize(10, 10);
				//jTextField0.setText("jTextField0");
			}
			return jTextField0;
		}
	 private JLabel getJLabel3() {
			
			
			if (jLabel3 == null) {
				jLabel3 = new JLabel();
				jLabel3.setText(">>");
			}/*jLabel3.addMouseListener(new MouseAdapter() {
				
				

				public void mouseClicked(MouseEvent event) {
					
					
					if(jSplitPane1.getDividerLocation() == 88) {
						jSplitPane1.setDividerLocation(14);
					} else {
						jSplitPane1.setDividerLocation(88);
					}
				}
			});*/
			return jLabel3;
		}
	 private JLabel getJLabel4() {
			
			
		
			if (jLabel4 == null) {
				jLabel4 = new JLabel();
				jLabel4.setText("   Completed       ");
			}
			return jLabel4;
		}
	 private JLabel getJLabel5() {
			
			
			
			
			if (jLabel5 == null) {
				jLabel5 = new JLabel();
				jLabel5.setText("   Object ID       ");
			}
			return jLabel5;
		}
	 JLabel jLabel5;
	 private JTable getJTable1() {
			
			
			if (jTable1 == null) {
								jTable1 =  new javax.swing.JTable(model) 			{
											public TableCellEditor getCellEditor(int row, int column)	{
												int modelColumn = convertColumnIndexToModel( column );
												if (modelColumn == 1 && row ==0)	{
														TableCellEditor t1 = null; //((TableCellEditor) datecel9);
														return (TableCellEditor)t1;
												}
												if (modelColumn == 3 && row ==0) {
														TableCellEditor t1 =  null;//((TableCellEditor) datecel18);
														return (TableCellEditor)t1;
												}	else{
													return super.getCellEditor(1, 0);
												}
											}
								};
								jTable1.setRowHeight(20);
								jTable1.setColumnSelectionAllowed(false);
								jTable1.setRowSelectionAllowed(true);
								jTable1.setPreferredScrollableViewportSize(new Dimension(200, 200));
								
			}		
				
			jTable1.setValueAt("StartDate", 0, 0);
			jTable1.setValueAt("EndDate", 0, 2);
			jTable1.setValueAt(commonUTIL.getDateFormat(commonUTIL.getCurrentDate()), 0, 1);
			jTable1.setValueAt(commonUTIL.getDateFormat(commonUTIL.getCurrentDate()), 0, 3);
			jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					commonUTIL.showAlertMessage((String) jTable1.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()));
				}
			});
			jTable1.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
				//	commonUTIL.showAlertMessage((String) jTable1.getValueAt(0,3));
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			}); 
				
		
			
			return jTable1;
	 }
	 }
	 class MyTableModel1 extends DefaultTableModel {   
   	  
	        public MyTableModel1(Object rowData[][], Object columnNames[]) {   
	             super(rowData, columnNames);   
	             
	          }   
	          
	        @Override  
	          public Class getColumnClass(int col) {   
	             
	             return String.class;  //other columns accept String values   
	        }   
	       
	        @Override  
	          public boolean isCellEditable(int row, int col) {   
	            if (col == 0 )       
	                return false;  
	           
	            if(col == 2)
	            		return false;  
	             return true;   
	          }   
	        }    

