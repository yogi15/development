package beans;


import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import constants.BookConstants;
import constants.ProductConstants;
import beans.Coupon;

public class Product implements BaseBean{

	int id;
	String underlyingProductType;

	/**
	 * @return the underlyingProductType
	 */
	public String getUnderlyingProductType() {
		return underlyingProductType;
	}

	/**
	 * @param underlyingProductType
	 *            the underlyingProductType to set
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
	
	int EXCHANGE=0;
	String PRODUCTSUBTYPE;
	String STATUS;
	String ISIN;
	String CUSIP;
	String SEDOL;
	String SYMBOL;
	String MATURITYDATE;
	String CURRENCY;
	AttributeContainer attributeContainer = null;
	Coupon c=new Coupon();
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj = null;
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.UNDERLYINGID)) {
			
			return obj = getId();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.UNDERLYINGNAME)) {
			return obj = getName();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.ASSETTYPE)) {
			return obj = c.get();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.COUNTRY)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.STATUS)) {
			return obj = getSTATUS();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.EXCHANGE)) {
			return obj = getEXCHANGE();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			return obj = getCurrency();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.FACEVALUE)) {
			
			return obj = getFaceValue();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.ISSUEDATE)) {
			return obj = getIssueDate();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.DATEDDATE)) {
			return obj = getDateDate();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.MATURITYDATE)) {
			return obj = getMATURITYDATE();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.FIXEDRATE)) {
			return obj = getf();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.RATEINDEX)) {
			return obj = getrate();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.DAYCOUNT)) {
			return obj = getda();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BUSINESSDAYCONVENTION)) {
			
			return obj = getbu();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.WITHHOLDTAX)) {
			return obj = getwith();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.ROLLDATE)) {
			return obj = getrol();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.PERIODADJUSTED)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.HOLIDAYCODE)) {
			return obj = getHolidaycode();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.TIMEZONE)) {
			return obj = getTimezone();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			return obj = getCurrency();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			return obj = getCurrency();
		}
	}
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.LEID)) {
			setLe_id((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNO)) {
			setBookno((Integer) object);
		}		
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNAME)) {
			setBook_name((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.HOLIDAYCODE)) {
			setHolidaycode((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.TIMEZONE)) {
			setTimezone((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			setCurrency((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.LEID)) {
			setLe_id((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNO)) {
			setBookno((Integer) object);
		}		
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNAME)) {
			setBook_name((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.HOLIDAYCODE)) {
			setHolidaycode((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.TIMEZONE)) {
			setTimezone((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			setCurrency((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.LEID)) {
			setLe_id((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNO)) {
			setBookno((Integer) object);
		}		
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.BOOKNAME)) {
			setBook_name((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.HOLIDAYCODE)) {
			setHolidaycode((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.TIMEZONE)) {
			setTimezone((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			setCurrency((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(ProductConstants.CURRENCY)) {
			setCurrency((String) object);
		}
		
		
	}
	
	public AttributeContainer getAttributeContainer() {
		return attributeContainer;
	}

	public void setAttributeContainer(AttributeContainer attributeContainer) {
		this.attributeContainer = attributeContainer;
	}

	public void setAttributesData(Hashtable<String, String> attributesData) {
		this.attributesData = attributesData;
	}

	public String getDateDate() {
		return dateDate;
	}

	public void setDateDate(String dateDate) {
		this.dateDate = dateDate;
	}

	public int getEXCHANGE() {
		return EXCHANGE;
	}

	public void setEXCHANGE(int eXCHANGE) {
		EXCHANGE = eXCHANGE;
	}

	public String getPRODUCTSUBTYPE() {
		return PRODUCTSUBTYPE;
	}

	public void setPRODUCTSUBTYPE(String pRODUCTSUBTYPE) {
		PRODUCTSUBTYPE = pRODUCTSUBTYPE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public String getCUSIP() {
		return CUSIP;
	}

	public void setCUSIP(String cUSIP) {
		CUSIP = cUSIP;
	}

	public String getSEDOL() {
		return SEDOL;
	}

	public void setSEDOL(String sEDOL) {
		SEDOL = sEDOL;
	}

	public String getSYMBOL() {
		return SYMBOL;
	}

	public void setSYMBOL(String sYMBOL) {
		SYMBOL = sYMBOL;
	}

	public String getMATURITYDATE() {
		return MATURITYDATE;
	}

	public void setMATURITYDATE(String mATURITYDATE) {
		MATURITYDATE = mATURITYDATE;
	}

	public String getCURRENCY() {
		return CURRENCY;
	}

	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}

	public ProductFXOption getProductFXOption() {
		return productFXOption;
	}

	public void setProductFXOption(ProductFXOption productFXOption) {
		this.productFXOption = productFXOption;
	}

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
	int collateralID = 0;

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
		if (isPosition == -1)
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
	String attributes = "";

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

	
	
transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
	
	public Hashtable<String, String> getAttributesData() {
		if(attributesData == null) 
			attributesData = new Hashtable<String, String>();
		if(attributesData.size() == 0)
			setAttributesData(getAttributes());
		return attributesData;
	}
	public String getAttributeValue(String key) {
		String value = null;
		if(attributesData.size() == 0)
			setAttributesData(attributes);
		attributesData.get(key);
		return value;
	}
	
	public void setAttributesData(String attributesFromTransfer) {
		if(((attributesFromTransfer != null) && (!attributesFromTransfer.isEmpty()))) {
			String [] attr = attributesFromTransfer.split(";");
			for(int i=0;i<attr.length;i++) {
				String value = attr[i].substring(attr[i].indexOf('=')+1, attr[i].length());
    			String key = attr[i].substring(0, attr[i].indexOf('='));
    			attributesData.put(key,value);
    			
			}
				 
			
		}
	}
	
	
	public boolean containTransferKey(String key) {
		return attributesData.containsKey(key);
	}
	
	public String getALLAttributesData() {
		String allAttributes = "";
		if(((attributesData != null) && (!attributesData.isEmpty()))) {
			
			Enumeration<String > keys = attributesData.keys();
			while(keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = (String) attributesData.get(key);
				allAttributes = allAttributes.trim() + key +"="+value+";";
		    }
			
	        
       }
		this.attributes = allAttributes;
       return allAttributes;

	}
	
	
	public void addAttribues(String key,String value) {
		if(((key != null) && (!key.isEmpty())) && ((value != null) && (!value.isEmpty()))) {
			attributesData.put(key, value);
		}
		this.attributes =getALLAttributesData();
	}
	
	
	public void removeAttribues(String key) {
		if(((key != null) && (!key.isEmpty())) ) {
			attributesData.remove(key);
		}
		this.attributes =getALLAttributesData();
}
}

	
