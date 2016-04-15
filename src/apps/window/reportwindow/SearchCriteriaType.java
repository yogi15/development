package apps.window.reportwindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import com.jidesoft.combobox.MultiSelectListExComboBox;

import util.commonUTIL;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import apps.window.operationwindow.jobpanl.FilterValues;
import beans.Book;
import beans.CurrencyPair;
import beans.FilterBean;
import beans.LegalEntity;
import beans.StartUPData;
import beans.UserJobsDetails;

public abstract class SearchCriteriaType extends JPanel
{
	public static ServerConnectionUtil de = null;
	RemoteReferenceData remoteBORef;
	RemoteTask remoteTask;
	RemoteTrade remoteTrade;
	RemoteBOProcess remoteBo;
	FilterValues filterValues = null;
	Hashtable<Integer,beans.Book> books = new Hashtable<Integer,beans.Book> ();
	Hashtable<Integer,beans.LegalEntity> counterPartyID = new Hashtable();
	Hashtable<Integer,beans.LegalEntity> poID = new Hashtable();
	Hashtable<Integer,beans.LegalEntity> agentID = new Hashtable();
     /**
	 * @return the filterValues
	 */
	public FilterValues getFilterValues() {
		return filterValues;
	}
	/**
	 * @param filterValues the filterValues to set
	 */
	
	public abstract Vector<FilterBean>  searchCriteria();
     public abstract  void clearllCriterial();
     public abstract void loadFilters(Vector<UserJobsDetails> jobdetails);
     
     public SearchCriteriaType() {
    	 de = ServerConnectionUtil.connect("localhost", 1099,
 				commonUTIL.getServerIP());

 		try {
 			remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
 			remoteTask = (RemoteTask) de.getRMIService("Task");
 			remoteTrade = (RemoteTrade) de.getRMIService("Trade");
 			remoteBo = (RemoteBOProcess) de.getRMIService("BOProcess");
 			filterValues = new FilterValues(remoteBORef, remoteTrade,remoteTask, remoteBo);
 		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameNewReport ", " Constructor ", e);
		}
    	 
     }
     
