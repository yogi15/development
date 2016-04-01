package beans;

import java.io.Serializable;

public class FutureProduct implements Serializable {
	
	int productID; 
	int ID;
	int underlying_productID;
	String und_benchmark_name;
	String quote_type;
	double quote_decimals;
	int contract_size;
	int Lots;
	int ticksize;
	int tradecontract_no;
	String settlement_method;
	String Expiry_Date_Rule;
	String Rate_index_code;
	String time_zone;
	String time_minute;
	String last_Trading_rule;
	String first_delivery_Trading_rule;
	String last_delivery_Trading_rule;
	String first__notification_rule;
	String last_notification_rule;
	String settlement_currency;
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
	 * @return the underlying_productID
	 */
	public int getUnderlying_productID() {
		return underlying_productID;
	}
	/**
	 * @param underlying_productID the underlying_productID to set
	 */
	public void setUnderlying_productID(int underlying_productID) {
		this.underlying_productID = underlying_productID;
	}
	/**
	 * @return the und_benchmark_name
	 */
	public String getUnd_benchmark_name() {
		return und_benchmark_name;
	}
	/**
	 * @param und_benchmark_name the und_benchmark_name to set
	 */
	public void setUnd_benchmark_name(String und_benchmark_name) {
		this.und_benchmark_name = und_benchmark_name;
	}
	/**
	 * @return the quote_type
	 */
	public String getQuote_type() {
		return quote_type;
	}
	/**
	 * @param quote_type the quote_type to set
	 */
	public void setQuote_type(String quote_type) {
		this.quote_type = quote_type;
	}
	/**
	 * @return the quote_decimals
	 */
	public double getQuote_decimals() {
		return quote_decimals;
	}
	/**
	 * @param quote_decimals the quote_decimals to set
	 */
	public void setQuote_decimals(double quote_decimals) {
		this.quote_decimals = quote_decimals;
	}
	/**
	 * @return the contract_size
	 */
	public int getContract_size() {
		return contract_size;
	}
	/**
	 * @param contract_size the contract_size to set
	 */
	public void setContract_size(int contract_size) {
		this.contract_size = contract_size;
	}
	/**
	 * @return the lots
	 */
	public int getLots() {
		return Lots;
	}
	/**
	 * @param lots the lots to set
	 */
	public void setLots(int lots) {
		Lots = lots;
	}
	/**
	 * @return the ticksize
	 */
	public int getTicksize() {
		return ticksize;
	}
	/**
	 * @param ticksize the ticksize to set
	 */
	public void setTicksize(int ticksize) {
		this.ticksize = ticksize;
	}
	/**
	 * @return the tradecontract_no
	 */
	public int getTradecontract_no() {
		return tradecontract_no;
	}
	/**
	 * @param tradecontract_no the tradecontract_no to set
	 */
	public void setTradecontract_no(int tradecontract_no) {
		this.tradecontract_no = tradecontract_no;
	}
	/**
	 * @return the settlement_method
	 */
	public String getSettlement_method() {
		return settlement_method;
	}
	/**
	 * @param settlement_method the settlement_method to set
	 */
	public void setSettlement_method(String settlement_method) {
		this.settlement_method = settlement_method;
	}
	/**
	 * @return the expiry_Date_Rule
	 */
	public String getExpiry_Date_Rule() {
		return Expiry_Date_Rule;
	}
	/**
	 * @param expiry_Date_Rule the expiry_Date_Rule to set
	 */
	public void setExpiry_Date_Rule(String expiry_Date_Rule) {
		Expiry_Date_Rule = expiry_Date_Rule;
	}
	/**
	 * @return the rate_index_code
	 */
	public String getRate_index_code() {
		return Rate_index_code;
	}
	/**
	 * @param rate_index_code the rate_index_code to set
	 */
	public void setRate_index_code(String rate_index_code) {
		Rate_index_code = rate_index_code;
	}
	/**
	 * @return the time_zone
	 */
	public String getTime_zone() {
		return time_zone;
	}
	/**
	 * @param time_zone the time_zone to set
	 */
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	/**
	 * @return the time_minute
	 */
	public String getTime_minute() {
		return time_minute;
	}
	/**
	 * @param time_minute the time_minute to set
	 */
	public void setTime_minute(String time_minute) {
		this.time_minute = time_minute;
	}
	/**
	 * @return the last_Trading_rule
	 */
	public String getLast_Trading_rule() {
		return last_Trading_rule;
	}
	/**
	 * @param last_Trading_rule the last_Trading_rule to set
	 */
	public void setLast_Trading_rule(String last_Trading_rule) {
		this.last_Trading_rule = last_Trading_rule;
	}
	/**
	 * @return the first_delivery_Trading_rule
	 */
	public String getFirst_delivery_Trading_rule() {
		return first_delivery_Trading_rule;
	}
	/**
	 * @param first_delivery_Trading_rule the first_delivery_Trading_rule to set
	 */
	public void setFirst_delivery_Trading_rule(String first_delivery_Trading_rule) {
		this.first_delivery_Trading_rule = first_delivery_Trading_rule;
	}
	/**
	 * @return the last_delivery_Trading_rule
	 */
	public String getLast_delivery_Trading_rule() {
		return last_delivery_Trading_rule;
	}
	/**
	 * @param last_delivery_Trading_rule the last_delivery_Trading_rule to set
	 */
	public void setLast_delivery_Trading_rule(String last_delivery_Trading_rule) {
		this.last_delivery_Trading_rule = last_delivery_Trading_rule;
	}
	/**
	 * @return the first__notification_rule
	 */
	public String getFirst__notification_rule() {
		return first__notification_rule;
	}
	/**
	 * @param first__notification_rule the first__notification_rule to set
	 */
	public void setFirst__notification_rule(String first__notification_rule) {
		this.first__notification_rule = first__notification_rule;
	}
	/**
	 * @return the last_notification_rule
	 */
	public String getLast_notification_rule() {
		return last_notification_rule;
	}
	/**
	 * @param last_notification_rule the last_notification_rule to set
	 */
	public void setLast_notification_rule(String last_notification_rule) {
		this.last_notification_rule = last_notification_rule;
	}
	public String getSettlement_currency() {
		return settlement_currency;
	}
	public void setSettlement_currency(String settlement_currency) {
		this.settlement_currency = settlement_currency;
	}
	Product underlyingProduct = null;
	/**
	 * @return the underlyingProduct
	 */
	public Product getUnderlyingProduct() {
		return underlyingProduct;
	}
	/**
	 * @param underlyingProduct the underlyingProduct to set
	 */
	public void setUnderlyingProduct(Product underlyingProduct) {
		this.underlyingProduct = underlyingProduct;
	}
	

	

}
