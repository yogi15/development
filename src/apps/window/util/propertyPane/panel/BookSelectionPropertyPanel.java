package apps.window.util.propertyPane.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apps.window.util.propertyDialog.BookSelectionPropertyDialog;
import apps.window.util.propertyPane.combox.BookSelectionPropertyCombox;
import apps.window.util.windowUtil.Frame12;
import beans.Book;

import com.jidesoft.combobox.PopupPanel;

public class BookSelectionPropertyPanel extends PopupPanel {
	private BookSelectionPropertyCombox _comboBox = null;
	private String _displayObjClass = null;
	private Book book = null;

	public BookSelectionPropertyPanel(BookSelectionPropertyCombox comboBox
			  , Book le) {
		setTitle("Book");
		_comboBox = comboBox;
		 
		book = le;
		initComponent();
	}

	private BookSelectionPropertyDialog _dialog = null;

	protected void initComponent() {
		setLayout(new BorderLayout());
		add(createBookSelectionPanel(_displayObjClass), BorderLayout.CENTER);
		setRequestFocusEnabled(true);
		setFocusable(true);
		_comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// JPanel returnPanel = _dialog.getMainPanel();
				// returnPanel.setVisible(true);

			}
		});
		_comboBox.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// JPanel returnPanel = _dialog.getMainPanel();
				// returnPanel.setVisible(true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				JPanel returnPanel = _dialog.getMainPanel();
				// _dialog.setVisible(true);
				// _dialog.show(true);
				returnPanel.setVisible(true);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// JPanel returnPanel = _dialog.getMainPanel();
				// _dialog.setVisible(true);
				JPanel returnPanel = _dialog.getMainPanel();
				// _dialog.setVisible(true);
				// _dialog.show(true);
				returnPanel.setVisible(true);
				// returnPanel.setVisible(true);

			}
		});

	}

	private JComponent createBookSelectionPanel(String displayableObjectClass) {
		if (_dialog == null)
			_dialog = new BookSelectionPropertyDialog(Frame12.getFrame(),
					false, displayableObjectClass);
		JPanel returnPanel = _dialog.getMainPanel();
		if (book != null) {
			_dialog.getClear();
			returnPanel.setVisible(false);
			// return;
		} else {
			JTable futcontable = _dialog.getBookTable();
			ListSelectionModel rowSM = futcontable.getSelectionModel();
			rowSM.addListSelectionListener(new ListSelectionListener() {

				public void newSelection(int min, int max) {
					Book businessobj = _dialog.getBookAtRow(min);
					setSelectedObject(businessobj);
				}

				public void valueChanged(ListSelectionEvent e) {
					// Ignore extra messages.
					if (e.getValueIsAdjusting())
						return;

					ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					if (lsm.isSelectionEmpty()) {
						return;
					} else {
						newSelection(lsm.getMinSelectionIndex(),
								lsm.getMaxSelectionIndex());
					}
				}
			});
		}
		// JPanel returnPanel = _dialog.getMainPanel();

		return returnPanel;
	}

	public int getSelectBookID() {
		return ((Book) getSelectedObject()).getBookno();
	}

	// private DateRule selectedDateRuleobj = null;
	public void reloadData(String _selectedObjType, int productID) {
		// TODO Auto-generated method stub

		_dialog.reloadData(_selectedObjType, productID);

	}

	public void reloadKeyPress(String keyTypeD) {
		// TODO Auto-generated method stub
		System.out.println(keyTypeD);

	}

}
