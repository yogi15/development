package util.common.holiday;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector; 
 
 

import util.commonUTIL;
import util.common.CDate;

import beans.HolidayCode;
import beans.HolidayRule;
 
 
public class Holiday {
	 protected HashMap _dates;
	    protected HashMap _workingDates;
	    protected HashMap _datesDescription;
	    protected HashMap _rules;
	    protected HashMap _codes;
	    protected int __version = 0;
	    protected String __user;
	    protected Vector _cachedCodeList; 
	    BusinessDaysCal _buCalculator;
	    static public Holiday getCurrent() {
	        return null;// values coming from DB. LocalCache.getCurrentHoliday();
	    }  
	    public Holiday() {
	        _dates = new HashMap();
	        _workingDates = new HashMap();
	        _datesDescription = new HashMap();
	        _rules = new HashMap();
	        _codes = new HashMap();
	        _cachedCodeList = null; // will be rebuilt if required
	        _buCalculator = BusinessDayComputeFactory.getInstance();
	        _buCalculator.init();
	    }   


	    /**
	     * Clears the hashtables of holiday dates and holiday rules from this
	     * Holiday object.
	     */
	    public final void clear() {
	        _dates.clear();
	        _workingDates.clear();
	        _datesDescription.clear();
	        _rules.clear();
	        _buCalculator.clear();
	    }
	    
	    /**
	     * Returns a HashMap containing all the holiday dates in the Holiday object.
	     */
	    public final HashMap getDates() {
	        return _dates;
	    }
	    /**
	     * Returns a HashMap containing all the working dates in the Holiday object.
	     */
	    public final HashMap getWorkingDates() {
	        return _workingDates;
	    }

	    /**
	     * Returns a HashMap containing all the holiday dates description in the
	     * Holiday object.
	     */
	    public final HashMap getDatesDescription() {
	        return _datesDescription;
	    }

	    /**
	     * Returns a HashMap containing all the holiday rules in the Holiday object.
	     */
	    public final HashMap getRules() {
	        return _rules;
	    }

	    /**
	     * Assigns the passed set of holiday dates as this object's list of holiday
	     * dates.
	     * 
	     * @param h
	     *            a HashMap containing a date or set of dates of holidays
	     */
	    public final void setDates(HashMap h) {
	        _dates = h;
	    }

	    /**
	     * Assigns the passed set of working dates as this object's list of working
	     * dates.
	     * 
	     * @param h
	     *            a HashMap containing a date or set of dates of holidays
	     */
	    public final void setWorkingDates(HashMap h) {
	        _workingDates = h;
	    }

	    /**
	     * Assigns the passed set of holiday dates description as this object's list
	     * of holiday dates.
	     * 
	     * @param h
	     *            a HashMap containing a date or set of dates of holidays
	     */
	    public final void setDatesDescription(HashMap h) {
	        _datesDescription = h;
	    }

	    /**
	     * Assigns the passed set of Holiday rules as this object's list of holiday
	     * rules.
	     * 
	     * @param h
	     *            a HashMap containing one or more Holiday rules
	     */
	    public final void setRules(HashMap h) {
	        _rules = h;
	    }

	    /**
	     * Returns a Vector containing the city codes of all the cities for which
	     * this Holiday object contains holiday dates.
	     */
	    public final Vector getHolidayCodeList() {

	        if (_cachedCodeList==null) {
	            _cachedCodeList = initHolidayCodeList();
	        }
	        return _cachedCodeList;
	    }

	    public final Vector initHolidayCodeList() {
	        _cachedCodeList = new Vector();
	        Set keys = _codes.keySet();
	        Iterator it = keys.iterator();
	        while (it.hasNext())
	            _cachedCodeList.addElement(it.next());
	        return util.SortShell.sort(_cachedCodeList);
	    }
	    /**
	     * Returns a Vector containing the city codes of all the cities for which
	     * this Holiday object contains holiday dates.
	     */
	    public final Map getHolidayRulesMap() {
	        HashMap map = new HashMap();
	        Iterator codeNames = _codes.keySet().iterator();
	        while (codeNames.hasNext()) {
	            ArrayList rules = getHolidayCodeRules((String) codeNames.next());
	            if (rules != null) {
	                for (int i = 0; i < rules.size(); i++) {
	                    HolidayRule rule = (beans.HolidayRule) rules.get(i);
	                    map.put(rule.getName(), rule);
	                }
	            }
	        }
	        return map;
	    }

	    /**
	     * Returns a Vector containing Holiday rules that apply to the specified
	     * city.
	     * 
	     * @param codeName
	     *            the String city code of the city for which you wish to
	     *            retrieve rules
	     */
	    public final ArrayList getHolidayCodeRules(String codeName) {
	        return (ArrayList) _rules.get(codeName);
	    }

	    /**
	     * Returns a Vector containing String names of Holiday rules that apply to
	     * the specified city.
	     * 
	     * @param codeName
	     *            the String city code of the city for which you wish to
	     *            retrieve rule names
	     */
	    public final Vector getHolidayCodeRuleNames(String codeName) {
	        ArrayList v = (ArrayList) _rules.get(codeName);
	        Vector rn = new Vector();
	        if (v != null) {
	            for (int i = 0; i < v.size(); i++) {
	                rn.addElement(((HolidayRule) v.get(i)).getName());
	            }
	        }
	        return rn;
	    }
	    
