package beans;

public class FilterBean {
	
	String ColumnName = "";
	String idSelected = "";
	String and_or = "";
	public String getAnd_or() {
		return and_or;
	}
	public void setAnd_or(String and_or) {
		this.and_or = and_or;
	}
	public String getIdSelected() {
		return idSelected;
	}
	public void setIdSelected(String idSelected) {
		this.idSelected = idSelected;
	}
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String criteria) {
		searchCriteria = criteria;
		
	//	this.searchCriteria = searchCriteria;
	}
	public String getColumnValues() {
		return columnValues;
	}
	public void setColumnValues(String values) {
		columnValues =values;
		//this.columnValues = columnValues;
	}
	String searchCriteria= "";;
	String columnValues= "";
	

}
