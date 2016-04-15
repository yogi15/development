package apps.window.reportwindow;

import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.jidesoft.combobox.DateComboBox;

import util.commonUTIL;

import beans.FilterBean;
import beans.UserJobsDetails;

//VS4E -- DO NOT REMOVE THIS LINE!
public class PNLSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private DateComboBox TradeDateFrom;
	private JComboBox currency;
	private JComboBox ProductType;
	private JComboBox productSubType;
	private JComboBox Status;
	private JComboBox Action;
	private JComboBox book;
	private JComboBox counterParty;
	private JComboBox bookAttributes;
	private JComboBox leAttributes;
	private JTextField leAttributeName;
	private JTextField bookAttributeValue;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	javax.swing.DefaultComboBoxModel bookData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel legalEntityData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel bookAttributesData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel legalEntityAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel currencyAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel actionAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel statusAttributeData = new javax.swing.DefaultComboBoxModel();

	javax.swing.DefaultComboBoxModel productTypeAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel productSubTypeAttributeData = new javax.swing.DefaultComboBoxModel();
	
	public PNLSearchPanel() {
		processBookDataCombo1(bookData, books);
		processLEDataCombo1(legalEntityData,  counterPartyID, "CounterParty");
		processDomainData(bookAttributesData,  getFilterValues().getDomainValues("BookAttributes"));
		processDomainData(legalEntityAttributeData,  getFilterValues().getDomainValues("LEAttributes"));
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(statusAttributeData,  getFilterValues().getDomainValues("Status"));
		processDomainData(actionAttributeData,  getFilterValues().getDomainValues("Action"));
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(18, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(12, 12, 12), new Leading(50, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(12, 12, 12), new Leading(80, 10, 10)));
		add(getJLabel3(), new Constraints(new Leading(12, 12, 12), new Leading(112, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(14, 10, 10), new Leading(147, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(12, 12, 12), new Leading(179, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(12, 12, 12), new Leading(205, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(12, 12, 12), new Leading(234, 10, 10)));
		add(getJLabel9(), new Constraints(new Leading(12, 12, 12), new Leading(299, 10, 10)));
		add(getCounterParty(), new Constraints(new Leading(136, 82, 10, 10), new Leading(238, 12, 12)));
		add(getCurrency(), new Constraints(new Leading(136, 82, 12, 12), new Leading(38, 12, 12)));
		add(getProductType(), new Constraints(new Leading(136, 82, 12, 12), new Leading(70, 12, 12)));
		add(getStatus(), new Constraints(new Leading(136, 82, 12, 12), new Leading(138, 12, 12)));
		add(getProductSubType(), new Constraints(new Leading(136, 82, 12, 12), new Leading(106, 12, 12)));
		add(getAction(), new Constraints(new Leading(136, 82, 12, 12), new Leading(170, 12, 12)));
		add(getBook(), new Constraints(new Leading(136, 82, 12, 12), new Leading(202, 12, 12)));
		add(getTradeDate(), new Constraints(new Leading(136, 121, 12, 12), new Leading(9, 10, 10)));
		add(getBookAttributes(), new Constraints(new Leading(138, 78, 12, 12), new Leading(267, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(12, 12, 12), new Leading(270, 12, 12)));
		add(getLeAttributes(), new Constraints(new Leading(136, 78, 12, 12), new Leading(299, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(232, 76, 10, 10), new Leading(299, 12, 12)));
		add(getJTextField2(), new Constraints(new Leading(230, 76, 10, 10), new Leading(266, 12, 12)));
		setSize(346, 361);
	//	leAttributeName.setText("dfdfdfd");
		//bookAttributeValue.setText("2222");
	}

	private JTextField getJTextField2() {
		if (bookAttributeValue == null) {
			bookAttributeValue = new JTextField();
		}
		return bookAttributeValue;
	}

	private JTextField getJTextField1() {
		if (leAttributeName == null) {
			leAttributeName = new JTextField();
		}
		return leAttributeName;
	}

	private JComboBox getLeAttributes() {
		if (leAttributes == null) {
			leAttributes = new JComboBox();
			leAttributes.setModel(legalEntityAttributeData);
		}
		return leAttributes;
	}

	private JComboBox getBookAttributes() {
		if (bookAttributes == null) {
			bookAttributes = new JComboBox();
			bookAttributes.setModel(bookAttributesData);
		}
		return bookAttributes;
	}

	private JComboBox getCounterParty() {
		if (counterParty == null) {
			counterParty = new JComboBox();
			counterParty.setModel(legalEntityData);
		}
		return counterParty;
	}

	private JComboBox getBook() {
		if (book == null) {
			book = new JComboBox();
			book.setModel(bookData);
		}
		return book;
	}

	private JComboBox getAction() {
		if (Action == null) {
			Action = new JComboBox();
			Action.setModel(actionAttributeData);
		}
		return Action;
	}

	private JComboBox getStatus() {
		if (Status == null) {
			Status = new JComboBox();
			Status.setModel(statusAttributeData);
		}
		return Status;
	}
	
	private  com.jidesoft.combobox.DateComboBox getTradeDate() {
if (TradeDateFrom == null) {
			
			TradeDateFrom = new  com.jidesoft.combobox.DateComboBox();
			//TradeDateFrom.setTimeDisplayed(true);
			TradeDateFrom.setFormat(commonUTIL.getDateFormat());
			TradeDateFrom.setDate(null);
			
		}
		
		return TradeDateFrom;
	}

	private JComboBox getProductType() {
		if (ProductType == null) {
			ProductType = new JComboBox();
			ProductType.setModel(productTypeAttributeData);
		}
		return ProductType;
	}

	private JComboBox getProductSubType() {
		if (productSubType == null) {
			productSubType = new JComboBox();
		 productSubType.setModel(productSubTypeAttributeData);
		}
		return productSubType;
	}
	private JComboBox getCurrency() {
		if (currency == null) {
			currency = new JComboBox();
			currency.setModel(currencyAttributeData);
		}
		return currency;
	}

	private DateComboBox getJTextField0() {
if (TradeDateFrom == null) {
			
			TradeDateFrom = new  com.jidesoft.combobox.DateComboBox();
			//TradeDateFrom.setTimeDisplayed(true);
			TradeDateFrom.setFormat(commonUTIL.getDateFormat());
			TradeDateFrom.setDate(null);
			
		}
		
		return TradeDateFrom;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("LE Attributes");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Book Attributes");
		}
		return jLabel8;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("CounterPary");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Book");
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

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Status");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Product Sub Type");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Product Type");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Currency");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Trade Date");
		}
		return jLabel0;
	}

	@Override
	public Vector<FilterBean> searchCriteria() {
		// TODO Auto-generated method stub
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		FilterBean bean = null;
		if(!commonUTIL.isEmpty(leAttributeName.getText())) {
			bean = new FilterBean();
			if(leAttributes.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(leAttributes.getSelectedItem().toString()))) {
			bean.setColumnName("LeKeyword");
			bean.setColumnValues("legalentityattribute.attributename = '"+leAttributes.getSelectedItem().toString()+"' and legalentityattribute.attributevalue = '"+leAttributeName.getText() +"'");
			bean.setAnd_or("And");
			bean.setSearchCriteria("in");
			filterBeans.add(bean);
			}
		} 
		if(!commonUTIL.isEmpty(bookAttributeValue.getText())) {
			bean = new FilterBean();
			if(bookAttributes.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(bookAttributes.getSelectedItem().toString()))) {
			bean.setColumnName("BookKeyword");
			bean.setColumnValues("bookattribute.attributename = '"+bookAttributes.getSelectedItem().toString()+"' and bookattribute.attributevalue = '"+bookAttributeValue.getText() +"'");
			bean.setAnd_or("And");
			bean.setSearchCriteria("in");
			filterBeans.add(bean);
			}
		} 
		if( (TradeDateFrom.getDate() != null  
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(TradeDateFrom.getDate())))) {				
			
			Date fromTrade = TradeDateFrom.getDate();
			
			if (TradeDateFrom.getDate() != null) {
				
				Date toTradeDate = TradeDateFrom.getDate();
				
				if (toTradeDate.after(fromTrade)) {
					
					filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(fromTrade), 
							commonUTIL.convertDateTOString(toTradeDate), "TradeDate"));
				
					
				}  else {
					
					commonUTIL.showAlertMessage("Trade From Date should be less then Trade To Date");
					
				}				
			} 
		}
