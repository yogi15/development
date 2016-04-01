package wfManager.messageRules;

import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import wfManager.MessageRule;

public class validupdateFormatTypeTOSWIFTMessageRule implements MessageRule {

	@Override
	public boolean checkValid(Trade newTrade, Transfer transfer,
			Message message, Vector messageData, RemoteTrade remoteTrade,
			RemoteBOProcess remoteBo, RemoteReferenceData RemoteRef,
			Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean update(Trade newTrade, Transfer transfer, Message message,
			Vector messageData, RemoteTrade remoteTrade,
			RemoteBOProcess remoteBo, RemoteReferenceData RemoteRef,
			Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		
		if(message !=null) {
			
			//	Message newmessage = (Message) message.clone();
				message.setFormat("SWIFT");
			
			
		}
		
		return true;
	}

}
