package util.cacheUtil;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import dsServices.ServerConnectionUtil;

import util.RemoteServiceUtil;
import util.ServerConnectionObjectUtil;
import util.commonUTIL;
 
import beans.AccessFunction;
import beans.Users;

public class AccessDataCacheUtil {
	
	static Hashtable<String,Vector<AccessFunction>> groupAccessFunction = new Hashtable<String,Vector<AccessFunction>>();
	/**
	 * @return the groupAccessFunction
	 */
	public static Hashtable<String, Vector<AccessFunction>> getGroupAccessFunction() {
		return groupAccessFunction;
	}

	/**
	 * @param groupAccessFunction the groupAccessFunction to set
	 */
	 
	static AccessDataCacheUtil singletonInstane = null;
	
	static public AccessDataCacheUtil getAccessData() {

		return AccessDataCacheUtil.getAccessDataCacheUtil();
	}

	private static AccessDataCacheUtil getAccessDataCacheUtil() {
		if(singletonInstane == null)  {
			singletonInstane = new AccessDataCacheUtil();
		    return singletonInstane;
		} 
		return singletonInstane;
	}
	
	public static boolean isAccessToWindow(Users user,String windowName) {
		Vector<AccessFunction> functions = null;
	 
			functions = getGroupAccessFunction().get(user.getUser_groups());
			
			if(functions == null) {
				try {
					functions = (Vector<AccessFunction>)	RemoteServiceUtil.getRemoteAccessDataService().getALLFunctionOfGroup(user );
					getGroupAccessFunction().put(user.getUser_groups().trim(), functions);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccessDataCacheUtil", "isOpenFunctionAccess", e);
				}
			}
			return isOpenFunctionAccess(user,windowName,functions);
		 
		
	}
	public static boolean isFunctionAccess(Users user,String windowName,String functionName) {
		Vector<AccessFunction> functions = null;
		 
			functions = groupAccessFunction.get(user.getUser_groups());
			if(functions == null) {
				try {
					functions = (Vector<AccessFunction>)	RemoteServiceUtil.getRemoteAccessDataService().getALLFunctionOfGroup(user );
					groupAccessFunction.put(user.getUser_groups(), functions);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccessDataCacheUtil", "isOpenFunctionAccess", e);
				}
			}
			return isFunctionAccess(user,windowName,functions,functionName);
		 
		
	}
	 
	private static boolean isFunctionAccess(Users user, String windowName,
			Vector<AccessFunction> functions,String function) {
		// TODO Auto-generated method stub
		 
		boolean flag = false;
		if(functions == null)
			return false;
		for(int i=0;i<functions.size();i++) {
			AccessFunction accesF = functions.get(i);
			//if(accesF.getWindowName().equalsIgnoreCase(windowName) && accesF.getFunctionName().equalsIgnoreCase(windowName+open) && accesF.getIsAccessable() == 1) {
				if( isFunctionAccess(accesF,windowName,windowName+"_"+function )) {
					flag = true;
				
				break;
				
			}
		}
		return flag;
	}
	private static boolean isOpenFunctionAccess(Users user, String windowName,
			Vector<AccessFunction> functions) {
		// TODO Auto-generated method stub
		String open = "_Open";
		boolean flag = false;
		if(functions == null)
			return false;
		for(int i=0;i<functions.size();i++) {
			AccessFunction accesF = functions.get(i);
			//if(accesF.getWindowName().equalsIgnoreCase(windowName) && accesF.getFunctionName().equalsIgnoreCase(windowName+open) && accesF.getIsAccessable() == 1) {
				if( isFunctionAccess(accesF,windowName,windowName+open )) {
					flag = true;
				    
				break;
				
			}
		}
		return flag;
	}
		 
	
	private static boolean isFunctionAccess(AccessFunction accesF,String windowName,String function ) {
	if(accesF.getWindowName().equalsIgnoreCase(windowName) && accesF.getFunctionName().equalsIgnoreCase(function) && accesF.getIsAccessable() == 1) 
		return true;
	return false;
		
	}
	public static void addNewAccesFunctionToGroup(
			Collection<AccessFunction> accessFunctionvec) {
		// TODO Auto-generated method stub
		if(!commonUTIL.isEmpty(accessFunctionvec)) {
			Vector<AccessFunction> functions = (Vector<AccessFunction>) accessFunctionvec;
			 
				AccessFunction function = functions.get(0);
				try {
					functions = (Vector<AccessFunction>)	RemoteServiceUtil.getRemoteAccessDataService().getALLFunctionOfGroup(function.getGroupname() );
					System.out.println(getGroupAccessFunction().size());
					if(getGroupAccessFunction().containsKey(function.getGroupname().trim())) {
						getGroupAccessFunction().remove(function.getGroupname());
						getGroupAccessFunction().put(function.getGroupname(), functions);
					}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccessDataCacheUtil", "isOpenFunctionAccess", e);
				}
				
				 
				
			 
		}
		
	}

}
