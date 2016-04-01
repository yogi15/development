package apps.window.util.propertyPane.enumsList;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import util.ReferenceDataCache; 
import util.commonUTIL;

import apps.window.uti.propertypane.enums.FutureContractPropertyEnum;
import apps.window.util.property.FutureContractProperty;
import beans.StartUPData;
 

import constants.FuturesConstants;

public enum FutureContractProductPropertyEnumList {

	FUTURE_FX(getFutureFXPropertyList()),
	FUTURES_FX_TRADE(getFutureFXTradePropertyList()),
	FUTURES_FX_TRADE_ATTRIBUTES(getFutureFXTradeAttributesPropertyList());
	
	private List<FutureContractProperty> propertyList = new ArrayList<FutureContractProperty>();
	public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
	
	
	public void clearALLList() {
		propertyList.clear();
	}
	
	public List<FutureContractProperty> getPropertyList(String name) {
		if(name.equalsIgnoreCase(FuturesConstants.FUTURES_FX_TRADE)) 
			return getFutureFXTradePropertyList();
		if(name.equalsIgnoreCase(FuturesConstants.FUTURES_FX_TRADE_ATTRIBUTE)) 
			return getFutureFXTradeAttributesPropertyList();
		if(name.equalsIgnoreCase(FuturesConstants.FUTURES_FX)) 
			return getFutureFXPropertyList();
		return propertyList;
	}

	public void setPropertyList(List<FutureContractProperty> propertyList) {
		this.propertyList = propertyList;
	}

	FutureContractProductPropertyEnumList(List<FutureContractProperty> propertyList) {

		this.propertyList = propertyList;

	}

/*	static {
		
		propertyListHash.put("Product Name"; FutureProductPropertyCategory.EXCHANGE.getCategoryName());
		propertyListHash.put("Symbol"; FutureProductPropertyCategory.EXCHANGE.getCategoryName());
		propertyListHash.put("Exchange"; FutureProductPropertyCategory.EXCHANGE.getCategoryName());
		propertyListHash.put("Settlement CCY"; FutureProductPropertyCategory.EXCHANGE.getCategoryName());
		propertyListHash.put("Underlying Type"; FutureProductPropertyCategory.UNDERLYINGTYPE.getCategoryName());
		propertyListHash.put("Quote Type"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Quote Decimal"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Contract Size"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Minimum # of lots"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Tick Size"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Trading Cycle"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Settlement Method"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Expiry Day"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Business Day Convention"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Calender"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Last Trading Day"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Last Trading Time"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Daily Settlement Price"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Final Settlement Price"; FutureProductPropertyCategory.CONTRACT.getCategoryName());
		propertyListHash.put("Product Attributes"; FutureProductPropertyCategory.CONTRACT.getCategoryName());

	}*/
	
	private static List<FutureContractProperty> getFutureFXPropertyList() {

		List<FutureContractProperty> propertyList = new ArrayList<FutureContractProperty>();
		
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.EXCHANGE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.NAME.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.SETTLEMENT_CCY.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.UNDERLYING_TYPE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.CCY_PAIR.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.QUOTE_TYPE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.QUOTE_DECIMAL.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.CONTRACT_SIZE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.MIN_LOTS.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.TICK_SIZE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.TRADING_CYCLE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.SETTLEMENT_METHOD.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.EXPIRY_DAY.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.TIME_ZONE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.TIME_MINUTE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.LAST_TRADING_SCHEDULE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.FIRST_DELIVERY_TRADING_SCHEDULE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.LAST_DELIVERY_TRADING_SCHEDULE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.FIRST_NOTIFICATION_SCHEDULE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.LAST_NOTIFICATION_SCHEDULE.getProperty());
	//	propertyList.add(new FutureContractProperty("Attribute", "Desc", String.class));

		return propertyList;
	}
	private static List<FutureContractProperty> getFutureFXTradePropertyList() {

		List<FutureContractProperty> propertyList12 = new ArrayList<FutureContractProperty>();
		
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.EXCHANGE.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.SETTLEMENT_CCY.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.CONTRACT.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.FUTURE.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.FUTURENAME.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.ID_TYPE.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.VALUE.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.BUY_SELL.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.PRICE.getProperty());
		//propertyList.add((FutureContractProperty) FutureContractPropertyEnum.QUANTITY.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.LOTS.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.CONTRACTSIZE.getProperty());
		propertyList12.add((FutureContractProperty) FutureContractPropertyEnum.NOMINAL.getProperty());
		
		
		
		
		return propertyList12;
	}
	private static List<FutureContractProperty> getFutureFXTradeAttributesPropertyList() {

		List<FutureContractProperty> propertyList = new ArrayList<FutureContractProperty>();
		
		Vector attributes = getAttributes();
		if(!commonUTIL.isEmpty(attributes)) {
			for(int i=0;i<attributes.size();i++) {
				StartUPData att = (StartUPData) attributes.get(i);
				FutureContractProperty atttributeProperty = new FutureContractProperty(att.getName(), att.getName(),String.class,"Attributes");
				propertyList.add(atttributeProperty);
			}
		}
		
		
		return propertyList;
	}
	
	
	private static Vector getAttributes() {
		Vector attributes = null;
	
			attributes = (Vector) ReferenceDataCache.getStarupData("TradeAttribute");
		
		
			// TODO Auto-generated catch block
		
		return attributes;
		
	}
	
}