     public SearchCriteriaType(RemoteTrade remoteTrade,RemoteReferenceData remoteRef,RemoteBOProcess remoteBO,RemoteTask remoteTask) {
    	 this.remoteBORef =  remoteRef;
 		 this.remoteTask = remoteTask;
 		 this.remoteTrade =  remoteTrade;
 		 this.remoteBo = remoteBO;
 		 filterValues = new FilterValues(remoteBORef, remoteTrade,remoteTask, remoteBo);    	 
     }
     
     
     public MultiSelectListExComboBox getMultiSelectListExComboBox(String val [],final FilterBean filler ) {
 		final MultiSelectListExComboBox values = new MultiSelectListExComboBox(val, String[].class);
 		values.addItemListener(new ItemListener() {
 			@Override
 			public void itemStateChanged(ItemEvent e) {
 				if (values.getSelectedIndex() != -1) {
 					
 					String attributeName = values.getSelectedItem().toString();
 					String idSelected ="";
 					int ids [] = values.getSelectedIndices();
 					String ss = "";
 					Object obj[] = values.getSelectedObjects();
 					for (int i = 0; i < values.getSelectedObjects().length; i++) {
 						    ss = ss + (String) obj[i] + ",";
 						    idSelected = idSelected + ids[i] +",";
 					}
 					
 					filler.setColumnValues(ss.substring(0, ss.length()-1));
 					filler.setIdSelected(idSelected.substring(0, idSelected.length()-1));
 					

 				}
 			}
 		});
 		return values;
 	}
     public FilterBean getTradeID(String tradeID)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(tradeID)) {
 			bean = new FilterBean();
 			bean.setColumnName("TradeID");
 			bean.setColumnValues(tradeID);
 			bean.setAnd_or("And");
 			bean.setSearchCriteria("in");
 			
 		}
    	 return bean;
     }
     
     public FilterBean getOtherId(String tradeID)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(tradeID)) {
 			bean = new FilterBean();
 			bean.setColumnName("otherTradeID");
 			bean.setColumnValues(tradeID);
 			bean.setAnd_or("And");
 			bean.setSearchCriteria("in");
 			
 		}
    	 return bean;
     }
     
     public FilterBean getTransferId(String transferId)  {
    	 FilterBean bean = null;
    	
		bean = new FilterBean();
		bean.setColumnName("TransferId");
		bean.setColumnValues(transferId);
		bean.setAnd_or("And");
		bean.setSearchCriteria("in");
 			
    	 return bean;
     }
     public FilterBean getCriteriaDate(String TradeDateFrom, String DateTo, String colName)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(TradeDateFrom)) {
    		 bean = new FilterBean();
 			bean.setColumnName(colName);
 			bean.setColumnValues(TradeDateFrom);
 			
 			if (!DateTo.equals(TradeDateFrom)) {
 				
 				bean.setAnd_or(DateTo);
 				bean.setSearchCriteria("between");
 				
 			} else {
 				
 				bean.setAnd_or(TradeDateFrom);
 				bean.setSearchCriteria("equal");
 			}
 			
 			
 		}
    	 return bean;
     }
     public FilterBean getMaturityDateFrom(String MaturityDateFrom)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(MaturityDateFrom)) {
    		 bean = new FilterBean();
 			bean.setColumnName("DeliveryDate");
 			bean.setColumnValues(MaturityDateFrom);
 			bean.setAnd_or(MaturityDateFrom);
 			bean.setSearchCriteria("between");
 			
 		}
    	 return bean;
     }
     public FilterBean getSettleDateFrom(String SettleDateFrom)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(SettleDateFrom)) {
    		 bean = new FilterBean();
 			bean.setColumnName("SettleDate");
 			bean.setColumnValues(SettleDateFrom);
 			bean.setAnd_or(SettleDateFrom);
 			bean.setSearchCriteria("between");
 			
 		}
    	 return bean;
     }
     
     
     public FilterBean getBUYSELL(String tradeType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(tradeType)) {
    		 bean = new FilterBean();
    		 bean.setColumnName("Type");
    		 bean.setColumnValues(tradeType);
    		 bean.setSearchCriteria("in");
    		 bean.setAnd_or("And"); 			
 		}
    	return bean;
     }
     public FilterBean getLadder(String ladder,String Ladder)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(ladder)) {
    		 bean = new FilterBean();
    		 bean.setColumnName(Ladder);
    		 bean.setColumnValues(ladder);
    		 bean.setSearchCriteria("in");
    		 bean.setAnd_or("And"); 			
 		}
    	 return bean;
     }
     public FilterBean getStatus(String Status)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(Status)) {
    		 bean = new FilterBean();
 			 bean.setColumnName("Status");
 			 bean.setColumnValues(Status);
 			 bean.setAnd_or("And");
 			 bean.setSearchCriteria("in"); 			
 		}
    	 return bean;
     }
     
     public FilterBean getStatus(MultiSelectListExComboBox values)  {
    	 FilterBean bean = new FilterBean();
    	 String ss = "";
    	 String idSelected ="";
    	 int ids [] = values.getSelectedIndices();
			Object obj[] = values.getSelectedObjects();
			for (int i = 0; i < values.getSelectedObjects().length; i++) {
				    ss = ss + (String) obj[i] + ",";
				    idSelected = idSelected + ids[i] +",";
			}
			bean.setColumnName("Status");
			bean.setColumnValues(ss.substring(0, ss.length()-1));
			bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
			bean.setSearchCriteria("in");
 			bean.setAnd_or("And");

    	 return bean;
     }
     
     public FilterBean getAction(String Action)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(Action)) {
    		 bean = new FilterBean();
 			 bean.setColumnName("Action");
 			 bean.setColumnValues(Action);
 			 bean.setSearchCriteria("in");
 			 bean.setAnd_or("And");
 		}
    	 return bean;
     }
     
     public FilterBean getCurrency(String Currency, String colName)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(Currency)) {
    		 bean = new FilterBean();
    		 bean.setColumnName(colName);
    		 bean.setColumnValues(Currency);
    		 bean.setAnd_or("And");
    		 bean.setSearchCriteria("in"); 			
 		}
    	 return bean;
     }
     
     public FilterBean getCurrency(MultiSelectListExComboBox values, String colName)  {
    	 FilterBean  bean = new FilterBean();
    	 String ss = "";
    	 String idSelected ="";
    	 int ids [] = values.getSelectedIndices();
		 Object obj[] = values.getSelectedObjects();
		 for (int i = 0; i < values.getSelectedObjects().length; i++) {
			    ss = ss + (String) obj[i] + ",";
			    idSelected = idSelected + ids[i] +",";
		 }
		
		 bean.setColumnName(colName);
		 bean.setColumnValues(ss.substring(0, ss.length()-1));
		 bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
		 bean.setSearchCriteria("in");
		 bean.setAnd_or("And");
 			
    	 return bean;
     }
     
     public FilterBean getProductSubType(MultiSelectListExComboBox values, String colName)  {
    	 FilterBean  bean = new FilterBean();
    	 String ss = "";
    	 String idSelected ="";
    	 int ids [] = values.getSelectedIndices();
		 Object obj[] = values.getSelectedObjects();
		 for (int i = 0; i < values.getSelectedObjects().length; i++) {
			    ss = ss + (String) obj[i] + ",";
			    idSelected = idSelected + ids[i] +",";
		 }

		 bean.setColumnName(colName);
		 bean.setColumnValues(ss.substring(0, ss.length()-1));
		 bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
		 bean.setSearchCriteria("in");
		 bean.setAnd_or("And");
    	
		 return bean;
     }
     
     public FilterBean getprimaryCurrency(String values, String colName)  {
    	 FilterBean  bean = new FilterBean();
    	 String ss = "";
    	 String idSelected ="";
    	
    	 bean.setColumnName("PrimaryCurrLedger");
		 bean.setColumnValues(values);
			//bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
		 bean.setSearchCriteria("in");
 		 bean.setAnd_or("or");
    	 
 		 return bean;
     }
     
     public FilterBean getquotingCurrency(String values, String colName)  {
    	 FilterBean  bean = new FilterBean();
    	 String ss = "";
    	 String idSelected ="";
    	
		 bean.setColumnName("QuotingCurrLedger");
		 bean.setColumnValues(values);
		 //bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
		 bean.setSearchCriteria("in");
 		 bean.setAnd_or("And");
    	 
 		 return bean;
     }
     public FilterBean getProductType(String ProductType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(ProductType)) {
    		bean = new FilterBean();
 			bean.setColumnName("ProductType");
 			bean.setColumnValues(ProductType);
 			bean.setAnd_or("And");
 			bean.setSearchCriteria("in");
 			
 		}
    	 
    	return bean;
     }
     public FilterBean getProductSubType(String ProductSubType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(ProductSubType)) {
    		bean = new FilterBean();
 			bean.setColumnName("ProductSubType");
 			bean.setColumnValues(ProductSubType);
 			bean.setSearchCriteria("in");
 			bean.setAnd_or("And"); 			
 		}
    	 
    	return bean;
     }
     
     public FilterBean getTransferType(String transferType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(transferType)) {
    		bean = new FilterBean();
 			bean.setColumnName("TransferType");
 			bean.setColumnValues(transferType);
 			bean.setSearchCriteria("in");
 			bean.setAnd_or("And");
 			
 		}
    	 
    	return bean;
     }
    
     public FilterBean getTransferEventType(String transferEventType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(transferEventType)) {
    		bean = new FilterBean();
 			bean.setColumnName("TransferEventType");
 			bean.setColumnValues(transferEventType);
 			bean.setSearchCriteria("in");
 			bean.setAnd_or("And"); 			
 		}
    	
    	return bean;
     }
     
     public FilterBean getTransferMethodType(String transferMethodType)  {
    	 FilterBean bean = null;
    	 if(!commonUTIL.isEmpty(transferMethodType)) {
    		bean = new FilterBean();
 			bean.setColumnName("TransferMethodType");
 			bean.setColumnValues(transferMethodType);
 			bean.setSearchCriteria("in");
 			bean.setAnd_or("And"); 			
 		}
    	 
    	return bean;
     }
     
     public FilterBean getBookName(int bookID)  {
         FilterBean bean = null;
         if(bookID > 0) {
        	 bean = new FilterBean();
             bean.setColumnName("Book");
             bean.setSearchCriteria("in");
             bean.setIdSelected(new Integer(getBookID(bookID)).toString());
             bean.setAnd_or("And");
         }
         
         return bean;
     }
     
     public FilterBean getBookName(MultiSelectListExComboBox values)  {
    	 FilterBean bean = null;
    	 
    	 String ss = "";
    	 String idSelected ="";
    	 int ids [] = values.getSelectedIndices();
		
    	 Object obj[] = values.getSelectedObjects();
		
    	 for (int i = 0; i < values.getSelectedObjects().length; i++) {				    
    		 ss = ss + (String) obj[i] + ",";
			 idSelected = idSelected + ids[i] +",";			 
    	 }
    	
    	 bean = new FilterBean();
		 bean.setColumnValues(ss.substring(0, ss.length()-1));
		 bean.setIdSelected(idSelected.substring(0, idSelected.length()-1));
		 bean.setColumnName("Book");
		 bean.setSearchCriteria("in");
		 bean.setAnd_or("And");
 		 			
    	 return bean;
     } 
     
     public FilterBean getLegalEntity(int leId, String role)  {
    	 FilterBean bean = null;
    	
    	 if(leId > -1) {    		 
    		 bean = new FilterBean();
 			 bean.setColumnName(role);
 			 
 			 if (role.equals("cpid")) { 				 
 				bean.setColumnValues(new Integer(getCPid(leId)).toString()); 				
 			 } else if (role.equals("poId")) { 				 
 				bean.setColumnValues(new Integer(getPOid(leId)).toString()); 			 
 			 } else if (role.equals("agentId")) { 				 
  				bean.setColumnValues(new Integer(getAgentid(leId)).toString());  			 
 			 }
 			 
 			 bean.setAnd_or("And");
 			 bean.setSearchCriteria("in"); 			
 		}
    	 
    	return bean;
     }
     
     public int getBookID(int idSelected) {
 		Book book = (Book) books.get(idSelected);
 		return book.getBookno();
 		
 	}
     public int getCPid(int idSelected) {
 		LegalEntity le = (LegalEntity)  counterPartyID.get(idSelected);
 		if(le == null)
 			return 0;
 		return le.getId();
 		
 	}
 	
     public int getPOid(int idSelected) {
  		LegalEntity le = (LegalEntity)  poID.get(idSelected);
  		if (le == null)
  			return 0;
  		return le.getId();
  		
  	}
     
     public int getAgentid(int idSelected) {
  		LegalEntity le = (LegalEntity)  agentID.get(idSelected);
  		if (le == null)
  			return 0;
  		return le.getId();
  		
  	}
     public int getBooktoSelected(int idSelected) {
 		int selectID = 0;
 		for(int i=0;i<books.size();i++) {
 			selectID = i;
 			Book book = (Book) books.get(i);
 			if(book != null)
 			if(book.getBookno() == idSelected) 
 				break;
 		}
 		return selectID;
 		
 	}
     public int getCPtoSelected(int idSelected) {
 		int selectID = 0;
 		for(int i=0;i<counterPartyID.size();i++) {
 			selectID = i;
 			LegalEntity le = (LegalEntity) counterPartyID.get(i);
 			if(le != null)
 			if(le.getId() == idSelected) 
 				break;
 		}
 		return selectID;
 		
 	}
     
     public int getPOtoSelected(int idSelected) {
  		int selectID = 0;
  		for(int i=0;i<poID.size();i++) {
  			selectID = i;
  			LegalEntity le = (LegalEntity) poID.get(i);
  			if(le != null)
  			if(le.getId() == idSelected) 
  				break;
  		}
  		return selectID;
  		
  	}
     
    public String[] getMultipleValuesSelected(String bookNames) {
    	
    	String splitBookNames[] = bookNames.split(",");
    	
    	int length = splitBookNames.length;
    	int bookids[] = new int[length];
    	
    	/*for(int i = 0; i < length; i++){
    		
    		bookids[i] = books.get(splitBookNames[i]).getBookno();
    	}
    	*/
    	return splitBookNames;
    }
    
     public void processLEDataCombo1(javax.swing.DefaultComboBoxModel combodata, Hashtable ids, String role) {
    	 Vector ledata;
 		 ledata = (Vector<String>) getFilterValues().getLegalEntitys();

 		 Iterator it = ledata.iterator();
 		 int p = 0;
 		 combodata.insertElementAt("", p++);
 			
 		 while (it.hasNext()) {
 			 LegalEntity le = (LegalEntity) it.next();
 			 if (le.getRole().equalsIgnoreCase(role)) {
 				 combodata.insertElementAt(le.getName(), p++);
 	 			 ids.put(p, le);
 	 		 }
 		}

 	} 	

     protected void processBookDataCombo1(javax.swing.DefaultComboBoxModel combodata, Hashtable ids) {    		
    	Vector ledata;    	
    	ledata = (Vector) getFilterValues().getBooks();

		Iterator it = ledata.iterator();
		int p = 1;
		combodata.addElement("");
		while (it.hasNext()) {
			Book book = (Book) it.next();
			combodata.insertElementAt(book.getBook_name(), p);
			ids.put(p, book);		
			p++;
		}
   	}     

 	public void processLEDataCombo1(DefaultComboBoxModel legalEntityData2,
 			Hashtable<Integer, LegalEntity> counterPartyID2) { 		
 		Vector ledata;
		ledata = (Vector<String>) getFilterValues().getLegalEntitys();

		Iterator it = ledata.iterator();
		int p = 0;
		legalEntityData2.addElement("");
		
		while (it.hasNext()) {
			LegalEntity le = (LegalEntity) it.next();
			legalEntityData2.insertElementAt(le.getName(), p);
			counterPartyID2.put(p, le);
			p++;		
		}
 	}
 	
 	protected void processDomainData(javax.swing.DefaultComboBoxModel combodata, Vector<String> domainData) { 		
 		Vector ledata;    		
		ledata =domainData;

		Iterator it = ledata.iterator();
		int p = 0;
		combodata.insertElementAt("", p++);
		while (it.hasNext()) {
			String stData = (String) it.next();
			combodata.insertElementAt(stData, p++);	
		}
		
 	}
      	  
 	protected void processCurrenyPairData(javax.swing.DefaultComboBoxModel combodata) { 		
 		
 		Vector<CurrencyPair> vector = null;
		try {
			vector = (Vector<CurrencyPair>) remoteBORef.selectALLCurrencyPair();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
 		Iterator it = vector.iterator();
 		
 		int p = 0;
		combodata.insertElementAt("", p++);
		while (it.hasNext()) {
			CurrencyPair data = (CurrencyPair) it.next();
			combodata.insertElementAt(data.getPrimary_currency()+"/"+data.getQuoting_currency(), p++);	
		}		
 	}
 
}
