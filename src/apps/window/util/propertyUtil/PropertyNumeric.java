package apps.window.util.propertyUtil;
 
import com.jidesoft.grid.DefaultProperty;
public   abstract class PropertyNumeric<N extends Number> extends DefaultProperty {

    /**
     * 
     */
    private static final long serialVersionUID = 9047830097181783208L;

    private final Class<N> type;
    private boolean allowNull;

    /**
     * Create a new property
     * 
     * @param type
     *            the numeric type; must be a numeric class and cannot be null
     */
    protected PropertyNumeric(Class<N> type) {
        assert type != null;
        assert Number.class.isAssignableFrom(type);
        this.type = type;
        super.setType(type);
    }

    @Override
    public Class<N> getType() {
        return type;
    }

    @Override
    public void setType(Class< ? > type) {
        if (this.type.equals(type)) {
          /*  Log.warn(getClass().getSimpleName(),
                     "Attempting to change type of a numeric property. Expected "
                             + this.type + ", got " + type); */
        }
        super.setType(type);
    }

    /**
     * Returns whether or not null is an allowed value
     * 
     * @return a boolean value
     */
    public boolean isAllowNull() {
        return allowNull;
    }

    /**
     * Sets whether or not null is an allowed value
     * 
     * @param allowNull
     *            a boolean value
     */
    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    /**
     * Returns the current value, as an int. If null, returns 0.
     * 
     * @return an int value
     */
    public int intValue() {
        Object value = getValue();
        if (value == null) {
            return 0;
        }
        return ((Number) value).intValue();
    }

    /**
     * Returns the current value, as a double. If null, returns 0.
     * 
     * @return a double value
     */
    public double doubleValue() {
        Object value = getValue();
        if (value == null) {
            return 0.0;
        }
        return ((Number) value).doubleValue();
    }

    /**
     * Returns the current value as an Integer
     * 
     * @return an Integer, or null if allowed
     */
    public Integer getValueAsInt() {
        Object value = getValue();
        return value == null
                            ? allowNull
                                       ? null : 0 : ((Number) value).intValue();
    }

    /**
     * Returns the current value as a Double
     * 
     * @return a Double, or null if allowed
     */
    public Double getValueAsDouble() {
        Object value = getValue();
        return value == null
                            ? allowNull
                                       ? null : 0
                            : ((Number) value).doubleValue();
    }

}
