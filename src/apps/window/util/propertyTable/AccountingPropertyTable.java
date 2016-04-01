package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import util.RemoteServiceUtil;
import util.commonUTIL;

import apps.window.util.property.AccountingProperty;
import apps.window.util.propertyPane.enumsList.AccountingPropertyListEnum;
import apps.window.util.propertyPane.enumsList.FutureContractProductPropertyEnumList;
import beans.FutureProduct;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.FuturesConstants;
import constants.ReferenceConstants;

public class AccountingPropertyTable implements PropertyChangeListener {

	String refernceWindowName = "";
	private PropertyTable propertyTable = null;
	List<AccountingProperty> accountingProperties = null;

	public AccountingPropertyTable(String refernceWindowName) {

		this.refernceWindowName = refernceWindowName;

	}

	public PropertyTable getAccountingPropertyTable() {

		accountingProperties = getAccountingProperties();
		propertyTable = new PropertyTable();

		PropertyTableModel<AccountingProperty> model = new PropertyTableModel<AccountingProperty>(
				accountingProperties);
		model.setMiscCategoryName(" ");
		// model.addPropertyChangeListener(listener);
		propertyTable.setModel(model);
		propertyTable.getColumnModel().getColumn(0).setMinWidth(100);
		propertyTable.expandAll();
		propertyTable.addPropertyChangeListener(this);
		// propertyTable.addPropertyChangeListener("Exchange", this);
		return propertyTable;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {/*
		System.out.println("ppp");
		PropertyTable table = (PropertyTable) evt.getSource();

		int row = table.getSelectedRow();

		if (row == -1)
			return;
		System.out.println(table.getSelectedProperty().getName());
		Property property = table.getSelectedProperty();
		String currency = (String) table.getModel().getValueAt(3, 1);

		if (property == null)
			return;
		if (property.getName().equalsIgnoreCase(FuturesConstants.EXCHANGE)) {
			if (property.getValue() == null)
				return;
			table.getModel().setValueAt("", 4, 1);
			table.getModel().setValueAt("", 5, 1);
			table.getModel().setValueAt("", 6, 1);
			// int rowid =
			// getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());

			String value = (String) property.getValue();
			AccountingProperty proper = AccountingPropertyEnum
					.getPropertyValue(FuturesConstants.CONTRACT, value);
			if (proper != null) {
				if (accountingProperties.size() > 2) {
					accountingProperties.remove(2);
					accountingProperties.set(2, proper);
				}
			}
			// table.getModel().setValueAt(proper, rowid, 1);

		}
		if (property.getName().equalsIgnoreCase(FuturesConstants.CONTRACT)) {
			if (property.getValue() == null)
				return;
			table.getModel().setValueAt("", 6, 1);
			// int rowid =
			// getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
			String value = (String) property.getValue();
			String exchange = (String) table.getModel().getValueAt(1, 1);
			String curr = (String) table.getModel().getValueAt(2, 1);
			if (commonUTIL.isEmpty(curr) || commonUTIL.isEmpty(exchange))
				return;
			String refernceWindowName = value + "." + exchange + "." + curr
					+ "." + "FX";

			AccountingProperty proper = AccountingPropertyEnum
					.getPropertyValue(FuturesConstants.FUTURE,
							refernceWindowName);
			if (proper != null) {
				if (accountingProperties.size() > 3) {
					accountingProperties.remove(3);
					accountingProperties.set(3, proper);
				}
			}
			// table.getModel().setValueAt(proper, rowid, 1);

		}
		if (property.getName().equalsIgnoreCase(FuturesConstants.FUTURE)) {
			if (property.getValue() == null)
				return;
			String value = (String) property.getValue();
			String exchange = (String) table.getModel().getValueAt(1, 1);
			String curr = (String) table.getModel().getValueAt(2, 1);
			String contract = (String) table.getModel().getValueAt(4, 1);
			if (!commonUTIL.isEmpty(value)) {
				String futureName = "Future." + curr + "." + exchange + "."
						+ contract + "." + value;
				table.getModel().setValueAt(futureName, 6, 1);
				try {
					FutureProduct fp = RemoteServiceUtil
							.getRemoteProductService()
							.getFutureProductOnQuoteName(futureName);
					table.getModel().setValueAt(
							new Double(fp.getLots()).doubleValue(), 12, 1);
					table.getModel().setValueAt(
							new Double(fp.getContract_size()).doubleValue(),
							13, 1);
					// Vector<Double> propertyValuesVec = new Vector<Double>();
					// propertyValuesVec.add(fp.getLots());
					// AccountingProperty propertylots =
					// AccountingProperty.createAutoCompleteComboBox(FuturesConstants.LOTS,
					// "Exchange", propertyValuesVec);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		if (property.getName().equalsIgnoreCase(FuturesConstants.PRICE)) {
			try {
				if (property.getValue() != null) {
					double value = Double.parseDouble(property.getValue()
							.toString());
					double lots = Double.parseDouble(table.getModel()
							.getValueAt(12, 1).toString());
					double contractsize = Double.parseDouble(table.getModel()
							.getValueAt(13, 1).toString());
					table.getModel().setValueAt(value * lots * contractsize,
							14, 1);
				}
			} catch (NumberFormatException e) {
				commonUTIL.showAlertMessage("Enter Number only");
				return;
			}

		}

		PropertyTableModel<AccountingProperty> propertyModel = (PropertyTableModel<AccountingProperty>) table
				.getModel();

		// TODO Auto-generated method stub

	*/}

	private List<AccountingProperty> getAccountingProperties() {

		List<AccountingProperty> properties = AccountingPropertyListEnum.ACCOUNTING
		.getPropertyList(ReferenceConstants.ACCOUNTING);

		
		return properties;

	}

	
}
