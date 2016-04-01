package apps.window.util.propertyUtil;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.ReferenceDataCache;
import util.commonUTIL;
import beans.AttributeContainer;
import beans.WindowSheet;
import com.jidesoft.combobox.AbstractComboBox;
import com.jidesoft.combobox.PopupPanel;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.grid.AbstractComboBoxCellEditor;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyPane;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.popup.JidePopup;

import constants.WindowSheetConstants;

public class AttributeProperty extends PropertyExtended<AttributeContainer>
		 {

	private CellEditor _attributeCellEditor = null;
	private boolean _isSecCodeValuePropertyChangeListenerDisabled;
	private boolean _isRemovedProcessON;
	private boolean propertyAssign = false;
	/* sort properties by security code name, those with value on top of the set */
	private static final Comparator SEC_CODE_PROPERTY_COMPARATOR = new Comparator<Property>() {
		@Override
		public int compare(Property p1, Property p2) {
			return key(p1).compareTo(key(p2));
		}

		private String key(Property p) {
			return (p.getValue() == null ? "" : "+") + p.getName();
		}
	};

	public AttributeProperty(final String attributeName, String category) {
		super();
		setName(attributeName);

		setType(AttributeContainer.class);
		setDisplayName(attributeName);
		setEditable(false); // this is important dont make it editable.

		addPropertyChangeListener(PROPERTY_VALUE, new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

				setDisplayName(attributeName);

				addChildren(0, buildChildrenProperties());

			}

		});
		addPropertyChangeListener(PROPERTY_EXPANDED,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if (getChildrenCount() < 2)
							return;
						boolean expanded = commonUTIL.isTrue(String.valueOf(evt
								.getNewValue()));
						if (!expanded)
							sortChildrenProperties();
					}
				});
		final ObjectConverter converter = new AttributeConverter();
		setConverterContext(new ConverterContext(getName()));
		ObjectConverterManager.registerConverter(getType(), converter,
				getConverterContext());
		_attributeCellEditor = new AttributeCellEditor(converter,
				getConverterContext());
		// _attributeChangedListener = attributeChangedListener;

	}

	public void showProduct(AttributeContainer c) {
		setValue(c);
	}

	@Override
	public void setValue(Object value) {
		super.setValue(null);
		if (value != null)
			super.setValue(value);
		setChildrenPropsSecCodeValues();
	}

	private void setChildrenPropsSecCodeValues() {
		if (getChildrenCount() < 1)
			return;
		AttributeContainer product = getValue();
		List<Property> children = (List<Property>) getChildren();
		try {
			_isSecCodeValuePropertyChangeListenerDisabled = true;
			for (Property child : children) {
				if (commonUTIL.isEmpty(product.getAttributes())) {
					child.setValue(null);
					if (!isEditable())
						child.setHidden(true);
				} else {
					String name = child.getName();
					// child.setValue(name);

					if (!isEditable())
						child.setHidden(false);
				}
			}

			if (!isExpanded() && isEditable()) { // sort the children properties
				sortChildrenProperties();
			} else if (!isEditable() && getTreeTableModel() != null) {
				getTreeTableModel().refresh(); // because some properties are
												// hidden
			}

		} finally {
			_isSecCodeValuePropertyChangeListenerDisabled = false;
		}
	}

	private void sortChildrenProperties() {
		List<Property> children = new ArrayList<Property>(
				(Collection<? extends Property>) getChildren());
		_isRemovedProcessON = true;
		removeAllChildren();
		_isRemovedProcessON = false;
		Collections.sort(children, SEC_CODE_PROPERTY_COMPARATOR);
		addChildren(0, children);
	}

	public class AttributeConverter implements ObjectConverter {
		public String toString(Object o, ConverterContext converterContext) {
			if (!(o instanceof AttributeContainer))
				return "";
			AttributeContainer p = (AttributeContainer) o;
			return getDisplayName();
		}

		public boolean supportToString(Object o,
				ConverterContext converterContext) {
			return true;
		}

		public Object fromString(String code, ConverterContext converterContext) {
			return getValue();
		}

		public boolean supportFromString(String s,
				ConverterContext converterContext) {
			return true;
		}
	}

	private class AttributeCellEditor extends AbstractComboBoxCellEditor {
		AttributeCellEditor(ObjectConverter converter,
				ConverterContext converterContext) {
			super(new DefaultComboBoxModel(
					new AttributeContainer[] { getValue() }),
					AttributeContainer.class);
			setConverterContext(converterContext);
			setConverter(converter);
		}

		@Override
		public AbstractComboBox createAbstractComboBox() {
			 System.out.println("From createAbstractComboBox" );
			AbstractComboBox comboBox = new AttributeComboBox(null, null);
			return (AbstractComboBox) comboBox;
		}
	}

	private class AttributeComboBox extends AbstractComboBox {

		AttributeComboBox(ComboBoxModel model, Class<?> type) {
			super(DROPDOWN);
			System.out.println("From AttributeComboBox" );
			setType(type);
			initComponent(model);
			AttributeComboBox.this.setEditable(true);
			setPopupCancelBehavior(PERSIST); // avoid nasty callbacks on edit
												// cancel
			setPopupVolatile(true);
		}

		@Override
		public PopupPanel createPopupComponent() {
			 System.out.println("From createPopupComponent" );
			return new AttributeEditionPopupPanel();
		}

		@Override
		protected boolean validateValueForNonEditable(Object value) {
			return value instanceof String;
		}

		@Override
		public EditorComponent createEditorComponent() {
			return new DefaultTextFieldEditorComponent(getType());
		}

		@Override
		public boolean commitEdit() {
			/*
			 * String code = ((EditorComponent)getEditor()).getText(); String
			 * secCodeType = getSecCodeType(); boolean validCode =
			 * validateSecCodeValue(secCodeType, code); Product p = getValue();
			 * if (validCode && p != null) { String oldCode =
			 * p.getSecCode(secCodeType); if (!Util.isSame(code, oldCode)) {
			 * p.setSecCode(secCodeType, code); if (_secCodesChangedListener !=
			 * null) _secCodesChangedListener.secCodeSaved(p.getSecCodes()); } }
			 * else return false;
			 */

			return super.commitEdit();
		}

		@Override
		protected void customizePopup(JidePopup popup) {
			super.customizePopup(popup);
			// make the editor dropdown panel focusable and then editable
			  System.out.println("From customizePopup" );
			popup.setDefaultFocusComponent(getPopupPanel());
		}
	}

	private class AttribueListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			if (_isSecCodeValuePropertyChangeListenerDisabled)
				return;
			Object newValue = evt.getNewValue();
			if (newValue == null)
				return;
			Property attribute = (Property) evt.getSource();
			AttributeContainer attC = getValue();
			if (attC != null) {
				String oldValue = attC.getAttributeValue(attribute.getName());
				if (commonUTIL.isEmpty(oldValue))
					attC.setAttributeValue(attribute.getName(),
							newValue.toString());

				if (!commonUTIL.isSame(newValue, oldValue)) {
					attC.setAttributeValue(attribute.getName(),
							newValue.toString());
					if (_isRemovedProcessON)
						setValue(attC);
				}
			}

		}
	}

	private class AttributeListenerFactory implements
			ValidatedTextProperty.DocumentListenerFactory, DocumentListener {
		private final String attributeCode;

		AttributeListenerFactory(String pCode) {
			attributeCode = pCode;
		}

		public DocumentListener createDocumentListener(JTextField textField) {
			return this;
		}

		public void changedUpdate(DocumentEvent e) {
		}

		public void insertUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() > 64) {
				// AppUtil.displayError(_pCode.getCode() +
				// " has to be less then 64 characters",
				// getParentComponentForErrorDisplay());
			}
		}

		public void removeUpdate(DocumentEvent e) {
		}
	}

	private class AttributeEditionPopupPanel extends PopupPanel {

		AttributeEditionPopupPanel() {
			super(new BorderLayout());
			  System.out.println("From AttributeEditionPopupPanel" );
			layoutSecCodes(getValue());
		}

		synchronized void layoutSecCodes(AttributeContainer attributes) {
			if (attributes == null)
				return;
			removeAll();

			List<Property> properties = buildChildrenProperties();

			PropertyTable propertyTable = CommonPropertyUtil
					.createPropertyTable(properties);
			propertyAssign = true;
			PropertyPane propertyPane = new PropertyPane(propertyTable, 0);
			Dimension d = propertyTable.getPreferredSize();
			propertyPane.setPreferredSize(new Dimension(d.width + 5,
					d.height + 5));
			propertyPane.setShowDescription(false);
			add(propertyPane, BorderLayout.CENTER);
			setStretchToFit(true);
			setResizable(true);
		}

	}

	private List<Property> buildChildrenProperties() {
		// TODO Auto-generated method stub
		AttributeContainer attributeC = getValue();

		String attributeName = attributeC.getAttributeName();

		Vector<WindowSheet> windowSheets = ReferenceDataCache
				.selectWindowSheets(attributeName,WindowSheetConstants.ATTRIBUTE);
		List<Property> properties = PropertyGenerator
				.getPropertiesOnAttributes(windowSheets);
		if (!commonUTIL.isEmpty(properties)) {
			for (int i = 0; i < properties.size(); i++) {
				AttribueListener attributeListener = new AttribueListener();
				properties.get(i).addPropertyChangeListener(attributeListener);
			}

		}
		return properties;
	}

	 

}
