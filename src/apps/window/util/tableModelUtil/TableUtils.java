package apps.window.util.tableModelUtil;

import javax.swing.JTable;
 
public class TableUtils {
	private static int[]  _selections;
	public static int getSelectedRowIndex(JTable jtable) {
		int rowSelected = 0;
		 for(int i=0;i<jtable.getRowCount();i++) {
			boolean tt = jtable.isRowSelected(i);
			if(tt) {
				 rowSelected = i;
				 break;
			}
			     //break;
		 }
		
		  return rowSelected;
		 // return -1;
	}
	public static int[] getSelectedRowIndexs(JTable jtable) {
		 
		com.jidesoft.grid.TableUtils.loadSelection(jtable, _selections);
		 
		  return _selections;
		  
	}
}
