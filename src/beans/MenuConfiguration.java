package beans;

import constants.MenuConfigurationConstants;

public class MenuConfiguration implements BaseBean {

	String MainConfigurationWindow;

	/**
	 * @return the mainConfigurationWindow
	 */
	public String getMainConfigurationWindow() {
		return MainConfigurationWindow;
	}

	/**
	 * @param mainConfigurationWindow
	 *            the mainConfigurationWindow to set
	 */
	public void setMainConfigurationWindow(String mainConfigurationWindow) {
		this.MainConfigurationWindow = mainConfigurationWindow;
	}

	/**
	 * @return the mainMenuName
	 */
	public String getMainMenuName() {
		return MainMenuName;
	}

	/**
	 * @param mainMenuName
	 *            the mainMenuName to set
	 */
	public void setMainMenuName(String mainMenuName) {
		this.MainMenuName = mainMenuName;
	}

	/**
	 * @return the parentMenuName
	 */
	public String getParentMenuName() {
		return ParentMenuName;
	}

	/**
	 * @param parentMenuName
	 *            the parentMenuName to set
	 */
	public void setParentMenuName(String parentMenuName) {
		this.ParentMenuName = parentMenuName;
	}

	/**
	 * @return the childMenuName
	 */
	public String getChildMenuName() {
		return ChildMenuName;
	}

	/**
	 * @param childMenuName
	 *            the childMenuName to set
	 */
	public void setChildMenuName(String childMenuName) {
		this.ChildMenuName = childMenuName;
	}

	/**
	 * @return the menuName
	 */
	public String getWindowName() {
		return WindowName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setWindowName(String WindowName) {
		this.WindowName = WindowName;
	}

	String MainMenuName;

	String ParentMenuName;

	String ChildMenuName;

	String WindowName;

	/**
	 * @return the height
	 */
	public int getHeight() {
		return Height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		Height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return Width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.Width = width;
	}

	/**
	 * @param isParentChild
	 *            the isParentChild to set
	 */
	public void setParentChild(boolean isParentChild) {
		this.isParentChild = isParentChild;
	}

	int Height = 0;
	int Width = 0;
	boolean isParentChild = false;

	/**
	 * @return the isParent
	 */
	public boolean isParentChild() {
		return isParentChild;
	}

	/**
	 * @param isParent
	 *            the isParent to set
	 */
	public void setIsParentChild(boolean isParent) {
		this.isParentChild = isParent;
	}

	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj =null;
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.MAINMENU)) { 
		 obj = getMainMenuName() ;
		 }   if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.PARENTMENU)) { 
		 obj = getParentMenuName() ; 
		 }   if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.WIDTH)) { 
		 obj = getWidth() ;
		 }   if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.HEIGHT)) { 
		 obj = getHeight() ;
		 }   if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.CHILDMENU)) { 
		 obj = getChildMenuName() ; 
		 }    
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.ISPARENTCHILD)) { 
			 obj = isParentChild() ; 
			 } 
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.WINDOWNAME)) { 
			 obj = getWindowName() ; 
			 } 
		 return obj;} 


		 public void setPropertyValue(String propertyPaneColumnName, Object object) { 
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.MAINMENU)) { 
		   setMainMenuName((String)object) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.PARENTMENU)) { 
		   setParentMenuName((String)object) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.WIDTH)) { 
		   setWidth(((Integer)object).intValue()) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.HEIGHT)) { 
		   setHeight(((Integer)object).intValue()) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.CHILDMENU)) { 
		   setChildMenuName((String)object) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.WINDOWNAME)) { 
			   setWindowName((String)object) ; }  
		 if(propertyPaneColumnName.equalsIgnoreCase(MenuConfigurationConstants.ISPARENTCHILD)) { 
			 setIsParentChild(((Boolean) object).booleanValue()) ; }  
			 }
   int id = 0;
		public int getID() {
			// TODO Auto-generated method stub
			return id;
		}
		public void  setID(int i) {
			// TODO Auto-generated method stub
			id = i;
		}

}
