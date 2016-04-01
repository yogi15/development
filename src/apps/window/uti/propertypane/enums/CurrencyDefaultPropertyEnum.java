package apps.window.uti.propertypane.enums;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.jidesoft.grid.Property;

import util.RemoteServiceUtil; 
import beans.StartUPData;

import apps.window.util.property.CurrencyDefaultProperty;
import apps.window.util.propertyUtil.CommonPropertyUtil;
import constants.CommonConstants; 
import constants.CurrencyDefaultConstant; 

public enum CurrencyDefaultPropertyEnum    {
	 
	 
	
	
   PRIMARYCURRENCY(CurrencyDefaultConstant.PRIMARY_CURRENCY, getPropertyValue(CommonConstants.CURRENCY), CurrencyDefaultConstant.CURRENCY_PAIR_CATEGORY),
	
	QUTOINGCURRENCY(CurrencyDefaultConstant.QUOTING_CURRENCY, getPropertyValue(CommonConstants.CURRENCY), CurrencyDefaultConstant.CURRENCY_PAIR_CATEGORY),
	NAME(CurrencyDefaultConstant.NAME, getStringProperty( CurrencyDefaultConstant.NAME), CurrencyDefaultConstant.CURRENCY_PAIR_CATEGORY),
    COUNTRY(CurrencyDefaultConstant.COUNTRY, getPropertyValue(CurrencyDefaultConstant.COUNTRY), CurrencyDefaultConstant.CURRENCY_PAIR_CATEGORY),
   ISOCODE(CurrencyDefaultConstant.ISO_CODE, getStringProperty(CurrencyDefaultConstant.ISO_CODE), CurrencyDefaultConstant.CURRENCY_PAIR_CATEGORY),
   GROUPLIST(CurrencyDefaultConstant.GROUPLIST, getPropertyValue(CurrencyDefaultConstant.GROUPLIST), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   SPOTDAYS(CurrencyDefaultConstant.SPOTDAYS, getIntegerProperty(CurrencyDefaultConstant.SPOTDAYS), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   ROUNDINGMETHOD(CurrencyDefaultConstant.ROUNDING_METHOD, getPropertyValue(CurrencyDefaultConstant.ROUNDING_METHOD), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   ROUNDING	(CurrencyDefaultConstant.ROUNDING , getIntegerProperty(CurrencyDefaultConstant.ROUNDING), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   ATTRIBUTES(CurrencyDefaultConstant.ATTRIBUTE, getPropertyValue(CurrencyDefaultConstant.ATTRIBUTE), CurrencyDefaultConstant.ATTRIBUTE_CATEGORY),
   DECIMAL(CurrencyDefaultConstant.DECIMAL, getIntegerProperty(CurrencyDefaultConstant.DECIMAL), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   RATEDECIMAL(CurrencyDefaultConstant.RATE_DECIMAL, getIntegerProperty(CurrencyDefaultConstant.RATE_DECIMAL), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   RATEINDEXCODE	(CurrencyDefaultConstant.RATE_INDEX_CODE, getPropertyValue(CurrencyDefaultConstant.RATE_INDEX_CODE), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   DAYCOUNT(CurrencyDefaultConstant.DAYCOUNT, getPropertyValue(CurrencyDefaultConstant.DAYCOUNT), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   HOLIDAY(CurrencyDefaultConstant.HOLIDAY, getPropertyValue(CurrencyDefaultConstant.HOLIDAY), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   BDC(CurrencyDefaultConstant.BDC, getPropertyValue(CurrencyDefaultConstant.BDC), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   PREIODADJ(CurrencyDefaultConstant.PERIOD_AJD_VALUE, getPropertyValue(CurrencyDefaultConstant.PERIOD_AJD_VALUE), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   CCIL(CurrencyDefaultConstant.CCIL,getBooleanPropertyValue(CurrencyDefaultConstant.CCIL,CurrencyDefaultConstant.CCIL,CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   CLS(CurrencyDefaultConstant.CLS, getBooleanPropertyValue(CurrencyDefaultConstant.CLS,CurrencyDefaultConstant.CLS,CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   NONDELIVERY(CurrencyDefaultConstant.NON_DELIVERABLE, getBooleanPropertyValue(CurrencyDefaultConstant.NON_DELIVERABLE,CurrencyDefaultConstant.NON_DELIVERABLE,CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY),
   PRESIOUSMETAL(CurrencyDefaultConstant.PRESIOUS_METAL, getBooleanPropertyValue(CurrencyDefaultConstant.PRESIOUS_METAL,CurrencyDefaultConstant.PRESIOUS_METAL,CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY), CurrencyDefaultConstant.CURRENCY_BUSINESS_CATEGORY);
   

	private String propertyName;
	private String _description;
	private CurrencyDefaultProperty property;
	public  Property commonproperty;

	CurrencyDefaultPropertyEnum(String propertyName, CurrencyDefaultProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	}
	public CurrencyDefaultProperty getProperty() {
		return property;
	}
	public Property getCommonProperty() {
		return commonproperty;
	}
	public void setProperty(CurrencyDefaultProperty property) {
		this.property = property;
	}
	CurrencyDefaultPropertyEnum(String propertyName,  Property commonproperty, String category) {
		this.propertyName = propertyName;
		this.commonproperty = commonproperty;
		commonproperty.setName(propertyName);
		commonproperty.setCategory(category);
	}
	public static CurrencyDefaultProperty getPropertyValue(String domainName) {
		Vector<String> propertyValuesVec = null; 
		 propertyValuesVec = getPropertyValueVecfromStartUpData(domainName);
		 CurrencyDefaultProperty property = CurrencyDefaultProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
	}
	public static Property getBooleanPropertyValue(String name,String displayName,String category) {
		Vector<String> propertyValuesVec = null; 
		 
		Property property = CommonPropertyUtil.createBooleanProperty(name, displayName, category);
		
		return property;
	}
public static CurrencyDefaultProperty getIntegerProperty(String domainName) {
		
	CurrencyDefaultProperty property = CurrencyDefaultProperty.getProperty(domainName, "Exchange", Integer.class);
		
		return property;
		
	}

private static Vector<String> getPropertyValueVecfromStartUpData(
			String domainName) {
		// TODO Auto-generated method stub
	Vector<String> propertyValuesVec = new Vector<String>();
	
	StartUPData startUPData = new StartUPData();
	startUPData.setName(domainName);
	//FutureContractProperty property = new FutureContractProperty();
		
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

public static CurrencyDefaultProperty getStringProperty(String domainName) {
		
		CurrencyDefaultProperty property = CurrencyDefaultProperty.getProperty(domainName, "Exchange", String.class);
		
		return property;
		
	}

}
