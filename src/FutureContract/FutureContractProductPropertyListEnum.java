package FutureContract;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.jidesoft.grid.Property;

public enum FutureContractProductPropertyListEnum {

	FUTURE_FX(getFutureFXPropertyList()),
	FUTURES_FX_TRADE(getFutureFXTradePropertyList());
	
	private List<FutureContractProperty> propertyList = new ArrayList<FutureContractProperty>();
	public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
	
	public List<FutureContractProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<FutureContractProperty> propertyList) {
		this.propertyList = propertyList;
	}

	FutureContractProductPropertyListEnum(List<FutureContractProperty> propertyList) {

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

		return propertyList;
	}
	
	private static List<FutureContractProperty> getFutureFXTradePropertyList() {

		List<FutureContractProperty> propertyList = new ArrayList<FutureContractProperty>();
		
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.EXCHANGE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.SETTLEMENT_CCY.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.CONTRACT.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.FUTURE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.ID_TYPE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.VALUE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.BUY_SELL.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.PRICE.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.QUANTITY.getProperty());
		propertyList.add((FutureContractProperty) FutureContractPropertyEnum.NOMINAL.getProperty());
		
		return propertyList;
	}
}
