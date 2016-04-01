package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import apps.window.util.propertyDialog.LastTradeTimePropertyDialog;
import apps.window.util.propertyPane.combox.LastTradeTimePropertyComboxBox;
import apps.window.util.propertyPane.editor.LastTradeTimePropertyEditor;
import apps.window.util.windowUtil.Frame12;

import com.jidesoft.combobox.PopupPanel;

public class LastTradeTimePropertyPane1 extends PopupPanel{
	private LastTradeTimePropertyEditor  _editor = null;
	private LastTradeTimePropertyDialog _dialog = null;
	private LastTradeTimePropertyComboxBox  _comboBox = null;
	
	public LastTradeTimePropertyPane1(LastTradeTimePropertyEditor editor){
		_editor = editor;
		initComponent();
	}
	
	protected void initComponent(){
		setLayout(new BorderLayout());
		add(createLastTradedTimePanel(_editor.getTimeinMinutes()),BorderLayout.CENTER);
		setRequestFocusEnabled(true);
        setFocusable(true);
	}

	private JComponent createLastTradedTimePanel(int timeinMinutes){
		if(_dialog == null)
			_dialog = new LastTradeTimePropertyDialog(Frame12.getFrame(),false,_editor.getTimeinMinutes());
		JPanel returnPanel = _dialog.getMainPanel();
        JButton cancelButton = _dialog.getCancelButton();
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	_editor.stopCellEditing();
            }
        });
        JButton okButton = _dialog.getOkButton();
        setDefaultFocusComponent(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	_dialog.okButton_actionPerformed();
            	setSelectedObject((_dialog.getLastTradedTimeinMinutes()));
            }
        });
        return returnPanel;
	}

}
