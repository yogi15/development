package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import util.RemoteServiceUtil;
import util.commonUTIL;

import dbSQL.EventSQL;
import dbSQL.TradeSQL;
import dbSQL.TransferSQL;
import dbSQL.dsSQL;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;

import beans.Event;
import beans.Trade;
import beans.Transfer;

public class EventServiceImp implements RemoteEvent {

	/*@Override
	public boolean saveEvent(EventProcessor event) throws RemoteException {
		// TODO Auto-generated method stub
		return EventSQL.save(event, dsSQL.getConn());
	} */

	@Override
	public EventProcessor updateEvent(EventProcessor event) throws RemoteException {
		// TODO Auto-generated method stub
		return event;
	}

	@Override
	public EventProcessor getEventOnType(String type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Collection getEventOnWhere(String type) throws RemoteException {
		// TODO Auto-generated method stub
		String sqlw= " subscriberlist not like  '%"+type+"%'  order by id asc "; // imp to get order by in asc to prcoess the old event first. 
		return EventSQL.selectWhere(sqlw, dsSQL.getConn());
	}

	@Override
	public Collection getEventNotProcessed(String managerName)
			throws RemoteException {
		// TODO Auto-generated method stub
		Vector<EventProcessor> evts = new Vector<EventProcessor> ();
		String sqlw= " subscriberlist not like  '%"+managerName+"%'  and (adminclearMark not like  '%"+managerName+"%' or adminclearMark is null) order by eventID asc "; // imp to get order by in asc to prcoess the old event first. 
		Vector <EventProcessor> evtsNotProcess = (Vector<EventProcessor>) EventSQL.selectWhere(sqlw, dsSQL.getConn());
		if(!commonUTIL.isEmpty(evtsNotProcess)) {
			for(int i=0;i<evtsNotProcess.size();i++) {
				EventProcessor event =  evtsNotProcess.get(i);
				if(event instanceof TransferEventProcessor) {
					String sql = " id = "+event.getObjectID() + " and version = "+event.getObjectVersionID();
					Transfer transfer  = TransferSQL.getSingleTransfer(sql, dsSQL.getConn());
					 if(transfer != null) {
						((TransferEventProcessor) event).setTransfer(transfer);
						sql = " id = "+transfer.getTradeId() + " and  version = "+transfer.getTradeVersionID();
						Trade trade = TradeSQL.getSingleTradeOnly(sql, dsSQL.getConn());
						((TransferEventProcessor) event).setTrade(trade);
						evts.add(event);
					 }
					}
				if(event instanceof TradeEventProcessor) {
					String sql = " id = "+event.getObjectID() + " and version = "+event.getObjectVersionID();
					 
					 	Trade trade = RemoteServiceUtil.getRemoteTradeService().getTradeOnVersion(event.getObjectID(),event.getObjectVersionID());
					 	if(trade != null) {
						((TradeEventProcessor) event).setTrade(trade);
						evts.add(event);
					 	}
					}
				}
			}
		
		return evts;
	}
	@Override
	public Collection getEventProcessed(String managerName)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sqlw= " subscriberlist like  '%"+managerName+"%'  and adminclearMark not like  '%"+managerName+"%'  order by eventID asc "; // imp to get order by in asc to prcoess the old event first. 
		
		return EventSQL.selectWhere(sqlw, dsSQL.getConn());
	}

	@Override
	public void updateClearEvents(EventProcessor eventp) throws RemoteException {
		// TODO Auto-generated method stub
		EventSQL.update(eventp, dsSQL.getConn());
	}
}
