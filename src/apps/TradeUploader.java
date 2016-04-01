package apps;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;

import constants.logConstants;

import logAppender.ProductAppender;
import logAppender.TradeAppender;

import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import util.csvReader.CSVFileHandler;
import util.csvReader.CSVProductFileHandler;
import util.csvReader.TradeCsvReader;
import beans.Coupon;

import beans.Deal;
import beans.DealBean;
import beans.HelperDealBean;
import beans.HelperProductUploaderBean;
import beans.Product;
import beans.ProductUploaderBean;
import beans.Trade;

public class TradeUploader {

	public static void main(String args[]) {

		ServerConnectionUtil de = null;

		String CFG_FILE = "application.properties";

		RemoteReferenceData remoteReference = null;
		RemoteTrade remoteTrade = null;
		RemoteProduct remoteproduct = null;

		de = ServerConnectionUtil.connect("localhost", 1099, "127.0.0.1");

		try {

			remoteReference = (RemoteReferenceData) de
					.getRMIService("ReferenceData");
			remoteTrade = (RemoteTrade) de.getRMIService("Trade");
			remoteproduct = (RemoteProduct) de.getRMIService("Product");

			DealSender dealSender = new DealSender();
			dealSender.setRemoteTrade(remoteTrade);
			dealSender.setRemoteProduct(remoteproduct);
			dealSender.setRemoteRefernce(remoteReference);

			Properties prop = new Properties();

			try {

				prop.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(CFG_FILE));

			} catch (IOException e) {

				e.printStackTrace();

			}

			String path = prop.getProperty("pathFXfile");

			Vector<DealBean> beans = TradeCsvReader.read(path,
					new HelperDealBean());

			for (int i = 0; i < beans.size(); i++) {
				//ProductAppender.printLog(logConstants.INFO, "IN FOR LOOP");
				DealBean bean = beans.get(i);

				Trade trade = dealSender.buildTrade(bean);

				if (trade != null) {

					//trade.setAction("EXECUTE");
					
					int t = remoteTrade.saveTrade(trade);

					System.out.println("Trade id saved= " + t);

					if (t > 0) {
					
						String msg = "Trade Saved With id=" + t;
						TradeAppender.printLog(logConstants.INFO, msg);

					} else {
						
						TradeAppender.printLog(logConstants.INFO, logConstants.ERROR_WHILE_SAVING + i+1);
						
					}

				} else {
					
					TradeAppender.printLog(logConstants.INFO, logConstants.TRADE_WITH_XL_ROW_ID_NOT_SAVED + i);
					System.out.println("---" + logConstants.TRADE_WITH_XL_ROW_ID_NOT_SAVED + i+1);
					
				}

			}
			
			
		} catch (RemoteException e) {

			System.out.println(e);

		}
	}

}
