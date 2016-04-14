package beans;

import java.io.Serializable;

public class Attribute implements Serializable {
	
	int id;
	String attributeName;
	String type;
	String attributeValue;
	public Attribute() {
		
	}
	public Attribute (String name,String value) {
		this.attributeName = name;
		this.attributeValue = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return attributeName;
	}
	public void setName(String name) {
		this.attributeName = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return attributeValue;
	}
	public void setValue(String value) {
		this.attributeValue = value;
	}
	
	
	
	
	

}

