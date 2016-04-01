package bo.transfer;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import dsServices.RemoteBOProcess;
import dsServices.RemoteTrade;
import beans.Flows;

import productPricing.BONDPricing;
import productPricing.BONDZCPricing;
import productPricing.DefaultCashFlow;
import productPricing.Pricer;
import productPricing.pricingUtil.BondCashFlow;
import util.commonUTIL;
import util.common.DateU;
import apps.window.tradewindow.cashflowpanel.CashFlowPanel;
import beans.Coupon;
import beans.Fees;
import beans.NettingConfig;
import beans.Product;
import beans.StartUPData;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.transfer.rule.GenerateBONDTransferRule;

public class GenerateBONDTransfer extends BOTransfer {
    Pricer bondPricer = null;
    String productType = "BOND";
    Product product = null;
    Trade trade = null;
    Coupon coupon = null;
	Vector<Flows>	 flows = new Vector<Flows>();
	//Vector<String>  feesType = null;
	Vector<Fees> fees = null;
	GenerateBONDTransferRule bondTransferRule = null;
	
	
	public GenerateBONDTransfer() {
		bondTransferRule = new GenerateBONDTransferRule();
		bondTransferRule.setRefDate(remoteRef);
		bondTransferRule.setRemoteBOProcess(boProcess);
		bondTransferRule.setRemoteTrade(remoteTrade);
		
	}
	
    private Vector<TransferRule> generateRule(Trade trade, Vector<String> message) {
     
    Vector<TransferRule> rules = null;
        if(!trade.isCustomRuleApply()) {
    	   rules = bondTransferRule.generateRules(trade,message);
        }   else {
    		   try {
				rules = (Vector<TransferRule>) boProcess.getCustomTransferRule(trade.getId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("GenerateBondTransfer", "generateRule get error on Trade id "+trade.getId(), e);
				message.add(new String("GenerateBondTransf generateRule get error on Trade id "+trade.getId()));
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
	
		  if(coupon.getCouponFrequency().equalsIgnoreCase("ZC")) 
			  bondPricer = new BONDZCPricing();
			  else 
				  bondPricer = new BONDPricing();
	        
		  bondPricer.price(trade, product, coupon);  
		 BondCashFlow bondCashflow = (BondCashFlow) bondPricer.genearteCashFlow();
	//	 bondPricer.setp
		 flows =  bondCashflow.getFlows();
		 bondPricer.setTradeData(bondCashflow);
		 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		commonUTIL.displayError("GenearteBONDTransfer", "getCashFlows", e);
		}
	}
		
	

	@Override
	public boolean updateTransfer(Trade trade, Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fillTransfer(Transfer transfer) {
		
		// TODO Auto-generated method stub
		bondPricer.getPrincipal();
		
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
	public Vector<Transfer> generateTransfer(Trade trade,Vector<String> feestype,NettingConfig netConfig,Vector<String> message) {
		// TODO Auto-generated method stub
	//	this.feesType = feestype;
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
			System.out.println(rule.get_productType() + "  " + rule.get_payReceive() + "   "+ rule.get_transferType());
			if((rule.get_transferType().equalsIgnoreCase(bondTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.RECEIVE))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("RECEIPT");
			    transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getCleanPrice())));
			    transfer.setTransferType(bondTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL"+"_"+rule.get_settleDate(), "RECEIVE"); // imp unquinly identified each cashflow and transfer
			    transfer.setDeliveryDate( rule.get_settleDate());
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
				transfer.setQuantity(bondPricer.getQuantity());
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(bondTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.PAY))) {
			    transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(flows.get(rule.get_seqNumber()).getCouponAmount())));
			    transfer.setEventType("PAYMENT");
			    transfer.setTransferType(bondTransferRule.transerTYPEPRINCIPAL);
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
				transfer.setQuantity(bondPricer.getQuantity());
				transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getCleanPrice())));
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(bondTransferRule.transerTYPESECURITY)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.RECEIVE))) {
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getPrincipal())));
				transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getCleanPrice())));
				transfer.setEventType("SEC_RECEIPT");
				transfer.setTransferType(bondTransferRule.transerTYPESECURITY);
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
			    transfer.addAttribues("PRINCIPAL", "RECEIVE");
			    transfer.setQuantity(bondPricer.getQuantity());
			    transfer.setTradeId(trade.getId());
				transfers.addElement(transfer);
				
			} 
			if((rule.get_transferType().equalsIgnoreCase(bondTransferRule.transerTYPESECURITY)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.PAY))) {
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getPrincipal())));
				transfer.setEventType("SEC_DELIVERY");
				transfer.setTransferType(bondTransferRule.transerTYPESECURITY);
				//transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getPrincipal())));
				transfer.setSettleAmount(Double.parseDouble(commonUTIL.doubleFormat(bondPricer.getCleanPrice())));
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
				transfer.setQuantity(bondPricer.getQuantity());
				  transfer.setTradeId(trade.getId());
				  transfer.addAttribues("PRINCIPAL", "PAY");
				transfers.addElement(transfer);
			} if((rule.get_productType().equalsIgnoreCase(productType+bondTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.PAY))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
				transfer.setEventType("PAYMENT");
				transfer.setTransferType(rule.get_transferType());
			//	transfer.setSettleAmount(bondPricer.getCleanPrice());
				transfer.setDeliveryDate(fee.getFeeDate());
				transfer.setTradeId(trade.getId());
				transfer.setProductId(trade.getProductId());
				transfer.setSettlecurrency(fee.getCurrency());
				transfer.setValueDate(fee.getStartDate());
				transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
				transfer.setPayerRole(rule.get_payerLegalEntityRole());
				transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
				transfer.setProductType(trade.getProductType());
				  transfer.setTradeId(trade.getId());
				//  transfer.setQuantity(trade.getQuantity());
				transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
			    
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+bondTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(bondTransferRule.RECEIVE))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(Double.parseDouble(commonUTIL.doubleFormat(fee.getAmount())));
				transfer.setEventType("RECEIPT");
				//transfer.setSettleAmount(bondPricer.getCleanPrice());
				transfer.setTransferType(rule.get_transferType());
				transfer.setDeliveryDate(fee.getFeeDate());
				transfer.setTradeId(trade.getId());
				transfer.setProductId(trade.getProductId());
				transfer.setQuantity(trade.getQuantity());
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

	

}
