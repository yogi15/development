package dsManager;

import java.io.InterruptedIOException;

import util.commonUTIL;
import dsServices.ServiceManager;

public class LimitManagerStartup extends ServiceManager {
	LimitManager amanager = null;
	Thread t = null;
	@Override
	public void start() {
		// TODO Auto-generated method stub
		String managerName = "LimitManager";
		String hostName = commonUTIL.getLocalHostName();
		String localHost = "localhost";
		amanager	= new LimitManager(localHost,hostName,managerName);
		
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
			commonUTIL.display("AccountManager", "LimitManager is stop");
		}
	
	//	System.out.println(t.getId());
		
	}

}
 