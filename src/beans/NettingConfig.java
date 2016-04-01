package beans;

import java.io.Serializable;

public class NettingConfig implements Serializable {
	
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeid() {
		return leid;
	}
	public void setLeid(int leid) {
		this.leid = leid;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	int leid;
	String productType;
	String currency;
	String effectiveDate;
	String endEdate;
	public String getEndEdate() {
		return endEdate;
	}
	public void setEndEdate(String endEdate) {
		this.endEdate = endEdate;
	}
	
	
	
	

}
