package apps;

import java.io.IOException;
import java.util.Properties;

import util.csvReader.CSVFileHandler;
import beans.Deal;
import beans.HelperLeBean;
import beans.HelperMMDealBean;
import beans.LeContacts;
import beans.LegalEntity;
import beans.Trade;
import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class LeUploader {

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

			DataSender dataSender = new DataSender();

			dataSender.setRemoteRefernce(remoteReference);

			Properties prop = new Properties();

			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CFG_FILE));
			String path = prop.getProperty("leFile");
			java.util.Vector<Deal> beans = CSVFileHandler.read(path,
					new HelperLeBean());

			for (int i = 0; i < beans.size(); i++) {
				Deal bean = (Deal) beans.get(i);
				LegalEntity le = dataSender.buildLe(bean);
				int leId = remoteReference.saveLe(le);
				int leContactId = 0;
				if (leId > 0) {
					
					LeContacts leContacts = dataSender.getLeContacts(bean);
					leContacts.setLeId(leId);
					leContactId= remoteReference.saveLeContacts(leContacts);
					
				}
				
				System.out.println("LeId= " + leId);
				System.out.println("LeContactId= " + leContactId);
				
				java.util.Vector message = new java.util.Vector();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}