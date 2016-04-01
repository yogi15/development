package apps.window.staticwindow.util;

import java.rmi.RemoteException;
import java.util.Vector;
 
import apps.window.staticwindow.Login; 
import beans.Users;
import util.RemoteServiceUtil;
import util.commonUTIL;

public class LoginUtil {
	
	public static Vector getUsersGroup() {
		Vector userGroupList = null;
		try {
			userGroupList = (Vector)  RemoteServiceUtil.getRemoteReferenceDataService().getStartUPData("UserGroup");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			 
			commonUTIL.displayError("ERROR", "ClientAplication LoginUtil getUsersGroup", e);
		}

   return userGroupList;
	}
	
	public static Users fillUserData(Users user,Login login) {
		if(  validateDetails(login)) {
			user = new Users();
			user.setUser_groups(login.groupList.getSelectedItem().toString());
			user.setUser_name(login.userName.getText());
	    	user.setPassword(login.password.getText());
	    	try {
	        	
	        	user =    RemoteServiceUtil.getRemoteReferenceDataService().validateUser(user);
	        	
	    		//user = remoteBORef.selectUser(user, user.getUser_groups());
	        	
	    	} catch (RemoteException e) {
	    		// TODO Auto-generated catch block
	    		commonUTIL.displayError("ERROR", "ClientAplication LoginUtil getUsersGroup", e);
	    	}



		}
		return user;
	}
	public static boolean validateDetails(Login login) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if (login.groupList.getSelectedIndex() == -1){
			commonUTIL.showAlertMessage("Select Group Name");
			 flag = false;
			 
		}
		if (commonUTIL.isEmpty(login.userName.getText())) {
			commonUTIL.showAlertMessage("Enter UserName");
			 flag = false;
		}
		if (commonUTIL.isEmpty(login.password.getText())) {
			commonUTIL.showAlertMessage("Enter password");
			 flag = false;
		}
		return flag;
	}
}
