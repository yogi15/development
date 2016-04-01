package bo.transfer.rule;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

import constants.SDIConstants;

import util.ReferenceDataCache;

import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

import beans.Book;
import beans.LegalEntity;
import beans.Sdi;
import beans.Trade;
import beans.TransferRule;

public abstract class ProductTransferRule {
	
	public RemoteTrade remoteTrade = null;
	public RemoteProduct remoteProduct = null;
	 
	
	public RemoteBOProcess remoteBOProcess = null;
	public RemoteReferenceData refData = null;
	public Vector<Sdi> sdis = null;
	public Sdi agentSdi = null;
	public Sdi getAgentSdi() {
		Sdi po = getSdi("PO");
	    Sdi cp = getSdi("CounterParty");
	    try {
	    	agentSdi = 	refData.selectAgentSdi(cp.getCpId(),po.getPoId(),po.getsdiformat(),po.getCurrency(),po.getProducts());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agentSdi;
	}

	public void setAgentID( Vector<Sdi> sdis ) {
		
	}


	public void setAgentSdi(Sdi agentSdi) {
		this.agentSdi = agentSdi;
	}

	public Sdi poSdi =null;
	
	 public Vector<Sdi> getSdis() {
		return sdis;
	}
	 public Sdi getSdi(String role,int leID,String currency,String productType,String messageType,int priority) {
			Sdi s = null;
			int priorityZero =priority;
			int p = priority;
			boolean found = false;
			Vector<Sdi> sdidata =ReferenceDataCache.getSdisonLegelEntityRole(role,leID,currency,messageType,productType);
			if(sdidata == null || sdidata.isEmpty())
				return null;
			s = setSdiValue(sdidata,priorityZero,s);
			return s;
		}
 
	 
	public Sdi getSdi(String role,int leID,String currency,String productType,int priority) {
		Sdi s = null;
		int priorityZero =priority;
		int p = priority;
		boolean found = false;
		Vector<Sdi> sdidata =ReferenceDataCache.getSdisonLegelEntityRole(role,leID,currency,productType);
		if(sdidata == null || sdidata.isEmpty())
			return null;
		s = setSdiValue(sdidata,priorityZero,s);
		return s;
	}
	// this logic can be dangerous. this logic find the priority from zero to highest number,   zero is given highest priority. 
	
	private Sdi setSdiValue(Vector<Sdi> sdidata,int priortiy,Sdi sd) {
		 Sdi s  = sd;
		boolean found = false;
		for(int i=0;i<sdidata.size();i++) {
			Sdi sdi = sdidata.elementAt(i);
			if(sdi.getPriority() == priortiy)  {
				s = sdi; // logic needs to be build, while inserting new SDI priority wise validation needs to be add
				found = true;
				break;
			}  
		}
		if(!found) {
			s = setSdiValue(sdidata,priortiy + 1,s);
		}
		return s;
	}
	public void setSdis(Vector<Sdi> sdis) {
		this.sdis = sdis;
	}
	 public Sdi getSdiOnEntity(int leid) {
		 return null;
	 }
	 
	 
	 public Sdi getSdi(int sdiID) {
		 Sdi sd = null;
		if(sdis ==  null || sdis.isEmpty()) 
			return null;
	
		 if((sdis != null) && (!sdis.isEmpty())) {
			 for(int i=0;i<sdis.size();i++) {
				 Sdi s = (Sdi) sdis.elementAt(i);
				 if(s.getId() == sdiID) {
					 sd =s;
				     break;
				 }
			 }
			 
			 
			 
		 }
		 return sd;
			
	 }
	
	public final String tradeTypeBUY = "BUY";
	 public final String tradeTypeSELL = "SELL";
	  public final String PAY = "PAY";
	 public final String RECEIVE = "RECEIVE";
	 public final String transerTYPEPRINCIPAL = "PRINCIPAL";
	 public final String transerTYPESECURITY = "SECURITY";
	 public final String transerTYPEINTEREST = "INTEREST";
	 public final String transerTYPEFEES = "_FEES";
	
	
	
	public void setPOSdi(Sdi sdi) {
		this.poSdi = sdi;
	}
	Sdi counterPartySdi = null;
	public void setCounterPartySDI(Sdi sdi) {
		this.counterPartySdi = sdi;
	}
	public Sdi getPOSDI() {
		return poSdi;
	}
	public Sdi getCounterPartySDI() {
		return counterPartySdi;
	}
	public Sdi getSdi(String role) {
		Sdi sdi = null;
		if(role.equalsIgnoreCase("PO"))
			sdi =  getPOSDI();
		if(role.equalsIgnoreCase("CounterParty"))
			sdi= getCounterPartySDI();
		return sdi;
	}
	
	 public  abstract String getProductType();
	 public abstract Vector<TransferRule> getTransferRules(Vector v1);
	 public abstract Vector<TransferRule>  generateRules(Trade trade,Vector<String> message);
	 
	 
	 public RemoteProduct getRemoteProduct() {
			return remoteProduct;
		}
		public void setRemoteProduct(RemoteProduct remoteProduct) {
			this.remoteProduct = remoteProduct;
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
		public RemoteReferenceData getRefDate() {
			return refData;
		}
		public void setRefDate(RemoteReferenceData refData) {
			this.refData = refData;
		}
		
		public LegalEntity getLegalEntity(int id) {
			LegalEntity le = null; 
			try {
				le =  (LegalEntity)  refData.selectLE(id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return le;
		}
		
		public Book getBook(int id) {
			Book book = new Book(); 
			book.setBookno(id);
			try {
				book =  (Book)  refData.selectBook(book);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return book;
		}
		
		
		
}
