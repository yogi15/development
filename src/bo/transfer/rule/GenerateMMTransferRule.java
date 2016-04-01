package bo.transfer.rule;

import java.rmi.RemoteException;
import java.util.Vector;

import productPricing.MMPricing;
import productPricing.pricingUtil.MMCashFlow;
import util.commonUTIL;
import util.common.DateU;
import dsServices.RemoteReferenceData;

import beans.Book;
import beans.Coupon;
import beans.Fees;
import beans.Flows;
import beans.LegalEntity;
import beans.Product;
import beans.Sdi;
import beans.Trade;
import beans.TransferRule;

public class GenerateMMTransferRule extends ProductTransferRule {
	 static private final String productType = "MM";
	 
	 
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
			try {
			Vector<Sdi> sdis = (Vector)	remoteTrade.getSDisOnTrade(trade);
			 fees = (Vector)	remoteTrade.selectFeesonTrade(trade.getId());
			setSdi(sdis);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Vector<TransferRule>  mmRules = new Vector<TransferRule>();
			 LegalEntity po = null;
			 MMPricing pricing =  new MMPricing();
			 try {
				product = remoteProduct.selectProduct(trade.getProductId());
				coupon =(Coupon) ((Vector) remoteProduct.getCoupon(product.getId())).elementAt(0);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 pricing.price(trade, product, coupon);
	         MMCashFlow cashFlow = pricing.generateCashFlow();
	         Vector<Flows> flows = cashFlow.getFlows();
	         Book book = (Book) getBook(trade.getBookId());
				po = (LegalEntity) getLegalEntity(book.getLe_id());
				 LegalEntity le =  (LegalEntity) getLegalEntity(trade.getCpID());
				 Sdi recSdi = getSdiOnEntity(trade.getCpID());
				 Sdi paySdi = getSdiOnEntity(po.getId());
				 if(recSdi == null || paySdi == null)
					 return null;
	         for(int i=0;i<flows.size();i++) {
	        	 Flows flow = (Flows)  flows.elementAt(i);
	        	 TransferRule rule = new TransferRule();
	        	 rule.setBookId(trade.getBookId());
	        	 rule.set_productId(trade.getProductId());
	        	 rule.set_productType(trade.getProductType());
	        	 rule.set__tradeCptyId(trade.getCpID());
	        	 rule.set_settleDate(new DateU(flow.getPaymentDate()));
	        	 rule.set_settlementCurrency(trade.getCurrency());
	        	 rule.set_transferCurrency(trade.getCurrency());
	        	 rule.set_seqNumber(i);
				
	        	 if(flow.getType().equalsIgnoreCase(transerTYPEPRINCIPAL)) {
	        		 rule.set_transferType(transerTYPEPRINCIPAL);
	        		// rule.set_seqNumber(); // this is set to know which is first principal and second principal 
	        		 if(trade.getType().equalsIgnoreCase(tradeTypeSELL)) {
	        			 rule.set_payReceive(PAY);
	        			 rule.set_payerLegalEntityId(po.getId());
	        			 rule.set_payerLegalEntityRole(paySdi.getRole());
	        			 rule.set_payerSDId(paySdi.getId());
	        			 rule.set__sMethod(paySdi);
	        			 rule.set_receiverLegalEntityId(le.getId());
	        			 rule.set_receiverLegalEntityRole(recSdi.getRole());
	        			 rule.set_receiverSDId(recSdi.getId());
	        		 } else {
	        			 rule.set_payReceive(RECEIVE);
	        			 
	        			 rule.set_payerLegalEntityId(le.getId());
	        			 rule.set_payerLegalEntityRole(recSdi.getRole());
	        			 rule.set_payerSDId(recSdi.getId());
	        			 rule.set__sMethod(recSdi);
	        			 rule.set_receiverLegalEntityId(po.getId());
	        			 rule.set_receiverLegalEntityRole(paySdi.getRole());
	        			 rule.set_receiverSDId(paySdi.getId());
	        			 
	        			 
	        			
	        		 }
	        		   
	        		 
	        		 
	        	 } if(flow.getType().equalsIgnoreCase(transerTYPEINTEREST)) {
	        		 rule.set_transferType(transerTYPEINTEREST);
	        	
	        		 if(trade.getType().equalsIgnoreCase(tradeTypeSELL)) {
	        			 rule.set_payReceive(RECEIVE);
	        			 rule.set_payerLegalEntityId(le.getId());
	        			 rule.set_payerLegalEntityRole(recSdi.getRole());
	        			 rule.set_payerSDId(recSdi.getId());
	        			 rule.set__sMethod(recSdi);
	        			 rule.set_receiverLegalEntityId(po.getId());
	        			 rule.set_receiverLegalEntityRole(paySdi.getRole());
	        			 rule.set_receiverSDId(paySdi.getId());
	        		 } else {
	        			 rule.set_payReceive(PAY);
	        			 rule.set_payerLegalEntityId(po.getId());
	        			 rule.set_payerLegalEntityRole(paySdi.getRole());
	        			 rule.set_payerSDId(paySdi.getId());
	        			 rule.set__sMethod(paySdi);
	        			 rule.set_receiverLegalEntityId(le.getId());
	        			 rule.set_receiverLegalEntityRole(recSdi.getRole());
	        			 rule.set_receiverSDId(recSdi.getId());
	        		 } 
	        	 } else if(!flow.getType().equalsIgnoreCase(transerTYPEINTEREST) && (!flow.getType().equalsIgnoreCase(transerTYPEPRINCIPAL))) {
	        		 rule.set_transferType(transerTYPEPRINCIPAL);
	        	//	 rule.set_seqNumber(1); // this is set to know which is first principal and second principal 
	        		 if(trade.getType().equalsIgnoreCase(tradeTypeSELL)) {
	        			 rule.set_payReceive(RECEIVE);
	        			 rule.set_payerLegalEntityId(le.getId());
	        			 rule.set_payerLegalEntityRole(recSdi.getRole());
	        			 rule.set_payerSDId(recSdi.getId());
	        			 rule.set__sMethod(recSdi);
	        			 rule.set_receiverLegalEntityId(po.getId());
	        			 rule.set_receiverLegalEntityRole(paySdi.getRole());
	        			 rule.set_receiverSDId(paySdi.getId());
	        		 } else {
	        			 rule.set_payReceive(PAY);
	        			 rule.set_payerLegalEntityId(po.getId());
	        			 rule.set_payerLegalEntityRole(paySdi.getRole());
	        			 rule.set_payerSDId(paySdi.getId());
	        			 rule.set__sMethod(paySdi);
	        			 rule.set_receiverLegalEntityId(le.getId());
	        			 rule.set_receiverLegalEntityRole(recSdi.getRole());
	        			 rule.set_receiverSDId(recSdi.getId());
	        		 } 
	        	 }
	        	 
	        	 
	        	  mmRules.addElement(rule);
	         }
			 
	       
			 
							 
			 if((fees != null)  && (!fees.isEmpty())) 
					 addFeesRule(fees,mmRules,po,trade);
			 return mmRules;
			 
		 }



		private void addFeesRule(Vector<Fees> fees, Vector<TransferRule> bondRules,LegalEntity po,Trade trade) {
			// TODO Auto-generated method stub
			if(fees == null ) {
				 commonUTIL.display("GenerateMMTransferRule", " No Fees Attached for Trade " + trade.getId());
				return;
			}
			 commonUTIL.display("GenerateMMTransferRule", " Started Processing transfers for Fees on " + trade.getId());
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
						  commonUTIL.display("GenerateMMTransferRule", " Missing SDI on Fees for LE" + po.getName());
						  return;
					  }
					  rulef.set_payerSDId(paySdi.getId());
					  rulef.set__sMethod(paySdi);
					  rulef.set_receiverLegalEntityId(fee.getLeID());
					  rulef.set_receiverLegalEntityRole(getLegalEntity(fee.getLeID()).getRole());
					  Sdi recSdi = getSdiOnEntity(fee.getLeID());
						if(recSdi == null) {
							  commonUTIL.display("GenerateMMTransferRule", " Missing SDI on Fees for LE" );
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
						 
							  commonUTIL.display("GenerateMMTransferRule", " Missing SDI on Fees for LE" );
							  return;
						  }
					  rulef.set_payerSDId(paySdi.getId());
					  rulef.set_receiverLegalEntityId(po.getId());
					  rulef.set_receiverLegalEntityRole(paySdi.getRole());
					  Sdi recSdi = getSdiOnEntity(po.getId());
					  if(recSdi == null) {
						  commonUTIL.display("GenerateMMTransferRule", " Missing SDI on Fees for LE" );
						  return;
					  }
					  rulef.set_receiverSDId(recSdi.getId());
					  rulef.set__sMethod(recSdi);
					  
				}
				  
				bondRules.addElement(rulef);
				 commonUTIL.display("GenerateMMTransferRule", " End of  Processing transfers for Fees on " + trade.getId() + " attached ");
			}
			
		}
	
	
	 Product product = null;
	 Coupon coupon = null;
	 RemoteReferenceData referenceData;
	 Vector cashFlows = null;
	 MMPricing pricing =  new MMPricing();
	
	
	

}
