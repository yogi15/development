package beans;

public class FeesUploader {
	int id;
	public int getId() {
		return id;
	}
	String leRole;
	
	public String getLeRole() {
		return leRole;
	}
	public void setLeRole(String leRole) {
		this.leRole = leRole;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBrokerName() {
		return BrokerName;
	}
	public void setLeCode(String BrokerName) {
		this.BrokerName = BrokerName;
	}
	public String getFeeDate() {
		return feeDate;
	}
	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPayrec() {
		return payrec;
	}
	public void setPayrec(String payrec) {
		this.payrec = payrec;
	}
	public String getLeCode() {
		return BrokerName;
	}
	int tradeID = 0;
	String feeType;
	double amount;
    String BrokerName;
    String feeDate;
    String startDate;
    String endDate;
    String currency;
    String payrec = "Pay";
}