package apps.window.util.propertyPane.enums;

import java.awt.Color;
import java.awt.Font;

import apps.window.util.property.CellDesignProperty;

import com.jidesoft.grid.Property;

public enum CellStylePropertyEnum {
	BACKGROUNDCOLOR(getBackGroundColor()),
	FOREGROUNDCOLOR( getForeGroundColor() ),
	FONT(getFont());
	
	private String propertyName;
	private String _description;
	public  Property commonproperty;
	
	/**
	 * @return the commonproperty
	 */
	public Property getProperty() {
		return commonproperty;
	}

	CellStylePropertyEnum(String propertyName,Property property,String category) {
		this.propertyName = propertyName;
		this.commonproperty = property;
		commonproperty.setName(propertyName);
		commonproperty.setCategory(category);
	}

	CellStylePropertyEnum(Property property) {
		this.propertyName = property.getName();
		this.commonproperty = property;
		commonproperty.setName(property.getName());
		commonproperty.setCategory(property.getCategory());
	}
	 public static Property getBackGroundColor() {
		 CellDesignProperty  property = new CellDesignProperty(
					"Background",
					"The row is intended to show how to create a cell to input color in RGB format.",
					Color.class, "Appearance");
		 property.setCategory("Appearance");
	    	return property;
	    }
	 public static Property getForeGroundColor() {
		 CellDesignProperty  property = new CellDesignProperty(
					"Foreground",
					"The row is intended to show how to create a cell to input color in RGB format.",
					Color.class, "Appearance");
		 property.setCategory("Appearance");
	    	return property;
	    }
	 public static Property getFont() {
		 CellDesignProperty property = new CellDesignProperty(
					"Font",
					"The row is intended to show how to create a cell to choose a font",
					Font.class);
			property.setCategory("Appearance");
			return property;
	    }
	 
	 
	 
		
		
		 
		
		
}
