package FutureContract;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

public class FutureContractPropertyTable {

	String productName = "";
	private PropertyTable propertyTable = null;

	public FutureContractPropertyTable(String productName) {

		this.productName = productName;

	}

	public PropertyTable getFutureProductPropertyTable() {

		List<FutureContractProperty> futureProductProperties = getFutureProductProperties();
		propertyTable = new PropertyTable();

		PropertyTableModel<FutureContractProperty> model = new PropertyTableModel<FutureContractProperty>(
				futureProductProperties);
		model.setMiscCategoryName(" ");
		// model.addPropertyChangeListener(listener);
		propertyTable.setModel(model);
		propertyTable.getColumnModel().getColumn(0).setMinWidth(100);
		propertyTable.expandAll();

		return propertyTable;
	}

	private List<FutureContractProperty> getFutureProductProperties() {

		List<FutureContractProperty> properties = null;

		if (productName.equals(FuturesConstants.FUTURES_FX)) {

			properties = getFUTURE_FX_Properties();

		} else if (productName.equals(FuturesConstants.FUTURES_FX_TRADE)) {

			properties = getFUTURE_FX_Properties();

		}

		return properties;

	}

	private List<FutureContractProperty> getFUTURE_FX_Properties() {

		List<FutureContractProperty> properties = new ArrayList<FutureContractProperty>();
		
		if (productName.equals(FuturesConstants.FUTURES_FX)) {
				
			properties = FutureContractProductPropertyListEnum.FUTURE_FX
			.getPropertyList();
			
		} else {
			
			properties = FutureContractProductPropertyListEnum.FUTURES_FX_TRADE
			.getPropertyList();
		}
		
		// properties.add(futureContractProperty);

		return properties;

	}

}
