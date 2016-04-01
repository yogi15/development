package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;
import java.rmi.Remote;

import dsEventProcessor.EventProcessor;

import beans.EventController;
import beans.Trade;
import beans.WFConfig;

public interface RemoteTrade extends Remote {
	
	public int saveTrade(Trade trade)throws RemoteException;
	public Vector getTradeRollOverHierarchies(int tradeID) throws RemoteException;
	public Vector getTradeRollBackHierarchies(int tradeID) throws RemoteException;
	public Vector saveTrade(Trade trade,Vector<String> message)throws RemoteException;
	public Trade getTradeOldVersion(int tradeID,int tradeVersion)throws RemoteException;
	public boolean isDifferentUser(Trade trade)throws RemoteException;
	public void isEventExceuted(EventProcessor event) throws RemoteException;
	
	public boolean checkLimitOnTrade(Trade trade,String type) throws RemoteException;
	
	public Trade selectTrade(int tradeID)throws RemoteException;
	public Collection  selectALLTrades() throws RemoteException;
	public boolean removeTrade(Trade trade) throws RemoteException;
	public boolean updateTrade(Trade trade) throws RemoteException;
	public Vector getSplitTrades(Trade trade) throws RemoteException;
	public Vector getMirrorTrades(int mirrorID) throws RemoteException;
	
	
	public void publishnewTrade(String messageIndicator,String messageType,Object object) throws RemoteException;
	public Collection getAction(Trade trade) throws RemoteException;
	public Collection getAction(String tradeStatus) throws RemoteException;
	public Collection getAllActionsOnStatus(String tradeStatus) throws RemoteException;
	public Collection getAuditedTrade(int tradeID) throws RemoteException;
	public Collection getTradesforReport(String sql) throws RemoteException;
	public Collection selectWhere(String sql) throws RemoteException;
	public Collection selectforOpen(String productype) throws RemoteException;
	public Collection getSDisOnTrade(Trade trade) throws RemoteException;
	public Trade getTradeOnVersion(int tradeID,int version)  throws RemoteException;
	public Vector selectFeesonTrade(int id) throws RemoteException;
	public Collection getOnlyAction(Trade trade) throws RemoteException;
	public void updateTradeAndPublish(Trade trade, int userID) throws RemoteException;
	public boolean isValidAction(Trade trade) throws RemoteException;
	public Collection<Vector<Trade>> getChildTrades(int parentID) throws RemoteException;
	public Trade getOutStandingBalanceonFWDOption(int FWDOptionID)  throws RemoteException;
	public Trade getOutStandingBalanceonFWDOption(int FWDOptionID,int tradeID)  throws RemoteException;
	public Vector<String> saveBatchSplitTrades(Vector<Trade> splitTrades,Trade originalTrade,Vector<String>  message) throws RemoteException;
	public Vector<String> saveBatchSplitTrades(Vector<Trade> splitTrades,Vector<String> message)  throws RemoteException;
	public Vector<Trade> getB2Btrades(Trade trade)  throws RemoteException;
//	public Trade undo(Trade trade, int tradeVersion) throws RemoteException;
	
	public String selectTradeAttributesAsString(String tradeId) throws RemoteException;
	public   Collection getFTDReport(String sql) throws RemoteException;
	public Collection getVarReport(String sql) throws RemoteException;
}
