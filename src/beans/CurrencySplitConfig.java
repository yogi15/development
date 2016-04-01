package beans;

import java.io.Serializable;

public class CurrencySplitConfig implements Serializable {
	
	
	String currencyPair = "";
	String currencyToSplit = "";
	int  bookid;
	int firstSpotBook;
	int secondSpotBook;
	String firstCurrencySplit;
	public String getFirstCurrencySplit() {
		return firstCurrencySplit;
	}
	public void setFirstCurrencySplit(String firstCurrencySplit) {
		this.firstCurrencySplit = firstCurrencySplit;
	}
	public String getSecondCurrencySPlit() {
		return secondCurrencySPlit;
	}
	public void setSecondCurrencySPlit(String secondCurrencySPlit) {
		this.secondCurrencySPlit = secondCurrencySPlit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String secondCurrencySPlit;
	int id;
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public String getCurrencyToSplit() {
		return currencyToSplit;
	}
	public void setCurrencyToSplit(String currencyToSplit) {
		this.currencyToSplit = currencyToSplit;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getFirstSpotBook() {
		return firstSpotBook;
	}
	public void setFirstSpotBook(int firstSpotBook) {
		this.firstSpotBook = firstSpotBook;
	}
	public int getSecondSpotBook() {
		return secondSpotBook;
	}
	public void setSecondSpotBook(int secondSpotBook) {
		this.secondSpotBook = secondSpotBook;
	}
	
	
	
	
	
		

}
