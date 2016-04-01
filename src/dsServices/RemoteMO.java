package dsServices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import beans.Liquidation;
import beans.OpenTrade;
import beans.Openpos;
import beans.Position;
import beans.Trade;

public interface RemoteMO extends Remote {
	
	public Collection getOpenPosition(Trade trade) throws RemoteException;
	public Collection getOpenPosition(int positionID) throws RemoteException;
	public Collection getOpenPositionLIFO(int positionID) throws RemoteException;
	public Collection getPositionOnSettleDateForBook(String settleDate,int bookID)  throws RemoteException;
	public Collection getPositionOnSettleDateForCurrency(String settleDate,String currency )  throws RemoteException;
	public Position getPositionOnSettleDateOnCurrencyOnBook(String settleDate,String currency,int bookID )  throws RemoteException;
	public double getPNLRealisedOnPosition(int positionID) throws RemoteException;
	public void saveOpenPosition(Openpos pos) throws RemoteException;
	public void removeOpenPosition(Trade trade) throws RemoteException;
	public void updateOpenPosition(Trade trade) throws RemoteException;
	public void  updateOpenPosition(Openpos opos) throws RemoteException;
	public Collection getFXPosition(String currencyPair,int bookID) throws RemoteException;
	
	public double getWACOnPosition(int positionID)  throws RemoteException;
	
	public Openpos getOpenPositionOnTradeID(int tradeID) throws RemoteException;
	public Collection getALLPosition() throws RemoteException;
	public Vector<Position> getPositionOnWhere(String where,String criteria) throws RemoteException;
	public Collection getOpenPositionOnWhere(String where,String criteria) throws RemoteException;
	public Position getPosition(int posID)  throws RemoteException;
	public Position getPositionOnTrade(Trade trade)  throws RemoteException;
	public Position savePosition(Position pos) throws RemoteException;
	public void removePosition(Trade trade) throws RemoteException;
	public Position updatePosition(Trade trade) throws RemoteException;
	
	public Collection getLiqPosition(int posID)  throws RemoteException;
	public void publishnewPosition(String messageIndicator,String messageType,Object object) throws RemoteException;
	
	public void saveLiqPosition(Liquidation liq) throws RemoteException;
	public void removeLiqPosition(Trade trade) throws RemoteException;
	public void updateLiqPosition(Trade trade) throws RemoteException;
	public void  updatePosition(Position position) throws RemoteException;
	
	public Collection getUnRealisedPosition(int posID) throws RemoteException;
	public Liquidation getRealisedPosition(int posID) throws RemoteException;
	public Collection getLiqPosition(int positionid, Trade trade)  throws RemoteException;
//	public Collection getLiqPosition(int positionid, Trade trade)  throws RemoteException;
	
	public void removePositionOnTrade(Position undoPosition,Openpos openpos)  throws RemoteException;
	public void updatePositionOnTrade(Position undoPosition,Openpos openpos)  throws RemoteException;
	public Vector getLiqPositionUndo(int positionId, Trade trade)  throws RemoteException;
	public void removeBatchLiqPositions(String where, String criteria)
			throws RemoteException;
	public void updateOpenPosition(Vector<Openpos> collectOpenPositionforUpdate) throws RemoteException;
	public void saveLiqudation(Vector<Liquidation> collectLiqudiation) throws RemoteException;
	public  Collection getLiquidationsOnPosition(int positionID) throws RemoteException;
	public Vector getOpenPositionOnProductSubType(String distinctproductSubType)
			throws RemoteException;
	public Openpos getOpenPositionOnFxSwapLeg(int tradeid, String fxSwapLeg) throws RemoteException;
	public void processManualLiquidation(Vector<OpenTrade> dataOpenTrades) throws RemoteException;
	public void updateCashPositionOnTakeUp(Trade trade) throws RemoteException;
	
	public void generateCashPositionOnMMTrade(Trade mmTrade)  throws RemoteException;
	public void updateCashPositionOnMMTrade(Trade mmTrade)  throws RemoteException;
	
	

}
