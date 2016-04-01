import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.Folder;
import dsServices.RemoteReferenceData;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TestTableToAddRows extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JButton jButton0;
	private JPanel jPanel0;
	Vector<customClass> data = new Vector<customClass>();
    String cols []= {"Name","Value"};
    TableModelUtil model = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TestTableToAddRows() {
		customClass c1 = new customClass();
		c1.setName("Test1");
		c1.setValue("Value");
		data.add(c1);
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(6, 596, 10, 10), new Leading(5, 286, 10, 10)));
		setSize(606, 298);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(9, 549, 10, 10), new Leading(94, 150, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(12, 12, 12), new Leading(31, 10, 10)));
		}
		return jPanel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
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
			model = new TableModelUtil(data, cols);
			jTable0.setModel(model);
		}
		return jTable0;
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