if(Status.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(Status.getSelectedItem().toString()))) {
			
			filterBeans.add(getStatus(Status.getSelectedItem().toString()));
		} 
		if(Action.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(Action.getSelectedItem().toString()))) {
			
			filterBeans.add(getAction(Action.getSelectedItem().toString()));
		} 
		if(ProductType.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(ProductType.getSelectedItem().toString()))) {
			
			filterBeans.add(getProductType(ProductType.getSelectedItem().toString()));
		} 
		if(productSubType.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(productSubType.getSelectedItem().toString()))) {
			
			filterBeans.add(getProductSubType(productSubType.getSelectedItem().toString()));
		} 
		if(book.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(book.getSelectedItem().toString()))) {
			filterBeans.add(getBookName(book.getSelectedIndex()));
			
		} 
		
		if(counterParty.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(counterParty.getSelectedItem().toString()))) {
			
			filterBeans.add(getLegalEntity(counterParty.getSelectedIndex(), "cpid"));
			
		} 
		
if(currency.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(currency.getSelectedItem().toString()))) {
			
			filterBeans.add(getCurrency(currency.getSelectedItem().toString(), "Currency"));
	
		}
		return filterBeans;
	}

	
	@Override
	public void clearllCriterial() {
		// TODO Auto-generated method stub
		
		TradeDateFrom.setDate(null);
		
		Status.setSelectedIndex(-1);
		Action.setSelectedIndex(-1);
		currency.setSelectedIndex(-1);
		
		ProductType.setSelectedIndex(-1);
		productSubType.setSelectedIndex(-1);
		bookAttributes.setSelectedIndex(-1);
		book.setSelectedIndex(-1);
		counterParty.setSelectedIndex(-1);
		
		leAttributes.setSelectedIndex(-1);
		
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			
			
			 if(bean.getColumnName().equalsIgnoreCase("TradeDate")) {
				TradeDateFrom.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
				
			}
			else 
			if(bean.getColumnName().equalsIgnoreCase("Action")) {
				Action.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Status")) {
				Status.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Currency")) {
				currency.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("ProductType")) {
				ProductType.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("ProductSubType")) {
				productSubType.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Book")) {
				book.setSelectedIndex(getBooktoSelected(Integer.parseInt(bean.getValues())));
			}
			if(bean.getColumnName().equalsIgnoreCase("cpid")) {
				counterParty.setSelectedIndex(getCPtoSelected(Integer.parseInt(bean.getValues())));
			}
			
			if(bean.getColumnName().equalsIgnoreCase("LeKeyword")) {
				String leAttName = "legalentityattribute.attributename = '";
				String leAttValue = "' and legalentityattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				leAttributes.setSelectedItem(leAttributeNames);
				leAttributeName.setText(leAttributeValue);
			}
			if(bean.getColumnName().equalsIgnoreCase("BookKeyword")) {
				String leAttName = "bookattribute.attributename = '";
				String leAttValue = "' and bookattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				bookAttributes.setSelectedItem(leAttributeNames);
				bookAttributeValue.setText(leAttributeValue);
			}
		}
		
		
	}

}
