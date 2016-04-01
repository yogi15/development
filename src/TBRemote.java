import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import beans.Account;
import util.commonUTIL;
import dsServices.RemoteAccount;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;


public class TBRemote {

	Hashtable<Integer,AccountTB> accou = new Hashtable<Integer,AccountTB> ();
	public void readH() {
		 System.out.println(accou);
	}
	
	  public static void main(String args[]) {
			ServerConnectionUtil de = null; 
			de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getLocalHostName() );
			RemoteBOProcess remoteBO = null;
			Vector accounts = null;
			try {
				TBRemote tb = new TBRemote();
				RemoteAccount remoteReference = (RemoteAccount) de.getRMIService("Account");
				 accounts = (Vector) remoteReference.getTBData();
				 tb.getParentAccount(accounts);
				 tb.readH();
			}catch(RemoteException e) {
				
			}
	  }
	  
	  
	  public AccountTB getParent(Account parentAcc) {
		  AccountTB acc  = null;
		  Enumeration enu = accou.elements();
		  while(enu.hasMoreElements()) {
			  AccountTB aTB = (AccountTB)enu.nextElement();
			  if(aTB.isParentOFChild(parentAcc)) {
				  acc = aTB;
			  break;
			  }
		  }
		  return acc;
		  
	  }
	  public Account getParentAccount(Vector accounts) {
		  Account parentAcc = null;
		  Account a =  (Account) accounts.get(0);
		//
		  Vector<Account> remomainingacc1 = new Vector<Account>();
		  for(int i=0;i<accounts.size();i++) {
			  parentAcc = (Account) accounts.get(i);
			  if(parentAcc.getParentID() == 0) {
				  AccountTB actB  = fillTB(parentAcc);
				  System.out.println(actB.getAccountID() +  "  " + actB.getParentID());
				  accou.put(actB.getAccountID(), actB);
			  }  	 else {
				  remomainingacc1.add(parentAcc);
				  
			  }
		  }
		  addChilds(remomainingacc1);
		 
		  return parentAcc;
		  
	  }
	  
	  
	  public void addChilds(Vector<Account> accts) {
		  Vector<Account> remaningAcc = new Vector<Account>();
		  for(int i=0;i<accts.size();i++) {
			 Account parentAcc = (Account) accts.get(i);
			   AccountTB accT =  getParent(parentAcc);
			   if(accT != null) {
				   AccountTB actB  = fillTB(parentAcc);
			   		accT.addChild(actB);
			   		accou.put(accT.getAccountID(), accT);
			   		
			   } else {
				   remaningAcc.add(parentAcc);
			   }
		  }
		  if(remaningAcc.size() > 0)  {
			  addChilds(remaningAcc);
		  }
	  }
	  
	  public AccountTB  fillTB(Account acc) {
		  AccountTB tbData = new AccountTB();
		  tbData.setAccountID(acc.getId());
		  tbData.setAccountName(acc.getAccountName());
		  tbData.setParentID(acc.getParentID());
		
		  return tbData;
		  
	  }
	  
	 
	  
}
