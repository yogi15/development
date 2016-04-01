import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class LimitConfig extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JLabel jLabel0;
	private JComboBox jComboBox0;
	private JComboBox jComboBox1;
	private JRadioButton jRadioButton0;
	private JPanel jPanel0;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton3;
	private JPanel jPanel1;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JComboBox jComboBox2;
	private JComboBox jComboBox3;
	private JComboBox jComboBox4;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JTextField jTextField1;
	private JComboBox jComboBox5;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public LimitConfig() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(6, 262, 12, 12), new Bilateral(8, 9, 10, 437)));
		add(getJPanel2(), new Constraints(new Bilateral(278, 13, 625), new Bilateral(60, 12, 0)));
		add(getJPanel1(), new Constraints(new Bilateral(280, 10, 626), new Leading(8, 44, 10, 10)));
		setSize(1101, 539);
	}

	private JComboBox getJComboBox5() {
		if (jComboBox5 == null) {
			jComboBox5 = new JComboBox();
			jComboBox5.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox5;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("Load");
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("DEL");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("ADD");
		}
		return jButton4;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable1;
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

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("jButton3");
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("DEL");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("ADD");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("NEW");
		}
		return jButton0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane1(), new Constraints(new Bilateral(5, 12, 23), new Leading(221, 234, 10, 10)));
			jPanel2.add(getJScrollPane0(), new Constraints(new Bilateral(6, 14, 23), new Leading(6, 209, 10, 10)));
		}
		return jPanel2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Product Type");
		}
		return jLabel2;
	}

	private JComboBox getJComboBox4() {
		if (jComboBox4 == null) {
			jComboBox4 = new JComboBox();
			jComboBox4.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox4;
	}

	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox3;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox2;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Book");
		}
		return jLabel3;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Currency");
		}
		return jLabel1;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJLabel1(), new Constraints(new Leading(12, 48, 12, 12), new Leading(12, 12, 12)));
			jPanel1.add(getJComboBox2(), new Constraints(new Leading(63, 81, 10, 10), new Leading(8, 28, 12, 12)));
			jPanel1.add(getJLabel3(), new Constraints(new Leading(376, 10, 10), new Leading(17, 12, 12)));
			jPanel1.add(getJComboBox3(), new Constraints(new Leading(244, 110, 12, 12), new Leading(8, 28, 12, 12)));
			jPanel1.add(getJLabel2(), new Constraints(new Leading(160, 78, 12, 12), new Leading(12, 19, 12, 12)));
			jPanel1.add(getJComboBox4(), new Constraints(new Leading(413, 208, 10, 10), new Leading(8, 28, 12, 12)));
			jPanel1.add(getJButton5(), new Constraints(new Leading(737, 54, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJButton4(), new Constraints(new Leading(665, 54, 12, 12), new Leading(11, 12, 12)));
		}
		return jPanel1;
	}

	private JRadioButton getJRadioButton3() {
		if (jRadioButton3 == null) {
			jRadioButton3 = new JRadioButton();
			jRadioButton3.setSelected(true);
			jRadioButton3.setText("Nominal Limit");
		}
		return jRadioButton3;
	}

	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setSelected(true);
			jRadioButton2.setText("Settlement Limit ");
		}
		return jRadioButton2;
	}

	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setSelected(true);
			jRadioButton1.setText("Trader Limit");
		}
		return jRadioButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(7, 47, 10, 10), new Leading(19, 21, 12, 12)));
			jPanel0.add(getJRadioButton0(), new Constraints(new Leading(9, 172, 12, 12), new Leading(62, 10, 10)));
			jPanel0.add(getJRadioButton1(), new Constraints(new Leading(7, 172, 12, 12), new Leading(119, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(50, 172, 10, 10), new Leading(18, 26, 12, 12)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(7, 223, 10, 10), new Leading(89, 27, 10, 10)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(63, 60, 12, 12), new Leading(481, 10, 10)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(126, 60, 10, 10), new Leading(481, 10, 10)));
			jPanel0.add(getJButton3(), new Constraints(new Trailing(12, 60, 12, 12), new Leading(481, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(3, 12, 12), new Leading(481, 10, 10)));
			jPanel0.add(getJComboBox1(), new Constraints(new Leading(9, 219, 12, 12), new Leading(148, 27, 12, 12)));
			jPanel0.add(getJRadioButton3(), new Constraints(new Leading(9, 185, 12, 12), new Leading(253, 10, 10)));
			jPanel0.add(getJButton6(), new Constraints(new Leading(5, 57, 10, 10), new Leading(452, 12, 12)));
			jPanel0.add(getJRadioButton2(), new Constraints(new Leading(9, 172, 12, 12), new Leading(189, 10, 10)));
			jPanel0.add(getJComboBox5(), new Constraints(new Leading(12, 219, 12, 12), new Leading(218, 27, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(12, 218, 12, 12), new Leading(284, 26, 10, 10)));
		}
		return jPanel0;
	}

	private JRadioButton getJRadioButton0() {
		if (jRadioButton0 == null) {
			jRadioButton0 = new JRadioButton();
			jRadioButton0.setSelected(true);
			jRadioButton0.setText("Counter Party Limit");
		}
		return jRadioButton0;
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
			jLabel0.setText("Name");
		}
		return jLabel0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

}
