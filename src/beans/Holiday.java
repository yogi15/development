package beans;

import java.io.Serializable;

public class Holiday implements Serializable {
	
	boolean isWeekEnd = false;
	
	
	String currency;
	int fweekday;
	public int getFweekday() {
		return fweekday;
	}
	public void setFweekday(int fweekday) {
		this.fweekday = fweekday;
	}
	public int getSweekdday() {
		return sweekdday;
	}
	public void setSweekdday(int sweekdday) {
		this.sweekdday = sweekdday;
	}
	int sweekdday;
	
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHdate() {
		return hdate;
	}
	public void setHdate(String hdate) {
		this.hdate = hdate;
	}
	String country;
	String hdate; 

}
