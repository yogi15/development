package wfManager.transferRules;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import beans.Trade;
import beans.Transfer;
import dsServices.RemoteBOProcess;
import dsServices.RemoteTrade;
import wfManager.TransferRule;

public class validOutStandingOnFWDOptionTransferRule implements TransferRule {



	@Override
	public boolean checkValid(Trade trade, Transfer oldTrade,
			Transfer newTransfer, RemoteTrade remoteTrade,
			RemoteBOProcess remoteBO, Vector messageData, Pricer pricer,
			Connection con) {
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public boolean update(Trade trade, Transfer oldTrade, Transfer newTransfer,
			RemoteTrade remoteTrade, RemoteBOProcess remoteBO,
			Vector messageData, Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		
		try {
			Vector transfers = (Vector) remoteBO.getTransferOnTrade(trade.getParentID());  // write sql to get transfer which are not in cancel status. 
			Transfer newtrs = null;
			Transfer oldtrs = null;
			Vector<Transfer> oldTransfers = new Vector<Transfer>();
			Vector<Transfer> newTransfers = new Vector<Transfer>();
			boolean flag = false;
			for(int i=0;i<transfers.size();i++) {
				   Transfer transfer = (Transfer) transfers.get(i);
				   if(checkValidTransfer(transfer) && transfer.getEventType().equalsIgnoreCase(newTransfer.getEventType())) {
	                   flag = true;       
					   try {
	                        	  newtrs = (Transfer) transfer.clone();
	                        	  oldtrs = transfer;
	                        	  if(newtrs.getAmount() > 0) {
	                        		  newtrs.setAmount(newtrs.getAmount() - newTransfer.getAmount());
	                        	  } else {
									newtrs.setAmount(newtrs.getAmount() + (newTransfer.getAmount() * -1));
	                        	  }
	                        	  
	                        	  break;
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				   }
			}
				   if(flag) {
						   oldtrs.setAction("CANCEL");
						   newtrs.setAction("NEW");
						   newtrs.setStatus("NONE");
						   newtrs.setId(0);
						   oldTransfers.add(oldtrs);
						   newTransfers.add(newtrs);
						   Vector<String> transferstatus = new Vector<String>();
						   
						   Vector oldV = (Vector) remoteBO.saveTransfers(newTransfers,"insert",trade.getStatus(),trade,newTransfer.getUserid());
				           
						   Vector newa = (Vector) remoteBO.saveTransfers(oldTransfers,"update",trade.getStatus(),trade,newTransfer.getUserid());
				   }
				   oldtrs = null;
				   newtrs = null;
				   
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	private boolean checkValidTransfer(Transfer transfer) {
		boolean flag = true;
		if(transfer.isCanceled())
			flag = false;
		if(transfer.isSettled())
			flag = false;
		return flag;
		
	}
}
