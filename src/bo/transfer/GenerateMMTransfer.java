package bo.transfer;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import productPricing.MMPricing;
import productPricing.Pricer;
import productPricing.pricingUtil.MMCashFlow;
import util.commonUTIL;

import beans.Coupon;
import beans.Fees;
import beans.Flows;
import beans.NettingConfig;
import beans.Product;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;

import bo.transfer.rule.GenerateMMTransferRule;
import bo.transfer.rule.ProductTransferRule;

public class GenerateMMTransfer extends BOTransfer {
	
	Pricer mmPricer = null;
	String productType = "MM";
    Product product = null;
    Trade trade = null;
    Coupon coupon = null;
	Vector<Flows>	 flows = new Vector<Flows>();
	Vector<String>  feesType = null;
	Vector<Fees> fees = null;
	ProductTransferRule mmTransferRule = null;
	
	

	
	public GenerateMMTransfer() {
		mmTransferRule = new GenerateMMTransferRule();
		mmTransferRule.setRefDate(remoteRef);
		mmTransferRule.setRemoteBOProcess(boProcess);
		mmTransferRule.setRemoteTrade(remoteTrade);
		mmTransferRule.setRemoteProduct(remoteProduct);
		
	}
	
	
	private Vector<TransferRule> generateRule(Trade trade,Vector<String> message) {
		 Vector<TransferRule> rules = null;
		 
	     if(!trade.isCustomRuleApply()) {
	 	   rules =mmTransferRule.generateRules(trade,message);
	     }   else {
			   try {
				rules = (Vector<TransferRule>) boProcess.getCustomTransferRule(trade.getId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("GenerateFXTransfer", "generateRule get error on Trade id "+trade.getId(), e);
				message.add(new String("GenerateFXTransfer generateRule get error on Trade id "+trade.getId()));
				return null;
			}
			  
	  }
		 
	     return rules;
    	 
    	
    	
    }
	private void getCashFlows(Trade trade) {
		// TODO Auto-generated method stub
		try {
		product =	(Product) remoteProduct.selectProduct(trade.getProductId());
		Vector couponV = (Vector) remoteProduct.getCoupon(product.getId());
		Coupon coupon = (Coupon) couponV.elementAt(0);
		mmPricer = new MMPricing();
		mmPricer.price(trade, product, coupon);
         MMCashFlow mmcashFlow = (MMCashFlow) mmPricer.genearteCashFlow();
         mmPricer.setTradeData(mmcashFlow);
         flows =  mmcashFlow.getFlows();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		commonUTIL.displayError("GenearteMMTransfer", "getCashFlows", e);
		}
	}
	private Fees getFee(int id) {
		Fees fee = null;
	    if(fees != null && (!fees.isEmpty())) {
	    	for(int i=0;i<fees.size();i++) {
	    		fee = (Fees) fees.get(i);
	    		if(id == fee.getId())
	    			break;
	    	}
	    }
	    
	    return fee;
	}


	
	@Override
	public Vector<Transfer> generateTransfer(Trade trade,
			Vector<String> feestype,NettingConfig netConfig,Vector<String> message) {
		// TODO Auto-generated method stub
		
		this.feesType = feestype;
		Vector<Transfer> transfers = new Vector<Transfer>();
		Vector<TransferRule> rules = generateRule(trade,message);
		try {
			fees = (Vector)  remoteTrade.selectFeesonTrade(trade.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		                
		getCashFlows(trade);
		Iterator<TransferRule> rulesI = rules.iterator();
		while(rulesI.hasNext()) {
			Transfer transfer = new Transfer();
			
		    transfer.setTradeVersionID(trade.getVersion());
			TransferRule rule = rulesI.next();
			transfer.setBookId(rule.getBookId());
			if((rule.get_transferType().equalsIgnoreCase(mmTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.RECEIVE))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(mmTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL"+"_"+rule.get_settleDate(), "RECEIVE"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate( rule.get_settleDate() );
				transfer.setTradeId(trade.getId());
				if(rule.get_productId() == 0) 
					transfer.setProductId(trade.getProductId());
				else
				transfer.setProductId(rule.get_productId());
				transfer.setSettlecurrency(rule.get_settlementCurrency());
				transfer.setValueDate(trade.getEffectiveDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
				transfer.setProductType(trade.getProductType());
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(mmTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.PAY))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
			    transfer.setEventType("PAYMENT");
			    transfer.setTransferType(mmTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL"+"_"+rule.get_settleDate(), "PAYMENT"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate( rule.get_settleDate() );
				transfer.setTradeId(trade.getId());
				if(rule.get_productId() == 0) 
					transfer.setProductId(trade.getProductId());
				else
				transfer.setProductId(rule.get_productId());
				transfer.setSettlecurrency(rule.get_settlementCurrency());
				transfer.setValueDate(trade.getEffectiveDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
				transfer.setProductType(trade.getProductType());
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(mmTransferRule.transerTYPEINTEREST)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.PAY))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
			    transfer.setEventType("PAYMENT");
			    transfer.setTransferType(mmTransferRule.transerTYPEINTEREST);
			    transfer.addAttribues(mmTransferRule.transerTYPEINTEREST+"_"+rule.get_settleDate(), "PAYMENT"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate( rule.get_settleDate() );
				transfer.setTradeId(trade.getId());
				if(rule.get_productId() == 0) 
					transfer.setProductId(trade.getProductId());
				else
				transfer.setProductId(rule.get_productId());
				transfer.setSettlecurrency(rule.get_settlementCurrency());
				transfer.setValueDate(trade.getEffectiveDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
				transfer.setProductType(trade.getProductType());
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(mmTransferRule.transerTYPEINTEREST)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.RECEIVE))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(mmTransferRule.transerTYPEINTEREST);
			    transfer.addAttribues(mmTransferRule.transerTYPEINTEREST+"_"+rule.get_settleDate(), "RECEIPT");  // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate( rule.get_settleDate() );
				transfer.setTradeId(trade.getId());
				if(rule.get_productId() == 0) 
					transfer.setProductId(trade.getProductId());
				else
				transfer.setProductId(rule.get_productId());
				transfer.setSettlecurrency(rule.get_settlementCurrency());
				transfer.setValueDate(trade.getEffectiveDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
				transfer.setProductType(trade.getProductType());
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+mmTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.PAY))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
				transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
				transfer.setEventType("PAYMENT");
				transfer.setTransferType(rule.get_transferType());
				transfer.setDeliveryDate(fee.getFeeDate());
				transfer.setTradeId(trade.getId());
				transfer.setProductId(trade.getProductId());
				transfer.setSettlecurrency(fee.getCurrency());
				transfer.setValueDate(fee.getStartDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				  transfer.setTradeId(trade.getId());
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
			    
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
			    transfer.setProductType(trade.getProductType());
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+mmTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(mmTransferRule.RECEIVE))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
				transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(trade.getTradeAmount())));
				transfer.setEventType("RECEIPT");
				transfer.setTransferType(rule.get_transferType());
				transfer.setDeliveryDate(fee.getFeeDate());
				transfer.setTradeId(trade.getId());
				transfer.setProductId(trade.getProductId());
				transfer.setSettlecurrency(fee.getCurrency());
				transfer.setValueDate(fee.getStartDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setProductType(trade.getProductType());
				
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
				 transfer.addAttribues("FEE_"+fee.getId(), "RECEIVE");
			   
			  
				transfers.addElement(transfer);
				
			}
		}
		return transfers;
	}

	@Override
	public boolean updateTransfer(Trade trade, Product product) {
		
		return false;
	
	}

	@Override
	public void fillTransfer(Transfer transfer) {
			
	}

	
	
	
	

}
