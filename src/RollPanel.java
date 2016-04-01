import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.NumericTextField;

import com.standbysoft.component.date.swing.JDatePicker;


//VS4E -- DO NOT REMOVE THIS LINE!
public class RollPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JDatePicker jTextField0;
	private NumericTextField jTextField1;

	DecimalFormat format = new DecimalFormat("##,###,#######");
	private JPanel jPanel0;
	private JLabel jLabel2;
	private JTextField jTextField2;
	private JCheckBox jCheckBox0;

	public RollPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(7, 331, 10, 10), new Leading(6, 90, 10, 10)));
		setSize(348, 100);
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setSelected(true);
			jCheckBox0.setText("jCheckBox0");
		}
		return jCheckBox0;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("jTextField2");
		}
		return jTextField2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Roll Amt");
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("ROLL DATE");
		}
		return jLabel0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel2(), new Constraints(new Leading(215, 58, 10, 10), new Leading(32, 18, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(81, 125, 12, 12), new Leading(12, 27, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(3, 68, 12, 12), new Leading(16, 12, 12)));
			jPanel0.add(getJCheckBox0(), new Constraints(new Leading(212, 12, 12), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(3, 60, 12, 12), new Leading(54, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(79, 124, 12, 12), new Leading(50, 25, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(212, 115, 12, 12), new Leading(51, 25, 12, 12)));
		}
		return jPanel0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new NumericTextField(10,format);
			jTextField1.setText("0");
		}
		return jTextField1;
	}

	private JDatePicker getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JDatePicker();
			//jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("FWD RATE");
		}
		return jLabel1;
	}

}
