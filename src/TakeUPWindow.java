import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TakeUPWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField0;
	private JTextField jTextField1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JLabel jLabel14;
	private JTextField jTextField6;
	private JTextField jTextField7;
	private JLabel jLabel2;
	private JButton jButton0;
	private JButton jButton1;
	private JTable jTable0;
	private JScrollPane jScrollPane0;

	public TakeUPWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(6, 970, 10, 10), new Leading(6, 418, 10, 10)));
		setSize(987, 433);
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

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Edit");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Save");
		}
		return jButton0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("EndDate");
		}
		return jLabel2;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setText("jTextField7");
		}
		return jTextField7;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText("settleDate");
		}
		return jTextField6;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Settle Date");
		}
		return jLabel14;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setText("StartDate");
		}
		return jTextField4;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setText("Trade Date");
		}
		return jTextField5;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("Trade Date");
		}
		return jLabel13;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Start Date");
		}
		return jLabel12;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText("Curr22");
		}
		return jTextField3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("Curr21");
		}
		return jTextField2;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("CURR2");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("CURR2");
		}
		return jLabel10;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("TakeUP");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("OutStanding");
		}
		return jLabel0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("Curr12");
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("Curr11");
		}
		return jTextField0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel5(), new Constraints(new Leading(100, 12, 12), new Leading(21, 23, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(17, 73, 12, 12), new Leading(21, 23, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(17, 58, 12, 12), new Leading(64, 23, 12, 12)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(102, 10, 10), new Leading(64, 23, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(153, 152, 12, 12), new Leading(59, 29, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(151, 154, 12, 12), new Leading(14, 30, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(320, 10, 10), new Leading(21, 23, 12, 12)));
			jPanel0.add(getJLabel11(), new Constraints(new Leading(317, 12, 12), new Leading(64, 23, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(374, 154, 10, 10), new Leading(14, 30, 12, 12)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(374, 154, 12, 12), new Leading(59, 30, 12, 12)));
			jPanel0.add(getJLabel12(), new Constraints(new Leading(552, 76, 10, 10), new Leading(21, 23, 12, 12)));
			jPanel0.add(getJLabel13(), new Constraints(new Leading(552, 76, 10, 10), new Leading(63, 23, 12, 12)));
			jPanel0.add(getJTextField5(), new Constraints(new Leading(631, 163, 10, 10), new Leading(59, 30, 12, 12)));
			jPanel0.add(getJTextField4(), new Constraints(new Leading(631, 162, 12, 12), new Leading(14, 30, 12, 12)));
			jPanel0.add(getJLabel14(), new Constraints(new Leading(552, 76, 12, 12), new Leading(105, 23, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(631, 163, 12, 12), new Leading(101, 30, 12, 12)));
			jPanel0.add(getJTextField7(), new Constraints(new Leading(806, 162, 12, 12), new Leading(56, 30, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(806, 76, 12, 12), new Leading(18, 23, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(285, 10, 10), new Leading(146, 12, 12)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(367, 62, 10, 10), new Leading(146, 12, 12)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(6, 955, 10, 10), new Leading(183, 229, 10, 10)));
		}
		return jPanel0;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("CURR1");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("CURR1");
		}
		return jLabel4;
	}

}
