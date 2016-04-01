package wfManager;

import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import dsServices.Remote;
import dsServices.RemoteTrade;
import beans.Trade;

public interface TradeRule {
	
	public boolean checkValid(Trade newTrade,Trade oldTrade,Vector messageData,RemoteTrade remoteTrade,Pricer pricer,Connection   con);
	public boolean update(Trade newTrade,Trade oldTrade,Vector messageData,RemoteTrade remoteTrade,Pricer pricer,Connection   con);

}
