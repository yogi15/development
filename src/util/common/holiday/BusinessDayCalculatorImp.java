package src.util.common.holiday;

import java.util.Collection;
import java.util.HashMap;
 
 import beans.HolidayCode;

import util.CosmosException;
import util.commonUTIL;
import util.common.CDate;

public class BusinessDayCalculatorImp  implements BusinessDaysCal {
	  private static final BusinessDaysCal INSTANCE = new BusinessDayCalculatorImp();

	    public static BusinessDaysCal getInstance() {
	        return INSTANCE;
	    }
	    
	    private BusinessDayCalculatorImp() {        
	    }
	    
	    /**
	     * Returns a boolean indicating whether the passed date is a holiday in one
	     * or more of the specified cities. Cities are specified by including their
	     * String city codes in the passed codeList array.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) whose holiday status you
	     *            wish to check
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
	        if (codeList == null
	                || codeList.size() == 0) {
	            // Backward compatibility only
	            // if no HolidayCode were specified
	            // then Saturday Sunday are non business day
	            if (date.isWeekEndDay())
	                return false;
	            else
	                return true;
	        }
	        int dayOfWeek = date.getDayOfWeek();
	        for (String code : codeList) {
	            HolidayCode hcode = holiday.getHolidayCode(code);
	            if (hcode == null) {
	               commonUTIL.displayError("BusinessDayCalImp", "HolidayCode does not exist (" + code + ")", new CosmosException());
	                continue;
	            }
	            if (hcode.getFirstNonBusinessDay() == dayOfWeek
	                    || hcode.getSecondNonBusinessDay() == dayOfWeek)
	                return false;
	            HashMap cityWorkings = (HashMap) holiday.getWorkingDates().get(code);
	            if ((cityWorkings != null)
	                    && (cityWorkings.get(date) != null))
	                continue;
	            boolean v = holiday.checkRules(date, code);
	            if (!v)
	                return false;
	            HashMap cityHolidays = (HashMap) holiday.getDates().get(code);
	            if ((cityHolidays != null)
	                    && (cityHolidays.get(date) != null))
	                return false;
	        }
	        return true;
	    }

	    /**
	     * Computes the number of days which are business days in all of the cities
	     * represented by their city codes in the codeList <code>Vector</code>.
	     * This number includes the start date, but not the last.
	     * This method bypasses any cache.
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
	    public final int numberOfBusinessDays(Holiday holiday, CDate startDate,
	                                          CDate endDate,
	                                          Collection<String> codeList) {
	        int days = 0;
	        int ndays = (int) CDate.diff(startDate, endDate);
	        CDate stDate = CDate.valueOf(startDate);
	        while (stDate.before(endDate)) {
	            if (isBusinessDay(holiday, stDate, codeList)) {
	                days++;
	            }
	            stDate = stDate.addDays(1);
	        }
	        return days;
	    }

	    public void clear() {
	    }

	    public void init() {
	    }
}