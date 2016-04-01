package apps.window.util.windowUtil;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class JDialogBoxJListSingleSelection extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField jTextField0;
	public int templateId = 0;
	/**
	 * @return the templateId
	 */
	public int getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public JList jList0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public JDialogBoxJListSingleSelection(Window parent) {
		super(parent);
		initComponents();
		
	}
	DefaultListModel cmodList = null;
	public JDialogBoxJListSingleSelection(DefaultListModel cmodList ) {
	//	initComponents();
		
		this.cmodList = cmodList;
		
		initComponents();
		
	}
	public JDialogBoxJListSingleSelection(Dialog parent, String title,
			boolean modal, GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Dialog parent, String title,
			boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Window parent,
			ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Frame parent) {
		super(parent);
		initComponents();
	}

	public JDialogBoxJListSingleSelection() {
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Dialog parent) {
		super(parent);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Frame parent, String title,
			boolean modal, GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JDialogBoxJListSingleSelection(Frame parent, String title,
			boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
	//	setUndecorated(true);
		add(getJPanel0(), new Constraints(new Leading(3, 213, 10, 10), new Bilateral(3, 12, 180)));
		setSize(226, 195);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(4, 203, 10, 10), new Leading(6, 26, 10, 10)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(5, 200, 12, 12), new Leading(35, 135, 10, 10)));
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0());
		}
		return jScrollPane0;
	}

	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			jList0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			jList0.setModel(cmodList);
			ListSelectionModel model = jList0.getSelectionModel();
			model.addListSelectionListener(new ListSelectionChangeHandler());
			this.dispose();
			
		} 
		return jList0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
		} jTextField0.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		return jTextField0;
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
				JDialogBoxJListSingleSelection dialog = new JDialogBoxJListSingleSelection();
				dialog.setDefaultCloseOperation(JDialogBoxJListSingleSelection.DISPOSE_ON_CLOSE);
				dialog.setTitle("JDialogBoxJListSingleSelection");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	class ListSelectionChangeHandler implements ListSelectionListener {

		

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
		 
		   jTextField0.setText(jList0.getSelectedValue().toString());
		   setTemplateId(jList0.getSelectedIndex());
			
		}
	}

}
