package dsServices;

import java.rmi.RemoteException;

import dsEventProcessor.EventProcessor;

public interface RemoteEngineMonitor {
	
	
	public void monitorLiveEngineService(EventProcessor event);
	public   void removeEngine(String engineName, int clientID)  throws RemoteException;
	

}
