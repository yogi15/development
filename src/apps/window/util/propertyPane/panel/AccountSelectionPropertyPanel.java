package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jidesoft.combobox.PopupPanel;

import apps.window.util.propertyDialog.AccountSelectionPropertyDialog;
import apps.window.util.propertyDialog.LESelectionPropertyDialog;
import apps.window.util.propertyPane.combox.AccountSelectorCombox;
import apps.window.util.windowUtil.Frame12;
import beans.Account;
import beans.LegalEntity;

public class AccountSelectionPropertyPanel extends PopupPanel {
	AccountSelectorCombox _accountSelectorCombox = null;
	String currency = null;
	LegalEntity accountHolder = null;
	 private String _displayObjClass = null;
	 private AccountSelectionPropertyDialog _dialog = null;
	public AccountSelectionPropertyPanel(
			AccountSelectorCombox accountSelectorCombox, String currency,
			LegalEntity beneficiary) {
		this._accountSelectorCombox = accountSelectorCombox;
		this.currency = currency;
		this.accountHolder = beneficiary;
		 initComponent();
		
		// TODO Auto-generated constructor stub
	}
	
	protected void initComponent() {
        setLayout(new BorderLayout());
        add(createAccountSelectionPanel(_displayObjClass), BorderLayout.CENTER);
        setRequestFocusEnabled(true);
        setFocusable(true);
    }
	 private JComponent createAccountSelectionPanel(String displayableObjectClass ) {
		 if(_dialog == null)
			 _dialog =  new AccountSelectionPropertyDialog(Frame12.getFrame(), false , displayableObjectClass,currency,accountHolder);
		        JPanel returnPanel = _dialog.getMainPanel();
		        if(accountHolder == null) {
		        	returnPanel.setVisible(false);
		        } else {
		        	
		        
		        JTable futcontable = _dialog.getAccountSelectorTabel();
		        ListSelectionModel rowSM = futcontable.getSelectionModel();
		        rowSM.addListSelectionListener(new ListSelectionListener() {
		        	public void newSelection(int min, int max) {
				           Account businessobj =_dialog.getAccountAtRow(min);
				           setSelectedObject(businessobj);
				       }
		            public void valueChanged(ListSelectionEvent e) {
		                //Ignore extra messages.
		                if (e.getValueIsAdjusting()) return;
		                
		                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		                if (lsm.isSelectionEmpty()) {
		                    return;
		                } else {
		                    newSelection(lsm.getMinSelectionIndex(), lsm.getMaxSelectionIndex());
		                }
		            }
		        });
		        }
		   //  JPanel returnPanel = _dialog.getMainPanel();
	        
		 return returnPanel;
	 }
	 
	 public int getSelectLegalEntityID() {
		return ((Account) getSelectedObject()).getId();
	 }

}
