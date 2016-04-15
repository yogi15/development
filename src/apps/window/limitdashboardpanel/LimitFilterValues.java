package apps.window.limitdashboardpanel;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;

import util.commonUTIL;

import dsServices.RemoteBOProcess;
import dsServices.RemoteLimit;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import beans.Book;
import beans.FilterBean;
import beans.LegalEntity;
import beans.StartUPData;
import beans.Users;

public class LimitFilterValues {
	
	Users user;
	RemoteLimit remoteLimit;
	RemoteTask remoteTask;
	RemoteReferenceData remoteReference = null;
	
	Hashtable<String,Vector> dataValues = null; 
	 String starupData [] = {"SearchCriteria","TaskColumn","Status","ProductType","Currency","EventType","WFType","BUY/SELL","TransferType","FEEType","accEvent","TaskType"};
	 String referenceData [] = {"Book" };
	 Hashtable<Integer,Book> bookValues = new Hashtable<Integer,Book>(); 
	 Hashtable<Integer,LegalEntity> counterPary = new Hashtable<Integer,LegalEntity>(); 
	 static Hashtable<String,String> columnNames = new Hashtable<String,String>(); 
	 static Hashtable<String,String> numberDataTypes = new Hashtable<String,String>(); 
	 RemoteTrade remoteTrade = null;
	 RemoteBOProcess remoteBO = null;
	
	 static {
		 columnNames.put("Book", "Bookno");
		 columnNames.put("LegalEntity", "id");
		 columnNames.put("Currency", "Currency");
		 columnNames.put("SettleCurrency", "Currency");
		 columnNames.put("Action", "action");
		 columnNames.put("Status", "Status");
		 columnNames.put("ProductType", "ProductType");
		 columnNames.put("EventType", "EventType");
		 columnNames.put("WFConfig", "EventType");
		 columnNames.put("WFType", "WFType");
		 columnNames.put("BUY/SELL", "Type");
		 columnNames.put("TradeID", "tradeid");
		 columnNames.put("Amount", "amount");
		 columnNames.put("TaskType", "taskType");
		 
		 numberDataTypes.put("Book", "Bookno");
		 numberDataTypes.put("LegalEntity", "id");
		 numberDataTypes.put("Task", "id");
		 numberDataTypes.put("Trade", "id");
		 numberDataTypes.put("Transfer", "id");
		 numberDataTypes.put("Posting", "id");
		 numberDataTypes.put("TradeID", "tradeid");
		 numberDataTypes.put("Amount", "amount");
		
		 
	 }
	 
	 
	 
	
	 public LimitFilterValues(RemoteReferenceData remoteRef,
			RemoteLimit remoteLimit2, RemoteTask remoteTask2, Users user2) {
		 this.remoteReference = remoteRef;
		 this.remoteLimit = remoteLimit;
		 this.remoteTask = remoteTask2;
		 dataValues = new Hashtable<String,Vector>();
			for(int i=0;i<starupData.length;i++) {
				getValuesonColumn(starupData[i],remoteRef);
			}
			dataValues.put("Book", getBooks(remoteRef));
			dataValues.put("CounterParty", getCounterParty(remoteRef));
		// TODO Auto-generated constructor stub
	}
	public  Vector getValuesonColumn(String name,RemoteReferenceData remote) {
			Vector data = null;
			if(remote == null)  {
				return data;
			}
			
		
				if(dataValues.containsKey(name)) {
						data = dataValues.get(name);
				}
			
			if(data == null)  {
				try{ 
					data = (Vector) remote.getStartUPData(name);
					synchronized (dataValues) {
						dataValues.put(name,data);
					}
					} catch (Exception e) {
						commonUTIL.displayError("JobsPanel ","FilterValues", e);
		            }
	    	
				}
				
			convertVectortoSringArray(data,name);
			
			
			return data;
			
			
		}
		public String []  convertVectortoSringArrayForBook(Vector v,String objectType) {
			 
	    	String name [] = null;
	    	int i=0;
	    	name = stringarray.get("Book");
	    	if(name != null) 
	    		return name;
	    	else 
	    	if(v != null ) {
	    		name = new String[v.size()+1];
	    		Iterator its = v.iterator();
	    		name[i]  = "Seleced Item";
	    		i = 1;
	    		while(its.hasNext()) {
	    			
	    			name [i] = ( (Book) its.next()).getBook_name();
	    			i++;
	    		}
	    	}
	    	stringarray.put("Book", name);
			return name;                                           
	        // TODO add your handling code here:
	    } 
		
