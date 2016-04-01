package dsManager;

import java.io.InterruptedIOException;

import util.commonUTIL;
import dsServices.ServiceManager;

public class AccountManagerStartup extends ServiceManager {
	AccountManager amanager = null;
	Thread t = null;
	@Override
	public void start() {
		// TODO Auto-generated method stub
		String managerName = "AccountManager";
		String hostName = commonUTIL.getLocalHostName();
		String localHost = "localhost";
		amanager	= new AccountManager(localHost,hostName,managerName);
		
		amanager.start(amanager);
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
		//System.out.println(t.getId());
		
		try {
			amanager.stop();
			throw new InterruptedIOException();
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
			commonUTIL.display("AccountManager", "AccountManager is stop");
		}
	
	//	System.out.println(t.getId());
		
	}

}
 