package dsManager;


import java.io.InterruptedIOException;
import java.rmi.RemoteException;
import java.util.Vector;

import util.commonUTIL;

import beans.Trade;
import beans.Transfer;
import bo.account.AccountProcessor;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.LiquidationEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.RemoteAccount;


public class AccountManager extends ControllerManager   {
	String managerName = "AccountManager";
	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	RemoteAccount remoteAccount;
	AccountProcessor processor = null;
	 public Thread getManagerThread() {
		return accountManagerThread;
	}
	Thread accountManagerThread = null;
	

	public AccountManager(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		
		try {
			remoteBO = (RemoteBOProcess) getDe().getRMIService("BOProcess");
			remoteTrade = (RemoteTrade) getDe().getRMIService("Trade");
	   		refData = (RemoteReferenceData) getDe().getRMIService("ReferenceData");
	   		remoteAccount = (RemoteAccount) getDe().getRMIService("Account");
	   		Vector<String> reversalAllPostingEvents = new Vector();
	   		reversalAllPostingEvents.add("TRADE_CANCELLED");
	   		reversalAllPostingEvents.add("TRADE_TERMINATED");
	   		processor = new AccountProcessor();
			processor.setRemoteBOProcess(remoteBO);
			processor.setRefData(refData);
			processor.setRemoteTrade(remoteTrade);
			processor.setRemoteAccount(remoteAccount);
			processor.setReversalAllPostingEvents(reversalAllPostingEvents);
		
	   		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated constructor stub
	}

	public void handleEvent(EventProcessor event)	 {
		if(event instanceof TradeEventProcessor) {
			TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
			commonUTIL.display("AccountManager","In Trade " + tradeEvent.getTrade().getId());
			processor.processPosting(event, tradeEvent.getTrade(), null);
			
		}
		if(event instanceof TransferEventProcessor) {
			TransferEventProcessor transferEvent = (TransferEventProcessor) event;
			commonUTIL.display("AccountManager",event.getEventType());
		//	System.out.println();
			commonUTIL.display("AccountManager","Accounting Manager Consuming  transfer event  "  + transferEvent.getEventType() + " for trade " +transferEvent.getTradeID() + " transfer id is " +   transferEvent.getTransfer().getId());
		    Trade trade = null;
			if(transferEvent.getTrade() == null) {
				try {
					trade = (Trade) remoteTrade.selectTrade(event.getTradeID());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				trade = transferEvent.getTrade();
			}
			processor.processPosting(transferEvent,trade, transferEvent.getTransfer());
		}
		
		if(event instanceof LiquidationEventProcessor) {
			LiquidationEventProcessor liquidatedEvent = (LiquidationEventProcessor) event;
			commonUTIL.display("AccountManager",event.getEventType());
		//	System.out.println();
			commonUTIL.display("AccountManager","Accounting Manager Consuming  Liquided event  "  + liquidatedEvent.getEventType() + " for trade " +liquidatedEvent.getTradeID() + " Position id is " +   liquidatedEvent.getPositionID());
		    Trade trade = null;
			if(liquidatedEvent.getTradeID() > 0) {
				try {
					trade = (Trade) remoteTrade.selectTrade(event.getTradeID());
					if(trade == null) {
						commonUTIL.display("AccountManager", "Accounting Manager not Consuming  Liquided event  as trade is null" );
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			processor.processPosting(liquidatedEvent,trade, null);
		}
		
	}
	
	public void stop() {
		try {
			managerThread.interrupt();
			throw new InterruptedException();
		} catch (java.lang.InterruptedException e) {
			// TODO Auto-generated catch block
			commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
		}
		
	}

	public Thread init(AccountManager amanager) {
		
		accountManagerThread = new Thread(amanager);
		return accountManagerThread;
	}
	public static void main(String args[]) {
		AccountManager amanager = new AccountManager("localhost",commonUTIL.getLocalHostName(),"AccountManager");
	    
		amanager.start(amanager);
	}
	
	

}
