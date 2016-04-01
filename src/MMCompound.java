import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMCompound extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JComboBox compoundFreqCombobox;
	private JComboBox compoundMethodCombobox;
	private JComboBox payFreqCombobox;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMCompound() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Leading(211, 10, 10), new Trailing(12, 12, 12)));
		add(getcompoundMethodCombobox(), new Constraints(new Leading(287, 105, 10, 10), new Leading(14, 24, 10, 10)));
		add(getcompoundFreqCombobox(), new Constraints(new Trailing(12, 125, 468, 468), new Leading(12, 23, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(413, 10, 10), new Leading(21, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(16, 10, 10), new Trailing(12, 12, 12)));
		add(getpayFreqCombobox(), new Constraints(new Leading(79, 108, 10, 10), new Trailing(12, 20, 12, 12)));
		setSize(617, 50);
	}

	private JComboBox getpayFreqCombobox() {
		if (payFreqCombobox == null) {
			payFreqCombobox = new JComboBox();
			payFreqCombobox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			payFreqCombobox.setDoubleBuffered(false);
			payFreqCombobox.setBorder(null);
		}
		return payFreqCombobox;
	}

	private JComboBox getcompoundMethodCombobox() {
		if (compoundMethodCombobox == null) {
			compoundMethodCombobox = new JComboBox();
			compoundMethodCombobox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			compoundMethodCombobox.setDoubleBuffered(false);
			compoundMethodCombobox.setBorder(null);
		}
		return compoundMethodCombobox;
	}

	private JComboBox getcompoundFreqCombobox() {
		if (compoundFreqCombobox == null) {
			compoundFreqCombobox = new JComboBox();
			compoundFreqCombobox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			compoundFreqCombobox.setDoubleBuffered(false);
			compoundFreqCombobox.setBorder(null);
		}
		return compoundFreqCombobox;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Cmp Freq");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Cmp Method");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Pay Freq");
		}
		return jLabel0;
	}

}
