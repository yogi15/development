package dsServices;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import static java.lang.System.out;

import beans.DealBean;
import beans.ServerBean;
import beans.Users;

public class DealServerImp  implements RemoteDeal {

	
	
	
	 public DealServerImp() {
	        	    }

	@Override
	public ServerBean connect() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("coming from DealServerImp");
		return null;
	}

	@Override
	public ServerBean connect(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int publishEvent(String messageIndicator, String queueName,
			String messageType, Object object) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServerBean connect(String username, String password,
			String applicationName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerBean connect(Users user, String applicationName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
