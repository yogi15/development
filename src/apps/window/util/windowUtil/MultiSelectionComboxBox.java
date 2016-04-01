package apps.window.util.windowUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import util.commonUTIL;

import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.ButtonPopupPanel;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;
import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.list.DualList;
import com.jidesoft.list.DualListModel;

public class MultiSelectionComboxBox extends AbstractComboBox {
	private List items;
	private List selection;
	private Pattern pattern, splitPattern;
	MultipleListPopupPanel panel;
	JTextField textField;

	public MultiSelectionComboxBox(List items, List initialSelection) {
		this.items = items;
		this.selection = initialSelection;
		pattern = Pattern.compile("\\[(.*)\\]");
		splitPattern = Pattern.compile(",");
		initComponent();
		setConverter(new ObjectConverter() {
			public String toString(Object object, ConverterContext context) {
				return object == null ? null
						: object instanceof Collection ? commonUTIL
								.collectionToString((Collection) object)
								: object.toString();
			}

			public boolean supportToString(Object object,
					ConverterContext context) {
				return true;
			}

			public Object fromString(String string, ConverterContext context) {
				return null;
			}

			public boolean supportFromString(String string,
					ConverterContext context) {
				return false;
			}
		});
		setSelectedItem(initialSelection);
	}

	@Override
	public EditorComponent createEditorComponent() {
		// TODO Auto-generated method stub
		final DefaultTextFieldEditorComponent textFieldEditorComponent = new DefaultTextFieldEditorComponent(
				List.class);
		textField = (JTextField) textFieldEditorComponent.getEditorComponent();
		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					setSelectedItem(textFieldEditorComponent.getText());
			}

			public void keyReleased(KeyEvent e) {
			}
		});
		return textFieldEditorComponent;
	}

	protected void showPopupPanelAsPopup(boolean show) {
		super.showPopupPanelAsPopup(show);
		panel.setupFocus();
	}

	@Override
	public PopupPanel createPopupComponent() {
		panel = new MultipleListPopupPanel(items, selection);
		panel.setupFocus();
		return panel;
	}

	public void setSelectedItem(Object anObject) {
		if (anObject instanceof String) {
			buildSelectionFromString((String) anObject);
		} else
			selection = (List) anObject;
		if (panel != null)
			panel.updateSelection(items, selection);
		super.setSelectedItem(selection);
	}

	private void buildSelectionFromString(String s) {
		Matcher matcher = pattern.matcher(s);
		if (matcher.find())
			s = matcher.group(1);
		String[] strings = splitPattern.split(s);
		selection = new ArrayList();
		for (String str : strings) {
			String elem = str.trim();
			if (isLegalElement(elem))
				selection.add(elem);
		}
	}

	private boolean isLegalElement(String s) {
		int i1 = items.size();
		for (int i = 0; i < i1; i++) {
			String element = (String) items.get(i);
			if (element.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	private class MultipleListPopupPanel extends ButtonPopupPanel {
		ExtendedDualList list;
		private List items;
		private AbstractAction okAction;

		public MultipleListPopupPanel(List items, List selection) {
			setStretchToFit(true);
			setLayout(new BorderLayout());
			this.items = items;
			okAction = new AbstractAction("OK") {
				public void actionPerformed(ActionEvent e) {
					MultiSelectionComboxBox.this.hidePopup();
					MultiSelectionComboxBox.this
							.setSelectedItem(getSelectedItems());
				}
			};

			list = new ExtendedDualList(items, okAction);

			list.setBorder(new EmptyBorder(0, 3, 0, 3));
			list.installActions();
			setDefaultFocusComponent(list);
			updateSelection(items, selection);
			add(list, BorderLayout.CENTER);
			add(makeButtonPanel(), BorderLayout.SOUTH);
		}

		public void setupFocus() {
			Runnable runnable = new Runnable() {
				public void run() {
					for (Component c : list.getOriginalListPane()
							.getComponents())
						if (c instanceof JScrollPane) {
							final JList originalsList = (JList) ((JScrollPane) c)
									.getViewport().getView();
							if (originalsList.getModel().getSize() > 0)
								originalsList.setSelectedIndex(0);
							originalsList.grabFocus();
						}
				}
			};
			EventQueue.invokeLater(runnable);
		}

		private ButtonPanel makeButtonPanel() {
			final ButtonPanel buttonPanel = new ButtonPanel();
			buttonPanel.addButton(new JButton(okAction));
			buttonPanel.addButton(new JButton(new AbstractAction("Cancel") {
				public void actionPerformed(ActionEvent e) {
					MultiSelectionComboxBox.this.hidePopup();
				}
			}));
			buttonPanel.setAlignment(SwingConstants.RIGHT);
			buttonPanel.setBorder(new CompoundBorder(
					new EmptyBorder(5, 3, 0, 3), new CompoundBorder(
							new MatteBorder(1, 0, 0, 0, Color.GRAY),
							new EmptyBorder(5, 0, 5, 0))));
			return buttonPanel;
		}

		public void updateSelection(List items, List selection) {
			final DualListModel listModel = list.getModel();
			listModel.clearSelection();
			if (selection != null)
				for (Object obj : selection) {
					int indexOf = items.indexOf(obj);
					if (indexOf != -1)
						listModel.addSelectionInterval(indexOf, indexOf);
				}
		}

		private List getSelectedItems() {
			final int[] ints = list.getModel().getSelectedIndices();
			ArrayList ret = new ArrayList();
			for (int i : ints)
				ret.add(items.get(i));
			return ret;
		}

		private class ExtendedDualList extends DualList {
			private AbstractAction okAction;

			public ExtendedDualList(List items, AbstractAction okAction) {
				super(items);
				this.okAction = okAction;
			}

			public JList getOriginalList() {
				for (Component c : list.getOriginalListPane().getComponents())
					if (c instanceof JScrollPane) {
						final JList originalsList = (JList) ((JScrollPane) c)
								.getViewport().getView();
						return originalsList;
					}
				return null;
			}

			public JList getSelectedList() {
				for (Component c : list.getSelectedListPane().getComponents())
					if (c instanceof JScrollPane) {
						final JList originalsList = (JList) ((JScrollPane) c)
								.getViewport().getView();
						return originalsList;
					}
				return null;
			}

			public void installActions() {
				final JList originalList = getOriginalList();
				final JList selectedList = getSelectedList();

				Action goOriginalAction = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						if (originalList.getModel().getSize() > 0
								&& originalList.getSelectedIndex() == -1)
							originalList.setSelectedIndex(0);
						originalList.requestFocus();
					}
				};

				Action goSelectedAction = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						if (selectedList.getModel().getSize() > 0
								&& selectedList.getSelectedIndex() == -1)
							selectedList.setSelectedIndex(0);
						selectedList.requestFocus();
					}
				};

				originalList.getActionMap().put("goSelected", goSelectedAction);
				originalList.getInputMap()
						.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
								"goSelected");
				selectedList.getActionMap().put("goSelected", goOriginalAction);
				selectedList.getInputMap()
						.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
								"goSelected");

				originalList.getActionMap().put("validate", okAction);
				originalList.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
								InputEvent.CTRL_DOWN_MASK), "validate");
				selectedList.getActionMap().put("validate", okAction);
				selectedList.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
								InputEvent.CTRL_DOWN_MASK), "validate");
			}
		}
	}

}
