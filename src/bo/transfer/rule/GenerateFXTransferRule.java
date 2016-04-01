package bo.transfer.rule;

import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.plaf.synth.SynthDesktopIconUI;

import logAppender.TransferServiceAppender;

import constants.SDIConstants;

import productPricing.MMPricing;
import util.commonUTIL;
import util.common.DateU;
import beans.Book;
import beans.Coupon;
import beans.Fees;
import beans.LegalEntity;
import beans.Product;
import beans.Sdi;
import beans.Trade;
import beans.TransferRule;
import bo.util.SDISelectorUtil;
import dsServices.RemoteReferenceData;

public class GenerateFXTransferRule extends ProductTransferRule {
	 static private final String productType = "FX";
	 public Sdi agentSdi = null;
	 public Vector<Sdi> getSdi() {
			return sdi;
		}
	 
	 
	 
	 public void setSdi(Vector<Sdi> sdi) {
			this.sdi = sdi;
		}



		Vector<Sdi>  sdi = new Vector();
		 
		
	     
		 public Sdi getSdiOnEntity(int leid) {
			 Sdi sd = null;
			 if((sdi != null) && (!sdi.isEmpty())) {
				 for(int i=0;i<sdi.size();i++) {
					 Sdi s = (Sdi) sdi.elementAt(i);
					 if(s.getCpId() == leid) {
						 sd =s;
					     break;
					 }
				 }
				 
				 
				 
			 }
			 return sd;
		 }
		 
		
	 
		 public Sdi getAgentSdi() {
				
				return agentSdi;
			}
	@Override
	public String getProductType() {
		// TODO Auto-generated method stub
		 return productType;
	}

	@Override
	public Vector<TransferRule> getTransferRules(Vector v1) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Vector<TransferRule> generateRules(Trade trade,Vector<String> message) {
		// TODO Auto-generated method stub
		TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting Called   ****** on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType());
		
		 Vector<Fees> fees = null;
		 Vector<Sdi> sdis = null;
		if(trade.isFXSwap()) {
			TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting Called   ****** on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() +" FXSWap");
			
