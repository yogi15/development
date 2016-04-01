package apps.window.util.propertyPane.combox;

import apps.window.util.propertyPane.editor.BookSelectionCellEditor;
import apps.window.util.propertyPane.panel.BookSelectionPropertyPanel;
import beans.Book;
import beans.LegalEntity;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;

public class BookSelectionPropertyCombox extends AbstractComboBox {
	String _selectedObjType = null;
	Book book = null;
	BookSelectionCellEditor _lESelectionCellEditor = null;

	@Override
	protected boolean validateValueForNonEditable(Object value) {
		return value instanceof LegalEntity;
	}

	/**
	 * @return the le
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param le
	 *            the le to set
	 */
	public void setBook(Book le) {
		this.book = le;
	}

	public BookSelectionPropertyCombox(String role) {
		_selectedObjType = role;

		setEditable(true);
		initComponent();
	}

	public BookSelectionPropertyCombox(
			BookSelectionCellEditor leSelectionCellEditor) {
		// TODO Auto-generated constructor stub
		_lESelectionCellEditor = leSelectionCellEditor;

		setBook(_lESelectionCellEditor.getBook());
		setEditable(true);
		initComponent();
	}

	@Override
	public boolean commitEdit() {
		return super.commitEdit();
	}

	@Override
	public EditorComponent createEditorComponent() {

		return new DefaultTextFieldEditorComponent(LegalEntity.class) {

			protected String convertElementToString(Object value) {

				String _stringFutCon = null;
				if (value != null) {
					if (value instanceof LegalEntity) {
						setBook((Book) value);
						_stringFutCon = String.valueOf(((LegalEntity) value)
								.getName());
						if (getBook() != null)
							return "";
					}
				}
				if (value == null)
					return "";
				System.out.println(_stringFutCon);
				return _stringFutCon;
			}

		};

		// TODO Auto-generated method stub

	}

	public Book getSelectBook() {
		return getBook();
	}

	public void reloadData(int productID) {
		if (getPopupPanel() != null)
			((BookSelectionPropertyPanel) getPopupPanel()).reloadData(
					_selectedObjType, productID);
	}

	public void keyTypeData(String keyTypeD) {
		// if(getPopupPanel() != null)
		// ((BookSelectionPropertyCombox)getPopupPanel())n
	}

	public void showPopupPanelAsPopup(boolean show) {
		super.showPopupPanelAsPopup(show);
	}

	@Override
	public PopupPanel createPopupComponent() {

		return new BookSelectionPropertyPanel(this, getBook());

	}

}
