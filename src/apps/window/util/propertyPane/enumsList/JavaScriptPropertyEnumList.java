package apps.window.util.propertyPane.enumsList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import apps.window.uti.propertypane.enums.JavaScriptPropertyEnum; 

import com.jidesoft.grid.Property;

public enum JavaScriptPropertyEnumList {
	
	JAVASCRIPT(getJavaScriptPropertyList());
	 
	 
	JavaScriptPropertyEnumList(List< Property> propertyList) {

			this.windowSheetList = propertyList;

		}
	
	 private List< Property> windowSheetList = new ArrayList< Property>();
		private List<Property> propertyList = new ArrayList<Property>();
		public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
		
		
		public void clearALLList() {
			propertyList.clear();
		}
		
		public List<Property> getPropertyList(String name) {
			 
			windowSheetList =  getJavaScriptPropertyList();
			 
			return windowSheetList;
		}
		public void setPropertyList(List< Property> propertyList) {
			this.windowSheetList = propertyList;
		}

		
	private static List<Property> getJavaScriptPropertyList() {

		List< Property> propertyList = new ArrayList< Property>();
		
		propertyList.add((Property) JavaScriptPropertyEnum.WINDOWNAME.getProperty());
		propertyList.add((Property) JavaScriptPropertyEnum.SCRIPTNAME.getProperty());
		return propertyList;
	}

}
