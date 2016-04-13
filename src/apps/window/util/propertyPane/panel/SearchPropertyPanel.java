package apps.window.util.propertyPane.panel;
 

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
 
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
	 private JComponent createSearchPropertyPanel() {
		 if(_dialog == null)
			 _dialog = new SearchPropertyDialog(Frame12.getFrame(), false,getSearchType());
		     JPanel returnPanel = _dialog.getMainPanel(); 
		     _dialog.sw.propWind.rightSideCenterTable.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					setSelectedObject("testing");
					
				}
			});
		     return returnPanel;
	 }
	private SearchPropertyDialog _dialog = null;
	private SearchPropertyComboxBox  _comboBox = null;
	
	 

}
