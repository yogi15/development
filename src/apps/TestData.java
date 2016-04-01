package apps;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;

import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import util.csvReader.CSVFileHandler;
import beans.Deal;
import beans.DealBean;
import beans.HelperDealBean;
import beans.Trade;

public class TestData {
	
	
	public static void main(String args[]) {
		 ServerConnectionUtil de = null;
		  String CFG_FILE = "application.properties";
    	 RemoteReferenceData remoteReference = null;
    	 RemoteTrade remoteTrade = null;
    	 RemoteProduct remoteproduct= null;
		 de =  ServerConnectionUtil.connect("localhost", 1099,"127.0.0.1" );
		  try {
			 
			  remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
			  remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
			  remoteproduct =   (RemoteProduct)  de.getRMIService("Product");
		  
		 DealSender dealSender = new DealSender();
		 dealSender.setRemoteTrade(remoteTrade);
		 dealSender.setRemoteProduct(remoteproduct);
		 dealSender.setRemoteRefernce(remoteReference);
		 Properties prop = new Properties();
         try {
			prop.load(Thread.currentThread().getContextClassLoader()
			         .getResourceAsStream(CFG_FILE));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String path  = prop.getProperty("pathBondfile");
	//	String path = "F:\\sbijars\\FeedData1236.csv";
		Vector<Deal> beans =  CSVFileHandler.read(path ,new HelperDealBean());
		for(int i=0;i<beans.size();i++) {
		Trade trade = dealSender.buildTrade((DealBean) beans.get(i));
		if(trade != null){
		int t =  remoteTrade.saveTrade(trade);
		 trade = remoteTrade.selectTrade(t);
		 trade.setAction("EXECUTE");
		 remoteTrade.saveTrade(trade);
	     System.out.println("Trade save id = " + t);
		}
		
		}
		  }catch(RemoteException e) {
			  System.out.println(e);
		  }
	}

}
