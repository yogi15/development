package mainServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import dsServices.ServerConnectionUtil;

import dsServices.ConfigService;
import dsServices.ServerControllerImp;
import dsServices.ServiceInit;

import enginePS.EnginePS;
import enginePS.EnginePSDealReader;
import enginePS.EngineTransfer;
import eventPS.EventPS;
import eventPS.EventPSDealData;


public class ProcessServer implements Runnable {
	
	InputStream is = null;
	ObjectInputStream ois = null;
	private ServerSocket ss = null;
    
	private Map mMap = new HashMap();
	 private Map rmiServices = Collections.synchronizedMap(new HashMap());
	    private static ThreadLocal requestInfo = new ThreadLocal();
	    
    public ProcessServer(int i) {
    	try {
			ss = new ServerSocket(i);
			ServerControllerImp server = new ServerControllerImp();
			server.start();
			
			createEnginesInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	
    }
   
	
	// need to be register with db. 
    public void createEnginesInstance() {
    	EnginePSDealReader dealReader = new EnginePSDealReader();
    	EngineTransfer transfer = new EngineTransfer();
    	mMap.put("dealReader", dealReader );
    	mMap.put("transfer", transfer );
    }
	
    
    
    
    public void processEvents(EventPS event) {
    	Iterator iter = mMap.entrySet().iterator();

    	while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			EnginePS engine = (EnginePS) mEntry.getValue();
			engine.process(event);
		}

    }
	public void run()
	   {
	      while(true)
	      {
	         try
	         {
	        	 Socket s = ss.accept();   
	        	is =  s.getInputStream();   
	        	ois = new ObjectInputStream(is);  
	        	EventPS to;
				try {
					to = (EventPS)ois.readObject();
					
					processEvents(to);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
	        	
	        	  
	         }catch(IOException e)
	         {
	        	 stop1();
	            e.printStackTrace();
	            
	           
	           
	            break;
	         }finally {
	        	 //stop1();
	         }
	      }
	     // stop1();
	     
	     
	   }

	public void stop1() {
		
		System.out.println(" Server got down");
		System.exit(0);
		
	}
	public static void main(String args[]) {
	
	int port = 2002;   
	
	
	try {   
		ProcessServer serverStart = new ProcessServer(1234);
		Thread Server = new Thread(serverStart);
		Server.start();
		System.out.println("Server has started ..... ");
	}catch(Exception e){
		System.out.println(e);}   
	}   
	}   

  
  

