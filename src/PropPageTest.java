import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.table.*;
import javax.swing.border.*;

import apps.window.util.windowUtil.TableModelUtil;
import beans.Fees;
public class PropPageTest extends JPanel
{
    private JComboBox b;
    private JTableX table;
    private DefaultTableModel model;
    private String[] col_names = {"Name", "Value","Values1"};
    private String[] anchor_values = { "CENTER", "NORTH", "NORTHEAST",
           "EAST", "SOUTHEAST", "SOUTH",
           "SOUTHWEST", "WEST", "NORTHWEST" };
    private String[] fill_values = { "NONE", "HORIZONTAL", "VERTICAL",
            "BOTH" };
    RowEditorModel rm;
    TableModelUtil mod;
    Vector<Filler> data = new Vector<Filler>();
    private void createGUI()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        b = new JComboBox();
        mod = new TableModelUtil(data,col_names);
        model = new DefaultTableModel(col_names,0)
        {
            public String[] prop_names = { "Name", "Anchor", "Fill",
                                           "GridHeight", "GridWidth",
                                           "GridX", "GridY", "Insets",
                                           "Ipadx", "Ipady",
                                           "WeightX", "WeightY" };
            public Object getValueAt(int row, int col)
            {
                if (col==0)
                    return (Filler) data.get(row);
                return super.getValueAt(row,col);
            }
            public boolean isCellEditable(int row, int col)
            {
                if (col==0)
                    return false;
                return true;
            }
            public void addRow(Object value) {   
        	    
       		 data.add((Filler) value) ;
       	 this.fireTableDataChanged();   
       	   
       	 } 
            
        };
        table = new JTableX(mod);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        JButton but = new JButton("Add");
        // create a RowEditorModel... this is used to hold the extra
        // information that is needed to deal with row specific editors
        rm = new RowEditorModel();
        // tell the JTableX which RowEditorModel we are using
        table.setRowEditorModel(rm);
        // create a new JComboBox and DefaultCellEditor to use in the
        // JTableX column
        JComboBox cb = new JComboBox(anchor_values);
        DefaultCellEditor ed = new DefaultCellEditor(cb);
        String firstCOl [] = {"p1","p1","p1"};
        JComboBox cb4 = new JComboBox(firstCOl);
        DefaultCellEditor ed4 = new DefaultCellEditor(cb4);
        
        // tell the RowEditorModel to use ed for row 1
      //  rm.addEditorForRow(1,0,ed4);
      //  rm.addEditorForRow(1,1,ed);
        // create a new JComboBox and editor for a different row
        cb = new JComboBox(fill_values);
        ed = new DefaultCellEditor(cb);
        String firstCOl2 [] = {"p31","p31","p31"};
        JComboBox cb42 = new JComboBox(firstCOl2);
        DefaultCellEditor ed42 = new DefaultCellEditor(cb42);
        // inform the RowEditorMode of the situation
      //  rm.addEditorForRow(5,0,ed);
      //  rm.addEditorForRow(5,1,ed42);
        add(b, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(but,BorderLayout.SOUTH);
        
        but.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
        		String testt = "est";
        		Filler fill = new Filler();
        		fill.setColumname(testt);
        		mod.addRow(fill);
        		int i = mod.getRowCount();
        		//model.addr
        		String s1 []= {"s1","s2","s3"};
        		final JComboBox cb = new JComboBox(s1);
        		 cb.addItemListener(new ItemListener () {

        				@Override
        				public void itemStateChanged(ItemEvent e) {
        					if(cb.getSelectedIndex() != -1) {
        					// TODO Auto-generated method stub
        					String attributeName = cb.getSelectedItem().toString();
        				System.out.println(data.size() + "  " + table.getSelectedRow());
        				int i = table.getSelectedRow();
        					Filler filler = (Filler) data.get(i);
        					filler.setColumncre(attributeName);
        					data.remove(i);
        					data.add(i, filler);
        					//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
        			//		mod.fireTableDataChanged();
        	        //		 table.repaint();
        					}
        				}
        		 });
        		            DefaultCellEditor ed = new DefaultCellEditor(cb);
        		// int rowCount = model.getRowCount();
        			//	 model.insertRow(arg0, arg1)
        		 rm.addEditorForRow(i-1,0,ed);
        		 String ss1 []= {"ss1","ss2","ss3"};
        		final JComboBox cbs = new JComboBox(ss1);
        		 DefaultCellEditor eds = new DefaultCellEditor(cbs);
        		// final JComboBox cb = new JComboBox(s1);
        		 cbs.addItemListener(new ItemListener () {

        				@Override
        				public void itemStateChanged(ItemEvent e) {
        					if(cb.getSelectedIndex() != -1) {
        					// TODO Auto-generated method stub
        					String attributeName = cbs.getSelectedItem().toString();
        				System.out.println(data.size() + "  " + table.getSelectedRow());
        				int i = table.getSelectedRow();
        					Filler filler = (Filler) data.get(i);
        					filler.setColumnval(attributeName);
        					data.remove(i);
        					data.add(i, filler);
        					//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
        			//		mod.fireTableDataChanged();
        	        //		 table.repaint();
        					}
        				}
        		 });
        		// int rowCount = model.getRowCount();
        			//	 model.insertRow(arg0, arg1)
        		 rm.addEditorForRow(i-1,1,eds);
        		 
        		mod.fireTableDataChanged();
        		 table.repaint();
				//
			}
		});
			
			
		
    }
    public PropPageTest()
    {
        createGUI();
    }
    public static void main(String[] args)
    {
        JFrame f = new JFrame("test");
        f.setSize(300,350);
        f.getContentPane().add(new PropPageTest(), BorderLayout.CENTER);
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
    
    class TableModelUtil extends AbstractTableModel {
    	 final String[] columnNames;  
 	    
    	 Vector<Filler> data; 
    	 public int getColumnCount() {   
    	     return columnNames.length;   
    	 }   
    	 public TableModelUtil( Vector<Filler> myData,String col []) {
    		 this.columnNames = col;
    			this.data = myData; 
    	 }
    	 
    	 public Object getValueAt(int row, int col)
         {
    		  Object value = null;  
    		  Filler fill = (Filler)  data.get(row);
             if (col==0)
                 return (String) fill.getColumname();
             if(col == 1)
            return  (String) fill.getColumncre();
             if(col == 2)
                 return  (String) fill.getColumnval();
             return value;
         }
         public boolean isCellEditable(int row, int col)
         {
             if (col==0)
                 return false;
             return true;
         }
         public void addRow(Object value) {   
     	    
    		 data.add((Filler) value) ;
    	 this.fireTableDataChanged();   
    	   
    	 } 
    	 public int getRowCount() {   
    		 if(data != null)
    	     return data.size();   
    		 return 0;
    	 }   
    	 public String getColumnName(int col) {   
    	     return columnNames[col];   
    	 }   

		 
    }
    
    class Filler {
    	String columname = "";
    	public String getColumname() {
			return columname;
		}
		public void setColumname(String columname) {
			this.columname = columname;
		}
		public String getColumncre() {
			return columncre;
		}
		public void setColumncre(String columncre) {
			this.columncre = columncre;
		}
		public String getColumnval() {
			return columnval;
		}
		public void setColumnval(String columnval) {
			this.columnval = columnval;
		}
		String columncre = "";
    	String columnval = "";
    	
    }
    
}