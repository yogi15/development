package apps.window.reportwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import apps.window.util.windowUtil.attributeUtil;
import beans.Attribute;
import beans.FilterBean;
import beans.UserJobsDetails;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.MultiSelectListExComboBox;

//VS4E -- DO NOT REMOVE THIS LINE!
public class TradeSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField TradeID;
	private DateComboBox TradeDateFrom;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JComboBox<String> TradeKeyWordName;
	private JLabel jLabel2;
	private JTextField jTextField4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel4;
	private DateComboBox TradeDateTo;
	private DateComboBox MaturityDateFrom;
	private JTextField TradeKeyWordValue;
	private JLabel jLabel7;
	private DateComboBox SettlementDateFrom;
	private DateComboBox SettlementDateTo;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private MultiSelectListExComboBox BookName;
	private JLabel jLabel10;
	private JComboBox BookAttributeName;
	private JTextField BookAttributeValue;
	private JLabel jLabel11;
	private JComboBox LegalEntityName;
	private JLabel jLabel12;
	private JComboBox LeAttributes;
	private DateComboBox MaturityDateTo;
	private JTextField LegalEntity;
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
	
	javax.swing.DefaultComboBoxModel bookData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel legalEntityData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel bookAttributesData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel legalEntityAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel tradeAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel currencyAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel actionAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel statusAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel productTypeAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel productSubTypeAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel primarycurrencyAttributeData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel quotingcurrencyAttributeData = new javax.swing.DefaultComboBoxModel();
	
	
	private JTextField LeAttributesValues;
	private JLabel jLabel13;
	//private MultiSelectListExComboBox Currency;
	private JLabel jLabel14;
	private MultiSelectListExComboBox Status;
	private MultiSelectListExComboBox Currency;
	private JLabel jLabel15;
	private JComboBox Action;
	private JLabel jLabel16;
	private JComboBox ProductType;
	private JLabel jLabel17;
	private JComboBox ProductSubType;
	private JLabel jLabel18;
	private JComboBox CurrencyPair;
	private JLabel jLabel19;
	private JComboBox BUYSELL;
	private JLabel jLabel20;
	private JTextField ProductId;
	private JLabel jLabel21;
	private JComboBox primaryCurr;
	private JLabel jLabel22;
	private JComboBox quotingCurr;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TradeSearchPanel() {
		init();
		initComponents();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		processBookDataCombo1(bookData, books);
		//processLEDataCombo1(legalEntityData,  counterPartyID, "CounterParty");
		processDomainData(bookAttributesData,  getFilterValues().getDomainValues("BookAttributes"));
		processDomainData(legalEntityAttributeData,  getFilterValues().getDomainValues("LEAttributes"));
		processDomainData(tradeAttributeData,  getFilterValues().getDomainValues("TradeAttribute"));
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(primarycurrencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(quotingcurrencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processDomainData(statusAttributeData,  getFilterValues().getDomainValues("Status"));
		processDomainData(actionAttributeData,  getFilterValues().getDomainValues("Action"));
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));
	
	}
	  final  attributeUtil attUtilBook = new attributeUtil();
	  final  attributeUtil attUtiltrade = new attributeUtil();// new attributeUtil(getFilterValues().getDomainValues("TradeAttribute"), hastradeEntityattributes);
	  final  attributeUtil attUtille = new attributeUtil();

	  
	private void initComponents() {
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setFocusable(true);
		setEnabled(true);
		setVisible(true);
		setVerifyInputWhenFocusTarget(true);
		setDoubleBuffered(true);
		setRequestFocusEnabled(true);
		setOpaque(true);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(10, 59, 12, 12), new Leading(14, 10, 10)));
		add(getTradeID(), new Constraints(new Leading(100, 95, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(10, 10, 10), new Leading(43, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(45, 24, 10, 10), new Leading(83, -33, 10, 10)));
		add(getJLabel5(), new Constraints(new Leading(10, 81, 12, 12), new Leading(78, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(10, 84, 12, 12), new Leading(145, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(10, 81, 12, 12), new Leading(113, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(10, 84, 12, 12), new Leading(183, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(10, 84, 12, 12), new Leading(215, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(10, 77, 12, 12), new Leading(253, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(10, 76, 12, 12), new Leading(289, 10, 10)));
		add(getJLabel13(), new Constraints(new Leading(10, 76, 12, 12), new Leading(327, 12, 12)));
		add(getJLabel14(), new Constraints(new Leading(10, 76, 12, 12), new Leading(365, 12, 12)));
	//	add(getStatus(), new Constraints(new Leading(100, 94, 12, 12), new Leading(361, 12, 12)));
		add(getJLabel15(), new Constraints(new Leading(213, 39, 10, 10), new Leading(365, 12, 12)));
		add(getJLabel16(), new Constraints(new Leading(10, 76, 12, 12), new Leading(399, 12, 12)));
		add(getProductType(), new Constraints(new Leading(101, 94, 12, 12), new Leading(395, 12, 12)));
		add(getJLabel17(), new Constraints(new Leading(211, 51, 10, 10), new Leading(399, 12, 12)));
		add(getJLabel19(), new Constraints(new Leading(206, 35, 12, 12), new Leading(14, 12, 12)));
		add(getBUYSELL(), new Constraints(new Leading(245, 65, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(10, 76, 12, 12), new Leading(433, 12, 12)));
		add(getProductId(), new Constraints(new Leading(100, 95, 12, 12), new Leading(429, 12, 12)));
	//	add(getCurrency(), new Constraints(new Leading(100, 94, 12, 12), new Leading(327, 12, 12)));
		add(getJLabel21(), new Constraints(new Leading(10, 12, 12), new Leading(465, 10, 10)));
		add(getQuotingCurr(), new Constraints(new Leading(100, 94, 12, 12), new Leading(465, 12, 12)));
		add(getJLabel22(), new Constraints(new Leading(209, 65, 10, 10), new Leading(467, 12, 12)));
		add(getProductSubType(), new Constraints(new Leading(272, 108, 10, 10), new Leading(397, 12, 12)));
		add(getPrimaryCurr(), new Constraints(new Leading(272, 108, 10, 10), new Leading(465, 12, 12)));
		add(getBookAttributeName(), new Constraints(new Leading(100, 130, 12, 12), new Leading(215, 12, 12)));
		add(getLeAttributes(), new Constraints(new Leading(100, 130, 12, 12), new Leading(289, 12, 12)));
		add(getLegalEntityName(), new Constraints(new Leading(100, 130, 12, 12), new Leading(253, 12, 12)));
		add(getCurrencyPair(), new Constraints(new Leading(318, 108, 12, 12), new Leading(327, 12, 12)));
		add(getAction(), new Constraints(new Leading(318, 108, 12, 12), new Leading(363, 12, 12)));
		add(getJLabel18(), new Constraints(new Leading(209, 76, 10, 10), new Leading(329, 12, 12)));
		add(getTradeKeyWordName(), new Constraints(new Leading(100, 134, 10, 10), new Leading(146, 10, 10)));
	//	add(getBookName(), new Constraints(new Leading(100, 130, 12, 12), new Leading(180, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(239, 12, 12), new Leading(78, 10, 10)));
		add(getJLabel4(), new Constraints(new Leading(239, 12, 12), new Leading(43, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(240, 28, 10, 10), new Leading(113, 12, 12)));
		add(getTradeDateTo(), new Constraints(new Leading(270, 143, 10, 10), new Leading(43, 12, 12)));
		add(getMaturityDateTo(), new Constraints(new Leading(270, 142, 10, 10), new Leading(78, 12, 12)));
		add(getSettlementDateTo(), new Constraints(new Leading(270, 142, 10, 10), new Leading(113, 12, 12)));
		add(getSettlementDateFrom(), new Constraints(new Leading(100, 122, 12, 12), new Leading(113, 12, 12)));
		add(getMaturityDateFrom(), new Constraints(new Leading(100, 122, 12, 12), new Leading(78, 12, 12)));
		add(getTradeDateFrom(), new Constraints(new Leading(100, 122, 12, 12), new Leading(43, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(434, 42, 10, 10), new Leading(142, 12, 12)));
		add(getTradeKeyWordValue(), new Constraints(new Leading(270, 144, 12, 12), new Leading(145, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(434, 38, 12, 12), new Leading(214, 22, 12, 12)));
		add(getBookAttributeValue(), new Constraints(new Leading(270, 138, 12, 12), new Leading(214, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(434, 38, 10, 10), new Leading(281, 12, 12)));
		add(getLeAttributesValues(), new Constraints(new Leading(270, 138, 12, 12), new Leading(284, 12, 12)));
		setSize(503, 519);
		// new attributeUtil(getFilterValues().getDomainValues("LEAttributes"), haslegalEntityattributes);
			attUtilBook.setLocationRelativeTo(this);
			attUtiltrade.setLocationRelativeTo(this);
			attUtille.setLocationRelativeTo(this);
			attUtilBook.jButton0.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					for(int i=0;i<attUtilBook.jTable0.getRowCount();i++) {
						String attName =(String) attUtilBook.jTable0.getValueAt(i, 0);
						String value =(String) attUtilBook.jTable0.getValueAt(i, 1);
						 if(!commonUTIL.isEmpty(value)) {
							 hasbookEntityattributes.put(attName, value);
							 
						 }
						
					}
					attUtilBook.dispose();
				}
			});
			attUtille.jButton0.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					for(int i=0;i<attUtille.jTable0.getRowCount();i++) {
						String attName =(String) attUtille.jTable0.getValueAt(i, 0);
						String value =(String) attUtille.jTable0.getValueAt(i, 1);
						 if(!commonUTIL.isEmpty(value)) {
							 haslegalEntityattributes.put(attName, value);
							 
						 }
						
					}
					attUtille.dispose();
				}
			});
			attUtiltrade.jButton0.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					for(int i=0;i<attUtiltrade.jTable0.getRowCount();i++) {
						String attName =(String) attUtiltrade.jTable0.getValueAt(i, 0);
						String value =(String) attUtiltrade.jTable0.getValueAt(i,1);
						 if(!commonUTIL.isEmpty(value)) {
							 hastradeEntityattributes.put(attName, value);
							 
						 }
						
					}
					attUtiltrade.dispose();
				}
			});

	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("jButton2");
			jButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				//	attUtiltrade.fillAttribute(getFilterValues().getDomainValues("TradeAttribute"), hastradeEntityattributes);
				//	attUtiltrade.setVisible(true);
		        	 
		             
		          }
		      });
		}
		return jButton2;
	}
 
    private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
			jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	attUtille.fillAttribute(getFilterValues().getDomainValues("LEAttributes"), haslegalEntityattributes);
			//	attUtille.setVisible(true);
				 
	             
	          }
	      });

		}
		
		return jButton1;
	}
    
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0"); 
			
			jButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//attUtilBook.fillAttribute(getFilterValues().getDomainValues("BookAttributes"), hasbookEntityattributes);
			//	attUtilBook.setVisible(true);
	        
	             
	          }
	      });
		}
		return jButton0;
	}

	private JComboBox<String> getPrimaryCurr(){
		
		if(primaryCurr==null){
		
			primaryCurr = new JComboBox<String>();
			primaryCurr.setModel(primarycurrencyAttributeData);

		}
		
		return primaryCurr;
	}

	private JLabel getJLabel22() {
		if (jLabel22 == null) {
			jLabel22 = new JLabel();
			jLabel22.setText("QuotingCurr");
		}
		return jLabel22;
	}
	
	private JComboBox<String> getQuotingCurr(){
		
		if(quotingCurr==null){
			quotingCurr = new JComboBox<String>();
			quotingCurr.setModel(quotingcurrencyAttributeData);

		
		}
			
		return quotingCurr;
		
	}


	private JLabel getJLabel21() {
		if (jLabel21 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("PrimaryCurr");
		}
		return jLabel21;
	}

	private JTextField getProductId() {
		if (ProductId == null) {
			ProductId = new JTextField();
		}
		return ProductId;
	}

	private JLabel getJLabel20() {
		if (jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("ProductID");
		}
		return jLabel20;
	}

	private JComboBox getBUYSELL() {
		if (BUYSELL == null) {
			BUYSELL = new JComboBox();
			BUYSELL.addItem("");
			BUYSELL.setSelectedIndex(0);
			BUYSELL.addItem("BUY");
			BUYSELL.addItem("SELL");
		}
		return BUYSELL;
	}

	private JLabel getJLabel19() {
		if (jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("Type");
		}
		return jLabel19;
	}

	private JComboBox getCurrencyPair() {
		if (CurrencyPair == null) {
			CurrencyPair = new JComboBox();
			
		}
		return CurrencyPair;
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("CurrPair");
		}
		return jLabel18;
	}

	private JComboBox getProductSubType() {
		if (ProductSubType == null) {
			ProductSubType = new JComboBox();
			ProductSubType.addItem("");
			ProductSubType.setSelectedIndex(0);
			ProductSubType.setModel(productTypeAttributeData);
		}
		return ProductSubType;
	}

	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("SubType");
		}
		return jLabel17;
	}

	private JComboBox getProductType() {
		if (ProductType == null) {
			ProductType = new JComboBox();
			ProductType.addItem("");
			ProductType.setSelectedIndex(0);
			ProductType.setModel(productTypeAttributeData);
			ProductType.addItemListener( new ItemListener() {

	        	@Override
	        	public void itemStateChanged(ItemEvent e) {
	        		if(ProductType.getSelectedIndex() == -1)
	        			return;
	        		String productType = ProductType.getSelectedItem().toString();
	        		productSubTypeAttributeData.removeAllElements();
	        		productSubTypeAttributeData = null;
	        		productSubTypeAttributeData = new  javax.swing.DefaultComboBoxModel();
	        		ProductSubType.removeAll();
	        		processDomainData(productSubTypeAttributeData,  getFilterValues().getDomainValues(productType+".subType"));
	        		ProductSubType.setModel(productSubTypeAttributeData);
	        		if(productType.equalsIgnoreCase("FX")) {
	        			primaryCurr.setEditable(true);
	        			primaryCurr.setEnabled(true);
	        			quotingCurr.setEditable(true);
	        			quotingCurr.setEnabled(true);
	        			
	        		}
	        		else {
	        			
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
		return ProductType;
	}

	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("ProductType");
		}
		return jLabel16;
	}

	private JComboBox getAction() {
		if (Action == null) {
			Action = new JComboBox();
			Action.setModel(actionAttributeData);
		}
		return Action;
	}

	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Action");
		}
		return jLabel15;
	}

	private MultiSelectListExComboBox getStatus() {
		if (Status == null) {
			Status = new MultiSelectListExComboBox();
			//Status.setModel(statusAttributeData);
		}
		return Status;
	}

	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Status");
		}
		return jLabel14;
	}

	private MultiSelectListExComboBox getCurrency() {
		if (Currency == null) {
			//Currency = new JComboBox();
		//	Currency.setModel(currencyAttributeData);
			Currency = new MultiSelectListExComboBox();
			//Currency.setModel(currencyAttributeData);
		}
		return Currency;
	} 

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("Currency");
		}
		return jLabel13;
	}

	private JTextField getLeAttributesValues() {
		if (LeAttributesValues == null) {
			LeAttributesValues = new JTextField();
			LeAttributesValues.setText("       ");
		/*	LeAttributesValues.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					String attributeName = (String) legalEntityAttributeData.getSelectedItem().toString();
					String value = BookAttributeValue.getText().trim();
					LeAttributesValues.setText("");
					haslegalEntityattributes.put(attributeName, value);
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stubd
					
				}
			}); */
		}
		return LeAttributesValues;
	}

	private JTextField getLegalEntity() {
		if (LegalEntity == null) {
			LegalEntity = new JTextField();
		}
		return LegalEntity;
	}

	private JTextField getJTextField0() {
		if (LegalEntity == null) {
			LegalEntity = new JTextField();
			LegalEntity.setText("jTextField0");
		}
		return LegalEntity;
	}

	private DateComboBox getMaturityDateTo() {
		if (MaturityDateTo == null) {
			MaturityDateTo = new DateComboBox();
			MaturityDateTo.setFormat(commonUTIL.getDateFormat());
			MaturityDateTo.setDate(null);
		}
		return MaturityDateTo;
	}

	

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("LE Attributes");
		}
		return jLabel12;
	}

	private JComboBox getLegalEntityName() {
		if (LegalEntityName == null) {
			LegalEntityName = new JComboBox();
			LegalEntityName.setModel(legalEntityData);
		}
		return LegalEntityName;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Legal Entity");
		}
		return jLabel11;
	}

	private JTextField getBookAttributeValue() {
		if (BookAttributeValue == null) {
			BookAttributeValue = new JTextField();
			/*BookAttributeValue.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					String attributeName = (String) bookAttributesData.getSelectedItem().toString();
					String value = BookAttributeValue.getText().trim();
					BookAttributeValue.setText("");
					hasbookEntityattributes.put(attributeName, value);
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stubd
					
				}
			}); */
		}
		return BookAttributeValue;
	}

	

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Book Attributes");
		}
		return jLabel10;
	}

	private MultiSelectListExComboBox getBookName() {
		if (BookName == null) {
			BookName = new MultiSelectListExComboBox();
			BookName.setModel(bookData);
		}
		return BookName;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Book");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("TO");
		}
		return jLabel8;
	}

	private DateComboBox getSettlementDateTo() {
		if (SettlementDateTo == null) {
			SettlementDateTo = new DateComboBox();
			SettlementDateTo.setFormat(commonUTIL.getDateFormat());
			SettlementDateTo.setDate(null);
		}
		return SettlementDateTo;
	}

	private DateComboBox getSettlementDateFrom() {
		if (SettlementDateFrom == null) {
			SettlementDateFrom = new DateComboBox();
			SettlementDateFrom.setFormat(commonUTIL.getDateFormat());
			SettlementDateFrom.setDate(null);
		}
		return SettlementDateFrom;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Settlement Date");
		}
		return jLabel7;
	}

	private JTextField getTradeKeyWordValue() {
		if (TradeKeyWordValue == null) {
			TradeKeyWordValue = new JTextField();
        /*   TradeKeyWordValue.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					String attributeName = (String) TradeKeyWordName.getSelectedItem().toString();
					String value = TradeKeyWordValue.getText().trim();
					TradeKeyWordValue.setText("");
					hastradeEntityattributes.put(attributeName, value);
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stubd
					
				}
			}); */
		}
		return TradeKeyWordValue;
	}

	private DateComboBox getMaturityDateFrom() {
		if (MaturityDateFrom == null) {
			MaturityDateFrom = new DateComboBox();
			MaturityDateFrom.setFormat(commonUTIL.getDateFormat());
			MaturityDateFrom.setDate(null);
			
		}
		return MaturityDateFrom;
	}

	private DateComboBox getTradeDateTo() {
		if (TradeDateTo == null) {
			TradeDateTo = new DateComboBox();
			TradeDateTo.setFormat(commonUTIL.getDateFormat());
			TradeDateTo.setDate(null);
		}
		return TradeDateTo;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("TO");
		}
		return jLabel4;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("TO");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Maturity Date");
		}
		return jLabel5;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
		}
		return jTextField4;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Trade keyword");
		}
		return jLabel2;
	}

	private JComboBox getTradeKeyWordName() {
		if (TradeKeyWordName == null) {
			TradeKeyWordName = new JComboBox<String>();
			TradeKeyWordName.setModel(tradeAttributeData);
			TradeKeyWordName.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					if(TradeKeyWordName.getSelectedIndex() != -1) {
						String attributeName = (String) TradeKeyWordName.getSelectedItem().toString();
						
						//String value = hastradeEntityattributes.get(attributeName);
						//TradeKeyWordValue.setText(value);
						//TradeKeyWordValue.setText(value);
					}
					
				}
				
			});
			
			
		}
		return TradeKeyWordName;
	}
	private JComboBox getLeAttributes() {
		if (LeAttributes == null) {
			LeAttributes = new JComboBox();
			LeAttributes.setModel(legalEntityAttributeData);
			LeAttributes.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					if(LeAttributes.getSelectedIndex() != -1) {
                              String attributeName = (String) LeAttributes.getSelectedItem().toString();
						
						//String value = haslegalEntityattributes.get(attributeName);
						//LeAttributesValues.setText(value);
						
						
					}
					
				}
			});
		}
		return LeAttributes;
	}
	private JComboBox getBookAttributeName() {
		if (BookAttributeName == null) {
			BookAttributeName = new JComboBox();
			BookAttributeName.setModel(bookAttributesData);
			BookAttributeName.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					if(BookAttributeName.getSelectedIndex() != -1) {
						String attributeName = (String) BookAttributeName.getSelectedItem().toString();
						
					//	String value = hasbookEntityattributes.get(attributeName);
					//	BookAttributeValue.setText(value);
					}
					
				}
			});
		}
		return BookAttributeName;
	}
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Trade Date");
		}
		return jLabel1;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Trade Date");
		}
		return jLabel3;
	}

	private DateComboBox getTradeDateFrom() {
		if (TradeDateFrom == null) {
			
			TradeDateFrom = new  com.jidesoft.combobox.DateComboBox();
			//TradeDateFrom.setTimeDisplayed(true);
			TradeDateFrom.setFormat(commonUTIL.getDateFormat());
			TradeDateFrom.setDate(null);
			
		}
		
		return TradeDateFrom;
	}

	private JTextField getTradeID() {
		if (TradeID == null) {
			TradeID = new JTextField();
		}
		return TradeID;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Trade ID");
		}
		return jLabel0;
	}

	
	public Vector<FilterBean>  searchCriteria() {
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		FilterBean bean = null;
		if(!commonUTIL.isEmpty(TradeID.getText())) {
			
			filterBeans.add(getTradeID(TradeID.getText()));
		} 
		if(!commonUTIL.isEmpty(TradeKeyWordValue.getText())) {
			bean = new FilterBean();
			if(TradeKeyWordName.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(TradeKeyWordName.getSelectedItem().toString()))) {
			bean.setColumnName("TradeKeyword");
			bean.setColumnValues("tradeattribute.attributename = '"+TradeKeyWordName.getSelectedItem().toString()+"' and tradeattribute.attributevalue = '"+TradeKeyWordValue.getText() +"'");
			bean.setAnd_or("And");
			bean.setSearchCriteria("in");
			filterBeans.add(bean);
			}
		}		
		if(!commonUTIL.isEmpty(LeAttributesValues.getText())) {
			bean = new FilterBean();
			if(LeAttributes.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(LeAttributes.getSelectedItem().toString()))) {
			bean.setColumnName("LeKeyword");
			bean.setColumnValues("legalentityattribute.attributename = '"+LeAttributes.getSelectedItem().toString()+"' and legalentityattribute.attributevalue = '"+LeAttributesValues.getText() +"'");
			bean.setAnd_or("And");
			bean.setSearchCriteria("in");
			filterBeans.add(bean);
			}
		} 
		if(!commonUTIL.isEmpty(BookAttributeValue.getText())) {
			bean = new FilterBean();
			if(BookAttributeName.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(BookAttributeName.getSelectedItem().toString()))) {
			bean.setColumnName("BookKeyword");
			bean.setColumnValues("bookattribute.attributename = '"+BookAttributeName.getSelectedItem().toString()+"' and bookattribute.attributevalue = '"+BookAttributeValue.getText() +"'");
			bean.setAnd_or("And");
			bean.setSearchCriteria("in");
			filterBeans.add(bean);
			}
		} 
		 
		if( (BUYSELL.getSelectedIndex() > 0 )) {
			filterBeans.add(getBUYSELL(BUYSELL.getSelectedItem().toString()));
					
		} 
		if( (Status.getSelectedIndex() > 0) ) {
			
			filterBeans.add(getStatus(Status));
		} 
		if( (Action.getSelectedIndex() > 0)) {
			
			filterBeans.add(getAction(Action.getSelectedItem().toString()));
		} 
		if((ProductType.getSelectedIndex() > 0)  ) {
			
			filterBeans.add(getProductType(ProductType.getSelectedItem().toString()));
		} 
		if( (ProductSubType.getSelectedIndex() > 0) ) {
			
			filterBeans.add(getProductSubType(ProductSubType.getSelectedItem().toString()));
		} 
		if( (BookName.getSelectedIndex() > 0)) {
			filterBeans.add(getBookName(BookName));
			
		} 
		
		if((LegalEntityName.getSelectedIndex() > 0)) {
			
			filterBeans.add(getLegalEntity(LegalEntityName.getSelectedIndex(), "cpid"));
			
		} 
		
		if( (Currency.getSelectedIndex() > 0) ) {
		//	int i [] = Currency.getSelectedIndices();
			filterBeans.add(getCurrency(Currency, "Currency"));
	
		} 
		if( (primaryCurr.getSelectedIndex() > 0 ) ) {
			//	int i [] = Currency.getSelectedIndices();
				filterBeans.add(getprimaryCurrency(primaryCurr.getSelectedItem().toString(), "PrimaryCurr"));
		
			} 
		if( (quotingCurr.getSelectedIndex() > 0) ) {
			//	int i [] = Currency.getSelectedIndices();
				filterBeans.add(getquotingCurrency(quotingCurr.getSelectedItem().toString(), "QuotingCurr"));
		
			} 
		
		return filterBeans;
		
	} 

	
	
	@Override
	public void clearllCriterial() {
		TradeID.setText("");
		TradeDateFrom.setDate(null);
		TradeDateTo.setDate(null);
		MaturityDateFrom.setDate(null);
		MaturityDateTo.setDate(null);
		SettlementDateFrom.setDate(null);
		SettlementDateTo.setDate(null);
		BUYSELL.setSelectedIndex(-1);
		Status.setSelectedIndex(-1);
		Action.setSelectedIndex(-1);
		//Currency.setSelectedIndex(-1);
		BUYSELL.setSelectedIndex(-1);
		ProductType.setSelectedIndex(-1);
		ProductSubType.setSelectedIndex(-1);
		BookAttributeName.setSelectedIndex(-1);
		BookName.setSelectedIndex(-1);
		LegalEntityName.setSelectedIndex(-1);
		TradeKeyWordName.setSelectedIndex(-1);
		TradeKeyWordValue.setText("");
		LeAttributes.setSelectedIndex(-1);
		LeAttributesValues.setText("");
		BookAttributeValue.setText("");
		primaryCurr.setSelectedIndex(-1);
		quotingCurr.setSelectedIndex(-1);
		//Legal
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails  ) {
	for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			
			if(bean.getColumnName().equalsIgnoreCase("TradeID")) {
				TradeID.setText(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("TradeDate")) {
				TradeDateFrom.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
				TradeDateTo.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getAnd_or()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("TradeDateTo")) {
				TradeDateTo.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("MaturityDateFrom")) {
				MaturityDateFrom.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("MaturityDateTo")) {
				MaturityDateTo.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("SettlementDateFrom")) {
				SettlementDateFrom.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			else if(bean.getColumnName().equalsIgnoreCase("SettlementDateTo")) {
				SettlementDateTo.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			if(bean.getColumnName().equalsIgnoreCase("Type")) {
				BUYSELL.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Action")) {
				Action.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Status")) {
				Status.setSelectedObjects(getMultipleValuesSelected(bean.getValues()));
			}
			if(bean.getColumnName().equalsIgnoreCase("Currency")) {
				Currency.setSelectedObjects(getMultipleValuesSelected(bean.getValues()));
			}
			if(bean.getColumnName().equalsIgnoreCase("ProductType")) {
				ProductType.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("ProductSubType")) {
				ProductSubType.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("Book")) {
				BookName.setSelectedObjects(getMultipleValuesSelected(bean.getValues()));
			}
			if(bean.getColumnName().equalsIgnoreCase("cpid")) {
				LegalEntityName.setSelectedIndex(getCPtoSelected(Integer.parseInt(bean.getValues())));
			}
			if(bean.getColumnName().equalsIgnoreCase("primaryCurr")) {
				primaryCurr.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("QuotingCurr")) {
				quotingCurr.setSelectedItem(bean.getValues());
			}
			if(bean.getColumnName().equalsIgnoreCase("TradeKeyword")) {
				
				String tradeAttName = "tradeattribute.attributename = '";
				String tradeAttValue = "' and tradeattribute.attributevalue = '";
				String tradeAttributeNames = bean.getValues().substring(bean.getValues().indexOf("tradeattribute.attributename = '")+tradeAttName.length(),bean.getValues().indexOf("' and tradeattribute.attributevalue = '"));
				String tradeAttributeValue = bean.getValues().substring(bean.getValues().indexOf("' and tradeattribute.attributevalue = '")+tradeAttValue.length(),bean.getValues().length()-1);
				TradeKeyWordName.setSelectedItem(tradeAttributeNames);
				TradeKeyWordValue.setText(tradeAttributeValue);
			}
			if(bean.getColumnName().equalsIgnoreCase("LeKeyword")) {
				String leAttName = "legalentityattribute.attributename = '";
				String leAttValue = "' and legalentityattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				LeAttributes.setSelectedItem(leAttributeNames);
				LeAttributesValues.setText(leAttributeValue);
			}
			if(bean.getColumnName().equalsIgnoreCase("BookKeyword")) {
				String leAttName = "bookattribute.attributename = '";
				String leAttValue = "' and bookattribute.attributevalue = '";
				String leAttributeNames = bean.getValues().substring(bean.getValues().indexOf(leAttName)+leAttName.length(),bean.getValues().indexOf(leAttValue));
				String leAttributeValue = bean.getValues().substring(bean.getValues().indexOf(leAttValue)+leAttValue.length(),bean.getValues().length()-1);
				BookAttributeName.setSelectedItem(leAttributeNames);
				BookAttributeValue.setText(leAttributeValue);
			}
		} 
		
	}


     
	
	
}
