package apps.window.util.propertyUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.CellEditor;

import apps.window.util.propertyPane.editor.ConditionalPropertyEditor;
import apps.window.util.propertyPane.editor.DateRulePropertyEditor;

import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;

public class PropertyConditional extends Property implements
		PropertyChangeListener {

	public PropertyConditional(final String propertyName, String description,String category,
			Class type,final Vector<String> sdata,final String windowName,final String designType) {
		super(propertyName, description, type);
		 setName(propertyName);
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new ConditionalPropertyEditor(getConditionalData(),getWindowName(),getDesignType(),getConditionPropertyName());
			}
		}, new EditorContext(propertyName));
		setCategory(description);
		final ObjectConverter converter = new ConditionalCodeConverter();
		setConverterContext(new ConverterContext(getName()));
		ObjectConverterManager.registerConverter(getType(), converter,
				getConverterContext());
		setEditorContext(new EditorContext(propertyName));
        setCategory(category);
       
        
		addPropertyChangeListener(PROPERTY_VALUE, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				setValue(getValue());

			}
		});

	}
	String conditionPropertyName = "";
	/**
	 * @return the conditionPropertyName
	 */
	public String getConditionPropertyName() {
		return conditionPropertyName;
	}

	/**
	 * @param conditionPropertyName the conditionPropertyName to set
	 */
	public void setConditionPropertyName(String conditionPropertyName) {
		this.conditionPropertyName = conditionPropertyName;
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

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return WindowName;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		WindowName = windowName;
	}

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
	String designType = "Window";
	String WindowName = "";
	Vector<String> conditionalData = null;
	 

	public void setValue(Object value, boolean fireEvent) {
		Object oldValue = setValue;
		setValue = value;
		if (oldValue != setValue && fireEvent) {
			firePropertyChange(PROPERTY_VALUE, oldValue, value);
		}
	}

	private Object setValue = null;

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return setValue;
	}

	@Override
	public void setValue(Object value) {
		// d stub
		setValue(value, true);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		System.out.println(getValue());

	}

}

class ConditionalCodeConverter implements ObjectConverter {
	String p = null;

	public String toString(Object o, ConverterContext converterContext) {
		if (!(o instanceof String))
			return "";
		p = (String) o;
		return p;
	}

	public boolean supportToString(Object o, ConverterContext converterContext) {
		return true;
	}

	public Object fromString(String code, ConverterContext converterContext) {
		return p;
	}

	public boolean supportFromString(String s, ConverterContext converterContext) {
		return true;
	}
}
