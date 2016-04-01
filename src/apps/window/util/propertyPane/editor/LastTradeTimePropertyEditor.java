package apps.window.util.propertyPane.editor;

import apps.window.util.propertyPane.combox.LastTradeTimePropertyComboxBox;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class LastTradeTimePropertyEditor extends AbstractComboBoxCellEditor{
	private int _timeInMinutes = -1;
	
	public LastTradeTimePropertyEditor(int timeInMinutes){
		_timeInMinutes = timeInMinutes;
		customizeAbstractComboBox();
	}
	
	public int getTimeinMinutes(){
		return _timeInMinutes;
	}
	
	public AbstractComboBox createAbstractComboBox() {
		return new LastTradeTimePropertyComboxBox(this);
	}

}
