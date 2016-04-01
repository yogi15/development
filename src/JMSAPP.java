import util.commonUTIL;
import mqServices.messageProducer.CreateNewMessage;
import beans.Trade;
import beans.Transfer;


public class JMSAPP {
	public static void main(String[] args) throws Exception {
		
		Trade trade1 = new Trade();
		trade1.setId(120);
		Transfer transfer1 = new Transfer();
		transfer1.setId(220);
		CreateNewMessage newMessage = new CreateNewMessage(commonUTIL.getLocalHostName()+":61616");
		newMessage.produceNewMessage("POS","TRADE","Object",trade1,"true"); 
		thread(newMessage, false);     
	//	Thread.sleep(100);
		newMessage.produceNewMessage("POS","TRANSFER","Object",transfer1,"true"); 
		thread(newMessage, false);       
		//thread(newMessage, false);   
		//thread(newMessage, false);   
		//Thread.sleep(100);
		
	}
	
	 public static void thread(Runnable runnable, boolean daemon) {
		 Thread brokerThread = new Thread(runnable);
		 brokerThread.setDaemon(daemon);       
		 brokerThread.start();
	 }
}
