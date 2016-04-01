package beans;

import java.io.Serializable;

public class LeContacts implements Serializable {

	 int id = 0; 
	 int leId = 0;
	 String leRole = "";
	 int poId = 0;
	 String contactCategory = ""; 
	 String leFirstName = "";
	 String leLastName = "";
	 String leTitle = "";
	 String city = "";
	 String zipCode = ""; 
	 String state = ""; 
	 String country = "" ;
	 String mailingAddress = "";
	 String emailAddresss = "";
	 String phone = "";
	 String fax ="";
	 String telex = "";
	 String swift = "";
	 String comments = "";
	 String effectiveFrom = "";
	 String effectiveTo = "";
	 String externalRef = "";
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeId() {
		return leId;
	}
	public void setLeId(int leId) {
		this.leId = leId;
	}
	public String getLeRole() {
		return leRole;
	}
	public void setLeRole(String leRole) {
		this.leRole = leRole;
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	public String getContactCategory() {
		return contactCategory;
	}
	public void setContactCategory(String contactCategory) {
		this.contactCategory = contactCategory;
	}
	public String getLeFirstName() {
		return leFirstName;
	}
	public void setLeFirstName(String leFirstName) {
		this.leFirstName = leFirstName;
	}
	public String getLeLastName() {
		return leLastName;
	}
	public void setLeLastName(String leLastName) {
		this.leLastName = leLastName;
	}
	public String getLeTitle() {
		return leTitle;
	}
	public void setLeTitle(String leTitle) {
		this.leTitle = leTitle;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getEmailAddresss() {
		return emailAddresss;
	}
	public void setEmailAddresss(String emailAddresss) {
		this.emailAddresss = emailAddresss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelex() {
		return telex;
	}
	public void setTelex(String telex) {
		this.telex = telex;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public String getExternalRef() {
		return externalRef;
	}
	public void setExternalRef(String externalRef) {
		this.externalRef = externalRef;
	}
	public String getAddressCode(String addressType) {
		// TODO Auto-generated method stub
		if(addressType.equalsIgnoreCase("SWIFT")) 
			return getSwift();
		if(addressType.equalsIgnoreCase("FAX")) 
			return getFax();
		if(addressType.equalsIgnoreCase("TELEX")) 
			return getTelex();
		return null;
	}
	String productType;
	public String getProductType() {
		// TODO Auto-generated method stub
		return productType;
	}
	public void setProductType(String productType) {
		// TODO Auto-generated method stub
		 this.productType = productType;
	}
	 

}
