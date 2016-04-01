package beans;

import java.io.Serializable;

public class B2BConfig  implements Serializable,Cloneable {
	
	int id;
	int counterPartyID;
	String currencyPair;
	String holdingCurrency;
	int bookid;
	int transferBookTo;
	
	/**
	 * @return the counterPartyID
	 */
	public int getCounterPartyID() {
		return counterPartyID;
	}
	/**
	 * @param counterPartyID the counterPartyID to set
	 */
	public void setCounterPartyID(int counterPartyID) {
		this.counterPartyID = counterPartyID;
	}
	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}
	int rate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public String getHoldingCurrency() {
		return holdingCurrency;
	}
	public void setHoldingCurrency(String holdingCurrency) {
		this.holdingCurrency = holdingCurrency;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getTransferBookTo() {
		return transferBookTo;
	}
	public void setTransferBookTo(int transferBookTo) {
		this.transferBookTo = transferBookTo;
	}


}
