package beans;

import java.io.Serializable;

import constants.FolderConstants;

public class Folder implements BaseBean {
	
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFolder_name() {
		return Folder_name;
	}
	public void setFolder_name(String folderName) {
		this.Folder_name = folderName;
	}
	String Folder_name;
	 
	
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj =null;
		 if(propertyPaneColumnName.equalsIgnoreCase(FolderConstants.FOLDER_NAME)) { 
		 obj = getFolder_name() ; }  
		 return obj;  } 


		 public void setPropertyValue(String propertyPaneColumnName, Object object) { 
		 if(propertyPaneColumnName.equalsIgnoreCase(FolderConstants.FOLDER_NAME)) { 
	 setFolder_name((String)object) ; }  
		 }
	

}
