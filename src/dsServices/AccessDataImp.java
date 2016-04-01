package dsServices;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Collection;

import util.cacheUtil.AccessDataCacheUtil;

import dbSQL.AccessSQL;
import dbSQL.dsSQL;
import beans.AccessFunction;
import beans.AccessWindow;
import beans.Users;

public class AccessDataImp implements RemoteAccessData {

	@Override
	public int saveAccessFuntion(Collection<AccessFunction> accessFunctionvec)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		Collection<AccessFunction> accessFunction = null;
		boolean isDeleted = AccessSQL.deleteAccessFunction(accessFunctionvec, con);
		
		if (isDeleted) {
			 if(AccessSQL.saveAccessFunction(accessFunctionvec, con) == 1)  {
				 //AccessDataCacheUtil.getAccessData().addNewAccesFunctionToGroup(accessFunctionvec);
				 return 1;
			 }
		} else {
			return 0;
		}
		return 0;
				
	}

	@Override
	public boolean deleteAccessFuntion(Collection<AccessFunction> accessFunctionvec) throws RemoteException {
		Connection con = dsSQL.getConn();		
		return AccessSQL.deleteAccessFunction(accessFunctionvec, con);
	}
	
	@Override
	public boolean deleteAllAccessFuntion(Collection<AccessFunction> accessFunctionvec) throws RemoteException {
		Connection con = dsSQL.getConn();		
		return AccessSQL.deleteAllAccessFunction(accessFunctionvec, con);
	}
	
	@Override
	public boolean updateAccessFuntion(AccessFunction accessFuntion)
			throws RemoteException {
		Connection con = dsSQL.getConn();		
		return AccessSQL.update(accessFuntion, con);
	}

	@Override
	public int isAccessFunctionAccessible(AccessFunction accessFunction)
			throws RemoteException {
		Connection con = dsSQL.getConn();		
		return AccessSQL.isAccessFunctionAccessible(accessFunction, con);
	}

	@Override
	public Collection<AccessFunction> selectAccessFunction(String where)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.selectAccessFunction(where, con);
	}
	
	@Override
	public AccessFunction selectAccessFunction(AccessFunction accessFunction)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.selectAccessFunction(accessFunction, con);
	}
	
	@Override
	public int saveAccessWindow(Collection<AccessWindow> accessWindowVec)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		
		boolean isDeleted = AccessSQL.deleteAccessWindow(accessWindowVec, con);
		
		if (isDeleted) {
			return AccessSQL.saveAccessWindow(accessWindowVec, con);
		} else {
			return 0;
		}			
	}

	@Override
	public boolean deleteAccessWindow(Collection<AccessWindow> accessWindowVec)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.deleteAccessWindow(accessWindowVec, con);
	}
	
	@Override
	public boolean deleteAccessWindow(Collection<AccessWindow> accessWindowVec, Collection<AccessFunction> accessFunctionvec)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		boolean isFunctionDeleted = AccessSQL.deleteAllAccessFunction(accessFunctionvec, con);
		
		if (isFunctionDeleted) {
			return AccessSQL.deleteAccessWindow(accessWindowVec, con);
		} else {
			return isFunctionDeleted;
		}
		
	}
	@Override
	public boolean updateAccessWindow(AccessWindow accessWindow)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.update(accessWindow, con);
	}

	@Override
	public int isAccessWindowAccessible(AccessWindow accessWindow)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.isAccessWindowAccessible(accessWindow, con);
	}

	@Override
	public Collection<AccessWindow> selectAccessWindow(String where)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.selectAccessWindow(where, con);
	}

	@Override
	public AccessWindow selectAccessWindow(AccessWindow accessWindow)
			throws RemoteException {
		Connection con = dsSQL.getConn();
		return AccessSQL.selectAccessWindow(accessWindow, con);
	}

	@Override
	public Collection<AccessFunction> getALLFunctionOfGroup(Users user
			 ) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " Groupname = '"+user.getUser_groups()+"'";
		return AccessSQL.selectAccessFunction(sql, dsSQL.getConn());
	}
	@Override
	public Collection<AccessFunction> getALLFunctionOfGroup(String groupName
			 ) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " Groupname = '"+groupName+"'";
		return AccessSQL.selectAccessFunction(sql, dsSQL.getConn());
	}

	 

}
