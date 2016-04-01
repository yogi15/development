
import java.awt.*;import javax.swing.*;import javax.swing.event.ChangeEvent;import javax.swing.event.ChangeListener;import javax.swing.table.DefaultTableCellRenderer; 
public class TableCellColor {  
	public static void main(String[] args) {    
		SwingUtilities.invokeLater(new Runnable() {      
			public void run() {        new 
				TableCellColor().makeUI();      }    });  }  
	public void makeUI() {    final JTable table = new JTable(20, 3) {       
		@Override      public Object getValueAt(int row, int column) {       
			return "Row " + row + "Column" + column;      }  
		};   
		final RowHighlightRenderer renderer = new RowHighlightRenderer();  
		table.setDefaultRenderer(Object.class, renderer);    
		final SpinnerModel model = new SpinnerNumberModel(-1, -1, table.getRowCount(), 1);   
		final JSpinner spinner = new JSpinner(model);    spinner.addChangeListener(new ChangeListener() {    
			public void stateChanged(ChangeEvent e) {   
				renderer.setHighlightRow((Integer) model.getValue());      
				table.repaint();      }    }); 
		JFrame frame = new JFrame();    
		frame.add(new JScrollPane(table), BorderLayout.CENTER);  
		frame.add(spinner, BorderLayout.SOUTH);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(400, 400); 
		frame.setLocationRelativeTo(null);   
		frame.setVisible(true);  }}

class RowHighlightRenderer extends DefaultTableCellRenderer 
{  
	private int highlightRow = -1; 
	private static final Color HIGHLIGHT = Color.RED;  
	
	@Override  public Component getTableCellRendererComponent(JTable table, Object value,         
			boolean isSelected, boolean hasFocus, int row, int column) {  
		super.getTableCellRendererComponent(table, value,            isSelected, hasFocus, row, column);   
		setBackground(row == highlightRow            ? HIGHLIGHT            : isSelected            ? table.getSelectionBackground()            : table.getBackground());
		return this; 
		} 
	public void setHighlightRow(int rowToHighlight) { 
		highlightRow = rowToHighlight;  }
	}