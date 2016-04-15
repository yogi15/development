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
public class ForwardLadderSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JComboBox Ladder;
	private DateComboBox settleDate;
	private JComboBox currency;
	private JLabel jLabel1;
	private JLabel jLabel2;
	javax.swing.DefaultComboBoxModel ladderData = new javax.swing.DefaultComboBoxModel();
	javax.swing.DefaultComboBoxModel currencyAttributeData = new javax.swing.DefaultComboBoxModel();
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public ForwardLadderSearchPanel() {
		processDomainData(ladderData,  getFilterValues().getDomainValues("Ladder"));
		processDomainData(currencyAttributeData,  getFilterValues().getDomainValues("Currency"));
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(14, 10, 10), new Leading(32, 10, 10)));
		add(getJComboBox0(), new Constraints(new Leading(103, 103, 10, 10), new Leading(64, 12, 12)));
		add(getCurrency(), new Constraints(new Leading(103, 102, 12, 12), new Leading(102, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(14, 51, 10, 10), new Leading(105, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(14, 51, 12, 12), new Leading(67, 12, 12)));
		add(getSettleDate(), new Constraints(new Leading(105, 98, 12, 12), new Leading(29, 12, 12)));
		setSize(320, 381);
	}

	

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Ladder");
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

	private DateComboBox getSettleDate() {
		if (settleDate == null) {
			settleDate =  new DateComboBox();
			settleDate.setFormat(commonUTIL.getDateFormat());
			settleDate.setDate(null);;
			
		}
		return settleDate;
	}
	private JComboBox getCurrency() {
		if (currency == null) {
			currency = new JComboBox();
			currency.setModel(currencyAttributeData);
		}
		return currency;
	}
	private JComboBox getJComboBox0() {
		if (Ladder == null) {
			Ladder = new JComboBox();
			Ladder.setModel(ladderData);
		}
		return Ladder;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("SettleDate");
		}
		return jLabel0;
	}

	@Override
	public Vector<FilterBean> searchCriteria() {
		// TODO Auto-generated method stub
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
if( (settleDate.getDate() != null 
&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(settleDate.getDate())))) {	
			
			
			filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(settleDate.getDate()), 
					commonUTIL.convertDateTOString(settleDate.getDate()), "SettleDate"));		
				
				
		}

if(Ladder.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(Ladder.getSelectedItem().toString()))) {
	filterBeans.add(getLadder(Ladder.getSelectedItem().toString(),"Ladder"));

	
}
if(currency.getSelectedIndex() != -1 && (!commonUTIL.isEmpty(currency.getSelectedItem().toString()))) {
	filterBeans.add(getCurrency(currency.getSelectedItem().toString(), "Currency"));

	
}
return filterBeans;
	}

	@Override
	public void clearllCriterial() {
		// TODO Auto-generated method stub
		settleDate.setDate(null);
		currency.setSelectedIndex(-1);
		Ladder.setSelectedIndex(-1);
		
	}

	@Override
	public void loadFilters(Vector<UserJobsDetails> jobdetails) {
		// TODO Auto-generated method stub
		for(int i=0;i<jobdetails.size();i++) {
			UserJobsDetails bean = jobdetails.get(i);
			if(bean.getColumnName().equalsIgnoreCase("Currency")) {
				currency.setSelectedItem(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("Ladder")) {
					Ladder.setSelectedItem(bean.getValues());
			}
			else if(bean.getColumnName().equalsIgnoreCase("SettleDate")) {
				settleDate.setDate(commonUTIL
						.convertStringtoSQLDate(bean.getValues()));
		}
		}
	}

}
