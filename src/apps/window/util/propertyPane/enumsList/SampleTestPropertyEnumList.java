package apps.window.util.propertyPane.enumsList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
 
import apps.window.uti.propertypane.enums.SampleTestPropertyEnum;

import com.jidesoft.grid.Property;

public enum SampleTestPropertyEnumList {
	 
	 SAMPLETEST(getSampleTestPropertyPropertyList());
	 SampleTestPropertyEnumList(List< Property> propertyList) {

			this.sampleTestList = propertyList;

		}
	 
	 private List< Property> sampleTestList = new ArrayList< Property>();
		private List<Property> propertyList = new ArrayList<Property>();
		public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
		
		
		public void clearALLList() {
			propertyList.clear();
		}
		
		public List<Property> getPropertyList(String name) {
			 
			sampleTestList =  getSampleTestPropertyPropertyList();
			 
			return sampleTestList;
		}

		public void setPropertyList(List< Property> propertyList) {
			this.sampleTestList = propertyList;
		}

		
	private static List<Property> getSampleTestPropertyPropertyList() {

		List< Property> propertyList = new ArrayList< Property>();
		
		propertyList.add((Property) SampleTestPropertyEnum.NAME.getProperty());
		propertyList.add((Property) SampleTestPropertyEnum.NUMBERFIELD.getProperty());
		propertyList.add((Property) SampleTestPropertyEnum.BOOLEANFIELD.getProperty());
		propertyList.add((Property) SampleTestPropertyEnum.ENUMTEST.getProperty());
		propertyList.add((Property) SampleTestPropertyEnum.STARTUPDATA.getProperty());

		propertyList.add((Property) SampleTestPropertyEnum.ATTRIBUTEDATA.getProperty());
		return propertyList;
	}

}
