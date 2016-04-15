package apps.window.util.propertyPane.panel;
 

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apps.window.util.propertyDialog.SearchPropertyDialog;
import apps.window.util.propertyPane.combox.SearchPropertyComboxBox;
import apps.window.util.windowUtil.Frame12;

import com.jidesoft.combobox.PopupPanel;

public class SearchPropertyPanel extends PopupPanel {
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	String searchType = "";
	public SearchPropertyPanel(SearchPropertyComboxBox comboBox) {
		_comboBox = comboBox;
		_comboBox.setEditable(true);
		setSearchType(_comboBox.getSearchType());
        initComponent();
      
        if(comboBox.getSelectedItem() instanceof String) {
        	String name = (String) comboBox.getSelectedItem();
        }
      
        setTitle("SearchProperty");
	}
	private void initComponent() {
		// TODO Auto-generated method stub
		  setLayout(new BorderLayout());
	        add(createSearchPropertyPanel(), BorderLayout.CENTER);
	        setRequestFocusEnabled(true);
	        setFocusable(true); 
		
	}
	boolean flagtoCloseDiagalBox = false;
	String name = "";
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
	 private JComponent createSearchPropertyPanel() {
		 if(_dialog == null)
			 _dialog = new SearchPropertyDialog(Frame12.getFrame(), false,getSearchType());
		     JPanel returnPanel = _dialog.getMainPanel(); 
		    
		     ListSelectionModel rowSM = _dialog.swrapper.propWind.rightSideCenterTable.getSelectionModel();
		     rowSM.addListSelectionListener(new ListSelectionListener() {
		    	 protected void newSelection(int minSelectionIndex, int maxSelectionIndex) {
		    			// TODO Auto-generated method stub
		    		 int row = _dialog.swrapper.propWind.rightSideCenterTable.getSelectedRow();
		    	String name = (String) _dialog.swrapper.propWind.rightSideCenterTable.getModel().getValueAt(row, 1);
		    	setName(name);
		    	flagtoCloseDiagalBox=true;
		    		 
 
		            	 
		    		}
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					  if (e.getValueIsAdjusting()) return;
		                
		                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		                if (lsm.isSelectionEmpty()) {
		                    return;
		                } else {
		                    newSelection(lsm.getMinSelectionIndex(), lsm.getMaxSelectionIndex());
		                }
		                
					
				}
		    	 
		     });
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
		            //	String selected = commonUTIL.convertHashTableKeyValuesToString(_dialog.conditionalD);
		            	 
		            	setSelectedObject(getName());
		            }
		        });
		     return returnPanel;
	 }
	
	private SearchPropertyDialog _dialog = null;
	private SearchPropertyComboxBox  _comboBox = null;
	
	 

}
