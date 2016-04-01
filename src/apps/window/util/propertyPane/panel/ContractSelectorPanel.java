package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apps.window.util.propertyDialog.ContractSelectorDialog;
import apps.window.util.propertyPane.combox.ContractSelectorComboBox;
import apps.window.util.windowUtil.Frame12;
import beans.Product;

import com.jidesoft.combobox.PopupPanel;

public class ContractSelectorPanel extends PopupPanel
{
	public ContractSelectorComboBox _comboBox;
	private ContractSelectorDialog _dialog;
    private String _displayObjClass = null;
    public ContractSelectorPanel(ContractSelectorComboBox comboBox,String displayableObject) {
        _comboBox = comboBox;
        _displayObjClass = displayableObject;
        initComponent();
 }
 
    protected void initComponent() {
        setLayout(new BorderLayout());
        add(createFutureContractSelectorPanel(_displayObjClass), BorderLayout.CENTER);
        setRequestFocusEnabled(true);
        setFocusable(true);
    }
    private JComponent createFutureContractSelectorPanel(String displayableObjectClass) {
    	if(_dialog == null)
    		_dialog = new ContractSelectorDialog(Frame12.getFrame(), false , displayableObjectClass);
        JPanel returnPanel = _dialog.getMainPanel();
        JTable futcontable = _dialog.getFutureContractSelectorTabel();
        ListSelectionModel rowSM = futcontable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
        	public void newSelection(int min, int max) {
		           Product businessobj =_dialog.getContractAtRow(min);
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
        return returnPanel;
    }	    

    public void reloadData(String objType,int productID){
    	_dialog.reloadData(objType, productID);
    }
    
}
