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
import beans.Coupon;
import beans.DealBean;
import beans.HelperDealBean;
import beans.HelperProductUploaderBean;
import beans.Product;
import beans.ProductUploaderBean;
import beans.Trade;

public class ProductUploader {

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

			ProductSender productSender = new ProductSender();
			
			Properties prop = new Properties();
			
			try {
				
				prop.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(CFG_FILE));

			} catch (IOException e) {
			
				e.printStackTrace();
			
			}
			
			String path = prop.getProperty("pathBondProductUploader");
			// String path = "F:\\sbijars\\FeedData1236.csv";
			
			Vector<ProductUploaderBean> beans = CSVProductFileHandler.read(path,
					new HelperProductUploaderBean());
			
			for (int i = 0; i < beans.size(); i++) {
				
				ProductUploaderBean bean = beans.get(i);
				
				if (productSender.validateProduct(bean) ) {
					
					Product product = productSender.buildProduct(bean);
					Coupon coupon 	= productSender.buildCoupon(bean);
					if ( product != null  && coupon != null ) {
						
						int t = remoteproduct.saveProduct(product, coupon);
						
						System.out.println("Product id saved= " + t);
						
						if ( t > 0 ) {
							
							String logType = "info";
							String msg = "Product Saved With id=" + t + " name= " + product.getName() + "/n" + 
							"Coupon id= " +coupon.getId();
							//ProductAppender.printLog(logConstants.INFO, msg);
							
						} else {
							
							//ProductAppender.printLog(logConstants.INFO, logConstants.ERROR_WHILE_SAVING);
							System.out.println(t);
						
						}
					
					} else {
						
					//	TradeAppender.printLog(logConstants.INFO, logConstants.PRODUCT_WITH_XL_ROW_ID_NOT_SAVED + i);;
						System.out.println("---" + logConstants.PRODUCT_WITH_XL_ROW_ID_NOT_SAVED + i+1);
						
					}
					
				} else {
					
				//	TradeAppender.printLog(logConstants.INFO, logConstants.PRODUCT_WITH_XL_ROW_ID_NOT_SAVED + i);
					System.out.println("---" + logConstants.PRODUCT_WITH_XL_ROW_ID_NOT_SAVED + i+1); 
				}
				

			}
			
		} catch (RemoteException e) {
			
			System.out.println(e);
			
		}
	}

}
