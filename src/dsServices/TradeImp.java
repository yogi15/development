package dsServices;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import mqServices.messageProducer.CreateNewMessage;
import util.commonUTIL;
import wfManager.WFHandler;
import apps.window.tradewindow.util.FXSplitUtil;
import beans.Attribute;
import beans.Audit;
import beans.B2BConfig;
import beans.Book;
import beans.Fees;
import beans.LegalEntity;
import beans.Product;
import beans.Sdi;
import beans.Task;
import beans.Trade;
import beans.TransferRule;
import beans.Users;
import beans.WFConfig;
import bo.transfer.rule.ProductTransferRule;
import dbSQL.AttributSQL;
import dbSQL.AuditSQL;
import dbSQL.BookSQL;
import dbSQL.EventSQL;
import dbSQL.FeesSQL;
import dbSQL.LegalEntitySQL;
import dbSQL.ProductSQL;
import dbSQL.SdiSQL;
import dbSQL.TaskSQL;
import dbSQL.TradeCustomXFerRuleSQL;
import dbSQL.TradeSQL;
import dbSQL.WFConfigSQL;
import dbSQL.dsSQL;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TaskEventProcessor;
import dsEventProcessor.TradeEventProcessor;
//import bo.transfer.BOTransfer;

public class TradeImp implements RemoteTrade {

	CreateNewMessage newMessage = null;
	RemoteReferenceData remoteRef = null;
	boolean startSendingPositionMess = false;
	final static String cancelStatus = "CANCELLED";
	WFHandler wfhandler = null;
	Hashtable<String, ProductTransferRule> handlers = new Hashtable<String, ProductTransferRule>();
	 public static  ServerConnectionUtil de = null;
	 RemoteLimit remoteLimit = null;
	public TradeImp() {
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
		
	}
	
	

	@Override
	public void publishnewTrade(String messageIndicator,String messageType,Object object) throws RemoteException {
	//	System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPP   ");
		try{   
			if(getNewMessage() == null) {
					startProducingMessage();
					EventProcessor e1 = (EventProcessor) object;
					if(e1.isSavetoDB()) {
						e1 = EventSQL.save((EventProcessor) object, dsSQL.getConn());
					}
					newMessage.produceNewMessage(messageIndicator,"TRADE",messageType,(Serializable) e1,null); 
			} else {
				EventProcessor event = (EventProcessor) object;
				if(event.isSavetoDB()) {
					
					event = EventSQL.save((EventProcessor) object, dsSQL.getConn());
				 
				}
				newMessage.produceNewMessage(messageIndicator,"TRADE",messageType,(Serializable) event,null); 
			 
			}
			}catch(Exception e){
					commonUTIL.displayError("TradeImp", "publishnewTrade", e);
					}   
	//de.publishEvent(messageIndicator,"TRADE",messageType,(Serializable) object);
		} 
		
	

	@Override
	public boolean removeTrade(Trade trade) throws RemoteException {
		
		 if(TradeSQL.delete(trade, dsSQL.getConn())) {
			 
			publishnewTrade("POS","Object",getTradeEvent(trade));
		   return true;
		 }  else {
			   return false;
		   }
	}
	@Override
	public Vector saveTrade(Trade trade, Vector<String> message)
			throws RemoteException {
		
		int i =0;
		
		Vector returnStatus = new Vector();
		Trade originalTrade = null;
		try {
		 	
			if(isTradeCancel(trade))   {
				//message.add(new String("Amend not allowed on Cancel Trade"));
				
				returnStatus.add(new String("Amend not allowed on Cancel Trade"));
				returnStatus.add(new Integer(-3));
				return returnStatus;
				
			}
				
			if(isTradeLock(trade)) {
				//message.add(new String("Trade is Lock by another User"));
				
				returnStatus.add(new String("Trade is Lock by another User"));
				commonUTIL.display("TradeImp", "Not an authorised User to changed Action on Trade " );
				returnStatus.add(new Integer(-4));
				
				return returnStatus;
				
				
			}
			/*if(!isAuthorisedUserAction(trade)) {
				
				returnStatus.add(new String("Not an authorised User to changed Action on Trade "));
				commonUTIL.displayError("TradeImp", "Not an authorised User to changed Action on Trade ", new Exception());
				returnStatus.add(new Integer(-6));
				
				return returnStatus;
				
			}*/
			
			/*if(trade.getMirrorBookid() > 0) {
				originalTrade = (Trade) trade.clone();
           		generateMirrorTrade(trade,false);
           	 }
			if(trade.isB2Bflag()) {
           		generateB2BTrade(trade);
           	 } */
			
			   String productType = null; 
			      Trade oldTrade = trade;
		            Vector fees = trade.getFees();
		            Vector<TransferRule> customTransferRules = trade.getCustomtransferRules();
		          //  System.out.println("fees size " + fees.size());
					WFConfig wf = getStatusOnTradeAction(trade,trade.getStatus(),returnStatus);
					if(wf == null) {
						
						commonUTIL.displayError("TradeImp", "SaveTrade wf is null for trade "+trade.getId() + " at  " + trade.getStatus() + " on action " + trade.getAction() , new Exception());
						returnStatus.add(new String(" Action "+ trade.getAction() + " not Valid on status "+ trade.getStatus()));
						returnStatus.add(new Integer(-10));
						return returnStatus;
					}
					if(wf.getDiffUser() == 1) {
						if(trade.getId() > 0)
							if(isDifferentUser(trade)) {
							
							 	returnStatus.add(new String("Same User not allowed to perfomed " + trade.getAction() + " Action  on Trade "));
							 	 returnStatus.add(new Integer(-9));
								return returnStatus;
									 	
						 }
					}
					if(returnStatus.size() > 0) {
						returnStatus.add(new Integer(-10));
						return returnStatus;
					}
					trade.setStatus(wf.getOrgStatus());
					if(trade.getTradedesc1().equalsIgnoreCase("FXTAKEUP")) {
						trade.setPositionBased(false);
					}
				i	= TradeSQL.save(trade, dsSQL.getConn());
					if( i > 0)  {
						if(originalTrade != null)
							fees.clear();
						processFees(fees,i);
						
						trade = (Trade) TradeSQL.select(i, dsSQL.getConn());
						if(trade.isCustomRuleApply()) 
							processCustomRules(customTransferRules,i);
						//productType = ( ProductSQL.selectProduct(trade.getProductId(), dsSQL.getConn())).getProductType();
						processAttribues(trade.getId(), trade.getAttributes(), trade.getVersion());
						
						
						
					//	trade.setId(i);
						Task task = checkTask(trade,wf,oldTrade);
						if(task  != null) {
						    int taskID = TaskSQL.save(task,dsSQL.getConn());
						    if(taskID >0 )
						    	task.setId(taskID);
						}
						//} else {
							//TaskSQL.update(task, dsSQL.getConn());	
							//TaskSQL.save(processTask(trade),dsSQL.getConn());
							
						//}
						
						trade.setFees(fees);
						//publishnewTrade(trade);
						
					
						prcessAudit(trade);
					
						
						//processTask(trade,wf);
					   //publishnewTrade("NEWTRADE","Text",null);
						
					   publishnewTrade("POS_NEWTRADE","Object",getTradeEvent(trade));
					   if(task != null)
							 publishnewTrade("POS_NEWTRADE","Object",getTaskEvent(task, trade));
							
					   if(originalTrade != null) {
						 //  originalTrade.setAttribute("B2BID",  Integer.valueOf(i).toString());
						 //  prcessAudit(originalTrade);
					   }
					  // System.out.println("Publishing %^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    neew Event on TRADE_"+trade.getStatus() +  " for trade id " + trade.getId());
						commonUTIL.display("TradeImpl", "Save :: Method Trade save "+i);
					//	message.add(new String("Trade save with ID "+i));
						//message.add(new String("Trade save with ID "+i));
 						returnStatus.add(new String("Trade save with ID "+i));
						returnStatus.add(new Integer(i));
					} else {
						return returnStatus;
					}
					
					
		}	catch(Exception e) {
						commonUTIL.displayError("TradeImpl", "Save", e);
						return returnStatus;
					}
		//return false;
		return returnStatus;
	}
	
	

	


