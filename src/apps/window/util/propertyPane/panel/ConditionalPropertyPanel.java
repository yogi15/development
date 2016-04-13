package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import util.commonUTIL;

import apps.window.util.propertyDialog.ConditionalPropertyDialog;
import apps.window.util.propertyPane.combox.ConditionalPropertyCombox;
import apps.window.util.propertyPane.editor.ConditionalPropertyEditor;
import apps.window.util.windowUtil.Frame12;
import beans.DateRule;

import com.jidesoft.combobox.PopupPanel;

public class ConditionalPropertyPanel extends PopupPanel {
	ConditionalPropertyEditor editor;
	public ConditionalPropertyPanel(ConditionalPropertyCombox comboBox,ConditionalPropertyEditor editor) {
		_comboBox = comboBox;
		this.editor = editor;
        initComponent(); 
         
        setTitle("ConditionalProperty");
	}
	private ConditionalPropertyDialog _dialog = null;
	private ConditionalPropertyCombox  _comboBox = null; 
	protected void initComponent() {
        setLayout(new BorderLayout());
        add(conditionalPropertyPanel(), BorderLayout.CENTER);
        setRequestFocusEnabled(true);
        setFocusable(true);
    }
	 /**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	String value = null;
	 private JComponent  conditionalPropertyPanel() {
		 JPanel returnPanel = null;
		
		 if(_dialog == null) {
			 _dialog = new ConditionalPropertyDialog(Frame12.getFrame(), false,editor.getWindowName(),editor.getDesignType(),editor.getConditionalData(),editor.getPropertyName());
		       returnPanel = _dialog.getMainPanel();
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
		            	String selected = commonUTIL.convertHashTableKeyValuesToString(_dialog.conditionalD);
		            	if (selected == null) return;
		            	setSelectedObject(selected);
		            }
		        });
		         
		        
			 return returnPanel;
		 }
		 return returnPanel;
	 }
	 
}