			return generateRulesForFXSwap(trade,message);
		} else {
			//try {
			//	sdis =  (Vector)	remoteTrade.getSDisOnTrade(trade);
			 fees =  null;//(Vector)	remoteTrade.selectFeesonTrade(trade.getId());
			// if(sdis == null)
			//	 return null;
			// sdis =  setAgentID(sdis);
		//	setSdi(sdis);
		///	agentSdi = getAgentSdis();
			
		//	} catch (RemoteException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
		//	}
			 Vector<TransferRule>  mmRules = new Vector<TransferRule>();
			 LegalEntity po = null;
			 if(trade.getType().equalsIgnoreCase(tradeTypeBUY)) {
				 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule  ****** on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() + " ");
				 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule processing rules on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() + " "+tradeTypeBUY);
				 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule Starting for First Leg on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() + " "+tradeTypeBUY);
							
				 TransferRule ruleB = new TransferRule();
				 ruleB.set_tradeId(trade.getId());
				 ruleB.set_productId(trade.getProductId());
				 ruleB.set_productType(trade.getProductType());
				 ruleB.set_settleDate( trade.getDelivertyDate());
				 ruleB.set_settlementCurrency(trade.getCurrency());
				 ruleB.set_transferCurrency(trade.getCurrency());
				 ruleB.set_transferType(transerTYPEPRINCIPAL);
			     
				 Book book = (Book) getBook(trade.getBookId());
				 po = (LegalEntity) getLegalEntity(book.getLe_id());
					 if(trade.isMirrorTrade()) {
				    
				    	 ruleB.set__tradeCptyId(po.getId());
				     } else {
				    	 ruleB.set__tradeCptyId(trade.getCpID());
				     }
					 
				 ruleB.set_payerLegalEntityId(book.getLe_id()); // po role 
				 Sdi paySdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
				 
				 
				 if (paySdi != null) {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for PaySdi on " 
							 +   trade.getId() + " on "+ trade.getCurrency()+ " for PO " + po.getName() 
							 + " sdi id == "+paySdi.getId());
					
				 } else {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule No SDI for PaySdi on " 
						+  trade.getId() + " on "+ trade.getCurrency()+ " for PO " + po.getName());	 
					 return null;
				 }				 
					
				
					if(paySdi != null) {
						 setPOSdi(paySdi);
					ruleB.set_payerLegalEntityRole(paySdi.getRole());
					//ruleB.set__sMethod(paySdi);
					ruleB.set_payerSDId(paySdi.getId());
					ruleB.set_payerAgentID(paySdi.getAgentId());
					ruleB.setPayerMethodType(paySdi.getMessageType());
					}
					 if(trade.isMirrorTrade()) {
						    
				    	 ruleB.set__tradeCptyId(po.getId());
				    	 ruleB.set_receiverLegalEntityId(po.getId());
				     } else {
				    	 ruleB.set__tradeCptyId(trade.getCpID());
				    	 ruleB.set_receiverLegalEntityId(trade.getCpID());
				     }
					
				 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
				
				 Sdi recSdi = getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0); 
				 if (recSdi != null) {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for RecSdi on " 
							 +   trade.getId() + " on "+ trade.getCurrency()+ " for PO " + po.getName() 
							 + " sdi id == "+ recSdi.getId());
				 } else {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule No SDI for RecSdi on " 
						+   trade.getId() + " on "+ trade.getCurrency()+ " for PO " + po.getName());	
					 
				 }	
					
				
			     if(trade.isMirrorTrade()) {
			    	 recSdi = paySdi;
			     }
			     if(recSdi != null) {
			    	 setCounterPartySDI(recSdi);
			    	
					 ruleB.set_receiverLegalEntityRole(SDIConstants.COUNTERPARY); // cp role
					 ruleB.set_receiverSDId(recSdi.getId()); // cp role
					 ruleB.set_receiverAgentID(recSdi.getAgentId());

					 ruleB.setReceiverMethodType(recSdi.getMessageType());
					// ruleB.set_manualSDId(_manualSDId)
			     }
			     if(ruleB.getReceiverMethodType().equalsIgnoreCase(ruleB.getPayerMethodType())) {
			    	 ruleB.set_settlementMethod(ruleB.getPayerMethodType());
			     }
				 ruleB.set_payReceive(PAY);
				 mmRules.addElement(ruleB);
				 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule Ending for First Leg on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() + " "+tradeTypeBUY);
				 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule Starting for Second Leg on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType() + " "+tradeTypeBUY);
						
				 TransferRule ruleS = new TransferRule();
				 ruleS.setBookId(trade.getBookId());
				 ruleS.set_tradeId(trade.getId());
				 ruleS.set_productId(trade.getProductId());
				 ruleS.set_productType(trade.getProductType());
				
				 ruleS.set__tradeCptyId(trade.getCpID());
				 if(trade.isMirrorTrade()) {					    
					 ruleS.set__tradeCptyId(po.getId());
			     } else {
			    	 ruleS.set__tradeCptyId(trade.getCpID());
			     }
				 ruleS.set_settleDate( trade.getDelivertyDate());
						 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferType(transerTYPEPRINCIPAL);
				 ruleS.set_payReceive(RECEIVE);
				 
				 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());  // po 
				 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole()); // po 
				 recSdi = getSdi(ruleB.get_payerLegalEntityRole(),ruleB.get_payerLegalEntityId(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0); // po 
					
				 if(recSdi != null) {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for recSdi on " +   trade.getId() + " on "+trade.getTradedesc().substring(0, 3) + " for PO " + le.getName() + " sdi id == "+recSdi.getId());
						
					 ruleS.set__sMethod(recSdi);
				  
					 ruleS.set_receiverSDId(recSdi.getId());
					 ruleS.set_receiverAgentID(recSdi.getAgentId());

					 ruleS.setReceiverMethodType(recSdi.getMessageType());
				 }
				 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId()); // cp 
				 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole()); //cp
				 paySdi = getSdi(ruleB.get_receiverLegalEntityRole(),ruleB.get_receiverLegalEntityId(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0); // cp 
					
				 if(paySdi != null) {
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for recSdi on " +   trade.getId() + " on "+trade.getTradedesc().substring(0, 3) + " for PO " + le.getName() + " sdi id == "+recSdi.getId());
					 ruleS.setPayerMethodType(paySdi.getMessageType());
					ruleS.set_payerSDId(paySdi.getId());
					ruleS.set_payerAgentID(paySdi.getAgentId());
				 }
				 if(ruleS.getReceiverMethodType().equalsIgnoreCase(ruleS.getPayerMethodType())) {
					 ruleS.set_settlementMethod(ruleS.getPayerMethodType());
			     }
				mmRules.addElement(ruleS);
				 
			 } else {
				 
				 TransferRule ruleB = new TransferRule();
				 ruleB.set_tradeId(trade.getId());
				 ruleB.set_productId(trade.getProductId());
				 ruleB.set_productType(trade.getProductType());
				
				 ruleB.set__tradeCptyId(trade.getCpID());
				 ruleB.set_settleDate( trade.getDelivertyDate());
						 ruleB.set_settlementCurrency(trade.getCurrency());
				 ruleB.set_transferCurrency(trade.getCurrency());
				 ruleB.set_transferType(transerTYPEPRINCIPAL);
				 
				 Book book = (Book) getBook(trade.getBookId());
				 po = (LegalEntity) getLegalEntity(book.getLe_id());
				 
				 ruleB.set_receiverLegalEntityId(book.getLe_id());  // po 
				 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
				 ruleB.set_payerLegalEntityId(le.getId()); // cp 
				 if(trade.isMirrorTrade()) {					    
			    	 ruleB.set__tradeCptyId(po.getId());
			    	 ruleB.set_payerLegalEntityId(po.getId());
			     } else {
			    	 ruleB.set__tradeCptyId(trade.getCpID());
			     }
				 ruleB.set_payReceive(RECEIVE);
				 Sdi recSdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0);  // po 
				 Sdi paySdi =getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0);   // cp 
					
				  if(paySdi != null) {
					  TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for paySdi on " +   trade.getId() + " on "+trade.getCurrency() + " for CounterParty " + le.getName() + " sdi id == "+paySdi.getId());
						
					 ruleB.set_payerLegalEntityRole(paySdi.getRole());  // cp
					 ruleB.set_payerAgentID(paySdi.getAgentId());
					
					
				 }
				 if(recSdi != null) {
					
					
					
					 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting SDI for recSdi on " +   trade.getId() + " on "+trade.getCurrency() + " for PO " + le.getName() + " sdi id == "+recSdi.getId());
						
					 setPOSdi(recSdi);
					
					 ruleB.set_receiverLegalEntityRole(recSdi.getRole()); // po
					
					 ruleB.set_payerSDId(recSdi.getId());
					 ruleB.set_receiverAgentID(recSdi.getAgentId());
					 ruleB.setReceiverMethodType(recSdi.getMessageType());
				 }
				 if(paySdi !=null) {
					 setCounterPartySDI(paySdi);
					 ruleB.set__sMethod(paySdi);
					 ruleB.set_receiverSDId(paySdi.getId());
					 ruleB.set_payerAgentID(paySdi.getAgentId());
					 ruleB.setPayerMethodType(paySdi.getMessageType());
				 }		
				 if(ruleB.getReceiverMethodType().equalsIgnoreCase(ruleB.getPayerMethodType())) {
					 ruleB.set_settlementMethod(ruleB.getPayerMethodType());
				     }				 
				 mmRules.addElement(ruleB);
				
				 TransferRule ruleS = new TransferRule();
				 ruleS.set_tradeId(trade.getId());
				 ruleS.set_productId(trade.getProductId());
				 ruleS.set_productType(trade.getProductType());
				
				 ruleS.set__tradeCptyId(trade.getCpID());
				 if(trade.isMirrorTrade()) {					    
			    	 ruleB.set__tradeCptyId(po.getId());
			    	
			     } else {
			    	 ruleB.set__tradeCptyId(trade.getCpID());
			     }
				 ruleB.set_settleDate( trade.getDelivertyDate());
						 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferType(transerTYPEPRINCIPAL);
				 ruleS.set_payReceive(PAY);
				 
				 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
				 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
				 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
				 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
				 paySdi =  getSdi(SDIConstants.PO,po.getId(),trade.getTradedesc().substring(0, 3),productType,0);
				
				 recSdi =getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getTradedesc().substring(0, 3),productType,0); 
			
				 if(recSdi != null) {
					 setPOSdi(recSdi);
					 ruleS.set_receiverSDId(recSdi.getId());
					 ruleS.set_receiverAgentID(recSdi.getAgentId());
					 ruleS.setReceiverMethodType(recSdi.getMessageType());
				 }
				 if(paySdi != null) {
					 setPOSdi(paySdi);
					 ruleS.set_payerSDId(paySdi.getId());
					 ruleS.set__sMethod(paySdi);
					 ruleS.set_payerAgentID(paySdi.getAgentId());
					 ruleS.setPayerMethodType(paySdi.getMessageType());
				 }
				 if(ruleS.getReceiverMethodType().equalsIgnoreCase(ruleS.getPayerMethodType())) {
					 ruleS.set_settlementMethod(ruleS.getPayerMethodType());
			     }	
				 mmRules.addElement(ruleS);
			 }
				 
			 if((fees != null)  && (!fees.isEmpty())) 
					 addFeesRule(fees,mmRules,po,trade);
			 return mmRules;
		}
			 
	}


        // how fees will get handle in SWAP trades needs to resolve this. 
	
		private Sdi getSdiOnEntity(int id, String productType, String currency,
			String role) {
		// TODO Auto-generated method stub
			Sdi sdi = null;
		Vector<Sdi> sdis = 	SDISelectorUtil.selectSdi(currency,productType,id,role,refData);
		if(commonUTIL.isEmpty(sdis))  {
			return null;
		} else {
			for(int i=0;i<sdis.size();i++) {
				Sdi s = sdis.get(i);
				if(!commonUTIL.isEmpty(s.getkey())) {
					sdi = s;
				    break;
				}
			}
			if(sdi == null)
			sdi = sdis.elementAt(0); // default value is always first 
		}
		return sdi;
	}



		private Vector<TransferRule> generateRulesForFXSwap(Trade swaptrade,Vector<String> mess) {
			Vector<Trade> swapTrades = new Vector<Trade>(); 
			 Vector<Fees> fees = null;
		// TODO Auto-generated method stub
			Trade swapTrade = swaptrade.getSwapLeg();
			Trade primaryTrade = swaptrade.getSwapPrimaryLeg();
			swapTrades.add(swapTrade);
			swapTrades.add(primaryTrade);
			 Vector<TransferRule>  mmRules = new Vector<TransferRule>();
			for(int i=0;i<swapTrades.size();i++) {
				Trade trade = swapTrades.get(i)	;
				/*try {
					Vector<Sdi> sdis = (Vector)	remoteTrade.getSDisOnTrade(trade);
					 fees = (Vector)	remoteTrade.selectFeesonTrade(swaptrade.getId());
					setSdi(sdis);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} */
					
					 LegalEntity po = null;
					 if(trade.getType().equalsIgnoreCase(tradeTypeBUY)) {
						
						 TransferRule ruleB = new TransferRule();
						
						 ruleB.set_tradeId(trade.getId());
						 ruleB.set_productId(trade.getProductId());
						 ruleB.set_productType(trade.getProductType());
						 ruleB.setFxSwapType(trade.getTradedesc1());  // used to identify which primary and secondary leg of swap
						 ruleB.set__tradeCptyId(trade.getCpID());
						 ruleB.set_settleDate( trade.getDelivertyDate());
								 ruleB.set_settlementCurrency(trade.getCurrency());
						 ruleB.set_transferCurrency(trade.getCurrency());
						 ruleB.set_transferType(transerTYPEPRINCIPAL);
					
							Book book = (Book) getBook(trade.getBookId());
							po = (LegalEntity) getLegalEntity(book.getLe_id());
						
							ruleB.set_payerLegalEntityId(book.getLe_id());
							 
							Sdi paySdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
							
							if(paySdi != null) {
								setPOSdi(paySdi);
							ruleB.set_payerLegalEntityRole(paySdi.getRole());
							ruleB.set__sMethod(paySdi);
							ruleB.set_payerSDId(paySdi.getId());
							ruleB.set_payerAgentID(paySdi.getAgentId());
							ruleB.setPayerMethodType(paySdi.getMessageType());
							
							}
							ruleB.set_receiverLegalEntityId(trade.getCpID());
						 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
						
						 Sdi recSdi = getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0); 
						// setCounterPartySDI(recSdi);
						
						 if(recSdi != null) {
							 setCounterPartySDI(recSdi);
						 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
						 ruleB.set_receiverSDId(recSdi.getId());
						 ruleB.set_receiverAgentID(recSdi.getAgentId());
						  ruleB.setReceiverMethodType(recSdi.getMessageType());
					 }
						 ruleB.set_payReceive(PAY);
						 if(ruleB.getReceiverMethodType().equalsIgnoreCase(ruleB.getPayerMethodType())) {
							 ruleB.set_settlementMethod(ruleB.getPayerMethodType());
						     }	
						 mmRules.addElement(ruleB);
						
						 TransferRule ruleS = new TransferRule();
						 ruleS.setBookId(trade.getBookId());
						 ruleS.set_tradeId(trade.getId());
						 ruleS.set_productId(trade.getProductId());
						 ruleS.set_productType(trade.getProductType());
						 ruleS.setFxSwapType(trade.getTradedesc1());  // used to identify which primary and secondary leg of swap
						 ruleS.set__tradeCptyId(trade.getCpID());
						 ruleS.set_settleDate( trade.getDelivertyDate());
								 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferType(transerTYPEPRINCIPAL);
						 ruleS.set_payReceive(RECEIVE);
						 
						 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
						 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
						
						  recSdi =  getSdi(SDIConstants.PO,po.getId(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0); 
						  
						  if(recSdi != null) {
							  setPOSdi(recSdi);
						  ruleS.set__sMethod(recSdi);
						  
						 ruleS.set_receiverSDId(recSdi.getId()); 
						 ruleS.set_receiverAgentID(recSdi.getAgentId());
						  ruleS.setReceiverMethodType(recSdi.getMessageType());
					 }
						 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
						 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole()); 
						 paySdi =
								 getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0);
						
						 if(paySdi != null) {
							 setCounterPartySDI(paySdi);
							ruleS.set_payerSDId(paySdi.getId());
							ruleS.set_payerAgentID(paySdi.getAgentId());
							  ruleS.setReceiverMethodType(paySdi.getMessageType());
						 }
						 if(ruleS.getReceiverMethodType().equalsIgnoreCase(ruleS.getPayerMethodType())) {
							 ruleS.set_settlementMethod(ruleS.getPayerMethodType());
					     }	
							mmRules.addElement(ruleS);
						 
						 
						 
						
						 
						 
						 
					 } else {
						 TransferRule ruleB = new TransferRule();
						 ruleB.set_tradeId(trade.getId());
						 ruleB.set_productId(trade.getProductId());
						 ruleB.set_productType(trade.getProductType());
						
						 ruleB.set__tradeCptyId(trade.getCpID());
						 ruleB.set_settleDate( trade.getDelivertyDate());
								 ruleB.set_settlementCurrency(trade.getCurrency());
						 ruleB.set_transferCurrency(trade.getCurrency());
						 ruleB.set_transferType(transerTYPEPRINCIPAL);
						 ruleB.setFxSwapType(trade.getTradedesc1());   // used to identify which primary and secondary leg of swap
							Book book = (Book) getBook(trade.getBookId());
							po = (LegalEntity) getLegalEntity(book.getLe_id());
						
							ruleB.set_payerLegalEntityId(book.getLe_id());
							
							ruleB.set_receiverLegalEntityId(book.getLe_id());
						 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
						 ruleB.set_payerLegalEntityId(le.getId());
						
						 ruleB.set_payReceive(RECEIVE);

						
						 Sdi paySdi =  getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0);  // cp 
						 
						 Sdi recSdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); // po 
						
						
						
						 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
						 if(paySdi != null) {
							 setCounterPartySDI(paySdi);
							 ruleB.set_payerLegalEntityRole(paySdi.getRole());
						 ruleB.set_receiverSDId(paySdi.getId());
						 ruleB.set_receiverAgentID(paySdi.getAgentId());
						 ruleB.set__sMethod(paySdi);
							ruleB.setPayerMethodType(paySdi.getMessageType());
						 }
						 if(recSdi != null) {
							 setPOSdi(recSdi);
						 ruleB.set_payerSDId(recSdi.getId());
						  ruleB.setReceiverMethodType(recSdi.getMessageType());
						ruleB.set_payerAgentID(recSdi.getAgentId());
						 }
						 
						 if(ruleB.getReceiverMethodType().equalsIgnoreCase(ruleB.getPayerMethodType())) {
							 ruleB.set_settlementMethod(ruleB.getPayerMethodType());
						     }	
						 
						 mmRules.addElement(ruleB);
						
						 TransferRule ruleS = new TransferRule();
						 ruleS.set_tradeId(trade.getId());
						 ruleS.set_productId(trade.getProductId());
						 ruleS.set_productType(trade.getProductType());
						 ruleS.setFxSwapType(trade.getTradedesc1()); // used to identify which primary and secondary leg of swap
						 ruleS.set__tradeCptyId(trade.getCpID());
						 ruleS.set_settleDate( trade.getDelivertyDate());
									 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferType(transerTYPEPRINCIPAL);
						 ruleS.set_payReceive(PAY);
						 
						 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
						 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
						 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
						 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
					
						  paySdi =  	 getSdi(SDIConstants.PO,po.getId(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0);
						  recSdi =		 getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getTradedesc().substring(0, 3),trade.getProductType(),0);
						
						
						  if(recSdi != null) {
							  setCounterPartySDI(recSdi);
						 ruleS.set_receiverSDId(recSdi.getId());
						 ruleS.set_receiverAgentID(recSdi.getAgentId());
						  ruleS.setReceiverMethodType(recSdi.getMessageType());
						  } 
						  if(paySdi != null) {
							  setPOSdi(paySdi);
						 ruleS.set_payerSDId(paySdi.getId());
						 ruleS.set__sMethod(paySdi);
						 ruleS.set_payerAgentID(paySdi.getAgentId());
							ruleS.setPayerMethodType(paySdi.getMessageType());
						  }
						  if(ruleS.getReceiverMethodType().equalsIgnoreCase(ruleS.getPayerMethodType())) {
								 ruleS.set_settlementMethod(ruleS.getPayerMethodType());
						     }	
						 mmRules.addElement(ruleS);
					 }
				}
			 LegalEntity po = null;
			 Book book = (Book) getBook(swaptrade.getBookId());
				po = (LegalEntity) getLegalEntity(book.getLe_id());
			
			 if((fees != null)  && (!fees.isEmpty())) 
				 addFeesRule(fees,mmRules,po,swaptrade);  // this is temperory adjustment we need understand how fees will get adjust in swap trade. 
		     return mmRules;
	}
		 
			
			
		
	

		private void addFeesRule(Vector<Fees> fees, Vector<TransferRule> bondRules,LegalEntity po,Trade trade) {
			// TODO Auto-generated method stub
			if(fees == null ) {
				 commonUTIL.display("GenerateFXTransferRule", " No Fees Attached for Trade " + trade.getId());
				return;
			}
			 commonUTIL.display("GenerateFXTransferRule", " Started Processing transfers for Fees on " + trade.getId());
			for(int f=0;f<fees.size();f++) {
				 TransferRule rulef = new TransferRule();
				Fees fee = (Fees) fees.elementAt(f);
				if(fee.getPayrec().trim().equalsIgnoreCase(PAY))  {
					rulef.set_payReceive(PAY);
					rulef.set_tradeId(trade.getId());
					rulef.set_productId(trade.getProductId());
					rulef.setFeeId(fee.getId());
					rulef.set_productType(trade.getProductType()+"_FEES");
					
					rulef.set__tradeCptyId(fee.getLeID());
					 rulef.set_settleDate( trade.getDelivertyDate());
								rulef.set_settlementCurrency(fee.getCurrency());
					rulef.set_transferCurrency(fee.getCurrency());
					  rulef.set_transferType(fee.getFeeType());
					  rulef.set_payerLegalEntityId(po.getId());

						
					  Sdi paySdi =   getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0);
					   if(paySdi == null) {
						  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE " + po.getName() + " for currency " + fee.getCurrency());
						
					  } else {
						
						  rulef.set_payerLegalEntityRole(paySdi.getRole());
							rulef.setPayerMethodType(paySdi.getMessageType());
					  rulef.set_payerSDId(paySdi.getId());
					  rulef.set__sMethod(paySdi);
					  }
					  rulef.set_receiverLegalEntityId(fee.getLeID());
					  rulef.set_receiverLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
					  Sdi recSdi = getSdiOnEntity(fee.getLeID(),productType,fee.getCurrency(),rulef.get_receiverLegalEntityRole());
						if(recSdi == null) {
							  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE " + fee.getCurrency());
							 
						  } else {
					  rulef.set_receiverSDId(recSdi.getId());
					  rulef.setReceiverMethodType(recSdi.getMessageType());
						  }
						if(rulef.getReceiverMethodType().equalsIgnoreCase(rulef.getPayerMethodType())) {
							  rulef.set_settlementMethod(rulef.getPayerMethodType());
						     }	
					
				} else  {
					rulef.set_payReceive(RECEIVE);
					rulef.set_tradeId(trade.getId());
					
					rulef.set_productType(trade.getProductType()+"_FEES");
					rulef.set_productId(trade.getProductId());
					rulef.setFeeId(fee.getId());
					rulef.set__tradeCptyId(fee.getLeID());
					 rulef.set_settleDate( trade.getDelivertyDate());
							rulef.set_settlementCurrency(fee.getCurrency());
					rulef.set_transferCurrency(fee.getCurrency());
					  rulef.set_transferType(fee.getFeeType());
					  rulef.set_payerLegalEntityId(fee.getLeID());
					  rulef.set_payerLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
					
					  Sdi paySdi =   getSdi(rulef.get_receiverLegalEntityRole(),fee.getLeID(),fee.getCurrency(),productType,0);
					  if(paySdi != null) {
						  rulef.set_payerSDId(paySdi.getId());
						  rulef.set_receiverLegalEntityId(po.getId());
							rulef.setPayerMethodType(paySdi.getMessageType());
						  } else {
							  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for currency " + fee.getCurrency());
							//  return;
						  }
					  
					 
					 
					  Sdi recSdi =   getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),productType,0);
					  if(recSdi != null) {
						  rulef.set_receiverLegalEntityRole(recSdi.getRole());
							
						  rulef.set_receiverSDId(recSdi.getId());
						  rulef.set__sMethod(recSdi);
						  rulef.setReceiverMethodType(recSdi.getMessageType());
						//  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE" );
						  //return;
						  if(rulef.getReceiverMethodType().equalsIgnoreCase(rulef.getPayerMethodType())) {
							  rulef.set_settlementMethod(rulef.getPayerMethodType());
						     }	
					  } else {
						  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for currency " + fee.getCurrency());
						//  return;
					  }
					 
					  
				}
				  
				bondRules.addElement(rulef);
				 commonUTIL.display("GenerateFXTransferRule", " End of  Processing transfers for Fees on " + trade.getId() + " attached ");
			}
			
		}
	

	 Product product = null;
	 Coupon coupon = null;
	 RemoteReferenceData referenceData;
	 Vector cashFlows = null;
	// FX pricing =  new MMPricing();
	
	
	

}
