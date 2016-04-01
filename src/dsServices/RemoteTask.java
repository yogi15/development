package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;
import java.rmi.Remote;

import beans.Task;
import beans.UserJob;
import beans.UserJobsDetails;

public interface RemoteTask extends Remote {
	
	public Collection  selectTaskWhere(String sql) throws RemoteException;
	public UserJob saveUserJob(UserJob job) throws RemoteException;
	public void saveUserJobsDetails(Vector<UserJobsDetails> jobs,int jobId) throws RemoteException;
	public boolean deleteUserJob(UserJob job) throws RemoteException;
	public boolean updateJob(UserJob job) throws RemoteException;
	public Vector<UserJob> getUserJob(int userID) throws RemoteException;
	public Vector<UserJob> getUserJob(int userID,String type) throws RemoteException;
	public Collection getDetailsJob(int jobid) throws RemoteException;
	public boolean updateDetailsJob(UserJobsDetails jobDetails) throws RemoteException;
	
	public boolean deleteUserJob(UserJobsDetails detJob) throws RemoteException;
	public Collection generateReport(String sql) throws RemoteException;

	public int saveTask(Task t) throws RemoteException;
	public Collection selectALLtasks(Task t) throws RemoteException;
	public boolean updateTask(Task t) throws RemoteException;
	public Task getTask(int taskID) throws RemoteException;
	public Collection getTaskOnTrade(int trade) throws RemoteException;
	public Collection getTaskOnTransfer(int transfer) throws RemoteException;
	public boolean updateTaskStatus(Task task,String status) throws RemoteException;
	public boolean updateTaskStatus(Task task, int userid, String user_name,
			String status)  throws RemoteException;
	
}
