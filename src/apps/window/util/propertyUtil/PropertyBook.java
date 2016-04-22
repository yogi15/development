package apps.window.util.propertyUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.CellEditor;

import util.cacheUtil.ReferenceDataCache;

import apps.window.util.propertyPane.editor.BookSelectionCellEditor;
import apps.window.util.propertyPane.editor.LESelectionCellEditor;
import beans.Book;
import beans.LegalEntity;

import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;

public class PropertyBook extends Property implements PropertyChangeListener {

	public PropertyBook(String propertyName, String description, Class type) {
		super(propertyName, description, type);

		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {

				Book book = new Book();
				return new BookSelectionCellEditor(book);
			}
		}, new EditorContext(propertyName));
		setCategory(description);
		final ObjectConverter converter = new BookCodeConverter();
		setConverterContext(new ConverterContext(getName()));
		ObjectConverterManager.registerConverter(getType(), converter,
				getConverterContext());
		setEditorContext(new EditorContext(propertyName));

		addPropertyChangeListener(PROPERTY_VALUE, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				setValue(getValue());

			}
		});

	}

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
		 if(value instanceof Integer) {
		BookSelectionCellEditor bookCellEditor = (BookSelectionCellEditor) this.getCellEditor();
		 Book book = bookCellEditor.getBook((int) value);
		 if(book == null) 
			 book=	 ReferenceDataCache.getBook((int) value);
		 setValue( book , true);
		} else {
		setValue(value, true);
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		//System.out.println(getValue());

	}

}

class BookCodeConverter implements ObjectConverter {
	Book p = null;

	public String toString(Object o, ConverterContext converterContext) {
		if (!(o instanceof Book))
			return "";
		p = (Book) o;
		return p.getBookName();
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
