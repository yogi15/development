package bo.transfer;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import productPricing.Pricer;
import util.commonUTIL;

import beans.Coupon;
import beans.Fees;
import beans.Flows;
import beans.NettingConfig;
import beans.Product;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.transfer.rule.GenerateBONDTransferRule;
import bo.transfer.rule.GenerateFXTransferRule;
import bo.transfer.rule.GenerateMMTransferRule;

public class GenerateFXTransfer extends BOTransfer {
	
	Pricer bondPricer = null;
    String productType = "FX";
    Product product = null;
    Trade trade = null;
    Coupon coupon = null;
	Vector<Flows>	 flows = new Vector<Flows>();
	Vector<String>  feesType = null;
	Vector<Fees> fees = null;
	GenerateFXTransferRule fxTransferRule = null;
	
	public GenerateFXTransfer() {
		fxTransferRule = new GenerateFXTransferRule();
		fxTransferRule.setRefDate(remoteRef);
		fxTransferRule.setRemoteBOProcess(boProcess);
		fxTransferRule.setRemoteTrade(remoteTrade);
		fxTransferRule.setRemoteProduct(remoteProduct);
		
	}
	
private Vector<TransferRule> generateRule(Trade trade,Vector<String> message) {
	 Vector<TransferRule> rules = null;
	 
     if(!trade.isCustomRuleApply()) {
 	   rules =fxTransferRule.generateRules(trade,message);
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
		Vector<TransferRule> rules = null;
		if(trade.isFXSwap()) {
			return generateTransferOnFXSwap(trade,feestype,netConfig,message);
		}
		this.feesType = feestype;
		Vector<Transfer> transfers = new Vector<Transfer>();
		 
		rules = generateRule(trade,message);
		if(commonUTIL.isEmpty(rules))
			return null;
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
			//Sdi sd = rule.get__sMethod();
			transfer.setReceiverInst(getLEName(rule.get_receiverAgentID()));

			transfer.setPayerInst(getLEName(rule.get_payerAgentID()));
			if((rule.get_transferType().equalsIgnoreCase(fxTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.RECEIVE))) {
				if(trade.getQuantity() > .0)
				   transfer.setAmount(trade.getQuantity());
				else 
					transfer.setAmount(trade.getNominal());
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(fxTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL", "RECEIVE");
			    transfer.setDeliveryDate( rule.get_settleDate()  );
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
				transfer.setMethod(rule.get__sMethod().getMessageType());
				transfer.setSettleAmount(transfer.getAmount());
				transfer.addAttribues("PRINCIPAL", "RECEIVE");
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(fxTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.PAY))) {
				
				if(trade.getQuantity() < .0)
					   transfer.setAmount(trade.getQuantity());
					else 
						transfer.setAmount(trade.getNominal());
				    transfer.setEventType("PAYMENT");
				    transfer.setTransferType(fxTransferRule.transerTYPEPRINCIPAL);
				    transfer.addAttribues("PRINCIPAL", "PAYMENT");
				    transfer.setDeliveryDate( rule.get_settleDate()  );
						transfer.setTradeId(trade.getId());
					if(rule.get_productId() == 0) 
						transfer.setProductId(trade.getProductId());
					else
					transfer.setProductId(rule.get_productId());
					transfer.setSettlecurrency(rule.get_settlementCurrency());
					transfer.setValueDate(trade.getEffectiveDate());
					//transfer.setPayerInst(getLEName(rule.get_payerAgentID()));
					transfer.setPayerCode(getLEName(rule.get_payerLegalEntityId()));
					transfer.setPayerRole(rule.get_payerLegalEntityRole());
					transfer.setReceiverCode(getLEName(rule.get_receiverLegalEntityId()));
					transfer.setReceiverRole(rule.get_receiverLegalEntityRole());
					transfer.setMethod(rule.get_settlementMethod());
					transfer.addAttribues("PRINCIPAL", "PAY");
					transfer.setProductType(trade.getProductType());
					transfer.setSettleAmount(transfer.getAmount());
					transfers.addElement(transfer);
			
			}if((rule.get_productType().equalsIgnoreCase(productType+fxTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.PAY))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(fee.getAmount());
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
				transfer.setMethod(rule.get_settlementMethod());
				transfer.setProductType(trade.getProductType());
				transfer.setSettleAmount(transfer.getAmount());
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+fxTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.RECEIVE))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(fee.getAmount());
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
				transfer.setMethod(rule.get_settlementMethod());
				 transfer.addAttribues("FEE_"+fee.getId(), "RECEIVE");
				 transfer.setSettleAmount(transfer.getAmount());
			  
				transfers.addElement(transfer);
				
			}
			
		}
		return transfers;
	}

	private Vector<Transfer> generateTransferOnFXSwap(Trade swaptrade,
			Vector<String> feestype, NettingConfig netConfig,Vector<String> message) {
		// TODO Auto-generated method stub
		this.feesType = feestype;
		Vector<Transfer> transfers = new Vector<Transfer>();
		Vector<TransferRule> rules = generateRule(swaptrade,message);
		try {
			fees = (Vector)  remoteTrade.selectFeesonTrade(swaptrade.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		                
		getCashFlows(swaptrade);
		Iterator<TransferRule> rulesI = rules.iterator();
		while(rulesI.hasNext()) {
			Trade trade = null;
			Transfer transfer = new Transfer();
			TransferRule rule = rulesI.next();
		    transfer.setTradeVersionID(swaptrade.getVersion());
		  if(rule.getFxSwapType().equalsIgnoreCase("SWAPLEG"))
			             trade = swaptrade.getSwapLeg();
		  else 
			              trade = swaptrade.getSwapPrimaryLeg();
			transfer.setBookId(rule.getBookId());
			if((rule.get_transferType().equalsIgnoreCase(fxTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.RECEIVE))) {
				if(trade.getQuantity() > .0)
				   transfer.setAmount(trade.getQuantity());
				else 
					transfer.setAmount(trade.getNominal());
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(fxTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL", "RECEIVE");
			    transfer.setDeliveryDate( rule.get_settleDate()  );
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
				transfer.setMethod(rule.get_settlementMethod());
				transfer.setSettleAmount(transfer.getAmount());
				transfer.addAttribues("PRINCIPAL", "RECEIVE");
				if(swaptrade.getRollOverFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPLEG")) {
					transfer.addAttribues("FARLEG", "NEW");
				}
				if(swaptrade.getRollBackFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPLEG")) {
					transfer.addAttribues("FARLEG", "CANCELLED");
				}
				if(swaptrade.getRollOverFrom() > 0  && trade.getFxSwapLeg().equalsIgnoreCase("SWAPPRIMARYLEG")) {
					transfer.addAttribues("NEARLEG", "CANCELLED");
				}
				if(swaptrade.getRollBackFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPPRIMARYLEG")) {
					transfer.addAttribues("NEAREG", "NEW");
				}
				transfers.addElement(transfer);
				
			}
			if((rule.get_transferType().equalsIgnoreCase(fxTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.PAY))) {
				if(trade.getQuantity() < .0)
					   transfer.setAmount(trade.getQuantity());
					else 
						transfer.setAmount(trade.getNominal());
				    transfer.setEventType("PAYMENT");
				    transfer.setTransferType(fxTransferRule.transerTYPEPRINCIPAL);
				    transfer.addAttribues("PRINCIPAL", "PAYMENT");
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
					transfer.setMethod(rule.get_settlementMethod());
					transfer.addAttribues("PRINCIPAL", "PAY");
					transfer.setSettleAmount(transfer.getAmount());
					transfer.setProductType(trade.getProductType());
					if(swaptrade.getRollOverFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPLEG")) {
						transfer.addAttribues("FARLEG", "NEW");
					}
					if(swaptrade.getRollBackFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPLEG")) {
						transfer.addAttribues("FARLEG", "CANCELLED");
					}
					if(swaptrade.getRollOverFrom() > 0  && trade.getFxSwapLeg().equalsIgnoreCase("SWAPPRIMARYLEG")) {
						transfer.addAttribues("NEARLEG", "CANCELLED");
					}
					if(swaptrade.getRollBackFrom() > 0 && trade.getFxSwapLeg().equalsIgnoreCase("SWAPPRIMARYLEG")) {
						transfer.addAttribues("NEAREG", "NEW");
					}
					transfers.addElement(transfer);
			
			}if((rule.get_productType().equalsIgnoreCase(productType+fxTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.PAY))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(fee.getAmount());
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
				transfer.setProductType(trade.getProductType());
				transfer.setMethod(rule.get_settlementMethod());
				transfer.setSettleAmount(transfer.getAmount());
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+fxTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(fxTransferRule.RECEIVE))) {
				Fees fee = getFee(rule.getFeeId()); 
				transfer.setAmount(fee.getAmount());
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
				 transfer.setMethod(rule.get_settlementMethod());
				 transfer.setSettleAmount(transfer.getAmount());
			  
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
