package dsEventProcessor;

import java.io.Serializable;

import util.commonUTIL;

import beans.Users;

public class EventProcessor implements Serializable,Cloneable {
	int clientID = 0;
	public void setClientID(int clientID) {
		// TODO Auto-generated method stub
		this.clientID = clientID;
		
	}
	public int getClientID() {
		return clientID;
	}
 boolean publishFlag = true;
	
	public boolean getPublishFlag() {
		return publishFlag;
	}
	public void setPublishFlag(boolean publishFlag) {
		this.publishFlag = publishFlag;
	}
	public boolean isAdminEvent = false;
	public String adminClearedEventType = "";
	
	
	  /**
	 * @return the adminClearedEventType
	 */
	public String getAdminClearedEventType() {
		return adminClearedEventType;
	}
	/**
	 * @param adminClearedEventType the adminClearedEventType to set
	 */
	public void setAdminClearedEventType(String adminClearedEventType) {
		this.adminClearedEventType = adminClearedEventType;
	}
	/**
	 * @return the isAdminEvent
	 */
	public boolean isAdminEvent() {
		return isAdminEvent;
	}
	/**
	 * @param isAdminEvent the isAdminEvent to set
	 */
	private void setAdminEvent(boolean isAdminEvent) {
		this.isAdminEvent = isAdminEvent;
	}
	public String occurrenceTime;
	int eventid = 0;
	private String occrrenceDate;
	int tradeID = 0;
	int taskID = 0;
	boolean isEventProcess = false;
	int objectID;
	boolean isSaveToDB = false;
	public void setSavetoDB(boolean flag) {
		isSaveToDB = flag;
	}
    public boolean isSavetoDB() {
    	return isSaveToDB;
    }
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
		 
	}
	String type = "";
	
	/**
	 * @return the occrrenceDate
	 */
	public String getOccrrenceDate() {
		return occrrenceDate;
	}
	/**
	 * @param occrrenceDate the occrrenceDate to set
	 */
	public void setOccrrenceDate(String occrrenceDate) {
		this.occrrenceDate = occrrenceDate;
	}
	/**
	 * @return the objectID
	 */
	public int getObjectID() {
		return objectID;
	}
	/**
	 * @param objectID the objectID to set
	 */
	public void setObjectID(int objectID) {
		this.objectID = objectID;
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
	/**
	 * @return the consumed
	 */
	public boolean isConsumed() {
		return consumed;
	}
	/**
	 * @param consumed the consumed to set
	 */
	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
	/**
	 * @return the objectVersionID
	 */
	public int getObjectVersionID() {
		return objectVersionID;
	}
	/**
	 * @param objectVersionID the objectVersionID to set
	 */
	public void setObjectVersionID(int objectVersionID) {
		this.objectVersionID = objectVersionID;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @param occurrenceTime the occurrenceTime to set
	 */
	public void setOccurrenceTime(long occurrenceTime) {
		this.occurrenceTime = String.valueOf(occurrenceTime);
	}
	boolean publish = false;
	boolean consumed = false;
	int objectVersionID = 0;
	String comments = "";
	
	
	
	
	
	
	
	/**
	 * @param occurrenceTime the occurrenceTime to set
	 */
	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}
	public boolean isEventProcess() {
		return isEventProcess;
	}
	public void setEventProcess(boolean isEventProcess) {
		this.isEventProcess = isEventProcess;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public final int getTradeID() {
		return tradeID;
	}
	int transferID = 0;
	public final int getTransferID() {
		return transferID;
	}
	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	String eventType = "";
	String processName = "";
	Users user = null;
	String ServerName = "";
	
	public EventProcessor() {
		eventid = 0;
    	occurrenceTime = String.valueOf(System.currentTimeMillis());
    	occrrenceDate = commonUTIL.dateToString(commonUTIL.getCurrentDate());
	}
	public String getOccurrenceDate()
    {
    	return occrrenceDate;
    }
	public String getOccurrenceTime()
    {
    	return occurrenceTime;
    }
			
	int tradeVersion =0;
	public int getTradeVersion() {
		return tradeVersion;
	}
	public void setTradeVersion(int tradeVersion) {
		this.tradeVersion = tradeVersion;
	}
	public int getTransferVerson() {
		return transferVerson;
	}
	public void setTransferVerson(int transferVerson) {
		this.transferVerson = transferVerson;
	}
	int transferVerson =0;
	
	public String toString() {
		return "Event (" + getClass().getName() + ") : " + eventid;
	    }
	
	public String getEventType() {
		return eventType;
	    }
	public String getName() {
		return getClassName();
	}
	final public String getClassName() {
		String className = getClass().getName();
		String subStr = className.substring(className.lastIndexOf('.')+1);
		return subStr;
	    }
	String queueName = "";
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueN) {
	 this.queueName = queueN;
		
	}
	String subscribeList = "";
	public String getSubscribeList() {
		return subscribeList;
	}
	public void setSubscribableList(String subscribeList1) {
	 this.subscribeList = subscribeList1;
		
	}
	
	final public String getServerName() { return ServerName;}
    final public void setServerName(String s) { ServerName=s;}
    boolean isClearedByAdmin = false;
	public boolean isClearedByAdmin() {
		// TODO Auto-generated method stub
		return isClearedByAdmin;
	}
	public void isClearedByAdmin(boolean isClearedByAdmin) {
		// TODO Auto-generated method stub
		this. isClearedByAdmin = isClearedByAdmin;
	}
}
