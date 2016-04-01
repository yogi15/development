 


import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
public class CurrencyDefaultWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JTabbedPane jTabbedPane0;
	private JTable currencyTable;
	private JScrollPane jScrollPane0;
	private JPanel jPanel1;
	private JTextField currency ;
	private JComboBox<String> countryjComboBox0;
	private JTextField isoCodejTextField1;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JLabel jLabel3;
	private JTextField spotDaysjTextField2;
	private JLabel jLabel4;
	private JComboBox<String> roundingMethodjComboBox1;
	private JLabel jLabel5;
	private JTextField roundingjTextField3;
	private JLabel jLabel6;
	private JTextField decimaljTextField4;
	private JLabel jLabel7;
	private JTextField rateDecimaljTextField5;
	private JLabel jLabel8;
	private JComboBox<String> rateIndexCodejComboBox2;
	private JLabel jLabel9;
	private JComboBox<String> dayCountjComboBox3;
	private JLabel jLabel10;
	private JComboBox<String> groupListjComboBox4;
	private JCheckBox preicousjCheckBox0;
	private JCheckBox nonDeliverablejCheckBox1;
	private JLabel jLabel11;
	private JComboBox<String> holidayjComboBox5;
	private JPanel jPanel3;
	private JButton savejButton0;
	private JButton deletejButton1;
	private JButton newjButton2;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel7;
	private JPanel jPanel6;
	private JLabel jLabel12;
	private JLabel jLabel14;
	private JLabel jLabel15;
	private JComboBox<String> jComboBox7;
	private JComboBox<String> jComboBox6;
	private JLabel jLabel13;
	private JLabel jLabel16;
	private JTextField jTextField8;
	private JTextField jTextField6;
	private JTextField jTextField7;
	private JCheckBox jCheckBox2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JPanel jPanel9;
	private JCheckBox jCheckBox1;
	private JCheckBox jCheckBox0;
	private JCheckBox ccilCheck;
	private JCheckBox clsCheck;
	private JButton jButton0;
	private JLabel jLabel17;
	private JComboBox<String> BDC;
	private JComboBox<String> periodAjuValue;
	private JLabel jLabel18;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public CurrencyDefaultWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(16, 971, 10, 10), new Leading(7, 493, 10, 10)));
		setSize(997, 510);
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("Period AJD Value");
		}
		return jLabel18;
	}

	private JComboBox<String> getperiodAjuValue() {
		if (periodAjuValue == null) {
			periodAjuValue = new JComboBox<String>();
			periodAjuValue.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return periodAjuValue;
	}

	private JComboBox<String> getBDC() {
		if (BDC == null) {
			BDC = new JComboBox<String>();
			BDC.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return BDC;
	}

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("BDC");
		}
		return jLabel17;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}

	private JCheckBox getJCheckBox4() {
		if (clsCheck == null) {
			clsCheck = new JCheckBox();
			clsCheck.setText("CLS");
		}
		return clsCheck;
	}

	private JCheckBox getCcilCheckbox() {
		if (ccilCheck == null) {
			ccilCheck = new JCheckBox();
			ccilCheck.setText("CCIL");
		}
		return ccilCheck;
	}

	private JCheckBox getPreciousMetalCheckBox() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setText("Presious Metal");
		}
		return jCheckBox0;
	}

	private JCheckBox getNonDeliverableCheckBox() {
		if (jCheckBox1 == null) {
			jCheckBox1 = new JCheckBox();
			jCheckBox1.setText("Non Deliverable");
		}
		return jCheckBox1;
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJButton3(), new Constraints(new Leading(35, 10, 10), new Leading(9, 10, 10)));
			jPanel9.add(getJButton4(), new Constraints(new Leading(110, 74, 10, 10), new Leading(9, 12, 12)));
			jPanel9.add(getJButton5(), new Constraints(new Leading(209, 84, 10, 10), new Leading(9, 12, 12)));
			jPanel9.add(getJButton6(), new Constraints(new Leading(311, 12, 12), new Leading(9, 12, 12)));
		}
		return jPanel9;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("New");
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("SaveAs..");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("Delete");
		}
		return jButton4;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Add");
		}
		return jButton3;
	}

	private JCheckBox getJCheckBox2() {
		if (jCheckBox2 == null) {
			jCheckBox2 = new JCheckBox();
			jCheckBox2.setText("Pair off Position");
		}
		return jCheckBox2;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
		}
		return jTextField7;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		}
		return jTextField6;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
		}
		return jTextField8;
	}

	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("Rounding");
		}
		return jLabel16;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("Quoting Currency");
		}
		return jLabel13;
	}

	private JComboBox<String> getJComboBox6() {
		if (jComboBox6 == null) {
			jComboBox6 = new JComboBox<String>();
			jComboBox6.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox6;
	}

	private JComboBox<String> getJComboBox7() {
		if (jComboBox7 == null) {
			jComboBox7 = new JComboBox<String>();
			jComboBox7.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox7;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Basis Point Factor");
		}
		return jLabel15;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Quoting Factor");
		}
		return jLabel14;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Primary Currency");
		}
		return jLabel12;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setVisible(false);
			jPanel6.setLayout(new GroupLayout());
			jPanel6.add(getJPanel7(), new Constraints(new Leading(622, 100, 10, 10), new Leading(-30, 100, 10, 10)));
		}
		return jPanel6;
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(new GroupLayout());
		}
		return jPanel7;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GroupLayout());
			jPanel5.add(getJPanel4(), new Constraints(new Leading(7, 770, 10, 10), new Leading(7, 431, 10, 10)));
		}
		return jPanel5;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJLabel12(), new Constraints(new Leading(21, 10, 10), new Leading(16, 10, 10)));
			jPanel4.add(getJLabel14(), new Constraints(new Leading(22, 12, 12), new Leading(50, 10, 10)));
			jPanel4.add(getJLabel15(), new Constraints(new Leading(228, 106, 10, 10), new Leading(50, 10, 10)));
			jPanel4.add(getJComboBox7(), new Constraints(new Leading(357, 66, 10, 10), new Leading(9, 27, 12, 12)));
			jPanel4.add(getJComboBox6(), new Constraints(new Leading(134, 70, 10, 10), new Leading(9, 27, 12, 12)));
			jPanel4.add(getJLabel13(), new Constraints(new Leading(229, 125, 12, 12), new Leading(16, 10, 10)));
			jPanel4.add(getJLabel16(), new Constraints(new Leading(436, 55, 12, 12), new Leading(50, 10, 10)));
			jPanel4.add(getJTextField8(), new Constraints(new Leading(497, 67, 10, 10), new Leading(44, 26, 12, 12)));
			jPanel4.add(getJTextField6(), new Constraints(new Leading(133, 67, 12, 12), new Leading(44, 26, 12, 12)));
			jPanel4.add(getJTextField7(), new Constraints(new Leading(358, 67, 12, 12), new Leading(44, 26, 12, 12)));
			 
		}
		return jPanel4;
	}

	private JButton getnewjButton2() {
		if (newjButton2 == null) {
			newjButton2 = new JButton();
			newjButton2.setText("New");
		}
		return newjButton2;
	}

	private JButton getdeletejButton1() {
		if (deletejButton1 == null) {
			deletejButton1 = new JButton();
			deletejButton1.setText("Delete");
		}
		return deletejButton1;
	}

	private JButton getsavejButton0() {
		if (savejButton0 == null) {
			savejButton0 = new JButton();
			savejButton0.setText("Save");
		}
		return savejButton0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getsavejButton0(), new Constraints(new Leading(42, 10, 10), new Trailing(12, 12, 12)));
			jPanel3.add(getdeletejButton1(), new Constraints(new Leading(115, 12, 12), new Leading(12, 12, 12)));
			jPanel3.add(getnewjButton2(), new Constraints(new Leading(202, 12, 12), new Leading(12, 12, 12)));
		}
		return jPanel3;
	}

	private JComboBox<String> getholidayjComboBox5() {
		if (holidayjComboBox5 == null) {
			holidayjComboBox5 = new JComboBox<String>();
			holidayjComboBox5.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return holidayjComboBox5;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Holiday");
		}
		return jLabel11;
	}

	private JCheckBox getnonDeliverablejCheckBox1() {
		if (nonDeliverablejCheckBox1 == null) {
			nonDeliverablejCheckBox1 = new JCheckBox();
			nonDeliverablejCheckBox1.setSelected(true);
			nonDeliverablejCheckBox1.setText("Non Deliverable");
		}
		return nonDeliverablejCheckBox1;
	}

	private JCheckBox getpreicousjCheckBox0() {
		if (preicousjCheckBox0 == null) {
			preicousjCheckBox0 = new JCheckBox();
			preicousjCheckBox0.setSelected(true);
			preicousjCheckBox0.setText("Presious Metal");
		}
		return preicousjCheckBox0;
	}

	private JComboBox<String> getgroupListjComboBox4() {
		if (groupListjComboBox4 == null) {
			groupListjComboBox4 = new JComboBox<String>();
			groupListjComboBox4.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return groupListjComboBox4;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("DayCount");
		}
		return jLabel10;
	}

	private JComboBox<String> getdayCountjComboBox3() {
		if (dayCountjComboBox3 == null) {
			dayCountjComboBox3 = new JComboBox<String>();
			dayCountjComboBox3.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return dayCountjComboBox3;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Group List");
		}
		return jLabel9;
	}

	private JComboBox<String> getrateIndexCodejComboBox2() {
		if (rateIndexCodejComboBox2 == null) {
			rateIndexCodejComboBox2 = new JComboBox<String>();
			rateIndexCodejComboBox2.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return rateIndexCodejComboBox2;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Rate Index Code");
		}
		return jLabel8;
	}

	private JTextField getrateDecimalJTextField5() {
		if (rateDecimaljTextField5 == null) {
			rateDecimaljTextField5 = new JTextField();
		}
		return rateDecimaljTextField5;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Rate Decimal");
		}
		return jLabel7;
	}

	private JTextField getdecimalJTextField4() {
		if (decimaljTextField4 == null) {
			decimaljTextField4 = new JTextField();
		}
		return decimaljTextField4;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Decimal");
		}
		return jLabel6;
	}

	private JTextField getroundingJTextField3() {
		if (roundingjTextField3 == null) {
			roundingjTextField3 = new JTextField();
		}
		return roundingjTextField3;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Rounding");
		}
		return jLabel5;
	}

	private JComboBox<String> getroundingMethodjComboBox1() {
		if (roundingMethodjComboBox1 == null) {
			roundingMethodjComboBox1 = new JComboBox<String>();
			roundingMethodjComboBox1.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return roundingMethodjComboBox1;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Rounding Method");
		}
		return jLabel4;
	}

	private JTextField getspotDaysJTextField2() {
		if (spotDaysjTextField2 == null) {
			spotDaysjTextField2 = new JTextField();
		}
		return spotDaysjTextField2;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Spot Days");
		}
		return jLabel3;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jTabbedPane0.addTab("CurrencyDefault", getJPanel2());
			jTabbedPane0.addTab("CurrencyPair", getJPanel4());
			jTabbedPane0.addTab("jPanel5", getJPanel5());
			jTabbedPane0.addTab("jPanel6", getJPanel6());
		}
		return jTabbedPane0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJPanel0(), new Constraints(new Bilateral(12, 12, 785), new Leading(12, 430, 12, 12)));
		}
		return jPanel2;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel1(), new Constraints(new Leading(18, 10, 10), new Leading(42, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(18, 12, 12), new Leading(94, 10, 10)));
			jPanel0.add(getJLabel6(), new Constraints(new Leading(18, 57, 12, 12), new Leading(145, 10, 10)));
			jPanel0.add(getdecimalJTextField4(), new Constraints(new Leading(87, 64, 12, 12), new Leading(139, 12, 12)));
			jPanel0.add(getnonDeliverablejCheckBox1(), new Constraints(new Leading(15, 133, 10, 10), new Leading(297, 12, 12)));
			jPanel0.add(getCcilCheckbox(), new Constraints(new Leading(183, 117, 10, 10), new Leading(242, 30, 12, 12)));
			jPanel0.add(getJCheckBox4(), new Constraints(new Leading(180, 74, 10, 10), new Leading(287, 30, 10, 10)));
			jPanel0.add(getcountryJComboBox0(), new Constraints(new Leading(85, 101, 10, 10), new Leading(31, 22, 10, 10)));
			jPanel0.add(getspotDaysJTextField2(), new Constraints(new Leading(87, 64, 12, 12), new Leading(85, 12, 12)));
			jPanel0.add(getJLabel11(), new Constraints(new Leading(18, 12, 12), new Leading(188, 10, 10)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(205, 10, 10), new Leading(88, 12, 12)));
			jPanel0.add(getJLabel7(), new Constraints(new Leading(205, 72, 12, 12), new Leading(145, 12, 12)));
			jPanel0.add(getJLabel17(), new Constraints(new Leading(206, 46, 12, 12), new Leading(188, 12, 12)));
			jPanel0.add(getholidayjComboBox5(), new Constraints(new Leading(87, 99, 12, 12), new Leading(184, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(204, 12, 12), new Leading(36, 12, 12)));
			jPanel0.add(getJPanel3(), new Constraints(new Leading(12, 761, 12, 12), new Leading(365, 50, 10, 10)));
			jPanel0.add(getroundingMethodjComboBox1(), new Constraints(new Leading(310, 97, 10, 10), new Leading(79, 22, 12, 12)));
			jPanel0.add(getrateDecimalJTextField5(), new Constraints(new Leading(313, 64, 10, 10), new Leading(134, 12, 12)));
			jPanel0.add(getBDC(), new Constraints(new Leading(310, 101, 12, 12), new Leading(181, 22, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(310, 70, 12, 12), new Leading(31, 18, 12, 12)));
			jPanel0.add(getJLabel18(), new Constraints(new Leading(432, 10, 10), new Leading(188, 20, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(435, 83, 75, 138), new Leading(139, 18, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(435, 64, 12, 12), new Leading(36, 18, 12, 12)));
			jPanel0.add(getrateIndexCodejComboBox2(), new Constraints(new Leading(535, 121, 75, 138), new Leading(126, 24, 12, 12)));
			jPanel0.add(getperiodAjuValue(), new Constraints(new Leading(535, 120, 12, 12), new Leading(177, 28, 12, 12)));
			jPanel0.add(getroundingJTextField3(), new Constraints(new Leading(535, 70, 12, 12), new Leading(79, 23, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(435, 12, 12), new Leading(91, 12, 12)));
			jPanel0.add(getisoCodeJTextField1(), new Constraints(new Leading(533, 66, 10, 10), new Leading(32, 21, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(671, 12, 12), new Leading(35, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(674, 75, 138), new Leading(136, 12, 12)));
			jPanel0.add(getgroupListjComboBox4(), new Constraints(new Leading(748, 114, 10, 10), new Leading(123, 24, 12, 12)));
			jPanel0.add(getdayCountjComboBox3(), new Constraints(new Leading(748, 104, 12, 12), new Leading(25, 24, 12, 12)));
			jPanel0.add(getpreicousjCheckBox0(), new Constraints(new Leading(18, 117, 12, 12), new Leading(245, 30, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(748, 88, 12, 12), new Leading(74, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("ISO CODE");
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Currency");
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Country");
		}
		return jLabel1;
	}

	private JTextField getisoCodeJTextField1() {
		if (isoCodejTextField1 == null) {
			isoCodejTextField1 = new JTextField();
		}
		return isoCodejTextField1;
	}

	private JComboBox<String> getcountryJComboBox0() {
		if (countryjComboBox0 == null) {
			countryjComboBox0 = new JComboBox<String>();
			countryjComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return countryjComboBox0;
	}

	private JTextField getJTextField0() {
		if (currency == null) {
			currency = new JTextField();
		}
		return currency;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane0(), new Constraints(new Leading(4, 47, 10, 10), new Leading(5, 476, 12, 12)));
			jPanel1.add(getJTabbedPane0(), new Constraints(new Leading(60, 924, 10, 10), new Leading(5, 476, 10, 10)));
		}
		return jPanel1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jScrollPane0.setViewportView(getCurrencyTable());
		}
		return jScrollPane0;
	}

	private JTable getCurrencyTable() {
		if (currencyTable == null) {
			currencyTable = new JTable();
			currencyTable.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return currencyTable;
	}

}
