package demo;

import java.rmi.RemoteException;
import java.util.Vector;

import beans.Book;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.Folder;
import beans.Holiday;
import beans.LegalEntity;
import beans.StartUPData;
import beans.WFConfig;

import util.commonUTIL;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

public class StartUPDemo {
	
	
	public static void main(String args[]) {
		ServerConnectionUtil de = null; 
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getLocalHostName() );
		try {
			RemoteReferenceData remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
			Vector col = (Vector) remoteReference.selectAllStartUPData();
			
			for(int i=0;i<col.size();i++) {
				String statupdata = " insert into STARTUPDATA(name,value) values('";
				StartUPData stdata = (StartUPData) col.get(i);
				statupdata = statupdata + stdata.getName() +"','" + stdata.getValue() +"');";
				System.out.println(statupdata);
				
			}
			 col = (Vector) remoteReference.selectALLFolders();
			 for(int i=0;i<col.size();i++) {
					String folder = " insert into folder(folderno,folder_name) values(";
					Folder fold = (Folder) col.get(i);
					folder = folder + fold.getId() +",'" + fold.getFolder_name() +"');";
					System.out.println(folder);
					
				}
			 col = (Vector) remoteReference.selectALLHolidays();
			 for(int i=0;i<col.size();i++) {
					String holid = " insert into HOLIDAY(currency,country) values('";
					Holiday hold = (Holiday) col.get(i);
					holid = holid + hold.getCurrency()  +"','" + hold.getCountry() +"');";
					System.out.println(holid);
					
				}
			 col = (Vector) remoteReference.selectAllLs();
			 for(int i=0;i<col.size();i++) {
					String legal = " insert into le(id,name,role) values(";
					LegalEntity le = (LegalEntity) col.get(i);
					legal = legal + le.getId()  +",'" + le.getName() +"','" + le.getRole() +"');";
					System.out.println(legal);
					
				}
			 col = (Vector) remoteReference.selectALLBooks();
			 for(int i=0;i<col.size();i++) {
					String book = " insert into book(bookno,le_id,book_name,folderid) values(";
					Book bo = (Book) col.get(i);
					book = book + bo.getBookno() +"," + bo.getLe_id() +",'" + bo.getBook_name() +"',"  + bo.getFolderID() +" );";
					System.out.println(book);
					
				}
			 col = (Vector) remoteReference.selectALLCurrencyDefault();
			 for(int i=0;i<col.size();i++) {
					String cp = " insert into CURRENCYDEFAULT(currency_code,iso_code,country,default_holidays) values('";
					CurrencyDefault cd = (CurrencyDefault) col.get(i);
					cp = cp + cd.getCurrency_code() +"','" + cd.getIso_code() +"','" + cd.getCountry() +"','"  + cd.getDefault_holiday() +"' );";
					System.out.println(cp);
					
				}
			 col = (Vector) remoteReference.selectAllWF();
			 for(int i=0;i<col.size();i++) {
					String wf = " insert into WFCONFIG(id,productType,productSubType,Action,orgStatus,currentStatus,Auto,rule,type,task) values(";
					WFConfig wfconfig = (WFConfig) col.get(i);
					wf = wf + wfconfig.getId() +",'" + wfconfig.getProductType() +"','" + wfconfig.getProductSubType() +"','"  + wfconfig.getAction() +"','"  + wfconfig.getOrgStatus() +"','"  + wfconfig.getCurrentStatus() +"',"  + wfconfig.getAuto()+",'"  + wfconfig.getRule() +"','"  + wfconfig.getType() +"'," ;
					if(wfconfig.isTask()) {
						wf = wf + "1);";
					} else {
						wf = wf + "0);";
					}
					System.out.println(wf);
					
				}
			 
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
