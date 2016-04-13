package apps.window.staticwindow; 
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.jidesoft.status.ProgressStatusBarItem;
import com.jidesoft.status.StatusBar;
import com.jidesoft.swing.JideBoxLayout;

import beans.Users;

//VS4E -- DO NOT REMOVE THIS LINE!
public class LoginProgressBar extends JDialog  {

	private static final long serialVersionUID = 1L;
	 
	private JPanel jPanel0;
	private JLabel jLabel1;     
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	 
	public LoginProgressBar(Window parent) {
		super(parent);
		
		initComponents();
	}

	public LoginProgressBar(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public LoginProgressBar(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public LoginProgressBar(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public LoginProgressBar(Window parent, String title, ModalityType modalityType,
			GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public LoginProgressBar(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public LoginProgressBar(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public LoginProgressBar(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public LoginProgressBar(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public LoginProgressBar(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public LoginProgressBar(Frame parent) {
		super(parent);
		initComponents();
	}

	public LoginProgressBar( ) {
		 
		initComponents();
	}

	public LoginProgressBar(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public LoginProgressBar(Dialog parent) {
		super(parent);
		initComponents();
	}

	public LoginProgressBar(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public LoginProgressBar(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setTitle("Cosmos");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setUndecorated(true);
		setBackground(new Color(128, 0, 255));
		setLocationByPlatform(true);
		setModal(true);
		 
		setForeground(new Color(128, 0, 255));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 200, 10, 10), new Leading(3, 150, 10, 10)));
		setSize(215, 163);
		add(getJPanel0(), new Constraints(new Bilateral(5, 0, 206), new Leading(3, 50, 10, 10)));
		setSize(213, 63);
		 setResizable(false);
		 setLocationRelativeTo(null);
		 setVisible(true); 
        
	}

	 

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
					"/resources/icon/sql.jpg"));
			 
			jLabel1 = new JLabel(imageIcon);
		//	jLabel1.setBorder(new LineBorder(Color.black, 1, false));
			
		    


			
		}
		return jLabel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBackground(new Color(239, 237, 252));
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel1(), new Constraints(new Leading(0, 202, 10, 10), new Leading(2, 30, 10, 10)));
			 
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
			public void run() {
				LoginProgressBar dialog = new LoginProgressBar( );
				dialog.setDefaultCloseOperation(LoginProgressBar.DISPOSE_ON_CLOSE);
				dialog.setTitle("LoginProgressBar");
				dialog.setResizable(false);
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	 
	   
	
}
