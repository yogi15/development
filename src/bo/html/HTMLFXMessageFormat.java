package bo.html;

import java.sql.Connection;
import java.util.Vector;

import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import bo.html.Formatter.HTMLFormatter;
import bo.transfer.rule.ProductTransferRule;


public class HTMLFXMessageFormat extends HTMLFormatter {
	public String parseFX_BUY_CURRENCY(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
          
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("BUY")) {
			return trade.getCurrency();
		} 
		return trade.getTradedesc().substring(4, 7);
	
	}
	public String parseFX_BUY_AMOUNT(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
           
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("BUY")) {
			return numberToString(trade.getQuantity());
		} 
	return numberToString(trade.getNominal());
	
	}	

	public String parseFX_SELL_CURRENCY(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
           
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return trade.getCurrency();
		} 
		return trade.getTradedesc().substring(4, 7);
	
	}
	public String parseFX_SELL_AMOUNT(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
           
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return numberToString(trade.getNominal());
		} 
	return numberToString(trade.getQuantity());
	
	}
	 public String parseFX_NEGOCIATEDPRICE(Message message,
	            Trade trade,
	            LeContacts sender,
	            LeContacts rec,
	            Vector transferRules,
	            Transfer transfer,
	           
	            Connection con,ProductTransferRule productTransferRule) {
		 return numberToString(trade.getPrice());
	 }

}
