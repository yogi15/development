package dsServices;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import mqServices.messageProducer.CreateNewMessage;
import util.commonUTIL;
import dbSQL.CashPositionSQL;
import dbSQL.LiquidationSQL;
import dbSQL.OpenPosSQL;
import dbSQL.PositionSQL;
import dbSQL.dsSQL;
import dsEventProcessor.LiquidationEventProcessor;
import dsEventProcessor.PositionEventProcessor; 
import beans.CashPosition;
import beans.Liquidation;
import beans.OpenTrade;
import beans.Openpos;
import beans.Position;
import beans.Trade;

public class MOImp implements RemoteMO {
	
	RemoteTrade remoteTrade = null;
	public static  ServerConnectionUtil de = null;
	
	

	@Override
	public Collection getLiqPosition(int liqID) throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationSQL.selectLiquidation(liqID, dsSQL.getConn());
	}
    
	
	@Override
	public Collection getLiquidationsOnPosition(int positionID) throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationSQL.selectLiquidationOnPosition(positionID, dsSQL.getConn());
	}
	@Override
	public Collection getOpenPosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		return OpenPosSQL.selectopenposOnKey(trade.getBookId(), trade.getProductId(), trade.getTradedesc1(), dsSQL.getConn());
		
	}
	@Override
	public Openpos getOpenPositionOnTradeID(int tradeID) throws RemoteException {
		// TODO Auto-generated method stub
		
		return OpenPosSQL.selectOpenposOnTradeID(tradeID, dsSQL.getConn());
		
	}
	
	@Override
	public Vector getOpenPositionOnProductSubType(String distinctproductSubType) throws RemoteException {
		// TODO Auto-generated method stub
		
		return OpenPosSQL.getDistinctOpenPositionWhere(distinctproductSubType,  dsSQL.getConn());
		
	}
	@Override
	public Collection getOpenPositionOnWhere(String where,String criteria) throws RemoteException {
		// TODO Auto-generated method stub
		String whereClause = where;
		if(criteria != null && criteria.length() > 0 ) {
			if(criteria.equalsIgnoreCase("in")) {
				whereClause = " tradeid  in (" + whereClause +")"; 
			}
		}
		return OpenPosSQL.getOpenPositionWhere(whereClause,dsSQL.getConn());
		
	}
	
	@Override
	public void removeBatchLiqPositions(String where,String criteria) throws RemoteException {
		// TODO Auto-generated method stub
		String whereClause = where;
		if(criteria != null && criteria.length() > 0 ) {
			if(criteria.equalsIgnoreCase("in")) {
				whereClause = " liquid  in (" + whereClause +")"; 
			}
		}
		 LiquidationSQL.removeLiqPos(whereClause,dsSQL.getConn());
		
	}
	
	
	@Override
	public Collection getOpenPosition(int positionID) throws RemoteException {
		// TODO Auto-generated method stub
		return OpenPosSQL.selectOpenPositionOnPositionId(positionID, dsSQL.getConn());
	}
	@Override
	public Position getPosition(int posID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLiqPosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOpenPosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveLiqPosition(Liquidation liq) throws RemoteException {
		// TODO Auto-generated method stub
		if(LiquidationSQL.save(liq, dsSQL.getConn())) {
			LiquidationEventProcessor liduidatedEvent = generateLiquidEvent(liq);
			try {
				if(remoteTrade == null) {
						de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
					remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
					remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE", liduidatedEvent);
				} else {
				
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE", liduidatedEvent);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	public LiquidationEventProcessor generateLiquidEvent(Liquidation liq) { 
		LiquidationEventProcessor liquidEvent = new LiquidationEventProcessor();
		liquidEvent.setTradeID(liq.getsTradeId());
		liquidEvent.setLiquidation(liq);
		liquidEvent.setPositionID(liq.getPositionId());
		liquidEvent.setBookID(liq.getBookId());
		liquidEvent.setEventType("POSITION_REALISED");
		return liquidEvent;
		
	}

	@Override
	public void saveOpenPosition(Openpos newpos) throws RemoteException {
		OpenPosSQL.save(newpos, dsSQL.getConn());
		Trade trade = null;
		if(newpos.getProductType().equalsIgnoreCase("FX")) {
			 if(remoteTrade == null) {
				 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
					remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
					trade = remoteTrade.selectTrade(newpos.getTradeId());
			 } else {
				 trade = remoteTrade.selectTrade(newpos.getTradeId());
			 }
			 Vector<CashPosition> cashPs = 	createCashPosition(newpos,trade);
			
			 for(int i=0;i < cashPs.size();i++) {
				 CashPosition cashPosition1  =  cashPs.get(i);
				 CashPositionSQL.save(cashPosition1, dsSQL.getConn());
				 
				 
			 }
		}
		
	}

	
	
	public Vector<CashPosition> createCashPosition(Openpos newpos,Trade trade) {
		Vector<CashPosition> cashPositions = new Vector<CashPosition>();
		CashPosition cashPosition1 = new CashPosition();
		cashPosition1.setCpID(trade.getCpID());
		cashPosition1.setBookId(newpos.getBookId());
		cashPosition1.setOpenpositionDate(newpos.getOpenpositionDate());
		cashPosition1.setQuantity(newpos.getQuantity());
		cashPosition1.setProductId(newpos.getProductId());
		cashPosition1.setTradeId(newpos.getTradeId());
		cashPosition1.setPrice(newpos.getPrice());
		cashPosition1.setProductType(newpos.getProductType());
		cashPosition1.setProductSubType(newpos.getTradedesc1());
		
		cashPosition1.setType(getTradeType(newpos.getSign()));
		cashPosition1.setOpenQuantity(newpos.getQuantity());
		cashPosition1.setPositionId(newpos.getPositionId());
		cashPosition1.setQuotingAmt(newpos.getQuotingAmt());
		cashPosition1.setSettleDate(newpos.getSettleDate());
		cashPosition1.setTradeDate(newpos.getTradeDate());
		cashPosition1.setOpenNominal(newpos.getOpenNominal());
		cashPosition1.setFxSwapLegType(newpos.getFxSwapLegType());
		cashPosition1.setTradedesc1(newpos.getTradedesc1());
		cashPosition1.setPrimaryCurr(newpos.getPrimaryCurr());
		cashPosition1.setQuotingCurr(newpos.getQuotingCurr());
		cashPosition1.setCurrency(newpos.getPrimaryCurr());
		 cashPosition1.setActualAmt(cashPosition1.getQuantity());
		 cashPosition1.setId(newpos.getId());
		 cashPosition1.setLeg(0);
		cashPositions.add(cashPosition1);
			CashPosition cashPosition2 = new CashPosition();
			cashPosition2.setBookId(newpos.getBookId());
			cashPosition2.setOpenpositionDate(newpos.getOpenpositionDate());
			cashPosition2.setQuantity(newpos.getQuantity());
			cashPosition2.setProductId(newpos.getProductId());
			cashPosition2.setTradeId(newpos.getTradeId());
			cashPosition2.setPrice(newpos.getPrice());
			cashPosition2.setProductType(newpos.getProductType());
			cashPosition2.setCpID(trade.getCpID());
				cashPosition2.setProductSubType(newpos.getTradedesc1());
			
			//	cashPosition2.setType(getTradeType(newpos.getSign()));
				cashPosition2.setOpenQuantity(newpos.getQuantity());
				cashPosition2.setPositionId(newpos.getPositionId());
				cashPosition2.setQuotingAmt(newpos.getQuotingAmt());
				cashPosition2.setSettleDate(newpos.getSettleDate());
				cashPosition2.setTradeDate(newpos.getTradeDate());
				cashPosition2.setOpenNominal(newpos.getOpenNominal());
				cashPosition2.setFxSwapLegType(newpos.getFxSwapLegType());
				cashPosition2.setTradedesc1(newpos.getTradedesc1());
				cashPosition2.setPrimaryCurr(newpos.getPrimaryCurr());
				cashPosition2.setQuotingCurr(newpos.getQuotingCurr());
				cashPosition2.setCurrency(newpos.getQuotingCurr());
				 cashPosition2.setId(newpos.getId());
				cashPosition2.setLeg(1);
				if(cashPosition1.getType().equalsIgnoreCase("BUY")) {
				    cashPosition2.setType("SELL");
				    cashPosition1.setQuotingAmt(0);
				    cashPosition2.setQuantity(0);
				   
				} else  {
					 cashPosition2.setType("BUY");
					
					 cashPosition1.setQuotingAmt(0);
					    cashPosition2.setQuantity(0);
				}
				cashPosition2.setActualAmt(cashPosition2.getQuotingAmt());
				cashPositions.add(cashPosition2);
			return	cashPositions;
			
	}
	
	public String getTradeType(int i) {
		if(i == 1) 
			return "BUY";
		return "SELL";
	}
	
	
	@Override
	public Position savePosition(Position pos) throws RemoteException {
		// TODO Auto-generated method stub
		Position pos1 = (Position) PositionSQL.save(pos,dsSQL.getConn());
		publishPosition(pos1,null);
		   return pos1;
		
	}

	@Override
	public void updateLiqPosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOpenPosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateOpenPosition(Openpos opos) throws RemoteException {
		// TODO Auto-generated method stub
		OpenPosSQL.update(opos, dsSQL.getConn());
		Trade trade = null;
		 if(remoteTrade == null) {
			 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
				remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
				trade = remoteTrade.selectTrade(opos.getTradeId());
		 } else {
			 trade = remoteTrade.selectTrade(opos.getTradeId());
		 }
		 Vector<CashPosition> cashPs =  createCashPosition(opos, trade);
		 for(int i=0;i < cashPs.size();i++) {
			 CashPosition cashPosition1  =  cashPs.get(i);
			 CashPositionSQL.update(cashPosition1, dsSQL.getConn());
			 
			 
		 }
		 
	}
	@Override
	public Position updatePosition(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void updatePosition(Position position) throws RemoteException {
		// TODO Auto-generated method stub
	
		 if( PositionSQL.update(position, dsSQL.getConn()))
			 publishPosition(position,null);
		  
		
	}

	
	// we can create Position as per Liquidation configuration by passing Liquidation Config object
	@Override
	public Position getPositionOnTrade(Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		Position pos = null;
		if(trade.getProductType().equalsIgnoreCase("FX")) {
			if( commonUTIL.getOnlyDate(trade.getTradeDate()) == null) 
				return null;
			 pos = PositionSQL.selectFXPositionOnSettleDateCurrencyBookKey(trade.getTradeDate().substring(0, 10),trade.getBookId(), trade.getTradedesc(), dsSQL.getConn());
		} else {
		     pos = PositionSQL.selectopenposOnKey(trade.getBookId(), trade.getProductId(), trade.getTradedesc1(), dsSQL.getConn());
		}
		 return pos;
	}

	
	
	@Override
	public Liquidation getRealisedPosition(int posID) throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationSQL.getRealisedPosition(posID,dsSQL.getConn());
	}

	@Override
	public Collection getUnRealisedPosition(int posID) throws RemoteException {
		// TODO Auto-generated method stub
	
		return OpenPosSQL.getUnRealisedPosition(posID,dsSQL.getConn());
	}

	@Override
	public Collection getLiqPosition(int positionID, Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		String where = "";
				if(trade != null) {
				     where = " positionid = " + positionID + "  and ftradeid = " + trade.getId() + "  or stradeid = " + trade.getId(); 
				} else {
					 where = " positionid = " + positionID;
				}
		return LiquidationSQL.selectOnWhere(where,dsSQL.getConn());
	}
	
	

	@Override
	public void removePositionOnTrade(Position undoPosition,Openpos pos) {
		// TODO Auto-generated method stub
	//	PositionSQL.delete(undoPosition, con)
		OpenPosSQL.delete(pos, dsSQL.getConn());
		PositionSQL.update(undoPosition, dsSQL.getConn());
		 publishPosition(undoPosition,null);
		
	}

	@Override
	public void updatePositionOnTrade(Position undoPosition,Openpos pos) {
		// TODO Auto-generated method stub
		OpenPosSQL.update(pos, dsSQL.getConn());
		PositionSQL.update(undoPosition, dsSQL.getConn());
		publishPosition(undoPosition,null);
	}

	@Override
	public Vector getLiqPositionUndo(int positionId, Trade trade) {
		// TODO Auto-generated method stub
		return LiquidationSQL.getLiqPositionForUndo(dsSQL.getConn(),positionId,trade.getId());
	}

	@Override
	public void updateOpenPosition(Vector<Openpos> OpenPositionforUpdate)
			throws RemoteException {
		// TODO Auto-generated method stub
		OpenPosSQL.update(OpenPositionforUpdate, dsSQL.getConn());
		
	}

	@Override
	public void saveLiqudation(Vector<Liquidation> collectLiqudiation)
			throws RemoteException {
		// TODO Auto-generated method stub
		LiquidationSQL.save(collectLiqudiation, dsSQL.getConn());
		
	}

	@Override
	public Collection getALLPosition() throws RemoteException {
		// TODO Auto-generated method stub
		return PositionSQL.selectALL(dsSQL.getConn());
	}


	@Override
	public Collection getFXPosition(String currencyPair,int bookID) throws RemoteException {
		// TODO Auto-generated method stub
		return PositionSQL.selectFXPositions(currencyPair,bookID,dsSQL.getConn());
	}

	
	
	
	private void publishPosition(Position position,Trade trade) {
		// TODO Auto-generated method stub
		PositionEventProcessor positionEvent = new PositionEventProcessor();
		positionEvent.setPosition(position);
		positionEvent.setPositionID(position.getPositionId());
		positionEvent.setType("POSITION");
	//	positionEvent.setTrade(trade);
	//	positionEvent.setTradeID(trade.getId());
		try {
			if(remoteTrade == null) {
					de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
				remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","LIQPOSITION", positionEvent);
			} else {
			
			remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","LIQPOSITION", positionEvent);
		}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

	
	
	

	@Override
	public void publishnewPosition(String messageIndicator, String messageType,
			Object object) throws RemoteException {
		try{   
			if(getNewMessage() == null) {
					startProducingMessage();
					newMessage.produceNewMessage(messageIndicator,"LIQPOSITION",messageType,(Serializable) object,"true"); 
			} else {
			
			newMessage.produceNewMessage(messageIndicator,"LIQPOSITION",messageType,(Serializable) object,null); 
			//newMessage.run();
			}
			}catch(Exception e){
					commonUTIL.displayError("MOImp", "publishnewTrade", e);
					}   
		} 
		
	

public void startProducingMessage() {
	   newMessage	 = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616");
	  
		Thread sendMessage =  new Thread(newMessage);
		 setNewMessage(newMessage);
		 sendMessage.start();
		 
}

public CreateNewMessage getNewMessage() {
	return newMessage;
}



public void setNewMessage(CreateNewMessage newMessage) {
	this.newMessage = newMessage;
}
CreateNewMessage newMessage = null;
@Override
public Vector<Position> getPositionOnWhere(String where, String criteria)
		throws RemoteException {
	
	// TODO Auto-generated method stub
	return  PositionSQL.getPositionONWhere(where,dsSQL.getConn());
}


public double getWACOnPosition(int positionID) throws RemoteException {
	// TODO Auto-generated method stub
	return OpenPosSQL.getWACRate(positionID, dsSQL.getConn());
}


@Override
public Collection<Vector> getOpenPositionLIFO(int positionID) throws RemoteException {
	// TODO Auto-generated method stub
	return (Vector) OpenPosSQL.selectopenposOnPositionIddesc(positionID, dsSQL.getConn());
}


@Override
public Openpos getOpenPositionOnFxSwapLeg(int tradeid, String fxSwapLeg)
		throws RemoteException {
	// TODO Auto-generated method stub
	String sql = " tradeid =" + tradeid + " and fxswaplegtype ='"+fxSwapLeg+"'";
	Vector vect = (Vector) OpenPosSQL.getOpenPositionWhere(sql, dsSQL.getConn());
	if(vect == null || vect.isEmpty())
		return null;
	Openpos ops =(Openpos) vect.elementAt(0);
	return ops;
}


@Override
public double getPNLRealisedOnPosition(int positionID) throws RemoteException {
	// TODO Auto-generated method stub
	return LiquidationSQL.getPNLRealised(positionID, dsSQL.getConn());
}


@Override
public Collection getPositionOnSettleDateForBook(String settleDate, int bookID)
		throws RemoteException {
	// TODO Auto-generated method stub
	return PositionSQL.selectFXPositionOnSettleDateBookKey(settleDate, bookID, dsSQL.getConn());
}


@Override
public Collection getPositionOnSettleDateForCurrency(String settleDate,
		String currency) throws RemoteException {
	// TODO Auto-generated method stub
	return PositionSQL.selectFXPositionOnSettleDateCurrencyKey(settleDate, currency, dsSQL.getConn());
}


@Override
public Position getPositionOnSettleDateOnCurrencyOnBook(String settleDate,
		String currency, int bookID) throws RemoteException {
	// TODO Auto-generated method stub
	return PositionSQL.selectFXPositionOnSettleDateCurrencyBookKey(settleDate, bookID, currency, dsSQL.getConn());
	
}


@Override
public void processManualLiquidation(Vector<OpenTrade> dataOpenTrades)
		throws RemoteException {
	// TODO Auto-generated method stub
	if(dataOpenTrades.size() == 2 ) {
		OpenTrade firstTrade = dataOpenTrades.get(0);
		OpenTrade secondTrade = dataOpenTrades.get(1);
		Liquidation liq = genearateManualLiquidation(firstTrade,secondTrade);
		LiquidationSQL.save(liq, dsSQL.getConn());
		Openpos openPosf = OpenPosSQL.getOpenPositionOnID(firstTrade.getOpenID(),dsSQL.getConn());
		Openpos openPoss = OpenPosSQL.getOpenPositionOnID(secondTrade.getOpenID(),dsSQL.getConn());
		openPoss.setOpenQuantity(0);
		if(firstTrade.getType().equalsIgnoreCase("BUY")) {
			openPosf.setOpenQuantity(openPosf.getOpenQuantity() - secondTrade.getOpenQuantity());
		} else {
			openPosf.setOpenQuantity(openPosf.getOpenQuantity() + secondTrade.getOpenQuantity());
		}
		OpenPosSQL.update(openPoss, dsSQL.getConn());	
		OpenPosSQL.update(openPosf, dsSQL.getConn());
		Position pos = PositionSQL.selectPosition(firstTrade.getPositionid(),dsSQL.getConn());
		
		
		
	}
	
}


private Liquidation genearateManualLiquidation(OpenTrade firstTrade,
		OpenTrade secondTrade) {
	Liquidation liq = new Liquidation();
	liq.setBookId(firstTrade.getBookid());
	liq.setfTradeId(firstTrade.getTradeid());
	liq.setsTradeId(secondTrade.getTradeid());
	liq.setfPrice(firstTrade.getPrice());
	liq.setsPrice(secondTrade.getPrice());
	liq.setPositionId(firstTrade.getPositionid());
	liq.setLidDate(firstTrade.getDeliveryDate());
	liq.setCurrency(secondTrade.getCurrencyPair().substring(4, 7));
	
//	liq.setProductId(firstTrade.getp)
	//liq.setLidDate(firstTrade.gets); // 
	if(firstTrade.getType().equalsIgnoreCase("BUY")) {
		liq.setQuantity(secondTrade.getOpenQuantity());
		liq.setTradeType("SELL");
	} else {
		liq.setQuantity(secondTrade.getOpenQuantity() *-1);
		liq.setTradeType("BUY");
	}
	liq.setRealisedPNL(liq.getQuantity() * (firstTrade.getPrice() - secondTrade.getPrice()));
	
	// TODO Auto-generated method stub
	return liq;
}


  

@Override
public void updateCashPositionOnTakeUp(Trade trade) throws RemoteException {
	// TODO Auto-generated method stub
	if(trade != null && trade.getTradedesc1().equalsIgnoreCase("FXTAKEUP")) {
		//String currency 
	     Vector<CashPosition> cps = CashPositionSQL.getForwardOptionCashPositionOnTakeup(trade.getParentID(),dsSQL.getConn());
	     CashPosition realisedCash = CashPositionSQL.getRealisedAmt(trade.getParentID(), dsSQL.getConn());
	     if(!commonUTIL.isEmpty(cps)) {
	    	for(int i=0;i < cps.size();i++) {
	    	CashPosition cash =  cps.get(i);
	    	String primaryCurr = trade.getTradedesc().substring(0, 3);
	    	String secondCurr = trade.getTradedesc().substring(4, 7);
	    	if(cash.getCurrency().trim().equalsIgnoreCase(primaryCurr)) {
	    	
	    		if(trade.getType().equalsIgnoreCase("BUY"))
		    		cash.setOut1amount(realisedCash.getOut1amount());
		    		else 
		    			cash.setOut1amount(realisedCash.getOut1amount());
	    	} else {
	    		if(trade.getType().equalsIgnoreCase("BUY"))
	    		cash.setOut2amount(realisedCash.getOut2amount());
	    		else 
	    			cash.setOut2amount(realisedCash.getOut2amount());
	    	}
	    	CashPositionSQL.update(cash, dsSQL.getConn());
	    	}
	     }
		
	}
	
}


@Override
public void generateCashPositionOnMMTrade(Trade mmTrade) throws RemoteException {
	// TODO Auto-generated method stub
	CashPosition cashPosition1 = new CashPosition();
	cashPosition1.setCpID(mmTrade.getCpID());
	cashPosition1.setBookId(mmTrade.getBookId());
	cashPosition1.setOpenpositionDate(mmTrade.getTradeDate());
	cashPosition1.setQuantity(mmTrade.getQuantity());
	cashPosition1.setProductId(mmTrade.getProductId());
	cashPosition1.setTradeId(mmTrade.getId());
	cashPosition1.setPrice(mmTrade.getPrice());
	cashPosition1.setProductType(mmTrade.getProductType());
	cashPosition1.setProductSubType(mmTrade.getTradedesc1());
	
	cashPosition1.setType(mmTrade.getType());
	cashPosition1.setOpenQuantity(mmTrade.getQuantity());
	cashPosition1.setPositionId(0);
	cashPosition1.setQuotingAmt(0);
	cashPosition1.setSettleDate(mmTrade.getDelivertyDate());
	cashPosition1.setTradeDate(mmTrade.getTradeDate());
	cashPosition1.setOpenNominal(0);
	//cashPosition1.setFxSwapLegType(newpos.getFxSwapLegType());
	cashPosition1.setTradedesc1(mmTrade.getTradedesc1());
	cashPosition1.setPrimaryCurr(mmTrade.getCurrency());
//	cashPosition1.setQuotingCurr(newpos.getQuotingCurr());
	cashPosition1.setCurrency(mmTrade.getCurrency());
	 cashPosition1.setActualAmt(cashPosition1.getQuantity());
	 cashPosition1.setId(mmTrade.getId());
	 CashPositionSQL.save(cashPosition1, dsSQL.getConn());
	
}


@Override
public void updateCashPositionOnMMTrade(Trade mmTrade) throws RemoteException {
	// TODO Auto-generated method stub
	CashPosition cashPosition1 = CashPositionSQL.selectOpenposOnTradeID(mmTrade.getId(),dsSQL.getConn());
	cashPosition1.setCpID(mmTrade.getCpID());
	cashPosition1.setBookId(mmTrade.getBookId());
	cashPosition1.setOpenpositionDate(mmTrade.getTradeDate());
	cashPosition1.setQuantity(mmTrade.getQuantity());
	cashPosition1.setProductId(mmTrade.getProductId());
	cashPosition1.setTradeId(mmTrade.getId());
	cashPosition1.setPrice(mmTrade.getPrice());
	cashPosition1.setProductType(mmTrade.getProductType());
	cashPosition1.setProductSubType(mmTrade.getTradedesc());
	
	cashPosition1.setType(mmTrade.getType());
	cashPosition1.setOpenQuantity(mmTrade.getQuantity());
	cashPosition1.setPositionId(0);
	cashPosition1.setQuotingAmt(0);
	cashPosition1.setSettleDate(mmTrade.getDelivertyDate());
	cashPosition1.setTradeDate(mmTrade.getTradeDate());
	cashPosition1.setOpenNominal(0);
	//cashPosition1.setFxSwapLegType(newpos.getFxSwapLegType());
	cashPosition1.setTradedesc1(mmTrade.getTradedesc1());
	cashPosition1.setPrimaryCurr(mmTrade.getCurrency());
//	cashPosition1.setQuotingCurr(newpos.getQuotingCurr());
	 cashPosition1.setCurrency(mmTrade.getCurrency());
	 cashPosition1.setActualAmt(cashPosition1.getQuantity());
	 cashPosition1.setId(mmTrade.getId());
	 CashPositionSQL.update(cashPosition1, dsSQL.getConn());
	 
	
}




}
