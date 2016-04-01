package eventPS;

import java.io.Serializable;

import beans.Trade;

public class EventPSDealData  implements EventPS {
	
	int value ;   
	String id;
	Trade trade;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
	public void setTrade(Trade trad) {
		this.trade = trad;
	}
	
	public Trade getTrade() {
		return trade;
	}
	

}
