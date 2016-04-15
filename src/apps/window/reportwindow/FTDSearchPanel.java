package apps.window.reportwindow;

import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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

import beans.FilterBean;
import beans.UserJobsDetails;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FTDSearchPanel extends SearchCriteriaType {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private DateComboBox jComboBox0;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public FTDSearchPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 306, 10, 10), new Bilateral(7, 12, 0)));
		setSize(320, 506);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(12, 54, 10, 10), new Leading(32, 12, 12)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(108, 170, 10, 10), new Leading(24, 27, 10, 10)));
		}
		return jPanel0;
	}

	private DateComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new DateComboBox();
			jComboBox0.setFormat(commonUTIL.getDateFormat());
			jComboBox0.setDate(null);
		}
	
		return jComboBox0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Date");
		}
		return jLabel0;
	}

	@Override
	public Vector<FilterBean> searchCriteria() {
		// TODO Auto-generated method stub
		Vector<FilterBean> filterBeans = new Vector<FilterBean>();
		FilterBean bean = null;
		if( (jComboBox0.getDate() != null 
				&& !commonUTIL.isEmpty(commonUTIL.convertDateTOString(jComboBox0.getDate())))) {
			
			Date tradeDt = jComboBox0.getDate();
			
			filterBeans.add(getCriteriaDate(commonUTIL.convertDateTOString(tradeDt),		
													commonUTIL.convertDateTOString(tradeDt), "FTDCurrentDate"));
			
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
		jComboBox0.setFormat(commonUTIL.getDateFormat());
		
	}

}
