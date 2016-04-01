package wfManager.tradeRules;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import productPricing.Pricer;
import beans.Trade;
import dsServices.RemoteTrade;
import wfManager.TradeRule;

public class validRollActionTradeRule implements TradeRule {


	@Override
	public boolean checkValid(Trade newTrade, Trade oldTrade,
			Vector messageData, RemoteTrade remoteTrade, Pricer pricer,
			Connection con) {
		// TODO Auto-generated method stub
		String status = newTrade.getStatus().trim();
		if( (status.equalsIgnoreCase("ROLLOVERED")) || (status.equalsIgnoreCase("ROLLBACKED"))) {
			
		String attributes = newTrade.getAttributes();
		if(status.equalsIgnoreCase("ROLLOVERED")) {
		
			if(!attributes.contains("rollOverTO="))  {
				messageData.add(new String("Trade missing RollOverTO Attribute"));
				return false;
			}
			
		}
		if(status.equalsIgnoreCase("ROLLBACKED")) {
			
			if(!attributes.contains("rollBackTo="))  {
				messageData.add(new String("Trade missing RollBackTO Attribute"));
				return false;
			}
			
		} 
			
		} else {
			messageData.add(new String("Trade Must be in ROLLBACKED or ROLLOVERED status "));
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Trade newTrade, Trade oldTrade, Vector messageData,
			RemoteTrade remoteTrade, Pricer pricer, Connection con) {
		
		String status = newTrade.getStatus();
		Hashtable<String, String> attributes = newTrade.getAttributeH();
	
		int tradeID = 0;
		try {
		if( (status.equalsIgnoreCase("ROLLOVERED"))) {
			Enumeration<String> enums = attributes.keys();
			while(enums.hasMoreElements()) {
				String key = enums.nextElement();
				String value = attributes.get(key);
				
				if(key.equalsIgnoreCase("rollOverTO")) {
					tradeID = Integer.valueOf(value);
					break;
				}
				
			}
		}
		if( (status.equalsIgnoreCase("ROLLBACKED"))) {
			Enumeration<String> enums = attributes.keys();
			while(enums.hasMoreElements()) {
				String key = enums.nextElement();
				String value = attributes.get(key);
				if(key.equalsIgnoreCase("rollBackTo")) {
					tradeID = Integer.valueOf(value);
					break;
				}
				
			}
		}
		
		
		
		Trade rollTrade = 	remoteTrade.selectTrade(tradeID);
		if( (status.equalsIgnoreCase("ROLLOVERED"))) {
			Hashtable<String,String> attribhr = rollTrade.getAttributeH();
			attribhr.put("rollOverFrom","0");
			rollTrade.setAttributes(attribhr);
			Hashtable<String,String>  attribh = newTrade.getAttributeH();
			attribh.put("rollOverTO","0");
			newTrade.setAttributes(attribh);
		   
		} else {
			Hashtable<String,String> attribh = rollTrade.getAttributeH();
			attribh.put("rollBackFrom","0");
			rollTrade.setAttributes(attribh);
			attribh = newTrade.getAttributeH();
			attribh.put("rollBackTo","0");
			newTrade.setAttributes(attribh);
			 
		}
		rollTrade.setAction("CANCEL");
		remoteTrade.saveTrade(rollTrade);
		newTrade.setAction("ROLL");
		remoteTrade.saveTrade(newTrade,messageData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			messageData.add(new String("validRollActionTradeRule" +e) );
		}catch (Exception e) {
			// TODO Auto-generated catch block
			messageData.add(new String("validRollActionTradeRule " +e));
		}
		return false;
	}

}
