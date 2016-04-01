package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.status.ProgressStatusBarItem;
import com.jidesoft.status.StatusBar;
import com.jidesoft.swing.JideBoxLayout;

import util.commonUTIL;
import apps.window.staticwindow.util.LoginUtil;
import apps.window.tradewindow.JFrameMainApplication;
import beans.StartUPData;
import beans.Users;
 

//VS4E -- DO NOT REMOVE THIS LINE!
public class Login extends JDialog  {

	private static final long serialVersionUID = 1L;
	private JButton loginButton;
	private JPanel jPanel0;
	private JLabel jLabel1;
	public JTextField userName;
	private JLabel jLabel2;
	public JFormattedTextField password;
	 private static StatusBar _statusBar;
	    /**
	 * @return the _statusBar
	 */
	public static StatusBar get_statusBar() {
		return _statusBar;
	}

	/**
	 * @param _statusBar the _statusBar to set
	 */
	public static void set_statusBar(StatusBar _statusBar) {
		Login._statusBar = _statusBar;
	}

		private static Timer _timer;
	/**
		 * @return the _timer
		 */
		public static Timer get_timer() {
			return _timer;
		}

		/**
		 * @param _timer the _timer to set
		 */
		public static void set_timer(Timer _timer) {
			Login._timer = _timer;
		}

	private JButton cancelButton;
	final static ProgressStatusBarItem progress = new ProgressStatusBarItem();
	
	/**
	 * @return the progress
	 */
	public static ProgressStatusBarItem getProgress() {
		return progress;
	}

