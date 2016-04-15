package apps.window.reportwindow;

import java.awt.Font;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import beans.FilterBean;
import beans.UserJobsDetails;

import com.jidesoft.combobox.DateComboBox;

//VS4E -- DO NOT REMOVE THIS LINE!
public class PostingSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private DateComboBox EndDate;
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
	private DateComboBox StartDate;
	private JTextField debit;
	private JLabel jLabel17;
	private DateComboBox effectiveDateFrom;
	private JLabel jLabel18;
	private DateComboBox EffectiveDateTo;
	private JLabel jLabel19;
	private DateComboBox CreationDate;
	private JLabel jLabel20;
	private DateComboBox CreationEnd;
	private JTextField Credit;
	private JTextField PostingId;
	private JTextField TradeId;

	
	javax.swing.DefaultComboBoxModel<String> legalEntityData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> poData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> agentData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> bookData = new javax.swing.DefaultComboBoxModel<String>();
	//javax.swing.DefaultComboBoxModel<String> bookAttributesData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> currencyAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	//javax.swing.DefaultComboBoxModel<String> actionAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	//javax.swing.DefaultComboBoxModel<String> statusAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> productTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> transferTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> eventTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	
	
	private JComboBox<String>  leComboBox;
	private JComboBox<String>  poComboBOx;
	private JComboBox<String>  productTypeComboBox;
	private JComboBox<String>  currencyComboBox;
	private JComboBox<String>  transferTypeComboBox;
	private JComboBox<String>  eventTypeComboBox;
	private JComboBox<String>  bookComboBox;
	private JComboBox jComboBox3;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public PostingSearchPanel() {
		init();
		initComponents();
	}
	
	private void init(){
		
		processLEDataCombo1(legalEntityData,  counterPartyID, "CounterParty");
		processLEDataCombo1(poData,  poID, "PO");
		processLEDataCombo1(agentData,  agentID, "Agent");
		
		//processBookDataCombo1(bookAttributesData, books);
		//processDomainData(bookAttributesData,  getFilterValues().getDomainValues("Book"));
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		//processDomainData(statusAttributeData,  getFilterValues().getDomainValues("Status"));
		//processDomainData(actionAttributeData,  getFilterValues().getDomainValues("Action"));
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));
		processDomainData(transferTypeAttributeData,  getFilterValues().getDomainValues("TransferType"));
		processDomainData(eventTypeAttributeData,  getFilterValues().getDomainValues("EventType"));
		
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
		add(getJLabel3(), new Constraints(new Leading(10, 52, 10, 10), new Leading(47, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(12, 58, 12, 12), new Leading(146, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(10, 70, 12, 12), new Leading(211, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(10, 70, 12, 12), new Leading(249, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(8, 70, 10, 10), new Leading(316, 12, 12)));
		add(getJLabel13(), new Constraints(new Leading(7, 70, 12, 12), new Leading(350, 17, 10, 10)));
		add(getJLabel16(), new Constraints(new Leading(8, 84, 12, 12), new Leading(418, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(12, 52, 12, 12), new Leading(15, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(8, 70, 12, 12), new Leading(178, 12, 12)));
		add(getJLabel14(), new Constraints(new Leading(7, 84, 12, 12), new Leading(386, 17, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(8, 70, 12, 12), new Leading(281, 12, 12)));
		add(getJLabel17(), new Constraints(new Leading(7, 70, 12, 12), new Leading(79, 12, 12)));
		add(getJLabel19(), new Constraints(new Leading(7, 70, 12, 12), new Leading(111, 12, 12)));
		add(getJComboBox3(), new Constraints(new Leading(96, 108, 12, 12), new Leading(275, 12, 12)));
		add(getJComboBox1(), new Constraints(new Leading(95, 110, 12, 12), new Leading(312, 12, 12)));
		add(getJComboBox2(), new Constraints(new Leading(96, 112, 12, 12), new Leading(380, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(227, 12, 12), new Leading(15, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(229, 12, 12), new Leading(44, 17, 12, 12)));
		add(getJLabel18(), new Constraints(new Leading(229, 52, 12, 12), new Leading(79, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(229, 52, 12, 12), new Leading(111, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(227, 52, 12, 12), new Leading(146, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(227, 50, 12, 12), new Leading(178, 12, 12)));
		add(getEffectiveFrom(), new Constraints(new Leading(95, 122, 12, 12), new Leading(73, 20, 12, 12)));
		add(getTradeId(), new Constraints(new Leading(96, 120, 12, 12), new Leading(9, 12, 12)));
		add(getStartDate(), new Constraints(new Leading(95, 122, 12, 12), new Leading(41, 20, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(94, 124, 12, 12), new Leading(105, 20, 12, 12)));
		add(getLeComboBox(), new Constraints(new Leading(94, 121, 12, 12), new Leading(140, 12, 12)));
		add(getProductTypeComboBox(), new Constraints(new Leading(94, 120, 12, 12), new Leading(172, 12, 12)));
		add(getCreationEnd(), new Constraints(new Leading(284, 120, 12, 12), new Leading(105, 20, 12, 12)));
		add(getEffectiveDateTo(), new Constraints(new Bilateral(282, 12, 9), new Leading(73, 20, 12, 12)));
		add(getEndDate(), new Constraints(new Bilateral(282, 12, 9), new Leading(41, 20, 12, 12)));
		add(getPostingId(), new Constraints(new Bilateral(282, 12, 6), new Leading(9, 12, 12)));
		add(getCredit(), new Constraints(new Bilateral(282, 12, 6), new Leading(205, 12, 12)));
		add(getJLabel15(), new Constraints(new Leading(227, 52, 24, 24), new Leading(210, 15, 12, 12)));
		add(getCurrencyComboBox(), new Constraints(new Leading(284, 120, 12, 12), new Leading(172, 12, 12)));
		add(getPoComboBOx(), new Constraints(new Leading(285, 118, 12, 12), new Leading(140, 12, 12)));
		add(getDebit(), new Constraints(new Leading(95, 120, 24, 24), new Leading(208, 17, 12, 12)));
		add(getJComboBox0(), new Constraints(new Leading(96, 118, 12, 12), new Leading(243, 12, 12)));
		setSize(420, 537);
	}

	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return jComboBox3;
	}

	private JComboBox<String> getJComboBox2() {
		if (bookComboBox == null) {
			bookComboBox = new JComboBox<String>();
			bookComboBox.setModel(bookData);
		}
		return bookComboBox;
	}

	private JComboBox<String> getJComboBox1() {
		if (eventTypeComboBox == null) {
			eventTypeComboBox = new JComboBox<String>();
			eventTypeComboBox.setModel(eventTypeAttributeData);
		}
		return eventTypeComboBox;
	}

	private JComboBox<String> getJComboBox0() {
		if (transferTypeComboBox == null) {
			transferTypeComboBox = new JComboBox<String>();
			transferTypeComboBox.setModel(transferTypeAttributeData);
		}
		return transferTypeComboBox;
	}

	private JComboBox<String> getCurrencyComboBox() {
		if (currencyComboBox == null) {
			currencyComboBox = new JComboBox<String>();
			currencyComboBox.setModel(currencyAttributeData);
		}
		return currencyComboBox;
	}

	private JComboBox<String> getProductTypeComboBox() {	
		if (productTypeComboBox == null) {
			productTypeComboBox = new JComboBox<String>();
			productTypeComboBox.setModel(productTypeAttributeData);
		}
		return productTypeComboBox;
	}

	private JComboBox<String> getPoComboBOx() {
		if (poComboBOx == null) {
			poComboBOx = new JComboBox<String>();
			poComboBOx.setModel(poData);
		}
		return poComboBOx;
	}

	private JComboBox<String> getLeComboBox() {
		if (leComboBox == null) {
			leComboBox = new JComboBox<String>();
			leComboBox.setModel(legalEntityData);
		}
		return leComboBox;
	}

	private JTextField getTradeId() {
		if (TradeId == null) {
			TradeId = new JTextField();
			TradeId.setText("");
		}
		return TradeId;
	}

	private JTextField getPostingId() {
		if (PostingId == null) {
			PostingId = new JTextField();
			PostingId.setText("");
		}
		return PostingId;
	}

	private JTextField getCredit() {
		if (Credit == null) {
			Credit = new JTextField();
		}
		return Credit;
	}

	private DateComboBox getCreationEnd() {
		if (CreationEnd == null) {
			CreationEnd = new DateComboBox();
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

	private DateComboBox getEffectiveFrom() {
		if (effectiveDateFrom == null) {
			effectiveDateFrom = new DateComboBox();
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

	private DateComboBox getEffectiveDateTo() {
		if (EffectiveDateTo == null) {
			EffectiveDateTo = new DateComboBox();
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

	private DateComboBox getJTextField1() {
		if (CreationDate == null) {
			CreationDate = new DateComboBox();
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

	private JTextField getDebit() {
		if (debit == null) {
			debit = new JTextField();
		}
		return debit;
	}

	private DateComboBox getEndDate() {
		if (EndDate == null) {
			EndDate = new DateComboBox();
			
		}
		return EndDate;
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
			jLabel12.setText("AccEventtype");
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
			jLabel10.setText("PostingType");
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

	private DateComboBox getStartDate() {
		if (StartDate == null) {
			StartDate = new DateComboBox();
		}
		return StartDate;
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
		
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		
		if(!commonUTIL.isEmpty(TradeId.getText())) {
			
			filterBeans.add(getOtherId(TradeId.getText()));
		} 
		
		if(!commonUTIL.isEmpty(PostingId.getText())) {
			
			filterBeans.add(getTransferId(PostingId.getText()));
		} 
		
		if( (StartDate.getDate() != null 
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(StartDate.getDate())))) {				
			
			Date fromTrade = StartDate.getDate();
			
			if (EndDate.getDate() != null) {
				
				Date toTradeDate = EndDate.getDate();
				
				if (toTradeDate.after(fromTrade)) {
					
					filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(fromTrade), 
							commonUTIL.convertDateTOString(toTradeDate), "TransferDate"));
				
					
				}  else {
					
					commonUTIL.showAlertMessage("Start Date should be less then End Date");
					
				}				
			} 
			
			
			if (fromTrade == null  && EndDate.getDate() != null) {
				
				commonUTIL.showAlertMessage("Please select Start Date first");
			
			}
			
			if (fromTrade != null  && EndDate == null) {
				
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(fromTrade), 
						commonUTIL.convertDateTOString(fromTrade), "TransferDate"));
				
			}
			
		}
		
		if( (effectiveDateFrom.getDate() != null 
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(effectiveDateFrom.getDate())))) {				
			
			Date effDateFrom = effectiveDateFrom.getDate();
			
			if (EffectiveDateTo.getDate() != null) {
				
				Date toEffectiveDate = EffectiveDateTo.getDate();
				
				if (toEffectiveDate.after(effDateFrom)) {
					
					filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(effDateFrom), 
							commonUTIL.convertDateTOString(toEffectiveDate), "EffectiveDate"));
					
					
				}  else {
					
					commonUTIL.showAlertMessage("Effective start date should be less then Effective end date");
					
				}				
			} 
				
			
			if (effectiveDateFrom == null  && EffectiveDateTo.getDate() != null) {
				
				commonUTIL.showAlertMessage("Please select Effective start date first");
			
			}
			
			if (effectiveDateFrom != null  && EffectiveDateTo == null) {
				
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(effDateFrom), 
						commonUTIL.convertDateTOString(effDateFrom), "EffectiveDate"));
				
			}
			
		}
		
		if( (CreationDate.getDate() != null 
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(CreationDate.getDate())))) {				
			
			Date fromCreationDate = CreationDate.getDate();
			
			if (CreationEnd.getDate() != null) {
				
				Date toCreationDate = CreationEnd.getDate();
				
				if (toCreationDate.after(fromCreationDate)) {
					
					filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(fromCreationDate), 
							commonUTIL.convertDateTOString(toCreationDate), "CreationDate"));
					
					
				}  else {
					
					commonUTIL.showAlertMessage("Creation start date should be less then Creation end date");
					
				}				
			} 
			
			
			if (fromCreationDate == null  && CreationEnd.getDate() != null) {
				
				commonUTIL.showAlertMessage("Please select Creation start date first");
			
			}
			
			if (fromCreationDate != null  && CreationEnd == null) {
				
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(fromCreationDate), 
						commonUTIL.convertDateTOString(fromCreationDate), "CreationDate"));
				
			}
			
			if( leComboBox.getSelectedItem()!= null && !commonUTIL.isEmpty(leComboBox.getSelectedItem().toString())) {
				
				filterBeans.add(getLegalEntity(leComboBox.getSelectedIndex(), "cpid"));
				
			} 
			
			if(poComboBOx.getSelectedItem() != null  && !commonUTIL.isEmpty(poComboBOx.getSelectedItem().toString())) {
				
				filterBeans.add(getLegalEntity(poComboBOx.getSelectedIndex(), "poId"));
				
			}
			
		}
		
		if( leComboBox.getSelectedItem()!= null && !commonUTIL.isEmpty(leComboBox.getSelectedItem().toString())) {
			
			filterBeans.add(getLegalEntity(leComboBox.getSelectedIndex(), "cpid"));
			
		} 
		
		if(poComboBOx.getSelectedItem() != null  && !commonUTIL.isEmpty(poComboBOx.getSelectedItem().toString())) {
			
			filterBeans.add(getLegalEntity(poComboBOx.getSelectedIndex(), "poId"));
			
		}

		if(productTypeComboBox.getSelectedItem() != null && !commonUTIL.isEmpty(productTypeComboBox.getSelectedItem().toString())) {
			
			filterBeans.add(getProductType(productTypeComboBox.getSelectedItem().toString()));
		} 	
		
		if( currencyComboBox.getSelectedItem() != null && !commonUTIL.isEmpty(currencyComboBox.getSelectedItem().toString())) {
			
			filterBeans.add(getCurrency(currencyComboBox.getSelectedItem().toString(), "TrasnferCurrency"));
	
		}
		return filterBeans;
		
		
		
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
