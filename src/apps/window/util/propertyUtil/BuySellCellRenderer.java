package apps.window.util.propertyUtil;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

public class BuySellCellRenderer extends JLabel implements ListCellRenderer <String>
 {
	String buysell;
	private final String BUY = "BUY";
	private final String SELL = "SELL";
	 public BuySellCellRenderer() {
	//	 setOpaque(true);
	 }
		 

		@Override
		public Component getListCellRendererComponent(
				JList<? extends String> list, String value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			String buysell = value == null
                    ? null
                    : ((String) value); 
	            if (buysell.equalsIgnoreCase(BUY)) 
	               setBackground( Color.GREEN);
	                if (buysell.equalsIgnoreCase(SELL)) 
		                setBackground( Color.RED);
		     
			 return this;
		}
		  
		 
		
}
