package beans;

import java.io.Serializable;

public class Favorities implements Serializable {
	
	int userId = 0;
	static public  String book = "Book";

	static public  String CounterParty = "CounterParty";
	static public  String trader = "Trader";
	static public  String CurrencyPair = "CurrencyPair";
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	String type = "";
	String typeValue = "";
	String typeName = "";

}
