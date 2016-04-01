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
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.transfer.rule.GenerateFUTURETransferRule;

public class GenerateFUTURECONTRACTTransfer  extends BOTransfer {
	
	Pricer bondPricer = null;
    String productType = "FX";
    Product product = null;
    Trade trade = null;
    Coupon coupon = null;
	Vector<Flows>	 flows = new Vector<Flows>();
	Vector<String>  feesType = null;
	Vector<Fees> fees = null;
	GenerateFUTURETransferRule futureTransferRule = null;
	
	public GenerateFUTURECONTRACTTransfer() {
		futureTransferRule = new GenerateFUTURETransferRule();
		futureTransferRule.setRefDate(remoteRef);
		futureTransferRule.setRemoteBOProcess(boProcess);
		futureTransferRule.setRemoteTrade(remoteTrade);
		futureTransferRule.setRemoteProduct(remoteProduct);
		
	}
	
private Vector<TransferRule> generateRule(Trade trade) {
	    
    	return futureTransferRule.generateRules(trade);
    	
    	
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
			Vector<String> feestype,NettingConfig netConfig) {
		// TODO Auto-generated method stub
		if(trade.isFXSwap()) {
			return generateTransferOnFXSwap(trade,feestype,netConfig);
		}
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
			if((rule.get_transferType().equalsIgnoreCase(futureTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.RECEIVE))) {
				if(trade.getQuantity() > .0)
				   transfer.setAmount(trade.getQuantity());
				else 
					transfer.setAmount(trade.getNominal());
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(futureTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL", "RECEIVE");
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
				transfer.addAttribues("PRINCIPAL", "RECEIVE");
				transfer.setQuantity(trade.getQuantity());
				transfers.addElement(transfer);
			}
			if((rule.get_transferType().equalsIgnoreCase(futureTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.PAY))) {
				if(trade.getQuantity() < .0)
					   transfer.setAmount(trade.getQuantity());
					else 
						transfer.setAmount(trade.getNominal());
				    transfer.setEventType("PAYMENT");
				    transfer.setTransferType(futureTransferRule.transerTYPEPRINCIPAL);
				    transfer.addAttribues("PRINCIPAL", "PAYMENT");
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
					transfer.addAttribues("PRINCIPAL", "PAY");
					transfer.setProductType(trade.getProductType());
					transfer.setQuantity(trade.getQuantity());
					transfers.addElement(transfer);
			
			}if((rule.get_productType().equalsIgnoreCase(productType+futureTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.PAY))) {
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
			    
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+futureTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.RECEIVE))) {
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
			   
			  
				transfers.addElement(transfer);
				
			}
			
		}
		return transfers;
	}

	private Vector<Transfer> generateTransferOnFXSwap(Trade swaptrade,
			Vector<String> feestype, NettingConfig netConfig) {
		// TODO Auto-generated method stub
		this.feesType = feestype;
		Vector<Transfer> transfers = new Vector<Transfer>();
		Vector<TransferRule> rules = generateRule(swaptrade);
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
			if((rule.get_transferType().equalsIgnoreCase(futureTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.RECEIVE))) {
				if(trade.getQuantity() > .0)
				   transfer.setAmount(trade.getQuantity());
				else 
					transfer.setAmount(trade.getNominal());
			    transfer.setEventType("RECEIPT");
			    transfer.setTransferType(futureTransferRule.transerTYPEPRINCIPAL);
			    transfer.addAttribues("PRINCIPAL", "RECEIVE");
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
			if((rule.get_transferType().equalsIgnoreCase(futureTransferRule.transerTYPEPRINCIPAL)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.PAY))) {
				if(trade.getQuantity() < .0)
					   transfer.setAmount(trade.getQuantity());
					else 
						transfer.setAmount(trade.getNominal());
				    transfer.setEventType("PAYMENT");
				    transfer.setTransferType(futureTransferRule.transerTYPEPRINCIPAL);
				    transfer.addAttribues("PRINCIPAL", "PAYMENT");
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
					transfer.addAttribues("PRINCIPAL", "PAY");
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
			
			}if((rule.get_productType().equalsIgnoreCase(productType+futureTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.PAY))) {
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
			    
			    transfer.addAttribues("FEE_"+fee.getId(), "PAY");
				transfers.addElement(transfer);
				
			}if((rule.get_productType().equalsIgnoreCase(productType+futureTransferRule.transerTYPEFEES)) && (rule.get_payReceive().equalsIgnoreCase(futureTransferRule.RECEIVE))) {
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
