package src.apps.window.util.property;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.HashMap;

import com.jidesoft.grid.Property;
import com.jidesoft.swing.JideSwingUtilities;

public class CellDesignProperty  extends Property {
	static HashMap<String, Object> map = new HashMap<String, Object>();
	static {
		 
		map.put("Background", new Color(255, 0, 0));
		map.put("Foreground", new Color(255, 255, 255));
		 
		map.put("Font", new Font("Tahoma", Font.BOLD, 11));
	}
	public CellDesignProperty(String name, String description, Class type,
			String category) {
		super(name, description, type, category);
	}
	public CellDesignProperty(String name, String description, Class type) {
		super(name, description, type);
	}


	@Override
	public void setValue(Object value) {
		Object old = getValue();
		if (!JideSwingUtilities.equals(old, value)) {
			map.put(getFullName(), value);
			firePropertyChange(PROPERTY_VALUE, old, value);
		}
	}

	@Override
	public Object getValue() {
		
		return map.get(getFullName());
	}

	@Override
	public boolean hasValue() {
		return map.get(getFullName()) != null;
	}
	}