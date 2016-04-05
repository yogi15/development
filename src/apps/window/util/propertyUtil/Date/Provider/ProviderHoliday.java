package src.apps.window.util.propertyUtil.Date.Provider;

import java.util.Vector;

public interface ProviderHoliday {
	public static Vector NO_HOLIDAYS = new Vector();

    public Vector getHolidays();
}