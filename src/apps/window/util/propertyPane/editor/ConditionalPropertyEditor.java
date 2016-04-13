package apps.window.util.propertyPane.editor;

import java.util.Vector;

import apps.window.util.propertyPane.combox.ConditionalPropertyCombox; 

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class ConditionalPropertyEditor extends AbstractComboBoxCellEditor {
	
	Vector<String> conditionalData = null;
	/**
	 * @return the conditionalData
	 */
	public Vector<String> getConditionalData() {
		return conditionalData;
	}

	/**
	 * @param conditionalData the conditionalData to set
	 */
	public void setConditionalData(Vector<String> conditionalData) {
		this.conditionalData = conditionalData;
	}

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the designType
	 */
	public String getDesignType() {
		return designType;
	}

	/**
	 * @param designType the designType to set
	 */
	public void setDesignType(String designType) {
		this.designType = designType;
	}

	String windowName ;
	String designType;
String propertyName; 

	/**
 * @return the propertyName
 */
public String getPropertyName() {
	return propertyName;
}

/**
 * @param propertyName the propertyName to set
 */
public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}

	public ConditionalPropertyEditor(Vector<String> sdata, String windowName,
			String designType, String propertyName) {
		// TODO Auto-generated constructor stub
		this.conditionalData = sdata;
		setConditionalData(sdata);
		setWindowName(windowName);
		setDesignType(designType);
		setPropertyName(propertyName);
		
		 
		customizeAbstractComboBox();
		
	}

	@Override
	public AbstractComboBox createAbstractComboBox() {
		// TODO Auto-generated method stub
		return new ConditionalPropertyCombox(this);
	}

}