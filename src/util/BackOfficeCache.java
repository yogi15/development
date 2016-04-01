package util;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import beans.Account;
import beans.Country;
import bo.swift.bic.BICSwiftData;


import dsServices.RemoteAccount;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

public class BackOfficeCache {
	
	static RemoteBOProcess remoteBO =null;
	static RemoteAccount remoteAccount =null;
	public  static  ServerConnectionUtil de = null;
	static public  Hashtable <Integer,Account> accountCache = new Hashtable  <Integer,Account>();
static public  BackOfficeCache singleTonInstance;

	public static BackOfficeCache getSingleInstatnce() {
		if(singleTonInstance == null) {
			singleTonInstance = new  BackOfficeCache();
			init();
		}
		return singleTonInstance;
	}
	
	private static void init() {
		// TODO Auto-generated method stub
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
		try {
			remoteBO = (RemoteBOProcess) de.getRMIService("BOProcess");
			remoteAccount = (RemoteAccount) de.getRMIService("Account");
			Vector accounts  = (Vector) remoteAccount.getAllAccounts();
			cacheAccount(accounts,accountCache);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	Vector LegalEntity = (Vector) remoteBO.get
		
	}
	private static void cacheAccount(Vector accounts,
			Hashtable<Integer, Account> accountCache) {
		// TODO Auto-generated method stub
		if(!commonUTIL.isEmpty(accounts)) {
			for(int i =0;i<accounts.size();i++) {
				Account account = (Account) accounts.get(i);
				accountCache.put(account.getId(), account);
			}
		}
	}
	
	

	public static RemoteBOProcess getRemoteBO() {
		if(remoteBO == null)
			
				singleTonInstance = getSingleInstatnce();
		return remoteBO;
	}

	public void setRemoteBO(RemoteBOProcess remoteBO) {
		this.remoteBO = remoteBO;
	}

	

	public static List getSwiftBICData(BICSwiftData query) {
		if(singleTonInstance == null)
			singleTonInstance = getSingleInstatnce();
		// TODO Auto-generated method stub
		try {
			return (List) getRemoteBO().getSwiftBICData(query);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Account getAccount(int accountID) {
		if(singleTonInstance == null)
			singleTonInstance = getSingleInstatnce();
		// TODO Auto-generated method stub
		return accountCache.get(accountID);
	}

}
