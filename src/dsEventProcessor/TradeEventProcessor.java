package dsEventProcessor;

import java.io.Serializable;

import beans.Trade;

public class TradeEventProcessor  extends EventProcessor  implements Serializable {
	
//	int tradeID = 0;
	boolean isLimitBreach = false;
	
	public boolean isLimitBreach() {
		return isLimitBreach;
	}
	public void setLimitBreach(boolean isLimitBreach) {
		this.isLimitBreach = isLimitBreach;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	
	
	
	Trade trade = null;
	
	

}
