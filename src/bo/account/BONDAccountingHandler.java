package bo.account;

import java.rmi.RemoteException;
import java.util.Vector;

import constants.TradeConstants;

import productPricing.BONDPricing;
import productPricing.BONDZCPricing;
import productPricing.Pricer;
import productPricing.pricingUtil.BondCashFlow;
import util.RemoteServiceUtil;
import util.commonUTIL;

import dsEventProcessor.EventProcessor;
import dsEventProcessor.LiquidationEventProcessor;
import dsServices.RemoteProduct;
import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.Coupon;
import beans.Product;
import beans.Trade;

public class BONDAccountingHandler extends AccountingHandler {

	public void getTRADE_AMOUNT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		Product product = null;
		Vector couponV = null;

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			couponV = (Vector) remoteProduct.getCoupon(product.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coupon coupon = (Coupon) couponV.elementAt(0);

		if (coupon.getCouponFrequency().equalsIgnoreCase("ZC"))
			pricer = new BONDZCPricing();
		else
			pricer = new BONDPricing();

		pricer.price(trade, product, coupon);
		BondCashFlow bondCashflow = (BondCashFlow) pricer.genearteCashFlow();

		pricer.setTradeData(bondCashflow);
		
		double tradeAmount = pricer.getCleanPrice();
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			tradeAmount = tradeAmount * -1;
			
			
		}
		
		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate );
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}
	
	public void getREALISED_CLEAN_PL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);
	
		LiquidationEventProcessor liquidationEvent = (LiquidationEventProcessor)event;
		
		accEvent.setAmount(liquidationEvent.getLiquidation().getRealisedPNL());
		accEvent.setEffectiveDate(liquidationDate);
		accEvent.setBookingDate(liquidationDate);
		accEvent.setCurrency(trade.getCurrency());

		accountingEvents.addElement(accEvent);

	}

	public void getPREMIUM(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		double bondPremiumValue = 0.0;

		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		Product product = null;
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// bondPremiumValue = trade.getPrice() - product.getFaceValue();

		bondPremiumValue = ((trade.getPrice() - product.getIssuePrice()) / 100)
				* trade.getTradeAmount();
		
		if (bondPremiumValue > 0.0) {
			
			BOPosting accEvent = new BOPosting(eventConfig);
			
			if (trade.getType().equals(TradeConstants.BUY)) {
				
				bondPremiumValue = bondPremiumValue * -1;
				
			}
			
			accEvent.setAmount(bondPremiumValue);
			accEvent.setEffectiveDate(trade.getDelivertyDate());
			accEvent.setBookingDate(tradeDate);
			accEvent.setCurrency(trade.getCurrency());
			accountingEvents.addElement(accEvent);

		}

	}

	public void getDISCOUNT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		double bondDiscountValue = 0.0;

		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		Product product = null;
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// bondDiscountValue = product.getFaceValue() - trade.getPrice();
		bondDiscountValue = (( trade.getPrice() - product.getIssuePrice()) / 100)
				* trade.getTradeAmount();

		if (bondDiscountValue < 0.0) {

			BOPosting accEvent = new BOPosting(eventConfig);
			
			if (trade.getType().equals(TradeConstants.BUY)) {
				
				bondDiscountValue = bondDiscountValue * -1;
				
			}
			
			accEvent.setAmount(bondDiscountValue);
			accEvent.setEffectiveDate(trade.getDelivertyDate());
			accEvent.setBookingDate(tradeDate);
			accEvent.setCurrency(trade.getCurrency());
			accountingEvents.addElement(accEvent);

		}

	}


	public void getACCRUAL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		Product product = null;
		Vector couponV = null;

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			couponV = (Vector) remoteProduct.getCoupon(product.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coupon coupon = (Coupon) couponV.elementAt(0);

		if (coupon.getCouponFrequency().equalsIgnoreCase("ZC"))
			pricer = new BONDZCPricing();
		else
			pricer = new BONDPricing();

		pricer.price(trade, product, coupon);
		BondCashFlow bondCashflow = (BondCashFlow) pricer.genearteCashFlow();

		pricer.setTradeData(bondCashflow);
		
		double accrualAmount = pricer.getAccural();
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			accrualAmount = accrualAmount * -1;
			
		}
		
		accEvent.setAmount(accrualAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getTOTAL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		Product product = null;
		Vector couponV = null;

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			couponV = (Vector) remoteProduct.getCoupon(product.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coupon coupon = (Coupon) couponV.elementAt(0);

		if (coupon.getCouponFrequency().equalsIgnoreCase("ZC"))
			pricer = new BONDZCPricing();
		else
			pricer = new BONDPricing();

		pricer.price(trade, product, coupon);
		BondCashFlow bondCashflow = (BondCashFlow) pricer.genearteCashFlow();
		
		double totalAmount = pricer.getTotalAmount();
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			totalAmount = totalAmount * -1;
			
			
		}
		
		pricer.setTradeData(bondCashflow);

		accEvent.setAmount(totalAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getFEES_BROKERAGE(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getREV_CONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		double tradeAmount = trade.getTradeAmount();
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getCONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		double tradeAmount = trade.getTradeAmount();
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(tradeDate);
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getNOMINAL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		// accountingEvents.addElement(accEvent);

	}

	public void getNOMINAL_REV(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		BOPosting accEvent = new BOPosting(eventConfig);

		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		// accountingEvents.addElement(accEvent);

	}

	public void getCOUPON(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		Product product = null;
		Vector couponV = null;

		BOPosting accEvent = new BOPosting(eventConfig);
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		RemoteProduct remoteProduct = RemoteServiceUtil
				.getRemoteProductService();

		try {
			product = remoteProduct.selectProduct(trade.getProductId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			couponV = (Vector) remoteProduct.getCoupon(product.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coupon coupon = (Coupon) couponV.elementAt(0);

		if (coupon.getCouponFrequency().equalsIgnoreCase("ZC"))
			pricer = new BONDZCPricing();
		else
			pricer = new BONDPricing();

		pricer.price(trade, product, coupon);
		BondCashFlow bondCashflow = (BondCashFlow) pricer.genearteCashFlow();

		// accEvent.setAmount(pricer.getTotalAmount());
		accEvent.setAmount(pricer.getPrincipal());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}
}
