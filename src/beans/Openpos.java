package beans;

import java.io.Serializable;

public class Openpos implements Serializable {
	
	int id;
	String tradedesc1;
	String primaryCurr;
	/**
	 * @return the primaryCurr
	 */
	public String getPrimaryCurr() {
		return primaryCurr;
	}
	/**
	 * @param primaryCurr the primaryCurr to set
	 */
	public void setPrimaryCurr(String primaryCurr) {
		this.primaryCurr = primaryCurr;
	}
	/**
	 * @return the quotingCurr
	 */
	public String getQuotingCurr() {
		return quotingCurr;
	}
	/**
	 * @param quotingCurr the quotingCurr to set
	 */
	public void setQuotingCurr(String quotingCurr) {
		this.quotingCurr = quotingCurr;
	}
	String quotingCurr;
	public String getTradedesc1() {
		return tradedesc1;
	}
	public void setTradedesc1(String tradedesc1) {
		this.tradedesc1 = tradedesc1;
	}
	public String getFxSwapLegType() {
		return fxSwapLegType;
	}
	public void setFxSwapLegType(String fxSwapLegType) {
		this.fxSwapLegType = fxSwapLegType;
	}
	String fxSwapLegType;
	String tradedesc;
	/**
	 * @return the tradedesc
	 */
	public String getTradedesc() {
		return tradedesc;
	}
	/**
	 * @param tradedesc the tradedesc to set
	 */
	public void setTradedesc(String tradedesc) {
		this.tradedesc = tradedesc;
	}
	double originalTradeAmt;
	int		tradeId;
	int		productId;
	String	settleDate;
	String	tradeDate;
	double	quantity;
	double	openQuantity;
	int		bookId;
	double	price;
	int		sign;
	int		positionId;
	String	openpositionDate;
	String	productType;
	String	productSubType;
	double  openNominal;
	double  tradeAmt;
	double  productFV; // this face value of trade product trade which is of bond type (to avoid unneccessary call to product and trade again when openpos is updated).
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getOriginalTradeAmt() {
		return originalTradeAmt;
	}
	public void setOriginalTradeAmt(double originalTradeAmt) {
		this.originalTradeAmt = originalTradeAmt;
	}
	public double getProductFV() {
		return productFV;
	}
	public void setProductFV(double productFV) {
		this.productFV = productFV;
	}
	public double getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(double tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public double getOpenNominal() {
		return openNominal;
	}
	public void setOpenNominal(double openNominal) {
		this.openNominal = openNominal;
	}
	public double getQuotingAmt() {
		return quotingAmt;
	}
	public void setQuotingAmt(double quotingAmt) {
		this.quotingAmt = quotingAmt;
	}
	double quotingAmt;
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getOpenQuantity() {
		return openQuantity;
	}
	public void setOpenQuantity(double openQuantity) {
		this.openQuantity = openQuantity;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getOpenpositionDate() {
		return openpositionDate;
	}
	public void setOpenpositionDate(String openpositionDate) {
		this.openpositionDate = openpositionDate;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductSubType() {
		return productSubType;
	}
	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}
	
}
