package apps.window.uti.propertypane.enums;


import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;


import constants.CommonConstants;
import constants.ReferenceConstants;

import apps.window.util.property.AccountingProperty;
import beans.CurrencyPair;
import beans.FutureContract;
import beans.Holiday;
import beans.LegalEntity;
import beans.Product;
import beans.StartUPData;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;
import util.SortShell;
import util.commonUTIL;


public enum AccountingPropertyEnum {

	ACCOUNT_ID(ReferenceConstants.ACCOUNT_ID, getIntegerProperty(ReferenceConstants.ACCOUNT_ID), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	ACCOUNT_NAME(ReferenceConstants.ACCOUNT_NAME, getStringProperty(ReferenceConstants.ACCOUNT_NAME), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	TYPE(ReferenceConstants.TYPE, getPropertyValue( ReferenceConstants.TYPE), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	DESCRIPTION(ReferenceConstants.DESCRIPTION, getStringProperty(ReferenceConstants.DESCRIPTION), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	CUURENCY(ReferenceConstants.CUURENCY, getPropertyValue(ReferenceConstants.CUURENCY), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	PO(ReferenceConstants.PO, getPropertyValue(ReferenceConstants.PO), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP);
	
	/*LE(ReferenceConstants.LE, getDoubleProperty(ReferenceConstants.LE), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	CREATIONDATE(ReferenceConstants.CREATIONDATE, getIntegerProperty(ReferenceConstants.CREATIONDATE), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	CLOSINGDATE(ReferenceConstants.CLOSINGDATE, getIntegerProperty(ReferenceConstants.CLOSINGDATE), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP),
	
	PARENT_AC(ReferenceConstants.PARENT_AC, getPropertyValue(ReferenceConstants.PARENT_AC), ReferenceConstants.ACCOUNTING_CATEGORY_SETUP);*/
		
	private String propertyName;
	private String _description;
	private AccountingProperty property;

	AccountingPropertyEnum(String propertyName, AccountingProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public AccountingProperty getProperty(String propertyName, AccountingProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	
		return property;
	}
	
	public AccountingProperty getProperty() {
		return property;
	}
	public void setProperty(AccountingProperty property) {
		this.property = property;
	}
	
	public static AccountingProperty getIntegerProperty(String domainName) {
		
		AccountingProperty property = AccountingProperty.createIntegerLeftAlignProperty(domainName, "Exchange", Integer.class);
		
		return property;
		
	}
	
	public static AccountingProperty getStringProperty(String domainName) {
		
		AccountingProperty property = AccountingProperty.getProperty(domainName, "Exchange", String.class);
		
		return property;
		
	}
	
	public static AccountingProperty getDoubleProperty(String domainName) {
		
		AccountingProperty property = AccountingProperty.createDoubleLeftAlignProperty(domainName, "Exchange", Double.class);
		
		return property;
		
	}
	

	public static AccountingProperty getPropertyValue(String domainName) {
		Vector<String> propertyValuesVec = null;
	
	   if(domainName.equalsIgnoreCase(ReferenceConstants.CUURENCY))  {
		 propertyValuesVec = getPropertyValueVec(domainName);
		
	   } else  if(domainName.equalsIgnoreCase(ReferenceConstants.PO))  {
		   propertyValuesVec =	getPropertyValueCP(domainName);
	   }  else  if(domainName.equalsIgnoreCase(ReferenceConstants.LE))  {
		   propertyValuesVec =	getPropertyValueCP(domainName);
	   } else  if(domainName.equalsIgnoreCase(ReferenceConstants.TYPE))  {
		  propertyValuesVec = getPropertyValueVec("AccType");		   
	   } else {
		   propertyValuesVec =	getPropertyValueVec(domainName);
	   }
	   
		
			
		AccountingProperty property = AccountingProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
	}
	
	private static Vector<String> getPropertyValueCP(String domainName) {
		
		Vector<String> propertyValuesVec = null;
		
		Collection<String> data = null;
		try {
			data = RemoteServiceUtil.getRemoteReferenceDataService().selectLEonWhereClause(" role = '"
					+ domainName + "'");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<String> iterator = data.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			String dataStr = iterator.next();
			System.out.println(dataStr);
			propertyValuesVec.add(iterator.next());
		}
		
		return propertyValuesVec;
		
	}
	
	private static Vector<String> getPropertyValueVec(String domainName) {
		
		Vector<String> propertyValuesVec = new Vector<String>();
		
		StartUPData startUPData = new StartUPData();
		startUPData.setName(domainName);
		//AccountingProperty property = new AccountingProperty();
			
		Collection<StartUPData> startUPDatas = new Vector<StartUPData>();
		
		try {
		 startUPDatas =	RemoteServiceUtil.getRemoteReferenceDataService().selectStartUPData(startUPData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<StartUPData> iterator = startUPDatas.iterator();
		while (iterator.hasNext()) {
			StartUPData data = iterator.next();
			propertyValuesVec.add(data.getValue());
		}
		
		return propertyValuesVec;
	}
private static Vector<String> getPropertyValueVecForCurrercyPair(String domainName) {
		
		Vector<String> propertyValuesVec = new Vector<String>();
		
		StartUPData startUPData = new StartUPData();
		startUPData.setName(domainName);
		//AccountingProperty property = new AccountingProperty();
			
		Collection<CurrencyPair> currencyPairs = new Vector<CurrencyPair>();
		
		try {
			currencyPairs =	RemoteServiceUtil.getRemoteReferenceDataService().selectALLCurrencyPair();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<CurrencyPair> iterator = currencyPairs.iterator();
		while (iterator.hasNext()) {
			CurrencyPair cp = iterator.next();
			propertyValuesVec.add(cp.getPrimary_currency()+"/"+cp.getQuoting_currency());
		}
		
		return propertyValuesVec;
	}


    private static Vector<String> getPropertyValueVecForExchange(String domainName) {
		
		Vector<String> propertyValuesVecEX = new Vector<String>();
		
		StartUPData startUPData = new StartUPData();
		startUPData.setName(domainName);
		//AccountingProperty property = new AccountingProperty();
			
		Collection<LegalEntity> exchanges = new Vector<LegalEntity>();
		
		try {
			exchanges =	RemoteServiceUtil.getRemoteReferenceDataService().getALLExchanges();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<LegalEntity> iterator = exchanges.iterator();
		while (iterator.hasNext()) {
			LegalEntity le = iterator.next();
			propertyValuesVecEX.add(le.getAlias());
		}
		
		return propertyValuesVecEX;
	}
    public static AccountingProperty getDoubleProperty(String domainName, boolean isEditable) {
		
		AccountingProperty property = AccountingProperty.createDoubleLeftAlignProperty(domainName, "Exchange", Double.class);
		
		if (!isEditable) { 
			
			property.setEditable(isEditable);
		}
		
		return property;
		
	}
public static AccountingProperty getAttributeProperty(String domainName, boolean isEditable) {
	
	AccountingProperty property = AccountingProperty.getProperty(domainName, "Exchange", String.class);
	
	if (!isEditable) { 
		
		property.setEditable(isEditable);
	}
	
	return property;
	
}
}