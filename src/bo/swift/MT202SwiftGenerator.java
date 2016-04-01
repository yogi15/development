package bo.swift;

import java.sql.Connection;
import java.util.Vector;


import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import bo.transfer.rule.ProductTransferRule;


public class MT202SwiftGenerator extends TransferSwiftGenerator {
	
	public String parsePO_DELIVERY_AGENT(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
            String format,
            Connection con,ProductTransferRule productTransferRule) {
		 message = getMessage(message);
	      //  trade = getTrade(message, con);
	      //  transfer = getTransfer(message, con);

	        ValueTag tagValue = SwiftUtil.getPoTagValue(SwiftUtil.AGENT,
	                                                 trade,
	                                                 transfer,
	                                                 message,
	                                                 false,
	                                                 con,productTransferRule);
	        setTagValue(tagValue);
	        return tagValue.getValue();
	    }
		
	}
	
	


