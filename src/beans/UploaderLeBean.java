package beans;

import java.io.Serializable;

public class UploaderLeBean implements Deal, Serializable {

	int id = 0;
	String name = "";
	String role = "";
	String status = "";
	String attributes = "";
	String alias = "";
	String country = "";
	String contact = "";
	String productType = "";
	String parentEntity = "";
	String accountManager = "";
	String periodStartDate = "";
	String periodEndDate = "";
	String isdaSubmitted = "";
	
	LeContacts leContactsBean;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getParentEntity() {
		return parentEntity;
	}

	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	public String getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	public String getIsdaSubmitted() {
		return isdaSubmitted;
	}

	public void setIsdaSubmitted(String isdaSubmitted) {
		this.isdaSubmitted = isdaSubmitted;
	}

	public LeContacts getLeContactsBean() {
		return leContactsBean;
	}

	public void setLeContactsBean(LeContacts leContactsBean) {
		this.leContactsBean = leContactsBean;
	}

	
}