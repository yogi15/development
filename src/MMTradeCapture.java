import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class MMTradeCapture extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel mmTradeCapture;
	private JLabel tradeDateLabel;
	private JLabel bookLabel;
	private JLabel directionLabel;
	private JLabel producTypeLabel;

	private JTextField jTextField1;
	private JComboBox<String>  bookComboBox;
	private JComboBox<String>  directionComboBox;
	private JComboBox<String>  productTypeComboBox;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JComboBox<String>  tradeCCYComboBox;
	private JComboBox<String>  cpComboBox;
	private JComboBox<String>  rateComboBox;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JTextField jTextField5;
	private JComboBox<String> traderComboBox;
	private JTextField jTextField6;
	private JComboBox<String>  settleCCYComboBox;
	private JTextField jTextField0;
	private JLabel jLabel10;
	private JComboBox<String>  actionComboBox;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MMTradeCapture() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getMmTradeCapture(), new Constraints(new Leading(9, 810, 10, 10), new Leading(27, 221, 10, 10)));
		setSize(836, 409);
	}

	private JComboBox<String>  getActionComboBox() {
		if (actionComboBox == null) {
			actionComboBox = new JComboBox<String> ();
			actionComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return actionComboBox;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Trade Id");
		}
		return jLabel10;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JComboBox<String>  getSettleCCYComboBox() {
		if (settleCCYComboBox == null) {
			settleCCYComboBox = new JComboBox<String> ();
			settleCCYComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			settleCCYComboBox.setDoubleBuffered(false);
			settleCCYComboBox.setBorder(null);
		}
		return settleCCYComboBox;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		}
		return jTextField6;
	}

	private JComboBox<String> getTraderComboBox() {
		if (traderComboBox == null) {
			traderComboBox = new JComboBox<String>();
			traderComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			traderComboBox.setDoubleBuffered(false);
			traderComboBox.setBorder(null);
		}
		return traderComboBox;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
		}
		return jTextField5;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Settle CCY");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Amount");
		}
		return jLabel8;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Trader");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("End Date");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Action");
		}
		return jLabel5;
	}

	private JComboBox<String>  getRateComboBox() {
		if (rateComboBox == null) {
			rateComboBox = new JComboBox<String> ();
			rateComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			rateComboBox.setDoubleBuffered(false);
			rateComboBox.setBorder(null);
		}
		return rateComboBox;
	}

	private JComboBox<String>  getCPComboBox() {
		if (cpComboBox == null) {
			cpComboBox = new JComboBox<String> ();
			cpComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			cpComboBox.setDoubleBuffered(false);
			cpComboBox.setBorder(null);
		}
		return cpComboBox;
	}

	private JComboBox<String>  getTradeCCYComboBox() {
		if (tradeCCYComboBox == null) {
			tradeCCYComboBox = new JComboBox<String> ();
			tradeCCYComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			tradeCCYComboBox.setDoubleBuffered(false);
			tradeCCYComboBox.setBorder(null);
		}
		return tradeCCYComboBox;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		}
		return jTextField3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Rate Type");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Trade CCY");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Counterparty");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Start Date");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Status");
		}
		return jLabel0;
	}

	private JComboBox<String>  getProductTypeComboBox() {
		if (productTypeComboBox == null) {
			productTypeComboBox = new JComboBox<String> ();
			productTypeComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			productTypeComboBox.setDoubleBuffered(false);
			productTypeComboBox.setBorder(null);
		}
		return productTypeComboBox;
	}

	private JComboBox<String>  getDirectionComboBox() {
		if (directionComboBox == null) {
			directionComboBox = new JComboBox<String> ();
			directionComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			directionComboBox.setDoubleBuffered(false);
			directionComboBox.setBorder(null);
		}
		return directionComboBox;
	}

	private JComboBox<String>  getBookComboBox() {
		if (bookComboBox == null) {
			bookComboBox = new JComboBox<String> ();
			bookComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			bookComboBox.setDoubleBuffered(false);
			bookComboBox.setBorder(null);
		}
		return bookComboBox;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JLabel getProducTypeLabel() {
		if (producTypeLabel == null) {
			producTypeLabel = new JLabel();
			producTypeLabel.setText("Product Type");
		}
		return producTypeLabel;
	}

	private JLabel getDirectionLabel() {
		if (directionLabel == null) {
			directionLabel = new JLabel();
			directionLabel.setText("Direction");
		}
		return directionLabel;
	}

	private JLabel getBookLabel() {
		if (bookLabel == null) {
			bookLabel = new JLabel();
			bookLabel.setText("Book");
		}
		return bookLabel;
	}

	private JLabel getTradeDateLabel() {
		if (tradeDateLabel == null) {
			tradeDateLabel = new JLabel();
			tradeDateLabel.setText("Trade Date");
		}
		return tradeDateLabel;
	}

	private JPanel getMmTradeCapture() {
		if (mmTradeCapture == null) {
			mmTradeCapture = new JPanel();
			mmTradeCapture.setLayout(new GroupLayout());
			mmTradeCapture.add(getJLabel10(), new Constraints(new Leading(17, 10, 10), new Leading(17, 12, 12)));
			mmTradeCapture.add(getTradeDateLabel(), new Constraints(new Leading(17, 12, 12), new Leading(51, 12, 12)));
			mmTradeCapture.add(getJTextField3(), new Constraints(new Leading(394, 123, 12, 12), new Leading(45, 12, 12)));
			mmTradeCapture.add(getJTextField2(), new Constraints(new Leading(394, 89, 12, 12), new Leading(11, 12, 12)));
			mmTradeCapture.add(getJLabel1(), new Constraints(new Leading(306, 12, 12), new Leading(51, 12, 12)));
			mmTradeCapture.add(getJLabel0(), new Constraints(new Leading(306, 12, 12), new Leading(17, 12, 12)));
			mmTradeCapture.add(getJTextField0(), new Constraints(new Leading(125, 137, 10, 10), new Leading(11, 12, 12)));
			mmTradeCapture.add(getJTextField1(), new Constraints(new Leading(125, 136, 12, 12), new Leading(45, 12, 12)));
			mmTradeCapture.add(getBookComboBox(), new Constraints(new Leading(125, 142, 12, 12), new Leading(82, 26, 12, 12)));
			mmTradeCapture.add(getProducTypeLabel(), new Constraints(new Leading(17, 12, 12), new Leading(176, 12, 12)));
			mmTradeCapture.add(getBookLabel(), new Constraints(new Leading(17, 60, 12, 12), new Leading(90, 18, 12, 12)));
			mmTradeCapture.add(getProductTypeComboBox(), new Constraints(new Leading(125, 142, 12, 12), new Leading(165, 25, 12, 12)));
			mmTradeCapture.add(getRateComboBox(), new Constraints(new Leading(394, 156, 12, 12), new Leading(164, 26, 12, 12)));
			mmTradeCapture.add(getJLabel4(), new Constraints(new Leading(306, 12, 12), new Leading(176, 12, 12)));
			mmTradeCapture.add(getJLabel2(), new Constraints(new Leading(306, 10, 10), new Leading(94, 12, 12)));
			mmTradeCapture.add(getJLabel9(), new Constraints(new Leading(579, 12, 12), new Leading(176, 12, 12)));
			mmTradeCapture.add(getJLabel7(), new Constraints(new Leading(579, 12, 12), new Leading(94, 12, 12)));
			mmTradeCapture.add(getTraderComboBox(), new Constraints(new Leading(661, 135, 10, 10), new Leading(81, 27, 12, 12)));
			mmTradeCapture.add(getJLabel6(), new Constraints(new Leading(579, 12, 12), new Leading(53, 12, 12, 12)));
			mmTradeCapture.add(getJLabel5(), new Constraints(new Leading(579, 12, 12), new Leading(17, 12, 12)));
			mmTradeCapture.add(getActionComboBox(), new Constraints(new Leading(661, 140, 12, 12), new Leading(8, 23, 12, 12)));
			mmTradeCapture.add(getJTextField5(), new Constraints(new Leading(661, 136, 12, 12), new Leading(45, 12, 12)));
			mmTradeCapture.add(getSettleCCYComboBox(), new Constraints(new Leading(661, 140, 10, 10), new Leading(164, 26, 12, 12)));
			mmTradeCapture.add(getJLabel8(), new Constraints(new Leading(579, 12, 12), new Leading(128, 24, 12, 12)));
			mmTradeCapture.add(getJTextField6(), new Constraints(new Leading(661, 134, 12, 12), new Leading(124, 22, 10, 10)));
			mmTradeCapture.add(getTradeCCYComboBox(), new Constraints(new Leading(394, 153, 12, 12), new Leading(126, 26, 12, 12)));
			mmTradeCapture.add(getDirectionComboBox(), new Constraints(new Leading(125, 142, 12, 12), new Leading(126, 26, 12, 12)));
			mmTradeCapture.add(getJLabel3(), new Constraints(new Leading(306, 12, 12), new Leading(138, 15, 10, 10)));
			mmTradeCapture.add(getDirectionLabel(), new Constraints(new Leading(19, 10, 10), new Leading(136, 10, 10)));
			mmTradeCapture.add(getCPComboBox(), new Constraints(new Leading(394, 152, 12, 12), new Leading(81, 27, 12, 12)));
		}
		return mmTradeCapture;
	}

	private JPanel getJPanel0() {
		if (mmTradeCapture == null) {
			mmTradeCapture = new JPanel();
			mmTradeCapture.setLayout(new GroupLayout());
		}
		return mmTradeCapture;
	}

}
