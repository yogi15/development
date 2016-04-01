package apps.window.util.propertyUtil;

import java.awt.Component;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import util.commonUTIL;
import util.common.NumberFormatUtil;
 
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.ObjectConverter;

public class BaseNumberProperty extends PropertyNumeric<Double>   {

	protected BaseNumberProperty(Class<Double> type) {
		super(type);
		// TODO Auto-generated constructor stub
	} 
	private static final long serialVersionUID = -4173515682001970469L;
    // Precision is the max number of digits for display, not the systematic number of decimals displayed.
    // Default is 10 digits
    public static final int DEFAULT_PRECISION = 10;

    private int precision = 2;
    
    
    public BaseNumberProperty(final int precision) {
        super(Double.class);
        this.precision = precision;

        //registerEditor(precision);
        //registerConverter(precision);
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getPrecision() {
        return precision;
    }

    public BaseNumberProperty(final int precision, final String name, final String description, final String displayName, double defaultValue) {
        this(precision);
        setName(name);
        setDescription(description);
        setDisplayName(displayName);
        setValue(defaultValue);
    }

    public BaseNumberProperty(String name, String description) {
        this(name, description, name);
    }

    public BaseNumberProperty(String name, String description, String displayName) {
        this(DEFAULT_PRECISION, name, description, displayName, 0d);
    }

    @Override
    public TableCellRenderer getTableCellRenderer() {
        return makeCellRenderer();
    }

    protected TableCellRenderer makeCellRenderer() {
        return new DefaultTableCellRenderer() {
            /**
             * 
             */
            private static final long serialVersionUID = 1841826446857176605L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String s = value == null ? null : NumberFormatUtil.numberToString(((Number) value).doubleValue());
                JLabel rendererComponent = (JLabel) super.getTableCellRendererComponent(table, s, isSelected, hasFocus, row, column);
                rendererComponent.setHorizontalAlignment(SwingConstants.RIGHT);
                return rendererComponent;
            }
        };
    }

    @Override
    public CellEditor getCellEditor() {
        return makeCellEditor();
    }

/*
    protected void registerConverter(final int precision) {
        final ConverterContext  converterContext = new ConverterContext(getClass().getName() + "_" + UUID.randomUUID().toString());
        setConverterContext(converterContext);
        ObjectConverterManager.registerConverter(Double.class, makeConverter(precision), converterContext);
    }
*/

    /**
     * @param precision
     *            the decimal precision
     * @return an object converter
     * @deprecated please use the following code to convert numbers to strings:
     *             value == null ? null : Util.numberToString(((Number)
     *             value).doubleValue())
     */
    @Deprecated
    protected ObjectConverter makeConverter(final int precision) {
        return new ObjectConverter() {
            public String toString(Object object, ConverterContext context) {
                if (object == null)
                    return "";

                return NumberFormatUtil.numberToString(((Number) object).doubleValue());
            }

            public boolean supportToString(Object object, ConverterContext context) {
                return true;
            }

            public Object fromString(String string, ConverterContext context) {
                return null;
            }

            public boolean supportFromString(String string, ConverterContext context) {
                return false;
            }
        };
    }

/*
    protected void registerEditor(int precision) {
        final EditorContext editorContext = new EditorContext(getClass().getName() + "_" + UUID.randomUUID().toString());
        setEditorContext(editorContext);
        CellEditorManager.registerEditor(Double.class, new CellEditorFactory() {
            public CellEditor create() {
                return makeCellEditor();
            }

        }, editorContext);
    }
*/

    protected CellEditor makeCellEditor() {
        return new DefaultCellEditor(new JTextField()) {
            /**
             * 
             */
            private static final long serialVersionUID = -1037055157868557914L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return super.getTableCellEditorComponent(table, value == null ? null : NumberFormatUtil.numberToString(((Number) value).doubleValue()), isSelected, row, column);
            }

            @Override
            public Object getCellEditorValue() {
                String s = (String) super.getCellEditorValue();
                s = commonUTIL.checkAmount(s,0,false);

                return commonUTIL.stringToNumber(s);
            }
        };
    }
}