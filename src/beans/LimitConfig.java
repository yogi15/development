package beans;

import java.io.Serializable;

public class LimitConfig implements Serializable {
	int id = 0;
String config_name = "";
String filter = "";
String createTime = "";
String modifiedTime = "";
String limitType = "";

public String getLimitType() {
	return limitType;
}
public void setLimitType(String limitType) {
	this.limitType = limitType;
}
public String getFilterType() {
	return filterType;
}
public void setFilterType(String filterType) {
	this.filterType = filterType;
}
public String getFilterValue() {
	return filterValue;
}
public void setFilterValue(String filterValue) {
	this.filterValue = filterValue;
}
String filterType="";
String filterValue="";

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getConfig_name() {
	return config_name;
}
public void setConfig_name(String config_name) {
	this.config_name = config_name;
}
public String getFilter() {
	return filter;
}
public void setFilter(String filter) {
	this.filter = filter;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getModifiedTime() {
	return modifiedTime;
}
public void setModifiedTime(String modifiedTime) {
	this.modifiedTime = modifiedTime;
}
public int getParentID() {
	// TODO Auto-generated method stub
	return parentID;
}
int parentID;
public void setParentID(int id) {
	// TODO Auto-generated method stub
	parentID = id;
}
	
}

