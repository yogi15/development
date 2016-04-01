package beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class Book implements BaseBean {
	
	int bookno;
	int le_id;
	String book_name;
	int folderID;
	
transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
	
	public Hashtable<String, String> getAttributesData() {
		if(attributesData == null) 
			attributesData = new Hashtable<String, String>();
		if(attributesData.size() == 0)
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
	
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public int getLe_id() {
		return le_id;
	}
	public void setLe_id(int le_id) {
		this.le_id = le_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
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
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		
	}

}
