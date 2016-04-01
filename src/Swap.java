import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.standbysoft.component.date.swing.JDatePicker;

import util.NumericTextField;


//VS4E -- DO NOT REMOVE THIS LINE!
public class Swap extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JPanel jPanel0;
	private JLabel jLabel1;
	public NumericTextField jTextField1;
	private JLabel jLabel2;
	public NumericTextField jTextField2;
	private JLabel jLabel3;
	public JDatePicker swapDate;
	public NumericTextField jTextField4;
	DecimalFormat format = new DecimalFormat("##,###,#######");
	public Swap() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 506, 10, 10), new Leading(5, 100, 10, 10)));
		setSize(518, 112);
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new NumericTextField(10,format);
			jTextField4.setText("0");
		}
		return jTextField4;
	}

	private JDatePicker getJTextField3() {
		if (swapDate == null) {
			swapDate = new JDatePicker();
			//jTextField3.setText("jTextField2");
		}
		return swapDate;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("FAR RATE");
		}
		return jLabel3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new  NumericTextField(10,format);
			jTextField2.setText("0");
		}
		return jTextField2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("FAR DATE");
		}
		return jLabel2;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new  NumericTextField(10,format);
			jTextField1.setText("0");
		}
		return jTextField1;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("FAR AMT2");
		}
		return jLabel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTextField1(), new Constraints(new Leading(94, 166, 10, 10), new Leading(8, 28, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(9, 12, 12), new Leading(14, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(266, 66, 12, 12), new Leading(16, 10, 10)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(94, 166, 10, 10), new Leading(48, 28, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 12, 12), new Leading(56, 10, 10)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(266, 57, 12, 12), new Leading(60, 12, 12)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(335, 166, 10, 10), new Leading(12, 28, 12, 12)));
			jPanel0.add(getJTextField4(), new Constraints(new Leading(335, 166, 12, 12), new Leading(52, 28, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("FAR AMT1");
		}
		return jLabel0;
	}

}
