package bo.transfer;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import productPricing.Pricer;
import productPricing.REPOPricing;
import productPricing.pricingUtil.RepoCashFlow;
import util.commonUTIL;

import beans.Coupon;
import beans.Fees;
import beans.Flows;
import beans.NettingConfig;
import beans.Product;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;


import bo.transfer.rule.GenerateREPOTransferRule;
import bo.transfer.rule.ProductTransferRule;

public class GenerateREPOTransfer extends BOTransfer {
	
	Pricer repoPricer = null;
	String productType = "REPO";
    Product product = null;
    Trade trade = null;
    Coupon coupon = null;
	Vector<Flows>	 flows = new Vector<Flows>();
	Vector<String>  feesType = null;
	Vector<Fees> fees = null;
	ProductTransferRule repoTransferRule = null;
	
	

	
	public GenerateREPOTransfer() {
		repoTransferRule = new GenerateREPOTransferRule();
		repoTransferRule.setRefDate(remoteRef);
		repoTransferRule.setRemoteBOProcess(boProcess);
		repoTransferRule.setRemoteTrade(remoteTrade);
		repoTransferRule.setRemoteProduct(remoteProduct);
		
	}
	
	
	private Vector<TransferRule> generateRule(Trade trade) {
	    
    	return repoTransferRule.generateRules(trade);
    	
    	
    }
	private void getCashFlows(Trade trade) {
		// TODO Auto-generated method stub
		try {
		product =	(Product) remoteProduct.selectProduct(trade.getProductId());
		Vector couponV = (Vector) remoteProduct.getCoupon(product.getId());
		Coupon coupon = (Coupon) couponV.elementAt(0);
		repoPricer = new REPOPricing();
		repoPricer.price(trade, product, coupon);
         RepoCashFlow repoCashFlow = (RepoCashFlow) repoPricer.genearteCashFlow();
         repoPricer.setTradeData(repoCashFlow);
         flows =  repoCashFlow.getFlows();
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
			Vector<String> feestype,NettingConfig netConfig) {
		// TODO Auto-generated method stub
		
		this.feesType = feestype;
		Vector<Transfer> transfers = new Vector<Transfer>();
		Vector<TransferRule> rules = generateRule(trade);
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
			if((rule.get_transferType().equalsIgnoreCase(repoTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.RECEIVE))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(repoTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL"+"_"+rule.get_settleDate(), "RECEIVE"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate(commonUTIL.dateToString(rule.get_settleDate().getDate()));
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
			if((rule.get_transferType().equalsIgnoreCase(repoTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.PAY))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("PAYMENT");
			    transfer.setTransferType(repoTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL"+"_"+rule.get_settleDate(), "PAYMENT"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate(commonUTIL.dateToString(rule.get_settleDate().getDate()));
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
			if((rule.get_transferType().equalsIgnoreCase(repoTransferRule.transerTYPEINTEREST)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.PAY))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("PAYMENT");
			    transfer.setTransferType(repoTransferRule.transerTYPEINTEREST);
			    transfer.addAttribues(repoTransferRule.transerTYPEINTEREST+"_"+rule.get_settleDate(), "PAYMENT"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate(commonUTIL.dateToString(rule.get_settleDate().getDate()));
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
			if((rule.get_transferType().equalsIgnoreCase(repoTransferRule.transerTYPEINTEREST)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.RECEIVE))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(repoTransferRule.transerTYPEINTEREST);
			    transfer.addAttribues(repoTransferRule.transerTYPEINTEREST+"_"+rule.get_settleDate(), "RECEIPT");  // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate(commonUTIL.dateToString(rule.get_settleDate().getDate()));
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
				
			}if((rule.get_productType().equalsIgnoreCase(productType+repoTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.PAY))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
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
				
			}if((rule.get_productType().equalsIgnoreCase(productType+repoTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(repoTransferRule.RECEIVE))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fillTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	

}