		public String []  convertVectortoSringArrayForCounterParty(Vector v,String objectType) {
			 
	    	String name [] = null;
	    	int i=0;
	    	name = stringarray.get("CounterParty");
	    	if(name != null) 
	    		return name;
	    	else 
	    	if(v != null ) {
	    		name = new String[v.size()+1];
	    		Iterator its = v.iterator();
	    		name[i]  = "Seleced Item";
	    		i = 1;
	    		while(its.hasNext()) {
	    			
	    			name [i] = ( (LegalEntity) its.next()).getName();
	    			i++;
	    		}
	    	}
	    	stringarray.put("CounterParty", name);
			return name;                                           
	        // TODO add your handling code here:
	    } 
		 public String []  convertVectortoSringArray(Vector v,String sname,int firstItem) {
			 
		    	String name [] = null;
		    	int i=0;
		    	name = stringarray.get(sname);
		    	if(name != null) 
		    		return name;
		    	else 
		    	if(v != null ) {
		    		name = new String[v.size()+1];
		    		Iterator its = v.iterator();
		    		name[i]  = "Seleced Item";
		    		i = 1;
		    		while(its.hasNext()) {
		    			
		    			name [i] = ( (StartUPData) its.next()).getName();
		    			i++;
		    		}
		    	}
		    	stringarray.put(sname, name);
				return name;                                           
		        // TODO add your handling code here:
		    } 
		 public String []  convertVectortoSringArray(Vector v,String sname) {
			 
		    	String name [] = null;
		    	int i=0;
		    	name = stringarray.get(sname);
		    	if(name != null) 
		    		return name;
		    	else 
		    	if(v != null ) {
		    		name = new String[v.size()+1]; 
		    		name[i] = "Selected Values";
		    		i = 1;
		    		Iterator its = v.iterator();
		    		while(its.hasNext()) {
		    			
		    			name [i] = ( (StartUPData) its.next()).getName();
		    			i++;
		    		}
		    	}
		    	stringarray.put(sname, name);
				return name;                                           
		        // TODO add your handling code here:
		    } 
		
		public void fillStartUPData(Vector data,JComboBox comb) {
			Iterator<StartUPData> its = data.iterator(); 
			while(its.hasNext()) {
				comb.addItem(((StartUPData) its.next()).getName());
				
			}
			
		}
		
		
		public void fillBookData(Vector data,JComboBox comb) {
			Iterator<Book> its = data.iterator(); 
			while(its.hasNext()) {
				comb.addItem(((Book) its.next()).getBook_name());
				
			}
			
		}
		public void fillCounterParty(Vector data,JComboBox comb) {
			Iterator<Book> its = data.iterator(); 
			while(its.hasNext()) {
				comb.addItem(((Book) its.next()).getBook_name());
				
			}
			
		}
		private Vector getCounterParty(RemoteReferenceData remote) {
			Vector counterParty = null;
			try {
				if(dataValues.containsKey("counterPary")) {
					counterParty = dataValues.get("counterPary");
			}
				if(counterParty == null ) {
					counterParty =  (Vector) remote.selectAllLs();
				   dataValues.put("CounterParty",counterParty);
				   convertVectortoSringArrayForCounterParty(counterParty,"CounterParty");
				}
				
				return counterParty;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FilterValues " , " getCounterParty", e);
				return null;
			}
			
		}
		private Vector getBooks(RemoteReferenceData remote) {
			Vector books = null;
			try {
				if(dataValues.containsKey("Book")) {
					books = dataValues.get("Book");
			}
				if(books == null ) {
				   books =  (Vector) remote.selectALLBooks();
				   dataValues.put("Book",books);
				   convertVectortoSringArrayForBook(books,"Book");
				}
				
				return books;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FilterValues " , " getBooks", e);
				return null;
			}
			
		}
		Hashtable<String,String[]> stringarray = new Hashtable<String,String[]>();


