package beans;

import java.io.Serializable;

public class AccConfigRule implements Serializable {
	
	
	int id;
	public int getId() {
		return id;
	}
	public void setId(int ruleID) {
		this.id = ruleID;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getPoID() {
		return poID;
	}
	public void setPoID(int poID) {
		this.poID = poID;
	}
	public String getRuleTYpe() {
		return ruleTYpe;
	}
	public void setRuleTYpe(String ruleTYpe) {
		this.ruleTYpe = ruleTYpe;
	}
	public String getCalDate() {
		return calDate;
	}
	public void setCalDate(String calDate) {
		this.calDate = calDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getDayAdj() {
		return dayAdj;
	}
	public void setDayAdj(int dayAdj) {
		this.dayAdj = dayAdj;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public boolean isDailyClosing() {
		return dailyClosing;
	}
	public void setDailyClosing(boolean dailyClosing) {
		this.dailyClosing = dailyClosing;
	}
	String ruleName;
	int poID;
	String ruleTYpe;
	String calDate;
	String currency;
	int dayAdj;
	String attributes;
	boolean dailyClosing = true;
	
	

}
