import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTableModel;
import com.jidesoft.grid.StyleModel;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import util.csvReader.*;
import util.*;
import javax.swing.UIManager;


public class PropertyTableModel12 extends PropertyTableModel implements StyleModel {

	
	
	
	 private Hashtable _cellStylesHash = null;
	    private CellStyle _defaultCellStyle = null;

	    public PropertyTableModel12() {
	        init();
	    }

	    public PropertyTableModel12(List properties) {
	        super(properties);
	        init();
	    }

	    public PropertyTableModel12(List properties, String[] categories) {
	        super(properties, categories);
	        init();
	    }
	
	    private void init() {
	        _cellStylesHash = new Hashtable();
	        _defaultCellStyle = new CellStyle();
	        _defaultCellStyle.setBackground(UIManager.getColor("TextField.background"));
	    }
	    public Property getProperty(String value) {
	        Property dependingProperty = null;
	        if (!JavaUtil.isEmpty(value)) {
	            dependingProperty = super.getProperty(value);
	            if (dependingProperty == null) {
	                List allProperties = getProperties(true); // get all properties and their children
	                ListIterator allPropertiesIterator = allProperties.listIterator();
	                while (allPropertiesIterator.hasNext() && dependingProperty == null) {
	                    Property currentProperty = (Property)allPropertiesIterator.next();
	                    if (currentProperty != null && value.equals(currentProperty.getName())) {
	                        dependingProperty = currentProperty;
	                    }
	                }
	            }
	        }
	        return dependingProperty;
	    }
	@Override
	public CellStyle getCellStyleAt(int rowIndex, int columnIndex) {
		CellStyle cellStyle = null;
        if (columnIndex == 1) {
            cellStyle = (CellStyle)_cellStylesHash.get(new Integer(rowIndex));
        }
        if (cellStyle == null) {
            cellStyle = _defaultCellStyle;
        }
        return cellStyle;
    }

    public void setCellStyleAt(int rowIndex, CellStyle style) {
        _cellStylesHash.put(new Integer(rowIndex), style);
    }

    public void removeCellStyleAt(int rowIndex) {
        _cellStylesHash.remove(new Integer(rowIndex));
    }
    public boolean isCellStyleOn() {
        return true;
    }
}
