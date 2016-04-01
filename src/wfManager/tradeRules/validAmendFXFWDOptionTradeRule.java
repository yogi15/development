package wfManager.tradeRules;

import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import beans.Trade;
import dbSQL.TradeSQL;
import dsServices.RemoteTrade;
import wfManager.TradeRule;

public class validAmendFXFWDOptionTradeRule implements TradeRule {

	
	/*
	 * (non-Javadoc)
	 * @see wfManager.TradeRule#checkValid(beans.Trade, beans.Trade, java.util.Vector, dsServices.RemoteTrade, productPricing.Pricer)
	 * reject  amendment  or cancellation of FXFWD Option if child trade exists. 
	 */
	
	@Override
	public boolean checkValid(Trade newTrade, Trade oldTrade,
			Vector messageData, RemoteTrade remoteTrade, Pricer pricer,Connection   con) {
		// TODO Auto-generated method stub
		boolean flag = true;
		 if(newTrade.getAction().equalsIgnoreCase("ROLLBACK") || newTrade.getAction().equalsIgnoreCase("ROLLOVER"))  {
			  messageData.add("ROLLBACK OR ROLLOVER not allowed on FX Time Option");
			 return false;
		 }
        if(newTrade.getAction().equalsIgnoreCase("CANCEL") || newTrade.getAction().equalsIgnoreCase("AMEND")) {
        	
           String sql = " parentid = " + newTrade.getId();
           Vector childTrades = (Vector) TradeSQL.selectwhere(sql, con);
           if(childTrades.size() > 0)  {
        	   messageData.add("Child Trade exists AMEND and CANCEL Rejected");
        	   flag = false;
           }
		
        }
		
		return flag;
		
	}

	@Override
	public boolean update(Trade newTrade, Trade oldTrade, Vector messageData,
			RemoteTrade remoteTrade, Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		return true;
	}

}
