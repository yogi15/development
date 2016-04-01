package wfManager.tradeRules;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import beans.Trade;
import dsEventProcessor.LimitEventProcessor;
import dsServices.RemoteTrade;
import wfManager.TradeRule;

public class validLimitBreachTradeRule implements TradeRule {

	@Override
	public boolean checkValid(Trade newTrade, Trade oldTrade,
			Vector messageData, RemoteTrade remoteTrade, Pricer pricer,
			Connection con) {
		// TODO Auto-generated method stub
		try {
			if(isTradeValidForLimit(newTrade) )  {
			boolean flag = remoteTrade.checkLimitOnTrade(newTrade,"CounterPartyLimit");
			if(!flag) {
				messageData.addElement(new String("Trade Breaching Limits    "));
				return flag;
			}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	private boolean isTradeValidForLimit(Trade trade) {
		// TODO Auto-generated method stub
		if(trade.getVersion() == 0)
			return true;
		if(trade.isEconomicChanged())
			return true;
		return false;
	}
	@Override
	public boolean update(Trade newTrade, Trade oldTrade, Vector messageData,
			RemoteTrade remoteTrade, Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
