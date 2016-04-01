package bo.account;

import beans.AccEventConfig;

public class BOPosting {

	String accEventType = "";
	double amount =0.0;
	String effectiveDate = "";
	String currency = "";
	String bookingDate = "";
	
	public BOPosting(AccEventConfig eventConfig) {
		// TODO Auto-generated constructor stub
		accEventType = eventConfig.getAccEvtType();
		eventConfig.getId();
	}

	public String getAccEventType() {
		return accEventType;
	}

	public void setAccEventType(String accEventType) {
		this.accEventType = accEventType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	

}
