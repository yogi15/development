package bo.transfer;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import util.RemoteServiceUtil;
import util.commonUTIL;

import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import beans.Fees;
import beans.LegalEntity;
import beans.NettingConfig;
import beans.Product;
import beans.Trade;
import beans.Transfer;

public abstract class BOTransfer {
	
	
    public RemoteProduct getRemoteProduct() {
		return remoteProduct;
	}
	public void setRemoteProduct(RemoteProduct remoteProduct) {
		this.remoteProduct = remoteProduct;
	}
	public RemoteBOProcess getBoProcess() {
		return boProcess;
	}
	public void setBoProcess(RemoteBOProcess boProcess) {
		this.boProcess = boProcess;
	}
	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}
	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}
	public RemoteReferenceData getRemoteRef() {
		return remoteRef;
	}
	public void setRemoteRef(RemoteReferenceData remoteRef) {
		this.remoteRef = remoteRef;
	}

	protected 	RemoteProduct remoteProduct;
    protected RemoteBOProcess boProcess;
    protected RemoteTrade remoteTrade;
    protected RemoteReferenceData remoteRef = null;
	public static  ServerConnectionUtil de = null;
	static final public String DAP = "DAP"; 
	static final public String DFP = "DFP"; //(Delivery Free of Payment) 

	
	abstract public Vector<Transfer> generateTransfer(Trade trade,Vector<String> feestype,NettingConfig netConfig,Vector<String> errorMessage);
	abstract public boolean updateTransfer(Trade trade,Product product);
	abstract public void fillTransfer(Transfer transfer);
	
	public void filterTransfer(Vector<Transfer> oldTransfers,Vector<Transfer> newTransfers,Trade trade,Hashtable transferData,boolean isTerminated) {
		
		commonUTIL.display("TransferProcessor", "In filterTransfer method");
		Vector<Transfer> updateTransfer = new Vector<Transfer>(); 
		Vector<Transfer> deleteTransfer = new Vector<Transfer>(); 
		Vector<Transfer> insertTransfer = new Vector<Transfer>(); 
		
		// TODO Auto-generated method stub
		if(isTerminated) {
			for(int i=0;i<oldTransfers.size();i++) {
				Transfer oldTransfer = oldTransfers.get(i);
				if(!oldTransfer.getStatus().equalsIgnoreCase("CANCELLED")) {
					oldTransfer.setAction("CANCEL");
					updateTransfer.addElement(oldTransfer);
				}
			}
		} else {
						for(int i=0;i<oldTransfers.size();i++) {
								boolean found = false;
								Transfer oldTransfer = oldTransfers.get(i);
								if(!oldTransfer.getStatus().equalsIgnoreCase("CANCELLED")) {
								Hashtable attributes = oldTransfer.getAttributesData();
								Enumeration<String> keys = attributes.keys();
								while(keys.hasMoreElements()) {
										String key = keys.nextElement();
										String ovalue = (String) attributes.get(key);
										for(int n=0;n<newTransfers.size();n++) {
											Transfer newTransfer = newTransfers.get(n);
											if(newTransfer.getALLAttributesData() != null) {
											if(newTransfer.containTransferKey(key)) {
												    String nvalue = newTransfer.getAttributeValue(key);
												    if(!ovalue.equalsIgnoreCase(nvalue)) {
															found = true;
															if(compareTransfer(oldTransfer,newTransfer,trade.getStatus()) != 0) {
																if(!oldTransfer.getStatus().equalsIgnoreCase("CANCELLED")) {
																updateTransfer.addElement(oldTransfer);
																newTransfer.setLinkid(oldTransfer.getId());
																if(!trade.getStatus().equalsIgnoreCase("CANCELLED"))
																insertTransfer.addElement(newTransfer);
																}
													      }
												    }
											}
											}
										}
										if(!found) 
											deleteTransfer.addElement(oldTransfer);
									}
								}
						}
						for(int i=0;i<newTransfers.size();i++) {	
											boolean found = false;
											Transfer newTransfer = newTransfers.get(i);
											Hashtable attributes = newTransfer.getAttributesData();
											Enumeration<String> keys = attributes.keys();
											while(keys.hasMoreElements()) {
												String key = keys.nextElement();
												for(int n=0;n<oldTransfers.size();n++) {
													Transfer oldTransfer = oldTransfers.get(n);
												//	if(!oldTransfer.getAction().equalsIgnoreCase("CANCEL")) {
													System.out.println(oldTransfer.getId());
												//	Hashtable oldTranasferattributes = oldTransfer.getAttributesData();
													if(oldTransfer.getALLAttributesData() != null)
															if(oldTransfer.containTransferKey(key)) {
																 found = true;
																
															}
													//}
												}
												
											}
											if(!found)
												insertTransfer.addElement(newTransfer);
						}
				}
		transferData.put("insert", insertTransfer);
		transferData.put("update", updateTransfer);
		transferData.put("delete", deleteTransfer);
		
		commonUTIL.display("TransferProcessor", "In filterTransfer method new Transfer "+ insertTransfer.size());
		commonUTIL.display("TransferProcessor", "In filterTransfer method old Transfer "+ updateTransfer.size());
		commonUTIL.display("TransferProcessor", "In filterTransfer method dold Transfer "+ deleteTransfer.size());
		
		    
	}

	private int compareTransfer(Transfer oldTransfer, Transfer newTransfer,
			String status) {
		// TODO Auto-generated method stub
		int i = oldTransfer.compareTo(newTransfer);
		if(i != 0) {
			if(status.equalsIgnoreCase("APPROVED") || status.equalsIgnoreCase("CANCELLED")) {
				oldTransfer.setAction("CANCEL");
				newTransfer.setId(0);
				
			}
		}
		return i;
	}


	public BOTransfer() {
		
		// de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	 //  	 try {
	   		remoteProduct = RemoteServiceUtil.getRemoteProductService();
	   		remoteRef =RemoteServiceUtil.getRemoteReferenceDataService();
	   		remoteTrade =RemoteServiceUtil.getRemoteTradeService();
	   		boProcess = RemoteServiceUtil.getRemoteBOProcessService();
				//System.out.println(remoteProduct);
			 
		
		
	}
    
	public String getLEName(int id) {
		   String name = "";
		   try {
			   if(id ==  0) 
				   return name;
			LegalEntity le = (LegalEntity )remoteRef.selectLE(id);
			name = le.getName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return name;
	   }
	
	
	
	
	
	
}
