package FutureContract;

import javax.swing.CellEditor;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;


import apps.productwindow.util.DateRule;
import apps.productwindow.util.DateRulePropertyE;

import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CellRendererManager;

public class ContractDefinationBase   extends JFrame {
	 protected void jbInit() throws Exception {
	    //   initializeButtonSetup();
	       registerPropertyEditors();
	       registerPropertyRenderors();
	    }

		protected ContractDefinationBase getWindow(){
	    	return this;
	    }
	 private void registerPropertyRenderors() {
		 CellRendererManager.registerRenderer(DateRule.class, new DefaultTableCellRenderer() {
	        	public void setValue(Object value) {
	        		if (value instanceof DateRule) {
	        		//	if (value instanceof ManualDateSchedule) {
	        				setText(value.toString());
	        			//} else {
	        				setText("DateRule: " + ((DateRule) value).getRuleName());
	        			}
	        		}
	         }, ContractPropertyEditorContext.DateRule.getEditorContext());
	 }
	
	 protected void registerPropertyEditors() {
		 CellEditorManager.registerEditor(DateRule.class, new CellEditorFactory() {
             public CellEditor create() {
            	 DateRulePropertyE  dateRulePropertyEditor = new DateRulePropertyE ();
            	 dateRulePropertyEditor.getComboBox().setEditable(false);
                 return dateRulePropertyEditor;
             }
         }, ContractPropertyEditorContext.DateRule.getEditorContext());
	 }

}
