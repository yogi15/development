package apps.window.util.propertyUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.CellEditor;

import apps.window.util.propertyPane.editor.DateRulePropertyEditor;
import apps.window.util.propertyPane.editor.SearchPropertyEditor;

import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;

public class PropertySearchBox extends Property implements
		PropertyChangeListener {

	 
	public PropertySearchBox(String propertyName, String description, Class type,final String searchType) {
		super(propertyName, description, type);

		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new SearchPropertyEditor(searchType);
			}
		}, new EditorContext(propertyName));
		setCategory(description);
		final ObjectConverter converter = new SearchPropertyConverter();
		setConverterContext(new ConverterContext(getName()));
		ObjectConverterManager.registerConverter(getType(), converter,
				getConverterContext());
		setEditorContext(new EditorContext(propertyName));
setEditable(true);
		addPropertyChangeListener(PROPERTY_VALUE, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				setValue(getValue());

			}
		});
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
		//System.out.println("From propertySearchBox " + value);
		//setValue(value);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		System.out.println(getValue());

	}

}

class SearchPropertyConverter implements ObjectConverter {
	String p = null;

	public String toString(Object o, ConverterContext converterContext) {
		if (!(o instanceof String))
			return "";
		p = (String) o;
		return p ;
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
