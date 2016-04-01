package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL;

import apps.window.util.propertyPane.enumsList.CurrencyDefaultPropertyEnumList;
import apps.window.util.propertyUtil.BasePropertyTable;
import beans.CurrencyDefault;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.CurrencyDefaultConstant;
import constants.FuturesConstants;

public class CurrencyDefaultPropertyTable extends BasePropertyTable implements PropertyChangeListener    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	List< Property> currencyDefaultProperties = null;
	CurrencyDefault currencyDef = null;
	
	public CurrencyDefaultPropertyTable() {
		 
	}
	
	/**
	 * @return the currencyDef
	 */
	public CurrencyDefault getCurrencyDef() {
		return currencyDef;
	}

	/**
	 * @param currencyDef the currencyDef to set
	 */
	public void setCurrencyDef(CurrencyDefault currencyDef) {
		this.currencyDef = currencyDef;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		PropertyTable table = (PropertyTable) evt.getSource();
		
		int row = table.getSelectedRow();
		
		if(row == -1  )
			return;
		 
	 
		Property property = table.getSelectedProperty();
		  
		 
		
		
		 
	}
	String name = "";
	public CurrencyDefaultPropertyTable(String name,CurrencyDefault bean) {
		
		this.name = name;
		setCurrencyDef(bean);
		//setCurrencyDefaultProperties();

	}
		 public PropertyTable getCurrencyDefaultTable() { 
			 currencyDefaultProperties = getCurrencyDefaultProperties(); 
           return  makePropertyTable(currencyDefaultProperties);
		 
	    }

		 
		 
		 
		 public void setCurrencyDefultValues() {
			 try {
			 currencyDef.setCurrency_code(  getValueAt(1, 1).toString());
			 currencyDef.setCountry(   getValueAt(2, 1).toString());
			 currencyDef.setIso_code(  getValueAt(3, 1).toString());
			 if(((Boolean) getValueAt(16,1)) == true)  {
				 currencyDef.setNon_deliverable_b(1);
			 }
			 } catch(Exception e) {
				 commonUTIL.displayError("CurrencyDefaultPropertyTable", "setCurrencyDefultValues", e);
				 return;
			 }
			 
		 }
		 
		 public void clearPropertyValues() {
			// CurrencyDefaultPropertyEnumList.clearALLList();
			 try {
			 
			  setValueAt(null, 1, 1);
			  setValueAt(null, 2, 1);
			  setValueAt(null, 16, 1);

			  setValueAt(null, 17, 1);
			 }catch(Exception e) {
				 commonUTIL.displayError("CurrencyDefaultPropertyTable", "setCurrencyDefultValues", e);
				 return;
			 }
			 
		 }
		 
		private List< Property> getCurrencyDefaultProperties() {
			// TODO Auto-generated method stub
			List< Property> properties = null;
			properties = CurrencyDefaultPropertyEnumList.CURRENCYDEFAULT.getPropertyList(CurrencyDefaultConstant.CURRENCYDEFAULT);
			return properties;
		} 
}
