package beans;

import java.io.Serializable;
import java.util.Vector;

public class UserJob implements Serializable {
	int id;
	int userID;
	int Treeid;
	String type;
	String sql;
	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String treeNodeName;
	int tabid;
	String tabName;
	Vector<UserJobsDetails> detailsJobs = null;
	
	public Vector<UserJobsDetails> getDetailsJobs() {
		return detailsJobs;
	}
	public void setDetailsJobs(Vector<UserJobsDetails> detailsJobs) {
		this.detailsJobs = detailsJobs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getTreeid() {
		return Treeid;
	}
	public void setTreeid(int treeid) {
		Treeid = treeid;
	}
	public String getTreeNodeName() {
		return treeNodeName;
	}
	public void setTreeNodeName(String treeNodeName) {
		this.treeNodeName = treeNodeName;
	}
	public int getTabid() {
		return tabid;
	}
	public void setTabid(int tabid) {
		this.tabid = tabid;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public void addUserJobDetails(UserJobsDetails jobDetails) {
		if(detailsJobs == null) {
			detailsJobs = new Vector<UserJobsDetails>();
			detailsJobs.add(jobDetails);
		} else {
			detailsJobs.add(jobDetails);
		}
	}

}
