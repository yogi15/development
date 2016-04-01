package dsServices;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface Report extends Remote {
	
	
	public Collection generateReportOnSQL(String sql) throws RemoteException;
	
	

}
