package bo.html.Formatter.Util;

import java.util.Vector;

import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;

import dsServices.RemoteTrade;



public interface FunctionFormat {
	 public Object call(RemoteTrade remoteTrade,
             Message message,
             Transfer transfer,
             Trade trade,
             LeContacts sender,
             LeContacts receiver,
             Vector args);

}
