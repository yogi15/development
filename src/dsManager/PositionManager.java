package dsManager;

import java.rmi.RemoteException;
import java.util.Hashtable;

import beans.Trade;
import util.RemoteServiceUtil;
import util.commonUTIL;
import mo.positionmang.PositionProcessor;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteMO;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class PositionManager extends ControllerManager   {
	 static private String hostName = "";
	    public static  ServerConnectionUtil de = null;
	String managerName = "PostionManager";
	RemoteBOProcess remoteBO;
	RemoteTrade remoteTrade;
	RemoteReferenceData refData;
	RemoteMO remoteMO;
	PositionProcessor posProcessor = null;
	int counter = 0;
	int consumeCounter =0;
	RemoteProduct remoteProduct;
	public Hashtable<Integer,TradeEventProcessor> tradeEvents = new Hashtable<Integer,TradeEventProcessor>();
	boolean seq = true;
	 public Thread getManagerThread() {
		return positionManagerThread;
	}
	Thread positionManagerThread = null;
	public PositionManager(String host, String hostName, String managerName) {
		super(host, hostName, managerName,"POSITION");
		hostName = commonUTIL.getLocalHostName();
	 
	   		
	   		remoteMO = RemoteServiceUtil.getRemoteMOService();
	   		remoteTrade = RemoteServiceUtil.getRemoteTradeService();
	   		remoteProduct =RemoteServiceUtil.getRemoteProductService();
	   		refData =  RemoteServiceUtil.getRemoteReferenceDataService();
	   		posProcessor = new PositionProcessor();
	   		posProcessor.setRemoteReference(refData);
	   		posProcessor.setRemoteMO(remoteMO);
	   		posProcessor.setRemoteTrade(remoteTrade);
	   		posProcessor.setRemoteProduct(remoteProduct);
	   		posProcessor.setManager(this);
	   		posProcessor.start();
			 
	   	
		// TODO Auto-generated constructor stub
	}
	public synchronized void handleEvent(EventProcessor event)	 {
		if(event instanceof TradeEventProcessor) {
		
			TradeEventProcessor tradeEvent = (TradeEventProcessor) event;
			
			if(seq) {
				System.out.println(tradeEvent.getTradeID() + " version  " + tradeEvent.getTrade().getVersion() + "  " + seq);
				seq = false;
			  if(!tradeEvent.getTrade().getProductType().equalsIgnoreCase("MM")) { // positions are not created on MM products. { 
			  processPosition(tradeEvent.getTrade());
			  } else {
				  processCashPositionOnly(tradeEvent.getTrade());
			  }
				//posProcessor.addEvents(tradeEvent);
			  //  posProcessor.readEvents();
			} else {
				counter++;
			//	System.out.println(tradeEvent.getTradeID() + " version  " + tradeEvent.getTrade().getVersion() + "  " + seq);
				tradeEvents.put(counter, tradeEvent);
				System.out.println("tradeEvents  "+ tradeEvents.size());
			//	if(consumeCounter == 0)
			//	processPosition();
			}
			    
		}
	}
		

	// this method is used for MM product Type only in case of MM no open position are created 
	// But we need for Cashposition to report products
	
	private void processCashPositionOnly(Trade trade) {
		// TODO Auto-generated method stub
		posProcessor.processPositiononOnMMTrade(trade);
		
	}
	public  void processPosition(Trade trade) {
		
		synchronized (posProcessor) {
			System.out.println("        Start Process of generating position for trade " + trade.getId() + " version == " + trade.getVersion());
			posProcessor.processPositiononTrade(trade);
			System.out.println("        End Process of generating position for trade  " + trade.getId());
		}
		
	}
	
public void stop() {
	try {
		positionManagerThread.interrupt();
		throw new InterruptedException();
	} catch (java.lang.InterruptedException e) {
		// TODO Auto-generated catch block
		commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
	}
	
}

public Thread init(PositionManager amanager) {
	
	positionManagerThread = new Thread(amanager);
	return positionManagerThread;
}
public static void main(String args[]) {
	PositionManager amanager = new PositionManager("localhost",commonUTIL.getLocalHostName(),"PositionManager");
    
	amanager.start(amanager);
}


}
