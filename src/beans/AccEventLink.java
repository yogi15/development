package beans;

import java.io.Serializable;

public class AccEventLink implements Serializable {
	
	int id =0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRuleID() {
		return ruleID;
	}
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}
	public String getAccEvent() {
		return accEvent;
	}
	public void setAccEvent(String accEvent) {
		this.accEvent = accEvent;
	}
	
	public int getPlusmius() {
		return plusmius;
	}
	public void setPlusmius(int plusmius) {
		this.plusmius = plusmius;
	}
	
	int ruleID = 0;
	String accEvent ;
	int debitaccount;
	public int getDebitaccount() {
		return debitaccount;
	}
	public void setDebitaccount(int debitaccount) {
		this.debitaccount = debitaccount;
	}
	public int getCreditaccount() {
		return creditaccount;
	}
	public void setCreditaccount(int creditaccount) {
		this.creditaccount = creditaccount;
	}
	public String getDebitaccType() {
		return debitaccType;
	}
	public void setDebitaccType(String debitaccType) {
		this.debitaccType = debitaccType;
	}
	public String getCreaditaccType() {
		return creaditaccType;
	}
	public void setCreaditaccType(String creaditaccType) {
		this.creaditaccType = creaditaccType;
	}

	int creditaccount;
	int plusmius;
	String debitaccType,creaditaccType;
	
	
	
	

}
