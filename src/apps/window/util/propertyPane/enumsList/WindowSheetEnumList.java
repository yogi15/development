package apps.window.util.propertyPane.enumsList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import apps.window.uti.propertypane.enums.SampleTestPropertyEnum;
import apps.window.uti.propertypane.enums.WindowSheetPropertyEnum;

import com.jidesoft.grid.Property;

public enum WindowSheetEnumList {
	 WINDOWSHEET(getwindowSheetListPropertyList());
	 
	 
	 WindowSheetEnumList(List< Property> propertyList) {

			this.windowSheetList = propertyList;

		}
	 
	 private List< Property> windowSheetList = new ArrayList< Property>();
		private List<Property> propertyList = new ArrayList<Property>();
		public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
		
		
		public void clearALLList() {
			propertyList.clear();
		}
		
		public List<Property> getPropertyList(String name) {
			 
			windowSheetList =  getwindowSheetListPropertyList();
			 
			return windowSheetList;
		}

		public void setPropertyList(List< Property> propertyList) {
			this.windowSheetList = propertyList;
		}

		
	private static List<Property> getwindowSheetListPropertyList() {

		List< Property> propertyList = new ArrayList< Property>();
		
		propertyList.add((Property) WindowSheetPropertyEnum.WINDOWNAME.getProperty());
		propertyList.add((Property) WindowSheetPropertyEnum.FIELDNAME.getProperty());
		propertyList.add((Property) WindowSheetPropertyEnum.DATATYPE.getProperty());

		propertyList.add((Property) WindowSheetPropertyEnum.CATEGORYNAME.getProperty());
		propertyList.add((Property) WindowSheetPropertyEnum.ISSTARTUPDATA.getProperty());
		propertyList.add((Property) WindowSheetPropertyEnum.STARTUPDATANAME.getProperty());

		propertyList.add((Property) WindowSheetPropertyEnum.DEFAULTVALUE.getProperty());
		propertyList.add((Property) WindowSheetPropertyEnum.CUSTOMPANELNAME.getProperty());

		propertyList.add((Property) WindowSheetPropertyEnum.NULLCHECKED.getProperty());
		return propertyList;
	}

}
