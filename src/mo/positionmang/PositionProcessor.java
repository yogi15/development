package mo.positionmang;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import oracle.net.aso.s;

import dsEventProcessor.PositionEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsManager.PositionManager;
import dsServices.RemoteMO;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import util.RemoteServiceUtil;
import util.commonUTIL;
import beans.Liquidation;
import beans.LiquidationConfig;
import beans.Openpos;
import beans.Position;
import beans.Product;
import beans.Trade;

public class PositionProcessor extends Thread {
	Trade trade = null;
	Hashtable< String,Openpos> openPositions = new Hashtable<String,Openpos>();
	Hashtable< Integer,Product> products = new Hashtable< Integer,Product>();
	RemoteMO remoteMO = null;
	RemoteTrade remoteTrade = null;
	boolean seqController = true;
	int counter = 0;
	PositionManager manager;
	RemoteReferenceData remoteRef;
  
	
	public PositionManager getManager() {
		return manager;
	}


	public void setManager(PositionManager manager) {
		this.manager = manager;
	}


	Hashtable<Integer,TradeEventProcessor> events = new Hashtable<Integer,TradeEventProcessor>();
	
	
	
	public Hashtable<Integer, TradeEventProcessor> getEvents() {
		return events;
	}


	public void setEvents(Hashtable<Integer, TradeEventProcessor> events) {
		this.events = events;
	}


	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}


	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}


	String flagForupdateOrDeletePosition = "Delete";
	final String productTYPEFX = "FX";
	private RemoteProduct remoteProduct;
	public PositionProcessor() {
		
	//	setRemoteMO(remoteMO);
	}
	
	
  public void  saveNewOpenPosition(Trade trade,Position position) {
	  try {
		remoteMO.saveOpenPosition(generateOpenPosition(trade,position));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void saveNewOpenPosition(Openpos pos) {
	  try {
		remoteMO.saveOpenPosition(pos);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	
  // this is only method to process FXPosition.
  public synchronized void processPositiononTrade(Trade trade) {
	  
	 if(trade.getProductType().equalsIgnoreCase("FX"))  {
		 commonUTIL.display("PositionManager Processor ", "Trade FX type "+ trade.getId() + "  positionBased **  "+trade.isPositionBased());
		 if(!trade.isPositionBased()) {
			 commonUTIL.display("PositionManager", "Trade FX type "+ trade.getId() + " is not Position Based");
			 if(trade.getTradedesc1().equalsIgnoreCase("FXTAKEUP")) {
				 try {
					remoteMO.updateCashPositionOnTakeUp(trade);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					 commonUTIL.display("PositionManager", "CashPosition getupdated  on trade "+ trade.getId() );
				}
				}
			     
			 
			 return;
		 }
		  if(trade.getVersion() == 1) {
			    if(trade.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
			    	Trade swapLeg =   trade.getSwapLeg();
			    	Trade primaryLeg = trade.getSwapPrimaryLeg();			    	
			    	 processFXPosition(primaryLeg,0,false);
			    	 processFXPosition(swapLeg,0,false);
			    	
			    } else {
			  	    processFXPosition(trade,0,false);
			    }
		  } else if(trade.getVersion() == -1  || trade.getStatus().equalsIgnoreCase("CANCELLED")) {
			  if(trade.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
			    	Trade swapLeg =   trade.getSwapLeg();
			    	Trade primaryLeg = trade.getSwapPrimaryLeg();
			    	 processFXPosition(primaryLeg,-1,false);
			    	 processFXPosition(swapLeg,-1,false);
			    	
			    } else {
			    		processFXPosition(trade,-1,false);
			    }
		  } else if(trade.getVersion() > 1) {
			  if(trade.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
				  if(trade.isEconomicChanged()) {
			    	Trade swapLeg =   trade.getSwapLeg();
			    	Trade primaryLeg = trade.getSwapPrimaryLeg();
			    	  processFXPositionForUpdate(primaryLeg);
			    	  processFXPositionForUpdate(swapLeg);
				  }
			    	
			    } else {
			    	if(trade.isEconomicChanged())
			  processFXPositionForUpdate(trade);
			    }
		  }
		 
	 } 
	 if(!trade.getProductType().equalsIgnoreCase("FX"))   {
		// System.out.println("processPosition at " + counter + " <<counter  trade >> " + trade.getId()+ " version  " + trade.getVersion() + " " + trade.getType() +  " << sell  quantity>> == "+ trade.getQuantity());
	  if(trade.getVersion() == 1) {
		  
		 // commonUTIL.display("PositionManager", "GeneratePositiong for Trade & version " + trade.getId() + " " +trade.getVersion() + "  Book " + trade.getBookId() + " productid " + trade.getProductId());
	       processPosition(trade);
	  } else if(trade.getVersion() == -1) {
		  setFlagForupdateOrDeletePosition("Delete");
		  undoTrade(trade);
	  } else if(trade.getVersion() > 1) {
		  setFlagForupdateOrDeletePosition("Update");
		  undoTrade(trade);
	  }
	 }
	
  }
   
  public void run(){
	  for( ; ; ) {
		  try {
			Thread.sleep(300);
			if(manager.tradeEvents.size() > counter) {
				
				  System.out.println(":pppp:"+manager.tradeEvents.size());
					 if(manager.tradeEvents.size() > counter) {
						 counter++;
						 TradeEventProcessor tradeEvent = manager.tradeEvents.get(counter);
						
						  if(!tradeEvent.getTrade().getProductType().equalsIgnoreCase("MM"))
						 processPositiononTrade(tradeEvent.getTrade());
					 }
				  }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  }
  }


public Openpos [] getOpenPositionOnTrade(Trade trade) {
	    //  Openpos op = generateOpenPosition(trade);
	      Openpos [] opos =  getOpenPositionFromDB(trade);
	      
	  return null;
  }
	
	
	private Openpos[] getOpenPositionFromDB(Trade trade) {
	// TODO Auto-generated method stub
		try {
			remoteMO.getOpenPosition(trade);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
}

	
	private void generateFXOpenPosition(Trade trade,Position position,Openpos op) {
		if(op == null)
			return;
		
		op.setBookId(trade.getBookId());
		op.setOpenpositionDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
		op.setQuantity(trade.getQuantity());
		op.setProductId(trade.getProductId());
		op.setTradeId(trade.getId());
		op.setPrice(trade.getPrice());
		op.setProductType(trade.getProductType());
		if(trade.getProductType().equalsIgnoreCase(productTYPEFX)) {
			op.setProductSubType(trade.getTradedesc());
		} else {
		op.setProductSubType(trade.getTradedesc1());
		}
		op.setSign(checkSign(trade.getType()));
		op.setOpenQuantity(trade.getQuantity());
		op.setPositionId(position.getPositionId());
		op.setQuotingAmt(trade.getNominal());
		op.setSettleDate(trade.getDelivertyDate());
		op.setTradeDate(trade.getTradeDate().substring(0, 10));
		op.setOpenNominal(trade.getNominal());
		op.setFxSwapLegType(trade.getFxSwapLeg());  
		op.setTradedesc1(trade.getTradedesc1());
		op.setPrimaryCurr(trade.getTradedesc().substring(0, 3));
		op.setQuotingCurr(trade.getTradedesc().substring(4, 7));
		
	}
private void generateFXCurrencyOpenPosition(Trade trade,Position position,Openpos op,boolean isPrimaryCurr) {
		
		op.setBookId(trade.getBookId());
		op.setOpenpositionDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
		
		op.setProductId(trade.getProductId());
		op.setTradeId(trade.getId());
		op.setPrice(trade.getPrice());
		op.setProductType(trade.getProductType());
		//if(trade.getProductType().equalsIgnoreCase(productTYPEFX)) {
			op.setProductSubType(position.getProductsubType());
		//} else {
	//	op.setProductSubType(trade.getTradedesc1());
		//}
		op.setSign(checkSign(trade.getType()));
		if(isPrimaryCurr) {
			op.setOpenQuantity(trade.getQuantity());
			op.setQuantity(trade.getQuantity());
		} else  {
			op.setOpenQuantity(trade.getNominal());
			op.setQuantity(trade.getNominal());
		}
		op.setPositionId(position.getPositionId());
	//	op.setQuotingAmt(trade.getQuantity());
		op.setSettleDate(trade.getDelivertyDate());
		op.setTradeDate(trade.getTradeDate().substring(0, 10));
	//	op.setOpenNominal(trade.getNominal());
		op.setFxSwapLegType(trade.getFxSwapLeg());
		op.setTradedesc1(trade.getTradedesc1());
		op.setTradedesc(trade.getTradedesc());
		
	}
	private synchronized  void  processPosition(Trade trade) {
		// TODO Auto-generated method stub
		
			
		
			//commonUTIL.display("PositionManager",""+ seqController);
		commonUTIL.display("PositionManager", "  Step 1  trade id " + trade.getId() + " for quantity == " + trade.getQuantity());
		  Position oldPosition = null;
		  Position newPosition = null;
			try {
				oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
				if(oldPosition == null) {
					
					newPosition =	remoteMO.savePosition(generatePositionOnGivenTrade(trade));
					
					saveNewOpenPosition(trade,newPosition);
					
				//	publishPosition(newPosition,trade);
					
				} else {
					commonUTIL.display("PositionManager", "Step 2" +  trade.getId() + " oldPosition == " + oldPosition.getPositionId());
					// caching of liqConfig can be done
					LiquidationConfig liqConfig = (LiquidationConfig)remoteRef.getLiquidationConfigOn(trade.getBookId(),trade.getProductType(),trade.getTradedesc1());
					if(liqConfig != null)
					oldPosition.setLigConfig(liqConfig);
					Vector openPos = null;
					if(isByLIFOMethod(liqConfig)) {
						openPos = (Vector) remoteMO.getOpenPositionLIFO(oldPosition.getPositionId());
					} else {
						openPos = (Vector) remoteMO.getOpenPosition(oldPosition.getPositionId());
					}
					processOpenPositions(openPos,trade,generateOpenPosition(trade,oldPosition),oldPosition);
				//	publishPosition(oldPosition,trade);
				}
				commonUTIL.display("PositionManager", "Completed Position for Trade " + trade.getId() + "  Book " + trade.getBookId() + " productid " + trade.getProductId());
				if(oldPosition != null) {
					commonUTIL.display("PositionManager"," For position " + oldPosition.getPositionId());
				//	commonUTIL.display("PositionManager"," For position " + oldPosition.getPositionId());
					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("positionProcessor", "processPosition", e);
			}
		}
		
	
	// in case of delete and update.
	

	private synchronized void undoTrade(Trade trade) {
		Position undoPosition = null;
		
		HashMap<Integer, Openpos> updateOpenposition = new HashMap<Integer,Openpos>();
			try {
				undoPosition = (Position) remoteMO.getPositionOnTrade(trade);
				
				
				if(undoPosition != null) {
					boolean noliquidation = true;
					Openpos pos = null;
					LiquidationConfig liqConfig = (LiquidationConfig)remoteRef.getLiquidationConfigOn(trade.getBookId(),trade.getProductType(),trade.getTradedesc1());
					if(liqConfig != null)
						undoPosition.setLigConfig(liqConfig);
					Openpos openPost = (Openpos) remoteMO.getOpenPositionOnTradeID(trade.getId());
					Vector liqupos = (Vector) remoteMO.getLiqPositionUndo(undoPosition.getPositionId(), trade);
					if(liqupos != null && liqupos.size()>0) {
						noliquidation = false;
					    reverseAllliquPosition(updateOpenposition,liqupos);
						
					}
					String tradeIDs = "";
					
					for (Map.Entry<Integer, Openpos> entry : updateOpenposition.entrySet()) {  
					//	System.out.println("Key = " + entry.getKey() + ", Value = " +((Openpos) entry.getValue()).getOpenQuantity()); 
						tradeIDs = tradeIDs + entry.getKey().intValue() + ",";
						}
					Vector openPos = null;
					if(tradeIDs.length() > 0 ) {
					 openPos = (Vector)		remoteMO.getOpenPositionOnWhere(tradeIDs.substring(0, tradeIDs.length()-1),"in");
					if(openPos != null && openPos.size()>0) {
						for(int i=0;i<openPos.size();i++) {
							Openpos opos = (Openpos) openPos.elementAt(i);
							Openpos temp = updateOpenposition.get(opos.getTradeId());
							opos.setOpenQuantity(opos.getOpenQuantity() + temp.getOpenQuantity()  );
							if(opos.getTradeId() == trade.getId())  {
								if(getFlagForupdateOrDeletePosition().equalsIgnoreCase("Delete")) {
								opos.setOpenQuantity(0);
								opos.setQuantity(0);
								} else {
									opos.setOpenQuantity(trade.getQuantity());
									opos.setQuantity(trade.getQuantity());
									opos.setSign(checkSign(trade.getType()));
									opos.setPrice(trade.getPrice());
								}
							}
							
							
						}
					}	
					
				  // 	System.out.println(openPos);
					   if(openPos != null ) {
						   for(int s=0;s<openPos.size();s++) {
							   Openpos opos = (Openpos) openPos.elementAt(s);
							   System.out.println(opos.getTradeId() + " " + opos.getQuantity() + " " + opos.getOpenQuantity());
						   }
						   Vector<Openpos> collectOpenPositionforUpdate = new Vector<Openpos>();
						   Vector<Openpos> collectOpenPositionforDelete = new Vector<Openpos>();
							Vector<Liquidation> collectLiqudiation = new Vector<Liquidation>();
							for(int i=0;i<openPos.size();i++) {
								Openpos opos = (Openpos) openPos.elementAt(i);
								if(opos.getQuantity() > 0)
								   processOpenPositionsOnUpdateAndDate(opos,collectOpenPositionforUpdate,collectLiqudiation,undoPosition);
								else 
									collectOpenPositionforDelete.add(opos);
							//	System.out.println(opos.getTradeId() + "  " + opos.getOpenQuantity());
								//remoteMO.updateOpenPosition(opos);
							}
							remoteMO.updateOpenPosition(collectOpenPositionforDelete);
							remoteMO.updateOpenPosition(collectOpenPositionforUpdate);
							remoteMO.saveLiqudation(collectLiqudiation);
							regeneratePosition(undoPosition);
					   }
					}
					
				
					if(noliquidation) {
						removePositionsOntradeWhenNoLiquationExists(undoPosition,openPost,getFlagForupdateOrDeletePosition(),generateOpenPosition(trade, undoPosition));
					}
					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("positionProcessor", "undoTrade", e);
			}
	}
	
	

	
	private synchronized void processOpenPositionsOnUpdateAndDate(Openpos newOpenPos, Vector<Openpos> collectOpenPositionforUpdate ,Vector<Liquidation> collectLiqudiation,Position position) {
		
		Vector<Openpos> collectOpenPositionforUpdateTemp = collectOpenPositionforUpdate;
		Vector<Liquidation> collectLiqudiationTemp = collectLiqudiation;
		if(collectOpenPositionforUpdate != null && collectOpenPositionforUpdate.size() > 0)  {
			  Iterator<Openpos > openpos = collectOpenPositionforUpdate.iterator();
			  double newopenPositionbalance = newOpenPos.getQuantity();
			  boolean samesign = true;  // used to check if negitive sign exist or not, if exits then  
			  boolean newopenPositionbalanceexists = true; // flag indicates balance exists in new open position. 
			  if(newopenPositionbalance <= 0)
				  newopenPositionbalanceexists = false;
			  if(newopenPositionbalanceexists) {   
			//  while(openpos.hasNext()) {
				 for(int i=0;i<collectOpenPositionforUpdate.size();i++) {
				 // Openpos pos = (Openpos) openpos.next();
					 Openpos pos = (Openpos) collectOpenPositionforUpdate.get(i);
					 if( pos.getSign() != newOpenPos.getSign()) {
						 samesign = false;
						 if(isOpenquantityExist(pos)) {
							 double balanceQuantity = getBalanceOpenQuantity(pos);
							 if(balanceQuantity > 0) {
								 if(balanceQuantity > newopenPositionbalance) {
									 double quantity = balanceQuantity - newopenPositionbalance;
											 double tradeAmount = newOpenPos.getTradeAmt();
									 pos.setOpenQuantity(quantity);
									 newOpenPos.setOpenQuantity(0);
									 newopenPositionbalanceexists = false;
									 collectOpenPositionforUpdateTemp.add(pos);
									 collectOpenPositionforUpdateTemp.add(newOpenPos);
									 collectLiqudiationTemp.add(generateLiqPosition(pos,newOpenPos,newopenPositionbalance,tradeAmount,position));
									 break;
								 } else {
									 double quantity = newopenPositionbalance - balanceQuantity;
									 double tradeAmount = pos.getTradeAmt();
									 pos.setOpenQuantity(0);
									 newOpenPos.setOpenQuantity(quantity);
									 newopenPositionbalance = newopenPositionbalance - balanceQuantity;
									 collectOpenPositionforUpdateTemp.add(pos);
									 collectOpenPositionforUpdateTemp.add(newOpenPos);
									 collectLiqudiationTemp.add(generateLiqPosition(pos,newOpenPos,balanceQuantity,tradeAmount,position));
								 }
							 }
								 
						 } 
						 
					 } else {
						 
					 }
				  }
				  
			  }
			  if(samesign) {
				  collectOpenPositionforUpdateTemp.add(newOpenPos);
			  }
		} else {
			collectOpenPositionforUpdateTemp.add(newOpenPos);
		}
		
		collectOpenPositionforUpdate = collectOpenPositionforUpdateTemp;
		collectLiqudiation = collectLiqudiationTemp;
	}
	
	
	
	
	
	private void reverseAllliquPosition(
			HashMap<Integer, Openpos> updateOpenposition, Vector liqupos) {
		Vector tempopenpos  = liqupos;
	    String removeliqIDs = "";
		for(int j=0;j<tempopenpos.size();j++) {
		Liquidation frecords =(Liquidation) tempopenpos.elementAt(j);
		removeliqIDs = removeliqIDs + frecords.getLiquid() + ",";
		double openQuantity = 0;
		if(!updateOpenposition.containsKey(frecords.getfTradeId())) {
		for(int i=0;i<liqupos.size();i++) {
			Liquidation  liq = (Liquidation) liqupos.elementAt(i);
			if(frecords.getfTradeId() == liq.getfTradeId() || frecords.getfTradeId() == liq.getsTradeId()) {
				openQuantity  = openQuantity  + liq.getQuantity();
			}
			 
		}
		}
		if(openQuantity > 0) {
		Openpos pos = new Openpos();
		pos.setTradeId(frecords.getfTradeId());
		pos.setOpenQuantity(openQuantity);
		updateOpenposition.put(frecords.getfTradeId(), pos);
		}
		}
		
		for(int s=0;s<tempopenpos.size();s++) {
			Liquidation srecords =(Liquidation) tempopenpos.elementAt(s);
			
			double sopenQuantity = 0;
			if(!updateOpenposition.containsKey(srecords.getsTradeId())) {
			for(int i=0;i<liqupos.size();i++) {
				Liquidation  liq = (Liquidation) liqupos.elementAt(i);
				if(srecords.getsTradeId() == liq.getfTradeId() || srecords.getsTradeId() == liq.getsTradeId()) {
					sopenQuantity  = sopenQuantity  + liq.getQuantity();
				}
				 
			}
			}
			if(sopenQuantity > 0) {
			Openpos poss = new Openpos();
			poss.setTradeId(srecords.getsTradeId());
			poss.setOpenQuantity(sopenQuantity);
			updateOpenposition.put(srecords.getsTradeId(), poss);
			}
		}
		removeliqIDs = removeliqIDs.substring(0, removeliqIDs.length() -1);
		try {
			remoteMO.removeBatchLiqPositions(removeliqIDs,"in");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// TODO Auto-generated method stub
		
	}


	private void removePositionsOntradeWhenNoLiquationExists(Position undoPosition,Openpos oldPos,String flagTodeleteOrUpdate,Openpos updatePos) {
		// TODO Auto-generated method stub
		try {
		if(flagTodeleteOrUpdate.equalsIgnoreCase("Delete")) {
			Openpos po = null;
			if(oldPos != null) {
				po = oldPos;
				undoPosition.setUnrealized(undoPosition.getUnrealized() - (po.getOpenQuantity() * po.getSign()));
				//System.out.println(undoPosition.getUnrealized());
			}
			// System.out.println(undoPosition.getUnrealized());
				remoteMO.removePositionOnTrade(undoPosition,po);
			
		} else { 
		
			Openpos po = null;
			if(oldPos != null) {
				po = oldPos;
				undoPosition.setUnrealized(undoPosition.getUnrealized() - (po.getQuantity() * po.getSign()));
			}
			  po = updatePos;
			  po.setId(oldPos.getId());
			  undoPosition.setUnrealized(undoPosition.getUnrealized() + (po.getQuantity() * po.getSign()));
			  if((oldPos.getSign() == updatePos.getSign()) && updatePos.getSign() == 1 ) {
			
			  undoPosition.setBuy_quantity((undoPosition.getBuy_quantity()  - oldPos.getOpenQuantity()) + updatePos.getOpenQuantity());
			  }
			  if((oldPos.getSign() == updatePos.getSign()) && updatePos.getSign() == -1 ) {
					
				  undoPosition.setSell_quantity((undoPosition.getSell_quantity()  - oldPos.getOpenQuantity()) + updatePos.getOpenQuantity());
				  }
			  if((oldPos.getSign() != updatePos.getSign()) && updatePos.getSign() == -1 ) {
				  undoPosition.setBuy_quantity((undoPosition.getBuy_quantity()  - oldPos.getOpenQuantity()) );
				  undoPosition.setSell_quantity((undoPosition.getSell_quantity()  + updatePos.getOpenQuantity()) );
			  }
			  if((oldPos.getSign() != updatePos.getSign()) && updatePos.getSign() == 1 ) {
				  undoPosition.setSell_quantity((undoPosition.getSell_quantity()  - oldPos.getOpenQuantity()) );
				  undoPosition.setBuy_quantity((undoPosition.getBuy_quantity()  + updatePos.getOpenQuantity()) );
			  }
			  remoteMO.updatePositionOnTrade(undoPosition,po);
		}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("PositionProcessor","removePositionsOntradeWhenNoLiquationExists" , e);
		}
	}

	
		
	
	private synchronized void processOpenPositions(Vector openPos, Trade trade,
			Openpos newOpenPos,Position position) {
		commonUTIL.display("PositionManager", "Step " + 3 + " processing  OpenPositions on trade id "  + trade.getId() + " for   position " + position.getPositionId());
		
		Vector<Openpos> collectOpenPositionforUpdate = new Vector<Openpos>();
		Vector<Liquidation> collectLiqudiation = new Vector<Liquidation>();
		if(newOpenPos != null) {
			if(newOpenPos.getSign() == 1 ) {
				position.setBuy_amount(position.getBuy_amount() + newOpenPos.getTradeAmt());
			} 
            if(newOpenPos.getSign() == -1) {
				position.setSell_amount(position.getSell_amount() + newOpenPos.getTradeAmt());
			}
		}
		
		if(openPos != null)  {
		  Iterator<Openpos > openpos = openPos.iterator();
		  double newopenPositionbalance = newOpenPos.getQuantity();
		  boolean samesign = true;  // used to check if negitive sign exist or not, if exits then  
		  boolean newopenPositionbalanceexists = true; // flag indicates balance exists in new open position. 
		  while(openpos.hasNext()) {
			  if(newopenPositionbalanceexists) {   
			  Openpos pos = (Openpos) openpos.next();
			 
				 if( pos.getSign() != newOpenPos.getSign()) {
					 samesign = false;
					 if(isOpenquantityExist(pos)) {
						 double balanceQuantity = getBalanceOpenQuantity(pos);
						 commonUTIL.display("PositionManager", "Step " + 3 + " processing  OpenPositions on trade id "  + trade.getId() + " with openPosition  on trade is " + balanceQuantity + "  on position " + pos.getPositionId());
						// commonUTIL.display("PositionManager", "Step " + 4 + " balanceQuantity == " + balanceQuantity + " on position " + pos.getPositionId());
						 if(balanceQuantity > 0) {
							 if(balanceQuantity > newopenPositionbalance) {
								 double quantity = balanceQuantity - newopenPositionbalance;
								 double tradeAmt = newOpenPos.getTradeAmt();
								 pos.setOpenQuantity(quantity);
								 newOpenPos.setOpenQuantity(0);
								 newOpenPos.setTradeAmt(0);
								 pos.setTradeAmt(quantity * pos.getProductFV());
								 newopenPositionbalanceexists = false;
								 collectOpenPositionforUpdate.add(pos);
								 collectLiqudiation.add(generateLiqPosition(pos,newOpenPos,newopenPositionbalance,tradeAmt,position));
								 break;
							 } else {
								 double quantity = newopenPositionbalance - balanceQuantity;
								 double tradeAmt = pos.getTradeAmt();
								 pos.setOpenQuantity(0);
								 pos.setTradeAmt(0);
							
								 newOpenPos.setOpenQuantity(quantity);
								 newOpenPos.setTradeAmt(quantity * newOpenPos.getProductFV());
								 newopenPositionbalance = newopenPositionbalance - balanceQuantity;
								 collectOpenPositionforUpdate.add(pos);
								 collectLiqudiation.add(generateLiqPosition(pos,newOpenPos,balanceQuantity,tradeAmt,position));
							 }
						 }
							 
					 } 
					 
				 } else {
					 
				 }
			  }
			  
		  }
		  if(samesign) {
			  commonUTIL.display("PositionManager", " new openPos Step 5  on trade on id " + trade.getId() + " position id  " + position.getPositionId());
			  saveNewOpenPosition(newOpenPos);
			 
		  } else {
			  saveNewOpenPosition(newOpenPos);
			  commonUTIL.display("PositionManager", " update openpos & liquidation position Step  5 on trade on id " + trade.getId() + " position id  " + position.getPositionId());
			  updateOpenPosition(collectOpenPositionforUpdate);
			  saveNewLiqiuation(collectLiqudiation);
		  }
		    
			  regeneratePosition(position);
			  commonUTIL.display("PositionManager", "  update position Step 5  on trade on id " + trade.getId() + " position id  " + position.getPositionId());
		}
	}
    
	private void regeneratePosition(Position position) {
		// TODO Auto-generated method stub
		Vector<Position> pos = new Vector<Position>();
		getUnrelisedQuantityOnPosition(position);  // calculated unrealised quantity on open position on given position
		getRealisedQuantityOnPosition(position); // calculated realised quantity on open position on given position
		getTotalBuysellQuanityAmount(position);// calculated total buy & sell quantity etc. on open position on given position
         pos.add(position);
         updatePosition(pos);
	}


	// we can do total for position of total sell quantity,buy quantity for specific position

	private void getTotalBuysellQuanityAmount(Position position) {
		// TODO Auto-generated method stub
		double sellQuantity = 0;
		double buyQuantity = 0;

		try {
			Vector unrealisedpos = (Vector) remoteMO.getOpenPosition(position.getPositionId());
			for(int i=0;i < unrealisedpos.size();i++) {
				Openpos openpos = (Openpos) unrealisedpos.elementAt(i);
						if(openpos.getSign() == 1) 
								buyQuantity = buyQuantity + openpos.getQuantity();
						if(openpos.getSign() == -1) 
								sellQuantity = sellQuantity + openpos.getQuantity();
				}
		} catch(RemoteException e) {
			commonUTIL.displayError("PositionProcessor", "getTotalBuysellQuanityAmount", e);
		}
		position.setSell_quantity(sellQuantity);
		position.setBuy_quantity(buyQuantity);
	}


	private void getRealisedQuantityOnPosition(Position position) {
		// TODO Auto-generated method stub
		double unRealisedAmt = 0;
		
		try {
			
			
			Vector unrealisedpos = (Vector) remoteMO.getUnRealisedPosition(position.getPositionId());
			if(unrealisedpos != null && unrealisedpos.size() > 0) {
				for(int i=0;i < unrealisedpos.size();i++) {
					Openpos openpos = (Openpos) unrealisedpos.elementAt(i);
					unRealisedAmt = unRealisedAmt + (openpos.getOpenQuantity() * openpos.getSign());
					
					
				}
			}
		} catch(RemoteException e) {
			commonUTIL.displayError("PositionProcessor", "getRealisedQuantityOnPosition", e);
		}
		position.setUnrealized(unRealisedAmt);
		
		
	}


	private void getUnrelisedQuantityOnPosition(Position position) {
		// TODO Auto-generated method stub
try {
			if(position.getLigConfig() != null) {
				LiquidationConfig liqConfig = position.getLigConfig();
				if(liqConfig.getLiqmethod().equalsIgnoreCase("AVERAGE")) {
					
				}
			}
			
	        Liquidation liqRealised = (Liquidation) remoteMO.getRealisedPosition(position.getPositionId());
			position.setRealized(liqRealised.getQuantity());
			position.setRealizedPNL(liqRealised.getRealisedPNL());
			
		} catch(RemoteException e) {
			commonUTIL.displayError("PositionProcessor", "getRealisedQuantityOnPosition", e);
		}
	}


	private void saveNewLiqiuation(Vector<Liquidation> collectLiqudiation) {
		// TODO Auto-generated method stub
		
		if(collectLiqudiation != null & collectLiqudiation.size() > 0) {
			for(int i=0;i < collectLiqudiation.size();i++) {
				
				try {
					commonUTIL.display("PositionProcessor", " step 6  Liquidation for Trade"  + collectLiqudiation.elementAt(i).getPositionId() + " "+ collectLiqudiation.elementAt(i).getTradeType());
					remoteMO.saveLiqPosition(collectLiqudiation.elementAt(i));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("PositionProcessor", "saveNewLiqiuation", e);
				}
			}
		}
	}


	public void updateOpenPosition(Vector<Openpos> openPositions) {
		if(openPositions != null & openPositions.size() > 0) {
			for(int i=0;i < openPositions.size();i++) {
				
				try {
					remoteMO.updateOpenPosition(openPositions.elementAt(i));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("PositionProcessor", "updateOpenPosition", e);
				}
			}
		}
		
	}
	
	
	public void updatePosition(Vector<Position> positions) {
		if(positions != null & positions.size() > 0) {
			for(int i=0;i < positions.size();i++) {
				
				try {
					remoteMO.updatePosition(positions.elementAt(i));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("PositionProcessor", "updatePosition", e);
				}
			}
		}
		
	}
	
	public boolean isOpenquantityExist(Openpos pos) {
		boolean flag = true;
		if(pos.getOpenQuantity() == 0 ||  pos.getOpenQuantity() < 0) {
			flag = false;
		}
		return flag;
			
	}
	
	public double getBalanceOpenQuantity(Openpos pos) {
		double balance = pos.getOpenQuantity();
		
		if(balance > 0 ) 
		  		return balance;
		return 0;
	}

	public Openpos generateOpenPosition(Trade trade,Position position) {
		Openpos op = new Openpos();
		op.setBookId(trade.getBookId());
		op.setOpenpositionDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
		op.setQuantity(trade.getQuantity());
		op.setProductId(trade.getProductId());
		op.setTradeId(trade.getId());
		op.setPrice(trade.getPrice());
		op.setProductType(trade.getProductType());
		if(trade.getProductType().equalsIgnoreCase(productTYPEFX)) {
			op.setProductSubType(trade.getTradedesc());
		} else {
		op.setProductSubType(trade.getTradedesc1());
		}
		op.setSign(checkSign(trade.getType()));
		op.setOpenQuantity(trade.getQuantity());
		op.setPositionId(position.getPositionId());
		op.setSettleDate(trade.getDelivertyDate());
		op.setTradeDate(trade.getTradeDate().substring(0, 10));
		op.setTradeAmt(trade.getTradeAmount());
		op.setOriginalTradeAmt(trade.getTradeAmount());
		Product product = getProduct(trade.getProductId());
		if(product == null) {
			commonUTIL.display("PositionProcessor " , "generateOpenPosition <<<<<<<<<<<<<< not able to get Product against trade id " + trade.getId());
			return null;
		} else {
			op.setProductFV(product.getFaceValue());
		}
		return op;
	}
	
	
	
	
	private Product getProduct(int productID) {
		// TODO Auto-generated method stub
		Product product = null;
		try {
			synchronized (products) {
				product =   products.get(productID);
			}
			
			if(product == null) {
			 product = (Product) remoteProduct.selectProduct(productID);
			 products.put(productID, product);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}


	public int checkSign(String sign) {
		if(sign.equalsIgnoreCase("BUY")) {
			return 1;
		}
		return -1;
	}


    public Position generatePositionOnGivenTrade(Trade trade) {
    	Position pos = new Position();
    
    	
    	pos.setBookId(trade.getBookId());
    	pos.setProductId(trade.getProductId());
    	pos.setProductType(trade.getProductType());
    	pos.setProductsubType(trade.getTradedesc1());
    	if(checkSign(trade.getType()) == 1) {
    	   pos.setBuy_amount( trade.getTradeAmount());
    	   pos.setBuy_quantity(trade.getQuantity());
    	   pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
    	}  else  {
    		pos.setSell_amount(trade.getTradeAmount());
     	   pos.setSell_quantity(trade.getQuantity());
     	  pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
    	}
        pos.setPrimaryCurr(trade.getCurrency());
    	pos.setAvg_price(0);
    	
    
    	pos.setRealized(0);
    	return pos;
    }
    private String getPrimaryCurrency(String currencyPair) {
    	return currencyPair.substring(0, 3);
    }
    private String getQuotingCurrency(String currencyPair) {
    	return currencyPair.substring(4, 7);
    }
    public void processFXPosition(Trade trade,int i,boolean isUpdated) {
    	try {
			LiquidationConfig liqConfig = (LiquidationConfig)remoteRef.getLiquidationConfigOn(trade.getBookId(),trade.getProductType(),trade.getTradedesc1().toUpperCase());
			if(liqConfig == null) {
				
				commonUTIL.display("PositionEngine " , "Missing LiquidationConfig for " + trade.getProductType() + "  " +trade.getTradedesc1() );
				return;
			}
			if(liqConfig.isAvaliableLiquidation()) {
				 Position oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
				 processFXPosition(oldPosition,trade,i,isUpdated,liqConfig,false);
			} else {
				 Position oldPosition = (Position) remoteMO.getPositionOnSettleDateOnCurrencyOnBook(trade.getTradeDate().substring(0, 10), getPrimaryCurrency(trade.getTradedesc()), trade.getBookId());
				 processFXPosition(oldPosition,trade,i,isUpdated,liqConfig,true);
				 oldPosition = (Position) remoteMO.getPositionOnSettleDateOnCurrencyOnBook(trade.getTradeDate().substring(0, 10), getQuotingCurrency(trade.getTradedesc()), trade.getBookId());
				 processFXPosition(oldPosition,trade,i,isUpdated,liqConfig,false);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void processFXPosition(Position oldPosition,Trade trade,int i,boolean isUpdated,LiquidationConfig liqCongfig,boolean isPrimaryCurr) {
   	// Position oldPosition = null;
		  Position newPosition = null;
		 
			  
			try {
				if(!isUpdated) {
				//	oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
						if(oldPosition == null) {
									if(liqCongfig.isAvaliableLiquidation()) {
											newPosition =	remoteMO.savePosition(generateFXPositionOnGivenTrade(trade));
											Openpos op = new Openpos();
											generateFXOpenPosition(trade, newPosition,op);
											saveNewOpenPosition(op);
									}    else {
										newPosition =	remoteMO.savePosition(generatePositionAsperCurrency(trade,isPrimaryCurr));
										Openpos op = new Openpos();
										generateFXCurrencyOpenPosition(trade,newPosition,op,isPrimaryCurr);
										saveNewOpenPosition(op);
									}
						
						} else {
								Openpos openPost = null;
								if(liqCongfig.isAvaliableLiquidation()) {
														updateoldFXPosition(oldPosition,trade,i);
														remoteMO.updatePosition(oldPosition);
														if(trade.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
															 openPost = (Openpos) remoteMO.getOpenPositionOnFxSwapLeg(trade.getId(),trade.getFxSwapLeg());
														} else {
															 openPost = (Openpos) remoteMO.getOpenPositionOnTradeID(trade.getId());
														}
														if(openPost == null || openPost.getTradeId() == 0)  {
															openPost = new Openpos();
															generateFXOpenPosition(trade, oldPosition,openPost);
															saveNewOpenPosition(openPost);
														} else {
															generateFXOpenPosition(trade, oldPosition,openPost);
															remoteMO.updateOpenPosition(openPost);
															
														}
								} else {
									updateoldFXCurrencyPosition(oldPosition,trade,i,isPrimaryCurr);
									remoteMO.updatePosition(oldPosition);
									Openpos op = new Openpos();
									generateFXCurrencyOpenPosition(trade,oldPosition,op,isPrimaryCurr);
									saveNewOpenPosition(op);
									
								}
						
						
						
						
						
							
					}
					
				} else {
					 oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
					updateoldFXPosition(oldPosition,trade,i);
					remoteMO.updatePosition(oldPosition);
					Openpos openPost = null;
					if(trade.getFxSwapLeg() == null || trade.getFxSwapLeg().trim().isEmpty() || trade.getFxSwapLeg().length() ==0 )
						openPost =	(Openpos) remoteMO.getOpenPositionOnTradeID(trade.getId());
					else 
						openPost =	(Openpos) remoteMO.getOpenPositionOnFxSwapLeg(trade.getId(), trade.getFxSwapLeg());
					if(openPost != null) {
					 generateFXOpenPosition(trade, oldPosition,openPost);
					 remoteMO.updateOpenPosition(openPost);
					}
					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("positionProcessor", "processPosition", e);
			}
   }
    
    private Position generatePositionAsperCurrency(Trade trade,boolean isPrimaryCurrency) {
    
    	Position pos = new Position();
		// TODO Auto-generated method stub
    	pos.setBookId(trade.getBookId());
    	pos.setProductId(trade.getProductId());
    	pos.setTradeDate(trade.getTradeDate().substring(0, 9));
    	pos.setSettleDate(trade.getDelivertyDate());
    	 // in this it will be productSubType as position need to be created on currency 
    	//primaryCurrenyPos.setBuy_quantity(trade.getQuantity());                         // -- primary Currency position
    	//primaryCurrenyPos.setSell_quantity(trade.getNominal());                         //  -- quoted Currency position 
    	pos.setPrimaryCurr(trade.getTradedesc1());   // this will be currencyPair
    	pos.setProductType(trade.getProductType());
    //	pos.setAmount(trade.getQuantity());
    	//pos.setNominal(trade.getNominal());
    	pos.setAvg_price(0);
    	if(isPrimaryCurrency) {
    		pos.setProductsubType(getPrimaryCurrency(trade.getTradedesc())); 
    			if(checkSign(trade.getType()) == 1) {
				     	   pos.setBuy_amount( trade.getTradeAmount());
				     	   pos.setBuy_quantity(trade.getQuantity());
				     	   pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
    			}  else  {
			     		pos.setSell_amount(trade.getTradeAmount());
			      	   pos.setSell_quantity(trade.getQuantity());
			      	  pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
    			} 
    	} else {
    		pos.setProductsubType(getQuotingCurrency(trade.getTradedesc())); 
			    		if(checkSign(trade.getType()) == 1) {
			    			pos.setSell_amount(trade.getNominal());
					      	   pos.setSell_quantity(trade.getNominal());
					      	  pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
					     	 
					}  else  {
							pos.setBuy_amount( trade.getNominal());
				     	   pos.setBuy_quantity(trade.getNominal());
				     	   pos.setUnrealized(checkSign(trade.getType()) * trade.getQuantity());
					} 
    	
    	}
    	
		return pos;
	}

    
  /*  public void processFXPosition(Trade trade,int i,boolean isUpdated) {
    	 Position oldPosition = null;
		  Position newPosition = null;
			try {
				if(!isUpdated) {
					oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
					if(oldPosition == null) {
						newPosition =	remoteMO.savePosition(generateFXPositionOnGivenTrade(trade));
						Openpos op = new Openpos();
						generateFXOpenPosition(trade, newPosition,op);
						saveNewOpenPosition(op);
					
					
					} else {
						updateoldFXPosition(oldPosition,trade,i);
						remoteMO.updatePosition(oldPosition);
						Openpos openPost = null;
						if(trade.getTradedesc1().equalsIgnoreCase("FXSWAP")) {
							 openPost = (Openpos) remoteMO.getOpenPositionOnFxSwapLeg(trade.getId(),trade.getFxSwapLeg());
						} else {
							 openPost = (Openpos) remoteMO.getOpenPositionOnTradeID(trade.getId());
						}
						
						
						if(openPost == null || openPost.getTradeId() == 0)  {
							openPost = new Openpos();
							generateFXOpenPosition(trade, oldPosition,openPost);
							saveNewOpenPosition(openPost);
						} else {
							generateFXOpenPosition(trade, oldPosition,openPost);
							remoteMO.updateOpenPosition(openPost);
							
						}
							
					}
					
				} else {
					 oldPosition = (Position) remoteMO.getPositionOnTrade(trade);
					updateoldFXPosition(oldPosition,trade,i);
					remoteMO.updatePosition(oldPosition);
					Openpos openPost = (Openpos) remoteMO.getOpenPositionOnTradeID(trade.getId());
					generateFXOpenPosition(trade, oldPosition,openPost);
					remoteMO.updateOpenPosition(openPost);
					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("positionProcessor", "processPosition", e);
			}
    } */
    public void  processFXPositionForUpdate(Trade trade) {
    	String sql = " version = " + (trade.getVersion() -1);
    	Trade oldTrade = null;
     try {
		  oldTrade = (Trade) remoteTrade.getTradeOnVersion(trade.getId(), trade.getVersion() -1);
		  if(oldTrade.getTradedesc1().equalsIgnoreCase("FXSwap")) {
		  if(trade.getFxSwapLeg().equalsIgnoreCase("SWAPLEG")) {
			  oldTrade = oldTrade.getSwapLeg();
		  } else {
			  oldTrade = oldTrade.getSwapPrimaryLeg();
		  }
		  }
		//  if(trade.)
		if(oldTrade != null) {
			commonUTIL.display("PositionProcessor", "Remove all previous version ");
		processFXPosition(oldTrade,-1,true);  // remove all previous version positions
		commonUTIL.display("PositionProcessor", "add updated one");
		processFXPosition(trade,0,true);  // add updated one. 
		}
	
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		commonUTIL.displayError("PositionProcessor", "processFXPositionForUpdate", e);
	}
    	
    }
    public void  processFXPositionForUpdate(Trade trade,String swapLag) {
    	String sql = " version = " + (trade.getVersion() -1);
    	Trade oldTrade = null;
     try {
		  oldTrade = (Trade) remoteTrade.getTradeOnVersion(trade.getId(), trade.getVersion() -1);
		  Trade swapLeg =   oldTrade.getSwapLeg();
	    	Trade primaryLeg = oldTrade.getSwapPrimaryLeg();	
		if(oldTrade != null) {
			commonUTIL.display("PositionProcessor", "Remove all previous version ");
		processFXPosition(oldTrade,-1,true);  // remove all previous version positions
		commonUTIL.display("PositionProcessor", "add updated one");
		processFXPosition(trade,0,true);  // add updated one. 
		}
	
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		commonUTIL.displayError("PositionProcessor", "processFXPositionForUpdate", e);
	}
    	
    }
    private void updateoldFXPosition(Position position,Trade trade2,int i) {
		// TODO Auto-generated method stub
    	if(i == 0 ) {
    		position.setBuy_quantity(position.getBuy_quantity() + trade2.getQuantity());
    		position.setSell_quantity(position.getSell_quantity() + trade2.getNominal());
    		position.setAmount(position.getAmount()  + trade2.getQuantity());
    		position.setNominal(position.getNominal() + trade2.getNominal());
    	}
    	if(i < 0 ) {
    		if(trade2.getQuantity() < 0 ) {
    	        	position.setBuy_quantity(position.getBuy_quantity() + Math.abs(trade2.getQuantity()));
    	        	position.setAmount(position.getAmount() + Math.abs(trade2.getQuantity()));
    		} else  {
    			position.setBuy_quantity(position.getBuy_quantity() - trade2.getQuantity());
    			position.setAmount(position.getAmount() - trade2.getQuantity());
    		}
    		if(trade2.getNominal() < 0 ) {
	        	position.setSell_quantity(position.getSell_quantity() + Math.abs(trade2.getNominal()));
	        	position.setNominal(position.getNominal() + Math.abs(trade2.getNominal()));
    		} else  {
		    		position.setSell_quantity(position.getSell_quantity() - trade2.getNominal());
		    		position.setNominal(position.getNominal() - trade2.getNominal());
    		}
    	}
    	
		
	}
    private void updateoldFXCurrencyPosition(Position position,Trade trade,int i,boolean isPrimaryCurr) {
		// TODO Auto-generated method stub
    	if(i == 0 ) {
    		if(isPrimaryCurr) {
    		if(checkSign(trade.getType()) == 1)
    		    position.setBuy_quantity(position.getBuy_quantity() + trade.getQuantity());
    		else 
    			  position.setSell_quantity(position.getSell_quantity() + trade.getQuantity());
    		} else {
    			if(checkSign(trade.getType()) == 1)
        		    position.setSell_quantity(position.getSell_quantity() + trade.getNominal());
        		else 
        			  position.setBuy_quantity(position.getBuy_quantity() + trade.getNominal());
    		}
    		//position.setSell_quantity(position.getSell_quantity() + trade2.getNominal());
    		//position.setAmount(position.getAmount()  + trade2.getQuantity());
    		//position.setNominal(position.getNominal() + trade2.getNominal());
    	}
    	/*if(i < 0 ) {
    		if(trade2.getQuantity() < 0 ) {
    	        	position.setBuy_quantity(position.getBuy_quantity() + Math.abs(trade2.getQuantity()));
    	        	position.setAmount(position.getAmount() + Math.abs(trade2.getQuantity()));
    		} else  {
    			position.setBuy_quantity(position.getBuy_quantity() - trade2.getQuantity());
    			position.setAmount(position.getAmount() - trade2.getQuantity());
    		}
    		if(trade2.getNominal() < 0 ) {
	        	position.setSell_quantity(position.getSell_quantity() + Math.abs(trade2.getNominal()));
	        	position.setNominal(position.getNominal() + Math.abs(trade2.getNominal()));
    		} else  {
		    		position.setSell_quantity(position.getSell_quantity() - trade2.getNominal());
		    		position.setNominal(position.getNominal() - trade2.getNominal());
    		}
    	} */
    	
		
	}


	public Position generateFXPositionOnGivenTrade(Trade trade) {
    	Position pos = new Position();
    
    	
    	pos.setBookId(trade.getBookId());
    	pos.setProductId(trade.getProductId());
    	pos.setProductsubType(trade.getTradedesc());
        pos.setBuy_quantity(trade.getQuantity());   // -- primary Currency position
        pos.setSell_quantity(trade.getNominal());  //  -- quoted Currency position 
     	pos.setPrimaryCurr(trade.getCurrency());
     	pos.setProductType(trade.getProductType());
    	pos.setAmount(trade.getQuantity());
        pos.setNominal(trade.getNominal());
    	pos.setAvg_price(0);
    	pos.setTradeDate(trade.getTradeDate().substring(0, 10));
    	pos.setSettleDate(trade.getDelivertyDate());
    
    	pos.setRealized(0);
    	return pos;
    }
    
    
   
    
    
    public Liquidation generateLiqPosition(Openpos oldPos,Openpos newPos,double quantity,double tradeAmt,Position position) {
    	
    	Liquidation liq = new Liquidation();
    	liq.setfPrice(oldPos.getPrice());
    	liq.setfTradeId(oldPos.getTradeId());
    	liq.setsPrice(newPos.getPrice());
    	liq.setsTradeId(newPos.getTradeId());
    	liq.setPositionId(oldPos.getPositionId());
    	liq.setLidDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
    	liq.setProductId(oldPos.getProductId());
    	liq.setBookId(oldPos.getBookId());
    	liq.setQuantity(quantity);
    	liq.setTradeAmount(tradeAmt);
    	liq.setCurrency(position.getPrimaryCurr());
    	if(oldPos.getSign() == 1)
    	 liq.setTradeType("BUY");
    	else 
    		liq.setTradeType("SELL");
    	if(liq.getTradeType().equalsIgnoreCase("BUY")) {
    	double finalRealPNLPrice = liq.getsPrice() - liq.getfPrice();
    	liq.setRealisedPNL(quantity * finalRealPNLPrice);
    }   else {
    	double finalRealPNLPrice =  liq.getfPrice() -liq.getsPrice() ;
    	liq.setRealisedPNL(quantity * finalRealPNLPrice);
    }
    	try {
    		if(isByAverageMethod(position.getLigConfig())) {
    		
    			double buywac = remoteMO.getWACOnPosition(newPos.getPositionId());
    			
				liq.setRealisedPNL((buywac - liq.getsPrice())*newPos.getOriginalTradeAmt()); // this can be originalTradeAmt or newTradeAMT. 
    		}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(liq.getfTradeId() + "  " + liq.getsTradeId());
    	return liq;
    }



     public boolean isByAverageMethod(LiquidationConfig liqConfig) {
    	 boolean flag = false;
    	 if(liqConfig == null)
    		 return flag;
    	 if(liqConfig.getLiqmethod().equalsIgnoreCase("AVERAGE")) {
    		 flag = true;
    	 }
    	 return flag;
     }
    
     public boolean isByLIFOMethod(LiquidationConfig liqConfig) {
    	 boolean flag = false;
    	 if(liqConfig == null)
    		 return flag;
    	 if(liqConfig.getLiqmethod().equalsIgnoreCase("LIFO")) {
    		 flag = true;
    	 }
    	 return flag;
     }
     
     
     
     public boolean isByTradeDateMethod(LiquidationConfig liqConfig) {
    	 boolean flag = false;
    	 if(liqConfig == null)
    		 return flag;
    	 if(liqConfig.getDatetype().equalsIgnoreCase("TRADEDATE")) {
    		 flag = true;
    	 }
    	 return flag;
     }
     
     
	public Trade getTrade() {
		return trade;
	}


    


	public void setTrade(Trade trade) {
		this.trade = trade;
	}


	public RemoteMO getRemoteMO() {
		return remoteMO;
	}


	public void setRemoteMO(RemoteMO remoteMO) {
		this.remoteMO = remoteMO;
	}


	public String getFlagForupdateOrDeletePosition() {
		return flagForupdateOrDeletePosition;
	}


	public void setFlagForupdateOrDeletePosition(
			String flagForupdateOrDeletePosition) {
		this.flagForupdateOrDeletePosition = flagForupdateOrDeletePosition;
	}
	
	
	public synchronized void addEvents(TradeEventProcessor event) {
		counter++;
		events.put(counter, event);
		System.out.println("addEvents "+ counter);
	}
	
	
	
	public synchronized void readEvents() {
		if(counter > 0) 
			counter--;
		System.out.println("readEvents "+counter);
	}


	public void setRemoteReference(RemoteReferenceData refData) {
		// TODO Auto-generated method stub
		this.remoteRef = refData;
	}


	public void setRemoteProduct(RemoteProduct remoteProduct) {
		// TODO Auto-generated method stub
		this.remoteProduct = remoteProduct;
		
	}


	public void processPositiononOnMMTrade(Trade trade2) {
		if(trade2.getVersion() == 1) {
			 generateNewCashPositionMMTrade(trade2);
		} else if(trade2.getVersion() > 2) {
			 generateUpdateCashPositionMMTrade(trade2);
			 
		}
		
	}
	private void generateUpdateCashPositionMMTrade(Trade newpos) {
		// TODO Auto-generated method stub
		if(newpos != null && newpos.getProductType().equalsIgnoreCase("MM")) {
			try {
				remoteMO.updateCashPositionOnMMTrade(newpos);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PositionProcessor", "updateCashPositionOnMMTrade", e);
			}
		}
		
	}
	private void generateNewCashPositionMMTrade(Trade newpos) {
		// TODO Auto-generated method stub
		if(newpos != null && newpos.getProductType().equalsIgnoreCase("MM")) {
			try {
				remoteMO.generateCashPositionOnMMTrade(newpos);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PositionProcessor", "generateNewCashPositionMMTrade", e);
			}
		}
		
	}
}
