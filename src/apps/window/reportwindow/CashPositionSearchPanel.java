package apps.window.reportwindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Hashtable;
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
import com.jidesoft.combobox.MultiSelectListExComboBox;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CashPositionSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JComboBox<String> currency;
	private JComboBox<String> book;
	private JComboBox<String> counterParty;
	private JComboBox<String> primaryCurr;
	private JComboBox<String> quotingCurr;
	private JComboBox<String> BUYSELL;
	Hashtable<String,String> haslegalEntityattributes = new Hashtable<String,String>();
	
	/**
	 * @return the haslegalEntityattributes
	 */
	public Hashtable<String, String> getHaslegalEntityattributes() {
		return haslegalEntityattributes;
	}

	/**
	 * @param haslegalEntityattributes the haslegalEntityattributes to set
	 */
	public void setHaslegalEntityattributes(
			Hashtable<String, String> haslegalEntityattributes) {
		this.haslegalEntityattributes = haslegalEntityattributes;
	}

	/**
	 * @return the hasbookEntityattributes
	 */
	public Hashtable<String, String> getHasbookEntityattributes() {
		return hasbookEntityattributes;
	}

	/**
	 * @param hasbookEntityattributes the hasbookEntityattributes to set
	 */
	public void setHasbookEntityattributes(
			Hashtable<String, String> hasbookEntityattributes) {
		this.hasbookEntityattributes = hasbookEntityattributes;
	}

	/**
	 * @return the hastradeEntityattributes
	 */
	public Hashtable<String, String> getHastradeEntityattributes() {
		return hastradeEntityattributes;
	}

	/**
	 * @param hastradeEntityattributes the hastradeEntityattributes to set
	 */
	public void setHastradeEntityattributes(
			Hashtable<String, String> hastradeEntityattributes) {
		this.hastradeEntityattributes = hastradeEntityattributes;
	}

	Hashtable<String,String> hasbookEntityattributes = new Hashtable<String,String>();
	Hashtable<String,String> hastradeEntityattributes = new Hashtable<String,String>();
	javax.swing.DefaultComboBoxModel<String> bookData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> legalEntityData = new javax.swing.DefaultComboBoxModel<String>();

	javax.swing.DefaultComboBoxModel<String> currencyAttributeData = new javax.swing.DefaultComboBoxModel<String>();

	javax.swing.DefaultComboBoxModel<String> productTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> productSubTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> primarryCurrAttributeData = new javax.swing.DefaultComboBoxModel<String>();

	javax.swing.DefaultComboBoxModel<String> quotingCurrAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> bookAttributesData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> legalEntityAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> tradeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	
	
	/*Hashtable<Integer,beans.Book> books = new Hashtable<Integer,beans.Book>();
	Hashtable<Integer,beans.LegalEntity> counterPartyID = new Hashtable<Integer,beans.LegalEntity>();*/
	private JComboBox<String> leattributesData;
	private JTextField leAttribute;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JComboBox<String> bookAttributessData;
	private JTextField bookAttribute;
	private JLabel jLabel8;
	private JComboBox<String> tradeAttributesData;
	private JTextField tradeAttribute;
	private JLabel jLabel9;
	private DateComboBox fromDate;
	private JLabel jLabel10;
	private DateComboBox toDate;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JComboBox<String> producttype;
	private MultiSelectListExComboBox productSubType;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public CashPositionSearchPanel() {
		processBookDataCombo1(bookData, books);
		processLEDataCombo1(legalEntityData,  counterPartyID);
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(primarryCurrAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(quotingCurrAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(bookAttributesData,  getFilterValues().getDomainValues("BookAttributes"));
		processDomainData(legalEntityAttributeData,  getFilterValues().getDomainValues("LEAttributes"));
		processDomainData(tradeAttributeData,  getFilterValues().getDomainValues("TradeAttribute"));
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));		
		processDomainData(productSubTypeAttributeData,getFilterValues().getDomainValues("FX"+".subType"));
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
		add(getJLabel0(), new Constraints(new Leading(18, 10, 10), new Leading(22, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(18, 10, 10), new Leading(68, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(18, 12, 12), new Leading(201, 15, 10, 10)));
		add(getJLabel7(), new Constraints(new Leading(18, 12, 12), new Leading(112, 12, 12)));
		add(getBookAttributessData(), new Constraints(new Leading(145, 162, 12, 12), new Leading(98, 28, 12, 12)));
		add(getCurrency(), new Constraints(new Leading(145, 100, 12, 12), new Leading(12, 24, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(18, 12, 12), new Leading(158, 10, 10)));
		add(getLeattributesData(), new Constraints(new Leading(145, 162, 12, 12), new Leading(188, 28, 10, 10)));
		add(getJLabel8(), new Constraints(new Leading(18, 12, 12), new Leading(246, 10, 10)));
		add(getTradeAttributesData(), new Constraints(new Leading(147, 162, 10, 10), new Leading(232, 28, 10, 10)));
		add(getJLabel9(), new Constraints(new Leading(18, 12, 12), new Leading(291, 10, 10)));
		add(getFromDate(), new Constraints(new Leading(145, 154, 12, 12), new Leading(278, 28, 12, 12)));
		add(getToDate(), new Constraints(new Bilateral(339, 13, 9), new Leading(278, 28, 12, 12)));
		add(getTradeAttribute(), new Constraints(new Bilateral(339, 11, 6), new Leading(232, 28, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(312, 28, 104), new Leading(291, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(18, 103, 12, 12), new Leading(339, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(18, 10, 10), new Leading(383, 12, 12)));
		add(getBUYSELL(), new Constraints(new Leading(145, 82, 12, 12), new Leading(417, 26, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(18, 12, 12), new Leading(429, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(18, 12, 12), new Leading(473, 12, 12)));
		add(getProducttype(), new Constraints(new Leading(143, 174, 10, 10), new Leading(461, 26, 10, 10)));
		add(getproductSubType(), new Constraints(new Leading(143, 174, 12, 12), new Leading(503, 27, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(18, 10, 10), new Leading(516, 10, 10)));
		add(getLeAttribute(), new Constraints(new Bilateral(339, 14, 6), new Leading(188, 28, 10, 10)));
		add(getBookAttribute(), new Constraints(new Bilateral(339, 14, 6), new Leading(100, 26, 12, 12)));
		add(getPrimaryCurr(), new Constraints(new Leading(145, 106, 10, 10), new Leading(329, 24, 10, 10)));
		add(getQuotingCurr(), new Constraints(new Leading(145, 106, 12, 12), new Leading(373, 24, 10, 10)));
		add(getCounterPary(), new Constraints(new Leading(145, 162, 12, 12), new Leading(146, 26, 12, 12)));
		add(getBook(), new Constraints(new Leading(145, 162, 12, 12), new Leading(54, 28, 12, 12)));
		setSize(497, 565);
	}

	private MultiSelectListExComboBox getproductSubType() {
		if (productSubType == null) {
			productSubType = new MultiSelectListExComboBox();
		}
		return productSubType;
	}

	private JComboBox<String> getProducttype() {
		if (producttype == null) {
			producttype = new JComboBox<String>();
			producttype.setModel(productTypeAttributeData);
			producttype.addItemListener( new ItemListener() {

	        	@Override
	        	public void itemStateChanged(ItemEvent e) {
	        		if(producttype.getSelectedIndex() == -1)
	        			return;
	        		String productType = producttype.getSelectedItem().toString();
	        		productSubTypeAttributeData.removeAllElements();
	        		productSubTypeAttributeData = null;
	        		productSubTypeAttributeData = new  javax.swing.DefaultComboBoxModel<String>();
	        		productSubType.removeAll();
	        		processDomainData(productSubTypeAttributeData,  getFilterValues().getDomainValues(productType+".subType"));
	        		productSubType.setModel(productSubTypeAttributeData);
	        		if(productType.equalsIgnoreCase("FX")) {
	        			primaryCurr.setEditable(true);
	        			primaryCurr.setEnabled(true);
	        			quotingCurr.setEditable(true);
	        			quotingCurr.setEnabled(true);
	        			
	        		} else {	        			
	        			primaryCurr.setEditable(false);
	        			primaryCurr.setEnabled(false);
	        			quotingCurr.setEditable(false);
	        			quotingCurr.setEnabled(false);
	        			primaryCurr.setSelectedIndex(-1);
	        			quotingCurr.setSelectedIndex(-1);
	        			
	        		}
	        		 
	        	}
			});
		}
		return producttype;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("ProductSubType");
		}
		return jLabel12;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("ProductType");
		}
		return jLabel11;
	}

	private DateComboBox getToDate() {
		if (toDate == null) {
			toDate = new DateComboBox();
			toDate.setFormat(commonUTIL.getDateFormat());
			toDate.setDate(null);
		}
		return toDate;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("To");
		}
		return jLabel10;
	}

	private DateComboBox getFromDate() {
		if (fromDate == null) {
			fromDate = new DateComboBox();
			fromDate.setFormat(commonUTIL.getDateFormat());
			fromDate.setDate(null);
		}
		return fromDate;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Trade Date");
		}
		return jLabel9;
	}

	private JTextField getTradeAttribute() {
		if (tradeAttribute == null) {
			tradeAttribute = new JTextField();
		}
		return tradeAttribute;
	}

	private JComboBox<String> getTradeAttributesData() {
		if (tradeAttributesData == null) {
			tradeAttributesData = new JComboBox<String>();
			tradeAttributesData.setModel(tradeAttributeData);
		}
		return tradeAttributesData;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Trade Attributes");
		}
		return jLabel8;
	}

	private JTextField getBookAttribute() {
		if (bookAttribute == null) {
			bookAttribute = new JTextField();
		}
		return bookAttribute;
	}

	private JComboBox<String> getBookAttributessData() {
		if (bookAttributessData == null) {
			bookAttributessData = new JComboBox<String>();
			bookAttributessData.setModel(bookAttributesData);
		}
		return bookAttributessData;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Book Attributes");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("LeAttributes");
		}
		return jLabel6;
	}

	private JTextField getLeAttribute() {
		if (leAttribute == null) {
			leAttribute = new JTextField();
		}
		return leAttribute;
	}

	private JComboBox<String> getLeattributesData() {
		if (leattributesData == null) {
			leattributesData = new JComboBox<String>();
			leattributesData.setModel(legalEntityAttributeData);
		}
		return leattributesData;
	}

	private JComboBox<String> getBUYSELL() {
		if (BUYSELL == null) {
			BUYSELL = new JComboBox<String>();
			BUYSELL.addItem("");
			BUYSELL.setSelectedIndex(0);
			BUYSELL.addItem("BUY");
			BUYSELL.addItem("SELL");
		}
		return BUYSELL;
	}


	private JComboBox<String> getQuotingCurr() {
		if (quotingCurr == null) {
			quotingCurr = new JComboBox<String>();
			quotingCurr.setModel(quotingCurrAttributeData);
		}
		return quotingCurr;
	}

	private JComboBox<String> getPrimaryCurr() {
		if (primaryCurr == null) {
			primaryCurr = new JComboBox<String>();
			primaryCurr.setModel(primarryCurrAttributeData);
		}
		return primaryCurr;
	}

	private JComboBox<String> getBook() {
		if (book == null) {
			book = new JComboBox<String>();
			book.setModel(bookData);
		}
		return book;
	}

	private JComboBox<String> getCounterPary() {
		if (counterParty == null) {
			counterParty = new JComboBox<String>();
			counterParty.setModel(legalEntityData);
		}
		return counterParty;
	}

	private JComboBox<String> getCurrency() {
		if (currency == null) {
			currency = new JComboBox<String>();
			currency.setModel(currencyAttributeData);
		}
		return currency;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Type");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Quoting Currency");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Primary Currency");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("LegalEntity");
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

	@Override
	public Vector<FilterBean> searchCriteria() {
		
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		FilterBean bean = null;
		if(currency.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(currency.getSelectedItem().toString()))) {			
			filterBeans.add(getCurrency(currency.getSelectedItem().toString(), "Currency"));	
		}
		
		if(book.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(book.getSelectedItem().toString()))) {
			filterBeans.add(getBookName(book.getSelectedIndex()));	
		}
		
		if(primaryCurr.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(primaryCurr.getSelectedItem().toString()))) {					
			filterBeans.add(getCurrency(primaryCurr.getSelectedItem().toString(), "primaryCurr"));
		}
		
		if(quotingCurr.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(quotingCurr.getSelectedItem().toString()))) {		
			filterBeans.add(getCurrency(quotingCurr.getSelectedItem().toString(), "quotingCurr"));		
		}
		
		if(counterParty.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(counterParty.getSelectedItem().toString()))) {	
			filterBeans.add(getLegalEntity(counterParty.getSelectedIndex(), "cpid"));			
		} 
		
		if(BUYSELL.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(BUYSELL.getSelectedItem().toString()))) {
			filterBeans.add(getBUYSELL(BUYSELL.getSelectedItem().toString()));			
		} 
		
		if((producttype.getSelectedIndex() > 0)  ) {	
			filterBeans.add(getProductType(producttype.getSelectedItem().toString()));		
		}
		
		if( (productSubType.getSelectedIndex() > 0) ) {			
			filterBeans.add(getProductSubType(productSubType,"ProductSubType"));
		} 
		
		if((counterParty.getSelectedIndex() > 0)) {		
			filterBeans.add(getLegalEntity(counterParty.getSelectedIndex(), "cpid"));			
		} 
		
		if(!commonUTIL.isEmpty(tradeAttribute.getText())) {
			bean = new FilterBean();
			if(tradeAttributesData.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(tradeAttributesData.getSelectedItem().toString()))) {
				bean.setColumnName("TradeKeyword");
				bean.setColumnValues("tradeattribute.attributename = '"+tradeAttributesData.getSelectedItem().toString()+"' and tradeattribute.attributevalue = '"+tradeAttribute.getText() +"'");
				bean.setAnd_or("And");
				bean.setSearchCriteria("in");
				filterBeans.add(bean);
			}
		}
		
		if(!commonUTIL.isEmpty(leAttribute.getText())) {
			bean = new FilterBean();
			if(leattributesData.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(leattributesData.getSelectedItem().toString()))) {
				bean.setColumnName("LeKeyword");
				bean.setColumnValues("legalentityattribute.attributename = '"+leattributesData.getSelectedItem().toString()+"' and legalentityattribute.attributevalue = '"+leAttribute.getText() +"'");
				bean.setAnd_or("And");
				bean.setSearchCriteria("in");
				filterBeans.add(bean);
			}
		} 
		
		if(!commonUTIL.isEmpty(bookAttribute.getText())) {
			bean = new FilterBean();
			if(bookAttributessData.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(bookAttributessData.getSelectedItem().toString()))) {
				bean.setColumnName("BookKeyword");
				bean.setColumnValues("bookattribute.attributename = '"+bookAttributessData.getSelectedItem().toString()+"' and bookattribute.attributevalue = '"+bookAttribute.getText() +"'");
				bean.setAnd_or("And");
				bean.setSearchCriteria("in");
				filterBeans.add(bean);
			}
		} 
		
		if( fromDate.getDate() != null ) {			
			Date tradeDt = fromDate.getDate();
			if(toDate.getDate() != null) {
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
													commonUTIL.convertDateTOString(toDate.getDate()), "TradeDate"));
			} else {
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
						commonUTIL.convertDateTOString(fromDate.getDate()), "TradeDate"));		
			}			
		}
		
		return filterBeans;
	}

	@Override
	public void clearllCriterial() {
		
		currency.setSelectedIndex(-1);
		BUYSELL.setSelectedIndex(-1);
		primaryCurr.setSelectedIndex(-1);
		quotingCurr.setSelectedIndex(-1);
		book.setSelectedIndex(-1);
		counterParty.setSelectedIndex(-1);
		fromDate.setDate(null);
		toDate.setDate(null);
		producttype.setSelectedIndex(-1);
		productSubType.setSelectedIndex(-1);
		bookAttributessData.setSelectedIndex(-1);
		tradeAttributesData.setSelectedIndex(-1);
		bookAttribute.setText("");
		leAttribute.setText("");
		tradeAttribute.setText("");
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		
		for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			if(bean.getColumnName().equalsIgnoreCase("Type")) {
				BUYSELL.setSelectedItem(bean.getValues());
			}
			
			if(bean.getColumnName().equalsIgnoreCase("Currency")) {
				currency.setSelectedItem(bean.getValues());
			}
			
			if(bean.getColumnName().equalsIgnoreCase("BookID")) {
				book.setSelectedIndex(getBooktoSelected(Integer.parseInt(bean.getValues())));
			}
			
			if(bean.getColumnName().equalsIgnoreCase("cpid")) {
				counterParty.setSelectedIndex(getCPtoSelected(Integer.parseInt(bean.getValues())));
			}
			
			if(bean.getColumnName().equalsIgnoreCase("primaryCurr")) {
				primaryCurr.setSelectedItem(bean.getValues());
			}
			
			if(bean.getColumnName().equalsIgnoreCase("quotingCurr")) {
				quotingCurr.setSelectedItem(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("TradeDate")) {
				fromDate.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
				fromDate.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getAnd_or()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("TradeDateTo")) {
				toDate.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			} 
			
			if(bean.getColumnName().equalsIgnoreCase("ProductType")) {
				producttype.setSelectedItem(bean.getValues());
			}
			
			if(bean.getColumnName().equalsIgnoreCase("ProductSubType")) {
				productSubType.setSelectedItem(bean.getValues());
			}

			if(bean.getColumnName().equalsIgnoreCase("TradeKeyword")) {
				
				String tradeAttName = "tradeattribute.attributename = '";
				String tradeAttValue = "' and tradeattribute.attributevalue = '";
				String tradeAttributeNames = bean.getValues().substring(bean.getValues().indexOf("tradeattribute.attributename = '")+tradeAttName.length(),bean.getValues().indexOf("' and tradeattribute.attributevalue = '"));
				String tradeAttributeValue = bean.getValues().substring(bean.getValues().indexOf("' and tradeattribute.attributevalue = '")+tradeAttValue.length(),bean.getValues().length()-1);
				tradeAttributesData.setSelectedItem(tradeAttributeNames);
				tradeAttribute.setText(tradeAttributeValue);
			}
			
			if(bean.getColumnName().equalsIgnoreCase("LeKeyword")) {
				String leAttName = "legalentityattribute.attributename = '";
				String leAttValue = "' and legalentityattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				leattributesData.setSelectedItem(leAttributeNames);
				leAttribute.setText(leAttributeValue);
			}
			
			if(bean.getColumnName().equalsIgnoreCase("BookKeyword")) {
				String leAttName = "bookattribute.attributename = '";
				String leAttValue = "' and bookattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				bookAttributessData.setSelectedItem(leAttributeNames);
				bookAttribute.setText(leAttributeValue);
			}
		}
		
	}

}
