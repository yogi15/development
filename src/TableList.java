import java.awt.*;   
import javax.swing.*;   
import javax.swing.event.*;   
import javax.swing.table.*;   
     
public class TableList   
{   
    public TableList()   
    {   
        JTable table = createTable();   
        JFrame f = new JFrame();   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f.getContentPane().add(table);   
        f.setSize(400,400);   
        f.setLocation(200,200);   
        f.setVisible(true);   
    }   
     
    private JTable createTable()   
    {   
        String[] names = { "", "" };   
        String[] col1 = {   
            "crow", "hawk", "sparrow", "bluejay",   
            "wren", "catbird", "warbler", "owl"  
        };   
        String[] col2 = {   
            "bear", "racoon", "lynx", "bison",   
            "cougar", "coyote", "horse", "fox"  
        };   
        TableListModel dataModel = new TableListModel(col1, col2);   
        JTable table = new JTable(dataModel);   
        TableListRenderer renderer = new TableListRenderer();   
        table.setDefaultRenderer(String.class, renderer);   
        int rowCount = renderer.list.getVisibleRowCount();   
        int cellHeight = renderer.cellHeight;   
        int rowHeight = rowCount * cellHeight;   
        table.setRowHeight(rowHeight);   
        return table;   
    }   
     
    public static void main(String[] args)   
    {   
        new TableList();   
    }   
}   
    
class TableListRenderer implements TableCellRenderer   
{   
    JList list;   
    int cellHeight;   
    
    public TableListRenderer()   
    {   
        list = new JList();   
        cellHeight = 20;   
    }   
    
    public Component getTableCellRendererComponent(JTable table,   
                                                   Object value,   
                                                   boolean isSelected,   
                                                   boolean hasFocus,   
                                                   int row,   
                                                   int column)   
    {   
        if(isSelected)   
            list.setBackground(new Color(200,240,200));   
        else  
            list.setBackground(Color.white);   
        if(hasFocus)   
            list.setBackground(new Color(240,200,200));   
        list.setListData((String[])value);   
        list.setFixedCellHeight(cellHeight);   
        list.setVisibleRowCount(((String[])value).length);   
        return list;   
    }   
}   
    
class TableListModel extends AbstractTableModel   
{   
    String[] col1, col2;   
    
    public TableListModel(String[] col1, String[] col2)   
    {   
        this.col1 = col1;   
        this.col2 = col2;   
    }   
    
    public Class getColumnClass(int columnIndex) { return String.class; };   
    
    public int getRowCount() { return 1; };   
    
    public int getColumnCount() { return 2; }   
    
    public Object getValueAt(int row, int column)   
    {   
        return column % 2 == 0 ? col1 : col2;   
    }   
}  
