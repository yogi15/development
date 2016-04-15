package apps.window.reportwindow;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
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

//VS4E -- DO NOT REMOVE THIS LINE!
public class CashLedgerPositionSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	
	javax.swing.DefaultComboBoxModel<String> bookData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String> currencyAttributeData = new javax.swing.DefaultComboBoxModel<String>();
	javax.swing.DefaultComboBoxModel<String>  poData = new javax.swing.DefaultComboBoxModel<String> ();

	private JLabel jLabel6;
	private JTextField tradeIdTextField;
	private JLabel jLabel7;
	private DateComboBox OpenpositionDate;
	private JComboBox<String> currencyComboBox;
	private MultiSelectListExComboBox bookComboBox;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	public CashLedgerPositionSearchPanel() {
		init();
		initComponents();
	}

	private void init(){
		
		processBookDataCombo1(bookData, books);	
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		processLEDataCombo1(poData,  poID, "PO");
		processBookDataCombo1(bookData, books);
		
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
		add(getJLabel7(), new Constraints(new Leading(18, 87, 10, 10), new Leading(106, 10, 10)));
		add(gettradeIdTextField(), new Constraints(new Leading(145, 130, 10, 10), new Leading(61, 18, 10, 10)));
		add(getJLabel6(), new Constraints(new Leading(18, 74, 12, 12), new Leading(63, 12, 12)));
		add(getcurrencyComboBox(), new Constraints(new Leading(145, 130, 12, 12), new Leading(16, 12, 12)));
		add(getOpenpositionDate(), new Constraints(new Leading(145, 130, 12, 12), new Leading(100, 17, 10, 10)));
		add(getBookComboBox(), new Constraints(new Leading(145, 127, 12, 12), new Leading(138, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(18, 12, 12), new Leading(144, 12, 12)));
		setSize(486, 397);
	}


	private MultiSelectListExComboBox getBookComboBox() {
		if (bookComboBox == null) {
			bookComboBox = new MultiSelectListExComboBox();
			bookComboBox.setModel(bookData);
		}
		return bookComboBox;
	}


	private JComboBox<String> getcurrencyComboBox() {
		if (currencyComboBox == null) {
			currencyComboBox = new JComboBox<String>();
			currencyComboBox.setModel(currencyAttributeData);
		}
		return currencyComboBox;
	}


	private DateComboBox getOpenpositionDate() {
		if (OpenpositionDate == null) {
			OpenpositionDate = new DateComboBox();
			OpenpositionDate.setFormat(commonUTIL.getDateFormat());
			OpenpositionDate.setDate(null);
		}
		return OpenpositionDate;
	}


	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("OpenpositionDate");
		}
		return jLabel7;
	}


	private JTextField gettradeIdTextField() {
		if (tradeIdTextField == null) {
			tradeIdTextField = new JTextField();
		}
		return tradeIdTextField;
	}


	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("TradeId");
		}
		return jLabel6;
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
		
		if( (currencyComboBox.getSelectedIndex() > 0) ) {
	
			filterBeans.add(getCurrency(currencyComboBox.getSelectedItem().toString(), "Currency"));
		
		} 		
		if(!commonUTIL.isEmpty(tradeIdTextField.getText())) {
			
			filterBeans.add(getOtherId(tradeIdTextField.getText()));
			
		} 
		
		if( (OpenpositionDate.getDate() != null 
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(OpenpositionDate.getDate())))) {
			
			Date tradeDt = OpenpositionDate.getDate();
			
			filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
													commonUTIL.convertDateTOString(tradeDt), "OpenpositionDate"));
			
		}
		
		/*if((poComboBox.getSelectedIndex() > 0)) {
			
			filterBeans.add(getLegalEntity(poComboBox.getSelectedIndex(), "poId"));
			
		} */

		if( (bookComboBox.getSelectedIndex() > 0)) {			
			filterBeans.add(getBookName(bookComboBox));			
		} 
		
		return filterBeans;
	}

	@Override
	public void clearllCriterial() {
		
		currencyComboBox.setSelectedIndex(-1);
		tradeIdTextField.setText("");
		OpenpositionDate.setDate(null);
		//poComboBox.setSelectedIndex(-1);
		bookComboBox.setSelectedIndex(-1);
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		
		for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			
			if(bean.getColumnName().equalsIgnoreCase("Currency")) {
				currencyComboBox.setSelectedItem(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("otherTradeID")) {
				tradeIdTextField.setText(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("OpenpositionDate")) {
				OpenpositionDate.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
			}
			/*else if(bean.getColumnName().equalsIgnoreCase("poId")) {
				poComboBox.setSelectedIndex(getPOtoSelected(Integer.parseInt(bean.getValues())));
			}*/
			else if(bean.getColumnName().equalsIgnoreCase("Book")) {
				bookComboBox.setSelectedObjects(getMultipleValuesSelected(bean.getValues()));
			}
		}
	
	}

}
