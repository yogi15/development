package beans;

import java.io.Serializable;

public class Task implements Serializable {
  int id;
  int tradeID;
  int productID;
  int transferID;
  public int getNettedConfigID() {
	return nettedConfigID;
}
public void setNettedConfigID(int nettedConfigID) {
	this.nettedConfigID = nettedConfigID;
}
int nettedConfigID;
 public Trade getTrade() {
	return trade;
}
public void setTrade(Trade trade) {
	this.trade = trade;
}
public Transfer getTransfer() {
	return transfer;
}
public void setTransfer(Transfer transfer) {
	this.transfer = transfer;
}
Trade trade;
 Transfer transfer;
String action;
  String type;
  String taskDate;
  String status;
  String event_type;
 
String WFType;
  String productType;
  
  int tradeVersionID;
 
int transferVersionID;
  
public int getTradeVersionID() {
	return tradeVersionID;
}
public void setTradeVersionID(int tradeVersionID) {
	this.tradeVersionID = tradeVersionID;
}
public int getTransferVersionID() {
	return transferVersionID;
}
public void setTransferVersionID(int transferVersionID) {
	this.transferVersionID = transferVersionID;
}
  public int getTransferID() {
		return transferID;
	}
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}
  
  
  public String getTransferDate() {
	return transferDate;
}
public void setTransferDate(String transferDate) {
	this.transferDate = transferDate;
}
String transferDate;
  
  public String getWFType() {
		return WFType;
	}
	public void setWFType(String wFType) {
		WFType = wFType;
	}
  public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
public String getEvent_type() {
	return event_type;
}
public void setEvent_type(String event_type) {
	this.event_type = event_type;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getTaskstatus() {
	return taskstatus;
}
public void setTaskstatus(String taskstatus) {
	this.taskstatus = taskstatus;
}
String user_name;
  int userid;
  String taskstatus;
  public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}
String currency;
  int bookid;
  
private String statusDone;
  
public int getId() {
	return id;
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
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.WFType = type;
	this.type = type;
}
public String getTaskDate() {
	return taskDate;
}
public void setTaskDate(String taskDate) {
	this.taskDate = taskDate;
}
public String getStatus() {
	return status;
}
public String getStatusDone() {
	return statusDone;
}
public void setStatus(String status) {
	this.status = status;
}
public void setStatusDone(String string) {
	this.statusDone = string;
	
}
public void setTradeDate(String tradeDate) {
	// TODO Auto-generated method stub
	this.tradeDate = tradeDate;
}
public String getTradeDate() {
	// TODO Auto-generated method stub
	return tradeDate;
}
  String tradeDate = "";

public void setWFConfigID(int wfcID) {
	// TODO Auto-generated method stub
	
	wfConfigid = wfcID;
}
public int getWFConfigID() {
	// TODO Auto-generated method stub
	
	return wfConfigid;
	}
  int wfConfigid =0;
int cpID = 0;
public void setCpid(int cPid) {
	// TODO Auto-generated method stub
	cpID = cPid;
	
}
public int getCpid() {
	// TODO Auto-generated method stub
	 return cpID;
	
}
int messageVersionID = 0; 
public void setMessageVersionID(int version) {
	// TODO Auto-generated method stub
	messageVersionID = version;
}
public int getMessageVersionID(int version) {
	// TODO Auto-generated method stub
	return messageVersionID;
}
int messageID = 0;
public void setMessageID(int id2) {
	// TODO Auto-generated method stub
	messageID = id2;
	
	
}

public int getMessageID() {
	// TODO Auto-generated method stub
	 return messageID;
	
}
}
