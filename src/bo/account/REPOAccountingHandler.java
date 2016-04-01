
package bo.account;

import java.util.Vector;

import productPricing.Pricer;
import dsEventProcessor.EventProcessor;
import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.Trade;

public class REPOAccountingHandler extends AccountingHandler {

	public void getTRADE_AMOUNT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getTOTAL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		BOPosting accEvent = new BOPosting(eventConfig);

		// accEvent.setAmount(pricer.getTotalAmount());
		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getREV_CONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(trade.getDelivertyDate());
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

	public void getCONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		String tradeDate = trade.getTradeDate().substring(0, 10);
		
		BOPosting accEvent = new BOPosting(eventConfig);

		accEvent.setAmount(trade.getTradeAmount());
		accEvent.setEffectiveDate(tradeDate);
		accEvent.setBookingDate(tradeDate);
		accEvent.setCurrency(trade.getCurrency());
		accountingEvents.addElement(accEvent);

	}

}
