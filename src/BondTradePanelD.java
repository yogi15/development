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

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class BondTradePanelD extends JPanel {

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
	private JRadioButton Rollover;
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
	private JTable CashFlowTable;
	private JScrollPane jScrollPane1;
	private JTable jTable0;
	private JScrollPane AttributeTable;
	private JLabel jLabel13;
	private JButton jButton4;
	private JButton jButton5;
	private JLabel tradeDatejLabel7;
	private JTextField tradeDate;
	private JTextField settleDate;
	private JLabel settleDatejLabel0;
	private JLabel pricejLabel4;
	private JTextField priceText;
	private JLabel BUYSELLJLabel6;
	private JTextField buySelltext;
	private JLabel tradeCurrJLabel10jLabel10;
	private JComboBox currency;
	private JLabel amountjLabel11;
	private JTextField amount;
	private JTextField underlying;
	private JLabel underlyingjLabel;
	private JButton searchProduct;
	private JLabel PrevCPNjLabel14;
	private JTextField previousCoupon;
	private JLabel NextCPNjLabel15;
	private JTextField nextCoupon;
	private JLabel MaturityDatejLabel16;
	private JTextField maturityDate;
	private JLabel AccruedDaysjLabel17;
	private JTextField accruedDays;
	private JLabel AccuredIntjLabel18;
	private JTextField accuredInt;
	private JLabel SettlementAMTjLabel19;
	private JTextField settlementAMT;
	private JLabel QTYjLabel20;
	private JTextField quantity;
	private JLabel DayCountjLabel21;
	private JTextField daycount;
	private JLabel BDCjLabel22;
	private JTextField bdcText;
	private JLabel RatejLabel23;
	private JTextField rate;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public BondTradePanelD() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(0, 716, 12, 12), new Leading(3, 86, 12, 12)));
		add(getJPanel1(), new Constraints(new Leading(3, 710, 12, 12), new Leading(92, 114, 12, 12)));
		add(getJPanel5(), new Constraints(new Leading(3, 710, 12, 12), new Leading(483, 46, 12, 12)));
		add(getJPanel4(), new Constraints(new Leading(724, 259, 10, 10), new Leading(8, 521, 12, 12)));
		add(getJPanel3(), new Constraints(new Leading(0, 720, 12, 12), new Leading(326, 12, 12)));
		add(getJPanel2(), new Constraints(new Leading(3, 714, 12, 12), new Leading(210, 110, 12, 12)));
		setSize(1013, 541);
	}

	private JTextField getRate() {
		if (rate == null) {
			rate = new JTextField();
		}
		return rate;
	}

	private JLabel getRatejLabel23() {
		if (RatejLabel23 == null) {
			RatejLabel23 = new JLabel();
			RatejLabel23.setText("Rate");
		}
		return RatejLabel23;
	}

	private JTextField getBdcText() {
		if (bdcText == null) {
			bdcText = new JTextField();
		}
		return bdcText;
	}

	private JLabel getBDCjLabel22() {
		if (BDCjLabel22 == null) {
			BDCjLabel22 = new JLabel();
			BDCjLabel22.setText("BDC");
		}
		return BDCjLabel22;
	}

	private JTextField getDaycount() {
		if (daycount == null) {
			daycount = new JTextField();
		}
		return daycount;
	}

	private JLabel getDayCountjLabel21() {
		if (DayCountjLabel21 == null) {
			DayCountjLabel21 = new JLabel();
			DayCountjLabel21.setText("DayCount");
		}
		return DayCountjLabel21;
	}

	private JTextField getQuantity() {
		if (quantity == null) {
			quantity = new JTextField();
		}
		return quantity;
	}

	private JLabel getQTYJLabel20() {
		if (QTYjLabel20 == null) {
			QTYjLabel20 = new JLabel();
			QTYjLabel20.setText("QTY");
		}
		return QTYjLabel20;
	}

	private JTextField getSettlementAMT() {
		if (settlementAMT == null) {
			settlementAMT = new JTextField();
		}
		return settlementAMT;
	}

	private JLabel getSettlementAMTJLabel19() {
		if (SettlementAMTjLabel19 == null) {
			SettlementAMTjLabel19 = new JLabel();
			SettlementAMTjLabel19.setText("SettlementAMT");
		}
		return SettlementAMTjLabel19;
	}

	private JTextField getAccuredInt() {
		if (accuredInt == null) {
			accuredInt = new JTextField();
		}
		return accuredInt;
	}

	private JLabel getAccuredIntjLabel18() {
		if (AccuredIntjLabel18 == null) {
			AccuredIntjLabel18 = new JLabel();
			AccuredIntjLabel18.setText("Accured Int");
		}
		return AccuredIntjLabel18;
	}

	private JTextField getAccruedDays() {
		if (accruedDays == null) {
			accruedDays = new JTextField();
		}
		return accruedDays;
	}

	private JLabel AccruedDaysjLabel17() {
		if (AccruedDaysjLabel17 == null) {
			AccruedDaysjLabel17 = new JLabel();
			AccruedDaysjLabel17.setText("AccruedDays");
		}
		return AccruedDaysjLabel17;
	}

	private JTextField getMaturityDate() {
		if (maturityDate == null) {
			maturityDate = new JTextField();
		}
		return maturityDate;
	}

	private JLabel getMaturityDatejLabel16() {
		if (MaturityDatejLabel16 == null) {
			MaturityDatejLabel16 = new JLabel();
			MaturityDatejLabel16.setText("MaturityDate");
		}
		return MaturityDatejLabel16;
	}

	private JTextField getNextCoupon() {
		if (nextCoupon == null) {
			nextCoupon = new JTextField();
		}
		return nextCoupon;
	}

	private JLabel getNextCPNjLabel15JLabel15() {
		if (NextCPNjLabel15 == null) {
			NextCPNjLabel15 = new JLabel();
			NextCPNjLabel15.setText("Next CPN");
		}
		return NextCPNjLabel15;
	}

	private JTextField getPreviousCoupon() {
		if (previousCoupon == null) {
			previousCoupon = new JTextField();
		}
		return previousCoupon;
	}

	private JLabel getPrevCPNjLabel14() {
		if (PrevCPNjLabel14 == null) {
			PrevCPNjLabel14 = new JLabel();
			PrevCPNjLabel14.setText("Prev. CPN");
		}
		return PrevCPNjLabel14;
	}

	private JButton getSearchProduct() {
		if (searchProduct == null) {
			searchProduct = new JButton();
			searchProduct.setText("...");
		}
		return searchProduct;
	}

	private JLabel getUnderlyingJLabel12() {
		if (underlyingjLabel == null) {
			underlyingjLabel = new JLabel();
			underlyingjLabel.setText("Underlying");
		}
		return underlyingjLabel;
	}

	private JTextField getUnderlying() {
		if (underlying == null) {
			underlying = new JTextField();
		}
		return underlying;
	}

	private JTextField getAmount() {
		if (amount == null) {
			amount = new JTextField();
			//amount.setText("jTextField3");
		}
		return  amount;
	}

	private JLabel getAmountJLabel11() {
		if (amountjLabel11 == null) {
			amountjLabel11 = new JLabel();
			amountjLabel11.setText("Amount");
		}
		return amountjLabel11;
	}

	private JComboBox getCurrency() {
		if (currency == null) {
			currency = new JComboBox();
			currency.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return currency;
	}

	private JLabel getTradeCurrJLabel10() {
		if (tradeCurrJLabel10jLabel10 == null) {
			tradeCurrJLabel10jLabel10 = new JLabel();
			tradeCurrJLabel10jLabel10.setText("TradeCurr");
		}
		return tradeCurrJLabel10jLabel10;
	}

	private JTextField getBuySellText() {
		if (buySelltext == null) {
			buySelltext = new JTextField();
		}
		return buySelltext;
	}

	private JLabel getBUYSELLJLabel6() {
		if (BUYSELLJLabel6 == null) {
			BUYSELLJLabel6 = new JLabel();
			BUYSELLJLabel6.setText("BUY/SELL");
		}
		return BUYSELLJLabel6;
	}

	private JTextField getPriceText() {
		if (priceText == null) {
			priceText = new JTextField();
		}
		return priceText;
	}

	private JLabel getPriceJLabel4() {
		if (pricejLabel4 == null) {
			pricejLabel4 = new JLabel();
			pricejLabel4.setText("Price");
		}
		return pricejLabel4;
	}

	private JLabel getSettleDateJLabel0() {
		if (settleDatejLabel0 == null) {
			settleDatejLabel0 = new JLabel();
			settleDatejLabel0.setText("SettleDate");
		}
		return settleDatejLabel0;
	}

	private JTextField getSettleDate() {
		if (settleDate == null) {
			settleDate = new JTextField();
		}
		return settleDate;
	}

	private JTextField getTradeDate() {
		if (tradeDate == null) {
			tradeDate = new JTextField();
		}
		return tradeDate;
	}

	private JLabel getTradeDateJLabel7() {
		if (tradeDatejLabel7 == null) {
			tradeDatejLabel7 = new JLabel();
			tradeDatejLabel7.setText("TradeDate");
		}
		return tradeDatejLabel7;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Bilateral(4, 12, 27), new Leading(4, 133, 10, 10)));
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
			jPanel2.add(getPrevCPNjLabel14(), new Constraints(new Leading(5, 10, 10), new Leading(7, 10, 10)));
			jPanel2.add(AccruedDaysjLabel17(), new Constraints(new Leading(2, 12, 12), new Leading(46, 12, 12)));
			jPanel2.add(getAccruedDays(), new Constraints(new Leading(78, 130, 10, 10), new Leading(37, 25, 12, 12)));
			jPanel2.add(getNextCPNjLabel15JLabel15(), new Constraints(new Leading(221, 12, 12), new Leading(7, 12, 12)));
			jPanel2.add(getNextCoupon(), new Constraints(new Leading(288, 130, 10, 10), new Leading(2, 25, 12, 12)));
			jPanel2.add(getMaturityDatejLabel16(), new Constraints(new Leading(423, 10, 10), new Leading(4, 12, 12)));
			jPanel2.add(getAccuredIntjLabel18(), new Constraints(new Leading(220, 12, 12), new Leading(41, 12, 12)));
			jPanel2.add(getAccuredInt(), new Constraints(new Leading(288, 130, 10, 10), new Leading(36, 25, 12, 12)));
			jPanel2.add(getSettlementAMTJLabel19(), new Constraints(new Leading(424, 90, 10, 10), new Leading(41, 12, 12)));
			jPanel2.add(getSettlementAMT(), new Constraints(new Leading(516, 130, 12, 12), new Leading(38, 25, 12, 12)));
			jPanel2.add(getQTYJLabel20(), new Constraints(new Leading(2, 73, 12, 12), new Leading(80, 12, 12)));
			jPanel2.add(getQuantity(), new Constraints(new Leading(38, 130, 10, 10), new Leading(74, 25, 12, 12)));
			jPanel2.add(getDayCountjLabel21(), new Constraints(new Leading(186, 73, 12, 12), new Leading(80, 12, 12)));
			jPanel2.add(getBDCjLabel22(), new Constraints(new Leading(401, 12, 12), new Leading(83, 12, 12)));
			jPanel2.add(getBdcText(), new Constraints(new Leading(434, 130, 10, 10), new Leading(75, 25, 12, 12)));
			jPanel2.add(getRatejLabel23(), new Constraints(new Leading(574, 33, 10, 10), new Leading(83, 12, 12)));
			jPanel2.add(getDaycount(), new Constraints(new Leading(250, 130, 10, 10), new Leading(75, 25, 12, 12)));
			jPanel2.add(getRate(), new Constraints(new Leading(608, 74, 10, 10), new Leading(77, 25, 12, 12)));
			jPanel2.add(getPreviousCoupon(), new Constraints(new Leading(79, 81, 10, 10), new Leading(2, 25, 12, 12)));
			jPanel2.add(getMaturityDate(), new Constraints(new Leading(516, 81, 10, 10), new Leading(2, 25, 12, 12)));
		}
		return jPanel2;
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

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (CashFlowTable == null) {
			CashFlowTable = new JTable();
			CashFlowTable.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return CashFlowTable;
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
			jPanel1.add(getTradeDateJLabel7(), new Constraints(new Leading(4, 10, 10), new Leading(20, 10, 10)));
			jPanel1.add(getSettleDateJLabel0(), new Constraints(new Leading(222, 12, 12), new Leading(20, 10, 10)));
			jPanel1.add(getSettleDate(), new Constraints(new Leading(303, 167, 12, 12), new Leading(9, 25, 12, 12)));
			jPanel1.add(getPriceJLabel4(), new Constraints(new Leading(482, 12, 12), new Leading(14, 12, 12)));
			jPanel1.add(getPriceText(), new Constraints(new Leading(528, 150, 10, 10), new Leading(9, 25, 12, 12)));
			jPanel1.add(getTradeCurrJLabel10(), new Constraints(new Leading(224, 12, 12), new Leading(50, 12, 12)));
			jPanel1.add(getCurrency(), new Constraints(new Leading(303, 90, 12, 12), new Leading(42, 25, 12, 12)));
			jPanel1.add(getTradeDate(), new Constraints(new Leading(70, 145, 12, 12), new Leading(11, 25, 12, 12)));
			jPanel1.add(getAmountJLabel11(), new Constraints(new Leading(469, 10, 10), new Leading(50, 12, 12)));
			jPanel1.add(getBUYSELLJLabel6(), new Constraints(new Leading(7, 12, 12), new Leading(51, 12, 12)));
			jPanel1.add(getUnderlying(), new Constraints(new Leading(70, 323, 10, 10), new Leading(78, 25, 12, 12)));
			jPanel1.add(getUnderlyingJLabel12(), new Constraints(new Leading(7, 12, 12), new Leading(81, 10, 10)));
			jPanel1.add(getSearchProduct(), new Constraints(new Leading(407, 12, 12), new Leading(78, 12, 12)));
			jPanel1.add(getBuySellText(), new Constraints(new Leading(70, 72, 12, 12), new Leading(46, 25, 12, 12)));
			jPanel1.add(getAmount(), new Constraints(new Leading(528, 150, 12, 12), new Leading(42, 25, 10, 10)));
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

	private JRadioButton getJRadioButton0() {
		if (Rollover == null) {
			Rollover = new JRadioButton();
			Rollover.setSelected(true);
			Rollover.setText("RollOver");
		}
		return Rollover;
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
			jPanel0.add(getJLabel9(), new Constraints(new Leading(233, 10, 10), new Leading(10, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(483, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(535, 170, 12, 12), new Leading(5, 26, 10, 51)));
			jPanel0.add(getJComboBox2(), new Constraints(new Leading(304, 175, 12, 12), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(211, 12, 12), new Leading(49, 12, 12)));
			jPanel0.add(getJComboBox4(), new Constraints(new Leading(304, 174, 12, 12), new Leading(5, 26, 10, 51)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(485, 41, 12, 12), new Leading(49, 25, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(67, 138, 12, 12), new Leading(7, 26, 40, 40)));
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
			jPanel4.add(getJScrollPane0(), new Constraints(new Leading(3, 245, 10, 10), new Leading(4, 506, 10, 10)));
		}
		return jPanel4;
	}

	private Component getAttributePanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
