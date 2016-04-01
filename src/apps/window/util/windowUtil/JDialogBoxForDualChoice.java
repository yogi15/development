package apps.window.util.windowUtil;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.SortShell;

import com.jidesoft.list.AbstractDualListModel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class JDialogBoxForDualChoice extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JList jList0;
	private JScrollPane jScrollPane0;
	private JList jList1;
	private JScrollPane jScrollPane1;
	private JButton jButton0;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton1;
	static JDialogBoxForDualChoice choice = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public JDialogBoxForDualChoice(Window parent) {
		super(parent);
		initComponents();
	}

	public JDialogBoxForDualChoice(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JDialogBoxForDualChoice(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public JDialogBoxForDualChoice(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxForDualChoice(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public JDialogBoxForDualChoice(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public JDialogBoxForDualChoice(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxForDualChoice(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public JDialogBoxForDualChoice(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public JDialogBoxForDualChoice(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JDialogBoxForDualChoice(Frame parent) {
		super(parent);
		initComponents();
	}

	public JDialogBoxForDualChoice() {
		initComponents();
	}

	public JDialogBoxForDualChoice(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public JDialogBoxForDualChoice(Dialog parent) {
		super(parent);
		initComponents();
	}

	public JDialogBoxForDualChoice(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public JDialogBoxForDualChoice(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}
	Vector<String>  leftD = null;
	Vector<String>  rightD = null;
	public JDialogBoxForDualChoice(   Vector<String> leftData,Vector<String> rightData) {
	 	leftD = leftData;
		rightD = rightData;
		initComponents(leftData,rightData);
	}
	
	
        	
        

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 434, 10, 10), new Leading(2, 295, 10, 10)));
		setSize(490, 320);
	}
	private void initComponents( Vector<String> leftData,Vector<String> rightData) {
		 
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0( ), new Constraints(new Leading(5, 434, 10, 10), new Leading(2, 295, 10, 10)));
		setSize(450, 340);
		setResizable(false);
	}
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("<<");
			jButton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 
					if(jList1.getSelectedIndices().length > 0 ) {
						int v [] = jList1.getSelectedIndices();
						for(int i=0;i < v.length;i++) {
							String ss = rightD.get(v[i]);
							if(!leftD.contains(ss))
							leftD.add(leftD.size(), ss);
						 
							
						}
						 
						Arrays.sort(v);
						 
						for(int i=v.length;i > 0;i--) {
							 
								 
							rightD.removeElementAt(v[i-1]);
								 
							
						}
					}
					jList0.setListData(leftD);
					jList1.setListData(rightD);
					 
				}
		});
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("V");
			jButton2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				/*	if(jList1.getSelectedIndex() > 0 ) {
						int v [] = jList1.getSelectedIndices();
						for(int i=v.length;i >0; i--) {
							String ss = rightD.get(v[i-1]);
							if(v[i-1] < rightD.size()) {
							rightD.removeElementAt(v[i-1]);
							
							rightD.add(v[i-1]+1, ss);
							}
							
						}
					}
					jList1.setListData(rightD);*/
				}
			});
		}
		return jButton2;
	}
		private JButton getJButton3() {
			if (jButton3 == null) {
				jButton3 = new JButton();
				jButton3.setText(">>");
				
				
			}
			return jButton3;
		}
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText(">>");
			jButton0.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(jList0.getSelectedIndex() > 0 ) {
							int v [] = jList0.getSelectedIndices();
							for(int i=0;i < v.length;i++) {
								String ss = leftD.get(v[i]);
								if(!rightD.contains(ss))
								rightD.add(rightD.size(), ss);
							//	leftD.removeElementAt(v[i]);
							}
							  
							Arrays.sort(v);
						 
							for(int i=v.length;i > 0;i--) {
								 
									 
									  leftD.removeElementAt(v[i-1]);
									 
								
							}
							
						}
						jList0.setListData(leftD);
						jList1.setListData(rightD);
					}
			});
		}
		return jButton0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJList1( ));
		}
		return jScrollPane1;
	}

	private JList getJList1( ) {
		if (jList1 == null) {
			jList1 = new JList(rightD);
			jList1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			 
		}
		return jList1;
	}

	private JScrollPane getJScrollPane0( ) {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0( ));
		}
		return jScrollPane0;
	}

	private JList getJList0( ) {
		if (jList0 == null) {
			jList0 = new JList(leftD);
			jList0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			 
		}
		return jList0;
	}
    public void ClearALL() {
    	rightD.clear();
    	leftD.clear();
    	rightD = null;
    	leftD = null;
    }
	private JPanel getJPanel0( ) {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0( ), new Constraints(new Leading(4, 167, 10, 10), new Leading(3, 284, 10, 10)));
			jPanel0.add(getJScrollPane1( ), new Constraints(new Leading(257, 171, 10, 10), new Leading(2, 285, 12, 12)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(186, 53, 12, 12), new Leading(60, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(186, 53, 12, 12), new Leading(101, 10, 10)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(186, 53, 12, 12), new Leading(140, 10, 10)));
			jPanel0.add(getJButton3(), new Constraints(new Leading(186, 53, 12, 12), new Leading(180, 10, 10)));
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
				JDialogBoxForDualChoice dialog = new JDialogBoxForDualChoice();
				dialog.setDefaultCloseOperation(JDialogBoxForDualChoice.DISPOSE_ON_CLOSE);
				dialog.setTitle("JDialogBoxForDualChoice");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	
	class  ListDataModel extends AbstractDualListModel {
		private static final long serialVersionUID = 6612638234750453824L;
		public ListDataModel(String [] data) {
			DATA_TABLE = data;
		}
		private   String[] DATA_TABLE = { "Argentina", "Australia",
				"Brazil", "Canada", "China", "France", "Germany", "India",
				"Ireland", "Italy", "Japan", "Portugal", "Russia", "Saudi Arabia",
				"Singapore", "South Korea", "Spain", "Sweden",
				"United Arab Emirates", "United Kingdom", "United States" };

		public int getSize() {
			return DATA_TABLE.length;
		}

		public Object getElementAt(int index) {
			return DATA_TABLE[index];
		}
	}

	public Vector<String> getObj() {
		// TODO Auto-generated method stub
		return rightD;
	}
	public Vector<String> getRightObj() {
		// TODO Auto-generated method stub
		return rightD;
	}
	public Vector<String> getLeftObj() {
		// TODO Auto-generated method stub
		return leftD;
	}
}
