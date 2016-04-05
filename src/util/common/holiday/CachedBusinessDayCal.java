package src.util.common.holiday;

import java.util.Collection;

import util.common.CDate;
 

public abstract class CachedBusinessDayCal  implements BusinessDaysCal  {
    final static public String BUSINESS_DAY_CACHE_SIZE = "BUSINESS_DAY_CACHE_SIZE";

    public static final int HOLIDAY_CACHE_SIZE = getBusinessDayCacheSize();
    
    
    /**
     * 
     * @return business days cache size
     */
    public static int getBusinessDayCacheSize() { 
        return 300; //  getIntProperty(BUSINESS_DAY_CACHE_SIZE, 300); Default
    }

    /**
     * initializes the cache
     */
    public void init() {
        clear();
    }

    /**
     * clear the cache
     */
    public abstract void clear();
    
    /**
     * Returns a boolean indicating whether the passed date is a holiday in one
     * or more of the specified cities. Cities are specified by including their
     * String city codes in the passed codeList array.
     * 
     * @param date
     *            the date (a <code>CDate</code>) whose holiday status you wish
     *            to check
     * @param codeList
     *            a Vector containing the city codes of all the cities whose
     *            calendars should be checked. See the city list domain in the
     *            database for a list of valid city codes.
     * 
     * @return a boolean indicating whether the passed date is a holiday in one
     *         or more of the specified cities
     */
    public final boolean isBusinessDay(Holiday holiday, CDate date, Collection<String> codeList) {
        // if (date.isWeekEndDay()) return false;
        if (codeList == null || codeList.size() == 0) {
            // Backward compatibility only
            // if no HolidayCode were specified
            // then Saturday Sunday are non business day
            if (date.isWeekEndDay())
                return false;
            else
                return true;
        }
        boolean isGoodBusinessDay = true;
        BusinessHolidayCache chc = getBusinessHolidayCache(holiday, codeList);
        if (chc != null) {
            if (chc.getFirstDateInCacheRange().after(date) || chc.getLastDateInCacheRange().before(date)) {
                // the date is not cached.
                isGoodBusinessDay = BusinessDayCalculatorImp.getInstance().isBusinessDay(holiday, date, codeList);
            } else {
                isGoodBusinessDay = chc.isGoodBusinessDay(date);
            }

        }
        return isGoodBusinessDay;
    }

    /**
     * returns a BusinessHolidayBoundedCache for the corresponding holiday codes.
     * @param holiday the corresponding holiday
     * @param codeList a list of holiday codes as String
     * @return a BusinessHolidayBoundedCache instance for the corresponding codeList
     */ 
    protected abstract BusinessHolidayCache getBusinessHolidayCache(final Holiday holiday, final Collection<String> codeList);

    /**
     * Computes the number of days which are business days in all of the cities
     * represented by their city codes in the codeList <code>Vector</code>.
     * This number includes the start date, but not the last.
     * 
     * @param startDate
     *            the starting date
     * @param endDate
     *            the end date of the interval. This one is not included in the
     *            count.
     * @param codeList
     *            a <code>Vector</code> of <code>String</code>s
     *            representing the codes of the cities in which we want to check
     *            the number of business days.
     * @return the number of business days in the cities.
     */
    public int numberOfBusinessDays(Holiday holiday, CDate startDate, CDate endDate, Collection<String> codeList) {
    	BusinessHolidayCache chc = getBusinessHolidayCache(holiday, codeList);
        if (chc != null) {
            if (startDate.after(endDate)) {
                // switch start and end if not in order
                CDate tmp = endDate;
                endDate = startDate;
                startDate = tmp;
            }
            if (startDate.gte(chc.getFirstDateInCacheRange()) && endDate.lte(chc.getLastDateInCacheRange())) {
                return chc.calcBusinessDaysBetween(startDate, endDate);
            }
            // else do non cached computation.
        }
        return BusinessDayCalculatorImp.getInstance().numberOfBusinessDays(holiday, startDate, endDate, codeList);
    }
    
}
    


