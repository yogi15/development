import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.jidesoft.combobox.DateComboBox;

import apps.window.referencewindow.DateCellEditor12;
import beans.Folder;
import dsServices.RemoteReferenceData;



//VS4E -- DO NOT REMOVE THIS LINE!
public class testingFrameToaddRowsinJtable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JButton jButton0;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
     private SimpleDateFormat _dateFormat;
		private static DateComboBox _dateComboBox;
	Vector<customClass> data = new Vector<customClass>();
	String[] items11 = { "Circle", "Square", "Triangle" };
	JComboBox comboBox11 = new JComboBox( items11 );
	DefaultCellEditor dce11 = new DefaultCellEditor( comboBox11 );
    String cols []= {"Amount","StartDate","Tenor","EndDate"};
  //  TableModelUtil model = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public testingFrameToaddRowsinJtable() {
		customClass c1 = new customClass();
		c1.setName("Test1");
		c1.setValue("Value");
		data.add(c1);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(6, 623, 10, 10), new Leading(7, 358, 10, 10)));
		setSize(632, 370);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}
	  Object[][] data1 = null;/*	    	{
	        		 {"ID", "1"},
	                 {"ID","0"}
	    	}; */
	  DefaultTableModel model = new DefaultTableModel(data1, cols);
	private JTable getJTable0() {
		if (jTable0 == null) {
			
			//model = new TableModelUtil(data, cols);
			jTable0 = new JTable(model){
	        	 
	        	public TableCellEditor getCellEditor(int row, int column)
	    		{
	    			int modelColumn = convertColumnIndexToModel( column );
	    			int modelRow = convertRowIndexToModel(row);
	    			if (modelColumn == 1 && row ==modelRow) {
	    				 TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
	    			        
	    			       
	    				return (TableCellEditor)t1;
	    				
	    			}
	    			if (modelColumn == 2 && row ==modelRow) {
	    				 TableCellEditor t1 = ((TableCellEditor)dce11);
	    			        
	    			       
	    				return (TableCellEditor)t1;
	    				
	    			}else 
	      				 
	    				return super.getCellEditor(1, 0);
	    		
	    		}
			};
			
			//jTable0.setModel(model);
		}
		return jTable0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}jButton0.addActionListener(new ActionListener() {   
		      public void actionPerformed(ActionEvent actionEvent) { 
		    	  customClass c1 = new customClass();
		  		c1.setName("Test1");
		  		c1.setValue("Value");
		  	//	model.addRow(c1);
		  		String [] data1 =
			    	
			        		 {"ID", "1"};
			               
			    	
		  		model.addRow(data1);
		      }
		});
		return jButton0;
		
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJButton0(), new Constraints(new Leading(35, 10, 10), new Leading(25, 10, 10)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(7, 610, 10, 10), new Leading(82, 268, 10, 10)));
		}
		return jPanel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				testingFrameToaddRowsinJtable frame = new testingFrameToaddRowsinJtable();
				frame.setDefaultCloseOperation(testingFrameToaddRowsinJtable.EXIT_ON_CLOSE);
				frame.setTitle("testingFrameToaddRowsinJtable");
				frame.getContentPane().setPreferredSize(frame.getSize());
				
				//frame.setl
			//	frame.add(rows);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

class TableModelUtil extends AbstractTableModel {   
    
	 final String[] columnNames;  
	    
	 Vector<customClass> myData;   
	 RemoteReferenceData remoteRef ;
	        
	 public TableModelUtil(  Vector<customClass> myData,String col []) {   
	 	this.columnNames = col;
	this.myData = myData;   
	
	}   

	    
	 public int getColumnCount() {   
	     return columnNames.length;   
	 }   
	    
	 public int getRowCount() {   
		 if(myData != null)
	     return myData.size();   
		 return 0;
	 }   
	 public String getColumnName(int col) {   
	     return columnNames[col];   
	 }   
	 public Object getValueAt(int row, int col) {   
	     Object value = null;  	 
	 
	     customClass limitEvnt = (customClass) myData.get(row);
	    
		 switch (col) {
	     case 0:
	         value = limitEvnt.getName();
	         break;
	     case 1:
	         value =limitEvnt.getValue();
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
	         if(value instanceof Folder) {
	        	 myData.set(row,(customClass) value) ;
	     this.fireTableDataChanged();   
	         System.out.println("New value of data:");   
	         }
	        
	 }   
	    
	 public void addRow(Object value) {   
	    if(myData != null) {
	    	
	    	myData.add((customClass) value) ;
	 this.fireTableDataChanged();   
	    }
	   
	 }   
	    
	 public void delRow(int row) {   
	    if(row != -1) {
	    	myData.remove(row);          
	 this.fireTableDataChanged();   
	    }
	    
	 }   
	 
	 public void udpateValueAt(Object value, int row, int col) {   
	     
	  
		 myData.set(row,(customClass) value) ;
	     for(int i=0;i<columnNames.length;i++)
	    	 	fireTableCellUpdated(row, i);   
	    
	}   
	    
	   
	    public void removeALL() {
	    	if(myData != null) {
	    		myData.removeAllElements();
	    	} 
	    	myData = null;
	  	 this.fireTableDataChanged();  
	    }
}

    class customClass {
    	String name = "Name";
    	Object dateCellEdi;
    	public Object getDateCellEdi() {
			return dateCellEdi;
		}
		public void setDateCellEdi(DateCellEditor12 Object) {
			this.dateCellEdi = Object;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		String value = "value";
    	
    	
    	
    }
}
