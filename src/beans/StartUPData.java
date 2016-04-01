package beans;

import java.io.Serializable;


public class StartUPData implements Serializable {
	
	
	String name;
	String value;
	String commts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCommts() {
		return commts;
	}
	public void setCommts(String commts) {
		this.commts = commts;
	}
	

}
