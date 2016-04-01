package beans;

import java.io.Serializable;

public class LimitUsage implements Serializable {
	int limitId = 0;
	int limitConfigId = 0;
	String startdate = "";
	String enddate = "";
	double amount_used;
	String comments = "";
	String Tenor = "";
	int tradeId = 0;
	
	
	public int getLimitId() {
		return limitId;
	}
	public void setLimitId(int limitId) {
		this.limitId = limitId;
	}
	public int getLimitConfigId() {
		return limitConfigId;
	}
	public void setLimitConfigId(int limitConfigId) {
		this.limitConfigId = limitConfigId;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public double getAmount_used() {
		return amount_used;
	}
	public void setAmount_used(double amount_used) {
		this.amount_used = amount_used;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTenor() {
		return Tenor;
	}
	public void setTenor(String tenor) {
		Tenor = tenor;
	}
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
}
