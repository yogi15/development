import java.awt.Component;

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

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FXOptionTradePanelD extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel AttributePanel;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel6;
	private JLabel jLabel5;
	private JButton New;
	private JButton Save;
	private JButton SaveAsNew;
	private JButton Delete;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JRadioButton Rollback;
	private JComboBox Book;
	private JComboBox cp;
	private JComboBox Trader;
	private JTextField TradeID;
	private JComboBox Action;
	private JTextField status;
	private JTable jTable0;
	private JScrollPane AttributeTable;
	private JLabel jLabel13;
	private JButton jButton4;
	private JButton jButton5;
	private JComboBox optionType;
	private JLabel jLabel0;
	private JLabel jLabel4;
	private JComboBox exeriseType;
	private JLabel jLabel6;
	private JComboBox settlementType;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel15;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JTextField exipryDate;
	private JLabel jLabel7;
	private JTextField expiryTime;
	private JComboBox currencyPair;
	private JComboBox NotionalCurrency;
	private JTextField callPut;
	private JLabel jLabel18;
	private JTextField buysell;
	private JTextField deliveryDate;
	private JLabel jLabel12;
	private JTextField curr2putcall;
	private JLabel jLabel14;
	private JTextField settlementCCY;
	private JLabel jLabel19;
	private JTextField Notional;
	private JTextField amt1;
	private JTextField amt2;
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JPanel jPanel7;
	private JTextField fxRate;
	private JLabel jLabel22;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public FXOptionTradePanelD() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(0, 716, 12, 12), new Leading(3, 86, 12, 12)));
		add(getJPanel4(), new Constraints(new Leading(724, 259, 10, 10), new Leading(8, 509, 10, 10)));
		add(getJPanel5(), new Constraints(new Leading(5, 710, 12, 12), new Leading(471, 46, 12, 12)));
		add(getJPanel3(), new Constraints(new Leading(0, 12, 12), new Leading(338, 121, 12, 12)));
		add(getJPanel1(), new Constraints(new Leading(3, 716, 12, 12), new Leading(90, 122, 12, 12)));
		add(getJPanel7(), new Constraints(new Leading(5, 712, 12, 12), new Leading(338, 121, 12, 12)));
		add(getJPanel2(), new Constraints(new Leading(3, 716, 12, 12), new Leading(215, 131, 10, 10)));
		setSize(990, 527);
	}

	private JLabel getJLabel22() {
		if (jLabel22 == null) {
			jLabel22 = new JLabel();
			jLabel22.setText("FX Rate");
		}
		return jLabel22;
	}

	private JTextField getFxRate() {
		if (fxRate == null) {
			fxRate = new JTextField();
		}
		return fxRate;
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel7.setLayout(new GroupLayout());
		}
		return jPanel7;
	}

	private JLabel getJLabel21() {
		if (jLabel21 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("Amt2");
		}
		return jLabel21;
	}

	private JLabel getJLabel20() {
		if (jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("Amt1");
		}
		return jLabel20;
	}

	private JTextField getAmt2() {
		if (amt2 == null) {
			amt2 = new JTextField();
		}
		return amt2;
	}

	private JTextField getAmt1() {
		if (amt1 == null) {
			amt1 = new JTextField();
		}
		return amt1;
	}

	private JTextField getNotional() {
		if (Notional == null) {
			Notional = new JTextField();
		}
		return Notional;
	}

	private JLabel getJLabel19() {
		if (jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("Strike Price");
		}
		return jLabel19;
	}

	private JTextField getSettlementCCY() {
		if (settlementCCY == null) {
			settlementCCY = new JTextField();
		}
		return settlementCCY;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Settlement CCY");
		}
		return jLabel14;
	}

	private JTextField getCurr2putcall() {
		if (curr2putcall == null) {
			curr2putcall = new JTextField();
		}
		return curr2putcall;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("CCY2 Put/Call");
		}
		return jLabel12;
	}

	private JTextField getDeliveryDate() {
		if (deliveryDate == null) {
			deliveryDate = new JTextField();
		}
		return deliveryDate;
	}

	private JTextField getBuySell() {
		if (buysell == null) {
			buysell = new JTextField();
		}
		return buysell;
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("CALL/PUT");
		}
		return jLabel18;
	}

	private JTextField getCallPut() {
		if (callPut == null) {
			callPut = new JTextField();
		}
		return callPut;
	}

	private JComboBox getNotionalCurrency() {
		if (NotionalCurrency == null) {
			NotionalCurrency = new JComboBox();
			NotionalCurrency.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return NotionalCurrency;
	}
	private JComboBox getCurrencyPair() {
		if (currencyPair == null) {
			currencyPair = new JComboBox();
			currencyPair.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return currencyPair;
	}
	private JTextField getExpiryTime() {
		if (expiryTime == null) {
			expiryTime = new JTextField();
		}
		return expiryTime;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Expiry Date");
		}
		return jLabel7;
	}

	private JTextField getExipryDate() {
		if (exipryDate == null) {
			exipryDate = new JTextField();
		}
		return exipryDate;
	}

	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("Expiry Time");
		}
		return jLabel16;
	}

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("BUY/SELL");
		}
		return jLabel17;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Notional Ccy");
		}
		return jLabel15;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("CurrPair/Underlying");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("DeliveryDate");
		}
		return jLabel10;
	}

	private JComboBox getSettlementType() {
		if (settlementType == null) {
			settlementType = new JComboBox();
			settlementType.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return settlementType;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Settlement Type");
		}
		return jLabel6;
	}

	private JComboBox getExeriseType() {
		if (exeriseType == null) {
			exeriseType = new JComboBox();
			exeriseType.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return exeriseType;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Exercise Type");
		}
		return jLabel4;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("StrategyName");
		}
		return jLabel0;
	}

	private JComboBox getOptionType() {
		if (optionType == null) {
			optionType = new JComboBox();
			optionType.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return optionType;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
		}
		return jPanel3;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("RollBack");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("RollOver");
		}
		return jButton4;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJLabel17(), new Constraints(new Leading(4, 10, 10), new Leading(14, 10, 10)));
			jPanel2.add(getJLabel18(), new Constraints(new Leading(147, 10, 10), new Leading(14, 10, 10)));
			jPanel2.add(getCallPut(), new Constraints(new Leading(235, 55, 10, 10), new Leading(12, 12, 12)));
			jPanel2.add(getJLabel12(), new Constraints(new Leading(317, 10, 10), new Leading(12, 12, 12)));
			jPanel2.add(getCurr2putcall(), new Constraints(new Leading(408, 55, 12, 12), new Leading(12, 12, 12)));
			jPanel2.add(getJLabel19(), new Constraints(new Leading(5, 12, 12), new Leading(54, 12, 12)));
			jPanel2.add(getBuySell(), new Constraints(new Leading(76, 55, 12, 12), new Leading(12, 12, 12)));
			jPanel2.add(getFxRate(), new Constraints(new Leading(76, 115, 10, 10), new Leading(82, 12, 12)));
			jPanel2.add(getJLabel22(), new Constraints(new Leading(7, 10, 10), new Leading(85, 12, 12)));
			jPanel2.add(getNotional(), new Constraints(new Leading(74, 122, 10, 10), new Leading(46, 25, 12, 12)));
			jPanel2.add(getJLabel20(), new Constraints(new Leading(208, 12, 12), new Leading(52, 12, 12)));
			jPanel2.add(getAmt1(), new Constraints(new Leading(247, 152, 10, 10), new Leading(48, 25, 12, 12)));
			jPanel2.add(getJLabel21(), new Constraints(new Leading(420, 12, 12), new Leading(54, 12, 12)));
			jPanel2.add(getAmt2(), new Constraints(new Leading(466, 152, 10, 10), new Leading(51, 25, 12, 12)));
			jPanel2.add(getJLabel14(), new Constraints(new Leading(488, 10, 10), new Leading(15, 19, 12, 12)));
			jPanel2.add(getSettlementCCY(), new Constraints(new Leading(593, 77, 10, 10), new Leading(14, 12, 12)));
		}
		return jPanel2;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("INR Equi");
		}
		return jLabel13;
	}

	private JScrollPane getJScrollPane0() {
		if (AttributeTable == null) {
			AttributeTable = new JScrollPane();
			AttributeTable.setViewportView(getJTable0());
		}
		return AttributeTable;
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

	private JTextField getJTextField6() {
		if (status == null) {
			status = new JTextField();
		}
		return status;
	}

	private JComboBox getJComboBox4() {
		if (Action == null) {
			Action = new JComboBox();
			Action.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return Action;
	}

	private JTextField getJTextField0() {
		if (TradeID == null) {
			TradeID = new JTextField();
		}
		return TradeID;
	}

	private JComboBox getJComboBox3() {
		if (Trader == null) {
			Trader = new JComboBox();
			Trader.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return Trader;
	}

	private JComboBox getJComboBox2() {
		if (cp == null) {
			cp = new JComboBox();
			cp.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return cp;
	}

	private JComboBox getJComboBox1() {
		if (Book == null) {
			Book = new JComboBox();
			Book.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return Book;
	}

	private JRadioButton getJRadioButton3() {
		if (Rollback == null) {
			Rollback = new JRadioButton();
			Rollback.setSelected(true);
			Rollback.setText("RollBack");
		}
		return Rollback;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Action");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Status");
		}
		return jLabel8;
	}

	private JButton getJButton3() {
		if (Delete == null) {
			Delete = new JButton();
			Delete.setText("Delete");
		}
		return Delete;
	}

	private JButton getJButton2() {
		if (SaveAsNew == null) {
			SaveAsNew = new JButton();
			SaveAsNew.setText("SaveAsNew");
		}
		return SaveAsNew;
	}

	private JButton getJButton1() {
		if (Save == null) {
			Save = new JButton();
			Save.setText("Save");
		}
		return Save;
	}

	private JButton getJButton0() {
		if (New == null) {
			New = new JButton();
			New.setText("NEW");
		}
		return New;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJLabel7(), new Constraints(new Leading(6, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getJLabel11(), new Constraints(new Leading(6, 12, 12), new Leading(83, 12, 12)));
			jPanel1.add(getOptionType(), new Constraints(new Leading(99, 119, 10, 10), new Leading(5, 25, 12, 12)));
			jPanel1.add(getJLabel4(), new Constraints(new Leading(224, 10, 10), new Leading(9, 12, 12)));
			jPanel1.add(getExeriseType(), new Constraints(new Leading(309, 151, 10, 10), new Leading(5, 25, 12, 12)));
			jPanel1.add(getJLabel6(), new Constraints(new Leading(466, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getSettlementType(), new Constraints(new Leading(568, 132, 10, 10), new Leading(5, 25, 12, 12)));
			jPanel1.add(getJLabel0(), new Constraints(new Leading(4, 92, 10, 10), new Leading(9, 12, 12)));
			jPanel1.add(getExpiryTime(), new Constraints(new Leading(571, 126, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getExipryDate(), new Constraints(new Leading(100, 117, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getDeliveryDate(), new Constraints(new Leading(312, 140, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getNotionalCurrency(), new Constraints(new Leading(312, 68, 12, 12), new Leading(79, 26, 12, 12)));
			jPanel1.add(getJLabel15(), new Constraints(new Leading(224, 12, 12), new Leading(80, 23, 12, 12)));
			jPanel1.add(getJLabel10(), new Constraints(new Leading(224, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getJLabel16(), new Constraints(new Leading(470, 12, 12), new Leading(43, 12, 12)));
			jPanel1.add(getCurrencyPair(), new Constraints(new Leading(126, 89, 10, 10), new Leading(77, 26, 12, 12)));
		}
		return jPanel1;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Trade ID");
		}
		return jLabel5;
	}

	
	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Trader");
		}
		return jLabel3;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJComboBox3(), new Constraints(new Leading(532, 177, 10, 10), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 10, 10), new Leading(49, 12, 12)));
			jPanel0.add(getJComboBox1(), new Constraints(new Leading(67, 138, 10, 10), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(7, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(483, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(535, 170, 12, 12), new Leading(5, 26, 10, 51)));
			jPanel0.add(getJComboBox2(), new Constraints(new Leading(304, 175, 12, 12), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(211, 12, 12), new Leading(49, 12, 12)));
			jPanel0.add(getJComboBox4(), new Constraints(new Leading(304, 174, 12, 12), new Leading(5, 26, 10, 51)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(485, 41, 12, 12), new Leading(49, 25, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(67, 138, 12, 12), new Leading(7, 26, 40, 40)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(214, 10, 10), new Leading(10, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("CounterParty");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Book");
		}
		return jLabel1;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel5.setLayout(new GroupLayout());
			jPanel5.add(getJButton0(), new Constraints(new Leading(19, 10, 10), new Leading(8, 10, 10)));
			jPanel5.add(getJButton1(), new Constraints(new Leading(98, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton2(), new Constraints(new Leading(179, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton3(), new Constraints(new Leading(301, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton4(), new Constraints(new Leading(388, 12, 12), new Leading(8, 12, 12)));
			jPanel5.add(getJButton5(), new Constraints(new Leading(489, 10, 10), new Leading(8, 12, 12)));
		}
		return jPanel5;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJScrollPane0(), new Constraints(new Leading(3, 245, 10, 10), new Leading(4, 492, 10, 10)));
		}
		return jPanel4;
	}

	private Component getAttributePanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
