package bo.message.bomessagehandler;


import dsServices.ReferenceDataImp;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import beans.DocumentInfo;
import beans.Message;
import beans.Trade;
import beans.Transfer;

public interface MessageFormat {
	
	
	
	
	public boolean display(Message message,DocumentInfo docInfo,RemoteTrade remoteTrade,RemoteReferenceData remoteRef);
	
	public DocumentInfo generate(Message message, Trade trade, Transfer transfer,
			boolean newDocument, RemoteTrade remoteTrade,RemoteReferenceData remoteRef);
	

}
