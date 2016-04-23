package beans;

import constants.BookConstants;
import constants.LeContactsConstants;

public class LeContacts implements BaseBean {

	int id = 0;
	int leId = 0;
	String leRole = "";
	int poId = 0;
	String ProductType = "";
	String contactType = "";
	String leFirstName = "";
	String leLastName = "";
	String leTitle = "";
	String city = "";
	String zipCode = "";
	String state = "";
	String country = "";
	String mailingAddress1 = "";
	String mailingAddress2 = "";
	String emailID = "";
	String phone = "";
	String fax = "";
	String telex = "";
	String swift = "";
	String comments = "";
	String effectiveFrom = "";
	String effectiveTo = "";
	String externalRef = "";

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		Object obj = null;
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ID)) {

			return obj = getId();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.LEID)) {
			return obj = getLeId();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ROLE)) {
			return obj = getLeRole();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.CONTACTTYPE)) {
			return obj = getContactType();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.FIRSTNAME)) {
			return obj = getLeFirstName();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.LASTNAME)) {
			return obj = getLeLastName();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.PRODUCTTYPE)) {
			return obj = getProductType();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.MAILINGADDRESS1)) {

			return obj = getMailingAddress1();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.MAILINGADDRESS2)) {
			return obj = getMailingAddress2();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.CITY)) {
			return obj = getCity();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.COUNTRY)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.STATE)) {
			return obj = getState();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ZIPCODE)) {
			return obj = getZipCode();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.SWIFTCODE)) {
			return obj = getSwift();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.PHONE)) {
			return obj = getPhone();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.FAX)) {
			return obj = getFax();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.EMAILID)) {
			return obj = getEmailID();
		}

		return obj;
	}

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub

		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ID)) {
			setId((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.LEID)) {
			setLeId((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ROLE)) {
			setLeRole((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.CONTACTTYPE)) {
			setContactType((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.FIRSTNAME)) {
			setLeFirstName((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.LASTNAME)) {
			setLeLastName((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.PRODUCTTYPE)) {
			setProductType((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.MAILINGADDRESS1)) {
			setMailingAddress1((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.MAILINGADDRESS2)) {
			setMailingAddress2((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.CITY)) {
			setCity((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.STATE)) {
			setState((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.ZIPCODE)) {
			setZipCode((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.SWIFTCODE)) {
			setSwift((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.PHONE)) {
			setPhone((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.FAX)) {
			setFax((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LeContactsConstants.EMAILID)) {
			setEmailID((String) object);
		}

	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

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

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
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

	public String getMailingAddress1() {
		return mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
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
		if (addressType.equalsIgnoreCase("SWIFT"))
			return getSwift();
		if (addressType.equalsIgnoreCase("FAX"))
			return getFax();
		if (addressType.equalsIgnoreCase("TELEX"))
			return getTelex();
		return null;
	}

}
