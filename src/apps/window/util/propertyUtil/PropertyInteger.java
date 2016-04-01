package apps.window.util.propertyUtil;

import javax.swing.CellEditor;
import javax.swing.SpinnerNumberModel;
 
import com.jidesoft.grid.SpinnerCellEditor;

public class PropertyInteger extends PropertyNumeric<Integer> {

    private class EditModel extends SpinnerNumberModel {

        /**
         * 
         */
        private static final long serialVersionUID = 6454693872966758350L;

        EditModel() {
        }

        EditModel(int value, int minimum, int maximum, int stepSize) {
            super(value, minimum, maximum, stepSize);
        }

        @Override
        public void setValue(Object value) {
            if (value == null) {
                value = 0;
            }
            super.setValue(value);
        }

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1736297096860967203L;

    private final SpinnerNumberModel spinnerModel;

    /**
     * Create a new property
     */
    public PropertyInteger() {
        super(Integer.class);
        spinnerModel = new EditModel();
    }

    /**
     * Create a new property
     * 
     * @param startValue
     *            the initial default value when editing
     * @param minimum
     *            the minimum value
     * @param maximum
     *            the maximum value
     * @param step
     *            the size for incrementing or decrementing the property
     */
    public PropertyInteger(int startValue, int minimum, int maximum, int step) {
        super(Integer.class);
        spinnerModel = new EditModel(startValue, minimum, maximum, step);
    }

    @Override
    public CellEditor getCellEditor() {
        return new SpinnerCellEditor(spinnerModel);
    }
}