package beans;

import java.io.Serializable;

public class AccTriggerEvent implements Serializable  {
	
	int id;
	int accEventConfigID;
	String triggerEvent;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccEventConfigID() {
		return accEventConfigID;
	}
	public void setAccEventConfigID(int accEventConfigID) {
		this.accEventConfigID = accEventConfigID;
	}
	public String getTriggerEvent() {
		return triggerEvent;
	}
	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}
	
	
	
	

}