		public String createWhere(Vector<FilterBean> jobdetails) {
			// TODO Auto-generated method stub
			String where  ="";
			if(jobdetails == null || jobdetails.size() == 0 || jobdetails.isEmpty()) 
				return null;
			for(int i=0;i<jobdetails.size();i++) {
				FilterBean bean = (FilterBean) jobdetails.get(i);
				boolean isStaticRefData = true;
				   if(bean.getColumnName().equalsIgnoreCase("Book")) {
					   isStaticRefData = false;
					   where = where + createCriteriaOnBook(dataValues.get("Book"),bean);
					   
				   }if(bean.getColumnName().equalsIgnoreCase("CounterParty")) {
					   isStaticRefData = false;
					   where = where + createCriteriaOnCounterParty(dataValues.get("CounterParty"),bean);
					   
				   } else if((isStaticRefData) ) {
					   where = where + createCriteria(bean);
				   }
				   if(i != jobdetails.size()-1) {
					   if(commonUTIL.isEmpty(bean.getAnd_or()))
						   where = where + " and ";
					   else 
						   where = where + " " + bean.getAnd_or() +  "  ";
				   } 
				
			}
			where = where + " and task_status not in ('2')";
			return where;
		}

		public String createWhere(Vector<FilterBean> jobdetails,String tableName) {
			// TODO Auto-generated method stub
			String where  ="";
			if(jobdetails == null || jobdetails.size() == 0 || jobdetails.isEmpty()) 
				return null;
			for(int i=0;i<jobdetails.size();i++) {
				FilterBean bean = (FilterBean) jobdetails.get(i);
				   if(bean.getColumnName().equalsIgnoreCase("Book")) {
					   where = where + " " + tableName+"."+ createCriteriaOnBook(dataValues.get("Book"),bean);
					   
				   } else {
					   where = where +  " " + tableName+"."+ createCriteria(bean);
				   }
				   if(i != jobdetails.size()-1) {
					   if(bean.getAnd_or() == null || bean.getAnd_or().isEmpty())
						   where = where + " and ";
					   else 
						   where = where + " " + bean.getAnd_or() +  "  ";
				   } 
				
			}
			if(tableName.equalsIgnoreCase("trade"))
			    where = where + " and trade.version >= 0 ";
			
			return where;
		}
		
		
		private String createCriteriaOnBook(Vector<Book> books,FilterBean bean) {
			String bookCriteria  ="";
			String ids =bean.getIdSelected();
			if(books == null || books.size() == 0 || books.isEmpty()) 
				return null;
			if(ids == null) 
				return null;
			if(ids.contains(","))  {
				String values [] = ids.split(",");
				 for(int i=0;i<values.length;i++) {
					 String id = values[i].trim();
					 Book book = books.elementAt(new Integer(id).intValue()-1); 
			       bookCriteria = bookCriteria + book.getBookno() + ",";
				 }
				 bookCriteria = bookCriteria.substring(0,bookCriteria.length()-1);
			} else {
				bookCriteria = ids;
			}
			ids = columnNames.get(bean.getColumnName()) + " " + " " +attachsqlTypeCrietria(bean.getSearchCriteria().trim(),bookCriteria,bean.getColumnName());
			return ids;
			
		} 
		
