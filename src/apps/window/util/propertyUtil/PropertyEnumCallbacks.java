package apps.window.util.propertyUtil;

import java.util.Map;
 

public interface PropertyEnumCallbacks<T> {
	/**
     * called after the property is first initialized (from constructors)
     * @param enumProprety
     * @param values
     */
    public void initialized(PropertyEnum enumProprety, Map<T, String> values);

    /**
     * called after the proprety is reconfigured
     * @param enumProprety
     * @param values
     */
    public void reconfigured(PropertyEnum enumProprety, Map<T, String> values);
}
