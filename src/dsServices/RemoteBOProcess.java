package dsServices;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.Collection;
import java.util.Vector;

import beans.DocumentInfo;
import beans.Message;
import beans.NettingConfig;
import beans.Posting;
import beans.Trade;
import beans.Transfer;
import beans.Users;
import bo.swift.bic.BICSwiftData;

public interface RemoteBOProcess  extends Remote {
	
	public Collection   getSwiftBICData(BICSwiftData query) throws RemoteException;
	public Collection  getCustomTransferRule(int tradeID)  throws RemoteException;
	public int saveTransfer(Transfer transfer) throws RemoteException;
	public void publishnewTransfer(String messageIndicator,String messageType,Object object) throws RemoteException;
	public void removeTransfer(Transfer transfer) throws RemoteException; 
	public void updateTransfer(Transfer transfer) throws RemoteException;
	public Transfer selectTransfer(Transfer transfer) throws RemoteException;
	public Collection getTransferOnTrade(int tradeID)  throws RemoteException;
	public Collection getTransferOnTradeWithNoCancelStatus(int tradeID)   throws RemoteException;
	
	
	public void removeMessage(Message message) throws RemoteException; 
	public void updateMessage(Message message) throws RemoteException;
	public void saveMessage(Message message) throws RemoteException;
	public Vector<Message> saveMesage(Vector<Message> mess,String sqlType)  throws RemoteException;
	public Vector<Message> getMessagesOnWhere(String where) throws RemoteException;
	public Vector<Message> getMessages(int tradeID,String eventType,String triggerON) throws RemoteException;
	public Vector<Message> getMessages(int messageConfigid,int tradeID,String eventType,String triggerON) throws RemoteException;
	public Message selectMessage(Message message) throws RemoteException;
	public Collection getMessageOnTrade(int tradeID)  throws RemoteException;
	public Collection getMessageOnTransfer(int transferID)  throws RemoteException;
	public Transfer getTransfer(int transferID) throws RemoteException;
	public Collection getOLDMessageForCancel(String messageEventtype,String messageformattype,int tradeid,String messageType,String triggeron) throws RemoteException;
	
	public Collection saveTransfers(Vector<Transfer> transfers,String type,String tradeAction,Trade trade,int userID) throws RemoteException;
	public Collection saveTransfers(Vector<Transfer> transfers,String type,String tradeAction,NettingConfig netConfig,Trade trade) throws RemoteException;
	public Collection getTransfers(Trade trade,boolean cancelTrue) throws RemoteException;
	public Collection queryWhere(String boObjectName,String where) throws RemoteException;
	
	public int savePosting(Posting Posting) throws RemoteException;
	public void removePosting(Posting Posting) throws RemoteException; 
	public void updatePosting(Posting Posting) throws RemoteException;
	public Posting selectPosting(Posting Posting) throws RemoteException;
	
	Collection getAction(Transfer transfer) throws RemoteException;
	public Collection getOnlyAction(Transfer transfer) throws RemoteException;
	public Collection updateTransferAndPublish(Transfer transfer,int userID) throws RemoteException;
	public Collection updateMessageAndPublish(Message message,int userID) throws RemoteException;
	public Transfer getNettingTransfer(int id, String deliveryDate) throws RemoteException;
	public Collection getNettedTransfers(NettingConfig netConfig) throws RemoteException;
	public Collection getNettedTransfers(int nettingTransferID) throws RemoteException;
	public Collection getOnlyAction(int transferID) throws RemoteException;
	public Collection getMessage(int id, String eventType, String triggerON) 
			throws RemoteException;
	public  Vector<Message> saveMesage(Vector<Message> mess, String sqlType,
			Trade trade, Transfer transfer) throws RemoteException;
	public Collection getOnlyAction(Message message) throws RemoteException;

	public Collection getSwiftBICData(String sql) throws RemoteException;

	public DocumentInfo getLatestAdviceDocument(int id, Object object) throws RemoteException;

	public Message selectMessageOnLinkid(int linkId) throws RemoteException;
	
	
	
	

}
