package apps.window.tradewindow.util;

import java.rmi.RemoteException;
import java.util.Vector;

import dsServices.RemoteReferenceData;

import util.commonUTIL;

import beans.CurrencySplitConfig;
import beans.Trade;

public class FXSplitUtil {
	
	  
	
	private static final String FXSWAP = "FXSWAP";

	public static Vector<Trade> getRountingData(String currencyPair,String currency,String buysell,int bookID,RemoteReferenceData remoteRef) {
		Vector<Trade> rountingTrade = new Vector<Trade>();
		if(bookID == 0)
			return rountingTrade;
		if(commonUTIL.isEmpty(currencyPair) || commonUTIL.isEmpty(buysell) ) 
		     return rountingTrade;
		try {
			Vector sconfig = remoteRef.getCurrencySplitConfig(bookID, currencyPair);
			if(sconfig != null && !sconfig.isEmpty() ) {
			      CurrencySplitConfig	 splitConfig = (CurrencySplitConfig) sconfig.elementAt(0);
			      Trade tradex1 = new Trade();
			      tradex1.setBookId(splitConfig.getFirstSpotBook());
			      tradex1.setTradedesc(splitConfig.getFirstCurrencySplit());
			       Trade tradex2 = new Trade();
			      tradex2.setBookId(splitConfig.getSecondSpotBook());
			      tradex2.setTradedesc(splitConfig.getSecondCurrencySPlit());
			      if(buysell.equalsIgnoreCase("BUY")) {
			    	  tradex1.setType("SELL");
			    	  tradex1.setTradedesc(getQuotingCurrency(currencyPair)+"/"+currency);
			    	  tradex2.setType("BUY");
			    	  tradex2.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+currency);
			    	  
			    	  
			      } else {
			    	  tradex1.setType("BUY");
			    	  tradex1.setTradedesc(getQuotingCurrency(currencyPair)+"/"+currency);
			    	  tradex2.setType("SELL");
			    	  tradex2.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+currency);
			      }
			      tradex2.setAutoType("XccySplit");
			      tradex1.setAutoType("XccySplit");
			      rountingTrade.add(tradex1);
			      rountingTrade.add(getMirrorTrade(tradex1));
			      rountingTrade.add(tradex2);
			     
			     rountingTrade.add(getMirrorTrade(tradex2));
			      
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rountingTrade;
		
	}
	public static Vector<Trade> getRountingData(Trade trade,RemoteReferenceData remoteRef,double farRate1,double farRate2) {
		if(trade.isFXSwap()) 
			return getRountingDataForFXSWAP(trade,remoteRef,farRate1,farRate2);
		Vector<Trade> rountingTrade = new Vector<Trade>();
		int bookID = trade.getBookId();
		 trade.setAutoType("Original");
		 if(trade.getId()  == 0) {
		 if(trade.getType().equalsIgnoreCase("BUY")) {
			 if(trade.getNominal() > 0)
			 trade.setNominal(trade.getNominal() * -1);
			 if(trade.getQuantity() < 0)
				 trade.setQuantity(trade.getQuantity() * -1);
		 } else {
			 if(trade.getNominal() < 0)
				 trade.setNominal(trade.getNominal() * -1);
				 if(trade.getQuantity() > 0)
					 trade.setQuantity(trade.getQuantity() * -1);
		 }
	}
		rountingTrade.add(trade);
		String currencyPair = trade.getTradedesc();
		String buysell = trade.getType();
		if(bookID == 0)
			return rountingTrade;
		if(commonUTIL.isEmpty(currencyPair) || commonUTIL.isEmpty(buysell) ) 
		     return rountingTrade;
		try {
			Vector sconfig = remoteRef.getCurrencySplitConfig(bookID, currencyPair);
			if(sconfig != null && !sconfig.isEmpty() ) {
			      CurrencySplitConfig	 splitConfig = (CurrencySplitConfig) sconfig.elementAt(0);
			   
			      Trade tradex1 = null;
				
					tradex1 = (Trade) trade.clone();
				
			      tradex1.setBookId(splitConfig.getFirstSpotBook());
			    //  tradex1.setTradedesc(splitConfig.getFirstCurrencySplit());
			       Trade tradex2  = (Trade) trade.clone();
			      tradex2.setBookId(splitConfig.getSecondSpotBook());
			   //   tradex2.setTradedesc(splitConfig.getSecondCurrencySPlit());
			      if(buysell.equalsIgnoreCase("BUY")) {
			    	//  tradex1.setType("SELL");
			    	//  tradex2.setType("BUY");
			    	//  trade.setNominal(trade.getNominal() * -1);
			    	  tradex1.setQuantity(0);
			    	  tradex1.setNominal(0);
			    	  tradex2.setQuantity(0);
			    	  tradex2.setNominal(0);
			    	  tradex1.setType("SELL");
			    	  tradex1.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  tradex2.setType("BUY");
			    	  tradex2.setTradedesc(getQuotingCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  
			    	  
			      } else if (buysell.equalsIgnoreCase("SELL")) {
			    	 // tradex1.setType("BUY");
			    	 // tradex2.setType("SELL");
			    	//  trade.setQuantity(trade.getQuantity() * -1);
			    	  tradex1.setQuantity(0);
			    	  tradex2.setQuantity(0 );
			    	  tradex1.setNominal(0);
			    	  tradex2.setNominal(0);
			    	  tradex1.setType("BUY");
			    	  tradex1.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  tradex2.setType("SELL");
			    	  tradex2.setTradedesc(getQuotingCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  
			    	  
			      }
			      
			      tradex2.setAutoType("XccySplit");
			      tradex1.setAutoType("XccySplit");
			      
			      rountingTrade.add(tradex1);
			      rountingTrade.add(getMirrorTrade(tradex1));
			      rountingTrade.add(tradex2);
			      rountingTrade.add(getMirrorTrade(tradex2));
			      
				
			}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rountingTrade;
		
	}
	private static Vector<Trade> getRountingDataForFXSWAP(Trade trade,
			RemoteReferenceData remoteRef,double farRate1,double farRate2) {
		// TODO Auto-generated method stub
		Vector<Trade> rountingTrade = new Vector<Trade>();
		int bookID = trade.getBookId();
		 trade.setAutoType("Original");
		 if(trade.getType().equalsIgnoreCase("BUY/SELL")) {
			 if(trade.getNominal() > 0)
			 trade.setNominal(trade.getNominal() * -1);
		 } else {
			 if(trade.getQuantity() > 0)
			 trade.setQuantity(trade.getQuantity() * -1);
		 }
		rountingTrade.add(trade);
		String currencyPair = trade.getTradedesc();
		String buysell = trade.getType();
		if(bookID == 0)
			return rountingTrade;
		if(commonUTIL.isEmpty(currencyPair) || commonUTIL.isEmpty(buysell) ) 
		     return rountingTrade;
		try {
			Vector sconfig = remoteRef.getCurrencySplitConfig(bookID, currencyPair);
			if(sconfig != null && !sconfig.isEmpty() ) {
			      CurrencySplitConfig	 splitConfig = (CurrencySplitConfig) sconfig.elementAt(0);
			   
			      Trade tradex1 = null;
				
					tradex1 = (Trade) trade.clone();
				tradex1.setSecondPrice(farRate1);
			      tradex1.setBookId(splitConfig.getFirstSpotBook());
			    //  tradex1.setTradedesc(splitConfig.getFirstCurrencySplit());
			       Trade tradex2  = (Trade) trade.clone();
			      tradex2.setBookId(splitConfig.getSecondSpotBook());
			  	tradex2.setSecondPrice(farRate2);
			   //   tradex2.setTradedesc(splitConfig.getSecondCurrencySPlit());
			      if(buysell.equalsIgnoreCase("BUY/SELL")) {
			    	//  tradex1.setType("SELL");
			    	//  tradex2.setType("BUY");
			    	//  trade.setNominal(trade.getNominal() * -1);
			    	  tradex1.setQuantity(0);
			    	  tradex1.setNominal(0);
			    	  tradex2.setQuantity(0);
			    	  tradex2.setNominal(0);
			    	  tradex1.setType("SELL/BUY");
			    	  tradex1.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  tradex2.setType("BUY/SELL");
			    	  tradex2.setTradedesc(getQuotingCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  
			    	  
			      } else if (buysell.equalsIgnoreCase("SELL/BUY")) {
			    	 // tradex1.setType("BUY");
			    	 // tradex2.setType("SELL");
			    	//  trade.setQuantity(trade.getQuantity() * -1);
			    	  tradex1.setQuantity(0);
			    	  tradex2.setQuantity(0 );
			    	  tradex1.setNominal(0);
			    	  tradex2.setNominal(0);
			    	  tradex1.setType("BUY/SELL");
			    	  tradex1.setTradedesc(getPrimaryCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  tradex2.setType("SELL/BUY");
			    	  tradex2.setTradedesc(getQuotingCurrency(currencyPair)+"/"+splitConfig.getCurrencyToSplit());
			    	  
			    	  
			      }
			      
			      tradex2.setAutoType("XccySplit");
			      tradex1.setAutoType("XccySplit");
			      
			      rountingTrade.add(tradex1);
			      rountingTrade.add(getMirrorTradeONSWAP(tradex1,new Trade(),false));
			      rountingTrade.add(tradex2);
			      rountingTrade.add(getMirrorTradeONSWAP(tradex2,new Trade(),false));
			      
				
			}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rountingTrade;
		
	}
	
	public static Trade getMirrorTrade(Trade trade) {
		Trade mirrorTrade = new Trade();
		
		try {
			if(trade.getId() == 0) {
			   mirrorTrade = (Trade) trade.clone();
			}
			if(trade.getType().equalsIgnoreCase("BUY")) {
				mirrorTrade.setType("SELL");
				mirrorTrade.setQuantity(mirrorTrade.getQuantity() * -1); 
				mirrorTrade.setNominal(mirrorTrade.getNominal() * -1); 
			} else if(trade.getType().equalsIgnoreCase("SELL")) {
				mirrorTrade.setType("BUY");
				mirrorTrade.setQuantity(mirrorTrade.getQuantity() * -1);
				mirrorTrade.setNominal(mirrorTrade.getNominal() * -1);  
			}
			mirrorTrade.setAutoType("Mirror");
			mirrorTrade.setCpID(trade.getBookId());
			mirrorTrade.setMirrorBookid(trade.getBookId());
			mirrorTrade.setBookId(trade.getBookId());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mirrorTrade;
	}
	public static Trade getMirrorTradeONSWAP(Trade trade,Trade mirrorTrad,boolean isupdate) {
		Trade mirrorTrade =mirrorTrad;
		
		try {
			if(trade.getId() == 0 && (!isupdate)) {
			   mirrorTrade = (Trade) trade.clone();
			}
			if(trade.getType().equalsIgnoreCase("BUY/SELL")) {
				mirrorTrade.setType("SELL/BUY");
				mirrorTrade.setQuantity(trade.getQuantity() * -1); //sell
				mirrorTrade.setNominal(trade.getNominal() * -1);  //buy
				mirrorTrade.setTradeAmount(trade.getTradeAmount() * -1); //sell;
				mirrorTrade.setYield(trade.getYield() * -1); //BUY;
			} else if(trade.getType().equalsIgnoreCase("SELL/BUY")) {
				mirrorTrade.setType("BUY/SELL");
				mirrorTrade.setQuantity(trade.getQuantity() * -1); //BUY
				mirrorTrade.setNominal(trade.getNominal() * -1);   //SELL
				mirrorTrade.setTradeAmount(trade.getTradeAmount() * -1); //BUY
				mirrorTrade.setYield(trade.getYield() * -1);
			}
			mirrorTrade.setAutoType("Mirror");
			mirrorTrade.setCpID(trade.getBookId());
			mirrorTrade.setMirrorBookid(trade.getBookId());
			mirrorTrade.setBookId(trade.getBookId());
			mirrorTrade.setCurrency(mirrorTrade.getTradedesc().substring(4, 7));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mirrorTrade;
	}
	public static Trade getMirrorTrade(Trade trade,Trade oldmirrorTrade,boolean isupdate) {
		Trade mirrorTrade=oldmirrorTrade;
		
		try {
			if(trade.getId() == 0) {
			   mirrorTrade = (Trade) trade.clone();
			} 
			
			if(trade.getType().equalsIgnoreCase("BUY")) {
				mirrorTrade.setType("SELL");
				mirrorTrade.setQuantity(trade.getQuantity() * -1); 
				mirrorTrade.setNominal(trade.getNominal() * -1); 
			} else if(trade.getType().equalsIgnoreCase("SELL")) {
				mirrorTrade.setType("BUY");
				mirrorTrade.setQuantity(trade.getQuantity() * -1);
				mirrorTrade.setNominal(trade.getNominal() * -1);  
			}
			mirrorTrade.setAutoType("Mirror");
			mirrorTrade.setCpID(trade.getBookId());
			mirrorTrade.setMirrorBookid(trade.getBookId());
			mirrorTrade.setBookId(trade.getBookId());
			if(isupdate) {
				mirrorTrade.setAction(trade.getAction());
				mirrorTrade.setStatus(trade.getStatus());			   
				
				mirrorTrade.setCurrency(mirrorTrade.getTradedesc().substring(4, 7));
				mirrorTrade.setDelivertyDate(trade.getDelivertyDate());
				 }
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mirrorTrade;
	}
	
	
	
	  public static Trade getSplitOrMirrorTradeFromRountingTrades(int tradeid,Vector<Trade> rountingTrades) {
		    Trade splitTrade = null;
		    for(int i=0;i<rountingTrades.size();i++) {
		    	Trade trade = rountingTrades.get(i);
		    	if(trade.getId() == tradeid)  {
		    		splitTrade = trade;
		    	    break;
		        }
		    	
		    }
		    return splitTrade;
	  }
	
	  public static Trade getOriginalTradeFromRountingTrades(Vector<Trade> rountingTrades) {
		    Trade Original = null;
		    for(int i=0;i<rountingTrades.size();i++) {
		    	Trade trade = rountingTrades.get(i);
		    	if(trade.getAutoType().equalsIgnoreCase("Original"))  {
		    		Original = trade;
		    	    break;
		        }
		    	
		    }
		    return Original;
	  }
	
	  public static  Vector<Trade> splitTrade(Vector<Trade> routingTrade,double firstRate, double secondRate, Trade trade,double firstFarRate,double secondFarRate, boolean isupdated) {
			// TODO Auto-generated method stub
		  Trade originalTrade = null;
		  if(commonUTIL.isEmpty(routingTrade) )
		    	 return null;
		  if(routingTrade.size() == 1) {
			  return routingTrade;
		  }
		  if(trade.getType().equalsIgnoreCase("BUY/SELL")) {
		      if(trade.getQuantity() < 0) 
		    	  trade.setQuantity(trade.getQuantity() * -1);
		      if(trade.getNominal() > 0 )
		    	  trade.setNominal(trade.getNominal() * -1);
		 }
	   if(trade.getType().equalsIgnoreCase("SELL/BUY")) {
		      if(trade.getQuantity() > 0) 
		    	  trade.setQuantity(trade.getQuantity() * -1);
		      if(trade.getNominal() < 0 )
		    	  trade.setNominal(trade.getNominal() * -1);
		 }
		     if(trade.getId() == 0)
		       originalTrade = trade;// getOriginalTradeFromRountingTrades(routingTrade);
		     else 
		    	 originalTrade = trade;// getOriginalTradeFromRountingTrades(routingTrade);
		     
		    int xxy1 = Integer.parseInt(originalTrade.getAttributeValue("FXccySplitID"));
		    Trade xccy1 = getSplitOrMirrorTradeFromRountingTrades(xxy1,routingTrade);
		    int xxy2 = Integer.parseInt(originalTrade.getAttributeValue("SXccySplitID"));
		    Trade xccy2 = getSplitOrMirrorTradeFromRountingTrades(xxy2,routingTrade);
		    if(xccy1.getId() == trade.getId()) 
		    	xccy1 = trade;
		    if(xccy2.getId() == trade.getId()) 
		    	xccy2 = trade;
		    
		   int mirr1 = Integer.parseInt(xccy1.getAttributeValue("MirrorID"));
		   Trade mirror1 = getSplitOrMirrorTradeFromRountingTrades(mirr1,routingTrade);
		   int mirr2 = Integer.parseInt(xccy2.getAttributeValue("MirrorID"));
		   Trade mirror2 = getSplitOrMirrorTradeFromRountingTrades(mirr2,routingTrade);
		   if(mirror1.getId() == trade.getId()) 
			   mirror1 = trade;
		   if(mirror2.getId() == trade.getId()) 
			   mirror2 = trade;
		   return  splitTrade(xccy1,xccy2,mirror1,mirror2,originalTrade,firstRate,secondRate,firstFarRate,secondFarRate,isupdated) ;
			
		}
	  
	  public static Vector<Trade> splitTrade(Vector<Trade> routingTrade,double firstRate, double secondRate,double firstFarRate,double secondFarRate)  {
		     if(commonUTIL.isEmpty(routingTrade) )
		    	 return null;
		     if(routingTrade.size() == 1) {
		    	return routingTrade;
		     }
		    Trade originalTrade =  getOriginalTradeFromRountingTrades(routingTrade);
		    int xxy1 = Integer.parseInt(originalTrade.getAttributeValue("FXccySplitID"));
		    Trade xccy1 = getSplitOrMirrorTradeFromRountingTrades(xxy1,routingTrade);
		    int xxy2 = Integer.parseInt(originalTrade.getAttributeValue("SXccySplitID"));
		    Trade xccy2 = getSplitOrMirrorTradeFromRountingTrades(xxy2,routingTrade);
		   int mirr1 = Integer.parseInt(xccy1.getAttributeValue("MirrorID"));
		   Trade mirror1 = getSplitOrMirrorTradeFromRountingTrades(mirr1,routingTrade);
		   int mirr2 = Integer.parseInt(xccy2.getAttributeValue("MirrorID"));
		   Trade mirror2 = getSplitOrMirrorTradeFromRountingTrades(mirr2,routingTrade);
		   return  splitTrade(xccy1,xccy2,mirror1,mirror2,originalTrade,firstRate,secondRate,firstFarRate,secondFarRate,false) ;
		   
		     
	  }
	 
	   private static   Vector<Trade> splitTrade(Trade splitTrade1, Trade splitTrade2, Trade mirror1,
			Trade mirror2, Trade orginalTrade, double firstRate, double secondRate,double firstFarRate,double secondFarRate, boolean isupdate) {
		// TODO Auto-generated method stub
		   if(orginalTrade.isFXSwap()) 
			   return splitFXSwapTrade(splitTrade1,splitTrade2,mirror1,mirror2,orginalTrade,firstRate,secondRate,firstFarRate,secondFarRate,true);
		   Vector<Trade> splitsTrade = new Vector<Trade>();
		   splitTrade1.setCpID(orginalTrade.getCpID());
			 //	xccY1.setTradedesc(sconfig.getSecondCurrencySPlit());
		 	//	xccY2.setBookId(sconfig.getSecondSpotBook());
		   splitTrade2.setCpID(orginalTrade.getCpID());
			 //	xccY2.setTradedesc(sconfig.getSecondCurrencySPlit());
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
			 	splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   String primaryCurrecy = getPrimaryCurrency(orginalTrade.getTradedesc());
		   String quotingCurrecy = getQuotingCurrency(orginalTrade.getTradedesc());
		   splitTrade1.setPrice(firstRate);
		   splitTrade2.setPrice(secondRate);
		   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
		 //  splitTrade1.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		  // splitTrade2.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		   splitTrade1.setAutoType("XccySplit");
		   splitTrade2.setAutoType("XccySplit");
		   
		   splitTrade1.setXccySPlitid(orginalTrade.getId());
		   splitTrade2.setXccySPlitid(orginalTrade.getId());
		   double splitCurrencyValue = 0.0;
		   if(!isPrimaryCurrency(splitTrade1.getTradedesc(),primaryCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
				   splitTrade1.setType("SELL");
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate);    // split Curr will be positive quantity 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() * -1);           // primary curre will be negitave 
				 //  splitTrade1.setType("BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
				   splitTrade1.setType("BUY");
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate );  // 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() *-1 );          // primary currency will be positive as trade type is sell 
				 //  splitTrade1.setType("SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
			   	 splitTrade1.setType("SELL");
			   	 splitTrade1.setQuantity(orginalTrade.getQuantity() *-1);
			  
				 splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1 );
				 	splitCurrencyValue = splitTrade1.getNominal();
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL")){
			   		splitTrade1.setQuantity(orginalTrade.getQuantity() *  -1);  
			   	
					   splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1);
					   splitCurrencyValue = splitTrade1.getNominal();
					   splitTrade1.setType("BUY");
				   
			   			}
			   
		   }
		   
		   if(!isPrimaryCurrency(splitTrade2.getTradedesc(),quotingCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
				   splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				   splitTrade2.setNominal(splitTrade2.getNominal() * secondRate * -1);
				   splitTrade2.setType("SELL");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
				   splitTrade2.setQuantity(orginalTrade.getNominal() );
				   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate  );
				   splitTrade2.setType("BUY");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
			   	 splitTrade2.setType("BUY");
			   	 splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				 splitTrade2.setNominal((splitTrade2.getQuantity() * -1) * secondRate);
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
			   		splitTrade2.setQuantity(splitCurrencyValue / secondRate);
					   splitTrade2.setNominal(splitCurrencyValue * -1);
					  splitTrade2.setType("SELL");
				   
			   			}
			   
		   }
		   if(isupdate) {
			   splitTrade1.setAction(orginalTrade.getAction());
			   splitTrade2.setAction(orginalTrade.getAction());
			   
			   splitTrade1.setStatus(orginalTrade.getStatus());
			   splitTrade2.setStatus(orginalTrade.getStatus());
			   
			   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
			   splitTrade2.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
			   splitTrade2.setDelivertyDate(orginalTrade.getDelivertyDate());
			   splitTrade1.setDelivertyDate(orginalTrade.getDelivertyDate());
			   
		   }
		   splitsTrade.add(0,orginalTrade);
		   splitsTrade.add(1,splitTrade1);
		   splitsTrade.add(2,getMirrorTrade(splitTrade1,mirror1,isupdate));
		   splitsTrade.add(3,splitTrade2);
		   splitsTrade.add(4,getMirrorTrade(splitTrade2,mirror2,isupdate));
		  
		  
		   
		   return  splitsTrade;
		
	}
	private static Vector<Trade> splitFXSwapTrade(Trade splitTrade1,
			Trade splitTrade2, Trade mirror1, Trade mirror2,
			Trade orginalTrade, double firstRate, double secondRate,double firstFarRate,double secondFarRate, boolean isupdate) {
		// TODO Auto-generated method stub
		Vector<Trade> splitsTrade = new Vector<Trade>();
		   splitTrade1.setCpID(orginalTrade.getBookId());
		   splitTrade1.setMirrorBookid(orginalTrade.getBookId());
			 //	xccY1.setTradedesc(sconfig.getSecondCurrencySPlit());
		 	//	xccY2.setBookId(sconfig.getSecondSpotBook());
		   splitTrade2.setCpID(orginalTrade.getBookId());
		   splitTrade1.setMirrorBookid(orginalTrade.getBookId());
		   splitTrade2.setMirrorBookid(orginalTrade.getBookId());
			 //	xccY2.setTradedesc(sconfig.getSecondCurrencySPlit());
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
			 	splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   String primaryCurrecy = getPrimaryCurrency(orginalTrade.getTradedesc());
		   String quotingCurrecy = getQuotingCurrency(orginalTrade.getTradedesc());
		   splitTrade1.setPrice(firstRate);
		   splitTrade2.setPrice(secondRate);
		   splitTrade1.setSecondPrice(firstFarRate);
		   splitTrade2.setSecondPrice(secondFarRate);
		   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
		 //  splitTrade1.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		  // splitTrade2.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		   splitTrade1.setAutoType("XccySplit");
		   splitTrade2.setAutoType("XccySplit");
		   splitTrade1.setXccySPlitid(orginalTrade.getId());
		   splitTrade2.setXccySPlitid(orginalTrade.getId());
		   double splitCurrencyValue = 0.0;
		   if(!isPrimaryCurrency(splitTrade1.getTradedesc(),primaryCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
				   splitTrade1.setType("SELL/BUY");
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate);    // split Curr will be positive quantity 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() * -1);           // primary curre will be negitave 
				 //  splitTrade1.setType("BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
				   splitTrade1.setType("BUY/SELL");
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate );  // 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() *-1 );          // primary currency will be positive as trade type is sell 
				 //  splitTrade1.setType("SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
			   	 splitTrade1.setType("SELL/BUY");
			   	 splitTrade1.setQuantity(orginalTrade.getQuantity() *-1);
			  
				 splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1 );
				 splitTrade1.setTradeAmount(orginalTrade.getTradeAmount() * -1); //BUY
				 splitTrade1.setYield(splitTrade1.getTradeAmount() * -1 * firstFarRate); // sell
				 	splitCurrencyValue = splitTrade1.getNominal(); 
				
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")){
			   		splitTrade1.setQuantity(orginalTrade.getQuantity() *  -1);  
			   	
					   splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1);
					   splitTrade1.setTradeAmount(splitTrade1.getQuantity() *-1); //sell
					   splitTrade1.setYield(splitTrade1.getTradeAmount() *-1 * firstFarRate); //buy
					   splitCurrencyValue = splitTrade1.getNominal();
					   splitTrade1.setType("BUY/SELL");
				   
			   			}
			   
		   }
		   
		   if(!isPrimaryCurrency(splitTrade2.getTradedesc(),quotingCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
				   splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				   splitTrade2.setNominal(splitTrade2.getNominal() * secondRate * -1);
				   splitTrade2.setType("SELL/BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
				   splitTrade2.setQuantity(orginalTrade.getNominal() );
				   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate  );
				   splitTrade2.setType("BUY/SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
			   	 splitTrade2.setType("BUY/SELL");
			   	 splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				 splitTrade2.setNominal((splitTrade2.getQuantity() * -1) * secondRate);
				 splitTrade2.setTradeAmount(orginalTrade.getYield() * -1 ); //SELL
				 splitTrade2.setYield(splitTrade2.getTradeAmount() * -1 * secondFarRate); //SELL
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
			   		splitTrade2.setQuantity(orginalTrade.getNominal() * -1); //sell 
					   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate *-1); // buy
					   splitTrade2.setTradeAmount(orginalTrade.getYield() * -1); // buy
					   splitTrade2.setYield(splitTrade2.getTradeAmount() * secondFarRate * -1); // sell
					  splitTrade2.setType("SELL/BUY");
				   
			   			}
			   
		   }
		   if(isupdate) {
			   splitTrade1.setAction(orginalTrade.getAction());
			   splitTrade2.setAction(orginalTrade.getAction());
			   mirror1.setAction(orginalTrade.getAction());
			   mirror2.setAction(orginalTrade.getAction());
			   splitTrade1.setStatus(orginalTrade.getStatus());
			   splitTrade2.setStatus(orginalTrade.getStatus());
			   mirror1.setStatus(orginalTrade.getStatus());
			   mirror2.setStatus(orginalTrade.getStatus());
			   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
			   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
			   splitTrade2.setDelivertyDate(orginalTrade.getDelivertyDate());
			   splitTrade1.setDelivertyDate(orginalTrade.getDelivertyDate());
			   
		   }
		   splitsTrade.add(0,orginalTrade);
		   splitsTrade.add(1,splitTrade1);
		   splitsTrade.add(2,getMirrorTradeONSWAP(splitTrade1,mirror1,isupdate));
		   splitsTrade.add(3,splitTrade2);
		   splitsTrade.add(4,getMirrorTradeONSWAP(splitTrade2,mirror2,isupdate));
		  
		  
		   
		   return  splitsTrade;
	}
	public static Vector<Trade> splitTrade(Trade splitTrade1,Trade splitTrade2,Trade orginalTrade,double firstRate,double secondRate,double firstFarRate,double secondFarRate)  {
		
		if(orginalTrade.isFXSwap())
		     return splitFXSWAPTrade(splitTrade1,splitTrade2,orginalTrade,firstRate,secondRate,firstFarRate,secondFarRate);
		   Vector<Trade> splitsTrade = new Vector<Trade>();
		   orginalTrade.setAutoType("Original");
		   if(orginalTrade.getId() == 0 ) {
			   
		   if(orginalTrade.getType().equalsIgnoreCase("BUY")) {
			      if(orginalTrade.getQuantity() < 0) 
			    	  orginalTrade.setQuantity(orginalTrade.getQuantity() * -1);
			      if(orginalTrade.getNominal() > 0 )
			    	  orginalTrade.setNominal(orginalTrade.getNominal() * -1);
			 }
		   if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
			      if(orginalTrade.getQuantity() > 0) 
			    	  orginalTrade.setQuantity(orginalTrade.getQuantity() * -1);
			      if(orginalTrade.getNominal() < 0 )
			    	  orginalTrade.setNominal(orginalTrade.getNominal() * -1);
			 }
	}
		   Trade mirrorTrade1 = null;
		   Trade mirrorTrade2 = null;
		   if(splitTrade1 == null) {
			   splitsTrade.add(orginalTrade);
			   return splitsTrade;
			   
		   }
		   if(orginalTrade.getId() == 0) {
			   
		       splitTrade1.setId(0);
		       splitTrade2.setId(0);
			  splitTrade1.setAction("NEW");
			splitTrade2.setAction("NEW");
		 	splitTrade1.setStatus("NONE");
		 	splitTrade2.setStatus("NONE");
	   }  
		  
		  
		   splitTrade1.setCpID(orginalTrade.getBookId());
			 //	xccY1.setTradedesc(sconfig.getSecondCurrencySPlit());
		 	//	xccY2.setBookId(sconfig.getSecondSpotBook());
		   splitTrade2.setCpID(orginalTrade.getBookId());
			 //	xccY2.setTradedesc(sconfig.getSecondCurrencySPlit());
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
			 	splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   String primaryCurrecy = getPrimaryCurrency(orginalTrade.getTradedesc());
		   String quotingCurrecy = getQuotingCurrency(orginalTrade.getTradedesc());
		   splitTrade1.setPrice(firstRate);
		   splitTrade2.setPrice(secondRate);
		   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
		 //  splitTrade1.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		//   splitTrade2.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		   splitTrade1.setAutoType("XccySplit");
		   splitTrade2.setAutoType("XccySplit");
		   splitTrade1.setXccySPlitid(orginalTrade.getId());
		   splitTrade2.setXccySPlitid(orginalTrade.getId());
		   double splitCurrencyValue = 0.0;
		   if(!isPrimaryCurrency(splitTrade1.getTradedesc(),primaryCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate);    // split Curr will be positive quantity 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() * -1);           // primary curre will be negitave 
				   splitTrade1.setType("BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate );  // 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() *-1 );          // primary currency will be positive as trade type is sell 
				  splitTrade1.setType("SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
			   	 splitTrade1.setType("SELL");
			   	 splitTrade1.setQuantity(orginalTrade.getQuantity() *-1);
			  
				 splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1 );
				 	splitCurrencyValue = splitTrade1.getNominal();
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
			   		splitTrade1.setQuantity(orginalTrade.getQuantity() *  -1); //buy 
			   	
					   splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1);
					   splitCurrencyValue = orginalTrade.getNominal();
					   splitTrade1.setType("BUY");
				   
			   			}
			   
		   }
		   
		   if(!isPrimaryCurrency(splitTrade2.getTradedesc(),quotingCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
				   splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				   splitTrade2.setNominal(splitTrade2.getNominal() * secondRate * -1);
				  splitTrade2.setType("SELL");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
				   splitTrade2.setQuantity(orginalTrade.getNominal() );
				   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate  );
				  splitTrade2.setType("BUY");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY")) { 
			   	 splitTrade2.setType("BUY");
			   	 splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				 splitTrade2.setNominal((splitTrade2.getQuantity() * -1) * secondRate);
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL")) {
			   		if(secondRate > 0  )
			   		splitTrade2.setQuantity(splitCurrencyValue / secondRate);
			   		splitTrade2.setQuantity(splitCurrencyValue * -1);
					   splitTrade2.setNominal(splitTrade2.getQuantity() * -1 * secondRate);
					  splitTrade2.setType("SELL");
				   
			   			}
			   
		   }
		   splitsTrade.add(0,orginalTrade);
		   splitsTrade.add(1,splitTrade1);
		   splitsTrade.add(2,getMirrorTrade(splitTrade1));
		   splitsTrade.add(3,splitTrade2);
		   if(splitTrade2.getId() ==0) {
		   splitsTrade.add(4,getMirrorTrade(splitTrade2));
		   } else {
			   splitsTrade.add(4,getMirrorTrade(splitTrade2));
		   }
		   return  splitsTrade;
		 
		   
	   }
	   
	   
	   private static Vector<Trade> splitFXSWAPTrade(Trade splitTrade1,
			Trade splitTrade2, Trade orginalTrade, double firstRate,
			double secondRate,double firstFarRate,double secondFarRate) {
		// TODO Auto-generated method stub
		   Vector<Trade> splitsTrade = new Vector<Trade>();
		   orginalTrade.setAutoType("Original");
		   Trade mirrorTrade1 = null;
		   Trade mirrorTrade2 = null;
		   if(splitTrade1 == null) {
			   splitsTrade.add(orginalTrade);
			   return splitsTrade;
			   
		   }
		   if(orginalTrade.getId() == 0) {
			   
		       splitTrade1.setId(0);
		       splitTrade2.setId(0);
			  splitTrade1.setAction("NEW");
			splitTrade2.setAction("NEW");
		 	splitTrade1.setStatus("NONE");
		 	splitTrade2.setStatus("NONE");
		 	splitTrade1.setSecondPrice(firstFarRate);
			splitTrade2.setSecondPrice(secondFarRate);
		 	if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
		 		if(orginalTrade.getQuantity() > 0) {
		 		   orginalTrade.setQuantity(orginalTrade.getQuantity() * -1);
		 		   orginalTrade.setNominal(orginalTrade.getQuantity() * orginalTrade.getPrice());
		 		   orginalTrade.setTradeAmount(orginalTrade.getQuantity() * -1);
		 		  orginalTrade.setYield(orginalTrade.getTradeAmount() * orginalTrade.getSecondPrice());
		 		} 
		 		if(orginalTrade.getQuantity() <  0) {
		 			 orginalTrade.setQuantity(orginalTrade.getQuantity());
			 		   orginalTrade.setNominal(orginalTrade.getQuantity() * orginalTrade.getPrice() * -1);
			 		   orginalTrade.setTradeAmount(orginalTrade.getQuantity() * -1);
			 		  orginalTrade.setYield(orginalTrade.getTradeAmount() * orginalTrade.getSecondPrice() * -1);
		 		  } 
		 		   
		 } else {
			 if(orginalTrade.getQuantity() > 0) {
				 orginalTrade.setQuantity(orginalTrade.getQuantity());
		 		   orginalTrade.setNominal(orginalTrade.getQuantity() * orginalTrade.getPrice() * -1);
		 		   orginalTrade.setTradeAmount(orginalTrade.getQuantity() * -1);
		 		  orginalTrade.setYield(orginalTrade.getTradeAmount() * orginalTrade.getSecondPrice() * -1);
		 		   
		 		} 
		 		if(orginalTrade.getQuantity() <  0) {
		 			orginalTrade.setQuantity(orginalTrade.getQuantity() * -1);
			 		   orginalTrade.setNominal(orginalTrade.getQuantity() * orginalTrade.getPrice());
			 		   orginalTrade.setTradeAmount(orginalTrade.getQuantity() * -1);
			 		  orginalTrade.setYield(orginalTrade.getTradeAmount() * orginalTrade.getSecondPrice());
		 		  } 
		 }
		 	
	   }  
		  
		  
		   splitTrade1.setCpID(orginalTrade.getBookId());
			 //	xccY1.setTradedesc(sconfig.getSecondCurrencySPlit());
		 	//	xccY2.setBookId(sconfig.getSecondSpotBook());
		   splitTrade2.setCpID(orginalTrade.getBookId());
		   splitTrade1.setMirrorBookid(orginalTrade.getBookId());
		   splitTrade2.setMirrorBookid(orginalTrade.getBookId());
			 //	xccY2.setTradedesc(sconfig.getSecondCurrencySPlit());
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
			 	splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   String primaryCurrecy = getPrimaryCurrency(orginalTrade.getTradedesc());
		   String quotingCurrecy = getQuotingCurrency(orginalTrade.getTradedesc());
		   splitTrade1.setPrice(firstRate);
		   splitTrade2.setPrice(secondRate);
		   splitTrade1.setSecondPrice(firstFarRate);
		   splitTrade2.setSecondPrice(secondFarRate);
		   splitTrade1.setCurrency(splitTrade1.getTradedesc().substring(4, 7));
		   splitTrade2.setCurrency(splitTrade2.getTradedesc().substring(4, 7));
		 //  splitTrade1.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		//   splitTrade2.setAttribute("XccySplitFrom",  Integer.valueOf(orginalTrade.getId()).toString());
		   splitTrade1.setAutoType("XccySplit");
		   splitTrade2.setAutoType("XccySplit");
		   splitTrade1.setXccySPlitid(orginalTrade.getId());
		   splitTrade2.setXccySPlitid(orginalTrade.getId());
		   double splitCurrencyValue = 0.0;
		   if(!isPrimaryCurrency(splitTrade1.getTradedesc(),primaryCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate);    // split Curr will be positive quantity 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() * -1);           // primary curre will be negitave 
				   
				   splitTrade1.setType("SELL/BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
				   splitTrade1.setQuantity(orginalTrade.getQuantity() / firstRate );  // 
				   splitCurrencyValue = splitTrade1.getQuantity();
				   splitTrade1.setNominal(orginalTrade.getQuantity() *-1 );          // primary currency will be positive as trade type is sell 
				  splitTrade1.setType("BUY/SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
			   	 splitTrade1.setType("SELL/BUY");
			   	 splitTrade1.setQuantity(orginalTrade.getQuantity() *-1);  //SELL 
			  
				 splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1 ); //BUY 
				 splitTrade1.setTradeAmount(orginalTrade.getTradeAmount() * -1); //BUY
				 splitTrade1.setYield(splitTrade1.getTradeAmount() * -1 * firstFarRate); // sell
				 	splitCurrencyValue = splitTrade1.getNominal(); 
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
			   		splitTrade1.setQuantity(orginalTrade.getQuantity() *  -1);  // buy
			   	
					   splitTrade1.setNominal((splitTrade1.getQuantity() * firstRate) * -1); //sell
					   splitCurrencyValue = splitTrade1.getNominal();
					   splitTrade1.setTradeAmount(splitTrade1.getQuantity() *-1); //sell
					   splitTrade1.setYield(splitTrade1.getTradeAmount() *-1 * firstFarRate); //buy
					   splitTrade1.setType("BUY/SELL");
				   
			   			}
			   
		   }
		   
		   if(!isPrimaryCurrency(splitTrade2.getTradedesc(),quotingCurrecy)) {
			   if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
				   splitTrade2.setQuantity(orginalTrade.getNominal() * -1);
				   splitTrade2.setNominal(splitTrade2.getNominal() * secondRate * -1);
				  splitTrade2.setType("SELL/BUY");
				   
			   } else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
				   splitTrade2.setQuantity(orginalTrade.getNominal() );
				   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate  );
				  splitTrade2.setType("BUY/SELL");
			   }
		   }  else {
			   	if(orginalTrade.getType().equalsIgnoreCase("BUY/SELL")) { 
			   	 splitTrade2.setType("BUY/SELL");
			   //	 splitTrade2.setType("");
			   	 splitTrade2.setQuantity(orginalTrade.getNominal() * -1); // BUY 
				 splitTrade2.setNominal((splitTrade2.getQuantity() * -1) * secondRate); // SELL 
				 splitTrade2.setTradeAmount(orginalTrade.getYield() * -1 ); //SELL
				 splitTrade2.setYield(splitTrade2.getTradeAmount() * -1 * secondFarRate); //SELL
			   	
			   	} else if(orginalTrade.getType().equalsIgnoreCase("SELL/BUY")) {
			   		splitTrade2.setQuantity(orginalTrade.getNominal() * -1); //sell 
					   splitTrade2.setNominal(splitTrade2.getQuantity() * secondRate *-1); // buy
					   splitTrade2.setTradeAmount(orginalTrade.getYield() * -1); // buy
					   splitTrade2.setYield(splitTrade2.getTradeAmount() * secondFarRate * -1); // sell
					  splitTrade2.setType("SELL/BUY");
				   
			   			}
			   
		   }
		   splitsTrade.add(0,orginalTrade);
		   splitsTrade.add(1,splitTrade1);
		   splitsTrade.add(2,getMirrorTradeONSWAP(splitTrade1,new Trade(),false));
		   splitsTrade.add(3,splitTrade2);
		   if(splitTrade2.getId() ==0) {
		   splitsTrade.add(4,getMirrorTradeONSWAP(splitTrade2,new Trade(),false));
		   } else {
			   splitsTrade.add(4,getMirrorTradeONSWAP(splitTrade2,new Trade(),false));
		   }
		   return  splitsTrade;
	}
	public static boolean  isPrimaryCurrency(String currencyPair,String currency) {
		   if(currencyPair.substring(0, 3).equalsIgnoreCase(currency))
				   return true;
		   else 
			   return false;
		   
	   }
	   
	   public static String getPrimaryCurrency(String currencyPair) {
		   return currencyPair.substring(0, 3);
	   }
	   
	   public static String getQuotingCurrency(String currencyPair) {
		   return currencyPair.substring(4, 7);
	   }
	
}

