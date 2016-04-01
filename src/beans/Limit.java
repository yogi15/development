package beans;

import java.io.Serializable;

public class Limit implements Serializable {

	int id = 0;
	int limitConfigId = 0;
	String limitName = "";
	String limitType = "";
	String limitDate = "";
	String limitAvaliableDate = "";
	String limitExpiryDate = "";
	double limitmax;
	double limitmin;
	double limitWarning;
	double limitTolarance;
	public double getLimitmax() {
		return limitmax;
	}
	public void setLimitmax(double limitmax) {
		this.limitmax = limitmax;
	}
	public double getLimitmin() {
		return limitmin;
	}
	public void setLimitmin(double limitmin) {
		this.limitmin = limitmin;
	}
	public double getLimitWarning() {
		return limitWarning;
	}
	public void setLimitWarning(double limitWarning) {
		this.limitWarning = limitWarning;
	}
	public double getLimitTolarance() {
		return limitTolarance;
	}
	public void setLimitTolarance(double limitTolarance) {
		this.limitTolarance = limitTolarance;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLimitConfigId() {
		return limitConfigId;
	}
	public void setLimitConfigId(int limitConfigId) {
		this.limitConfigId = limitConfigId;
	}
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
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	public String getLimitAvaliableDate() {
		return limitAvaliableDate;
	}
	public void setLimitAvaliableDate(String limitAvaliableDate) {
		this.limitAvaliableDate = limitAvaliableDate;
	}
	public String getLimitExpiryDate() {
		return limitExpiryDate;
	}
	public void setLimitExpiryDate(String limitExpiryDate) {
		this.limitExpiryDate = limitExpiryDate;
	}
	
	
	
	

}
