package dsServices;


import java.rmi.Remote;
import java.rmi.RemoteException;

import beans.DealBean;

public interface RemoteDealStation extends Remote {
	
	  public void viewDealData(DealBean bean) throws RemoteException;

}