		private String createCriteriaOnCounterParty(Vector<LegalEntity> counterPartys,FilterBean bean) {
			String leCriteria  ="";
			String ids =bean.getIdSelected();
			if(counterPartys == null || counterPartys.size() == 0 || counterPartys.isEmpty()) 
				return null;
			if(ids == null) 
				return null;
			if(ids.contains(","))  {
				String values [] = ids.split(",");
				 for(int i=0;i<values.length;i++) {
					 String id = values[i].trim();
					 LegalEntity le = counterPartys.elementAt(new Integer(id).intValue()-1); 
					 leCriteria = leCriteria + le.getId() + ",";
				 }
				 leCriteria = leCriteria.substring(0,leCriteria.length()-1);
			} else {
				leCriteria = ids;
			}
			ids = columnNames.get(bean.getColumnName()) + " " + " " +attachsqlTypeCrietria(bean.getSearchCriteria().trim(),leCriteria,bean.getColumnName());
			return ids;
			
		} 
		
		private String attachsqlTypeCrietria(String criteriaType,String values,String columnName ) {
			String typeC ="";
			if(criteriaType.equalsIgnoreCase("in")  || criteriaType.equalsIgnoreCase("not in") ) {
				typeC = criteriaType + " ('";
				if(values.contains(","))  {
					//values =getFilterValues(values,"Books");
					String value [] = values.split(",");
					 for(int i=0;i<value.length;i++) {
						 String id = value[i].trim();
				          typeC = typeC + id +"','";
					 }
					 typeC =typeC.substring(0,typeC.length()-2)   + ")";
				} else {
					typeC = typeC + values +"')";
				}
			}
			if(criteriaType.equalsIgnoreCase("=")  || criteriaType.equalsIgnoreCase("!=") ) {
				typeC = criteriaType + " '" + values +"'";
				}
			if(criteriaType.equalsIgnoreCase(">")  || criteriaType.equalsIgnoreCase("<") || criteriaType.equalsIgnoreCase(">=") || criteriaType.equalsIgnoreCase("<=") ) {
				
				if (numberDataTypes.containsKey(columnName.trim()) ) {
					typeC = criteriaType + values;
				} else 
					typeC = criteriaType + " '" + values +"'";
				}
			return typeC;
			
		}
		private String createCriteria(FilterBean bean) {
			String criteria = columnNames.get(bean.getColumnName()) + " " + " " +attachsqlTypeCrietria(bean.getSearchCriteria().trim(),bean.getColumnValues().toUpperCase(),bean.getColumnName());
			return criteria;
			
		}



		public String getids(String columnValues,String values) {
			// TODO Auto-generated method stub
			return null;
		}



		public String getFilterValues(String ids, String columnName) {
			// TODO Auto-generated method stub
			String bookIds = "";
			if(ids == null || ids.isEmpty())
				return null;
			int start = ids.indexOf("[")+1;
			int end = ids.indexOf("]");
			String id = ids.substring(start,end);
			
				return id;
			
			
		}

		public Book getBook(int bookId) {
		Vector books = 	dataValues.get("Book");
		Book book = (Book) books.get(bookId);
			return book;
		}
		
		public LegalEntity geCounterParty(int cpID) {
			Vector counterParty = 	dataValues.get("CounterParty");
			LegalEntity le = (LegalEntity) counterParty.get(cpID);
				return le;
			}
			
	/**
	 * @return the user
	 */
	private Users getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	private void setUser(Users user) {
		this.user = user;
	}
	/**
	 * @return the remoteLimit
	 */
	private RemoteLimit getRemoteLimit() {
		return remoteLimit;
	}
	/**
	 * @param remoteLimit the remoteLimit to set
	 */
	private void setRemoteLimit(RemoteLimit remoteLimit) {
		this.remoteLimit = remoteLimit;
	}
	/**
	 * @return the remoteTask
	 */
	private RemoteTask getRemoteTask() {
		return remoteTask;
	}
	/**
	 * @param remoteTask the remoteTask to set
	 */
	private void setRemoteTask(RemoteTask remoteTask) {
		this.remoteTask = remoteTask;
	}
	
	
	
	

}
