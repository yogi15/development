package beans;

import java.io.Serializable;

public class Liquidation implements Serializable {

	int 	liquid;
	int 	bookId;
	int 	productId;
	int 	fTradeId;
	int 	sTradeId;
	String	lidDate;
	double 	quantity;
	double	fPrice;
	double	sPrice;
	int		positionId; 
	String	tradeType;
	
	public int getLiquid() {
		return liquid;
	}
	public void setLiquid(int liquid) {
		this.liquid = liquid;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getfTradeId() {
		return fTradeId;
	}
	public void setfTradeId(int fTradeId) {
		this.fTradeId = fTradeId;
	}
	public int getsTradeId() {
		return sTradeId;
	}
	public void setsTradeId(int TradIid) {
		this.sTradeId = TradIid;
	}
	public String getLidDate() {
		return lidDate;
	}
	public void setLidDate(String lidDate) {
		this.lidDate = lidDate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getfPrice() {
		return fPrice;
	}
	public void setfPrice(double fPrice) {
		this.fPrice = fPrice;
	}
	public double getsPrice() {
		return sPrice;
	}
	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	double tradeAmt;
	public void setTradeAmount(double tradeAmt) {
		// TODO Auto-generated method stub
		this.tradeAmt = tradeAmt;
	}
	
	public double getTradeAmount() {
		// TODO Auto-generated method stub
		return  tradeAmt;
	}
	double realisedPNL = 0.0;
	public double getRealisedPNL() {
		// TODO Auto-generated method stub
		return realisedPNL;
	}
	public void setRealisedPNL(double pnl) {
		// TODO Auto-generated method stub
		this.realisedPNL = pnl;
	}
	String currency = "";
	public void setCurrency(String primaryCurr) {
		// TODO Auto-generated method stub
		this.currency = primaryCurr;
		
	}
	
	public String getCurrency() {
		return currency;
	}
}
