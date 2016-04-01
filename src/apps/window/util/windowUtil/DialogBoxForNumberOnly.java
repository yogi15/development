package apps.window.util.windowUtil;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.Window;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.NumericTextField;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DialogBoxForNumberOnly extends JDialog {

	private static final long serialVersionUID = 1L;
	public JLabel jLabel0;
	public JLabel jLabel1;
	public JTextField jTextField2;
	private JLabel jLabel3;
	public NumericTextField jTextField0;
	public JButton jButton0;
	private JLabel jLabel2;
	public NumericTextField jTextField1;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public DialogBoxForNumberOnly(Window parent) {
		super(parent);
		initComponents();
	}
	public DialogBoxForNumberOnly(String name) {
	//	super(parent);
		
		initComponents();
		jLabel1.setText(name);
	}
	public URL getImageURL(String s) {

		return this.getClass().getResource(s);
	}
	public DialogBoxForNumberOnly(String name,String betweenR,String alias) {
		//	super(parent);
			if(betweenR.equalsIgnoreCase("between") && alias.endsWith("ID") ) {
			initComponents(betweenR,name);

			jLabel1.setText(name);
			} else if(betweenR.equalsIgnoreCase("between") && alias.endsWith("Date")) {
				initComponents(betweenR,"");
			URL url = getImageURL("/resources/icon/sql.jpg");
			setIconImage(Toolkit.getDefaultToolkit().getImage(url));
			jLabel1.setText(name);
			} else {
				initComponents(name);
				jLabel1.setText(name);
			}
		}
	public DialogBoxForNumberOnly(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DialogBoxForNumberOnly(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DialogBoxForNumberOnly(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogBoxForNumberOnly(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public DialogBoxForNumberOnly(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DialogBoxForNumberOnly(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogBoxForNumberOnly(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DialogBoxForNumberOnly(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogBoxForNumberOnly(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DialogBoxForNumberOnly(Frame parent) {
		super(parent);
		initComponents();
	}

	public DialogBoxForNumberOnly() {
		initComponents();
	}

	public DialogBoxForNumberOnly(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DialogBoxForNumberOnly(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DialogBoxForNumberOnly(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DialogBoxForNumberOnly(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents(String name) {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(45, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(25, 12, 12), new Leading(12, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(123, 124, 10, 10), new Leading(8, 25, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(125, 10, 10), new Leading(42, 10, 10)));
	//	add(getJLabel2(), new Constraints(new Leading(255, 10, 10), new Leading(12, 12, 12)));
		//add(getJTextField1(), new Constraints(new Leading(276, 115, 12, 12), new Leading(7, 27, 12, 12)));
		setSize(306, 121);
		setResizable(false);
	}
	private void initComponents(String dateRange,String to) {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(45, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(25, 12, 12), new Leading(12, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(123, 124, 10, 10), new Leading(8, 25, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(125, 10, 10), new Leading(42, 10, 10)));
		add(getJLabel2(), new Constraints(new Leading(255, 10, 10), new Leading(12, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(276, 115, 12, 12), new Leading(7, 27, 12, 12)));
		setSize(414, 121);
		setResizable(false);
	}
	private NumericTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new NumericTextField();
		}
		return jTextField1;
	}
	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("To");
		}
		return jLabel2;
	}
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Assign");
		}
		return jButton0;
	}
private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("To");
		}
		return jLabel3;
	}
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(45, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(25, 12, 12), new Leading(12, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(123, 124, 10, 10), new Leading(8, 25, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(125, 10, 10), new Leading(42, 10, 10)));
		add(getJTextField2(), new Constraints(new Leading(294, 117, 10, 10), new Leading(8, 24, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(261, 12, 12), new Leading(12, 12, 12)));
		setSize(456, 95);
	}
	private NumericTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new NumericTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("jLabel1");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
		}
		return jLabel0;
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
			public void run() {
				DialogBoxForNumberOnly dialog = new DialogBoxForNumberOnly();
				dialog.setDefaultCloseOperation(DialogBoxForNumberOnly.DISPOSE_ON_CLOSE);
				dialog.setTitle("DialogBoxForNumberOnly");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
