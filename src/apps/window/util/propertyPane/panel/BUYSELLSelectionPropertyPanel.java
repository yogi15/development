package apps.window.util.propertyPane.panel;
 
import java.awt.BorderLayout;

import com.jidesoft.combobox.PopupPanel;
  
import apps.window.util.propertyDialog.BUYSELLSelectionPropertyDialog;
import apps.window.util.propertyPane.combox.BUYSELLSelectionPropertyComboxBox;  

public class BUYSELLSelectionPropertyPanel extends PopupPanel {
	String buysell = null; 
	private BUYSELLSelectionPropertyComboxBox  _comboBox = null;
	 
	public BUYSELLSelectionPropertyPanel(BUYSELLSelectionPropertyComboxBox comboBox,String buysell) {
		 setTitle("BUYSELL");
		 _comboBox = comboBox;
		 this.buysell = buysell;
        initComponent();
		} 
       

	private BUYSELLSelectionPropertyDialog _dialog = null;
	
	protected void initComponent() {
        setLayout(new BorderLayout());
	}
	

}
