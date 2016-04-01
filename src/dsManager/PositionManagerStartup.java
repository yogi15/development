package dsManager;

import java.io.InterruptedIOException;

import util.commonUTIL;
import dsServices.ServiceManager;

public class PositionManagerStartup  extends ServiceManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PositionManager amanager = null;
	Thread t = null;
	@Override
	public void start() {
		// TODO Auto-generated method stub
		String managerName = "PositionManager";
		String hostName = commonUTIL.getLocalHostName();
		String localHost = "localhost";
		amanager	= new PositionManager(localHost,hostName,managerName );
		
		amanager.start(amanager);
		
	}
	public static void main(String args[]) {
		PositionManagerStartup startTransferService = new PositionManagerStartup();
		startTransferService.start();
		
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
			commonUTIL.display("PositionManager", "PositionManager is stop");
		}
	
	//	System.out.println(t.getId());
		
	}

}
 