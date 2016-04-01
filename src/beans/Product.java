package beans;

import java.io.Serializable;

public class Product implements Serializable {
	
	
	int id ;
	String underlyingProductType;
	/**
	 * @return the underlyingProductType
	 */
	public String getUnderlyingProductType() {
		return underlyingProductType;
	}
	/**
	 * @param underlyingProductType the underlyingProductType to set
	 */
	public void setUnderlyingProductType(String underlyingProductType) {
		this.underlyingProductType = underlyingProductType;
	}
	String dateDate;
	String startDate;
	String name;
	String currency;
	String productSubType;
	String productType;
	String productname;
	String prodcutShortName;
	double quantity;
	String issueDate;
	String marturityDate;
	int IssuerId;
	String Country;
	double IssuePrice;
	String IssueCurrency;
	double RedemptionPrice;
	String RedemptionCurrency;
	double FaceValue;
	String code;
	boolean isPositionBased = false;
	public String getRepoType() {
		return repoType;
	}
	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	String repoType;
	String callType;
	int collateralID =0;
public int getCollateralID() {
		return collateralID;
	}
	public void setCollateralID(int collateralID) {
		this.collateralID = collateralID;
	}
int isPosition;
	
	public int getIsPosition() {
		return isPosition;
	}
	public void setIsPosition(int isPosition) {
		this.isPosition = isPosition;
		if(isPosition ==-1)
			isPositionBased = false;
		else 
			isPositionBased = true;
	}
	public boolean isPositionBased() {
		return isPositionBased;
	}
	public void setPositionBased(boolean isPositionBased) {
		this.isPositionBased = isPositionBased;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProdcutShortName() {
		return prodcutShortName;
	}
	public void setProdcutShortName(String prodcutShortName) {
		this.prodcutShortName = prodcutShortName;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getMarturityDate() {
		return marturityDate;
	}
	public void setMarturityDate(String marturityDate) {
		this.marturityDate = marturityDate;
	}
	public int getIssuerId() {
		return IssuerId;
	}
	public void setIssuerId(int issuerId) {
		IssuerId = issuerId;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public double getIssuePrice() {
		return IssuePrice;
	}
	public void setIssuePrice(double issuePrice) {
		IssuePrice = issuePrice;
	}
	public String getIssueCurrency() {
		return IssueCurrency;
	}
	public void setIssueCurrency(String issueCurrency) {
		IssueCurrency = issueCurrency;
	}
	public double getRedemptionPrice() {
		return RedemptionPrice;
	}
	public void setRedemptionPrice(double redemptionPrice) {
		RedemptionPrice = redemptionPrice;
	}
	public String getRedemptionCurrency() {
		return RedemptionCurrency;
	}
	public void setRedemptionCurrency(String redemptionCurrency) {
		RedemptionCurrency = redemptionCurrency;
	}
	public double getFaceValue() {
		return FaceValue;
	}
	public void setFaceValue(double faceValue) {
		FaceValue = faceValue;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDatedDate() {
		return dateDate;
	}
	public void setDatedDate(String endDate) {
		this.dateDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTenor(String Tenor) {
		// TODO Auto-generated method stub
		this.tenor = Tenor;
		
	}
	
	public String getTenor() {
		
		return tenor;
	}
	String tenor;
String attributes ="";
	public void setAttributes(String attribtes) {
		// TODO Auto-generated method stub
		this.attributes = attribtes;
		
	}
public String getAttributes() {
		
		return attributes;
	}
Coupon coupon;
public Coupon getCoupon() {
	return coupon;
}
public void setCoupon(Coupon coup) {
	// TODO Auto-generated method stub
	coupon = coup;
	
}
ProductFXOption productFXOption = null;
public void setFXOptionProduct(ProductFXOption fxOptionProduct) {
	// TODO Auto-generated method stub
	productFXOption = fxOptionProduct;
}
public ProductFXOption getFXOptionProduct() {
	return productFXOption;
}
public String getCurrency() {
	// TODO Auto-generated method stub
	return currency;
}
public String getProductSubType() {
	return productSubType;
}
public void setProductSubType(String productSubType) {
	this.productSubType = productSubType;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
}
