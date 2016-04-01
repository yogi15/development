

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableComboBoxByRow extends JFrame
{
    ArrayList editors = new ArrayList(3);

    public TableComboBoxByRow()
    {
    	// Create the editors to be used for each row

    	String[] items11 = { "Circle", "Square", "Triangle" };
    	JComboBox comboBox11 = new JComboBox( items11 );
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
    	Boolean boo = new Boolean(true);
    	editors.add( boo );
    	//  Create the table with default data

    	Object[][] data =
    	{
    		{"Color", "Red"},
    		{"Shape", "Square"},
    		{"Fruit", "Banana"},
    		{"Plain", "Text"},
    		{"Boolean",new Boolean(true)}
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
    			else if (modelColumn == 1 && row ==4)
    				return  (TableCellEditor)editors.get(4);
    				return super.getCellEditor(1, 0);
    			    			
    			
    		}
    	};
    	System.out.println(table.getCellEditor());

    	JScrollPane scrollPane = new JScrollPane( table );
    	getContentPane().add( scrollPane );
    }

    public static void main(String[] args)
    {
    	TableComboBoxByRow frame = new TableComboBoxByRow();
    	frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
    	frame.pack();
    	frame.setVisible(true);
    }
}