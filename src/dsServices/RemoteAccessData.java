package dsServices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.AccessFunction;
import beans.AccessWindow;
import beans.Users;

public interface RemoteAccessData extends Remote{
	
	public int saveAccessFuntion(Collection<AccessFunction> accessFunctionVec) throws RemoteException;
	public boolean deleteAccessFuntion(Collection<AccessFunction> accessFunctionvec) throws RemoteException;
	public boolean updateAccessFuntion(AccessFunction accessFunction) throws RemoteException;
	public int isAccessFunctionAccessible(AccessFunction accessFunction) throws RemoteException;
	public AccessFunction selectAccessFunction(AccessFunction accessFunction) throws RemoteException;
	public Collection<AccessFunction> selectAccessFunction(String where) throws RemoteException;
	public boolean deleteAllAccessFuntion(Collection<AccessFunction> accessFunctionvec) throws RemoteException;
	public Collection<AccessFunction> getALLFunctionOfGroup(Users user) throws RemoteException;
	public int saveAccessWindow(Collection<AccessWindow> accessWindowVec) throws RemoteException;
	public boolean deleteAccessWindow(Collection<AccessWindow> accessWindowVec) throws RemoteException;
	public boolean updateAccessWindow(AccessWindow accessWindow) throws RemoteException;
	public int isAccessWindowAccessible(AccessWindow accessWindow) throws RemoteException;
	public AccessWindow selectAccessWindow(AccessWindow accessWindow) throws RemoteException;
	public Collection<AccessWindow> selectAccessWindow(String where) throws RemoteException;
	boolean deleteAccessWindow(Collection<AccessWindow> accessWindowVec, Collection<AccessFunction> accessFunctionvec) throws RemoteException;
	public Collection<AccessFunction> getALLFunctionOfGroup(String groupName)
			throws RemoteException;
	
	
}
