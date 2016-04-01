package apps.window.util.propertyPane.editor;

import apps.window.util.propertyPane.combox.BookSelectionPropertyCombox;
import apps.window.util.propertyPane.combox.LESelectionPropertyCombox;
import beans.Book;
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.grid.AbstractComboBoxCellEditor;

public class BookSelectionCellEditor extends AbstractComboBoxCellEditor {

	private Book book = null;

	/**
	 * @return the _le
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param _le
	 *            the _le to set
	 */
	public void set_le(Book book) {
		this.book = book;
	}

	public BookSelectionCellEditor(Book book) {

		this.book = book;
		customizeAbstractComboBox();
	}
	public BookSelectionCellEditor( ) {

		this.book = book;
		customizeAbstractComboBox();
	}

	public AbstractComboBox createAbstractComboBox() {
		return new BookSelectionPropertyCombox(this);
	}
}
