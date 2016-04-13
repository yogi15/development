package apps.window.util.propertyPane.combox;
 

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel; 
 
import apps.window.util.propertyPane.editor.BUYSELLSelectionCellEditor;
import apps.window.util.propertyPane.panel.BUYSELLSelectionPropertyPanel;
import apps.window.util.propertyPane.panel.LESelectionPropertyPanel;

public class BUYSELLSelectionPropertyComboxBox   extends AbstractComboBox {
	 /**
	 * @return the cdate
	 */
	public String getBuySell() {
		return buysell;
	}


	/**
	 * @param cdate the cdate to set
	 */
	public void setBuySell(String buysell) {
		this.buysell = buysell;
	}
	String buysell;
	BUYSELLSelectionCellEditor buysellSelectionCellEditor = null;
	public BUYSELLSelectionPropertyComboxBox(
			BUYSELLSelectionCellEditor buysellSelectionCellEditor) {
		// TODO Auto-generated constructor stub
		this.buysellSelectionCellEditor = buysellSelectionCellEditor;
		setEditable(true);
		initComponent();
	}


	  @Override
    protected boolean validateValueForNonEditable(Object value) {
        return value instanceof String;
    }
	  public BUYSELLSelectionPropertyComboxBox(String buysell){
		  buysell = String.valueOf(buysell);
			
			setEditable(true);
			initComponent();
		}
		@Override
	    public boolean commitEdit() {
			 return super.commitEdit();    
		}
	@Override
	public EditorComponent createEditorComponent() {
		// TODO Auto-generated method stub
return new DefaultTextFieldEditorComponent(String.class) {
			
			
			protected String convertElementToString(Object value) {
				
				String _stringFutCon = null;
				 
			return 	_stringFutCon;
			}
			
		};
	}


	@Override
	public PopupPanel createPopupComponent() {
		// TODO Auto-generated method stub
		return new BUYSELLSelectionPropertyPanel(this,getBuySell());
	}

}



	 