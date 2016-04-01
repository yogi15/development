package beans;

import java.io.Serializable;

public class Amortization implements Serializable {
	
	String startDate;
	String freq;
	String endDate;
	String amortType;
	String rate;
	String scheduledValue;
	String amortizingFrequency = "";
	String amortization = "";
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
		this.freq = freq;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAmortType() {
		return amortType;
	}
	public void setAmortType(String amortType) {
		this.amortType = amortType;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getScheduledValue() {
		return scheduledValue;
	}
	public void setScheduledValue(String scheduledValue) {
		this.scheduledValue = scheduledValue;
	}
	public String getAmortizingFrequency() {
		return amortizingFrequency;
	}
	public void setAmortizingFrequency(String amortizingFrequency) {
		this.amortizingFrequency = amortizingFrequency;
	}
	public String getAmortization() {
		return amortization;
	}
	public void setAmortization(String amortization) {
		this.amortization = amortization;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	boolean isOpen = false;
	
	
}
