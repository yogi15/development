package apps.window.util.propertyPane.enumsList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.jidesoft.grid.Property;
 

import apps.window.uti.propertypane.enums.CurrencyDefaultPropertyEnum;
import apps.window.util.property.CurrencyDefaultProperty;

public enum CurrencyDefaultPropertyEnumList {
	  CURRENCYDEFAULT(getCurrencyDefaultPropertyPropertyList());
	
	
	private static List< Property> currencypropertyList = new ArrayList< Property>();
	private static List<Property> propertyList = new ArrayList<Property>();
	public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
	
	
	public static  void  clearALLList() {
		propertyList.clear();
	}
	
	public List<Property> getPropertyList(String name) {
		 
		currencypropertyList =  getCurrencyDefaultPropertyPropertyList();
		 
		return currencypropertyList;
	}

	public void setPropertyList(List< Property> propertyList) {
		this.currencypropertyList = propertyList;
	}

	CurrencyDefaultPropertyEnumList(List< Property> propertyList) {
		setPropertyList(propertyList);
		//this.currencypropertyList = propertyList;

	}
	private static List<Property> getCurrencyDefaultPropertyPropertyList() {

		List< Property> propertyList = new ArrayList< Property>();
		
		//propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.PRIMARYCURRENCY.getProperty());
		//propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.QUTOINGCURRENCY.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.NAME.getProperty());
	 	propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.COUNTRY.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.ISOCODE.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.GROUPLIST.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.SPOTDAYS.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.ROUNDING.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.ROUNDINGMETHOD.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.BDC.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.DECIMAL.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.DAYCOUNT.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.HOLIDAY.getProperty());
		propertyList.add(  CurrencyDefaultPropertyEnum.RATEDECIMAL.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.RATEINDEXCODE.getProperty());
		propertyList.add((CurrencyDefaultProperty) CurrencyDefaultPropertyEnum.PREIODADJ.getProperty());
		propertyList.add(  CurrencyDefaultPropertyEnum.PRESIOUSMETAL.getCommonProperty());
		propertyList.add( CurrencyDefaultPropertyEnum.NONDELIVERY.getCommonProperty());
		propertyList.add( CurrencyDefaultPropertyEnum.CCIL.getCommonProperty());
		propertyList.add( CurrencyDefaultPropertyEnum.CLS.getCommonProperty());
		propertyList.add(  CurrencyDefaultPropertyEnum.ATTRIBUTES.getProperty());

		return propertyList;
	}

	 

}
