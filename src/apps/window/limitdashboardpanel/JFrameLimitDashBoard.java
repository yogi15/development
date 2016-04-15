package apps.window.limitdashboardpanel;

import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JFrame;

import util.commonUTIL;

import dsServices.RemoteLimit;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.ServerConnectionUtil;
 
import beans.StartUPData;
import beans.UserJob;
import beans.Users;

public class JFrameLimitDashBoard extends JFrame  {
	
	 Users user = null;
	 LimitDashBoardPanel limitPanel = null;
	 public static  ServerConnectionUtil de = null;
	 RemoteLimit remoteLimit;
	 RemoteTask remoteTask;
	 RemoteReferenceData remoteBORef = null;
	 LimitFilterValues filterValues = null;
		Vector<StartUPData> searchCriteria;
		Vector<StartUPData> searchColumn;
		Vector<UserJob> jobs = null;
		String reportType = "LimitDashBoard";
		String columnSQL;
	 
	 public JFrameLimitDashBoard(String name,Users user)  {
			super(name);
			setUser(user);
			de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
			
			try {
				remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
				remoteLimit =  (RemoteLimit) de.getRMIService("Limit");
				remoteTask  =  (RemoteTask) de.getRMIService("Task");
				 initComponents();
	 }catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameLimitDashBoard ", " Constructor " , e);
		}
	}
	 
	 private void initComponents() {
			getUserTempleates(user.getId());
			UserJob job = jobs.elementAt(0);
			  Vector detailsJob = job.getDetailsJobs();
			  limitPanel  = new LimitDashBoardPanel(remoteBORef,remoteTask,remoteLimit,user);
			  add(limitPanel);
		}
	
	 private void setUser(Users user2) {
			// TODO Auto-generated method stub
			this.user = user2;
			
		}
		
		private Vector<UserJob> getUserTempleates(int userid) {
			Vector<UserJob> jobs = null;
			try {
				jobs =	remoteTask.getUserJob(userid,"LIMIT"); 
				if((jobs == null) || jobs.isEmpty()) {
					UserJob job = new UserJob();
					job.setId(0);
					job.setTabid(0);
					job.setUserID(user.getId());
					job.setType(reportType.toUpperCase());
					job.setTreeNodeName("CounterPartyLimit");
					jobs = new Vector<UserJob>();
					jobs.add(job);
					saveJobs(jobs);
					
				}else {
					setJobs(jobs);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("JFrameLimitDashBoard ", " getUserTempleates " , e);
					
				}
				return jobs;
			}
	
			
		
			
			private void saveJobs(Vector<UserJob> jobs2) {
				// TODO Auto-generated method stub
				if(jobs2 != null || jobs2.size() > 0) {
					for(int i=0;i<jobs2.size();i++) {
						UserJob job = jobs2.elementAt(i);
						
						try {
							job = (UserJob) remoteTask.saveUserJob(job);
							if(jobs == null) 
								this.jobs = new Vector<UserJob>();
							jobs.add(job);
							
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					setJobs(jobs);
				}
				
			}
			private void setJobs(Vector<UserJob> jobs2) {
				// TODO Auto-generated method stub
				this.jobs = jobs2;
			}
			

}