  // This is method is used to save customTransferRUle  if CustomRule applied 
	private void processCustomRules(Vector<TransferRule> customTransferRules,
			int tradeID) {
		// TODO Auto-generated method stub
		
		if(tradeID > 0 && !commonUTIL.isEmpty(customTransferRules)) {
			TradeCustomXFerRuleSQL.delete(customTransferRules.get(0), dsSQL.getConn()); // first delete customTransfer from table for that trade 
			for(int i=0;i<customTransferRules.size();i++) {
				TransferRule rule = customTransferRules.get(i);
				rule.set_tradeId(tradeID);
				TradeCustomXFerRuleSQL.save(rule, dsSQL.getConn());
			}
			
		}
	}



	private boolean isAuthorisedUserAction(Trade trade) {
		
		boolean flag = false;
		String productsubtype = "";
	  try {
		  if(remoteRef == null) {
				
				remoteRef  = (RemoteReferenceData) de.getRMIService("ReferenceData");
			
 }
		 if(trade.getProductType().equalsIgnoreCase("FX")) {
			 productsubtype = trade.getTradedesc1();
		 } else {
			 productsubtype = trade.getTradedesc1();
		 }
		Users user = (Users)  remoteRef.selectUser(trade.getUserID());
		//trade.getStatus() +  trade.getAction() + user.getUser_groups();
		String groups = user.getUser_groups();
		if(groups.contains(";")) {
			groups = groups.replace(";", "','");
			groups = groups.substring(0, groups.length()-3);
		}
		String sql = "groupname in( '" +groups  + "') and producttype = '"+ trade.getProductType() + "' and productsubtype = '" + productsubtype
				 + "' and action = '"+ trade.getAction() +"' and currentstatus = '" +trade.getStatus() +"'"; 
		
		Collection result = remoteRef.selectWFWhere(sql);
		
		if (result.size() > 0) {
			
			flag = true;
			
		}
		
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
	      commonUTIL.displayError("TradeImp", "isAuthorisedUserAction", e);
	}
		return flag;
	}



