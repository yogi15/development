package util.common.holiday;

import util.common.CDate;
 
/**
 * Provides a caching mechanism for business holidays with a 
 * bounded range of dates. Callees must guard against calls on this 
 * cache outside the bounded range of dates. The behaviour of an implementation
 * outside the bounded range is not defined.
 * 
 *  
 */
public interface BusinessHolidayCache {
	/**
	 * Lower bound on date range supported
	 * @return
	 */
	public CDate getFirstDateInCacheRange();

	/**
	 * Upper bound on date range supported
	 * @return
	 */
	public CDate getLastDateInCacheRange();

	/**
	 *  
	 * @param d
	 * @return
	 */
	public boolean isGoodBusinessDay(CDate d);

	/**
	 * 
	 * @param d
	 */
	public void setGoodBusinessDay(CDate d);

	/**
     * Computes the number of days which are business days.
     * This number includes the start date, but not the last.
     * Callee must ensure the first is strictly before the second date. When requirement
     * is not met behaviour is not defined.
     * 
     * @param x
     *            the starting date
     * @param y
     *            the end date of the interval. This one is not included in the
     *            count.
     * @return the number of business days
     * @see com.calypso.tk.core.Holiday numberOfBusinessDays method
     */
	public int calcBusinessDaysBetween(CDate x, CDate y);

	/**
	 * Adds all good business days to the business holiday cache.
	 * @param chc
	 */
	public void addBusinessDays(BusinessHolidayCache chc);

	/**
	 * Clear all business days from the cache
	 */
	public void clear();
	

}
