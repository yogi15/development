package beans;

import java.io.Serializable;

public class FutureContract implements Serializable {
	
	public static final String EXPIRATION_DATE = "Expiration Date";
	   public static final String FIRST_TRADE_DATE = "First Trade Date";
	   public static final String LAST_TRADE_DATE = "Last Trade Date";
	   public static final String FIRST_DELIVERY_DATE = "First Delivery Date";
	   public static final String LAST_DELIVERY_DATE = "Last Delivery Date";
	   public static final String FIRST_NOTIFICATION_DATE = "First Notification Date";
	   public static final String LAST_NOTIFICATION_DATE = "Last Notification Date";
	   public static final String ATTRIBUTES = "Attributes";
	   public static final String CTD = "Ctd";
	   public static final String QUOTE_NAME = "Quote Name";
	   /**
	 * @return the parentProductID
	 */
	public int getParentProductID() {
		return parentProductID;
	}
	/**
	 * @param parentProductID the parentProductID to set
	 */
	public void setParentProductID(int parentProductID) {
		this.parentProductID = parentProductID;
	}
	int parentProductID = 0;
	   /**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
	/**
	 * @return the expirationDate
	 */
	
	int ID;
	   int productID;
	   
	   
	   String expriationDate = "";
	 /**
	 * @return the expriationDate
	 */
	public String getExpriationDate() {
		return expriationDate;
	}
	/**
	 * @param expriationDate the expriationDate to set
	 */
	public void setExpriationDate(String expriationDate) {
		this.expriationDate = expriationDate;
	}
	
	/**
	 * @return the lastTradeDate
	 */
	public String getLastTradeDate() {
		return lastTradeDate;
	}
	/**
	 * @param lastTradeDate the lastTradeDate to set
	 */
	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	/**
	 * @return the firstDeliveryDate
	 */
	public String getFirstDeliveryDate() {
		return firstDeliveryDate;
	}
	/**
	 * @param firstDeliveryDate the firstDeliveryDate to set
	 */
	public void setFirstDeliveryDate(String firstDeliveryDate) {
		this.firstDeliveryDate = firstDeliveryDate;
	}
	/**
	 * @return the lastDeliveryDate
	 */
	public String getLastDeliveryDate() {
		return lastDeliveryDate;
	}
	/**
	 * @param lastDeliveryDate the lastDeliveryDate to set
	 */
	public void setLastDeliveryDate(String lastDeliveryDate) {
		this.lastDeliveryDate = lastDeliveryDate;
	}
	/**
	 * @return the firstNotificationDate
	 */
	public String getFirstNotificationDate() {
		return firstNotificationDate;
	}
	/**
	 * @param firstNotificationDate the firstNotificationDate to set
	 */
	public void setFirstNotificationDate(String firstNotificationDate) {
		this.firstNotificationDate = firstNotificationDate;
	}
	/**
	 * @return the lastNotificationDate
	 */
	public String getLastNotificationDate() {
		return lastNotificationDate;
	}
	/**
	 * @param lastNotificationDate the lastNotificationDate to set
	 */
	public void setLastNotificationDate(String lastNotificationDate) {
		this.lastNotificationDate = lastNotificationDate;
	}
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
	/**
	 * @return the ctd
	 */
	public String getCtd() {
		return ctd;
	}
	/**
	 * @param ctd the ctd to set
	 */
	public void setCtd(String ctd) {
		this.ctd = ctd;
	}
	/**
	 * @return the quoteName
	 */
	public String getQuoteName() {
		return quoteName;
	}
	/**
	 * @param quoteName the quoteName to set
	 */
	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}
	
	  String lastTradeDate = "";
	  String firstDeliveryDate = "";
	   String lastDeliveryDate = "";
	  String firstNotificationDate = "";
	  String lastNotificationDate = "";
	  String attributes = "Attributes";
	  String ctd = "";
	  String quoteName = "";
	  /**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the productshortname
	 */
	public String getProductshortname() {
		return productshortname;
	}
	/**
	 * @param productshortname the productshortname to set
	 */
	public void setProductshortname(String productshortname) {
		this.productshortname = productshortname;
	}
	String productType = "";
	  String productshortname = "";

}
