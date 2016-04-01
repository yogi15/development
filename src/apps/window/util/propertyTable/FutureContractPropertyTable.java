package apps.window.util.propertyTable;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

import util.RemoteServiceUtil;
import util.commonUTIL;

import apps.window.uti.propertypane.enums.FutureContractPropertyEnum;
import apps.window.util.property.FutureContractProperty;
import apps.window.util.propertyPane.enumsList.FutureContractProductPropertyEnumList;
import beans.FutureProduct;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.FuturesConstants;

public class FutureContractPropertyTable implements PropertyChangeListener    {

	String productName = "";
	private PropertyTable propertyTable = null;
	List<FutureContractProperty> futureProductProperties = null;
	public FutureContractPropertyTable(String productName) {

		this.productName = productName;
	

	}

		 public PropertyTable getFutureProductPropertyTable() {
	       
			 futureProductProperties = getFutureProductProperties();
			 propertyTable = new PropertyTable();

			PropertyTableModel<FutureContractProperty> model = new PropertyTableModel<FutureContractProperty>(
					futureProductProperties);
			model.setMiscCategoryName(" ");
			// model.addPropertyChangeListener(listener);
			propertyTable.setModel(model);
			propertyTable.getColumnModel().getColumn(0).setMinWidth(100);
			propertyTable.expandAll();
			propertyTable.addPropertyChangeListener(this);
			//propertyTable.addPropertyChangeListener("Exchange", this);
			return propertyTable;
	    }
		 @Override
			public void propertyChange(PropertyChangeEvent evt) {
				 
				PropertyTable table = (PropertyTable) evt.getSource();
				
				int row = table.getSelectedRow();
				
				if(row == -1  )
					return;
			//	System.out.println(table.getSelectedProperty().getName());
				Property property = table.getSelectedProperty();
				String currency =(String) table.getModel().getValueAt(3, 1);
				
				if(property == null)
					return;
				if(property.getName().equalsIgnoreCase(FuturesConstants.EXCHANGE)) {
					if(property.getValue() == null)
						return;
					table.getModel().setValueAt("", 4, 1);
					table.getModel().setValueAt("", 5, 1);
					table.getModel().setValueAt("", 6, 1);
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					
					String value = (String) property.getValue();
					 FutureContractProperty  proper = FutureContractPropertyEnum.getPropertyValue(FuturesConstants.CONTRACT, value);
					 if(proper != null) {
						 if(futureProductProperties.size() > 2) {
					 futureProductProperties.remove(2);
					 futureProductProperties.set(2, proper);
						 }
					 }
					// table.getModel().setValueAt(proper, rowid, 1);
					 
					
				}
				if(property.getName().equalsIgnoreCase(FuturesConstants.CONTRACT)) {
					if(property.getValue() == null)
						return;
					table.getModel().setValueAt("", 6, 1);
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					String value = (String) property.getValue();
					String exchange = (String) table.getModel().getValueAt(1, 1);
					String curr = (String) table.getModel().getValueAt(2, 1);
					if(commonUTIL.isEmpty(curr) || commonUTIL.isEmpty(exchange))
						return;
					String productname = value+"."+exchange+"."+curr+"."+"FX";
					
					 FutureContractProperty  proper = FutureContractPropertyEnum.getPropertyValue(FuturesConstants.FUTURE, productname);
					 if(proper != null) {
						 if(futureProductProperties.size() > 3) {
					 futureProductProperties.remove(3);
					 futureProductProperties.set(3, proper);
						 }
					 }
					// table.getModel().setValueAt(proper, rowid, 1);
					 
					
				}
				if(property.getName().equalsIgnoreCase(FuturesConstants.FUTURE)) {
					if(property.getValue() == null)
						return;
					String value = (String) property.getValue();
					String exchange = (String) table.getModel().getValueAt(1, 1);
					String curr = (String) table.getModel().getValueAt(2, 1);
					String contract = (String) table.getModel().getValueAt(4, 1);
					if(!commonUTIL.isEmpty(value)) {
					String futureName = "Future."+curr+"."+exchange+"."+contract+"."+value;
					table.getModel().setValueAt(futureName, 6, 1);
					try {
						FutureProduct fp = RemoteServiceUtil.getRemoteProductService().getFutureProductOnQuoteName(futureName);
						table.getModel().setValueAt(new Double(fp.getLots()).doubleValue(), 12, 1);
						table.getModel().setValueAt(new Double(fp.getContract_size()).doubleValue(), 13, 1);
						//Vector<Double> propertyValuesVec = new Vector<Double>();
					//	propertyValuesVec.add(fp.getLots());
						//FutureContractProperty propertylots = FutureContractProperty.createAutoCompleteComboBox(FuturesConstants.LOTS, "Exchange", propertyValuesVec);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
				}
				if(property.getName().equalsIgnoreCase(FuturesConstants.PRICE)) {
					try {
						if(property.getValue() != null) {
					double value = (Double) property.getValue();
					double lots = (Double) table.getModel().getValueAt( 12, 1);
					double contractsize =(Double)  table.getModel().getValueAt( 13, 1);
					table.getModel().setValueAt(value*lots*contractsize, 14, 1);
						}
					}catch(NumberFormatException e) {
						commonUTIL.showAlertMessage("Enter Number only");
						return;
					}
				
					
					
				}
			
				PropertyTableModel<FutureContractProperty> propertyModel = (PropertyTableModel<FutureContractProperty>) table.getModel();
		    
				// TODO Auto-generated method stub
				
			}
	private List<FutureContractProperty> getFutureProductProperties() {

		List<FutureContractProperty> properties = null;

		if (productName.equals(FuturesConstants.FUTURES_FX)) {

			properties = getFUTURE_FX_Properties();

		} else if (productName.equals(FuturesConstants.FUTURES_FX_TRADE)) {

			properties = getFUTURE_FX_Properties();

		}else if (productName.equals(FuturesConstants.FUTURES_FX_TRADE_ATTRIBUTE)) {

			properties = getFUTURE_FX_Properties();

		}

		return properties;

	}
	List<FutureContractProperty> propertiesTrade = null;
	
	
	
	/**
	 * @return the propertiesTrade
	 */
	public List<FutureContractProperty> getPropertiesTrade() {
		return propertiesTrade;
	}

	/**
	 * @param propertiesTrade the propertiesTrade to set
	 */
	public void setPropertiesTrade(List<FutureContractProperty> propertiesTrade) {
		this.propertiesTrade = propertiesTrade;
	}

	private List<FutureContractProperty> getFUTURE_FX_Properties() {

List<FutureContractProperty> properties = new ArrayList<FutureContractProperty>();
		
		if (productName.equals(FuturesConstants.FUTURES_FX)) {
				
			properties = FutureContractProductPropertyEnumList.FUTURE_FX
			.getPropertyList(FuturesConstants.FUTURES_FX);
			return properties;
			
		} if (productName.equals(FuturesConstants.FUTURES_FX_TRADE_ATTRIBUTE)) {
			
		properties = FutureContractProductPropertyEnumList.FUTURES_FX_TRADE_ATTRIBUTES
		.getPropertyList(FuturesConstants.FUTURES_FX_TRADE_ATTRIBUTE);
		return properties;
		
	} else {
		
			properties = FutureContractProductPropertyEnumList.FUTURES_FX_TRADE	.getPropertyList(FuturesConstants.FUTURES_FX_TRADE);
		
			//FutureContractProductPropertyListEnum.FUTURES_FX_TRADE.clearALLList();
		}

		return properties;

	}

}
