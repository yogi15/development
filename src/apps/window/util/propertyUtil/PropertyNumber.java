package apps.window.util.propertyUtil;

import javax.swing.CellEditor;
import javax.swing.table.TableCellRenderer;
 
import apps.window.util.propertyUtil.editor.NumberCellEditor;
import apps.window.util.propertyUtil.editor.PropertyValueProcessor;
import apps.window.util.propertyUtil.editor.ValueReferenceProvider;

import com.jidesoft.grid.TreeTableModel;

public class PropertyNumber extends BaseNumberProperty {

	protected PropertyNumber(Class<Double> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	private boolean isInPercent;
    private boolean isInBp;
    private ValueReferenceProvider referenceValueProvider;
    private PropertyValueProcessor<Double> propertyValueProcessor;

    public PropertyNumber(String name, String displayName, String category, int precision) {
        super(precision);
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
    }

    public PropertyNumber(String name, String displayName, String category, int precision, ValueReferenceProvider referenceValueProvider) {
        this(name, displayName, category, precision);
        this.referenceValueProvider = referenceValueProvider;
    }
    
    public PropertyNumber(String name, String description) {
        super(name, description, name);
    }

      @Override
    public String getDisplayName() {
        return super.getDisplayName() + (isInBp ? " (Bp)" : (isInPercent ? " (%)" : ""));
    }

    public void setPropertyValueProcessor(PropertyValueProcessor<Double> propertyValueProcessor) {
        this.propertyValueProcessor = propertyValueProcessor;
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
        notifyCellUpdated(null, 1);
    }

    public boolean isInPercent() {
        return isInPercent;
    }
    
    public boolean isInBp(){
    	return isInBp;
    }

    public void setInPercent(boolean inPercent) {
        if (isInPercent && referenceValueProvider != null)
            throw new IllegalStateException("A number property built with a reference value provider cannot be set to 'inPercent'");
        
        isInPercent = inPercent;
        TreeTableModel<?> tableModel = getTreeTableModel();
        if (tableModel != null)
            tableModel.fireTableDataChanged();
    }

    public void setPercentDecimals(int decimals) {
        super.setPrecision(decimals);
    }

    public void setInBp(boolean inBp) {
        isInBp = inBp;
        setInPercent(inBp);
    }

    @Override
    protected TableCellRenderer makeCellRenderer() {
        return new NumberCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isInPercent() {
                return PropertyNumber.this.isInPercent();
            }
        };
    }

    @Override
    protected CellEditor makeCellEditor() {
        NumberCellEditor editor;

        if (isInPercent) {
            editor = new NumberCellEditor(isInPercent);
            editor.setPercentDecimals(getPrecision());
        } else {
            editor = new NumberCellEditor(referenceValueProvider);
            editor.setPercentDecimals(getPrecision());
        }

        if (propertyValueProcessor != null)
            editor.setPropertyValueEditor(propertyValueProcessor);

        return editor;
    }
}

