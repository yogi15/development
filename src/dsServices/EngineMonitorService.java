package dsServices;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

import util.commonUTIL;

import dsEventListener.AdminEvtListener;
import dsEventListener.EngineMonitorEvtListener;
import dsEventProcessor.AdminEventProcessor;
import dsEventProcessor.EngineEventMonitorProcessor;
import dsEventProcessor.EventProcessor;

public class EngineMonitorService extends Thread {
	static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    static int producer =0;
    static int consumer = 0;
    Thread monitorjob;
    RemoteEngineMonitor monitorserver = null;
	static ConcurrentHashMap<String,EngineTracker> engineTrackersData = new ConcurrentHashMap<String,EngineTracker> ();
	Hashtable<Integer,EventProcessor> events = new Hashtable<Integer,EventProcessor> ();
	EngineMonitorEvtListener mointerEvntListener; 
	EngineMonitorService(RemoteEngineMonitor monitorserver) {
		monitorjob = new Thread();
		mointerEvntListener = new EngineMonitorEvtListener("localhost",commonUTIL.getLocalHostName(),"");
		mointerEvntListener.start(mointerEvntListener);
		mointerEvntListener.setEngineMonitorService(this);
		this.monitorserver = monitorserver;
		
	//	this.start();
	}
	boolean startTrack = false;
	public void monitorLiveEngineService(EventProcessor event) {
		if(event != null) {
	    
		
		if(!event.isPublish()) {
			if(event instanceof EngineEventMonitorProcessor) {
				try {
			events.put(producer, event);
			producer++;
			EngineEventMonitorProcessor  engineMEvt = (EngineEventMonitorProcessor) event;
			//EngineTracker egnine 
		//	System.out.println("EngineMointer Consuming " + engineMEvt.getEngineName() + " " + engineMEvt.getIsAliveAtdateTime());
			synchronized (engineTrackersData) {
				
				EngineTracker etrack = addEngineForTrack(engineMEvt.getEngineName(),engineMEvt.getClientID());
				engineTrackersData.put(engineMEvt.getEngineName(),etrack );
				if(engineTrackersData.size() > 0) {
					startTrack = true;
				}
			}
			
			} catch(NullPointerException e) {
				System.out.println(" EnginMointorService getting NullPointor monitorLiveEngineService ");
			}
				catch(NumberFormatException e) {
					System.out.println(" EnginMointorService getting NumberFormatException "+e);
				}catch(Exception e) {
				System.out.println(" EnginMointorService getting Exception  "+e);
			}
			}
		}
	}
		
	}
	public void run() {
		for( ; ; ) {
			try {
				Thread.sleep(1500);
				synchronized (engineTrackersData) {
					if(startTrack) {
						
					Enumeration<EngineTracker>  eng =  engineTrackersData.elements();
					if(eng == null)
						return;
					EngineTracker trackEngine  = null;
					while(eng.hasMoreElements()) {
						trackEngine = eng.nextElement();
						if(trackEngine != null) {
							if(!isAlive(trackEngine.getLastestTimeUpdate())) {
								System.out.println("EngineMointer tracking " + trackEngine.getEngineName() + " Shutdown " + trackEngine.getLastestTimeUpdate());
							
								try {
									monitorserver.removeEngine(trackEngine.getEngineName()+"_Stopped",trackEngine.getClientID());
									break;
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								//addEngineForTrack(trackEngine.getEngineName());
								//System.out.println("EngineMointer tracking " + trackEngine.getEngineName() + " Alive "+trackEngine.getLastestTimeUpdate());
							}
							
						}
					} 
					if(trackEngine != null) {
						
					engineTrackersData.remove(trackEngine);
					trackEngine = null;
					}
					
				
				}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch(NullPointerException e) {
			System.out.println(" EnginMointorService getting NullPointor");
		} catch(NumberFormatException e) {
			System.out.println(" EnginMointorService getting NumberFormatException  "+e);
		}
		 catch(Exception e) {
			System.out.println(" EnginMointorService getting NullPointor "+e);
		}
		}
	}
	private boolean isAlive(String time) {
		boolean flag = true;
		try {
			Date date1 = format.parse(time);
			
				long diff = commonUTIL.getCurrentDate().getTime() - date1.getTime();
		    long diffSeconds = diff / 1000 % 60;
		   // System.out.println(diffSeconds);
		   if(diffSeconds > 1000000000) 
			   return false;
		  

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	} catch(NullPointerException e) {
		System.out.println(" EnginMointorService getting NullPointor isAlive method");
	}
		catch(NumberFormatException e) {
			System.out.println(" EnginMointorService getting NumberFormatException isAlive method");
		}
		 return flag;
		

	}
	public EngineTracker addEngineForTrack(String engine,int clientID) {
		
		String timeStampTrack = format.format(commonUTIL.getCurrentDate()); /// how to add the logic of date differene.
		EngineTracker egineTracker = new EngineTracker();
		egineTracker.setEngineName(engine);
		egineTracker.setLastestTimeUpdate(timeStampTrack);
		egineTracker.setRegisterDate(commonUTIL.getCurrentDateInString());
		egineTracker.setClientID(clientID);
		return egineTracker;
		
	}
	
	
	
	class EngineTracker {
		String engineName;
		String registerDate;
		int clientID =0;
		
		/**
		 * @return the clientID
		 */
		public int getClientID() {
			return clientID;
		}
		/**
		 * @param clientID the clientID to set
		 */
		public void setClientID(int clientID) {
			this.clientID = clientID;
		}
		/**
		 * @return the registerDate
		 */
		public String getRegisterDate() {
			return registerDate;
		}
		/**
		 * @param registerDate the registerDate to set
		 */
		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}
		/**
		 * @return the engineName
		 */
		public String getEngineName() {
			return engineName;
		}
		/**
		 * @param engineName the engineName to set
		 */
		public void setEngineName(String engineName) {
			this.engineName = engineName;
		}
		/**
		 * @return the lastestTimeUpdate
		 */
		public String getLastestTimeUpdate() {
			return lastestTimeUpdate;
		}
		/**
		 * @param lastestTimeUpdate the lastestTimeUpdate to set
		 */
		public void setLastestTimeUpdate(String lastestTimeUpdate) {
			this.lastestTimeUpdate = lastestTimeUpdate;
		}
		String lastestTimeUpdate;
		
	}

}
