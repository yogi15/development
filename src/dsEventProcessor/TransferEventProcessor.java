package dsEventProcessor;

import java.io.Serializable;

import beans.Trade;
import beans.Transfer;

public class TransferEventProcessor  extends EventProcessor  implements Serializable  {
	
	Trade trade = null;
	
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}

//	int tradeID = 0;
	
	public void setTransferID(int transferID) {
		this.transferID = transferID;
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
	
	Transfer transfer = null;
	
	
	
	
	
	

}
