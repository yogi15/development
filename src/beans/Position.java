package beans;

import java.io.Serializable;

public class Position implements Serializable {

	double	sell_amount;
	double	buy_amount;
	double	sell_quantity;
	double	buy_quantity;
	double	avg_price;
	double	unrealized;
	double	realized;
	int		bookId;
	int		productId;
	double  nominal;
	String  tradeDate;
	/**
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}
	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	/**
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}
	/**
	 * @param settleDate the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	String  settleDate;
	LiquidationConfig ligConfig;
	
	public LiquidationConfig getLigConfig() {
		return ligConfig;
	}
	public void setLigConfig(LiquidationConfig ligConfig) {
		this.ligConfig = ligConfig;
	}
	public double getRealisedPNL() {
		return realisedPNL;
	}
	public void setRealisedPNL(double realisedPNL) {
		this.realisedPNL = realisedPNL;
	}
	public double getNominal() {
		return nominal;
	}
	public void setNominal(double nominal) {
		this.nominal = nominal;
	}
	String primaryCurr;
	
	public String getPrimaryCurr() {
		return primaryCurr;
	}
	public void setPrimaryCurr(String primaryCurr) {
		this.primaryCurr = primaryCurr;
	}
	String productType;
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductsubtype() {
		return productsubtype;
	}
	
	int		positionId;
	
	public double getSell_amount() {
		return sell_amount;
	}
	public void setSell_amount(double sell_amount) {
		this.sell_amount = sell_amount;
	}
	public double getBuy_amount() {
		return buy_amount;
	}
	public void setBuy_amount(double buy_amount) {
		this.buy_amount = buy_amount;
	}
	public double getSell_quantity() {
		return sell_quantity;
	}
	public void setSell_quantity(double sell_quantity) {
		this.sell_quantity = sell_quantity;
	}
	public double getBuy_quantity() {
		return buy_quantity;
	}
	public void setBuy_quantity(double buy_quantity) {
		this.buy_quantity = buy_quantity;
	}
	public double getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(double avg_price) {
		this.avg_price = avg_price;
	}
	public double getUnrealized() {
		return unrealized;
	}
	public void setUnrealized(double unrealized) {
		this.unrealized = unrealized;
	}
	public double getRealized() {
		return realized;
	}
	public void setRealized(double realized) {
		this.realized = realized;
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
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getProductsubType() {
		
		// TODO Auto-generated method stub
		return productsubtype;
	}
	String productsubtype;
	
	public void setProductsubType(String productsubtype) {
		this.productsubtype = productsubtype;
	}
	
	double amount = 0.0;
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	double realisedPNL = 0.0;
	public double getRealizedPNL() {
		// TODO Auto-generated method stub
		return realisedPNL;
		}
	public void setRealizedPNL(double realisedPNL) {
		// TODO Auto-generated method stub
		this.realisedPNL = realisedPNL;
		}
		
}
