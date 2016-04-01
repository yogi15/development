package FutureContract;


import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import constants.CommonConstants;
import constants.FXConstants;

import beans.Holiday;
import beans.StartUPData;

import util.RemoteServiceUtil;


public enum FutureContractPropertyEnum {

	EXCHANGE(FuturesConstants.EXCHANGE, getPropertyValue(FuturesConstants.EXCHANGE), FuturesConstants.EXCHANGE_CATEGORY),
	
	SETTLEMENT_CCY(FuturesConstants.SETTLEMENT_CCY, getPropertyValue(CommonConstants.CURRENCY), FuturesConstants.EXCHANGE_CATEGORY),
	
	UNDERLYING_TYPE(FuturesConstants.UNDERLYING_TYPE, getPropertyValue(FuturesConstants.UNDERLYING_TYPE), FuturesConstants.UNDERLYING_CATEGORY),
	
	CCY_PAIR(FuturesConstants.CCY_PAIR, getPropertyValue(FuturesConstants.CCY_PAIR), FuturesConstants.CONTRACT_CATEGORY),
	
	QUOTE_TYPE(FuturesConstants.QUOTE_TYPE, getPropertyValue(FuturesConstants.QUOTE_TYPE), FuturesConstants.CONTRACT_CATEGORY),
	
	QUOTE_DECIMAL(FuturesConstants.QUOTE_DECIMAL, getDoubleProperty(FuturesConstants.QUOTE_DECIMAL, false), FuturesConstants.CONTRACT_CATEGORY),
	
	CONTRACT_SIZE(FuturesConstants.CONTRACT_SIZE, getIntegerProperty(FuturesConstants.CONTRACT_SIZE), FuturesConstants.CONTRACT_CATEGORY),
	
	MIN_LOTS(FuturesConstants.MIN_LOTS, getIntegerProperty(FuturesConstants.MIN_LOTS), FuturesConstants.CONTRACT_CATEGORY),
	
	TICK_SIZE(FuturesConstants.TICK_SIZE, getIntegerProperty(FuturesConstants.TICK_SIZE), FuturesConstants.CONTRACT_CATEGORY),
	
	TRADING_CYCLE(FuturesConstants.TRADING_CYCLE, getIntegerProperty(FuturesConstants.TRADING_CYCLE), FuturesConstants.CONTRACT_CATEGORY),
	
	SETTLEMENT_METHOD(FuturesConstants.SETTLEMENT_METHOD, getPropertyValue(FuturesConstants.SETTLEMENT_METHOD), FuturesConstants.CONTRACT_CATEGORY),

	EXPIRY_DAY(FuturesConstants.EXPIRY_DAY, createDateRuleDialogBox(FuturesConstants.EXPIRY_DAY), FuturesConstants.CONTRACT_CATEGORY),
	
	TIME_ZONE(FuturesConstants.TIME_ZONE, getPropertyValue(FuturesConstants.TIME_ZONE), FuturesConstants.CONTRACT_CATEGORY),
	
	TIME_MINUTE(FuturesConstants.TIME_MINUTE, getPropertyValue(FuturesConstants.TIME_MINUTE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_TRADING_SCHEDULE(FuturesConstants.LAST_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
		
	FIRST_DELIVERY_TRADING_SCHEDULE(FuturesConstants.FIRST_DELIVERY_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.FIRST_DELIVERY_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_DELIVERY_TRADING_SCHEDULE(FuturesConstants.LAST_DELIVERY_TRADING_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_DELIVERY_TRADING_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	FIRST_NOTIFICATION_SCHEDULE(FuturesConstants.FIRST_NOTIFICATION_SCHEDULE, createDateRuleDialogBox(FuturesConstants.FIRST_NOTIFICATION_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	LAST_NOTIFICATION_SCHEDULE(FuturesConstants.LAST_NOTIFICATION_SCHEDULE, createDateRuleDialogBox(FuturesConstants.LAST_NOTIFICATION_SCHEDULE), FuturesConstants.CONTRACT_CATEGORY),
	
	CONTRACT(FuturesConstants.CONTRACT, getPropertyValue(FuturesConstants.CONTRACT), FuturesConstants.CONTRACT_CATEGORY),
	
	FUTURE(FuturesConstants.FUTURE, getPropertyValue(FuturesConstants.FUTURE), FuturesConstants.CONTRACT_CATEGORY),
	
	ID_TYPE(FuturesConstants.ID_TYPE, getPropertyValue(FuturesConstants.ID_TYPE), FuturesConstants.CONTRACT_CATEGORY),
	
	VALUE(FuturesConstants.VALUE, getStringProperty(FuturesConstants.VALUE), FuturesConstants.CONTRACT_CATEGORY),
	
	BUY_SELL(FuturesConstants.BUY_SELL, getPropertyValue(FuturesConstants.BUY_SELL), FuturesConstants.TRADE),
	
	PRICE(FuturesConstants.PRICE, getDoubleProperty(FuturesConstants.PRICE, true), FuturesConstants.TRADE),
	
	QUANTITY(FuturesConstants.QUANTITY, getDoubleProperty(FuturesConstants.QUANTITY, true), FuturesConstants.TRADE),
	
	NOMINAL(FuturesConstants.NOMINAL, getDoubleProperty(FuturesConstants.NOMINAL, false), FuturesConstants.TRADE);
	
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
	
	public static FutureContractProperty getDoubleProperty(String domainName, boolean isEditable) {
		
		FutureContractProperty property = FutureContractProperty.getProperty(domainName, "Exchange", Double.class);
		
		if (!isEditable) { 
			
			property.setEditable(isEditable);
		}
		
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
				
		Vector<String> propertyValuesVec =	getPropertyValueVec(domainName);
			
		FutureContractProperty property = FutureContractProperty.createAutoCompleteComboBox(domainName, domainName, propertyValuesVec);
				
		return property;
	}
	
	public static FutureContractProperty createDateRuleDialogBox(String domainName) {
		
		return FutureContractProperty.createDateRuleDialogBox(domainName, "Date Rule");
		
		
		
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

}