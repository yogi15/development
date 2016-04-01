package bo.transfer.rule;

import java.rmi.RemoteException;
import java.util.Vector;

import constants.SDIConstants;

import logAppender.TransferServiceAppender;

import util.commonUTIL;
import util.common.DateU;

import beans.Book;
import beans.Fees;
import beans.LegalEntity;
import beans.Sdi;
import beans.Trade;
import beans.TransferRule;

public class GenerateBONDTransferRule extends ProductTransferRule {
	
	
	 static private final String productType = "BOND";
	 
	 
	
	 public Vector<Sdi> getSdi() {
		return sdi;
	}


	public void setSdi(Vector<Sdi> sdi) {
		this.sdi = sdi;
	}



	Vector<Sdi>  sdi = new Vector();
	 
	 
	 
	 
	 public  String getProductType() {
		 return productType;
		 
	 }
     
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
	 
	 public Vector<TransferRule>  generateRules(Trade trade,Vector<String> message) {
		 Vector<Fees> fees = null;
		 TransferServiceAppender.printLog("INFO", " GenerateFXTransferRule getting Called   ****** on " +   trade.getId() + " on "+ trade.getStatus() + " status for "+trade.getProductType());
			 
		 Vector<Sdi> sdis = null;
		
		 Vector<TransferRule>  bondRules = new Vector<TransferRule>();
		 LegalEntity po = null;
		 if(trade.getType().equalsIgnoreCase(tradeTypeBUY)) {
			
			 TransferRule ruleB = new TransferRule();
			 ruleB.setBookId(trade.getBookId());
			 ruleB.set_tradeId(trade.getId());
			 ruleB.set_productId(trade.getProductId());
			 ruleB.set_productType(trade.getProductType());
			
			 ruleB.set__tradeCptyId(trade.getCpID());
			 ruleB.set_settleDate(  trade.getDelivertyDate() );
			 ruleB.set_settlementCurrency(trade.getCurrency());
			 ruleB.set_transferCurrency(trade.getCurrency());
			 ruleB.set_transferType(transerTYPEPRINCIPAL);
		    
				Book book = (Book) getBook(trade.getBookId());
				po = (LegalEntity) getLegalEntity(book.getLe_id());
			
				ruleB.set_payerLegalEntityId(book.getLe_id());
				
				Sdi paySdi =  getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
				if(paySdi == null) {
					String mess = "No SDI found for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
				ruleB.set_payerAgentID(paySdi.getAgentId());
				ruleB.set_payerLegalEntityRole(paySdi.getRole());
				ruleB.set__sMethod(paySdi);
				ruleB.set_payerSDId(paySdi.getId());
			    ruleB.setPayerMethodType(paySdi.getMessageType());
				ruleB.set_receiverLegalEntityId(trade.getCpID());
			 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
			
			 Sdi recSdi =getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0);  //getSdiOnEntity(trade.getCpID());
			 if(recSdi == null) {
					String mess = "No SDI found for "+le.getName() + " on role "+le.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
			 ruleB.set_receiverAgentID(recSdi.getAgentId());
			 ruleB.set_receiverSDId(recSdi.getId());
			 ruleB.setReceiverMethodType(recSdi.getMessageType());
			 ruleB.set_payReceive(PAY);
			 bondRules.addElement(ruleB);
			
			 TransferRule ruleS = new TransferRule();
			 ruleS.setBookId(trade.getBookId());
			 
			 ruleS.set_tradeId(trade.getId());
			 ruleS.set_productId(trade.getProductId());
			 ruleS.set_productType(trade.getProductType());
			
			 ruleS.set__tradeCptyId(trade.getCpID());
			 ruleS.set_settleDate( trade.getDelivertyDate() );
			 ruleS.set_settlementCurrency(trade.getCurrency());
			 ruleS.set_transferCurrency(trade.getCurrency());
			 ruleS.set_transferType(transerTYPESECURITY);
			 ruleS.set_payReceive(RECEIVE);
			 
			 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
			
			  recSdi = getSdi(ruleB.get_payerLegalEntityRole(),ruleB.get_payerLegalEntityId(),trade.getCurrency(),trade.getProductType(),0); 
			  if(recSdi == null) {
					String mess = "No SDI found for "+le.getName() + " on role "+le.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			  ruleS.set_receiverLegalEntityRole( recSdi.getRole());

			  ruleS.set_payerAgentID(recSdi.getAgentId());
			  ruleS.set__sMethod(recSdi);
			  ruleS.setPayerMethodType(recSdi.getMessageType());
			 ruleS.set_receiverSDId(recSdi.getId());
			 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
			 
			 paySdi = getSdi(ruleB.get_receiverLegalEntityRole(),ruleB.get_receiverLegalEntityId(),trade.getCurrency(),trade.getProductType(),0); // getSdiOnEntity(ruleB.get_receiverLegalEntityId());
			 if(paySdi == null) {
					String mess = "No SDI found for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			 ruleS.setReceiverMethodType(paySdi.getMessageType());
			 ruleS.set_receiverAgentID(recSdi.getAgentId());
			 ruleS.set_payerLegalEntityRole(paySdi.getRole());
				ruleS.set_payerSDId(paySdi.getId());
			 bondRules.addElement(ruleS);
			 
			 
			 
			
			 
			 
			 
		 } else {
			 TransferRule ruleB = new TransferRule();
			 ruleB.set_tradeId(trade.getId());
			 ruleB.set_productId(trade.getProductId());
			 ruleB.set_productType(trade.getProductType());
			
			 ruleB.set__tradeCptyId(trade.getCpID());
			 ruleB.set_settleDate( trade.getDelivertyDate() );
			 ruleB.set_settlementCurrency(trade.getCurrency());
			 ruleB.set_transferCurrency(trade.getCurrency());
			 ruleB.set_transferType(transerTYPEPRINCIPAL);
			 
				Book book = (Book) getBook(trade.getBookId());
				po = (LegalEntity) getLegalEntity(book.getLe_id());
			
				ruleB.set_payerLegalEntityId(book.getLe_id());
			
				ruleB.set_receiverLegalEntityId(trade.getCpID());
				
			 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
			
			 ruleB.set_payReceive(RECEIVE);
			 
			 Sdi paySdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
			 if(paySdi == null) {
				 ruleB = null;
					String mess = "No SDI found for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			 ruleB.setPayerMethodType(paySdi.getMessageType());
			 Sdi recSdi   =getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),0);  //getSdiOnEntity(trade.getCpID());
			 if(recSdi == null) {
				 ruleB = null;
				 paySdi = null;
					String mess = "No SDI found for "+le.getName() + " on role "+le.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			 ruleB.setReceiverMethodType(recSdi.getMessageType());
				ruleB.set_payerLegalEntityRole(paySdi.getRole());
				 ruleB.set_payerAgentID(paySdi.getAgentId());
				 ruleB.set_receiverLegalEntityRole(recSdi.getRole());
				 ruleB.set_receiverAgentID(recSdi.getAgentId());
				 
			 if(recSdi != null)
			 ruleB.set_receiverSDId(recSdi.getId());
			 if(paySdi != null)
			 ruleB.set_payerSDId(paySdi.getId());
			 ruleB.set__sMethod(paySdi);
			 
			 
			 
			 bondRules.addElement(ruleB);
			
			 TransferRule ruleS = new TransferRule();
			 ruleS.set_tradeId(trade.getId());
			 ruleS.set_productId(trade.getProductId());
			 ruleS.set_productType(trade.getProductType());
			
			 ruleS.set__tradeCptyId(trade.getCpID());
			 ruleS.set_settleDate( trade.getDelivertyDate() );
			 ruleS.set_settlementCurrency(trade.getCurrency());
			 ruleS.set_transferCurrency(trade.getCurrency());
			 ruleS.set_transferType(transerTYPESECURITY);
			 ruleS.set_payReceive(PAY);
			 
			 ruleS.set_receiverLegalEntityId(ruleB.get_payerLegalEntityId());
			 ruleS.set_receiverLegalEntityRole(ruleB.get_payerLegalEntityRole());
			 ruleS.set_payerLegalEntityId(ruleB.get_receiverLegalEntityId());
			 ruleS.set_payerLegalEntityRole(ruleB.get_receiverLegalEntityRole());
			  paySdi =  getSdi(ruleS.get_payerLegalEntityRole(),ruleS.get_payerLegalEntityId(),trade.getCurrency(),trade.getProductType(),0); 
			  if(paySdi == null) {
					String mess = "No SDI found for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			  ruleS.setPayerMethodType(paySdi.getMessageType());
			  ruleS.set_payerAgentID(paySdi.getAgentId());
			  recSdi = getSdi(ruleS.get_receiverLegalEntityRole(),ruleS.get_receiverLegalEntityId(),trade.getCurrency(),trade.getProductType(),0); 
			  if(recSdi == null) {
					String mess = "No SDI found for "+le.getName() + " on role "+le.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
					message.addElement(mess);
					return null;
				}
			  
			  if(recSdi != null)
				  ruleS.set_receiverSDId(recSdi.getId());
					 if(paySdi != null)
						 ruleS.set_payerSDId(paySdi.getId());
					 ruleS.setReceiverMethodType(recSdi.getMessageType());
					  ruleS.set_receiverAgentID(recSdi.getAgentId());
			 ruleS.set__sMethod(paySdi);
			 bondRules.addElement(ruleS);
		 }
			 
		 if((fees != null)  && (!fees.isEmpty())) 
				 addFeesRule(fees,bondRules,po,trade,message);
		 return bondRules;
		 
	 }



	private void addFeesRule(Vector<Fees> fees, Vector<TransferRule> bondRules,LegalEntity po,Trade trade,Vector message) {
		// TODO Auto-generated method stub
		if(fees == null ) {
			 commonUTIL.display("GenerateBONDTransferRule", " No Fees Attached for Trade " + trade.getId());
			return;
		}
		 commonUTIL.display("GenerateBONDTransferRule", " Started Processing transfers for Fees on " + trade.getId());
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
				rulef.set_settleDate( fee.getFeeDate());
				rulef.set_settlementCurrency(fee.getCurrency());
				rulef.set_transferCurrency(fee.getCurrency());
				  rulef.set_transferType(fee.getFeeType());
				  rulef.set_payerLegalEntityId(po.getId());
				
				  Sdi paySdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
					 if(paySdi == null) {
						 rulef = null;
							String mess = "No SDI found on fees for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
							message.addElement(mess);
							return;
						}
				  rulef.set_payerLegalEntityRole(paySdi.getRole());
				   
				  rulef.set_payerSDId(paySdi.getId());
				  rulef.set__sMethod(paySdi);
				  rulef.set_receiverLegalEntityId(fee.getLeID());
				  rulef.set_receiverLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
				  Sdi recSdi = getSdi(getLegalEntity(fee.getLeID()).getRole(),fee.getLeID(),trade.getCurrency(),trade.getProductType(),0); 
				  if(recSdi == null) {
						String mess = "No SDI found for "+getLegalEntity(fee.getLeID()).getName() + " on role "+getLegalEntity(fee.getLeID()).getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
						message.addElement(mess);
						return  ;
					}
				  rulef.set_receiverSDId(recSdi.getId());
				
			} else  {
				rulef.set_payReceive(RECEIVE);
				rulef.set_tradeId(trade.getId());
				
				rulef.set_productType(trade.getProductType()+"_FEES");
				rulef.set_productId(trade.getProductId());
				rulef.setFeeId(fee.getId());
				rulef.set__tradeCptyId(fee.getLeID());
				rulef.set_settleDate( fee.getFeeDate());
				rulef.set_settlementCurrency(fee.getCurrency());
				rulef.set_transferCurrency(fee.getCurrency());
				  rulef.set_transferType(fee.getFeeType());
				  rulef.set_payerLegalEntityId(fee.getLeID());
				  rulef.set_payerLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
				  Sdi paySdi = getSdi(getLegalEntity(fee.getLeID()).getRole(),fee.getLeID(),trade.getCurrency(),trade.getProductType(),0); 
				  if(paySdi == null) {
						String mess = "No SDI found for "+getLegalEntity(fee.getLeID()).getName() + " on role "+getLegalEntity(fee.getLeID()).getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
						message.addElement(mess);
						return  ;
					}
				  rulef.set_payerSDId(paySdi.getId());
				  rulef.set_receiverLegalEntityId(po.getId());
				
				  Sdi recSdi = getSdi(SDIConstants.PO,po.getId(),trade.getCurrency(),trade.getProductType(),0); 
					 if(paySdi == null) {
						 rulef = null;
							String mess = "No SDI found on fees for "+po.getName() + " on role "+po.getRole() + " for product "+trade.getProductType() + " "+trade.getCurrency();
							message.addElement(mess);
							return;
						}
				  rulef.set_receiverLegalEntityRole(paySdi.getRole());
				  if(recSdi == null) {
					  commonUTIL.display("GenerateBONDTransferRule", " Missing SDI on Fees for LE" );
					  return;
				  }
				  rulef.set_receiverSDId(recSdi.getId());
				  rulef.set__sMethod(recSdi);
				  
			}
			  
			bondRules.addElement(rulef);
			 commonUTIL.display("GenerateBONDTransferRule", " End of  Processing transfers for Fees on " + trade.getId() + " attached ");
		}
		
	}


	@Override
	public Vector<TransferRule> getTransferRules(Vector sdis) {
		// TODO Auto-generated method stub
		
		return sdis;
	}
	 
	 
	 

}
