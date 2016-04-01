package apps;

import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import util.commonUTIL;
import util.csvReader.CSVFileHandler;
import beans.Deal;
import beans.DealBean;
import beans.HelperDealBean;
import beans.HelperFXBean;
import beans.Trade;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class TestFXData {
	
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
			  String path  = prop.getProperty("pathFXfile");
			 java.util.Vector<Deal> beans =  CSVFileHandler.read(path ,new HelperFXBean());
				for(int i=0;i<beans.size();i++) {
				Trade trade = dealSender.buildFXTrade((Deal)beans.get(i));
				 java.util.Vector message = new  java.util.Vector();
				message = remoteTrade.saveTrade(trade, message);
				 int tradeid = ((Integer) message.elementAt(1)).intValue();
				
				System.out.println("Trade saved with id "+tradeid);
				for(int k=0;k<1000000000;k++) {
					int kk = k+1;
				}
				
				/* message =  remoteTrade.saveTrade(trade, message);
				 if(returnStatus(message)) 
				 	 trade.setAction("EXECUTE" );
				 message =  remoteTrade.saveTrade(trade, message);
				 
				     trade = remoteTrade.selectTrade(tradeid);
			 trade.setAction("APPROVE");
				 message =  remoteTrade.saveTrade(trade, message);
				 if(returnStatus(message)) 
				 System.out.println("Trade saved with id "+tradeid); */
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }

	
	private static boolean returnStatus(Vector message) {
		if(!commonUTIL.isEmpty(message) && message.size() == 2) {
			  String mess = (String) message.get(0);
			   Integer intt = (Integer) message.get(1);
			  System.out.println(mess);
			  if(intt  < 0)
				  return false;
		 }
		return true;
	}
}
