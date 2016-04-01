package wfManager;

import java.sql.Connection;
import java.util.Vector;

import dsServices.RemoteBOProcess;
import dsServices.RemoteTrade;

import productPricing.Pricer;
import beans.Trade;
import beans.Transfer;

public interface TransferRule {
	
	public boolean checkValid(Trade trade,Transfer oldTrade,Transfer newTransfer,RemoteTrade remoteTrade,RemoteBOProcess remoteBO,Vector messageData,Pricer pricer,Connection con);
	public boolean update(Trade trade,Transfer oldTrade,Transfer newTransfer,RemoteTrade remoteTrade,RemoteBOProcess remoteBO,Vector messageData,Pricer pricer,Connection con);

}
