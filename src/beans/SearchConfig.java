package beans;

import java.util.Vector;

import util.commonUTIL;
import apps.window.util.propertyUtil.Selection;

import constants.SearchConfigConstants;

public class SearchConfig implements BaseBean {
	
	
	
	int id;
	String columnsNames =  null;
	String beanName = null;
	String attributeType = null;
	/**
	 * @return the attributeType
	 */
	public String getSearchAttributes() {
		return attributeType;
	}
	/**
	 * @param attributeType the attributeType to set
	 */
	public void setSearchAttributes(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}
	/**
	 * @param beanName the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	/**
	 * @return the columnsNames
	 */
	public String getColumnNames() {
		return columnsNames;
	}
	/**
	 * @param columnsNames the columnsNames to set
	 */
	public void setColumnNames(String columnsNames) {
		this.columnsNames = columnsNames;
	}

	AttributeContainer searchAttributeContainer;
	/**
	 * @return the searchAttributeContainer
	 */
	public AttributeContainer getSearchAttributeContainer() {
		return searchAttributeContainer;
	}
	/**
	 * @param searchAttributeContainer the searchAttributeContainer to set
	 */
	public void setSearchAttributeContainer(
			AttributeContainer searchAttributeContainer) {
		this.searchAttributeContainer = searchAttributeContainer;
	}
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	 
	String searchType;
	 
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		Object obj =null;
		 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.ID)) { 
		 obj = getID() ;
		 return obj;
		 }  
		 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.COLUMNNAMES)) { 
		 obj = getColumnNames() ; 
		 return obj;
		 }
		 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.SEARCHTYPE)) { 
		 obj = getSearchType() ; 
		 return obj;}   
		 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.ATTRIBUTES)) { 
		 obj = getSearchAttributes() ; 
		 return obj;} 
		 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.BEANNAME)) { 
		 obj = getBeanName() ; 
		 }    
		 return obj; 

	}
	@Override


	 public void setPropertyValue(String propertyPaneColumnName, Object object) { 
	 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.ID)) { 
		 setID((Integer)object) ; 
	   }  
	 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.COLUMNNAMES)) { 
		 Selection<String> s = (Selection<String>) object;
		 
			setColumnNames(commonUTIL.collectionToString(s.getItems()) );
	   }  
	 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.SEARCHTYPE)) { 
	   setSearchType((String)object) ; }  
	 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.SEARCHATTRIBUTES)) { 
	   setSearchAttributes((String)object) ; }  
	 if(propertyPaneColumnName.equalsIgnoreCase(SearchConfigConstants.BEANNAME)) { 
	   setBeanName((String)object) ; }  
	 }
    
}
