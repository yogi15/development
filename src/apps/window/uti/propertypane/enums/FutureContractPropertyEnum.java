package apps.window.uti.propertypane.enums;


import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;


import constants.CommonConstants;
import constants.FuturesConstants;

import apps.window.util.property.FutureContractProperty;
import beans.CurrencyPair;
import beans.FutureContract;
import beans.Holiday;
import beans.LegalEntity;
import beans.Product;
import beans.StartUPData;
 
import util.RemoteServiceUtil;  


public enum FutureContractPropertyEnum {

	EXCHANGE(FuturesConstants.EXCHANGE, getPropertyValue(FuturesConstants.EXCHANGE), FuturesConstants.EXCHANGE_CATEGORY),
	
	SETTLEMENT_CCY(FuturesConstants.SETTLEMENT_CCY, getPropertyValue(CommonConstants.CURRENCY), FuturesConstants.EXCHANGE_CATEGORY),
	NAME(FuturesConstants.NAME, getStringProperty( FuturesConstants.NAME), FuturesConstants.EXCHANGE_CATEGORY),
	//TYPE(FuturesConstants.NAME, getStringProperty( FuturesConstants.NAME), FuturesConstants.NAME),
	UNDERLYING_TYPE(FuturesConstants.UNDERLYING_TYPE, getPropertyValue(FuturesConstants.UNDERLYING_TYPE), FuturesConstants.UNDERLYING_CATEGORY),
	
	CCY_PAIR(FuturesConstants.CCY_PAIR, getPropertyValue(FuturesConstants.CCY_PAIR), FuturesConstants.CONTRACT_CATEGORY),
	
	QUOTE_TYPE(FuturesConstants.QUOTE_TYPE, getPropertyValue(FuturesConstants.QUOTE_TYPE), FuturesConstants.CONTRACT_CATEGORY),
	
	QUOTE_DECIMAL(FuturesConstants.QUOTE_DECIMAL, getDoubleProperty(FuturesConstants.QUOTE_DECIMAL), FuturesConstants.CONTRACT_CATEGORY),
	
	CONTRACT_SIZE(FuturesConstants.CONTRACT_SIZE, getIntegerProperty(FuturesConstants.CONTRACT_SIZE), FuturesConstants.CONTRACT_CATEGORY),
	
	MIN_LOTS(FuturesConstants.MIN_LOTS, getIntegerProperty(FuturesConstants.MIN_LOTS), FuturesConstants.CONTRACT_CATEGORY),
	
	TICK_SIZE(FuturesConstants.TICK_SIZE, getIntegerProperty(FuturesConstants.TICK_SIZE), FuturesConstants.CONTRACT_CATEGORY),
	
	TRADING_CYCLE(FuturesConstants.TRADING_CYCLE, getIntegerProperty(FuturesConstants.TRADING_CYCLE), FuturesConstants.CONTRACT_CATEGORY),
	
	SETTLEMENT_METHOD(FuturesConstants.SETTLEMENT_METHOD, getPropertyValue(FuturesConstants.SETTLEMENT_METHOD), FuturesConstants.CONTRACT_CATEGORY),

	EXPIRY_DAY(FuturesConstants.EXPIRY_DAY, createDateRuleDialogBox(FuturesConstants.EXPIRY_DAY), FuturesConstants.CONTRACT_CATEGORY),
	
	TIME_ZONE(FuturesConstants.TIME_ZONE, getPropertyValue(FuturesConstants.TIME_ZONE), FuturesConstants.CONTRACT_CATEGORY),
	
