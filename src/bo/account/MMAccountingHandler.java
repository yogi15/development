package bo.account;

import java.util.Vector;

import constants.CommonConstants;
import constants.TradeConstants;

import productPricing.Pricer;
import dsEventProcessor.EventProcessor;
import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.Trade;

public class MMAccountingHandler extends AccountingHandler {

	public void getTRADE_AMOUNT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		double tradeAmount = trade.getTradeAmount();
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		if (trade.getType().equals(TradeConstants.SELL)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getTOTAL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		double tradeAmount = trade.getTradeAmount();
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		if (trade.getType().equals(TradeConstants.BUY)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		BOPosting accEvent = new BOPosting(eventConfig);

		// accEvent.setAmount(pricer.getTotalAmount());
		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getREV_CONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		double tradeAmount = trade.getTradeAmount();
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		if (trade.getType().equals(TradeConstants.SELL)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getCONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		double tradeAmount = trade.getTradeAmount();
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		if (trade.getType().equals(TradeConstants.SELL)) {
			
			tradeAmount = tradeAmount * -1;
			
		}
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(tradeAmount);
		accEvent.setEffectiveDate(tradeDate);
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

}
