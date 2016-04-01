package mainServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import beans.DealBean;
import beans.Trade;

import dsServices.RemoteDeal;
import dsServices.RemoteDealStation;

import eventPS.EventPSDealData;

public class PublishEvent {
	
	Socket s;
	
	public PublishEvent(String localhost,int port,Trade trade) {
try {
	s = new Socket(localhost,port);
	OutputStream os = s.getOutputStream();   
	ObjectOutputStream oos = new ObjectOutputStream(os);
	EventPSDealData to = new EventPSDealData(); 
	to.setTrade(trade);
	oos.writeObject(to);   
	//oos.writeObject(new String("another object from the client"));   
	oos.close();   
	os.close();   
	s.close();  
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}   
		 
	}
	
	public static void main(String args[]) {
	try{   
		Socket s = new Socket("localhost",3983);   
		
		OutputStream os = s.getOutputStream();   
		ObjectOutputStream oos = new ObjectOutputStream(os);   
		EventPSDealData to = new EventPSDealData();   
		to.setId("pankaj");
		to.setValue(12);
		oos.writeObject(to);   
		oos.writeObject(new String("another object from the client"));   
		oos.close();   
		os.close();   
		s.close();   
		}catch(Exception e){
				System.out.println(e);
				}   
	} 
		
		

}

