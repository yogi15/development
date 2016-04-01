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

public interface MessageRule {
	public boolean checkValid(Trade newTrade,Transfer transfer,Message message,Vector messageData,RemoteTrade remoteTrade,RemoteBOProcess remoteBo,RemoteReferenceData RemoteRef,Pricer pricer,Connection   con);
	public boolean update(Trade newTrade,Transfer transfer,Message message,Vector messageData,RemoteTrade remoteTrade,RemoteBOProcess remoteBo,RemoteReferenceData RemoteRef,Pricer pricer,Connection   con);


}
