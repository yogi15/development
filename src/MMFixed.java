import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMFixed extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField floatRateField;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMFixed() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(20, 10, 10), new Leading(16, 10, 10)));
		add(getfloatRateField(), new Constraints(new Leading(102, 76, 10, 10), new Leading(14, 12, 12)));
		setSize(604, 70);
	}

	private JTextField getfloatRateField() {
		if (floatRateField == null) {
			floatRateField = new JTextField();
		}
		return floatRateField;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Fixed Rate");
		}
		return jLabel0;
	}

}
