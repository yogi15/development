package apps.window.util.propertyPane.editor;


import apps.window.util.propertyPane.combox.DateRulePropertyCombox;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class DateRulePropertyEditor extends AbstractComboBoxCellEditor {

	@Override
	public AbstractComboBox createAbstractComboBox() {
		// TODO Auto-generated method stub
		return new DateRulePropertyCombox();
	}

}
