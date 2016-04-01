package bo.account;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import productPricing.Pricer;
import util.ClassInstantiateUtil;
import util.commonUTIL;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.LiquidationEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;
import dsServices.RemoteAccount;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.AccEventLink;
import beans.AccTriggerEvent;
import beans.Book;
import beans.Liquidation;
import beans.Posting;
import beans.Trade;
import beans.Transfer;





public class AccountProcessor {
	
	RemoteTrade remoteTrade = null;
	

	RemoteBOProcess remoteBOProcess = null;
//	Transfer transfer = null;
	RemoteReferenceData refData = null;
	Vector<Posting> postings = new Vector<Posting>();
	RemoteAccount remoteAccount = null;
	static protected Hashtable _accountingHanlders = new Hashtable();
	static protected Hashtable _pricers = new Hashtable();
	static protected Hashtable<String,Vector<AccConfigRule>> _rules = new Hashtable<String,Vector<AccConfigRule>> ();
	
	Vector<String> reversalAllPostingEvents = new Vector();
	public void setReversalAllPostingEvents(Vector<String> reversalAllPostingEvents) {
		this.reversalAllPostingEvents = reversalAllPostingEvents;
	}


	public Vector<Posting> getTransfers() {
		return postings;
	}
	
	
	public void  processPosting(EventProcessor event,Trade trade,Transfer transfer) {
		commonUTIL.display("AccountProcessor","Accounting Manager Consuming  transfer event  "  + event.getEventType() );
        Vector<AccConfigRule>rules  = null;
        Vector<AccEventConfig> accEventConfig = null;
        Book book = new Book();
        Vector<BOPosting> events = null;
        try {
        	    
					book.setBookno(trade.getBookId());
					book  = refData.selectBook(book);
					String rulesSQL = "producttype = '"+trade.getProductType() + "'  and folderID = " + book.getFolderID();
					rules = getRules(rulesSQL,trade.getId());
					if(rules == null || (rules.isEmpty())) {
						commonUTIL.display("AccountProcessor ", " processPosting  <<< Rules not set for "+ trade.getProductType() + " on book " + book.getBook_name() + " <<<<<<< ");
						return;
					}
					AccountingHandler accHandler = getAccountingHandler(trade.getProductType());
					if(accHandler == null) {
						commonUTIL.display("AccountProcessor ", "processPosting <<<  Missing accountingHandler for productType "+ trade.getProductType() + " <<<<<<< ");
						return;
					}
					String eventConfigSQL = " producttype ='"+ trade.getProductType() + "'";
					accEventConfig = (Vector) remoteAccount.getAccEventConfigWhereClause(eventConfigSQL);
					if(accEventConfig == null) {
									
							commonUTIL.display("AccountProcessor", "processPosting <<< for trade "+ trade.getId()+ " AccoutEventConfig missing <<<<<<< " );
							return;
					}
					Pricer pricer = getProductPricer(trade.getProductType());
					if(pricer == null) {
						commonUTIL.display("AccountProcessor", "processPosting Missing pricer  <<<"+ trade.getProductType() + "  <<<<<<< " );
						return;
					}
					if(event instanceof TradeEventProcessor) {
						if((!reversalAllPostingEvents.isEmpty()) && reversalAllPostingEvents.contains(event.getEventType())) {
							
							reverseALLPosting((TradeEventProcessor)event); // reverse all posting on trade cancel and termination.
							return;
						}
					    events = generateAccountEvents(accHandler,event,trade,rules,pricer,accEventConfig); /// get productAccountHandler method calls 
					} 
					if(event instanceof TransferEventProcessor) {
						  events = generateAccountEventsOnTransfers(accHandler,event,transfer,rules,pricer,accEventConfig);
					}
					    	
					if(event instanceof LiquidationEventProcessor) {
						LiquidationEventProcessor liquidEvt = (LiquidationEventProcessor) event;
						  events = generateAccountEvents(accHandler,liquidEvt,trade,rules,pricer,accEventConfig);
					}
					Vector<Posting> postings = new Vector<Posting>();
					if(events != null && (!events.isEmpty())) {
						for(int r=0;r<rules.size();r++) {
							  AccConfigRule accRule = (AccConfigRule) rules.get(r);
							  Vector accEventLinks = getAccountEventLinksonRule(accRule.getId());
							  if(accEventLinks != null && (!accEventLinks.isEmpty())) 
							     generatePostingonAccEvents(event,events,accEventLinks,trade,postings,accRule);
							  else 
								  commonUTIL.display("AccountProcessor ", "Rule " + accRule.getRuleName() + " missing accEvent Links");
								
						}
						processGeneratedPosting(postings,trade,event);
					} else {
						commonUTIL.display("AccountProcessor ",  "processPosting <<< events found zero");
					}
					
					
					
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			 commonUTIL.displayError("AccountProcessor", "processPosting" ,e);
		}
			
	
	}
	
	
	
