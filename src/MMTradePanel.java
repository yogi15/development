package apps.window.tradewindow;

import java.awt.Component;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import productPricing.Pricer;
import beans.Trade;
import beans.Users;
import dsEventProcessor.TaskEventProcessor;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMTradePanel extends TradePanel {

	private static final long serialVersionUID = 1L;
	private JPanel AttributePanel;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel5;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JRadioButton Rollover;
	private JPanel jPanel6;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton New;
	private JButton Save;
	private JButton SaveAsNew;
	private JButton Delete;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JComboBox Currency;
	private JRadioButton Rollback;
	private JComboBox Book;
	private JComboBox cp;
	private JComboBox Trader;
	private JTextField TradeID;
	private JTextField startDate;
	private JTextField productName;
	private JTextField EndDate;
	private JTextField Rate;
	private JTextField Amount1;
	private JComboBox Action;
	private JTextField status;
	private JTable CashFlowTable;
	private JScrollPane jScrollPane1;
	private JLabel jLabel13;
	private JPanel jPanel7;
	private JLabel jLabel12;
	private JLabel jLabel14;
	private JTextField jTextField7;
	private JTextField jTextField8;
	private JPanel jPanel8;
	private JCheckBox jCheckBox0;
	private JTable jTable2;
	private JScrollPane jScrollPane2;
	private JPanel jPanel9;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMTradePanel() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setFocusable(true);
		setEnabled(true);
		setVisible(true);
		setVerifyInputWhenFocusTarget(true);
		setDoubleBuffered(true);
		setRequestFocusEnabled(true);
		setOpaque(true);
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(-1, 908, 337, 337), new Leading(88, 116, 10, 10)));
		add(getJPanel2(), new Constraints(new Leading(0, 907, 337, 337), new Leading(210, 112, 251, 251)));
		add(getJPanel5(), new Constraints(new Leading(1, 904, 337, 337), new Leading(328, 46, 251, 251)));
		add(getJPanel3(), new Constraints(new Leading(3, 900, 337, 337), new Leading(378, 241, 10, 10)));
		add(getJPanel0(), new Constraints(new Leading(-1, 908, 291, 311), new Leading(3, 79, 10, 10)));
		add(getJPanel9(), new Constraints(new Leading(921, 295, 10, 10), new Leading(7, 608, 10, 10)));
		setSize(1556, 698);
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(new GroupLayout());
			jPanel9.add(getJScrollPane2(), new Constraints(new Bilateral(8, 12, 27), new Leading(10, 584, 10, 10)));
		}
		return jPanel9;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}

	private JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
			jTable2.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable2;
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setSelected(true);
			jCheckBox0.setText("Float");
		}
		return jCheckBox0;
	}

	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jPanel8 = new JPanel();
			jPanel8.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel8.setLayout(new GroupLayout());
		}
		return jPanel8;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
		}
		return jTextField8;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
		}
		return jTextField7;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("INR Equi");
		}
		return jLabel14;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Split Rate");
		}
		return jLabel12;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJPanel7(), new Constraints(new Leading(637, 250, 10, 10), new Leading(4, 101, 10, 10)));
			jPanel2.add(getJPanel8(), new Constraints(new Leading(4, 623, 10, 10), new Leading(2, 101, 10, 10)));
		}
		return jPanel2;
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel7.setLayout(new GroupLayout());
			jPanel7.add(getJLabel12(), new Constraints(new Leading(3, 10, 10), new Leading(3, 10, 10)));
			jPanel7.add(getJLabel14(), new Constraints(new Leading(6, 12, 12), new Leading(37, 12, 12)));
			jPanel7.add(getJTextField7(), new Constraints(new Leading(72, 97, 10, 10), new Leading(3, 12, 12)));
			jPanel7.add(getJTextField8(), new Constraints(new Leading(72, 96, 12, 12), new Leading(37, 12, 12)));
		}
		return jPanel7;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("INR Equi");
		}
		return jLabel13;
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

	

	private JTextField getJTextField2() {
		if (Amount1 == null) {
			Amount1 = new JTextField();
		}
		return Amount1;
	}

	private JTextField getJTextField5() {
		if (Rate == null) {
			Rate = new JTextField();
		}
		return Rate;
	}

	private JTextField getJTextField4() {
		if (EndDate == null) {
			EndDate = new JTextField();
		}
		return EndDate;
	}

	private JTextField getJTextField1() {
		if (productName == null) {
			productName = new JTextField();
		}
		return productName;
	}

	private JTextField getJTextField3() {
		if (startDate == null) {
			startDate = new JTextField();
		}
		return startDate;
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

	private JComboBox getJComboBox0() {
		if (Currency == null) {
			Currency = new JComboBox();
			Currency.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return Currency;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("EndDate");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("StartDate");
		}
		return jLabel10;
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

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Rate");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Amount");
		}
		return jLabel6;
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
			jPanel1.add(getJLabel4(), new Constraints(new Leading(12, 12, 12), new Leading(82, 12, 12)));
			jPanel1.add(getJTextField1(), new Constraints(new Leading(60, 390, 10, 10), new Leading(82, 26, 12, 12)));
			jPanel1.add(getJCheckBox0(), new Constraints(new Leading(475, 12, 12), new Leading(82, 12, 12)));
			jPanel1.add(getActionC(), new Constraints(new Leading(749, 152, 10, 10), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJLabel9(), new Constraints(new Leading(751, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJLabel7(), new Constraints(new Leading(651, 10, 10), new Leading(12, 10, 10)));
			jPanel1.add(getJLabel5(), new Constraints(new Leading(8, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJLabel10(), new Constraints(new Leading(109, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJLabel6(), new Constraints(new Leading(530, 10, 10), new Leading(12, 10, 10)));
			jPanel1.add(getJTextField2(), new Constraints(new Leading(465, 168, 12, 12), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJTextField5(), new Constraints(new Leading(651, 92, 12, 12), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJLabel11(), new Constraints(new Leading(309, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJTextField3(), new Constraints(new Leading(117, 161, 10, 10), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJTextField4(), new Constraints(new Leading(294, 151, 10, 10), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJTextField0(), new Constraints(new Leading(8, 97, 12, 12), new Leading(34, 26, 12, 12)));
			jPanel1.add(getJLabel8(), new Constraints(new Leading(751, 12, 12), new Leading(66, 12, 12)));
			jPanel1.add(getJTextField6(), new Constraints(new Leading(754, 142, 12, 12), new Leading(86, 24, 10, 10)));
		}
		return jPanel1;
	}

	private JComboBox getActionC() {
		// TODO Auto-generated method stub
		if(Action == null) {
			Action = new JComboBox();
			Action.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		
		return Action;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Trade ID");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Deposit");
		}
		return jLabel4;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel6.setLayout(new GroupLayout());
			jPanel6.add(getJRadioButton0(), new Constraints(new Leading(3, 10, 10), new Leading(9, 10, 10)));
			jPanel6.add(getJRadioButton3(), new Constraints(new Leading(3, 12, 12), new Leading(34, 10, 10)));
		}
		return jPanel6;
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
			jPanel0.add(getJLabel0(), new Constraints(new Leading(7, 10, 10), new Leading(10, 10, 10)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(5, 54, 12, 12), new Leading(36, 26, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(545, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJComboBox3(), new Constraints(new Leading(545, 219, 12, 12), new Leading(36, 26, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(265, 10, 10), new Leading(10, 12, 12)));
			jPanel0.add(getJComboBox2(), new Constraints(new Leading(261, 264, 10, 10), new Leading(36, 26, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(87, 10, 10), new Leading(6, 12, 12)));
			jPanel0.add(getJComboBox1(), new Constraints(new Leading(85, 164, 12, 12), new Leading(36, 26, 12, 12)));
			jPanel0.add(getJPanel6(), new Constraints(new Leading(776, 126, 10, 10), new Leading(0, 71, 10, 10)));
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

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Currency");
		}
		return jLabel0;
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
		}
		return jPanel5;
	}

	private Component getAttributePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Leading(5, 889, 10, 10), new Leading(3, 216, 10, 10)));
		}
		return jPanel3;
	}

	@Override
	public void setTaskPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSDIPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFEESPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLimitPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTradeTransfers(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTradePostings(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTrade(Trade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trade getTrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveASNew(Trade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(Users user) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void setTradeApplication(TradeApplication app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processTask(TaskEventProcessor taskEvent, Users WindowUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLimitBreachMarkOnAction(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildTrade(Trade trade, String actionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openTrade(Trade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTradePanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection getCashFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pricer getPricer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
