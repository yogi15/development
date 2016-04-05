package src.beans;

import java.io.Serializable;

public class HolidayCode implements Serializable {
	
	boolean isWeekEnd = false;
	private int _firstNonBusinessDay;
	private int _secondNonBusinessDay;
	

    final public int getFirstNonBusinessDay() { return _firstNonBusinessDay ;}
    final public int getSecondNonBusinessDay() { return _secondNonBusinessDay;}

    final public void setFirstNonBusinessDay(int s) { _firstNonBusinessDay=s;}
    final public void setSecondNonBusinessDay(int s){ _secondNonBusinessDay=s;}

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
