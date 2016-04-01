package bo.transfer.rule;

import java.rmi.RemoteException;
import java.util.Vector;

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
import dsServices.RemoteReferenceData;

public class GenerateFUTURETransferRule extends ProductTransferRule {
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
		 
		 public Sdi getSdi(String role) {
			 Sdi sd = null;
			if(sdi ==  null || sdi.isEmpty()) 
				return null;
		
			 if((sdi != null) && (!sdi.isEmpty())) {
				 for(int i=0;i<sdi.size();i++) {
					 Sdi s = (Sdi) sdi.elementAt(i);
					 if(s.getRole().equalsIgnoreCase(role)) {
						 sd =s;
					     break;
					 }
				 }
				 
				 
				 
			 }
			 return sd;
				
		 }
		 public Sdi getAgentSdis() {
				Sdi po = getSdi("PO");
			    Sdi cp = getSdi("CounterParty");
			    try {
			    //	if(agentSdi == null)
			    	agentSdi = 	refData.selectAgentSdi(po.getAgentId(),po.getCpId(),po.getsdiformat(),po.getCurrency(),po.getProducts());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return agentSdi;
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
	public Vector<TransferRule> generateRules(Trade trade) {
		// TODO Auto-generated method stub
		 Vector<Fees> fees = null;
		if(trade.isFXSwap()) {
			return generateRulesForFXSwap(trade);
		} else {
			try {
			Vector<Sdi> sdis = (Vector)	remoteTrade.getSDisOnTrade(trade);
			 fees = (Vector)	remoteTrade.selectFeesonTrade(trade.getId());
			 
			// sdis =  setAgentID(sdis);
			setSdi(sdis);
			agentSdi = getAgentSdis();
			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Vector<TransferRule>  mmRules = new Vector<TransferRule>();
			 LegalEntity po = null;
			 if(trade.getType().equalsIgnoreCase(tradeTypeBUY)) {
				
				 TransferRule ruleB = new TransferRule();
				 ruleB.set_tradeId(trade.getId());
				 ruleB.set_productId(trade.getProductId());
				 ruleB.set_productType(trade.getProductType());
				
				
				 ruleB.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
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
					ruleB.set_payerLegalEntityId(book.getLe_id());
					
					Sdi paySdi = getSdiOnEntity(po.getId());
					ruleB.set_payerLegalEntityRole(paySdi.getRole());
					ruleB.set__sMethod(paySdi);
					ruleB.set_payerSDId(paySdi.getId());
					 if(trade.isMirrorTrade()) {
						    
				    	 ruleB.set__tradeCptyId(po.getId());
				    	 ruleB.set_receiverLegalEntityId(po.getId());
				     } else {
				    	 ruleB.set__tradeCptyId(trade.getCpID());
				    	 ruleB.set_receiverLegalEntityId(trade.getCpID());
				     }
					
				 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
				
				 Sdi recSdi = getSdiOnEntity(trade.getCpID());
			     if(trade.isMirrorTrade()) {
			    	 recSdi =  getSdiOnEntity(po.getId());
			     }
				 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
				 ruleB.set_receiverSDId(recSdi.getId());
				 ruleB.set_payReceive(PAY);
				 mmRules.addElement(ruleB);
				
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
				 ruleS.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
				 ruleS.set_settlementCurrency(trade.getCurrency());
				 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferType(transerTYPEPRINCIPAL);
				 ruleS.set_payReceive(RECEIVE);
				 
				 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
				 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
				  recSdi = getSdiOnEntity(ruleB.get_payerLegalEntityId());
				  
				  ruleS.set__sMethod(recSdi);
				  
				 ruleS.set_receiverSDId(recSdi.getId());
				 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
				 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
				 paySdi = getSdiOnEntity(ruleB.get_receiverLegalEntityId());
					ruleS.set_payerSDId(paySdi.getId());
					mmRules.addElement(ruleS);
				 
				 
				 
				
				 
				 
				 
			 } else {
				 TransferRule ruleB = new TransferRule();
				 ruleB.set_tradeId(trade.getId());
				 ruleB.set_productId(trade.getProductId());
				 ruleB.set_productType(trade.getProductType());
				
				 ruleB.set__tradeCptyId(trade.getCpID());
				 ruleB.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
				 ruleB.set_settlementCurrency(trade.getCurrency());
				 ruleB.set_transferCurrency(trade.getCurrency());
				 ruleB.set_transferType(transerTYPEPRINCIPAL);
				 
					Book book = (Book) getBook(trade.getBookId());
					po = (LegalEntity) getLegalEntity(book.getLe_id());
				
					ruleB.set_payerLegalEntityId(book.getLe_id());
					
					ruleB.set_receiverLegalEntityId(book.getLe_id());
				 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
				 ruleB.set_payerLegalEntityId(le.getId());
				 if(trade.isMirrorTrade()) {
					    
			    	 ruleB.set__tradeCptyId(po.getId());
			    	 ruleB.set_payerLegalEntityId(po.getId());
			     } else {
			    	 ruleB.set__tradeCptyId(trade.getCpID());
			     }
				 ruleB.set_payReceive(RECEIVE);
				 
				 Sdi paySdi = getSdiOnEntity(ruleB.get_payerLegalEntityId());
				 Sdi recSdi = getSdiOnEntity(ruleB.get_receiverLegalEntityId());
				 ruleB.set_payerLegalEntityRole(paySdi.getRole());
				 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
				 ruleB.set_receiverSDId(paySdi.getId());
				 ruleB.set_payerSDId(recSdi.getId());
				 ruleB.set__sMethod(paySdi);
				 
				 
				 
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
				 ruleS.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
				 ruleS.set_settlementCurrency(trade.getCurrency());
				 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
				 ruleS.set_transferType(transerTYPEPRINCIPAL);
				 ruleS.set_payReceive(PAY);
				 
				 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
				 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
				 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
				 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
				  paySdi = getSdiOnEntity(ruleS.get_payerLegalEntityId());
				  recSdi = getSdiOnEntity(ruleS.get_receiverLegalEntityId());
				 ruleS.set_receiverSDId(recSdi.getId());
				 ruleS.set_payerSDId(paySdi.getId());
				 ruleS.set__sMethod(paySdi);
				 mmRules.addElement(ruleS);
			 }
				 
			 if((fees != null)  && (!fees.isEmpty())) 
					 addFeesRule(fees,mmRules,po,trade);
			 return mmRules;
		}
			 
		 }


        // how fees will get handle in SWAP trades needs to resolve this. 
	
		private Vector<TransferRule> generateRulesForFXSwap(Trade swaptrade) {
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
				try {
					Vector<Sdi> sdis = (Vector)	remoteTrade.getSDisOnTrade(trade);
					 fees = (Vector)	remoteTrade.selectFeesonTrade(swaptrade.getId());
					setSdi(sdis);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 LegalEntity po = null;
					 if(trade.getType().equalsIgnoreCase(tradeTypeBUY)) {
						
						 TransferRule ruleB = new TransferRule();
						
						 ruleB.set_tradeId(trade.getId());
						 ruleB.set_productId(trade.getProductId());
						 ruleB.set_productType(trade.getProductType());
						 ruleB.setFxSwapType(trade.getTradedesc1());  // used to identify which primary and secondary leg of swap
						 ruleB.set__tradeCptyId(trade.getCpID());
						 ruleB.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
						 ruleB.set_settlementCurrency(trade.getCurrency());
						 ruleB.set_transferCurrency(trade.getCurrency());
						 ruleB.set_transferType(transerTYPEPRINCIPAL);
					
							Book book = (Book) getBook(trade.getBookId());
							po = (LegalEntity) getLegalEntity(book.getLe_id());
						
							ruleB.set_payerLegalEntityId(book.getLe_id());
							
							Sdi paySdi = getSdiOnEntity(po.getId());
							ruleB.set_payerLegalEntityRole(paySdi.getRole());
							ruleB.set__sMethod(paySdi);
							ruleB.set_payerSDId(paySdi.getId());
						
							ruleB.set_receiverLegalEntityId(trade.getCpID());
						 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
						
						 Sdi recSdi = getSdiOnEntity(trade.getCpID());
						 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
						 ruleB.set_receiverSDId(recSdi.getId());
						 ruleB.set_payReceive(PAY);
						 mmRules.addElement(ruleB);
						
						 TransferRule ruleS = new TransferRule();
						 ruleS.setBookId(trade.getBookId());
						 ruleS.set_tradeId(trade.getId());
						 ruleS.set_productId(trade.getProductId());
						 ruleS.set_productType(trade.getProductType());
						 ruleS.setFxSwapType(trade.getTradedesc1());  // used to identify which primary and secondary leg of swap
						 ruleS.set__tradeCptyId(trade.getCpID());
						 ruleS.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
						 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferType(transerTYPEPRINCIPAL);
						 ruleS.set_payReceive(RECEIVE);
						 
						 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
						 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
						  recSdi = getSdiOnEntity(ruleB.get_payerLegalEntityId());
						  
						  ruleS.set__sMethod(recSdi);
						  
						 ruleS.set_receiverSDId(recSdi.getId());
						 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
						 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
						 paySdi = getSdiOnEntity(ruleB.get_receiverLegalEntityId());
						 
							ruleS.set_payerSDId(paySdi.getId());
							mmRules.addElement(ruleS);
						 
						 
						 
						
						 
						 
						 
					 } else {
						 TransferRule ruleB = new TransferRule();
						 ruleB.set_tradeId(trade.getId());
						 ruleB.set_productId(trade.getProductId());
						 ruleB.set_productType(trade.getProductType());
						
						 ruleB.set__tradeCptyId(trade.getCpID());
						 ruleB.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
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
						 
						 Sdi paySdi = getSdiOnEntity(ruleB.get_payerLegalEntityId());
						 Sdi recSdi = getSdiOnEntity(ruleB.get_receiverLegalEntityId());
						 ruleB.set_payerLegalEntityRole(paySdi.getRole());
						 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
						 ruleB.set_receiverSDId(paySdi.getId());
						 ruleB.set_payerSDId(recSdi.getId());
						 ruleB.set__sMethod(paySdi);
						 
						 
						 
						 mmRules.addElement(ruleB);
						
						 TransferRule ruleS = new TransferRule();
						 ruleS.set_tradeId(trade.getId());
						 ruleS.set_productId(trade.getProductId());
						 ruleS.set_productType(trade.getProductType());
						 ruleS.setFxSwapType(trade.getTradedesc1()); // used to identify which primary and secondary leg of swap
						 ruleS.set__tradeCptyId(trade.getCpID());
						 ruleS.set_settleDate(new DateU(commonUTIL.stringToDate(trade.getDelivertyDate(),true)));
						 ruleS.set_settlementCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferCurrency(trade.getTradedesc().substring(0, 3));
						 ruleS.set_transferType(transerTYPEPRINCIPAL);
						 ruleS.set_payReceive(PAY);
						 
						 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
						 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
						 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
						 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
						  paySdi = getSdiOnEntity(ruleS.get_payerLegalEntityId());
						  recSdi = getSdiOnEntity(ruleS.get_receiverLegalEntityId());
						 ruleS.set_receiverSDId(recSdi.getId());
						 ruleS.set_payerSDId(paySdi.getId());
						 ruleS.set__sMethod(paySdi);
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
					rulef.set_settleDate(new DateU(commonUTIL.stringToDate(fee.getFeeDate(),true)));
					rulef.set_settlementCurrency(fee.getCurrency());
					rulef.set_transferCurrency(fee.getCurrency());
					  rulef.set_transferType(fee.getFeeType());
					  rulef.set_payerLegalEntityId(po.getId());
					 
					  Sdi paySdi = getSdiOnEntity(po.getId());
					  rulef.set_payerLegalEntityRole(paySdi.getRole());
					  if(paySdi == null) {
						  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE" + po.getName());
						  return;
					  }
					  rulef.set_payerSDId(paySdi.getId());
					  rulef.set__sMethod(paySdi);
					  rulef.set_receiverLegalEntityId(fee.getLeID());
					  rulef.set_receiverLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
					  Sdi recSdi = getSdiOnEntity(fee.getLeID());
						if(recSdi == null) {
							  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE" );
							  return;
						  }
					  rulef.set_receiverSDId(recSdi.getId());
					
				} else  {
					rulef.set_payReceive(RECEIVE);
					rulef.set_tradeId(trade.getId());
					
					rulef.set_productType(trade.getProductType()+"_FEES");
					rulef.set_productId(trade.getProductId());
					rulef.setFeeId(fee.getId());
					rulef.set__tradeCptyId(fee.getLeID());
					rulef.set_settleDate(new DateU(commonUTIL.stringToDate(fee.getFeeDate(),true)));
					rulef.set_settlementCurrency(fee.getCurrency());
					rulef.set_transferCurrency(fee.getCurrency());
					  rulef.set_transferType(fee.getFeeType());
					  rulef.set_payerLegalEntityId(fee.getLeID());
					  rulef.set_payerLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
					  Sdi paySdi = getSdiOnEntity(fee.getLeID());
					  if(paySdi == null) {
						 
							  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE" );
							  return;
						  }
					  rulef.set_payerSDId(paySdi.getId());
					  rulef.set_receiverLegalEntityId(po.getId());
					 
					  Sdi recSdi = getSdiOnEntity(po.getId());
					  rulef.set_receiverLegalEntityRole(recSdi.getRole());
					  if(recSdi == null) {
						  commonUTIL.display("GenerateFXTransferRule", " Missing SDI on Fees for LE" );
						  return;
					  }
					  rulef.set_receiverSDId(recSdi.getId());
					  rulef.set__sMethod(recSdi);
					  
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
