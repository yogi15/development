import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class BasicData extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JTextField currencyPair;
	private JLabel jLabel2;
	private JRadioButton jRadioButton1;
	private JLabel jLabel3;
	private JTextField book;
	private JTextField buysell;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton3;
	private JLabel jLabel4;
	private JTextField counterPary;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton4;
	private JRadioButton jRadioButton5;
	private JRadioButton jRadioButton6;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public BasicData() {
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.green, new Color(255, 128, 0)));
		setLayout(new GroupLayout());
		add(getJRadioButton0(), new Constraints(new Leading(733, 10, 10), new Leading(5, 12, 12)));
		add(getJRadioButton4(), new Constraints(new Leading(733, 12, 12), new Leading(34, 12, 12)));
		add(getCurrencyPair(), new Constraints(new Leading(12, 65, 12, 12), new Leading(40, 23, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(14, 75, 10, 10), new Leading(12, 18, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(99, 57, 10, 10), new Leading(12, 18, 12, 12)));
		add(getBook(), new Constraints(new Leading(99, 147, 12, 12), new Leading(40, 23, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(256, 64, 10, 10), new Leading(12, 12, 12)));
		add(getBuysell(), new Constraints(new Leading(258, 65, 12, 12), new Leading(40, 23, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(338, 121, 12, 12), new Leading(12, 12, 12)));
		add(getCounterPary(), new Constraints(new Leading(337, 197, 10, 10), new Leading(40, 23, 12, 12)));
		add(getJRadioButton2(), new Constraints(new Leading(546, 12, 12), new Leading(34, 12, 12)));
		add(getJRadioButton3(), new Constraints(new Leading(546, 10, 10), new Leading(60, 10, 10)));
		add(getJRadioButton1(), new Constraints(new Leading(546, 10, 10), new Leading(5, 12, 12)));
		add(getJRadioButton5(), new Constraints(new Leading(646, 83, 10, 10), new Leading(7, 12, 12)));
		add(getJRadioButton6(), new Constraints(new Leading(646, 83, 12, 12), new Leading(34, 12, 12)));
		setSize(864, 106);
	}

	private JRadioButton getJRadioButton6() {
		if (jRadioButton6 == null) {
			jRadioButton6 = new JRadioButton();
			jRadioButton6.setSelected(true);
			jRadioButton6.setText("RollOver");
		}
		return jRadioButton6;
	}

	private JRadioButton getJRadioButton5() {
		if (jRadioButton5 == null) {
			jRadioButton5 = new JRadioButton();
			jRadioButton5.setSelected(true);
			jRadioButton5.setText("RollBack");
		}
		return jRadioButton5;
	}

	private JRadioButton getJRadioButton4() {
		if (jRadioButton4 == null) {
			jRadioButton4 = new JRadioButton();
			jRadioButton4.setSelected(true);
			jRadioButton4.setText("jRadioButton4");
		}
		return jRadioButton4;
	}

	private JRadioButton getJRadioButton0() {
		if (jRadioButton0 == null) {
			jRadioButton0 = new JRadioButton();
			jRadioButton0.setSelected(true);
			jRadioButton0.setText("jRadioButton0");
		}
		return jRadioButton0;
	}

	private JTextField getCounterPary() {
		if (counterPary == null) {
			counterPary = new JTextField();
			counterPary.setText("Counterparty");
		}
		return counterPary;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("CounterParty");
		}
		return jLabel4;
	}

	private JRadioButton getJRadioButton3() {
		if (jRadioButton3 == null) {
			jRadioButton3 = new JRadioButton();
			jRadioButton3.setSelected(true);
			jRadioButton3.setText("FWD Option");
		}
		return jRadioButton3;
	}

	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setSelected(true);
			jRadioButton2.setText("Swap");
		}
		return jRadioButton2;
	}

	private JTextField getBuysell() {
		if (buysell == null) {
			buysell = new JTextField();
			buysell.setText("Buy/sell");
		}
		return buysell;
	}

	private JTextField getBook() {
		if (book == null) {
			book = new JTextField();
			book.setText("Book");
		}
		return book;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("BUY/SELL");
		}
		return jLabel3;
	}

	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setSelected(true);
			jRadioButton1.setText("OutRight");
		}
		return jRadioButton1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Book");
		}
		return jLabel2;
	}

	private JTextField getCurrencyPair() {
		if (currencyPair == null) {
			currencyPair = new JTextField();
			currencyPair.setText("CurrencyPair");
		}
		return currencyPair;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Currency Pair");
		}
		return jLabel1;
	}

}
