package beans;

import java.io.Serializable;

public class ProductUploaderBean implements Serializable,Deal  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String productId;
	public String bondClass;
	public String bondType;
	public String securityType;
	public String issueDate;
	public String datedDate;
	public String maturityDate;
	public String issuer;
	public String country;
	public double issuerPrice;
	public String issueCurrency;
	public String redemptionPrice;
	public String redemptionCurrency;
	public String totalIssue;
	public String faceValue;
	public String isin;
	public String settlementType;
	public String common;
	public String couponType;
	public String couponRate;
	public String couponCurrency;
	public String dayCount;
	public String couponFrequency;
	public String bdc;
	public String tenor;
	
	
	
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBondClass() {
		return bondClass;
	}
	public void setBondClass(String bondClass) {
		this.bondClass = bondClass;
	}
	public String getBondType() {
		return bondType;
	}
	public void setBondType(String bondType) {
		this.bondType = bondType;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDatedDate() {
		return datedDate;
	}
	public void setDatedDate(String datedDate) {
		this.datedDate = datedDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getIssuerPrice() {
		return issuerPrice;
	}
	public void setIssuerPrice(double issuerPrice) {
		this.issuerPrice = issuerPrice;
	}
	public String getIssueCurrency() {
		return issueCurrency;
	}
	public void setIssueCurrency(String issueCurrency) {
		this.issueCurrency = issueCurrency;
	}
	public String getRedemptionPrice() {
		return redemptionPrice;
	}
	public void setRedemptionPrice(String redemptionPrice) {
		this.redemptionPrice = redemptionPrice;
	}
	public String getRedemptionCurrency() {
		return redemptionCurrency;
	}
	public void setRedemptionCurrency(String redemptionCurrency) {
		this.redemptionCurrency = redemptionCurrency;
	}
	public String getTotalIssue() {
		return totalIssue;
	}
	public void setTotalIssue(String totalIssue) {
		this.totalIssue = totalIssue;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponRate() {
		return couponRate;
	}
	public void setCouponRate(String couponRate) {
		this.couponRate = couponRate;
	}
	public String getCouponCurrency() {
		return couponCurrency;
	}
	public void setCouponCurrency(String couponCurrency) {
		this.couponCurrency = couponCurrency;
	}
	public String getDayCount() {
		return dayCount;
	}
	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}
	public String getCouponFrequency() {
		return couponFrequency;
	}
	public void setCouponFrequency(String couponFrequency) {
		this.couponFrequency = couponFrequency;
	}
	public String getBdc() {
		return bdc;
	}
	public void setBdc(String bdc) {
		this.bdc = bdc;
	}
	int issuerID = 0;
	public void setIssuerId(int issuerID) {
		// TODO Auto-generated method stub
		this.issuerID = issuerID;
		
	}
	public int getIssuerId() {
		// TODO Auto-generated method stub
		return issuerID;
		
	}
	double issuePrice = 0.0;
	public void setIssuePrice(double issueprice) {
		// TODO Auto-generated method stub
		this.issuerPrice = issueprice;
		
	}
	
	
	
	
}
