package beans;

import java.io.Serializable;

public class Audit implements Serializable {
	
	String changeDate;
	String type;
	int tradeid;
	String fieldname ;
	int userid ;
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setVersion(int version) {
		// TODO Auto-generated method stub
		this.version = version;
		
	}
	int version;
	public int getVersion() {
		
		return version;
	}
	String values;
	public void setValues(String value) {
		// TODO Auto-generated method stub
		this.values = value;
	}
	public String getValues() {
		return values;
		// TODO Auto-generated method stub
		
	}
	String attributes;
	public String getTattribue() {
		// TODO Auto-generated method stub
		return attributes;
	}
	public void setTattribue( String Tattribue) {
		// TODO Auto-generated method stub
		 this.attributes = Tattribue;
	
	}
	
	
}
