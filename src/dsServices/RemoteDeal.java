package dsServices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import beans.DealBean;
import beans.ServerBean;
import beans.Users;

public interface RemoteDeal extends Remote {
	
	public ServerBean connect() throws RemoteException;
	public ServerBean connect(String username,String password) throws RemoteException;

	public ServerBean connect(String username,String password,String applicationName) throws RemoteException;
	public ServerBean connect(Users user,String applicationName) throws RemoteException;
	public int publishEvent(String messageIndicator,String queueName,String messageType,Object object) throws RemoteException;

	

}
