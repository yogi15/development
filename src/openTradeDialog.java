import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class openTradeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private JLabel jLabel0;
	private JComboBox jComboBox0;
	private JComboBox jComboBox1;
	private JLabel jLabel1;
	private JComboBox jComboBox2;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField jTextField0;
	private JTextField jTextField1;
	private JLabel jLabel4;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel0;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public openTradeDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public openTradeDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public openTradeDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public openTradeDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public openTradeDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public openTradeDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public openTradeDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public openTradeDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public openTradeDialog(Frame parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog() {
		initComponents();
	}

	public openTradeDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public openTradeDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 830, 10, 10), new Leading(8, 379, 10, 10)));
		setSize(838, 391);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Leading(4, 253, 10, 10), new Leading(2, 370, 10, 10)));
			jPanel0.add(getJPanel2(), new Constraints(new Leading(263, 557, 10, 10), new Leading(5, 367, 12, 12)));
		}
		return jPanel0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane0(), new Constraints(new Leading(6, 543, 10, 10), new Leading(5, 352, 10, 10)));
		}
		return jPanel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJButton0(), new Constraints(new Leading(10, 10, 10), new Leading(327, 10, 10)));
			jPanel1.add(getJLabel0(), new Constraints(new Leading(8, 10, 10), new Leading(14, 10, 10)));
			jPanel1.add(getJComboBox0(), new Constraints(new Leading(106, 137, 10, 10), new Leading(12, 28, 12, 12)));
			jPanel1.add(getJComboBox1(), new Constraints(new Leading(106, 137, 12, 12), new Leading(49, 28, 10, 10)));
			jPanel1.add(getJLabel1(), new Constraints(new Leading(8, 10, 10), new Leading(52, 24, 12, 12)));
			jPanel1.add(getJComboBox2(), new Constraints(new Leading(106, 137, 10, 10), new Leading(94, 28, 10, 10)));
			jPanel1.add(getJLabel2(), new Constraints(new Leading(8, 12, 12), new Leading(101, 12, 12)));
			jPanel1.add(getJLabel3(), new Constraints(new Leading(8, 12, 12), new Leading(152, 10, 10)));
			jPanel1.add(getJTextField0(), new Constraints(new Bilateral(106, 12, 6), new Leading(149, 12, 12)));
			jPanel1.add(getJTextField1(), new Constraints(new Leading(106, 136, 12, 12), new Leading(198, 10, 10)));
			jPanel1.add(getJLabel4(), new Constraints(new Leading(150, 10, 10), new Leading(177, 12, 12)));
		}
		return jPanel1;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("TO");
		}
		return jLabel4;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Date From");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Currency Pair");
		}
		return jLabel2;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Product SubType");
		}
		return jLabel1;
	}

	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox1;
	}

	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Product Type");
		}
		return jLabel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Query");
		}
		return jButton0;
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
				openTradeDialog dialog = new openTradeDialog();
				dialog.setDefaultCloseOperation(openTradeDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("openTradeDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
