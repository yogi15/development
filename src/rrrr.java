

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;   
import java.awt.event.*;   
import java.util.*;   
import javax.swing.*;   
import javax.swing.plaf.basic.BasicComboBoxRenderer;  

public class rrrr extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList editors = new ArrayList(3);

    public rrrr()
    {
    	// Create the editors to be used for each row
    	 SelectionManager manager = new SelectionManager(); 
    	String[] items11 = { "Circle", "Square", "Triangle" ,"Circle444", "Square222", "Triangle333" };
    	 DefaultComboBoxModel modeld = new DefaultComboBoxModel(items11);
    	JComboBox comboBox11 = new JComboBox( modeld );
    	 MultiRenderer renderer = new MultiRenderer(manager);   
    	comboBox11.setRenderer(renderer); 
    	comboBox11.addActionListener(manager);  
    	//comboBox11.set
    	DefaultCellEditor dce11 = new DefaultCellEditor( comboBox11 );
    	editors.add( dce11 );
    	
    	String[] items2 = { "Red", "Blue", "Green" };
    	JComboBox comboBox2 = new JComboBox( items2 );
    	DefaultCellEditor dce2 = new DefaultCellEditor( comboBox2 );
    	editors.add( dce2 );
    	    	
    	String[] items4 = { "1", "2", "3" };
    	JComboBox comboBox4= new JComboBox( items4);
    	DefaultCellEditor dce4 = new DefaultCellEditor( comboBox4 );
    	editors.add( dce4 );
    	
    	String[] items3 = { "Apple", "Orange", "Banana" };
    	JComboBox comboBox3 = new JComboBox( items3 );
    	DefaultCellEditor dce3 = new DefaultCellEditor( comboBox3 );
    	editors.add( dce3 );

    	//  Create the table with default data

    	Object[][] data =
    	{
    		{"Color", "Red"},
    		{"Shape", "Square"},
    		{"Fruit", "Banana"},
    		{"Plain", "Text"}
    	};
    	String[] columnNames = {"Type","Value"};
    	DefaultTableModel model = new DefaultTableModel(data, columnNames);
    	JTable table = new JTable(model)
    	{
    		//  Determine editor to be used by row
    		public TableCellEditor getCellEditor(int row, int column)
    		{
    			int modelColumn = convertColumnIndexToModel( column );
    			
    			if (modelColumn == 1 && row ==0)
    				return (TableCellEditor)editors.get(row);
    			else if (modelColumn == 1 && row ==3)
    				return (TableCellEditor)editors.get(2);
    			
    			else
    				return super.getCellEditor(1, 0);
    			    			
    			
    		}
    	};
    	System.out.println(table.getCellEditor());

    	JScrollPane scrollPane = new JScrollPane( table );
    
    	String s1[]  = {"23","23","443","000","ppp","oppo","lokm"};
    	 DefaultComboBoxModel modeldc = new DefaultComboBoxModel(s1);
    	// MultiRenderer renderer = new MultiRenderer(manager);   
     	
    	JComboBox testing = new JComboBox(modeldc);
    	testing.setRenderer(renderer); 
    	testing.addActionListener(manager);
    	getContentPane().add( scrollPane );
    	getContentPane().add( testing );
    }

    public static void main(String[] args)
    {
    	TableComboBoxByRow frame = new TableComboBoxByRow();
    	frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
    	frame.pack();
    	frame.setVisible(true);
    }
    
    class SelectionManager implements ActionListener {   
        JComboBox combo = null;   
       java.util.List<Object> selectedItems = new ArrayList<Object>();  // j2se 1.5+   
        // List selectedItems = new ArrayList();               // j2se 1.4-   
       java.util.List<Object> nonSelectables = new ArrayList<Object>();   
        
        public void actionPerformed(ActionEvent e) {   
            if(combo == null) {   
                combo = (JComboBox)e.getSource();   
            }   
            Object item = combo.getSelectedItem();   
            // Toggle the selection state for item.   
            if(selectedItems.contains(item)) {   
                selectedItems.remove(item);   
            } else if(!nonSelectables.contains(item)) {   
                selectedItems.add(item);   
            }   
        }   
        
        /**  
         * The varargs feature (Object... args) is new in j2se 1.5  
         * You can replace the argument with an array.  
         */  
        public void setNonSelectables(Object... args) {   
            for(int j = 0; j < args.length; j++) {   
                nonSelectables.add(args[j]);   
            }   
        }   
        
        public boolean isSelected(Object item) {   
            return selectedItems.contains(item);   
        }   
    }   
        
    
    class MultiRenderer extends BasicComboBoxRenderer {   
        SelectionManager selectionManager;   
        
        public MultiRenderer(SelectionManager sm) {   
            selectionManager = sm;   
        }   
        
        public Component getListCellRendererComponent(JList list,   
                                                      Object value,   
                                                      int index,   
                                                      boolean isSelected,   
                                                      boolean cellHasFocus) {   
            if (selectionManager.isSelected(value)) {   
                setBackground(list.getSelectionBackground());   
                setForeground(list.getSelectionForeground());   
            } else {   
                setBackground(list.getBackground());   
                setForeground(list.getForeground());   
            }   
        
            setFont(list.getFont());   
        
            if (value instanceof Icon) {   
                setIcon((Icon)value);   
            } else {   
                setText((value == null) ? "" : value.toString());   
            }   
            return this;   
        }   
    }  
}