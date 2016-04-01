package dsEventProcessor;

import java.io.Serializable;

import beans.Message;
import beans.Trade;
import beans.Transfer;

public class MessageEventProcessor extends EventProcessor  implements Serializable {
	
	
	
	Trade trade;
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public Transfer getTransfer() {
		return transfer;
	}
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	Transfer transfer;
	int tradeID;
	int transferID;
	String eventType;
	String processName;
	public void setProcessName(String string) {
		// TODO Auto-generated method stub
		processName = "MessageEngineProcess";
	}
	
	public String getProcessName() {
		// TODO Auto-generated method stub
		return processName;
	}
	Message message = null;
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		
	}
	public Message getMessage() {
		// TODO Auto-generated method stub
		return message;
		
	}
	

}
