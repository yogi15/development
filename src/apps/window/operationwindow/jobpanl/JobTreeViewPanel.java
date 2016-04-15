package apps.window.operationwindow.jobpanl;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import beans.UserJob;
import beans.Users;

public class JobTreeViewPanel extends JPanel {
	public JTree jTree1;
	private JScrollPane jScrollPane1;
	DefaultTreeModel treeModel = null;
	DefaultMutableTreeNode root = null;
	JPanel jPanel1 = null;
	FilterValues filterValues;
	
	/**
	 * @return the filterValues
	 */
	public FilterValues getFilterValues() {
		return filterValues;
	}

	/**
	 * @param filterValues the filterValues to set
	 */
	public void setFilterValues(FilterValues filterValues) {
		this.filterValues = filterValues;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTree1());
		}
		return jScrollPane1;
	}
	
	
	public JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			//jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel1.setLayout(new BorderLayout());
			//jPanel1.add(getJToolBar1(), new Constraints(new Bilateral(3, 3, 149), new Leading(5, 26, 10, 10)));
			jPanel1.add(getJScrollPane1(), BorderLayout.CENTER);
		}
		return jPanel1;
	}
	private Vector<UserJob> getUserjobs(int userid) {
		Vector<UserJob> jobs = null;
		try {
			jobs =	filterValues.remoteTask.getUserJob(userid,"TASK");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MainTaskPanel ", " getUserjobs" , e);
			
		}
		return jobs;
	}
	private JTree getJTree1() {
		Vector<UserJob> jobs = null;
		if (jTree1 == null) {
			jTree1 = new JTree();
			
			 root = new DefaultMutableTreeNode("Jobs");
			 treeModel = new DefaultTreeModel(root);
			 jobs = getUserjobs(user.getId()); 
			 
			if((jobs == null) ||  jobs.isEmpty() ||  jobs.size() == 0) {
				jobs = new Vector<UserJob>(); 
				DefaultMutableTreeNode trade = new DefaultMutableTreeNode("Trade");
				DefaultMutableTreeNode transfer = new DefaultMutableTreeNode("Transfer");
				DefaultMutableTreeNode message = new DefaultMutableTreeNode("Message");
				DefaultMutableTreeNode nTransfer = new DefaultMutableTreeNode("NettingTransfer");
				
				root.add(trade);
				root.add(transfer);
				root.add(message);
				root.add(nTransfer);
			     
			    
			     for(int i=0;i<root.getChildCount();i++) {
			    	 UserJob job = new UserJob();
			    //	 job.setUserID(user.getId());
			    	job.setId(0);
			    	job.setTreeid(i);
			    	job.setTreeNodeName(root.getChildAt(i).toString());
			    	job.setType("TASK");
			    	jobs.add(job);
			     }
			   saveJobs(jobs);
			} else {
				setJobs(jobs);
				for(int j=0;j<jobs.size();j++ ) {
					UserJob job = jobs.get(j);
					root.add(new DefaultMutableTreeNode(job.getTreeNodeName()));
				//	i = job.getTabid();
					
					
				}
			}
			treeModel = new DefaultTreeModel(root);
			jTree1.setModel(treeModel);
		}
		return jTree1;
	}
	private void saveJobs(Vector<UserJob> jobs2) {
		// TODO Auto-generated method stub
		if(jobs2 != null || jobs2.size() > 0) {
			for(int i=0;i<jobs2.size();i++) {
				UserJob job = jobs2.elementAt(i);
				
				try {
					job = (UserJob) filterValues.remoteTask.saveUserJob(job);
					if(jobs == null) 
						this.jobs = new Vector<UserJob>();
					jobs.add(job);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public Vector<UserJob> getJobs() {
		return jobs;
	}
	public void setJobs(Vector<UserJob> jobs) {
		this.jobs = jobs;
	}
	Users user = null;
	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	public Vector<UserJob> jobs = null;
}