	public javax.swing.DefaultComboBoxModel grouplistModel = new javax.swing.DefaultComboBoxModel();
	ActionMap actionMap =null;
	Users user = null;
	private JLabel jLabel0;
	 public JComboBox groupList;
	private JFrame jPanel1;
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
		installLnF();
		buildGroupList();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setUndecorated(true);
		setBackground(new Color(128, 0, 255));
		setLocationByPlatform(true);
		setResizable(false);
		setModal(true);
		setForeground(new Color(128, 0, 255));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 200, 10, 10), new Leading(3, 150, 10, 10)));
		//add(getJPanel1(), new Constraints(new Leading(5, 200, 10, 10), new Leading(3, 150, 10, 10)));
		
		setSize(215, 163);
	}

	private JFrame getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JFrame();
			jPanel1.setBackground(new Color(239, 237, 252));
			jPanel1.setUndecorated(true);
			jPanel1.setAlwaysOnTop(false);
	        _statusBar = createStatusBar();
		//	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		//	jPanel1.setLayout(new GroupLayout());
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
					"/resources/icon/cosmos.png"));
			
			jPanel1.setUndecorated(true);
			jPanel1.setBackground(Color.white);
			jPanel1.setAlwaysOnTop(true);
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(new JLabel(imageIcon), new Constraints(new Leading(7, 227, 10, 10), new Leading(3, 58, 10, 10)));
			jPanel1.add(_statusBar, new Constraints(new Leading(5, 230, 12, 12), new Leading(67, 12, 12)));
			jPanel1.setVisible(false);
		
		}
		return jPanel1;
	}
	private static StatusBar createStatusBar() {
        // setup status bar
        StatusBar statusBar = new StatusBar();
        
      
        statusBar.setBorder(BorderFactory.createEmptyBorder());
        progress.setBorder(BorderFactory.createEmptyBorder());
        statusBar.add(progress );
        return statusBar;
	}
	private JComboBox getJComboBox0() {
		if (groupList == null) {
			groupList = new JComboBox();
			groupList.setEditable(true);
			groupList.setModel(grouplistModel);
			groupList.setSelectedIndex(2);
			groupList.setRequestFocusEnabled(false);
			
		}
		return groupList;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Group");
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
			jPanel0.add(getJLabel1(), new Constraints(new Leading(4, 56, 12, 12), new Leading(9, 19, 10, 10)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(4, 52, 12, 12), new Leading(46, 17, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(4, 55, 12, 12), new Leading(79, 10, 10)));
			jPanel0.add(getLoginButton(), new Constraints(new Leading(2, 12, 12), new Leading(117, 26, 12, 12)));
			jPanel0.add(getUserName(), new Constraints(new Leading(69, 124, 10, 10), new Leading(9, 27, 10, 10)));
			jPanel0.add(getPassword(), new Constraints(new Leading(69, 124, 12, 12), new Leading(44, 27, 12, 12)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(69, 124, 12, 12), new Leading(77, 28, 10, 10)));
			jPanel0.add(getCancelButton(), new Constraints(new Leading(83, 77, 10, 10), new Leading(117, 26, 12, 12)));
			//jPanel0.add(getJPanel1(), new Constraints(new Leading(-2, 200, 10, 10), new Leading(0, 150, 12, 12)));
		}
		return jPanel0;
	}

	private Component getCancelButton() {
		// TODO Auto-generated method stub
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				 
						dispose();
					
				}
			});
		}
		return cancelButton;
	}

	private JButton getLoginButton() {
		// TODO Auto-generated method stub
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setText("Login");
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				user = 	buildLogin( );
					if(user == null) {
		        		
		        		JOptionPane.showMessageDialog(null,"Login Failed" ,null,
		           				JOptionPane.INFORMATION_MESSAGE);
		        	} 
					if(user != null) {
						
						jPanel0.setVisible(false);
						getJPanel1();
						jPanel1.setVisible(true);
						jPanel1.setLocationRelativeTo(null);
						jPanel1.setSize(244, 84);
						jPanel1.pack();
						jPanel1.show();
						 dispose();
						if (_timer != null && _timer.isRunning())
		                    return;
		                _timer = new Timer(10, new ActionListener() {
		                    int i = 0;

		                    public void actionPerformed(ActionEvent e) {
		                        if (i == 0)
		                            progress.setProgressStatus("started..");
		                        if (i == 10)
		                            progress.setProgressStatus("Running..");
		                        if (i == 90)
		                            progress.setProgressStatus("Completing..");
		                        progress.setProgress(i++);
		                        if (i > 100) {
		                            _timer.stop();
		                            JFrameMainApplication   mainApp = new JFrameMainApplication(user);
		                          //mainApp.setBackground(commonUTIL.getColors());
		                          		setFrame(mainApp, 500, 100,user); 
		                          		jPanel1.dispose();
		                           
		                        }
		                    }
		                });
		                _timer.start();
		               
						
						
					}
				}
			});
		}
		return loginButton;
	}
	 public   void    setFrame(final JFrame frame, final int width, final int height,final Users user) {   
	    	SwingUtilities.invokeLater(new Runnable() {  
	    		public void run() {  
	    			// LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
	    			frame.setTitle(" Cosmos Main Apps User: " + user.getUser_name());
	    			URL imgURL =  imgURL = this.getClass().getResource("/resources/icon/sql.jpg");
	    			frame.setIconImage(Toolkit.getDefaultToolkit()
	    		      		   .getImage(imgURL));
	    		//	frame.setIconImage(Toolkit.getDefaultToolkit()
	    	      //		   .getImage("E:\\eclipse\\workspace\\sbiCCIL\\resources\\icon\\sql.jpg"));
	    			//frame.setTitle(frame.getClass().getSimpleName());     
	    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    			frame.setSize(width, height);    
	    			frame.setVisible(true);  
	    			} });
	    }

	private Component getPassword() {
		// TODO Auto-generated method stub
		if (password == null) {
			password = new JFormattedTextField();
			password.setText("user1");
		}
		return password;
	}

	private Component getUserName() {
		// TODO Auto-generated method stub
		if (userName == null) {
			userName = new JTextField();
			userName.setText("user1");
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
	public  ArrayList<Component> getFocusOrderList() {
		// TODO Auto-generated method stub
		ArrayList<Component> loginOrder = new ArrayList<Component>();
		loginOrder.add(userName);
		loginOrder.add(password);
		loginOrder.add(groupList);
		loginOrder.add(loginButton);
		loginOrder.add(cancelButton);
//		fOrder.add(out.jTextField2);
//		fOrder.add(out.outRightDate);
		return loginOrder;
		 
	}
	private Users buildLogin(  ) {
		// TODO Auto-generated method stub
		
		 return LoginUtil.fillUserData(this.user, this);
		  
	}
	
	private void buildGroupList() {
		
		
		Vector<String> groupListV = (Vector) LoginUtil.getUsersGroup();
		if(commonUTIL.isEmpty(groupListV)) 
			return;
		commonUTIL.display("Debug",  "LoginWindow buildGroupList","Getting GroupList "+groupListV.size());
		Iterator it = groupListV.iterator();
		int p = 0;
		while (it.hasNext()) { 
		    grouplistModel.insertElementAt((String) it.next(), p);
			 
		}
	}

	 
	
}
