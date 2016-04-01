package beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class Account implements Serializable {
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public int getCpId() {
		return cpId;
	}
	public void setCpId(int cpId) {
		this.cpId = cpId;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
	
	public Hashtable<String, String> getAttributesData() {
		if(attributesData == null) 
			attributesData = new Hashtable<String, String>();
		if(attributesData.size() == 0)
			setAttributesData(getAttributes());
		return attributesData;
	}
	
	public void addAttribues(String key,String value) {
		if(((key != null) && (!key.isEmpty())) && ((value != null) && (!value.isEmpty()))) {
			attributesData.put(key, value);
		}
		this.attributes =getALLAttributesData();
	}
	
	
	public void removeAttribues(String key) {
		if(((key != null) && (!key.isEmpty())) ) {
			attributesData.remove(key);
		}
		this.attributes =getALLAttributesData();
	}
	
	
	public String getAttributeValue(String key) {
		String value = null;
		if(attributesData.size() == 0)
			setAttributesData(attributes);
		attributesData.get(key);
		return value;
	}
	
	public void setAttributesData(String attributesFromTransfer) {
		if(((attributesFromTransfer != null) && (!attributesFromTransfer.isEmpty()))) {
			String [] attr = attributesFromTransfer.split(";");
			for(int i=0;i<attr.length;i++) {
				String value = attr[i].substring(attr[i].indexOf('=')+1, attr[i].length());
    			String key = attr[i].substring(0, attr[i].indexOf('='));
    			attributesData.put(key,value);
    			
			}
				 
			
		}
	}
	
	
	public boolean containTransferKey(String key) {
		return attributesData.containsKey(key);
	}
	
	public String getALLAttributesData() {
		String allAttributes = "";
		if(((attributesData != null) && (!attributesData.isEmpty()))) {
			
			Enumeration<String > keys = attributesData.keys();
			while(keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = (String) attributesData.get(key);
				allAttributes = allAttributes.trim() + key +"="+value+";";
		    }
			
	        
       }
		this.attributes = allAttributes;
       return allAttributes;

	}
	
	int id;
	String accountName;
	String currency;
	String desc;
	String attributes;
	String type;
	int poid;
	int cpId;
	String closingDate;
	String creationDate;
	int parentID;
	
	
	

}
