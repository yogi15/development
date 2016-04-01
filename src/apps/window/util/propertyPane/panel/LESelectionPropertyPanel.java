package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apps.window.util.propertyDialog.ContractSelectorDialog;
import apps.window.util.propertyDialog.DateRulePropertyDialog;
import apps.window.util.propertyDialog.LESelectionPropertyDialog;
import apps.window.util.propertyPane.combox.ContractSelectorComboBox;
import apps.window.util.propertyPane.combox.DateRulePropertyCombox;
import apps.window.util.propertyPane.combox.LESelectionPropertyCombox;
import apps.window.util.windowUtil.Frame12;
import beans.DateRule;
import beans.LegalEntity;
import beans.Product;

import com.jidesoft.combobox.PopupPanel;

public class LESelectionPropertyPanel extends PopupPanel {
	private LESelectionPropertyCombox  _comboBox = null;
	 private String _displayObjClass = null;
	 private LegalEntity _le = null;
	public LESelectionPropertyPanel(LESelectionPropertyCombox comboBox,String displayableObject,LegalEntity le) {
		 setTitle("LegalEntity");
		 _comboBox = comboBox;
        _displayObjClass = displayableObject;
        _le = le;
        initComponent();
 } 
       
	
	private LESelectionPropertyDialog _dialog = null;
	
	protected void initComponent() {
        setLayout(new BorderLayout());
        add(createLESelectionPanel(_displayObjClass), BorderLayout.CENTER);
        setRequestFocusEnabled(true);
        setFocusable(true);
        _comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// JPanel returnPanel = _dialog.getMainPanel();
				// returnPanel.setVisible(true);
				
			}
		});
 _comboBox.addKeyListener(new KeyListener() {
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// JPanel returnPanel = _dialog.getMainPanel();
		// returnPanel.setVisible(true);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		 JPanel returnPanel = _dialog.getMainPanel();
		// _dialog.setVisible(true);
	//	 _dialog.show(true);
		 returnPanel.setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// JPanel returnPanel = _dialog.getMainPanel();
		// _dialog.setVisible(true);
		JPanel returnPanel = _dialog.getMainPanel();
		// _dialog.setVisible(true);
	//	 _dialog.show(true);
		 returnPanel.setVisible(true);
		// returnPanel.setVisible(true);
		
	}
});
				
				
		
    }
	 private JComponent createLESelectionPanel(String displayableObjectClass ) {
		 if(_dialog == null)
			 _dialog =  new LESelectionPropertyDialog(Frame12.getFrame(), false , displayableObjectClass);
		        JPanel returnPanel = _dialog.getMainPanel();
		        if(_le != null && _le.getRole().equalsIgnoreCase("PO")) {
		        	_dialog.getClear();
		        	returnPanel.setVisible(false);
		        	//return;
		        } else {
		        JTable futcontable = _dialog.getFutureContractSelectorTabel();
		        ListSelectionModel rowSM = futcontable.getSelectionModel();
		        rowSM.addListSelectionListener(new ListSelectionListener() {
		        	
		        	public void newSelection(int min, int max) {
				           LegalEntity businessobj =_dialog.getContractAtRow(min);
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
		return ((LegalEntity) getSelectedObject()).getId();
	 }
	//private DateRule selectedDateRuleobj = null;
	public void reloadData(String _selectedObjType, int productID) {
		// TODO Auto-generated method stub
		 
		    	_dialog.reloadData(_selectedObjType, productID);
		    
		    
	}
	public void reloadKeyPress(String keyTypeD) {
		// TODO Auto-generated method stub
		System.out.println(keyTypeD);
		
	}

}
