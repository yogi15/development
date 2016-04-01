package dsManager;

import java.util.Hashtable;

import util.commonUTIL;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.QuoteEventProcessor;
import marketquotes.FeedListener;

public class QuoteManager  extends ControllerManager   {
	
	
	String managerName = "QuoteManager";
	Hashtable<String,FeedListener> feedListener =  new Hashtable<String,FeedListener>();
	public QuoteManager(String host, String hostName, String managerName) {
		super(host, hostName, managerName);
		// TODO Auto-generated constructor stub
	}
	 public  void addListeners(String name, FeedListener readListener) {
		 feedListener.put(name, readListener);
		}
	 
	 Thread quoteManagerThread = null;
		
		public Thread getManagerThread() {
			return quoteManagerThread;
		}
		public void handleEvent(EventProcessor event)	 {
			if(event instanceof QuoteEventProcessor ) {
				QuoteEventProcessor quoteEvent = (QuoteEventProcessor) event;
				for(int i =0;i<feedListener.size();i++) {
					FeedListener listener = feedListener.get(quoteEvent.getFeedname());
					listener.listenFeed(quoteEvent.getQuoteData());
				}
				
			}
		}
		public void stop() {
			try {
				managerThread.interrupt();
				throw new InterruptedException();
			} catch (java.lang.InterruptedException e) {
				// TODO Auto-generated catch block
				commonUTIL.display(manager.getManagerName(), manager.getManagerName() +"  is stop");
			}
			
		}
		
		public static void main(String args[]) {
			QuoteManager amanager = new QuoteManager("localhost",commonUTIL.getLocalHostName(),"TaskManager");
		    
			amanager.start(amanager);
		}
}
