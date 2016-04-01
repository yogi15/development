package apps.window.util.windowUtil;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import beans.Attribute;
import beans.Folder;

class TableModelUtil extends AbstractTableModel {
    final String[] columnNames;

    Vector<Attribute> data;

    public TableModelUtil(Vector<Attribute> myData, String col[]) {
            this.columnNames = col;
            this.data = myData;
    }

    public int getColumnCount() {
            return columnNames.length;
    }

    public int getRowCount() {
            if (data != null)
                    return data.size();
            return 0;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Object value = null;

        Attribute attribute = (Attribute) data.get(row);

        switch (col) {

        case 0:
                value = attribute.getName();
                break;
        case 1:
                value = attribute.getValue()==null?"":attribute.getValue();
                break;
        
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {   
    	  if (col == 0)       //first column will be uneditable   
              return false;
        
        return true;            
    }
    
    public void setValueAt(Object value, int row, int col) {
    	if(row == -1)
    		return;
        if (value == null)
                return;
    //    System.out.println("Setting value at " + row + "," + col + " to "
    //                    + value + " (an instance of " + value.getClass() + ")");
        Attribute ff = (Attribute)      data.get(row);
        ff.setValue((String) value);

        data.set(row, ff);
        this.fireTableDataChanged();
            //System.out.println("New value of data:");           
    }

    public void addRow(Object value) {
        data.add((Attribute) value);
        this.fireTableDataChanged();
    }

    public void delRow(int row) {
        if (row != -1) {
            data.remove(row);
            this.fireTableDataChanged();
        }
    }

    public void udpateValueAt(Object value, int row, int col) {
        data.set(row, (Attribute) value);
        for (int i = 0; i < columnNames.length; i++)
            fireTableCellUpdated(row, i);
    }
    public void removeALL() {
        if (data != null) {
            data.removeAllElements();
        }
        // data = null;
        this.fireTableDataChanged();
    }
}