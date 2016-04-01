import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FWDOptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JTextField jTextField0;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JLabel jLabel3;
	private JButton jButton0;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton1;

	public FWDOptionPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 81, 12, 12), new Leading(8, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(134, 81, 12, 12), new Leading(8, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(9, 104, 10, 10), new Leading(32, 27, 10, 10)));
		add(getJLabel2(), new Constraints(new Leading(134, 48, 12, 12), new Leading(32, 27, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(197, 163, 10, 10), new Leading(32, 27, 10, 10)));
		add(getJTextField2(), new Constraints(new Leading(427, 163, 10, 10), new Leading(32, 27, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(372, 48, 12, 12), new Leading(32, 27, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(596, 41, 10, 10), new Leading(32, 12, 12)));
		add(getJRadioButton0(), new Constraints(new Leading(656, 10, 10), new Leading(12, 12, 12)));
		add(getJRadioButton1(), new Constraints(new Leading(656, 10, 10), new Leading(48, 12, 12)));
		setSize(851, 100);
	}

	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setSelected(true);
			jRadioButton1.setText("jRadioButton1");
		}
		return jRadioButton1;
	}

	private JRadioButton getJRadioButton0() {
		if (jRadioButton0 == null) {
			jRadioButton0 = new JRadioButton();
			jRadioButton0.setSelected(true);
			jRadioButton0.setText("jRadioButton0");
		}
		return jRadioButton0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("jLabel3");
		}
		return jLabel3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("jTextField2");
		}
		return jTextField2;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("jLabel2");
		}
		return jLabel2;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("OutStanding");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("StartDate");
		}
		return jLabel0;
	}

}
