package beans;

import java.io.Serializable;

public class DealRepoBean implements Deal,Serializable  {
	
	public String cpName;
	public String traderName;
	public String bookName;
	public String type;
	public String interestType;
	public String marketPrice;
	public String tradeCurrency;
	public String tradeAmount;
	public String settleDate;	
	public String issueDate;
	public String maturityDate;
	public String compoundingFrequency;
	public String collateralISIN;
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
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getCompoundingFrequency() {
		return compoundingFrequency;
	}
	public void setCompoundingFrequency(String compoundingFrequency) {
		this.compoundingFrequency = compoundingFrequency;
	}
	public String getCollateralISIN() {
		return collateralISIN;
	}
	public void setCollateralISIN(String collateralISIN) {
		this.collateralISIN = collateralISIN;
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
