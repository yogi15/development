package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import dsEventProcessor.LimitEventProcessor;

import beans.Limit;
import beans.LimitConfig;
import beans.LimitUsage;
import beans.Trade;

public interface RemoteLimit extends Remote {
	
	public boolean checkLimitsExceedOnTrade(Trade trade,String type) throws RemoteException;
	public Vector<Limit> getLimitsOnTrader(int TraderID,Trade trade)  throws RemoteException;
	public Vector<Limit> getLimitsOnCounterParty(int CounterPartyID,Trade trade)  throws RemoteException;
	public int saveLimit(Limit limit)   throws RemoteException;
	public int updateLimit(Limit limit)   throws RemoteException;
	public boolean deleteLimit(Limit limit)   throws RemoteException;
	public boolean saveLimitUsage(LimitUsage limitUsage) throws RemoteException;
	public int saveLimitConfig(LimitConfig limitConfig,boolean flagToGetId) throws RemoteException;
	public LimitConfig getLimitConfig(int limitConfigID) throws RemoteException;
	public LimitUsage getLimitUsage(Limit limit) throws RemoteException;
	public Vector<LimitConfig> getCounterPartyLimitConfig(int counterPartyID) throws RemoteException;
	public Vector<LimitConfig> getTraderLimitConfig(String traderID,Trade trade) throws RemoteException;
	public Vector<LimitConfig> getNominalLimitConfig(String productType,String currency,Trade trade) throws RemoteException;
	public Vector<LimitConfig> getSettlementDate(String deliveryDate,Trade trade) throws RemoteException;
	public Collection getBreachDetailsOnLimit(Trade trade) 
			throws RemoteException;
	public Collection getAllUnquineLimitConfig() throws RemoteException;
	public Vector<LimitConfig> getLimitConfig(String limitConfigName) throws RemoteException;
	public Vector<Limit> getLimitOnLimitConfig(int  limitConfigID) throws RemoteException;
	public Vector<LimitConfig> getTraderLimitConfig(int traderID) throws RemoteException;
	public Vector<LimitConfig> getIssuerLimitConfig(int cpid) throws RemoteException;
	
	
	
	
	
	

}
