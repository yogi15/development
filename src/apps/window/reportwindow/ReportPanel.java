package apps.window.reportwindow;

import java.util.Vector;

import javax.swing.JPanel;

import beans.StartUPData;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.positionwindow.PositionFilterValues;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
import apps.window.reportwindow.util.ReportColumns;
import apps.window.reportwindow.util.ReportSQLGenerator;

import dsServices.RemoteBOProcess;
import dsServices.RemoteMO;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;


public abstract class ReportPanel extends JPanel {
	
		RemoteReferenceData referenceData;
		UserJob userJob;
		RemoteMO remoteMO;
		PositionFilterValues positionFilterValues;
		/**
		 * @return the positionFilterValues
		 */
		public PositionFilterValues getPositionFilterValues() {
			return positionFilterValues;
		}
		/**
		 * @param positionFilterValues the positionFilterValues to set
		 */
		public void setPositionFilterValues(PositionFilterValues positionFilterValues1) {
			this.positionFilterValues = positionFilterValues1;
			
		}
		/**
		 * @return the remoteMO
		 */
		public RemoteMO getRemoteMO() {
			return remoteMO;
		}
		/**
		 * @param remoteMO the remoteMO to set
		 */
		public void setRemoteMO(RemoteMO remoteMO) {
			this.remoteMO = remoteMO;
		}
		public SwingReportDemo demo = null;
		/**
		 * @return the demo
		 */
		public SwingReportDemo getDemo() {
			return demo;
		}
		/**
		 * @param demo the demo to set
		 */
		public void setDemo(SwingReportDemo demo) {
			this.demo = demo;
		}
		/**
		 * @return the pReport
		 */
		public PivotReport getpReport() {
			return pReport;
		}
		/**
		 * @param pReport the pReport to set
		 */
		public void setpReport(PivotReport pReport) {
			this.pReport = pReport;
		}
		public PivotReport pReport = null;
		public String sql = "";
		
		
		public String getColumnSQL() {
			return this.sql;
		}
		public UserJob getUserJob() {
			return userJob;
		}
		public void setUserJob(UserJob userJob) {
			this.userJob = userJob;
		}
		public RemoteReferenceData getReferenceData() {
			return referenceData;
		}
		public void setReferenceData(RemoteReferenceData referenceData) {
			this.referenceData = referenceData;
		}
		public RemoteTrade getRemoteTrade() {
			return remoteTrade;
		}
		public void setRemoteTrade(RemoteTrade remoteTrade) {
			this.remoteTrade = remoteTrade;
		}
		public RemoteProduct getRemoteproduct() {
			return remoteproduct;
		}
		public void setRemoteproduct(RemoteProduct remoteproduct) {
			this.remoteproduct = remoteproduct;
		}
		public ReportColumns getColumns() {
			return columns;
		}
		public void setColumns(ReportColumns columns) {
			this.columns = columns;
		}
		public ReportSQLGenerator getSqlGen() {
			return sqlGen;
		}
		public void setSqlGen(ReportSQLGenerator sqlGen) {
			this.sqlGen = sqlGen;
		}
		public FilterValues getFilterValues() {
			return filterValues;
		}
		public void setFilterValues(FilterValues filterValues) {
			this.filterValues = filterValues;
		}
		public Vector<StartUPData> getSearchCriteriaA() {
			return searchCriteriaA;
		}
		public void setSearchCriteriaA(Vector<StartUPData> searchCriteriaA) {
			this.searchCriteriaA = searchCriteriaA;
		}
		public Vector<StartUPData> getSearchColumn() {
			return searchColumn;
		}
		public void setSearchColumn(Vector<StartUPData> searchColumn) {
			this.searchColumn = searchColumn;
		}
		public Vector<UserJob> getJobs() {
			return jobs;
		}
		public void setJobs(Vector<UserJob> jobs) {
			this.jobs = jobs;
		}
		public RemoteTask getRemoteTask() {
			return remoteTask;
		}
		public void setRemoteTask(RemoteTask remoteTask) {
			this.remoteTask = remoteTask;
		}
		public RemoteBOProcess getRemoteBo() {
			return remoteBo;
		}
		public void setRemoteBo(RemoteBOProcess remoteBo) {
			this.remoteBo = remoteBo;
		}
		public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		public  JPanel loadreport(Vector data) {
			return demo;
			
		}
		Vector<UserJobsDetails> detailsJobs;
		
		 RemoteTrade remoteTrade;
		 RemoteProduct remoteproduct;
		 ReportColumns columns;
		 ReportSQLGenerator sqlGen;
		 FilterValues filterValues = null;
		 Vector<StartUPData> searchCriteriaA;
		 Vector<StartUPData> searchColumn;
		 Vector<UserJob> jobs = null;
		 RemoteTask remoteTask;
		 RemoteBOProcess remoteBo = null;
		 Users user = null;
		 public abstract void populateReportData(String sql,boolean replaceColumns);
		 public abstract  void setColumnSQL(String sql);
	    	public abstract  JPanel loadreport();
			// TODO Auto-generated method stub
			public void setUserJobsDetails(Vector<UserJobsDetails> detailsJobs) {
				// TODO Auto-generated method stub
				this.detailsJobs = detailsJobs;
			}
			public Vector<UserJobsDetails> getUserJobsDetails() {
				return detailsJobs;
			}
			
		
		 
			// TODO Auto-generated method stub
			
		}


