package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;


import apps.window.util.propertyDialog.DateRulePropertyDialog;
import apps.window.util.propertyPane.combox.DateRulePropertyCombox;
import apps.window.util.windowUtil.Frame12;
import beans.DateRule;

import com.jidesoft.combobox.PopupPanel;

public class DateRulePropertyPanel extends PopupPanel {
	
	public DateRulePropertyPanel(DateRulePropertyCombox comboBox) {
		_comboBox = comboBox;
        initComponent();
        DateRule selected  = null;
        if(comboBox.getSelectedItem() instanceof String) {
        	String name = (String) comboBox.getSelectedItem();
        	selected = 	_dialog.getDateRule(name);
        	_dialog.setSelectedDateRule(selected);
        } else {
        	
        	selected = (DateRule) comboBox.getSelectedItem();
        _dialog.setSelectedDateRule(selected);
        }
        setTitle("DateRule");
	}
	private DateRulePropertyDialog _dialog = null;
	private DateRulePropertyCombox  _comboBox = null;
	private DateRule selectedDateRuleobj = null;
	
	protected void initComponent() {
        setLayout(new BorderLayout());
        add(createDateRulePanel(), BorderLayout.CENTER);
        setRequestFocusEnabled(true);
        setFocusable(true);
    }
	
	 private JComponent createDateRulePanel() {
		 if(_dialog == null)
			 _dialog = new DateRulePropertyDialog(Frame12.getFrame(), false);
		     JPanel returnPanel = _dialog.getMainPanel();
	        _dialog.getCancelButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	_comboBox.hidePopup();
	            }
	        });
	        _dialog.getClearButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	setSelectedObject(null);
	            }
	        });
	        _dialog.getOkButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	DateRule selected = _dialog.getSelectedDateRule();
	            	if (selected == null) return;
	            	setSelectedObject(selected.getName());
	            }
	        });
	        _dialog.getShowButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	selectedDateRuleobj = _dialog.getSelectedDateRule();
					if (selectedDateRuleobj != null) {
		            	if(selectedDateRuleobj.getName() == DateRule.MANUAL_DATE_SCHEDULE){
		            		/*_manualDateScheduleWindow = new ManualDateScheduleWindow();
		            	/*	AppUtil.centerWindow(_manualDateScheduleWindow);
		            		_manualDateScheduleWindow.showDateSchedule(selectedDateRuleobj.getName());
		            		_manualDateScheduleWindow.setVisible(true); */
		            	}
		            	else{
		            		/*	_dateRuleWindow = new DateRuleWindow();
		            		AppUtil.centerWindow(_dateRuleWindow);
		            		_dateRuleWindow.showRule(selectedDateRuleobj);
		            		_dateRuleWindow.setVisible(true); */
		            	}
					}
		          }
		        });
	        
		 return returnPanel;
	 }
}
