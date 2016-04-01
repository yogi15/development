import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton loginButton;
	private JPanel jPanel0;
	private JLabel jLabel1;
	private JTextField userName;
	private JLabel jLabel2;
	private JTextField password;
	private JButton cancelButton;
	private JLabel jLabel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public Login(Window parent) {
		super(parent);
		initComponents();
	}

	public Login(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public Login(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public Login(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public Login(Window parent, String title, ModalityType modalityType,
			GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public Login(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public Login(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public Login(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public Login(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public Login(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public Login(Frame parent) {
		super(parent);
		initComponents();
	}

	public Login() {
		initComponents();
	}

	public Login(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public Login(Dialog parent) {
		super(parent);
		initComponents();
	}

	public Login(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public Login(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setTitle("Cosmos");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setUndecorated(true);
		setBackground(new Color(128, 0, 255));
		setLocationByPlatform(true);
		setResizable(false);
		setModal(true);
		setForeground(new Color(128, 0, 255));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(7, 10, 10), new Leading(5, 158, 10, 10)));
		setSize(283, 173);
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("PassWord");
		}
		return jLabel2;
	}

	 

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("UserName");
		}
		return jLabel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBackground(new Color(239, 237, 252));
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel1(), new Constraints(new Leading(16, 67, 10, 10), new Leading(14, 27, 10, 10)));
			jPanel0.add(getUserName(), new Constraints(new Leading(104, 146, 10, 10), new Leading(14, 27, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(14, 67, 10, 10), new Leading(53, 27, 12, 12)));
			jPanel0.add(getPassword(), new Constraints(new Leading(104, 146, 12, 12), new Leading(53, 27, 12, 12)));
			jPanel0.add(getLoginButton(), new Constraints(new Leading(14, 12, 12), new Leading(124, 26, 10, 10)));
			jPanel0.add(getCancelButton(), new Constraints(new Leading(104, 67, 12, 12), new Leading(124, 26, 10, 10)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(14, 12, 12), new Leading(98, 12, 12)));
		}
		return jPanel0;
	}

	private Component getCancelButton() {
		// TODO Auto-generated method stub
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
		}
		return cancelButton;
	}

	private JButton getLoginButton() {
		// TODO Auto-generated method stub
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setText("Login");
		}
		return loginButton;
	}

	private Component getPassword() {
		// TODO Auto-generated method stub
		if (password == null) {
			password = new JTextField();
		}
		return password;
	}

	private Component getUserName() {
		// TODO Auto-generated method stub
		if (userName == null) {
			userName = new JTextField();
		}
		return userName;
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
				Login dialog = new Login();
				dialog.setDefaultCloseOperation(Login.DISPOSE_ON_CLOSE);
				dialog.setTitle("Login");
				dialog.setResizable(false);
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
