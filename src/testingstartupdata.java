import java.rmi.RemoteException;
import java.util.Vector;

import beans.StartUPData;

import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;


public class testingstartupdata {
	
	public static void main(String args[]) {
		
		
		ServerConnectionUtil 	de =ServerConnectionUtil.connect("localhost", 1099,"127.0.0.1" );
	
	
		RemoteReferenceData remote;
		try {
			remote = (RemoteReferenceData) de.getRMIService("ReferenceData");
			Vector vec = (Vector) remote.getStartUPDataName();
			for(int i =0;i<vec.size();i++) {
				StartUPData data = (StartUPData) vec.elementAt(i);
				String insert = "insert into startupdata(name,value) values ('InitialData','"+data.getName()+"');";
				System.out.println(insert);
				System.out.println();
				System.out.println();
				
				getData(remote,data.getName());
				
				
			}
			System.out.println("PPPPPPPPPPPPPP");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void getData(RemoteReferenceData remote,String name) {
		try {
			
			Vector vec = (Vector) remote.getStartUPData(name);
			for(int i =0;i<vec.size();i++) {
				StartUPData data = (StartUPData) vec.elementAt(i);
				String insert = "insert into startupdata(name,value) values ('"+name+"','"+data.getName()+"');";
				System.out.println(insert);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
 
}
