package apps.window.reportwindow;

import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.FilterBean;
import beans.UserJobsDetails;

//VS4E -- DO NOT REMOVE THIS LINE!
public class MessageSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField TradeID;
	private JTextField EndDate;
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
	private JLabel jLabel16;
	private JTextField POLegalEntity;
	//private JTextField counterParty;
	private JTextField StartDate;
	private JTextField action;
	private JTextField LegalEntity;
	private JTextField productType;
	private JTextField debit;
	private JTextField currency;
	private JTextField transferType;
	private JTextField account;
	private JTextField eventType;
	private JTextField accountingRule;
	private JTextField Book;
	private JLabel jLabel17;
	private JTextField effectiveDateFrom;
	private JLabel jLabel18;
	private JTextField EffectiveDateTo;
	private JLabel jLabel19;
	private JTextField CreationDate;
//	private JTextField jTextField2;
	private JLabel jLabel20;
	private JTextField CreationEnd;
	private JTextField Credit;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public MessageSearchPanel() {
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
		add(getJLabel2(), new Constraints(new Leading(-181, 60, 10, 10), new Leading(181, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(10, 52, 10, 10), new Leading(9, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(10, 52, 10, 10), new Leading(41, 12, 12)));
		add(getTradeID(), new Constraints(new Leading(264, 96, 10, 10), new Leading(12, 12, 12)));
		add(getEndDate(), new Constraints(new Leading(264, 96, 12, 12), new Leading(41, 12, 12)));
		add(getStartDate(), new Constraints(new Leading(95, 98, 12, 12), new Leading(41, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(201, 12, 12), new Leading(12, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(201, 52, 12, 12), new Leading(44, 12, 12)));
		add(getAction(), new Constraints(new Leading(95, 98, 12, 12), new Leading(12, 12, 12)));
		add(getJLabel17(), new Constraints(new Leading(12, 70, 10, 10), new Leading(76, 12, 12)));
		add(getJLabel18(), new Constraints(new Leading(201, 52, 12, 12), new Leading(76, 12, 12)));
		add(getEffectiveDateTo(), new Constraints(new Leading(265, 96, 12, 12), new Leading(73, 12, 12)));
		add(getJLabel19(), new Constraints(new Leading(12, 70, 12, 12), new Leading(111, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(94, 98, 12, 12), new Leading(111, 12, 12)));
		add(getEffectiveFrom(), new Constraints(new Leading(95, 98, 12, 12), new Leading(76, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(199, 52, 12, 12), new Leading(114, 12, 12)));
		add(getCreationEnd(), new Constraints(new Leading(265, 98, 12, 12), new Leading(111, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(12, 58, 12, 12), new Leading(146, 12, 12)));
		add(getLegalEntity(), new Constraints(new Leading(94, 98, 12, 12), new Leading(143, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(204, 52, 12, 12), new Leading(146, 12, 12)));
		add(getPOLegalEntity(), new Constraints(new Leading(265, 96, 12, 12), new Leading(143, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(201, 52, 12, 12), new Leading(175, 12, 12)));
		add(getCurrency(), new Constraints(new Leading(265, 98, 12, 12), new Leading(173, 12, 12)));
		add(getProductType(), new Constraints(new Leading(94, 98, 12, 12), new Leading(176, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(10, 70, 12, 12), new Leading(179, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(10, 70, 12, 12), new Leading(214, 12, 12)));
		add(getDebit(), new Constraints(new Leading(95, 98, 12, 12), new Leading(211, 12, 12)));
		add(getJLabel15(), new Constraints(new Leading(204, 52, 12, 12), new Leading(211, 12, 12)));
		add(getCredit(), new Constraints(new Leading(265, 98, 12, 12), new Leading(208, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(10, 70, 12, 12), new Leading(246, 12, 12)));
		add(getTransferType(), new Constraints(new Leading(94, 98, 12, 12), new Leading(243, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(10, 70, 12, 12), new Leading(281, 12, 12)));
		add(getAccount(), new Constraints(new Leading(95, 98, 12, 12), new Leading(278, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(8, 70, 10, 10), new Leading(316, 12, 12)));
		add(getEventType(), new Constraints(new Leading(96, 98, 12, 12), new Leading(316, 12, 12)));
		add(getJLabel13(), new Constraints(new Leading(7, 70, 12, 12), new Leading(350, 17, 10, 10)));
		add(getAccountingRule(), new Constraints(new Leading(96, 98, 12, 12), new Leading(348, 12, 12)));
		add(getJLabel14(), new Constraints(new Leading(5, 84, 12, 12), new Leading(385, 12, 12)));
		add(getBook(), new Constraints(new Leading(96, 98, 12, 12), new Leading(380, 12, 12)));
		add(getJLabel16(), new Constraints(new Leading(8, 84, 12, 12), new Leading(418, 12, 12)));
		setSize(420, 537);
	}

	private JTextField getCredit() {
		if (Credit == null) {
			Credit = new JTextField();
		}
		return Credit;
	}

	private JTextField getCreationEnd() {
		if (CreationEnd == null) {
			CreationEnd = new JTextField();
		}
		return CreationEnd;
	}

	private JLabel getJLabel20() {
		if (jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("To");
		}
		return jLabel20;
	}

	private JTextField getEffectiveFrom() {
		if (effectiveDateFrom == null) {
			effectiveDateFrom = new JTextField();
		}
		return effectiveDateFrom;
	}

	private JLabel getJLabel19() {
		if (jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("Creation Date");
		}
		return jLabel19;
	}

	private JTextField getEffectiveDateTo() {
		if (EffectiveDateTo == null) {
			EffectiveDateTo = new JTextField();
		}
		return EffectiveDateTo;
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("To");
		}
		return jLabel18;
	}

	private JTextField getJTextField1() {
		if (CreationDate == null) {
			CreationDate = new JTextField();
		}
		return CreationDate;
	}

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("Effective Date");
		}
		return jLabel17;
	}

	private JTextField getJTextField0() {
		if (Book == null) {
			Book = new JTextField();
		}
		return Book;
	}

	private JTextField getBook() {
		if (Book == null) {
			Book = new JTextField();
			Book.setText("        ");
		}
		return Book;
	}
	private JTextField getAccountingRule() {
		if (accountingRule == null) {
			accountingRule = new JTextField();
			accountingRule.setText("        ");
		}
		return accountingRule;
	}

	private JTextField getAccount() {
		if (account == null) {
			account = new JTextField();
		}
		return account;
	}
	private JTextField getEventType() {
		if (eventType == null) {
			eventType = new JTextField();
		}
		return eventType;
	}

	private JTextField getCurrency() {
		if (currency == null) {
			currency = new JTextField();
		}
		return currency;
	}


	private JTextField getTransferType() {
		if (transferType == null) {
			transferType = new JTextField();
		}
		return transferType;
	}

	private JTextField getDebit() {
		if (debit == null) {
			debit = new JTextField();
		}
		return debit;
	}

	private JTextField getProductType() {
		if (productType == null) {
			productType = new JTextField();
		}
		return productType;
	}

	private JTextField getLegalEntity() {
		if (LegalEntity == null) {
			LegalEntity = new JTextField();
		}
		return LegalEntity;
	}

	private JTextField getAction() {
		if (action == null) {
			action = new JTextField();
			
		}
		return action;
	}
	private JTextField getEndDate() {
		if (EndDate == null) {
			EndDate = new JTextField();
			
		}
		return EndDate;
	}
	private JTextField getPOLegalEntity() {
		if (POLegalEntity == null) {
			POLegalEntity = new JTextField();
			
		}
		return POLegalEntity;
	}
	
	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("Agent");
		}
		return jLabel16;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Credit");
		}
		return jLabel15;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Book");
		}
		return jLabel14;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("AccountRule");
		}
		return jLabel13;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Eventtype");
		}
		return jLabel12;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Account");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Transfer Type");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Debit");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Currency");
		}
		return jLabel8;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("ProductType");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("PO");
		}
		return jLabel6;
	}

	private JTextField getStartDate() {
		if (StartDate == null) {
			StartDate = new JTextField();
		}
		return StartDate;
	}

	private JTextField getTradeID() {
		if (TradeID == null) {
			TradeID = new JTextField();
		}
		return TradeID;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("LegalEntity");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("EndDate");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("StartDate");
		}
		return jLabel3;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Posting ID");
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Trade ID");
		}
		return jLabel1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Arial", Font.PLAIN, 9));
			jLabel2.setText("StartDate");
		}
		return jLabel2;
	}

	@Override
	public Vector<FilterBean> searchCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearllCriterial() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		// TODO Auto-generated method stub
		
	}

}

