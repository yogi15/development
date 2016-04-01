package apps.window.util.windowUtil;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.MultiSelectListExComboBox;

import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;

import beans.FilterBean;
import beans.UserJobsDetails;

import apps.window.reportwindow.SearchCriteriaType;

//VS4E -- DO NOT REMOVE THIS LINE!
public class openSearchPanel extends SearchCriteriaType {
	public JButton jButton0;
	private JPanel jPanel1;
	private JLabel jLabel0;
	private JComboBox<String> productType;
	private MultiSelectListExComboBox productSubType;
	private JLabel jLabel1;

	private MultiSelectListExComboBox CurrencyPair;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private DateComboBox TradeDateFrom;
	private DateComboBox TradeDateTo;
	private JLabel jLabel4;
	private static final long serialVersionUID = 1L;
	javax.swing.DefaultComboBoxModel<String> currencyPairAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> productTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> productSubTypeAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	
    JPanel searchC = null;
	public openSearchPanel(RemoteTrade remoteTrade,RemoteReferenceData remoteR, RemoteBOProcess remoteBO,RemoteTask remoteTask) {
		super(remoteTrade,remoteR,remoteBO,remoteTask);
		initComponents();
		
		processDomainData(productTypeAttributeData,  getFilterValues().getDomainValues("ProductType"));
		processCurrenyPairData(currencyPairAttributeData);
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(4, 253, 10, 10), new Leading(2, 370, 10, 10)));
		//setPanel(panel);
		setSize(320, 240);
	}
     public void setPanel(JPanel panel) {
    	this.searchC = panel;
     }
	@Override
	public Vector<FilterBean> searchCriteria() {
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		FilterBean bean = null;
		
		if((productType.getSelectedIndex() > 0)  ) {			
			filterBeans.add(getProductType(productType.getSelectedItem().toString()));
		} 
		
		if( (productSubType.getSelectedIndex() > 0) ) {			
			filterBeans.add(getProductSubType(productSubType,"ProductSubType"));
		} 
		
		if( (CurrencyPair.getSelectedIndex() > 0) ) {
			//	int i [] = Currency.getSelectedIndices();
				filterBeans.add(getCurrency(CurrencyPair, "CurrencyPair"));
		} 
		
		if( TradeDateFrom.getDate() != null ) {
			
			Date tradeDt = TradeDateFrom.getDate();
			if(TradeDateTo.getDate() != null) {
			filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
													commonUTIL.convertDateTOString(TradeDateTo.getDate()), "TradeDate"));
			} else {
				filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
						commonUTIL.convertDateTOString(TradeDateFrom.getDate()), "TradeDate"));

			}
			
		}		
		return filterBeans;
	}
	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJButton0(), new Constraints(new Leading(10, 10, 10), new Leading(327, 10, 10)));
			jPanel1.add(getJLabel0(), new Constraints(new Leading(8, 10, 10), new Leading(14, 10, 10)));
			jPanel1.add(getProductType(), new Constraints(new Leading(106, 137, 10, 10), new Leading(12, 28, 12, 12)));
			jPanel1.add(getJComboBox1(), new Constraints(new Leading(106, 137, 12, 12), new Leading(49, 28, 10, 10)));
			jPanel1.add(getJLabel1(), new Constraints(new Leading(8, 10, 10), new Leading(52, 24, 12, 12)));
			jPanel1.add(getJComboBox2(), new Constraints(new Leading(106, 137, 10, 10), new Leading(94, 28, 10, 10)));
			jPanel1.add(getJLabel2(), new Constraints(new Leading(8, 12, 12), new Leading(101, 12, 12)));
			jPanel1.add(getJLabel3(), new Constraints(new Leading(8, 12, 12), new Leading(152, 10, 10)));
			jPanel1.add(getTradeDateFrom(), new Constraints(new Bilateral(106, 12, 6), new Leading(149, 12, 12)));
			jPanel1.add(getTradeDateTo(), new Constraints(new Leading(106, 136, 12, 12), new Leading(198, 10, 10)));
			jPanel1.add(getJLabel4(), new Constraints(new Leading(150, 10, 10), new Leading(177, 12, 12)));
		}
		return jPanel1;
	}
	
	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("TO");
		}
		return jLabel4;
	}

	private DateComboBox getTradeDateTo() {
		if (TradeDateTo == null) {
			TradeDateTo = new DateComboBox();
			TradeDateTo.setFormat(commonUTIL.getDateFormat());
			TradeDateTo.setDate(null);
		}
		return TradeDateTo;
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

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Trade Date");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Currency Pair");
		}
		return jLabel2;
	}

	private MultiSelectListExComboBox getJComboBox2() {
		if (CurrencyPair == null) {
			CurrencyPair = new MultiSelectListExComboBox();
			CurrencyPair.setModel(currencyPairAttributeData);
		}
		return CurrencyPair;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Product SubType");
		}
		return jLabel1;
	}

	private MultiSelectListExComboBox getJComboBox1() {
		if (productSubType == null) {
			productSubType = new MultiSelectListExComboBox();		
		}
		return productSubType;
	}

	private JComboBox<String> getProductType() {
		if (productType == null) {
			productType = new JComboBox<String>();
			productType.setModel(productTypeAttributeData);
			productType.addItemListener( new ItemListener() {

	        	@Override
	        	public void itemStateChanged(ItemEvent e) {
	        		if(productType.getSelectedIndex() == -1)
	        			return;
	        		String productType1 = productType.getSelectedItem().toString();
	        		productSubTypeAttributeData.removeAllElements();
	        		productSubTypeAttributeData = null;
	        		productSubTypeAttributeData = new  javax.swing.DefaultComboBoxModel<String>();
	        		productSubType.removeAll();
	        		processDomainData(productSubTypeAttributeData,getFilterValues().getDomainValues(productType1+".subType"));
	        		productSubType.setModel(productSubTypeAttributeData);
	        	}
			});
		}
		return productType;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Product Type");
		}
		return jLabel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Query");
		}
		return jButton0;
	}

	@Override
	public void clearllCriterial() {		
		TradeDateFrom.setDate(null);
		TradeDateTo.setDate(null);
		productType.setSelectedIndex(-1);
		productSubType.setSelectedIndex(-1);		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {	
	}

}
