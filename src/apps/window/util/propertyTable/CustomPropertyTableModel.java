package apps.window.util.propertyTable;

import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

import javax.swing.UIManager;

import util.commonUTIL;
 
import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTableModel;
import com.jidesoft.grid.StyleModel;

public class CustomPropertyTableModel extends PropertyTableModel implements StyleModel {

    private Hashtable _cellStylesHash = null;
    private CellStyle _defaultCellStyle = null;

    public CustomPropertyTableModel() {
        init();
    }

    public CustomPropertyTableModel(List properties) {
        super(properties);
        init();
    }

    public CustomPropertyTableModel(List properties, String[] categories) {
        super(properties, categories);
        init();
    }

    public CustomPropertyTableModel(List properties, int categoryOrder) {
        super(properties, categoryOrder);
        init();
    }

    private void init() {
        _cellStylesHash = new Hashtable();
        _defaultCellStyle = new CellStyle();
        _defaultCellStyle.setBackground(UIManager.getColor("TextField.background"));
    }

    /**
     * Gets a property event if it is embedded as a child property to another.
     * @param value - String name of the property to get
     * @return - Property - if found by name otherise null if not
     */
    public Property getProperty(String value) {
        Property dependingProperty = null;
        if (!commonUTIL.isEmpty(value)) {
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


