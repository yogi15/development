package beans;

import java.io.Serializable;

public class UserJobsDetails implements Serializable {
	
	int jobId;
	String filterValues;
	String and_or = "";
	
	public String getAnd_or() {
		return and_or;
	}
	public void setAnd_or(String and_or) {
		this.and_or = and_or;
	}
	public String getFilterValues() {
		return filterValues;
	}
	public void setFilterValues(String filterValues) {
		this.filterValues = filterValues;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	int rowid;
	String columnName;
	String criteria;
	String values;

	
}
