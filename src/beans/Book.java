package beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import constants.BookConstants;
 

public class Book implements BaseBean {

	int id;
	int customerID;
	String bookName, timezone, holidaycode, currency;

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

	int folderID;
	AttributeContainer attributeContainer = null;

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

	String attributes;

	public int getID() {
		return id;
	}

	public void setID(int bookno) {
		this.id = bookno;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int le_id) {
		this.customerID= le_id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String book_name) {
		this.bookName = book_name;
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

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj =null;
		if(propertyPaneColumnName.equalsIgnoreCase(BookConstants.ID)) {
			return obj = getID();
		}
		 
		 return obj;
		 } 

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub

		 
		  
		 

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
