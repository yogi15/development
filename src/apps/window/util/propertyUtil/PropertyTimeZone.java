package src.apps.window.util.propertyUtil;

import java.util.TimeZone;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.table.TableCellRenderer;

import util.commonUTIL;
 
import com.jidesoft.converter.EnumConverter;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.EnumCellEditor;
import com.jidesoft.grid.EnumCellRenderer;

public class PropertyTimeZone extends DefaultProperty {
    private static final String[] tzs = TimeZone.getAvailableIDs();
    private EnumConverter timeZoneConverter;
    public static final String TIME_ZONE_NONE = "NONE";
    private boolean includeNoneChoice;

    public PropertyTimeZone(boolean includeNoneChoice) {
        this.includeNoneChoice = includeNoneChoice;
        prepareTimeZonePropertyPanelCell();

        setType(String.class);
        setName("timezone");
        setDisplayName("Time zone");
        setConverterContext(timeZoneConverter.getContext());
        final TimeZone defTimeZone = TimeZone.getDefault();
        setValue(includeNoneChoice ? TIME_ZONE_NONE : defTimeZone.getID());
    }


    protected void prepareTimeZonePropertyPanelCell() {
        Vector v=new Vector();
        for(int i=0;i< tzs.length;i++) v.addElement(tzs[i]);
        v= commonUTIL.sort(v);
        if (includeNoneChoice)
            v.insertElementAt(TIME_ZONE_NONE,0);

        timeZoneConverter = new EnumConverter("TimeZone", String.class,
                v.toArray(),
                (String[]) v.toArray(new String[0]),
                1);
    }

    @Override
    public CellEditor getCellEditor() {
        return new EnumCellEditor(timeZoneConverter);
    }

    @Override
    public TableCellRenderer getTableCellRenderer() {
        return new EnumCellRenderer(timeZoneConverter);
    }

    public TimeZone getValueAsTimeZone() {
        String tzid = (String) getValue();
        return tzid == null || tzid.equals(TIME_ZONE_NONE) ? TimeZone.getDefault() : TimeZone.getTimeZone(tzid);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

}
