package bo.account;

import java.util.Collections;
import java.util.Vector;

import constants.FXConstants;
import constants.TradeConstants;

import productPricing.Pricer;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.LiquidationEventProcessor;
import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.Trade;

public class FXAccountingHandler extends AccountingHandler {

	private Vector<String> splitCurrency(String splitCurrency) {

		Vector<String> currency = new Vector<String>();

		String[] currencyArr = splitCurrency.split("/");

		Collections.addAll(currency, currencyArr);

		return currency;
	}

	public void getCONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		String fxType = trade.getTradedesc1();

		if ( !fxType.equals(FXConstants.FXTAKEUP) ) {
			
			Vector<String> splittedCurrency = splitCurrency(trade
					.getTradedesc());
			
			String tradeDate = trade.getTradeDate().substring(0, 10);
		
			BOPosting accEventBuy = new BOPosting(eventConfig);

			accEventBuy.setAmount(trade.getQuantity());
			accEventBuy.setEffectiveDate(tradeDate);
			accEventBuy.setBookingDate(tradeDate);
			accEventBuy.setCurrency(splittedCurrency.get(0));
			accountingEvents.addElement(accEventBuy);

			BOPosting accEventSell = new BOPosting(eventConfig);

			accEventSell.setAmount(trade.getNominal());
			accEventSell.setEffectiveDate(tradeDate);
			accEventSell.setBookingDate(tradeDate);
			accEventSell.setCurrency(splittedCurrency.get(1));
			accountingEvents.addElement(accEventSell);
			
			if ( fxType.equals(FXConstants.FXSWAP) ) {
				
				BOPosting accEventFarLegAmt1 = new BOPosting(eventConfig);

				accEventFarLegAmt1.setAmount(trade.getQuantity());
				accEventFarLegAmt1.setEffectiveDate(tradeDate);
				accEventFarLegAmt1.setBookingDate(tradeDate);
				accEventFarLegAmt1.setCurrency(splittedCurrency.get(0));
				accountingEvents.addElement(accEventFarLegAmt1);

				BOPosting accEventFarLegAmt2= new BOPosting(eventConfig);

				accEventFarLegAmt2.setAmount(trade.getNominal());
				accEventFarLegAmt2.setEffectiveDate(tradeDate);
				accEventFarLegAmt2.setBookingDate(tradeDate);
				accEventFarLegAmt2.setCurrency(splittedCurrency.get(1));
				accountingEvents.addElement(accEventFarLegAmt2);
				
			}
		}
	}

	public void getREV_CONT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {

		String fxType = trade.getTradedesc1();
		if (fxType.equals(FXConstants.FXFORWARD)
				|| fxType.equals(FXConstants.FXSWAP)
				|| fxType.equals(FXConstants.FXTAKEUP)) {

			Vector<String> splittedCurrency = splitCurrency(trade
					.getTradedesc());
			
			String tradeDate = trade.getTradeDate().substring(0, 10);
			String deliveryDate = trade.getDelivertyDate().substring(0, 10);
			
			BOPosting accEvent = new BOPosting(eventConfig);

			accEvent.setAmount(trade.getQuantity());
			accEvent.setEffectiveDate(deliveryDate);
			accEvent.setBookingDate(tradeDate);
			accEvent.setCurrency(splittedCurrency.get(0));
			accountingEvents.addElement(accEvent);
			BOPosting accEventSell = new BOPosting(eventConfig);

			accEventSell.setAmount(trade.getNominal());
			accEventSell.setEffectiveDate(deliveryDate);
			accEventSell.setBookingDate(tradeDate);
			accEventSell.setCurrency(splittedCurrency.get(1));
			accountingEvents.addElement(accEventSell);
			
			if ( fxType.equals(FXConstants.FXSWAP ) ) {
				
				// for far leg end date is saved in effective date and not in 
				// settlement date
				String effectiveDate = trade.getEffectiveDate();
				
				BOPosting accEventFarLegAmt1 = new BOPosting(eventConfig);

				accEventFarLegAmt1.setAmount(trade.getQuantity());
				accEventFarLegAmt1.setEffectiveDate(effectiveDate);
				accEventFarLegAmt1.setBookingDate(tradeDate);
				accEventFarLegAmt1.setCurrency(splittedCurrency.get(0));
				accountingEvents.addElement(accEventFarLegAmt1);

				BOPosting accEventFarLegAmt2= new BOPosting(eventConfig);

				accEventFarLegAmt2.setAmount(trade.getNominal());
				accEventFarLegAmt2.setEffectiveDate(effectiveDate);
				accEventFarLegAmt2.setBookingDate(tradeDate);
				accEventFarLegAmt2.setCurrency(splittedCurrency.get(1));
				accountingEvents.addElement(accEventFarLegAmt2);
				
			}

		}

	}

	public void getTRADE_AMOUNT(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		String fxType = trade.getTradedesc1();
		
		if ( !fxType.equals(FXConstants.FXFORWARDOPTION) ) {
			
			Vector<String> splittedCurrency = splitCurrency(trade
					.getTradedesc());
			
			String tradeDate = trade.getTradeDate().substring(0, 10);
			
			BOPosting accEvent = new BOPosting(eventConfig);

			accEvent.setAmount(trade.getQuantity());
			accEvent.setEffectiveDate(tradeDate);
			accEvent.setBookingDate(tradeDate);
			accEvent.setCurrency(splittedCurrency.get(0));
			accountingEvents.addElement(accEvent);
			
			BOPosting accEventSell = new BOPosting(eventConfig);

			accEventSell.setAmount(trade.getNominal());
			accEventSell.setEffectiveDate(tradeDate);
			accEventSell.setBookingDate(tradeDate);
			accEventSell.setCurrency(splittedCurrency.get(1));
			accountingEvents.addElement(accEventSell);
			
			if ( fxType.equals(FXConstants.FXSWAP ) ) {
				
				// for far leg end date is saved in effective date and not in 
				// settlement date
				String effectiveDate = trade.getEffectiveDate();
				
				BOPosting accEventFarLegAmt1 = new BOPosting(eventConfig);

				accEventFarLegAmt1.setAmount(trade.getQuantity());
				accEventFarLegAmt1.setEffectiveDate(effectiveDate);
				accEventFarLegAmt1.setBookingDate(tradeDate);
				accEventFarLegAmt1.setCurrency(splittedCurrency.get(0));
				accountingEvents.addElement(accEventFarLegAmt1);

				BOPosting accEventFarLegAmt2= new BOPosting(eventConfig);

				accEventFarLegAmt2.setAmount(trade.getNominal());
				accEventFarLegAmt2.setEffectiveDate(effectiveDate);
				accEventFarLegAmt2.setBookingDate(tradeDate);
				accEventFarLegAmt2.setCurrency(splittedCurrency.get(1));
				accountingEvents.addElement(accEventFarLegAmt2);
				
			}
			
		}
		
	}

	public void getREALISED_CLEAN_PL(Trade trade, EventProcessor event,
			AccEventConfig eventConfig, Vector accountingEvents,
			AccConfigRule rule, Pricer pricer) {
		
		if ( !trade.getTradedesc1().equals(FXConstants.FXTAKEUP) ) {
			
			BOPosting accEvent = new BOPosting(eventConfig);
			
			LiquidationEventProcessor liquidationEvent = (LiquidationEventProcessor)event;
			
			String liquidationDate = liquidationEvent.getLiquidation().getLidDate().substring(0, 10);
			
			accEvent.setAmount(liquidationEvent.getLiquidation().getRealisedPNL());
			accEvent.setEffectiveDate(liquidationDate);
			accEvent.setBookingDate(liquidationDate);
			accEvent.setCurrency(trade.getCurrency());

			accountingEvents.addElement(accEvent);


		}
	}		
}
