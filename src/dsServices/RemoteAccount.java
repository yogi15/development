package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;
import java.rmi.Remote;

import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.AccEventLink;
import beans.AccTriggerEvent;
import beans.Account;
import beans.LinkFolder;
import beans.Posting;

public interface RemoteAccount extends Remote {
	
	
	
	public Collection   getAllAccounts()  throws RemoteException;
	public Account getAccount(int accountID)  throws RemoteException;
	public int saveAccount(Account account)  throws RemoteException;
	public boolean updateAccount(Account account)  throws RemoteException;
	public boolean deleteAccount(Account account)  throws RemoteException;
	public Collection getAccountonWhereClause(String where) throws RemoteException;
	
	public Collection   getAllPostings()  throws RemoteException;
	public Collection getPosting(int postingID)  throws RemoteException;
	public int savePosting(Posting posting)  throws RemoteException;
	public boolean updatePosting(Posting posting)  throws RemoteException;
	public boolean deletePosting(Posting posting)  throws RemoteException;
	public Collection getPostingonWhereClause(String where) throws RemoteException;
	public Collection getPostingonWhereClause(int tradeid,String eventType) throws RemoteException;
	public int updateLinkToPosting(int linkTo,int postingID) throws RemoteException;
	
	
	
	public Collection   getAllAccEventLinks()  throws RemoteException;
	public Account getAccEventLink(int accEventLinkID)  throws RemoteException;
	public int saveAccEventLink(AccEventLink accEventLink)  throws RemoteException;
	public boolean updateAccEventLink(AccEventLink accEventLink)  throws RemoteException;
	public boolean deleteAccEventLink(AccEventLink accEventLink)  throws RemoteException;
	public Collection getAccEventLinkonWhereClause(String where) throws RemoteException;
	public Collection getAccEventLinkonrule(int ruleId) throws RemoteException;
	
	
	public Collection   getAllLinkFolders()  throws RemoteException;
	public LinkFolder getLinkFolder(int linkFolderId)  throws RemoteException;
	public int saveLinkFolder(LinkFolder linkFolder)  throws RemoteException;
	public boolean updateLinkFolder(LinkFolder linkFolder)  throws RemoteException;
	public boolean deleteLinkFolder(LinkFolder linkFolder)  throws RemoteException;
	public Collection getLinkFolderWhereClause(String where) throws RemoteException;
	public Collection getRulesLinkonFolders(String where) throws RemoteException;
	
	
	
	public Collection   getAllAccountConfigRules()  throws RemoteException;
	public AccConfigRule getaccConfigRule(int accConfigRuleId)  throws RemoteException;
	public int saveAccConfigRule(AccConfigRule accConfigRule)  throws RemoteException;
	public boolean updateAccConfigRule(AccConfigRule accConfigRule)  throws RemoteException;
	public boolean deleteAccConfigRule(AccConfigRule accConfigRule)  throws RemoteException;
	public Collection getAccConfigRuleWhereClause(String where) throws RemoteException;
	
	public Collection   getAllAccountEvtConfigs()  throws RemoteException;
	public AccEventConfig getAccountConfig(int AccEventConfigID)  throws RemoteException;
	public int saveAccountEvtConfig(AccEventConfig accEvtConfig)  throws RemoteException;
	public boolean updateAccountEvtConfig(AccEventConfig accEvtConfig)  throws RemoteException;
	public boolean deleteAccountEvtConfig(AccEventConfig accEvtConfig)  throws RemoteException;
	public Collection getAccEventConfigWhereClause(String where) throws RemoteException;
	public int getAccountEvtConfig(AccEventConfig accEvtConfig)  throws RemoteException;
	
	public Collection   getAllAccountTriggerEvts()  throws RemoteException;
	public Collection getAccountTriggerEvts(int accEventConfigID)  throws RemoteException;
	public int saveAccountTriggerEvts(AccTriggerEvent account)  throws RemoteException;
	public boolean updateAccountTriggerEvts(AccTriggerEvent accountTriggerEvt)  throws RemoteException;
	public boolean deleteAccountTriggerEvts(AccTriggerEvent accountTriggerEvt)  throws RemoteException;
	public Collection getAccountTriggerEvtsWhereClause(String where) throws RemoteException;
	public Collection getTBData() throws RemoteException;
	
	
	
	
	
	
	
	
	
	
	

}
