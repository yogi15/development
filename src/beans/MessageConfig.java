package beans;

import java.io.Serializable;

public class MessageConfig implements Serializable {
	
	 int id = 0;
	 int poid = 0;
	 String eventType = "";
	 String productType = "";
	 String productSubType = "";
	 String receiverContactType = "";
	 String formatType = "";
	 int receiverID ;
	 String templateName = "";
	 String poContactType = "";
	 String messageType = "";
	 public String getGateWay() {
		return gateWay;
	}
	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}
	String gateWay ="";
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductSubType() {
		return productSubType;
	}
	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}
	public String getReceiverContactType() {
		return receiverContactType;
	}
	public void setReceiverContactType(String receiverContactType) {
		this.receiverContactType = receiverContactType;
	}
	public String getFormatType() {
		return formatType;
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getPoContactType() {
		return poContactType;
	}
	public void setPoContactType(String poContactType) {
		this.poContactType = poContactType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	String addressType;
	public String getAddressType() {
		// TODO Auto-generated method stub
		return addressType;
	}
	
	
	public void setAddressType(String addressType) {
		// TODO Auto-generated method stub
		this.addressType = addressType;
	}
	String receiverRole ="";
	public String getReceiverRole() {
		// TODO Auto-generated method stub
		return receiverRole;
	}
	public void setReceiverRole(String receiverRole) {
		// TODO Auto-generated method stub
		this.receiverRole = receiverRole;
	}
	String senderRole  = "";
	public void setSenderRole(String senderRole) {
		// TODO Auto-generated method stub
		this.senderRole = senderRole;
				
	}
	public String getSenderRole() {
		// TODO Auto-generated method stub
		return senderRole;
				
	}
}
