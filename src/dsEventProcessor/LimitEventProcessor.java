package dsEventProcessor;

import java.io.Serializable;

import beans.Trade;

public class LimitEventProcessor extends EventProcessor  implements Serializable {
	Trade trade;
	String limitName;
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public double getLimitUsage() {
		return limitUsage;
	}
	public void setLimitUsage(double limitUsage) {
		this.limitUsage = limitUsage;
	}
	public double getLimitWarning() {
		return limitWarning;
	}
	public void setLimitWarning(double limitWarning) {
		this.limitWarning = limitWarning;
	}
	public String getFlagOnwarning() {
		return flagOnwarning;
	}
	public void setFlagOnwarning(String flagOnwarning) {
		this.flagOnwarning = flagOnwarning;
	}
	public String getThreshold() {
		return Threshold;
	}
	public void setThreshold(String threshold) {
		Threshold = threshold;
	}
	String limitType;
	double limitUsage;
	double limitWarning;
	String flagOnwarning;
	String Threshold;
	
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	int limitID =0;
	public void setLimitID(int id) {
		// TODO Auto-generated method stub
		this.limitID = id;
		
	}
	public int getLimitID() {
		// TODO Auto-generated method stub
		return limitID;
		
	}
	int limitMaxFlag = 0;
	public void setLimiMaxFlag(int i) {
		// TODO Auto-generated method stub
		this.limitMaxFlag = i;
		
	}
	
	

public int getLimiMaxFlag() {
	// TODO Auto-generated method stub
	return limitMaxFlag;
	
}
double limitmin = 0;
public void setlimitMinValue(double limitmin) {
	// TODO Auto-generated method stub
	this.limitmin = limitmin;
	
}
public double getlimitMinValue() {
	// TODO Auto-generated method stub
	return limitmin;
	
}
double limitMaxvalue;
public void setlimitMaxValue(double limitmax) {
	// TODO Auto-generated method stub
	this.limitMaxvalue = limitmax;
}
public double getlimitMaxValue() {
	// TODO Auto-generated method stub
	return limitMaxvalue;
	
}
String limitDate = "";
public void setLimitStartDate(String limitDate) {
	// TODO Auto-generated method stub
	this.limitDate = limitDate;
	
}
public String getLimitStartDate() {
	// TODO Auto-generated method stub
	return limitDate;	
}
String limitExpirtyDate = "";
public void setLimitExpiryDate(String limitExpiryDate) {
	// TODO Auto-generated method stub
	this.limitExpirtyDate = limitExpiryDate;
	
}
public String getLimitExpiryDate() {
	// TODO Auto-generated method stub
	return limitExpirtyDate;	
}
double limitThreashold = 0;
public void setLimitThreshold(double i) {
	limitThreashold = i;
	// TODO Auto-generated method stub
	
}
public double getLimitThreshold() {
	return limitThreashold;
	// TODO Auto-generated method stub
	
}
}
