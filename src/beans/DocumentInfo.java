package beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import util.commonUTIL;

import bo.swift.SwiftMessage;

public class  DocumentInfo implements Serializable {
	   protected String       _characterEncoding = commonUTIL.ENCODING;
	    protected byte[]       _bytes;
	    protected boolean      _isBinary = false;
	
	 private byte[] getBytes() {
	    	return _bytes;
	    }
	 public void setCharacterEncoding(String s) {
		 _characterEncoding = s;
	 }
	    private void setBytes(byte[] value) {
	    	_bytes = value;
	    }
	
	    /**
	     * @return true if the advice is associated with a binary document.
	     * false otherwise. If an advice is associated with both a binary and
	     * a text document, the text document prevails and this method returns
	     * false.
	     */
	    public boolean getIsBinary() {
	    	return _isBinary;
	    }
	    
	    /**
	     * Private setter for mapping to database column
	     * @param value
	     */
	    private void setIsBinary(boolean value) {
	    	_isBinary = value;
	    }
	    
	    public StringBuffer getHTMLDocumentData() {
	    	if (getIsBinary()) {
	    		return null;
	    	}
	    	byte[] bytes = getBytes();
	    	if (bytes == null) {
	    	    return null;
	    	}
	    	try {
	    		byte doc[] = getBytes();
	    		return new StringBuffer(new String(doc, commonUTIL.ENCODING));
	    	} catch (UnsupportedEncodingException e) {
	    	  
	    		return null;
	    	}
	    }
	public final void setHTMLDocumentData(StringBuffer strBuffer) {
       
        try {
			if (strBuffer != null) {
			    setBytes(strBuffer.toString().getBytes(commonUTIL.ENCODING));
			    setIsBinary(false);
			} else {
			    setBytes(null);
			    // _bytes == null is handled if isBinary == true
			    setIsBinary(true);  
			}
		} catch (UnsupportedEncodingException e) {
		    commonUTIL.displayError("DocumentInfo", "setDocument",e);
		}
    }
	int id = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
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
	int messageID =0;
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
	 SwiftMessage swiftM = null;
	public void setSwiftMessage(SwiftMessage swift) {
		// TODO Auto-generated method stub
		this.swiftM = swift;
		
	}
	public SwiftMessage getSwiftMessage() {
		// TODO Auto-generated method stub
		return swiftM;
		
	}
	public String getDocument() {
		// TODO Auto-generated method stub
		return null;
	}
}
