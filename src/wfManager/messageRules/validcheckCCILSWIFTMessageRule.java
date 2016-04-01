package wfManager.messageRules;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Vector;

import logAppender.MessageServiceAppender;

import productPricing.Pricer;
import beans.LegalEntity;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import wfManager.MessageRule;

public class validcheckCCILSWIFTMessageRule implements MessageRule {

	@Override
	public boolean checkValid(Trade newTrade, Transfer transfer,
			Message message, Vector messageData, RemoteTrade remoteTrade,
			RemoteBOProcess remoteBo, RemoteReferenceData remoteRef,
			Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		try {
			
			LegalEntity receiver = remoteRef.selectLE(message.getReceiverId());
			LegalEntity sender = remoteRef.selectLE(message.getSenderId());
						
			if(message.getFormat().equalsIgnoreCase("CCIL")) {
				
						if (!(sender.getCountry().equalsIgnoreCase("India") 
						&& receiver.getCountry().equalsIgnoreCase("India") 
						&& newTrade.getProductType().equals("FX")
						&& newTrade.getTradedesc().equals("USD/INR"))) {
					
					messageData.addElement(new String("validCCILSWIFTMessageRule failed As Message will not generated for CCIL"));
					
				} 			
				
			} else  {
				if ((sender.getCountry().equalsIgnoreCase("India") 
			
					&& receiver.getCountry().equalsIgnoreCase("India") 
					&& newTrade.getProductType().equals("FX")
					&& newTrade.getTradedesc().equals("USD/INR"))) {
				
					messageData.addElement(new String("validCCILSWIFTMessageRule failed As Message will not generated for CCIL"));
				
				}
			}
			
		}  catch (NullPointerException e) {
			// TODO Auto-generated catch block
			 MessageServiceAppender.printLog("ERROR", "WorkflowRule Failed in  checkCCILSWIFT ");
				
			return true;
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 MessageServiceAppender.printLog("DEBUG", "WorkflowRule checkCCILSWIFT exectued successfully ");
			
		return true;
	}

	@Override
	public boolean update(Trade newTrade, Transfer transfer, Message message,
			Vector messageData, RemoteTrade remoteTrade,
			RemoteBOProcess remoteBo, RemoteReferenceData remoteRef,
			Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
	
		return true;
	}

}
