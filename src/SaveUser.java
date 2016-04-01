import java.rmi.RemoteException;

import beans.Users;

import util.commonUTIL;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;


public class SaveUser {
	
	public static void main(String[] args) {
		
		ServerConnectionUtil de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getLocalHost());
		RemoteReferenceData remoteBORef = null;
		try {
			 remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Users user = new Users();
		user.setUser_name("user2");
		user.setUser_groups("Group2");
		user.setPassword("user2");
		
		int id = 0;
		try {
			 id = remoteBORef.saveUser(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("id---- " + id );
		
	}
	
	
}	
