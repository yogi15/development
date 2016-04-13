package beans;

public class SearchProperty implements BaseBean {
	
	String attributesName;
	/**
	 * @return the attributesName
	 */
	public String getAttributesName() {
		return attributesName;
	}

	/**
	 * @param attributesName the attributesName to set
	 */
	public void setAttributesName(String attributesName) {
		this.attributesName = attributesName;
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
		return null;
	}

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		
	}

}
