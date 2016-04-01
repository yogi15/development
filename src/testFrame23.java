import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.NumericTextField;


//VS4E -- DO NOT REMOVE THIS LINE!
public class testFrame23 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton0;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JPanel jPanel1;
	private JFormattedTextField jFormattedTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public testFrame23() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(4, 468, 10, 10), new Leading(3, 78, 10, 10)));
		add(getJPanel1(), new Constraints(new Trailing(0, 468, 12, 12), new Leading(83, 200, 10, 10)));
		setSize(478, 313);
	}

	private JFormattedTextField getJFormattedTextField0() {
		if (jFormattedTextField0 == null) {
			jFormattedTextField0 = new JFormattedTextField();
		//	jFormattedTextField0.setfor
			jFormattedTextField0.setText("jFormattedTextField0");
		}
		return jFormattedTextField0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.green, new Color(255, 128, 0)));
			jPanel1.setToolTipText("testing");
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJButton1(), new Constraints(new Leading(200, 10, 10), new Leading(27, 10, 10)));
			jPanel1.add(getJButton2(), new Constraints(new Leading(345, 10, 10), new Leading(27, 10, 10)));
			jPanel1.add(getJButton0(), new Constraints(new Leading(41, 10, 10), new Leading(22, 10, 10)));
			jPanel1.add(getJLabel0(), new Constraints(new Leading(133, 73, 12, 12), new Leading(22, 23, 10, 10)));
			jPanel1.add(getJTextField0(), new Constraints(new Leading(60, 142, 10, 10), new Leading(93, 10, 10)));
			jPanel1.add(getJFormattedTextField0(), new Constraints(new Leading(224, 10, 10), new Leading(111, 10, 10)));
		}
		return jPanel1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new NumericTextField();
			//jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("jButton2");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
		}
		return jButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.green, new Color(255, 128, 0)));
			jPanel0.setToolTipText("testing");
			jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				testFrame23 frame = new testFrame23();
				frame.setDefaultCloseOperation(testFrame23.EXIT_ON_CLOSE);
				frame.setTitle("testFrame23");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
