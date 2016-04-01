package beans;

import java.io.Serializable;

public class DealFXBean implements Deal,Serializable  {
	public String memberName;
	public FeesUploader fees;
	public String attributes = "";
	
	/**
	 * @return the attributes
	 */
	public String getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public boolean isPositionBased = true;
	/**
	 * @return the isPosition
	 */
	public boolean isPositionBased() {
		return isPositionBased;
	}
	/**
	 * @param isPosition the isPosition to set
	 */
	public void setisPositionBased(boolean isPosition) {
		this.isPositionBased = isPosition;
	}
	/**
	 * @return the fees
	 */
	public FeesUploader getFees() {
		return fees;
	}
	/**
	 * @param fees the fees to set
	 */
	public void setFees(FeesUploader fees) {
		this.fees = fees;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getDEALERname() {
		return DEALERname;
	}
	public void setDEALERname(String dEALERname) {
		DEALERname = dEALERname;
	}
	public String getDealer() {
		return Dealer;
	}
	public void setDealer(String dealer) {
		Dealer = dealer;
	}
	public String getMarket() {
		return Market;
	}
	public void setMarket(String market) {
		Market = market;
	}
	public String getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}
	public String getTradeDate() {
		return TradeDate;
	}
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	public String getTradeTime() {
		return TradeTime;
	}
	public void setTradeTime(String tradeTime) {
		TradeTime = tradeTime;
	}
	public String getTradeNumber() {
		return TradeNumber;
	}
	public void setTradeNumber(String tradeNumber) {
		TradeNumber = tradeNumber;
	}
	public String getTradeType() {
		return TradeType;
	}
	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}
	public String getType() {
		return Type;
	}
	public void setType(String Type) {
		this.Type = Type;
	}
	public String getSettlement() {
		return Settlement;
	}
	public void setSettlement(String settlement) {
		Settlement = settlement;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getMaturityDate() {
		return MaturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		MaturityDate = maturityDate;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getSpotPrice() {
		return SpotPrice;
	}
	public void setSpotPrice(String spotPrice) {
		SpotPrice = spotPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public String member;
	public String DEALERname;
	public String Dealer;
	public String  Market;
	public String OrderNumber;
	public String TradeDate;
	public String TradeTime;
	public String TradeNumber;
	public String TradeType;
	public String Type;
	public String Settlement;
	public String Date;
	public String MaturityDate;
	public String Amount;
	public String SpotPrice;
	public String quantity;
	public String currency;
	public String currencyPair;
	
	
	

}
