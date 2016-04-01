import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class ProductWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel9;
	private JPanel jPanel10;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel4;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel15;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JComboBox jComboBox4;
	private JComboBox jComboBox0;
	private JTextField jTextField7;
	private JButton jButton0;
	private JComboBox jComboBox7;
	private JComboBox jComboBox1;
	private JTextField jTextField0;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JTextField jTextField8;
	private JComboBox jComboBox8;
	private JTextField jTextField9;
	private JComboBox jComboBox9;
	private JTextField jTextField10;
	private JTextField jTextField11;
	private JButton button2;
	private JComboBox jComboBox3;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JComboBox jComboBox5;
	private JComboBox jComboBox6;
	private JTextField jTextField6;
	private JTextField jTextField12;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JTextArea jTextArea0;
	private JScrollPane jScrollPane2;
	private JTextField jTextField13;
	private JButton jButton6;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public ProductWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Trailing(11, 343, 10, 10), new Bilateral(65, 12, 0)));
		add(getJPanel0(), new Constraints(new Bilateral(12, 9, 983), new Leading(9, 50, 10, 10)));
		add(getJPanel2(), new Constraints(new Bilateral(12, 360, 0), new Leading(67, 64, 10, 10)));
		add(getJPanel3(), new Constraints(new Leading(12, 476, 10, 10), new Leading(137, 100, 12, 12)));
		add(getJPanel4(), new Constraints(new Bilateral(492, 360, 0), new Leading(137, 100, 12, 12)));
		add(getJPanel6(), new Constraints(new Leading(12, 825, 10, 10), new Trailing(8, 51, 10, 519)));
		add(getJPanel9(), new Constraints(new Leading(14, 479, 10, 10), new Trailing(68, 224, 10, 10)));
		add(getJPanel5(), new Constraints(new Bilateral(12, 361, 0), new Bilateral(243, 298, 40)));
		add(getJPanel10(), new Constraints(new Bilateral(502, 361, 0), new Trailing(68, 224, 295, 321)));
		setSize(1201, 623);
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(new LineBorder(Color.black, 1, false));
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJLabel9(), new Constraints(new Leading(18, 75, 12, 12), new Leading(59, 20, 10, 10)));
			jPanel4.add(getJLabel8(), new Constraints(new Leading(18, 39, 10, 10), new Leading(14, 20, 10, 10)));
			jPanel4.add(getJComboBox1(), new Constraints(new Leading(90, 202, 10, 10), new Leading(53, 26, 10, 10)));
			jPanel4.add(getJTextField13(), new Constraints(new Leading(90, 196, 10, 10), new Leading(12, 27, 12, 12)));
			jPanel4.add(getJButton6(), new Constraints(new Trailing(12, 36, 108, 298), new Leading(12, 25, 12, 12)));
		}
		return jPanel4;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("jButton6");
		}
		return jButton6;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(new LineBorder(Color.black, 1, false));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJLabel5(), new Constraints(new Leading(15, 75, 10, 10), new Leading(12, 20, 12, 12)));
			jPanel3.add(getJLabel6(), new Constraints(new Leading(155, 75, 10, 10), new Leading(12, 20, 10, 10)));
			jPanel3.add(getJLabel7(), new Constraints(new Leading(301, 75, 10, 10), new Leading(12, 20, 12, 12)));
			jPanel3.add(getJTextField0(), new Constraints(new Leading(12, 120, 10, 10), new Leading(44, 33, 12, 12)));
			jPanel3.add(getJTextField3(), new Constraints(new Leading(142, 120, 10, 10), new Leading(48, 29, 10, 10)));
			jPanel3.add(getJTextField4(), new Constraints(new Leading(280, 112, 12, 12), new Leading(50, 27, 12, 12)));
		}
		return jPanel3;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJLabel4(), new Constraints(new Leading(560, 71, 10, 10), new Leading(22, 20, 10, 10)));
			jPanel2.add(getJLabel2(), new Constraints(new Leading(10, 59, 12, 12), new Leading(22, 20, 12, 12)));
			jPanel2.add(getJLabel3(), new Constraints(new Leading(257, 71, 10, 10), new Leading(22, 20, 12, 12)));
			jPanel2.add(getJComboBox0(), new Constraints(new Leading(340, 200, 10, 10), new Leading(16, 26, 12, 12)));
			jPanel2.add(getJComboBox3(), new Constraints(new Leading(79, 160, 12, 12), new Leading(16, 26, 12, 12)));
		}
		return jPanel2;
	}

	private JTextField getJTextField13() {
		if (jTextField13 == null) {
			jTextField13 = new JTextField();
			jTextField13.setText("jTextField13");
		}
		return jTextField13;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTextArea0());
		}
		return jScrollPane2;
	}

	private JTextArea getJTextArea0() {
		if (jTextArea0 == null) {
			jTextArea0 = new JTextArea();
			jTextArea0.setText("jTextArea0");
		}
		return jTextArea0;
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

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane1(), new Constraints(new Leading(8, 326, 10, 10), new Leading(10, 528, 10, 10)));
		}
		return jPanel1;
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

	private JTextField getJTextField12() {
		if (jTextField12 == null) {
			jTextField12 = new JTextField();
			jTextField12.setText("jTextField12");
		}
		return jTextField12;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText("jTextField6");
		}
		return jTextField6;
	}

	private JComboBox getJComboBox6() {
		if (jComboBox6 == null) {
			jComboBox6 = new JComboBox();
			jComboBox6.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox6;
	}

	private JComboBox getJComboBox5() {
		if (jComboBox5 == null) {
			jComboBox5 = new JComboBox();
			jComboBox5.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox5;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("Save As New");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Save");
		}
		return jButton4;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Delete");
		}
		return jButton3;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("New");
		}
		return jButton1;
	}

	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox3;
	}

	private JButton getJButton2() {
		if (button2 == null) {
			button2 = new JButton();
			button2.setText("Load");
		}
		return button2;
	}

	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
			jTextField9.setText("jTextField9");
		}
		return jTextField9;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox9 == null) {
			jComboBox9 = new JComboBox();
			jComboBox9.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox9;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setText("jTextField8");
		}
		return jTextField8;
	}

	private JTextField getJTextField5() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setText("jTextField11");
		}
		return jTextField11;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setText("jTextField4");
		}
		return jTextField4;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText("jTextField3");
		}
		return jTextField3;
	}

	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton4");
		}
		return jButton0;
	}

	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("jTextField2");
		}
		return jTextField2;
	}

	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("Comment");
		}
		return jLabel16;
	}

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("Code");
		}
		return jLabel17;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Face Value");
		}
		return jLabel15;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Total Issue");
		}
		return jLabel14;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("Redem Curr");
		}
		return jLabel13;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Redem Price");
		}
		return jLabel12;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Currency");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Issue Price");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Country");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Issuer");
		}
		return jLabel8;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Maturity Date");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Dated Date ");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Issue Date");
		}
		return jLabel5;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Bond Type");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Bond Class");
		}
		return jLabel2;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Security Type");
		}
		return jLabel4;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel1(), new Constraints(new Leading(706, 63, 12, 12), new Leading(12, 20, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(104, 596, 12, 12), new Leading(5, 27, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(775, 144, 12, 12), new Leading(5, 27, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(7, 83, 10, 10), new Leading(12, 20, 10, 10)));
		}
		return jPanel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Product ID");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Product Name");
		}
		return jLabel0;
	}

	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			jPanel10 = new JPanel();
			jPanel10.setBorder(new LineBorder(Color.black, 1, false));
			jPanel10.setLayout(new GroupLayout());
			jPanel10.add(getJLabel16(), new Constraints(new Leading(18, 75, 10, 10), new Leading(15, 20, 10, 10)));
			jPanel10.add(getJScrollPane2(), new Constraints(new Leading(17, 313, 10, 10), new Leading(38, 174, 10, 10)));
		}
		return jPanel10;
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setBorder(new LineBorder(Color.black, 1, false));
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJLabel17(), new Constraints(new Leading(12, 75, 12, 12), new Leading(12, 20, 12, 12)));
			jPanel9.add(getJScrollPane0(), new Constraints(new Leading(42, 426, 10, 10), new Bilateral(10, 12, 27, 402)));
		}
		return jPanel9;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setBorder(new LineBorder(Color.black, 1, false));
			jPanel6.setLayout(new GroupLayout());
			jPanel6.add(getJButton2(), new Constraints(new Leading(14, 87, 10, 10), new Leading(10, 31, 10, 10)));
			jPanel6.add(getJButton1(), new Constraints(new Leading(115, 87, 10, 10), new Leading(10, 31, 12, 12)));
			jPanel6.add(getJButton3(), new Constraints(new Leading(220, 87, 12, 12), new Leading(10, 31, 12, 12)));
			jPanel6.add(getJButton5(), new Constraints(new Leading(418, 87, 12, 12), new Leading(10, 31, 12, 12)));
			jPanel6.add(getJButton4(), new Constraints(new Leading(315, 87, 10, 10), new Leading(10, 31, 12, 12)));
		}
		return jPanel6;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setBorder(new LineBorder(Color.black, 1, false));
			jPanel5.setLayout(new GroupLayout());
			jPanel5.add(getJLabel10(), new Constraints(new Leading(9, 75, 10, 10), new Leading(8, 20, 10, 10)));
			jPanel5.add(getJLabel12(), new Constraints(new Leading(260, 75, 10, 10), new Leading(8, 20, 12, 12)));
			jPanel5.add(getJLabel13(), new Constraints(new Leading(418, 75, 10, 10), new Leading(8, 20, 12, 12)));
			jPanel5.add(getJTextField9(), new Constraints(new Leading(250, 150, 24, 83), new Leading(36, 29, 12, 12)));
			jPanel5.add(getJLabel11(), new Constraints(new Leading(149, 75, 10, 10), new Leading(8, 20, 12, 12)));
			jPanel5.add(getJTextField8(), new Constraints(new Leading(9, 123, 10, 10), new Leading(36, 29, 10, 10)));
			jPanel5.add(getJComboBox5(), new Constraints(new Leading(146, 75, 10, 10), new Leading(36, 29, 12, 12)));
			jPanel5.add(getJComboBox6(), new Constraints(new Leading(418, 75, 12, 12), new Leading(36, 29, 12, 12)));
			jPanel5.add(getJTextField6(), new Constraints(new Leading(511, 150, 18, 162), new Leading(36, 29, 12, 12)));
			jPanel5.add(getJLabel14(), new Constraints(new Leading(511, 75, 12, 12), new Leading(8, 20, 12, 12)));
			jPanel5.add(getJLabel15(), new Constraints(new Trailing(75, 75, 576, 598), new Leading(8, 20, 12, 12)));
			jPanel5.add(getJTextField12(), new Constraints(new Leading(669, 150, 10, 10), new Leading(36, 29, 12, 12)));
		}
		return jPanel5;
	}

}