	private void processAttribues(int tradeId, String attributes,int versionID) {
		
		
		if (!commonUTIL.isEmpty(attributes)) {
			Attribute deleteAt = new Attribute();
			deleteAt.setId(tradeId);
			AttributSQL.delete(deleteAt, dsSQL.getConn());
			
			String atttoken [] = attributes.trim().split(";"); 	
			
			for(int i =0;i<atttoken.length;i++) {
				String att = (String) atttoken[i];
				if(att.contains("=")) {
						String attvalue = att.substring(att.indexOf('=')+1, att.length());
						String attnameName = att.substring(0, att.indexOf('='));
						Attribute atBean = new Attribute();
						atBean.setId(tradeId);
						atBean.setName(attnameName);
						atBean.setValue(attvalue);
						atBean.setType("Trade");
						if(!commonUTIL.isEmpty(attvalue)) {
							/*if(versionID == 1)
						       AttributSQL.save(atBean, dsSQL.getConn());
							else 
								AttributSQL.update(atBean, dsSQL.getConn());*/
							
							
							AttributSQL.save(atBean, dsSQL.getConn());
						}
				}
				}
			
		}
		
		
	}
  // this method is to save split trades only
    private void saveSplitTrade(Trade trade) {
    	try {
    	int i =0;
    	Vector statusTrade = new Vector();
    	WFConfig wf = getStatusOnTradeAction(trade,trade.getStatus(),statusTrade);
		
		trade.setStatus(wf.getOrgStatus());
    	i	=  TradeSQL.save(trade, dsSQL.getConn());
    	trade = (Trade) TradeSQL.select(i, dsSQL.getConn());
    	
    	processAttribues(i, trade.getAttributes(),trade.getVersion());
    	Trade oldTrade = trade;
    	Task task = checkTask(trade,wf,oldTrade);
		if(task  != null) {
		    int taskID = TaskSQL.save(task,dsSQL.getConn());
		    if(taskID >0 )
		    	task.setId(taskID);
		}
		prcessAudit(trade);
		if(task != null)
				publishnewTrade("POS_NEWTRADE","Object",getTaskEvent(task, trade));
			
			//System.out.println("Publishing %^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    neew Event on TRADE_"+trade.getStatus() +  " for trade id " + trade.getId());
		   publishnewTrade("POS_NEWTRADE","Object",getTradeEvent(trade));
		  
			commonUTIL.display("TradeImpl", "saveSplitTrade :: Method Trade save "+i);
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public int saveTrade(Trade trade) throws RemoteException {
		
		
		int i =0;
		Vector statusTrade = new Vector();
		try {
			
			if(isTradeCancel(trade))  
				return -3;
			if(isTradeLock(trade))
				return -4;
			
			   String productType = null; 
			      Trade oldTrade = trade;
		            Vector fees = trade.getFees();
		          //  System.out.println("fees size " + fees.size());
					WFConfig wf = getStatusOnTradeAction(trade,trade.getStatus(),statusTrade);
					
					trade.setStatus(wf.getOrgStatus());
				i	= TradeSQL.save(trade, dsSQL.getConn());
					if( i > 0)  {
						processFees(fees,i);
						trade = (Trade) TradeSQL.select(i, dsSQL.getConn());
						
					/*	if (oldTrade.getProductType().equals(TradeConstants.TRADE_TYPE_FUTURECONTRACT)) {
							productType = TradeConstants.TRADE_TYPE_FUTURECONTRACT;
						} else {*/
						//	productType = ( ProductSQL.selectProduct(trade.getProductId(), dsSQL.getConn())).getProductType();
							
						//}
						
						
						processAttribues(i, trade.getAttributes(),trade.getVersion());
					//	trade.setId(i);
						Task task = checkTask(trade,wf,oldTrade);
						if(task  != null) {
						    int taskID = TaskSQL.save(task,dsSQL.getConn());
						    if(taskID >0 )
						    	task.setId(taskID);
						}
						//} else {
							//TaskSQL.update(task, dsSQL.getConn());	
							//TaskSQL.save(processTask(trade),dsSQL.getConn());
							
						//}
						
						trade.setFees(fees);
						//publishnewTrade(trade);
						
					
						prcessAudit(trade);
						//processTask(trade,wf);
					   //publishnewTrade("NEWTRADE","Text",null);
						if(task != null) {
						//	System.out.println("task  ************** " +task.getId() );
						 publishnewTrade("POS_NEWTRADE","Object",getTaskEvent(task, trade));
						}
						
							
						//System.out.println("Publishing %^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    neew Event on TRADE_"+trade.getStatus() +  " for trade id " + trade.getId());
					   publishnewTrade("POS_NEWTRADE","Object",getTradeEvent(trade));
					  
						commonUTIL.display("TradeImpl", "Save :: Method Trade save "+i);
						return i;
					} else {
						return 0;
					}
					
					
		}	catch(Exception e) {
						commonUTIL.displayError("TradeImpl", "Save", e);
						return 0;
					}
		//return false;
	}

	
	private boolean isTradeLock(Trade trade) {
		
		boolean flag = true;
	   if(!TaskSQL.checkLockOnTrade(trade, dsSQL.getConn())) 
		   return false;
	   return flag;
	   
	}



	private TaskEventProcessor getTaskEvent(Task task,Trade trade) {
		TaskEventProcessor taskEvent = new TaskEventProcessor();
		taskEvent.setTask(task);
		taskEvent.setTrade(trade);
		taskEvent.setTaskID(task.getId());
		taskEvent.setTradeID(trade.getId());
		taskEvent.setTradeVersion(trade.getVersion());
		taskEvent.setUserID(trade.getUserID());
		taskEvent.setType("Task");
		taskEvent.setEventType("TaskEventProcessor");
		taskEvent.setObjectID(task.getId());
				return taskEvent;
	}
	
	private boolean isTradeCancel(Trade trade) {
		boolean  isCancel = true;
		
		String status = trade.getStatus();
		if(!status.equalsIgnoreCase(cancelStatus))
		     isCancel = false;
		
		return isCancel;
	}



	private TradeEventProcessor getTradeEvent(Trade trade) {
		
		TradeEventProcessor tradeEvent = new TradeEventProcessor();
		 tradeEvent.setTrade(trade);
		 tradeEvent.setTradeID(trade.getId());
		 tradeEvent.setEventType("TRADE_"+trade.getStatus());
		 tradeEvent.setType("Trade");
		 tradeEvent.setObjectID(trade.getId());
		 tradeEvent.setObjectVersionID(trade.getVersion());
		 tradeEvent.setPublish(true);
		 tradeEvent.setSavetoDB(true);
		 tradeEvent.setProcessName("TradeProcess");
		 //tradeEvent.setUser(new Userstrade.getUserID());
		 tradeEvent.setComments(" Trade  " +trade.getId() +  " for Action "+trade.getAction() );
		 return tradeEvent;
	}
	
	private void processFees(Vector Fees,int tradeid) {
		
		
		Vector existsingFees =(Vector) FeesSQL.selectFeesOnTrades(tradeid, dsSQL.getConn());
		if((existsingFees != null && existsingFees.size() >0) && (Fees == null || Fees.size() == 0))
			deleteFeesnotExists(existsingFees,Fees);
		if(Fees == null || Fees.size() == 0) 
			return;
		for(int i=0;i<Fees.size();i++) {
			Fees nfee = (Fees) Fees.elementAt(i);
			if(nfee.getId() == 0) {
				nfee.setTradeID(tradeid);
				insertFeesOnTrade(nfee);
			} else {
				updateFeesOnTrade(nfee);
			}
			
		}
		deleteFeesnotExists(existsingFees,Fees);
		
		
	}


	private void insertFeesOnTrade(Fees nfee) {
		
		FeesSQL.save(nfee, dsSQL.getConn());
	}
	
	private void updateFeesOnTrade(Fees nfee) {
		
		FeesSQL.update(nfee, dsSQL.getConn());
	}

	@Override
	   public Collection getOnlyAction(Trade trade) throws RemoteException {
		   Product product = ProductSQL.selectProduct(trade.getProductId(), dsSQL.getConn());
			String whereClause = "productType ='" + product.getProductType() + "' and productSubType = '"+ product.getProdcutShortName() + "' and currentstatus = '" + trade.getStatus() + "' and type ='TRADE'";
			Vector v1 = (Vector)  WFConfigSQL.selectWhere(whereClause, dsSQL.getConn());
			return v1;
	   }


	private void deleteFeesnotExists(Vector existingFees,Vector newFees)  {
		
		if((existingFees != null && existingFees.size() >0) &&  (newFees == null || newFees.size() == 0))  {
			for(int i=0;i<existingFees.size();i++) {
				Fees efee = (Fees) existingFees.elementAt(i);
				deleteFeesOnTrade(efee);
			}
			return;
		}
			
		
		if(existingFees == null || existingFees.size() == 0) 
			return;
		boolean deleteFlag = false;
		Fees feetodelete = null;
		Iterator<Fees> efees = existingFees.iterator();
		int d =0;
		while(efees.hasNext()) {
			Fees efee = (Fees) efees.next();
			deleteFlag = false;
			for(int i=0;i<newFees.size();i++) {
				Fees nfee = (Fees) newFees.elementAt(i);
				if(efee.getId() == nfee.getId()) {
					deleteFlag = true;
					
				}
								}
			if(!deleteFlag) {
			  deleteFeesOnTrade(efee);
			
			}
		}
	}

	private void deleteFeesOnTrade(Fees efee) {
		
		FeesSQL.delete(efee, dsSQL.getConn());
	}



	
	
	
	private void prcessAudit(Trade trade) {
		try {
		
		String currentDateTime= util.commonUTIL.getCurrentDateTime();
		// trade.getVersion();
		 
		if(trade.getVersion() == 1) {
			Audit audit = new Audit();
			audit.setChangeDate(currentDateTime);
			audit.setFieldname("--");
			audit.setTradeid(trade.getId());
			audit.setType("NEW");
			audit.setUserid(trade.getUserID());
			audit.setVersion(trade.getVersion());
			//System.out.println(trade.getValues());
			audit.setValues(trade.getValues());
			audit.setTattribue(trade.getAttributes());
			AuditSQL.save(audit,  dsSQL.getConn());
		} else {
			
			Vector v1 = (Vector) AuditSQL.selectLatestTradeVersion(trade.getId(), dsSQL.getConn());
			
			String oldTradeValues = ((Audit)v1.elementAt(0)).getValues();
			String newTradeValues = trade.getValues();
			//System.out.println();
			String changevalues = getChangeValues(oldTradeValues,newTradeValues);
			if(changevalues.contains("quantity") || changevalues.contains("tradeamount") || changevalues.contains("price") || changevalues.contains("type") ) {
				trade.setEconomicChanged(true);
			}
			if (trade.getProductType().equals("FX") && changevalues.contains("delieveryDate")) {
				trade.setEconomicChanged(true);
			}
			if(trade.getTradedesc1().equalsIgnoreCase("FXSwap")) {
				if(changevalues.contains("FarAmt2") || changevalues.contains("Nominal") || changevalues.contains("FarRate")) {
					trade.setEconomicChanged(true);
				}
			}
			Audit audit = new Audit();
			audit.setChangeDate(currentDateTime);
			audit.setFieldname(changevalues);
			audit.setTradeid(trade.getId());
			audit.setType("UPDATE");
			audit.setUserid(trade.getUserID());
			audit.setVersion(trade.getVersion());
			audit.setValues(trade.getValues());
			if(trade.getAttributes() !=null) {
				String auditAttribures = ((Audit)v1.elementAt(0)).getTattribue();
				if(auditAttribures == null) {
					audit.setTattribue(trade.getAttributes().trim());
				
				} else {
					if(!auditAttribures.equalsIgnoreCase(trade.getAttributes())) {
						audit.setTattribue(trade.getAttributes());
					}
				}
				}
			
			else {
				audit.setTattribue("");
			}
			AuditSQL.save(audit,  dsSQL.getConn());
			
		}
	}	catch(Exception e) {
		commonUTIL.displayError("TradeImpl", "ProcessAudit", e);
		
	}
		
		
	}

	private String getChangeValues(String OldTradeValues,
			String tNewTradeValues) {
		String changeColumn = "";
		try {
		System.out.println(" OldTradeValue = "+ OldTradeValues)	;
		System.out.println(" tNewTradeValues = "+ tNewTradeValues);
		String oldtoken [] = OldTradeValues.trim().split(";");
		String newtoken [] = tNewTradeValues.trim().split(";");
	//	System.out.println(Arrays.toString(oldtoken));
		
		for(int i =0;i<oldtoken.length;i++) {
			String ovalue = "";
			String nvalue = "";
			String old = (String) oldtoken[i];
			String ne = (String) newtoken[i];
			if(old.length() > 0)
			    ovalue = old.substring(old.indexOf('='), old.length());
			if(ne.length() > 0)
			nvalue = ne.substring(ne.indexOf('='), ne.length());
			
			
		//	System.out.println(" old " + old + " value "+ ovalue);
			//System.out.println(" ne " + ne + " value "+ nvalue);
			
			if(!ovalue.equalsIgnoreCase(nvalue)) {
				if(old.length() > 0)
				changeColumn = changeColumn + old.substring(0, old.indexOf('=')) + ",";
				else 
					changeColumn = changeColumn + ne.substring(0, ne.indexOf('=')) + ",";
			}
			
		}
		return changeColumn;
		}catch(Exception e) {
			commonUTIL.displayError("TradeImpl", "getChangeValues " + changeColumn ,    e);
			return "";
		}
		
	}
	
	// 1. Get new Task from NewTrade.
	// 2. Check any old task which is existing in task station or db on oldtrade version.
	// 3. Mark old task on oldtrade version as 2 so that it will not process by task station. 

	private Task checkTask(Trade newTrade,WFConfig wf,Trade oldTrade) {
		
		Task t = null;
		{
			
			
			if(wf.isTask()) {
				 t = processTask(newTrade,wf.getId());
			} 
			if(oldTrade.getId() != 0 ) {
				  TaskSQL.updateCompletedTradeTask(oldTrade.getId(),oldTrade.getVersion(),dsSQL.getConn());  //need to pass system user TOBE done later
				 
			}
		}
		return t;
	}

	private Task processTask(Trade trade,int wfcID) {
		
		Task task = new Task();
		task.setUserid(trade.getUserID());
		task.setTradeID(trade.getId());
		task.setProductID(trade.getProductId());
		task.setType(trade.getStatus());
		task.setAction(trade.getAction());
		task.setStatus(trade.getStatus());
		task.setTaskDate(commonUTIL.dateToString(commonUTIL.getCurrentDate()));
		task.setTradeDate(trade.getTradeDate());
		task.setTaskstatus("0");
		task.setUserid(trade.getUserID());
		task.setCurrency(trade.getCurrency());
		task.setBookid(trade.getBookId());
		task.setEvent_type("TRADE_"+trade.getStatus());
		task.setType("TRADE");
		task.setProductType(trade.getProductType());
		task.setTradeVersionID(trade.getVersion());
		task.setTransferVersionID(0);
		task.setWFConfigID(wfcID);
		task.setId(0);
		//task.se
		
		return task;
		
		
	}

	@Override
	public Collection selectALLTrades() throws RemoteException {
		
		return TradeSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Trade selectTrade(int tradeID) throws RemoteException {
		
		Trade trade = TradeSQL.select(tradeID, dsSQL.getConn());
		if(trade == null)
			return null;
		if(trade != null) {
		Vector fees = (Vector) FeesSQL.selectFeesOnTrades(tradeID, dsSQL.getConn());
		
		trade.setAttributes(selectTradeAttributesAsString(tradeID + ""));
		
		if(fees != null)
		    trade.setFees(fees);
		}
		if(trade.getProductId() > 0) {
		    Product product =     ProductSQL.selectProductWithCoupons(trade.getProductId(), dsSQL.getConn());
		    trade.setProduct(product);
		}
		return trade;
		
	}

	@Override
	public boolean updateTrade(Trade trade) throws RemoteException {
		
		return TradeSQL.update(trade, dsSQL.getupdateConn());
	}
	
	/*private Transfer processTransfer(Trade trade,String productType) {
		
		BOTransfer botransfer = getTransferObject(productType);
		
		return botransfer.generateTransfer(trade);
		
	}
	private Posting processPosting(Trade trade,Transfer transfer,String productType) {
		
		BOPosting posting = getPostingObject(productType);
		
		return posting.generatePosting(trade, transfer);
		
	}
	private BOTransfer getTransferObject(String name) {
        String transferName= "bo.transfer.Generate"  + name + "Transfer";
        BOTransfer botransfer = null;
        
        try {
        Class class1 =    ClassInstantiateUtil.getClass(transferName,true);
        botransfer =  (BOTransfer) class1.newInstance();
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
            System.out.println( e);
        }

        return botransfer;
    } */
	
	

	@Override
	public Collection getAction(Trade trade) throws RemoteException {
		
		
		Product product = ProductSQL.selectProduct(trade.getProductId(), dsSQL.getConn());
		String whereClause = "productType ='" + product.getProductType() + "' and productSubType = '"+ product.getProdcutShortName() + "' and currentstatus = '" + trade.getStatus() + "' and action = '" + trade.getAction() + "' and type ='TRADE'";
		return getAction(whereClause);
		
	}

	@Override
	public Collection getAction(String whereClause) throws RemoteException {
		
		String sqls = whereClause;
		System.out.println(sqls);
		Vector v1 = (Vector)  WFConfigSQL.selectWhere(whereClause, dsSQL.getConn());
		if(v1 == null || v1.isEmpty()) {
		   if(sqls.contains("1")) {
			   sqls = sqls.replace('1','0');
				v1 = (Vector)  WFConfigSQL.selectWhere(sqls, dsSQL.getConn());
		   }
		   
		}
		for(int i=0;i<v1.size();i++) {
				WFConfig wfconfig = (WFConfig) v1.elementAt(i); 
				if(!wfconfig.getCurrentStatus().equalsIgnoreCase(wfconfig.getOrgStatus())) {
				if(wfconfig.getAuto() == 1) {
					sqls = "productType ='" + wfconfig.getProductType() + "' and productSubType = '"+ wfconfig.getProductSubType() + "' and currentstatus = '" + wfconfig.getOrgStatus() + "' and auto = " + wfconfig.getAuto() + " and type ='" +wfconfig.getType()+"'";
				v1 = 	(Vector)  getAction(sqls);
				
				} else {
					sqls = "productType ='" + wfconfig.getProductType() + "' and productSubType = '"+ wfconfig.getProductSubType() + "' and currentstatus = '" + wfconfig.getCurrentStatus()+ "' and auto = " + wfconfig.getAuto() + " and type ='" +wfconfig.getType()+"'";
					//v1 = 	(Vector)  getAction(sqls);
				}
				}
				}
		
		
	
		return v1;
	}
	
	@Override
	public Collection getAllActionsOnStatus(String whereClause) throws RemoteException {
		
		
		return  WFConfigSQL.selectWhere(whereClause, dsSQL.getConn());
		
		
	}
	
	
	
	public WFConfig getStatusOnTradeAction(Trade trade,String status,Vector statusTrade) {
		
		Product product = ProductSQL.selectProduct(trade.getProductId(), dsSQL.getConn());
			
		String whereClause = " productType ='" + product.getProductType() + "' and productSubType = '"+ product.getProdcutShortName() + "' and currentstatus = '" + trade.getStatus() + "' and action = '" + trade.getAction() + "' and type ='TRADE'";
		Vector  wfs = (Vector) WFConfigSQL.selectWhere(whereClause, dsSQL.getConn());// this must alway retunr one transition which is unique .
		if(wfs == null || wfs.isEmpty())
			return null;
		WFConfig wf = (WFConfig) wfs.elementAt(0);
		if(!commonUTIL.isEmpty(wf.getRule())) {
			if(wfhandler != null) {				
				wfhandler.generateTradeRule(trade, wf, statusTrade,dsSQL.getConn());				
			} else {
				wfhandler = new WFHandler();				
				wfhandler.generateTradeRule(trade, wf, statusTrade,dsSQL.getConn());
				
				
			}
			
		} 
		if(wfhandler == null) {
			wfhandler = new WFHandler();
		}
		wf = checkSTPExistsonTransition(trade,product,wf,statusTrade) ; //check if stp exists on this transition
		return wf;
	}
	
	private WFConfig checkSTPExistsonTransition(Trade trade,Product product,WFConfig wf,Vector statusTrade) {
		
		WFConfig cf = wf;
		if(wf != null) {
			if(wf.getAuto() == 1 ) {
				String whereClause = "productType ='" + product.getProductType() + "' and productSubType = '"+ product.getProdcutShortName() + "' and currentstatus = '" + wf.getOrgStatus()  + "' and type ='TRADE'";
				//System.out.println(whereClause);
				Vector<WFConfig>  wfs = (Vector) WFConfigSQL.selectWhere(whereClause, dsSQL.getConn());
				if(wfs.size() ==1) {
					cf = wfs.elementAt(0); 
					if(cf.getAuto() == 0) 
						cf = wf;
					if(cf != null)
					wfhandler.generateTradeRule(trade, cf, statusTrade,dsSQL.getConn());
				} else {
					
						WFConfig cff = containsSTPTransitions(trade,wfs,statusTrade);
						if(cff == null) { 
							return cf;
						} else {
							cf = checkSTPExistsonTransition(trade,product,cff,statusTrade);
						}
					
					
				}
			} else {
				cf = wf;
			}
			
			
		}
		return cf;
	}

  
	private WFConfig containsSTPTransitions(Trade trade,Vector<WFConfig> transitions,Vector tradeStats) {
		WFConfig stpWFConfig = null;
		if(transitions != null || !transitions.isEmpty()) {
			for(int i=0;i<transitions.size();i++) {
				WFConfig cfs = (WFConfig) transitions.get(i);
				if(cfs.getAuto() == 1)  {
					
					stpWFConfig = cfs; 
					wfhandler.generateTradeRule(trade, stpWFConfig, tradeStats,dsSQL.getConn());
					break;
				}
			}
		}
		return stpWFConfig;
	}

	private String getProcessTradeStatus(Collection actions)  {
		String status = "";
		if(actions == null)
			return null;
		Vector vector = (Vector) actions;
		Iterator< WFConfig> its = vector.iterator();
while(its.hasNext()) {
    		
	WFConfig wf = its.next();
	status = wf.getOrgStatus();
	System.out.println(" new Status " + status);
		
		
	}
return status;
	
	}
	@Override
	public Collection selectWhere(String sql)  throws RemoteException {
		return TradeSQL.selectwhere(sql, dsSQL.getConn());
	}

	@Override
	public Collection getAuditedTrade(int tradeID) throws RemoteException {
		String sql = " id = " + tradeID;
		return AuditSQL.selectwhere(sql, dsSQL.getConn());
		
		
	}

	@Override
	public Collection selectforOpen(String productype) throws RemoteException {
		
		return TradeSQL.selectforOpen(productype,dsSQL.getConn());
		
	}

	@Override
	public Collection getTradesforReport(String sql) throws RemoteException {	
		return  TradeSQL.selectwhereforReports(sql, dsSQL.getConn());
	}
	@Override
	public Collection getFTDReport(String sql) throws RemoteException {		
		return  TradeSQL.getFTDReport(sql, dsSQL.getConn());
	}
	@Override
	public Collection getVarReport(String sql) throws RemoteException {		
		return  TradeSQL.getVarReport(sql, dsSQL.getConn());
	}
	
   public void startProducingMessage() {
	   newMessage	 = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616");
	  
		Thread sendMessage =  new Thread(newMessage);
		 setNewMessage(newMessage);
		 sendMessage.start();
		 
   }



			public CreateNewMessage getNewMessage() {
				return newMessage;
			}
			
			   
			
			public void setNewMessage(CreateNewMessage newMessage) {
				this.newMessage = newMessage;
			}



			@Override
			public Trade getTradeOnVersion(int tradeID,int version) throws RemoteException {
				
				Trade audittrade = new Trade();
				String sql = " id = " + tradeID + " and version = " + version;
				Vector v1 = (Vector) AuditSQL.selectwhere(sql,  dsSQL.getConn());
				if(v1 != null || v1.size() > 0) {
					audittrade = new Trade();
					String oldTradeValues = ((Audit)v1.elementAt(0)).getValues();
					if(oldTradeValues != null) {
					fillTradeObject(oldTradeValues,audittrade);
					audittrade.setAttributes(((Audit)v1.elementAt(0)).getTattribue());
					}
				
				}
				return audittrade;
			}
// this issue can be solved through procedure or joins (temporary solution)

				public Vector populateSDI(Trade trade) {
					Vector<Sdi> sdi = new Vector<Sdi>();
					Sdi csdi  = null;
					Sdi psdi = null;
					Sdi agenSdi = null;
					Book book =(Book)  BookSQL.selectBook(trade.getBookId(), dsSQL.getConn());
					Vector<LegalEntity> le = (Vector<LegalEntity>) LegalEntitySQL.selectLEOnWhereClause("id = " + book.getCustomerID(),  dsSQL.getConn());
					LegalEntity po = (LegalEntity) le.elementAt(0);
					
					if(trade.getBookId() == trade.getMirrorBookid()) {
						//Book book3 = BookSQL.selectBook(trade.getBookId(), dsSQL.getConn());
						trade.setCpID(po.getId());
					}
					String cKeys = " cpid = " + trade.getCpID() + " and poid =  "  + po.getId() + " and  products ='" + trade.getProductType() +"' and currency  like '%" + trade.getCurrency() +"%'"; 
					Vector cvector = (Vector) SdiSQL.SDIWhere(cKeys, dsSQL.getConn());
					if(cvector.size() > 0) {
						if(cvector.size() == 1) {
							 csdi = (Sdi) cvector.elementAt(0);
							 String key = csdi.getAgentId()+"/"+csdi.getsdiformat() +"/"+csdi.getProducts()+"/"+csdi.getPoId()+"/0/"+trade.getCurrency();
							  Vector vectorp = (Vector) SdiSQL.SDIWhere(" key ='" + key +"' and cpid = "+ csdi.getPoId(), dsSQL.getConn());
							  if(!vectorp.isEmpty() && vectorp.size() > 0)
							  psdi = (Sdi) vectorp.elementAt(0);
						} else {
						   for(int i=0;i < cvector.size();i++) {
							    csdi = (Sdi) cvector.elementAt(i);
							    Vector vect = (Vector)SdiSQL.SDIWhere(" key ='" +csdi.getkey() +"' and poid = "+ csdi.getPoId(), dsSQL.getConn());
								 psdi = (Sdi) vect.elementAt(0);
								if(csdi.getkey().equalsIgnoreCase(psdi.getkey())) {
									break;
								}
							   
						   }
						}
						String agentSDIsql = " cpid = " + csdi.getAgentId() + " and  poid = " + psdi.getCpId() + " and sdiformat = '"+psdi.getSdiformat()+"' and currency = '"+ psdi.getCurrency()+ "' and products = '" + psdi.getProducts() + "'";
						Vector v1 = (Vector) SdiSQL.selectSDIWhere(agentSDIsql,  dsSQL.getConn());
						
						if(v1 != null && (!v1.isEmpty()) ) 
							agenSdi = (Sdi) v1.elementAt(0);
						if(agenSdi != null) {
							csdi.setAgentSdi(agenSdi);
							psdi.setAgentSdi(agenSdi);
						}
						sdi.addElement(csdi);
						sdi.addElement(psdi);
						
					}
					Vector<Fees> fees = (Vector<Fees>) trade.getFees();
					if(fees != null && fees.size() > 0) {
						for(int f=0;f<fees.size();f++) {
							Fees fee = (Fees) fees.elementAt(f);
					         String aKeys = " cpid = " + fee.getLeID() + " and poid =  "  + po.getId() + " and  products ='" + trade.getProductType() +"' and currency  like '%" + trade.getCurrency() +"%'";
					          cvector = (Vector) SdiSQL.SDIWhere(aKeys, dsSQL.getConn());
								if(cvector.size() > 0) {
									if(cvector.size() == 1) {
										 csdi = (Sdi) cvector.elementAt(0);
										 String key = csdi.getAgentId()+"/"+csdi.getsdiformat() +"/"+csdi.getProducts()+"/"+csdi.getCpId()+"/"+ csdi.getPoId() +"/"+trade.getCurrency();
										  Vector vectorp = (Vector) SdiSQL.SDIWhere(" key ='" + key +"' and cpid = "+ csdi.getPoId(), dsSQL.getConn());
										  if(!vectorp.isEmpty() && vectorp.size() > 0)
											  csdi = (Sdi) vectorp.elementAt(0);
									} else {
									   for(int i=0;i < cvector.size();i++) {
										    csdi = (Sdi) cvector.elementAt(i);
											 psdi = (Sdi) SdiSQL.SDIWhere(" key ='" +csdi.getkey() +"' and poid = "+ csdi.getPoId(), dsSQL.getConn());
											if(csdi.getkey().equalsIgnoreCase(psdi.getkey())) {
												break;
											}
										   
									   }
									}
									sdi.addElement(csdi);
						}
					}
					}
					return sdi;
				}

				
				
				
			public void fillTradeObject(String values,Trade trade) {
				String fieldValues [] = values.split(";");
				
				for(int i=0 ;i< fieldValues.length;i++) {
					
					String fieldwithValue = fieldValues[i];
					if(fieldwithValue.startsWith("Id")) {
						trade.setId(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
					}else if (fieldwithValue.startsWith("Currency")) {
						trade.setCurrency(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			            
					}else if (fieldwithValue.startsWith("UserID")) {
						trade.setUserID(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
			            
					}else if (fieldwithValue.startsWith("version")) {
						trade.setVersionID(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
					}else if (fieldwithValue.startsWith("price")) {
						trade.setPrice(new Double(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).doubleValue());
					}else if (fieldwithValue.startsWith("action")) {
						
						trade.setAction(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			            
					}else if (fieldwithValue.startsWith("quantity")) {
						trade.setQuantity(new Double(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).doubleValue());
			            
					}else if (fieldwithValue.startsWith("book")) {
						trade.setBookId(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
			           
					}else if (fieldwithValue.startsWith("broker")) {
						trade.setBrokerID(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
				            
					}else if (fieldwithValue.startsWith("effectivedate")) {
						trade.setEffectiveDate(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			           
					}else if (fieldwithValue.startsWith("delieveryDate")) {
						trade.setDelivertyDate(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
				           
					}else if (fieldwithValue.startsWith("productID")) {
						trade.setProductId(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
				            
					}else if (fieldwithValue.startsWith("tradedate")) {
						trade.setTradeDate(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			           
					}else if (fieldwithValue.startsWith("Nominal")) {
						trade.setNominal(new Double(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
				            
					}else if (fieldwithValue.startsWith("tradeamount")) {
						trade.setTradeAmount(new Double(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).doubleValue());
					}else if (fieldwithValue.startsWith("type")) {
						trade.setType(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
					}else if (fieldwithValue.startsWith("Yield")) {
						
						trade.setYield(new Double(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
			            
					}else if (fieldwithValue.startsWith("status")) {
						trade.setStatus(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			             
					}else if (fieldwithValue.startsWith("ProductName")) {
						trade.setTradedesc(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
			           
					}else if (fieldwithValue.startsWith("productSubType")) {
						trade.setTradedesc1(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
					}else if (fieldwithValue.startsWith("productType")) {
						trade.setProductType(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length()));
					}
					else if (fieldwithValue.startsWith("TraderID")) {
						
						trade.setTraderID(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
					}else if (fieldwithValue.startsWith("CpID")) {
						
						trade.setCpID(new Integer(fieldwithValue.substring(fieldwithValue.indexOf("=")+1, fieldwithValue.length())).intValue());
					}
						
						
					
				} 
				
				
			}



			@Override
			public Collection getSDisOnTrade(Trade trade) throws RemoteException {
				
				
				return populateSDI(trade);
			}



			@Override
			public Vector selectFeesonTrade(int id) throws RemoteException {
				
				return (Vector) FeesSQL.selectFeesOnTrades(id, dsSQL.getConn());
			}



			@Override
			public void updateTradeAndPublish(Trade trade, int userID)
					throws RemoteException {
				
				
			}



			@Override
			public boolean isValidAction(Trade trade) throws RemoteException {
				
				Vector returnStatus = new Vector();
				WFConfig wfs = getStatusOnTradeAction(trade, trade.getStatus(), returnStatus);
				if(wfs == null && returnStatus.isEmpty())
					return false;
				if(returnStatus.size() > 0)
					return false;
				else 
					return true;
				
			}



			@Override
			public Collection<Vector<Trade>> getChildTrades(int parentID)
					throws RemoteException {
				
				String sql = " parentID = " + parentID + " and tradedesc1 like 'FXTAKEUP'";
				return  TradeSQL.selectwhere(sql, dsSQL.getConn());
			}



			@Override
			public Trade getOutStandingBalanceonFWDOption(int FWDOptionID)
					throws RemoteException {
				
				return TradeSQL.getOutStandingBalanceonFWDOption(FWDOptionID, dsSQL.getConn());
			}



			@Override
			public Trade getOutStandingBalanceonFWDOption(int FWDOptionID,
					int tradeID) throws RemoteException {
				
				 return TradeSQL.getOutStandingBalanceonFWDOption(FWDOptionID,tradeID, dsSQL.getConn());
			}



			@Override
			public Vector getTradeRollOverHierarchies(int tradeID)
					throws RemoteException {
				
				return (Vector) TradeSQL.getRollOverHierarchies(tradeID, dsSQL.getConn());
			}



			@Override
			public Vector getTradeRollBackHierarchies(int tradeID)
					throws RemoteException {
				
				return null;
			}



			public Vector<String> saveBatchSplitTrades(Vector<Trade> splitTrades,Trade originalTrade, Vector<String> message)
					throws RemoteException {
				
				if(splitTrades == null || splitTrades.isEmpty())
					return null;
				int cout =0;
				
				if(originalTrade.getId() > 0) 
					return saveBatchUpdateSplitTrades(splitTrades,originalTrade,message);
				 //originalTrade.clearAttributes();
				 Trade xccy1 = splitTrades.get(1);
				 Trade xccy2 = splitTrades.get(3);
				 Trade mirror1 = splitTrades.get(2);
				 Trade mirror2 = splitTrades.get(4);
				 
			/*	 for(int i=0;i<splitTrades.size();i++) {
					 Trade trade =(Trade) splitTrades.get(i);
					int t= saveTrade(trade);
					
					if(trade.getOffsetid() > 0) {
						originalTrade.setAttribute("OffsetID", Integer.valueOf(t).toString());
					} else {
						if(cout ==0 )
							originalTrade.setAttribute("FXccySplitID", Integer.valueOf(t).toString());
						else 
							originalTrade.setAttribute("SXccySplitID", Integer.valueOf(t).toString());
						cout++;
					}
					commonUTIL.display("TradeImp", "saveBatchTrades  Saving Split trades " + t);
				 } */
				 originalTrade.setAutoType("Original");
				// int t= saveTrade(originalTrade);
				 int allocateIDForOriginalTrade =  TradeSQL.selectMaxID(dsSQL.getConn()) + 1; 
				 int allocateIDForxccy1 =  TradeSQL.selectMaxID(dsSQL.getConn()) + 1;
				 int allocateIDFormirror1 =  TradeSQL.selectMaxID(dsSQL.getConn()) + 1;
				 int allocateIDForxccy2 =  TradeSQL.selectMaxID(dsSQL.getConn()) + 1;
				 int allocateIDFormirror2 =  TradeSQL.selectMaxID(dsSQL.getConn()) + 1;
				 originalTrade.setAllocatedID(allocateIDForOriginalTrade);
				 xccy1.setAllocatedID(allocateIDForxccy1);
				 xccy2.setAllocatedID(allocateIDForxccy2);
				 mirror1.setAllocatedID(allocateIDFormirror1);
				 mirror2.setAllocatedID(allocateIDFormirror2);
				 originalTrade.setAttribute("FXccySplitID", Integer.valueOf(allocateIDForxccy1).toString());
				 originalTrade.setAttribute("SXccySplitID", Integer.valueOf(allocateIDForxccy2).toString());
			//	 xccy1.setAttribute("MirrorFromTradeID", Integer.valueOf(allocateIDForxccy2).toString());
				 xccy1.setParentID(allocateIDForOriginalTrade);
				 xccy1.setAttribute("SplitSequence", "FirstSplit");
				 xccy1.setAttribute("XCurrSOriginalTradeID", Integer.valueOf(allocateIDForOriginalTrade).toString());
				 xccy2.setAttribute("XCurrSOriginalTradeID", Integer.valueOf(allocateIDForOriginalTrade).toString());
				 xccy2.setParentID(allocateIDForOriginalTrade);
				 xccy1.setAttribute("MirrorID", Integer.valueOf(allocateIDFormirror1).toString());
				 xccy2.setAttribute("MirrorID", Integer.valueOf(allocateIDFormirror2).toString());
				 mirror1.setAttribute("MirrorFromTradeID", Integer.valueOf(allocateIDForxccy1).toString());
				 mirror1.setParentID(allocateIDForOriginalTrade);
     			 mirror2.setAttribute("MirrorFromTradeID", Integer.valueOf(allocateIDForxccy2).toString());
     			mirror2.setAttribute("ParentID",Integer.toString(allocateIDForOriginalTrade));
     			mirror1.setAttribute("ParentID",Integer.toString(allocateIDForOriginalTrade));
     			xccy1.setAttribute("ParentID",Integer.toString(allocateIDForOriginalTrade));
     			xccy2.setAttribute("ParentID",Integer.toString(allocateIDForOriginalTrade));
     			 xccy2.setAttribute("SplitSequence", "SecondSplit");
     		 	mirror1.setAttribute("SplitSequence", "FirstMirror");
     		 	mirror2.setAttribute("SplitSequence", "SecondMirror");
     			 mirror2.setParentID(allocateIDForOriginalTrade);
     			saveSplitTrade(mirror2);
     			saveSplitTrade(mirror1);
     			saveSplitTrade(xccy2);
     			saveSplitTrade(xccy1);
     			originalTrade.setAttribute("splitBaseNearRate", String.valueOf(xccy1.getPrice()));
     			originalTrade.setAttribute("splitQuoteNearRate", String.valueOf(xccy2.getPrice()));
     			originalTrade.setAttribute("splitBaseFarRate", String.valueOf(xccy1.getSecondPrice()));

     			originalTrade.setAttribute("splitQuoteFarRate", String.valueOf(xccy2.getSecondPrice()));
     			
     			return saveTrade(originalTrade,message);
			//	return originalTrade;
			}
			private Vector<String> saveBatchUpdateSplitTrades(
					Vector<Trade> splitTrades, Trade originalTrade,
					Vector<String> message) {
				
				return null;
			}



			public Vector<String> saveBatchSplitTrades(Vector<Trade> splitTrades,Vector<String> message)
					throws RemoteException {
				
				if(splitTrades == null || splitTrades.isEmpty())
					return null;
				int cout =0;
				 Trade xccy1 = splitTrades.get(1);
				 Trade xccy2 = splitTrades.get(3);
				 Trade mirror1 = splitTrades.get(2);
				 Trade mirror2 = splitTrades.get(4);
				Trade originalTrade = FXSplitUtil.getOriginalTradeFromRountingTrades(splitTrades);
			    
				saveSplitTrade(mirror2);
				saveSplitTrade(mirror1);
				saveSplitTrade(xccy2);
				saveSplitTrade(xccy1);
     			return saveTrade(originalTrade,message);
			//	return originalTrade;
			}



			public Vector getSplitTrades(Trade trade) throws RemoteException {
				
				Vector trades = null;
			String autoType = trade.getAutoType();
			if(commonUTIL.isEmpty(autoType))
				return null;
			 if (autoType.equalsIgnoreCase("Original") ) {
			           trades =  (Vector) TradeSQL.getXccySplitOnParentID(trade.getId(), dsSQL.getConn());
			           if(commonUTIL.isEmpty(trades)) {
			        	   trades.add(0,trade);
			        	   return trades;
			           }
			           trades.add(0,trade);
			         //  trades.add(trade);
			 }
			 if (autoType.equalsIgnoreCase("Offset") ) {
				 if(trade.getOffsetid() > 0) {
		           trades =  (Vector) TradeSQL.getXccySplitOnOffset(trade.getOffsetid(), dsSQL.getConn());
		          // trades.add(trade);
				 }
				 
		 } if(trade.getAutoType().equalsIgnoreCase("Mirror") || trade.getAutoType().equalsIgnoreCase("XccySplit") ) {
			 trades =  (Vector) TradeSQL.getXccySplitOnParentID(trade.getParentID(), dsSQL.getConn());
			  Trade tradep =  selectTrade(trade.getParentID());
			  trades.add(0,tradep);
			  Vector<Trade> tradesarrange = new Vector<Trade>();
			  tradesarrange.add(0,tradep);
			
			//	Trade tradeOriginal =  selectTrade(tradep.getParentID());
				Trade xxccy1 =  FXSplitUtil.getSplitOrMirrorTradeFromRountingTrades(Integer.parseInt(tradep.getAttributeValue("FXccySplitID")), trades);
				   Trade xxccy2 =  FXSplitUtil.getSplitOrMirrorTradeFromRountingTrades(Integer.parseInt(tradep.getAttributeValue("SXccySplitID")), trades);
				   Trade mirror1 = FXSplitUtil. getSplitOrMirrorTradeFromRountingTrades( Integer.parseInt(xxccy1.getAttributeValue("MirrorID")),trades);
				   Trade mirror2 = FXSplitUtil. getSplitOrMirrorTradeFromRountingTrades( Integer.parseInt(xxccy2.getAttributeValue("MirrorID")),trades);
				   
				   tradesarrange.add(1,xxccy1);
				   tradesarrange.add(2,mirror1);
				   
				   tradesarrange.add(3,xxccy2);
				   tradesarrange.add(4,mirror2);
				   trades = tradesarrange;
				  
			  
			   
			   
			  
			  
		 } else {
			 Vector<Trade> tradesarrange = new Vector<Trade>();
			 Trade xxccy1 =  FXSplitUtil.getSplitOrMirrorTradeFromRountingTrades(Integer.parseInt(trade.getAttributeValue("FXccySplitID")), trades);
			   Trade xxccy2 =  FXSplitUtil.getSplitOrMirrorTradeFromRountingTrades(Integer.parseInt(trade.getAttributeValue("SXccySplitID")), trades);
			   Trade mirror1 = FXSplitUtil. getSplitOrMirrorTradeFromRountingTrades( Integer.parseInt(xxccy1.getAttributeValue("MirrorID")),trades);
			   Trade mirror2 = FXSplitUtil. getSplitOrMirrorTradeFromRountingTrades( Integer.parseInt(xxccy2.getAttributeValue("MirrorID")),trades);
			   tradesarrange.add(0,trade);
			   tradesarrange.add(1,xxccy1);
			   tradesarrange.add(2,mirror1);
			   
			   tradesarrange.add(3,xxccy2);
			   tradesarrange.add(4,mirror2);
			   trades = tradesarrange;
	        
		 }
		
				return trades;
			}

			
			
			private void generateB2BTrade(Trade trade) {
				
				   try {
					Trade b2bTrade = (Trade) trade.clone();
					
					b2bTrade.setId(0);
					b2bTrade.setB2Bflag(false);
					b2bTrade.setAttribute("B2BFlag", ""); 
					//if(remoteRef == null) {
								
						//			remoteRef  = (RemoteReferenceData) de.getRMIService("ReferenceData");
								
					//}
					//Vector b2bconfig = (Vector) remoteRef.getB2BConfig(trade.getBookId(), trade.getTradedesc());
					B2BConfig b2b = trade.getB2bConfig();
					if(b2b == null) {
						return;
					}
					b2bTrade.setCpID(b2b.getCounterPartyID());
					b2bTrade.setPrice( b2b.getRate());
					if(b2bTrade.getType().equalsIgnoreCase("BUY")) {
						b2bTrade.setQuantity(trade.getQuantity() * -1) ; 
						b2bTrade.setNominal(b2bTrade.getQuantity() *  b2b.getRate() * -1 );  
						b2bTrade.setType("SELL");
					} else {
						b2bTrade.setQuantity(trade.getQuantity() *-1 ) ;
						b2bTrade.setNominal(b2bTrade.getQuantity() *  b2b.getRate() * -1 );  
						b2bTrade.setType("BUY");
					}
					b2bTrade.setAction("NEW");
					b2bTrade.setStatus("NONE");
					
				//	generateMirrorTrade(b2bTrade,true);
					b2bTrade.setAutoType("BackToBack");
					int b2bID = saveTrade(b2bTrade);
					trade.setB2bid(b2bID);
					trade.setAttribute("B2BID",  Integer.valueOf(b2bID).toString());
					trade.setAutoType("Original");        
			                     
			                    
			                   
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}


			
			
            // to get Mirror Trade if mirror book id is greater then zero this is case when you have internal trade and not B2B trade ;
			private void generateMirrorTrade(Trade b2btrade,boolean isFromB2B) {
				
				try {
					Trade mirrorTrade = (Trade) b2btrade.clone();
					String autoType = mirrorTrade.getAutoType();
					if(!commonUTIL.isEmpty(autoType)) {
						if(autoType.equalsIgnoreCase("INTERNAL")) {
							int i =  saveTrade(mirrorTrade);
							return;
						}
					}
					
					
					mirrorTrade.setId(0);
					mirrorTrade.setFees(null);
					mirrorTrade.setCpID(b2btrade.getMirrorBookid());
					mirrorTrade.setBookId(b2btrade.getBookId());
					mirrorTrade.setMirrorBookid(b2btrade.getBookId());
					mirrorTrade.setEconomicChanged(false);
					if(mirrorTrade.getType().equalsIgnoreCase("BUY")) {
						mirrorTrade.setQuantity(b2btrade.getQuantity() * -1); 
						mirrorTrade.setNominal(b2btrade.getNominal() * -1);  
						mirrorTrade.setType("SELL");
					} else {
						mirrorTrade.setQuantity(b2btrade.getQuantity() * -1);
						mirrorTrade.setNominal(b2btrade.getNominal() * -1);  
						mirrorTrade.setType("BUY");
					}
					mirrorTrade.setAction("NEW");
					mirrorTrade.setStatus("NONE");
				//	mirrorTrade.setAttribute("OriginalTradeID", Integer.valueOf(b2btrade.getId()).toString());
					mirrorTrade.setAttribute("MirrorFromTradeID", Integer.valueOf(b2btrade.getId()).toString());
					if(isFromB2B)  {
						mirrorTrade.setAutoType("MIRROR");
					} else {
					   mirrorTrade.setAutoType("INTERNAL");
					 b2btrade.setB2bid(0);
					 mirrorTrade.setPositionBased(false);
				//	 b2btrade.setMirrorBookid(0);
					}
			        
					int i =  saveTrade(mirrorTrade);
					b2btrade.setMirrorID(i);
				   
					b2btrade.setAttribute("MirrorID", Integer.valueOf(i).toString());
				   if(!isFromB2B)
					   b2btrade.setAutoType("Original");
        
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}



			@Override
			public Vector getMirrorTrades(int mirrorID) throws RemoteException {
				
				
				return TradeSQL.getMirrorTrade( mirrorID,dsSQL.getConn());
			}



			@Override
			public Vector<Trade> getB2Btrades(Trade trade)
					throws RemoteException {
				Vector<Trade> b2btrades = null;
				
				String autoType = trade.getAutoType();
				if(commonUTIL.isEmpty(autoType))
					return null;
			     if(trade.getB2bid() > 0 ) {
			    	
			    	 b2btrades =  TradeSQL.geB2BTradeOnOriginalID(trade.getB2bid(),dsSQL.getConn());
			    	 if(commonUTIL.isEmpty(b2btrades))
			    		 return null;
			    	 Trade b2btrade = b2btrades.elementAt(0);
			    	 b2btrades =  TradeSQL.getMirrorTradeonB2B(b2btrade.getMirrorID(),dsSQL.getConn());
			    	 b2btrades.add(b2btrade);
			    	 b2btrades.add(trade);
			    	 
			     }
			     if(trade.getAutoType().equalsIgnoreCase("MIRROR")) {
			    	 b2btrades =  TradeSQL.getB2BTradeonMirrorID(trade.getId(), dsSQL.getConn());
			    	 if(commonUTIL.isEmpty(b2btrades))
			    		 return null;
			    	 Trade b2btrade = b2btrades.elementAt(0);
			    	 b2btrades =  TradeSQL.getOrginalTradeOnB2bID(b2btrade.getId(), dsSQL.getConn());
			    	 b2btrades.add(b2btrade);
			    	 b2btrades.add(trade);
			     } 
			     if(trade.getAutoType().equalsIgnoreCase("BackToBack")) {
			    	 b2btrades =  TradeSQL.getTradesOnB2bTrade(trade,dsSQL.getConn());
			    	 
			    	 b2btrades.add(trade);
			     } 
			    return b2btrades;
			}



			@Override
			public boolean checkLimitOnTrade(Trade trade,String type)
					throws RemoteException {
				
				if(remoteLimit == null)  
					remoteLimit= (RemoteLimit) de.getRMIService("Limit");
				 
					 return remoteLimit.checkLimitsExceedOnTrade(trade,"CounterPartyLimit");
				
				
				
			}



			@Override
			public String selectTradeAttributesAsString(String tradeId)
					throws RemoteException {
				
				return AttributSQL.selectTradeAttributes(tradeId, dsSQL.getConn());
			}



			@Override
			public void isEventExceuted(EventProcessor event)
					throws RemoteException {
				
				
				EventSQL.update(event, dsSQL.getConn());
			}



			@Override
			public Trade getTradeOldVersion(int tradeID, int tradeVersion)
					throws RemoteException {
				
				/*String sql = " id = " + tradeID + " and version = " + tradeVersion;
				Vector v1 = (Vector) AuditSQL.selectwhere(sql,  dsSQL.getConn());
				String oldTradeValues = "";
				if(v1 != null || v1.size() > 0) 
						  oldTradeValues = ((Audit)v1.elementAt(0)).getValues();
			//	Trade trade = (Trade) ReflectionUtilObject.getObject("beans.trade", oldTradeValues)	;*/
				
		      return null;



			}



			@Override
			public boolean isDifferentUser(Trade trade) throws RemoteException {
				// TODO Auto-generated method stub
				if(trade != null && trade.getId() > 0) {
					Trade auditTrade = getTradeOnVersion(trade.getId(), trade.getVersion());
					 if(auditTrade.getUserID() == trade.getUserID()) {
						 return true;
					 }
				}
				return false;
			}



			@Override
			public Vector<String> getActionsOnTradeCurrentStatus(Trade trade,
					String productType) throws RemoteException {
				// TODO Auto-generated method stub
				Vector<String> actions = new Vector<String>();
				if(trade == null)
					return null;
				String sql = "productType = '"+productType.toUpperCase().trim() +  "'  and productSubType = '"+trade.getTradedesc1().toUpperCase().trim()+   "' and currentstatus = '" +trade.getStatus() +"' and type ='TRADE'";
			Vector<WFConfig>	vector = (Vector) getAllActionsOnStatus(sql);
				if(vector == null)
					return null;
				Iterator it = vector.iterator();
				int i =0;
				while(it.hasNext()) {
		    		
					WFConfig wf = (WFConfig)	 it.next();
					actions.add(wf.getAction());
					i++;
				}
				return actions;
			}

		


    
}
