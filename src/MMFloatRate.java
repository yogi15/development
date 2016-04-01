import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMFloatRate extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JComboBox rateIndeComboBox;
	private JLabel jLabel1;
	private JComboBox tenorComboBox;
	private JLabel jLabel2;
	private JTextField jTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMFloatRate() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJTextField0(), new Constraints(new Leading(507, 74, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(258, 10, 10), new Leading(18, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(452, 10, 10), new Leading(18, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(15, 10, 10), new Leading(20, 12, 12)));
		add(gettenorComboBox(), new Constraints(new Leading(308, 115, 10, 10), new Leading(12, 22, 12, 12)));
		add(getrateIndeComboBox(), new Constraints(new Leading(101, 123, 10, 10), new Leading(12, 22, 12, 12)));
		setSize(633, 58);
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Spread");
		}
		return jLabel2;
	}

	private JComboBox gettenorComboBox() {
		if (tenorComboBox == null) {
			tenorComboBox = new JComboBox();
			tenorComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			tenorComboBox.setDoubleBuffered(false);
			tenorComboBox.setBorder(null);
		}
		return tenorComboBox;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Tenor");
		}
		return jLabel1;
	}

	private JComboBox getrateIndeComboBox() {
		if (rateIndeComboBox == null) {
			rateIndeComboBox = new JComboBox();
			rateIndeComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			rateIndeComboBox.setDoubleBuffered(false);
			rateIndeComboBox.setBorder(null);
		}
		return rateIndeComboBox;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Rate Index");
		}
		return jLabel0;
	}

}
