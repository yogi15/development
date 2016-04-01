package beans;

import java.io.Serializable;

public class OpenTrade implements Serializable {
	
	int tradeid;
	int bookid;
	int cpid;
	String type;
	String productType;
	String ProductSubType;
	String currencyPair;
	double price;
	double quantity;
	double nominal;
	double openQuantity;
	double openNominal;
	int openID;
	String deliveryDate;
	String bookName;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	String cpName;
	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the openID
	 */
	public int getOpenID() {
		return openID;
	}
	/**
	 * @param openID the openID to set
	 */
	public void setOpenID(int openID) {
		this.openID = openID;
	}
	/**
	 * @return the positionid
	 */
	public int getPositionid() {
		return positionid;
	}
	/**
	 * @param positionid the positionid to set
	 */
	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}
	int positionid;
	
	/**
	 * @return the tradeid
	 */
	public int getTradeid() {
		return tradeid;
	}
	/**
	 * @param tradeid the tradeid to set
	 */
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	/**
	 * @return the bookid
	 */
	public int getBookid() {
		return bookid;
	}
	/**
	 * @param bookid the bookid to set
	 */
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	/**
	 * @return the cpid
	 */
	public int getCpid() {
		return cpid;
	}
	/**
	 * @param cpid the cpid to set
	 */
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
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
	 * @return the productSubType
	 */
	public String getProductSubType() {
		return ProductSubType;
	}
	/**
	 * @param productSubType the productSubType to set
	 */
	public void setProductSubType(String productSubType) {
		ProductSubType = productSubType;
	}
	/**
	 * @return the currencyPair
	 */
	public String getCurrencyPair() {
		return currencyPair;
	}
	/**
	 * @param currencyPair the currencyPair to set
	 */
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the nominal
	 */
	public double getNominal() {
		return nominal;
	}
	/**
	 * @param nominal the nominal to set
	 */
	public void setNominal(double nominal) {
		this.nominal = nominal;
	}
	/**
	 * @return the openQuantity
	 */
	public double getOpenQuantity() {
		return openQuantity;
	}
	/**
	 * @param openQuantity the openQuantity to set
	 */
	public void setOpenQuantity(double openQuantity) {
		this.openQuantity = openQuantity;
	}
	/**
	 * @return the openNominal
	 */
	public double getOpenNominal() {
		return openNominal;
	}
	/**
	 * @param openNominal the openNominal to set
	 */
	public void setOpenNominal(double openNominal) {
		this.openNominal = openNominal;
	}
	

}
