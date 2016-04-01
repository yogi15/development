package bo.swift;


/**
 * SWIFT Formatter for MT210 - Notice to receive
 */

import java.sql.Connection;
import java.util.Vector;

import util.BackOfficeCache;
import util.ReferenceDataCache;


import beans.Account;
import beans.LeContacts;
import beans.Message;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import bo.transfer.rule.ProductTransferRule;


public class MT210SwiftGenerator  extends TransferSwiftGenerator {
	
	public String parseAGENT_ACCOUNT(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
            String format,
            Connection con,ProductTransferRule productTransferRule) {
		Sdi agentSdi = productTransferRule.getAgentSdi();
		if(productTransferRule == null)
			  return EMPTYSTRING;
		Account account = BackOfficeCache.getAccount(agentSdi.getAccountID());
		String accountName =account.getAccountName();
		return accountName;
		 
		
	}
}