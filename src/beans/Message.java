package beans;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Hashtable;

public class Message implements Serializable, Cloneable {
	transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
		public static final String FORMAT_ISSUE_MSG_ATTR = "FORMAT ISSUE";
	 /**
     * Msg Attribute for Message references.
     * In certain instances, messages must have awareness of
     * each other (For SWIFT tags, typically)
     */
    static final public String MESSAGE_REF = "MessageRef";
    /**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	int userID = 0;
	int id = 0;
	 int tradeId = 0;
	 int transferId = 0;
	 String messageType = "";
	 String productType = "";
	 String senderName = "";
	 String senderRole = "";
	 String receiverRole = "";
	 String receiverName = "";
	 int tradeVersion = 0;
	 int transferVersion = 0;
	 String action = "";
	 String status = "";
	 String addressType = "";
	 String templateName ="";
	 int linkId = 0;
	 String messageDate = "";
	 String tradeDate = "";
	 String messageGateway = "";
	 String productSubType = "";
	 /**
	 * @return the taskID
	 */
	public int getTaskID() {
		return taskID;
	}
	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
	/**
	 * @return the receiverID
	 */
	public int getReceiverID() {
		return receiverID;
	}
	/**
	 * @param receiverID the receiverID to set
	 */
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	/**
	 * @return the poContactType
	 */
	public String getPoContactType() {
		return poContactType;
	}
	/**
	 * @param poContactType the poContactType to set
	 */
	public void setPoContactType(String poContactType) {
		this.poContactType = poContactType;
	}
	/**
	 * @return the senderID
	 */
	public int getSenderID() {
		return senderID;
	}
	/**
	 * @param senderID the senderID to set
	 */
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	int taskID = 0; 
	 
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderRole() {
		return senderRole;
	}
	public void setSenderRole(String senderRole) {
		this.senderRole = senderRole;
	}
	public String getReceiverRole() {
		return receiverRole;
	}
	public void setReceiverRole(String receiverRole) {
		this.receiverRole = receiverRole;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public int getTradeVersion() {
		return tradeVersion;
	}
	public void setTradeVersion(int tradeVersion) {
		this.tradeVersion = tradeVersion;
	}
	public int getTransferVersion() {
		return transferVersion;
	}
	public void setTransferVersion(int transferVersion) {
		this.transferVersion = transferVersion;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getMessageGateway() {
		return messageGateway;
	}
	public void setMessageGateway(String messageGateway) {
		this.messageGateway = messageGateway;
	}
	public String getProductSubType() {
		return productSubType;
	}
	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}
	String eventType="";
	public String getEventType() {
		// TODO Auto-generated method stub
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	String triggerON = "";
	public String getTriggerON() {
		// TODO Auto-generated method stub
		return triggerON;
	}
	public void setTriggerON(String triggerON) {
		// TODO Auto-generated method stub
		this.triggerON = triggerON;
	}
	int productID =0;
	public int getproductID() {
		// TODO Auto-generated method stub
		return productID;
	}
	public void setproductID(int productID) {
		// TODO Auto-generated method stub
		this.productID = productID;
	}
	String attributes;
	
	
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
		
	}
	
	public void setAttributes(String attributeName,String value) {
		if(attributesData == null) 
			attributesData = new Hashtable<String,String>();
attributesData.put(attributeName,value);
		
	}
	public String getAttributeValue(String attributeName) {
		return attributesData.get(attributeName);
				
			}
	String format = "";
	private String _senderAddressCode;
	public void setFormat(String formatType) {
		// TODO Auto-generated method stub
		format = formatType;
	}
	public String getFormat() {
		// TODO Auto-generated method stub
		return format;
	}
	 /**
     * Returns the sender person's address string, which is an
     * actual address value like a fax number. See details at
     * {@link #setSenderAddressCode(String) setSenderAddressCode}.
     *
     * @return   the sender's address String, which is an actual
     * address value like a fax number
     */
	 protected String             _receiverAddressCode;
	 final public void setSenderAddressCode(String s) {  _senderAddressCode=s;}

    final public String getSenderAddressCode() { return _senderAddressCode;}
    final public String getReceiverAddressCode() { return _receiverAddressCode;}
    final public void setReceiverAddressCode(String s) {  _receiverAddressCode=s;}
    int receiverID;
	public int getReceiverId() {
		// TODO Auto-generated method stub
		return receiverID;
	}
	public void setReceiverId(int receiverID) {
		// TODO Auto-generated method stub
		this.receiverID =  receiverID;
	}
	public String getSenderContactType() {
		// TODO Auto-generated method stub
		return poContactType;
	}
	public String getReceiverContactType() {
		// TODO Auto-generated method stub
		return receiverContactType;
	}
	public String getSettleDate() {
		// TODO Auto-generated method stub
		return null;
	}
	String poContactType="";
	public void setSenderContactType(String poContactType) {
		// TODO Auto-generated method stub
		this.poContactType = poContactType;
	}
	String receiverContactType="";
	public void setReceiverContactType(String receiverContactType) {
		// TODO Auto-generated method stub
		this.receiverContactType = receiverContactType;
	}
	 int senderID = 0;
	public void setSenderId(int poid) {
		// TODO Auto-generated method stub
		this.senderID = poid;
		
	}
	public int getSenderId() {
		// TODO Auto-generated method stub
		return senderID ;
		
	}
	public boolean getExternalB() {
		// TODO Auto-generated method stub
		return false;
	}
	public String  getAttribute(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getLanguage() {
		// TODO Auto-generated method stub
		return "English";
	}
	int messageConfigID = 0;
	public void setMessageConfigID(int messageConfig) {
		// TODO Auto-generated method stub
		messageConfigID = messageConfig;
		
	}
	public int getMessageConfigID() {
		return messageConfigID;
	}
	String subAction = "NEW";
	public void setSubAction(String subAction) {
		this.subAction = subAction;
	}
	public String getSubAction() {
		return subAction;
	}
	String isUpdatedBeforeSend = "";
	public String getUpdateBeforeSend() {
		// TODO Auto-generated method stub
		return isUpdatedBeforeSend;
	}
	public void setUpdateBeforeSend(String flagforUpdate) {
		// TODO Auto-generated method stub
		isUpdatedBeforeSend =  flagforUpdate;
	}
	public Object clone() throws CloneNotSupportedException {

		return super.clone();

		}
	
}
