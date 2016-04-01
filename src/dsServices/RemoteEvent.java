package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;

import dsEventProcessor.EventProcessor;

import beans.Event;

public interface RemoteEvent extends Remote {
	
	//public EventProcessor saveEvent(EventProcessor event) throws RemoteException;
	public EventProcessor updateEvent(EventProcessor event) throws RemoteException;
	public EventProcessor getEventOnType(String type) throws RemoteException;
	public void updateClearEvents(EventProcessor eventp) throws RemoteException;
	public Collection getEventNotProcessed(String managerName) throws RemoteException;

	public Collection getEventProcessed(String managerName) throws RemoteException;
	
}
