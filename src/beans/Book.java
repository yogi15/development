package beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;


import constants.BookConstants;

 


public class Book implements BaseBean {

	
	int bookno;
	int le_id;
	String book_name,timezone, holidaycode, currency,country ;

	int folderID;
	AttributeContainer attributeContainer = null;
	String attributes;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		
		Object obj = null;
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.LEID)) {
			
			return obj = getCustomerID();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.BOOKNO)) {
			return obj = getID();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.BOOKNAME)) {
			return obj = getBookName();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.COUNTRY)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.HOLIDAYCODE)) {
			return obj = getHolidaycode();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.TIMEZONE)) {
			return obj = getTimezone();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.CURRENCY)) {
			return obj = getCurrency();
		}
		
				
		return obj;
	}
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.LEID)) {
			setCustomerID((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.BOOKNO)) {
			setID((Integer) object);
		}		
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.BOOKNAME)) {
			setBookName((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.HOLIDAYCODE)) {
			setHolidaycode((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.TIMEZONE)) {
			setTimezone((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(BookConstants.CURRENCY)) {
			setCurrency((String) object);
		}
		
		
	}
	

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getHolidaycode() {
		return holidaycode;
	}

	public void setHolidaycode(String holidaycode) {
		this.holidaycode = holidaycode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	transient Hashtable<String, String> attributesData = new Hashtable<String, String>();

	public Hashtable<String, String> getAttributesData() {
		if (attributesData == null)
			attributesData = new Hashtable<String, String>();
		if (attributesData.size() == 0)
			setAttributesData(getAttributes());
		return attributesData;
	}

	public int getFolderID() {
		return folderID;
	}

	public void setFolderID(int folderID) {
		this.folderID = folderID;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	

	public int getID() {
		return bookno;
	}

	public void setID(int bookno) {
		this.bookno = bookno;
	}

	public int getCustomerID() {
		return le_id;
	}

	public void setCustomerID(int le_id) {
		this.le_id= le_id;
	}

	public String getBookName() {
		return book_name;
	}

	public void setBookName(String book_name) {
		this.book_name = book_name;
	}

	public String getAttributeValue(String key) {
		String value = null;
		if (attributesData.size() == 0)
			setAttributesData(attributes);
		attributesData.get(key);
		return value;
	}

	public void setAttributesData(String attributesFromTransfer) {
		if (((attributesFromTransfer != null) && (!attributesFromTransfer.isEmpty()))) {
			String[] attr = attributesFromTransfer.split(";");
			for (int i = 0; i < attr.length; i++) {
				String value = attr[i].substring(attr[i].indexOf('=') + 1, attr[i].length());
				String key = attr[i].substring(0, attr[i].indexOf('='));
				attributesData.put(key, value);

			}

		}
	}

	public boolean containTransferKey(String key) {
		return attributesData.containsKey(key);
	}

	public String getALLAttributesData() {
		String allAttributes = "";
		if (((attributesData != null) && (!attributesData.isEmpty()))) {

			Enumeration<String> keys = attributesData.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = (String) attributesData.get(key);
				allAttributes = allAttributes.trim() + key + "=" + value + ";";
			}

		}
		this.attributes = allAttributes;
		return allAttributes;

	}

	public void addAttribues(String key, String value) {
		if (((key != null) && (!key.isEmpty())) && ((value != null) && (!value.isEmpty()))) {
			attributesData.put(key, value);
		}
		this.attributes = getALLAttributesData();
	}

	public void removeAttribues(String key) {
		if (((key != null) && (!key.isEmpty()))) {
			attributesData.remove(key);
		}
		this.attributes = getALLAttributesData();
	}


	public AttributeContainer getAttributeContainer() {
		return attributeContainer;
	}

	public void setAttributeContainer(AttributeContainer attributeContainer) {
		this.attributeContainer = attributeContainer;
	}

	public void setAttributesData(Hashtable<String, String> attributesData) {
		this.attributesData = attributesData;
	}

}
