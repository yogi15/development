package dsManager;

import java.rmi.RemoteException;

import limit.LimitProcessor;

import util.commonUTIL;
import beans.Limit;
import beans.Trade;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.LimitEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteLimit;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class LimitManager  extends ControllerManager {
		String managerName = "LimitManager";
	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	LimitProcessor processor = null;
	Thread limitManagerThread = null;
	RemoteLimit remoteLimit = null;
	Limit message = null;
	String queueName = "LIMIT";
	
	
	public String getQueueName() {
		return queueName;
	}
	
	public LimitManager(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		try {
			processor = new LimitProcessor();
			remoteBO = (RemoteBOProcess) getDe().getRMIService("BOProcess");
			remoteTrade = (RemoteTrade) getDe().getRMIService("Trade");
	   		refData = (RemoteReferenceData) getDe().getRMIService("ReferenceData");
	   		remoteLimit = (RemoteLimit) getDe().getRMIService("Limit");
	   	        // setTransferTriggerEvt(refData.getStartUPData(transferTriggerEvt));
	   	    //  processor.setCancelTransferTriggerEvent(refData.getStartUPData(canceltransferTriggerEvt));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LimitManager", "In Constructor", e);
		}
   		
   		
		
		processor.setRemoteBOProcess(remoteBO);
		processor.setRefData(refData);
		processor.setRemoteTrade(remoteTrade);
		processor.setRemoteLimit(remoteLimit);
   		
		}
	


public Thread init(LimitManager amanager,Trade trade) {
	
	limitManagerThread = new Thread(amanager);
	return limitManagerThread;
}
public static void main(String args[]) {
	LimitManager lmanager = new LimitManager("localhost",commonUTIL.getLocalHostName(),"LimitManager");
	commonUTIL.display("LimitManager", "Started Limit Manager >>>>>>>> ");
	lmanager.start(lmanager);
}


public void handleEvent(EventProcessor event)	 {
		if(event instanceof TradeEventProcessor) {
			TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
			System.out.println(tradeEvent.getTrade().getId());
		
			Trade trade = tradeEvent.getTrade();
			if(isTradeValidForLimit(trade)) {
		    	processor.processLimitOnTrade(trade,tradeEvent);
			} else {
				commonUTIL.display("LimitManager" , "Trade not valid for limit as no changes in trade data");
				
				
				
			}
			    // publishLimit(tradeEvent.getTrade());
			
		}
	}

private boolean isTradeValidForLimit(Trade trade) {
	// TODO Auto-generated method stub
	if(trade.getVersion() == 1)
		return true;
	if(trade.isEconomicChanged())
		return true;
	return false;
}

}