	    /**
	     * Returns a boolean indicating whether the passed date is a business day 
	     * in any of the specified cities. Cities are specified by including their
	     * String city codes in the passed codeList array.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) whose business day status you
	     *            wish to check
	     * @param codeList
	     *            a Vector containing the city codes of all the cities whose
	     *            calendars should be checked. See the city list domain in the
	     *            database for a list of valid city codes.
	     * 
	     * @return a boolean indicating whether the passed date is a business day in any
	     *          of the specified cities
	     */
	    public final boolean isBusinessDayInAnyCode(CDate date, Vector codeList) {
	        if (commonUTIL.isEmpty(codeList)) {
	            return isBusinessDay(date, codeList);
	        }
	        for (int i = 0; i < codeList.size(); i++) {
	            Vector oneCode = new Vector();
	            oneCode.add(codeList.elementAt(i));
	            //whether the passed date is a holiday in
	            //the specified city
	            boolean isBusDay = isBusinessDay(date, oneCode);
	            if (isBusDay) return true;
	        }
	        return false;
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
	    public final boolean isBusinessDay(CDate date, Vector codeList) {
	        return _buCalculator.isBusinessDay(this, date, codeList);
	    }
	       
	    HashMap getCityHolidays(Vector codeList) {
	        HashMap cityHolidays = new HashMap();
	        if (commonUTIL.isEmpty(codeList))
	            return cityHolidays;
	        for (int i = 0; i < codeList.size(); i++) {
	            HashMap tmp = (HashMap) _dates.get(codeList.get(i));
	            if (tmp != null)
	                cityHolidays.putAll(tmp);
	        }
	        return cityHolidays;
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
	     * @param numberHoliday
	     *            the number of holiday to validate a business day
	     * @return a boolean indicating whether the passed date is a holiday in one
	     *         or more of the specified cities
	     */
	    public final boolean isBusinessDay(CDate date,
	                                       Vector codeList,
	                                       int numberHoliday) {
	        // if (date.isWeekEndDay()) return false;
	        if (commonUTIL.isEmpty(codeList)) {
	            // Backward compatibility only
	            // if no HolidayCode were specified
	            // then Saturday Sunday are non business day
	            if (date.isWeekEndDay())
	                return false;
	            else
	                return true;
	        }
	        int dayOfWeek = date.getDayOfWeek();
	        int nbHoliday = 0;
	        if (numberHoliday > codeList.size())
	            numberHoliday = codeList.size();
	        for (int i = 0; i < codeList.size(); i++) {
	            boolean isbusinessday = true;
	            String code = (String) codeList.elementAt(i);
	            HolidayCode hcode = getHolidayCode(code);
	            if (hcode == null) {
	              //  Log.error(this, "HolidayCode does not exist ("
	              //            + code + ")", new Throwable());
	                continue;
	            }
	            if (hcode.getFirstNonBusinessDay() == dayOfWeek
	                    || hcode.getSecondNonBusinessDay() == dayOfWeek)
	                isbusinessday = false;
	            else {
	                HashMap cityWorkings = (HashMap) _workingDates.get(code);
	                if (commonUTIL.isEmpty(cityWorkings)
	                        || cityWorkings.get(date) == null) {
	                    boolean v = checkRules(date, code);
	                    if (!v)
	                        isbusinessday = false;
	                    HashMap cityHolidays = (HashMap) _dates.get(code);
	                    if ((cityHolidays != null)
	                            && (cityHolidays.get(date) != null))
	                        isbusinessday = false;
	                }
	            }
	            if (isbusinessday)
	                nbHoliday++;
	        }
	        return nbHoliday >= numberHoliday;
	    }

	    /**
	     * Checks whether the specified date is a business day or not according to
	     * the rules applied in the specified city.
	     * 
	     * @param date
	     *            the <code>CDate</code> for which we want to know if it is a
	     *            business day or not.
	     * @param code
	     *            a <code>String</code> representing the city code in which we
	     *            want to know if the day is a business day or not.
	     * @return true if it is a business day, false otherwise. By default, if no
	     *         rules are specified for this city, this method returns true.
	     */
	    public final boolean checkRules(CDate date, String code) {
	        ArrayList rules = getHolidayCodeRules(code);
	        if (rules == null
	                || rules.size() == 0)
	            return true;
	        int size = rules.size();
	        for (int i = 0; i < size; i++) {
	            HolidayRule rule = (HolidayRule) rules.get(i);
	            if (rule == null) {
	               // Log.error(this, "Null rule for HolidayCode " + code);
	                continue;
	            }
	            if (!rule.isBusinessDay(date))
	                return false;
	        }
	        return true;
	    }

	    public boolean[] weekendDays(Vector codeList) {
	        boolean weekendDays[] = {
	                                 false, true, false, false, false, false,
	                                 false, true};
	        if (commonUTIL.isEmpty(codeList))
	            return weekendDays;
	        weekendDays[1] = false;
	        weekendDays[7] = false;
	        for (int i = 0; i < codeList.size(); i++) {
	            String code = (String) codeList.elementAt(i);
	            HolidayCode hcode = getHolidayCode(code);
	            if (hcode == null) {
	                continue;
	            }
	            weekendDays[hcode.getFirstNonBusinessDay()] = true;
	            weekendDays[hcode.getSecondNonBusinessDay()] = true;
	        }
	        return weekendDays;
	    }

	    /**
	     * Given a date and a list of one or more cities, finds the next day that is
	     * a business day in all of the passed cities.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) for which you wish to find
	     *            the next subsequent business day
	     * @param codeList
	     *            the Vector of cities (each a String city code) whose holiday
	     *            calendars you wish to observe in searching for the next
	     *            business day
	     * 
	     * @return the date (a <code>CDate</code>) of the next business day
	     */
	    public final CDate nextBusinessDay(CDate date, Vector codeList) {
	        CDate dt = date.addDays(1);
	        while (!isBusinessDay(dt, codeList))
	            dt = dt.addDays(1);
	        return dt;
	    }

	    /**
	     * Given a date and a list of one or more cities, finds the next day that is
	     * a business day in all of the passed cities.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) for which you wish to find
	     *            the next subsequent business day
	     * @param codeList
	     *            the Vector of cities (each a String city code) whose holiday
	     *            calendars you wish to observe in searching for the next
	     *            business day
	     * @param numberHolidays
	     *            the number of holiday to validate a business day
	     * 
	     * @return the date (a <code>CDate</code>) of the next business day
	     */
	    public final CDate nextBusinessDay(CDate date,
	                                       Vector codeList,
	                                       int numberHolidays) {
	        CDate dt = date.addDays(1);
	        while (!isBusinessDay(dt, codeList, numberHolidays))
	            dt = dt.addDays(1);
	        return dt;
	    }

	    /**
	     * Given a date and a list of one or more cities, finds the most recent
	     * previous day that is a business day in all of the passed cities.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) for which you wish to find
	     *            the most recent previous business day
	     * @param codeList
	     *            the Vector of cities (each a String city code) whose holiday
	     *            calendars you wish to observe in searching for the last
	     *            previous business day
	     * 
	     * @return the date (a <code>CDate</code>) of the last previous business
	     *         day
	     */
	    public final CDate previousBusinessDay(CDate date, Vector codeList) {
	        CDate dt = date.addDays(-1);
	        while (!isBusinessDay(dt, codeList))
	            dt = dt.addDays(-1);
	        return dt;
	    }

	    /**
	     * Given a date and a list of one or more cities, finds the most recent
	     * previous day that is a business day in all of the passed cities.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) for which you wish to find
	     *            the most recent previous business day
	     * @param codeList
	     *            the Vector of cities (each a String city code) whose holiday
	     *            calendars you wish to observe in searching for the last
	     *            previous business day
	     * @param numberHoliday
	     *            the number of holiday to validate a business day
	     * @return the date (a <code>CDate</code>) of the last previous business
	     *         day
	     */

	    public final CDate previousBusinessDay(CDate date,
	                                           Vector codeList,
	                                           int numberHolidays) {
	        CDate dt = date.addDays(-1);
	        while (!isBusinessDay(dt, codeList, numberHolidays))
	            dt = dt.addDays(-1);
	        return dt;
	    }

	    /**
	     * Given a list of one or more cities, returns the date that is the
	     * specified number of (<code>days</code>) after the passed
	     * <code>date</code>.
	     * 
	     * @param date
	     *            the date (a <code>CDate</code>) to which you wish to add
	     *            business days
	     * @param codeList
	     *            the Vector of cities (each a String city code) whose holiday
	     *            calendars you wish to observe while adding days
	     * @param days
	     *            the number of business days you wish to add. A business day is
	     *            a day which is a market day (not a holiday) in all of the
	     *            passed cities.
	     * @param following
	     *            if number of days is equal to 0 and following is true then
	     *            search next following business day if not then search next
	     *            preceeding business day
	     * 
	     * @return the date (a <code>CDate</code>) that is the specified number
	     *         of (<code>days</code>) after the passed <code>date</code>
	     */

	    /*
	     * public final CDate addBusinessDays(CDate date, Vector codeList, int
	     * days,boolean following ) { CDate dt = CDate.valueOf(date); if (days == 0) {
	     * while(!isBusinessDay(dt,codeList)){ if (following) dt = dt.addDays(1);
	     * else dt = dt.addDays(-1); } return dt; } if (days > 0) { for (int i = 0;
	     * i < days; i++) dt = nextBusinessDay(dt, codeList); return dt; } if (days <
	     * 0) { for (int i = 0; i < -days; i++) dt = previousBusinessDay(dt,
	     * codeList); } return dt; }
	     */

	    /**
	     * Does the same as addBusinessDays(date, codeList, days, following ). With
	     * following set to true.
	     */
	    public final CDate addBusinessDays(CDate date, Vector codeList, int days) {
	        return addBusinessDays(date, codeList, days, true);
	    }

	    /**
	     * Does the same as addBusinessDays(date, codeList, days, following,
	     * numberHolidays ). With following set to true.
	     */
	    public final CDate addBusinessDays(CDate date,
	                                       Vector codeList,
	                                       int days,
	                                       int numberHolidays) {
	        return addBusinessDays(date, codeList, days, true, numberHolidays);
	    }

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
	    public final int numberOfBusinessDays(CDate startDate,
	                                          CDate endDate,
	                                          Vector codeList) {
	        return _buCalculator.numberOfBusinessDays(this, startDate, endDate, codeList);
	    }
	    

	    /**
	     * Computes the number of days which are business days in all of the cities
	     * represented by their city codes in the codeList <code>Vector</code>.
	     * This number include the start date, but not the last.
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
	     * @param numberHoliday
	     *            the number of holiday to validate a business day
	     * @return the number of business days in the cities.
	     */
	    public final int numberOfBusinessDays(CDate startDate,
	                                          CDate endDate,
	                                          Vector codeList,
	                                          int numberHolidays) {
	        int days = 0;
	        CDate.diff(startDate, endDate);
	        CDate stDate = CDate.valueOf(startDate);
	        while (stDate.before(endDate)) {
	            if (isBusinessDay(stDate, codeList, numberHolidays))
	                days++;
	            stDate = stDate.addDays(1);
	        }
	        return days;
	    }

	    /**
	     * Returns a clone for this object.
	     * 
	     * @return a clone for this object.
	     */
	    public Object clone() throws CloneNotSupportedException {
	        Holiday copy = (Holiday) super.clone();
	        copy._codes = new HashMap(_codes);
	        copy._dates = new HashMap(_dates);
	        copy._workingDates = new HashMap(_workingDates);
	        copy._datesDescription = new HashMap(_datesDescription);
	        copy._rules = new HashMap(_rules);
	        copy._cachedCodeList = null;  // will be rebuilt if required
	        copy._buCalculator.init();
	        return copy;
	    }

	    /**
	     * Add a number of actual days to a date and roll to the next business day
	     * if it isn't already one. You can also add negative days, in which case it
	     * will roll to the previous business day if the computed day is not already
	     * a business day. The fact that a day is considered a business day or not
	     * is checked in accordance with the rules that are applied in the cities
	     * which citycodes are given in the <code>Vector</code> codeList.
	     * 
	     * @param date
	     *            the original date to start from.
	     * @param codeList
	     *            the list of citycodes in which to look for holiday rules
	     * @param days
	     *            the number of days to be added to the original date.
	     * @return a <code>CDate</code> object corresponding to the original date +
	     *         the specified number of days + (if not a business day) a date
	     *         roll so that the value returned is a business day.
	     */
	    public final CDate addCalendarDays(CDate date, Vector codeList, int days) {
	        CDate dt = CDate.valueOf(date);

	        if (days >= 0) {
	            dt = dt.addDays(days);
	            while (!isBusinessDay(dt, codeList))
	                dt = dt.addDays(1);
	        }
	        if (days < 0) {
	            dt = dt.addDays(days);
	            while (!isBusinessDay(dt, codeList))
	                dt = dt.addDays(-1);
	        }

	        return dt;
	    }

	    /**
	     * Add a number of actual days to a date and roll to the next business day
	     * if it isn't already one. You can also add negative days, in which case it
	     * will roll to the previous business day if the computed day is not already
	     * a business day. The fact that a day is considered a business day or not
	     * is checked in accordance with the rules that are applied in the cities
	     * which citycodes are given in the <code>Vector</code> codeList.
	     * 
	     * @param date
	     *            the original date to start from.
	     * @param codeList
	     *            the list of citycodes in which to look for holiday rules
	     * @param days
	     *            the number of days to be added to the original date.
	     * @param numberHoliday
	     *            the number of holiday to validate a business day
	     * @return a <code>CDate</code> object corresponding to the original date +
	     *         the specified number of days + (if not a business day) a date
	     *         roll so that the value returned is a business day.3
	     */

	    public final CDate addCalendarDays(CDate date,
	                                       Vector codeList,
	                                       int days,
	                                       int numberHoliday) {
	        CDate dt = CDate.valueOf(date);
	        if (days >= 0) {
	            dt = dt.addDays(days);
	            while (!isBusinessDay(dt, codeList, numberHoliday))
	                dt = dt.addDays(1);
	        }
	        if (days < 0) {
	            dt = dt.addDays(days);
	            while (!isBusinessDay(dt, codeList, numberHoliday))
	                dt = dt.addDays(-1);
	        }
	        return dt;
	    }

	    /**
	     * Generates a series of dates that are holidays in the specified city.
	     * Observes any rules that are set in a <code>HolidayRules</code> object.
	     * 
	     * @param code
	     *            a <code>String</code> that is the city code.
	     * @param startYear
	     *            an <code>int</code> that specifies the first year to
	     *            generate the holidays for.
	     * @param endYear
	     *            an <code>int</code> that specifies the last year to generate
	     *            the holidays for.
	     */

	    public final Hashtable generate(String code, int startYear, int endYear) {
	        Hashtable h = new Hashtable();
	        generate(code, startYear, endYear, h);
	        return h;
	    }

	    public final Hashtable generate(Vector codeList, int startYear, int endYear) {
	        Hashtable h = new Hashtable();
	        for (int k = 0; k < codeList.size(); k++) {
	            String code = (String) codeList.elementAt(k);
	            generate(code, startYear, endYear, h);
	        }
	        return h;
	    }

	    public final void generate(String code,
	                               int startYear,
	                               int endYear,
	                               Hashtable h) {
	        HashMap cityHolidays = (HashMap) _dates.get(code);
	        if (cityHolidays != null
	                && cityHolidays.size() > 0) {
	            Set keySet = cityHolidays.keySet();
	            Iterator it = keySet.iterator();
	            while (it.hasNext()) {
	                CDate d = (CDate) it.next();
	                if (d.getYear() >= startYear
	                        && d.getYear() <= endYear)
	                    h.put(d, d);
	            }
	        }
	        ArrayList rules = getHolidayCodeRules(code);
	        if (rules != null
	                && rules.size() > 0) {
	            for (int i = 0; i < rules.size(); i++) {
	                HolidayRule rule = (HolidayRule) rules.get(i);
	                Vector v = rule.generate(startYear, endYear);
	                if (v != null)
	                    for (int j = 0; j < v.size(); j++) {
	                        Object d = v.elementAt(j);
	                        h.put(d, d);
	                    }
	            }
	        }
	    }

	    /**
	     * Writes the specified <code>Holiday</code> object to the ObjectOutput
	     * 
	     * @param out
	     *            the <code>ObjectOutput</code> to write to.
	     * @throws IOException
	     *             Signals that an exceptional condition has occurred during
	     *             input or output.
	     */
	    public void writeExternal(ObjectOutput out) throws IOException {
	    //    out.writeInt(CalypsoVersion.getCurrentVersion());
	        Set keys = new HashSet();
	        keys.addAll(_dates.keySet());
	        keys.addAll(_workingDates.keySet());
	        int size = keys.size();
	        out.writeInt(size);
	        Iterator it = keys.iterator();
	        while (it.hasNext()) {
	            String code = (String) it.next();
	            HashMap cityHolidays = (HashMap) _dates.get(code);
	            HashMap descriptions = (HashMap) _datesDescription.get(code);
	            HashMap cityWorkings = (HashMap) _workingDates.get(code);
	            out.writeUTF(code);
	            int hsize = (cityHolidays != null) ? cityHolidays.size() : 0;
	            out.writeInt(hsize);
	            if (cityHolidays != null) {
	                Set ss = cityHolidays.keySet();
	                Iterator ee = ss.iterator();
	                while (ee.hasNext()) {
	                    CDate currentDate = (CDate) ee.next();
	                    out.writeLong(currentDate.getJulian());
	                    String comment = null;
	                    if (descriptions != null)
	                        comment = (String) descriptions.get(currentDate);
	                    commonUTIL.writeUTF(comment, out);
	                }
	            }
	            hsize = (cityWorkings != null) ? cityWorkings.size() : 0;
	            out.writeInt(hsize);
	            if (cityWorkings != null) {
	                Set ss = cityWorkings.keySet();
	                Iterator ee = ss.iterator();
	                while (ee.hasNext()) {
	                    CDate currentDate = (CDate) ee.next();
	                    out.writeLong(currentDate.getJulian());
	                    String comment = null;
	                    if (descriptions != null)
	                        comment = (String) descriptions.get(currentDate);
	                    commonUTIL.writeUTF(comment, out);
	                }
	            }
	        }
	        size = _rules.size();
	        out.writeInt(size);
	        keys = _rules.keySet();
	        it = keys.iterator();
	        while (it.hasNext()) {
	            String code = (String) it.next();
	            ArrayList rules = (ArrayList) _rules.get(code);
	            out.writeUTF(code);
	            int vsize = (rules != null)
	                                       ? rules.size() : 0;
	            out.writeInt(vsize);
	            if (vsize > 0) {
	                for (int i = 0; i < rules.size(); i++) {
	                    HolidayRule rule = (HolidayRule) rules.get(i);
	                    rule.writeExternal(out);
	                }
	            }
	        }
	        out.writeObject(_codes);
	        out.writeInt(__version);
	        commonUTIL.writeUTF(__user, out);
	    }

	    /**
	     * Read the specified <code>Holiday</code> from the
	     * <code>ObjectInput</code>
	     * 
	     * @param in
	     *            the <code>ObjectInput</code> from which to read.
	     * @throws IOException
	     *             Signals that an exceptional condition has occurred during
	     *             input or output.
	     * @throws ClassNotFoundException
	     *             Thrown when no definition for a class is found.
	     */

	    public void readExternal(ObjectInput in) throws IOException,
	            ClassNotFoundException {
	      //  __auditVersion = in.readInt();
	      
	    }

	    /**
	     * Returns a HashMap containing all the city codes in the Holiday object.
	     */
	    public final HashMap getCodes() {
	        return _codes;
	    }

	    /**
	     * Assigns the passed set of city codes as this object's list of city codes.
	     * 
	     * @param codes
	     *            a HashMap containing one or more Holiday codes
	     */

	    public final void setCodes(HashMap codes) {
	        _codes = codes;
	    }

	    /**
	     * Return a Holiday code for the specified city.
	     * 
	     * @param code
	     *            a string city code
	     */
	    final public HolidayCode getHolidayCode(String code) {
	        return (HolidayCode) _codes.get(code);
	    }

	    // Optimized version

	    /**
	     * Used to determine a business day that is a specified number of days from
	     * a date.
	     * 
	     * @param date
	     *            a <code>CDate</code> - The date from which to calculate.
	     * @param codeList
	     *            a <code>Vector</code> of city codes whose holidays should be
	     *            included in the calculation.
	     * @param days
	     *            an <code>int</code> the number of days to add to date.
	     * @param following
	     *            if number of days is equal to 0 and following is true then
	     *            search next following business day if not then search next
	     *            preceeding business day
	     * 
	     * @return a <code>CDate</code> the newly calculated date.
	     */

	    public final CDate addBusinessDays(CDate date,
	                                       Vector codeList,
	                                       int days,
	                                       boolean following) {        
	        CDate dt = CDate.valueOf(date);
	        if (days == 0) {
	            while (!isBusinessDay(dt, codeList)) {
	                if (following)
	                    dt = dt.addDays(1);
	                else
	                    dt = dt.addDays(-1);
	            }            
	            return dt;
	        }

	        boolean[] businessDays = getBusinessDays(codeList);

	        if (days > 0) {
	            if (days > 5)
	               // Log.info(this, "addBusinessDays called with days = "
	               //         + days);
	            for (int i = 0; i < days; i++) {
	                dt = dt.addOneDayFixed();
	                while (!isBusinessDay(dt, codeList, businessDays))
	                    dt = dt.addOneDayFixed();
	            }            
	            return dt;
	        }

	        if (days < 0) {
	            for (int i = 0; i < -days; i++) {
	                dt = dt.subOneDayFixed();
	                while (!isBusinessDay(dt, codeList, businessDays))
	                    dt = dt.subOneDayFixed();
	            }
	        }        
	        return dt;
	    }

	    /**
	     * Used to determine a business day that is a specified number of days from
	     * a date.
	     * 
	     * @param date
	     *            a <code>CDate</code> - The date from which to calculate.
	     * @param codeList
	     *            a <code>Vector</code> of city codes whose holidays should be
	     *            included in the calculation.
	     * @param days
	     *            an <code>int</code> the number of days to add to date.
	     * @param following
	     *            if number of days is equal to 0 and following is true then
	     *            search next following business day if not then search next
	     *            preceeding business day
	     * @param numberHoliday
	     *            the number of holiday to validate a business day
	     * 
	     * @return a <code>CDate</code> the newly calculated date.
	     */

	    public final CDate addBusinessDays(CDate date,
	                                       Vector codeList,
	                                       int days,
	                                       boolean following,
	                                       int numberHolidays) {        
	        CDate dt = CDate.valueOf(date);
	        if (days == 0) {
	            while (!isBusinessDay(dt, codeList, numberHolidays)) {
	                if (following)
	                    dt = dt.addDays(1);
	                else
	                    dt = dt.addDays(-1);
	            }            
	            return dt;
	        }

	        boolean[] businessDays = getBusinessDays(codeList);

	        if (days > 0) {
	            if (days > 5)
	              //  Log.info(this, "addBusinessDays called with days = "
	                //        + days);
	            for (int i = 0; i < days; i++) {
	                dt = dt.addOneDayFixed();
	                while (!isBusinessDay(dt,
	                                      codeList,
	                                      businessDays,
	                                      numberHolidays))
	                    dt = dt.addOneDayFixed();
	            }            
	            return dt;
	        }

	        if (days < 0) {
	            for (int i = 0; i < -days; i++) {
	                dt = dt.subOneDayFixed();
	                while (!isBusinessDay(dt,
	                                      codeList,
	                                      businessDays,
	                                      numberHolidays))
	                    dt = dt.subOneDayFixed();
	            }
	        }        
	        return dt;
	    }

	    private final boolean isBusinessDay(CDate date,
	                                        Vector codeList,
	                                        boolean[] businessDays) {
	        if (commonUTIL.isEmpty(codeList)) {
	            if (date.isWeekEndDay())
	                return false;
	            else
	                return true;
	        }

	        int dayOfWeek = date.getDayOfWeek();
	        // if it's not a businessday, return false
	        if (!businessDays[dayOfWeek])
	            return false;

	        for (int i = 0; i < codeList.size(); i++) {
	            String code = (String) codeList.elementAt(i);
	            HashMap cityWorkings = (HashMap) _workingDates.get(code);
	            if (commonUTIL.isEmpty(cityWorkings)
	                    || cityWorkings.get(date) == null) {

	                HashMap cityHolidays = (HashMap) _dates.get(code);
	                if ((cityHolidays != null)
	                        && (cityHolidays.get(date) != null))
	                    return false;

	                boolean v = checkRules(date, code);
	                if (!v)
	                    return false;
	            }
	        }
	        return true;
	    }

	    private final boolean isBusinessDay(CDate date,
	                                        Vector codeList,
	                                        boolean[] businessDays,
	                                        int numberHoliday) {
	        if (commonUTIL.isEmpty(codeList)) {
	            if (date.isWeekEndDay())
	                return false;
	            else
	                return true;
	        }
	        int dayOfWeek = date.getDayOfWeek();
	        // if it's not a businessday, return false
	        if (!businessDays[dayOfWeek])
	            return false;
	        int nbHolidays = 0;
	        if (numberHoliday > codeList.size())
	            numberHoliday = codeList.size();
	        for (int i = 0; i < codeList.size(); i++) {
	            boolean isBusinessDay = true;
	            String code = (String) codeList.elementAt(i);

	            HashMap cityWorkings = (HashMap) _workingDates.get(code);
	            if (commonUTIL.isEmpty(cityWorkings)
	                    || cityWorkings.get(date) == null) {
	                
	                HashMap cityHolidays = (HashMap) _dates.get(code);
	                if ((cityHolidays != null)
	                        && (cityHolidays.get(date) != null))
	                    isBusinessDay = false;

	                boolean v = checkRules(date, code);
	                if (!v)
	                    isBusinessDay = false;
	            }
	            if (isBusinessDay)
	                nbHolidays++;
	        }
	        return nbHolidays >= numberHoliday;
	    }

	    private boolean[] getBusinessDays(Vector codeList) {
	        boolean[] isBusinessDay = new boolean[8];
	        for (int i = 0; i < 8; i++)
	            isBusinessDay[i] = true;
	        if (codeList == null)
	            return isBusinessDay;
	        for (int i = 0; i < codeList.size(); i++) {
	            String code = (String) codeList.elementAt(i);
	            HolidayCode hcode = getHolidayCode(code);
	            if (hcode == null) {
	             //   Log.error(this, "HolidayCode does not exist ("
	              //          + code + ")");
	                continue;
	            }
	            isBusinessDay[hcode.getFirstNonBusinessDay()] = false;
	            isBusinessDay[hcode.getSecondNonBusinessDay()] = false;
	        }
	        return isBusinessDay;
	    }

	    

	  

	    final public String getUser() {
	        return __user;
	    }

	    final public void setUser(String s) {
	        __user = s;
	    }

	    final public int getVersion() {
	        return __version;
	    }

	    final public void setVersion(int s) {
	        __version = s;
	    }

	    public int getId() {
	        return 0;
	    }

	    public void setId(int i) {
	    }

	     

	     
	    public String getAuthName() {
	        return getClass().getName();
	    }

	    class Item {
	        CDate date;
	        String codeList;
	        int days;
	        boolean following;
	        transient int _hashCode;

	        Item(CDate _date, Vector _codeList, int _days, boolean _following) {
	            date = _date;
	            codeList = commonUTIL.collectionToString(_codeList);
	            days = _days;
	            following = _following;
	            _hashCode = -1;
	        }

	        public int hashCode() {
	            if (_hashCode == -1)
	                _hashCode = date.hashCode()
	                        ^ codeList.hashCode() ^ days;
	            return _hashCode;
	        }

	        public boolean equals(Object o) {
	            Item it = (Item) o;
	            return date.equals(it.date)
	                    && days == it.days && following == it.following
	                    && codeList.equals(it.codeList);
	        }
	    }
	    
	    /*
	     * Gets a list of HolidayWrappers from holiday object to manage persistence.
	     */
	    
	    public List<HolidayWrapper> getHolidayByCity(){
	    	
	    	List<HolidayWrapper> result = new ArrayList<HolidayWrapper>();
	    	HolidayWrapper holidayForCityCode = new HolidayWrapper();
	    	
	    	HashMap<CDate, String> datesDescription = new HashMap<CDate, String>();
	    	Map<CDate, String> datesMap = new HashMap<CDate, String>();
	        Map<CDate, String> workingDatesMap = new HashMap<CDate, String>();
	        
	    	Iterator codeNames = _codes.keySet().iterator();
	         while(codeNames.hasNext()){
	        	 String cityCode = (String)codeNames.next();
	        	 HolidayCode holCode = (HolidayCode)_codes.get(cityCode);
	             if (getDatesDescription() != null) {
	            	 datesDescription = (HashMap) getDatesDescription().get(cityCode);
	             }
	             if (getDates() != null) {
	                 HashMap<CDate, CDate> originalDatesMap = (HashMap) getDates()
	                         .get(cityCode);
	                 datesMap.clear();
	                 if (originalDatesMap != null
	                         && !originalDatesMap.isEmpty()) {
	                     Iterator<CDate> originalDatesMapIter = originalDatesMap.keySet()
	                             .iterator();
	                     while (originalDatesMapIter.hasNext()) {
	                         CDate currentKey = originalDatesMapIter.next();
	                         String currentDescription = null;
	                         if (datesDescription != null) {
	                             currentDescription = datesDescription.get(currentKey);
	                         } 
	                         datesMap.put(currentKey, currentDescription);
	                     }
	                 }
	             }
	             if (getWorkingDates() != null) {
	                 HashMap<CDate, CDate> originalDatesMap = (HashMap) getWorkingDates()
	                         .get(cityCode);
	                 workingDatesMap.clear();
	                 if (originalDatesMap != null
	                         && !originalDatesMap.isEmpty()) {
	                     Iterator<CDate> originalDatesMapIter = originalDatesMap.keySet()
	                             .iterator();
	                     while (originalDatesMapIter.hasNext()) {
	                         CDate currentKey = originalDatesMapIter.next();
	                         String currentDescription = null;
	                         if (datesDescription != null) {
	                             currentDescription = datesDescription.get(currentKey);
	                         } 
	                         workingDatesMap.put(currentKey, currentDescription);
	                     }
	                 }
	             }
	             holidayForCityCode.setCityCode(cityCode);
	             holidayForCityCode.setHolCode(holCode);
	             holidayForCityCode.setHolDates(datesMap);
	             holidayForCityCode.setWorkingDates(workingDatesMap);
	             holidayForCityCode.setHolRules(getHolidayCodeRules(cityCode));
	             
	             result.add(holidayForCityCode);
	         }
	         
	         return result;
	    }
	    
	    /*
	     * Set a list of HolidayWrappers to a holiday object. This is used for persitence.
	     */
	    
	    public void setHolidaysByCity(List<HolidayWrapper> holsByCity){
	    	
	    	if (holsByCity != null){
	    		
	    		for (Iterator iterator = holsByCity.iterator(); iterator.hasNext();) {
					
	    			HolidayWrapper holidayForCityCode = (HolidayWrapper) iterator
							.next();
	    			if (holidayForCityCode != null){
	    				
						String originalCityCode = holidayForCityCode.getCityCode();
						HolidayCode originalHolCode = holidayForCityCode.getHolCode();
						Map originalHolDates = holidayForCityCode.getHolDates();
	                    Map originalWorkingDates = holidayForCityCode.getWorkingDates();
						List originalHolRules = holidayForCityCode.getHolRules();
					
						//setting CityCode to Holiday object
						if (originalHolCode != null){
							if (!getCodes().containsKey(holidayForCityCode.getCityCode())){
								getCodes().put(originalCityCode, originalHolCode);
							}
						}
						
						//setting dates and dateDescription collections
						Iterator holDates = originalHolDates.keySet().iterator();
						while (holDates.hasNext()){
							CDate aDate = (CDate)holDates.next();
							if (aDate != null) {
								Map datesByCode = (Map)getDates().get(originalCityCode);
								if (datesByCode == null){
									datesByCode = new HashMap();
									getDates().put(originalCityCode, datesByCode);
								}
								datesByCode.put(aDate, aDate);
							}
							String aComment  = (String)originalHolDates.get(aDate);
							if (aComment != null) {
							    Map cityHolidaysDesc = (Map) getDatesDescription().get(originalCityCode);
							    if (cityHolidaysDesc == null) {
								cityHolidaysDesc = new HashMap();
								getDatesDescription().put(originalCityCode, cityHolidaysDesc);
							    }
							    cityHolidaysDesc.put(aDate, aComment);
							}
						}
						holDates = originalWorkingDates.keySet().iterator();
	                    while (holDates.hasNext()){
	                        CDate aDate = (CDate)holDates.next();
	                        if (aDate != null) {
	                            Map datesByCode = (Map)getWorkingDates().get(originalCityCode);
	                            if (datesByCode == null){
	                                datesByCode = new HashMap();
	                                getWorkingDates().put(originalCityCode, datesByCode);
	                            }
	                            datesByCode.put(aDate, aDate);
	                        }
	                        String aComment  = (String)originalHolDates.get(aDate);
	                        if (aComment != null) {
	                            Map cityHolidaysDesc = (Map) getDatesDescription().get(originalCityCode);
	                            if (cityHolidaysDesc == null) {
	                            cityHolidaysDesc = new HashMap();
	                            getDatesDescription().put(originalCityCode, cityHolidaysDesc);
	                            }
	                            cityHolidaysDesc.put(aDate, aComment);
	                        }
	                    }
						
						//setting rules
						if (originalHolRules != null){				
							List cityRules = new ArrayList();
							for (Iterator it = originalHolRules.iterator(); it
									.hasNext();) {
								HolidayRule rule = (HolidayRule) it.next();
								if (rule != null){
									cityRules.add(rule);
								}
							}
							getRules().put(originalCityCode, cityRules);	
						}
					}	
				}  		
	    	}
		}
	    
	    //Wrapper class that holds a holiday for a particular given cityCode. This is used
	    //on persistence.
	    public static class HolidayWrapper implements Serializable {
	    	
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 3259940106615682303L;
			private String __cityCode;
	    	private HolidayCode __holCode;
	    	private Map<CDate, String> __holDates;
	        private Map<CDate, String> __workingDates;
	    	private List<HolidayRule> __holRules;
	    	
	    	/**
	    	 * Default constructor
	    	 */
	    	public HolidayWrapper(){
	    	}
	    	

			public String getCityCode() {
				return __cityCode;
			}
			public void setCityCode(String code) {
				__cityCode = code;
			}
			public HolidayCode getHolCode() {
				return __holCode;
			}
			public void setHolCode(HolidayCode code) {
				__holCode = code;
			}
			public Map<CDate, String> getHolDates() {
				return __holDates;
			}
			public void setHolDates(Map<CDate, String> dates) {
				__holDates = dates;
			}
	        public Map<CDate, String> getWorkingDates() {
	            return __workingDates;
	        }
	        public void setWorkingDates(Map<CDate, String> dates) {
	            __workingDates = dates;
	        }
			public List<HolidayRule> getHolRules() {
				return __holRules;
			}
			public void setHolRules(List<HolidayRule> rules) {
				__holRules = rules;
			}
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result
				+ ((__cityCode == null) ? 0 : __cityCode.hashCode());
				result = prime * result
				+ ((__holCode == null) ? 0 : __holCode.hashCode());
				result = prime * result
				+ ((__holDates == null) ? 0 : __holDates.hashCode());
	            result = prime * result
	            + ((__workingDates == null) ? 0 : __workingDates.hashCode());
				result = prime * result
				+ ((__holRules == null) ? 0 : __holRules.hashCode());
				return result;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				final HolidayWrapper other = (HolidayWrapper) obj;
				if (__cityCode == null) {
					if (other.__cityCode != null)
						return false;
				} else if (!__cityCode.equals(other.__cityCode))
					return false;
				return true;
			} 	
	    }


}
