package dsEventProcessor;

import java.io.Serializable;

import beans.Message;
import beans.Task;
import beans.Trade;
import beans.Transfer;

public class TaskEventProcessor  extends EventProcessor  implements Serializable {
	Task task = null;
	
	int tradeVersion = 0;
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	int transferVersion = 0;
	int userID = 0 ;
	
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
	Trade trade;
	  Transfer transfer;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
 
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	Message message = null;
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		
		
	}
	public Message getMessage() {
		return message;
	}
	
	
	
	
	

}
