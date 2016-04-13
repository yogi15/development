package apps.window.util.propertyPane.enumsList;

import java.util.ArrayList;
import java.util.List;

import apps.window.util.property.CellDesignProperty;
import apps.window.util.propertyPane.enums.CellStylePropertyEnum;

public enum CellStylePropertyEnumList {
	
	DESIGN_PROPERTY(getCellDesignPropertyList());
	
	CellStylePropertyEnumList(List<CellDesignProperty> propertyList) {

		this.propertyList = propertyList;

	}

	
	private List<CellDesignProperty> propertyList = new ArrayList<CellDesignProperty>();
	
	public static List<CellDesignProperty> getCellDesignPropertyList() {

		List<CellDesignProperty> propertyList = new ArrayList<CellDesignProperty>();
		
		propertyList.add((CellDesignProperty) CellStylePropertyEnum.BACKGROUNDCOLOR.getProperty());
		propertyList.add((CellDesignProperty) CellStylePropertyEnum.FOREGROUNDCOLOR.getProperty());
		propertyList.add((CellDesignProperty) CellStylePropertyEnum.FONT.getProperty()); 
		return propertyList;
	}
	

}
