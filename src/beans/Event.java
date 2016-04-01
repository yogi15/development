package beans;

public class Event {
	

	int eventID;
	int objectID;
	boolean publish = false;
	
	int objectVersion = 0;
	
	/**
	 * @return the objectVersion
	 */
	public int getObjectVersion() {
		return objectVersion;
	}
	/**
	 * @param objectVersion the objectVersion to set
	 */
	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}
	/**
	 * @return the publish
	 */
	public boolean isPublish() {
		return publish;
	}
	/**
	 * @param publish the publish to set
	 */
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	public int getObjectID() {
		return objectID;
	}
	public void setObjectID(int object) {
		objectID =  object;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public int getTransferID() {
		return transferID;
	}
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}
	public boolean isConsumedFlag() {
		return consumedFlag;
	}
	public void setConsumedFlag(boolean consumedFlag) {
		this.consumedFlag = consumedFlag;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	String eventType;
	String type;
	int tradeID;
	int transferID;
	boolean consumedFlag = false;
	String sqlType;

}
