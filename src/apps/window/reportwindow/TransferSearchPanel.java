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

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import beans.FilterBean;
import beans.UserJobsDetails;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.MultiSelectListExComboBox;

import constants.CommonConstants;

//VS4E -- DO NOT REMOVE THIS LINE!
public class TransferSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField TransferId;
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

	private DateComboBox StartDate;
	private JTextField tradeId;
	private JTextField nettingID;
	private JLabel jLabel17;
	private JComboBox<String> leComboBox;
	private JComboBox<String>  poComboBOx;
	private JComboBox<String>  currencyComboBox;
	private JComboBox<String>  productTypeComboBox;
	private JComboBox<String>  statusComboBox;
	private JComboBox<String>  actionComboBox;
	private JComboBox<String>  transferTypeComboBox;
	private JComboBox<String>  nettingTypeComboBox;
	private JComboBox<String>  eventTypeComboBox;
	private JComboBox<String>  methodComboBox;
	private JComboBox<String>  settlementTypeComboBox;
	
	javax.swing.DefaultComboBoxModel<String> legalEntityData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> poData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> agentData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> bookAttributesData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> currencyAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> actionAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> statusAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> productTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> transferTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> eventTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> methodAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	
	private JLabel jLabel18;
	private JLabel jLabel19;
	private JLabel jLabel20;
	private MultiSelectListExComboBox bookComboBox;
	private JComboBox jComboBox0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TransferSearchPanel() {
		init();
		initComponents();
	}
	
	private void init() {
		
		processLEDataCombo1(legalEntityData,  counterPartyID, "CounterParty");
		processLEDataCombo1(poData,  poID, "PO");
		processLEDataCombo1(agentData,  agentID, "Agent");
		
		processBookDataCombo1(bookAttributesData, books);
		//processDomainData(bookAttributesData,  getFilterValues().getDomainValues("Book"));
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(statusAttributeData,  getFilterValues().getDomainValues("Status"));
		processDomainData(actionAttributeData,  getFilterValues().getDomainValues("Action"));
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));
		processDomainData(transferTypeAttributeData,  getFilterValues().getDomainValues("TransferType"));
		processDomainData(eventTypeAttributeData,  getFilterValues().getDomainValues("EventType"));
		processDomainData(methodAttributeData, getFilterValues().getDomainValues("MessageFormateType"));
		
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
		add(getJLabel1(), new Constraints(new Leading(8, 52, 12, 12), new Leading(18, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(8, 70, 12, 12), new Leading(149, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(8, 52, 12, 12), new Leading(47, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(8, 70, 12, 12), new Leading(108, 19, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(8, 70, 12, 12), new Leading(179, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(8, 70, 12, 12), new Leading(212, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(209, 70, 10, 10), new Leading(179, 12, 12)));
		add(getJLabel14(), new Constraints(new Leading(8, 84, 12, 12), new Leading(246, 20, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(8, 12, 12), new Leading(294, 10, 10)));
		add(getJLabel18(), new Constraints(new Leading(8, 12, 12), new Leading(326, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(206, 58, 10, 10), new Leading(252, 10, 10)));
		add(getJLabel17(), new Constraints(new Leading(8, 36, 12, 12), new Leading(79, 12, 12)));
		add(getTransferId(), new Constraints(new Leading(279, 128, 10, 10), new Leading(12, 12, 12)));
		add(getEndDate(), new Constraints(new Leading(279, 128, 12, 12), new Leading(41, 20, 12, 12)));
		add(getPOComboBOx(), new Constraints(new Leading(279, 128, 12, 12), new Leading(73, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(209, 52, 12, 12), new Leading(79, 12, 12)));
		add(getJLabel19(), new Constraints(new Leading(209, 12, 12), new Leading(105, 22, 12, 12)));
		add(getProductTypeComboBox(), new Constraints(new Leading(84, 114, 12, 12), new Leading(107, 12, 12)));
		add(getLeComboBox(), new Constraints(new Leading(84, 114, 12, 12), new Leading(73, 12, 12)));
		add(getStartDate(), new Constraints(new Leading(84, 113, 10, 10), new Leading(41, 20, 12, 12)));
		add(getTradeId(), new Constraints(new Leading(84, 114, 12, 12), new Leading(12, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(209, 52, 12, 12), new Leading(47, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(209, 12, 12), new Leading(18, 12, 12)));
		add(getJLabel15(), new Constraints(new Leading(209, 52, 12, 12), new Leading(149, 12, 12)));
		add(getJLabel13(), new Constraints(new Leading(209, 70, 12, 12), new Leading(209, 17, 12, 12)));
		add(getEventTypeComboBox(), new Constraints(new Leading(279, 128, 12, 12), new Leading(173, 12, 12)));
		add(getNettingID(), new Constraints(new Leading(279, 124, 12, 12), new Leading(206, 12, 12)));
		add(getCurrencyComboBox(), new Constraints(new Leading(279, 128, 12, 12), new Leading(246, 12, 12)));
		add(getActionComboBox(), new Constraints(new Leading(279, 128, 12, 12), new Leading(107, 12, 12)));
		add(getStatusComboBox(), new Constraints(new Leading(84, 112, 12, 12), new Leading(143, 12, 12)));
		add(getTransferTypeComboBox(), new Constraints(new Leading(84, 114, 12, 12), new Leading(173, 12, 12)));
		add(getNettingTypeComboBox(), new Constraints(new Leading(84, 113, 12, 12), new Leading(206, 12, 12)));
		add(getSettlementTypeComboBox(), new Constraints(new Leading(84, 112, 12, 12), new Leading(246, 12, 12)));
		add(getBookComboBox(), new Constraints(new Leading(84, 112, 12, 12), new Leading(288, 10, 10)));
		add(getMethodComboBox(), new Constraints(new Leading(87, 108, 12, 12), new Leading(318, 10, 10)));
		setSize(420, 537);
	}

	private JComboBox<String> getMethodComboBox() {		
		if (methodComboBox == null) {
			methodComboBox = new JComboBox<String>();
			methodComboBox.setModel(methodAttributeData);
		}
		return methodComboBox;

	}

	private MultiSelectListExComboBox getBookComboBox() {
		if (bookComboBox == null) {
			bookComboBox = new MultiSelectListExComboBox();
			bookComboBox.setModel(bookAttributesData);
		}
		return bookComboBox;
	}

	private JLabel getJLabel20() {
		if (jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("Book");
		}
		return jLabel20;
	}

	private JLabel getJLabel19() {
		if (jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("PrdSubType");
		}
		return jLabel19;
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("Method");
		}
		return jLabel18;
	}
	private JComboBox<String> getSettlementTypeComboBox() {
		if (settlementTypeComboBox == null) {
			settlementTypeComboBox = new JComboBox<String>();
			settlementTypeComboBox.setModel(new DefaultComboBoxModel(new Object[] {}));
		}
		return settlementTypeComboBox;
	}

	private JComboBox<String> getEventTypeComboBox() {
		if (eventTypeComboBox == null) {
			eventTypeComboBox = new JComboBox<String>();
			eventTypeComboBox.setModel(eventTypeAttributeData);
		}
		return eventTypeComboBox;
	}

	private JComboBox<String> getNettingTypeComboBox() {
		if (nettingTypeComboBox == null) {
			nettingTypeComboBox = new JComboBox<String>();
			nettingTypeComboBox.setModel(new DefaultComboBoxModel(new Object[] {}));
		}
		return nettingTypeComboBox;
	}

	private JComboBox<String> getTransferTypeComboBox() {
		if (transferTypeComboBox == null) {
			transferTypeComboBox = new JComboBox<String>();
			transferTypeComboBox.setModel(transferTypeAttributeData);
		}
		return transferTypeComboBox;
	}

	private JComboBox<String> getActionComboBox() {
		if (actionComboBox == null) {
			actionComboBox = new JComboBox<String>();
			actionComboBox.setModel(actionAttributeData);
		}
		return actionComboBox;
	}

	private JComboBox<String> getStatusComboBox() {
		if (statusComboBox == null) {
			statusComboBox = new JComboBox<String>();
			statusComboBox.setModel(statusAttributeData);
		}
		return statusComboBox;
	}

	private JComboBox<String> getProductTypeComboBox() {
		if (productTypeComboBox == null) {
			productTypeComboBox = new JComboBox<String>();
			productTypeComboBox.setModel(productTypeAttributeData);
		}
		return productTypeComboBox;
	}

	private JComboBox<String> getCurrencyComboBox() {
		if (currencyComboBox == null) {
			currencyComboBox = new JComboBox<String>();
			currencyComboBox.setModel(currencyAttributeData);
		}
		return currencyComboBox;
	}

	private JComboBox<String> getPOComboBOx() {
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

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("CP");
		}
		return jLabel17;
	}

	private JTextField getNettingID() {
		if (nettingID == null) {
			nettingID = new JTextField();
			nettingID.setText("");
		}
		return nettingID;
	}

	private JTextField getTradeId() {
		if (tradeId == null) {
			tradeId = new JTextField();
			
		}
		return tradeId;
	}
	private DateComboBox getEndDate() {
		if (EndDate == null) {
			EndDate = new DateComboBox();
			EndDate.setFormat(commonUTIL.getDateFormat());
			EndDate.setDate(null);
		}
		return EndDate;
	}
	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Action");
		}
		return jLabel15;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("SettleType");
		}
		return jLabel14;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("NettingID");
		}
		return jLabel13;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("EventType");
		}
		return jLabel12;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("NettingType");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("LedgerType");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Status");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("SettleCCY");
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
			StartDate.setFormat(commonUTIL.getDateFormat());
			StartDate.setDate(null);
		}
		return StartDate;
	}

	private JTextField getTransferId() {
		if (TransferId == null) {
			TransferId = new JTextField();
		}
		return TransferId;
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
			jLabel0.setText("LedgerID");
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
		
		if(!commonUTIL.isEmpty(tradeId.getText())) {			
			filterBeans.add(getOtherId(tradeId.getText()));
		} 
		
		if(!commonUTIL.isEmpty(TransferId.getText())) {			
			filterBeans.add(getTransferId(TransferId.getText()));
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
		
		if( statusComboBox.getSelectedItem() != null &&  !commonUTIL.isEmpty(statusComboBox.getSelectedItem().toString())) {			
			filterBeans.add(getStatus(statusComboBox.getSelectedItem().toString()));
		} 
		
		if( actionComboBox.getSelectedItem() != null && !commonUTIL.isEmpty(actionComboBox.getSelectedItem().toString())) {
			filterBeans.add(getAction(actionComboBox.getSelectedItem().toString()));
		} 
		
		if( transferTypeComboBox.getSelectedItem() != null && !commonUTIL.isEmpty(transferTypeComboBox.getSelectedItem().toString())) {			
			filterBeans.add(getTransferType(transferTypeComboBox.getSelectedItem().toString()));
		}
		
		if( eventTypeComboBox.getSelectedItem() != null && !commonUTIL.isEmpty(eventTypeComboBox.getSelectedItem().toString())) {
			filterBeans.add(getTransferEventType(eventTypeComboBox.getSelectedItem().toString()));
		}
		
		/*if(bookComboBox.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(bookComboBox.getSelectedItem().toString()))) {
			filterBeans.add(getBookName(bookComboBox.getSelectedIndex()));			
		} */
		if( (bookComboBox.getSelectedIndex() > 0)) {
			filterBeans.add(getBookName(bookComboBox));			
		} 
		
		if(methodComboBox.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(methodComboBox.getSelectedItem().toString()))) {
			filterBeans.add(getTransferMethodType(methodComboBox.getSelectedItem().toString()));			
		} 
		
		return filterBeans;
	
	}

	@Override
	public void clearllCriterial() {
		
		tradeId.setText(CommonConstants.BLANKSTRING);
		TransferId.setText(CommonConstants.BLANKSTRING);
		StartDate.setDate(null);
		EndDate.setDate(null);
		leComboBox.setSelectedIndex(-1);
		poComboBOx.setSelectedIndex(-1);
		productTypeComboBox.setSelectedIndex(-1);
		statusComboBox.setSelectedIndex(-1);
		actionComboBox.setSelectedIndex(-1);
		eventTypeComboBox.setSelectedIndex(-1);
		transferTypeComboBox.setSelectedIndex(-1);
		nettingTypeComboBox.setSelectedIndex(-1);
		nettingID.setText(CommonConstants.BLANKSTRING);
		settlementTypeComboBox.setSelectedIndex(-1);
		currencyComboBox.setSelectedIndex(-1);
		bookComboBox.setSelectedIndex(-1);
		methodComboBox.setSelectedIndex(-1);
		// method and prodSubType left 
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		
		for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			
			if(bean.getColumnName().equalsIgnoreCase("otherTradeID")) {
				tradeId.setText(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("TransferId")) {
				TransferId.setText(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("TransferDate")) {
				
				if (bean.getCriteria().equals("between")) {
					
					StartDate.setDate(commonUTIL
							.convertStringtoSQLDate(bean.getValues()));
					
					EndDate.setDate(commonUTIL
							.convertStringtoSQLDate(bean.getAnd_or()));
					
				} else if (bean.getCriteria().equals("equal")) {
					
					StartDate.setDate(commonUTIL
							.convertStringtoSQLDate(bean.getValues()));
					
					EndDate.setDate(null);
				}				
			}
			else if(bean.getColumnName().equalsIgnoreCase("cpid")) {
				leComboBox.setSelectedIndex(getCPtoSelected(Integer.parseInt(bean.getValues())));
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("poId")) {
				poComboBOx.setSelectedIndex(getPOtoSelected(Integer.parseInt(bean.getValues())));
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("ProductType")) {
				productTypeComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("ProductSubType")) {
				//ProductSubType.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("Status")) {
				statusComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("Action")) {
				actionComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("TransferType")) {
				transferTypeComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("TransferEventType")) {
				eventTypeComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("TransferNettingType")) {
				nettingTypeComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("TransferNettingType")) {
				nettingTypeComboBox.setSelectedItem(bean.getValues());
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("Book")) {
				bookComboBox.setSelectedObjects(getMultipleValuesSelected(bean.getValues()));
			}
			
			else if(bean.getColumnName().equalsIgnoreCase("TransferMethodType")) {
				methodComboBox.setSelectedItem(bean.getValues());
			}
			
		}
		
	}

}
