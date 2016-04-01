import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMINREqui extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField splitRateField;
	private JLabel jLabel1;
	private JTextField inrEquiField;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMINREqui() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(191, 10, 10), new Leading(12, 12, 12)));
		add(getsplitRateField(), new Constraints(new Leading(86, 61, 10, 10), new Leading(10, 12, 12)));
		add(getinrEquiField(), new Constraints(new Leading(249, 109, 10, 10), new Leading(10, 12, 12)));
		setSize(529, 44);
	}

	private JTextField getinrEquiField() {
		if (inrEquiField == null) {
			inrEquiField = new JTextField();
		}
		return inrEquiField;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("INR Equi");
		}
		return jLabel1;
	}

	private JTextField getsplitRateField() {
		if (splitRateField == null) {
			splitRateField = new JTextField();
		}
		return splitRateField;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Split Rate");
		}
		return jLabel0;
	}

}
