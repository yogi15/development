package apps;

import java.io.IOException;
import java.util.Properties;

import util.csvReader.CSVFileHandler;
import beans.Deal;
import beans.DealBean;
import beans.HelperDealBean;
import beans.HelperFXBean;
import beans.HelperMMDealBean;
import beans.Trade;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class MMUploader {
	
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
    
			prop.load(Thread.currentThread().getContextClassLoader()
			         .getResourceAsStream(CFG_FILE));
			  String path  = prop.getProperty("pathMMFile");
			 java.util.Vector<Deal> beans =  CSVFileHandler.read(path ,new HelperMMDealBean());
				for(int i=0;i<beans.size();i++) {
				Trade trade = dealSender.buildMMTrade((Deal)beans.get(i));
				 java.util.Vector message = new  java.util.Vector();
				message = remoteTrade.saveTrade(trade, message);
				 int tradeid = ((Integer) message.elementAt(1)).intValue();
				System.out.println("Trade saved with id "+tradeid);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }

}
