package mqServices.Broker;

import org.apache.activemq.broker.BrokerService;

public class StartMQBroker {
	
	
	public StartMQBroker(String host,int port) {
		start(host,port);
	}
	public void start(String host,int port) {
	
	BrokerService broker = new BrokerService(); 
    // configure the broker 
    try {
	//	broker.addConnector("tcp://LPT062-M1:61616");
    	broker.addConnector("tcp://"+ host+":"+ port);
		broker.setBrokerName("Broker1"); 
        broker.setUseJmx(false); 
      //  broker.set
        broker.start(); 
        System.out.println(" JMS Service started at " + host + " port " + port + " successfully >>>>>> ");
        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }

	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(" JMS Service ERROR *************  "+e);
		try {
			broker.stop();
			System.exit(0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	}
	public static void main(String args[]) {
		
		StartMQBroker start = new StartMQBroker(null,0);
		//start.start(null, port)
	}


}
