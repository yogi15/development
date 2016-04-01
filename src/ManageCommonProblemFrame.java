import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class ManageCommonProblemFrame extends JInternalFrame {   
	  
	JPanel mainPanel = null;   
	JTable theTable= null;   
	JScrollPane listScroll= null;   
	JTextField nameField= null;   
	JTextArea problemArea= null;   
	JTextArea fixArea= null;   
	JScrollPane problemScroll= null;   
	JScrollPane fixScroll= null;   
	  
	MyTableModel model= null;   
	  
	  
	public ManageCommonProblemFrame() {   
	super("Common Problems", true, true, true, true);   
	  
	  
	Vector v1 = new Vector();   
	Vector rv = new Vector();   
	  
	rv = new Vector();   
	rv.add("Mary");   
	v1.addElement(rv);   
	  
	  
	mainPanel = new JPanel();   
	setContentPane(mainPanel);   
	mainPanel.setLayout(null);   
	  
	model = new MyTableModel(v1);   
	  
	  
	theTable = new JTable(model);   
	theTable.setShowGrid(false);   
	  
	  
	listScroll = new JScrollPane(theTable);   
	listScroll.setBounds(5, 5, 150, 200);   
	mainPanel.add(listScroll);   
	  
	JButton addButton = new JButton("Add Name");   
	addButton.setBounds(160, 6, 100, 20);   
	addButton.addActionListener(new ActionListener() {   
	public void actionPerformed(ActionEvent e) {   
	  
	Vector row = new Vector();   
	row.addElement("new row");   
	model.addRow(row);   
	  
	}});   
	JButton udpateButton = new JButton("udpateName");   
	udpateButton.setBounds(160, 36, 100, 20);   
	udpateButton.addActionListener(new ActionListener() {   
	public void actionPerformed(ActionEvent e) {   
		Vector rowv = new Vector();   
		rowv.addElement("update row");  
	
	model.udpateValueAt(rowv, theTable.getSelectedRow(), theTable.getSelectedColumn());
	 
	  
	}});
	JButton deleteButton = new JButton("deleteName");   
	deleteButton.setBounds(160, 56, 100, 20);   
	deleteButton.addActionListener(new ActionListener() {   
	public void actionPerformed(ActionEvent e) {   
	
	
	model.delRow(theTable.getSelectedRow());
	 
	  
	}});
	mainPanel.add(addButton);   
	mainPanel.add(udpateButton);  
	mainPanel.add(deleteButton);  
	  
	}   
  
	  
	private void getInitialData() {   
	  
	           
	  
	}   
	  
	  
	class MyTableModel extends AbstractTableModel {   
	           
	        final String[] columnNames = {"Name"};   
	           
	        final Vector data;   
	               
	               
	        MyTableModel( Vector myData) {   
	        	
	this.data = myData;   
	}   
	  
	           
	        public int getColumnCount() {   
	            return columnNames.length;   
	        }   
	           
	        public int getRowCount() {   
	            return data.size();   
	        }   
	        public String getColumnName(int col) {   
	            return columnNames[col];   
	        }   
	        public Object getValueAt(int row, int col) {   
	            return data.get(row);   
	        }   
	          
	        public boolean isCellEditable(int row, int col) {   
	        return true;   
	        }   
	        public void setValueAt(Object value, int row, int col) {   
	                System.out.println("Setting value at " + row + "," + col   
	                                   + " to " + value   
	                                   + " (an instance of "    
	                                   + value.getClass() + ")");   
	            data.set(row, value);   
	            this.fireTableDataChanged();   
	                System.out.println("New value of data:");   
	               
	        }   
	           
	        public void addRow(Vector row) {   
	           
	        data.addElement(row);   
	        this.fireTableDataChanged();   
	          
	        }   
	           
	        public void delRow(int row) {   
	           
	        data.remove(row);          
	        this.fireTableDataChanged();   
	           
	        }   
	        
	        public void udpateValueAt(Object value, int row, int col) {   
	            System.out.println("Setting value at " + row + "," + col   
	                               + " to " + value   
	                               + " (an instance of "    
	                               + value.getClass() + ")");   
	         
	            data.set(row, value);  
	        fireTableCellUpdated(row, col);   
	            System.out.println("New value of data:");   
	           
	    }   
	           
	           
	           
	    }   
	  
	  public static void main(String args[]) {
		  ManageCommonProblemFrame dd = new ManageCommonProblemFrame();
		  dd.setVisible(true);
		  dd.setSize(300, 300);
		  JFrame frame = new JFrame();
		  frame.add(dd);
		    frame.setVisible(true);
		    frame.setSize(300, 300);
	  }
}



	
