package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;

import beans.Task;
import beans.UserJob;
import beans.UserJobsDetails;
import dbSQL.JobDetailsSQL;
import dbSQL.PostingSQL;
import dbSQL.TaskSQL;
import dbSQL.TradeSQL;
import dbSQL.UserJobSQL;
import dbSQL.dsSQL;
import dsEventProcessor.TaskEventProcessor;



public class TaskImp implements RemoteTask {
	
	RemoteTrade remoteTrade = null;
	 public static  ServerConnectionUtil de = null;
	@Override
	public Collection selectTaskWhere(String sql) throws RemoteException {
		// TODO Auto-generated method stub
		//TaskSQL
		return TaskSQL.selectTaskWhere(sql, dsSQL.getConn());
		
	}

	@Override
	public UserJob saveUserJob(UserJob job) throws RemoteException {
		// TODO Auto-generated method stub
		UserJob newjob= UserJobSQL.save(job, dsSQL.getConn());
		if(job != null)
		saveJobDetails(job.getDetailsJobs(),newjob.getId()) ;
		return newjob;
	}
	
	private Vector<UserJobsDetails> saveJobDetails(Vector<UserJobsDetails>  jobDetails,int jobID) {
		Vector<UserJobsDetails> details = null;
		if( jobDetails == null || jobDetails.size() == 0)
		   return details;
		JobDetailsSQL.delete(jobID,dsSQL.getConn());
			details = new Vector<UserJobsDetails>();
			for(int i=0;i<jobDetails.size();i++) {
				UserJobsDetails jdets = jobDetails.get(i);
				jdets.setJobId(jobID);
				JobDetailsSQL.save(jdets, dsSQL.getConn());
				details.add(jdets);
			}
		return details;
		
		
		
	}

	
	
	@Override
	public boolean deleteUserJob(UserJob job) throws RemoteException {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(JobDetailsSQL.delete(job.getId(),dsSQL.getConn())) 
		   return UserJobSQL.delete(job, dsSQL.getConn());
		else 
			return flag;
	}
	
	@Override
	public boolean updateJob(UserJob job) throws RemoteException {
		// TODO Auto-generated method stub
		return  UserJobSQL.update(job, dsSQL.getConn());
	}

	@Override
	public Vector<UserJob>  getUserJob(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		Vector<UserJob> job = new Vector<UserJob>();
		Vector jobs =  UserJobSQL.selectUserJob(userID, dsSQL.getConn());
		if(jobs != null && jobs.size() > 0) {
			   for(int i=0;i<jobs.size();i++) {
				   UserJob userJobs = (UserJob) jobs.elementAt(i);
				   Vector detailsJob =(Vector) getDetailsJob(userJobs.getId());
					
				   userJobs.setDetailsJobs(detailsJob);
				   job.add(userJobs);
			   }
			      
		}
				
		return job;
	}
   
	
	
	
	@Override
	public Collection getDetailsJob(int jobid) throws RemoteException {
		// TODO Auto-generated method stub
		return JobDetailsSQL.selectUserJobsDetails(jobid,  dsSQL.getConn());
	}

	@Override
	public boolean updateDetailsJob(UserJobsDetails jobDetails)
			throws RemoteException {
		// TODO Auto-generated method stub
		 return JobDetailsSQL.update(jobDetails, dsSQL.getConn());
	}

	@Override
	public boolean deleteUserJob(UserJobsDetails detJob) throws RemoteException {
		// TODO Auto-generated method stub
		return JobDetailsSQL.delete(detJob, dsSQL.getConn());
		
	}

	@Override
	public void saveUserJobsDetails(Vector<UserJobsDetails> detailsjob,int jobID)
			throws RemoteException {
		// TODO Auto-generated method stub
		JobDetailsSQL.delete(jobID,dsSQL.getConn());
		for(int i=0;i<detailsjob.size();i++) {
			UserJobsDetails jobd = detailsjob.get(i);
		    JobDetailsSQL.save(jobd, dsSQL.getConn()); 
		}
	}

	@Override
	public Collection generateReport(String sql) throws RemoteException {
		// TODO Auto-generated method stub
		
		return  TaskSQL.selectwhereforReports(sql, dsSQL.getConn());
	
		
	}

	@Override
	public int saveTask(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		return	TaskSQL.save(t, dsSQL.getConn());
		
	}

	@Override
	public Collection selectALLtasks(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		return TaskSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public boolean updateTask(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		return TaskSQL.update(t, dsSQL.getConn());
	}

	@Override
	public Task getTask(int taskID) throws RemoteException {
		// TODO Auto-generated method stub
		Vector task =  (Vector) TaskSQL.selectTask(taskID,dsSQL.getConn());
		if(task == null)
			return null;
		else 
			return (Task) task.elementAt(0);
	}

	@Override
	public boolean updateTaskStatus(Task task,String status) throws RemoteException {
		// TODO Auto-generated method stub
	
		if(TaskSQL.updateTaskStatus(task.getId(),status, dsSQL.getConn())) {
			task.setTaskstatus(status);
			if(remoteTrade == null) {
				initRemoteInterface();
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",getTaskEvent(task));
			} else {
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",getTaskEvent(task));
			}
			return true;
		}
		return false;
	}

	
	private TaskEventProcessor getTaskEvent(Task task) {
		TaskEventProcessor taskEvent = new TaskEventProcessor();
		taskEvent.setTask(task);
		//taskEvent.setT
		taskEvent.setTaskID(task.getId());
		return taskEvent;
	}
	
	private void initRemoteInterface() {
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
		try {
			remoteTrade = (RemoteTrade) de.getRMIService("Trade");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		commonUTIL.displayError("BOProcessImp", "initRemoteInterface", e);
		}
	}
	
	@Override
	public Collection getTaskOnTrade(int tradeid) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " tradeid = " + tradeid;
		return  TaskSQL.selectTaskWhere(sql,dsSQL.getConn());
	}

	@Override
	public Collection getTaskOnTransfer(int transferid) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " transferID = " + transferid;
		return  TaskSQL.selectTaskWhere(sql,dsSQL.getConn());
	
	}

	@Override
	public Vector<UserJob>  getUserJob(int userID, String type)
			throws RemoteException {
		// TODO Auto-generated method stub
		Vector<UserJob> job = new Vector<UserJob>();
		Vector<UserJob>  jobs = (Vector) UserJobSQL.select(userID, type,dsSQL.getConn());
		if(jobs != null && jobs.size() > 0) {
			   for(int i=0;i<jobs.size();i++) {
				   UserJob userJobs = (UserJob) jobs.elementAt(i);
				   Vector detailsJob =(Vector) getDetailsJob(userJobs.getId());
					
				   userJobs.setDetailsJobs(detailsJob);
				   job.add(userJobs);
			   }
			      
		}
		return job;
	}

	@Override
	public boolean updateTaskStatus(Task task, int userid, String user_name,
			String status) throws RemoteException {
		if(TaskSQL.updateTaskStatus(task.getId(),userid,user_name,status, dsSQL.getConn())) {
			task.setTaskstatus(status);
			if(remoteTrade == null) {
				initRemoteInterface();
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",getTaskEvent(task));
			} else {
				remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE",getTaskEvent(task));
			}
			return true;
		}
		return false;
	}
}

