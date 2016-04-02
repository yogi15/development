package apps.window.util.propertyUtil.editor;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

import util.commonUTIL;
import util.common.NumberFormatUtil;
 
public class NumberCellEditor extends DefaultCellEditor {
    private boolean isInPercent;
    private int percentDecimals = 2;// Default to 2 as that is the original implementation
    private ValueReferenceProvider referenceValueProvider;
    private PropertyValueProcessor<Double> propertyValueProcessor;

    /**
     * <p>creates a number table cell editor that enables users to enter numbers either as absolute values or as percent.
     * <p> if the parameter is true, then the editor will scale the value by 100
     * <p> if the parameter is false, the editor will output the exact value the user entered
     * @param isInPercent controls wether the editor is in percent mode or absolute value mode
     */
    public NumberCellEditor(boolean isInPercent) {
        super(new JTextField());
        this.isInPercent = isInPercent;
    }
    /**
     * <p>creates  number table cell editor that enables users to enter numbers either as absolute value or as percent
     *    of a reference value. So if the user enters "234", that will be the value returned by the editor. but if the user
     *    enters "36%", the value returned by the editor will be 36% of the reference value
     * @param referenceValueProvider
     */
    public NumberCellEditor(ValueReferenceProvider referenceValueProvider) {
        super(new JTextField());
        this.referenceValueProvider = referenceValueProvider;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Double scaledValue = value == null ? null : isInPercent ? scalePercentFordisplay(value) : ((Number) value).doubleValue();
        return super.getTableCellEditorComponent(table, scaledValue == null ? null :NumberFormatUtil.numberToString(scaledValue), isSelected, row, column);
    }

    public Object getCellEditorValue() {
        String s = (String) super.getCellEditorValue();

        if (referenceValueProvider != null && s.endsWith("%") && !isInPercent) {
            String percentStr = s.substring(0, s.length() - 1);
            double percent = commonUTIL.stringToNumber(percentStr )  / 100d;
            return percent * referenceValueProvider.getReferenceValue();
        }

        s = checkAmount(s);
        double v = commonUTIL.stringToNumber(s) / (isInPercent ? 100d : 1);
        if (propertyValueProcessor != null)
            v = propertyValueProcessor.process(v).doubleValue();
        return v;
    }
    
    protected String checkAmount(String s) {
        return commonUTIL.checkAmount(s, percentDecimals,true);
    }


    public void setPercentDecimals(int decimals) {
        percentDecimals = decimals;
    }
    
    private double scalePercentFordisplay(Object value) {
        double precisionFactor = Math.pow(10, percentDecimals);
        return (double)(Math.round((100*precisionFactor) * ((Double) value))) / (double)precisionFactor;
    }

    public void setPropertyValueEditor(PropertyValueProcessor<Double> propertyValueProcessor) {
        this.propertyValueProcessor = propertyValueProcessor;
    }
}
