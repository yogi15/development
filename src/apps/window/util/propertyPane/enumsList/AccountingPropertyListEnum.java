package apps.window.util.propertyPane.enumsList;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import apps.window.uti.propertypane.enums.AccountingPropertyEnum;
import apps.window.util.property.AccountingProperty;
import constants.ReferenceConstants;

public enum AccountingPropertyListEnum {

	ACCOUNTING(getAccountingProperties());
	
	
	private List<AccountingProperty> propertyList = new ArrayList<AccountingProperty>();
	public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
	
	
	public void clearALLList() {
		propertyList.clear();
	}
	
	public List<AccountingProperty> getPropertyList(String name) {
		if(name.equalsIgnoreCase(ReferenceConstants.ACCOUNTING)) 
			return getAccountingProperties();
		
		return propertyList;
	}

	public void setPropertyList(List<AccountingProperty> propertyList) {
		this.propertyList = propertyList;
	}

	AccountingPropertyListEnum(List<AccountingProperty> propertyList) {

		this.propertyList = propertyList;

	}
	
	
	private static List<AccountingProperty> getAccountingProperties() {

		List<AccountingProperty> propertyList = new ArrayList<AccountingProperty>();
		
		propertyList.add((AccountingProperty) AccountingPropertyEnum.ACCOUNT_ID.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.ACCOUNT_NAME.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.TYPE.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.CUURENCY.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.DESCRIPTION.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.PO.getProperty());
		/*propertyList.add((AccountingProperty) AccountingPropertyEnum.LE.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.CREATIONDATE.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.CLOSINGDATE.getProperty());
		propertyList.add((AccountingProperty) AccountingPropertyEnum.PARENT_AC.getProperty());*/
	

		return propertyList;
	}

}
