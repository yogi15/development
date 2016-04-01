package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import dbSQL.AccConfigRuleSQL;
import dbSQL.AccEventConfigSQL;
import dbSQL.AccEventLinkSQL;
import dbSQL.AccEventTriggerSQL;
import dbSQL.AccountSQL;
import dbSQL.LinkFolderSQL;
import dbSQL.PostingSQL;
import dbSQL.dsSQL;

import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.AccEventLink;
import beans.AccTriggerEvent;
import beans.Account;
import beans.LinkFolder;
import beans.Posting;

public class AccountImp implements RemoteAccount {

   public AccountImp() {
		
		
	}
	
   @Override
	public Collection getTBData() throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.getTB(dsSQL.getConn());
	}
	@Override
	public Collection getAllAccounts() throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Account getAccount(int accountID) throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.selectAccount(accountID, dsSQL.getConn());
	}

	@Override
	public int saveAccount(Account account) throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.save(account, dsSQL.getConn());
	}

	@Override
	public boolean updateAccount(Account updateAccount) throws RemoteException {
		// TODO Auto-generated method stub
		 return AccountSQL.update(updateAccount, dsSQL.getConn());
	}

	@Override
	public boolean deleteAccount(Account deleteAccount) throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.delete(deleteAccount, dsSQL.getConn());
	}

	@Override
	public Collection getAccountonWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccountSQL.selectWhere(where, dsSQL.getConn());
	}

	@Override
	public Collection getAllAccountEvtConfigs() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccEventConfig getAccountConfig(int AccEventConfigID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveAccountEvtConfig(AccEventConfig accEvtConfig) throws RemoteException {
		// TODO Auto-generated method stub
		int configId =AccEventConfigSQL.checkExistingConfig(accEvtConfig,dsSQL.getConn());
		if(configId == 0)
		 return AccEventConfigSQL.save(accEvtConfig, dsSQL.getConn());
		
		return configId;
		
	}

	@Override
	public boolean updateAccountEvtConfig(AccEventConfig accEvtConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		int configId =AccEventConfigSQL.checkExistingConfig(accEvtConfig,dsSQL.getConn());
		if(configId != 0) {
			
		}
		return false;
	}

	@Override
	public boolean deleteAccountEvtConfig(AccEventConfig accEvtConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		int configId =AccEventConfigSQL.checkExistingConfig(accEvtConfig,dsSQL.getConn());
		if(configId != 0) {
			Vector<AccTriggerEvent> v1 = (Vector<AccTriggerEvent>) getAccountTriggerEvts(configId);
			for(int i=0;i<v1.size();i++)
				deleteAccountTriggerEvts(v1.get(i));
			
			AccEventConfigSQL.delete(configId, dsSQL.getConn());
		}
		
		return false;
	}

	@Override
	public Collection getAccEventConfigWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventConfigSQL.selectAccEventConfigOnWhere(where,dsSQL.getConn());
	}

	@Override
	public Collection getAllAccountTriggerEvts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection getAccountTriggerEvts(int accEventConfigtID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventTriggerSQL.selectTriggerEvent(accEventConfigtID, dsSQL.getConn());
	}

	@Override
	public int saveAccountTriggerEvts(AccTriggerEvent aTriggerEvent)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventTriggerSQL.save(aTriggerEvent, dsSQL.getConn());
	}

	@Override
	public boolean updateAccountTriggerEvts(AccTriggerEvent accountTriggerEvt)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccountTriggerEvts(AccTriggerEvent accountTriggerEvt)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventTriggerSQL.delete(accountTriggerEvt,dsSQL.getConn());
	}

	@Override
	public Collection getAccountTriggerEvtsWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventTriggerSQL.selectAccTriggerEventOnWhere(where,dsSQL.getConn());
	}


	@Override
	public Collection getAllAccountConfigRules() throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.selectALL(dsSQL.getConn());
	}


	@Override
	public AccConfigRule getaccConfigRule(int accConfigRuleId)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.select(accConfigRuleId,dsSQL.getConn());
	}


	@Override
	public int saveAccConfigRule(AccConfigRule accConfigRule)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.save(accConfigRule, dsSQL.getConn());
	}


	@Override
	public boolean updateAccConfigRule(AccConfigRule accConfigRule)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.update(accConfigRule, dsSQL.getConn());
	}


	@Override
	public boolean deleteAccConfigRule(AccConfigRule accConfigRule)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.delete(accConfigRule,  dsSQL.getConn());
	}


	@Override
	public Collection getAccConfigRuleWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccConfigRuleSQL.selectWhere(where,  dsSQL.getConn());
	}


	@Override
	public Collection getAllLinkFolders() throws RemoteException {
		// TODO Auto-generated method stub
		return LinkFolderSQL.selectALL( dsSQL.getConn());
	}


	@Override
	public LinkFolder getLinkFolder(int linkFolderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int saveLinkFolder(LinkFolder linkFolder) throws RemoteException {
		// TODO Auto-generated method stub
		return LinkFolderSQL.save(linkFolder, dsSQL.getConn());
	}


	@Override
	public boolean updateLinkFolder(LinkFolder linkFolder)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteLinkFolder(LinkFolder linkFolder)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Collection getLinkFolderWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return LinkFolderSQL.selectLinkFolderOnWhere(where, dsSQL.getConn());
	}


	@Override
	public Collection getAllAccEventLinks() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Account getAccEventLink(int accEventLinkID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int saveAccEventLink(AccEventLink accEventLink)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventLinkSQL.save(accEventLink, dsSQL.getConn());
	}


	@Override
	public boolean updateAccEventLink(AccEventLink accEventLink)
			throws RemoteException {
		// TODO Auto-generated method stub
		return  AccEventLinkSQL.update(accEventLink, dsSQL.getConn());
	}


	@Override
	public boolean deleteAccEventLink(AccEventLink accEventLink)
			throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventLinkSQL.delete(accEventLink, dsSQL.getConn());
	}


	@Override
	public Collection getAccEventLinkonWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return  AccEventLinkSQL.selectWhere(where, dsSQL.getConn());
	}


	@Override
	public Collection getAccEventLinkonrule(int ruleId) throws RemoteException {
		// TODO Auto-generated method stub
		return AccEventLinkSQL.selectAccEventLinkonRule(ruleId,dsSQL.getConn());
	}


	@Override
	public Collection getRulesLinkonFolders(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		Vector<LinkFolder> linkFolders = (Vector<LinkFolder>) getLinkFolderWhereClause(where);
		Vector<AccConfigRule> rules = null;
		if((linkFolders !=null) && (!linkFolders.isEmpty())) {
			 rules = new Vector<AccConfigRule>();
			for(int i=0;i<linkFolders.size();i++) {
				LinkFolder linkFolder = linkFolders.get(i);
				rules.addElement(getaccConfigRule(linkFolder.getRuleid()));
			}
		}
		return rules;
	}


	@Override
	public Collection getAllPostings() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collection getPosting(int postingID) throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.selectPosting(postingID, dsSQL.getConn());
	}


	@Override
	public int savePosting(Posting posting) throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.save(posting, dsSQL.getConn());
	}


	@Override
	public boolean updatePosting(Posting posting) throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.update(posting, dsSQL.getConn());
	}


	@Override
	public boolean deletePosting(Posting posting) throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.delete(posting, dsSQL.getConn());
	}


	@Override
	public Collection getPostingonWhereClause(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.selectWhere(where,  dsSQL.getConn());
	}


	@Override
	public Collection getPostingonWhereClause(int tradeid, String eventType)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getAccountEvtConfig(AccEventConfig accEvtConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		int configId =AccEventConfigSQL.checkExistingConfig(accEvtConfig,dsSQL.getConn());
		return configId;
	}

	@Override
	public int updateLinkToPosting(int linkTo, int postingID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return PostingSQL.editlinkToPosting(linkTo, postingID,dsSQL.getConn());
	}
	
	

}
