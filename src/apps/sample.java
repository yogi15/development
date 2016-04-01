package apps;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import beans.Country;
import beans.StartUPData;

import apps.window.staticwindow.StartDataUPWindow;

import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

public class sample {

	
	
	 
     
     
     public static void main(String args[]) {
    	 ServerConnectionUtil de = null;
    	
    	 RemoteReferenceData remoteReference;
    	 String sql = "";
    	 de =  ServerConnectionUtil.connect("localhost", 1099,"127.0.0.1" );
		  try {
			 
			  remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
		//	Vector v1 = (Vector) remoteReference.selectALLStartUPDatas();
			/*  Iterator it = v1.iterator();
			     while(it.hasNext() ) {
			  	   
			    	 StartUPData s1 = (StartUPData) it.next();
			  	  sql  = "insert into startupData(name,value) values('"+ s1.getName()+"','" + s1.getValue() + "');";
			  	//   System.out.println(s1);
			  	  // sql = sql + s1 +"','');";
			  	   System.out.println(sql);
			     } */
			  
			     Vector cc = (Vector) remoteReference.selectALLCountry();
			     Iterator icc= cc.iterator();
			     while(icc.hasNext() ) {
			    	 Country country = (Country) icc.next();
			    	 sql  = "insert into COUNTRY(COUNTRY_ID,ISO_CODE,NAME) values("+country.getId()+",'"+country.getIsocode()+"','"+country.getName()+"');";
			    	 System.out.println(sql);
			     }
			     
				//	System.out.println(deals.toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
     }
}