	TIME_MINUTE(FuturesConstants.TIME_MINUTE, createTimeHHMMBox(FuturesConstants.TIME_MINUTE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_TRADING_SCHEDULE(FuturesConstants.LAST_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
		
	FIRST_DELIVERY_TRADING_SCHEDULE(FuturesConstants.FIRST_DELIVERY_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.FIRST_DELIVERY_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_DELIVERY_TRADING_SCHEDULE(FuturesConstants.LAST_DELIVERY_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_DELIVERY_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	FIRST_NOTIFICATION_SCHEDULE(FuturesConstants.FIRST_NOTIFICATION_SCHEDULE, createDateRuleDialogBox(FuturesConstants.FIRST_NOTIFICATION_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_NOTIFICATION_SCHEDULE(FuturesConstants.LAST_NOTIFICATION_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_NOTIFICATION_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	CONTRACT(FuturesConstants.CONTRACT, getPropertyValue(FuturesConstants.CONTRACT), FuturesConstants.CONTRACT_CATEGORY),
	
	FUTURE(FuturesConstants.FUTURE, getPropertyValue(FuturesConstants.FUTURE), FuturesConstants.CONTRACT_CATEGORY),
	FUTURENAME(FuturesConstants.FUTURENAME, getPropertyValue(FuturesConstants.FUTURENAME), FuturesConstants.CONTRACT_CATEGORY),
	
	ID_TYPE(FuturesConstants.ID_TYPE, getPropertyValue(FuturesConstants.ID_TYPE), FuturesConstants.CONTRACT_CATEGORY),
	
	VALUE(FuturesConstants.VALUE, getStringProperty(FuturesConstants.VALUE), FuturesConstants.CONTRACT_CATEGORY),
	
	BUY_SELL(FuturesConstants.BUY_SELL, getPropertyValue(FuturesConstants.BUY_SELL), FuturesConstants.TRADE),
	
	PRICE(FuturesConstants.PRICE, getDoubleProperty(FuturesConstants.PRICE, true), FuturesConstants.TRADE),
	
	QUANTITY(FuturesConstants.QUANTITY, getDoubleProperty(FuturesConstants.QUANTITY, true), FuturesConstants.TRADE),
	LOTS(FuturesConstants.LOTS, getDoubleProperty(FuturesConstants.LOTS, true), FuturesConstants.TRADE),
	CONTRACTSIZE(FuturesConstants.CONTRACTSIZE, getDoubleProperty(FuturesConstants.CONTRACTSIZE, true), FuturesConstants.TRADE),
	
	NOMINAL(FuturesConstants.NOMINAL, getDoubleProperty(FuturesConstants.NOMINAL, true), FuturesConstants.TRADE);
	
	//BDC(FuturesConstants.BDC, getPropertyValue(CommonConstants.DATE_ROLL), FuturesConstants.CONTRACT_CATEGORY),
	
	//CALENDER(FuturesConstants.CALENDER, getCalenderPropertyValue(CommonConstants.HOLIDAY), FuturesConstants.CONTRACT_CATEGORY),
	
	//LAST_TRADING_DAY(FuturesConstants.LAST_TRADING_DAY, getPropertyValue(FuturesConstants.LAST_TRADING_DAY), FuturesConstants.CONTRACT_CATEGORY),
	
	//LAST_TRADING_TIME(FuturesConstants.LAST_TRADING_TIME, getPropertyValue(FuturesConstants.LAST_TRADING_TIME), FuturesConstants.CONTRACT_CATEGORY),
	
	//DAILY_SETTLEMENT_PRICE(FuturesConstants.DAILY_SETTLEMENT_PRICE, getPropertyValue(FuturesConstants.DAILY_SETTLEMENT_PRICE), FuturesConstants.CONTRACT_CATEGORY),
	
	//FINAL_SETTLEMENT_PRICE(FuturesConstants.FINAL_SETTLEMENT_PRICE, getPropertyValue(FuturesConstants.FINAL_SETTLEMENT_PRICE), FuturesConstants.CONTRACT_CATEGORY),
	
	//PRODUCT_ATTRIBUTES(FuturesConstants.PRODUCT_ATTRIBUTES, getPropertyValue(FuturesConstants.PRODUCT_ATTRIBUTES), FuturesConstants.PRODUCT_ATTRIBUTES);
	
	
	
	private String propertyName;
	private String _description;
	private FutureContractProperty property;

	FutureContractPropertyEnum(String propertyName, FutureContractProperty property, String category) {
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

	public FutureContractProperty getProperty(String propertyName, FutureContractProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	
		return property;
	}
	
	public FutureContractProperty getProperty() {
		return property;
	}
	public void setProperty(FutureContractProperty property) {
		this.property = property;
	}
	
	public static FutureContractProperty getIntegerProperty(String domainName) {
		
		FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", Integer.class);
		
		return property;
		
	}
	
	public static FutureContractProperty getStringProperty(String domainName) {
		
		FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", String.class);
		
		return property;
		
	}
	
	public static FutureContractProperty getDoubleProperty(String domainName) {
		
		FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", Double.class);
		
		return property;
		
	}
	
	public static FutureContractProperty getCalenderPropertyValue(String domainName) {
		
		Vector<String> propertyValuesVec = new Vector<String>();
			
		Collection<Holiday> holiday = new Vector<Holiday>();
		
		try {
			holiday =	RemoteServiceUtil.getRemoteReferenceDataService().selectALLHolidays();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Holiday> iterator = holiday.iterator();
		while (iterator.hasNext()) {
			Holiday data = iterator.next();
			propertyValuesVec.add(data.getCountry());
		}
		
		FutureContractProperty property = FutureContractProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
		
	}

	public static FutureContractProperty getPropertyValue(String domainName) {
		Vector<String> propertyValuesVec = null;
	
	   if(domainName.equalsIgnoreCase(FuturesConstants.EXCHANGE))  {
		 propertyValuesVec = getPropertyValueVecForExchange(domainName);
		
	   } else  if(domainName.equalsIgnoreCase(FuturesConstants.CCY_PAIR))  {
		   propertyValuesVec =	getPropertyValueVecForCurrercyPair(domainName);
	   }  else  if(domainName.equalsIgnoreCase(FuturesConstants.TIME_ZONE))  {
		   propertyValuesVec =	getPropertyValueTimeZone(domainName);
	   } else  if(domainName.equalsIgnoreCase(FuturesConstants.BUY_SELL))  {
		   Vector<String> propertyValuesVecBUYSELL = new Vector<String>();
		   propertyValuesVecBUYSELL.add("SELL");
		   propertyValuesVecBUYSELL.add("BUY");
		   propertyValuesVec = propertyValuesVecBUYSELL;
		   
	   } else {
		   propertyValuesVec =	getPropertyValueVec(domainName);
	   }
	   
		
			
		FutureContractProperty property = FutureContractProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
	}
	
	public static FutureContractProperty getPropertyValue(String domainName,String value) {
		Vector<String> propertyValuesVec = null;
	
	   if(domainName.equalsIgnoreCase(FuturesConstants.CONTRACT))  {
		 propertyValuesVec = getContractNames(value,"");
	   } else  if(domainName.equalsIgnoreCase(FuturesConstants.FUTURE))  {
		   propertyValuesVec =	getPropertyValueVecFuture(value);
	   }  else  if(domainName.equalsIgnoreCase(FuturesConstants.TIME_ZONE))  {
		   propertyValuesVec =	getPropertyValueTimeZone(domainName);
	   } else {
		   propertyValuesVec =	getPropertyValueVec(domainName);
	   }
	   
		
			
		FutureContractProperty property = FutureContractProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
	}
	private static Vector<String> getPropertyValueVecFuture(String futureProductName) {
		// TODO Auto-generated method stub
		 Vector products = null;
			try {
				products = (Vector)          RemoteServiceUtil.getRemoteProductService().getFutureContract(futureProductName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Vector<String> propertyValuesVec = new Vector<String>();
	          if(products == null)
	        	  return propertyValuesVec;
	          for(int i=0;i<products.size();i++) {
	        	  FutureContract product = (FutureContract) products.get(i);
	        	 String name =  product.getQuoteName().substring(product.getQuoteName().lastIndexOf(".")-3, product.getQuoteName().length());
	        	
	        	  propertyValuesVec.add(name);
	          }
	          return propertyValuesVec;
			
	}

	public static FutureContractProperty getPropertyValue(String domainName,String exchange,String currency) {
		Vector<String> propertyValuesVec = null;
	
	   if(domainName.equalsIgnoreCase(FuturesConstants.CONTRACT))  {
		 propertyValuesVec = getContractNames(exchange,currency);
	   } else  if(domainName.equalsIgnoreCase(FuturesConstants.CCY_PAIR))  {
		   propertyValuesVec =	getPropertyValueVecForCurrercyPair(domainName);
	   }  else  if(domainName.equalsIgnoreCase(FuturesConstants.TIME_ZONE))  {
		   propertyValuesVec =	getPropertyValueTimeZone(domainName);
	   } else {
		   propertyValuesVec =	getPropertyValueVec(domainName);
	   }
	   
		
			
		FutureContractProperty property = FutureContractProperty.createAutoCompleteComboBox(domainName, "Exchange", propertyValuesVec);
		
		return property;
	}
	public static Vector<String> getPropertyValueTimeZone(String domainName) {
		Vector<String> propertyValuesVec = new Vector<String>();
		String tzs[] = TimeZone.getAvailableIDs();
	    for (int i = 0; i < tzs.length; i++)
	    	propertyValuesVec.add(tzs[i]);
	 //   SortShell.sort(propertyValuesVec);
	   
	    return propertyValuesVec;
	}
	
	public static FutureContractProperty createDateRuleDialogBox(String domainName) {
		
		return FutureContractProperty.createDateRuleDialogBox(domainName, "Date Rule");
		
		
		
	}public static FutureContractProperty createTimeHHMMBox(String domainName) {
		
		return FutureContractProperty.createTimeHHMMBox(domainName, "Time : Minute");
		
		
		
	}
	private static Vector<String> getContractNames(String exchangeName,String currency) {
		          Vector products = null;
				try {
					products = (Vector)          RemoteServiceUtil.getRemoteProductService().getFutureProduct(exchangeName, currency);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          Vector<String> propertyValuesVec = new Vector<String>();
		          
		          for(int i=0;i<products.size();i++) {
		        	  Product product = (Product) products.get(i);
		        	 String name =  product.getProductname().replace(".", ":");
		        	 String n1 [] = name.split(":");
		        	  propertyValuesVec.add(n1[0]);
		          }
		          return propertyValuesVec;
	}
	private static Vector<String> getPropertyValueVec(String domainName) {
		
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
private static Vector<String> getPropertyValueVecForCurrercyPair(String domainName) {
		
		Vector<String> propertyValuesVec = new Vector<String>();
		
		StartUPData startUPData = new StartUPData();
		startUPData.setName(domainName);
		//FutureContractProperty property = new FutureContractProperty();
			
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
		//FutureContractProperty property = new FutureContractProperty();
			
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
public static FutureContractProperty getDoubleProperty(String domainName, boolean isEditable) {
		
		FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", Double.class);
		
		if (!isEditable) { 
			
			property.setEditable(isEditable);
		}
		
		return property;
		
	}
public static FutureContractProperty getAttributeProperty(String domainName, boolean isEditable) {
	
	FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", String.class);
	
	if (!isEditable) { 
		
		property.setEditable(isEditable);
	}
	
	return property;
	
}
}