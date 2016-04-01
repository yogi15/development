import javax.swing.table.*;
import java.util.*;
public class RowEditorModel
{
     private Hashtable data;
      public RowEditorModel()
      {
          data = new Hashtable();
      }
     public void addEditorForRow(int row, int col,TableCellEditor e )
     {
    	 System.out.println("From roweditors adding row " + row + " col " + col);
    	Vector columns = (Vector) data.get(new Integer(row));
    	if(columns != null) {
    		columns.add(col, e);
    	} else {
    		columns = new Vector();
    		columns.add(col, e);
    	}
    	 
    	 
         data.put(new Integer(row), columns);
     }
     public void removeEditorForRow(int row)
     {
        data.remove(new Integer(row));
     }
    public TableCellEditor getEditor(int row,int col)
     {
    	System.out.println("From getEditor row " + row + " col " + col);
    	Vector columns = (Vector) data.get(new Integer(row));
         return (TableCellEditor)columns.get(col);
     }
 }