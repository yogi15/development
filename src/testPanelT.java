import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class testPanelT extends JPanel {
	
	
	JButton but = new JButton();
	JLabel ll = new JLabel("Testing");
	
	
	public testPanelT() {
		super.add(but);
		super.add(ll);
	}

}