	// to reverse all posting of trade when trade is terminated or cancel. // this can be handled through procedure or subquery.
	
	private void reverseALLPosting(TradeEventProcessor event) {
		// TODO Auto-generated method stub
		int tradeID = event.getTradeID();
		String sql = " tradeid = "+ tradeID + " and linkid = " + 0;
		String reversalSQL = " tradeid = "+ tradeID + " and linkid = " + 0;
	try {
		Vector<Posting> oldpostings = (Vector) remoteAccount.getPostingonWhereClause(sql);
		Vector<Posting> revpostings = (Vector) remoteAccount.getPostingonWhereClause(sql);
		reversalPostingOnTradeTerminationAndCancel(event, oldpostings,revpostings);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}


	private Vector<BOPosting> generateAccountEventsOnTransfers(
			AccountingHandler accHandler, EventProcessor event, Transfer transfer,
			Vector<AccConfigRule> rules, Pricer pricer,
			Vector<AccEventConfig> accEventConfig) {
		// TODO Auto-generated method stub
		Vector<BOPosting>  events = new Vector<BOPosting> ();
		 Vector exceptions = new Vector();
		  
		
		 
		 BOPosting transferPosting = null;
		 for(int r=0;r<rules.size();r++) {
			 AccConfigRule rule = rules.get(r);
		 
			 for(int a=0;a<accEventConfig.size();a++) {
				 AccEventConfig aEventConfig = accEventConfig.get(a);
				 try {
					Vector<String>  trigEvent=(Vector<String>) remoteAccount.getAccountTriggerEvts(aEventConfig.getId());
					if(trigEvent.contains(event.getEventType())) {
						commonUTIL.display("AccountProcessor", "Processing Posting for rule "+ rule.getRuleName() + " on  event "+event.getEventType() + " for " + aEventConfig.getAccEvtType());
						transferPosting = new BOPosting(aEventConfig);
						 transferPosting.setAmount(transfer.getAmount());
						 transferPosting.setCurrency(transfer.getSettlecurrency());
						 transferPosting.setEffectiveDate(transfer.getValueDate());
						 
						// accHandler.invokeMethodOnHandler(accHandler, aEventConfig, trade, event, events, rule, pricer, exceptions);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				 
			 }
		 }
		 if(transferPosting != null)
		    events.add(transferPosting);
		 
		return events;
	}

	
	
	private Vector<BOPosting> generateAccountEventsOnLiquidEvents(
			AccountingHandler accHandler, EventProcessor event,Liquidation liquidatedPosition,
			Vector<AccConfigRule> rules, Pricer pricer,
			Vector<AccEventConfig> accEventConfig) {
		// TODO Auto-generated method stub
		Vector<BOPosting>  events = new Vector<BOPosting> ();
		 Vector exceptions = new Vector();
		  
		
		 
		 BOPosting liquidPosting = null;
		 for(int r=0;r<rules.size();r++) {
			 AccConfigRule rule = rules.get(r);
		 
			 for(int a=0;a<accEventConfig.size();a++) {
				 AccEventConfig aEventConfig = accEventConfig.get(a);
				 try {
					Vector<String>  trigEvent=(Vector<String>) remoteAccount.getAccountTriggerEvts(aEventConfig.getId());
					if(trigEvent.contains(event.getEventType())) {
						commonUTIL.display("AccountProcessor", "Processing Posting for rule "+ rule.getRuleName() + " on  event "+event.getEventType() + " for " + aEventConfig.getAccEvtType());
						liquidPosting = new BOPosting(aEventConfig);
						liquidPosting.setAmount(liquidatedPosition.getRealisedPNL());
						liquidPosting.setCurrency(liquidatedPosition.getCurrency());
						liquidPosting.setEffectiveDate(liquidatedPosition.getLidDate());
						 
						// accHandler.invokeMethodOnHandler(accHandler, aEventConfig, trade, event, events, rule, pricer, exceptions);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				 
			 }
		 }
		 if(liquidPosting != null)
		    events.add(liquidPosting);
		 
		return events;
	}


	// get all oldposting on same eventtype , accType and trade id where type is new.
	private void processGeneratedPosting(Vector<Posting> postings,Trade trade,EventProcessor event) {
		// TODO Auto-generated method stub
		Vector<Posting> oldposting = null;
			if( postings.isEmpty()) 
			return;
		for(int i=0;i<postings.size();i++) {
			Posting newPosting = postings.get(i);
			String sql = " tradeid = "+newPosting.getTradeID() + " and accEventType = '" + newPosting.getAccEventType() +
					      "' and type = 'NEW' and eventType = '" + newPosting.getEventType() +"' and accEventConfigID = "+newPosting.getAccEvtConfigId();
			if(event instanceof TransferEventProcessor) {
				//if(event.getEventType().contains("CANCELLED_")) 
					//System.out.println("In Cancell");
				sql  = " tradeid = "+newPosting.getTradeID() + " and accEventType = '" + newPosting.getAccEventType() +
					      "' and type = 'NEW' and transferid = " + event.getTransferID() +" and accEventConfigID = "+newPosting.getAccEvtConfigId();
			}
			try {
				oldposting = (Vector) remoteAccount.getPostingonWhereClause(sql);
				filterPosting(newPosting,oldposting,event);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	private void reversalPostingOnTradeTerminationAndCancel(TradeEventProcessor event,Vector<Posting> oldpostings,Vector<Posting> revpostings) {
		if(oldpostings == null || oldpostings.isEmpty()) {
			
		    return;
		}
		for(int i=0;i<oldpostings.size();i++) {
			  boolean isReverse = false;
				Posting oldposting = oldpostings.get(i);
				for(int r=0;r<revpostings.size();r++) {
					Posting revposting = revpostings.get(r);
					if(oldposting.getId() == revposting.getLinkId()) 
						 isReverse = true;
					
				}
				if(!isReverse) {
					oldposting.setlinkId(oldposting.getId());
					oldposting.setId(0);
					oldposting.setType("REVERSAL");					
					oldposting.setEventType(event.getEventType());
					processPosting(oldposting,"insert");
				}
				
			}
	}
	
	
   // filter out old posting which are linked with other posting as same are consider to be reversal with other posting
	private void filterPosting(Posting newPosting, Vector<Posting> oldpostings,EventProcessor event) {
		// TODO Auto-generated method stub
		if(oldpostings == null || oldpostings.isEmpty()) {
			processPosting(newPosting,"insert");
		    return;
		}
		// if oldposting exists check ist link with any other posting if not then its ok
		for(int i=0;i<oldpostings.size();i++) {
			try {
				Posting oldposting = oldpostings.get(i);
				String sql = " linkid = " +oldposting.getId();
				Vector<Posting> checkLinkid = (Vector) remoteAccount.getPostingonWhereClause(sql);
				if(checkLinkid.isEmpty() || checkLinkid == null) {
					newPosting.setId(oldposting.getId());
					if(event instanceof TransferEventProcessor) {
						if( oldposting.comparePostingTransfer(newPosting) != 0 ) { // if oldposting is not same as newposting reversal the oldposting and create new one.
							oldposting.setlinkId(oldposting.getId());
						//	oldposting.setEventType(newPosting.getEventType());
							oldposting.setId(0);
							oldposting.setType("REVERSAL");
							newPosting.setId(0);
							processPosting(oldposting,"insert");
							if(event.getProcessName().equalsIgnoreCase("BOOfficeProcess"))
							   processPosting(newPosting,"insert");
							
						}
					}
					if(event instanceof TradeEventProcessor) {
					if( oldposting.comparePostingTrade(newPosting) != 0 ) { // if oldposting is not same as newposting reversal the oldposting and create new one.
						oldposting.setlinkId(oldposting.getId());
						oldposting.setId(0);
						oldposting.setType("REVERSAL");
						newPosting.setId(0);
						processPosting(oldposting,"insert");
						processPosting(newPosting,"insert");
						
					}
					
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}


	private void processPosting(Posting newPosting, String type) { // transcation handling needed
		// TODO Auto-generated method stub
		try {
				if(type.equalsIgnoreCase("insert")) {
					 int posting =	remoteAccount.savePosting(newPosting);
						if(newPosting.getType().equalsIgnoreCase("REVERSAL")) {
							remoteAccount.updateLinkToPosting(posting, newPosting.getLinkId());
						}
				}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void generatePostingonAccEvents(EventProcessor event,Vector<BOPosting> events, Vector accEvtls,Trade trade,Vector<Posting> postings,AccConfigRule accRule ) {
		
		if(accEvtls == null || accEvtls.isEmpty())
			return;
		Hashtable<String,Vector<AccEventLink>> _accEventLinks = (Hashtable<String, Vector<AccEventLink>>) accEvtls.elementAt(0);
		
		Enumeration enu = _accEventLinks.keys();
		while(enu.hasMoreElements()) {
			String accType = (String) enu.nextElement();
			Vector<AccEventLink> accEventLinks = _accEventLinks.get(accType);
			int size = accEventLinks.size();
			
			for(int p=0;p<events.size();p++) {
				BOPosting boposting = events.get(p);
				if(boposting.getAccEventType().equalsIgnoreCase(accType)) {
					for(int counter = 0; counter < size;counter++) {
						
						boolean flag = getmatchingSignAccEvtLink(boposting,accEventLinks.get(counter));
						//System.out.println("--"+ accEventLinks.get(counter).getId());
						if(flag) {
							AccEventLink accEventLink  =	accEventLinks.get(counter);
							fillPosting(boposting,accEventLink,postings,trade,event,accRule);
							accEventLinks.remove(counter);
							size = accEventLinks.size();
							counter = 0;
							//System.out.println("Removed"+ accEventLinks.get(counter).getId());
						} 
					}
				}
			}
			
			
		}
		
	
		
	}
	
	
	private boolean getmatchingSignAccEvtLink(BOPosting boposting,AccEventLink accEventLink) {
		
	    boolean flag = false;
		if(accEventLink.getPlusmius() == 0) {
			if(boposting.getAmount() > .0) {
				flag = true;
			}
		}
		if(accEventLink.getPlusmius() == 1) {
			if(boposting.getAmount() < .0) {
				flag = true;
			}
		}
		return flag;
	}
	
	
	private Vector<AccEventLink> getAccEvtLinks(Hashtable<String,Vector<AccEventLink>> _accEventLinks,String name ) {
		 Vector<AccEventLink> accEvntLinks  = null;
		 if(_accEventLinks.containsKey(name)) {
			 accEvntLinks = _accEventLinks.get(name);
		 }
		 
		 return accEvntLinks;
	}
	
	
	private void fillPosting(BOPosting boposting,AccEventLink accEventLink ,Vector<Posting> postings,Trade trade,EventProcessor event,AccConfigRule rule) {
		Posting posting = new Posting();
		posting.setId(0);
		posting.setTradeID(trade.getId());
		posting.setRuleName(rule.getRuleName());
		posting.setAccEventType(boposting.getAccEventType());
		posting.setlinkId(0);
		
		posting.setTransferId(event.getTransferID());
		posting.setEventType(event.getEventType());
		posting.setDebitAccount(accEventLink.getDebitaccount());
		posting.setCreditAccount(accEventLink.getCreditaccount());
		posting.setEffectiveDate(boposting.getEffectiveDate());
		posting.setCreationDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
		posting.setBookingDate(boposting.getBookingDate());
		posting.setStatus("NEW");
		posting.setType("NEW");
		if(accEventLink.getPlusmius() == 0) {
			if(boposting.getAmount() > .0) {
				posting.setCreditAmount(Double.parseDouble(commonUTIL.doubleFormat(boposting.getAmount())));
				posting.setDebitAmount(Double.parseDouble(commonUTIL.doubleFormat(boposting.getAmount())));
			} else {
				return;
			}
		}
		if(accEventLink.getPlusmius() == 1) {
			if(boposting.getAmount() < .0) {
				posting.setCreditAmount(Double.parseDouble(commonUTIL.doubleFormat(boposting.getAmount())));
				posting.setDebitAmount(Double.parseDouble(commonUTIL.doubleFormat(boposting.getAmount())));
			} else {
				return;
			}
		}
		
		posting.setCurrency(boposting.getCurrency());
		posting.setAccEvtConfigId(accEventLink.getId());
		postings.addElement(posting);
		
		
	}
	
	private Vector  getAccountEventLinksonRule(int ruleID) {
		 Vector<AccEventLink> accEventLinks = null;
		 Vector accEvent = null;
		 try {
			 accEventLinks = ( Vector<AccEventLink>)	remoteAccount.getAccEventLinkonrule(ruleID);
			 accEvent =  generateAccLinks(accEventLinks); // caching can be done for this. 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return accEvent;
	}
	
	
	private Vector<AccEventLink> generateAccLinks(Vector<AccEventLink> accEventLinks) {
		if(accEventLinks == null || accEventLinks.isEmpty())
			return null;
		Vector<AccEventLink> accEvts = new Vector<AccEventLink>();
		Hashtable<String,Vector<AccEventLink>> _accEventLinks = new Hashtable<String,Vector<AccEventLink>> ();
		String accEventType = ((AccEventLink) accEventLinks.elementAt(0)).getAccEvent();
		
		for(int i=0;i<accEventLinks.size();i++) {
			AccEventLink accEvtL = 	(AccEventLink) accEventLinks.elementAt(i);
			if(accEvtL.getAccEvent().equalsIgnoreCase(accEventType)) {
				accEvts.add(accEvtL);
			} else {
				_accEventLinks.put(accEventType, accEvts);
				accEvts =   new Vector<AccEventLink>();
				accEventType = accEvtL.getAccEvent();
				accEvts.add(accEvtL);
				_accEventLinks.put(accEventType, accEvts);
				
			}
		}
		_accEventLinks.put(accEventType, accEvts);
		Vector accLinks = new Vector();
		accLinks.add(_accEventLinks);
		return accLinks; // this can be done in cache
		
		
	}
	
	
	private  Vector<BOPosting> generateAccountEvents(AccountingHandler accHandler,EventProcessor event,Trade trade, Vector<AccConfigRule> rules  ,Pricer pricer,Vector<AccEventConfig> accEventConfig) {
		 Vector<BOPosting>  events = new Vector<BOPosting> ();
		 Vector exceptions = new Vector();
		     
		 for(int r=0;r<rules.size();r++) {
			 AccConfigRule rule = rules.get(r);
		 
			 for(int a=0;a<accEventConfig.size();a++) {
				 AccEventConfig aEventConfig = accEventConfig.get(a);
				 try {
					Vector<String>  trigEvent=(Vector<String>) remoteAccount.getAccountTriggerEvts(aEventConfig.getId());
					if(trigEvent.contains(event.getEventType()))
						 accHandler.invokeMethodOnHandler(accHandler, aEventConfig, trade, event, events, rule, pricer, exceptions);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				 
			 }
		 }
		 
		 
		return events;
	}
	
	
	
	private boolean containsTriggEvents(Vector<String>  trigEvent,String eventType) {
		boolean flag = false;
		if((trigEvent != null) && (!trigEvent.isEmpty())) {
			for(int i=0;i<trigEvent.size();i++) {
				String trigEvt = (String) trigEvent.get(i);
				if(trigEvt.equalsIgnoreCase(eventType)) {
					flag = true;
					break;
				}
			}
			
		}
		return flag;
	}
	
	
	private Vector<AccConfigRule> getRules(String sql,int id) {
		Vector<AccConfigRule> rules = null;
		try {
      	  synchronized(_rules) {
      		rules =  _rules.get(sql);
      	  }
      	if(rules == null) {
      		rules = 	(Vector<AccConfigRule>) remoteAccount.getRulesLinkonFolders(sql);
      		if(rules == null) {
      			commonUTIL.display("AccountProcessor", "process Posting for trade "+ id+ " rules missing <<<<<<< " );
      		} else  {
      		_rules.put(sql,rules);
      		
      		}
      		if(rules != null && (!rules.isEmpty()))
      			commonUTIL.display("AccountProcessor", "getRules Rules found for trade " + id);
      	}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		 commonUTIL.displayError("AccountProcessor", "getRules" ,e);
		}
		return rules;
	}
	
	private AccountingHandler getAccountingHandler(String name) {
        String productAccountingHandler = "bo.account."  + name.toUpperCase() + "AccountingHandler";
        AccountingHandler accountHandler = null;
        
        try {
        	  synchronized(_accountingHanlders) {
        	accountHandler = (AccountingHandler) _accountingHanlders.get(name);
        	  }
        	if(accountHandler == null) {
        Class class1 =    ClassInstantiateUtil.getClass(productAccountingHandler,true);
        accountHandler =  (AccountingHandler) class1.newInstance();
        if ( accountHandler == null) 
        	commonUTIL.display("AccountProcessor", "getAccountingHandler for Product "+ name + " <<<<<<< " );
        else 
        _accountingHanlders.put(productAccountingHandler, accountHandler);
        commonUTIL.display("AccountProcessor", "getAccountingHandler accountingHolder Found for Product " + name);  
        }
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
        	commonUTIL.displayError("AccountProcessor", "getAccountingHandler <<<<< not able to create Handler ", e);
        }

        return accountHandler;
    }
	
	private Pricer getProductPricer(String name) {
        String pricerName = "productPricing."  + name.toUpperCase() + "Pricing";
        Pricer pricer = null;
        
        try {
        	  synchronized(_pricers) {
        		  pricer = (Pricer) _pricers.get(name);
        	  }
        	if(pricer == null) {
        Class class1 =    ClassInstantiateUtil.getClass(pricerName,true);
        pricer =  (Pricer) class1.newInstance();
        if ( pricer == null) 
        	commonUTIL.display("AccountProcessor", "getAccountingHandler Pricer for Product "+ name + " not found <<<<<<< " );
        else 
        	_pricers.put(pricerName, pricer);
        commonUTIL.display("AccountProcessor", "getAccountingHandler Pricer  Found for Product " + name);  
        }
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
        	commonUTIL.displayError("AccountProcessor", "getAccountingHandler <<<<< not able to create Pricer  ", e);
        }

        return pricer;
    }
	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}


	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}


	public RemoteBOProcess getRemoteBOProcess() {
		return remoteBOProcess;
	}


	public void setRemoteBOProcess(RemoteBOProcess remoteBOProcess) {
		this.remoteBOProcess = remoteBOProcess;
	}


	public RemoteReferenceData getRefData() {
		return refData;
	}


	public void setRefData(RemoteReferenceData refData) {
		this.refData = refData;
	}


	public RemoteAccount getRemoteAccount() {
		return remoteAccount;
	}


	public void setRemoteAccount(RemoteAccount remoteAccount) {
		this.remoteAccount = remoteAccount;
	}
}
