package dsManager;

import java.io.InterruptedIOException;

import javax.jms.JMSException;

import logAppender.MessageServiceAppender;
import logAppender.TransferServiceAppender;

import beans.Users;

import util.commonUTIL;
import dsServices.ServiceManager;

public class MessageManagerStartup extends ServiceManager {

	MessageManager amanager = null;
	MessageManager aTransfermanager = null;
 //   Thread t = null;
	String managerName = "MessageManager";
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
	public static void main(String args[]) {
		MessageManagerStartup startMessageService = new MessageManagerStartup();
		startMessageService.start();
		
		
		
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		user = new Users();
		user.setUser_name("UserTransfer1");
		user.setPassword("1");
		user.setHostName(commonUTIL.getServerIP());
		user.setApplicattionNameLoginOn("MessageApplication");
		setUser(user);
		String hostName = commonUTIL.getLocalHostName();
		String localHost = "localhost";
		try {
			amanager = new MessageManager(localHost,hostName,managerName,this,"MESSAGE");
		//	aTransfermanager = new MessageManager(localHost,hostName,managerName,this,"Transfer");
			if(amanager == null) {
				amanager = null;
				System.exit(0);
			}
			amanager.publishStartEvent(managerName,"Started",getClientID());
			amanager.start(amanager);
		//	aTransfermanager.start(aTransfermanager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MessageManagerStartUP", "Start ", e);
			MessageServiceAppender.printLog("ERROR", "MessageService Shutdown not able to listen to Server "+e);
			
			System.exit(0);
		}
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		try {
			MessageServiceAppender.printLog("INFO", "MessageService Starting  Shutdown Process ");
			
			amanager.publishStartEvent(managerName,"Stopped",getClientID());
			
			amanager.stop();
			
			throw new InterruptedIOException();
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
			commonUTIL.display("MessageManager", "MessageManager is stop");
			MessageServiceAppender.printLog("INFO", "MessageService   Shutdown Completed ");
			
			System.exit(0);
		} finally {
			amanager = null;
			System.exit(0);
		}
	
		
	}
	

}
