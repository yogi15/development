package bo.message.bomessagehandler;

import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import beans.LegalEntity;
import beans.Message;
import beans.MessageConfig;
import beans.Trade;
import beans.Transfer;

public class BOBONDGSECMessageHandler extends BOMessageHandler {
	
	
	public Message fillMessage(Trade trade,Transfer transfer,MessageConfig mConfig,String trrigerON,TransferEventProcessor transferEvent,TradeEventProcessor tradeEvent,LegalEntity receiver,LegalEntity sender) {
		Message message = null;
		return message;
		
	}
	

}
