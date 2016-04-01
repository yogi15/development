package bo.message.bomessagehandler;

import java.util.Hashtable;

import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;


import util.ClassInstantiateUtil;
import util.commonUTIL;

import beans.LegalEntity;
import beans.Message;
import beans.MessageConfig;
import beans.Trade;
import beans.Transfer;

public class BOMessageHandler {
	
	
     static Hashtable<String,BOMessageHandler> boHandlers = new  Hashtable<String,BOMessageHandler>();
  
	
	public static BOMessageHandler getBOHandler(String productType,String productSubType) {
		BOMessageHandler boHandler = null;
		
		String key = productType.toUpperCase()+productSubType.toUpperCase();
		if(productType.equalsIgnoreCase("FX")) {
			key = productSubType.toUpperCase();
		}
		synchronized (boHandlers) {
			boHandler = boHandlers.get(key);
			
		}
		if(boHandler == null) {
			boHandler =	getBOHandler(key);
			if(boHandler != null)
				boHandlers.put(key, boHandler);
		}
		return boHandler;
		
	}
	
	
	
	
	
	
	private static BOMessageHandler getBOHandler(String key) {
		// TODO Auto-generated method stub		
		BOMessageHandler instance = null;


        String classname = "bo.message.bomessagehandler.BO" + key + "MessageHandler";
        try {
          
               Class class1 = ClassInstantiateUtil.getClass(classname,
    					true);
               instance =  (BOMessageHandler) class1.newInstance();
        }
        catch(Exception e) {
        	commonUTIL.displayError("BOMessageHandler", "getBOHandler + Missing handler for Product  W" + classname , e);
        }
        
        return instance;
    
	}




	public Message fillMessage(Trade trade,Transfer transfer,MessageConfig mConfig,String trrigerON,TransferEventProcessor transferEvent,TradeEventProcessor tradeEvent,LegalEntity receiver,LegalEntity sender) {
		Message message = null;
		return message;
		
	}
	
   
}
