package bo.html;

import java.sql.Connection;
import java.util.Vector;



import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import bo.transfer.rule.ProductTransferRule;

public class HTMLCONFIRMFXFORWARDMessageFormat extends HTMLFXMessageFormat {
	
	public String parseFX_BUY_CURRENCY(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
                Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("BUY")) {
			return trade.getTradedesc().substring(0, 3); 
		}
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return trade.getTradedesc().substring(4, 7); 
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
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return numberToString(trade.getNominal());
		}
	return numberToString(trade.getQuantity());
	
	}	

	public String parseFX_SELL_CURRENCY(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
          
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("BUY")) {
		return trade.getTradedesc().substring(4, 7); 
		} 
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return trade.getTradedesc().substring(0, 3); 
			} 
		return trade.getTradedesc().substring(0, 3); 
	}
	public String parseFX_SELL_AMOUNT(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,           
            Connection con,ProductTransferRule productTransferRule) {
		if(trade.getType().equalsIgnoreCase("BUY")) {
			return numberToString(trade.getNominal());
		}
		if(trade.getType().equalsIgnoreCase("SELL")) {
			return numberToString(trade.getQuantity());
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
	
	   public String parseFWD_BASIS_POINTS(Message message,
	            Trade trade,
	            LeContacts sender,
	            LeContacts rec,
	            Vector transferRules,
	            Transfer transfer,
	           
	            Connection con,ProductTransferRule productTransferRule) {

   if(!(trade.getTradedesc1().equalsIgnoreCase("FXForward")))return null;

  /* double spotrate = ((FXForward)trade.getProduct()).getSpotRateMkt();
   if (trade.getNegociatedPriceType().equals("Divide")) spotrate = 1/spotrate;

   double result = trade.getNegociatedPrice()-spotrate;

   CurrencyPair cp =((FXBased)trade.getProduct()).getCurrencyPair();
   if (cp != null)  result = result * cp.getBpFactor();*/
   double result = trade.getPrice();

	return numberToString(Math.abs(result));
}
	   public static boolean USE_MESSAGE_AMOUNT_FORMAT_B = false;
public String parseFWD_SPOT_RATE(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        
        Connection con,ProductTransferRule productTransferRule) {
	 if(!(trade.getTradedesc1().equalsIgnoreCase("FXForward")))return null;

double spotrate = trade.getPrice();
//if (trade.getNegociatedPriceType().equals("Divide")) spotrate = 1/spotrate;

return numberToString(Math.abs(spotrate));
}

}
