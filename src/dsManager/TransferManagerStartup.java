package dsManager;

import java.io.InterruptedIOException;
import java.io.Serializable;

import javax.jms.JMSException;

import logAppender.TransferServiceAppender;

import beans.Users;

import dsServices.EngineMonitorUtil;
import dsServices.ServiceManager;
import util.commonUTIL;

public class TransferManagerStartup  extends ServiceManager  {
	TransferManager amanager = null;
	String managerName = "TransferManager";
	Thread t = null;
	Users user = null;
	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		//startProducingMessage("Transfer",commonUTIL.getLocalHostName(),":61616");
		//startProducingMessage();
		String hostName = commonUTIL.getLocalHostName();
		String localHost = "localhost";
		user = new Users();
		user.setUser_name("UserTransfer1");
		user.setPassword("1");
		user.setHostName(hostName);
		user.setApplicattionNameLoginOn("TransferManager");
		setUser(user);
		try {
			amanager = new TransferManager(localHost,hostName,managerName,this,"TRANSFER");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TransferManagerStartUP", "Start ", e);
			TransferServiceAppender.printLog("ERROR", "TransferService Shutdown not able to listen to Server "+e);
			
			System.exit(0);
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TransferManagerStartUP", "Start ", e);
			TransferServiceAppender.printLog("ERROR", "TransferService Shutdown not able to listen to Server "+e);
			System.exit(0);
		} 

		
		amanager.publishStartEvent(managerName,"Started");
		amanager.start(amanager);
		
	}
	
	
	public static void main(String args[]) {
		TransferManagerStartup startTransferService = new TransferManagerStartup();
		startTransferService.start();
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
//		System.out.println(t.getId());
		
		try {

			  TransferServiceAppender.printLog("DEBUG", "TransferService Starting Stop process ");
			amanager.publishStartEvent(managerName,"Stopped");
			TransferServiceAppender.printLog("DEBUG", "TransferService Publish event to  Stop process ");
			amanager.stop();
			amanager = null;
			throw new InterruptedIOException();
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			commonUTIL.display("TransferManager", "TransferManager is Stopped");
			TransferServiceAppender.printLog("ERROR", "TransferManager is Stopped");
			
			System.exit(0);
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
			commonUTIL.display("TransferManager", "TransferManager is Stopped");
			System.exit(0);
		} finally {
			System.exit(0);
		}
	
	//	System.out.println(t.getId());
		
	}
    protected void finalize() {
    	try {
			amanager.publishStartEvent(managerName,"Stopped");
			amanager.stop();
			throw new InterruptedIOException();
			
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
			commonUTIL.display("TransferManager", "TransferManager is stop");
		} finally {
			amanager = null;
			System.exit(0);
		}
    }


}