import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class JTreeChoiceDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JList jList1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel jPanel0;
	public DefaultListModel cmodList2 = new DefaultListModel();
	public DefaultMutableTreeNode _root;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public JTreeChoiceDialog(Window parent,DefaultMutableTreeNode nodes) {
		
		super(parent);
		_root = nodes;
		initComponents();
	}

	public JTreeChoiceDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JTreeChoiceDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public JTreeChoiceDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JTreeChoiceDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public JTreeChoiceDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public JTreeChoiceDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JTreeChoiceDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public JTreeChoiceDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JTreeChoiceDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JTreeChoiceDialog(Frame parent) {
		super(parent);
		initComponents();
	}

	public JTreeChoiceDialog() {
		initComponents();
	}

	public JTreeChoiceDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JTreeChoiceDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public JTreeChoiceDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JTreeChoiceDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(4, 12, 0), new Leading(7, 351, 10, 10)));
		setSize(366, 367);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(7, 124, 10, 10), new Leading(8, 336, 10, 10)));
			jPanel0.add(getJScrollPane1(), new Constraints(new Leading(216, 124, 10, 10), new Leading(9, 335, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(140, 62, 10, 10), new Leading(114, 10, 10)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(140, 62, 12, 12), new Leading(152, 12, 12)));
		}
		return jPanel0;
	}

	private JButton getJButton1() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("<<");
		} jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
        
				if(jList1.getSelectedIndex() >= 0) { 
					int i = jList1.getSelectedIndex();
					
					
					jList1.removeAll();
					cmodList2.remove(i);
					
					jList1.setModel(cmodList2);
					}
				
			}
    		
    	});
		return jButton2;
	}

	private JButton getJButton0() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText(">>");
		} jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 DefaultMutableTreeNode _selectedName  = null;
				    DefaultMutableTreeNode _selectedValue = null;
				    String name = "";
				    String value = "";
				TreePath selPath = jTree0.getSelectionPath();
				if (selPath.getPathCount() >= 2) {
			        _selectedName =
			        (DefaultMutableTreeNode)selPath.getPathComponent(1);
			        name = _selectedName.toString();
			        if (selPath.getPathCount() >= 3) {
			        _selectedValue =
			            (DefaultMutableTreeNode)selPath.getPathComponent(2);
			        value = _selectedValue.toString();
			        }
			    }
			    
				if(selPath != null) {
					if(!cmodList2.contains(name+"."+value))
					cmodList2.addElement(name+"."+value);
					jList1.removeAll();
					jList1.setModel(cmodList2);
				}
					
			}
        	
        });
		return jButton1;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJList0());
		}
		return jScrollPane1;
	}

	private JList getJList0() {
		if (jList1 == null) {
			jList1 = new JList();
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("item0");
			listModel.addElement("item1");
			listModel.addElement("item2");
			listModel.addElement("item3");
			jList1.setModel(cmodList2);
		}
		return jList1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTree0());
		}
		return jScrollPane0;
	}

	private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree(_root);

		//	jTree0.setModel(_root);
		}
		return jTree0;
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
				JTreeChoiceDialog dialog = new JTreeChoiceDialog();
				dialog.setDefaultCloseOperation(JTreeChoiceDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("JTreeChoiceDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
