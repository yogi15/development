package util.common.holiday;

import java.util.Collection;

import util.common.CDate;
 

public interface BusinessDaysCal {
	 /**
     * clear any persistent data from the calculator
     */
    void clear();

    /**
     * Returns a boolean indicating whether the passed date is a holiday in one
     * or more of the specified cities. Cities are specified by including their
     * String city codes in the passed codeList array.
     * 
     * @param date
     *            the date (a <code>JDate</code>) whose holiday status you wish
     *            to check
     * @param codeList
     *            a Vector containing the city codes of all the cities whose
     *            calendars should be checked. See the city list domain in the
     *            database for a list of valid city codes.
     * 
     * @return a boolean indicating whether the passed date is a holiday in one
     *         or more of the specified cities
     */
    boolean isBusinessDay(Holiday holiday, CDate date, Collection<String> codeList);

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
    int numberOfBusinessDays(Holiday holiday, CDate startDate, CDate endDate, Collection<String> codeList);
    
    /**
     * initializes the calculator
     */
    void init();
}
