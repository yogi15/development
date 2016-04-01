package beans;

import java.io.Serializable;
 

public interface   BaseBean extends Serializable {
	
	
	
	
	public   Object getPropertyValue(String propertyPaneColumnName);
	public   void setPropertyValue(String propertyPaneColumnName,Object object);
	
	

}
