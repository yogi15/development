package beans;

import java.io.Serializable;

public class DealMMBean implements Deal,Serializable  {
	
	public String cpName;
	public String traderName;
	public String bookName;
	public String type;
	public String interestType;
	public String marketPrice;
	public String tradeCurrency;
	public String tradeAmount;
	public String settleDate;	
	public String amortizationType;
	public String amortizationStartDate;
	public String amortizationEndDate;
	public String amortizationRate;
	public String amortizationFrequency;
	public String compoundingType;
	public String compoundingFrequency;
	public Product productBean;
	public Coupon couponBean;
	
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getTradeCurrency() {
		return tradeCurrency;
	}
	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}
	public String getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getAmortizationType() {
		return amortizationType;
	}
	public void setAmortizationType(String amortizationType) {
		this.amortizationType = amortizationType;
	}
	public String getAmortizationStartDate() {
		return amortizationStartDate;
	}
	public void setAmortizationStartDate(String amortizationStartDate) {
		this.amortizationStartDate = amortizationStartDate;
	}
	public String getAmortizationEndDate() {
		return amortizationEndDate;
	}
	public void setAmortizationEndDate(String amortizationEndDate) {
		this.amortizationEndDate = amortizationEndDate;
	}
	public String getAmortizationRate() {
		return amortizationRate;
	}
	public void setAmortizationRate(String amortizationRate) {
		this.amortizationRate = amortizationRate;
	}
	public String getAmortizationFrequency() {
		return amortizationFrequency;
	}
	public void setAmortizationFrequency(String amortizationFrequency) {
		this.amortizationFrequency = amortizationFrequency;
	}
	public String getCompoundingType() {
		return compoundingType;
	}
	public void setCompoundingType(String compoundingType) {
		this.compoundingType = compoundingType;
	}
	public String getCompoundingFrequency() {
		return compoundingFrequency;
	}
	public void setCompoundingFrequency(String compoundingFrequency) {
		this.compoundingFrequency = compoundingFrequency;
	}
	public Product getProductBean() {
		return productBean;
	}
	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}
	public Coupon getCouponBean() {
		return couponBean;
	}
	public void setCouponBean(Coupon couponBean) {
		this.couponBean = couponBean;
	}
	
}
