package dsServices;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Vector;

import beans.Users;

public interface RemoteAdminManager extends Serializable,Remote {
	
	public void addEngines(String engineName,int clientID,Users user) throws RemoteException;
	//public void removeEngine(String engineName) throws RemoteException;
	public Vector<String> getALLRunningEngines() throws RemoteException;

	public Vector<String> getALLUserConnected() throws RemoteException;
	public   void removeEngine(String engineName, int clientID)  throws RemoteException;
	

}
